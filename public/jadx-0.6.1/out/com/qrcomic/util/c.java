package com.qrcomic.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.os.Build;
import android.provider.Settings.System;
import android.util.TypedValue;
import android.view.WindowManager.LayoutParams;
import com.tencent.android.tpush.common.Constants;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/* compiled from: AppUtils */
public class c {

    /* compiled from: AppUtils */
    public static final class a {
        public static int a(Context context, int i) {
            return (int) TypedValue.applyDimension(1, (float) i, context.getResources().getDisplayMetrics());
        }
    }

    /* compiled from: AppUtils */
    public static class b {
        public static String a(String str) {
            try {
                return URLEncoder.encode(str, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return "";
            }
        }

        public static String b(String str) {
            try {
                return URLDecoder.decode(str, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return "";
            }
        }
    }

    /* compiled from: AppUtils */
    public static final class c {
        public static long a(Context context) {
            MemoryInfo memoryInfo = new MemoryInfo();
            ((ActivityManager) context.getSystemService(Constants.FLAG_ACTIVITY_NAME)).getMemoryInfo(memoryInfo);
            return memoryInfo.availMem;
        }

        public static boolean b(Context context) {
            int i;
            try {
                i = System.getInt(context.getContentResolver(), "screen_brightness_mode");
            } catch (Exception e) {
                i = 0;
            }
            if (i == 1) {
                return true;
            }
            return false;
        }

        public static void a(Activity activity, int i) {
            int min = Math.min(255, Math.max(25, i));
            LayoutParams attributes = activity.getWindow().getAttributes();
            attributes.screenBrightness = ((float) min) / 255.0f;
            if (b((Context) activity) && Build.MODEL.contains("LT29i")) {
                try {
                    com.qrcomic.manager.b.a().b().f().a().a(activity, true);
                } catch (Exception e) {
                    e.getStackTrace();
                }
                b(activity, 0);
            }
            activity.getWindow().setAttributes(attributes);
        }

        private static void b(Activity activity, int i) {
            try {
                System.putInt(activity.getContentResolver(), "screen_brightness_mode", i);
            } catch (Exception e) {
            }
        }

        public static void a(Activity activity) {
            LayoutParams attributes = activity.getWindow().getAttributes();
            attributes.screenBrightness = -1.0f;
            activity.getWindow().setAttributes(attributes);
        }

        public static void b(Activity activity) {
            LayoutParams attributes = activity.getWindow().getAttributes();
            attributes.screenBrightness = -1.0f;
            activity.getWindow().setAttributes(attributes);
        }
    }

    /* compiled from: AppUtils */
    public static final class d {
        public static int[] a(Context context) {
            return new int[]{context.getResources().getDisplayMetrics().widthPixels, context.getResources().getDisplayMetrics().heightPixels};
        }
    }
}
