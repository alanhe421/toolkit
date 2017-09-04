package com.tencent.av.opengl.texture;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import junit.framework.Assert;

public class ResourceTexture extends UploadedTexture {
    protected final Context mContext;
    protected final int mResId;

    public ResourceTexture(Context context, int i) {
        Assert.assertNotNull(context);
        this.mContext = context;
        this.mResId = i;
        setOpaque(false);
    }

    protected Bitmap onGetBitmap() {
        Options options = new Options();
        options.inPreferredConfig = Config.ARGB_8888;
        options.inScaled = false;
        int i = this.mContext.getResources().getDisplayMetrics().widthPixels;
        if (i <= 480) {
            options.inSampleSize = 2;
        } else if (i <= 720) {
            options.inSampleSize = 1;
        } else {
            options.inSampleSize = 1;
        }
        try {
            return BitmapFactory.decodeResource(this.mContext.getResources(), this.mResId, options);
        } catch (OutOfMemoryError e) {
            options.inSampleSize *= 2;
            try {
                return BitmapFactory.decodeResource(this.mContext.getResources(), this.mResId, options);
            } catch (OutOfMemoryError e2) {
                options.inSampleSize *= 2;
                return BitmapFactory.decodeResource(this.mContext.getResources(), this.mResId, options);
            }
        }
    }

    protected void onFreeBitmap(Bitmap bitmap) {
        if (!BasicTexture.inFinalizer()) {
            bitmap.recycle();
        }
    }
}
