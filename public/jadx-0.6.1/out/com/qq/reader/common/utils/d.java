package com.qq.reader.common.utils;

import android.content.Context;
import com.tencent.assistant.link.sdk.AppLinkHelper;

/* compiled from: AppLinkUtil */
public class d {
    public static String a(Context context) {
        return AppLinkHelper.getSDCardActionTask(context);
    }

    public static void b(Context context) {
        AppLinkHelper.deteleActionTask(context);
    }
}
