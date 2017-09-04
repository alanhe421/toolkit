package com.qq.reader.plugin;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import com.iflytek.speech.UtilityConfig;
import com.qq.reader.common.c.a;
import com.qq.reader.common.login.c;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.utils.ao;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import java.io.File;
import java.util.ArrayList;
import tencent.tls.platform.SigType;

/* compiled from: ExternalAPKPluginHandler */
public class d extends a {
    public String n = null;
    Context o;

    protected d(Context context, l lVar, h hVar) {
        super(context, lVar, hVar);
        this.o = context;
        c(lVar.i());
    }

    public void c(String str) {
        if ("25".equals(str)) {
            this.n = "cn.wps.moffice_eng";
        } else if ("29".equals(str)) {
            this.n = UtilityConfig.COMPONENT_PKG;
        } else if ("30".equals(str)) {
            this.n = "com.qq.qcloud";
        }
    }

    public String w() {
        return this.i.substring(0, this.i.lastIndexOf("/") + 1);
    }

    public boolean o() {
        if ("29".equals(this.j.i())) {
            PackageInfo packageInfo = null;
            PackageManager packageManager = this.o.getPackageManager();
            try {
                if (this.n != null) {
                    packageInfo = packageManager.getPackageInfo(this.n, 0);
                }
            } catch (NameNotFoundException e) {
                e.printStackTrace();
            }
            if (packageInfo != null && packageInfo.versionCode < 50) {
                return true;
            }
        }
        return false;
    }

    protected String a(l lVar) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(a.aJ);
        stringBuffer.append(lVar.i());
        stringBuffer.append("/");
        stringBuffer.append(lVar.i());
        stringBuffer.append(".zip");
        return stringBuffer.toString();
    }

    protected void a(String str) {
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected void a(java.lang.String r20, android.content.Context r21) {
        /*
        r19 = this;
        r2 = 0;
        r5 = 0;
        r0 = r19;
        r3 = r0.i;	 Catch:{ IOException -> 0x02ee, all -> 0x02e9 }
        com.qq.reader.common.utils.ao.c(r3);	 Catch:{ IOException -> 0x02ee, all -> 0x02e9 }
        r6 = new java.io.RandomAccessFile;	 Catch:{ IOException -> 0x02ee, all -> 0x02e9 }
        r0 = r19;
        r3 = r0.i;	 Catch:{ IOException -> 0x02ee, all -> 0x02e9 }
        r4 = "rw";
        r6.<init>(r3, r4);	 Catch:{ IOException -> 0x02ee, all -> 0x02e9 }
        r8 = r6.length();	 Catch:{ IOException -> 0x015a, all -> 0x0264 }
        r6.seek(r8);	 Catch:{ IOException -> 0x015a, all -> 0x0264 }
        r2 = 0;
        r3 = 5120; // 0x1400 float:7.175E-42 double:2.5296E-320;
        r10 = new byte[r3];	 Catch:{ IOException -> 0x015a, all -> 0x0264 }
        r7 = 0;
        r3 = 0;
        r4 = 1;
        r11 = new org.apache.http.message.BasicHeader[r4];	 Catch:{ Exception -> 0x0307, all -> 0x02f1 }
        r4 = 0;
        r12 = new org.apache.http.message.BasicHeader;	 Catch:{ Exception -> 0x0307, all -> 0x02f1 }
        r13 = "Range";
        r14 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0307, all -> 0x02f1 }
        r14.<init>();	 Catch:{ Exception -> 0x0307, all -> 0x02f1 }
        r15 = "bytes=";
        r14 = r14.append(r15);	 Catch:{ Exception -> 0x0307, all -> 0x02f1 }
        r15 = java.lang.String.valueOf(r8);	 Catch:{ Exception -> 0x0307, all -> 0x02f1 }
        r14 = r14.append(r15);	 Catch:{ Exception -> 0x0307, all -> 0x02f1 }
        r15 = "-";
        r14 = r14.append(r15);	 Catch:{ Exception -> 0x0307, all -> 0x02f1 }
        r14 = r14.toString();	 Catch:{ Exception -> 0x0307, all -> 0x02f1 }
        r12.<init>(r13, r14);	 Catch:{ Exception -> 0x0307, all -> 0x02f1 }
        r11[r4] = r12;	 Catch:{ Exception -> 0x0307, all -> 0x02f1 }
        r4 = 0;
        r0 = r20;
        r1 = r21;
        r4 = com.qq.reader.common.conn.http.b.a(r0, r11, r1);	 Catch:{ RangeException -> 0x0096 }
    L_0x0059:
        r11 = r4.getContentLength();	 Catch:{ Exception -> 0x0307, all -> 0x02f1 }
        r12 = (long) r11;	 Catch:{ Exception -> 0x0307, all -> 0x02f1 }
        r14 = 0;
        r11 = (r8 > r14 ? 1 : (r8 == r14 ? 0 : -1));
        if (r11 <= 0) goto L_0x019b;
    L_0x0064:
        r14 = 0;
        r11 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1));
        if (r11 != 0) goto L_0x019b;
    L_0x006a:
        r3 = r19.x();	 Catch:{ Exception -> 0x0307, all -> 0x02f1 }
        if (r3 == 0) goto L_0x0130;
    L_0x0070:
        r0 = r19;
        r4 = r0.m;	 Catch:{ Exception -> 0x00d5, all -> 0x013b }
        r5 = 6108; // 0x17dc float:8.559E-42 double:3.0178E-320;
        r4.sendEmptyMessage(r5);	 Catch:{ Exception -> 0x00d5, all -> 0x013b }
    L_0x0079:
        r4 = 0;
        r0 = r19;
        r0.c(r4);	 Catch:{ IOException -> 0x015a, all -> 0x0264 }
        if (r2 == 0) goto L_0x0084;
    L_0x0081:
        r2.close();	 Catch:{ IOException -> 0x02d8, all -> 0x0264 }
    L_0x0084:
        r2 = r19.s();	 Catch:{ Exception -> 0x0198 }
        if (r2 == 0) goto L_0x0166;
    L_0x008a:
        r2 = 0;
        r0 = r19;
        r0.d(r2);	 Catch:{ Exception -> 0x0198 }
    L_0x0090:
        if (r6 == 0) goto L_0x0095;
    L_0x0092:
        r6.close();	 Catch:{ IOException -> 0x02db }
    L_0x0095:
        return;
    L_0x0096:
        r11 = move-exception;
        r12 = 0;
        r11 = (r8 > r12 ? 1 : (r8 == r12 ? 0 : -1));
        if (r11 <= 0) goto L_0x0059;
    L_0x009d:
        r3 = r19.x();	 Catch:{ Exception -> 0x0307, all -> 0x02f1 }
        if (r3 == 0) goto L_0x00cb;
    L_0x00a3:
        r0 = r19;
        r4 = r0.m;	 Catch:{ Exception -> 0x00d5, all -> 0x013b }
        r5 = 6108; // 0x17dc float:8.559E-42 double:3.0178E-320;
        r4.sendEmptyMessage(r5);	 Catch:{ Exception -> 0x00d5, all -> 0x013b }
    L_0x00ac:
        r4 = 0;
        r0 = r19;
        r0.c(r4);	 Catch:{ IOException -> 0x015a, all -> 0x0264 }
        if (r2 == 0) goto L_0x00b7;
    L_0x00b4:
        r2.close();	 Catch:{ IOException -> 0x02d5, all -> 0x0264 }
    L_0x00b7:
        r2 = r19.s();	 Catch:{ Exception -> 0x012e }
        if (r2 == 0) goto L_0x00fd;
    L_0x00bd:
        r2 = 0;
        r0 = r19;
        r0.d(r2);	 Catch:{ Exception -> 0x012e }
    L_0x00c3:
        if (r6 == 0) goto L_0x0095;
    L_0x00c5:
        r6.close();	 Catch:{ IOException -> 0x00c9 }
        goto L_0x0095;
    L_0x00c9:
        r2 = move-exception;
        goto L_0x0095;
    L_0x00cb:
        r0 = r19;
        r4 = r0.m;	 Catch:{ Exception -> 0x00d5, all -> 0x013b }
        r5 = 6115; // 0x17e3 float:8.569E-42 double:3.021E-320;
        r4.sendEmptyMessage(r5);	 Catch:{ Exception -> 0x00d5, all -> 0x013b }
        goto L_0x00ac;
    L_0x00d5:
        r4 = move-exception;
    L_0x00d6:
        r4 = "网络连接失败，请稍后再试。";
        r0 = r19;
        r0.b(r4);	 Catch:{ all -> 0x02fe }
        r4 = 0;
        r0 = r19;
        r0.c(r4);	 Catch:{ IOException -> 0x015a, all -> 0x0264 }
        if (r2 == 0) goto L_0x00e9;
    L_0x00e6:
        r2.close();	 Catch:{ IOException -> 0x02e1, all -> 0x0264 }
    L_0x00e9:
        r2 = r19.s();	 Catch:{ Exception -> 0x029d }
        if (r2 == 0) goto L_0x026b;
    L_0x00ef:
        r2 = 0;
        r0 = r19;
        r0.d(r2);	 Catch:{ Exception -> 0x029d }
    L_0x00f5:
        if (r6 == 0) goto L_0x0095;
    L_0x00f7:
        r6.close();	 Catch:{ IOException -> 0x00fb }
        goto L_0x0095;
    L_0x00fb:
        r2 = move-exception;
        goto L_0x0095;
    L_0x00fd:
        if (r3 == 0) goto L_0x00c3;
    L_0x00ff:
        r0 = r19;
        r2 = r0.j;	 Catch:{ Exception -> 0x012e }
        r0 = r19;
        r3 = r0.b;	 Catch:{ Exception -> 0x012e }
        r2.a(r3);	 Catch:{ Exception -> 0x012e }
        r0 = r19;
        r2 = r0.j;	 Catch:{ Exception -> 0x012e }
        r0 = r19;
        r3 = r0.b;	 Catch:{ Exception -> 0x012e }
        r2.b(r3);	 Catch:{ Exception -> 0x012e }
        r2 = com.qq.reader.plugin.k.b();	 Catch:{ Exception -> 0x012e }
        r0 = r19;
        r3 = r0.j;	 Catch:{ Exception -> 0x012e }
        r3 = r3.i();	 Catch:{ Exception -> 0x012e }
        r0 = r19;
        r4 = r0.b;	 Catch:{ Exception -> 0x012e }
        r0 = r19;
        r5 = r0.b;	 Catch:{ Exception -> 0x012e }
        r7 = 0;
        r2.a(r3, r4, r5, r7);	 Catch:{ Exception -> 0x012e }
        goto L_0x00c3;
    L_0x012e:
        r2 = move-exception;
        goto L_0x00c3;
    L_0x0130:
        r0 = r19;
        r4 = r0.m;	 Catch:{ Exception -> 0x00d5, all -> 0x013b }
        r5 = 6115; // 0x17e3 float:8.569E-42 double:3.021E-320;
        r4.sendEmptyMessage(r5);	 Catch:{ Exception -> 0x00d5, all -> 0x013b }
        goto L_0x0079;
    L_0x013b:
        r4 = move-exception;
        r5 = r3;
        r18 = r2;
        r2 = r4;
        r4 = r18;
    L_0x0142:
        r3 = 0;
        r0 = r19;
        r0.c(r3);	 Catch:{ IOException -> 0x015a, all -> 0x0264 }
        if (r4 == 0) goto L_0x014d;
    L_0x014a:
        r4.close();	 Catch:{ IOException -> 0x02e4, all -> 0x0264 }
    L_0x014d:
        r3 = r19.s();	 Catch:{ Exception -> 0x02d2 }
        if (r3 == 0) goto L_0x02a0;
    L_0x0153:
        r3 = 0;
        r0 = r19;
        r0.d(r3);	 Catch:{ Exception -> 0x02d2 }
    L_0x0159:
        throw r2;	 Catch:{ IOException -> 0x015a, all -> 0x0264 }
    L_0x015a:
        r2 = move-exception;
        r2 = r6;
    L_0x015c:
        if (r2 == 0) goto L_0x0095;
    L_0x015e:
        r2.close();	 Catch:{ IOException -> 0x0163 }
        goto L_0x0095;
    L_0x0163:
        r2 = move-exception;
        goto L_0x0095;
    L_0x0166:
        if (r3 == 0) goto L_0x0090;
    L_0x0168:
        r0 = r19;
        r2 = r0.j;	 Catch:{ Exception -> 0x0198 }
        r0 = r19;
        r3 = r0.b;	 Catch:{ Exception -> 0x0198 }
        r2.a(r3);	 Catch:{ Exception -> 0x0198 }
        r0 = r19;
        r2 = r0.j;	 Catch:{ Exception -> 0x0198 }
        r0 = r19;
        r3 = r0.b;	 Catch:{ Exception -> 0x0198 }
        r2.b(r3);	 Catch:{ Exception -> 0x0198 }
        r2 = com.qq.reader.plugin.k.b();	 Catch:{ Exception -> 0x0198 }
        r0 = r19;
        r3 = r0.j;	 Catch:{ Exception -> 0x0198 }
        r3 = r3.i();	 Catch:{ Exception -> 0x0198 }
        r0 = r19;
        r4 = r0.b;	 Catch:{ Exception -> 0x0198 }
        r0 = r19;
        r5 = r0.b;	 Catch:{ Exception -> 0x0198 }
        r7 = 0;
        r2.a(r3, r4, r5, r7);	 Catch:{ Exception -> 0x0198 }
        goto L_0x0090;
    L_0x0198:
        r2 = move-exception;
        goto L_0x0090;
    L_0x019b:
        r4 = r4.getInputStream();	 Catch:{ Exception -> 0x0307, all -> 0x02f1 }
        r2 = android.os.Message.obtain();	 Catch:{ Exception -> 0x030b, all -> 0x02f6 }
        r11 = 6104; // 0x17d8 float:8.554E-42 double:3.016E-320;
        r2.what = r11;	 Catch:{ Exception -> 0x030b, all -> 0x02f6 }
        r14 = r8 + r12;
        r11 = (int) r14;	 Catch:{ Exception -> 0x030b, all -> 0x02f6 }
        r2.arg1 = r11;	 Catch:{ Exception -> 0x030b, all -> 0x02f6 }
        r0 = r19;
        r11 = r0.m;	 Catch:{ Exception -> 0x030b, all -> 0x02f6 }
        r11.sendMessage(r2);	 Catch:{ Exception -> 0x030b, all -> 0x02f6 }
        r2 = r3;
        r3 = r7;
    L_0x01b5:
        r7 = r19.s();	 Catch:{ Exception -> 0x030b, all -> 0x02f6 }
        if (r7 != 0) goto L_0x01f2;
    L_0x01bb:
        r7 = r4.read(r10);	 Catch:{ Exception -> 0x030b, all -> 0x02f6 }
        r11 = -1;
        if (r7 == r11) goto L_0x01f2;
    L_0x01c2:
        r11 = 0;
        r6.write(r10, r11, r7);	 Catch:{ Exception -> 0x030b, all -> 0x02f6 }
        r14 = r6.length();	 Catch:{ Exception -> 0x030b, all -> 0x02f6 }
        r7 = 20;
        if (r2 == r7) goto L_0x01d4;
    L_0x01ce:
        r16 = r12 + r8;
        r7 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1));
        if (r7 != 0) goto L_0x01ef;
    L_0x01d4:
        r2 = (int) r14;	 Catch:{ Exception -> 0x030b, all -> 0x02f6 }
        if (r3 >= r2) goto L_0x0313;
    L_0x01d7:
        r3 = android.os.Message.obtain();	 Catch:{ Exception -> 0x030b, all -> 0x02f6 }
        r7 = 6105; // 0x17d9 float:8.555E-42 double:3.0163E-320;
        r3.what = r7;	 Catch:{ Exception -> 0x030b, all -> 0x02f6 }
        r3.arg1 = r2;	 Catch:{ Exception -> 0x030b, all -> 0x02f6 }
        r0 = r19;
        r7 = r0.m;	 Catch:{ Exception -> 0x030b, all -> 0x02f6 }
        r7.sendMessage(r3);	 Catch:{ Exception -> 0x030b, all -> 0x02f6 }
    L_0x01e8:
        r3 = 0;
        r18 = r3;
        r3 = r2;
        r2 = r18;
        goto L_0x01b5;
    L_0x01ef:
        r2 = r2 + 1;
        goto L_0x01b5;
    L_0x01f2:
        r2 = r19.s();	 Catch:{ Exception -> 0x030b, all -> 0x02f6 }
        if (r2 != 0) goto L_0x0310;
    L_0x01f8:
        r2 = r19.x();	 Catch:{ Exception -> 0x030b, all -> 0x02f6 }
        if (r2 == 0) goto L_0x0223;
    L_0x01fe:
        r0 = r19;
        r3 = r0.m;	 Catch:{ Exception -> 0x022d, all -> 0x02f9 }
        r5 = 6108; // 0x17dc float:8.559E-42 double:3.0178E-320;
        r3.sendEmptyMessage(r5);	 Catch:{ Exception -> 0x022d, all -> 0x02f9 }
    L_0x0207:
        r3 = 0;
        r0 = r19;
        r0.c(r3);	 Catch:{ IOException -> 0x015a, all -> 0x0264 }
        if (r4 == 0) goto L_0x0212;
    L_0x020f:
        r4.close();	 Catch:{ IOException -> 0x02de, all -> 0x0264 }
    L_0x0212:
        r3 = r19.s();	 Catch:{ Exception -> 0x0220 }
        if (r3 == 0) goto L_0x0232;
    L_0x0218:
        r2 = 0;
        r0 = r19;
        r0.d(r2);	 Catch:{ Exception -> 0x0220 }
        goto L_0x00f5;
    L_0x0220:
        r2 = move-exception;
        goto L_0x00f5;
    L_0x0223:
        r0 = r19;
        r3 = r0.m;	 Catch:{ Exception -> 0x022d, all -> 0x02f9 }
        r5 = 6115; // 0x17e3 float:8.569E-42 double:3.021E-320;
        r3.sendEmptyMessage(r5);	 Catch:{ Exception -> 0x022d, all -> 0x02f9 }
        goto L_0x0207;
    L_0x022d:
        r3 = move-exception;
        r3 = r2;
        r2 = r4;
        goto L_0x00d6;
    L_0x0232:
        if (r2 == 0) goto L_0x00f5;
    L_0x0234:
        r0 = r19;
        r2 = r0.j;	 Catch:{ Exception -> 0x0220 }
        r0 = r19;
        r3 = r0.b;	 Catch:{ Exception -> 0x0220 }
        r2.a(r3);	 Catch:{ Exception -> 0x0220 }
        r0 = r19;
        r2 = r0.j;	 Catch:{ Exception -> 0x0220 }
        r0 = r19;
        r3 = r0.b;	 Catch:{ Exception -> 0x0220 }
        r2.b(r3);	 Catch:{ Exception -> 0x0220 }
        r2 = com.qq.reader.plugin.k.b();	 Catch:{ Exception -> 0x0220 }
        r0 = r19;
        r3 = r0.j;	 Catch:{ Exception -> 0x0220 }
        r3 = r3.i();	 Catch:{ Exception -> 0x0220 }
        r0 = r19;
        r4 = r0.b;	 Catch:{ Exception -> 0x0220 }
        r0 = r19;
        r5 = r0.b;	 Catch:{ Exception -> 0x0220 }
        r7 = 0;
        r2.a(r3, r4, r5, r7);	 Catch:{ Exception -> 0x0220 }
        goto L_0x00f5;
    L_0x0264:
        r2 = move-exception;
    L_0x0265:
        if (r6 == 0) goto L_0x026a;
    L_0x0267:
        r6.close();	 Catch:{ IOException -> 0x02e7 }
    L_0x026a:
        throw r2;
    L_0x026b:
        if (r3 == 0) goto L_0x00f5;
    L_0x026d:
        r0 = r19;
        r2 = r0.j;	 Catch:{ Exception -> 0x029d }
        r0 = r19;
        r3 = r0.b;	 Catch:{ Exception -> 0x029d }
        r2.a(r3);	 Catch:{ Exception -> 0x029d }
        r0 = r19;
        r2 = r0.j;	 Catch:{ Exception -> 0x029d }
        r0 = r19;
        r3 = r0.b;	 Catch:{ Exception -> 0x029d }
        r2.b(r3);	 Catch:{ Exception -> 0x029d }
        r2 = com.qq.reader.plugin.k.b();	 Catch:{ Exception -> 0x029d }
        r0 = r19;
        r3 = r0.j;	 Catch:{ Exception -> 0x029d }
        r3 = r3.i();	 Catch:{ Exception -> 0x029d }
        r0 = r19;
        r4 = r0.b;	 Catch:{ Exception -> 0x029d }
        r0 = r19;
        r5 = r0.b;	 Catch:{ Exception -> 0x029d }
        r7 = 0;
        r2.a(r3, r4, r5, r7);	 Catch:{ Exception -> 0x029d }
        goto L_0x00f5;
    L_0x029d:
        r2 = move-exception;
        goto L_0x00f5;
    L_0x02a0:
        if (r5 == 0) goto L_0x0159;
    L_0x02a2:
        r0 = r19;
        r3 = r0.j;	 Catch:{ Exception -> 0x02d2 }
        r0 = r19;
        r4 = r0.b;	 Catch:{ Exception -> 0x02d2 }
        r3.a(r4);	 Catch:{ Exception -> 0x02d2 }
        r0 = r19;
        r3 = r0.j;	 Catch:{ Exception -> 0x02d2 }
        r0 = r19;
        r4 = r0.b;	 Catch:{ Exception -> 0x02d2 }
        r3.b(r4);	 Catch:{ Exception -> 0x02d2 }
        r3 = com.qq.reader.plugin.k.b();	 Catch:{ Exception -> 0x02d2 }
        r0 = r19;
        r4 = r0.j;	 Catch:{ Exception -> 0x02d2 }
        r4 = r4.i();	 Catch:{ Exception -> 0x02d2 }
        r0 = r19;
        r5 = r0.b;	 Catch:{ Exception -> 0x02d2 }
        r0 = r19;
        r7 = r0.b;	 Catch:{ Exception -> 0x02d2 }
        r8 = 0;
        r3.a(r4, r5, r7, r8);	 Catch:{ Exception -> 0x02d2 }
        goto L_0x0159;
    L_0x02d2:
        r3 = move-exception;
        goto L_0x0159;
    L_0x02d5:
        r2 = move-exception;
        goto L_0x00b7;
    L_0x02d8:
        r2 = move-exception;
        goto L_0x0084;
    L_0x02db:
        r2 = move-exception;
        goto L_0x0095;
    L_0x02de:
        r3 = move-exception;
        goto L_0x0212;
    L_0x02e1:
        r2 = move-exception;
        goto L_0x00e9;
    L_0x02e4:
        r3 = move-exception;
        goto L_0x014d;
    L_0x02e7:
        r3 = move-exception;
        goto L_0x026a;
    L_0x02e9:
        r3 = move-exception;
        r6 = r2;
        r2 = r3;
        goto L_0x0265;
    L_0x02ee:
        r3 = move-exception;
        goto L_0x015c;
    L_0x02f1:
        r3 = move-exception;
        r4 = r2;
        r2 = r3;
        goto L_0x0142;
    L_0x02f6:
        r2 = move-exception;
        goto L_0x0142;
    L_0x02f9:
        r3 = move-exception;
        r5 = r2;
        r2 = r3;
        goto L_0x0142;
    L_0x02fe:
        r4 = move-exception;
        r5 = r3;
        r18 = r2;
        r2 = r4;
        r4 = r18;
        goto L_0x0142;
    L_0x0307:
        r3 = move-exception;
        r3 = r5;
        goto L_0x00d6;
    L_0x030b:
        r2 = move-exception;
        r2 = r4;
        r3 = r5;
        goto L_0x00d6;
    L_0x0310:
        r2 = r5;
        goto L_0x0207;
    L_0x0313:
        r2 = r3;
        goto L_0x01e8;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.plugin.d.a(java.lang.String, android.content.Context):void");
    }

    private boolean x() {
        try {
            String substring = this.i.substring(0, this.i.lastIndexOf("/") + 1);
            if (this.i.endsWith(ShareConstants.PATCH_SUFFIX)) {
                a(this.o, this.i);
                return true;
            }
            ArrayList d = ao.d(this.i, substring);
            if (d == null || d.size() != 1) {
                return false;
            }
            a(this.o, (String) d.get(0));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    protected void b(String str) {
        this.m.obtainMessage(6109, str).sendToTarget();
    }

    protected void a(Bundle bundle) {
    }

    public synchronized void r() {
        if (c.b() && this.i != null && this.i.endsWith(ShareConstants.PATCH_SUFFIX)) {
            a(this.o, this.i);
            this.m.sendEmptyMessage(6108);
        } else {
            super.r();
        }
    }

    public synchronized boolean i() {
        boolean z = false;
        synchronized (this) {
            try {
                if (y()) {
                    z = true;
                } else {
                    File file = new File(this.i.substring(0, this.i.lastIndexOf("/") + 1));
                    if (file != null && file.exists()) {
                        File[] listFiles = file.listFiles();
                        if (listFiles != null) {
                            int length = listFiles.length;
                            int i = 0;
                            while (i < length && !listFiles[i].getAbsolutePath().toLowerCase().endsWith(ShareConstants.PATCH_SUFFIX)) {
                                i++;
                            }
                        }
                    }
                }
            } catch (Exception e) {
            }
        }
        return z;
    }

    public boolean l() {
        return y();
    }

    public boolean j() {
        boolean y = y();
        boolean z = this.j.d() == 4;
        boolean exists = new File(this.i.substring(0, this.i.lastIndexOf("/") + 1)).exists();
        if (y) {
            if (this.j.d() == 4 || this.j.d() == 7) {
                if (!n()) {
                    k.b().a(this.j.i(), 0, 7, null, 2);
                    this.j.b(7);
                    return false;
                } else if (o()) {
                    k.b().a(this.j.i(), 0, 7, null, 2);
                    this.j.b(7);
                    return true;
                }
            }
            if (z) {
                return true;
            }
            k.b().a(this.j.i(), 0, 4, null, 2);
            this.j.b(4);
            return true;
        } else if (this.j.d() == 3 || this.j.d() == 2 || this.j.d() == 6) {
            return true;
        } else {
            if (!exists) {
                k.b().a(this.j.i(), 0, 1, null, 2);
                this.j.b(1);
            } else if (z) {
                k.b().a(this.j.i(), 0, 6, null, 2);
                this.j.b(6);
            } else {
                k.b().a(this.j.i(), 0, 1, null, 2);
                this.j.b(1);
            }
            return false;
        }
    }

    public boolean k() {
        if (this.h) {
            return false;
        }
        this.h = true;
        try {
            File file = new File(w());
            if (file != null && file.exists()) {
                ao.c(file);
            }
            this.m.sendEmptyMessage(6110);
            b(this.o, this.n);
            this.h = false;
            return true;
        } catch (Exception e) {
            f.a("PdfPluginHandler", "uninstall mPluginId = " + this.j.i() + "  " + e.toString());
            this.h = false;
            return false;
        }
    }

    private boolean y() {
        PackageInfo packageInfo = null;
        PackageManager packageManager = this.o.getPackageManager();
        try {
            if (this.n != null) {
                packageInfo = packageManager.getPackageInfo(this.n, 0);
            }
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        if (packageInfo != null) {
            return true;
        }
        return false;
    }

    private void a(Context context, String str) {
        if (str != null && str.length() > 0 && str.endsWith(ShareConstants.PATCH_SUFFIX)) {
            File file = new File(str);
            Intent intent = new Intent();
            intent.addFlags(SigType.TLS);
            intent.setAction("android.intent.action.VIEW");
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            context.getApplicationContext().startActivity(intent);
        }
    }

    private void b(Context context, String str) {
        int i = VERSION.SDK_INT;
        if (i >= 9) {
            Intent intent = new Intent("android.intent.action.DELETE", Uri.parse("package:" + str));
            intent.addFlags(SigType.TLS);
            context.startActivity(intent);
            return;
        }
        intent = new Intent();
        intent.addFlags(SigType.TLS);
        String str2 = i == 8 ? "pkg" : "com.android.settings.ApplicationPkgName";
        intent.setAction("android.intent.action.VIEW");
        intent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
        intent.putExtra(str2, str);
        context.startActivity(intent);
    }
}
