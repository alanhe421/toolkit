package com.tencent.av.opengl.texture;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.util.FloatMath;
import com.tencent.smtt.sdk.WebView;

public class StringTexture extends UploadedTexture {
    protected Canvas mCanvas;
    private final Config mConfig = Config.ARGB_8888;
    private final FontMetricsInt mMetrics;
    private final TextPaint mPaint;
    private final String mText;

    private StringTexture(String str, TextPaint textPaint, FontMetricsInt fontMetricsInt, int i, int i2) {
        this.mText = str;
        this.mPaint = textPaint;
        this.mMetrics = fontMetricsInt;
        setTextureSize(i, i2);
        setOpaque(false);
    }

    public static TextPaint getDefaultPaint(float f, int i) {
        TextPaint textPaint = new TextPaint();
        textPaint.setTextSize(f);
        textPaint.setAntiAlias(true);
        textPaint.setColor(i);
        textPaint.setShadowLayer(2.0f, 0.0f, 0.0f, WebView.NIGHT_MODE_COLOR);
        return textPaint;
    }

    public static StringTexture newInstance(String str, float f, int i) {
        return newInstance(str, getDefaultPaint(f, i));
    }

    public static StringTexture newInstance(String str, float f, int i, float f2, boolean z) {
        TextPaint defaultPaint = getDefaultPaint(f, i);
        if (z) {
            defaultPaint.setTypeface(Typeface.defaultFromStyle(1));
        }
        if (f2 > 0.0f) {
            str = TextUtils.ellipsize(str, defaultPaint, f2, TruncateAt.END).toString();
        }
        return newInstance(str, defaultPaint);
    }

    private static StringTexture newInstance(String str, TextPaint textPaint) {
        int i = 1;
        FontMetricsInt fontMetricsInt = textPaint.getFontMetricsInt();
        int ceil = (int) FloatMath.ceil(textPaint.measureText(str));
        int i2 = fontMetricsInt.bottom - fontMetricsInt.top;
        if (ceil <= 0) {
            ceil = 1;
        }
        if (i2 > 0) {
            i = i2;
        }
        return new StringTexture(str, textPaint, fontMetricsInt, ceil, i);
    }

    protected Bitmap onGetBitmap() {
        Bitmap createBitmap = Bitmap.createBitmap(this.mWidth, this.mHeight, this.mConfig);
        this.mCanvas = new Canvas(createBitmap);
        this.mCanvas.translate(0.0f, (float) (-this.mMetrics.ascent));
        this.mCanvas.drawText(this.mText, 0.0f, 0.0f, this.mPaint);
        return createBitmap;
    }

    protected void onFreeBitmap(Bitmap bitmap) {
        if (!BasicTexture.inFinalizer()) {
            bitmap.recycle();
        }
    }
}
