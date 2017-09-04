package com.qq.reader.common.conn.socket;

import android.content.Context;
import android.text.TextUtils;
import com.qq.reader.appconfig.a.c;
import com.qq.reader.appconfig.b;
import java.util.Random;

/* compiled from: PushConfig */
public class d {
    public static final String[] a = new String[]{"9080", "9082", "9084", "9086"};
    public static int b = 0;
    private static Random c = new Random();

    public static String a(Context context) {
        String str = "";
        if (b.a) {
            return "ws://101.227.160.57:9090";
        }
        StringBuilder stringBuilder;
        try {
            stringBuilder = new StringBuilder();
            str = c.e(context);
            if (TextUtils.isEmpty(str)) {
                str = "ws://ws.reader.qq.com";
            } else if (!str.startsWith("ws://")) {
                str = "ws://" + str;
            }
            stringBuilder.append(str);
            stringBuilder.append(":");
            String[] strArr = a;
            Object d = c.d(context);
            if (!TextUtils.isEmpty(d)) {
                strArr = d.split("\\|");
            }
            b = c.nextInt(strArr.length);
            str = strArr[b];
            Integer.parseInt(str);
        } catch (Exception e) {
            str = a[c.nextInt(a.length)];
        } catch (Throwable th) {
            th.printStackTrace();
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("ws://ws.reader.qq.com");
            stringBuilder2.append(":");
            stringBuilder2.append(a[c.nextInt(a.length)]);
            return stringBuilder2.toString();
        }
        stringBuilder.append(str);
        return stringBuilder.toString();
    }
}
