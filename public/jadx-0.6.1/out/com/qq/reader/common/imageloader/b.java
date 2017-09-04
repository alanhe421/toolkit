package com.qq.reader.common.imageloader;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView.ScaleType;
import com.bumptech.glide.load.b.b.d;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.f;

/* compiled from: GlideOptions */
public class b {
    public static final DiskCacheStrategy a = DiskCacheStrategy.SOURCE;
    private final int b;
    private final int c;
    private Drawable d;
    private Drawable e;
    private final boolean f;
    private final ScaleType g;
    private final f<Bitmap> h;
    private final int i;
    private boolean j;
    private int k;
    private int l;
    private int m;
    private final DiskCacheStrategy n;
    private final d<String> o;

    /* compiled from: GlideOptions */
    public static class a {
        private int a = 0;
        private int b = 0;
        private Drawable c = null;
        private Drawable d = null;
        private boolean e = true;
        private ScaleType f = ScaleType.FIT_CENTER;
        private f<Bitmap> g;
        private int h;
        private boolean i = false;
        private int j;
        private int k;
        private int l;
        private DiskCacheStrategy m = DiskCacheStrategy.SOURCE;
        private d<String> n;

        public a a(int i) {
            this.b = i;
            return this;
        }

        public a b(int i) {
            this.a = i;
            return this;
        }

        public a a(f<Bitmap> fVar) {
            this.g = fVar;
            return this;
        }

        public a a(ScaleType scaleType) {
            this.f = scaleType;
            return this;
        }

        public a a(int i, int i2) {
            this.k = i;
            this.l = i2;
            return this;
        }

        public a a(DiskCacheStrategy diskCacheStrategy) {
            this.m = diskCacheStrategy;
            return this;
        }

        public a a(d<String> dVar) {
            this.n = dVar;
            return this;
        }

        public b a() {
            return new b();
        }
    }

    private b(a aVar) {
        this.d = null;
        this.e = null;
        this.j = false;
        this.b = aVar.a;
        this.c = aVar.b;
        this.e = aVar.d;
        this.d = aVar.c;
        this.f = aVar.e;
        this.g = aVar.f;
        this.h = aVar.g;
        this.i = aVar.h;
        this.j = aVar.i;
        this.k = aVar.j;
        this.l = aVar.k;
        this.m = aVar.l;
        this.n = aVar.m;
        this.o = aVar.n;
    }

    public Drawable a() {
        return this.e;
    }

    public int b() {
        return this.b;
    }

    public Drawable c() {
        return this.d;
    }

    public int d() {
        return this.c;
    }

    public int e() {
        return this.i;
    }

    public boolean f() {
        return this.j;
    }

    public DiskCacheStrategy g() {
        return this.n;
    }

    public int h() {
        return this.k;
    }

    public d<String> i() {
        return this.o;
    }

    public ScaleType j() {
        return this.g;
    }

    public f<Bitmap> k() {
        return this.h;
    }

    public int l() {
        return this.m;
    }

    public int m() {
        return this.l;
    }
}
