package com.qq.reader.module.feed.loader;

import android.text.TextUtils;
import com.qq.reader.common.monitor.f;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/* compiled from: FeedTimeStyleBUtil */
public class g {
    private static SimpleDateFormat a = new SimpleDateFormat("yyyyMMddHH", Locale.CHINA);

    public static boolean a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (TextUtils.isEmpty(str2)) {
            return true;
        }
        try {
            String[] split = str.split("-");
            String[] split2 = str2.split("-");
            long time = a.parse(split[0]).getTime();
            long time2 = a.parse(split2[0]).getTime();
            if (time == time2) {
                if (split.length <= 1 || split2.length <= 1) {
                    if (split.length <= split2.length) {
                        return false;
                    }
                    return true;
                } else if (Integer.parseInt(split[1]) <= Integer.parseInt(split2[1])) {
                    return false;
                } else {
                    return true;
                }
            } else if (time <= time2) {
                return false;
            } else {
                return true;
            }
        } catch (ParseException e) {
            f.a("FeedTimeUtil", e.getMessage());
            return false;
        }
    }

    public static long a(String str) {
        long j = 0;
        try {
            return (System.currentTimeMillis() - a.parse(str).getTime()) / 1000;
        } catch (ParseException e) {
            return j;
        }
    }

    public static String b(String str) {
        try {
            Date parse = a.parse(str);
            Calendar instance = Calendar.getInstance();
            instance.setTime(parse);
            instance.add(11, 1);
            return a.format(instance.getTime());
        } catch (Exception e) {
            f.a("FeedTimeUtil", e.getMessage());
            return "";
        }
    }

    public static String a(String str, int i) {
        return str + "-" + i;
    }

    public static String c(String str) {
        try {
            String substring = str.substring(str.length() - 2, str.length());
            if (substring.startsWith("0")) {
                substring.replace("0", "");
            }
            int parseInt = Integer.parseInt(substring) - 1;
            if (parseInt < 10) {
                return str.substring(0, str.length() - 2) + "0" + parseInt;
            }
            return str.substring(0, str.length() - 2) + parseInt;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String d(String str) {
        int i = 1;
        try {
            Date parse = a.parse(str);
            Calendar instance = Calendar.getInstance();
            instance.setTime(parse);
            int i2 = instance.get(11);
            if (i2 > 7 || i2 < 1) {
                i = i2;
            }
            instance.add(11, (i - i2) - 1);
            return a.format(instance.getTime());
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
