package com.bumptech.glide.integration.okhttp3;

import android.content.Context;
import com.bumptech.glide.d.a;
import com.bumptech.glide.g;
import com.bumptech.glide.h;
import com.bumptech.glide.load.b.d;
import java.io.InputStream;

public class OkHttpGlideModule implements a {
    public void a(Context context, h hVar) {
    }

    public void a(Context context, g gVar) {
        gVar.a(d.class, InputStream.class, new b.a());
    }
}
