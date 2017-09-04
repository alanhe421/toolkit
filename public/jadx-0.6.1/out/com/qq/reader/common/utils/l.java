package com.qq.reader.common.utils;

import android.content.Context;
import android.os.Build.VERSION;
import com.qq.reader.common.monitor.debug.c;
import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/* compiled from: DexUtils */
public class l {
    public static boolean a = false;

    public static boolean a(Context context, ClassLoader classLoader, String str) {
        Exception e;
        boolean z = true;
        if (str == null) {
            c.e("DexUtils", "injectSo ->>soDir null");
            return true;
        } else if (a) {
            return true;
        } else {
            File file = new File(str);
            if (!file.exists() || !file.isDirectory()) {
                return false;
            }
            file.setReadOnly();
            try {
                b(classLoader, file.getAbsolutePath());
                try {
                    a = true;
                    return true;
                } catch (Exception e2) {
                    e = e2;
                    e.printStackTrace();
                    return z;
                }
            } catch (Exception e3) {
                Exception exception = e3;
                z = false;
                e = exception;
                e.printStackTrace();
                return z;
            }
        }
    }

    private static void a(ClassLoader classLoader, String str) {
        try {
            Object a = a((Object) classLoader);
            ((ArrayList) ae.a(a, a.getClass(), "nativeLibraryDirectories")).add(0, new File(str));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private static void b(ClassLoader classLoader, String str) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException, ClassNotFoundException {
        Object newInstance;
        Object a = a(classLoader);
        if (VERSION.SDK_INT >= 23) {
            Constructor constructor = a[0].getClass().getConstructors()[0];
            constructor.setAccessible(true);
            Class[] parameterTypes = constructor.getParameterTypes();
            Object[] objArr = new Object[parameterTypes.length];
            for (int i = 0; i < parameterTypes.length; i++) {
                if (parameterTypes[i] == File.class) {
                    objArr[i] = new File(str);
                } else if (parameterTypes[i] == Boolean.TYPE) {
                    objArr[i] = Boolean.valueOf(true);
                }
            }
            newInstance = constructor.newInstance(objArr);
        } else {
            newInstance = new File(str);
        }
        Object newInstance2 = Array.newInstance(a[0].getClass(), 1);
        Array.set(newInstance2, 0, newInstance);
        newInstance = a(newInstance2, a);
        Object a2 = a((Object) classLoader);
        if (VERSION.SDK_INT >= 23) {
            ae.a(a2, a2.getClass(), "nativeLibraryPathElements", newInstance);
            a(classLoader, str);
            return;
        }
        ae.a(a2, a2.getClass(), "nativeLibraryDirectories", newInstance);
    }

    private static Object[] a(ClassLoader classLoader) throws IllegalArgumentException, NoSuchFieldException, IllegalAccessException, ClassNotFoundException {
        Object a = a((Object) classLoader);
        if (VERSION.SDK_INT >= 23) {
            return (Object[]) ae.a(a, a.getClass(), "nativeLibraryPathElements");
        }
        return (Object[]) ae.a(a, a.getClass(), "nativeLibraryDirectories");
    }

    private static Object a(Object obj) throws IllegalArgumentException, NoSuchFieldException, IllegalAccessException, ClassNotFoundException {
        return ae.a(obj, Class.forName("dalvik.system.BaseDexClassLoader"), "pathList");
    }

    private static Object a(Object obj, Object obj2) {
        Class componentType = obj.getClass().getComponentType();
        int length = Array.getLength(obj);
        int length2 = Array.getLength(obj2) + length;
        Object newInstance = Array.newInstance(componentType, length2);
        for (int i = 0; i < length2; i++) {
            if (i < length) {
                Array.set(newInstance, i, Array.get(obj, i));
            } else {
                Array.set(newInstance, i, Array.get(obj2, i - length));
            }
        }
        return newInstance;
    }
}
