package com.tencent.android.tpush;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import com.tencent.android.tpush.encrypt.Rijndael;
import com.tencent.android.tpush.service.channel.security.TpnsSecurity;

/* compiled from: ProGuard */
final class q implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ String b;

    q(Context context, String str) {
        this.a = context;
        this.b = str;
    }

    public void run() {
        if (TpnsSecurity.checkTpnsSecurityLibSo(this.a)) {
            SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.a);
            if (defaultSharedPreferences != null) {
                Editor edit = defaultSharedPreferences.edit();
                edit.putString(XGPushConfig.TPUSH_ACCESS_KEY, Rijndael.encrypt(this.b));
                edit.commit();
            }
        }
    }
}
