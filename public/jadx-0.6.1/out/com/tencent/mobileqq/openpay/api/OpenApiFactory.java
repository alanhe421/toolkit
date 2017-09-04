package com.tencent.mobileqq.openpay.api;

import android.content.Context;
import android.text.TextUtils;

public class OpenApiFactory {
    private OpenApiFactory() {
        throw new RuntimeException(new StringBuilder(String.valueOf(getClass().getSimpleName())).append(" should not be created.").toString());
    }

    public static IOpenApi getInstance(Context context, String str) {
        return (context == null || TextUtils.isEmpty(str)) ? null : new a(context);
    }
}
