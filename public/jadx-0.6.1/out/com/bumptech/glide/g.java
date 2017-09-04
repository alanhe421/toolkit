package com.bumptech.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.ImageView;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.b.c;
import com.bumptech.glide.load.b.l;
import com.bumptech.glide.load.engine.a.h;
import com.bumptech.glide.load.engine.b;
import com.bumptech.glide.load.engine.b.a;
import com.bumptech.glide.load.resource.bitmap.e;
import com.bumptech.glide.load.resource.bitmap.i;
import com.bumptech.glide.load.resource.bitmap.j;
import com.bumptech.glide.load.resource.bitmap.m;
import com.bumptech.glide.load.resource.bitmap.n;
import com.bumptech.glide.load.resource.e.d;
import com.bumptech.glide.manager.k;
import com.bumptech.glide.request.b.f;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

/* compiled from: Glide */
public class g {
    private static volatile g a;
    private final c b;
    private final b c;
    private final com.bumptech.glide.load.engine.bitmap_recycle.c d;
    private final h e;
    private final DecodeFormat f;
    private final f g = new f();
    private final d h = new d();
    private final com.bumptech.glide.e.c i;
    private final e j;
    private final com.bumptech.glide.load.resource.d.f k;
    private final i l;
    private final com.bumptech.glide.load.resource.d.f m;
    private final Handler n;
    private final a o;

    public static g a(Context context) {
        if (a == null) {
            synchronized (g.class) {
                if (a == null) {
                    Context applicationContext = context.getApplicationContext();
                    List<com.bumptech.glide.d.a> a = new com.bumptech.glide.d.b(applicationContext).a();
                    h hVar = new h(applicationContext);
                    for (com.bumptech.glide.d.a a2 : a) {
                        a2.a(applicationContext, hVar);
                    }
                    a = hVar.a();
                    for (com.bumptech.glide.d.a a22 : a) {
                        a22.a(applicationContext, a);
                    }
                }
            }
        }
        return a;
    }

    g(b bVar, h hVar, com.bumptech.glide.load.engine.bitmap_recycle.c cVar, Context context, DecodeFormat decodeFormat) {
        this.c = bVar;
        this.d = cVar;
        this.e = hVar;
        this.f = decodeFormat;
        this.b = new c(context);
        this.n = new Handler(Looper.getMainLooper());
        this.o = new a(hVar, cVar, decodeFormat);
        this.i = new com.bumptech.glide.e.c();
        com.bumptech.glide.e.b nVar = new n(cVar, decodeFormat);
        this.i.a(InputStream.class, Bitmap.class, nVar);
        com.bumptech.glide.e.b gVar = new com.bumptech.glide.load.resource.bitmap.g(cVar, decodeFormat);
        this.i.a(ParcelFileDescriptor.class, Bitmap.class, gVar);
        com.bumptech.glide.e.b mVar = new m(nVar, gVar);
        this.i.a(com.bumptech.glide.load.b.g.class, Bitmap.class, mVar);
        nVar = new com.bumptech.glide.load.resource.c.c(context, cVar);
        this.i.a(InputStream.class, com.bumptech.glide.load.resource.c.b.class, nVar);
        this.i.a(com.bumptech.glide.load.b.g.class, com.bumptech.glide.load.resource.d.a.class, new com.bumptech.glide.load.resource.d.g(mVar, nVar, cVar));
        this.i.a(InputStream.class, File.class, new com.bumptech.glide.load.resource.b.d());
        a(File.class, ParcelFileDescriptor.class, new com.bumptech.glide.load.b.a.a.a());
        a(File.class, InputStream.class, new com.bumptech.glide.load.b.b.c.a());
        a(Integer.TYPE, ParcelFileDescriptor.class, new com.bumptech.glide.load.b.a.c.a());
        a(Integer.TYPE, InputStream.class, new com.bumptech.glide.load.b.b.e.a());
        a(Integer.class, ParcelFileDescriptor.class, new com.bumptech.glide.load.b.a.c.a());
        a(Integer.class, InputStream.class, new com.bumptech.glide.load.b.b.e.a());
        a(String.class, ParcelFileDescriptor.class, new com.bumptech.glide.load.b.a.d.a());
        a(String.class, InputStream.class, new com.bumptech.glide.load.b.b.f.a());
        a(Uri.class, ParcelFileDescriptor.class, new com.bumptech.glide.load.b.a.e.a());
        a(Uri.class, InputStream.class, new com.bumptech.glide.load.b.b.g.a());
        a(URL.class, InputStream.class, new com.bumptech.glide.load.b.b.h.a());
        a(com.bumptech.glide.load.b.d.class, InputStream.class, new com.bumptech.glide.load.b.b.a.a());
        a(byte[].class, InputStream.class, new com.bumptech.glide.load.b.b.b.a());
        this.h.a(Bitmap.class, j.class, new com.bumptech.glide.load.resource.e.b(context.getResources(), cVar));
        this.h.a(com.bumptech.glide.load.resource.d.a.class, com.bumptech.glide.load.resource.a.b.class, new com.bumptech.glide.load.resource.e.a(new com.bumptech.glide.load.resource.e.b(context.getResources(), cVar)));
        this.j = new e(cVar);
        this.k = new com.bumptech.glide.load.resource.d.f(cVar, this.j);
        this.l = new i(cVar);
        this.m = new com.bumptech.glide.load.resource.d.f(cVar, this.l);
    }

    public com.bumptech.glide.load.engine.bitmap_recycle.c a() {
        return this.d;
    }

    <Z, R> com.bumptech.glide.load.resource.e.c<Z, R> a(Class<Z> cls, Class<R> cls2) {
        return this.h.a(cls, cls2);
    }

    <T, Z> com.bumptech.glide.e.b<T, Z> b(Class<T> cls, Class<Z> cls2) {
        return this.i.a(cls, cls2);
    }

    <R> com.bumptech.glide.request.b.j<R> a(ImageView imageView, Class<R> cls) {
        return this.g.a(imageView, cls);
    }

    b b() {
        return this.c;
    }

    e c() {
        return this.j;
    }

    i d() {
        return this.l;
    }

    com.bumptech.glide.load.resource.d.f e() {
        return this.k;
    }

    com.bumptech.glide.load.resource.d.f f() {
        return this.m;
    }

    Handler g() {
        return this.n;
    }

    DecodeFormat h() {
        return this.f;
    }

    private c k() {
        return this.b;
    }

    public void i() {
        com.bumptech.glide.g.h.a();
        this.e.a();
        this.d.a();
    }

    public void a(int i) {
        com.bumptech.glide.g.h.a();
        this.e.a(i);
        this.d.a(i);
    }

    public void j() {
        com.bumptech.glide.g.h.b();
        b().a();
    }

    public static void a(com.bumptech.glide.request.b.j<?> jVar) {
        com.bumptech.glide.g.h.a();
        com.bumptech.glide.request.b a = jVar.a();
        if (a != null) {
            a.d();
            jVar.a(null);
        }
    }

    public <T, Y> void a(Class<T> cls, Class<Y> cls2, com.bumptech.glide.load.b.m<T, Y> mVar) {
        com.bumptech.glide.load.b.m a = this.b.a((Class) cls, (Class) cls2, (com.bumptech.glide.load.b.m) mVar);
        if (a != null) {
            a.a();
        }
    }

    public static <T, Y> l<T, Y> a(Class<T> cls, Class<Y> cls2, Context context) {
        if (cls != null) {
            return a(context).k().a(cls, cls2);
        }
        if (Log.isLoggable("Glide", 3)) {
            Log.d("Glide", "Unable to load null model, setting placeholder only");
        }
        return null;
    }

    public static <T> l<T, InputStream> a(Class<T> cls, Context context) {
        return a((Class) cls, InputStream.class, context);
    }

    public static <T> l<T, ParcelFileDescriptor> b(Class<T> cls, Context context) {
        return a((Class) cls, ParcelFileDescriptor.class, context);
    }

    public static i b(Context context) {
        return k.a().a(context);
    }

    public static i a(Fragment fragment) {
        return k.a().a(fragment);
    }
}
