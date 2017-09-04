package com.hmt.analytics.common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import com.dynamicload.Lib.DLConstants;

public class CommonUtillNetwork {
    public static String getNetwork(Context context) {
        String str = "未知";
        switch (getNetworkClass(context)) {
            case DLConstants.LOAD_ERR_SIGNATURES /*-101*/:
                return "WIFI";
            case -1:
                return "";
            case 0:
                return "";
            case 1:
                return "2G";
            case 2:
                return "3G";
            case 3:
                return "4G";
            default:
                return str;
        }
    }

    private static int getNetworkClass(Context context) {
        int type;
        try {
            if (CommonUtil.checkPermissions(context, "android.permission.ACCESS_NETWORK_STATE")) {
                NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
                if (activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.isConnected()) {
                    type = activeNetworkInfo.getType();
                    if (type == 1) {
                        type = DLConstants.LOAD_ERR_SIGNATURES;
                    } else {
                        if (type == 0) {
                            type = ((TelephonyManager) context.getSystemService("phone")).getNetworkType();
                        }
                        type = 0;
                    }
                } else {
                    type = -1;
                }
            } else {
                CommonUtil.printLog("getNetworkClass lost  permission", "lost----> android.permission.ACCESS_NETWORK_STATE");
                type = 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getNetworkClassByType(type);
    }

    private static int getNetworkClassByType(int i) {
        switch (i) {
            case DLConstants.LOAD_ERR_SIGNATURES /*-101*/:
                return DLConstants.LOAD_ERR_SIGNATURES;
            case -1:
                return -1;
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
                return 1;
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
                return 2;
            case 13:
                return 3;
            default:
                return 0;
        }
    }
}
