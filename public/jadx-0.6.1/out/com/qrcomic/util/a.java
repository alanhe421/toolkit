package com.qrcomic.util;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.util.TypedValue;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;

/* compiled from: AIOUtils */
public class a {
    public static float a(int i, Resources resources) {
        return (float) ((int) TypedValue.applyDimension(1, (float) i, resources.getDisplayMetrics()));
    }

    public static void a(Activity activity, boolean z) {
        if (VERSION.SDK_INT >= 19) {
            if (z) {
                activity.getWindow().clearFlags(2048);
                activity.getWindow().addFlags(1024);
                return;
            }
            activity.getWindow().clearFlags(1024);
            activity.getWindow().addFlags(2048);
            activity.getWindow().clearFlags(SigType.WLOGIN_QRPUSH);
        } else if (z) {
            activity.getWindow().addFlags(512);
            activity.getWindow().clearFlags(2048);
            activity.getWindow().addFlags(1024);
        } else {
            activity.getWindow().clearFlags(1024);
            activity.getWindow().addFlags(2048);
        }
    }
}
