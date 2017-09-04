package com.tencent.beacon.upload;

import android.content.Context;
import android.util.SparseArray;
import com.tencent.beacon.b.b.e;
import com.tencent.beacon.b.f;
import com.tencent.beacon.b.j;
import com.tencent.beacon.c.a.b;
import com.tencent.beacon.c.a.c;
import com.tencent.beacon.e.a;
import com.tencent.beacon.event.o;
import com.tencent.beacon.f.d;
import com.tencent.midas.api.APMidasPayAPI;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ProGuard */
public final class i implements h {
    private static i a = null;
    private SparseArray<g> b = new SparseArray(5);
    private List<UploadHandleListener> c = new ArrayList(5);
    private f d;
    private Context e = null;
    private boolean f = true;
    private boolean g = true;

    public static synchronized i a(Context context) {
        i iVar;
        synchronized (i.class) {
            if (a == null) {
                a = new i(context, true);
                a.h(" create uphandler up:true", new Object[0]);
            }
            iVar = a;
        }
        return iVar;
    }

    public static synchronized i a(Context context, boolean z) {
        i iVar;
        synchronized (i.class) {
            if (a == null) {
                a = new i(context, z);
                a.h(" create uphandler up: %b", Boolean.valueOf(z));
            }
            if (a.a() != z) {
                a.b(z);
                a.h(" change uphandler up: %b", Boolean.valueOf(z));
            }
            iVar = a;
        }
        return iVar;
    }

    private i(Context context, boolean z) {
        Context context2 = null;
        if (context != null) {
            context2 = context.getApplicationContext();
        }
        if (context2 != null) {
            this.e = context2;
        } else {
            this.e = context;
        }
        this.f = z;
        this.d = f.a(this.e);
    }

    public final synchronized boolean a(int i, g gVar) {
        boolean z;
        if (gVar == null) {
            z = false;
        } else {
            this.b.append(i, gVar);
            z = true;
        }
        return z;
    }

    public final synchronized boolean a(UploadHandleListener uploadHandleListener) {
        boolean z;
        if (uploadHandleListener == null) {
            z = false;
        } else {
            if (!this.c.contains(uploadHandleListener)) {
                this.c.add(uploadHandleListener);
            }
            z = true;
        }
        return z;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(com.tencent.beacon.upload.a r27) {
        /*
        r26 = this;
        r2 = r26.a();
        if (r2 == 0) goto L_0x000c;
    L_0x0006:
        r2 = r26.b();
        if (r2 != 0) goto L_0x0046;
    L_0x000c:
        r2 = r27.c();
        r3 = 2;
        if (r2 != r3) goto L_0x0022;
    L_0x0013:
        r2 = "  Not UpProc real event sync 2 DB done false";
        r3 = 0;
        r3 = new java.lang.Object[r3];
        com.tencent.beacon.e.a.h(r2, r3);
        r2 = 0;
        r0 = r27;
        r0.b(r2);
    L_0x0022:
        r0 = r27;
        r2 = r0.b;
        if (r2 == 0) goto L_0x003d;
    L_0x0028:
        r2 = "  Not UpProc not req: %d";
        r3 = 1;
        r3 = new java.lang.Object[r3];
        r4 = 0;
        r5 = r27.c();
        r5 = java.lang.Integer.valueOf(r5);
        r3[r4] = r5;
        com.tencent.beacon.e.a.h(r2, r3);
    L_0x003c:
        return;
    L_0x003d:
        r2 = "  NotUpProc com req start ";
        r3 = 0;
        r3 = new java.lang.Object[r3];
        com.tencent.beacon.e.a.h(r2, r3);
    L_0x0046:
        r0 = r26;
        r2 = r0.e;
        r2 = com.tencent.beacon.b.f.o(r2);
        if (r2 != 0) goto L_0x0067;
    L_0x0050:
        r2 = " doUpload network is disabled!";
        r3 = 0;
        r3 = new java.lang.Object[r3];
        com.tencent.beacon.e.a.c(r2, r3);
        r2 = r27.c();
        r3 = 2;
        if (r2 != r3) goto L_0x003c;
    L_0x0060:
        r2 = 0;
        r0 = r27;
        r0.b(r2);
        goto L_0x003c;
    L_0x0067:
        r0 = r26;
        r2 = r0.e;
        r2 = com.tencent.beacon.b.h.a(r2);
        r0 = r26;
        r0.a(r2);
        if (r27 != 0) goto L_0x0080;
    L_0x0076:
        r2 = " upData == null ";
        r3 = 0;
        r3 = new java.lang.Object[r3];
        com.tencent.beacon.e.a.d(r2, r3);
        goto L_0x003c;
    L_0x0080:
        r4 = r27.c();
        r10 = 0;
        r5 = -1;
        r3 = r27.e();
        if (r3 == 0) goto L_0x0099;
    L_0x008c:
        r2 = "";
        r6 = r3.trim();
        r2 = r2.equals(r6);
        if (r2 == 0) goto L_0x00bf;
    L_0x0099:
        r2 = " url error";
        r3 = 0;
        r3 = new java.lang.Object[r3];
        com.tencent.beacon.e.a.d(r2, r3);
        r2 = r27.c();
        r3 = 2;
        if (r2 != r3) goto L_0x00af;
    L_0x00a9:
        r2 = 0;
        r0 = r27;
        r0.b(r2);
    L_0x00af:
        r5 = -1;
        r6 = 0;
        r8 = 0;
        r10 = 0;
        r11 = "url error";
        r3 = r26;
        r3.a(r4, r5, r6, r8, r10, r11);
        goto L_0x003c;
    L_0x00bf:
        r6 = b(r27);
        r7 = r27.d();
        r2 = 0;
        if (r7 == 0) goto L_0x00da;
    L_0x00ca:
        r2 = new java.lang.StringBuilder;
        r8 = "?rid=";
        r2.<init>(r8);
        r2 = r2.append(r7);
        r2 = r2.toString();
    L_0x00da:
        r7 = com.tencent.beacon.b.b.e.a();
        if (r7 == 0) goto L_0x0101;
    L_0x00e0:
        r7 = r7.q();
        if (r7 == 0) goto L_0x0101;
    L_0x00e6:
        r8 = "";
        r8 = r8.equals(r7);
        if (r8 != 0) goto L_0x0101;
    L_0x00ef:
        if (r2 != 0) goto L_0x014d;
    L_0x00f1:
        r2 = new java.lang.StringBuilder;
        r8 = "?sid=";
        r2.<init>(r8);
        r2 = r2.append(r7);
        r2 = r2.toString();
    L_0x0101:
        if (r2 == 0) goto L_0x040c;
    L_0x0103:
        r7 = new java.lang.StringBuilder;
        r7.<init>();
        r3 = r7.append(r3);
        r2 = r3.append(r2);
        r2 = r2.toString();
    L_0x0114:
        r3 = " start upload cmd: %d  url:%s  datas:%s";
        r7 = 3;
        r7 = new java.lang.Object[r7];
        r8 = 0;
        r9 = java.lang.Integer.valueOf(r4);
        r7[r8] = r9;
        r8 = 1;
        r7[r8] = r2;
        r8 = 2;
        r9 = r27.getClass();
        r9 = r9.toString();
        r7[r8] = r9;
        com.tencent.beacon.e.a.h(r3, r7);
        if (r6 != 0) goto L_0x0166;
    L_0x0134:
        r2 = " sendData is null";
        r3 = 0;
        r3 = new java.lang.Object[r3];
        com.tencent.beacon.e.a.c(r2, r3);
        r5 = -1;
        r6 = 0;
        r8 = 0;
        r10 = 0;
        r11 = "sendData error";
        r3 = r26;
        r3.a(r4, r5, r6, r8, r10, r11);
        goto L_0x003c;
    L_0x014d:
        r8 = new java.lang.StringBuilder;
        r8.<init>();
        r2 = r8.append(r2);
        r8 = "&sid=";
        r2 = r2.append(r8);
        r2 = r2.append(r7);
        r2 = r2.toString();
        goto L_0x0101;
    L_0x0166:
        r7 = r26.d();
        if (r7 != 0) goto L_0x0185;
    L_0x016c:
        r2 = " reqH error";
        r3 = 0;
        r3 = new java.lang.Object[r3];
        com.tencent.beacon.e.a.d(r2, r3);
        r5 = -1;
        r6 = 0;
        r8 = 0;
        r10 = 0;
        r11 = "reqHandler error";
        r3 = r26;
        r3.a(r4, r5, r6, r8, r10, r11);
        goto L_0x003c;
    L_0x0185:
        r0 = r26;
        r3 = r0.e;
        r20 = com.tencent.beacon.b.f.k(r3);
        r25 = new com.tencent.beacon.upload.e;
        r25.<init>();
        r3 = com.tencent.beacon.b.d.m();
        r8 = r3.h();
        r3 = new java.util.Date;
        r3.<init>();
        r12 = r3.getTime();
        r8 = r8 + r12;
        r12 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r16 = r8 / r12;
        r0 = r25;
        r1 = r27;
        r3 = r7.a(r2, r6, r0, r1);	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        if (r3 != 0) goto L_0x0409;
    L_0x01b2:
        r8 = 100;
        if (r4 != r8) goto L_0x0409;
    L_0x01b6:
        r8 = "http://strategy.beacon.qq.com/analytics/upload";
        r2 = r8.equals(r2);	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        if (r2 != 0) goto L_0x0409;
    L_0x01bf:
        r2 = "http://strategy.beacon.qq.com/analytics/upload";
        r0 = r25;
        r1 = r27;
        r2 = r7.a(r2, r6, r0, r1);	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
    L_0x01ca:
        r6 = r25.a();	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        r8 = r25.b();	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        r2 = a(r2);	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        if (r2 == 0) goto L_0x01fa;
    L_0x01d8:
        r5 = r2.b;	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        r3 = r2.a;	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        if (r3 != 0) goto L_0x0233;
    L_0x01de:
        r10 = 1;
    L_0x01df:
        r3 = "response.cmd:%d response.result:%d";
        r11 = 2;
        r11 = new java.lang.Object[r11];	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        r12 = 0;
        r13 = r2.b;	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        r13 = java.lang.Integer.valueOf(r13);	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        r11[r12] = r13;	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        r12 = 1;
        r13 = r2.a;	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        r13 = java.lang.Byte.valueOf(r13);	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        r11[r12] = r13;	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        com.tencent.beacon.e.a.b(r3, r11);	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
    L_0x01fa:
        if (r27 == 0) goto L_0x01fe;
    L_0x01fc:
        if (r2 != 0) goto L_0x0235;
    L_0x01fe:
        r11 = 0;
        r3 = r26;
        r3.a(r4, r5, r6, r8, r10, r11);	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        r8 = r25.d();	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        r0 = (int) r8;	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        r18 = r0;
        r19 = r27.f();	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        r21 = r25.e();	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        r22 = r25.c();	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        r3 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        r3.<init>();	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        r2 = r2.a;	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        r2 = r3.append(r2);	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        r23 = r2.toString();	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        r12 = r4;
        r13 = r6;
        r15 = r10;
        a(r12, r13, r15, r16, r18, r19, r20, r21, r22, r23);	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        r0 = r27;
        r0.b(r10);
        goto L_0x003c;
    L_0x0233:
        r10 = 0;
        goto L_0x01df;
    L_0x0235:
        r3 = com.tencent.beacon.b.d.m();	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        if (r3 == 0) goto L_0x0272;
    L_0x023b:
        r11 = r2.d;	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        if (r11 == 0) goto L_0x0248;
    L_0x023f:
        r11 = r2.d;	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        r11 = r11.trim();	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        r3.b(r11);	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
    L_0x0248:
        r11 = new java.util.Date;	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        r11.<init>();	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        r12 = r2.e;	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        r14 = r11.getTime();	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        r12 = r12 - r14;
        r3.a(r12);	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        r11 = " fix ip:%s  tmgap: %d";
        r12 = 2;
        r12 = new java.lang.Object[r12];	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        r13 = 0;
        r14 = r3.g();	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        r12[r13] = r14;	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        r13 = 1;
        r14 = r3.h();	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        r3 = java.lang.Long.valueOf(r14);	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        r12[r13] = r3;	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        com.tencent.beacon.e.a.h(r11, r12);	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
    L_0x0272:
        r3 = r2.b;	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        r11 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        if (r3 == r11) goto L_0x0284;
    L_0x0278:
        r3 = r2.b;	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        r11 = 103; // 0x67 float:1.44E-43 double:5.1E-322;
        if (r3 == r11) goto L_0x0284;
    L_0x027e:
        r3 = r2.b;	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        r11 = 105; // 0x69 float:1.47E-43 double:5.2E-322;
        if (r3 != r11) goto L_0x02ad;
    L_0x0284:
        r0 = r26;
        r3 = r0.e;	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        r3 = com.tencent.beacon.b.b.c.a(r3);	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        r3 = r3.e();	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        if (r3 == 0) goto L_0x02ad;
    L_0x0292:
        r11 = r3.n();	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        if (r11 == 0) goto L_0x02a8;
    L_0x0298:
        r11 = r2.f;	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        if (r11 == 0) goto L_0x02ad;
    L_0x029c:
        r11 = r3.n();	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        r12 = r2.f;	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        r11 = r11.equals(r12);	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        if (r11 != 0) goto L_0x02ad;
    L_0x02a8:
        r11 = r2.f;	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        r3.b(r11);	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
    L_0x02ad:
        r3 = r2.c;	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        if (r3 != 0) goto L_0x030f;
    L_0x02b1:
        r3 = " no response! ";
        r11 = 0;
        r11 = new java.lang.Object[r11];	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        com.tencent.beacon.e.a.h(r3, r11);	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        goto L_0x01fe;
    L_0x02bc:
        r2 = move-exception;
        r24 = r10;
        r6 = r25.a();	 Catch:{ all -> 0x0401 }
        r8 = r25.b();	 Catch:{ all -> 0x0401 }
        r3 = r2.toString();	 Catch:{ all -> 0x0401 }
        r10 = "java.lang.Exception: ";
        r11 = "";
        r11 = r3.replace(r10, r11);	 Catch:{ all -> 0x0401 }
        r10 = 0;
        r3 = r26;
        r3.a(r4, r5, r6, r8, r10, r11);	 Catch:{ all -> 0x0401 }
        r15 = 0;
        r8 = r25.d();	 Catch:{ all -> 0x0401 }
        r0 = (int) r8;	 Catch:{ all -> 0x0401 }
        r18 = r0;
        r19 = r27.f();	 Catch:{ all -> 0x0401 }
        r21 = r25.e();	 Catch:{ all -> 0x0401 }
        r22 = r25.c();	 Catch:{ all -> 0x0401 }
        r12 = r4;
        r13 = r6;
        r23 = r11;
        a(r12, r13, r15, r16, r18, r19, r20, r21, r22, r23);	 Catch:{ all -> 0x0401 }
        r3 = " req error  %s";
        r4 = 1;
        r4 = new java.lang.Object[r4];	 Catch:{ all -> 0x0401 }
        r5 = 0;
        r2 = r2.toString();	 Catch:{ all -> 0x0401 }
        r4[r5] = r2;	 Catch:{ all -> 0x0401 }
        com.tencent.beacon.e.a.d(r3, r4);	 Catch:{ all -> 0x0401 }
        r0 = r27;
        r1 = r24;
        r0.b(r1);
        goto L_0x003c;
    L_0x030f:
        r11 = r26.e();	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        if (r11 == 0) goto L_0x031b;
    L_0x0315:
        r12 = r11.size();	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        if (r12 > 0) goto L_0x032d;
    L_0x031b:
        r3 = " no handler! ";
        r11 = 0;
        r11 = new java.lang.Object[r11];	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        com.tencent.beacon.e.a.h(r3, r11);	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        goto L_0x01fe;
    L_0x0326:
        r2 = move-exception;
    L_0x0327:
        r0 = r27;
        r0.b(r10);
        throw r2;
    L_0x032d:
        r12 = r27.c();	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        r13 = r2.b;	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        r14 = r2.a;	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        if (r14 != 0) goto L_0x0381;
    L_0x0337:
        if (r12 <= 0) goto L_0x0381;
    L_0x0339:
        r14 = 5;
        if (r12 > r14) goto L_0x0381;
    L_0x033c:
        r14 = new com.tencent.beacon.e.b;	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        r0 = r26;
        r15 = r0.e;	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        r14.<init>(r15);	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        r15 = "";
        r0 = r26;
        r0 = r0.e;	 Catch:{ Exception -> 0x0406 }
        r18 = r0;
        r18 = com.tencent.beacon.b.j.a(r18);	 Catch:{ Exception -> 0x0406 }
        r18 = r18.b();	 Catch:{ Exception -> 0x0406 }
        r0 = r18;
        r15 = r15.equals(r0);	 Catch:{ Exception -> 0x0406 }
        if (r15 == 0) goto L_0x0381;
    L_0x035e:
        r15 = com.tencent.beacon.b.b.e.a();	 Catch:{ Exception -> 0x0406 }
        r15 = r15.k();	 Catch:{ Exception -> 0x0406 }
        if (r15 != 0) goto L_0x0381;
    L_0x0368:
        r15 = new com.tencent.beacon.upload.d;	 Catch:{ Exception -> 0x0406 }
        r0 = r26;
        r0 = r0.e;	 Catch:{ Exception -> 0x0406 }
        r18 = r0;
        r0 = r18;
        r15.<init>(r0);	 Catch:{ Exception -> 0x0406 }
        r18 = com.tencent.beacon.b.b.c.c();	 Catch:{ Exception -> 0x0406 }
        r0 = r18;
        r0.a(r15);	 Catch:{ Exception -> 0x0406 }
        r14.c();	 Catch:{ Exception -> 0x0406 }
    L_0x0381:
        if (r13 != 0) goto L_0x038e;
    L_0x0383:
        r3 = " response no datas ";
        r11 = 0;
        r11 = new java.lang.Object[r11];	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        com.tencent.beacon.e.a.h(r3, r11);	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        goto L_0x01fe;
    L_0x038e:
        switch(r12) {
            case 4: goto L_0x03c0;
            case 100: goto L_0x03a3;
            case 102: goto L_0x03dd;
            default: goto L_0x0391;
        };	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
    L_0x0391:
        r3 = " unknown req: %d ";
        r11 = 1;
        r11 = new java.lang.Object[r11];	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        r13 = 0;
        r12 = java.lang.Integer.valueOf(r12);	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        r11[r13] = r12;	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        com.tencent.beacon.e.a.c(r3, r11);	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        goto L_0x01fe;
    L_0x03a3:
        r14 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        if (r13 == r14) goto L_0x03fa;
    L_0x03a7:
        r3 = " UNMATCH req: %d , rep: %d";
        r11 = 2;
        r11 = new java.lang.Object[r11];	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        r14 = 0;
        r12 = java.lang.Integer.valueOf(r12);	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        r11[r14] = r12;	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        r12 = 1;
        r13 = java.lang.Integer.valueOf(r13);	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        r11[r12] = r13;	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        com.tencent.beacon.e.a.c(r3, r11);	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        goto L_0x01fe;
    L_0x03c0:
        r14 = 105; // 0x69 float:1.47E-43 double:5.2E-322;
        if (r13 == r14) goto L_0x03fa;
    L_0x03c4:
        r3 = " UNMATCH req: %d , rep: %d";
        r11 = 2;
        r11 = new java.lang.Object[r11];	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        r14 = 0;
        r12 = java.lang.Integer.valueOf(r12);	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        r11[r14] = r12;	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        r12 = 1;
        r13 = java.lang.Integer.valueOf(r13);	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        r11[r12] = r13;	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        com.tencent.beacon.e.a.c(r3, r11);	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        goto L_0x01fe;
    L_0x03dd:
        r14 = 103; // 0x67 float:1.44E-43 double:5.1E-322;
        if (r13 == r14) goto L_0x03fa;
    L_0x03e1:
        r3 = " UNMATCH req: %d  , rep: %d";
        r11 = 2;
        r11 = new java.lang.Object[r11];	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        r14 = 0;
        r12 = java.lang.Integer.valueOf(r12);	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        r11[r14] = r12;	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        r12 = 1;
        r13 = java.lang.Integer.valueOf(r13);	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        r11[r12] = r13;	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        com.tencent.beacon.e.a.c(r3, r11);	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        goto L_0x01fe;
    L_0x03fa:
        r0 = r26;
        r0.a(r11, r13, r3);	 Catch:{ Throwable -> 0x02bc, all -> 0x0326 }
        goto L_0x01fe;
    L_0x0401:
        r2 = move-exception;
        r10 = r24;
        goto L_0x0327;
    L_0x0406:
        r14 = move-exception;
        goto L_0x0381;
    L_0x0409:
        r2 = r3;
        goto L_0x01ca;
    L_0x040c:
        r2 = r3;
        goto L_0x0114;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.beacon.upload.i.a(com.tencent.beacon.upload.a):void");
    }

    private static byte[] b(a aVar) {
        if (aVar != null) {
            try {
                b a = aVar.a();
                if (a != null) {
                    a.b(" RequestPackage info appkey:%s sdkid:%s appVersion:%s cmd: %d", a.b, a.d, a.c, Integer.valueOf(a.f));
                    d dVar = new d();
                    dVar.a();
                    dVar.b(APMidasPayAPI.ENV_TEST);
                    dVar.a(APMidasPayAPI.ENV_TEST);
                    dVar.a("detail", a);
                    byte[] b = dVar.b();
                    e a2 = e.a();
                    if (a2 != null) {
                        return com.tencent.beacon.net.a.a(b, a2.m(), a2.l(), a2.p());
                    }
                }
            } catch (Throwable th) {
                a.d(" parseSendDatas error", new Object[0]);
                a.a(th);
                aVar.b();
            }
        }
        return null;
    }

    private static c a(byte[] bArr) {
        if (bArr != null) {
            try {
                byte[] b;
                e a = e.a();
                if (a != null) {
                    b = com.tencent.beacon.net.a.b(bArr, a.m(), a.l(), a.p());
                } else {
                    b = null;
                }
                if (b != null) {
                    d dVar = new d();
                    dVar.a(b);
                    c cVar = new c();
                    a.b(" covert to ResponsePackage ", new Object[0]);
                    return (c) dVar.b("detail", cVar);
                }
            } catch (Throwable th) {
                a.a(th);
            }
        }
        return null;
    }

    private void a(int i, int i2, long j, long j2, boolean z, String str) {
        UploadHandleListener[] c = c();
        if (c != null) {
            for (UploadHandleListener onUploadEnd : c) {
                onUploadEnd.onUploadEnd(i, i2, j, j2, z, str);
            }
        }
    }

    private static void a(int i, long j, boolean z, long j2, int i2, int i3, String str, String str2, int i4, String str3) {
        if (i != 0 && j != 0) {
            o d = o.d();
            if (d != null && d.d != null) {
                d.d.a(i, z, j2, j, i2, i3, str, str2, i4, str3);
            }
        }
    }

    private synchronized UploadHandleListener[] c() {
        UploadHandleListener[] uploadHandleListenerArr;
        if (this.c == null || this.c.size() <= 0) {
            uploadHandleListenerArr = null;
        } else {
            uploadHandleListenerArr = (UploadHandleListener[]) this.c.toArray(new UploadHandleListener[0]);
        }
        return uploadHandleListenerArr;
    }

    private synchronized f d() {
        return this.d;
    }

    private synchronized SparseArray<g> e() {
        SparseArray<g> sparseArray;
        if (this.b == null || this.b.size() <= 0) {
            sparseArray = null;
        } else {
            com.tencent.beacon.e.d dVar = new com.tencent.beacon.e.d();
            sparseArray = com.tencent.beacon.e.d.a(this.b);
        }
        return sparseArray;
    }

    private boolean a(SparseArray<g> sparseArray, int i, byte[] bArr) {
        if (sparseArray == null || bArr == null) {
            return true;
        }
        switch (i) {
            case 103:
                try {
                    a.a(" process CMD_RESPONSE_GEN_QIMEI", new Object[0]);
                    com.tencent.beacon.f.a aVar = new com.tencent.beacon.f.a(bArr);
                    com.tencent.beacon.c.c.a aVar2 = new com.tencent.beacon.c.c.a();
                    aVar2.a(aVar);
                    if (aVar2.a != null) {
                        j.a(this.e).a(aVar2.a);
                        new com.tencent.beacon.e.b(this.e).a(aVar2.a);
                    }
                    a.h(" Qimei:%s  imei:%s  imsi:%s  aid:%s  mac:%s ", aVar2.a, aVar2.b, aVar2.d, aVar2.e, aVar2.c);
                } catch (Throwable th) {
                    a.a(th);
                }
                return true;
            default:
                g gVar = (g) sparseArray.get(i);
                if (gVar == null) {
                    a.c(" no handler key:%d", Integer.valueOf(i));
                    return false;
                }
                try {
                    a.b(" key:%d  handler: %s", Integer.valueOf(i), gVar.getClass().toString());
                    gVar.a(i, bArr, true);
                    return true;
                } catch (Throwable th2) {
                    a.a(th2);
                    a.d(" handle error key:%d", Integer.valueOf(i));
                    return false;
                }
        }
    }

    public final synchronized boolean a() {
        return this.f;
    }

    private synchronized void b(boolean z) {
        this.f = z;
    }

    public final synchronized boolean b() {
        boolean z;
        if (f.n(this.e)) {
            z = true;
        } else {
            z = this.g;
        }
        return z;
    }

    public final synchronized void a(boolean z) {
        this.g = z;
    }
}
