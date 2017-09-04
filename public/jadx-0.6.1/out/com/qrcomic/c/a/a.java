package com.qrcomic.c.a;

import android.content.Context;
import android.widget.Toast;
import com.qrcomic.c.g;

/* compiled from: CToast */
public class a implements g {
    public void a(Context context, String str, int i) {
        Toast.makeText(context, str, i).show();
    }

    public void a(Context context, int i, int i2) {
        Toast.makeText(context, i, i2).show();
    }
}
