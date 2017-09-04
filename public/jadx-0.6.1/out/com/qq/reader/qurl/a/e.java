package com.qq.reader.qurl.a;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.i;
import com.qq.reader.common.utils.o;
import com.qq.reader.qurl.d;

/* compiled from: URLServerOfCategory */
public class e extends d {
    private final String a = "index";
    private final String b = "list";
    private final String c = "secondarypage";
    private final String d = "library";

    public e(Activity activity, String str, String str2) {
        super(activity, str, str2);
    }

    public void f() throws Exception {
        String d = d();
        if ("index".equalsIgnoreCase(d)) {
            h();
        } else if ("list".equalsIgnoreCase(d)) {
            g();
        } else if ("secondarypage".equalsIgnoreCase(d)) {
            i();
        } else if ("library".equalsIgnoreCase(d)) {
            j();
        }
    }

    public void g() {
        if (e() != null) {
            int intValue;
            String str = (String) e().get("actionId");
            String str2 = (String) e().get("actionTag");
            String str3 = (String) e().get("action");
            String str4 = (String) e().get("title");
            try {
                intValue = Integer.valueOf((String) e().get("area")).intValue();
            } catch (NumberFormatException e) {
                e.printStackTrace();
                intValue = -1;
            }
            o.a(b(), str2, str, intValue, str3, str4, a());
        }
    }

    public void h() {
        if (e() != null) {
            o.a(b(), a(), (String) e().get("categoryFlag"));
        }
    }

    public void i() {
        if (e() != null) {
            String str = (String) e().get("categoryId");
            String str2 = (String) e().get("pagetitle");
            String b = i.b(str);
            Bundle a = ao.a(e());
            a.putString("bidsincid", String.valueOf(i.a(str)));
            a.putString("cidincate", i.a());
            if (!TextUtils.isEmpty(b)) {
                o.d(b(), b, str2, a, a());
            }
        }
    }

    private void j() {
        String str = "1";
        if (e() != null) {
            str = (String) e().get("tabIndex");
        }
        o.h(b(), str);
    }
}
