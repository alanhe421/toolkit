package com.tencent.upload.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.util.Log;
import java.io.File;

public final class b {
    private static Context a;
    private static boolean b = false;

    public static final Context a() {
        if (a != null) {
            return a;
        }
        throw new a("Global's Context is NULL, have your Application in manifest subclasses BaseApplication or Call 'Global.init(this)' in your own Application ? ");
    }

    public static final SharedPreferences a(String str, int i) {
        return a().getSharedPreferences(str, 0);
    }

    public static final void a(Context context) {
        a = context;
        try {
            boolean z = (context.getApplicationInfo().flags & 2) != 0;
            b = z;
            if (z) {
                Log.w("Wns.Global.Runtime", "DEBUG is ON");
            }
        } catch (Exception e) {
            b = false;
        }
    }

    public static final AssetManager b() {
        return a().getAssets();
    }

    public static final PackageManager c() {
        return a().getPackageManager();
    }

    public static final String d() {
        return a().getPackageName();
    }

    public static final File e() {
        return a().getFilesDir();
    }

    public static final File f() {
        return a().getCacheDir();
    }
}
