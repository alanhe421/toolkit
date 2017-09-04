package com.tencent.kapalaiadapter;

import android.os.IBinder;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class ServiceManager {
    private static Method mAddServiceMethod;
    private static Method mCheckServiceMethod;
    private static Method mGetServiceMethod;
    private static Method mListServiceMethod;
    private static Class<?> mServiceManagerClass;

    static {
        try {
            mServiceManagerClass = Class.forName("android.os.ServiceManager");
            mGetServiceMethod = mServiceManagerClass.getDeclaredMethod("getService", new Class[]{String.class});
            mAddServiceMethod = mServiceManagerClass.getDeclaredMethod("addService", new Class[]{String.class, IBinder.class});
            mCheckServiceMethod = mServiceManagerClass.getDeclaredMethod("checkService", new Class[]{String.class});
            mListServiceMethod = mServiceManagerClass.getDeclaredMethod("listServices", new Class[0]);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SecurityException e2) {
            e2.printStackTrace();
        } catch (NoSuchMethodException e3) {
            e3.printStackTrace();
        }
    }

    private ServiceManager() {
    }

    private static Object invoke(Method method, Object... objArr) {
        Object obj = null;
        try {
            obj = method.invoke(null, objArr);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
        }
        return obj;
    }

    public static IBinder getService(String str) {
        return (IBinder) invoke(mGetServiceMethod, str);
    }

    public static void addService(String str, IBinder iBinder) {
        invoke(mAddServiceMethod, str, iBinder);
    }

    public static IBinder checkService(String str) {
        return (IBinder) invoke(mCheckServiceMethod, str);
    }

    public static String[] listService() {
        return (String[]) invoke(mListServiceMethod, new Object[0]);
    }
}
