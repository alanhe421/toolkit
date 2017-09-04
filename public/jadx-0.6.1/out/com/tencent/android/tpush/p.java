package com.tencent.android.tpush;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import com.tencent.android.tpush.encrypt.Rijndael;
import com.tencent.android.tpush.service.channel.security.TpnsSecurity;

/* compiled from: ProGuard */
final class p implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ long b;

    p(Context context, long j) {
        this.a = context;
        this.b = j;
    }

    public void run() {
        if (TpnsSecurity.checkTpnsSecurityLibSo(this.a)) {
            SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.a);
            if (defaultSharedPreferences != null) {
                Editor edit = defaultSharedPreferences.edit();
                edit.putString(XGPushConfig.TPUSH_ACCESS_ID, Rijndael.encrypt(String.valueOf(this.b)));
                edit.commit();
            }
        }
    }
}
