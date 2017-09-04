package com.tencent.av.opengl.glrenderer;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.RectF;
import android.opengl.GLU;
import android.opengl.GLUtils;
import android.opengl.Matrix;
import android.util.Log;
import com.tencent.av.opengl.texture.BasicTexture;
import com.tencent.av.opengl.utils.IntArray;
import com.tencent.av.opengl.utils.Utils;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import javax.microedition.khronos.opengles.GL11;
import javax.microedition.khronos.opengles.GL11Ext;
import junit.framework.Assert;

public class GLES11Canvas implements GLCanvas {
    private static final float[] BOX_COORDINATES = new float[]{0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f};
    private static final int MSCALE_X = 0;
    private static final int MSCALE_Y = 5;
    private static final int MSKEW_X = 4;
    private static final int MSKEW_Y = 1;
    private static final int OFFSET_DRAW_LINE = 4;
    private static final int OFFSET_DRAW_RECT = 6;
    private static final int OFFSET_FILL_RECT = 0;
    private static final float OPAQUE_ALPHA = 0.95f;
    private static final String TAG = "GLCanvasImp";
    private static GLId mGLId = new GLES11Id();
    private static float[] sCropRect = new float[4];
    private float mAlpha;
    private boolean mBlendEnabled = true;
    private int mBoxCoords;
    int mCountDrawLine;
    int mCountDrawMesh;
    int mCountFillRect;
    int mCountTextureOES;
    int mCountTextureRect;
    private final IntArray mDeleteBuffers = new IntArray();
    private final RectF mDrawTextureSourceRect = new RectF();
    private final RectF mDrawTextureTargetRect = new RectF();
    private GL11 mGL;
    private GLState mGLState;
    private final float[] mMapPointsBuffer = new float[4];
    private final float[] mMatrixValues = new float[16];
    private ConfigState mRecycledRestoreAction;
    private final ArrayList<ConfigState> mRestoreStack = new ArrayList();
    private final float[] mTempMatrix = new float[32];
    private final float[] mTextureColor = new float[4];
    private final float[] mTextureMatrixValues = new float[16];
    private final IntArray mUnboundTextures = new IntArray();

    private static class ConfigState {
        float mAlpha;
        float[] mMatrix;
        ConfigState mNextFree;

        private ConfigState() {
            this.mMatrix = new float[16];
        }

        public void restore(GLES11Canvas gLES11Canvas) {
            if (this.mAlpha >= 0.0f) {
                gLES11Canvas.setAlpha(this.mAlpha);
            }
            if (this.mMatrix[0] != Float.NEGATIVE_INFINITY) {
                System.arraycopy(this.mMatrix, 0, gLES11Canvas.mMatrixValues, 0, 16);
            }
        }
    }

    private static class GLState {
        private boolean mBlendEnabled = true;
        private final GL11 mGL;
        private float mLineWidth = 1.0f;
        private int mTexEnvMode = 7681;
        private float mTextureAlpha = 1.0f;
        private int mTextureTarget = 3553;

        public GLState(GL11 gl11) {
            this.mGL = gl11;
            gl11.glDisable(2896);
            gl11.glEnable(3024);
            gl11.glEnableClientState(32884);
            gl11.glEnableClientState(32888);
            gl11.glEnable(3553);
            gl11.glTexEnvf(8960, 8704, 7681.0f);
            gl11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
            gl11.glEnable(3042);
            gl11.glBlendFunc(1, 771);
            gl11.glPixelStorei(3317, 2);
        }

        public void setTexEnvMode(int i) {
            if (this.mTexEnvMode != i) {
                this.mTexEnvMode = i;
                this.mGL.glTexEnvf(8960, 8704, (float) i);
            }
        }

        public void setLineWidth(float f) {
            if (this.mLineWidth != f) {
                this.mLineWidth = f;
                this.mGL.glLineWidth(f);
            }
        }

        public void setTextureAlpha(float f) {
            if (this.mTextureAlpha != f) {
                this.mTextureAlpha = f;
                if (f >= GLES11Canvas.OPAQUE_ALPHA) {
                    this.mGL.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
                    setTexEnvMode(7681);
                    return;
                }
                this.mGL.glColor4f(f, f, f, f);
                setTexEnvMode(8448);
            }
        }

        public void setColorMode(int i, float f) {
            boolean z = !Utils.isOpaque(i) || f < GLES11Canvas.OPAQUE_ALPHA;
            setBlendEnabled(z);
            this.mTextureAlpha = -1.0f;
            setTextureTarget(0);
            float f2 = (((((float) (i >>> 24)) * f) * 65535.0f) / 255.0f) / 255.0f;
            this.mGL.glColor4x(Math.round(((float) ((i >> 16) & 255)) * f2), Math.round(((float) ((i >> 8) & 255)) * f2), Math.round(((float) (i & 255)) * f2), Math.round(f2 * 255.0f));
        }

        public void setTextureTarget(int i) {
            if (this.mTextureTarget != i) {
                if (this.mTextureTarget != 0) {
                    this.mGL.glDisable(this.mTextureTarget);
                }
                this.mTextureTarget = i;
                if (this.mTextureTarget != 0) {
                    this.mGL.glEnable(this.mTextureTarget);
                }
            }
        }

        public void setBlendEnabled(boolean z) {
            if (this.mBlendEnabled != z) {
                this.mBlendEnabled = z;
                if (z) {
                    this.mGL.glEnable(3042);
                } else {
                    this.mGL.glDisable(3042);
                }
            }
        }
    }

    public GLES11Canvas(GL11 gl11) {
        this.mGL = gl11;
        this.mGLState = new GLState(gl11);
        Buffer asFloatBuffer = allocateDirectNativeOrderBuffer((BOX_COORDINATES.length * 32) / 8).asFloatBuffer();
        asFloatBuffer.put(BOX_COORDINATES, 0, BOX_COORDINATES.length).position(0);
        int[] iArr = new int[1];
        mGLId.glGenBuffers(1, iArr, 0);
        this.mBoxCoords = iArr[0];
        gl11.glBindBuffer(34962, this.mBoxCoords);
        gl11.glBufferData(34962, asFloatBuffer.capacity() * 4, asFloatBuffer, 35044);
        gl11.glVertexPointer(2, 5126, 0, 0);
        gl11.glTexCoordPointer(2, 5126, 0, 0);
        gl11.glClientActiveTexture(33985);
        gl11.glTexCoordPointer(2, 5126, 0, 0);
        gl11.glClientActiveTexture(33984);
        gl11.glEnableClientState(32888);
    }

    public void setSize(int i, int i2) {
        boolean z = i >= 0 && i2 >= 0;
        Assert.assertTrue(z);
        this.mAlpha = 1.0f;
        GL11 gl11 = this.mGL;
        gl11.glViewport(0, 0, i, i2);
        gl11.glMatrixMode(5889);
        gl11.glLoadIdentity();
        GLU.gluOrtho2D(gl11, 0.0f, (float) i, 0.0f, (float) i2);
        gl11.glMatrixMode(5888);
        gl11.glLoadIdentity();
        float[] fArr = this.mMatrixValues;
        Matrix.setIdentityM(fArr, 0);
        Matrix.translateM(fArr, 0, 0.0f, (float) i2, 0.0f);
        Matrix.scaleM(fArr, 0, 1.0f, -1.0f, 1.0f);
    }

    public void setAlpha(float f) {
        boolean z = f >= 0.0f && f <= 1.0f;
        Assert.assertTrue(z);
        this.mAlpha = f;
    }

    public float getAlpha() {
        return this.mAlpha;
    }

    public void multiplyAlpha(float f) {
        boolean z = f >= 0.0f && f <= 1.0f;
        Assert.assertTrue(z);
        this.mAlpha *= f;
    }

    private static ByteBuffer allocateDirectNativeOrderBuffer(int i) {
        return ByteBuffer.allocateDirect(i).order(ByteOrder.nativeOrder());
    }

    public void drawRect(float f, float f2, float f3, float f4, GLPaint gLPaint) {
        GL11 gl11 = this.mGL;
        this.mGLState.setColorMode(gLPaint.getColor(), this.mAlpha);
        this.mGLState.setLineWidth(gLPaint.getLineWidth());
        saveTransform();
        translate(f, f2);
        scale(f3, f4, 1.0f);
        gl11.glLoadMatrixf(this.mMatrixValues, 0);
        gl11.glDrawArrays(2, 6, 4);
        restoreTransform();
        this.mCountDrawLine++;
    }

    public void drawLine(float f, float f2, float f3, float f4, GLPaint gLPaint) {
        GL11 gl11 = this.mGL;
        this.mGLState.setColorMode(gLPaint.getColor(), this.mAlpha);
        this.mGLState.setLineWidth(gLPaint.getLineWidth());
        saveTransform();
        translate(f, f2);
        scale(f3 - f, f4 - f2, 1.0f);
        gl11.glLoadMatrixf(this.mMatrixValues, 0);
        gl11.glDrawArrays(3, 4, 2);
        restoreTransform();
        this.mCountDrawLine++;
    }

    public void fillRect(float f, float f2, float f3, float f4, int i) {
        this.mGLState.setColorMode(i, this.mAlpha);
        GL11 gl11 = this.mGL;
        saveTransform();
        translate(f, f2);
        scale(f3, f4, 1.0f);
        gl11.glLoadMatrixf(this.mMatrixValues, 0);
        gl11.glDrawArrays(5, 0, 4);
        restoreTransform();
        this.mCountFillRect++;
    }

    public void translate(float f, float f2, float f3) {
        Matrix.translateM(this.mMatrixValues, 0, f, f2, f3);
    }

    public void translate(float f, float f2) {
        float[] fArr = this.mMatrixValues;
        fArr[12] = fArr[12] + ((fArr[0] * f) + (fArr[4] * f2));
        fArr[13] = fArr[13] + ((fArr[1] * f) + (fArr[5] * f2));
        fArr[14] = fArr[14] + ((fArr[2] * f) + (fArr[6] * f2));
        fArr[15] = fArr[15] + ((fArr[3] * f) + (fArr[7] * f2));
    }

    public void scale(float f, float f2, float f3) {
        Matrix.scaleM(this.mMatrixValues, 0, f, f2, f3);
    }

    public void rotate(float f, float f2, float f3, float f4) {
        if (f != 0.0f) {
            Object obj = this.mTempMatrix;
            Matrix.setRotateM(obj, 0, f, f2, f3, f4);
            Matrix.multiplyMM(obj, 16, this.mMatrixValues, 0, obj, 0);
            System.arraycopy(obj, 16, this.mMatrixValues, 0, 16);
        }
    }

    public void multiplyMatrix(float[] fArr, int i) {
        Object obj = this.mTempMatrix;
        Matrix.multiplyMM(obj, 0, this.mMatrixValues, 0, fArr, i);
        System.arraycopy(obj, 0, this.mMatrixValues, 0, 16);
    }

    private void textureRect(float f, float f2, float f3, float f4) {
        GL11 gl11 = this.mGL;
        saveTransform();
        translate(f, f2);
        scale(f3, f4, 1.0f);
        gl11.glLoadMatrixf(this.mMatrixValues, 0);
        gl11.glDrawArrays(5, 0, 4);
        restoreTransform();
        this.mCountTextureRect++;
    }

    public void drawMesh(BasicTexture basicTexture, int i, int i2, int i3, int i4, int i5, int i6) {
        float f = this.mAlpha;
        if (bindTexture(basicTexture)) {
            GLState gLState = this.mGLState;
            boolean z = this.mBlendEnabled && (!basicTexture.isOpaque() || f < OPAQUE_ALPHA);
            gLState.setBlendEnabled(z);
            this.mGLState.setTextureAlpha(f);
            setTextureCoords(0.0f, 0.0f, 1.0f, 1.0f);
            saveTransform();
            translate((float) i, (float) i2);
            this.mGL.glLoadMatrixf(this.mMatrixValues, 0);
            this.mGL.glBindBuffer(34962, i3);
            this.mGL.glVertexPointer(2, 5126, 0, 0);
            this.mGL.glBindBuffer(34962, i4);
            this.mGL.glTexCoordPointer(2, 5126, 0, 0);
            this.mGL.glBindBuffer(34963, i5);
            this.mGL.glDrawElements(5, i6, 5121, 0);
            this.mGL.glBindBuffer(34962, this.mBoxCoords);
            this.mGL.glVertexPointer(2, 5126, 0, 0);
            this.mGL.glTexCoordPointer(2, 5126, 0, 0);
            restoreTransform();
            this.mCountDrawMesh++;
        }
    }

    private float[] mapPoints(float[] fArr, int i, int i2, int i3, int i4) {
        float[] fArr2 = this.mMapPointsBuffer;
        float f = ((fArr[1] * ((float) i)) + (fArr[5] * ((float) i2))) + fArr[13];
        float f2 = ((fArr[3] * ((float) i)) + (fArr[7] * ((float) i2))) + fArr[15];
        fArr2[0] = (((fArr[0] * ((float) i)) + (fArr[4] * ((float) i2))) + fArr[12]) / f2;
        fArr2[1] = f / f2;
        f = ((fArr[1] * ((float) i3)) + (fArr[5] * ((float) i4))) + fArr[13];
        f2 = ((fArr[3] * ((float) i3)) + (fArr[7] * ((float) i4))) + fArr[15];
        fArr2[2] = (((fArr[0] * ((float) i3)) + (fArr[4] * ((float) i4))) + fArr[12]) / f2;
        fArr2[3] = f / f2;
        return fArr2;
    }

    private void drawBoundTexture(BasicTexture basicTexture, int i, int i2, int i3, int i4) {
        if (isMatrixRotatedOrFlipped(this.mMatrixValues)) {
            setTextureCoords(0.0f, 0.0f, ((float) basicTexture.getSourceWidth()) / ((float) basicTexture.getTextureWidth()), ((float) basicTexture.getSourceHeight()) / ((float) basicTexture.getTextureHeight()));
            textureRect((float) i, (float) i2, (float) i3, (float) i4);
            return;
        }
        float[] mapPoints = mapPoints(this.mMatrixValues, i, i2 + i4, i + i3, i2);
        int i5 = (int) (mapPoints[0] + 0.5f);
        int i6 = (int) (mapPoints[1] + 0.5f);
        int i7 = ((int) (mapPoints[2] + 0.5f)) - i5;
        int i8 = ((int) (mapPoints[3] + 0.5f)) - i6;
        if (i7 > 0 && i8 > 0) {
            ((GL11Ext) this.mGL).glDrawTexiOES(i5, i6, 0, i7, i8);
            this.mCountTextureOES++;
        }
    }

    public void drawTexture(BasicTexture basicTexture, int i, int i2, int i3, int i4) {
        drawTexture(basicTexture, i, i2, i3, i4, this.mAlpha);
    }

    private void drawTexture(BasicTexture basicTexture, int i, int i2, int i3, int i4, float f) {
        if (i3 > 0 && i4 > 0) {
            GLState gLState = this.mGLState;
            boolean z = this.mBlendEnabled && (!basicTexture.isOpaque() || f < OPAQUE_ALPHA);
            gLState.setBlendEnabled(z);
            if (bindTexture(basicTexture)) {
                this.mGLState.setTextureAlpha(f);
                drawBoundTexture(basicTexture, i, i2, i3, i4);
            }
        }
    }

    public void drawTexture(BasicTexture basicTexture, RectF rectF, RectF rectF2) {
        if (rectF2.width() > 0.0f && rectF2.height() > 0.0f) {
            this.mDrawTextureSourceRect.set(rectF);
            this.mDrawTextureTargetRect.set(rectF2);
            RectF rectF3 = this.mDrawTextureSourceRect;
            RectF rectF4 = this.mDrawTextureTargetRect;
            GLState gLState = this.mGLState;
            boolean z = this.mBlendEnabled && (!basicTexture.isOpaque() || this.mAlpha < OPAQUE_ALPHA);
            gLState.setBlendEnabled(z);
            if (bindTexture(basicTexture)) {
                convertCoordinate(rectF3, rectF4, basicTexture);
                setTextureCoords(rectF3);
                this.mGLState.setTextureAlpha(this.mAlpha);
                textureRect(rectF4.left, rectF4.top, rectF4.width(), rectF4.height());
            }
        }
    }

    public void drawTexture(BasicTexture basicTexture, float[] fArr, int i, int i2, int i3, int i4) {
        GLState gLState = this.mGLState;
        boolean z = this.mBlendEnabled && (!basicTexture.isOpaque() || this.mAlpha < OPAQUE_ALPHA);
        gLState.setBlendEnabled(z);
        if (bindTexture(basicTexture)) {
            setTextureCoords(fArr);
            this.mGLState.setTextureAlpha(this.mAlpha);
            textureRect((float) i, (float) i2, (float) i3, (float) i4);
        }
    }

    private static void convertCoordinate(RectF rectF, RectF rectF2, BasicTexture basicTexture) {
        int textureWidth = basicTexture.getTextureWidth();
        int textureHeight = basicTexture.getTextureHeight();
        rectF.left /= (float) textureWidth;
        rectF.right /= (float) textureWidth;
        rectF.top /= (float) textureHeight;
        rectF.bottom /= (float) textureHeight;
    }

    public void drawMixed(BasicTexture basicTexture, int i, float f, int i2, int i3, int i4, int i5) {
        drawMixed(basicTexture, i, f, i2, i3, i4, i5, this.mAlpha);
    }

    private boolean bindTexture(BasicTexture basicTexture) {
        if (!basicTexture.onBind(this)) {
            return false;
        }
        int target = basicTexture.getTarget();
        this.mGLState.setTextureTarget(target);
        this.mGL.glBindTexture(target, basicTexture.getId()[0]);
        return true;
    }

    private void setTextureColor(float f, float f2, float f3, float f4) {
        float[] fArr = this.mTextureColor;
        fArr[0] = f;
        fArr[1] = f2;
        fArr[2] = f3;
        fArr[3] = f4;
    }

    private void setMixedColor(int i, float f, float f2) {
        float f3 = (1.0f - f) * f2;
        float f4 = (((f2 * f) / (1.0f - f3)) * ((float) (i >>> 24))) / 65025.0f;
        setTextureColor(((float) ((i >>> 16) & 255)) * f4, ((float) ((i >>> 8) & 255)) * f4, f4 * ((float) (i & 255)), f3);
        GL11 gl11 = this.mGL;
        gl11.glTexEnvfv(8960, 8705, this.mTextureColor, 0);
        gl11.glTexEnvf(8960, 34161, 34165.0f);
        gl11.glTexEnvf(8960, 34162, 34165.0f);
        gl11.glTexEnvf(8960, 34177, 34166.0f);
        gl11.glTexEnvf(8960, 34193, 768.0f);
        gl11.glTexEnvf(8960, 34185, 34166.0f);
        gl11.glTexEnvf(8960, 34201, 770.0f);
        gl11.glTexEnvf(8960, 34178, 34166.0f);
        gl11.glTexEnvf(8960, 34194, 770.0f);
        gl11.glTexEnvf(8960, 34186, 34166.0f);
        gl11.glTexEnvf(8960, 34202, 770.0f);
    }

    public void drawMixed(BasicTexture basicTexture, int i, float f, RectF rectF, RectF rectF2) {
        if (rectF2.width() > 0.0f && rectF2.height() > 0.0f) {
            if (f <= 0.01f) {
                drawTexture(basicTexture, rectF, rectF2);
            } else if (f >= 1.0f) {
                fillRect(rectF2.left, rectF2.top, rectF2.width(), rectF2.height(), i);
            } else {
                float f2 = this.mAlpha;
                this.mDrawTextureSourceRect.set(rectF);
                this.mDrawTextureTargetRect.set(rectF2);
                RectF rectF3 = this.mDrawTextureSourceRect;
                RectF rectF4 = this.mDrawTextureTargetRect;
                GLState gLState = this.mGLState;
                boolean z = this.mBlendEnabled && !(basicTexture.isOpaque() && Utils.isOpaque(i) && f2 >= OPAQUE_ALPHA);
                gLState.setBlendEnabled(z);
                if (bindTexture(basicTexture)) {
                    this.mGLState.setTexEnvMode(34160);
                    setMixedColor(i, f, f2);
                    convertCoordinate(rectF3, rectF4, basicTexture);
                    setTextureCoords(rectF3);
                    textureRect(rectF4.left, rectF4.top, rectF4.width(), rectF4.height());
                    this.mGLState.setTexEnvMode(7681);
                }
            }
        }
    }

    private void drawMixed(BasicTexture basicTexture, int i, float f, int i2, int i3, int i4, int i5, float f2) {
        if (f <= 0.01f) {
            drawTexture(basicTexture, i2, i3, i4, i5, f2);
        } else if (f >= 1.0f) {
            fillRect((float) i2, (float) i3, (float) i4, (float) i5, i);
        } else {
            GLState gLState = this.mGLState;
            boolean z = this.mBlendEnabled && !(basicTexture.isOpaque() && Utils.isOpaque(i) && f2 >= OPAQUE_ALPHA);
            gLState.setBlendEnabled(z);
            if (bindTexture(basicTexture)) {
                this.mGLState.setTexEnvMode(34160);
                setMixedColor(i, f, f2);
                drawBoundTexture(basicTexture, i2, i3, i4, i5);
                this.mGLState.setTexEnvMode(7681);
            }
        }
    }

    private static boolean isMatrixRotatedOrFlipped(float[] fArr) {
        return Math.abs(fArr[4]) > 1.0E-5f || Math.abs(fArr[1]) > 1.0E-5f || fArr[0] < -1.0E-5f || fArr[5] > 1.0E-5f;
    }

    public void clearBuffer() {
        this.mGL.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        this.mGL.glClear(16384);
    }

    public void clearBuffer(float f, float f2, float f3, float f4) {
        this.mGL.glClearColor(f, f2, f3, f4);
        this.mGL.glClear(16384);
    }

    private void setTextureCoords(RectF rectF) {
        setTextureCoords(rectF.left, rectF.top, rectF.right, rectF.bottom);
    }

    private void setTextureCoords(float f, float f2, float f3, float f4) {
        this.mGL.glMatrixMode(5890);
        this.mTextureMatrixValues[0] = f3 - f;
        this.mTextureMatrixValues[5] = f4 - f2;
        this.mTextureMatrixValues[10] = 1.0f;
        this.mTextureMatrixValues[12] = f;
        this.mTextureMatrixValues[13] = f2;
        this.mTextureMatrixValues[15] = 1.0f;
        this.mGL.glLoadMatrixf(this.mTextureMatrixValues, 0);
        this.mGL.glMatrixMode(5888);
    }

    private void setTextureCoords(float[] fArr) {
        this.mGL.glMatrixMode(5890);
        this.mGL.glLoadMatrixf(fArr, 0);
        this.mGL.glMatrixMode(5888);
    }

    public boolean unloadTexture(BasicTexture basicTexture) {
        boolean z = false;
        synchronized (this.mUnboundTextures) {
            if (basicTexture.isLoaded()) {
                this.mUnboundTextures.add(basicTexture.getId()[0]);
                z = true;
            }
        }
        return z;
    }

    public void deleteBuffer(int i) {
        synchronized (this.mUnboundTextures) {
            this.mDeleteBuffers.add(i);
        }
    }

    public void deleteRecycledResources() {
        synchronized (this.mUnboundTextures) {
            IntArray intArray = this.mUnboundTextures;
            if (intArray.size() > 0) {
                mGLId.glDeleteTextures(this.mGL, intArray.size(), intArray.getInternalArray(), 0);
                intArray.clear();
            }
            intArray = this.mDeleteBuffers;
            if (intArray.size() > 0) {
                mGLId.glDeleteBuffers(this.mGL, intArray.size(), intArray.getInternalArray(), 0);
                intArray.clear();
            }
        }
    }

    public void save() {
        save(-1);
    }

    public void save(int i) {
        ConfigState obtainRestoreConfig = obtainRestoreConfig();
        if ((i & 1) != 0) {
            obtainRestoreConfig.mAlpha = this.mAlpha;
        } else {
            obtainRestoreConfig.mAlpha = -1.0f;
        }
        if ((i & 2) != 0) {
            System.arraycopy(this.mMatrixValues, 0, obtainRestoreConfig.mMatrix, 0, 16);
        } else {
            obtainRestoreConfig.mMatrix[0] = Float.NEGATIVE_INFINITY;
        }
        this.mRestoreStack.add(obtainRestoreConfig);
    }

    public void restore() {
        if (this.mRestoreStack.isEmpty()) {
            throw new IllegalStateException();
        }
        ConfigState configState = (ConfigState) this.mRestoreStack.remove(this.mRestoreStack.size() - 1);
        configState.restore(this);
        freeRestoreConfig(configState);
    }

    private void freeRestoreConfig(ConfigState configState) {
        configState.mNextFree = this.mRecycledRestoreAction;
        this.mRecycledRestoreAction = configState;
    }

    private ConfigState obtainRestoreConfig() {
        if (this.mRecycledRestoreAction == null) {
            return new ConfigState();
        }
        ConfigState configState = this.mRecycledRestoreAction;
        this.mRecycledRestoreAction = configState.mNextFree;
        return configState;
    }

    public void dumpStatisticsAndClear() {
        String format = String.format("MESH:%d, TEX_OES:%d, TEX_RECT:%d, FILL_RECT:%d, LINE:%d", new Object[]{Integer.valueOf(this.mCountDrawMesh), Integer.valueOf(this.mCountTextureRect), Integer.valueOf(this.mCountTextureOES), Integer.valueOf(this.mCountFillRect), Integer.valueOf(this.mCountDrawLine)});
        this.mCountDrawMesh = 0;
        this.mCountTextureRect = 0;
        this.mCountTextureOES = 0;
        this.mCountFillRect = 0;
        this.mCountDrawLine = 0;
        Log.d(TAG, format);
    }

    private void saveTransform() {
        System.arraycopy(this.mMatrixValues, 0, this.mTempMatrix, 0, 16);
    }

    private void restoreTransform() {
        System.arraycopy(this.mTempMatrix, 0, this.mMatrixValues, 0, 16);
    }

    public void setTextureParameters(BasicTexture basicTexture) {
        int sourceWidth = basicTexture.getSourceWidth();
        int sourceHeight = basicTexture.getSourceHeight();
        sCropRect[0] = 0.0f;
        sCropRect[1] = (float) sourceHeight;
        sCropRect[2] = (float) sourceWidth;
        sCropRect[3] = (float) (-sourceHeight);
        sourceWidth = basicTexture.getTarget();
        this.mGL.glBindTexture(sourceWidth, basicTexture.getId()[0]);
        this.mGL.glTexParameterfv(sourceWidth, 35741, sCropRect, 0);
        this.mGL.glTexParameteri(sourceWidth, 10242, 33071);
        this.mGL.glTexParameteri(sourceWidth, 10243, 33071);
        this.mGL.glTexParameterf(sourceWidth, 10241, 9729.0f);
        this.mGL.glTexParameterf(sourceWidth, 10240, 9729.0f);
    }

    public void initializeTextureSize(BasicTexture basicTexture, int i, int i2) {
        int target = basicTexture.getTarget();
        this.mGL.glBindTexture(target, basicTexture.getId()[0]);
        this.mGL.glTexImage2D(target, 0, i, basicTexture.getTextureWidth(), basicTexture.getTextureHeight(), 0, i, i2, null);
    }

    public void initializeTexture(BasicTexture basicTexture, Bitmap bitmap) {
        int target = basicTexture.getTarget();
        this.mGL.glBindTexture(target, basicTexture.getId()[0]);
        GLUtils.texImage2D(target, 0, bitmap, 0);
    }

    public void texSubImage2D(BasicTexture basicTexture, int i, int i2, Bitmap bitmap, int i3, int i4) {
        int target = basicTexture.getTarget();
        this.mGL.glBindTexture(target, basicTexture.getId()[0]);
        GLUtils.texSubImage2D(target, 0, i, i2, bitmap, i3, i4);
    }

    public int uploadBuffer(FloatBuffer floatBuffer) {
        return uploadBuffer(floatBuffer, 4);
    }

    public int uploadBuffer(ByteBuffer byteBuffer) {
        return uploadBuffer(byteBuffer, 1);
    }

    private int uploadBuffer(Buffer buffer, int i) {
        int[] iArr = new int[1];
        mGLId.glGenBuffers(iArr.length, iArr, 0);
        int i2 = iArr[0];
        this.mGL.glBindBuffer(34962, i2);
        this.mGL.glBufferData(34962, buffer.capacity() * i, buffer, 35044);
        return i2;
    }

    public void recoverFromLightCycle() {
    }

    public void getBounds(Rect rect, int i, int i2, int i3, int i4) {
    }

    public GLId getGLId() {
        return mGLId;
    }
}
