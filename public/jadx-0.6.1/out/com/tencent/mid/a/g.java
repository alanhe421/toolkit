package com.tencent.mid.a;

import android.content.Context;
import com.tencent.mid.api.MidCallback;
import com.tencent.mid.api.MidEntity;
import com.tencent.mid.util.Util;
import com.tencent.mid.util.f;

public class g {
    private static f a = Util.getLogger();

    public static MidEntity a(Context context) {
        return com.tencent.mid.b.g.a(context).g();
    }

    public static void a(Context context, MidCallback midCallback) {
        a.b("requestMid, callback=" + midCallback);
        b(context, new h(midCallback));
    }

    public static void a(boolean z) {
        Util.getLogger().a(z);
    }

    public static boolean a() {
        return Util.getLogger().a();
    }

    public static boolean a(String str) {
        return Util.isMidValid(str);
    }

    public static String b(Context context) {
        if (context == null) {
            a.f("context==null in getMid()");
            return null;
        }
        String f = com.tencent.mid.b.g.a(context).f();
        if (Util.isMidValid(f)) {
            return f;
        }
        MidCallback iVar = new i();
        a.h("getMid -> request new mid entity.");
        m.a().a(new j(context, 1, iVar));
        return f;
    }

    public static void b(Context context, MidCallback midCallback) {
        if (c(context, midCallback)) {
            MidEntity a = a(context);
            if (a == null || !a.isMidValid()) {
                a.b((Object) "requestMidEntity -> request new mid entity.");
                m.a().a(new j(context, 1, midCallback));
                return;
            }
            a.b("requestMidEntity -> get local mid entity:" + a.toString());
            midCallback.onSuccess(a.toString());
            m.a().a(new j(context, 2, midCallback));
        }
    }

    public static String c(Context context) {
        if (context != null) {
            return com.tencent.mid.b.g.a(context).f();
        }
        a.f("context==null in getMid()");
        return null;
    }

    private static boolean c(Context context, MidCallback midCallback) {
        return true;
    }
}
