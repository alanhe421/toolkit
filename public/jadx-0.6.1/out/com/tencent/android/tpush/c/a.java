package com.tencent.android.tpush.c;

import android.content.Context;
import com.tencent.android.tpush.common.Constants;
import java.lang.reflect.InvocationTargetException;

/* compiled from: ProGuard */
public class a {
    public static boolean a() {
        try {
            Class.forName("com.tencent.android.tpush.otherpush.impl.OtherPushImpl");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static String b() {
        try {
            Class cls = Class.forName("com.tencent.android.tpush.otherpush.impl.OtherPushImpl");
            return (String) cls.getMethod("getPushInfo", new Class[0]).invoke(cls, new Object[0]);
        } catch (Exception e) {
            com.tencent.android.tpush.a.a.i(Constants.OTHER_PUSH_TAG, "getPushInfo Error, are you import otherpush package? " + e);
            return null;
        }
    }

    public static void a(Context context) {
        try {
            Class cls = Class.forName("com.tencent.android.tpush.otherpush.impl.OtherPushImpl");
            cls.getMethod("registerPush", new Class[]{Context.class}).invoke(cls, new Object[]{context});
        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            com.tencent.android.tpush.a.a.i(Constants.OTHER_PUSH_TAG, "registerPush Error for InvocationTargetException: " + cause.getMessage());
            cause.printStackTrace();
        } catch (Exception e2) {
            com.tencent.android.tpush.a.a.i(Constants.OTHER_PUSH_TAG, "registerPush Error, are you import otherpush package? " + e2);
        }
    }

    public static void b(Context context) {
        try {
            Class cls = Class.forName("com.tencent.android.tpush.otherpush.impl.OtherPushImpl");
            cls.getMethod("unregisterPush", new Class[]{Context.class}).invoke(cls, new Object[]{context});
        } catch (Exception e) {
            com.tencent.android.tpush.a.a.i(Constants.OTHER_PUSH_TAG, "unregisterPush Error, are you import otherpush package? " + e);
        }
    }

    public static String c(Context context) {
        try {
            Class cls = Class.forName("com.tencent.android.tpush.otherpush.impl.OtherPushImpl");
            return (String) cls.getMethod("getToken", new Class[]{Context.class}).invoke(cls, new Object[]{context});
        } catch (Exception e) {
            com.tencent.android.tpush.a.a.i(Constants.OTHER_PUSH_TAG, "getToken Error: " + e);
            return null;
        }
    }

    public static boolean d(Context context) {
        try {
            Class cls = Class.forName("com.tencent.android.tpush.otherpush.impl.OtherPushImpl");
            return ((Boolean) cls.getMethod("checkDevice", new Class[]{Context.class}).invoke(cls, new Object[]{context})).booleanValue();
        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            com.tencent.android.tpush.a.a.i(Constants.OTHER_PUSH_TAG, "checkDevice Error for InvocationTargetException: " + cause.getMessage());
            cause.printStackTrace();
            return false;
        } catch (Exception e2) {
            com.tencent.android.tpush.a.a.i(Constants.OTHER_PUSH_TAG, "checkDevice Error, are you import otherpush package? " + e2);
            return false;
        }
    }

    public static void a(Context context, String str) {
        try {
            Class cls = Class.forName("com.tencent.android.tpush.otherpush.impl.OtherPushImpl");
            cls.getMethod("setAppid", new Class[]{Context.class, String.class}).invoke(cls, new Object[]{context, str});
        } catch (Exception e) {
            com.tencent.android.tpush.a.a.i(Constants.OTHER_PUSH_TAG, "setAppid Error, are you import otherpush package? " + e);
        }
    }

    public static String e(Context context) {
        try {
            Class cls = Class.forName("com.tencent.android.tpush.otherpush.impl.OtherPushImpl");
            return (String) cls.getMethod("getAppid", new Class[]{Context.class}).invoke(cls, new Object[]{context});
        } catch (Exception e) {
            com.tencent.android.tpush.a.a.i(Constants.OTHER_PUSH_TAG, "getAppid Error, are you import otherpush package? " + e);
            return null;
        }
    }

    public static void b(Context context, String str) {
        try {
            Class cls = Class.forName("com.tencent.android.tpush.otherpush.impl.OtherPushImpl");
            cls.getMethod("setAppKey", new Class[]{Context.class, String.class}).invoke(cls, new Object[]{context, str});
        } catch (Exception e) {
            com.tencent.android.tpush.a.a.i(Constants.OTHER_PUSH_TAG, "setAppKey Error, are you import otherpush package? " + e);
        }
    }

    public static String f(Context context) {
        try {
            Class cls = Class.forName("com.tencent.android.tpush.otherpush.impl.OtherPushImpl");
            return (String) cls.getMethod("getAppKey", new Class[]{Context.class}).invoke(cls, new Object[]{context});
        } catch (Exception e) {
            com.tencent.android.tpush.a.a.i(Constants.OTHER_PUSH_TAG, "getAppKey Error, are you import otherpush package? " + e);
            return null;
        }
    }
}
