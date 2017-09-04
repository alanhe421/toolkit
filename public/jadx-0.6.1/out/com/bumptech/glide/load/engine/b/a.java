package com.bumptech.glide.load.engine.b;

import android.os.Handler;
import android.os.Looper;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.a.h;
import com.bumptech.glide.load.engine.bitmap_recycle.c;

/* compiled from: BitmapPreFiller */
public final class a {
    private final h a;
    private final c b;
    private final DecodeFormat c;
    private final Handler d = new Handler(Looper.getMainLooper());

    public a(h hVar, c cVar, DecodeFormat decodeFormat) {
        this.a = hVar;
        this.b = cVar;
        this.c = decodeFormat;
    }
}
