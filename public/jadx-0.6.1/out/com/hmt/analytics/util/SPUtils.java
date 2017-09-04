package com.hmt.analytics.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

public class SPUtils {

    private static class SharedPreferencesCompat {
        private static final Method a = findApplyMethod();

        private SharedPreferencesCompat() {
        }

        private static Method findApplyMethod() {
            try {
                return Editor.class.getMethod("apply", new Class[0]);
            } catch (NoSuchMethodException e) {
                return null;
            }
        }

        public static void apply(Editor editor) {
            try {
                if (a != null) {
                    a.invoke(editor, new Object[0]);
                    return;
                }
            } catch (IllegalArgumentException e) {
            } catch (IllegalAccessException e2) {
            } catch (InvocationTargetException e3) {
            }
            editor.commit();
        }
    }

    public static void put(Context context, String str, String str2, Object obj) {
        put(getSpEdit(context, str), str2, obj);
    }

    public static void put(Context context, String str, Object obj) {
        put(context, "HMTAGENT_INFO", str, obj);
    }

    private static Editor getSpEdit(Context context, String str) {
        return getSp(context, str).edit();
    }

    private static SharedPreferences getSp(Context context, String str) {
        return context.getSharedPreferences(str, 0);
    }

    @SuppressLint({"NewApi"})
    private static void put(Editor editor, String str, Object obj) {
        if (obj instanceof String) {
            editor.putString(str, (String) obj);
        } else if (obj instanceof Integer) {
            editor.putInt(str, ((Integer) obj).intValue());
        } else if (obj instanceof Boolean) {
            editor.putBoolean(str, ((Boolean) obj).booleanValue());
        } else if (obj instanceof Float) {
            editor.putFloat(str, ((Float) obj).floatValue());
        } else if (obj instanceof Long) {
            editor.putLong(str, ((Long) obj).longValue());
        } else if (obj instanceof Set) {
            editor.putStringSet(str, (Set) obj);
        } else if (obj instanceof Double) {
            editor.putString(str, obj);
        } else {
            editor.putString(str, obj.toString());
        }
        SharedPreferencesCompat.apply(editor);
    }

    public static Object get(Context context, String str, Object obj) {
        return get(context, "HMTAGENT_INFO", str, obj);
    }

    public static Object get(Context context, String str, String str2, Object obj) {
        return get(getSp(context, str), str2, obj);
    }

    @SuppressLint({"NewApi"})
    private static Object get(SharedPreferences sharedPreferences, String str, Object obj) {
        if (obj instanceof String) {
            return sharedPreferences.getString(str, (String) obj);
        }
        if (obj instanceof Integer) {
            return Integer.valueOf(sharedPreferences.getInt(str, ((Integer) obj).intValue()));
        }
        if (obj instanceof Boolean) {
            return Boolean.valueOf(sharedPreferences.getBoolean(str, ((Boolean) obj).booleanValue()));
        }
        if (obj instanceof Float) {
            return Float.valueOf(sharedPreferences.getFloat(str, ((Float) obj).floatValue()));
        }
        if (obj instanceof Long) {
            return Long.valueOf(sharedPreferences.getLong(str, ((Long) obj).longValue()));
        }
        if (obj instanceof Set) {
            return sharedPreferences.getStringSet(str, (Set) obj);
        }
        return null;
    }

    public static void remove(Context context, String str) {
        remove(context, "HMTAGENT_INFO", str);
    }

    public static void remove(Context context, String str, String str2) {
        remove(getSpEdit(context, str), str2);
    }

    private static void remove(Editor editor, String str) {
        editor.remove(str);
        SharedPreferencesCompat.apply(editor);
    }

    public static void clear(Context context) {
        clear(getSpEdit(context, "HMTAGENT_INFO"));
    }

    public static void clear(Context context, String str) {
        clear(getSpEdit(context, str));
    }

    private static void clear(Editor editor) {
        editor.clear();
        SharedPreferencesCompat.apply(editor);
    }

    public static boolean contains(Context context, String str, String str2) {
        return contains(getSp(context, str), str2);
    }

    public static boolean contains(Context context, String str) {
        return contains(context, "HMTAGENT_INFO", str);
    }

    private static boolean contains(SharedPreferences sharedPreferences, String str) {
        return sharedPreferences.contains(str);
    }

    public static Map<String, ?> getAll(Context context) {
        return getSp(context, "HMTAGENT_INFO").getAll();
    }

    public static Map<String, ?> getAll(Context context, String str) {
        return getSp(context, str).getAll();
    }
}
