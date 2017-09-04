package com.tencent.midas.comm;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class APBeanUtil {
    public static void copyProperties(Object obj, Object obj2) {
        try {
            copyPropertiesExclude(obj, obj2, null, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void copyPropertiesExclude(Object obj, Object obj2, String[] strArr, boolean z) {
        Method[] methods;
        Method[] methodArr;
        List asList = (strArr == null || strArr.length <= 0) ? null : Arrays.asList(strArr);
        Method[] methods2;
        if (z) {
            methods2 = obj.getClass().getMethods();
            methods = obj2.getClass().getMethods();
            methodArr = methods2;
        } else {
            methods2 = obj.getClass().getDeclaredMethods();
            methods = obj2.getClass().getDeclaredMethods();
            methodArr = methods2;
        }
        for (Method method : methodArr) {
            String name = method.getName();
            if (name.contains("get") && (asList == null || !asList.contains(name.substring(3).toLowerCase()))) {
                Method findMethodByName = findMethodByName(methods, "set" + name.substring(3));
                if (findMethodByName != null) {
                    Object invoke = method.invoke(obj, new Object[0]);
                    if (invoke != null && (!(invoke instanceof Collection) || ((Collection) invoke).size() > 0)) {
                        findMethodByName.invoke(obj2, new Object[]{invoke});
                    }
                }
            }
        }
    }

    public static void copyPropertiesInclude(Object obj, Object obj2, String[] strArr, boolean z) {
        if (strArr != null && strArr.length > 0) {
            Method[] methods;
            Method[] methodArr;
            List asList = Arrays.asList(strArr);
            Method[] methods2;
            if (z) {
                methods2 = obj.getClass().getMethods();
                methods = obj2.getClass().getMethods();
                methodArr = methods2;
            } else {
                methods2 = obj.getClass().getDeclaredMethods();
                methods = obj2.getClass().getDeclaredMethods();
                methodArr = methods2;
            }
            for (Method method : methodArr) {
                String name = method.getName();
                if (name.contains("get")) {
                    String substring = name.substring(3);
                    if (asList.contains(substring.substring(0, 1).toLowerCase() + substring.substring(1))) {
                        Method findMethodByName = findMethodByName(methods, "set" + name.substring(3));
                        if (findMethodByName != null) {
                            Object invoke = method.invoke(obj, new Object[0]);
                            if (invoke != null && (!(invoke instanceof Collection) || ((Collection) invoke).size() > 0)) {
                                findMethodByName.invoke(obj2, new Object[]{invoke});
                            }
                        }
                    }
                }
            }
        }
    }

    public static Method findMethodByName(Method[] methodArr, String str) {
        for (int i = 0; i < methodArr.length; i++) {
            if (methodArr[i].getName().equals(str)) {
                return methodArr[i];
            }
        }
        return null;
    }
}
