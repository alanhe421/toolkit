package com.tencent.android.tpush;

import android.content.Context;
import android.content.Intent;
import com.tencent.android.tpush.c.a;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.o;
import com.tencent.android.tpush.common.p;
import com.tencent.android.tpush.encrypt.Rijndael;

/* compiled from: ProGuard */
final class v implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ XGIOperateCallback b;
    final /* synthetic */ long c;
    final /* synthetic */ String d;

    v(Context context, XGIOperateCallback xGIOperateCallback, long j, String str) {
        this.a = context;
        this.b = xGIOperateCallback;
        this.c = j;
        this.d = str;
    }

    public void run() {
        try {
            int a = p.a(this.a);
            if (a == 0) {
                long accessId = this.c <= 0 ? XGPushConfig.getAccessId(this.a) : this.c;
                String accessKey = p.b(this.d) ? XGPushConfig.getAccessKey(this.a) : this.d;
                String token = XGPushConfig.getToken(this.a);
                if ((accessId <= 0 || p.b(accessKey) || p.b(token)) && this.b != null) {
                    this.b.onFail(null, 10001, "The accessId, accessKey or token is invalid! accessId=" + accessId + ",accessKey=" + accessKey + ",token=" + token);
                    throw new IllegalArgumentException("accessId, accessKey or token is invalid.");
                }
                Intent intent = new Intent("com.tencent.android.tpush.action.UNREGISTER");
                intent.putExtra("accId", Rijndael.encrypt("" + accessId));
                intent.putExtra("accKey", Rijndael.encrypt(accessKey));
                intent.putExtra(Constants.FLAG_TOKEN, Rijndael.encrypt(token));
                intent.putExtra(Constants.FLAG_PACK_NAME, Rijndael.encrypt(this.a.getPackageName()));
                intent.putExtra("operation", 101);
                intent.putExtra("opType", 1);
                boolean a2 = o.a(this.a).a();
                if (p.d(this.a) != 1 || a2) {
                    XGPushManager.a(this.a, intent, this.b, a2);
                } else {
                    XGPushManager.d(this.a, intent, this.b);
                }
                if (o.a(this.a).b() && a.d(this.a)) {
                    a.b(this.a);
                }
            } else if (this.b != null) {
                this.b.onFail(null, a, "XINGE SDK config error");
            }
        } catch (Throwable th) {
            com.tencent.android.tpush.a.a.d(Constants.LogTag, "unregisterPush", th);
        }
    }
}
