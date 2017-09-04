package com.tencent.android.tpush.b;

import android.content.Context;
import android.content.Intent;
import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.android.tpush.service.d.f;

/* compiled from: ProGuard */
class h implements Runnable {
    final /* synthetic */ g a;
    private final String b = h.class.getSimpleName();
    private Context c;
    private Intent d;
    private XGIOperateCallback e;

    public h(g gVar, Context context, Intent intent, XGIOperateCallback xGIOperateCallback) {
        this.a = gVar;
        this.c = context;
        this.d = intent;
        this.e = xGIOperateCallback;
    }

    private void a() {
        Intent intent = new Intent(Constants.ACTION_PUSH_MESSAGE);
        intent.setPackage(this.c.getPackageName());
        intent.putExtras(this.d);
        this.c.sendBroadcast(intent);
        String stringExtra = this.d.getStringExtra(MessageKey.MSG_SERVICE_PACKAGE_NAME);
        if (!f.a(stringExtra)) {
            Intent intent2 = new Intent("com.tencent.android.tpush.action.ack.sdk2srv");
            intent2.setPackage(stringExtra);
            intent2.putExtras(this.d);
            this.c.sendBroadcast(intent2);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
        r18 = this;
        r0 = r18;
        r11 = r0.a;
        monitor-enter(r11);
        r2 = com.tencent.android.tpush.XGPushConfig.enableDebug;	 Catch:{ all -> 0x00a0 }
        if (r2 == 0) goto L_0x0013;
    L_0x0009:
        r0 = r18;
        r2 = r0.b;	 Catch:{ all -> 0x00a0 }
        r3 = "Action -> handlerPushMessage";
        com.tencent.android.tpush.a.a.c(r2, r3);	 Catch:{ all -> 0x00a0 }
    L_0x0013:
        r10 = 0;
        r0 = r18;
        r2 = r0.d;	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r3 = "expire_time";
        r4 = 0;
        r4 = r2.getLongExtra(r3, r4);	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r0 = r18;
        r2 = r0.d;	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r2 = r2.getPackage();	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r6 = java.lang.System.currentTimeMillis();	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r0 = r18;
        r3 = r0.d;	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r8 = "msgId";
        r12 = -1;
        r8 = r3.getLongExtra(r8, r12);	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r0 = r18;
        r3 = r0.c;	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r0 = r18;
        r12 = r0.d;	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r3 = com.tencent.android.tpush.b.i.a(r3, r12);	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r12 = 0;
        r12 = (r4 > r12 ? 1 : (r4 == r12 ? 0 : -1));
        if (r12 <= 0) goto L_0x008d;
    L_0x004c:
        r4 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1));
        if (r4 <= 0) goto L_0x008d;
    L_0x0050:
        r2 = "PushMessageHandler";
        r4 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r4.<init>();	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r5 = "msg is expired, currentTimeMillis=";
        r4 = r4.append(r5);	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r4 = r4.append(r6);	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r5 = "current=";
        r4 = r4.append(r5);	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r4 = r4.append(r6);	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r5 = ".";
        r4 = r4.append(r5);	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r0 = r18;
        r5 = r0.d;	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r4 = r4.append(r5);	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r4 = r4.toString();	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        com.tencent.android.tpush.a.a.h(r2, r4);	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r0 = r18;
        r2 = r0.c;	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        com.tencent.android.tpush.XGPushManager.msgAck(r2, r3);	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        monitor-exit(r11);	 Catch:{ all -> 0x00a0 }
    L_0x008c:
        return;
    L_0x008d:
        r4 = java.lang.Long.valueOf(r8);	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r4 = com.tencent.android.tpush.b.g.a(r4);	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        if (r4 != 0) goto L_0x00a3;
    L_0x0097:
        r0 = r18;
        r2 = r0.c;	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        com.tencent.android.tpush.XGPushManager.msgAck(r2, r3);	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        monitor-exit(r11);	 Catch:{ all -> 0x00a0 }
        goto L_0x008c;
    L_0x00a0:
        r2 = move-exception;
        monitor-exit(r11);	 Catch:{ all -> 0x00a0 }
        throw r2;
    L_0x00a3:
        r4 = 2;
        com.tencent.android.tpush.a.a.a(r4, r8);	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r0 = r18;
        r4 = r0.d;	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r5 = "busiMsgId";
        r6 = 0;
        r6 = r4.getLongExtra(r5, r6);	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r0 = r18;
        r4 = r0.d;	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r5 = "timestamps";
        r12 = 0;
        r4 = r4.getLongExtra(r5, r12);	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r12 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r12.<init>();	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r13 = "@";
        r12 = r12.append(r13);	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r12 = r12.append(r8);	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r2 = r12.append(r2);	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r12 = "@";
        r2 = r2.append(r12);	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r2 = r2.toString();	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r0 = r18;
        r12 = r0.d;	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r13 = "accId";
        r14 = -1;
        r12 = r12.getLongExtra(r13, r14);	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r0 = r18;
        r14 = r0.c;	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r14 = com.tencent.android.tpush.XGPushConfig.getAccessidList(r14);	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        if (r14 == 0) goto L_0x0142;
    L_0x00f7:
        r15 = r14.size();	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        if (r15 <= 0) goto L_0x0142;
    L_0x00fd:
        r15 = java.lang.Long.valueOf(r12);	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r15 = r14.contains(r15);	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        if (r15 != 0) goto L_0x0142;
    L_0x0107:
        r2 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r2.<init>();	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r4 = "PushMessageRunnable match accessId failed, message droped cause accessId:";
        r2 = r2.append(r4);	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r2 = r2.append(r12);	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r4 = " not in ";
        r2 = r2.append(r4);	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r2 = r2.append(r14);	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r4 = " msgId = ";
        r2 = r2.append(r4);	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r2 = r2.append(r8);	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r2 = r2.toString();	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r0 = r18;
        r4 = r0.b;	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        com.tencent.android.tpush.a.a.i(r4, r2);	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r0 = r18;
        r2 = r0.c;	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        com.tencent.android.tpush.XGPushManager.msgAck(r2, r3);	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        monitor-exit(r11);	 Catch:{ all -> 0x00a0 }
        goto L_0x008c;
    L_0x0142:
        r0 = r18;
        r14 = r0.c;	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r14 = com.tencent.android.tpush.service.d.f.a(r14, r12);	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r15 = r14.contains(r2);	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        if (r15 != 0) goto L_0x028a;
    L_0x0150:
        r0 = r18;
        r15 = r0.c;	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r16 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r16.<init>();	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r17 = "tpush_msgId_";
        r16 = r16.append(r17);	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r0 = r16;
        r16 = r0.append(r12);	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r16 = r16.toString();	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r17 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r17.<init>();	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r0 = r17;
        r17 = r0.append(r2);	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r0 = r17;
        r17 = r0.append(r14);	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r17 = r17.toString();	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        com.tencent.android.tpush.common.m.b(r15, r16, r17);	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r0 = r18;
        r15 = r0.c;	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r16 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r16.<init>();	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r17 = "tpush_msgId_";
        r16 = r16.append(r17);	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r0 = r16;
        r16 = r0.append(r12);	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r16 = r16.toString();	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r17 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r17.<init>();	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r0 = r17;
        r17 = r0.append(r2);	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r0 = r17;
        r14 = r0.append(r14);	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r14 = r14.toString();	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r17 = 1;
        r0 = r16;
        r1 = r17;
        com.tencent.android.tpush.service.d.f.a(r15, r0, r14, r1);	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r0 = r18;
        r14 = r0.c;	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r15 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r15.<init>();	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r16 = "tpush_msgId_";
        r15 = r15.append(r16);	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r12 = r15.append(r12);	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r12 = r12.toString();	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r13 = "";
        r12 = com.tencent.android.tpush.common.m.a(r14, r12, r13);	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r12 = r12.contains(r2);	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        if (r12 != 0) goto L_0x01fd;
    L_0x01df:
        r0 = r18;
        r3 = r0.b;	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r4 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r4.<init>();	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r2 = r4.append(r2);	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r4 = " flag write failed";
        r2 = r2.append(r4);	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r2 = r2.toString();	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        com.tencent.android.tpush.a.a.h(r3, r2);	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        monitor-exit(r11);	 Catch:{ all -> 0x00a0 }
        goto L_0x008c;
    L_0x01fd:
        r2 = com.tencent.android.tpush.XGPushConfig.enableDebug;	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        if (r2 == 0) goto L_0x0220;
    L_0x0201:
        r0 = r18;
        r2 = r0.b;	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r12 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r12.<init>();	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r13 = "Receiver msg from server :";
        r12 = r12.append(r13);	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r13 = r3.toString();	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r12 = r12.append(r13);	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r12 = r12.toString();	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        com.tencent.android.tpush.a.a.e(r2, r12);	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
    L_0x0220:
        r0 = r18;
        r2 = r0.c;	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        com.tencent.android.tpush.XGPushManager.msgAck(r2, r3);	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r12 = r3.g();	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        if (r12 == 0) goto L_0x028f;
    L_0x022d:
        r2 = r3.f();	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r2 = com.tencent.android.tpush.service.d.f.a(r2);	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        if (r2 != 0) goto L_0x028f;
    L_0x0237:
        r2 = new com.tencent.android.tpush.b.c;	 Catch:{ Throwable -> 0x0272, JSONException -> 0x027e, IllegalArgumentException -> 0x0291 }
        r0 = r18;
        r13 = r0.c;	 Catch:{ Throwable -> 0x0272, JSONException -> 0x027e, IllegalArgumentException -> 0x0291 }
        r0 = r18;
        r14 = r0.d;	 Catch:{ Throwable -> 0x0272, JSONException -> 0x027e, IllegalArgumentException -> 0x0291 }
        r2.<init>(r13, r14);	 Catch:{ Throwable -> 0x0272, JSONException -> 0x027e, IllegalArgumentException -> 0x0291 }
        r18.a();	 Catch:{ Throwable -> 0x0272, JSONException -> 0x027e, IllegalArgumentException -> 0x0291 }
        r12 = r12.c();	 Catch:{ Throwable -> 0x0272, JSONException -> 0x027e, IllegalArgumentException -> 0x0291 }
        r13 = 1;
        if (r12 != r13) goto L_0x0257;
    L_0x024e:
        r2 = r2.a(r3, r4, r6, r8);	 Catch:{ Throwable -> 0x0272, JSONException -> 0x027e, IllegalArgumentException -> 0x0291 }
        if (r2 == 0) goto L_0x0257;
    L_0x0254:
        r3.a();	 Catch:{ Throwable -> 0x0272, JSONException -> 0x027e, IllegalArgumentException -> 0x0291 }
    L_0x0257:
        r2 = r10;
    L_0x0258:
        r0 = r18;
        r3 = r0.e;	 Catch:{ all -> 0x00a0 }
        if (r3 == 0) goto L_0x026f;
    L_0x025e:
        if (r2 == 0) goto L_0x02a8;
    L_0x0260:
        r0 = r18;
        r3 = r0.e;	 Catch:{ all -> 0x00a0 }
        r4 = "";
        r5 = -1;
        r2 = r2.toString();	 Catch:{ all -> 0x00a0 }
        r3.onFail(r4, r5, r2);	 Catch:{ all -> 0x00a0 }
    L_0x026f:
        monitor-exit(r11);	 Catch:{ all -> 0x00a0 }
        goto L_0x008c;
    L_0x0272:
        r2 = move-exception;
        r0 = r18;
        r3 = r0.b;	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        r4 = "unknown error";
        com.tencent.android.tpush.a.a.c(r3, r4, r2);	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
        goto L_0x0258;
    L_0x027e:
        r2 = move-exception;
        r0 = r18;
        r3 = r0.b;	 Catch:{ all -> 0x00a0 }
        r4 = "push parse error";
        com.tencent.android.tpush.a.a.c(r3, r4, r2);	 Catch:{ all -> 0x00a0 }
        goto L_0x0258;
    L_0x028a:
        r2 = 0;
        r0 = r18;
        r0.e = r2;	 Catch:{ JSONException -> 0x027e, IllegalArgumentException -> 0x0291, Throwable -> 0x029c }
    L_0x028f:
        r2 = r10;
        goto L_0x0258;
    L_0x0291:
        r2 = move-exception;
        r3 = "XGService";
        r4 = "push msg type error";
        com.tencent.android.tpush.a.a.c(r3, r4, r2);	 Catch:{ all -> 0x00a0 }
        goto L_0x0258;
    L_0x029c:
        r2 = move-exception;
        r0 = r18;
        r3 = r0.b;	 Catch:{ all -> 0x00a0 }
        r4 = "unknown error";
        com.tencent.android.tpush.a.a.c(r3, r4, r2);	 Catch:{ all -> 0x00a0 }
        goto L_0x0258;
    L_0x02a8:
        r0 = r18;
        r2 = r0.e;	 Catch:{ all -> 0x00a0 }
        r3 = "";
        r4 = 0;
        r2.onSuccess(r3, r4);	 Catch:{ all -> 0x00a0 }
        goto L_0x026f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.android.tpush.b.h.run():void");
    }
}
