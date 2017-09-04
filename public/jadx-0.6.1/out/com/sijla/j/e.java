package com.sijla.j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class e {
    static final /* synthetic */ boolean a = (!e.class.desiredAssertionStatus());

    public static Method a(Class cls, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("get");
        stringBuilder.append(str.substring(0, 1).toUpperCase());
        stringBuilder.append(str.substring(1));
        try {
            return cls.getMethod(stringBuilder.toString(), new Class[0]);
        } catch (Exception e) {
            return null;
        }
    }

    public static Method b(Class cls, String str) {
        try {
            return cls.getMethod("set" + str.substring(0, 1).toUpperCase() + str.substring(1), new Class[]{cls.getDeclaredField(str).getType()});
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void a(Object obj, String str, Object obj2) {
        Method b = b(obj.getClass(), str);
        try {
            if (a || b != null) {
                b.invoke(obj, new Object[]{obj2});
                return;
            }
            throw new AssertionError();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e2) {
            b.a("invokeSet", str + "'s value not Long");
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
        }
    }

    public static Object a(Object obj, String str) {
        Method a = a(obj.getClass(), str);
        if (a == null) {
            System.out.println("InvokeSetGet: null==method");
        }
        try {
            return a.invoke(obj, new Object[0]);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
