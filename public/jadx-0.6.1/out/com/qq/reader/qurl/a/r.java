package com.qq.reader.qurl.a;

import android.app.Activity;
import com.qq.reader.common.utils.o;
import com.qq.reader.qurl.d;
import java.util.Map;

/* compiled from: URLServerOfTag */
public class r extends d {
    public r(Activity activity, String str, String str2) {
        super(activity, str, str2);
    }

    public void f() throws Exception {
        Map e = e();
        String str = (String) e.get("key");
        String str2 = (String) e.get("action");
        o.c(b(), str, str2, (String) e.get("actionId"), (String) e.get("actionTag"), null);
    }
}
