package com.qq.reader.common.imageloader.b;

import android.content.Context;
import com.bumptech.glide.load.a.c;
import com.bumptech.glide.load.a.h;
import com.bumptech.glide.load.b.b.d;
import java.io.InputStream;

/* compiled from: AssertLoader */
public class a implements d<String> {
    private final Context a;
    private final String b;

    public a(Context context, String str) {
        this.a = context;
        this.b = str;
    }

    public c<InputStream> a(final String str, int i, int i2) {
        return new h(this, this.a.getApplicationContext().getAssets(), this.b) {
            final /* synthetic */ a b;

            public String b() {
                return str;
            }
        };
    }
}
