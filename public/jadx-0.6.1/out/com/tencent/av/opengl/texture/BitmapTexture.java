package com.tencent.av.opengl.texture;

import android.graphics.Bitmap;
import junit.framework.Assert;

public class BitmapTexture extends UploadedTexture {
    protected Bitmap mContentBitmap;

    public BitmapTexture(Bitmap bitmap) {
        boolean z = (bitmap == null || bitmap.isRecycled()) ? false : true;
        Assert.assertTrue(z);
        this.mContentBitmap = bitmap;
    }

    protected void onFreeBitmap(Bitmap bitmap) {
    }

    protected Bitmap onGetBitmap() {
        return this.mContentBitmap;
    }

    public Bitmap getBitmap() {
        return this.mContentBitmap;
    }
}
