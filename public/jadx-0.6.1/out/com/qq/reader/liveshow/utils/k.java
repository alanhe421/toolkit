package com.qq.reader.liveshow.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.FrameLayout;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;

/* compiled from: ScreenModeUtils */
public class k {
    private static String a = "EmotionUI_3.1";
    private static boolean b = false;
    private static String c = "";

    public static void a(Activity activity) {
        Window window;
        if (VERSION.SDK_INT >= 21 && !a()) {
            window = activity.getWindow();
            window.clearFlags(SigType.WLOGIN_QRPUSH);
            window.getDecorView().setSystemUiVisibility(1024);
            window.addFlags(Integer.MIN_VALUE);
        } else if (VERSION.SDK_INT >= 19) {
            window = activity.getWindow();
            window.clearFlags(Integer.MIN_VALUE);
            window.addFlags(SigType.WLOGIN_QRPUSH);
        }
        a(activity, 0);
    }

    public static boolean a() {
        if (!b) {
            b = true;
            c = p.a("ro.build.version.emui");
        }
        if (a.equals(c)) {
            return true;
        }
        return false;
    }

    public static void a(Activity activity, int i) {
        if (VERSION.SDK_INT >= 21) {
            Window window = activity.getWindow();
            try {
                Class.forName("android.view.Window").getDeclaredMethod("setStatusBarColor", new Class[]{Integer.TYPE}).invoke(window, new Object[]{Integer.valueOf(i)});
            } catch (Exception e) {
                e.printStackTrace();
                window.clearFlags(Integer.MIN_VALUE);
                window.addFlags(SigType.WLOGIN_QRPUSH);
            }
        } else if (VERSION.SDK_INT >= 19 && i != 0) {
            b(activity, i);
        }
    }

    private static void b(Activity activity, int i) {
        View view = new View(activity);
        LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, p.a((Context) activity));
        layoutParams.gravity = 48;
        view.setLayoutParams(layoutParams);
        view.setVisibility(0);
        view.setBackgroundColor(i);
        ((ViewGroup) activity.getWindow().getDecorView()).addView(view);
    }
}
