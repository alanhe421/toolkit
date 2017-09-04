package com.qq.reader.common.utils;

import android.text.TextUtils;
import com.qq.reader.module.question.fragment.FamousAuthorSayFragment;
import com.tencent.android.tpush.common.Constants;
import com.tencent.qalsdk.im_open.http;
import com.tencent.upload.log.trace.TracerConfig;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/* compiled from: ConvertUtil */
public class j {
    public static String a(int i) {
        StringBuilder stringBuilder = new StringBuilder();
        if (i < Constants.ERRORCODE_UNKNOWN) {
            stringBuilder.append(i);
            stringBuilder.append("字");
        } else if (i < Constants.ERRORCODE_UNKNOWN || i >= 1000000) {
            stringBuilder.append((i + 5000) / Constants.ERRORCODE_UNKNOWN);
            stringBuilder.append("万字");
        } else {
            stringBuilder.append(i / Constants.ERRORCODE_UNKNOWN);
            stringBuilder.append(".");
            stringBuilder.append(((i + http.Internal_Server_Error) % Constants.ERRORCODE_UNKNOWN) / 1000);
            stringBuilder.append("万字");
        }
        return stringBuilder.toString();
    }

    public static String a(long j) {
        StringBuilder stringBuilder = new StringBuilder();
        if (j <= 0) {
            stringBuilder.append(0);
        } else if (j < TracerConfig.LOG_FLUSH_DURATION) {
            stringBuilder.append(j);
        } else if (j < 1000000) {
            stringBuilder.append((j + 500) / TracerConfig.LOG_FLUSH_DURATION);
            r2 = ((j + 500) % TracerConfig.LOG_FLUSH_DURATION) / 1000;
            if (r2 != 0) {
                stringBuilder.append(".");
                stringBuilder.append(r2);
            }
            stringBuilder.append("万");
        } else if (j < 99995000) {
            stringBuilder.append(((int) (5000 + j)) / Constants.ERRORCODE_UNKNOWN);
            stringBuilder.append("万");
        } else {
            stringBuilder.append((j + 5000000) / 100000000);
            r2 = ((j + 5000000) % 100000000) / 10000000;
            if (r2 != 0) {
                stringBuilder.append(".");
                stringBuilder.append(r2);
            }
            stringBuilder.append("亿");
        }
        return stringBuilder.toString();
    }

    public static String a(String str) {
        long longValue;
        String str2 = null;
        if (TextUtils.isDigitsOnly(str)) {
            try {
                longValue = Long.valueOf(str).longValue();
            } catch (Exception e) {
                e.printStackTrace();
                return str2;
            }
        }
        try {
            longValue = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA).parse(str).getTime();
        } catch (ParseException e2) {
            e2.printStackTrace();
            return str2;
        }
        return e(longValue);
    }

    private static String e(long j) {
        StringBuilder stringBuilder = new StringBuilder();
        long timeInMillis = (Calendar.getInstance().getTimeInMillis() - j) / 1000;
        long j2 = timeInMillis / 84640;
        long j3 = timeInMillis / 3600;
        timeInMillis = (timeInMillis / 60) % 60;
        if (j2 > 0) {
            return stringBuilder.append(j2).append("天前").toString();
        }
        if (j3 > 0) {
            return stringBuilder.append(j3).append("小时前").toString();
        }
        if (timeInMillis > 0) {
            return stringBuilder.append(timeInMillis).append("分钟前").toString();
        }
        return stringBuilder.append("刚刚").toString();
    }

    public static String b(long j) {
        long currentTimeMillis = System.currentTimeMillis() - j;
        if (currentTimeMillis < 0) {
            return "";
        }
        if (currentTimeMillis < 259200000) {
            return "3天内";
        }
        if (currentTimeMillis < FamousAuthorSayFragment.DATA_EXPIREDTIME_WEEK) {
            return "7天内";
        }
        if (currentTimeMillis < 1296000000) {
            return "15天内";
        }
        if (currentTimeMillis < 2592000000L) {
            return "最近1月";
        }
        return "1月以前";
    }

    public static String c(long j) {
        if (j <= 0) {
            return null;
        }
        long j2 = j / 1000;
        long j3 = (j2 / 60) / 60;
        if (j3 <= 0) {
            j2 = (j2 / 60) % 60;
            if (j2 == 0) {
                j2 = 1;
            }
            return j2 + "分钟";
        } else if (j3 <= 9999) {
            return j3 + "小时" + ((j2 / 60) % 60) + "分钟";
        } else {
            j3 = ((j2 / 60) / 60) / 24;
            return j3 + "天" + (((j2 / 60) / 60) % 24) + "小时";
        }
    }

    public static String d(long j) {
        if (j < 0) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder(String.valueOf(j));
        int length = stringBuilder.length();
        while (length > 3) {
            length -= 3;
            stringBuilder.insert(length, ',');
        }
        return stringBuilder.toString();
    }
}
