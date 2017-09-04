package com.tencent.av.sdk;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import com.tencent.av.utils.QLog;

public class NetworkHelp {
    public static final String TAG = "NetworkHelp";

    public static class APInfo {
        public String apName = "AP_UNKNOWN";
        public int apType = APType.AP_UNKNOWN.value();
    }

    private enum APType {
        AP_UNKNOWN(0),
        AP_WIFI(1),
        AP_2G(2),
        AP_3G(3),
        AP_4G(4);
        
        private int value;

        private APType(int i) {
            this.value = 0;
            this.value = i;
        }

        public int value() {
            return this.value;
        }
    }

    private enum MobileCarrier {
        UNKNOWN,
        CHINA_MOBILE,
        CHINA_UNICOM,
        CHINA_TELECOM
    }

    protected static APInfo getAPInfo(Context context) {
        APInfo aPInfo = new APInfo();
        if (context == null) {
            QLog.e(TAG, 0, "getAPInfo initial context is null");
            return aPInfo;
        }
        APInfo mobileAPInfo;
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            switch (activeNetworkInfo.getType()) {
                case 0:
                    mobileAPInfo = getMobileAPInfo(context, activeNetworkInfo.getSubtype());
                    break;
                case 1:
                    aPInfo.apType = APType.AP_WIFI.value();
                    aPInfo.apName = "AP_WIFI";
                    mobileAPInfo = aPInfo;
                    break;
            }
        }
        mobileAPInfo = aPInfo;
        return mobileAPInfo;
    }

    private static APInfo getMobileAPInfo(Context context, int i) {
        MobileCarrier mobileCarrier;
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        MobileCarrier mobileCarrier2 = MobileCarrier.UNKNOWN;
        String subscriberId = telephonyManager.getSubscriberId();
        if (subscriberId == null) {
            QLog.e(TAG, 0, "getAPInfo IMSI is null");
            mobileCarrier = mobileCarrier2;
        } else if (subscriberId.startsWith("46000") || subscriberId.startsWith("46002") || subscriberId.startsWith("46007")) {
            mobileCarrier = MobileCarrier.CHINA_MOBILE;
        } else if (subscriberId.startsWith("46001") || subscriberId.startsWith("46006")) {
            mobileCarrier = MobileCarrier.CHINA_UNICOM;
        } else if (subscriberId.startsWith("46003") || subscriberId.startsWith("46005")) {
            mobileCarrier = MobileCarrier.CHINA_TELECOM;
        } else {
            mobileCarrier = mobileCarrier2;
        }
        APInfo aPInfo = new APInfo();
        switch (i) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
                aPInfo.apType = APType.AP_2G.value();
                if (MobileCarrier.CHINA_MOBILE != mobileCarrier) {
                    if (MobileCarrier.CHINA_UNICOM != mobileCarrier) {
                        if (MobileCarrier.CHINA_TELECOM == mobileCarrier) {
                            aPInfo.apName = "AP_CTNET";
                            break;
                        }
                    }
                    aPInfo.apName = "AP_UNINET";
                    break;
                }
                aPInfo.apName = "AP_CMNET";
                break;
                break;
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
                aPInfo.apType = APType.AP_3G.value();
                if (MobileCarrier.CHINA_MOBILE != mobileCarrier) {
                    if (MobileCarrier.CHINA_UNICOM != mobileCarrier) {
                        if (MobileCarrier.CHINA_TELECOM == mobileCarrier) {
                            aPInfo.apName = "AP_CTNET";
                            break;
                        }
                    }
                    aPInfo.apName = "AP_3GNET";
                    break;
                }
                aPInfo.apName = "AP_CM3G";
                break;
                break;
            case 13:
                aPInfo.apType = APType.AP_4G.value();
                if (MobileCarrier.CHINA_MOBILE != mobileCarrier) {
                    if (MobileCarrier.CHINA_UNICOM != mobileCarrier) {
                        if (MobileCarrier.CHINA_TELECOM == mobileCarrier) {
                            aPInfo.apName = "AP_CTLTE";
                            break;
                        }
                    }
                    aPInfo.apName = "AP_UNLTE";
                    break;
                }
                aPInfo.apName = "AP_CMLTE";
                break;
                break;
        }
        return aPInfo;
    }
}
