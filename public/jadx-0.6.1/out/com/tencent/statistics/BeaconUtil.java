package com.tencent.statistics;

import android.content.Context;
import com.tencent.TIMManager;
import com.tencent.imsdk.QLog;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class BeaconUtil {
    static final String TAG = "statistics.BeaconUtil";
    private static String appKey = "0S2007DKQC1FC53C";
    private static String appVersion = ("imsdkV" + TIMManager.getInstance().getVersion());
    private static boolean beaconFuncEnable = false;
    private static Method beaconOnUserAction = null;
    private static boolean enable = false;
    private static boolean isTest = false;

    private BeaconUtil() {
    }

    public static void Init(Context context) {
        if (enable) {
            try {
                Class cls = Class.forName("com.tencent.beaconimsdk.event.UserAction");
                beaconFuncEnable = true;
                cls.getMethod("setAppkey", new Class[]{String.class}).invoke(null, new Object[]{appKey});
                cls.getMethod("setAPPVersion", new Class[]{String.class}).invoke(null, new Object[]{appVersion});
                cls.getMethod("initUserAction", new Class[]{Context.class}).invoke(null, new Object[]{context});
                if (isTest) {
                    cls.getMethod("setLogAble", new Class[]{Boolean.TYPE, Boolean.TYPE}).invoke(null, new Object[]{Boolean.valueOf(true), Boolean.valueOf(true)});
                }
            } catch (ClassNotFoundException e) {
                QLog.d(TAG, 1, "enable beacon failed,do not have jar");
            } catch (NoSuchMethodException e2) {
                QLog.d(TAG, 1, "enable beacon failed|NoSuchMethodException");
            } catch (InvocationTargetException e3) {
                QLog.d(TAG, 1, "enable beacon failed|InvocationTargetException");
            } catch (IllegalAccessException e4) {
                QLog.d(TAG, 1, "enable beacon failed|IllegalAccessException");
            } catch (Exception e5) {
                QLog.d(TAG, 1, "enable beacon failed|" + e5.toString());
            }
        }
    }

    public static void onEvent(String str, boolean z, long j, long j2, Map<String, String> map, boolean z2) {
        if (enable && beaconFuncEnable) {
            try {
                if (beaconOnUserAction == null) {
                    beaconOnUserAction = Class.forName("com.tencent.beaconimsdk.event.UserAction").getMethod("onUserAction", new Class[]{String.class, Boolean.TYPE, Long.TYPE, Long.TYPE, Map.class, Boolean.TYPE});
                }
                beaconOnUserAction.invoke(null, new Object[]{str, Boolean.valueOf(z), Long.valueOf(j), Long.valueOf(j2), map, Boolean.valueOf(z2)});
            } catch (ClassNotFoundException e) {
                QLog.d(TAG, 1, "beancon onUserAction failed,do not have jar");
            } catch (NoSuchMethodException e2) {
                QLog.d(TAG, 1, "beancon onUserAction failed|NoSuchMethodException");
            } catch (InvocationTargetException e3) {
                QLog.d(TAG, 1, "beancon onUserAction failed|InvocationTargetException");
            } catch (IllegalAccessException e4) {
                QLog.d(TAG, 1, "beancon onUserAction failed|IllegalAccessException");
            } catch (Exception e5) {
                QLog.d(TAG, 1, "beancon onUserAction failed|" + e5.toString());
            }
        }
    }

    public static void setEnable(boolean z) {
        enable = z;
    }
}
