package format.epub.b;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Rect;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.resource.bitmap.f;
import com.qq.reader.ReaderApplication;
import format.epub.common.image.g;
import format.epub.paint.ZLPaintContext.ScalingType;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: InputStreamImageData */
final class a extends b {
    private final g a;

    a(g gVar) {
        this.a = gVar;
    }

    protected Bitmap a(Options options) {
        Bitmap decodeStream;
        InputStream c = this.a.c();
        if (c == null) {
            return null;
        }
        if (options.inJustDecodeBounds) {
            decodeStream = BitmapFactory.decodeStream(c, new Rect(), options);
        } else {
            decodeStream = f.b.a(c, com.bumptech.glide.g.a(ReaderApplication.getApplicationImp()).a(), options.outWidth, options.outHeight, DecodeFormat.PREFER_ARGB_8888);
        }
        try {
            c.close();
            return decodeStream;
        } catch (IOException e) {
            return decodeStream;
        }
    }

    public synchronized Bitmap a(int i, int i2, ScalingType scalingType) {
        Bitmap a;
        a = super.a(i, i2, scalingType);
        if (!(a == null || this.a == null)) {
            c.b().a((b) this);
        }
        return a;
    }
}
