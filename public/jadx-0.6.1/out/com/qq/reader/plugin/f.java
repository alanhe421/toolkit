package com.qq.reader.plugin;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import com.qq.reader.common.utils.ao;
import java.io.File;

/* compiled from: FontPluginHandler */
public class f extends a {
    private e n;

    protected f(Context context, l lVar, h hVar) {
        super(context, lVar, hVar);
        this.a = context;
    }

    public void a(e eVar) {
        this.n = eVar;
    }

    protected String a(l lVar) {
        if (this.i == null) {
            this.i = ao.m(lVar.l());
        }
        return this.i;
    }

    public synchronized boolean i() {
        boolean z;
        File file = new File(this.i);
        if (file == null || !file.exists() || file.length() <= 0) {
            z = false;
        } else {
            z = true;
        }
        return z;
    }

    public boolean l() {
        return i();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected void a(java.lang.String r24, android.content.Context r25) {
        /*
        r23 = this;
        r2 = 0;
        r5 = new java.io.File;	 Catch:{ IOException -> 0x02da, all -> 0x033b }
        r0 = r23;
        r3 = r0.i;	 Catch:{ IOException -> 0x02da, all -> 0x033b }
        r5.<init>(r3);	 Catch:{ IOException -> 0x02da, all -> 0x033b }
        r4 = new java.io.File;	 Catch:{ IOException -> 0x02da, all -> 0x033b }
        r3 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x02da, all -> 0x033b }
        r3.<init>();	 Catch:{ IOException -> 0x02da, all -> 0x033b }
        r0 = r23;
        r6 = r0.i;	 Catch:{ IOException -> 0x02da, all -> 0x033b }
        r3 = r3.append(r6);	 Catch:{ IOException -> 0x02da, all -> 0x033b }
        r6 = ".c";
        r3 = r3.append(r6);	 Catch:{ IOException -> 0x02da, all -> 0x033b }
        r3 = r3.toString();	 Catch:{ IOException -> 0x02da, all -> 0x033b }
        r4.<init>(r3);	 Catch:{ IOException -> 0x02da, all -> 0x033b }
        r3 = r5.exists();	 Catch:{ IOException -> 0x02da, all -> 0x033b }
        if (r3 != 0) goto L_0x0033;
    L_0x002d:
        r3 = r4.exists();	 Catch:{ IOException -> 0x02da, all -> 0x033b }
        if (r3 == 0) goto L_0x0047;
    L_0x0033:
        r0 = r23;
        r3 = r0.m;	 Catch:{ IOException -> 0x02da, all -> 0x033b }
        r4 = 6108; // 0x17dc float:8.559E-42 double:3.0178E-320;
        r3.sendEmptyMessage(r4);	 Catch:{ IOException -> 0x02da, all -> 0x033b }
        if (r2 == 0) goto L_0x0041;
    L_0x003e:
        r2.close();	 Catch:{ IOException -> 0x0042 }
    L_0x0041:
        return;
    L_0x0042:
        r2 = move-exception;
        r2.printStackTrace();
        goto L_0x0041;
    L_0x0047:
        r0 = r23;
        r3 = r0.j;	 Catch:{ IOException -> 0x02da, all -> 0x033b }
        r6 = r3.i();	 Catch:{ IOException -> 0x02da, all -> 0x033b }
        r3 = ".ftf";
        r0 = r24;
        r8 = r0.endsWith(r3);	 Catch:{ IOException -> 0x02da, all -> 0x033b }
        r3 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x02da, all -> 0x033b }
        r3.<init>();	 Catch:{ IOException -> 0x02da, all -> 0x033b }
        r0 = r23;
        r7 = r0.i;	 Catch:{ IOException -> 0x02da, all -> 0x033b }
        r3 = r3.append(r7);	 Catch:{ IOException -> 0x02da, all -> 0x033b }
        r7 = ".tmp";
        r3 = r3.append(r7);	 Catch:{ IOException -> 0x02da, all -> 0x033b }
        r7 = r3.toString();	 Catch:{ IOException -> 0x02da, all -> 0x033b }
        r3 = new java.io.File;	 Catch:{ IOException -> 0x02da, all -> 0x033b }
        r3.<init>(r7);	 Catch:{ IOException -> 0x02da, all -> 0x033b }
        com.qq.reader.common.utils.ao.c(r7);	 Catch:{ IOException -> 0x02da, all -> 0x033b }
        r10 = new java.io.RandomAccessFile;	 Catch:{ IOException -> 0x02da, all -> 0x033b }
        r9 = "rw";
        r10.<init>(r7, r9);	 Catch:{ IOException -> 0x02da, all -> 0x033b }
        r12 = r10.length();	 Catch:{ IOException -> 0x036e, all -> 0x0365 }
        r10.seek(r12);	 Catch:{ IOException -> 0x036e, all -> 0x0365 }
        r9 = 0;
        r2 = 5120; // 0x1400 float:7.175E-42 double:2.5296E-320;
        r14 = new byte[r2];	 Catch:{ IOException -> 0x036e, all -> 0x0365 }
        r7 = 0;
        r2 = 0;
        r11 = 1;
        r15 = new org.apache.http.message.BasicHeader[r11];	 Catch:{ Exception -> 0x0280, all -> 0x02e8 }
        r11 = 0;
        r16 = new org.apache.http.message.BasicHeader;	 Catch:{ Exception -> 0x0280, all -> 0x02e8 }
        r17 = "Range";
        r18 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0280, all -> 0x02e8 }
        r18.<init>();	 Catch:{ Exception -> 0x0280, all -> 0x02e8 }
        r19 = "bytes=";
        r18 = r18.append(r19);	 Catch:{ Exception -> 0x0280, all -> 0x02e8 }
        r19 = java.lang.String.valueOf(r12);	 Catch:{ Exception -> 0x0280, all -> 0x02e8 }
        r18 = r18.append(r19);	 Catch:{ Exception -> 0x0280, all -> 0x02e8 }
        r19 = "-";
        r18 = r18.append(r19);	 Catch:{ Exception -> 0x0280, all -> 0x02e8 }
        r18 = r18.toString();	 Catch:{ Exception -> 0x0280, all -> 0x02e8 }
        r16.<init>(r17, r18);	 Catch:{ Exception -> 0x0280, all -> 0x02e8 }
        r15[r11] = r16;	 Catch:{ Exception -> 0x0280, all -> 0x02e8 }
        r11 = 0;
        r0 = r24;
        r1 = r25;
        r11 = com.qq.reader.common.conn.http.b.a(r0, r15, r1);	 Catch:{ RangeException -> 0x010a }
    L_0x00c4:
        r15 = r11.getContentLength();	 Catch:{ Exception -> 0x0280, all -> 0x02e8 }
        r0 = (long) r15;	 Catch:{ Exception -> 0x0280, all -> 0x02e8 }
        r16 = r0;
        r18 = 0;
        r15 = (r12 > r18 ? 1 : (r12 == r18 ? 0 : -1));
        if (r15 <= 0) goto L_0x01a8;
    L_0x00d1:
        r18 = 0;
        r15 = (r16 > r18 ? 1 : (r16 == r18 ? 0 : -1));
        if (r15 != 0) goto L_0x01a8;
    L_0x00d7:
        r0 = r23;
        r7 = r0.m;	 Catch:{ Exception -> 0x0280, all -> 0x02e8 }
        r2 = r23;
        r2.a(r3, r4, r5, r6, r7, r8);	 Catch:{ Exception -> 0x0280, all -> 0x02e8 }
        r2 = 0;
        r0 = r23;
        r0.c(r2);	 Catch:{ IOException -> 0x036e, all -> 0x0365 }
        if (r9 == 0) goto L_0x00eb;
    L_0x00e8:
        r9.close();	 Catch:{ IOException -> 0x034f, all -> 0x0365 }
    L_0x00eb:
        if (r10 == 0) goto L_0x0387;
    L_0x00ed:
        r10.close();	 Catch:{ IOException -> 0x0352, all -> 0x0365 }
    L_0x00f0:
        r2 = 0;
    L_0x00f1:
        r3 = r23.s();	 Catch:{ Exception -> 0x01a5, IOException -> 0x02da, all -> 0x0367 }
        if (r3 == 0) goto L_0x0175;
    L_0x00f7:
        r3 = 0;
        r0 = r23;
        r0.d(r3);	 Catch:{ Exception -> 0x01a5, IOException -> 0x02da, all -> 0x0367 }
    L_0x00fd:
        if (r2 == 0) goto L_0x0041;
    L_0x00ff:
        r2.close();	 Catch:{ IOException -> 0x0104 }
        goto L_0x0041;
    L_0x0104:
        r2 = move-exception;
        r2.printStackTrace();
        goto L_0x0041;
    L_0x010a:
        r15 = move-exception;
        r16 = 0;
        r15 = (r12 > r16 ? 1 : (r12 == r16 ? 0 : -1));
        if (r15 <= 0) goto L_0x00c4;
    L_0x0111:
        r0 = r23;
        r7 = r0.m;	 Catch:{ Exception -> 0x0280, all -> 0x02e8 }
        r2 = r23;
        r2.a(r3, r4, r5, r6, r7, r8);	 Catch:{ Exception -> 0x0280, all -> 0x02e8 }
        r2 = 0;
        r0 = r23;
        r0.c(r2);	 Catch:{ IOException -> 0x036e, all -> 0x0365 }
        if (r9 == 0) goto L_0x0125;
    L_0x0122:
        r9.close();	 Catch:{ IOException -> 0x0349, all -> 0x0365 }
    L_0x0125:
        if (r10 == 0) goto L_0x038a;
    L_0x0127:
        r10.close();	 Catch:{ IOException -> 0x034c, all -> 0x0365 }
    L_0x012a:
        r2 = 0;
    L_0x012b:
        r3 = r23.s();	 Catch:{ Exception -> 0x0173, IOException -> 0x02da, all -> 0x0367 }
        if (r3 == 0) goto L_0x0144;
    L_0x0131:
        r3 = 0;
        r0 = r23;
        r0.d(r3);	 Catch:{ Exception -> 0x0173, IOException -> 0x02da, all -> 0x0367 }
    L_0x0137:
        if (r2 == 0) goto L_0x0041;
    L_0x0139:
        r2.close();	 Catch:{ IOException -> 0x013e }
        goto L_0x0041;
    L_0x013e:
        r2 = move-exception;
        r2.printStackTrace();
        goto L_0x0041;
    L_0x0144:
        r0 = r23;
        r3 = r0.j;	 Catch:{ Exception -> 0x0173, IOException -> 0x02da, all -> 0x0367 }
        r0 = r23;
        r4 = r0.b;	 Catch:{ Exception -> 0x0173, IOException -> 0x02da, all -> 0x0367 }
        r3.a(r4);	 Catch:{ Exception -> 0x0173, IOException -> 0x02da, all -> 0x0367 }
        r0 = r23;
        r3 = r0.j;	 Catch:{ Exception -> 0x0173, IOException -> 0x02da, all -> 0x0367 }
        r0 = r23;
        r4 = r0.b;	 Catch:{ Exception -> 0x0173, IOException -> 0x02da, all -> 0x0367 }
        r3.b(r4);	 Catch:{ Exception -> 0x0173, IOException -> 0x02da, all -> 0x0367 }
        r3 = com.qq.reader.plugin.k.b();	 Catch:{ Exception -> 0x0173, IOException -> 0x02da, all -> 0x0367 }
        r0 = r23;
        r4 = r0.j;	 Catch:{ Exception -> 0x0173, IOException -> 0x02da, all -> 0x0367 }
        r4 = r4.i();	 Catch:{ Exception -> 0x0173, IOException -> 0x02da, all -> 0x0367 }
        r0 = r23;
        r5 = r0.b;	 Catch:{ Exception -> 0x0173, IOException -> 0x02da, all -> 0x0367 }
        r0 = r23;
        r6 = r0.b;	 Catch:{ Exception -> 0x0173, IOException -> 0x02da, all -> 0x0367 }
        r7 = 0;
        r3.a(r4, r5, r6, r7);	 Catch:{ Exception -> 0x0173, IOException -> 0x02da, all -> 0x0367 }
        goto L_0x0137;
    L_0x0173:
        r3 = move-exception;
        goto L_0x0137;
    L_0x0175:
        r0 = r23;
        r3 = r0.j;	 Catch:{ Exception -> 0x01a5, IOException -> 0x02da, all -> 0x0367 }
        r0 = r23;
        r4 = r0.b;	 Catch:{ Exception -> 0x01a5, IOException -> 0x02da, all -> 0x0367 }
        r3.a(r4);	 Catch:{ Exception -> 0x01a5, IOException -> 0x02da, all -> 0x0367 }
        r0 = r23;
        r3 = r0.j;	 Catch:{ Exception -> 0x01a5, IOException -> 0x02da, all -> 0x0367 }
        r0 = r23;
        r4 = r0.b;	 Catch:{ Exception -> 0x01a5, IOException -> 0x02da, all -> 0x0367 }
        r3.b(r4);	 Catch:{ Exception -> 0x01a5, IOException -> 0x02da, all -> 0x0367 }
        r3 = com.qq.reader.plugin.k.b();	 Catch:{ Exception -> 0x01a5, IOException -> 0x02da, all -> 0x0367 }
        r0 = r23;
        r4 = r0.j;	 Catch:{ Exception -> 0x01a5, IOException -> 0x02da, all -> 0x0367 }
        r4 = r4.i();	 Catch:{ Exception -> 0x01a5, IOException -> 0x02da, all -> 0x0367 }
        r0 = r23;
        r5 = r0.b;	 Catch:{ Exception -> 0x01a5, IOException -> 0x02da, all -> 0x0367 }
        r0 = r23;
        r6 = r0.b;	 Catch:{ Exception -> 0x01a5, IOException -> 0x02da, all -> 0x0367 }
        r7 = 0;
        r3.a(r4, r5, r6, r7);	 Catch:{ Exception -> 0x01a5, IOException -> 0x02da, all -> 0x0367 }
        goto L_0x00fd;
    L_0x01a5:
        r3 = move-exception;
        goto L_0x00fd;
    L_0x01a8:
        r9 = r11.getInputStream();	 Catch:{ Exception -> 0x0280, all -> 0x02e8 }
        r11 = new android.os.Message;	 Catch:{ Exception -> 0x0377, all -> 0x02e8 }
        r11.<init>();	 Catch:{ Exception -> 0x0377, all -> 0x02e8 }
        r15 = 6104; // 0x17d8 float:8.554E-42 double:3.016E-320;
        r11.what = r15;	 Catch:{ Exception -> 0x0377, all -> 0x02e8 }
        r18 = r12 + r16;
        r0 = r18;
        r15 = (int) r0;	 Catch:{ Exception -> 0x0377, all -> 0x02e8 }
        r11.arg1 = r15;	 Catch:{ Exception -> 0x0377, all -> 0x02e8 }
        r0 = r23;
        r15 = r0.m;	 Catch:{ Exception -> 0x0377, all -> 0x02e8 }
        r15.sendMessage(r11);	 Catch:{ Exception -> 0x0377, all -> 0x02e8 }
        r0 = r23;
        r11 = r0.n;	 Catch:{ Exception -> 0x0377, all -> 0x02e8 }
        if (r11 == 0) goto L_0x01d6;
    L_0x01c9:
        r0 = r23;
        r11 = r0.n;	 Catch:{ Exception -> 0x0377, all -> 0x02e8 }
        r15 = 6106; // 0x17da float:8.556E-42 double:3.017E-320;
        r18 = 0;
        r0 = r18;
        r11.a(r15, r0);	 Catch:{ Exception -> 0x0377, all -> 0x02e8 }
    L_0x01d6:
        r11 = r23.s();	 Catch:{ Exception -> 0x0377, all -> 0x02e8 }
        if (r11 != 0) goto L_0x0216;
    L_0x01dc:
        r11 = r9.read(r14);	 Catch:{ Exception -> 0x0377, all -> 0x02e8 }
        r15 = -1;
        if (r11 == r15) goto L_0x0216;
    L_0x01e3:
        r15 = 0;
        r10.write(r14, r15, r11);	 Catch:{ Exception -> 0x0377, all -> 0x02e8 }
        r18 = r10.length();	 Catch:{ Exception -> 0x0377, all -> 0x02e8 }
        r11 = 20;
        if (r2 == r11) goto L_0x01f5;
    L_0x01ef:
        r20 = r16 + r12;
        r11 = (r18 > r20 ? 1 : (r18 == r20 ? 0 : -1));
        if (r11 != 0) goto L_0x0213;
    L_0x01f5:
        r0 = r18;
        r2 = (int) r0;	 Catch:{ Exception -> 0x0377, all -> 0x02e8 }
        if (r7 >= r2) goto L_0x0384;
    L_0x01fa:
        r7 = new android.os.Message;	 Catch:{ Exception -> 0x0377, all -> 0x02e8 }
        r7.<init>();	 Catch:{ Exception -> 0x0377, all -> 0x02e8 }
        r11 = 6105; // 0x17d9 float:8.555E-42 double:3.0163E-320;
        r7.what = r11;	 Catch:{ Exception -> 0x0377, all -> 0x02e8 }
        r7.arg1 = r2;	 Catch:{ Exception -> 0x0377, all -> 0x02e8 }
        r0 = r23;
        r11 = r0.m;	 Catch:{ Exception -> 0x0377, all -> 0x02e8 }
        r11.sendMessage(r7);	 Catch:{ Exception -> 0x0377, all -> 0x02e8 }
    L_0x020c:
        r7 = 0;
        r22 = r7;
        r7 = r2;
        r2 = r22;
        goto L_0x01d6;
    L_0x0213:
        r2 = r2 + 1;
        goto L_0x01d6;
    L_0x0216:
        r2 = r23.s();	 Catch:{ Exception -> 0x0377, all -> 0x02e8 }
        if (r2 != 0) goto L_0x0225;
    L_0x021c:
        r0 = r23;
        r7 = r0.m;	 Catch:{ Exception -> 0x0377, all -> 0x02e8 }
        r2 = r23;
        r2.a(r3, r4, r5, r6, r7, r8);	 Catch:{ Exception -> 0x0377, all -> 0x02e8 }
    L_0x0225:
        r2 = 0;
        r0 = r23;
        r0.c(r2);	 Catch:{ IOException -> 0x036e, all -> 0x0365 }
        if (r9 == 0) goto L_0x0230;
    L_0x022d:
        r9.close();	 Catch:{ IOException -> 0x0355, all -> 0x0365 }
    L_0x0230:
        if (r10 == 0) goto L_0x0381;
    L_0x0232:
        r10.close();	 Catch:{ IOException -> 0x0358, all -> 0x0365 }
    L_0x0235:
        r2 = 0;
    L_0x0236:
        r3 = r23.s();	 Catch:{ Exception -> 0x027e, IOException -> 0x02da, all -> 0x0367 }
        if (r3 == 0) goto L_0x024f;
    L_0x023c:
        r3 = 0;
        r0 = r23;
        r0.d(r3);	 Catch:{ Exception -> 0x027e, IOException -> 0x02da, all -> 0x0367 }
    L_0x0242:
        if (r2 == 0) goto L_0x0041;
    L_0x0244:
        r2.close();	 Catch:{ IOException -> 0x0249 }
        goto L_0x0041;
    L_0x0249:
        r2 = move-exception;
        r2.printStackTrace();
        goto L_0x0041;
    L_0x024f:
        r0 = r23;
        r3 = r0.j;	 Catch:{ Exception -> 0x027e, IOException -> 0x02da, all -> 0x0367 }
        r0 = r23;
        r4 = r0.b;	 Catch:{ Exception -> 0x027e, IOException -> 0x02da, all -> 0x0367 }
        r3.a(r4);	 Catch:{ Exception -> 0x027e, IOException -> 0x02da, all -> 0x0367 }
        r0 = r23;
        r3 = r0.j;	 Catch:{ Exception -> 0x027e, IOException -> 0x02da, all -> 0x0367 }
        r0 = r23;
        r4 = r0.b;	 Catch:{ Exception -> 0x027e, IOException -> 0x02da, all -> 0x0367 }
        r3.b(r4);	 Catch:{ Exception -> 0x027e, IOException -> 0x02da, all -> 0x0367 }
        r3 = com.qq.reader.plugin.k.b();	 Catch:{ Exception -> 0x027e, IOException -> 0x02da, all -> 0x0367 }
        r0 = r23;
        r4 = r0.j;	 Catch:{ Exception -> 0x027e, IOException -> 0x02da, all -> 0x0367 }
        r4 = r4.i();	 Catch:{ Exception -> 0x027e, IOException -> 0x02da, all -> 0x0367 }
        r0 = r23;
        r5 = r0.b;	 Catch:{ Exception -> 0x027e, IOException -> 0x02da, all -> 0x0367 }
        r0 = r23;
        r6 = r0.b;	 Catch:{ Exception -> 0x027e, IOException -> 0x02da, all -> 0x0367 }
        r7 = 0;
        r3.a(r4, r5, r6, r7);	 Catch:{ Exception -> 0x027e, IOException -> 0x02da, all -> 0x0367 }
        goto L_0x0242;
    L_0x027e:
        r3 = move-exception;
        goto L_0x0242;
    L_0x0280:
        r2 = move-exception;
        r2 = r9;
    L_0x0282:
        r3 = "网络连接失败，请稍后再试。";
        r0 = r23;
        r0.b(r3);	 Catch:{ all -> 0x0372 }
        r3 = 0;
        r0 = r23;
        r0.c(r3);	 Catch:{ IOException -> 0x036e, all -> 0x0365 }
        if (r2 == 0) goto L_0x0295;
    L_0x0292:
        r2.close();	 Catch:{ IOException -> 0x035b, all -> 0x0365 }
    L_0x0295:
        if (r10 == 0) goto L_0x037e;
    L_0x0297:
        r10.close();	 Catch:{ IOException -> 0x035e, all -> 0x0365 }
    L_0x029a:
        r2 = 0;
    L_0x029b:
        r3 = r23.s();	 Catch:{ Exception -> 0x02a8, IOException -> 0x02da, all -> 0x0367 }
        if (r3 == 0) goto L_0x02aa;
    L_0x02a1:
        r3 = 0;
        r0 = r23;
        r0.d(r3);	 Catch:{ Exception -> 0x02a8, IOException -> 0x02da, all -> 0x0367 }
        goto L_0x0242;
    L_0x02a8:
        r3 = move-exception;
        goto L_0x0242;
    L_0x02aa:
        r0 = r23;
        r3 = r0.j;	 Catch:{ Exception -> 0x02a8, IOException -> 0x02da, all -> 0x0367 }
        r0 = r23;
        r4 = r0.b;	 Catch:{ Exception -> 0x02a8, IOException -> 0x02da, all -> 0x0367 }
        r3.a(r4);	 Catch:{ Exception -> 0x02a8, IOException -> 0x02da, all -> 0x0367 }
        r0 = r23;
        r3 = r0.j;	 Catch:{ Exception -> 0x02a8, IOException -> 0x02da, all -> 0x0367 }
        r0 = r23;
        r4 = r0.b;	 Catch:{ Exception -> 0x02a8, IOException -> 0x02da, all -> 0x0367 }
        r3.b(r4);	 Catch:{ Exception -> 0x02a8, IOException -> 0x02da, all -> 0x0367 }
        r3 = com.qq.reader.plugin.k.b();	 Catch:{ Exception -> 0x02a8, IOException -> 0x02da, all -> 0x0367 }
        r0 = r23;
        r4 = r0.j;	 Catch:{ Exception -> 0x02a8, IOException -> 0x02da, all -> 0x0367 }
        r4 = r4.i();	 Catch:{ Exception -> 0x02a8, IOException -> 0x02da, all -> 0x0367 }
        r0 = r23;
        r5 = r0.b;	 Catch:{ Exception -> 0x02a8, IOException -> 0x02da, all -> 0x0367 }
        r0 = r23;
        r6 = r0.b;	 Catch:{ Exception -> 0x02a8, IOException -> 0x02da, all -> 0x0367 }
        r7 = 0;
        r3.a(r4, r5, r6, r7);	 Catch:{ Exception -> 0x02a8, IOException -> 0x02da, all -> 0x0367 }
        goto L_0x0242;
    L_0x02da:
        r3 = move-exception;
    L_0x02db:
        if (r2 == 0) goto L_0x0041;
    L_0x02dd:
        r2.close();	 Catch:{ IOException -> 0x02e2 }
        goto L_0x0041;
    L_0x02e2:
        r2 = move-exception;
        r2.printStackTrace();
        goto L_0x0041;
    L_0x02e8:
        r2 = move-exception;
    L_0x02e9:
        r3 = 0;
        r0 = r23;
        r0.c(r3);	 Catch:{ IOException -> 0x036e, all -> 0x0365 }
        if (r9 == 0) goto L_0x02f4;
    L_0x02f1:
        r9.close();	 Catch:{ IOException -> 0x0361, all -> 0x0365 }
    L_0x02f4:
        if (r10 == 0) goto L_0x037b;
    L_0x02f6:
        r10.close();	 Catch:{ IOException -> 0x0363, all -> 0x0365 }
    L_0x02f9:
        r3 = 0;
    L_0x02fa:
        r4 = r23.s();	 Catch:{ Exception -> 0x0339 }
        if (r4 == 0) goto L_0x030a;
    L_0x0300:
        r4 = 0;
        r0 = r23;
        r0.d(r4);	 Catch:{ Exception -> 0x0339 }
    L_0x0306:
        throw r2;	 Catch:{ IOException -> 0x0307, all -> 0x036b }
    L_0x0307:
        r2 = move-exception;
        r2 = r3;
        goto L_0x02db;
    L_0x030a:
        r0 = r23;
        r4 = r0.j;	 Catch:{ Exception -> 0x0339 }
        r0 = r23;
        r5 = r0.b;	 Catch:{ Exception -> 0x0339 }
        r4.a(r5);	 Catch:{ Exception -> 0x0339 }
        r0 = r23;
        r4 = r0.j;	 Catch:{ Exception -> 0x0339 }
        r0 = r23;
        r5 = r0.b;	 Catch:{ Exception -> 0x0339 }
        r4.b(r5);	 Catch:{ Exception -> 0x0339 }
        r4 = com.qq.reader.plugin.k.b();	 Catch:{ Exception -> 0x0339 }
        r0 = r23;
        r5 = r0.j;	 Catch:{ Exception -> 0x0339 }
        r5 = r5.i();	 Catch:{ Exception -> 0x0339 }
        r0 = r23;
        r6 = r0.b;	 Catch:{ Exception -> 0x0339 }
        r0 = r23;
        r7 = r0.b;	 Catch:{ Exception -> 0x0339 }
        r8 = 0;
        r4.a(r5, r6, r7, r8);	 Catch:{ Exception -> 0x0339 }
        goto L_0x0306;
    L_0x0339:
        r4 = move-exception;
        goto L_0x0306;
    L_0x033b:
        r3 = move-exception;
        r10 = r2;
        r2 = r3;
    L_0x033e:
        if (r10 == 0) goto L_0x0343;
    L_0x0340:
        r10.close();	 Catch:{ IOException -> 0x0344 }
    L_0x0343:
        throw r2;
    L_0x0344:
        r3 = move-exception;
        r3.printStackTrace();
        goto L_0x0343;
    L_0x0349:
        r2 = move-exception;
        goto L_0x0125;
    L_0x034c:
        r2 = move-exception;
        goto L_0x012a;
    L_0x034f:
        r2 = move-exception;
        goto L_0x00eb;
    L_0x0352:
        r2 = move-exception;
        goto L_0x00f0;
    L_0x0355:
        r2 = move-exception;
        goto L_0x0230;
    L_0x0358:
        r2 = move-exception;
        goto L_0x0235;
    L_0x035b:
        r2 = move-exception;
        goto L_0x0295;
    L_0x035e:
        r2 = move-exception;
        goto L_0x029a;
    L_0x0361:
        r3 = move-exception;
        goto L_0x02f4;
    L_0x0363:
        r3 = move-exception;
        goto L_0x02f9;
    L_0x0365:
        r2 = move-exception;
        goto L_0x033e;
    L_0x0367:
        r3 = move-exception;
        r10 = r2;
        r2 = r3;
        goto L_0x033e;
    L_0x036b:
        r2 = move-exception;
        r10 = r3;
        goto L_0x033e;
    L_0x036e:
        r2 = move-exception;
        r2 = r10;
        goto L_0x02db;
    L_0x0372:
        r3 = move-exception;
        r9 = r2;
        r2 = r3;
        goto L_0x02e9;
    L_0x0377:
        r2 = move-exception;
        r2 = r9;
        goto L_0x0282;
    L_0x037b:
        r3 = r10;
        goto L_0x02fa;
    L_0x037e:
        r2 = r10;
        goto L_0x029b;
    L_0x0381:
        r2 = r10;
        goto L_0x0236;
    L_0x0384:
        r2 = r7;
        goto L_0x020c;
    L_0x0387:
        r2 = r10;
        goto L_0x00f1;
    L_0x038a:
        r2 = r10;
        goto L_0x012b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.plugin.f.a(java.lang.String, android.content.Context):void");
    }

    protected void b(String str) {
        this.m.obtainMessage(6109, str).sendToTarget();
        if (this.n != null) {
            this.n.a(6109, str);
        }
    }

    protected void a(Bundle bundle) {
    }

    public boolean k() {
        if (this.h) {
            return false;
        }
        this.h = true;
        try {
            File file = new File(this.i);
            if (file.exists()) {
                file.delete();
            }
            this.m.sendEmptyMessage(6110);
            return true;
        } catch (Exception e) {
            com.qq.reader.common.monitor.f.a("PdfPluginHandler", "uninstall mPluginId = " + this.j.i() + "  " + e.toString());
            this.h = false;
            return false;
        }
    }

    protected void a(String str) {
        this.m.obtainMessage(6113, str).sendToTarget();
    }

    public boolean j() {
        if (this.j.d() == 4) {
            if (this.j.l().equalsIgnoreCase("系统字体")) {
                return true;
            }
            File file = new File(this.i);
            if (!file.exists() || file.length() == 0) {
                if (file.exists()) {
                    file.delete();
                }
                k.b().a(this.j.i(), 0, 0, null, 4);
                this.j.b(1);
                com.qq.reader.common.monitor.f.a("checkErrorDB", "checkErrorDB restore Plugin " + this.j.i());
                return false;
            }
        }
        return true;
    }

    private void a(File file, File file2, File file3, String str, Handler handler, boolean z) {
        if (z) {
            a(file, file2, str);
        } else if (file.renameTo(file3)) {
            handler.sendEmptyMessage(6108);
        } else {
            file.delete();
        }
    }

    private void a(File file, File file2, String str) {
        if (file.renameTo(file2)) {
            if (a(this.i + ".c", this.i, str)) {
                this.m.sendEmptyMessage(6108);
            }
        } else if (!file.renameTo(file2)) {
            file.delete();
            b("下载失败。");
        } else if (a(this.i + ".c", this.i, str)) {
            this.m.sendEmptyMessage(6108);
        }
    }

    private boolean a(String str, String str2, String str3) {
        if (!c.a(str, str2) && !c.a(str, str2)) {
            return false;
        }
        if (this.n != null) {
            this.n.a(6108, null);
        }
        return true;
    }
}
