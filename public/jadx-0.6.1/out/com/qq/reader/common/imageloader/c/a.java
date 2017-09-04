package com.qq.reader.common.imageloader.c;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import com.bumptech.glide.g;
import com.bumptech.glide.load.engine.bitmap_recycle.c;
import com.bumptech.glide.load.engine.i;
import com.bumptech.glide.load.f;

/* compiled from: WidthTransFormation */
public class a implements f<Bitmap> {
    private int a;
    private c b;

    public a(Context context, int i) {
        this.b = g.a(context).a();
        this.a = i;
    }

    public i<Bitmap> a(i<Bitmap> iVar, int i, int i2) {
        if (iVar.b() == null) {
            return null;
        }
        int width = ((Bitmap) iVar.b()).getWidth();
        int height = ((Bitmap) iVar.b()).getHeight();
        float f = ((float) this.a) / ((float) width);
        Matrix matrix = new Matrix();
        matrix.postScale(f, f);
        return com.bumptech.glide.load.resource.bitmap.c.a(Bitmap.createBitmap((Bitmap) iVar.b(), 0, 0, width, height, matrix, true), this.b);
    }

    public String a() {
        return "CircleBitmapDisplayer()";
    }
}
