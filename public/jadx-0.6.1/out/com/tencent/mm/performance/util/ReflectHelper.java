package com.tencent.mm.performance.util;

import com.qq.reader.common.monitor.f;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectHelper {
    public static Object getField(Class<?> cls, String str, Object obj) {
        try {
            Field declaredField = cls.getDeclaredField(str);
            declaredField.setAccessible(true);
            return declaredField.get(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object getField(String str, String str2, Object obj) {
        try {
            return getField(Class.forName(str), str2, obj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void setField(Class<?> cls, String str, Object obj, Object obj2) {
        try {
            Field declaredField = cls.getDeclaredField(str);
            declaredField.setAccessible(true);
            declaredField.set(obj2, obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setField(String str, String str2, Object obj, Object obj2) {
        try {
            setField(Class.forName(str), str2, obj, obj2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object invokeMethod(Class<?> cls, String str, Object obj, Class<?>[] clsArr, Object[] objArr) {
        try {
            Method declaredMethod = cls.getDeclaredMethod(str, clsArr);
            declaredMethod.setAccessible(true);
            return declaredMethod.invoke(obj, objArr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object invokeMethod(String str, String str2, Object obj, Class<?>[] clsArr, Object[] objArr) {
        try {
            return invokeMethod(Class.forName(str), str2, obj, (Class[]) clsArr, objArr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static long getInstanceCount(Class<?> cls) {
        long longValue;
        long currentTimeMillis = System.currentTimeMillis();
        try {
            longValue = ((Long) Class.forName("dalvik.system.VMDebug").getMethod("countInstancesOfClass", new Class[]{Class.class, Boolean.TYPE}).invoke(null, new Object[]{cls, Boolean.valueOf(false)})).longValue();
        } catch (Exception e) {
            e.printStackTrace();
            longValue = 0;
        }
        f.a("fuck", "use time===" + (System.currentTimeMillis() - currentTimeMillis));
        return longValue;
    }
}
