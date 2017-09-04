package com.sina.weibo.sdk.b;

import android.app.AlertDialog.Builder;
import android.content.Context;

/* compiled from: UIUtils */
public class j {
    public static void a(Context context, String str, String str2) {
        if (context != null) {
            new Builder(context).setTitle(str).setMessage(str2).create().show();
        }
    }
}
