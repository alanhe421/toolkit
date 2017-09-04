package com.qq.reader.liveshow.utils;

import android.content.Context;

/* compiled from: SizeUtils */
public class m {
    public static int a(Context context, float f) {
        return (int) ((context.getResources().getDisplayMetrics().density * f) + 0.5f);
    }
}
