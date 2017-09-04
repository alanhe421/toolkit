package com.qq.reader.common.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.qq.reader.ReaderApplication;
import com.qq.reader.activity.NewLoginActivity;
import com.qq.reader.common.login.a.b;
import com.qq.reader.common.login.a.d;
import com.qq.reader.common.login.a.e;
import com.qq.reader.common.login.b.a;
import java.util.ArrayList;
import java.util.Iterator;
import oicq.wlogin_sdk.sharemem.WloginLoginInfo;

/* compiled from: LoginManager */
public class c {
    private static a a = null;

    public static synchronized void a(Context context) {
        synchronized (c.class) {
            if (!com.qq.reader.common.login.define.a.c(context)) {
                com.qq.reader.common.login.define.a.a(context);
            }
        }
    }

    public static synchronized void a(Activity activity, int i) {
        synchronized (c.class) {
            Intent intent = new Intent();
            intent.setClass(activity, NewLoginActivity.class);
            intent.putExtra("display_login_type", i);
            activity.startActivityForResult(intent, 4098);
        }
    }

    public static synchronized void a() {
        synchronized (c.class) {
            Intent intent = new Intent();
            intent.setAction(com.qq.reader.common.c.a.cN);
            ReaderApplication.getApplicationImp().sendBroadcast(intent);
            switch (c().d()) {
                case 1:
                    com.qq.reader.common.login.a.c.a(ReaderApplication.getApplicationImp()).a();
                    break;
                case 2:
                    e.a(ReaderApplication.getApplicationImp()).a();
                    break;
                case 10:
                    b.a(ReaderApplication.getApplicationImp()).a();
                    break;
                case 50:
                    d.g().a();
                    break;
            }
            com.qq.reader.common.login.define.a.l(ReaderApplication.getApplicationImp(), "");
            com.qq.reader.common.login.define.a.a(ReaderApplication.getApplicationImp(), -1);
            com.qq.reader.common.login.define.a.c(ReaderApplication.getApplicationImp(), null);
            com.qq.reader.appconfig.a.d.d(ReaderApplication.getApplicationImp(), true);
            d.a(ReaderApplication.getApplicationImp());
        }
    }

    public static synchronized boolean b() {
        boolean z;
        synchronized (c.class) {
            z = com.qq.reader.common.login.a.c.a(ReaderApplication.getApplicationImp()).b() || e.a(ReaderApplication.getApplicationImp()).b() || b.a(ReaderApplication.getApplicationImp()).b() || d.g().b();
        }
        return z;
    }

    public static synchronized a c() {
        a aVar;
        synchronized (c.class) {
            switch (com.qq.reader.common.login.define.a.h(ReaderApplication.getApplicationImp())) {
                case -1:
                    a = new com.qq.reader.common.login.b.b();
                    break;
                case 1:
                    a = com.qq.reader.common.login.a.c.a(ReaderApplication.getApplicationImp()).e();
                    break;
                case 2:
                    a = e.a(ReaderApplication.getApplicationImp()).e();
                    break;
                case 10:
                    a = b.a(ReaderApplication.getApplicationImp()).e();
                    break;
                case 50:
                    a = d.g().e();
                    break;
                default:
                    a = new com.qq.reader.common.login.b.b();
                    break;
            }
            aVar = a;
        }
        return aVar;
    }

    public static synchronized boolean a(Activity activity, Boolean bool) {
        boolean z = false;
        synchronized (c.class) {
            if (b()) {
                a c = c();
                switch (c.d()) {
                    case 1:
                        String c2 = c.c();
                        ArrayList i = com.qq.reader.common.login.a.c.a((Context) activity).i();
                        if (i != null && i.size() > 0) {
                            boolean z2;
                            String e = com.qq.reader.common.login.define.a.e(activity);
                            if (e != null && e.length() > 0) {
                                Iterator it = i.iterator();
                                while (it.hasNext()) {
                                    if (e.equals(new Long(((WloginLoginInfo) it.next()).mUin).toString())) {
                                        if (bool.booleanValue()) {
                                            Intent intent = new Intent();
                                            intent.setClass(activity, NewLoginActivity.class);
                                            activity.startActivity(intent);
                                        } else {
                                            Bundle bundle = new Bundle();
                                            bundle.putString("login_qq", c2);
                                            bundle.putString("login_password", "");
                                            com.qq.reader.common.login.a.c.a((Context) activity).a(activity, bundle);
                                        }
                                        z2 = true;
                                        z = z2;
                                        break;
                                    }
                                }
                            }
                            z2 = false;
                            z = z2;
                        }
                    case 2:
                        e.a((Context) activity).a(false);
                        break;
                    default:
                        break;
                }
            }
        }
        return z;
    }

    public static synchronized void b(Context context) {
        synchronized (c.class) {
            com.qq.reader.common.login.a.c.a(context).c();
            e.a(context).c();
            b.a(context).c();
        }
    }
}
