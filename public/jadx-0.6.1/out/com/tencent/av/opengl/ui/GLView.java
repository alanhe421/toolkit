package com.tencent.av.opengl.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import com.tencent.av.opengl.glrenderer.GLCanvas;
import com.tencent.av.opengl.texture.BasicTexture;
import com.tencent.av.opengl.texture.BitmapTexture;
import com.tencent.av.opengl.texture.ResourceTexture;
import com.tencent.smtt.sdk.WebView;

public class GLView {
    protected static final int FLAG_INVISIBLE = 1;
    protected static final int FLAG_LAYOUT_REQUESTED = 4;
    protected static final int FLAG_SET_MEASURED_SIZE = 2;
    public static final int INVISIBLE = 1;
    private static final String TAG = "GLView";
    public static final int VISIBLE = 0;
    protected Animation mAnimation;
    protected BasicTexture mBackground;
    protected int mBackgroundColor = WebView.NIGHT_MODE_COLOR;
    protected int mBackgroundResource = 0;
    protected final Rect mBounds = new Rect(0, 0, 0, 0);
    protected int mLastHeightSpec = -1;
    protected int mLastWidthSpec = -1;
    protected int mMeasuredHeight = 0;
    protected int mMeasuredWidth = 0;
    protected OnTouchListener mOnTouchListener;
    protected OnZOrderChangedListener mOnZOrderChangedListener;
    protected final Rect mPaddings = new Rect(0, 0, 0, 0);
    protected GLView mParent;
    protected GLRootView mRootView;
    protected int mScrollHeight = 0;
    protected int mScrollWidth = 0;
    protected int mScrollX = 0;
    protected int mScrollY = 0;
    protected Transformation mTransformation;
    protected int mViewFlags = 0;
    protected int mZOrder = 0;

    public void setOnTouchListener(OnTouchListener onTouchListener) {
        this.mOnTouchListener = onTouchListener;
    }

    protected void setOnZOrderChangedListener(OnZOrderChangedListener onZOrderChangedListener) {
        this.mOnZOrderChangedListener = onZOrderChangedListener;
    }

    public void startAnimation(Animation animation) {
        if (getGLRootView() == null) {
            throw new IllegalStateException();
        }
        this.mAnimation = animation;
        if (this.mAnimation != null) {
            this.mAnimation.start();
        }
        invalidate();
    }

    public void setVisibility(int i) {
        if (i != getVisibility()) {
            if (i == 0) {
                this.mViewFlags &= -2;
            } else {
                this.mViewFlags |= 1;
            }
            onVisibilityChanged(i);
            invalidate();
        }
    }

    public int getVisibility() {
        return (this.mViewFlags & 1) == 0 ? 0 : 1;
    }

    public void attachToRoot(GLRootView gLRootView) {
        onAttachToRoot(gLRootView);
    }

    public void detachFromRoot() {
        onDetachFromRoot();
    }

    public void setPaddings(int i, int i2, int i3, int i4) {
        this.mPaddings.set(i, i2, i3, i4);
    }

    public Rect getPaddings() {
        return this.mPaddings;
    }

    public boolean setBounds(int i, int i2, int i3, int i4) {
        boolean z = (i3 - i == this.mBounds.right - this.mBounds.left && i4 - i2 == this.mBounds.bottom - this.mBounds.top) ? false : true;
        this.mBounds.set(i, i2, i3, i4);
        return z;
    }

    public Rect getBounds() {
        return this.mBounds;
    }

    public int getWidth() {
        return this.mBounds.right - this.mBounds.left;
    }

    public int getHeight() {
        return this.mBounds.bottom - this.mBounds.top;
    }

    public GLRootView getGLRootView() {
        return this.mRootView;
    }

    public void setRotation(int i) {
    }

    public int getZOrder() {
        return this.mZOrder;
    }

    public void setZOrder(int i) {
        if (this.mZOrder != i) {
            int i2 = this.mZOrder;
            this.mZOrder = i;
            if (this.mOnZOrderChangedListener != null) {
                this.mOnZOrderChangedListener.OnZOrderChanged(this, i, i2);
            }
        }
    }

    public void invalidate() {
        GLRootView gLRootView = getGLRootView();
        if (gLRootView != null) {
            gLRootView.requestRender();
        }
    }

    public void requestLayout() {
        this.mViewFlags |= 4;
        this.mLastHeightSpec = -1;
        this.mLastWidthSpec = -1;
        if (this.mParent != null) {
            this.mParent.requestLayout();
            return;
        }
        GLRootView gLRootView = getGLRootView();
        if (gLRootView != null) {
            gLRootView.requestLayoutContentPane();
        }
    }

    protected void render(GLCanvas gLCanvas) {
        renderBackground(gLCanvas);
    }

    public int getBackgroundColor() {
        return this.mBackgroundColor;
    }

    public void setBackgroundColor(int i) {
        this.mBackgroundColor = i;
    }

    public void setBackground(int i) {
        BasicTexture basicTexture = null;
        if (i == 0 || i != this.mBackgroundResource) {
            GLRootView gLRootView = getGLRootView();
            if (gLRootView == null) {
                throw new RuntimeException("Cannot set resource background before attach to GLRootView!");
            }
            if (this.mBackground != null) {
                this.mBackground.recycle();
                this.mBackground = null;
            }
            Context context = gLRootView.getContext();
            if (i != 0) {
                basicTexture = new ResourceTexture(context, i);
            }
            this.mBackground = basicTexture;
            this.mBackgroundResource = i;
        }
    }

    public void setBackground(Bitmap bitmap) {
        if (bitmap != null) {
            if (this.mBackground != null) {
                this.mBackground.recycle();
                this.mBackground = null;
            }
            this.mBackground = new BitmapTexture(bitmap);
            this.mBackgroundResource = 0;
        }
    }

    protected void renderBackground(GLCanvas gLCanvas) {
        if (this.mBackground != null) {
            this.mBackground.draw(gLCanvas, 0, 0, getWidth(), getHeight());
            return;
        }
        GLCanvas gLCanvas2 = gLCanvas;
        float f = 0.0f;
        gLCanvas2.fillRect(0.0f, f, (float) getWidth(), (float) getHeight(), this.mBackgroundColor);
    }

    protected boolean onTouchEvent(MotionEvent motionEvent) {
        return false;
    }

    protected boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (getVisibility() == 0 && this.mOnTouchListener != null && this.mOnTouchListener.onTouch(this, motionEvent)) {
            return true;
        }
        return onTouchEvent(motionEvent);
    }

    @SuppressLint({"WrongCall"})
    public void layout(int i, int i2, int i3, int i4) {
        boolean bounds = setBounds(i, i2, i3, i4);
        this.mViewFlags &= -5;
        onLayout(bounds, i, i2, i3, i4);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
    }

    @SuppressLint({"WrongCall"})
    public void measure(int i, int i2) {
        if (i != this.mLastWidthSpec || i2 != this.mLastHeightSpec || (this.mViewFlags & 4) != 0) {
            this.mLastWidthSpec = i;
            this.mLastHeightSpec = i2;
            this.mViewFlags &= -3;
            onMeasure(i, i2);
            if ((this.mViewFlags & 2) == 0) {
                throw new IllegalStateException(getClass().getName() + " should call setMeasuredSize() in onMeasure()");
            }
        }
    }

    protected void onMeasure(int i, int i2) {
    }

    protected void setMeasuredSize(int i, int i2) {
        this.mViewFlags |= 2;
        this.mMeasuredWidth = i;
        this.mMeasuredHeight = i2;
    }

    public int getMeasuredWidth() {
        return this.mMeasuredWidth;
    }

    public int getMeasuredHeight() {
        return this.mMeasuredHeight;
    }

    public boolean getBoundsOf(GLView gLView, Rect rect) {
        int i = 0;
        int i2 = 0;
        for (GLView gLView2 = gLView; gLView2 != this; gLView2 = gLView2.mParent) {
            if (gLView2 == null) {
                return false;
            }
            Rect rect2 = gLView2.mBounds;
            i2 += rect2.left;
            i += rect2.top;
        }
        rect.set(i2, i, gLView.getWidth() + i2, gLView.getHeight() + i);
        return true;
    }

    protected void onVisibilityChanged(int i) {
    }

    protected void onAttachToRoot(GLRootView gLRootView) {
        this.mRootView = gLRootView;
    }

    protected void onDetachFromRoot() {
        this.mRootView = null;
    }

    protected void onFirstDraw() {
    }

    public void lockRendering() {
        if (this.mRootView != null) {
            this.mRootView.lockRenderThread();
        }
    }

    public void unlockRendering() {
        if (this.mRootView != null) {
            this.mRootView.unlockRenderThread();
        }
    }

    protected void finalize() throws Throwable {
        super.finalize();
        if (this.mBackground != null) {
            this.mBackground.recycle();
            this.mBackground = null;
            this.mBackgroundResource = 0;
        }
    }
}
