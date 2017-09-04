package com.qq.reader.qurl.a;

import android.app.Activity;
import android.text.TextUtils;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.utils.o;
import com.qq.reader.qurl.d;
import java.net.URLDecoder;
import java.util.Map;

/* compiled from: URLServerRedPacket */
public class v extends d {
    private final String a = "sendpacket";
    private final String b = "receivepacket";
    private final String c = "squarepage";
    private final String d = "singlebook";
    private final String e = "rank";
    private final String f = "myreceivedpacket";
    private final String g = "bid";
    private final String h = "bookname";
    private final String i = "rid";
    private final String j = "rtype";
    private final String k = "cbid";
    private final String l = "ranktype";
    private final String m = "timetype";

    public v(Activity activity, String str, String str2) {
        super(activity, str, str2);
    }

    public void f() throws Exception {
        String d = d();
        Map e = e();
        if ("sendpacket".equalsIgnoreCase(d)) {
            if (e != null) {
                d = (String) e.get("bid");
                if (!TextUtils.isEmpty(d)) {
                    o.a(b(), d);
                }
            }
        } else if ("receivepacket".equalsIgnoreCase(d)) {
            if (e != null) {
                d = (String) e.get("rid");
                if (!TextUtils.isEmpty(d)) {
                    String str = (String) e.get("rtype");
                    o.a(b(), Long.valueOf(d).longValue(), TextUtils.isEmpty(str) ? 0 : Integer.valueOf(str).intValue());
                }
            }
        } else if ("squarepage".equalsIgnoreCase(d)) {
            o.A(b(), a());
        } else if ("singlebook".equalsIgnoreCase(d)) {
            if (e != null) {
                try {
                    o.a(b(), URLDecoder.decode((String) e.get("bookname"), "UTF-8"), Long.valueOf((String) e.get("bid")).longValue(), Long.valueOf((String) e.get("cbid")).longValue(), false, a());
                } catch (NumberFormatException e2) {
                    e2.printStackTrace();
                }
            }
        } else if ("rank".equalsIgnoreCase(d)) {
            if (e != null) {
                try {
                    int parseInt = Integer.parseInt((String) e.get("ranktype"));
                    int parseInt2 = Integer.parseInt((String) e.get("timetype"));
                    if (parseInt == 2 || parseInt == 1) {
                        o.c(b(), parseInt, parseInt2, a());
                    }
                } catch (Exception e3) {
                    c.e("Error", e3.getMessage());
                }
            }
        } else if ("myreceivedpacket".equalsIgnoreCase(d)) {
            try {
                o.z(b(), null);
            } catch (Exception e32) {
                c.e("Error", e32.getMessage());
            }
        }
    }
}
