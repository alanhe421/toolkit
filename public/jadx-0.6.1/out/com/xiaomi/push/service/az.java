package com.xiaomi.push.service;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.OnAccountsUpdateListener;
import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.android.f;
import com.xiaomi.channel.commonutils.b.c;
import java.util.ArrayList;
import java.util.Iterator;

public class az {
    private static volatile az c;
    private Context a;
    private AccountManager b;
    private ArrayList<a> d;
    private OnAccountsUpdateListener e;

    public interface a {
        void a(String str, Context context);
    }

    private az(Context context) {
        this.a = context;
        if (f.b(this.a)) {
            this.b = AccountManager.get(this.a);
            this.d = new ArrayList();
        }
    }

    public static az a(Context context) {
        if (c == null) {
            synchronized (az.class) {
                if (c == null) {
                    c = new az(context);
                }
            }
        }
        return c;
    }

    private void a(String str) {
        if (this.d != null && this.d.size() >= 1) {
            Iterator it = this.d.iterator();
            while (it.hasNext()) {
                ((a) it.next()).a(str, this.a);
            }
        }
    }

    private void d() {
        if (this.e == null) {
            this.e = new ba(this);
        }
    }

    private String e() {
        Account a = f.a(this.a);
        return a == null ? "" : a.name;
    }

    public void a(a aVar) {
        if (this.d == null) {
            this.d = new ArrayList();
        }
        if (aVar != null) {
            int size = this.d.size();
            this.d.add(aVar);
            if (size == 0 && !a()) {
                c.a("MIIDManager startMIIDUpdateListener failed cause lack of GET_ACCOUNTS permission");
            }
        }
    }

    public boolean a() {
        try {
            if (!f.b(this.a)) {
                return false;
            }
            if (this.e == null) {
                d();
            }
            this.b.addOnAccountsUpdatedListener(this.e, null, true);
            return true;
        } catch (Exception e) {
            c.d(e.toString());
            return false;
        }
    }

    public void b() {
        if (f.b(this.a) && this.e != null) {
            this.b.removeOnAccountsUpdatedListener(this.e);
        }
    }

    public void b(a aVar) {
        if (this.d != null && aVar != null) {
            this.d.remove(aVar);
            if (this.d.size() == 0) {
                b();
            }
        }
    }

    public String c() {
        String e = e();
        if (TextUtils.isEmpty(e)) {
            bb.a(this.a).a("0");
            return "0";
        }
        bb.a(this.a).a(e);
        return e;
    }
}
