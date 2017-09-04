package com.qq.reader.qurl.a;

import android.app.Activity;
import android.text.TextUtils;
import com.qq.reader.activity.NativeBookStoreSearchActivity;
import com.qq.reader.common.utils.o;
import com.qq.reader.qurl.d;
import java.util.Map;

/* compiled from: URLServerOfSearch */
public class q extends d {
    public q(Activity activity, String str, String str2) {
        super(activity, str, str2);
    }

    public void f() throws Exception {
        String str;
        CharSequence charSequence;
        Map e = e();
        if (e != null) {
            str = (String) e.get("key");
            charSequence = (String) e.get("type");
        } else {
            charSequence = null;
            str = null;
        }
        Activity b = b();
        if (b instanceof NativeBookStoreSearchActivity) {
            ((NativeBookStoreSearchActivity) b).a(str, ((NativeBookStoreSearchActivity) b).e());
        } else if (TextUtils.isEmpty(charSequence) || !"audio".equals(charSequence)) {
            o.c(b(), str);
        } else {
            o.d(b(), str);
        }
    }
}
