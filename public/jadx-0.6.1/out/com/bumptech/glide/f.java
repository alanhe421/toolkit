package com.bumptech.glide;

import android.content.Context;
import com.bumptech.glide.e.e;
import com.bumptech.glide.load.b.l;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.e.c;
import com.bumptech.glide.manager.g;
import com.bumptech.glide.manager.m;
import com.bumptech.glide.request.a;
import com.bumptech.glide.request.b.j;
import java.io.File;

/* compiled from: GenericTranscodeRequest */
public class f<ModelType, DataType, ResourceType> extends e<ModelType, DataType, ResourceType, ResourceType> {
    private final l<ModelType, DataType> g;
    private final Class<DataType> h;
    private final Class<ResourceType> i;
    private final d j;

    private static <A, T, Z, R> com.bumptech.glide.e.f<A, T, Z, R> a(g gVar, l<A, T> lVar, Class<T> cls, Class<Z> cls2, c<Z, R> cVar) {
        return new e(lVar, cVar, gVar.b((Class) cls, (Class) cls2));
    }

    f(Class<ResourceType> cls, e<ModelType, ?, ?, ?> eVar, l<ModelType, DataType> lVar, Class<DataType> cls2, Class<ResourceType> cls3, d dVar) {
        super(a(eVar.c, lVar, cls2, cls3, com.bumptech.glide.load.resource.e.e.b()), cls, eVar);
        this.g = lVar;
        this.h = cls2;
        this.i = cls3;
        this.j = dVar;
    }

    f(Context context, g gVar, Class<ModelType> cls, l<ModelType, DataType> lVar, Class<DataType> cls2, Class<ResourceType> cls3, m mVar, g gVar2, d dVar) {
        super(context, cls, a(gVar, lVar, cls2, cls3, com.bumptech.glide.load.resource.e.e.b()), cls3, gVar, mVar, gVar2);
        this.g = lVar;
        this.h = cls2;
        this.i = cls3;
        this.j = dVar;
    }

    public <Y extends j<File>> Y a(Y y) {
        return a().b((j) y);
    }

    public a<File> a(int i, int i2) {
        return a().d(i, i2);
    }

    private e<ModelType, DataType, File, File> a() {
        return this.j.a(new e(new e(this.g, com.bumptech.glide.load.resource.e.e.b(), this.c.b(this.h, File.class)), File.class, this)).b(Priority.LOW).b(DiskCacheStrategy.SOURCE).b(true);
    }
}
