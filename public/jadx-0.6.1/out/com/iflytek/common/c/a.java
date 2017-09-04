package com.iflytek.common.c;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.view.Display;
import android.view.WindowManager;
import com.dynamicload.Lib.DLConstants;
import com.tencent.qalsdk.sdk.v;
import java.lang.reflect.Field;

public class a {
    private String a;
    private String b;
    private String c;
    private String d;
    private TelephonyManager e;

    public a(Context context) {
        this.e = (TelephonyManager) context.getSystemService("phone");
        a();
        this.d = a(context);
        String str = "";
        if (VERSION.SDK_INT > 7) {
            str = Build.HARDWARE;
        }
        this.c = "Android";
        this.b = a("MANUFACTURER") + DLConstants.DEPENDENCY_PACKAGE_DIV + a("MODEL") + DLConstants.DEPENDENCY_PACKAGE_DIV + a("PRODUCT") + "|ANDROID" + VERSION.RELEASE + DLConstants.DEPENDENCY_PACKAGE_DIV + this.d + DLConstants.DEPENDENCY_PACKAGE_DIV + str;
    }

    private String a(Context context) {
        int width;
        int height;
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        if (defaultDisplay.getOrientation() == 0) {
            width = defaultDisplay.getWidth();
            height = defaultDisplay.getHeight();
        } else {
            width = defaultDisplay.getHeight();
            height = defaultDisplay.getWidth();
        }
        return width + v.n + height;
    }

    private String a(String str) {
        try {
            Field field = Build.class.getField(str);
            Build build = new Build();
            if (field != null) {
                return field.get(build).toString();
            }
        } catch (Throwable e) {
            c.a("", "", e);
        }
        return "";
    }

    public String a() {
        try {
            if (this.a == null || this.a.length() <= 0) {
                this.a = this.e.getDeviceId();
                c.a("", "getIMEI:" + this.a);
            }
        } catch (Throwable e) {
            c.a("", "", e);
        }
        return this.a;
    }

    public String b() {
        return this.b;
    }
}
