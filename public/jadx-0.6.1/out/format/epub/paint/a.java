package format.epub.paint;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.text.TextPaint;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.utils.ao;
import format.epub.paint.ZLPaintContext.ScalingType;

/* compiled from: BitmapScaleHelper */
public class a {
    private static int a(int i, float f, float f2) {
        return (int) ((((float) i) / f) * f2);
    }

    private static int b(int i, float f, float f2) {
        return (int) ((((float) i) / f2) * f);
    }

    public static Rect a(Bitmap bitmap, int i, int i2, int i3, int i4, ScalingType scalingType) {
        if (bitmap == null) {
            return null;
        }
        Rect rect = new Rect();
        int width;
        int height;
        float f;
        int height2;
        switch (scalingType) {
            case FILLSCREEN:
            case SCALEWIDTH:
                width = bitmap.getWidth();
                height = bitmap.getHeight();
                if (width > 0 && height > 0) {
                    f = ((float) i) / ((float) width);
                    rect.right = i;
                    rect.bottom = (int) (f * ((float) height));
                    break;
                }
            case SCALEHEIGHT:
                width = bitmap.getWidth();
                height = bitmap.getHeight();
                if (width > 0 && height > 0) {
                    rect.right = (int) (((float) width) * (((float) i2) / ((float) height)));
                    rect.bottom = i2;
                    break;
                }
            case SCALEWH:
                width = bitmap.getWidth();
                height = bitmap.getHeight();
                if (width > 0 && height > 0) {
                    rect.right = width;
                    rect.bottom = height;
                    break;
                }
            case FULLSCREEN:
                height = bitmap.getWidth();
                height2 = bitmap.getHeight();
                if (height > 0 && height2 > 0) {
                    if (ao.l() != 0) {
                        width = b(i2, (float) height, (float) height2);
                        if (width > i) {
                            a(i, (float) height, (float) height2);
                        } else {
                            i = width;
                        }
                    } else if (a(i, (float) height, (float) height2) > i2) {
                        i = b(i2, (float) height, (float) height2);
                    }
                    f = ((float) i) / ((float) height);
                    rect.right = i;
                    rect.bottom = (int) (f * ((float) height2));
                    break;
                }
            case FitMaximum:
                width = bitmap.getWidth();
                height = bitmap.getHeight();
                if (width > 0 && height > 0 && width != i && height != i2 && (width > i || height > i2)) {
                    if (width * i2 > height * i) {
                        i2 = Math.max(1, (height * i) / width);
                    } else {
                        i = Math.max(1, (width * i2) / height);
                    }
                    rect.right = i;
                    rect.bottom = i2;
                    break;
                }
            case IntegerCoefficient:
                width = bitmap.getWidth();
                height = bitmap.getHeight();
                if (width > 0 && height > 0 && (width > i || height > i2)) {
                    if (width * i2 > height * i) {
                        i2 = Math.max(1, (height * i) / width);
                    } else {
                        i = Math.max(1, (width * i2) / height);
                    }
                    rect.right = i;
                    rect.bottom = i2;
                    break;
                }
                TextPaint textPaint = new TextPaint();
                textPaint.setTextSize(d.I(ReaderApplication.getApplicationImp()));
                height2 = (int) ao.b('中', textPaint);
                if (width < height2) {
                    width = (int) ((((float) height2) / ((float) width)) * ((float) height));
                    rect.right = height2;
                    rect.bottom = width;
                    break;
                }
                break;
        }
        rect.left = i3;
        rect.top = i4 - rect.bottom;
        rect.right += rect.left;
        rect.bottom += rect.top;
        return rect;
    }
}
