package com.tencent.theme;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import java.io.IOException;
import java.io.InputStream;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class SkinnableBitmapDrawable extends Drawable {
    private static final int[] BitmapDrawable = new int[]{16843033, 16843034, 16843035, 16843036, 16842927, 16843265};
    private static final int DEFAULT_PAINT_FLAGS = 6;
    private boolean mApplyGravity;
    private Bitmap mBitmap;
    private int mBitmapHeight;
    private BitmapState mBitmapState;
    private int mBitmapWidth;
    private final Rect mDstRect;
    private boolean mMutated;
    private boolean mRebuildShader;
    private int mTargetDensity;

    static final class BitmapState extends BaseConstantState {
        Bitmap mBitmap;
        boolean mBuildFromXml;
        int mChangingConfigurations;
        int mGravity;
        Paint mPaint;
        int mTargetDensity;
        TileMode mTileModeX;
        TileMode mTileModeY;

        BitmapState(Bitmap bitmap) {
            this.mGravity = Opcodes.INVOKE_STATIC_RANGE;
            this.mPaint = new Paint(6);
            this.mTargetDensity = 160;
            this.mBuildFromXml = false;
            this.mBitmap = bitmap;
        }

        BitmapState(BitmapState bitmapState) {
            this(bitmapState.mBitmap);
            this.mChangingConfigurations = bitmapState.mChangingConfigurations;
            this.mGravity = bitmapState.mGravity;
            this.mTileModeX = bitmapState.mTileModeX;
            this.mTileModeY = bitmapState.mTileModeY;
            this.mTargetDensity = bitmapState.mTargetDensity;
            this.mPaint = new Paint(bitmapState.mPaint);
        }

        public Drawable newDrawable() {
            return new SkinnableBitmapDrawable(this, null);
        }

        public Drawable newDrawable(Resources resources) {
            return new SkinnableBitmapDrawable(this, resources);
        }

        public int getChangingConfigurations() {
            return this.mChangingConfigurations;
        }
    }

    SkinnableBitmapDrawable() {
        this.mDstRect = new Rect();
        this.mBitmapState = new BitmapState((Bitmap) null);
    }

    public SkinnableBitmapDrawable(Resources resources) {
        this.mDstRect = new Rect();
        this.mBitmapState = new BitmapState((Bitmap) null);
        this.mBitmapState.mTargetDensity = this.mTargetDensity;
    }

    @Deprecated
    public SkinnableBitmapDrawable(Bitmap bitmap) {
        this(new BitmapState(bitmap), null);
    }

    public SkinnableBitmapDrawable(Resources resources, Bitmap bitmap) {
        this(new BitmapState(bitmap), resources);
        this.mBitmapState.mTargetDensity = this.mTargetDensity;
    }

    @Deprecated
    public SkinnableBitmapDrawable(String str) {
        this(new BitmapState(BitmapFactory.decodeFile(str)), null);
        if (this.mBitmap == null) {
            Log.w("BitmapDrawable", "BitmapDrawable cannot decode " + str);
        }
    }

    public SkinnableBitmapDrawable(Resources resources, String str) {
        this(new BitmapState(BitmapFactory.decodeFile(str)), null);
        this.mBitmapState.mTargetDensity = this.mTargetDensity;
        if (this.mBitmap == null) {
            Log.w("BitmapDrawable", "BitmapDrawable cannot decode " + str);
        }
    }

    @Deprecated
    public SkinnableBitmapDrawable(InputStream inputStream) {
        this(new BitmapState(BitmapFactory.decodeStream(inputStream)), null);
        if (this.mBitmap == null) {
            Log.w("BitmapDrawable", "BitmapDrawable cannot decode " + inputStream);
        }
    }

    public SkinnableBitmapDrawable(Resources resources, InputStream inputStream) {
        this(new BitmapState(BitmapFactory.decodeStream(inputStream)), null);
        this.mBitmapState.mTargetDensity = this.mTargetDensity;
        if (this.mBitmap == null) {
            Log.w("BitmapDrawable", "BitmapDrawable cannot decode " + inputStream);
        }
    }

    public final Paint getPaint() {
        return this.mBitmapState.mPaint;
    }

    public final Bitmap getBitmap() {
        updateBitmap();
        return this.mBitmap;
    }

    private void computeBitmapSize() {
        updateBitmap();
        if (this.mBitmapState.mImageSizeWhenOOM != null) {
            int[] iArr = this.mBitmapState.mImageSizeWhenOOM;
            this.mBitmapWidth = BaseConstantState.scaleFromDensity(iArr[0], iArr[2], this.mTargetDensity);
            this.mBitmapWidth = BaseConstantState.scaleFromDensity(iArr[1], iArr[2], this.mTargetDensity);
            return;
        }
        this.mBitmapWidth = this.mBitmap.getScaledWidth(this.mTargetDensity);
        this.mBitmapHeight = this.mBitmap.getScaledHeight(this.mTargetDensity);
    }

    private void setBitmap(Bitmap bitmap) {
        this.mBitmap = bitmap;
        if (bitmap != null) {
            computeBitmapSize();
            return;
        }
        this.mBitmapHeight = -1;
        this.mBitmapWidth = -1;
    }

    public void setTargetDensity(Canvas canvas) {
        setTargetDensity(canvas.getDensity());
    }

    public void setTargetDensity(DisplayMetrics displayMetrics) {
        this.mTargetDensity = displayMetrics.densityDpi;
        updateBitmap();
        if (this.mBitmap != null) {
            computeBitmapSize();
        }
    }

    public void setTargetDensity(int i) {
        if (i == 0) {
            i = 160;
        }
        this.mTargetDensity = i;
        updateBitmap();
        if (this.mBitmap != null) {
            computeBitmapSize();
        }
    }

    public int getGravity() {
        return this.mBitmapState.mGravity;
    }

    public void setGravity(int i) {
        this.mBitmapState.mGravity = i;
        this.mApplyGravity = true;
    }

    public void setAntiAlias(boolean z) {
        this.mBitmapState.mPaint.setAntiAlias(z);
    }

    public void setFilterBitmap(boolean z) {
        this.mBitmapState.mPaint.setFilterBitmap(z);
    }

    public void setDither(boolean z) {
        this.mBitmapState.mPaint.setDither(z);
    }

    public TileMode getTileModeX() {
        return this.mBitmapState.mTileModeX;
    }

    public TileMode getTileModeY() {
        return this.mBitmapState.mTileModeY;
    }

    public void setTileModeX(TileMode tileMode) {
        setTileModeXY(tileMode, this.mBitmapState.mTileModeY);
    }

    public final void setTileModeY(TileMode tileMode) {
        setTileModeXY(this.mBitmapState.mTileModeX, tileMode);
    }

    public void setTileModeXY(TileMode tileMode, TileMode tileMode2) {
        BitmapState bitmapState = this.mBitmapState;
        if (bitmapState.mPaint.getShader() == null || bitmapState.mTileModeX != tileMode || bitmapState.mTileModeY != tileMode2) {
            bitmapState.mTileModeX = tileMode;
            bitmapState.mTileModeY = tileMode2;
            this.mRebuildShader = true;
        }
    }

    public int getChangingConfigurations() {
        return super.getChangingConfigurations() | this.mBitmapState.mChangingConfigurations;
    }

    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.mApplyGravity = true;
    }

    private void updateBitmap() {
        if (this.mBitmap != this.mBitmapState.mBitmap) {
            this.mBitmap = this.mBitmapState.mBitmap;
            if (this.mBitmapState.mBuildFromXml) {
                this.mRebuildShader = true;
                this.mApplyGravity = true;
            }
            if (this.mBitmapState.mImageSizeWhenOOM != null) {
                int[] iArr = this.mBitmapState.mImageSizeWhenOOM;
                this.mBitmapWidth = BaseConstantState.scaleFromDensity(iArr[0], iArr[2], this.mTargetDensity);
                this.mBitmapWidth = BaseConstantState.scaleFromDensity(iArr[1], iArr[2], this.mTargetDensity);
                return;
            }
            this.mBitmapWidth = this.mBitmap.getScaledWidth(this.mTargetDensity);
            this.mBitmapHeight = this.mBitmap.getScaledHeight(this.mTargetDensity);
        }
    }

    public void draw(Canvas canvas) {
        updateBitmap();
        if (this.mBitmapState.mImageSizeWhenOOM == null) {
            Bitmap bitmap = this.mBitmap;
            if (bitmap == null) {
                return;
            }
            if (this.mBitmapState.bitmapType == 1) {
                setGravity(48);
                canvas.drawBitmap(this.mBitmap, getImageDrawnRect(getBounds()), getBounds(), this.mBitmapState.mPaint);
                return;
            }
            BitmapState bitmapState = this.mBitmapState;
            if (this.mRebuildShader) {
                TileMode tileMode = bitmapState.mTileModeX;
                TileMode tileMode2 = bitmapState.mTileModeY;
                if (tileMode == null && tileMode2 == null) {
                    bitmapState.mPaint.setShader(null);
                } else {
                    if (tileMode == null) {
                        tileMode = TileMode.CLAMP;
                    }
                    if (tileMode2 == null) {
                        tileMode2 = TileMode.CLAMP;
                    }
                    bitmapState.mPaint.setShader(new BitmapShader(bitmap, tileMode, tileMode2));
                }
                this.mRebuildShader = false;
                copyBounds(this.mDstRect);
            }
            if (bitmapState.mPaint.getShader() == null) {
                if (this.mApplyGravity) {
                    Gravity.apply(bitmapState.mGravity, this.mBitmapWidth, this.mBitmapHeight, getBounds(), this.mDstRect);
                    this.mApplyGravity = false;
                }
                canvas.drawBitmap(bitmap, null, this.mDstRect, bitmapState.mPaint);
                return;
            }
            if (this.mApplyGravity) {
                this.mDstRect.set(getBounds());
                this.mApplyGravity = false;
            }
            canvas.drawRect(this.mDstRect, bitmapState.mPaint);
        }
    }

    protected Rect getImageDrawnRect(Rect rect) {
        int height = getBitmap().getHeight();
        int width = getBitmap().getWidth();
        if (rect == null) {
            return new Rect(0, 0, width, height);
        }
        if (((float) rect.height()) / ((float) rect.width()) < ((float) height) / ((float) width)) {
            return new Rect(0, 0, width, (rect.height() * width) / rect.width());
        }
        int width2 = (rect.width() * height) / rect.height();
        width = (int) (((double) (width - width2)) * 0.5d);
        return new Rect(width, 0, width2 + width, height);
    }

    public void setAlpha(int i) {
        this.mBitmapState.mPaint.setAlpha(i);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.mBitmapState.mPaint.setColorFilter(colorFilter);
    }

    public Drawable mutate() {
        return this;
    }

    static BitmapState inflateBitmapState(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, AttributeSet attributeSet2, boolean z) throws XmlPullParserException, IOException {
        int i;
        int attributeResourceValue;
        int attributeCount = attributeSet.getAttributeCount();
        BitmapState bitmapState = new BitmapState((Bitmap) null);
        bitmapState.mBuildFromXml = true;
        Paint paint = new Paint();
        for (i = 0; i < attributeCount; i++) {
            switch (attributeSet.getAttributeNameResource(i)) {
                case 16842927:
                    bitmapState.mGravity = attributeSet.getAttributeIntValue(i, Opcodes.INVOKE_STATIC_RANGE);
                    break;
                case 16843033:
                    attributeResourceValue = attributeSet.getAttributeResourceValue(i, 0);
                    if (attributeResourceValue != 0) {
                        BitmapState bitmapState2 = (BitmapState) SkinEngine.getInstances().loadConstantState(attributeResourceValue);
                        bitmapState.mBitmap = bitmapState2.mBitmap;
                        bitmapState.mImageSizeWhenOOM = bitmapState2.mImageSizeWhenOOM;
                        break;
                    }
                    throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": <bitmap> requires a valid src attribute");
                case 16843034:
                    paint.setAntiAlias(attributeSet.getAttributeBooleanValue(i, paint.isAntiAlias()));
                    break;
                case 16843035:
                    paint.setFilterBitmap(attributeSet.getAttributeBooleanValue(i, paint.isFilterBitmap()));
                    break;
                case 16843036:
                    paint.setDither(attributeSet.getAttributeBooleanValue(i, paint.isDither()));
                    break;
                case 16843265:
                    attributeResourceValue = attributeSet.getAttributeIntValue(i, -1);
                    if (attributeResourceValue == -1) {
                        break;
                    }
                    switch (attributeResourceValue) {
                        case 0:
                            bitmapState.mTileModeX = TileMode.CLAMP;
                            bitmapState.mTileModeY = TileMode.CLAMP;
                            break;
                        case 1:
                            bitmapState.mTileModeX = TileMode.REPEAT;
                            bitmapState.mTileModeY = TileMode.REPEAT;
                            break;
                        case 2:
                            bitmapState.mTileModeX = TileMode.MIRROR;
                            bitmapState.mTileModeY = TileMode.MIRROR;
                            break;
                        default:
                            break;
                    }
                default:
                    break;
            }
        }
        if (attributeSet2 != null) {
            if (z) {
                i = attributeSet2.getAttributeCount();
                for (attributeResourceValue = 0; attributeResourceValue < i; attributeResourceValue++) {
                    switch (attributeSet2.getAttributeNameResource(attributeResourceValue)) {
                        case 16842927:
                            bitmapState.mGravity = attributeSet2.getAttributeIntValue(attributeResourceValue, Opcodes.INVOKE_STATIC_RANGE);
                            break;
                        case 16843034:
                            paint.setAntiAlias(attributeSet2.getAttributeBooleanValue(attributeResourceValue, paint.isAntiAlias()));
                            break;
                        case 16843035:
                            paint.setFilterBitmap(attributeSet2.getAttributeBooleanValue(attributeResourceValue, paint.isFilterBitmap()));
                            break;
                        case 16843036:
                            paint.setDither(attributeSet2.getAttributeBooleanValue(attributeResourceValue, paint.isDither()));
                            break;
                        case 16843265:
                            attributeCount = attributeSet2.getAttributeIntValue(attributeResourceValue, -2);
                            if (attributeCount == -2) {
                                break;
                            }
                            switch (attributeCount) {
                                case -1:
                                    bitmapState.mTileModeX = null;
                                    bitmapState.mTileModeY = null;
                                    break;
                                case 0:
                                    bitmapState.mTileModeX = TileMode.CLAMP;
                                    bitmapState.mTileModeY = TileMode.CLAMP;
                                    break;
                                case 1:
                                    bitmapState.mTileModeX = TileMode.REPEAT;
                                    bitmapState.mTileModeY = TileMode.REPEAT;
                                    break;
                                case 2:
                                    bitmapState.mTileModeX = TileMode.MIRROR;
                                    bitmapState.mTileModeY = TileMode.MIRROR;
                                    break;
                                default:
                                    break;
                            }
                        default:
                            break;
                    }
                }
            } else {
                int attributeCount2 = attributeSet2.getAttributeCount();
                for (attributeCount = 0; attributeCount < attributeCount2; attributeCount++) {
                    String attributeName = attributeSet2.getAttributeName(attributeCount);
                    if ("antialias".equals(attributeName)) {
                        paint.setAntiAlias(attributeSet2.getAttributeBooleanValue(attributeCount, paint.isAntiAlias()));
                    } else if ("filter".equals(attributeName)) {
                        paint.setAntiAlias(attributeSet2.getAttributeBooleanValue(attributeCount, paint.isAntiAlias()));
                    } else if ("dither".equals(attributeName)) {
                        paint.setAntiAlias(attributeSet2.getAttributeBooleanValue(attributeCount, paint.isAntiAlias()));
                    } else if ("gravity".equals(attributeName)) {
                        String attributeValue = attributeSet2.getAttributeValue(attributeCount);
                        attributeResourceValue = 0;
                        for (Object obj : attributeValue.split("\\|")) {
                            if ("top".equals(obj)) {
                                attributeResourceValue |= 48;
                            } else if ("bottom".equals(obj)) {
                                attributeResourceValue |= 80;
                            } else if ("left".equals(obj)) {
                                attributeResourceValue |= 3;
                            } else if ("right".equals(obj)) {
                                attributeResourceValue |= 5;
                            } else if ("center_vertical".equals(obj)) {
                                attributeResourceValue |= 16;
                            } else if ("fill_vertical".equals(obj)) {
                                attributeResourceValue |= 112;
                            } else if ("center_horizontal".equals(obj)) {
                                attributeResourceValue |= 1;
                            } else if ("fill_horizontal".equals(obj)) {
                                attributeResourceValue |= 7;
                            } else if ("center".equals(obj)) {
                                attributeResourceValue |= 17;
                            } else if ("fill".equals(obj)) {
                                attributeResourceValue |= Opcodes.INVOKE_STATIC_RANGE;
                            } else if ("clip_vertical".equals(obj)) {
                                attributeResourceValue |= 128;
                            } else if ("clip_horizontal".equals(obj)) {
                                attributeResourceValue |= 8;
                            } else if (MessageKey.MSG_ACCEPT_TIME_START.equals(obj)) {
                                attributeResourceValue |= 8388611;
                            } else if (MessageKey.MSG_ACCEPT_TIME_END.equals(obj)) {
                                attributeResourceValue |= 8388613;
                            } else {
                                throw new XmlPullParserException("error attribute value: " + attributeValue);
                            }
                        }
                        bitmapState.mGravity = attributeResourceValue;
                    } else if ("tileMode".equals(attributeName)) {
                        attributeName = attributeSet2.getAttributeValue(attributeCount);
                        if ("clamp".equals(attributeName)) {
                            bitmapState.mTileModeX = TileMode.CLAMP;
                            bitmapState.mTileModeY = TileMode.CLAMP;
                        } else if ("repeat".equals(attributeName)) {
                            bitmapState.mTileModeX = TileMode.REPEAT;
                            bitmapState.mTileModeY = TileMode.REPEAT;
                        } else if ("mirror".equals(attributeName)) {
                            bitmapState.mTileModeX = TileMode.MIRROR;
                            bitmapState.mTileModeY = TileMode.MIRROR;
                        } else if ("disabled".equals(attributeName)) {
                            bitmapState.mTileModeX = null;
                            bitmapState.mTileModeY = null;
                        } else {
                            throw new XmlPullParserException("error attribute value: " + attributeName);
                        }
                    } else {
                        continue;
                    }
                }
            }
        }
        if (bitmapState.mBitmap != null || bitmapState.mImageSizeWhenOOM != null) {
            return bitmapState;
        }
        throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": <bitmap> requires a valid src attribute");
    }

    public int getIntrinsicWidth() {
        updateBitmap();
        return this.mBitmapWidth;
    }

    public int getIntrinsicHeight() {
        updateBitmap();
        return this.mBitmapHeight;
    }

    public int getOpacity() {
        if (this.mBitmapState.mGravity != Opcodes.INVOKE_STATIC_RANGE) {
            return -3;
        }
        updateBitmap();
        Bitmap bitmap = this.mBitmap;
        if (bitmap == null || bitmap.hasAlpha() || this.mBitmapState.mPaint.getAlpha() < 255) {
            return -3;
        }
        return -1;
    }

    public final ConstantState getConstantState() {
        return this.mBitmapState;
    }

    SkinnableBitmapDrawable(BitmapState bitmapState, Resources resources) {
        this.mDstRect = new Rect();
        this.mBitmapState = bitmapState;
        if (resources != null) {
            this.mTargetDensity = resources.getDisplayMetrics().densityDpi;
        } else if (bitmapState != null) {
            this.mTargetDensity = bitmapState.mTargetDensity;
        } else {
            this.mTargetDensity = 160;
        }
        setBitmap(bitmapState.mBitmap);
        if (bitmapState.mBuildFromXml) {
            this.mRebuildShader = true;
            this.mApplyGravity = true;
        }
    }
}
