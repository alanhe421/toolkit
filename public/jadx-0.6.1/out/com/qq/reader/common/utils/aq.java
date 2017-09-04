package com.qq.reader.common.utils;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.qq.reader.activity.MainActivity;
import com.qq.reader.activity.ReaderBaseActivity;
import com.qq.reader.module.bookstore.qweb.fragment.BaseFragment;
import java.lang.reflect.Field;

/* compiled from: ViewMapUtil */
public final class aq {
    public static void a(Object obj, View view) {
        Exception e;
        Class cls = obj.getClass();
        while (cls != null && !cls.equals(Activity.class) && !cls.equals(Fragment.class) && !cls.equals(Object.class) && !cls.equals(ReaderBaseActivity.class) && !cls.equals(BaseFragment.class) && !cls.equals(MainActivity.class)) {
            for (Field field : cls.getDeclaredFields()) {
                int a;
                ar arVar = (ar) field.getAnnotation(ar.class);
                if (arVar != null) {
                    try {
                        a = arVar.a();
                        try {
                            field.setAccessible(true);
                            View findViewById = view.findViewById(a);
                            if (findViewById != null) {
                                field.set(obj, findViewById);
                            }
                        } catch (Exception e2) {
                            e = e2;
                        }
                    } catch (Exception e3) {
                        e = e3;
                        a = 0;
                    }
                }
            }
            cls = cls.getSuperclass();
        }
        return;
        System.err.println(String.format("view map error = %h, clazz:%s, field:%s", new Object[]{Integer.valueOf(a), cls.getSimpleName(), field.getName()}));
        e.printStackTrace();
        throw new RuntimeException();
    }

    public static View a(Object obj, LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View inflate = layoutInflater.inflate(a(obj.getClass()).a(), viewGroup, false);
        a(obj, inflate);
        return inflate;
    }

    static ar a(Class<?> cls) {
        ar arVar = null;
        while (true) {
            if (Activity.class.equals(cls) && Fragment.class.equals(cls) && BaseFragment.class.equals(cls) && ReaderBaseActivity.class.equals(cls) && MainActivity.class.equals(cls)) {
                break;
            }
            arVar = (ar) cls.getAnnotation(ar.class);
            if (arVar != null) {
                break;
            }
            cls = cls.getSuperclass();
        }
        return arVar;
    }
}
