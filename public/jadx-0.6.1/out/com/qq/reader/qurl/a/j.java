package com.qq.reader.qurl.a;

import android.app.Activity;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.o;
import com.qq.reader.qurl.d;

/* compiled from: URLServerOfDiscover */
public class j extends d {
    private final String a = "index";
    private final String b = "vipzone";
    private final String c = "todayfree";
    private final String d = "listenzone";
    private final String e = "authorsay";
    private final String f = "obtainwelfare";
    private final String g = "classicalbook";
    private final String h = "famousauthor";
    private final String i = "specialoffer";
    private final String j = "finishedbook";
    private final String k = "limittimediscountbuy";
    private final String l = "audiozoneactivity";
    private final String m = "audiosecondpage";
    private final String n = "audiozonelistenbook";

    public j(Activity activity, String str, String str2) {
        super(activity, str, str2);
    }

    public void f() throws Exception {
        String d = d();
        if ("index".equalsIgnoreCase(d)) {
            h();
        } else if ("vipzone".equalsIgnoreCase(d)) {
            i();
        } else if ("todayfree".equalsIgnoreCase(d)) {
            g();
        } else if ("listenzone".equalsIgnoreCase(d)) {
            j();
        } else if ("authorsay".equalsIgnoreCase(d)) {
            k();
        } else if ("obtainwelfare".equalsIgnoreCase(d)) {
            l();
        } else if ("classicalbook".equalsIgnoreCase(d)) {
            m();
        } else if ("famousauthor".equalsIgnoreCase(d)) {
            n();
        } else if ("specialoffer".equalsIgnoreCase(d)) {
            o();
        } else if ("finishedbook".equalsIgnoreCase(d)) {
            p();
        } else if ("limittimediscountbuy".equalsIgnoreCase(d)) {
            q();
        } else if ("audiozoneactivity".equalsIgnoreCase(d)) {
            r();
        } else if ("audiosecondpage".equalsIgnoreCase(d)) {
            s();
        } else if ("audiozonelistenbook".equalsIgnoreCase(d)) {
            t();
        }
    }

    public void g() {
        o.b(b(), ao.a(e()), a());
    }

    public void h() {
        o.f(b(), a());
    }

    public void i() {
        o.l(b(), a());
    }

    public void j() {
        o.a(b(), false, a());
    }

    public void k() {
        o.b(b(), 0, 0, null);
    }

    public void l() {
        o.B(b(), null);
    }

    public void m() {
        o.d(b(), null, null);
    }

    public void n() {
        o.C(b(), null);
    }

    public void o() {
        o.e(b(), ao.a(e()), a());
    }

    public void p() {
        o.f(b(), ao.a(e()), a());
    }

    public void q() {
        String str;
        String str2 = null;
        if (e() != null) {
            str = (String) e().get("starttime");
            str2 = (String) e().get("bids");
        } else {
            str = null;
        }
        o.h(b(), str, str2, a());
    }

    public void r() {
        o.k(b(), a());
    }

    public void s() {
        String str = "";
        String str2 = "";
        String str3 = "";
        String str4 = "";
        String str5 = "";
        if (e() != null) {
            str2 = (String) e().get("actionId");
            str5 = (String) e().get("title");
            str4 = (String) e().get("actionFlag");
            str3 = (String) e().get("actionTag");
            str = (String) e().get("action");
        }
        o.a(b(), str2, str, str3, str4, str5, a());
    }

    public void t() {
        o.j(b(), a());
    }
}
