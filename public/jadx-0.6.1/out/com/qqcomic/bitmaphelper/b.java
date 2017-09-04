package com.qqcomic.bitmaphelper;

import android.graphics.Bitmap;
import com.qqcomic.bitmaphelper.references.a;
import com.qqcomic.bitmaphelper.references.c;

/* compiled from: ClosableStaticBitmap */
public class b extends a {
    private a<Bitmap> a;
    private volatile Bitmap b = ((Bitmap) this.a.a());

    public b(a<Bitmap> aVar) {
        this.a = (a) c.a(aVar.c());
    }

    public int b() {
        Bitmap bitmap = this.b;
        return bitmap == null ? 0 : bitmap.getWidth();
    }

    public int c() {
        Bitmap bitmap = this.b;
        return bitmap == null ? 0 : bitmap.getHeight();
    }

    public int d() {
        int i;
        Bitmap bitmap = this.b;
        if (bitmap == null) {
            i = 0;
        } else {
            i = bitmap.getRowBytes() * bitmap.getHeight();
        }
        return Math.max(0, i);
    }

    public Bitmap e() {
        return this.b;
    }

    public void close() {
        synchronized (this) {
            if (this.a == null) {
                return;
            }
            a aVar = this.a;
            this.a = null;
            this.b = null;
            aVar.close();
        }
    }

    public boolean a() {
        return this.a == null;
    }
}
