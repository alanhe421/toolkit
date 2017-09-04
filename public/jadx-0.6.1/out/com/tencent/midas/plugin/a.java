package com.tencent.midas.plugin;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.text.TextUtils;
import android.view.ContextThemeWrapper;
import com.tencent.midas.comm.APLog;
import java.util.ArrayList;

class a extends ContextThemeWrapper {
    private AssetManager a;
    private Resources b;
    private Theme c;
    private int d;
    private ClassLoader e;

    public a(Context context, int i, String str, ClassLoader classLoader) {
        this(context, i, str, classLoader, null);
    }

    public a(Context context, int i, String str, ClassLoader classLoader, Resources resources) {
        super(context, i);
        this.a = null;
        this.b = null;
        this.c = null;
        this.e = classLoader;
        APLog.i("APPluginContext", "APPluginContext mClassLoader:" + this.e + " apkPath:" + str);
        if (resources != null) {
            this.a = resources.getAssets();
            APLog.i("APPluginContext", "APPluginActivity APPluginContext 1 mAsset:" + this.a);
            this.b = resources;
        } else {
            this.a = a(context, str);
            APLog.i("APPluginContext", "APPluginActivity APPluginContext 2 mAsset:" + this.a);
            this.b = a(context, this.a);
            APLog.i("APPluginContext", "APPluginActivity APPluginContext 2 mResources:" + this.b);
        }
        this.c = a(this.b);
    }

    private int a(String str) {
        int i;
        Throwable th;
        try {
            String substring = str.substring(0, str.indexOf(".R.") + 2);
            int lastIndexOf = str.lastIndexOf(".");
            String substring2 = str.substring(lastIndexOf + 1, str.length());
            String substring3 = str.substring(0, lastIndexOf);
            String str2 = substring + "$" + substring3.substring(substring3.lastIndexOf(".") + 1, substring3.length());
            i = Class.forName(str2).getDeclaredField(substring2).getInt(null);
            try {
                APLog.i("APPluginContext", "getInnderR rStrnig:" + substring3);
                APLog.i("APPluginContext", "getInnderR className:" + str2);
                APLog.i("APPluginContext", "getInnderR fieldName:" + substring2);
            } catch (Throwable th2) {
                th = th2;
                th.printStackTrace();
                return i;
            }
        } catch (Throwable th3) {
            Throwable th4 = th3;
            i = -1;
            th = th4;
            th.printStackTrace();
            return i;
        }
        return i;
    }

    private AssetManager a(Context context, String str) {
        AssetManager assetManager;
        Throwable th;
        int i = 0;
        try {
            int i2;
            assetManager = (AssetManager) AssetManager.class.newInstance();
            try {
                Class.forName("com.tencent.theme.SkinEngine").getMethod("getInstances", new Class[0]);
                i2 = 1;
            } catch (Exception e) {
                APLog.w("APPluginContext", " is not has com.tencent.theme.SkinEngine e:" + e.toString());
                i2 = 0;
            } catch (Throwable th2) {
                th = th2;
                th.printStackTrace();
                return assetManager;
            }
            if (i2 == 0) {
                try {
                    Class.forName("com.tencent.component.theme.SkinEngine").getMethod("getInstances", new Class[0]);
                    i2 = 1;
                } catch (Exception e2) {
                    APLog.w("APPluginContext", " is not has com.tencent.component.theme.SkinEngine e:" + e2.toString());
                    i2 = 0;
                }
            }
            if (i2 != 0) {
                ArrayList midasEmptyPaht = APPluginUtils.getMidasEmptyPaht(context);
                APLog.i("APPluginContext", "loadEmptyResAPK emptyList.size:" + midasEmptyPaht.size());
                while (i < midasEmptyPaht.size()) {
                    String str2 = (String) midasEmptyPaht.get(i);
                    APLog.i("APPluginContext", "loadEmptyResAPK emptyResFirstPath:" + str2);
                    if (!TextUtils.isEmpty(str2)) {
                        APLog.i("APPluginContext", "loadEmptyResAPK id:" + ((Integer) AssetManager.class.getDeclaredMethod("addAssetPath", new Class[]{String.class}).invoke(assetManager, new Object[]{str2})).intValue());
                    }
                    i++;
                }
            }
            AssetManager.class.getDeclaredMethod("addAssetPath", new Class[]{String.class}).invoke(assetManager, new Object[]{str});
        } catch (Throwable th3) {
            Throwable th4 = th3;
            assetManager = null;
            th = th4;
            th.printStackTrace();
            return assetManager;
        }
        return assetManager;
    }

    private Theme a(Resources resources) {
        Theme newTheme = resources.newTheme();
        this.d = a("com.android.internal.R.style.Theme");
        newTheme.applyStyle(this.d, true);
        return newTheme;
    }

    private Resources a(Context context, AssetManager assetManager) {
        return new Resources(assetManager, context.getResources().getDisplayMetrics(), context.getResources().getConfiguration());
    }

    public AssetManager getAssets() {
        return this.a;
    }

    public ClassLoader getClassLoader() {
        return this.e != null ? this.e : super.getClassLoader();
    }

    public Resources getResources() {
        return this.b;
    }

    public Theme getTheme() {
        return this.c;
    }
}
