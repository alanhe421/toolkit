package com.tencent.mid.util;

import android.content.Context;
import android.os.Build.VERSION;
import android.provider.Settings.System;
import java.lang.reflect.Method;

public class i {
    private static volatile i c = null;
    private int a = 10;
    private int b = 0;
    private Context d = null;
    private boolean e = false;

    private i(Context context) {
        this.d = context.getApplicationContext();
        try {
            this.e = Util.checkPermission(this.d, "android.permission.WRITE_SETTINGS");
            if (this.e && VERSION.SDK_INT >= 23) {
                Method declaredMethod = System.class.getDeclaredMethod("canWrite", new Class[]{Context.class});
                declaredMethod.setAccessible(true);
                this.e = ((Boolean) declaredMethod.invoke(null, new Object[]{this.d})).booleanValue();
            }
        } catch (Throwable th) {
            int i = this.b;
            this.b = i + 1;
            if (i < this.a) {
                th.printStackTrace();
            }
        }
    }

    public static i a(Context context) {
        if (c == null) {
            synchronized (i.class) {
                if (c == null) {
                    c = new i(context);
                }
            }
        }
        return c;
    }

    public String a(String str) {
        try {
            return System.getString(this.d.getContentResolver(), str);
        } catch (Throwable th) {
            int i = this.b;
            this.b = i + 1;
            if (i < this.a) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public boolean a(String str, int i) {
        if (this.e) {
            try {
                return System.putInt(this.d.getContentResolver(), str, i);
            } catch (Throwable th) {
                int i2 = this.b;
                this.b = i2 + 1;
                if (i2 < this.a) {
                    th.printStackTrace();
                }
            }
        }
        return false;
    }

    public boolean a(String str, String str2) {
        if (this.e) {
            try {
                return System.putString(this.d.getContentResolver(), str, str2);
            } catch (Throwable th) {
                int i = this.b;
                this.b = i + 1;
                if (i < this.a) {
                    th.printStackTrace();
                }
            }
        }
        return false;
    }

    public int b(String str, int i) {
        try {
            i = System.getInt(this.d.getContentResolver(), str, i);
        } catch (Throwable th) {
            int i2 = this.b;
            this.b = i2 + 1;
            if (i2 < this.a) {
                th.printStackTrace();
            }
        }
        return i;
    }
}
