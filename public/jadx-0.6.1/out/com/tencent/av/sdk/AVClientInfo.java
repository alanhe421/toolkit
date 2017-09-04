package com.tencent.av.sdk;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.dynamicload.Lib.DLConstants;
import com.tencent.av.config.Common;
import com.tencent.qalsdk.base.a;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.regex.Pattern;

public class AVClientInfo {
    public static final int CHIP_ARM_V5 = 1;
    public static final int CHIP_ARM_V6 = 2;
    public static final int CHIP_ARM_V7_NENO = 4;
    public static final int CHIP_ARM_V7_NO_NENO = 3;
    public static final int CHIP_ARM_V8 = 5;
    public static final int CHIP_MIPS = 6;
    public static final int CHIP_UNKNOW = 0;
    public static final int CHIP_X86 = 7;
    static final String TAG = "AVClientInfo";
    static int mChip = 1;
    static int mCoreNumber = 1;
    static int mCpuArchitecture = 5;
    static String mFeature = "";
    static String mHardware = null;
    static boolean mIsMarvell = false;
    static boolean mIsSupportSharpAudio = true;
    static boolean mIsSupportSharpVideo = true;
    static long mMaxCpuFreq = 0;
    static int mOpenGLVersion = 2;
    static String mProcessorName = "";
    static String mVendorId = null;
    public static Context m_Context = null;
    static boolean mfReadCpuInfo = false;
    static long mgMaxCpuFreq = 0;
    static int mgNumCores = 0;

    public static void Init(Context context) {
        m_Context = context;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void getCpuInfo() {
        /*
        r0 = mfReadCpuInfo;
        r1 = 1;
        if (r0 != r1) goto L_0x0006;
    L_0x0005:
        return;
    L_0x0006:
        r4 = 0;
        r2 = 0;
        r0 = -1;
        r3 = -1082130432; // 0xffffffffbf800000 float:-1.0 double:NaN;
        r5 = new java.io.FileReader;	 Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0262, NumberFormatException -> 0x0259, all -> 0x0250 }
        r6 = "/proc/cpuinfo";
        r5.<init>(r6);	 Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0262, NumberFormatException -> 0x0259, all -> 0x0250 }
        r4 = new java.io.BufferedReader;	 Catch:{ FileNotFoundException -> 0x026e, IOException -> 0x0267, NumberFormatException -> 0x025e, all -> 0x0255 }
        r4.<init>(r5);	 Catch:{ FileNotFoundException -> 0x026e, IOException -> 0x0267, NumberFormatException -> 0x025e, all -> 0x0255 }
    L_0x0019:
        r6 = r4.readLine();	 Catch:{ FileNotFoundException -> 0x007d, IOException -> 0x00d3, NumberFormatException -> 0x0130, all -> 0x0167 }
        if (r6 != 0) goto L_0x0056;
    L_0x001f:
        r2 = 1;
        mfReadCpuInfo = r2;	 Catch:{ FileNotFoundException -> 0x007d, IOException -> 0x00d3, NumberFormatException -> 0x0130, all -> 0x0167 }
        if (r4 == 0) goto L_0x0027;
    L_0x0024:
        r4.close();	 Catch:{ Exception -> 0x01f1 }
    L_0x0027:
        if (r5 == 0) goto L_0x002c;
    L_0x0029:
        r5.close();	 Catch:{ Exception -> 0x01f1 }
    L_0x002c:
        r2 = readNumCores();
        if (r2 <= 0) goto L_0x01fd;
    L_0x0032:
        mCoreNumber = r2;
    L_0x0034:
        r0 = readMaxCpuFreq();
        r4 = 0;
        r2 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1));
        if (r2 <= 0) goto L_0x0208;
    L_0x003e:
        mMaxCpuFreq = r0;
    L_0x0040:
        r0 = mCoreNumber;
        if (r0 <= 0) goto L_0x004c;
    L_0x0044:
        r0 = mMaxCpuFreq;
        r2 = 0;
        r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r0 > 0) goto L_0x0245;
    L_0x004c:
        r0 = 0;
        mfReadCpuInfo = r0;
    L_0x004f:
        r0 = readCpuArchitecture();
        mChip = r0;
        goto L_0x0005;
    L_0x0056:
        r2 = "Processor";
        r2 = r6.startsWith(r2);	 Catch:{ FileNotFoundException -> 0x007d, IOException -> 0x00d3, NumberFormatException -> 0x0130, all -> 0x0167 }
        if (r2 == 0) goto L_0x0090;
    L_0x005f:
        r2 = 58;
        r2 = r6.indexOf(r2);	 Catch:{ FileNotFoundException -> 0x007d, IOException -> 0x00d3, NumberFormatException -> 0x0130, all -> 0x0167 }
        r7 = 1;
        if (r2 <= r7) goto L_0x0019;
    L_0x0068:
        r2 = r2 + 1;
        r7 = r6.length();	 Catch:{ FileNotFoundException -> 0x007d, IOException -> 0x00d3, NumberFormatException -> 0x0130, all -> 0x0167 }
        r2 = r6.substring(r2, r7);	 Catch:{ FileNotFoundException -> 0x007d, IOException -> 0x00d3, NumberFormatException -> 0x0130, all -> 0x0167 }
        mProcessorName = r2;	 Catch:{ FileNotFoundException -> 0x007d, IOException -> 0x00d3, NumberFormatException -> 0x0130, all -> 0x0167 }
        r2 = mProcessorName;	 Catch:{ FileNotFoundException -> 0x007d, IOException -> 0x00d3, NumberFormatException -> 0x0130, all -> 0x0167 }
        r2 = r2.trim();	 Catch:{ FileNotFoundException -> 0x007d, IOException -> 0x00d3, NumberFormatException -> 0x0130, all -> 0x0167 }
        mProcessorName = r2;	 Catch:{ FileNotFoundException -> 0x007d, IOException -> 0x00d3, NumberFormatException -> 0x0130, all -> 0x0167 }
        goto L_0x0019;
    L_0x007d:
        r2 = move-exception;
        r2 = r4;
        r4 = r5;
    L_0x0080:
        if (r2 == 0) goto L_0x0085;
    L_0x0082:
        r2.close();	 Catch:{ Exception -> 0x008b }
    L_0x0085:
        if (r4 == 0) goto L_0x002c;
    L_0x0087:
        r4.close();	 Catch:{ Exception -> 0x008b }
        goto L_0x002c;
    L_0x008b:
        r2 = move-exception;
        r2.printStackTrace();
        goto L_0x002c;
    L_0x0090:
        r2 = "CPU architecture";
        r2 = r6.startsWith(r2);	 Catch:{ FileNotFoundException -> 0x007d, IOException -> 0x00d3, NumberFormatException -> 0x0130, all -> 0x0167 }
        if (r2 == 0) goto L_0x010c;
    L_0x0099:
        r2 = 58;
        r2 = r6.indexOf(r2);	 Catch:{ FileNotFoundException -> 0x007d, IOException -> 0x00d3, NumberFormatException -> 0x0130, all -> 0x0167 }
        r7 = 1;
        if (r2 <= r7) goto L_0x00c5;
    L_0x00a2:
        r2 = r2 + 1;
        r7 = r6.length();	 Catch:{ FileNotFoundException -> 0x007d, IOException -> 0x00d3, NumberFormatException -> 0x0130, all -> 0x0167 }
        r2 = r6.substring(r2, r7);	 Catch:{ FileNotFoundException -> 0x007d, IOException -> 0x00d3, NumberFormatException -> 0x0130, all -> 0x0167 }
        r7 = r2.trim();	 Catch:{ FileNotFoundException -> 0x007d, IOException -> 0x00d3, NumberFormatException -> 0x0130, all -> 0x0167 }
        if (r7 == 0) goto L_0x00c5;
    L_0x00b2:
        r2 = r7.length();	 Catch:{ FileNotFoundException -> 0x007d, IOException -> 0x00d3, NumberFormatException -> 0x0130, all -> 0x0167 }
        if (r2 <= 0) goto L_0x00c5;
    L_0x00b8:
        r2 = "aarch64";
        r2 = r7.equalsIgnoreCase(r2);	 Catch:{ FileNotFoundException -> 0x007d, IOException -> 0x00d3, NumberFormatException -> 0x0130, all -> 0x0167 }
        if (r2 == 0) goto L_0x00e6;
    L_0x00c1:
        r2 = 8;
        mCpuArchitecture = r2;	 Catch:{ FileNotFoundException -> 0x007d, IOException -> 0x00d3, NumberFormatException -> 0x0130, all -> 0x0167 }
    L_0x00c5:
        r2 = "5TE";
        r2 = r6.contains(r2);	 Catch:{ FileNotFoundException -> 0x007d, IOException -> 0x00d3, NumberFormatException -> 0x0130, all -> 0x0167 }
        if (r2 == 0) goto L_0x0019;
    L_0x00ce:
        r2 = 5;
        mCpuArchitecture = r2;	 Catch:{ FileNotFoundException -> 0x007d, IOException -> 0x00d3, NumberFormatException -> 0x0130, all -> 0x0167 }
        goto L_0x0019;
    L_0x00d3:
        r2 = move-exception;
    L_0x00d4:
        if (r4 == 0) goto L_0x00d9;
    L_0x00d6:
        r4.close();	 Catch:{ Exception -> 0x00e0 }
    L_0x00d9:
        if (r5 == 0) goto L_0x002c;
    L_0x00db:
        r5.close();	 Catch:{ Exception -> 0x00e0 }
        goto L_0x002c;
    L_0x00e0:
        r2 = move-exception;
        r2.printStackTrace();
        goto L_0x002c;
    L_0x00e6:
        r8 = java.lang.Long.parseLong(r7);	 Catch:{ NumberFormatException -> 0x00ee, FileNotFoundException -> 0x007d, IOException -> 0x00d3, all -> 0x0167 }
        r2 = (int) r8;	 Catch:{ NumberFormatException -> 0x00ee, FileNotFoundException -> 0x007d, IOException -> 0x00d3, all -> 0x0167 }
        mCpuArchitecture = r2;	 Catch:{ NumberFormatException -> 0x00ee, FileNotFoundException -> 0x007d, IOException -> 0x00d3, all -> 0x0167 }
        goto L_0x00c5;
    L_0x00ee:
        r2 = move-exception;
        r2 = 1;
    L_0x00f0:
        r8 = r7.length();	 Catch:{ FileNotFoundException -> 0x007d, IOException -> 0x00d3, NumberFormatException -> 0x0130, all -> 0x0167 }
        if (r2 >= r8) goto L_0x00c5;
    L_0x00f6:
        r8 = 0;
        r9 = r7.length();	 Catch:{ NumberFormatException -> 0x0108, FileNotFoundException -> 0x007d, IOException -> 0x00d3, all -> 0x0167 }
        r9 = r9 - r2;
        r8 = r7.substring(r8, r9);	 Catch:{ NumberFormatException -> 0x0108, FileNotFoundException -> 0x007d, IOException -> 0x00d3, all -> 0x0167 }
        r8 = java.lang.Long.parseLong(r8);	 Catch:{ NumberFormatException -> 0x0108, FileNotFoundException -> 0x007d, IOException -> 0x00d3, all -> 0x0167 }
        r8 = (int) r8;	 Catch:{ NumberFormatException -> 0x0108, FileNotFoundException -> 0x007d, IOException -> 0x00d3, all -> 0x0167 }
        mCpuArchitecture = r8;	 Catch:{ NumberFormatException -> 0x0108, FileNotFoundException -> 0x007d, IOException -> 0x00d3, all -> 0x0167 }
        goto L_0x00c5;
    L_0x0108:
        r8 = move-exception;
        r2 = r2 + 1;
        goto L_0x00f0;
    L_0x010c:
        r2 = "Features";
        r2 = r6.startsWith(r2);	 Catch:{ FileNotFoundException -> 0x007d, IOException -> 0x00d3, NumberFormatException -> 0x0130, all -> 0x0167 }
        if (r2 == 0) goto L_0x0143;
    L_0x0115:
        r2 = 58;
        r2 = r6.indexOf(r2);	 Catch:{ FileNotFoundException -> 0x007d, IOException -> 0x00d3, NumberFormatException -> 0x0130, all -> 0x0167 }
        r7 = 1;
        if (r2 <= r7) goto L_0x0019;
    L_0x011e:
        r2 = r2 + 1;
        r7 = r6.length();	 Catch:{ FileNotFoundException -> 0x007d, IOException -> 0x00d3, NumberFormatException -> 0x0130, all -> 0x0167 }
        r2 = r6.substring(r2, r7);	 Catch:{ FileNotFoundException -> 0x007d, IOException -> 0x00d3, NumberFormatException -> 0x0130, all -> 0x0167 }
        r2 = r2.trim();	 Catch:{ FileNotFoundException -> 0x007d, IOException -> 0x00d3, NumberFormatException -> 0x0130, all -> 0x0167 }
        mFeature = r2;	 Catch:{ FileNotFoundException -> 0x007d, IOException -> 0x00d3, NumberFormatException -> 0x0130, all -> 0x0167 }
        goto L_0x0019;
    L_0x0130:
        r2 = move-exception;
    L_0x0131:
        if (r4 == 0) goto L_0x0136;
    L_0x0133:
        r4.close();	 Catch:{ Exception -> 0x013d }
    L_0x0136:
        if (r5 == 0) goto L_0x002c;
    L_0x0138:
        r5.close();	 Catch:{ Exception -> 0x013d }
        goto L_0x002c;
    L_0x013d:
        r2 = move-exception;
        r2.printStackTrace();
        goto L_0x002c;
    L_0x0143:
        r2 = "vendor_id";
        r2 = r6.startsWith(r2);	 Catch:{ FileNotFoundException -> 0x007d, IOException -> 0x00d3, NumberFormatException -> 0x0130, all -> 0x0167 }
        if (r2 == 0) goto L_0x0173;
    L_0x014c:
        r2 = 58;
        r2 = r6.indexOf(r2);	 Catch:{ FileNotFoundException -> 0x007d, IOException -> 0x00d3, NumberFormatException -> 0x0130, all -> 0x0167 }
        r7 = 1;
        if (r2 <= r7) goto L_0x0019;
    L_0x0155:
        r2 = r2 + 1;
        r7 = r6.length();	 Catch:{ FileNotFoundException -> 0x007d, IOException -> 0x00d3, NumberFormatException -> 0x0130, all -> 0x0167 }
        r2 = r6.substring(r2, r7);	 Catch:{ FileNotFoundException -> 0x007d, IOException -> 0x00d3, NumberFormatException -> 0x0130, all -> 0x0167 }
        r2 = r2.trim();	 Catch:{ FileNotFoundException -> 0x007d, IOException -> 0x00d3, NumberFormatException -> 0x0130, all -> 0x0167 }
        mVendorId = r2;	 Catch:{ FileNotFoundException -> 0x007d, IOException -> 0x00d3, NumberFormatException -> 0x0130, all -> 0x0167 }
        goto L_0x0019;
    L_0x0167:
        r0 = move-exception;
    L_0x0168:
        if (r4 == 0) goto L_0x016d;
    L_0x016a:
        r4.close();	 Catch:{ Exception -> 0x01f7 }
    L_0x016d:
        if (r5 == 0) goto L_0x0172;
    L_0x016f:
        r5.close();	 Catch:{ Exception -> 0x01f7 }
    L_0x0172:
        throw r0;
    L_0x0173:
        r2 = "Hardware";
        r2 = r6.startsWith(r2);	 Catch:{ FileNotFoundException -> 0x007d, IOException -> 0x00d3, NumberFormatException -> 0x0130, all -> 0x0167 }
        if (r2 == 0) goto L_0x0190;
    L_0x017c:
        r2 = r6.trim();	 Catch:{ FileNotFoundException -> 0x007d, IOException -> 0x00d3, NumberFormatException -> 0x0130, all -> 0x0167 }
        mHardware = r2;	 Catch:{ FileNotFoundException -> 0x007d, IOException -> 0x00d3, NumberFormatException -> 0x0130, all -> 0x0167 }
        r2 = "PXA";
        r2 = r6.contains(r2);	 Catch:{ FileNotFoundException -> 0x007d, IOException -> 0x00d3, NumberFormatException -> 0x0130, all -> 0x0167 }
        if (r2 == 0) goto L_0x0019;
    L_0x018b:
        r2 = 1;
        mIsMarvell = r2;	 Catch:{ FileNotFoundException -> 0x007d, IOException -> 0x00d3, NumberFormatException -> 0x0130, all -> 0x0167 }
        goto L_0x0019;
    L_0x0190:
        r2 = "processor";
        r2 = r6.startsWith(r2);	 Catch:{ FileNotFoundException -> 0x007d, IOException -> 0x00d3, NumberFormatException -> 0x0130, all -> 0x0167 }
        if (r2 == 0) goto L_0x01c6;
    L_0x0199:
        r2 = 58;
        r2 = r6.indexOf(r2);	 Catch:{ FileNotFoundException -> 0x007d, IOException -> 0x00d3, NumberFormatException -> 0x0130, all -> 0x0167 }
        r7 = 1;
        if (r2 <= r7) goto L_0x0019;
    L_0x01a2:
        r2 = r2 + 1;
        r7 = r6.length();	 Catch:{ FileNotFoundException -> 0x007d, IOException -> 0x00d3, NumberFormatException -> 0x0130, all -> 0x0167 }
        r2 = r6.substring(r2, r7);	 Catch:{ FileNotFoundException -> 0x007d, IOException -> 0x00d3, NumberFormatException -> 0x0130, all -> 0x0167 }
        r2 = r2.trim();	 Catch:{ FileNotFoundException -> 0x007d, IOException -> 0x00d3, NumberFormatException -> 0x0130, all -> 0x0167 }
        r6 = java.lang.Long.parseLong(r2);	 Catch:{ NumberFormatException -> 0x024a, FileNotFoundException -> 0x007d, IOException -> 0x00d3, all -> 0x0167 }
        r8 = 0;
        r2 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
        if (r2 < 0) goto L_0x0019;
    L_0x01ba:
        r8 = 1;
        r8 = r8 + r6;
        r2 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1));
        if (r2 <= 0) goto L_0x0019;
    L_0x01c1:
        r0 = 1;
        r0 = r0 + r6;
        goto L_0x0019;
    L_0x01c6:
        r2 = "BogoMIPS";
        r2 = r6.startsWith(r2);	 Catch:{ FileNotFoundException -> 0x007d, IOException -> 0x00d3, NumberFormatException -> 0x0130, all -> 0x0167 }
        if (r2 == 0) goto L_0x0019;
    L_0x01cf:
        r2 = 58;
        r2 = r6.indexOf(r2);	 Catch:{ FileNotFoundException -> 0x007d, IOException -> 0x00d3, NumberFormatException -> 0x0130, all -> 0x0167 }
        r7 = 1;
        if (r2 <= r7) goto L_0x0019;
    L_0x01d8:
        r2 = r2 + 1;
        r7 = r6.length();	 Catch:{ FileNotFoundException -> 0x007d, IOException -> 0x00d3, NumberFormatException -> 0x0130, all -> 0x0167 }
        r2 = r6.substring(r2, r7);	 Catch:{ FileNotFoundException -> 0x007d, IOException -> 0x00d3, NumberFormatException -> 0x0130, all -> 0x0167 }
        r2 = r2.trim();	 Catch:{ FileNotFoundException -> 0x007d, IOException -> 0x00d3, NumberFormatException -> 0x0130, all -> 0x0167 }
        r2 = java.lang.Float.parseFloat(r2);	 Catch:{ NumberFormatException -> 0x024d, FileNotFoundException -> 0x007d, IOException -> 0x00d3, all -> 0x0167 }
        r6 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1));
        if (r6 <= 0) goto L_0x0272;
    L_0x01ee:
        r3 = r2;
        goto L_0x0019;
    L_0x01f1:
        r2 = move-exception;
        r2.printStackTrace();
        goto L_0x002c;
    L_0x01f7:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0172;
    L_0x01fd:
        r4 = 0;
        r2 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1));
        if (r2 <= 0) goto L_0x0034;
    L_0x0203:
        r0 = (int) r0;
        mCoreNumber = r0;
        goto L_0x0034;
    L_0x0208:
        r0 = 0;
        r0 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1));
        if (r0 <= 0) goto L_0x0040;
    L_0x020d:
        r0 = 1150681088; // 0x44960000 float:1200.0 double:5.68511995E-315;
        r0 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1));
        if (r0 >= 0) goto L_0x0225;
    L_0x0213:
        r0 = (double) r3;
        r2 = 4608083138725491507; // 0x3ff3333333333333 float:4.172325E-8 double:1.2;
        r0 = r0 * r2;
        r2 = 4652007308841189376; // 0x408f400000000000 float:0.0 double:1000.0;
        r0 = r0 * r2;
        r0 = (long) r0;
        mMaxCpuFreq = r0;
        goto L_0x0040;
    L_0x0225:
        r0 = 1157234688; // 0x44fa0000 float:2000.0 double:5.717499035E-315;
        r0 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1));
        if (r0 <= 0) goto L_0x023d;
    L_0x022b:
        r0 = (double) r3;
        r2 = 4610785298501913805; // 0x3ffccccccccccccd float:-1.07374184E8 double:1.8;
        r0 = r0 / r2;
        r2 = 4652007308841189376; // 0x408f400000000000 float:0.0 double:1000.0;
        r0 = r0 * r2;
        r0 = (long) r0;
        mMaxCpuFreq = r0;
        goto L_0x0040;
    L_0x023d:
        r0 = 1148846080; // 0x447a0000 float:1000.0 double:5.676053805E-315;
        r0 = r0 * r3;
        r0 = (long) r0;
        mMaxCpuFreq = r0;
        goto L_0x0040;
    L_0x0245:
        r0 = 1;
        mfReadCpuInfo = r0;
        goto L_0x004f;
    L_0x024a:
        r2 = move-exception;
        goto L_0x0019;
    L_0x024d:
        r2 = move-exception;
        goto L_0x0019;
    L_0x0250:
        r0 = move-exception;
        r5 = r4;
        r4 = r2;
        goto L_0x0168;
    L_0x0255:
        r0 = move-exception;
        r4 = r2;
        goto L_0x0168;
    L_0x0259:
        r5 = move-exception;
        r5 = r4;
        r4 = r2;
        goto L_0x0131;
    L_0x025e:
        r4 = move-exception;
        r4 = r2;
        goto L_0x0131;
    L_0x0262:
        r5 = move-exception;
        r5 = r4;
        r4 = r2;
        goto L_0x00d4;
    L_0x0267:
        r4 = move-exception;
        r4 = r2;
        goto L_0x00d4;
    L_0x026b:
        r5 = move-exception;
        goto L_0x0080;
    L_0x026e:
        r4 = move-exception;
        r4 = r5;
        goto L_0x0080;
    L_0x0272:
        r2 = r3;
        goto L_0x01ee;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.av.sdk.AVClientInfo.getCpuInfo():void");
    }

    static long readMaxCpuFreq() {
        Throwable th;
        Reader reader;
        BufferedReader bufferedReader = null;
        if (mCoreNumber < 1) {
            mCoreNumber = 8;
        }
        FileReader fileReader = null;
        long j = 0;
        for (int i = 0; i < mCoreNumber; i++) {
            try {
                String str = "";
                Reader fileReader2 = new FileReader("/sys/devices/system/cpu/cpu" + i + "/cpufreq/cpuinfo_max_freq");
                try {
                    BufferedReader bufferedReader2 = new BufferedReader(fileReader2);
                    if (bufferedReader2 != null) {
                        try {
                            String str2;
                            Object readLine = bufferedReader2.readLine();
                            if (TextUtils.isEmpty(readLine)) {
                                str2 = str;
                            } else {
                                str2 = readLine.trim();
                            }
                            if (str2 != null && str2.length() > 0) {
                                j = Long.parseLong(str2);
                            }
                        } catch (Exception e) {
                            bufferedReader = bufferedReader2;
                            fileReader = fileReader2;
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (Exception e2) {
                                    e2.printStackTrace();
                                    j = 0;
                                }
                            }
                            if (fileReader != null) {
                                fileReader.close();
                            }
                            j = 0;
                            if (j <= 0) {
                                return j;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            bufferedReader = bufferedReader2;
                            fileReader = fileReader2;
                        }
                    }
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                        } catch (Exception e3) {
                            e3.printStackTrace();
                            bufferedReader = bufferedReader2;
                            reader = fileReader2;
                        }
                    }
                    if (fileReader2 != null) {
                        fileReader2.close();
                    }
                    bufferedReader = bufferedReader2;
                    fileReader = fileReader2;
                } catch (Exception e4) {
                    reader = fileReader2;
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    if (fileReader != null) {
                        fileReader.close();
                    }
                    j = 0;
                    if (j <= 0) {
                        return j;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    reader = fileReader2;
                }
            } catch (Exception e5) {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (fileReader != null) {
                    fileReader.close();
                }
                j = 0;
                if (j <= 0) {
                    return j;
                }
            } catch (Throwable th4) {
                th = th4;
            }
            if (j <= 0) {
                break;
            }
        }
        return j;
        if (bufferedReader != null) {
            try {
                bufferedReader.close();
            } catch (Exception e32) {
                e32.printStackTrace();
                throw th;
            }
        }
        if (fileReader != null) {
            fileReader.close();
        }
        throw th;
    }

    public static long getCurrentCpuFreq() {
        FileReader fileReader;
        FileReader fileReader2;
        Throwable th;
        BufferedReader bufferedReader = null;
        long j = 0;
        BufferedReader bufferedReader2;
        try {
            String str = "";
            fileReader = new FileReader("/sys/devices/system/cpu/cpu0/cpufreq/scaling_cur_freq");
            try {
                bufferedReader2 = new BufferedReader(fileReader);
                if (bufferedReader2 != null) {
                    try {
                        String str2;
                        Object readLine = bufferedReader2.readLine();
                        if (TextUtils.isEmpty(readLine)) {
                            str2 = str;
                        } else {
                            str2 = readLine.trim();
                        }
                        if (str2 != null && str2.length() > 0) {
                            j = Long.parseLong(str2);
                        }
                    } catch (FileNotFoundException e) {
                        fileReader2 = fileReader;
                        if (bufferedReader2 != null) {
                            try {
                                bufferedReader2.close();
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                        }
                        if (fileReader2 != null) {
                            fileReader2.close();
                        }
                        return j;
                    } catch (IOException e3) {
                        bufferedReader = bufferedReader2;
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (Exception e22) {
                                e22.printStackTrace();
                            }
                        }
                        if (fileReader != null) {
                            fileReader.close();
                        }
                        return j;
                    } catch (NumberFormatException e4) {
                        bufferedReader = bufferedReader2;
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (Exception e222) {
                                e222.printStackTrace();
                            }
                        }
                        if (fileReader != null) {
                            fileReader.close();
                        }
                        return j;
                    } catch (Throwable th2) {
                        th = th2;
                        bufferedReader = bufferedReader2;
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (Exception e5) {
                                e5.printStackTrace();
                                throw th;
                            }
                        }
                        if (fileReader != null) {
                            fileReader.close();
                        }
                        throw th;
                    }
                }
                if (bufferedReader2 != null) {
                    try {
                        bufferedReader2.close();
                    } catch (Exception e2222) {
                        e2222.printStackTrace();
                    }
                }
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (FileNotFoundException e6) {
                bufferedReader2 = null;
                fileReader2 = fileReader;
                if (bufferedReader2 != null) {
                    bufferedReader2.close();
                }
                if (fileReader2 != null) {
                    fileReader2.close();
                }
                return j;
            } catch (IOException e7) {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (fileReader != null) {
                    fileReader.close();
                }
                return j;
            } catch (NumberFormatException e8) {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (fileReader != null) {
                    fileReader.close();
                }
                return j;
            } catch (Throwable th3) {
                th = th3;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (fileReader != null) {
                    fileReader.close();
                }
                throw th;
            }
        } catch (FileNotFoundException e9) {
            bufferedReader2 = null;
            if (bufferedReader2 != null) {
                bufferedReader2.close();
            }
            if (fileReader2 != null) {
                fileReader2.close();
            }
            return j;
        } catch (IOException e10) {
            fileReader = null;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (fileReader != null) {
                fileReader.close();
            }
            return j;
        } catch (NumberFormatException e11) {
            fileReader = null;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (fileReader != null) {
                fileReader.close();
            }
            return j;
        } catch (Throwable th4) {
            th = th4;
            fileReader = null;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (fileReader != null) {
                fileReader.close();
            }
            throw th;
        }
        return j;
    }

    static int readNumCores() {
        try {
            return new File("/sys/devices/system/cpu/").listFiles(new FileFilter() {
                public boolean accept(File file) {
                    if (Pattern.matches("cpu[0-9]", file.getName())) {
                        return true;
                    }
                    return false;
                }
            }).length;
        } catch (Exception e) {
            return 0;
        }
    }

    static int readCpuArchitecture() {
        if (mProcessorName.contains("ARMv6")) {
            return 2;
        }
        if (Build.CPU_ABI.equalsIgnoreCase("armeabi-v7a")) {
            return 4;
        }
        if (Build.CPU_ABI.equalsIgnoreCase(DLConstants.CPU_ARMEABI)) {
            return 2;
        }
        if (mCpuArchitecture == 7 && mFeature.indexOf("neon") < 0) {
            long maxCpuFreq = getMaxCpuFreq();
            int numCores = getNumCores();
            if (maxCpuFreq < 1100000 || numCores < 2) {
                return 3;
            }
        }
        if (Build.CPU_ABI.equalsIgnoreCase(DLConstants.CPU_X86)) {
            return 7;
        }
        switch (mCpuArchitecture) {
            case 5:
                return 1;
            case 6:
                return 2;
            case 7:
                return 4;
            case 8:
                return 5;
            default:
                if (mVendorId == null || (!mVendorId.equalsIgnoreCase("AuthenticAMD") && !mVendorId.equalsIgnoreCase("GenuineIntel"))) {
                    return 0;
                }
                return 7;
        }
    }

    public static boolean isLowLevelDevice() {
        if (VERSION.SDK_INT < 11) {
            return true;
        }
        if (mgNumCores == 0) {
            mgNumCores = getNumCores();
        }
        if (mgMaxCpuFreq == 0) {
            mgMaxCpuFreq = getMaxCpuFreq();
        }
        if (mgNumCores > 1 || mgMaxCpuFreq > 1025000) {
            return false;
        }
        return true;
    }

    public static String getDeviceName() {
        return Build.MODEL;
    }

    public static String getDeviceNameForConfigSystem() {
        return Build.MANUFACTURER + "_" + Build.MODEL;
    }

    public int getCameraFacing() {
        if (VERSION.SDK_INT > 9) {
            return 2;
        }
        return 1;
    }

    public static long getMaxCpuFreq() {
        getCpuInfo();
        return mMaxCpuFreq;
    }

    public static int getNumCores() {
        getCpuInfo();
        return mCoreNumber;
    }

    public static int getCpuArchitecture() {
        getCpuInfo();
        return mChip;
    }

    public static String getCPUName() {
        getCpuInfo();
        return mProcessorName;
    }

    public static String getCpuReport() {
        getCpuInfo();
        return "prcs(" + mProcessorName + ") " + "arch(" + mCpuArchitecture + ") " + "hard(" + mHardware + ") " + "chip(" + mChip + ") " + "freq(" + mMaxCpuFreq + ") " + "num(" + mCoreNumber + ")";
    }

    public static String getModelReport() {
        getCpuInfo();
        return "model(" + Build.MODEL + ") " + "Mnfc(" + Build.MANUFACTURER + ") " + "dev(" + VERSION.INCREMENTAL + ") " + "sdk(" + VERSION.SDK_INT + ") " + "gl(" + mOpenGLVersion + ")";
    }

    public static int getDeviceType() {
        return 101;
    }

    public static String getOSVersion() {
        return VERSION.RELEASE;
    }

    public static String getAppVersion() {
        return Common.getVersion(m_Context);
    }

    public static int getOsType() {
        try {
            String str = VERSION.RELEASE;
            if (str.equals("L")) {
                return 118;
            }
            if (VERSION.SDK_INT == 20) {
                return 118;
            }
            if (VERSION.SDK_INT > 20) {
                return 200;
            }
            char charAt = str.charAt(0);
            char charAt2 = str.charAt(2);
            char c = '\u0000';
            if (str.length() >= 5) {
                c = str.charAt(4);
            }
            switch (charAt) {
                case '1':
                    if (charAt2 == '1') {
                        return 101;
                    }
                    if (charAt2 == '5') {
                        return 102;
                    }
                    if (charAt2 == '6') {
                        return 103;
                    }
                    return 200;
                case '2':
                    if (charAt2 == '0') {
                        if (c == '1') {
                            return 105;
                        }
                        return 104;
                    } else if (charAt2 == '1') {
                        return 106;
                    } else {
                        if (charAt2 == '2') {
                            if (c == '1') {
                                return 108;
                            }
                            return 107;
                        } else if (charAt2 != '3' || c < '0' || c > '9') {
                            return 200;
                        } else {
                            return 109;
                        }
                    }
                case '3':
                    if (charAt2 == '0') {
                        return 110;
                    }
                    if (charAt2 == '1') {
                        return 111;
                    }
                    if (charAt2 == '2') {
                        return 112;
                    }
                    return 200;
                case '4':
                    if (charAt2 == '0') {
                        return 113;
                    }
                    if (charAt2 == '1') {
                        return 114;
                    }
                    if (charAt2 == '2') {
                        return a.cd;
                    }
                    if (charAt2 == '3') {
                        return 116;
                    }
                    if (charAt2 == '4') {
                        return Opcodes.INVOKE_SUPER_RANGE;
                    }
                    return 200;
                case '5':
                    if (charAt2 == '0') {
                        return 118;
                    }
                    return 200;
                default:
                    return 200;
            }
        } catch (Exception e) {
            return 200;
        }
    }
}
