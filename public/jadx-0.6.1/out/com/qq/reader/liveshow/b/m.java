package com.qq.reader.liveshow.b;

import android.content.Context;
import android.widget.Toast;

/* compiled from: QavToast */
public class m {
    private static i a = null;

    public static void a(i iVar) {
        a = iVar;
    }

    public static void a(Context context, String str, int i) {
        if (a == null) {
            Toast.makeText(context, str, i).show();
        } else {
            a.a(context, str, i);
        }
    }

    public static void a(Context context, int i, int i2) {
        if (a == null) {
            Toast.makeText(context, i, i2).show();
        } else {
            a.a(context, i, i2);
        }
    }
}
