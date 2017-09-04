package com.dynamicload;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import com.dynamicload.Lib.DLConstants;
import com.dynamicload.internal.PackageConfig;
import com.dynamicload.internal.PackageConfigList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.utils.ab;

/* compiled from: DLConfigs */
public class a {
    public static boolean a = true;
    private static PackageConfigList b;

    public static PackageConfigList a() {
        if (b == null) {
            a(ReaderApplication.getApplicationImp());
            b(ReaderApplication.getApplicationImp());
        }
        return b;
    }

    private static void a(Context context) {
        String string = context.getSharedPreferences(DLConstants.PREFERENCE_NAME, 0).getString(DLConstants.PROPERTIES, null);
        if (string != null) {
            try {
                b = (PackageConfigList) ab.e(string);
            } catch (Exception e) {
                c.c("loadDLConfigFromLocal Exception e= " + e);
            }
        }
    }

    private static void b(Context context) {
        try {
            PackageConfigList packageConfigList = (PackageConfigList) new Gson().fromJson(ab.f(DLConstants.PROPERTIES), new TypeToken<PackageConfigList>() {
            }.getType());
            if (b == null || packageConfigList.version >= b.version) {
                b = packageConfigList;
                c(context);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void c(Context context) {
        if (b != null) {
            Editor edit = context.getSharedPreferences(DLConstants.PREFERENCE_NAME, 0).edit();
            edit.putString(DLConstants.PROPERTIES, ab.a(b));
            if (com.qq.reader.common.utils.a.a()) {
                edit.apply();
            } else {
                edit.commit();
            }
        }
    }

    public static String a(String str) {
        if (b != null) {
            for (PackageConfig packageConfig : b.configPlugins.values()) {
                if (packageConfig.packageName.equals(str)) {
                    return packageConfig.apkName;
                }
            }
        }
        return null;
    }
}
