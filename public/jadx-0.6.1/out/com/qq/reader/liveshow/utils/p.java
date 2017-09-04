package com.qq.reader.liveshow.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.TranslateAnimation;
import android.webkit.WebView;
import android.widget.ImageView;
import com.bumptech.glide.g;
import com.qq.reader.liveshow.a.a;
import com.qq.reader.liveshow.a.d;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;

/* compiled from: UIUtils */
public class p {
    public static Bitmap a(Bitmap bitmap, int i) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        int width = i == 0 ? bitmap.getHeight() > bitmap.getWidth() ? bitmap.getWidth() : bitmap.getHeight() : i;
        Bitmap createBitmap = Bitmap.createBitmap(width, width, Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawCircle((float) (width / 2), (float) (width / 2), (float) (width / 2), paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        return createBitmap;
    }

    public static String a(String str, int i) {
        if (str == null || str.length() <= i) {
            return str;
        }
        return str.substring(0, i) + "...";
    }

    public static int a(int i) {
        switch (i) {
            case 4:
                return d.card_platinum;
            case 5:
                return d.card_god;
            case 6:
                return d.card_star;
            case 7:
                return d.card_auther;
            default:
                return 0;
        }
    }

    public static int b(int i) {
        switch (i) {
            case 0:
                return d.live_audience_gift_0;
            case 1:
                return d.live_audience_gift_1;
            case 2:
                return d.live_audience_gift_2;
            case 3:
                return d.live_audience_gift_3;
            case 4:
                return d.live_audience_gift_4;
            case 5:
                return d.live_audience_gift_5;
            case 6:
                return d.live_audience_gift_6;
            case 7:
                return d.live_audience_gift_7;
            case 8:
                return d.live_audience_gift_8;
            case 9:
                return d.live_audience_gift_9;
            default:
                return 0;
        }
    }

    public static void a(Context context, ImageView imageView, String str, boolean z) {
        if (z) {
            a(context, imageView, str, d.host_default_avatar);
        } else {
            a(context, imageView, str, d.default_avatar);
        }
    }

    private static void a(Context context, ImageView imageView, String str, int i) {
        if (TextUtils.isEmpty(str)) {
            imageView.setImageBitmap(a(BitmapFactory.decodeResource(context.getResources(), i), 0));
            return;
        }
        g.b(context).a(str).a(new h(context)).c(i).g(i).a(imageView);
    }

    public static void a(Context context, ImageView imageView, String str) {
        if (TextUtils.isEmpty(str)) {
            imageView.setImageResource(d.default_gift);
        } else {
            g.b(context).a(str).c(d.default_gift).g(d.default_gift).a(imageView);
        }
    }

    public static String a(String str) {
        Exception e;
        Throwable th;
        BufferedReader bufferedReader = null;
        BufferedReader bufferedReader2;
        try {
            bufferedReader2 = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("getprop " + str).getInputStream()), 1024);
            try {
                String readLine = bufferedReader2.readLine();
                bufferedReader2.close();
                if (bufferedReader2 == null) {
                    return readLine;
                }
                try {
                    bufferedReader2.close();
                    return readLine;
                } catch (IOException e2) {
                    SxbLog.e("getSystemPropertyError", e2.getMessage());
                    return readLine;
                }
            } catch (Exception e3) {
                e = e3;
                try {
                    SxbLog.e("getSystemPropertyError", e.getMessage());
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                        } catch (IOException e4) {
                            SxbLog.e("getSystemPropertyError", e4.getMessage());
                        }
                    }
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    bufferedReader = bufferedReader2;
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e22) {
                            SxbLog.e("getSystemPropertyError", e22.getMessage());
                        }
                    }
                    throw th;
                }
            }
        } catch (Exception e5) {
            e = e5;
            bufferedReader2 = null;
            SxbLog.e("getSystemPropertyError", e.getMessage());
            if (bufferedReader2 != null) {
                bufferedReader2.close();
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            throw th;
        }
    }

    public static int a(Context context) {
        int i = 0;
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            i = resources.getDimensionPixelSize(identifier);
        }
        if (i <= 0) {
            return a(25.0f, context);
        }
        return i;
    }

    public static int a(float f, Context context) {
        return (int) TypedValue.applyDimension(1, f, context.getResources().getDisplayMetrics());
    }

    public static TranslateAnimation a(int i, int i2, int i3, int i4, int i5, Interpolator interpolator) {
        TranslateAnimation translateAnimation = new TranslateAnimation((float) i2, (float) i3, (float) i4, (float) i5);
        translateAnimation.setDuration((long) i);
        if (interpolator != null) {
            translateAnimation.setInterpolator(interpolator);
        }
        translateAnimation.setFillAfter(true);
        return translateAnimation;
    }

    public static Animation a(Context context, int i) {
        Animation loadAnimation = AnimationUtils.loadAnimation(context, a.overshoot_interpolator_scale_0_to_100_anim);
        loadAnimation.setDuration((long) i);
        loadAnimation.setFillAfter(true);
        return loadAnimation;
    }

    public static Animation b(Context context, int i) {
        Animation loadAnimation = AnimationUtils.loadAnimation(context, a.incresing_number_scale_80_to_100_anim);
        loadAnimation.setDuration((long) i);
        return loadAnimation;
    }

    public static void b(Context context) {
        if (context != null) {
            try {
                Context applicationContext = context.getApplicationContext();
                if (applicationContext != null) {
                    AccessibilityManager accessibilityManager = (AccessibilityManager) applicationContext.getSystemService("accessibility");
                    if (accessibilityManager.isEnabled()) {
                        Method declaredMethod = accessibilityManager.getClass().getDeclaredMethod("setState", new Class[]{Integer.TYPE});
                        declaredMethod.setAccessible(true);
                        declaredMethod.invoke(accessibilityManager, new Object[]{Integer.valueOf(0)});
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public static void a(WebView webView) {
        webView.removeJavascriptInterface("searchBoxJavaBridge_");
        webView.removeJavascriptInterface("accessibility");
        webView.removeJavascriptInterface("accessibilityTraversal");
    }
}
