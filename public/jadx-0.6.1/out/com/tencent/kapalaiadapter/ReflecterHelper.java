package com.tencent.kapalaiadapter;

import android.app.Dialog;
import android.content.Context;
import android.os.Message;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.tencent.av.utils.QLog;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public final class ReflecterHelper {
    public static Class<?> mCurrentClass;

    public static final boolean setClass(String str) {
        Class cls = null;
        try {
            cls = Class.forName(str);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        mCurrentClass = cls;
        if (mCurrentClass != null) {
            return true;
        }
        return false;
    }

    public static final int getStaticIntValue(String str, int i) {
        Field field = getField(str);
        if (field != null) {
            try {
                i = field.getInt(null);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
            }
        }
        return i;
    }

    public static final int getIntValue(Object obj, String str, int i) {
        setClass(obj.getClass().getName());
        Field field = getField(str);
        if (field != null) {
            try {
                i = field.getInt(obj);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
            }
        }
        return i;
    }

    private static final Field getField(String str) {
        Field declaredField;
        SecurityException e;
        NoSuchFieldException e2;
        try {
            declaredField = mCurrentClass.getDeclaredField(str);
            try {
                declaredField.setAccessible(true);
            } catch (SecurityException e3) {
                e = e3;
                e.printStackTrace();
                return declaredField;
            } catch (NoSuchFieldException e4) {
                e2 = e4;
                e2.printStackTrace();
                return declaredField;
            }
        } catch (SecurityException e5) {
            SecurityException securityException = e5;
            declaredField = null;
            e = securityException;
            e.printStackTrace();
            return declaredField;
        } catch (NoSuchFieldException e6) {
            NoSuchFieldException noSuchFieldException = e6;
            declaredField = null;
            e2 = noSuchFieldException;
            e2.printStackTrace();
            return declaredField;
        }
        return declaredField;
    }

    public static Object getStaticProperty(String str, String str2) {
        Object obj = null;
        setClass(str);
        Field field = getField(str2);
        if (field != null) {
            try {
                obj = field.get(null);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
            }
        }
        return obj;
    }

    public static void setStaticProperty(String str, String str2, Object obj) {
        setClass(str);
        Field field = getField(str2);
        if (field != null) {
            try {
                field.setAccessible(true);
                field.set(null, obj);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
    }

    public static Object newInstance(String str, Object[] objArr) throws Exception {
        return Class.forName(str).getConstructor(getArgsClasses(objArr)).newInstance(objArr);
    }

    public static Object newInstance(String str) throws Exception {
        return newInstance(str, (Object[]) null);
    }

    public static Object invokeMethod(Object obj, String str, Object[] objArr) throws Exception {
        return invokeMethod(obj, str, getArgsClasses(objArr), objArr);
    }

    public static Object invokeMethod(Object obj, String str) throws Exception {
        return invokeMethod(obj, str, null);
    }

    public static Object invokeMethod(Object obj, String str, Class<?>[] clsArr, Object[] objArr) throws Exception {
        Method method = obj.getClass().getMethod(str, clsArr);
        method.setAccessible(true);
        return method.invoke(obj, objArr);
    }

    public static Object getProperty(Object obj, String str) throws Exception {
        return obj.getClass().getField(str).get(obj);
    }

    public static void setProperty(Object obj, String str, Object obj2) throws Exception {
        Field field = obj.getClass().getField(str);
        field.setAccessible(true);
        field.set(obj, obj2);
    }

    public static Object newInstance(String str, Object[] objArr, Class<?>[] clsArr) throws Exception {
        return Class.forName(str).getConstructor(clsArr).newInstance(objArr);
    }

    public static Object invokeStaticMethod(String str, String str2, Object[] objArr, Class<?>[] clsArr) throws Exception {
        Class cls = Class.forName(str);
        return cls.getDeclaredMethod(str2, clsArr).invoke(cls, objArr);
    }

    public static Object invokeStaticMethod(String str, String str2, Object[] objArr) throws Exception {
        return invokeStaticMethod(str, str2, objArr, getArgsClasses(objArr));
    }

    public static Object invokeStaticMethod(String str, String str2) throws Exception {
        return invokeStaticMethod(str, str2, (Object[]) null);
    }

    private static Class<?>[] getArgsClasses(Object[] objArr) {
        Class<?>[] clsArr = (Class[]) null;
        if (objArr != null) {
            clsArr = new Class[objArr.length];
            int length = objArr.length;
            for (int i = 0; i < length; i++) {
                if (objArr[i] != null) {
                    clsArr[i] = objArr[i].getClass();
                } else {
                    clsArr[i] = String.class;
                }
                if (clsArr[i] == Integer.class) {
                    clsArr[i] = Integer.TYPE;
                } else if (clsArr[i] == Boolean.class) {
                    clsArr[i] = Boolean.TYPE;
                }
            }
        }
        return clsArr;
    }

    public static void fixInputMethodManagerLeak(Context context) {
        if (context != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService("input_method");
            if (inputMethodManager != null) {
                String[] strArr = new String[]{"mCurRootView", "mServedView", "mNextServedView"};
                for (String declaredField : strArr) {
                    try {
                        Field declaredField2 = inputMethodManager.getClass().getDeclaredField(declaredField);
                        if (!declaredField2.isAccessible()) {
                            declaredField2.setAccessible(true);
                        }
                        Object obj = declaredField2.get(inputMethodManager);
                        if (obj != null && (obj instanceof View)) {
                            View view = (View) obj;
                            if (view.getContext() == context) {
                                declaredField2.set(inputMethodManager, null);
                            } else if (QLog.isColorLevel()) {
                                QLog.d(ReflecterHelper.class.getSimpleName(), 0, "fixInputMethodManagerLeak break, context is not suitable, get_context=" + view.getContext() + " dest_context=" + context);
                                return;
                            } else {
                                return;
                            }
                        }
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            }
        }
    }

    public static void fixMesssageLeak(Dialog dialog) {
        if (dialog != null) {
            for (String declaredField : new String[]{"mDismissMessage", "mCancelMessage", "mShowMessage"}) {
                try {
                    Field declaredField2 = Dialog.class.getDeclaredField(declaredField);
                    if (declaredField2 != null) {
                        if (!declaredField2.isAccessible()) {
                            declaredField2.setAccessible(true);
                        }
                        Object obj = declaredField2.get(dialog);
                        if (obj instanceof Message) {
                            Message message = (Message) obj;
                            if (message.obj != null) {
                                message.obj = null;
                                message.what = 0;
                            }
                        }
                    }
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e2) {
                    e2.printStackTrace();
                } catch (IllegalAccessException e3) {
                    e3.printStackTrace();
                }
            }
        }
    }
}
