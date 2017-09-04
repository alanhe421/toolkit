package com.qq.reader.common.utils;

import com.tencent.feedback.eup.BuglyBroadcastRecevier;
import com.tencent.upload.log.trace.TracerConfig;
import com.tencent.util.TimeFormatterUtils;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* compiled from: DateTimeUtil */
public class k {
    public static SimpleDateFormat a = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static SimpleDateFormat b = new SimpleDateFormat("MM-dd HH:mm");
    public static SimpleDateFormat c = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public static SimpleDateFormat d = new SimpleDateFormat("HH:mm");
    public static SimpleDateFormat e = new SimpleDateFormat("yyyy-MM-dd");
    public static SimpleDateFormat f = new SimpleDateFormat("HH小时mm分钟");
    public static SimpleDateFormat g = new SimpleDateFormat("HH:mm:ss");
    public static SimpleDateFormat h = new SimpleDateFormat("mm:ss");
    public static final DecimalFormat i = new DecimalFormat("#.##");
    public static StringBuilder j = new StringBuilder();
    private static volatile Calendar k;
    private static ScheduledExecutorService l = new ScheduledThreadPoolExecutor(1);
    private static ThreadLocal<Calendar> m = new ThreadLocal<Calendar>() {
        protected /* synthetic */ Object initialValue() {
            return a();
        }

        protected Calendar a() {
            return Calendar.getInstance();
        }
    };

    static {
        k = Calendar.getInstance();
        Calendar instance = Calendar.getInstance();
        long timeInMillis = instance.getTimeInMillis();
        c(instance);
        k = instance;
        l.scheduleWithFixedDelay(new Runnable() {
            public void run() {
                Calendar instance = Calendar.getInstance();
                k.c(instance);
                k.k = instance;
            }
        }, (k.getTimeInMillis() + TracerConfig.LOG_FLUSH_DURATION) - timeInMillis, TimeFormatterUtils.ONE_DAY, TimeUnit.MILLISECONDS);
    }

    private static void c(Calendar calendar) {
        calendar.add(5, 1);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
    }

    public static String a(long j) {
        try {
            Calendar calendar = (Calendar) m.get();
            calendar.setTimeInMillis(j);
            long timeInMillis = k.getTimeInMillis() - j;
            Date date = new Date(j);
            if (timeInMillis <= TimeFormatterUtils.ONE_DAY) {
                return "今天" + d.format(date);
            }
            if (timeInMillis < 172800000) {
                return "昨天 " + d.format(date);
            }
            if (timeInMillis < 259200000) {
                return "前天 " + d.format(date);
            }
            if (calendar.get(1) == k.get(1)) {
                return b.format(date);
            }
            return e.format(date);
        } catch (Throwable th) {
            return "";
        }
    }

    public static String b(long j) {
        return c.format(Long.valueOf(j));
    }

    public static String c(long j) {
        try {
            Calendar calendar = (Calendar) m.get();
            calendar.setTimeInMillis(j);
            long currentTimeMillis = System.currentTimeMillis() - j;
            long timeInMillis = k.getTimeInMillis() - j;
            Date date = new Date(j);
            if (currentTimeMillis <= BuglyBroadcastRecevier.UPLOADLIMITED) {
                return "刚刚";
            }
            if (currentTimeMillis <= 3600000) {
                return ((currentTimeMillis / 1000) / 60) + "分钟前";
            }
            if (timeInMillis <= TimeFormatterUtils.ONE_DAY) {
                return "今天" + d.format(date);
            }
            if (timeInMillis < 172800000) {
                return "昨天 " + d.format(date);
            }
            if (timeInMillis < 259200000) {
                return "前天 " + d.format(date);
            }
            if (calendar.get(1) == k.get(1)) {
                return b.format(date);
            }
            return e.format(date);
        } catch (Throwable th) {
            return "";
        }
    }

    public static String d(long j) {
        try {
            Calendar calendar = (Calendar) m.get();
            calendar.setTimeInMillis(j);
            long currentTimeMillis = System.currentTimeMillis() - j;
            long timeInMillis = k.getTimeInMillis() - j;
            Date date = new Date(j);
            if (currentTimeMillis < 1000) {
                return "1秒前";
            }
            if (currentTimeMillis < BuglyBroadcastRecevier.UPLOADLIMITED) {
                return (currentTimeMillis / 1000) + "秒前";
            }
            if (currentTimeMillis <= 3600000) {
                return ((currentTimeMillis / 1000) / 60) + "分钟前";
            }
            if (timeInMillis <= TimeFormatterUtils.ONE_DAY) {
                return "今天" + d.format(date);
            }
            if (timeInMillis < 172800000) {
                return "昨天 " + d.format(date);
            }
            if (timeInMillis < 259200000) {
                return "前天 " + d.format(date);
            }
            if (calendar.get(1) == k.get(1)) {
                return b.format(date);
            }
            return e.format(date);
        } catch (Throwable th) {
            return "";
        }
    }

    public static String e(long j) {
        j.setLength(0);
        try {
            long currentTimeMillis = j - System.currentTimeMillis();
            if (currentTimeMillis <= 0) {
                return "1分钟后";
            }
            if (currentTimeMillis <= BuglyBroadcastRecevier.UPLOADLIMITED) {
                return "1分钟后";
            }
            if (currentTimeMillis <= 3600000) {
                j.append((currentTimeMillis / 1000) / 60).append("分钟后");
                return j.toString();
            }
            j.append(currentTimeMillis / 3600000).append("小时");
            currentTimeMillis = (currentTimeMillis % 3600000) / BuglyBroadcastRecevier.UPLOADLIMITED;
            if (currentTimeMillis > 0) {
                j.append(currentTimeMillis).append("分钟");
            }
            j.append("后");
            return j.toString();
        } catch (Throwable th) {
            return "";
        }
    }

    public static String f(long j) {
        if (j > 3600000) {
            return g.format(Long.valueOf(j));
        }
        return h.format(Long.valueOf(j));
    }
}
