package com.liveshow.b;

import android.app.Activity;
import com.qq.reader.qurl.c;

/* compiled from: RedirectionImpl */
public class g implements com.qq.reader.liveshow.b.g {
    public void a(Activity activity, String str) {
        if (str.contains("dialogshare")) {
            str = ("uniteqqreader://nativepage/client/" + str) + "&sharetype=16";
        } else if (!str.contains("http://")) {
            if (str.contains("liveshow")) {
                str = "uniteqqreader://nativepage/client/" + str;
            } else if (str.contains("recharge")) {
                str = "uniteqqreader://nativepage/coin/" + str;
            }
        }
        try {
            c.a(activity, str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
