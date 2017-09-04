package com.tencent.av.utils;

import android.content.Context;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import com.dynamicload.Lib.DLConstants;
import com.tencent.kapalaiadapter.ReflecterHelper;

public class PhoneStatusTools {
    static final String TAG = "PhoneStatusTools";

    public static boolean isRingerSilent(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService("audio");
        return audioManager != null && audioManager.getRingerMode() == 0;
    }

    public static boolean isRingerVibrate(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService("audio");
        return audioManager != null && audioManager.getRingerMode() == 1;
    }

    public static boolean isRingerNormal(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService("audio");
        return audioManager != null && audioManager.getRingerMode() == 2;
    }

    public static boolean isRingEqualsZero(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService("audio");
        return audioManager != null && audioManager.getStreamVolume(2) == 0;
    }

    public static void listen(Context context, PhoneStateListener phoneStateListener, int i) {
        Object obj;
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager != null) {
            try {
                ReflecterHelper.invokeMethod(telephonyManager, "listen", new Class[]{PhoneStateListener.class, Integer.TYPE}, new Object[]{phoneStateListener, Integer.valueOf(i)});
                ReflecterHelper.invokeMethod(telephonyManager, "listenGemini", new Class[]{Integer.TYPE, PhoneStateListener.class, Integer.TYPE}, new Object[]{Integer.valueOf(0), phoneStateListener, Integer.valueOf(i)});
                ReflecterHelper.invokeMethod(telephonyManager, "listenGemini", new Class[]{Integer.TYPE, PhoneStateListener.class, Integer.TYPE}, new Object[]{Integer.valueOf(1), phoneStateListener, Integer.valueOf(i)});
            } catch (Exception e) {
            }
        }
        try {
            obj = (TelephonyManager) context.getSystemService("phone2");
        } catch (Exception e2) {
            obj = null;
        }
        if (obj != null) {
            try {
                ReflecterHelper.invokeMethod(obj, "listen", new Class[]{PhoneStateListener.class, Integer.TYPE}, new Object[]{phoneStateListener, Integer.valueOf(i)});
                ReflecterHelper.invokeMethod(obj, "listenGemini", new Class[]{Integer.TYPE, PhoneStateListener.class, Integer.TYPE}, new Object[]{Integer.valueOf(0), phoneStateListener, Integer.valueOf(i)});
                ReflecterHelper.invokeMethod(obj, "listenGemini", new Class[]{Integer.TYPE, PhoneStateListener.class, Integer.TYPE}, new Object[]{Integer.valueOf(1), phoneStateListener, Integer.valueOf(i)});
            } catch (Exception e3) {
            }
        }
        try {
            ReflecterHelper.invokeMethod(ReflecterHelper.invokeStaticMethod("android.telephony.MSimTelephonyManager", "getDefault", null, null), "listen", new Class[]{PhoneStateListener.class, Integer.TYPE}, new Object[]{phoneStateListener, Integer.valueOf(i)});
        } catch (Exception e4) {
        }
    }

    public static int getNetWorkType(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            String typeName = activeNetworkInfo.getTypeName();
            if (typeName.equalsIgnoreCase("WIFI")) {
                return 2;
            }
            if (typeName.equalsIgnoreCase("MOBILE")) {
                NetworkInfo networkInfo = connectivityManager.getNetworkInfo(0);
                if (networkInfo != null) {
                    switch (networkInfo.getType()) {
                        case 0:
                            switch (networkInfo.getSubtype()) {
                                case 1:
                                case 2:
                                case 4:
                                case 7:
                                case 11:
                                    return 4;
                                case 3:
                                case 5:
                                case 6:
                                case 8:
                                case 9:
                                case 10:
                                case 12:
                                case 14:
                                case 15:
                                    return 3;
                                case 13:
                                    return 5;
                                default:
                                    return 0;
                            }
                    }
                }
            }
        }
        return 0;
    }

    public static boolean isCalling(Context context) {
        if (context == null) {
            QLog.d(TAG, 0, "context is null");
            return false;
        }
        boolean z;
        TelephonyManager telephonyManager;
        TelephonyManager telephonyManager2 = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager2 != null) {
            if (telephonyManager2.getCallState() != 0) {
                z = true;
            } else {
                try {
                    if (((Integer) ReflecterHelper.invokeMethod(telephonyManager2, "getCallStateGemini", new Class[]{Integer.TYPE}, new Object[]{Integer.valueOf(0)})).intValue() != 0) {
                        z = true;
                    } else {
                        if (((Integer) ReflecterHelper.invokeMethod(telephonyManager2, "getCallStateGemini", new Class[]{Integer.TYPE}, new Object[]{Integer.valueOf(1)})).intValue() != 0) {
                            z = true;
                        }
                    }
                } catch (Exception e) {
                    if (QLog.isColorLevel()) {
                        QLog.d(TAG, 0, "1 isCalling Exception", e);
                    }
                }
            }
            if (QLog.isColorLevel()) {
                QLog.d(TAG, 0, "isCalling: " + z);
            }
            return z;
        }
        try {
            telephonyManager = (TelephonyManager) context.getSystemService("phone2");
        } catch (Exception e2) {
            telephonyManager = null;
        }
        if (telephonyManager != null) {
            if (telephonyManager.getCallState() != 0) {
                z = true;
            } else {
                try {
                    if (((Integer) ReflecterHelper.invokeMethod(telephonyManager, "getCallStateGemini", new Object[]{Integer.valueOf(0)})).intValue() != 0) {
                        z = true;
                    } else {
                        if (((Integer) ReflecterHelper.invokeMethod(telephonyManager, "getCallStateGemini", new Object[]{Integer.valueOf(1)})).intValue() != 0) {
                            z = true;
                        }
                    }
                } catch (Exception e3) {
                    if (QLog.isColorLevel()) {
                        QLog.d(TAG, 0, "2 isCalling Exception", e3);
                    }
                }
            }
            if (QLog.isColorLevel()) {
                QLog.d(TAG, 0, "isCalling: " + z);
            }
            return z;
        }
        try {
            Object invokeStaticMethod = ReflecterHelper.invokeStaticMethod("android.telephony.MSimTelephonyManager", "getDefault", null, null);
            if (invokeStaticMethod != null) {
                if (((Integer) ReflecterHelper.invokeMethod(invokeStaticMethod, "getCallState", new Object[]{Integer.valueOf(0)})).intValue() != 0) {
                    z = true;
                } else {
                    if (((Integer) ReflecterHelper.invokeMethod(invokeStaticMethod, "getCallState", new Object[]{Integer.valueOf(1)})).intValue() != 0) {
                        z = true;
                    }
                }
                if (QLog.isColorLevel()) {
                    QLog.d(TAG, 0, "isCalling: " + z);
                }
                return z;
            }
            z = false;
        } catch (Exception e4) {
            z = false;
        }
        if (QLog.isColorLevel()) {
            QLog.d(TAG, 0, "isCalling: " + z);
        }
        return z;
    }

    public static boolean isWifiEnv(Context context) {
        NetworkInfo netWorkInfo = getNetWorkInfo(context);
        if (netWorkInfo == null) {
            return false;
        }
        if (netWorkInfo.getType() == 1) {
            return true;
        }
        return false;
    }

    public static NetworkInfo getNetWorkInfo(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        return connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
    }

    public static boolean isHardCodePhoneDevice() {
        String str = Build.MODEL;
        String str2 = Build.MANUFACTURER;
        if ((str2.equalsIgnoreCase(DLConstants.BRAND_SAMSUNG) && str.equalsIgnoreCase("SM-T230")) || str2.equalsIgnoreCase("SF101")) {
            return true;
        }
        if (str2.equalsIgnoreCase("htc") && str.equalsIgnoreCase("Nexus 9")) {
            return true;
        }
        return false;
    }

    public static boolean isHardCodeTabletDevice() {
        return false;
    }

    public static boolean isTablet(Context context) {
        if (isHardCodePhoneDevice()) {
            return false;
        }
        if (isHardCodeTabletDevice()) {
            return true;
        }
        double sqrt;
        boolean z;
        double d = 0.0d;
        try {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            float f = ((float) displayMetrics.widthPixels) / displayMetrics.xdpi;
            sqrt = Math.sqrt(Math.pow((double) (((float) displayMetrics.heightPixels) / displayMetrics.ydpi), 2.0d) + Math.pow((double) f, 2.0d));
        } catch (Throwable th) {
            sqrt = d;
        }
        try {
            if (((TelephonyManager) context.getSystemService("phone")).getPhoneType() == 0) {
                z = false;
                if (QLog.isColorLevel()) {
                    QLog.d(TAG, 0, "device size : " + sqrt + ", hasTelephone : " + z);
                }
                if (sqrt <= 6.5d && !z) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        z = true;
        if (QLog.isColorLevel()) {
            QLog.d(TAG, 0, "device size : " + sqrt + ", hasTelephone : " + z);
        }
        return sqrt <= 6.5d ? false : false;
    }
}
