package com.hmt.analytics.util;

import android.content.Context;
import android.text.TextUtils;
import com.hmt.analytics.common.HMTConstants;
import com.hmt.analytics.common.HMTTool;
import java.util.regex.Pattern;

public class HmtUtils {
    public static boolean checkToday(long j) {
        return HMTTool.isToDay(System.currentTimeMillis(), j);
    }

    public static boolean checkGap(long j) {
        return System.currentTimeMillis() - j > HMTConstants.c;
    }

    public static boolean checkGap(long j, long j2) {
        return System.currentTimeMillis() - j > j2;
    }

    public static boolean checkClientDataSendState(Context context) {
        return HMTTool.isToDay(System.currentTimeMillis(), ((Long) SPUtils.get(context, "hmt_client_data_send_time", Long.valueOf(0))).longValue());
    }

    public static boolean checkStrIsNum(String str) {
        if (TextUtils.isEmpty(str) || !Pattern.compile("[0-9]*").matcher(str).matches()) {
            return false;
        }
        return true;
    }
}
