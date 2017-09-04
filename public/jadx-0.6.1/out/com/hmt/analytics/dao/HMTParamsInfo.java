package com.hmt.analytics.dao;

import android.content.Context;
import com.hmt.analytics.common.CommonUtil;

public class HMTParamsInfo {
    private static String a = null;

    public static String getPackName(Context context) {
        if (a == null || a.equals("")) {
            a = CommonUtil.getPackageName(context);
        }
        return a;
    }
}
