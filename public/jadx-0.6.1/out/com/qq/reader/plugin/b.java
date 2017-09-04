package com.qq.reader.plugin;

import android.content.Context;
import android.os.Bundle;
import com.dynamicload.Lib.DLPluginManager;
import com.dynamicload.Lib.DLPluginPackage;
import com.qq.reader.common.c.a;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.w;
import java.io.File;

/* compiled from: DefaultPluginHandler */
public class b extends a {
    private String n = null;

    protected b(Context context, l lVar, h hVar) {
        super(context, lVar, hVar);
    }

    public String w() {
        return this.i.substring(0, this.i.lastIndexOf("/") + 1);
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

    public String a(Context context, String str) {
        if (this.n == null) {
            this.n = context.getFilesDir().getAbsolutePath() + "/PlugIn/" + str + "/";
        }
        return this.n;
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
            file = new File(a(this.a, this.j.i()));
            if (file != null && file.exists()) {
                ao.c(file);
            }
            DLPluginPackage b = w.b(this.j.i());
            if (b != null) {
                DLPluginManager.getInstance().freeApk(b);
            }
            this.m.sendEmptyMessage(6110);
            this.h = false;
            return true;
        } catch (Exception e) {
            f.a("PdfPluginHandler", "uninstall mPluginId = " + this.j.i() + "  " + e.toString());
            this.h = false;
            return false;
        }
    }

    public synchronized boolean i() {
        boolean z = false;
        synchronized (this) {
            try {
                File file = new File(this.i.substring(0, this.i.lastIndexOf("/") + 1));
                if (file != null && file.exists()) {
                    File file2 = new File(a(this.a, this.j.i()));
                    if (file2 != null && file2.exists()) {
                        z = true;
                    }
                }
            } catch (Exception e) {
            }
        }
        return z;
    }

    public boolean l() {
        try {
            File file = new File(this.i.substring(0, this.i.lastIndexOf("/") + 1));
            if (file == null || !file.exists()) {
                return false;
            }
            File file2 = new File(a(this.a, this.j.i()));
            if (file2 == null || !file2.exists()) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean j() {
        if (this.j.d() == 4) {
            File file = new File(this.i.substring(0, this.i.lastIndexOf("/") + 1));
            File file2 = new File(a(this.a, this.j.i()));
            if (!n()) {
                k.b().a(this.j.i(), 0, 7, null, 2);
                this.j.b(7);
                return false;
            } else if (o()) {
                k.b().a(this.j.i(), 0, 7, null, 2);
                this.j.b(7);
                return true;
            } else if (!(file.exists() && file2.exists())) {
                if (!file.exists()) {
                    f.a("checkErrorDB", "checkErrorDB restore Plugin " + this.j.i());
                    ao.c(file);
                    ao.c(file2);
                }
                k.b().a(this.j.i(), 0, 6, null, 2);
                this.j.b(6);
                return false;
            }
        }
        return true;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean x() throws java.io.IOException {
        /*
        r11 = this;
        r3 = 0;
        r1 = 1;
        r0 = 0;
        r2 = r11.i;	 Catch:{ Exception -> 0x00e2 }
        r4 = 0;
        r5 = r11.i;	 Catch:{ Exception -> 0x00e2 }
        r6 = "/";
        r5 = r5.lastIndexOf(r6);	 Catch:{ Exception -> 0x00e2 }
        r5 = r5 + 1;
        r2 = r2.substring(r4, r5);	 Catch:{ Exception -> 0x00e2 }
        r4 = r11.i;	 Catch:{ Exception -> 0x00e2 }
        com.qq.reader.common.utils.ao.d(r4, r2);	 Catch:{ Exception -> 0x00e2 }
        r4 = new java.io.File;	 Catch:{ Exception -> 0x00e2 }
        r5 = r11.a;	 Catch:{ Exception -> 0x00e2 }
        r6 = r11.j;	 Catch:{ Exception -> 0x00e2 }
        r6 = r6.i();	 Catch:{ Exception -> 0x00e2 }
        r5 = r11.a(r5, r6);	 Catch:{ Exception -> 0x00e2 }
        r4.<init>(r5);	 Catch:{ Exception -> 0x00e2 }
        com.qq.reader.common.utils.ab.b(r4);	 Catch:{ Exception -> 0x00e2 }
        r4 = 1;
        r4 = new java.lang.String[r4];	 Catch:{ Exception -> 0x00e2 }
        r5 = 0;
        r6 = ".apk";
        r4[r5] = r6;	 Catch:{ Exception -> 0x00e2 }
        r6 = com.qq.reader.common.utils.ao.a(r2, r4);	 Catch:{ Exception -> 0x00e2 }
        if (r6 == 0) goto L_0x00e4;
    L_0x003d:
        r2 = r6.length;	 Catch:{ Exception -> 0x00e2 }
        if (r2 < r1) goto L_0x00e4;
    L_0x0040:
        r5 = r0;
    L_0x0041:
        r2 = r6.length;	 Catch:{ Exception -> 0x00e2 }
        if (r5 >= r2) goto L_0x00e4;
    L_0x0044:
        r2 = r6[r5];	 Catch:{ Exception -> 0x00e2 }
        r2 = r2.getPath();	 Catch:{ Exception -> 0x00e2 }
        r4 = r6[r5];	 Catch:{ Exception -> 0x00e2 }
        r4 = r4.getPath();	 Catch:{ Exception -> 0x00e2 }
        r7 = "/";
        r4 = r4.lastIndexOf(r7);	 Catch:{ Exception -> 0x00e2 }
        r4 = r4 + 1;
        r7 = r2.substring(r4);	 Catch:{ Exception -> 0x00e2 }
        r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00e2 }
        r2.<init>();	 Catch:{ Exception -> 0x00e2 }
        r4 = com.qq.reader.common.c.a.aJ;	 Catch:{ Exception -> 0x00e2 }
        r2 = r2.append(r4);	 Catch:{ Exception -> 0x00e2 }
        r2 = r2.append(r7);	 Catch:{ Exception -> 0x00e2 }
        r2 = r2.toString();	 Catch:{ Exception -> 0x00e2 }
        r8 = new java.io.File;	 Catch:{ Exception -> 0x00e2 }
        r8.<init>(r2);	 Catch:{ Exception -> 0x00e2 }
        r4 = r8.exists();	 Catch:{ Exception -> 0x00e2 }
        if (r4 == 0) goto L_0x007e;
    L_0x007b:
        r8.delete();	 Catch:{ Exception -> 0x00e2 }
    L_0x007e:
        com.qq.reader.common.utils.ao.c(r2);	 Catch:{ Exception -> 0x00e2 }
        r4 = new java.io.FileInputStream;	 Catch:{ Exception -> 0x00eb, all -> 0x00d5 }
        r2 = r6[r5];	 Catch:{ Exception -> 0x00eb, all -> 0x00d5 }
        r2 = r2.getPath();	 Catch:{ Exception -> 0x00eb, all -> 0x00d5 }
        r4.<init>(r2);	 Catch:{ Exception -> 0x00eb, all -> 0x00d5 }
        r2 = new java.io.FileOutputStream;	 Catch:{ Exception -> 0x00ee, all -> 0x00e6 }
        r8 = r8.getPath();	 Catch:{ Exception -> 0x00ee, all -> 0x00e6 }
        r2.<init>(r8);	 Catch:{ Exception -> 0x00ee, all -> 0x00e6 }
        r8 = 51200; // 0xc800 float:7.1746E-41 double:2.5296E-319;
        r8 = new byte[r8];	 Catch:{ Exception -> 0x00a6, all -> 0x00e8 }
    L_0x009a:
        r9 = r4.read(r8);	 Catch:{ Exception -> 0x00a6, all -> 0x00e8 }
        r10 = -1;
        if (r9 == r10) goto L_0x00b4;
    L_0x00a1:
        r10 = 0;
        r2.write(r8, r10, r9);	 Catch:{ Exception -> 0x00a6, all -> 0x00e8 }
        goto L_0x009a;
    L_0x00a6:
        r1 = move-exception;
        r1 = r2;
        r3 = r4;
    L_0x00a9:
        if (r3 == 0) goto L_0x00b3;
    L_0x00ab:
        if (r1 == 0) goto L_0x00b3;
    L_0x00ad:
        r3.close();	 Catch:{ Exception -> 0x00e2 }
        r1.close();	 Catch:{ Exception -> 0x00e2 }
    L_0x00b3:
        return r0;
    L_0x00b4:
        r2.flush();	 Catch:{ Exception -> 0x00a6, all -> 0x00e8 }
        if (r4 == 0) goto L_0x00c1;
    L_0x00b9:
        if (r2 == 0) goto L_0x00c1;
    L_0x00bb:
        r4.close();	 Catch:{ Exception -> 0x00e2 }
        r2.close();	 Catch:{ Exception -> 0x00e2 }
    L_0x00c1:
        r2 = r11.a;	 Catch:{ Exception -> 0x00e2 }
        r2 = com.dynamicload.Lib.DLPluginManager.getInstance(r2);	 Catch:{ Exception -> 0x00e2 }
        r4 = 0;
        r2.asyncLoadApk(r7, r4);	 Catch:{ Exception -> 0x00e2 }
        r2 = r6[r5];	 Catch:{ Exception -> 0x00e2 }
        r2.delete();	 Catch:{ Exception -> 0x00e2 }
        r2 = r5 + 1;
        r5 = r2;
        goto L_0x0041;
    L_0x00d5:
        r1 = move-exception;
        r4 = r3;
    L_0x00d7:
        if (r4 == 0) goto L_0x00e1;
    L_0x00d9:
        if (r3 == 0) goto L_0x00e1;
    L_0x00db:
        r4.close();	 Catch:{ Exception -> 0x00e2 }
        r3.close();	 Catch:{ Exception -> 0x00e2 }
    L_0x00e1:
        throw r1;	 Catch:{ Exception -> 0x00e2 }
    L_0x00e2:
        r1 = move-exception;
        goto L_0x00b3;
    L_0x00e4:
        r0 = r1;
        goto L_0x00b3;
    L_0x00e6:
        r1 = move-exception;
        goto L_0x00d7;
    L_0x00e8:
        r1 = move-exception;
        r3 = r2;
        goto L_0x00d7;
    L_0x00eb:
        r1 = move-exception;
        r1 = r3;
        goto L_0x00a9;
    L_0x00ee:
        r1 = move-exception;
        r1 = r3;
        r3 = r4;
        goto L_0x00a9;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.plugin.b.x():boolean");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected void a(java.lang.String r20, android.content.Context r21) {
        /*
        r19 = this;
        r2 = 0;
        r5 = 0;
        r0 = r19;
        r3 = r0.i;	 Catch:{ IOException -> 0x02f6, all -> 0x02f1 }
        com.qq.reader.common.utils.ao.c(r3);	 Catch:{ IOException -> 0x02f6, all -> 0x02f1 }
        r6 = new java.io.RandomAccessFile;	 Catch:{ IOException -> 0x02f6, all -> 0x02f1 }
        r0 = r19;
        r3 = r0.i;	 Catch:{ IOException -> 0x02f6, all -> 0x02f1 }
        r4 = "rw";
        r6.<init>(r3, r4);	 Catch:{ IOException -> 0x02f6, all -> 0x02f1 }
        r8 = r6.length();	 Catch:{ IOException -> 0x015a, all -> 0x026c }
        r6.seek(r8);	 Catch:{ IOException -> 0x015a, all -> 0x026c }
        r2 = 0;
        r3 = 5120; // 0x1400 float:7.175E-42 double:2.5296E-320;
        r10 = new byte[r3];	 Catch:{ IOException -> 0x015a, all -> 0x026c }
        r7 = 0;
        r3 = 0;
        r4 = 1;
        r11 = new org.apache.http.message.BasicHeader[r4];	 Catch:{ Exception -> 0x030f, all -> 0x02f9 }
        r4 = 0;
        r12 = new org.apache.http.message.BasicHeader;	 Catch:{ Exception -> 0x030f, all -> 0x02f9 }
        r13 = "Range";
        r14 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x030f, all -> 0x02f9 }
        r14.<init>();	 Catch:{ Exception -> 0x030f, all -> 0x02f9 }
        r15 = "bytes=";
        r14 = r14.append(r15);	 Catch:{ Exception -> 0x030f, all -> 0x02f9 }
        r15 = java.lang.String.valueOf(r8);	 Catch:{ Exception -> 0x030f, all -> 0x02f9 }
        r14 = r14.append(r15);	 Catch:{ Exception -> 0x030f, all -> 0x02f9 }
        r15 = "-";
        r14 = r14.append(r15);	 Catch:{ Exception -> 0x030f, all -> 0x02f9 }
        r14 = r14.toString();	 Catch:{ Exception -> 0x030f, all -> 0x02f9 }
        r12.<init>(r13, r14);	 Catch:{ Exception -> 0x030f, all -> 0x02f9 }
        r11[r4] = r12;	 Catch:{ Exception -> 0x030f, all -> 0x02f9 }
        r4 = 0;
        r0 = r20;
        r1 = r21;
        r4 = com.qq.reader.common.conn.http.b.a(r0, r11, r1);	 Catch:{ RangeException -> 0x0096 }
    L_0x0059:
        r11 = r4.getContentLength();	 Catch:{ Exception -> 0x030f, all -> 0x02f9 }
        r12 = (long) r11;	 Catch:{ Exception -> 0x030f, all -> 0x02f9 }
        r14 = 0;
        r11 = (r8 > r14 ? 1 : (r8 == r14 ? 0 : -1));
        if (r11 <= 0) goto L_0x019b;
    L_0x0064:
        r14 = 0;
        r11 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1));
        if (r11 != 0) goto L_0x019b;
    L_0x006a:
        r3 = r19.x();	 Catch:{ Exception -> 0x030f, all -> 0x02f9 }
        if (r3 == 0) goto L_0x0130;
    L_0x0070:
        r0 = r19;
        r4 = r0.m;	 Catch:{ Exception -> 0x00d5, all -> 0x013b }
        r5 = 6108; // 0x17dc float:8.559E-42 double:3.0178E-320;
        r4.sendEmptyMessage(r5);	 Catch:{ Exception -> 0x00d5, all -> 0x013b }
    L_0x0079:
        r4 = 0;
        r0 = r19;
        r0.c(r4);	 Catch:{ IOException -> 0x015a, all -> 0x026c }
        if (r2 == 0) goto L_0x0084;
    L_0x0081:
        r2.close();	 Catch:{ IOException -> 0x02e0, all -> 0x026c }
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
        r6.close();	 Catch:{ IOException -> 0x02e3 }
    L_0x0095:
        return;
    L_0x0096:
        r11 = move-exception;
        r12 = 0;
        r11 = (r8 > r12 ? 1 : (r8 == r12 ? 0 : -1));
        if (r11 <= 0) goto L_0x0059;
    L_0x009d:
        r3 = r19.x();	 Catch:{ Exception -> 0x030f, all -> 0x02f9 }
        if (r3 == 0) goto L_0x00cb;
    L_0x00a3:
        r0 = r19;
        r4 = r0.m;	 Catch:{ Exception -> 0x00d5, all -> 0x013b }
        r5 = 6108; // 0x17dc float:8.559E-42 double:3.0178E-320;
        r4.sendEmptyMessage(r5);	 Catch:{ Exception -> 0x00d5, all -> 0x013b }
    L_0x00ac:
        r4 = 0;
        r0 = r19;
        r0.c(r4);	 Catch:{ IOException -> 0x015a, all -> 0x026c }
        if (r2 == 0) goto L_0x00b7;
    L_0x00b4:
        r2.close();	 Catch:{ IOException -> 0x02dd, all -> 0x026c }
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
        r0.b(r4);	 Catch:{ all -> 0x0306 }
        r4 = 0;
        r0 = r19;
        r0.c(r4);	 Catch:{ IOException -> 0x015a, all -> 0x026c }
        if (r2 == 0) goto L_0x00e9;
    L_0x00e6:
        r2.close();	 Catch:{ IOException -> 0x02e9, all -> 0x026c }
    L_0x00e9:
        r2 = r19.s();	 Catch:{ Exception -> 0x02a5 }
        if (r2 == 0) goto L_0x0273;
    L_0x00ef:
        r2 = 0;
        r0 = r19;
        r0.d(r2);	 Catch:{ Exception -> 0x02a5 }
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
        r0.c(r3);	 Catch:{ IOException -> 0x015a, all -> 0x026c }
        if (r4 == 0) goto L_0x014d;
    L_0x014a:
        r4.close();	 Catch:{ IOException -> 0x02ec, all -> 0x026c }
    L_0x014d:
        r3 = r19.s();	 Catch:{ Exception -> 0x02da }
        if (r3 == 0) goto L_0x02a8;
    L_0x0153:
        r3 = 0;
        r0 = r19;
        r0.d(r3);	 Catch:{ Exception -> 0x02da }
    L_0x0159:
        throw r2;	 Catch:{ IOException -> 0x015a, all -> 0x026c }
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
        r4 = r4.getInputStream();	 Catch:{ Exception -> 0x030f, all -> 0x02f9 }
        r0 = r19;
        r2 = r0.m;	 Catch:{ Exception -> 0x0313, all -> 0x02fe }
        r2 = r2.obtainMessage();	 Catch:{ Exception -> 0x0313, all -> 0x02fe }
        r11 = 6104; // 0x17d8 float:8.554E-42 double:3.016E-320;
        r2.what = r11;	 Catch:{ Exception -> 0x0313, all -> 0x02fe }
        r14 = r8 + r12;
        r11 = (int) r14;	 Catch:{ Exception -> 0x0313, all -> 0x02fe }
        r2.arg1 = r11;	 Catch:{ Exception -> 0x0313, all -> 0x02fe }
        r0 = r19;
        r11 = r0.m;	 Catch:{ Exception -> 0x0313, all -> 0x02fe }
        r11.sendMessage(r2);	 Catch:{ Exception -> 0x0313, all -> 0x02fe }
        r2 = r3;
        r3 = r7;
    L_0x01b9:
        r7 = r19.s();	 Catch:{ Exception -> 0x0313, all -> 0x02fe }
        if (r7 != 0) goto L_0x01fa;
    L_0x01bf:
        r7 = r4.read(r10);	 Catch:{ Exception -> 0x0313, all -> 0x02fe }
        r11 = -1;
        if (r7 == r11) goto L_0x01fa;
    L_0x01c6:
        r11 = 0;
        r6.write(r10, r11, r7);	 Catch:{ Exception -> 0x0313, all -> 0x02fe }
        r14 = r6.length();	 Catch:{ Exception -> 0x0313, all -> 0x02fe }
        r7 = 20;
        if (r2 == r7) goto L_0x01d8;
    L_0x01d2:
        r16 = r8 + r12;
        r7 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1));
        if (r7 != 0) goto L_0x01f7;
    L_0x01d8:
        r2 = (int) r14;	 Catch:{ Exception -> 0x0313, all -> 0x02fe }
        if (r3 >= r2) goto L_0x031b;
    L_0x01db:
        r0 = r19;
        r3 = r0.m;	 Catch:{ Exception -> 0x0313, all -> 0x02fe }
        r3 = r3.obtainMessage();	 Catch:{ Exception -> 0x0313, all -> 0x02fe }
        r7 = 6105; // 0x17d9 float:8.555E-42 double:3.0163E-320;
        r3.what = r7;	 Catch:{ Exception -> 0x0313, all -> 0x02fe }
        r3.arg1 = r2;	 Catch:{ Exception -> 0x0313, all -> 0x02fe }
        r0 = r19;
        r7 = r0.m;	 Catch:{ Exception -> 0x0313, all -> 0x02fe }
        r7.sendMessage(r3);	 Catch:{ Exception -> 0x0313, all -> 0x02fe }
    L_0x01f0:
        r3 = 0;
        r18 = r3;
        r3 = r2;
        r2 = r18;
        goto L_0x01b9;
    L_0x01f7:
        r2 = r2 + 1;
        goto L_0x01b9;
    L_0x01fa:
        r2 = r19.s();	 Catch:{ Exception -> 0x0313, all -> 0x02fe }
        if (r2 != 0) goto L_0x0318;
    L_0x0200:
        r2 = r19.x();	 Catch:{ Exception -> 0x0313, all -> 0x02fe }
        if (r2 == 0) goto L_0x022b;
    L_0x0206:
        r0 = r19;
        r3 = r0.m;	 Catch:{ Exception -> 0x0235, all -> 0x0301 }
        r5 = 6108; // 0x17dc float:8.559E-42 double:3.0178E-320;
        r3.sendEmptyMessage(r5);	 Catch:{ Exception -> 0x0235, all -> 0x0301 }
    L_0x020f:
        r3 = 0;
        r0 = r19;
        r0.c(r3);	 Catch:{ IOException -> 0x015a, all -> 0x026c }
        if (r4 == 0) goto L_0x021a;
    L_0x0217:
        r4.close();	 Catch:{ IOException -> 0x02e6, all -> 0x026c }
    L_0x021a:
        r3 = r19.s();	 Catch:{ Exception -> 0x0228 }
        if (r3 == 0) goto L_0x023a;
    L_0x0220:
        r2 = 0;
        r0 = r19;
        r0.d(r2);	 Catch:{ Exception -> 0x0228 }
        goto L_0x00f5;
    L_0x0228:
        r2 = move-exception;
        goto L_0x00f5;
    L_0x022b:
        r0 = r19;
        r3 = r0.m;	 Catch:{ Exception -> 0x0235, all -> 0x0301 }
        r5 = 6115; // 0x17e3 float:8.569E-42 double:3.021E-320;
        r3.sendEmptyMessage(r5);	 Catch:{ Exception -> 0x0235, all -> 0x0301 }
        goto L_0x020f;
    L_0x0235:
        r3 = move-exception;
        r3 = r2;
        r2 = r4;
        goto L_0x00d6;
    L_0x023a:
        if (r2 == 0) goto L_0x00f5;
    L_0x023c:
        r0 = r19;
        r2 = r0.j;	 Catch:{ Exception -> 0x0228 }
        r0 = r19;
        r3 = r0.b;	 Catch:{ Exception -> 0x0228 }
        r2.a(r3);	 Catch:{ Exception -> 0x0228 }
        r0 = r19;
        r2 = r0.j;	 Catch:{ Exception -> 0x0228 }
        r0 = r19;
        r3 = r0.b;	 Catch:{ Exception -> 0x0228 }
        r2.b(r3);	 Catch:{ Exception -> 0x0228 }
        r2 = com.qq.reader.plugin.k.b();	 Catch:{ Exception -> 0x0228 }
        r0 = r19;
        r3 = r0.j;	 Catch:{ Exception -> 0x0228 }
        r3 = r3.i();	 Catch:{ Exception -> 0x0228 }
        r0 = r19;
        r4 = r0.b;	 Catch:{ Exception -> 0x0228 }
        r0 = r19;
        r5 = r0.b;	 Catch:{ Exception -> 0x0228 }
        r7 = 0;
        r2.a(r3, r4, r5, r7);	 Catch:{ Exception -> 0x0228 }
        goto L_0x00f5;
    L_0x026c:
        r2 = move-exception;
    L_0x026d:
        if (r6 == 0) goto L_0x0272;
    L_0x026f:
        r6.close();	 Catch:{ IOException -> 0x02ef }
    L_0x0272:
        throw r2;
    L_0x0273:
        if (r3 == 0) goto L_0x00f5;
    L_0x0275:
        r0 = r19;
        r2 = r0.j;	 Catch:{ Exception -> 0x02a5 }
        r0 = r19;
        r3 = r0.b;	 Catch:{ Exception -> 0x02a5 }
        r2.a(r3);	 Catch:{ Exception -> 0x02a5 }
        r0 = r19;
        r2 = r0.j;	 Catch:{ Exception -> 0x02a5 }
        r0 = r19;
        r3 = r0.b;	 Catch:{ Exception -> 0x02a5 }
        r2.b(r3);	 Catch:{ Exception -> 0x02a5 }
        r2 = com.qq.reader.plugin.k.b();	 Catch:{ Exception -> 0x02a5 }
        r0 = r19;
        r3 = r0.j;	 Catch:{ Exception -> 0x02a5 }
        r3 = r3.i();	 Catch:{ Exception -> 0x02a5 }
        r0 = r19;
        r4 = r0.b;	 Catch:{ Exception -> 0x02a5 }
        r0 = r19;
        r5 = r0.b;	 Catch:{ Exception -> 0x02a5 }
        r7 = 0;
        r2.a(r3, r4, r5, r7);	 Catch:{ Exception -> 0x02a5 }
        goto L_0x00f5;
    L_0x02a5:
        r2 = move-exception;
        goto L_0x00f5;
    L_0x02a8:
        if (r5 == 0) goto L_0x0159;
    L_0x02aa:
        r0 = r19;
        r3 = r0.j;	 Catch:{ Exception -> 0x02da }
        r0 = r19;
        r4 = r0.b;	 Catch:{ Exception -> 0x02da }
        r3.a(r4);	 Catch:{ Exception -> 0x02da }
        r0 = r19;
        r3 = r0.j;	 Catch:{ Exception -> 0x02da }
        r0 = r19;
        r4 = r0.b;	 Catch:{ Exception -> 0x02da }
        r3.b(r4);	 Catch:{ Exception -> 0x02da }
        r3 = com.qq.reader.plugin.k.b();	 Catch:{ Exception -> 0x02da }
        r0 = r19;
        r4 = r0.j;	 Catch:{ Exception -> 0x02da }
        r4 = r4.i();	 Catch:{ Exception -> 0x02da }
        r0 = r19;
        r5 = r0.b;	 Catch:{ Exception -> 0x02da }
        r0 = r19;
        r7 = r0.b;	 Catch:{ Exception -> 0x02da }
        r8 = 0;
        r3.a(r4, r5, r7, r8);	 Catch:{ Exception -> 0x02da }
        goto L_0x0159;
    L_0x02da:
        r3 = move-exception;
        goto L_0x0159;
    L_0x02dd:
        r2 = move-exception;
        goto L_0x00b7;
    L_0x02e0:
        r2 = move-exception;
        goto L_0x0084;
    L_0x02e3:
        r2 = move-exception;
        goto L_0x0095;
    L_0x02e6:
        r3 = move-exception;
        goto L_0x021a;
    L_0x02e9:
        r2 = move-exception;
        goto L_0x00e9;
    L_0x02ec:
        r3 = move-exception;
        goto L_0x014d;
    L_0x02ef:
        r3 = move-exception;
        goto L_0x0272;
    L_0x02f1:
        r3 = move-exception;
        r6 = r2;
        r2 = r3;
        goto L_0x026d;
    L_0x02f6:
        r3 = move-exception;
        goto L_0x015c;
    L_0x02f9:
        r3 = move-exception;
        r4 = r2;
        r2 = r3;
        goto L_0x0142;
    L_0x02fe:
        r2 = move-exception;
        goto L_0x0142;
    L_0x0301:
        r3 = move-exception;
        r5 = r2;
        r2 = r3;
        goto L_0x0142;
    L_0x0306:
        r4 = move-exception;
        r5 = r3;
        r18 = r2;
        r2 = r4;
        r4 = r18;
        goto L_0x0142;
    L_0x030f:
        r3 = move-exception;
        r3 = r5;
        goto L_0x00d6;
    L_0x0313:
        r2 = move-exception;
        r2 = r4;
        r3 = r5;
        goto L_0x00d6;
    L_0x0318:
        r2 = r5;
        goto L_0x020f;
    L_0x031b:
        r2 = r3;
        goto L_0x01f0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.plugin.b.a(java.lang.String, android.content.Context):void");
    }

    protected void b(String str) {
        this.m.obtainMessage(6109, str).sendToTarget();
    }

    protected void a(Bundle bundle) {
    }

    protected void a(String str) {
    }
}
