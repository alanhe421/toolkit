package com.bumptech.glide.integration.okhttp3;

import android.content.Context;
import com.bumptech.glide.load.b.c;
import com.bumptech.glide.load.b.d;
import com.bumptech.glide.load.b.l;
import com.bumptech.glide.load.b.m;
import java.io.InputStream;
import okhttp3.u;

/* compiled from: OkHttpUrlLoader */
public class b implements l<d, InputStream> {
    private final okhttp3.e.a a;

    /* compiled from: OkHttpUrlLoader */
    public static class a implements m<d, InputStream> {
        private static volatile okhttp3.e.a a;
        private okhttp3.e.a b;

        public a() {
            this(b());
        }

        public a(okhttp3.e.a aVar) {
            this.b = aVar;
        }

        private static okhttp3.e.a b() {
            if (a == null) {
                synchronized (a.class) {
                    if (a == null) {
                        a = new u();
                    }
                }
            }
            return a;
        }

        public l<d, InputStream> a(Context context, c cVar) {
            return new b(this.b);
        }

        public void a() {
        }
    }

    public b(okhttp3.e.a aVar) {
        this.a = aVar;
    }

    public com.bumptech.glide.load.a.c<InputStream> a(d dVar, int i, int i2) {
        return new a(this.a, dVar);
    }
}
