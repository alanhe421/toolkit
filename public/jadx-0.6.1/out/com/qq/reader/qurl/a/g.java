package com.qq.reader.qurl.a;

import android.app.Activity;
import android.text.TextUtils;
import com.qq.reader.common.utils.o;
import com.qq.reader.qurl.d;

/* compiled from: URLServerOfCoin */
public class g extends d {
    private final String a = "recharge";
    private final String b = "value";

    public g(Activity activity, String str, String str2) {
        super(activity, str, str2);
    }

    public void f() throws Exception {
        String d = d();
        int intValue = Integer.valueOf((String) e().get("value")).intValue();
        if ("recharge".equalsIgnoreCase(d)) {
            a(intValue);
        }
    }

    public void a(int i) {
        String str = (String) e().get("type");
        if (TextUtils.isEmpty(str)) {
            o.a(b(), i);
        } else {
            o.a(b(), i, str);
        }
    }
}
