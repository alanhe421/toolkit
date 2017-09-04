package com.tencent.av.opengl.texture;

import android.util.Log;
import com.tencent.av.opengl.glrenderer.GLCanvas;
import java.util.WeakHashMap;

public abstract class BasicTexture {
    public static final int FORMAT_RGB = 0;
    public static final int FORMAT_YUV = 1;
    private static final int MAX_TEXTURE_SIZE = 4096;
    protected static final int STATE_ERROR = -1;
    protected static final int STATE_LOADED = 1;
    protected static final int STATE_UNLOADED = 0;
    private static final String TAG = "BasicTexture";
    protected static final int UNSPECIFIED = -1;
    private static WeakHashMap<BasicTexture, Object> sAllTextures = new WeakHashMap();
    private static ThreadLocal<Class<BasicTexture>> sInFinalizer = new ThreadLocal();
    protected GLCanvas mCanvasRef;
    protected int mHeight;
    protected int[] mId;
    int mLeft;
    protected int mState;
    protected int mTextureHeight;
    protected int mTextureWidth;
    int mTop;
    protected int mWidth;

    public abstract int getTarget();

    public abstract boolean isOpaque();

    public abstract boolean onBind(GLCanvas gLCanvas);

    protected BasicTexture(GLCanvas gLCanvas, int i, int i2) {
        this.mWidth = -1;
        this.mHeight = -1;
        this.mCanvasRef = null;
        this.mLeft = 0;
        this.mTop = 0;
        setAssociatedCanvas(gLCanvas);
        this.mState = i2;
        synchronized (sAllTextures) {
            sAllTextures.put(this, null);
        }
    }

    protected BasicTexture() {
        this(null, 0, 0);
    }

    protected void setAssociatedCanvas(GLCanvas gLCanvas) {
        this.mCanvasRef = gLCanvas;
    }

    public int getFormatType() {
        return 0;
    }

    public boolean isFlippedVertically() {
        return false;
    }

    public int[] getId() {
        return this.mId;
    }

    public void setSourceSize(int i, int i2) {
        this.mWidth = i;
        this.mHeight = i2;
    }

    public void setTextureSize(int i, int i2) {
        this.mTextureWidth = i;
        this.mTextureHeight = i2;
        if (this.mTextureWidth > 4096 || this.mTextureHeight > 4096) {
            Log.w(TAG, String.format("texture is too large: %d x %d", new Object[]{Integer.valueOf(this.mTextureWidth), Integer.valueOf(this.mTextureHeight)}), new Exception());
        }
        if (this.mWidth == -1) {
            this.mWidth = i;
            this.mHeight = i2;
        }
    }

    public void setSourceLeft(int i) {
        this.mLeft = i;
    }

    public void setSourceTop(int i) {
        this.mTop = i;
    }

    public int getSourceLeft() {
        return this.mLeft;
    }

    public int getSourceTop() {
        return this.mTop;
    }

    public int getSourceWidth() {
        return this.mWidth;
    }

    public int getSourceHeight() {
        return this.mHeight;
    }

    public int getTextureWidth() {
        return this.mTextureWidth;
    }

    public int getTextureHeight() {
        return this.mTextureHeight;
    }

    public void draw(GLCanvas gLCanvas, int i, int i2) {
        gLCanvas.drawTexture(this, i, i2, getSourceWidth(), getSourceHeight());
    }

    public void draw(GLCanvas gLCanvas, int i, int i2, int i3, int i4) {
        gLCanvas.drawTexture(this, i, i2, i3, i4);
    }

    public boolean isLoaded() {
        return this.mState == 1;
    }

    public void recycle() {
        freeResource();
    }

    public void yield() {
        freeResource();
    }

    private void freeResource() {
        GLCanvas gLCanvas = this.mCanvasRef;
        if (!(gLCanvas == null || this.mId == null)) {
            gLCanvas.unloadTexture(this);
            this.mId = null;
        }
        this.mState = 0;
        setAssociatedCanvas(null);
    }

    protected void finalize() {
        sInFinalizer.set(BasicTexture.class);
        recycle();
        sInFinalizer.set(null);
    }

    public static boolean inFinalizer() {
        return sInFinalizer.get() != null;
    }

    public static void yieldAllTextures() {
        synchronized (sAllTextures) {
            for (BasicTexture yield : sAllTextures.keySet()) {
                yield.yield();
            }
        }
    }

    public static void invalidateAllTextures() {
        synchronized (sAllTextures) {
            for (BasicTexture basicTexture : sAllTextures.keySet()) {
                basicTexture.mState = 0;
                basicTexture.setAssociatedCanvas(null);
            }
        }
    }
}
