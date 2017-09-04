package com.tencent.android.tpush;

import android.content.Context;

/* compiled from: ProGuard */
public class XGPro {
    private static boolean a = false;

    public static void enableXGPro(Context context, boolean z) {
        a = z;
    }

    public static boolean isEnableXGPro(Context context) {
        return a;
    }
}
