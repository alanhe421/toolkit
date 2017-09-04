package com.tencent.beacon.b.b;

import android.content.Context;
import com.tencent.beacon.upload.g;

/* compiled from: ProGuard */
public final class a implements g {
    private Context a = null;

    public a(Context context) {
        this.a = context;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(int r17, byte[] r18, boolean r19) {
        /*
        r16 = this;
        r2 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        r0 = r17;
        if (r0 == r2) goto L_0x0017;
    L_0x0006:
        r2 = "com strategy unmatch key: %d";
        r3 = 1;
        r3 = new java.lang.Object[r3];
        r4 = 0;
        r5 = java.lang.Integer.valueOf(r17);
        r3[r4] = r5;
        com.tencent.beacon.e.a.c(r2, r3);
    L_0x0016:
        return;
    L_0x0017:
        if (r18 == 0) goto L_0x0016;
    L_0x0019:
        r0 = r18;
        r2 = r0.length;
        if (r2 <= 0) goto L_0x0016;
    L_0x001e:
        r0 = r16;
        r2 = r0.a;
        r2 = com.tencent.beacon.b.b.c.a(r2);
        r8 = r2.e();
        if (r8 != 0) goto L_0x0036;
    L_0x002c:
        r2 = "imposible! common strategy null!";
        r3 = 0;
        r3 = new java.lang.Object[r3];
        com.tencent.beacon.e.a.c(r2, r3);
        goto L_0x0016;
    L_0x0036:
        r9 = new com.tencent.beacon.c.e.a;	 Catch:{ Throwable -> 0x00be }
        r9.<init>();	 Catch:{ Throwable -> 0x00be }
        r2 = new com.tencent.beacon.f.a;	 Catch:{ Throwable -> 0x00be }
        r0 = r18;
        r2.<init>(r0);	 Catch:{ Throwable -> 0x00be }
        r9.a(r2);	 Catch:{ Throwable -> 0x00be }
        if (r8 != 0) goto L_0x00cd;
    L_0x0047:
        r2 = 0;
    L_0x0048:
        if (r2 == 0) goto L_0x0016;
    L_0x004a:
        if (r19 == 0) goto L_0x006d;
    L_0x004c:
        r2 = "update common strategy should save ";
        r3 = 0;
        r3 = new java.lang.Object[r3];	 Catch:{ Throwable -> 0x00be }
        com.tencent.beacon.e.a.e(r2, r3);	 Catch:{ Throwable -> 0x00be }
        r0 = r16;
        r2 = r0.a;	 Catch:{ Throwable -> 0x00be }
        if (r18 == 0) goto L_0x006d;
    L_0x005b:
        r3 = new com.tencent.beacon.b.b.h;	 Catch:{ Throwable -> 0x00be }
        r3.<init>();	 Catch:{ Throwable -> 0x00be }
        r0 = r17;
        r3.a(r0);	 Catch:{ Throwable -> 0x00be }
        r0 = r18;
        r3.a(r0);	 Catch:{ Throwable -> 0x00be }
        com.tencent.beacon.net.a.a(r2, r3);	 Catch:{ Throwable -> 0x00be }
    L_0x006d:
        r2 = "com strategy changed notify! ";
        r3 = 0;
        r3 = new java.lang.Object[r3];	 Catch:{ Throwable -> 0x00be }
        com.tencent.beacon.e.a.e(r2, r3);	 Catch:{ Throwable -> 0x00be }
        r0 = r16;
        r2 = r0.a;	 Catch:{ Throwable -> 0x00be }
        r2 = com.tencent.beacon.b.b.c.a(r2);	 Catch:{ Throwable -> 0x00be }
        r2.a(r8);	 Catch:{ Throwable -> 0x00be }
        if (r19 == 0) goto L_0x0016;
    L_0x0083:
        r2.b();	 Catch:{ Throwable -> 0x00be }
        r2 = 0;
        r3 = com.tencent.beacon.event.o.d();	 Catch:{ Throwable -> 0x00be }
        if (r3 == 0) goto L_0x008f;
    L_0x008d:
        r2 = r3.a;	 Catch:{ Throwable -> 0x00be }
    L_0x008f:
        if (r2 == 0) goto L_0x0016;
    L_0x0091:
        r2 = r2.D();	 Catch:{ Throwable -> 0x00be }
        if (r2 == 0) goto L_0x0016;
    L_0x0097:
        r0 = r16;
        r2 = r0.a;	 Catch:{ Throwable -> 0x00be }
        r3 = "today_success_strategy_query_times";
        r2 = com.tencent.beacon.b.b.a(r2, r3);	 Catch:{ Throwable -> 0x00be }
        r2 = r2 + 1;
        r0 = r16;
        r3 = r0.a;	 Catch:{ Throwable -> 0x00be }
        r4 = "today_success_strategy_query_times";
        com.tencent.beacon.b.b.a(r3, r4, r2);	 Catch:{ Throwable -> 0x00be }
        r0 = r16;
        r2 = r0.a;	 Catch:{ Throwable -> 0x00be }
        r3 = "last_success_strategy_query_time";
        r4 = java.lang.System.currentTimeMillis();	 Catch:{ Throwable -> 0x00be }
        com.tencent.beacon.b.b.a(r2, r3, r4);	 Catch:{ Throwable -> 0x00be }
        goto L_0x0016;
    L_0x00be:
        r2 = move-exception;
        com.tencent.beacon.e.a.a(r2);
        r2 = "error to common strategy!";
        r3 = 0;
        r3 = new java.lang.Object[r3];
        com.tencent.beacon.e.a.d(r2, r3);
        goto L_0x0016;
    L_0x00cd:
        r5 = 0;
        r2 = r9.c;	 Catch:{ Throwable -> 0x00be }
        r3 = r8.b();	 Catch:{ Throwable -> 0x00be }
        r2 = r2.equals(r3);	 Catch:{ Throwable -> 0x00be }
        if (r2 != 0) goto L_0x00ee;
    L_0x00da:
        r2 = "strategy url changed to: %s";
        r3 = 1;
        r3 = new java.lang.Object[r3];	 Catch:{ Throwable -> 0x00be }
        r4 = 0;
        r5 = r9.c;	 Catch:{ Throwable -> 0x00be }
        r3[r4] = r5;	 Catch:{ Throwable -> 0x00be }
        com.tencent.beacon.e.a.b(r2, r3);	 Catch:{ Throwable -> 0x00be }
        r5 = 1;
        r2 = r9.c;	 Catch:{ Throwable -> 0x00be }
        r8.a(r2);	 Catch:{ Throwable -> 0x00be }
    L_0x00ee:
        r2 = r9.b;	 Catch:{ Throwable -> 0x00be }
        r3 = r8.c();	 Catch:{ Throwable -> 0x00be }
        if (r2 == r3) goto L_0x010e;
    L_0x00f6:
        r2 = "QueryPeriod changed to: %d";
        r3 = 1;
        r3 = new java.lang.Object[r3];	 Catch:{ Throwable -> 0x00be }
        r4 = 0;
        r5 = r9.b;	 Catch:{ Throwable -> 0x00be }
        r5 = java.lang.Integer.valueOf(r5);	 Catch:{ Throwable -> 0x00be }
        r3[r4] = r5;	 Catch:{ Throwable -> 0x00be }
        com.tencent.beacon.e.a.b(r2, r3);	 Catch:{ Throwable -> 0x00be }
        r5 = 1;
        r2 = r9.b;	 Catch:{ Throwable -> 0x00be }
        r8.a(r2);	 Catch:{ Throwable -> 0x00be }
    L_0x010e:
        r10 = r9.a;	 Catch:{ Throwable -> 0x00be }
        if (r8 != 0) goto L_0x0120;
    L_0x0112:
        r2 = 0;
    L_0x0113:
        if (r2 == 0) goto L_0x033d;
    L_0x0115:
        r3 = 1;
    L_0x0116:
        r4 = r9.d;	 Catch:{ Throwable -> 0x00be }
        if (r8 != 0) goto L_0x0313;
    L_0x011a:
        r2 = 0;
    L_0x011b:
        if (r2 == 0) goto L_0x033a;
    L_0x011d:
        r2 = 1;
        goto L_0x0048;
    L_0x0120:
        r2 = 0;
        if (r10 == 0) goto L_0x02d9;
    L_0x0123:
        r11 = r8.e();	 Catch:{ Throwable -> 0x00be }
        if (r11 == 0) goto L_0x0113;
    L_0x0129:
        r3 = 0;
        r6 = r3;
        r4 = r2;
    L_0x012c:
        r2 = r11.size();	 Catch:{ Throwable -> 0x00be }
        if (r6 >= r2) goto L_0x0343;
    L_0x0132:
        r2 = r11.valueAt(r6);	 Catch:{ Throwable -> 0x00be }
        r0 = r2;
        r0 = (com.tencent.beacon.b.b.e.a) r0;	 Catch:{ Throwable -> 0x00be }
        r3 = r0;
        r12 = r10.iterator();	 Catch:{ Throwable -> 0x00be }
    L_0x013e:
        r2 = r12.hasNext();	 Catch:{ Throwable -> 0x00be }
        if (r2 == 0) goto L_0x02d4;
    L_0x0144:
        r2 = r12.next();	 Catch:{ Throwable -> 0x00be }
        r2 = (com.tencent.beacon.c.e.d) r2;	 Catch:{ Throwable -> 0x00be }
        r7 = r2.a;	 Catch:{ Throwable -> 0x00be }
        r13 = r3.f();	 Catch:{ Throwable -> 0x00be }
        if (r7 != r13) goto L_0x0282;
    L_0x0152:
        r7 = "server module strategy mid: %d";
        r13 = 1;
        r13 = new java.lang.Object[r13];	 Catch:{ Throwable -> 0x00be }
        r14 = 0;
        r15 = r2.a;	 Catch:{ Throwable -> 0x00be }
        r15 = java.lang.Byte.valueOf(r15);	 Catch:{ Throwable -> 0x00be }
        r13[r14] = r15;	 Catch:{ Throwable -> 0x00be }
        com.tencent.beacon.e.a.a(r7, r13);	 Catch:{ Throwable -> 0x00be }
        r7 = r2.b;	 Catch:{ Throwable -> 0x00be }
        r13 = 1;
        if (r7 != r13) goto L_0x0286;
    L_0x0169:
        r7 = 1;
    L_0x016a:
        r13 = r3.a();	 Catch:{ Throwable -> 0x00be }
        if (r13 == r7) goto L_0x018d;
    L_0x0170:
        r4 = "mid: %d , isUsable changed: %b ";
        r13 = 2;
        r13 = new java.lang.Object[r13];	 Catch:{ Throwable -> 0x00be }
        r14 = 0;
        r15 = r2.a;	 Catch:{ Throwable -> 0x00be }
        r15 = java.lang.Byte.valueOf(r15);	 Catch:{ Throwable -> 0x00be }
        r13[r14] = r15;	 Catch:{ Throwable -> 0x00be }
        r14 = 1;
        r15 = java.lang.Boolean.valueOf(r7);	 Catch:{ Throwable -> 0x00be }
        r13[r14] = r15;	 Catch:{ Throwable -> 0x00be }
        com.tencent.beacon.e.a.b(r4, r13);	 Catch:{ Throwable -> 0x00be }
        r4 = 1;
        r3.a(r7);	 Catch:{ Throwable -> 0x00be }
    L_0x018d:
        r7 = r3.b();	 Catch:{ Throwable -> 0x00be }
        r13 = r2.c;	 Catch:{ Throwable -> 0x00be }
        r7 = r7.equals(r13);	 Catch:{ Throwable -> 0x00be }
        if (r7 != 0) goto L_0x01b6;
    L_0x0199:
        r4 = "mid: %d , url changed: %s";
        r7 = 2;
        r7 = new java.lang.Object[r7];	 Catch:{ Throwable -> 0x00be }
        r13 = 0;
        r14 = r2.a;	 Catch:{ Throwable -> 0x00be }
        r14 = java.lang.Byte.valueOf(r14);	 Catch:{ Throwable -> 0x00be }
        r7[r13] = r14;	 Catch:{ Throwable -> 0x00be }
        r13 = 1;
        r14 = r2.c;	 Catch:{ Throwable -> 0x00be }
        r7[r13] = r14;	 Catch:{ Throwable -> 0x00be }
        com.tencent.beacon.e.a.b(r4, r7);	 Catch:{ Throwable -> 0x00be }
        r4 = 1;
        r7 = r2.c;	 Catch:{ Throwable -> 0x00be }
        r3.a(r7);	 Catch:{ Throwable -> 0x00be }
    L_0x01b6:
        r7 = r2.a;	 Catch:{ Throwable -> 0x00be }
        r13 = 1;
        if (r7 == r13) goto L_0x01c0;
    L_0x01bb:
        r7 = r2.a;	 Catch:{ Throwable -> 0x00be }
        r13 = 2;
        if (r7 != r13) goto L_0x01fd;
    L_0x01c0:
        r7 = r3.c();	 Catch:{ Throwable -> 0x00be }
        if (r7 == 0) goto L_0x0289;
    L_0x01c6:
        r7 = r2.d;	 Catch:{ Throwable -> 0x00be }
        if (r7 == 0) goto L_0x0289;
    L_0x01ca:
        r7 = r3.c();	 Catch:{ Throwable -> 0x00be }
        r13 = r2.d;	 Catch:{ Throwable -> 0x00be }
        r7 = r7.equals(r13);	 Catch:{ Throwable -> 0x00be }
        if (r7 != 0) goto L_0x01fd;
    L_0x01d6:
        r4 = "mid: %d , detail changed...";
        r7 = 1;
        r7 = new java.lang.Object[r7];	 Catch:{ Throwable -> 0x00be }
        r13 = 0;
        r14 = r2.a;	 Catch:{ Throwable -> 0x00be }
        r14 = java.lang.Byte.valueOf(r14);	 Catch:{ Throwable -> 0x00be }
        r7[r13] = r14;	 Catch:{ Throwable -> 0x00be }
        com.tencent.beacon.e.a.b(r4, r7);	 Catch:{ Throwable -> 0x00be }
        r4 = 1;
        r7 = r2.d;	 Catch:{ Throwable -> 0x00be }
        r3.a(r7);	 Catch:{ Throwable -> 0x00be }
        r0 = r16;
        r7 = r0.a;	 Catch:{ Throwable -> 0x00be }
        r7 = com.tencent.beacon.b.b.c.a(r7);	 Catch:{ Throwable -> 0x00be }
        r13 = r2.a;	 Catch:{ Throwable -> 0x00be }
        r14 = r2.d;	 Catch:{ Throwable -> 0x00be }
        r7.a(r13, r14);	 Catch:{ Throwable -> 0x00be }
    L_0x01fd:
        r7 = r2.a;	 Catch:{ Throwable -> 0x00be }
        r13 = 1;
        if (r7 != r13) goto L_0x0266;
    L_0x0202:
        r7 = r3.d();	 Catch:{ Throwable -> 0x00be }
        if (r7 == 0) goto L_0x02bc;
    L_0x0208:
        r7 = r2.e;	 Catch:{ Throwable -> 0x00be }
        if (r7 == 0) goto L_0x02bc;
    L_0x020c:
        r7 = r3.d();	 Catch:{ Throwable -> 0x00be }
        r13 = r2.e;	 Catch:{ Throwable -> 0x00be }
        r7 = r7.equals(r13);	 Catch:{ Throwable -> 0x00be }
        if (r7 != 0) goto L_0x0234;
    L_0x0218:
        r4 = "mid: %d , PreventEventCode changed...";
        r7 = 1;
        r7 = new java.lang.Object[r7];	 Catch:{ Throwable -> 0x00be }
        r13 = 0;
        r14 = r2.a;	 Catch:{ Throwable -> 0x00be }
        r14 = java.lang.Byte.valueOf(r14);	 Catch:{ Throwable -> 0x00be }
        r7[r13] = r14;	 Catch:{ Throwable -> 0x00be }
        com.tencent.beacon.e.a.b(r4, r7);	 Catch:{ Throwable -> 0x00be }
        r4 = 1;
        r7 = r2.e;	 Catch:{ Throwable -> 0x00be }
        r7 = com.tencent.beacon.net.a.a(r7);	 Catch:{ Throwable -> 0x00be }
        r3.a(r7);	 Catch:{ Throwable -> 0x00be }
    L_0x0234:
        r7 = r3.g();	 Catch:{ Throwable -> 0x00be }
        if (r7 == 0) goto L_0x02c8;
    L_0x023a:
        r7 = r2.g;	 Catch:{ Throwable -> 0x00be }
        if (r7 == 0) goto L_0x02c8;
    L_0x023e:
        r7 = r3.g();	 Catch:{ Throwable -> 0x00be }
        r13 = r2.g;	 Catch:{ Throwable -> 0x00be }
        r7 = r7.equals(r13);	 Catch:{ Throwable -> 0x00be }
        if (r7 != 0) goto L_0x0266;
    L_0x024a:
        r4 = "mid: %d , SampleEventSet changed...";
        r7 = 1;
        r7 = new java.lang.Object[r7];	 Catch:{ Throwable -> 0x00be }
        r13 = 0;
        r14 = r2.a;	 Catch:{ Throwable -> 0x00be }
        r14 = java.lang.Byte.valueOf(r14);	 Catch:{ Throwable -> 0x00be }
        r7[r13] = r14;	 Catch:{ Throwable -> 0x00be }
        com.tencent.beacon.e.a.b(r4, r7);	 Catch:{ Throwable -> 0x00be }
        r4 = 1;
        r7 = r2.g;	 Catch:{ Throwable -> 0x00be }
        r7 = com.tencent.beacon.net.a.a(r7);	 Catch:{ Throwable -> 0x00be }
        r3.b(r7);	 Catch:{ Throwable -> 0x00be }
    L_0x0266:
        r7 = r2.a;	 Catch:{ Throwable -> 0x00be }
        r13 = 2;
        if (r7 != r13) goto L_0x0282;
    L_0x026b:
        r7 = "mid: %d , SpeedMonitorStrategy";
        r13 = 1;
        r13 = new java.lang.Object[r13];	 Catch:{ Throwable -> 0x00be }
        r14 = 0;
        r15 = r2.a;	 Catch:{ Throwable -> 0x00be }
        r15 = java.lang.Byte.valueOf(r15);	 Catch:{ Throwable -> 0x00be }
        r13[r14] = r15;	 Catch:{ Throwable -> 0x00be }
        com.tencent.beacon.e.a.b(r7, r13);	 Catch:{ Throwable -> 0x00be }
        r2 = r2.f;	 Catch:{ Throwable -> 0x00be }
        r3.a(r2);	 Catch:{ Throwable -> 0x00be }
    L_0x0282:
        r2 = r4;
        r4 = r2;
        goto L_0x013e;
    L_0x0286:
        r7 = 0;
        goto L_0x016a;
    L_0x0289:
        r7 = r2.d;	 Catch:{ Throwable -> 0x00be }
        if (r7 == 0) goto L_0x01fd;
    L_0x028d:
        r7 = r3.c();	 Catch:{ Throwable -> 0x00be }
        if (r7 != 0) goto L_0x01fd;
    L_0x0293:
        r4 = "mid: %d , detail changed...";
        r7 = 1;
        r7 = new java.lang.Object[r7];	 Catch:{ Throwable -> 0x00be }
        r13 = 0;
        r14 = r2.a;	 Catch:{ Throwable -> 0x00be }
        r14 = java.lang.Byte.valueOf(r14);	 Catch:{ Throwable -> 0x00be }
        r7[r13] = r14;	 Catch:{ Throwable -> 0x00be }
        com.tencent.beacon.e.a.b(r4, r7);	 Catch:{ Throwable -> 0x00be }
        r4 = 1;
        r7 = r2.d;	 Catch:{ Throwable -> 0x00be }
        r3.a(r7);	 Catch:{ Throwable -> 0x00be }
        r0 = r16;
        r7 = r0.a;	 Catch:{ Throwable -> 0x00be }
        r7 = com.tencent.beacon.b.b.c.a(r7);	 Catch:{ Throwable -> 0x00be }
        r13 = r2.a;	 Catch:{ Throwable -> 0x00be }
        r14 = r2.d;	 Catch:{ Throwable -> 0x00be }
        r7.a(r13, r14);	 Catch:{ Throwable -> 0x00be }
        goto L_0x01fd;
    L_0x02bc:
        r7 = r3.d();	 Catch:{ Throwable -> 0x00be }
        if (r7 != 0) goto L_0x0218;
    L_0x02c2:
        r7 = r2.e;	 Catch:{ Throwable -> 0x00be }
        if (r7 == 0) goto L_0x0234;
    L_0x02c6:
        goto L_0x0218;
    L_0x02c8:
        r7 = r3.g();	 Catch:{ Throwable -> 0x00be }
        if (r7 != 0) goto L_0x024a;
    L_0x02ce:
        r7 = r2.g;	 Catch:{ Throwable -> 0x00be }
        if (r7 == 0) goto L_0x0266;
    L_0x02d2:
        goto L_0x024a;
    L_0x02d4:
        r2 = r6 + 1;
        r6 = r2;
        goto L_0x012c;
    L_0x02d9:
        r6 = r8.e();	 Catch:{ Throwable -> 0x00be }
        if (r6 == 0) goto L_0x0113;
    L_0x02df:
        r7 = r6.size();	 Catch:{ Throwable -> 0x00be }
        r3 = 0;
        r4 = r3;
        r3 = r2;
    L_0x02e6:
        if (r4 >= r7) goto L_0x0340;
    L_0x02e8:
        r2 = r6.valueAt(r4);	 Catch:{ Throwable -> 0x00be }
        r2 = (com.tencent.beacon.b.b.e.a) r2;	 Catch:{ Throwable -> 0x00be }
        r10 = r2.a();	 Catch:{ Throwable -> 0x00be }
        if (r10 == 0) goto L_0x030d;
    L_0x02f4:
        r3 = "mid: %d , server not response strategy, sdk local close it!";
        r10 = 1;
        r10 = new java.lang.Object[r10];	 Catch:{ Throwable -> 0x00be }
        r11 = 0;
        r12 = r2.f();	 Catch:{ Throwable -> 0x00be }
        r12 = java.lang.Integer.valueOf(r12);	 Catch:{ Throwable -> 0x00be }
        r10[r11] = r12;	 Catch:{ Throwable -> 0x00be }
        com.tencent.beacon.e.a.b(r3, r10);	 Catch:{ Throwable -> 0x00be }
        r3 = 1;
        r10 = 0;
        r2.a(r10);	 Catch:{ Throwable -> 0x00be }
    L_0x030d:
        r2 = r3;
        r3 = r4 + 1;
        r4 = r3;
        r3 = r2;
        goto L_0x02e6;
    L_0x0313:
        r2 = 0;
        if (r4 == 0) goto L_0x032c;
    L_0x0316:
        r5 = r8.d();	 Catch:{ Throwable -> 0x00be }
        if (r5 == 0) goto L_0x032c;
    L_0x031c:
        r5 = r8.d();	 Catch:{ Throwable -> 0x00be }
        r5 = r4.equals(r5);	 Catch:{ Throwable -> 0x00be }
        if (r5 != 0) goto L_0x011b;
    L_0x0326:
        r8.a(r4);	 Catch:{ Throwable -> 0x00be }
        r2 = 1;
        goto L_0x011b;
    L_0x032c:
        if (r4 == 0) goto L_0x011b;
    L_0x032e:
        r5 = r8.d();	 Catch:{ Throwable -> 0x00be }
        if (r5 != 0) goto L_0x011b;
    L_0x0334:
        r8.a(r4);	 Catch:{ Throwable -> 0x00be }
        r2 = 1;
        goto L_0x011b;
    L_0x033a:
        r2 = r3;
        goto L_0x0048;
    L_0x033d:
        r3 = r5;
        goto L_0x0116;
    L_0x0340:
        r2 = r3;
        goto L_0x0113;
    L_0x0343:
        r2 = r4;
        goto L_0x0113;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.beacon.b.b.a.a(int, byte[], boolean):void");
    }
}
