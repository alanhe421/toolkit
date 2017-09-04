package com.qq.reader.widget.swipBackView;

import android.app.Activity;
import android.app.ActivityOptions;
import android.os.Build.VERSION;
import com.qq.reader.common.monitor.debug.c;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/* compiled from: Utils */
public class d {
    public static void a(Activity activity) {
        try {
            Method declaredMethod = Activity.class.getDeclaredMethod("convertFromTranslucent", new Class[0]);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(activity, new Object[0]);
        } catch (Throwable th) {
        }
    }

    public static void a(Activity activity, c cVar) {
        c.e("SwipeBackUtils", "convertActivityToTranslucent");
        try {
            for (Class cls : Activity.class.getDeclaredClasses()) {
                if (cls.getSimpleName().contains("TranslucentConversionListener")) {
                    break;
                }
            }
            Class cls2 = null;
            InvocationHandler bVar = new b();
            bVar.a(cVar);
            Object newProxyInstance = Proxy.newProxyInstance(cls2.getClassLoader(), new Class[]{cls2}, bVar);
            Method declaredMethod;
            if (VERSION.SDK_INT >= 21) {
                declaredMethod = Activity.class.getDeclaredMethod("convertToTranslucent", new Class[]{cls2, ActivityOptions.class});
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(activity, new Object[]{newProxyInstance, null});
                return;
            }
            declaredMethod = Activity.class.getDeclaredMethod("convertToTranslucent", new Class[]{cls2});
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(activity, new Object[]{newProxyInstance});
        } catch (Throwable th) {
        }
    }
}
