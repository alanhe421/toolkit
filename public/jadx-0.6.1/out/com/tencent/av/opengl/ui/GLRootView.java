package com.tencent.av.opengl.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.os.Process;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import com.tencent.av.opengl.glrenderer.GLCanvas;
import com.tencent.av.opengl.glrenderer.GLES11Canvas;
import com.tencent.av.opengl.glrenderer.GLES20Canvas;
import com.tencent.av.opengl.texture.BasicTexture;
import com.tencent.av.opengl.texture.UploadedTexture;
import com.tencent.av.opengl.utils.Utils;
import com.tencent.av.utils.QLog;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

public class GLRootView extends GLSurfaceView implements Renderer {
    private static final boolean DEBUG_DRAWING_STAT = false;
    private static final boolean DEBUG_FPS = false;
    private static final int FLAG_INITIALIZED = 1;
    private static final int FLAG_NEED_LAYOUT = 2;
    private static final String TAG = "GLRootView";
    private GLCanvas mCanvas;
    protected GLView mContentView;
    private boolean mFirstDraw;
    private int mFlags;
    private int mFrameCount;
    private long mFrameCountingStart;
    private boolean mFreeze;
    private final Condition mFreezeCondition;
    private GL11 mGL;
    private boolean mInDownState;
    long mLastRenderTime;
    private final ReentrantLock mRenderLock;
    private volatile boolean mRenderRequested;
    private Runnable mRequestRenderOnAnimationFrame;

    public GLRootView(Context context) {
        this(context, null);
    }

    public GLRootView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mFrameCount = 0;
        this.mFrameCountingStart = 0;
        this.mFlags = 2;
        this.mRenderRequested = false;
        this.mRenderLock = new ReentrantLock();
        this.mFreezeCondition = this.mRenderLock.newCondition();
        this.mInDownState = false;
        this.mFirstDraw = true;
        this.mLastRenderTime = 0;
        this.mRequestRenderOnAnimationFrame = new Runnable() {
            public void run() {
                GLRootView.this.superRequestRender();
            }
        };
        if (!isInEditMode()) {
            this.mFlags |= 1;
            setBackgroundDrawable(null);
            setEGLContextClientVersion(Utils.getGLVersion(context));
            if (Utils.USE_888_PIXEL_FORMAT) {
                setEGLConfigChooser(8, 8, 8, 0, 0, 0);
            } else {
                setEGLConfigChooser(5, 6, 5, 0, 0, 0);
            }
            setRenderer(this);
            if (Utils.USE_888_PIXEL_FORMAT) {
                getHolder().setFormat(3);
            } else {
                getHolder().setFormat(4);
            }
        }
    }

    public void setContentPane(GLView gLView) {
        if (this.mContentView != gLView) {
            if (this.mContentView != null) {
                if (this.mInDownState) {
                    long uptimeMillis = SystemClock.uptimeMillis();
                    MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
                    this.mContentView.dispatchTouchEvent(obtain);
                    obtain.recycle();
                    this.mInDownState = false;
                }
                this.mContentView.detachFromRoot();
                BasicTexture.yieldAllTextures();
            }
            this.mContentView = gLView;
            if (gLView != null) {
                gLView.attachToRoot(this);
                requestLayoutContentPane();
            }
        }
    }

    public void requestRenderForced() {
        superRequestRender();
    }

    @SuppressLint({"NewApi"})
    public void requestRender() {
        if (!this.mRenderRequested) {
            this.mRenderRequested = true;
            long elapsedRealtime = SystemClock.elapsedRealtime() - this.mLastRenderTime;
            if (elapsedRealtime > 0 && elapsedRealtime < 33) {
                super.postDelayed(this.mRequestRenderOnAnimationFrame, 33 - elapsedRealtime);
            } else if (Utils.HAS_POST_ON_ANIMATION) {
                postOnAnimation(this.mRequestRenderOnAnimationFrame);
            } else {
                super.requestRender();
            }
        }
    }

    private void superRequestRender() {
        super.requestRender();
    }

    public void requestLayoutContentPane() {
        this.mRenderLock.lock();
        try {
            if (this.mContentView == null || (this.mFlags & 2) != 0) {
                this.mRenderLock.unlock();
            } else if ((this.mFlags & 1) == 0) {
                this.mRenderLock.unlock();
            } else {
                this.mFlags |= 2;
                requestRender();
                this.mRenderLock.unlock();
            }
        } catch (Throwable th) {
            this.mRenderLock.unlock();
        }
    }

    private void layoutContentPane() {
        this.mFlags &= -3;
        int width = getWidth();
        int height = getHeight();
        if (this.mContentView != null && width != 0 && height != 0) {
            this.mContentView.layout(0, 0, width, height);
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (z) {
            requestLayoutContentPane();
        }
    }

    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        GL11 gl11 = (GL11) gl10;
        if (this.mGL != null && QLog.isColorLevel()) {
            QLog.d(TAG, 0, "GLObject has changed from " + this.mGL + " to " + gl11);
        }
        this.mRenderLock.lock();
        try {
            this.mGL = gl11;
            this.mCanvas = Utils.getGLVersion(getContext().getApplicationContext()) >= 2 ? new GLES20Canvas() : new GLES11Canvas(gl11);
            BasicTexture.invalidateAllTextures();
            setRenderMode(0);
        } finally {
            this.mRenderLock.unlock();
        }
    }

    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
        if (QLog.isColorLevel()) {
            QLog.i(TAG, 0, "onSurfaceChanged: " + i + "x" + i2 + ", gl10: " + gl10.toString());
        }
        Process.setThreadPriority(-4);
        this.mCanvas.setSize(i, i2);
    }

    private void outputFps() {
        long nanoTime = System.nanoTime();
        if (this.mFrameCountingStart == 0) {
            this.mFrameCountingStart = nanoTime;
        } else if (nanoTime - this.mFrameCountingStart > 1000000000) {
            if (QLog.isColorLevel()) {
                QLog.d(TAG, 0, "fps: " + ((((double) this.mFrameCount) * 1.0E9d) / ((double) (nanoTime - this.mFrameCountingStart))));
            }
            this.mFrameCountingStart = nanoTime;
            this.mFrameCount = 0;
        }
        this.mFrameCount++;
    }

    public void onDrawFrame(GL10 gl10) {
        AnimationTime.update();
        this.mRenderLock.lock();
        while (this.mFreeze) {
            this.mFreezeCondition.awaitUninterruptibly();
        }
        try {
            onDrawFrameLocked(gl10);
            if (this.mFirstDraw && this.mContentView != null) {
                this.mFirstDraw = false;
                this.mContentView.onFirstDraw();
            }
            this.mLastRenderTime = SystemClock.elapsedRealtime();
        } finally {
            this.mRenderLock.unlock();
        }
    }

    protected void onDrawFrameLocked(GL10 gl10) {
        this.mCanvas.deleteRecycledResources();
        UploadedTexture.resetUploadLimit();
        this.mRenderRequested = false;
        if ((this.mFlags & 2) != 0) {
            layoutContentPane();
        }
        if (this.mContentView != null) {
            this.mContentView.render(this.mCanvas);
        } else {
            this.mCanvas.clearBuffer();
        }
        if (UploadedTexture.uploadLimitReached()) {
            requestRender();
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        boolean z = false;
        if (isEnabled()) {
            int action = motionEvent.getAction();
            if (action == 3 || action == 1) {
                this.mInDownState = false;
            } else if (!(this.mInDownState || action == 0)) {
            }
            this.mRenderLock.lock();
            try {
                if (this.mContentView != null && this.mContentView.dispatchTouchEvent(motionEvent)) {
                    z = true;
                }
                if (action == 0 && r0) {
                    this.mInDownState = true;
                }
                this.mRenderLock.unlock();
            } catch (Throwable th) {
                this.mRenderLock.unlock();
            }
        }
        return z;
    }

    public void lockRenderThread() {
        this.mRenderLock.lock();
    }

    public void unlockRenderThread() {
        this.mRenderLock.unlock();
    }

    public void onPause() {
        unfreeze();
        super.onPause();
    }

    public void freeze() {
        this.mRenderLock.lock();
        this.mFreeze = true;
        this.mRenderLock.unlock();
    }

    public void unfreeze() {
        this.mRenderLock.lock();
        this.mFreeze = false;
        this.mFreezeCondition.signalAll();
        this.mRenderLock.unlock();
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        unfreeze();
        super.surfaceChanged(surfaceHolder, i, i2, i3);
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        unfreeze();
        super.surfaceCreated(surfaceHolder);
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        unfreeze();
        super.surfaceDestroyed(surfaceHolder);
    }

    protected void onDetachedFromWindow() {
        unfreeze();
        super.onDetachedFromWindow();
    }

    protected void finalize() throws Throwable {
        try {
            unfreeze();
        } finally {
            super.finalize();
        }
    }
}
