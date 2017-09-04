package com.tencent.midas.data;

import android.content.Context;
import android.content.SharedPreferences;
import com.tencent.midas.api.APMidasPayAPI;
import com.tencent.midas.comm.APLog;

public class APDataId {
    private static APDataId a = null;
    private final String b = "TencentUnipay";
    private final String c = "initdataCount";
    private final String d = "dataCount";

    private APDataId() {
    }

    public static APDataId getInstance() {
        if (a == null) {
            a = new APDataId();
        }
        return a;
    }

    public int getDataId() {
        Context context = null;
        try {
            context = APMidasPayAPI.fromContext;
        } catch (Exception e) {
            APLog.i("getDataId context", e.toString());
        }
        if (context == null) {
            return -1000;
        }
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences("TencentUnipay" + APPluginDataInterface.singleton().getOfferId() + APPluginDataInterface.singleton().getOpenId(), 0);
            if (sharedPreferences == null) {
                return 0;
            }
            int i = sharedPreferences.getInt("dataCount", 0);
            setDataId(i + 1);
            return i;
        } catch (Exception e2) {
            APLog.i("getDataId", e2.toString());
            return 0;
        }
    }

    public int getInitDataId() {
        Context context = null;
        try {
            context = APMidasPayAPI.fromContext;
        } catch (Exception e) {
            APLog.i("getInitDataId context", e.toString());
        }
        if (context == null) {
            return -1000;
        }
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences("TencentUnipay" + APPluginDataInterface.singleton().getOfferId() + APPluginDataInterface.singleton().getOpenId(), 0);
            if (sharedPreferences == null) {
                return 0;
            }
            int i = sharedPreferences.getInt("initdataCount", 0);
            setInitDataId(i + 1);
            return i;
        } catch (Exception e2) {
            APLog.i("getInitDataId", e2.toString());
            return 0;
        }
    }

    public void setDataId(int i) {
        Context context = null;
        try {
            context = APMidasPayAPI.fromContext;
        } catch (Exception e) {
            APLog.i("setDataId context", e.toString());
        }
        if (context != null) {
            try {
                SharedPreferences sharedPreferences = context.getSharedPreferences("TencentUnipay" + APPluginDataInterface.singleton().getOfferId() + APPluginDataInterface.singleton().getOpenId(), 0);
                if (sharedPreferences == null) {
                    return;
                }
                if (i < 0) {
                    sharedPreferences.edit().putInt("dataCount", 0).commit();
                } else {
                    sharedPreferences.edit().putInt("dataCount", i).commit();
                }
            } catch (Exception e2) {
                APLog.i("setDataId", e2.toString());
            }
        }
    }

    public void setInitDataId(int i) {
        Context context = null;
        try {
            context = APMidasPayAPI.fromContext;
        } catch (Exception e) {
            APLog.i("setInitDataId context", e.toString());
        }
        if (context != null) {
            try {
                SharedPreferences sharedPreferences = context.getSharedPreferences("TencentUnipay" + APPluginDataInterface.singleton().getOfferId() + APPluginDataInterface.singleton().getOpenId(), 0);
                if (sharedPreferences == null) {
                    return;
                }
                if (i < 0) {
                    sharedPreferences.edit().putInt("initdataCount", 0).commit();
                } else {
                    sharedPreferences.edit().putInt("initdataCount", i).commit();
                }
            } catch (Exception e2) {
                APLog.i("setInitDataId", e2.toString());
            }
        }
    }
}
