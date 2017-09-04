package com.qq.reader.qurl.a;

import android.app.Activity;
import com.qq.reader.common.utils.o;
import com.qq.reader.qurl.d;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;

/* compiled from: URLServerOfVip */
public class t extends d {
    private final String a = "privilege";
    private final String b = "open";
    private final String c = "openbybookcoin";

    public t(Activity activity, String str, String str2) {
        super(activity, str, str2);
    }

    public void f() throws Exception {
        String d = d();
        if ("privilege".equalsIgnoreCase(d)) {
            a(b());
        } else if ("open".equalsIgnoreCase(d)) {
            g();
        } else if ("openbybookcoin".equalsIgnoreCase(d)) {
            h();
        }
    }

    public void a(Activity activity) {
        o.h(activity, a().b(SigType.WLOGIN_QRPUSH).a(c()));
    }

    public void g() {
        o.a(b());
    }

    public void h() {
        try {
            boolean z;
            int intValue = Integer.valueOf((String) e().get("month")).intValue();
            try {
                z = Integer.valueOf((String) e().get("autopay")).intValue() == 1;
            } catch (Exception e) {
                z = false;
            }
            o.a(b(), intValue, z);
        } catch (Exception e2) {
        }
    }
}
