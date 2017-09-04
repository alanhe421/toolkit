package com.tencent.av.opengl.texture;

import android.graphics.Bitmap;
import android.opengl.GLUtils;
import com.tencent.av.opengl.glrenderer.GLCanvas;
import junit.framework.Assert;

public abstract class UploadedTexture extends BasicTexture {
    private static final String TAG = "UploadedTexture";
    private static final int UPLOAD_LIMIT = 100;
    private static int sUploadedCount;
    protected Bitmap mBitmap;
    private boolean mContentValid = true;
    private boolean mIsUploading = false;
    private boolean mOpaque = true;
    private boolean mThrottled = false;

    protected abstract void onFreeBitmap(Bitmap bitmap);

    protected abstract Bitmap onGetBitmap();

    protected UploadedTexture() {
        super(null, 0, 0);
    }

    protected void setIsUploading(boolean z) {
        this.mIsUploading = z;
    }

    public boolean isUploading() {
        return this.mIsUploading;
    }

    protected void setThrottled(boolean z) {
        this.mThrottled = z;
    }

    private Bitmap getBitmap() {
        if (this.mBitmap == null) {
            this.mBitmap = onGetBitmap();
            int width = this.mBitmap.getWidth();
            int height = this.mBitmap.getHeight();
            if (this.mWidth == -1) {
                setTextureSize(width, height);
            }
        }
        return this.mBitmap;
    }

    private void freeBitmap() {
        Assert.assertTrue(this.mBitmap != null);
        onFreeBitmap(this.mBitmap);
        this.mBitmap = null;
    }

    public int getSourceWidth() {
        if (this.mWidth == -1) {
            getBitmap();
        }
        return this.mWidth;
    }

    public int getSourceHeight() {
        if (this.mWidth == -1) {
            getBitmap();
        }
        return this.mHeight;
    }

    protected void invalidateContent() {
        if (this.mBitmap != null) {
            freeBitmap();
        }
        this.mContentValid = false;
        this.mWidth = -1;
        this.mHeight = -1;
    }

    public boolean isContentValid() {
        return isLoaded() && this.mContentValid;
    }

    public void updateContent(GLCanvas gLCanvas) {
        if (!isLoaded()) {
            if (this.mThrottled) {
                int i = sUploadedCount + 1;
                sUploadedCount = i;
                if (i > 100) {
                    return;
                }
            }
            uploadToCanvas(gLCanvas);
        } else if (!this.mContentValid) {
            Bitmap bitmap = getBitmap();
            gLCanvas.texSubImage2D(this, 0, 0, bitmap, GLUtils.getInternalFormat(bitmap), GLUtils.getType(bitmap));
            freeBitmap();
            this.mContentValid = true;
        }
    }

    public static void resetUploadLimit() {
        sUploadedCount = 0;
    }

    public static boolean uploadLimitReached() {
        return sUploadedCount > 100;
    }

    private void uploadToCanvas(GLCanvas gLCanvas) {
        boolean z = false;
        Bitmap bitmap = getBitmap();
        if (bitmap != null) {
            try {
                int width = bitmap.getWidth();
                int height = bitmap.getHeight();
                int textureWidth = getTextureWidth();
                int textureHeight = getTextureHeight();
                if (width <= textureWidth && height <= textureHeight) {
                    z = true;
                }
                Assert.assertTrue(z);
                if (this.mId == null) {
                    this.mId = new int[1];
                }
                this.mId[0] = gLCanvas.getGLId().generateTexture();
                gLCanvas.setTextureParameters(this);
                if (width == textureWidth && height == textureHeight) {
                    gLCanvas.initializeTexture(this, bitmap);
                } else {
                    textureHeight = GLUtils.getInternalFormat(bitmap);
                    int type = GLUtils.getType(bitmap);
                    gLCanvas.initializeTextureSize(this, textureHeight, type);
                    gLCanvas.texSubImage2D(this, 0, 0, bitmap, textureHeight, type);
                }
                freeBitmap();
                setAssociatedCanvas(gLCanvas);
                this.mState = 1;
                this.mContentValid = true;
            } catch (Throwable th) {
                freeBitmap();
            }
        } else {
            this.mState = -1;
            throw new RuntimeException("Texture load fail, no bitmap");
        }
    }

    public boolean onBind(GLCanvas gLCanvas) {
        updateContent(gLCanvas);
        return isContentValid();
    }

    public int getTarget() {
        return 3553;
    }

    public void setOpaque(boolean z) {
        this.mOpaque = z;
    }

    public boolean isOpaque() {
        return this.mOpaque;
    }

    public void recycle() {
        super.recycle();
        if (this.mBitmap != null) {
            freeBitmap();
        }
    }
}
