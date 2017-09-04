package com.qq.reader.plugin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import com.qq.reader.common.c.a;
import com.qq.reader.common.login.c;

/* compiled from: InternalPluginHandler */
public class i extends a {
    private SharedPreferences n;

    protected i(Context context, l lVar, h hVar) {
        super(context, lVar, hVar);
        this.n = context.getSharedPreferences("internalplugin", 0);
    }

    protected String a(l lVar) {
        return null;
    }

    protected void a(String str) {
    }

    protected void a(String str, Context context) {
    }

    protected void b(String str) {
    }

    protected void a(Bundle bundle) {
    }

    public synchronized void r() {
        if (c.b()) {
            Editor edit = this.n.edit();
            edit.putBoolean(this.j.i(), true);
            edit.commit();
            this.m.sendEmptyMessage(6108);
        } else {
            h();
        }
    }

    public boolean o() {
        return false;
    }

    public synchronized boolean i() {
        boolean z = true;
        synchronized (this) {
            if (this.n == null) {
                this.n = g().getSharedPreferences("internalplugin", 0);
            }
            boolean z2 = this.n.getBoolean(this.j.i(), false);
            if (z2 || k.b().b(this.j.i()).d() != 4) {
                z = z2;
            } else {
                Editor edit = this.n.edit();
                edit.putBoolean(this.j.i(), true);
                edit.commit();
            }
        }
        return z;
    }

    public boolean l() {
        return i();
    }

    public boolean j() {
        return true;
    }

    public boolean k() {
        Editor edit = this.n.edit();
        edit.putBoolean(this.j.i(), false);
        edit.commit();
        this.m.sendEmptyMessage(6110);
        Intent intent = new Intent();
        intent.setAction(a.cp);
        g().sendBroadcast(intent);
        return true;
    }
}
