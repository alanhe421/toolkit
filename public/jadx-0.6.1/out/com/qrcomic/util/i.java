package com.qrcomic.util;

import com.tencent.feedback.eup.BuglyBroadcastRecevier;
import com.tencent.util.TimeFormatterUtils;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;

/* compiled from: StringUtils */
public class i {
    static Field a = null;
    static Field b = null;
    static volatile boolean c = true;
    static volatile boolean d = true;

    public static Long a(String str) {
        long time;
        System.currentTimeMillis();
        try {
            time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str).getTime();
        } catch (Exception e) {
            time = System.currentTimeMillis();
        }
        return Long.valueOf(time);
    }

    public static String b(String str) {
        long longValue = a(str).longValue();
        StringBuilder stringBuilder = new StringBuilder();
        longValue -= System.currentTimeMillis();
        if (longValue > 0) {
            stringBuilder.append("还剩");
            int i = (int) (longValue / TimeFormatterUtils.ONE_DAY);
            if (i > 0) {
                stringBuilder.append(i);
                stringBuilder.append("天");
                longValue -= ((long) i) * TimeFormatterUtils.ONE_DAY;
            }
            i = (int) (longValue / 3600000);
            if (i > 0) {
                stringBuilder.append(i);
                stringBuilder.append("小时");
                longValue -= ((long) i) * 3600000;
            }
            int i2 = (int) (longValue / BuglyBroadcastRecevier.UPLOADLIMITED);
            if (i2 > 0) {
                stringBuilder.append(i2);
                stringBuilder.append("分钟");
            }
        }
        return stringBuilder.toString();
    }
}
