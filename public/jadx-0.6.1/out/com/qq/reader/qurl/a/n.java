package com.qq.reader.qurl.a;

import android.app.Activity;
import android.os.Bundle;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.o;
import com.qq.reader.qurl.d;

/* compiled from: URLServerOfInfoStream */
public class n extends d {
    private final String a = "list";
    private final String b = "boutiques";
    private final String c = "column";
    private final String d = "dailyreading";
    private final String e = "individualbooklist";
    private final String f = "virtualrecommend";

    public n(Activity activity, String str, String str2) {
        super(activity, str, str2);
    }

    public void f() throws Exception {
        String d = d();
        if ("list".equalsIgnoreCase(d)) {
            h();
        } else if ("boutiques".equalsIgnoreCase(d)) {
            g();
        } else if ("column".equalsIgnoreCase(d)) {
            i();
        } else if ("virtualrecommend".equalsIgnoreCase(d)) {
            j();
        } else if ("dailyreading".equals(d)) {
            l();
        } else if ("individualbooklist".equals(d)) {
            k();
        }
    }

    private void j() {
        o.D(b(), a());
    }

    private void k() {
        Bundle bundle = new Bundle();
        if (e() != null) {
            String str = (String) e().get("bids");
            String str2 = (String) e().get("needGeneInfo");
            String str3 = (String) e().get("fromgene");
            if ("2".equals(str2)) {
                o.b(b());
                return;
            }
            bundle.putString("bids", str);
            bundle.putString("needGeneInfo", str2);
            bundle.putString("fromgene", str3);
        }
        bundle.putString("KEY_JUMP_PAGENAME", "Personality_books");
        bundle.putString("LOCAL_STORE_IN_TITLE", "专属推荐");
        o.d(b(), bundle, a());
    }

    private void l() {
        Bundle bundle = new Bundle();
        if (e() != null) {
            String str = (String) e().get("plan");
            bundle.putString("bids", (String) e().get("bids"));
            bundle.putString("ABTEST_PARAM", str);
        }
        bundle.putString("KEY_JUMP_PAGENAME", "today_read");
        bundle.putString("LOCAL_STORE_IN_TITLE", "今日必读");
        o.d(b(), bundle, a());
    }

    public void g() {
        o.a(b(), ao.a(e()), a());
    }

    public void h() {
        o.d(b(), a());
    }

    public void i() {
        o.g(b(), "SpecialColumn", "专栏", a());
        i.a("event_F83", null, b());
    }
}
