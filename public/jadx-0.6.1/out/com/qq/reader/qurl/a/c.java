package com.qq.reader.qurl.a;

import android.app.Activity;
import com.qq.reader.common.utils.o;
import com.qq.reader.qurl.d;
import java.util.Map;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;

/* compiled from: URLServerOfAuthor */
public class c extends d {
    private final String a = "index";
    private final String b = "product";
    private final String c = "mainpage";

    public c(Activity activity, String str, String str2) {
        super(activity, str, str2);
    }

    public void f() throws Exception {
        String d = d();
        if ("index".equalsIgnoreCase(d)) {
            g();
        } else if ("product".equalsIgnoreCase(d)) {
            i();
        } else if ("mainpage".equalsIgnoreCase(d)) {
            h();
        } else if ("allbooks".equals(d) && e() != null) {
            a((String) e().get("authorId"), (String) e().get("bid"));
        }
    }

    private void i() {
        o.c(b(), (String) e().get("name"), 12, a());
    }

    public void g() {
        o.a(b(), null, 0, a().b(SigType.WLOGIN_QRPUSH));
    }

    public void a(String str, String str2) {
        o.a(b(), str, str2);
    }

    public void h() {
        Map e = e();
        o.c(b(), (String) e.get("authorId"), (String) e.get("name"), (String) e.get("iconUrl"), a());
    }
}
