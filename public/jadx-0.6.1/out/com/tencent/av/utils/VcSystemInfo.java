package com.tencent.av.utils;

import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.dynamicload.Lib.DLConstants;
import com.etrump.jni.ETConverter;
import com.tencent.qalsdk.base.a;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

public class VcSystemInfo {
    public static final int CHIP_ARM_V5 = 1;
    public static final int CHIP_ARM_V6 = 2;
    public static final int CHIP_ARM_V7_NENO = 4;
    public static final int CHIP_ARM_V7_NO_NENO = 3;
    public static final int CHIP_ARM_V8 = 5;
    public static final int CHIP_MIPS = 6;
    public static final int CHIP_UNKNOW = 0;
    public static final int CHIP_X86 = 7;
    static final String TAG = "VcSystemInfo";
    static int mChip = 1;
    static int mCoreNumber = 1;
    static int mCpuArchitecture = 5;
    static long mCurrCpuFreq = 0;
    static String mFeature = "";
    static String mHardware = null;
    static boolean mIsMarvell = false;
    static boolean mIsSupportSharpAudio = true;
    static boolean mIsSupportSharpVideo = true;
    static long mMaxCpuFreq = 0;
    static int mOpenGLVersion = 2;
    static String mProcessorName = "";
    static String mVendorId = null;
    static boolean mfReadCpuInfo = false;
    static long mgMaxCpuFreq = 0;
    static int mgNumCores = 0;
    int mScreenHeight = 480;
    int mScreenWidth = ErrorCode.ERROR_SDKENGINE_ISCOMPATIBLE;
    public int mdispHeight = ETConverter.ET_CONVERTER_GLYPH_CACHE_SIZE_MASK;
    public int mdispWidth = ErrorCode.ERROR_SDKENGINE_ISCOMPATIBLE;

    public void setScreenSize(int i, int i2) {
        this.mScreenWidth = i;
        this.mScreenHeight = i2;
    }

    public void setDispSize(int i, int i2) {
        this.mdispWidth = i;
        this.mdispHeight = i2;
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
        r6 = 0;
        r2 = 0;
        r0 = -1;
        r3 = -1082130432; // 0xffffffffbf800000 float:-1.0 double:NaN;
        r4 = new java.io.FileReader;	 Catch:{ FileNotFoundException -> 0x0434, IOException -> 0x042b, NumberFormatException -> 0x0422, all -> 0x0419 }
        r5 = "/proc/cpuinfo";
        r4.<init>(r5);	 Catch:{ FileNotFoundException -> 0x0434, IOException -> 0x042b, NumberFormatException -> 0x0422, all -> 0x0419 }
        r5 = new java.io.BufferedReader;	 Catch:{ FileNotFoundException -> 0x0438, IOException -> 0x0430, NumberFormatException -> 0x0427, all -> 0x041e }
        r5.<init>(r4);	 Catch:{ FileNotFoundException -> 0x0438, IOException -> 0x0430, NumberFormatException -> 0x0427, all -> 0x041e }
    L_0x0019:
        r6 = r5.readLine();	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        if (r6 != 0) goto L_0x0107;
    L_0x001f:
        r2 = 1;
        mfReadCpuInfo = r2;	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        if (r5 == 0) goto L_0x0027;
    L_0x0024:
        r5.close();	 Catch:{ Exception -> 0x037d }
    L_0x0027:
        if (r4 == 0) goto L_0x002c;
    L_0x0029:
        r4.close();	 Catch:{ Exception -> 0x037d }
    L_0x002c:
        r4 = readMaxCpuFreq();
        r6 = 0;
        r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r2 <= 0) goto L_0x0389;
    L_0x0036:
        mMaxCpuFreq = r4;
        r2 = com.tencent.av.utils.QLog.isColorLevel();
        if (r2 == 0) goto L_0x005b;
    L_0x003e:
        r2 = "VcSystemInfo";
        r3 = 0;
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = "[CpuInfo] mMaxCpuFreq=";
        r4 = r4.append(r5);
        r6 = mMaxCpuFreq;
        r4 = r4.append(r6);
        r4 = r4.toString();
        com.tencent.av.utils.QLog.d(r2, r3, r4);
    L_0x005b:
        r2 = getCurrentCpuFreq();
        r4 = 0;
        r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r4 <= 0) goto L_0x008a;
    L_0x0065:
        mCurrCpuFreq = r2;
        r2 = com.tencent.av.utils.QLog.isColorLevel();
        if (r2 == 0) goto L_0x008a;
    L_0x006d:
        r2 = "VcSystemInfo";
        r3 = 0;
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = "[CpuInfo] mCurrCpuFreq=";
        r4 = r4.append(r5);
        r6 = mCurrCpuFreq;
        r4 = r4.append(r6);
        r4 = r4.toString();
        com.tencent.av.utils.QLog.d(r2, r3, r4);
    L_0x008a:
        r2 = readNumCores();
        if (r2 <= 0) goto L_0x03e0;
    L_0x0090:
        mCoreNumber = r2;
        r0 = com.tencent.av.utils.QLog.isColorLevel();
        if (r0 == 0) goto L_0x00b5;
    L_0x0098:
        r0 = "VcSystemInfo";
        r1 = 0;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "[CpuInfo] mCoreNumber=";
        r2 = r2.append(r3);
        r3 = mCoreNumber;
        r2 = r2.append(r3);
        r2 = r2.toString();
        com.tencent.av.utils.QLog.d(r0, r1, r2);
    L_0x00b5:
        r0 = readCpuArchitecture();
        mChip = r0;
        r0 = readSupportSharpAudio();
        mIsSupportSharpAudio = r0;
        r0 = mIsSupportSharpAudio;
        if (r0 == 0) goto L_0x040e;
    L_0x00c5:
        r0 = 1;
        mIsSupportSharpVideo = r0;
    L_0x00c8:
        r0 = com.tencent.av.utils.QLog.isColorLevel();
        if (r0 == 0) goto L_0x0005;
    L_0x00ce:
        r0 = "VcSystemInfo";
        r1 = 0;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "[CpuInfo] Chip=";
        r2 = r2.append(r3);
        r3 = mChip;
        r2 = r2.append(r3);
        r3 = ", Video=";
        r2 = r2.append(r3);
        r3 = mIsSupportSharpVideo;
        r2 = r2.append(r3);
        r3 = ", Audio=";
        r2 = r2.append(r3);
        r3 = mIsSupportSharpAudio;
        r2 = r2.append(r3);
        r2 = r2.toString();
        com.tencent.av.utils.QLog.d(r0, r1, r2);
        goto L_0x0005;
    L_0x0107:
        r2 = "Processor";
        r2 = r6.startsWith(r2);	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        if (r2 == 0) goto L_0x0172;
    L_0x0110:
        r2 = 58;
        r2 = r6.indexOf(r2);	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        r7 = 1;
        if (r2 <= r7) goto L_0x0019;
    L_0x0119:
        r2 = r2 + 1;
        r7 = r6.length();	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        r2 = r6.substring(r2, r7);	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        mProcessorName = r2;	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        r2 = mProcessorName;	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        r2 = r2.trim();	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        mProcessorName = r2;	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        r2 = com.tencent.av.utils.QLog.isColorLevel();	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        if (r2 == 0) goto L_0x0019;
    L_0x0133:
        r2 = "VcSystemInfo";
        r7 = 0;
        r8 = new java.lang.StringBuilder;	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        r8.<init>();	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        r9 = "[CpuInfo] mProcessorName=";
        r8 = r8.append(r9);	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        r9 = mProcessorName;	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        r8 = r8.append(r9);	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        r9 = " | ";
        r8 = r8.append(r9);	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        r6 = r8.append(r6);	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        r6 = r6.toString();	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        com.tencent.av.utils.QLog.d(r2, r7, r6);	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        goto L_0x0019;
    L_0x015d:
        r2 = move-exception;
        r2 = r4;
        r4 = r5;
    L_0x0160:
        if (r4 == 0) goto L_0x0165;
    L_0x0162:
        r4.close();	 Catch:{ Exception -> 0x016c }
    L_0x0165:
        if (r2 == 0) goto L_0x002c;
    L_0x0167:
        r2.close();	 Catch:{ Exception -> 0x016c }
        goto L_0x002c;
    L_0x016c:
        r2 = move-exception;
        r2.printStackTrace();
        goto L_0x002c;
    L_0x0172:
        r2 = "CPU architecture";
        r2 = r6.startsWith(r2);	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        if (r2 == 0) goto L_0x020e;
    L_0x017b:
        r2 = 58;
        r2 = r6.indexOf(r2);	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        r7 = 1;
        if (r2 <= r7) goto L_0x01a1;
    L_0x0184:
        r2 = r2 + 1;
        r7 = r6.length();	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        r2 = r6.substring(r2, r7);	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        r7 = r2.trim();	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        if (r7 == 0) goto L_0x01a1;
    L_0x0194:
        r2 = r7.length();	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        if (r2 <= 0) goto L_0x01a1;
    L_0x019a:
        r8 = java.lang.Long.parseLong(r7);	 Catch:{ NumberFormatException -> 0x01f0, FileNotFoundException -> 0x015d, IOException -> 0x01dd, all -> 0x02c5 }
        r2 = (int) r8;	 Catch:{ NumberFormatException -> 0x01f0, FileNotFoundException -> 0x015d, IOException -> 0x01dd, all -> 0x02c5 }
        mCpuArchitecture = r2;	 Catch:{ NumberFormatException -> 0x01f0, FileNotFoundException -> 0x015d, IOException -> 0x01dd, all -> 0x02c5 }
    L_0x01a1:
        r2 = "5TE";
        r2 = r6.contains(r2);	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        if (r2 == 0) goto L_0x01ad;
    L_0x01aa:
        r2 = 5;
        mCpuArchitecture = r2;	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
    L_0x01ad:
        r2 = com.tencent.av.utils.QLog.isColorLevel();	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        if (r2 == 0) goto L_0x0019;
    L_0x01b3:
        r2 = "VcSystemInfo";
        r7 = 0;
        r8 = new java.lang.StringBuilder;	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        r8.<init>();	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        r9 = "[CpuInfo] mCpuArchitecture=";
        r8 = r8.append(r9);	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        r9 = mCpuArchitecture;	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        r8 = r8.append(r9);	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        r9 = " | ";
        r8 = r8.append(r9);	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        r6 = r8.append(r6);	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        r6 = r6.toString();	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        com.tencent.av.utils.QLog.d(r2, r7, r6);	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        goto L_0x0019;
    L_0x01dd:
        r2 = move-exception;
    L_0x01de:
        if (r5 == 0) goto L_0x01e3;
    L_0x01e0:
        r5.close();	 Catch:{ Exception -> 0x01ea }
    L_0x01e3:
        if (r4 == 0) goto L_0x002c;
    L_0x01e5:
        r4.close();	 Catch:{ Exception -> 0x01ea }
        goto L_0x002c;
    L_0x01ea:
        r2 = move-exception;
        r2.printStackTrace();
        goto L_0x002c;
    L_0x01f0:
        r2 = move-exception;
        r2 = 1;
    L_0x01f2:
        r8 = r7.length();	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        if (r2 >= r8) goto L_0x01a1;
    L_0x01f8:
        r8 = 0;
        r9 = r7.length();	 Catch:{ NumberFormatException -> 0x020a, FileNotFoundException -> 0x015d, IOException -> 0x01dd, all -> 0x02c5 }
        r9 = r9 - r2;
        r8 = r7.substring(r8, r9);	 Catch:{ NumberFormatException -> 0x020a, FileNotFoundException -> 0x015d, IOException -> 0x01dd, all -> 0x02c5 }
        r8 = java.lang.Long.parseLong(r8);	 Catch:{ NumberFormatException -> 0x020a, FileNotFoundException -> 0x015d, IOException -> 0x01dd, all -> 0x02c5 }
        r8 = (int) r8;	 Catch:{ NumberFormatException -> 0x020a, FileNotFoundException -> 0x015d, IOException -> 0x01dd, all -> 0x02c5 }
        mCpuArchitecture = r8;	 Catch:{ NumberFormatException -> 0x020a, FileNotFoundException -> 0x015d, IOException -> 0x01dd, all -> 0x02c5 }
        goto L_0x01a1;
    L_0x020a:
        r8 = move-exception;
        r2 = r2 + 1;
        goto L_0x01f2;
    L_0x020e:
        r2 = "Features";
        r2 = r6.startsWith(r2);	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        if (r2 == 0) goto L_0x0273;
    L_0x0217:
        r2 = 58;
        r2 = r6.indexOf(r2);	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        r7 = 1;
        if (r2 <= r7) goto L_0x0230;
    L_0x0220:
        r2 = r2 + 1;
        r7 = r6.length();	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        r2 = r6.substring(r2, r7);	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        r2 = r2.trim();	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        mFeature = r2;	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
    L_0x0230:
        r2 = com.tencent.av.utils.QLog.isColorLevel();	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        if (r2 == 0) goto L_0x0019;
    L_0x0236:
        r2 = "VcSystemInfo";
        r7 = 0;
        r8 = new java.lang.StringBuilder;	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        r8.<init>();	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        r9 = "[CpuInfo] mFeature=";
        r8 = r8.append(r9);	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        r9 = mFeature;	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        r8 = r8.append(r9);	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        r9 = " | ";
        r8 = r8.append(r9);	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        r6 = r8.append(r6);	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        r6 = r6.toString();	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        com.tencent.av.utils.QLog.d(r2, r7, r6);	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        goto L_0x0019;
    L_0x0260:
        r2 = move-exception;
    L_0x0261:
        if (r5 == 0) goto L_0x0266;
    L_0x0263:
        r5.close();	 Catch:{ Exception -> 0x026d }
    L_0x0266:
        if (r4 == 0) goto L_0x002c;
    L_0x0268:
        r4.close();	 Catch:{ Exception -> 0x026d }
        goto L_0x002c;
    L_0x026d:
        r2 = move-exception;
        r2.printStackTrace();
        goto L_0x002c;
    L_0x0273:
        r2 = "vendor_id";
        r2 = r6.startsWith(r2);	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        if (r2 == 0) goto L_0x02d1;
    L_0x027c:
        r2 = 58;
        r2 = r6.indexOf(r2);	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        r7 = 1;
        if (r2 <= r7) goto L_0x0295;
    L_0x0285:
        r2 = r2 + 1;
        r7 = r6.length();	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        r2 = r6.substring(r2, r7);	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        r2 = r2.trim();	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        mVendorId = r2;	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
    L_0x0295:
        r2 = com.tencent.av.utils.QLog.isColorLevel();	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        if (r2 == 0) goto L_0x0019;
    L_0x029b:
        r2 = "VcSystemInfo";
        r7 = 0;
        r8 = new java.lang.StringBuilder;	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        r8.<init>();	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        r9 = "[CpuInfo] mVendorId=";
        r8 = r8.append(r9);	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        r9 = mVendorId;	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        r8 = r8.append(r9);	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        r9 = " | ";
        r8 = r8.append(r9);	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        r6 = r8.append(r6);	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        r6 = r6.toString();	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        com.tencent.av.utils.QLog.d(r2, r7, r6);	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        goto L_0x0019;
    L_0x02c5:
        r0 = move-exception;
    L_0x02c6:
        if (r5 == 0) goto L_0x02cb;
    L_0x02c8:
        r5.close();	 Catch:{ Exception -> 0x0383 }
    L_0x02cb:
        if (r4 == 0) goto L_0x02d0;
    L_0x02cd:
        r4.close();	 Catch:{ Exception -> 0x0383 }
    L_0x02d0:
        throw r0;
    L_0x02d1:
        r2 = "Hardware";
        r2 = r6.startsWith(r2);	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        if (r2 == 0) goto L_0x031c;
    L_0x02da:
        r2 = r6.trim();	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        mHardware = r2;	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        r2 = "PXA";
        r2 = r6.contains(r2);	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        if (r2 == 0) goto L_0x02ec;
    L_0x02e9:
        r2 = 1;
        mIsMarvell = r2;	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
    L_0x02ec:
        r2 = com.tencent.av.utils.QLog.isColorLevel();	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        if (r2 == 0) goto L_0x0019;
    L_0x02f2:
        r2 = "VcSystemInfo";
        r7 = 0;
        r8 = new java.lang.StringBuilder;	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        r8.<init>();	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        r9 = "[CpuInfo] mIsMarvell=";
        r8 = r8.append(r9);	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        r9 = mIsMarvell;	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        r8 = r8.append(r9);	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        r9 = " | ";
        r8 = r8.append(r9);	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        r6 = r8.append(r6);	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        r6 = r6.toString();	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        com.tencent.av.utils.QLog.d(r2, r7, r6);	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        goto L_0x0019;
    L_0x031c:
        r2 = "processor";
        r2 = r6.startsWith(r2);	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        if (r2 == 0) goto L_0x0352;
    L_0x0325:
        r2 = 58;
        r2 = r6.indexOf(r2);	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        r7 = 1;
        if (r2 <= r7) goto L_0x0019;
    L_0x032e:
        r2 = r2 + 1;
        r7 = r6.length();	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        r2 = r6.substring(r2, r7);	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        r2 = r2.trim();	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        r6 = java.lang.Long.parseLong(r2);	 Catch:{ NumberFormatException -> 0x0413, FileNotFoundException -> 0x015d, IOException -> 0x01dd, all -> 0x02c5 }
        r8 = 0;
        r2 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
        if (r2 < 0) goto L_0x0019;
    L_0x0346:
        r8 = 1;
        r8 = r8 + r6;
        r2 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1));
        if (r2 <= 0) goto L_0x0019;
    L_0x034d:
        r0 = 1;
        r0 = r0 + r6;
        goto L_0x0019;
    L_0x0352:
        r2 = "BogoMIPS";
        r2 = r6.startsWith(r2);	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        if (r2 == 0) goto L_0x0019;
    L_0x035b:
        r2 = 58;
        r2 = r6.indexOf(r2);	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        r7 = 1;
        if (r2 <= r7) goto L_0x0019;
    L_0x0364:
        r2 = r2 + 1;
        r7 = r6.length();	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        r2 = r6.substring(r2, r7);	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        r2 = r2.trim();	 Catch:{ FileNotFoundException -> 0x015d, IOException -> 0x01dd, NumberFormatException -> 0x0260, all -> 0x02c5 }
        r2 = java.lang.Float.parseFloat(r2);	 Catch:{ NumberFormatException -> 0x0416, FileNotFoundException -> 0x015d, IOException -> 0x01dd, all -> 0x02c5 }
        r6 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1));
        if (r6 <= 0) goto L_0x043d;
    L_0x037a:
        r3 = r2;
        goto L_0x0019;
    L_0x037d:
        r2 = move-exception;
        r2.printStackTrace();
        goto L_0x002c;
    L_0x0383:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x02d0;
    L_0x0389:
        r2 = 0;
        r2 = (r3 > r2 ? 1 : (r3 == r2 ? 0 : -1));
        if (r2 <= 0) goto L_0x005b;
    L_0x038e:
        r2 = 1150681088; // 0x44960000 float:1200.0 double:5.68511995E-315;
        r2 = (r3 > r2 ? 1 : (r3 == r2 ? 0 : -1));
        if (r2 >= 0) goto L_0x03c9;
    L_0x0394:
        r2 = (double) r3;
        r4 = 4608083138725491507; // 0x3ff3333333333333 float:4.172325E-8 double:1.2;
        r2 = r2 * r4;
        r4 = 4652007308841189376; // 0x408f400000000000 float:0.0 double:1000.0;
        r2 = r2 * r4;
        r2 = (long) r2;
        mMaxCpuFreq = r2;
    L_0x03a4:
        r2 = com.tencent.av.utils.QLog.isColorLevel();
        if (r2 == 0) goto L_0x005b;
    L_0x03aa:
        r2 = "VcSystemInfo";
        r3 = 0;
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = "[CpuInfo] mMaxCpuFreq(bogoMIPS)=";
        r4 = r4.append(r5);
        r6 = mMaxCpuFreq;
        r4 = r4.append(r6);
        r4 = r4.toString();
        com.tencent.av.utils.QLog.d(r2, r3, r4);
        goto L_0x005b;
    L_0x03c9:
        r2 = 1157234688; // 0x44fa0000 float:2000.0 double:5.717499035E-315;
        r2 = (r3 > r2 ? 1 : (r3 == r2 ? 0 : -1));
        if (r2 <= 0) goto L_0x03a4;
    L_0x03cf:
        r2 = (double) r3;
        r4 = 4610785298501913805; // 0x3ffccccccccccccd float:-1.07374184E8 double:1.8;
        r2 = r2 / r4;
        r4 = 4652007308841189376; // 0x408f400000000000 float:0.0 double:1000.0;
        r2 = r2 * r4;
        r2 = (long) r2;
        mMaxCpuFreq = r2;
        goto L_0x03a4;
    L_0x03e0:
        r2 = 0;
        r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r2 <= 0) goto L_0x00b5;
    L_0x03e6:
        r0 = (int) r0;
        mCoreNumber = r0;
        r0 = com.tencent.av.utils.QLog.isColorLevel();
        if (r0 == 0) goto L_0x00b5;
    L_0x03ef:
        r0 = "VcSystemInfo";
        r1 = 0;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "[CpuInfo] mCoreNumber(bogoCoreNum)=";
        r2 = r2.append(r3);
        r3 = mCoreNumber;
        r2 = r2.append(r3);
        r2 = r2.toString();
        com.tencent.av.utils.QLog.d(r0, r1, r2);
        goto L_0x00b5;
    L_0x040e:
        r0 = 0;
        mIsSupportSharpVideo = r0;
        goto L_0x00c8;
    L_0x0413:
        r2 = move-exception;
        goto L_0x0019;
    L_0x0416:
        r2 = move-exception;
        goto L_0x0019;
    L_0x0419:
        r0 = move-exception;
        r4 = r2;
        r5 = r6;
        goto L_0x02c6;
    L_0x041e:
        r0 = move-exception;
        r5 = r6;
        goto L_0x02c6;
    L_0x0422:
        r4 = move-exception;
        r4 = r2;
        r5 = r6;
        goto L_0x0261;
    L_0x0427:
        r2 = move-exception;
        r5 = r6;
        goto L_0x0261;
    L_0x042b:
        r4 = move-exception;
        r4 = r2;
        r5 = r6;
        goto L_0x01de;
    L_0x0430:
        r2 = move-exception;
        r5 = r6;
        goto L_0x01de;
    L_0x0434:
        r4 = move-exception;
        r4 = r6;
        goto L_0x0160;
    L_0x0438:
        r2 = move-exception;
        r2 = r4;
        r4 = r6;
        goto L_0x0160;
    L_0x043d:
        r2 = r3;
        goto L_0x037a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.av.utils.VcSystemInfo.getCpuInfo():void");
    }

    static long readMaxCpuFreq() {
        FileReader fileReader;
        BufferedReader bufferedReader;
        FileReader fileReader2;
        Throwable th;
        BufferedReader bufferedReader2 = null;
        long j = 0;
        try {
            String str = "";
            fileReader = new FileReader("/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq");
            try {
                bufferedReader = new BufferedReader(fileReader);
                if (bufferedReader != null) {
                    try {
                        String str2;
                        Object readLine = bufferedReader.readLine();
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
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                        }
                        if (fileReader2 != null) {
                            fileReader2.close();
                        }
                        return j;
                    } catch (IOException e3) {
                        bufferedReader2 = bufferedReader;
                        if (bufferedReader2 != null) {
                            try {
                                bufferedReader2.close();
                            } catch (Exception e22) {
                                e22.printStackTrace();
                            }
                        }
                        if (fileReader != null) {
                            fileReader.close();
                        }
                        return j;
                    } catch (NumberFormatException e4) {
                        bufferedReader2 = bufferedReader;
                        if (bufferedReader2 != null) {
                            try {
                                bufferedReader2.close();
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
                        bufferedReader2 = bufferedReader;
                        if (bufferedReader2 != null) {
                            try {
                                bufferedReader2.close();
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
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Exception e2222) {
                        e2222.printStackTrace();
                    }
                }
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (FileNotFoundException e6) {
                bufferedReader = null;
                fileReader2 = fileReader;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (fileReader2 != null) {
                    fileReader2.close();
                }
                return j;
            } catch (IOException e7) {
                if (bufferedReader2 != null) {
                    bufferedReader2.close();
                }
                if (fileReader != null) {
                    fileReader.close();
                }
                return j;
            } catch (NumberFormatException e8) {
                if (bufferedReader2 != null) {
                    bufferedReader2.close();
                }
                if (fileReader != null) {
                    fileReader.close();
                }
                return j;
            } catch (Throwable th3) {
                th = th3;
                if (bufferedReader2 != null) {
                    bufferedReader2.close();
                }
                if (fileReader != null) {
                    fileReader.close();
                }
                throw th;
            }
        } catch (FileNotFoundException e9) {
            bufferedReader = null;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (fileReader2 != null) {
                fileReader2.close();
            }
            return j;
        } catch (IOException e10) {
            fileReader = null;
            if (bufferedReader2 != null) {
                bufferedReader2.close();
            }
            if (fileReader != null) {
                fileReader.close();
            }
            return j;
        } catch (NumberFormatException e11) {
            fileReader = null;
            if (bufferedReader2 != null) {
                bufferedReader2.close();
            }
            if (fileReader != null) {
                fileReader.close();
            }
            return j;
        } catch (Throwable th4) {
            th = th4;
            fileReader = null;
            if (bufferedReader2 != null) {
                bufferedReader2.close();
            }
            if (fileReader != null) {
                fileReader.close();
            }
            throw th;
        }
        return j;
    }

    public static long getCurrentCpuFreq() {
        FileReader fileReader;
        BufferedReader bufferedReader;
        FileReader fileReader2;
        Throwable th;
        BufferedReader bufferedReader2 = null;
        long j = 0;
        try {
            String str = "";
            fileReader = new FileReader("/sys/devices/system/cpu/cpu0/cpufreq/scaling_cur_freq");
            try {
                bufferedReader = new BufferedReader(fileReader);
                if (bufferedReader != null) {
                    try {
                        String str2;
                        Object readLine = bufferedReader.readLine();
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
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                        }
                        if (fileReader2 != null) {
                            fileReader2.close();
                        }
                        return j;
                    } catch (IOException e3) {
                        bufferedReader2 = bufferedReader;
                        if (bufferedReader2 != null) {
                            try {
                                bufferedReader2.close();
                            } catch (Exception e22) {
                                e22.printStackTrace();
                            }
                        }
                        if (fileReader != null) {
                            fileReader.close();
                        }
                        return j;
                    } catch (NumberFormatException e4) {
                        bufferedReader2 = bufferedReader;
                        if (bufferedReader2 != null) {
                            try {
                                bufferedReader2.close();
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
                        bufferedReader2 = bufferedReader;
                        if (bufferedReader2 != null) {
                            try {
                                bufferedReader2.close();
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
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Exception e2222) {
                        e2222.printStackTrace();
                    }
                }
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (FileNotFoundException e6) {
                bufferedReader = null;
                fileReader2 = fileReader;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (fileReader2 != null) {
                    fileReader2.close();
                }
                return j;
            } catch (IOException e7) {
                if (bufferedReader2 != null) {
                    bufferedReader2.close();
                }
                if (fileReader != null) {
                    fileReader.close();
                }
                return j;
            } catch (NumberFormatException e8) {
                if (bufferedReader2 != null) {
                    bufferedReader2.close();
                }
                if (fileReader != null) {
                    fileReader.close();
                }
                return j;
            } catch (Throwable th3) {
                th = th3;
                if (bufferedReader2 != null) {
                    bufferedReader2.close();
                }
                if (fileReader != null) {
                    fileReader.close();
                }
                throw th;
            }
        } catch (FileNotFoundException e9) {
            bufferedReader = null;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (fileReader2 != null) {
                fileReader2.close();
            }
            return j;
        } catch (IOException e10) {
            fileReader = null;
            if (bufferedReader2 != null) {
                bufferedReader2.close();
            }
            if (fileReader != null) {
                fileReader.close();
            }
            return j;
        } catch (NumberFormatException e11) {
            fileReader = null;
            if (bufferedReader2 != null) {
                bufferedReader2.close();
            }
            if (fileReader != null) {
                fileReader.close();
            }
            return j;
        } catch (Throwable th4) {
            th = th4;
            fileReader = null;
            if (bufferedReader2 != null) {
                bufferedReader2.close();
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

    static boolean readSupportSharpAudio() {
        String str = Build.MODEL;
        String str2 = VERSION.INCREMENTAL;
        String str3 = Build.MANUFACTURER;
        int i = VERSION.SDK_INT;
        if (QLog.isColorLevel()) {
            QLog.d(TAG, 0, "Model: " + str + "\n" + "Version: " + str2 + "\n" + "Manufacturer: " + str3 + "\n" + "SDK Version: " + i);
        }
        if (mChip < 1 || i < 8) {
            if (!QLog.isColorLevel()) {
                return false;
            }
            QLog.d(TAG, 0, "CHIP or SDK NOT_SUPPORT");
            return false;
        } else if (str3.equalsIgnoreCase("HUAWEI") && str.equalsIgnoreCase("C8500")) {
            if (!QLog.isColorLevel()) {
                return false;
            }
            QLog.d(TAG, 0, "DEVICE_NOT_SUPPORT");
            return false;
        } else if (str3.equalsIgnoreCase("Samsung") && str.equalsIgnoreCase("GT-I5508")) {
            if (!QLog.isColorLevel()) {
                return false;
            }
            QLog.d(TAG, 0, "DEVICE_NOT_SUPPORT");
            return false;
        } else if (!str3.equalsIgnoreCase("FIH") || !str.equalsIgnoreCase("SH8128U")) {
            return true;
        } else {
            if (!QLog.isColorLevel()) {
                return false;
            }
            QLog.d(TAG, 0, "DEVICE_NOT_SUPPORT");
            return false;
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

    public int getOsType() {
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

    public static boolean hasFeature(String str) {
        try {
            if (mFeature == null || mFeature.equals("")) {
                getCpuInfo();
            }
            if (mFeature.indexOf(str) > 0) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public static boolean isSupportSharpVideo() {
        getCpuInfo();
        if (QLog.isColorLevel()) {
            QLog.d(TAG, 0, "[isSupportSharpVideo] [CpuInfo] mProcessorName=" + mProcessorName + " \n" + "mCpuArchitecture=" + mCpuArchitecture + " \n" + "mFeature=" + mFeature + " \n" + "mVendorId=" + mVendorId + " \n" + "mMaxCpuFreq=" + mMaxCpuFreq + " \n" + "mCoreNumber=" + mCoreNumber + " \n" + "mOpenGLVersion=" + mOpenGLVersion + " \n" + "mIsMarvell=" + mIsMarvell + " \n" + "Chip=" + mChip + ", Video=" + mIsSupportSharpVideo + ", Audio=" + mIsSupportSharpAudio + " \n" + "devModel=" + Build.MODEL + " \n" + "devVersion=" + VERSION.INCREMENTAL + " \n" + "devManufacturer=" + Build.MANUFACTURER + " \n" + "sdkVersion=" + VERSION.SDK_INT + " \n");
        }
        return mIsSupportSharpVideo;
    }

    public static boolean isSupportSharpAudio() {
        getCpuInfo();
        return mIsSupportSharpAudio;
    }

    public static boolean isNormalSharp() {
        getCpuInfo();
        if (mChip < 3 || mMaxCpuFreq / 1000 < 800 || VERSION.SDK_INT < 9) {
            return false;
        }
        return true;
    }

    public static String getCpuReport() {
        getCpuInfo();
        return "prcs(" + mProcessorName + ") " + "arch(" + mCpuArchitecture + ") " + "hard(" + mHardware + ") " + "chip(" + mChip + ") " + "freq(" + mMaxCpuFreq + ") " + "num(" + mCoreNumber + ")";
    }

    public static boolean isBeautySupported() {
        if ((hasFeature("neon") || hasFeature("asimd")) && getNumCores() >= 2 && getCpuArchitecture() >= 4) {
            return true;
        }
        return false;
    }

    public static String getModelReport() {
        getCpuInfo();
        return "model(" + Build.MODEL + ") " + "Mnfc(" + Build.MANUFACTURER + ") " + "dev(" + VERSION.INCREMENTAL + ") " + "sdk(" + VERSION.SDK_INT + ") " + "gl(" + mOpenGLVersion + ")";
    }
}
