package com.qq.reader.common.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.tencent.feedback.proguard.R;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;

/* compiled from: ScreenModeUtils */
public class aj {

    /* compiled from: ScreenModeUtils */
    public static class a {
        public static boolean a(Window window, boolean z) {
            if (window == null) {
                return false;
            }
            try {
                LayoutParams attributes = window.getAttributes();
                Field declaredField = LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field declaredField2 = LayoutParams.class.getDeclaredField("meizuFlags");
                declaredField.setAccessible(true);
                declaredField2.setAccessible(true);
                int i = declaredField.getInt(null);
                int i2 = declaredField2.getInt(attributes);
                if (z) {
                    i |= i2;
                } else {
                    i = (i ^ -1) & i2;
                }
                declaredField2.setInt(attributes, i);
                window.setAttributes(attributes);
                return true;
            } catch (Exception e) {
                return false;
            }
        }

        public static boolean a(Activity activity, boolean z) {
            Window window = activity.getWindow();
            if (window == null) {
                return false;
            }
            Class cls = window.getClass();
            try {
                Class cls2 = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                int i = cls2.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE").getInt(cls2);
                Method method = cls.getMethod("setExtraFlags", new Class[]{Integer.TYPE, Integer.TYPE});
                if (z) {
                    method.invoke(window, new Object[]{Integer.valueOf(i), Integer.valueOf(i)});
                } else {
                    method.invoke(window, new Object[]{Integer.valueOf(0), Integer.valueOf(i)});
                }
                try {
                    if (VERSION.SDK_INT < 23) {
                        return true;
                    }
                    if (z) {
                        activity.getWindow().getDecorView().setSystemUiVisibility(9216);
                        return true;
                    }
                    activity.getWindow().getDecorView().setSystemUiVisibility(0);
                    return true;
                } catch (Exception e) {
                    return true;
                }
            } catch (Exception e2) {
                return false;
            }
        }
    }

    public static void a(Activity activity, boolean z) {
        if (VERSION.SDK_INT < 19) {
            f(activity, z);
        } else if (z) {
            activity.getWindow().getDecorView().setSystemUiVisibility(5381);
            b(activity, true);
        } else {
            activity.getWindow().getDecorView().setSystemUiVisibility(1281);
            b(activity, false);
        }
    }

    private static void f(Activity activity, boolean z) {
        if (z) {
            activity.getWindow().addFlags(512);
            activity.getWindow().clearFlags(2048);
            activity.getWindow().addFlags(1024);
            return;
        }
        activity.getWindow().clearFlags(512);
        activity.getWindow().clearFlags(1024);
        activity.getWindow().addFlags(2048);
    }

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
        if (!com.qq.reader.common.c.a.dj) {
            com.qq.reader.common.c.a.dj = true;
            com.qq.reader.common.c.a.dk = ao.x("ro.build.version.emui");
        }
        if ("EmotionUI_3.1".equals(com.qq.reader.common.c.a.dk)) {
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
        ViewGroup.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, com.qq.reader.common.c.a.ca);
        layoutParams.gravity = 48;
        view.setLayoutParams(layoutParams);
        view.setVisibility(0);
        view.setBackgroundColor(i);
        ((ViewGroup) activity.getWindow().getDecorView()).addView(view);
    }

    public static void a(Fragment fragment) {
        if (VERSION.SDK_INT >= 19 && fragment != null && fragment.getView() != null) {
            RelativeLayout relativeLayout = (RelativeLayout) fragment.getView().findViewById(R.id.common_titler);
            if (relativeLayout != null) {
                relativeLayout.setPadding(0, com.qq.reader.common.c.a.ca, 0, 0);
                int dimension = com.qq.reader.common.c.a.ca + ((int) ReaderApplication.getApplicationImp().getResources().getDimension(R.dimen.bookstore_titlerbar_height));
                relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(-1, dimension));
                relativeLayout = (RelativeLayout) fragment.getView().findViewById(R.id.webview_layout);
                if (relativeLayout != null) {
                    relativeLayout.setPadding(0, com.qq.reader.common.c.a.ca, 0, 0);
                }
                TextView textView = (TextView) fragment.getView().findViewById(R.id.main_toastbar);
                if (textView != null) {
                    textView.setPadding(0, com.qq.reader.common.c.a.ca + ((int) ReaderApplication.getApplicationImp().getResources().getDimension(R.dimen.toastbar_paddingtop)), 0, 0);
                    textView.setLayoutParams(new RelativeLayout.LayoutParams(-1, dimension));
                }
            }
        }
    }

    public static void b(Activity activity) {
        if (VERSION.SDK_INT >= 19) {
            RelativeLayout relativeLayout = (RelativeLayout) activity.findViewById(R.id.common_titler);
            if (relativeLayout != null) {
                relativeLayout.setPadding(0, com.qq.reader.common.c.a.ca, 0, 0);
                int dimension = com.qq.reader.common.c.a.ca + ((int) activity.getResources().getDimension(R.dimen.bookstore_titlerbar_height));
                relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(-1, dimension));
                relativeLayout = (RelativeLayout) activity.findViewById(R.id.webview_layout);
                if (relativeLayout != null) {
                    relativeLayout.setPadding(0, com.qq.reader.common.c.a.ca, 0, 0);
                }
                TextView textView = (TextView) activity.findViewById(R.id.main_toastbar);
                if (textView != null) {
                    textView.setPadding(0, com.qq.reader.common.c.a.ca + ((int) activity.getResources().getDimension(R.dimen.toastbar_paddingtop)), 0, 0);
                    textView.setLayoutParams(new RelativeLayout.LayoutParams(-1, dimension));
                }
            }
        }
    }

    public static void c(Activity activity) {
        if (VERSION.SDK_INT >= 19) {
            RelativeLayout relativeLayout = (RelativeLayout) activity.findViewById(R.id.common_titler);
            if (relativeLayout != null) {
                relativeLayout.setPadding(0, 0, 0, 0);
                int dimension = (int) activity.getResources().getDimension(R.dimen.bookstore_titlerbar_height);
                relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(-1, dimension));
                relativeLayout = (RelativeLayout) activity.findViewById(R.id.webview_layout);
                if (relativeLayout != null) {
                    relativeLayout.setPadding(0, com.qq.reader.common.c.a.ca, 0, 0);
                }
                TextView textView = (TextView) activity.findViewById(R.id.main_toastbar);
                if (textView != null) {
                    int dimension2 = (int) activity.getResources().getDimension(R.dimen.toastbar_paddingtop);
                    textView.setPadding(0, 0, 0, 0);
                    textView.setLayoutParams(new RelativeLayout.LayoutParams(-1, dimension));
                }
            }
        }
    }

    public static void d(Activity activity) {
        if (VERSION.SDK_INT >= 21 && com.qq.reader.common.c.a.C) {
            e(activity);
        }
        if (VERSION.SDK_INT >= 19) {
            activity.getWindow().getDecorView().setSystemUiVisibility(256);
            activity.getWindow().clearFlags(1024);
            activity.getWindow().addFlags(2048);
            return;
        }
        activity.getWindow().clearFlags(512);
        activity.getWindow().clearFlags(1024);
        activity.getWindow().addFlags(2048);
    }

    private static void g(Activity activity, boolean z) {
        if (z) {
            activity.getWindow().addFlags(512);
            activity.getWindow().clearFlags(2048);
            activity.getWindow().addFlags(1024);
            return;
        }
        activity.getWindow().clearFlags(1024);
        activity.getWindow().addFlags(2048);
    }

    public static void b(Activity activity, boolean z) {
        if (d.ao(ReaderApplication.getApplicationImp()) && VERSION.SDK_INT >= 19) {
            if (z) {
                activity.getWindow().clearFlags(2048);
                activity.getWindow().addFlags(1024);
                return;
            }
            activity.getWindow().clearFlags(1024);
            activity.getWindow().addFlags(2048);
        }
    }

    public static void c(Activity activity, boolean z) {
        if (VERSION.SDK_INT < 19) {
            g(activity, z);
        } else if (z) {
            activity.getWindow().getDecorView().setSystemUiVisibility(5381);
            b(activity, true);
        } else {
            activity.getWindow().getDecorView().setSystemUiVisibility(1281);
        }
    }

    public static void d(Activity activity, boolean z) {
        if (VERSION.SDK_INT < 19) {
            g(activity, z);
        } else if (z) {
            activity.getWindow().getDecorView().setSystemUiVisibility(5894);
            b(activity, true);
        } else {
            activity.getWindow().getDecorView().setSystemUiVisibility(1792);
        }
    }

    public static void a(Dialog dialog, boolean z) {
        if (z) {
            dialog.getWindow().clearFlags(2048);
            dialog.getWindow().addFlags(1024);
            return;
        }
        dialog.getWindow().clearFlags(1024);
        dialog.getWindow().addFlags(2048);
    }

    public static void b(Dialog dialog, boolean z) {
        if (z) {
            if (VERSION.SDK_INT >= 19) {
                dialog.getWindow().getDecorView().setSystemUiVisibility(5894);
            } else {
                a(dialog, z);
            }
        } else if (VERSION.SDK_INT >= 19) {
            dialog.getWindow().getDecorView().setSystemUiVisibility(4);
        } else {
            a(dialog, z);
        }
    }

    public static boolean a(Context context) {
        if (VERSION.SDK_INT < 19 || Build.MODEL.contains("M460A") || ViewConfiguration.get(context.getApplicationContext()).hasPermanentMenuKey()) {
            return false;
        }
        return true;
    }

    public static void e(Activity activity) {
        activity.getWindow().clearFlags(SigType.WLOGIN_QRPUSH);
        activity.getWindow().addFlags(Integer.MIN_VALUE);
        a(activity, -12303292);
        e(activity, false);
    }

    public static boolean e(Activity activity, boolean z) {
        if (activity == null) {
            return false;
        }
        try {
            LayoutParams attributes = activity.getWindow().getAttributes();
            Field declaredField = LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
            Field declaredField2 = LayoutParams.class.getDeclaredField("meizuFlags");
            declaredField.setAccessible(true);
            declaredField2.setAccessible(true);
            int i = declaredField.getInt(null);
            int i2 = declaredField2.getInt(attributes);
            if (z) {
                i |= i2;
            } else {
                i = (i ^ -1) & i2;
            }
            declaredField2.setInt(attributes, i);
            activity.getWindow().setAttributes(attributes);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
