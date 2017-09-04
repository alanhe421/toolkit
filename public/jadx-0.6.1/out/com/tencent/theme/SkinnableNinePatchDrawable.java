package com.tencent.theme;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.NinePatch;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.util.DisplayMetrics;

public class SkinnableNinePatchDrawable extends Drawable {
    private static final boolean DEFAULT_DITHER = true;
    private int mBitmapHeight;
    private int mBitmapWidth;
    private boolean mMutated;
    private NinePatch mNinePatch;
    private NinePatchState mNinePatchState;
    private Rect mPadding;
    private Paint mPaint;
    private int mTargetDensity;

    static final class NinePatchState extends BaseConstantState {
        Bitmap mBitmap;
        int mChangingConfigurations;
        final boolean mDither;
        NinePatch mNinePatch;
        Rect mOldPadding;
        Rect mPadding;
        int mTargetDensity;

        NinePatchState(NinePatch ninePatch, Bitmap bitmap, Rect rect) {
            this(ninePatch, bitmap, rect, true);
        }

        NinePatchState(NinePatch ninePatch, Bitmap bitmap, Rect rect, boolean z) {
            this.mTargetDensity = 160;
            this.mBitmap = bitmap;
            this.mNinePatch = ninePatch;
            this.mPadding = rect;
            this.mDither = z;
        }

        NinePatchState(NinePatchState ninePatchState) {
            this.mTargetDensity = 160;
            this.mNinePatch = ninePatchState.mNinePatch;
            this.mPadding = ninePatchState.mPadding;
            this.mDither = ninePatchState.mDither;
            this.mChangingConfigurations = ninePatchState.mChangingConfigurations;
            this.mTargetDensity = ninePatchState.mTargetDensity;
        }

        public Drawable newDrawable() {
            return new SkinnableNinePatchDrawable(this, null);
        }

        public Drawable newDrawable(Resources resources) {
            return new SkinnableNinePatchDrawable(this, resources);
        }

        public int getChangingConfigurations() {
            return this.mChangingConfigurations;
        }
    }

    SkinnableNinePatchDrawable() {
        this.mTargetDensity = 160;
    }

    @Deprecated
    public SkinnableNinePatchDrawable(Bitmap bitmap, byte[] bArr, Rect rect, String str) {
        this(new NinePatchState(new NinePatch(bitmap, bArr, str), bitmap, rect), null);
    }

    public SkinnableNinePatchDrawable(Resources resources, Bitmap bitmap, byte[] bArr, Rect rect, String str) {
        this(new NinePatchState(new NinePatch(bitmap, bArr, str), bitmap, rect), resources);
        this.mNinePatchState.mTargetDensity = this.mTargetDensity;
    }

    private void setNinePatchState(NinePatchState ninePatchState, Resources resources) {
        this.mNinePatchState = ninePatchState;
        this.mNinePatch = ninePatchState.mNinePatch;
        this.mPadding = ninePatchState.mPadding;
        this.mTargetDensity = resources != null ? resources.getDisplayMetrics().densityDpi : ninePatchState.mTargetDensity;
        if (true != ninePatchState.mDither) {
            setDither(ninePatchState.mDither);
        }
        if (this.mNinePatch != null) {
            computeBitmapSize();
        }
    }

    public void setTargetDensity(Canvas canvas) {
        setTargetDensity(canvas.getDensity());
    }

    public void setTargetDensity(DisplayMetrics displayMetrics) {
        updateImage();
        this.mTargetDensity = displayMetrics.densityDpi;
        if (this.mNinePatch != null) {
            computeBitmapSize();
        }
    }

    public void setTargetDensity(int i) {
        updateImage();
        if (i == 0) {
            i = 160;
        }
        this.mTargetDensity = i;
        if (this.mNinePatch != null) {
            computeBitmapSize();
        }
    }

    private void computeBitmapSize() {
        updateImage();
        if (this.mNinePatchState.mImageSizeWhenOOM != null) {
            int[] iArr = this.mNinePatchState.mImageSizeWhenOOM;
            this.mBitmapWidth = BaseConstantState.scaleFromDensity(iArr[0], iArr[2], this.mTargetDensity);
            this.mBitmapWidth = BaseConstantState.scaleFromDensity(iArr[1], iArr[2], this.mTargetDensity);
            this.mPadding.set(0, 0, 0, 0);
            return;
        }
        int density = this.mNinePatch.getDensity();
        int i = this.mTargetDensity;
        if (density == i) {
            this.mBitmapWidth = this.mNinePatch.getWidth();
            this.mBitmapHeight = this.mNinePatch.getHeight();
            return;
        }
        this.mBitmapWidth = BaseConstantState.scaleFromDensity(this.mNinePatch.getWidth(), density, i);
        this.mBitmapHeight = BaseConstantState.scaleFromDensity(this.mNinePatch.getHeight(), density, i);
        Rect rect = this.mPadding;
        Rect rect2 = this.mNinePatchState.mPadding;
        if (rect == rect2) {
            rect = new Rect(rect2);
            this.mPadding = rect;
        }
        rect.left = BaseConstantState.scaleFromDensity(rect2.left, density, i);
        rect.top = BaseConstantState.scaleFromDensity(rect2.top, density, i);
        rect.right = BaseConstantState.scaleFromDensity(rect2.right, density, i);
        rect.bottom = BaseConstantState.scaleFromDensity(rect2.bottom, density, i);
    }

    private void updateImage() {
        if (this.mNinePatch != this.mNinePatchState.mNinePatch) {
            this.mNinePatch = this.mNinePatchState.mNinePatch;
            this.mPadding = this.mNinePatchState.mPadding;
            if (this.mNinePatchState.mImageSizeWhenOOM != null) {
                int[] iArr = this.mNinePatchState.mImageSizeWhenOOM;
                this.mBitmapWidth = BaseConstantState.scaleFromDensity(iArr[0], iArr[2], this.mTargetDensity);
                this.mBitmapWidth = BaseConstantState.scaleFromDensity(iArr[1], iArr[2], this.mTargetDensity);
                this.mPadding.set(0, 0, 0, 0);
                return;
            }
            int density = this.mNinePatch.getDensity();
            int i = this.mTargetDensity;
            if (density == i) {
                this.mBitmapWidth = this.mNinePatch.getWidth();
                this.mBitmapHeight = this.mNinePatch.getHeight();
                return;
            }
            this.mBitmapWidth = BaseConstantState.scaleFromDensity(this.mNinePatch.getWidth(), density, i);
            this.mBitmapHeight = BaseConstantState.scaleFromDensity(this.mNinePatch.getHeight(), density, i);
            Rect rect = this.mPadding;
            Rect rect2 = this.mNinePatchState.mPadding;
            if (rect == rect2) {
                rect = new Rect(rect2);
                this.mPadding = rect;
            }
            rect.left = BaseConstantState.scaleFromDensity(rect2.left, density, i);
            rect.top = BaseConstantState.scaleFromDensity(rect2.top, density, i);
            rect.right = BaseConstantState.scaleFromDensity(rect2.right, density, i);
            rect.bottom = BaseConstantState.scaleFromDensity(rect2.bottom, density, i);
        }
    }

    public void draw(Canvas canvas) {
        updateImage();
        if (this.mNinePatchState.mImageSizeWhenOOM == null) {
            Rect bounds = getBounds();
            try {
                this.mNinePatch.draw(canvas, bounds, this.mPaint);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (this.mNinePatchState.hasProblem) {
                canvas.drawRect(bounds, BaseConstantState.sColorPaint);
                canvas.drawLine((float) bounds.left, (float) bounds.top, (float) bounds.right, (float) bounds.bottom, BaseConstantState.sPaint);
                canvas.drawLine((float) bounds.right, (float) bounds.top, (float) bounds.left, (float) bounds.bottom, BaseConstantState.sPaint);
            }
        }
    }

    public int getChangingConfigurations() {
        return super.getChangingConfigurations() | this.mNinePatchState.mChangingConfigurations;
    }

    public boolean getPadding(Rect rect) {
        updateImage();
        rect.set(this.mPadding);
        return true;
    }

    boolean getOldPadding(Rect rect) {
        if (this.mNinePatchState.mOldPadding == null) {
            return false;
        }
        rect.set(this.mNinePatchState.mOldPadding);
        return true;
    }

    public void setAlpha(int i) {
        getPaint().setAlpha(i);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        getPaint().setColorFilter(colorFilter);
    }

    public void setDither(boolean z) {
        getPaint().setDither(z);
    }

    public Paint getPaint() {
        if (this.mPaint == null) {
            this.mPaint = new Paint();
            this.mPaint.setDither(true);
        }
        return this.mPaint;
    }

    public int getIntrinsicWidth() {
        updateImage();
        return this.mBitmapWidth;
    }

    public int getIntrinsicHeight() {
        updateImage();
        return this.mBitmapHeight;
    }

    public int getMinimumWidth() {
        updateImage();
        return this.mBitmapWidth;
    }

    public int getMinimumHeight() {
        updateImage();
        return this.mBitmapHeight;
    }

    public int getOpacity() {
        updateImage();
        return (this.mNinePatch == null || this.mNinePatch.hasAlpha() || (this.mPaint != null && this.mPaint.getAlpha() < 255)) ? -3 : -1;
    }

    public Region getTransparentRegion() {
        updateImage();
        return this.mNinePatch == null ? null : this.mNinePatch.getTransparentRegion(getBounds());
    }

    public ConstantState getConstantState() {
        this.mNinePatchState.mChangingConfigurations = super.getChangingConfigurations();
        return this.mNinePatchState;
    }

    public Drawable mutate() {
        return this;
    }

    public Bitmap getBitmap() {
        return this.mNinePatchState.mBitmap;
    }

    SkinnableNinePatchDrawable(NinePatchState ninePatchState, Resources resources) {
        this.mTargetDensity = 160;
        setNinePatchState(ninePatchState, resources);
    }
}
