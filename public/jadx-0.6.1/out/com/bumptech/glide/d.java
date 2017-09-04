package com.bumptech.glide;

import android.content.Context;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.e.e;
import com.bumptech.glide.load.b.f;
import com.bumptech.glide.load.b.g;
import com.bumptech.glide.load.b.l;
import com.bumptech.glide.load.resource.a.b;
import com.bumptech.glide.load.resource.d.a;
import com.bumptech.glide.load.resource.e.c;
import com.bumptech.glide.manager.m;
import com.bumptech.glide.request.b.j;
import java.io.File;
import java.io.InputStream;

/* compiled from: DrawableTypeRequest */
public class d<ModelType> extends c<ModelType> {
    private final l<ModelType, InputStream> g;
    private final l<ModelType, ParcelFileDescriptor> h;
    private final d i;

    private static <A, Z, R> e<A, g, Z, R> a(g gVar, l<A, InputStream> lVar, l<A, ParcelFileDescriptor> lVar2, Class<Z> cls, Class<R> cls2, c<Z, R> cVar) {
        if (lVar == null && lVar2 == null) {
            return null;
        }
        if (cVar == null) {
            cVar = gVar.a((Class) cls, (Class) cls2);
        }
        return new e(new f(lVar, lVar2), cVar, gVar.b(g.class, (Class) cls));
    }

    d(Class<ModelType> cls, l<ModelType, InputStream> lVar, l<ModelType, ParcelFileDescriptor> lVar2, Context context, g gVar, m mVar, com.bumptech.glide.manager.g gVar2, d dVar) {
        super(context, cls, a(gVar, lVar, lVar2, a.class, b.class, null), gVar, mVar, gVar2);
        this.g = lVar;
        this.h = lVar2;
        this.i = dVar;
    }

    public b<ModelType> j() {
        return (b) this.i.a(new b(this, this.g, this.h, this.i));
    }

    public <Y extends j<File>> Y a(Y y) {
        return k().a(y);
    }

    public com.bumptech.glide.request.a<File> c(int i, int i2) {
        return k().a(i, i2);
    }

    private f<ModelType, InputStream, File> k() {
        return (f) this.i.a(new f(File.class, this, this.g, InputStream.class, File.class, this.i));
    }
}
