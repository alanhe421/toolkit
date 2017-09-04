package com.qq.reader.qurl.a;

import android.app.Activity;
import com.qq.reader.common.utils.o;
import com.qq.reader.qurl.d;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;

/* compiled from: URLServerOfRank */
public class p extends d {
    private final String a = "index";
    private final String b = "list";
    private final String c = "secondlist";

    public p(Activity activity, String str, String str2) {
        super(activity, str, str2);
    }

    public void f() throws Exception {
        String d = d();
        if ("index".equalsIgnoreCase(d)) {
            g();
        } else if ("list".equalsIgnoreCase(d)) {
            h();
        } else if ("secondlist".equalsIgnoreCase(d)) {
            i();
        }
    }

    public void g() {
        o.b(b(), null, (String) e().get("rankFlag"), a().b(SigType.WLOGIN_QRPUSH));
    }

    public void h() {
        if (e() != null) {
            o.b(b(), null, (String) e().get("actionId"), (String) e().get("actionTag"), a().b(SigType.WLOGIN_QRPUSH));
        }
    }

    public void i() {
        if (e() != null) {
            o.a(b(), null, (String) e().get("actionId"), (String) e().get("actionTag"), a().b(SigType.WLOGIN_QRPUSH));
        }
    }
}
