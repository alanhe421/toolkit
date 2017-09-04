package com.qq.reader.common.login.define;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.push.b;
import com.qq.reader.common.readertask.g;
import com.qq.reader.module.feed.mypreference.c;
import com.qq.reader.plugin.w;
import com.tencent.connect.common.Constants;

/* compiled from: LoginConfig */
public class a {
    private static void a(Editor editor) {
        editor.commit();
    }

    public static void a(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("SETTING", 0);
        String string = sharedPreferences.getString("DEFAULT_ACC_INDEX", "");
        if (string.trim().length() > 0) {
            b(context, string);
        }
        string = sharedPreferences.getString("NICK_NAME", "");
        if (string.trim().length() > 0) {
            c(context, string);
        }
        string = sharedPreferences.getString("user_avator", "");
        if (string.trim().length() > 0) {
            d(context, string);
        }
        int i = sharedPreferences.getInt("login_type", -1);
        if (i == 1 || i == 2 || i == 50) {
            a(context, i);
        }
        if (i == 2) {
            a(context, sharedPreferences.getString(Constants.PARAM_ACCESS_TOKEN, null));
            e(context, sharedPreferences.getString("refresh_token", null));
            g(context, sharedPreferences.getString("openid", null));
            i(context, sharedPreferences.getString("unionid", null));
        }
        g.a().a(new LoginConfig$1(context));
    }

    public static void b(Context context) {
        a(context.getSharedPreferences("LOGIN", 0).edit().putBoolean("init_status", true));
    }

    public static boolean c(Context context) {
        return context.getSharedPreferences("LOGIN", 0).getBoolean("init_status", false);
    }

    public static void a(Context context, String str) {
        a(context.getSharedPreferences("LOGIN", 4).edit().putString("login_key", str));
    }

    public static String d(Context context) {
        return context.getSharedPreferences("LOGIN", 4).getString("login_key", "");
    }

    public static void b(Context context, String str) {
        String e = e(context);
        a(context.getSharedPreferences("LOGIN", 4).edit().putString("login_uin", str));
        d.j(context, str);
        com.qq.reader.appconfig.a.a.a(str);
        d.z = true;
        d.A = true;
        if (e == null || !e.equals(str)) {
            b.a();
            com.qq.reader.appconfig.a.a.a().b();
            w.b().e(d.bS(ReaderApplication.getApplicationImp()));
            c.b().a(null);
            com.qq.reader.module.feed.mypreference.a.a().a(null);
            Intent intent = new Intent();
            intent.setAction(com.qq.reader.common.c.a.cI);
            context.sendBroadcast(intent);
        }
        d.c(context, false);
    }

    public static String e(Context context) {
        String string = context.getSharedPreferences("LOGIN", 4).getString("login_uin", null);
        if (string == null) {
            return com.qq.reader.appconfig.a.a.c();
        }
        return string;
    }

    public static void c(Context context, String str) {
        a(context.getSharedPreferences("LOGIN", 4).edit().putString("login_nickname", str));
    }

    public static String f(Context context) {
        return context.getSharedPreferences("LOGIN", 4).getString("login_nickname", "");
    }

    public static void d(Context context, String str) {
        a(context.getSharedPreferences("LOGIN", 4).edit().putString("login_avatar", str));
    }

    public static String g(Context context) {
        return context.getSharedPreferences("LOGIN", 4).getString("login_avatar", "");
    }

    public static void a(Context context, int i) {
        a(context.getSharedPreferences("LOGIN", 4).edit().putInt("login_type", i));
    }

    public static int h(Context context) {
        return context.getSharedPreferences("LOGIN", 4).getInt("login_type", -1);
    }

    public static void e(Context context, String str) {
        a(context.getSharedPreferences("LOGIN", 0).edit().putString("refresh_token", str));
    }

    public static void f(Context context, String str) {
        a(context.getSharedPreferences("LOGIN", 0).edit().putString("old_acctess_token", str));
    }

    public static String i(Context context) {
        return context.getSharedPreferences("LOGIN", 0).getString("refresh_token", "");
    }

    public static void g(Context context, String str) {
        a(context.getSharedPreferences("LOGIN", 0).edit().putString("wx_openid", str));
    }

    public static String j(Context context) {
        return context.getSharedPreferences("LOGIN", 0).getString("wx_openid", "");
    }

    public static void h(Context context, String str) {
        a(context.getSharedPreferences("LOGIN", 0).edit().putString("qidian_openid", str));
    }

    public static String k(Context context) {
        return context.getSharedPreferences("LOGIN", 0).getString("qidian_openid", "");
    }

    public static void a(Context context, boolean z) {
        a(context.getSharedPreferences("LOGIN", 0).edit().putBoolean("other_login_show", z));
    }

    public static boolean l(Context context) {
        return context.getSharedPreferences("LOGIN", 0).getBoolean("other_login_show", true);
    }

    public static void i(Context context, String str) {
        a(context.getSharedPreferences("LOGIN", 0).edit().putString("unionid", str));
    }

    public static String m(Context context) {
        return context.getSharedPreferences("LOGIN", 0).getString("unionid", "");
    }

    public static void a(Context context, long j) {
        a(context.getSharedPreferences("LOGIN", 0).edit().putLong("wx_expire_time", j));
    }

    public static void j(Context context, String str) {
        a(context.getSharedPreferences("LOGIN", 0).edit().putString("wx_scope", str));
    }

    public static String n(Context context) {
        return context.getSharedPreferences("LOGIN", 0).getString("wx_scope", "snsapi_userinfo");
    }

    public static void b(Context context, boolean z) {
        a(context.getSharedPreferences("LOGIN", 4).edit().putBoolean("is_vip", z));
    }

    public static boolean o(Context context) {
        return context.getSharedPreferences("LOGIN", 4).getBoolean("is_vip", false);
    }

    public static void b(Context context, int i) {
        a(context.getSharedPreferences("LOGIN", 4).edit().putInt("vip_type", i));
    }

    public static int p(Context context) {
        return context.getSharedPreferences("LOGIN", 4).getInt("vip_type", 0);
    }

    public static String q(Context context) {
        return context.getSharedPreferences("LOGIN", 0).getString("vip_end_time", "");
    }

    public static void k(Context context, String str) {
        a(context.getSharedPreferences("LOGIN", 0).edit().putString("vip_end_time", str));
    }

    public static void l(Context context, String str) {
        a(context.getSharedPreferences("LOGIN", 4).edit().putString("openid", str));
    }

    public static String r(Context context) {
        return context.getSharedPreferences("LOGIN", 4).getString("openid", "");
    }

    public static void c(Context context, int i) {
        a(context.getSharedPreferences("LOGIN", 4).edit().putInt("banlance", i));
    }

    public static int s(Context context) {
        return context.getSharedPreferences("LOGIN", 4).getInt("banlance", 0);
    }

    public static void d(Context context, int i) {
        a(context.getSharedPreferences("LOGIN", 4).edit().putInt(com.tencent.android.tpush.common.Constants.FLAG_TICKET, i));
    }

    public static int t(Context context) {
        return context.getSharedPreferences("LOGIN", 4).getInt(com.tencent.android.tpush.common.Constants.FLAG_TICKET, 0);
    }

    public static void e(Context context, int i) {
        a(context.getSharedPreferences("LOGIN", 4).edit().putInt("left_ticket", i));
    }

    public static int u(Context context) {
        return context.getSharedPreferences("LOGIN", 4).getInt("left_ticket", 0);
    }

    public static void f(Context context, int i) {
        a(context.getSharedPreferences("LOGIN", 0).edit().putInt("left_combine", i));
    }

    public static int v(Context context) {
        return context.getSharedPreferences("LOGIN", 0).getInt("left_combine", 0);
    }

    public static void g(Context context, int i) {
        a(context.getSharedPreferences("LOGIN", 4).edit().putInt("left_month_ticket", i));
    }

    public static int w(Context context) {
        return context.getSharedPreferences("LOGIN", 4).getInt("left_month_ticket", 0);
    }

    public static String x(Context context) {
        return context.getSharedPreferences("LOGIN", 4).getString("other_openid", "");
    }

    public static void b(Context context, long j) {
        a(context.getSharedPreferences("LOGIN", 0).edit().putLong("manitor_id", j));
    }

    public static long y(Context context) {
        return context.getSharedPreferences("LOGIN", 0).getLong("manitor_id", 0);
    }

    public static void h(Context context, int i) {
        a(context.getSharedPreferences("LOGIN", 0).edit().putInt("vipstatus", i));
    }

    public static int z(Context context) {
        return context.getSharedPreferences("LOGIN", 0).getInt("vipstatus", 0);
    }

    public static void c(Context context, long j) {
        a(context.getSharedPreferences("LOGIN", 0).edit().putLong("readtime", j));
    }

    public static long A(Context context) {
        return context.getSharedPreferences("LOGIN", 0).getLong("readtime", 0);
    }

    public static void m(Context context, String str) {
        a(context.getSharedPreferences("LOGIN", 0).edit().putString("vipComment", str));
    }

    public static String B(Context context) {
        return context.getSharedPreferences("LOGIN", 0).getString("vipComment", null);
    }
}
