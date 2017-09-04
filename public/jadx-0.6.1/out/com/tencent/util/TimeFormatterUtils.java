package com.tencent.util;

import android.content.Context;
import android.provider.Settings.SettingNotFoundException;
import android.provider.Settings.System;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.text.format.Time;
import com.qq.reader.ReaderApplication;
import com.tencent.feedback.proguard.R;
import com.tencent.imsdk.QLogImpl;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class TimeFormatterUtils {
    public static final int FORMAT_DEFAULT = 0;
    public static final int FORMAT_SHOW_WEEKDAY = 2;
    public static final int FORMAT_SHOW_YESTERDAY = 1;
    public static final int FORMAT_SHOW_YESTERDAY_WEEKDAY = 3;
    private static final int INTEVEL_WEEKDAY = 7;
    private static final int INTEVEL_YESTERDAY = 1;
    public static final long ONE_DAY = 86400000;
    private static final String STR_WEEKDAY = "EEEE";
    private static StringBuffer dateStrBuf = null;
    private static Calendar stampCal = null;
    private static int timeOffset = 0;
    private static final char timeSplit = '/';

    public enum TimeInterval {
        TODAY,
        YESTERDAY,
        THE_DAY_BEFORE_YESTERDAY,
        WITHIN_YEAR,
        WITHOUT_YEAR
    }

    public static void init() {
        timeOffset = TimeZone.getTimeZone("GMT+8").getRawOffset();
        stampCal = Calendar.getInstance();
        dateStrBuf = new StringBuffer();
    }

    public static int getDateName(long j) {
        int currentTimeMillis = (int) ((System.currentTimeMillis() + ((long) timeOffset)) / ONE_DAY);
        int i = currentTimeMillis - 1;
        int i2 = currentTimeMillis - 2;
        int i3 = (int) ((((long) timeOffset) + j) / ONE_DAY);
        if (i3 == currentTimeMillis) {
            return R.string.today;
        }
        if (i3 == i) {
            return R.string.yesterday;
        }
        if (i3 == i2) {
            return R.string.before_yesterday;
        }
        return -1;
    }

    public static int getDateName(long j, Calendar calendar) {
        int currentTimeMillis = (int) ((System.currentTimeMillis() + ((long) timeOffset)) / ONE_DAY);
        int i = currentTimeMillis - 1;
        int i2 = currentTimeMillis - 2;
        i2 = currentTimeMillis - 7;
        int i3 = (int) ((((long) timeOffset) + j) / ONE_DAY);
        if (i3 == currentTimeMillis) {
            return R.string.today;
        }
        if (i3 == i) {
            return R.string.yesterday;
        }
        if (i3 >= i || i3 <= i2) {
            return -1;
        }
        if (calendar == null) {
            calendar = Calendar.getInstance();
            calendar.setTimeInMillis(j);
        }
        switch (calendar.get(7)) {
            case 1:
                return R.string.sunday;
            case 2:
                return R.string.monday;
            case 3:
                return R.string.tuesday;
            case 4:
                return R.string.wednesday;
            case 5:
                return R.string.thursday;
            case 6:
                return R.string.friday;
            case 7:
                return R.string.saturday;
            default:
                return -1;
        }
    }

    public static int getDateNameForRecentList(long j, Calendar calendar) {
        Time time = new Time();
        time.set(j);
        long currentTimeMillis = System.currentTimeMillis();
        Time time2 = new Time();
        time2.set(currentTimeMillis);
        int i = time2.yearDay - 1;
        int i2 = time2.yearDay - 7;
        if (time.year == time2.year) {
            if (time2.yearDay < time.yearDay) {
                return -1;
            }
            if (time2.yearDay == time.yearDay) {
                return R.string.today;
            }
            if (time.yearDay == i) {
                return R.string.yesterday;
            }
            if (time.yearDay >= i || time.yearDay <= i2) {
                return -1;
            }
            switch (time.weekDay) {
                case 0:
                    return R.string.sunday;
                case 1:
                    return R.string.monday;
                case 2:
                    return R.string.tuesday;
                case 3:
                    return R.string.wednesday;
                case 4:
                    return R.string.thursday;
                case 5:
                    return R.string.friday;
                case 6:
                    return R.string.saturday;
                default:
                    return -1;
            }
        } else if (time.year + 1 != time2.year) {
            return -1;
        } else {
            currentTimeMillis = (((currentTimeMillis - j) + ONE_DAY) - 1) / ONE_DAY;
            if (currentTimeMillis <= 0 || currentTimeMillis > 7) {
                return -1;
            }
            if (currentTimeMillis == 1) {
                return R.string.yesterday;
            }
            switch (time.weekDay) {
                case 0:
                    return R.string.sunday;
                case 1:
                    return R.string.monday;
                case 2:
                    return R.string.tuesday;
                case 3:
                    return R.string.wednesday;
                case 4:
                    return R.string.thursday;
                case 5:
                    return R.string.friday;
                case 6:
                    return R.string.saturday;
                default:
                    return -1;
            }
        }
    }

    public static String getRecentMessageDateTime(long j, boolean z, String str) {
        return getRecentMessageDateTime(dateStrBuf, j, z, str);
    }

    public static String getRecentMessageDateTime(StringBuffer stringBuffer, long j, boolean z, String str) {
        if (stringBuffer == null) {
            return null;
        }
        stringBuffer.setLength(0);
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        Object obj = null;
        int dateNameForRecentList = getDateNameForRecentList(j, instance);
        if (dateNameForRecentList != -1) {
            obj = 1;
            if (dateNameForRecentList != R.string.today) {
                stringBuffer.append(ReaderApplication.getApplicationImp().getApplicationContext().getString(dateNameForRecentList));
            }
        }
        int i = instance.get(11);
        int i2 = instance.get(12);
        if (obj != null) {
            boolean is24HourFormat = DateFormat.is24HourFormat(ReaderApplication.getApplicationImp().getApplicationContext());
            if (dateNameForRecentList == R.string.today) {
                if (is24HourFormat) {
                    if (i < 10) {
                        stringBuffer.append('0');
                    }
                    stringBuffer.append(i);
                    stringBuffer.append(':');
                    if (i2 < 10) {
                        stringBuffer.append('0');
                    }
                    stringBuffer.append(i2);
                } else {
                    if (i < 0 || i >= 12) {
                        stringBuffer.append(ReaderApplication.getApplicationImp().getApplicationContext().getString(R.string.afternoon));
                    } else {
                        stringBuffer.append(ReaderApplication.getApplicationImp().getApplicationContext().getString(R.string.shangwu));
                    }
                    stringBuffer.append(' ');
                    int i3 = i == 12 ? 12 : i % 12;
                    if (i3 < 10) {
                        stringBuffer.append('0');
                    }
                    stringBuffer.append(i3);
                    stringBuffer.append(':');
                    if (i2 < 10) {
                        stringBuffer.append('0');
                    }
                    stringBuffer.append(i2);
                }
            } else if (!z) {
                stringBuffer.append(' ');
                if (i < 10) {
                    stringBuffer.append('0');
                }
                stringBuffer.append(i);
                stringBuffer.append(':');
                if (i2 < 10) {
                    stringBuffer.append('0');
                }
                stringBuffer.append(i2);
            }
        } else {
            SimpleDateFormat simpleDateFormat;
            if (TextUtils.isEmpty(str)) {
                str = "yyyy-MM-dd";
            }
            try {
                simpleDateFormat = new SimpleDateFormat(str);
            } catch (Exception e) {
                simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            }
            stringBuffer.append(simpleDateFormat.format(instance.getTime()));
            if (!z) {
                stringBuffer.append(' ');
                stringBuffer.append(i);
                stringBuffer.append(':');
                if (i2 < 10) {
                    stringBuffer.append('0');
                }
                stringBuffer.append(i2);
            }
        }
        return stringBuffer.toString();
    }

    public static String getMessageDateTime(long j, boolean z) {
        dateStrBuf.setLength(0);
        stampCal.setTimeInMillis(j);
        Object obj = null;
        int dateName = getDateName(j);
        if (dateName != -1) {
            obj = 1;
            if (dateName != R.string.today) {
                dateStrBuf.append(ReaderApplication.getApplicationImp().getApplicationContext().getString(dateName));
            }
        }
        int i = stampCal.get(11);
        int i2 = stampCal.get(12);
        if (obj == null) {
            dateStrBuf.append(stampCal.get(1)).append(timeSplit).append(stampCal.get(2) + 1).append(timeSplit).append(stampCal.get(5));
            if (!z) {
                dateStrBuf.append(' ');
                dateStrBuf.append(i);
                dateStrBuf.append(':');
                if (i2 < 10) {
                    dateStrBuf.append('0');
                }
                dateStrBuf.append(i2);
            }
        } else if (dateName == R.string.today) {
            if (i < 0 || i >= 12) {
                dateStrBuf.append(ReaderApplication.getApplicationImp().getApplicationContext().getString(R.string.afternoon));
            } else {
                dateStrBuf.append(ReaderApplication.getApplicationImp().getApplicationContext().getString(R.string.shangwu));
            }
            dateStrBuf.append(' ');
            int i3 = i == 12 ? 12 : i % 12;
            if (i3 < 10) {
                dateStrBuf.append('0');
            }
            dateStrBuf.append(i3);
            dateStrBuf.append(':');
            if (i2 < 10) {
                dateStrBuf.append('0');
            }
            dateStrBuf.append(i2);
        } else if (!z) {
            dateStrBuf.append(' ');
            if (i < 10) {
                dateStrBuf.append('0');
            }
            dateStrBuf.append(i);
            dateStrBuf.append(':');
            if (i2 < 10) {
                dateStrBuf.append('0');
            }
            dateStrBuf.append(i2);
        }
        return dateStrBuf.toString();
    }

    public static CharSequence formatDateTime(Context context, int i, long j) {
        return formatDateTime(context, i, j, true);
    }

    public static CharSequence formatDateTime(Context context, int i, long j, boolean z) {
        StringBuilder stringBuilder = new StringBuilder();
        SimpleDateFormat simpleDateFormat = (SimpleDateFormat) DateFormat.getDateFormat(context);
        if (i != 0) {
            return getDateTimeFormatStr(context, j, stringBuilder, simpleDateFormat, i, z);
        }
        stringBuilder.append(simpleDateFormat.toLocalizedPattern());
        return DateFormat.format(stringBuilder.append(" ").toString(), j) + DateFormat.getTimeFormat(context).format(Long.valueOf(j));
    }

    private static CharSequence getDateTimeFormatStr(Context context, long j, StringBuilder stringBuilder, SimpleDateFormat simpleDateFormat, int i, boolean z) {
        Object obj = null;
        Time time = new Time();
        time.set(j);
        Time time2 = new Time();
        time2.setToNow();
        Object obj2 = (i & 2) != 0 ? 1 : null;
        Object obj3;
        if ((i & 1) != 0) {
            obj3 = 1;
        } else {
            obj3 = null;
        }
        if (time.year != time2.year) {
            stringBuilder.append(simpleDateFormat.toLocalizedPattern()).append(" ");
        } else if (time.yearDay != time2.yearDay) {
            Object obj4;
            int abs = Math.abs(time2.yearDay - time.yearDay);
            if (time2.yearDay > time.yearDay) {
                obj4 = 1;
            } else {
                obj4 = null;
            }
            if (obj4 == null) {
                stringBuilder.append(simpleDateFormat.toLocalizedPattern()).append(" ");
            } else if (abs == 1 && r0 != null) {
                stringBuilder.append(context.getString(R.string.aio_yesterday)).append(" ");
                if (z) {
                    return stringBuilder.toString() + DateFormat.getTimeFormat(context).format(Long.valueOf(j));
                }
                return stringBuilder.toString().trim();
            } else if (abs > 1 && abs < 7 && obj2 != null) {
                stringBuilder.append(STR_WEEKDAY).append(" ");
            } else if (time.year == time2.year) {
                stringBuilder.append("MM-dd").append(" ");
            } else {
                stringBuilder.append(simpleDateFormat.toLocalizedPattern()).append(" ");
            }
        } else {
            int i2 = 1;
        }
        if (obj != null || z) {
            return DateFormat.format(stringBuilder.toString(), j) + DateFormat.getTimeFormat(context).format(Long.valueOf(j));
        }
        return DateFormat.format(stringBuilder.toString().trim(), j);
    }

    public static CharSequence formatDateTimeMonthly(Context context, long j) {
        stampCal.setTimeInMillis(j);
        int i = stampCal.get(2) + 1;
        int i2 = stampCal.get(5);
        return String.format("%s%s%s%s", new Object[]{Integer.valueOf(i), context.getString(R.string.month), Integer.valueOf(i2), context.getString(R.string.day)});
    }

    public static CharSequence formatTime(Context context, long j) {
        return DateFormat.getTimeFormat(context).format(new Date(j));
    }

    public static String formatRefreshTime(Context context, long j) {
        stampCal.setTimeInMillis(System.currentTimeMillis());
        int i = stampCal.get(1);
        int i2 = stampCal.get(2);
        int i3 = stampCal.get(5);
        stampCal.setTimeInMillis(j);
        i = calDayDiff(i, i2, i3, stampCal.get(1), stampCal.get(2), stampCal.get(5));
        if (i == 0) {
            try {
                i = System.getInt(context.getContentResolver(), "time_12_24");
            } catch (SettingNotFoundException e) {
                i = 0;
            }
            if (i != 12) {
                return new SimpleDateFormat("HH:mm", context.getResources().getConfiguration().locale).format(new Date(j));
            }
            String string = context.getString(stampCal.get(11) < 12 ? R.string.shangwu : R.string.afternoon);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm", context.getResources().getConfiguration().locale);
            return String.format("%s %s", new Object[]{string, simpleDateFormat.format(new Date(j))});
        } else if (i == 1) {
            return context.getString(R.string.yesterday);
        } else {
            if (i >= 7 || i <= 1) {
                return DateFormat.getDateFormat(context).format(new Date(j));
            }
            return new SimpleDateFormat(QLogImpl.TAG_REPORTLEVEL_USER, context.getResources().getConfiguration().locale).format(new Date(j));
        }
    }

    public static String formatBackupTime(Context context, long j) {
        stampCal.setTimeInMillis(System.currentTimeMillis());
        int i = stampCal.get(1);
        int i2 = stampCal.get(2);
        int i3 = stampCal.get(5);
        stampCal.setTimeInMillis(j);
        i = calDayDiff(i, i2, i3, stampCal.get(1), stampCal.get(2), stampCal.get(5));
        String string;
        SimpleDateFormat simpleDateFormat;
        if (i == 0) {
            try {
                i = System.getInt(context.getContentResolver(), "time_12_24");
            } catch (SettingNotFoundException e) {
                i = 0;
            }
            if (i == 12) {
                string = context.getString(stampCal.get(11) < 12 ? R.string.shangwu : R.string.afternoon);
                simpleDateFormat = new SimpleDateFormat("hh:mm", context.getResources().getConfiguration().locale);
                return context.getString(R.string.today) + " " + String.format("%s %s", new Object[]{string, simpleDateFormat.format(new Date(j))});
            }
            return context.getString(R.string.today) + " " + new SimpleDateFormat("HH:mm", context.getResources().getConfiguration().locale).format(new Date(j));
        } else if (i == 1) {
            try {
                i = System.getInt(context.getContentResolver(), "time_12_24");
            } catch (SettingNotFoundException e2) {
                i = 0;
            }
            if (i == 12) {
                string = context.getString(stampCal.get(11) < 12 ? R.string.shangwu : R.string.afternoon);
                simpleDateFormat = new SimpleDateFormat("hh:mm", context.getResources().getConfiguration().locale);
                return context.getString(R.string.yesterday) + " " + String.format("%s %s", new Object[]{string, simpleDateFormat.format(new Date(j))});
            }
            return context.getString(R.string.yesterday) + " " + new SimpleDateFormat("HH:mm", context.getResources().getConfiguration().locale).format(new Date(j));
        } else if (i >= 7 || i <= 1) {
            return new SimpleDateFormat("yyyy-MM-dd", context.getResources().getConfiguration().locale).format(new Date(j));
        } else {
            return new SimpleDateFormat(QLogImpl.TAG_REPORTLEVEL_USER, context.getResources().getConfiguration().locale).format(new Date(j));
        }
    }

    private static int calDayDiff(int i, int i2, int i3, int i4, int i5, int i6) {
        return (int) (((((new GregorianCalendar(i, i2, i3).getTimeInMillis() - new GregorianCalendar(i4, i5, i6).getTimeInMillis()) / 1000) / 60) / 60) / 24);
    }

    public static String formatNewRefreshTime(Context context, long j) {
        stampCal.setTimeInMillis(System.currentTimeMillis());
        int i = stampCal.get(1);
        int i2 = stampCal.get(2);
        int i3 = stampCal.get(6);
        stampCal.setTimeInMillis(j);
        SimpleDateFormat simpleDateFormat;
        switch (calTimeInterval(i, i2, i3, stampCal.get(1), stampCal.get(2), stampCal.get(6))) {
            case TODAY:
                return new SimpleDateFormat("HH:mm", context.getResources().getConfiguration().locale).format(new Date(j));
            case YESTERDAY:
                simpleDateFormat = new SimpleDateFormat("HH:mm", context.getResources().getConfiguration().locale);
                return String.format("%s %s", new Object[]{"鏄ㄥぉ", simpleDateFormat.format(new Date(j))});
            case THE_DAY_BEFORE_YESTERDAY:
                simpleDateFormat = new SimpleDateFormat("HH:mm", context.getResources().getConfiguration().locale);
                return String.format("%s %s", new Object[]{"鍓嶅ぉ", simpleDateFormat.format(new Date(j))});
            case WITHIN_YEAR:
                return new SimpleDateFormat("MM-dd HH:mm", context.getResources().getConfiguration().locale).format(new Date(j));
            case WITHOUT_YEAR:
                return new SimpleDateFormat("yyyy-MM-dd HH:mm", context.getResources().getConfiguration().locale).format(new Date(j));
            default:
                return null;
        }
    }

    public static String formatDatetime(long j) {
        Calendar instance = Calendar.getInstance();
        Date date = new Date(j);
        instance.setTime(date);
        Calendar instance2 = Calendar.getInstance();
        instance2.setTimeInMillis(System.currentTimeMillis());
        if (instance.get(1) == instance2.get(1) && instance.get(6) == instance2.get(6)) {
            return "浠婂ぉ";
        }
        if (instance.get(1) == instance2.get(1) && instance.get(6) + 1 == instance2.get(6)) {
            return "鏄ㄥぉ";
        }
        if (instance.get(1) == instance2.get(1) && instance.get(6) > instance2.get(6) - 7) {
            switch (instance.get(7)) {
                case 1:
                    return "鏄熸湡镞?";
                case 2:
                    return "鏄熸湡涓?";
                case 3:
                    return "鏄熸湡浜?";
                case 4:
                    return "鏄熸湡涓?";
                case 5:
                    return "鏄熸湡锲?";
                case 6:
                    return "鏄熸湡浜?";
                case 7:
                    return "鏄熸湡鍏?";
            }
        } else if (instance.get(1) == instance2.get(1)) {
            return new SimpleDateFormat("MM-dd").format(date);
        }
        return new SimpleDateFormat("yy-MM-dd").format(date);
    }

    private static TimeInterval calTimeInterval(int i, int i2, int i3, int i4, int i5, int i6) {
        if (i != i4) {
            return TimeInterval.WITHOUT_YEAR;
        }
        if (i3 == i6) {
            return TimeInterval.TODAY;
        }
        if (i3 == i6 + 1) {
            return TimeInterval.YESTERDAY;
        }
        if (i3 == i6 + 2) {
            return TimeInterval.THE_DAY_BEFORE_YESTERDAY;
        }
        return TimeInterval.WITHIN_YEAR;
    }

    public static String getFormatTime(long j, String str) {
        if (j <= 0) {
            return null;
        }
        return new SimpleDateFormat(str).format(new Date(j));
    }

    public static TimeInterval calTimeInterval(long j) {
        stampCal.setTimeInMillis(System.currentTimeMillis());
        int i = stampCal.get(1);
        int i2 = stampCal.get(2);
        int i3 = stampCal.get(6);
        stampCal.setTimeInMillis(j);
        return calTimeInterval(i, i2, i3, stampCal.get(1), stampCal.get(2), stampCal.get(6));
    }
}
