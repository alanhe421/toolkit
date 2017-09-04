package com.tencent.android.tpush.stat.b;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

/* compiled from: ProGuard */
class e extends g {
    public e(Context context) {
        super(context);
    }

    protected String b() {
        String string;
        synchronized (this) {
            string = PreferenceManager.getDefaultSharedPreferences(this.a).getString(f(), null);
        }
        return string;
    }

    protected void a(String str) {
        synchronized (this) {
            Editor edit = PreferenceManager.getDefaultSharedPreferences(this.a).edit();
            edit.putString(f(), str);
            edit.commit();
        }
    }

    protected boolean a() {
        return true;
    }
}
