package com.pay.tool;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import com.tencent.midas.api.APMidasPayAPI;
import com.tencent.midas.api.APMidasResponse;
import com.tencent.midas.comm.APLog;
import com.tencent.midas.control.APMidasPayHelper;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Stack;
import java.util.TreeMap;

public class APMidasCommMethod {
    private static Stack<Activity> a = null;

    public static String MaptoString(HashMap<String, String> hashMap) {
        StringBuffer stringBuffer = new StringBuffer();
        for (Entry entry : hashMap.entrySet()) {
            stringBuffer.append((String) entry.getKey());
            stringBuffer.append("=");
            stringBuffer.append((String) entry.getValue());
            stringBuffer.append("&");
        }
        if (stringBuffer.length() > 0) {
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        }
        return stringBuffer.toString();
    }

    public static String MaptoString(HashMap<String, String> hashMap, String str) {
        StringBuffer stringBuffer = new StringBuffer();
        for (Entry value : hashMap.entrySet()) {
            stringBuffer.append((String) value.getValue());
            stringBuffer.append(str);
        }
        if (stringBuffer.length() > 0) {
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        }
        return stringBuffer.toString();
    }

    public static String dealString(String str) {
        String rawString = rawString(str);
        return rawString.length() <= 3 ? str : (rawString.length() <= 3 || rawString.length() > 6) ? (rawString.length() <= 6 || rawString.length() > 9) ? (rawString.length() <= 9 || rawString.length() > 12) ? str : str.substring(6, 9) + " " + str.substring(10, str.length()) : str.substring(3, 6) + " " + str.substring(7, str.length()) : str.substring(0, 3) + " " + str.substring(4, str.length());
    }

    public static int dip2px(Context context, float f) {
        return (int) ((context.getResources().getDisplayMetrics().density * f) + 0.5f);
    }

    public static String fenToYuan(String str, int i) {
        String str2 = "";
        DecimalFormat decimalFormat = (DecimalFormat) DecimalFormat.getInstance();
        if (i == 0) {
            decimalFormat.applyPattern("0");
        } else if (i == 1) {
            decimalFormat.applyPattern("0.0");
        } else if (i == 2) {
            decimalFormat.applyPattern("0.00");
        }
        try {
            return decimalFormat.format((double) (Float.valueOf(str).floatValue() / 100.0f));
        } catch (Exception e) {
            return str2;
        }
    }

    public static int getAnimId(Context context, String str) {
        return context.getApplicationContext().getResources().getIdentifier(str, "anim", context.getApplicationContext().getPackageName());
    }

    public static String getApplicationPackageName() {
        String str = "";
        try {
            Context context = APMidasPayAPI.fromContext;
            if (context == null) {
                return str;
            }
            PackageManager packageManager = context.getPackageManager();
            return packageManager != null ? packageManager.getPackageInfo(context.getPackageName(), 0).packageName : str;
        } catch (Exception e) {
            APLog.i("APMidasCommMethod", "getApplicationPackageName error:" + e.toString());
            return str;
        }
    }

    public static String getApplicationVersion() {
        String str = "";
        try {
            return APMidasPayAPI.fromContext.getPackageManager().getPackageInfo(APMidasPayAPI.fromContext.getPackageName(), 0).versionName;
        } catch (Exception e) {
            APLog.i("APMidasCommMethod", "getApplicationVersion error:" + e.toString());
            return str;
        }
    }

    public static int getColorId(Context context, String str) {
        return context.getResources().getColor(context.getResources().getIdentifier(str, "color", context.getPackageName()));
    }

    public static Drawable getDrawable(Context context, String str) {
        return context.getResources().getDrawable(context.getResources().getIdentifier(str, "drawable", context.getPackageName()));
    }

    public static int getDrawableId(Context context, String str) {
        return context.getResources().getIdentifier(str, "drawable", context.getPackageName());
    }

    public static int getId(Context context, String str) {
        return context.getResources().getIdentifier(str, "id", context.getPackageName());
    }

    public static int getLayoutId(Context context, String str) {
        return context.getResources().getIdentifier(str, "layout", context.getPackageName());
    }

    public static String getStringId(Context context, String str) {
        return context.getResources().getString(context.getResources().getIdentifier(str, "string", context.getPackageName()));
    }

    public static int getStyleId(Context context, String str) {
        return context.getResources().getIdentifier(str, "style", context.getPackageName());
    }

    public static void payErrorCallback(int i, String str) {
        if (APMidasPayHelper.midasCallBack != null) {
            APMidasResponse aPMidasResponse = new APMidasResponse();
            aPMidasResponse.resultCode = i;
            aPMidasResponse.resultMsg = str;
            APMidasPayHelper.midasCallBack.MidasPayCallBack(aPMidasResponse);
        }
    }

    public static void popActivity() {
        APLog.i("jar popActivity", "1");
        try {
            if (a != null) {
                for (int i = 0; i < a.size(); i++) {
                    if (a.get(i) != null) {
                        ((Activity) a.get(i)).finish();
                    }
                }
                releaseActivityStack();
            }
        } catch (Exception e) {
            APLog.i("jar popActivity ex", e.toString());
        }
    }

    public static void pushActivity(Activity activity) {
        if (a == null) {
            a = new Stack();
        }
        a.push(activity);
    }

    public static int px2dip(Context context, float f) {
        return (int) ((f / context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static String rawString(String str) {
        return str.replace(" ", "");
    }

    public static void releaseActivityStack() {
        if (a != null) {
            a.clear();
        }
        a = null;
    }

    public static void transformStrToList(String str, List<String> list) {
        int indexOf = str.indexOf("[");
        int indexOf2 = str.indexOf("]");
        list.clear();
        if (indexOf != -1 && indexOf2 != -1 && indexOf2 > indexOf) {
            String substring = str.substring(indexOf + 1, indexOf2);
            if (substring.length() != 0) {
                for (Object add : substring.split(",")) {
                    list.add(add);
                }
            }
        }
    }

    public static void transformStrToMap(String str, TreeMap<String, String> treeMap) {
        int indexOf = str.indexOf("[");
        int indexOf2 = str.indexOf("]");
        if (indexOf != -1 && indexOf2 != -1 && indexOf2 > indexOf) {
            String substring = str.substring(indexOf + 1, indexOf2);
            if (substring.length() == 0) {
                treeMap.clear();
                return;
            }
            String[] split = substring.split(",");
            int length = split.length;
            if (length > 0 && length % 2 == 0) {
                treeMap.clear();
                for (indexOf = 0; indexOf < length / 2; indexOf++) {
                    treeMap.put(split[indexOf * 2], split[(indexOf * 2) + 1]);
                }
            }
        }
    }

    public static void transformStrToMpInfoList(String str, List<String> list, List<String> list2) {
        int indexOf = str.indexOf("[");
        int indexOf2 = str.indexOf("]");
        if (indexOf != -1 && indexOf2 != -1 && indexOf2 > indexOf) {
            String substring = str.substring(indexOf + 1, indexOf2);
            if (substring.length() == 0) {
                list.clear();
                list2.clear();
                return;
            }
            String[] split = substring.split(",");
            int length = split.length;
            if (length > 0 && length % 2 == 0) {
                list.clear();
                list2.clear();
                for (indexOf = 0; indexOf < length / 2; indexOf++) {
                    Object obj = split[indexOf * 2];
                    Object obj2 = split[(indexOf * 2) + 1];
                    list.add(obj);
                    list2.add(obj2);
                }
            }
        }
    }
}
