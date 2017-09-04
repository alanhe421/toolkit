package com.qq.reader.common.web;

import android.app.Activity;
import android.graphics.Color;
import android.text.TextUtils;
import com.qq.reader.activity.WebBrowserForContents;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.utils.ao;
import com.tencent.feedback.proguard.R;

/* compiled from: WebCustomExParamHandle */
public class b {
    private static b a;

    public static b a() {
        if (a == null) {
            a = new b();
        }
        return a;
    }

    public void a(Activity activity, String str) {
        if (activity != null) {
            try {
                if (activity instanceof WebBrowserForContents) {
                    String i = ao.i(str, "titlebarcolor");
                    if (!TextUtils.isEmpty(i)) {
                        if (!i.startsWith("#")) {
                            i = "#" + i;
                        }
                        activity.findViewById(R.id.common_titler).setBackgroundColor(Color.parseColor(i));
                    }
                }
            } catch (Exception e) {
                c.e("Error", e.getMessage());
            }
        }
    }
}
