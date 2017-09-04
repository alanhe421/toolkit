package com.bumptech.glide.load.resource.c;

import android.graphics.Bitmap;
import android.util.Log;
import com.bumptech.glide.b.d;
import com.bumptech.glide.load.e;
import com.bumptech.glide.load.engine.bitmap_recycle.c;
import com.bumptech.glide.load.engine.i;
import com.bumptech.glide.load.f;
import java.io.OutputStream;

/* compiled from: GifResourceEncoder */
public class j implements e<b> {
    private static final a a = new a();
    private final com.bumptech.glide.b.a.a b;
    private final c c;
    private final a d;

    /* compiled from: GifResourceEncoder */
    static class a {
        a() {
        }

        public com.bumptech.glide.b.a a(com.bumptech.glide.b.a.a aVar) {
            return new com.bumptech.glide.b.a(aVar);
        }

        public d a() {
            return new d();
        }

        public com.bumptech.glide.c.a b() {
            return new com.bumptech.glide.c.a();
        }

        public i<Bitmap> a(Bitmap bitmap, c cVar) {
            return new com.bumptech.glide.load.resource.bitmap.c(bitmap, cVar);
        }
    }

    public boolean a(com.bumptech.glide.load.engine.i<com.bumptech.glide.load.resource.c.b> r11, java.io.OutputStream r12) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.ssa.SSATransform.placePhi(SSATransform.java:82)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:50)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
        /*
        r10 = this;
        r3 = 0;
        r4 = com.bumptech.glide.g.d.a();
        r0 = r11.b();
        r0 = (com.bumptech.glide.load.resource.c.b) r0;
        r6 = r0.c();
        r1 = r6 instanceof com.bumptech.glide.load.resource.d;
        if (r1 == 0) goto L_0x001c;
    L_0x0013:
        r0 = r0.d();
        r3 = r10.a(r0, r12);
    L_0x001b:
        return r3;
    L_0x001c:
        r1 = r0.d();
        r7 = r10.a(r1);
        r1 = r10.d;
        r8 = r1.b();
        r1 = r8.a(r12);
        if (r1 == 0) goto L_0x001b;
    L_0x0030:
        r2 = r3;
    L_0x0031:
        r1 = r7.c();
        if (r2 >= r1) goto L_0x0069;
    L_0x0037:
        r1 = r7.f();
        r9 = r10.a(r1, r6, r0);
        r1 = r9.b();	 Catch:{ all -> 0x0064 }
        r1 = (android.graphics.Bitmap) r1;	 Catch:{ all -> 0x0064 }
        r1 = r8.a(r1);	 Catch:{ all -> 0x0064 }
        if (r1 != 0) goto L_0x004f;
    L_0x004b:
        r9.d();
        goto L_0x001b;
    L_0x004f:
        r1 = r7.d();	 Catch:{ all -> 0x0064 }
        r1 = r7.a(r1);	 Catch:{ all -> 0x0064 }
        r8.a(r1);	 Catch:{ all -> 0x0064 }
        r7.a();	 Catch:{ all -> 0x0064 }
        r9.d();
        r1 = r2 + 1;
        r2 = r1;
        goto L_0x0031;
    L_0x0064:
        r0 = move-exception;
        r9.d();
        throw r0;
    L_0x0069:
        r3 = r8.a();
        r1 = "GifEncoder";
        r2 = 2;
        r1 = android.util.Log.isLoggable(r1, r2);
        if (r1 == 0) goto L_0x001b;
    L_0x0077:
        r1 = "GifEncoder";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r6 = "Encoded gif with ";
        r2 = r2.append(r6);
        r6 = r7.c();
        r2 = r2.append(r6);
        r6 = " frames and ";
        r2 = r2.append(r6);
        r0 = r0.d();
        r0 = r0.length;
        r0 = r2.append(r0);
        r2 = " bytes in ";
        r0 = r0.append(r2);
        r4 = com.bumptech.glide.g.d.a(r4);
        r0 = r0.append(r4);
        r2 = " ms";
        r0 = r0.append(r2);
        r0 = r0.toString();
        android.util.Log.v(r1, r0);
        goto L_0x001b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.resource.c.j.a(com.bumptech.glide.load.engine.i, java.io.OutputStream):boolean");
    }

    public j(c cVar) {
        this(cVar, a);
    }

    j(c cVar, a aVar) {
        this.c = cVar;
        this.b = new a(cVar);
        this.d = aVar;
    }

    private boolean a(byte[] bArr, OutputStream outputStream) {
        try {
            outputStream.write(bArr);
            return true;
        } catch (Throwable e) {
            if (Log.isLoggable("GifEncoder", 3)) {
                Log.d("GifEncoder", "Failed to write data to output stream in GifResourceEncoder", e);
            }
            return false;
        }
    }

    private com.bumptech.glide.b.a a(byte[] bArr) {
        d a = this.d.a();
        a.a(bArr);
        com.bumptech.glide.b.c b = a.b();
        com.bumptech.glide.b.a a2 = this.d.a(this.b);
        a2.a(b, bArr);
        a2.a();
        return a2;
    }

    private i<Bitmap> a(Bitmap bitmap, f<Bitmap> fVar, b bVar) {
        i a = this.d.a(bitmap, this.c);
        i<Bitmap> a2 = fVar.a(a, bVar.getIntrinsicWidth(), bVar.getIntrinsicHeight());
        if (!a.equals(a2)) {
            a.d();
        }
        return a2;
    }

    public String a() {
        return "";
    }
}
