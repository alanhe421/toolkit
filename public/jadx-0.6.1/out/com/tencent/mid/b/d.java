package com.tencent.mid.b;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class d extends f {
    public d(Context context, int i) {
        super(context, i);
    }

    public int a() {
        return 4;
    }

    protected void a(a aVar) {
        synchronized (this) {
            b.b("write CheckEntity to sharedPreferences:" + aVar.toString());
            Editor edit = PreferenceManager.getDefaultSharedPreferences(this.c).edit();
            edit.putString(g(), aVar.toString());
            edit.commit();
        }
    }

    protected void a(String str) {
        a(h(), str);
    }

    public void a(String str, String str2) {
        synchronized (this) {
            b.b((Object) "write mid to sharedPreferences");
            Editor edit = PreferenceManager.getDefaultSharedPreferences(this.c).edit();
            edit.putString(str, str2);
            edit.commit();
        }
    }

    public String b(String str) {
        String string;
        synchronized (this) {
            b.b("read mid from sharedPreferences， key=" + str);
            string = PreferenceManager.getDefaultSharedPreferences(this.c).getString(str, null);
        }
        return string;
    }

    protected boolean b() {
        return true;
    }

    protected String c() {
        return b(h());
    }

    protected a d() {
        a aVar;
        synchronized (this) {
            aVar = new a(PreferenceManager.getDefaultSharedPreferences(this.c).getString(g(), null));
            b.b("read CheckEntity from sharedPreferences:" + aVar.toString());
        }
        return aVar;
    }
}
