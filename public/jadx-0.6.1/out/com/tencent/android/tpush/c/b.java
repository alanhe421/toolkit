package com.tencent.android.tpush.c;

import android.content.Context;
import android.content.Intent;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.a.a;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.g;
import com.tencent.android.tpush.common.p;
import com.tencent.android.tpush.encrypt.Rijndael;

/* compiled from: ProGuard */
public class b {
    public static void a(Context context) {
        if (context == null) {
            a.i(Constants.OTHER_PUSH_TAG, "updateToken Error: context is null");
        } else {
            g.a().a(new c(context));
        }
    }

    private static void c(Context context) {
        String token = XGPushConfig.getToken(context);
        if (token == null) {
            a.i(Constants.OTHER_PUSH_TAG, "updateToken Error: get XG Token is null");
            return;
        }
        long accessId = XGPushConfig.getAccessId(context);
        String b = a.b();
        String c = a.c(context);
        a.e(Constants.OTHER_PUSH_TAG, "other push token is : " + c + " other push type: " + b);
        if (p.b(b) || p.b(c)) {
            a.i(Constants.OTHER_PUSH_TAG, "updateToken Error: get otherPushType or otherPushToken is null");
        }
        Intent intent = new Intent("com.tencent.android.tpush.action.UPDATE_OTHER_PUSH_TOKEN");
        intent.putExtra("accId", Rijndael.encrypt("" + accessId));
        intent.putExtra(Constants.FLAG_TOKEN, Rijndael.encrypt(token));
        intent.putExtra("other_push_type", Rijndael.encrypt(b));
        intent.putExtra("other_push_token", Rijndael.encrypt(c));
        token = p.c(context);
        if (!p.b(token)) {
            intent.setPackage(token);
        }
        context.sendBroadcast(intent);
    }
}
