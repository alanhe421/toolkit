package com.qq.reader.qurl.a;

import android.app.Activity;
import com.qq.reader.common.utils.o;
import com.qq.reader.qurl.d;

/* compiled from: URLServerOfGene */
public class m extends d {
    private final String a = "booklist_change_gene";
    private final String b = "edit";

    public m(Activity activity, String str, String str2) {
        super(activity, str, str2);
    }

    public void f() throws Exception {
        String d = d();
        if ("booklist_change_gene".equalsIgnoreCase(d)) {
            h();
        } else if ("edit".equalsIgnoreCase(d)) {
            i();
        } else {
            g();
        }
    }

    public void g() {
        o.o(b(), a());
    }

    public void h() {
        o.a(b(), true);
    }

    public void i() {
        boolean equalsIgnoreCase;
        try {
            equalsIgnoreCase = ((String) e().get("newuser")).equalsIgnoreCase("true");
        } catch (Exception e) {
            equalsIgnoreCase = false;
        }
        o.b(b(), equalsIgnoreCase, a());
    }
}
