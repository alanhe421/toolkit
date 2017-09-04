package com.qrcomic.screenshot.d;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.os.Build.VERSION;
import com.qrcomic.util.g;

/* compiled from: QRBitmapUtil */
public class b {
    public static Bitmap a(int i, int i2, Config config) {
        Bitmap bitmap = null;
        try {
            bitmap = Bitmap.createBitmap(i, i2, config);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            if (g.a()) {
                g.a("QRBitmapUtil", g.d, "QRBitmapUtil.createBitmap OutOfMemoryError " + e.toString());
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return bitmap;
    }

    @TargetApi(11)
    public static Bitmap a(Context context, int i, int i2, int i3) {
        OutOfMemoryError e;
        Exception e2;
        Bitmap decodeResource;
        try {
            if (VERSION.SDK_INT >= 11) {
                Options options = new Options();
                options.inMutable = true;
                decodeResource = BitmapFactory.decodeResource(context.getResources(), i, options);
            } else {
                decodeResource = BitmapFactory.decodeResource(context.getResources(), i);
            }
            if (decodeResource == null) {
                return decodeResource;
            }
            try {
                if (decodeResource.getWidth() == i2 && decodeResource.getHeight() == i3) {
                    return decodeResource;
                }
                Bitmap createScaledBitmap = Bitmap.createScaledBitmap(decodeResource, i2, i3, true);
                if (!decodeResource.isRecycled()) {
                    decodeResource.recycle();
                }
                return createScaledBitmap;
            } catch (OutOfMemoryError e3) {
                e = e3;
                e.printStackTrace();
                if (g.a()) {
                    return decodeResource;
                }
                g.a("QRBitmapUtil", g.d, "QRBitmapUtil.loadMutableBitmap OutOfMemoryError " + e.toString());
                return decodeResource;
            } catch (Exception e4) {
                e2 = e4;
                e2.printStackTrace();
                return decodeResource;
            }
        } catch (OutOfMemoryError e5) {
            OutOfMemoryError outOfMemoryError = e5;
            decodeResource = null;
            e = outOfMemoryError;
            e.printStackTrace();
            if (g.a()) {
                return decodeResource;
            }
            g.a("QRBitmapUtil", g.d, "QRBitmapUtil.loadMutableBitmap OutOfMemoryError " + e.toString());
            return decodeResource;
        } catch (Exception e6) {
            Exception exception = e6;
            decodeResource = null;
            e2 = exception;
            e2.printStackTrace();
            return decodeResource;
        }
    }

    public static void a(Matrix matrix, Matrix matrix2) {
        if (matrix != null && matrix2 != null && !matrix.invert(matrix2) && g.a()) {
            g.a("QRBitmapUtil", g.d, "srcMatrix : " + matrix + " do not has invert matrix");
        }
    }
}
