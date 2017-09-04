package com.tencent.av.opengl.glrenderer;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.RectF;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.opengl.Matrix;
import android.util.Log;
import com.tencent.av.opengl.texture.BasicTexture;
import com.tencent.av.opengl.utils.IntArray;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.Arrays;

public class GLES20Canvas implements GLCanvas {
    private static final String ALPHA_UNIFORM = "uAlpha";
    private static final float[] BOUNDS_COORDINATES = new float[]{0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f};
    private static final float[] BOX_COORDINATES = new float[]{0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f};
    private static final String COLOR_UNIFORM = "uColor";
    private static final int COORDS_PER_VERTEX = 2;
    private static final int COUNT_FILL_VERTEX = 4;
    private static final int COUNT_LINE_VERTEX = 2;
    private static final int COUNT_RECT_VERTEX = 4;
    private static final String DRAW_FRAGMENT_SHADER = "precision mediump float;\nuniform vec4 uColor;\nvoid main() {\n  gl_FragColor = uColor;\n}\n";
    private static final String DRAW_VERTEX_SHADER = "uniform mat4 uMatrix;\nattribute vec2 aPosition;\nvoid main() {\n  vec4 pos = vec4(aPosition, 0.0, 1.0);\n  gl_Position = uMatrix * pos;\n}\n";
    private static final int FLOAT_SIZE = 4;
    private static final int INDEX_ALPHA = 2;
    private static final int INDEX_COLOR = 2;
    private static final int INDEX_MATRIX = 1;
    private static final int INDEX_POSITION = 0;
    private static final int INDEX_TEXTURE_COORD = 2;
    private static final int INDEX_TEXTURE_MATRIX = 3;
    private static final int INDEX_TEXTURE_SAMPLER0 = 4;
    private static final int INDEX_TEXTURE_SAMPLER1 = 5;
    private static final int INDEX_TEXTURE_SAMPLER2 = 6;
    private static final int INITIAL_RESTORE_STATE_SIZE = 8;
    private static final int MATRIX_SIZE = 16;
    private static final String MATRIX_UNIFORM = "uMatrix";
    private static final String MESH_VERTEX_SHADER = "uniform mat4 uMatrix;\nattribute vec2 aPosition;\nattribute vec2 aTextureCoordinate;\nvarying vec2 vTextureCoord;\nvoid main() {\n  vec4 pos = vec4(aPosition, 0.0, 1.0);\n  gl_Position = uMatrix * pos;\n  vTextureCoord = aTextureCoordinate;\n}\n";
    private static final String OES_TEXTURE_FRAGMENT_SHADER = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 vTextureCoord;\nuniform float uAlpha;\nuniform samplerExternalOES uTextureSampler0;\nvoid main() {\n  gl_FragColor = texture2D(uTextureSampler0, vTextureCoord);\n  gl_FragColor *= uAlpha;\n}\n";
    private static final int OFFSET_DRAW_LINE = 4;
    private static final int OFFSET_DRAW_RECT = 6;
    private static final int OFFSET_FILL_RECT = 0;
    private static final float OPAQUE_ALPHA = 0.95f;
    private static final String POSITION_ATTRIBUTE = "aPosition";
    private static final String TAG = GLES20Canvas.class.getSimpleName();
    private static final String TEXTURE_COORD_ATTRIBUTE = "aTextureCoordinate";
    private static final String TEXTURE_FRAGMENT_SHADER = "precision mediump float;\nvarying vec2 vTextureCoord;\nuniform float uAlpha;\nuniform sampler2D uTextureSampler0;\nvoid main() {\n  gl_FragColor = texture2D(uTextureSampler0, vTextureCoord);\n  gl_FragColor *= uAlpha;\n}\n";
    private static final String TEXTURE_MATRIX_UNIFORM = "uTextureMatrix";
    private static final String TEXTURE_SAMPLER_UNIFORM0 = "uTextureSampler0";
    private static final String TEXTURE_SAMPLER_UNIFORM1 = "uTextureSampler1";
    private static final String TEXTURE_SAMPLER_UNIFORM2 = "uTextureSampler2";
    private static final String TEXTURE_VERTEX_SHADER = "uniform mat4 uMatrix;\nuniform mat4 uTextureMatrix;\nattribute vec2 aPosition;\nvarying vec2 vTextureCoord;\nvoid main() {\n  vec4 pos = vec4(aPosition, 0.0, 1.0);\n  gl_Position = uMatrix * pos;\n  vTextureCoord = (uTextureMatrix * pos).xy;\n}\n";
    private static final int VERTEX_STRIDE = 8;
    private static final String YUV_TEXTURE_FRAGMENT_SHADER = "precision mediump float;\nvarying vec2 vTextureCoord;\nuniform float uAlpha;\nuniform sampler2D uTextureSampler0;\nuniform sampler2D uTextureSampler1;\nuniform sampler2D uTextureSampler2;\nconst vec3 matYUVRGB1 = vec3(1.0,  0.0,   1.402);\nconst vec3 matYUVRGB2 = vec3(1.0, -0.344, -0.714);\nconst vec3 matYUVRGB3 = vec3(1.0,  1.772,  0.0);\nvoid main() {\n  vec3 yuvData;\n  vec3 rgbData;\n  yuvData.x = texture2D(uTextureSampler0, vTextureCoord).x;\n  yuvData.y = texture2D(uTextureSampler1, vTextureCoord).x-0.5;\n  yuvData.z = texture2D(uTextureSampler2, vTextureCoord).x-0.5;\n  rgbData.x = dot(yuvData, matYUVRGB1);\n  rgbData.y = dot(yuvData, matYUVRGB2);\n  rgbData.z = dot(yuvData, matYUVRGB3);\n  gl_FragColor = vec4(rgbData, 1.0) * uAlpha;\n}\n";
    private static final GLId mGLId = new GLES20Id();
    private float[] mAlphas = new float[8];
    private int mBoxCoordinates;
    private int mCountDrawLine = 0;
    private int mCountDrawMesh = 0;
    private int mCountFillRect = 0;
    private int mCountTextureRect = 0;
    private int mCurrentAlphaIndex = 0;
    private int mCurrentMatrixIndex = 0;
    private final IntArray mDeleteBuffers = new IntArray();
    ShaderParameter[] mDrawParameters = new ShaderParameter[]{new AttributeShaderParameter(POSITION_ATTRIBUTE), new UniformShaderParameter(MATRIX_UNIFORM), new UniformShaderParameter(COLOR_UNIFORM)};
    private int mDrawProgram;
    private int mHeight;
    private float[] mMatrices = new float[128];
    ShaderParameter[] mMeshParameters = new ShaderParameter[]{new AttributeShaderParameter(POSITION_ATTRIBUTE), new UniformShaderParameter(MATRIX_UNIFORM), new UniformShaderParameter(ALPHA_UNIFORM), new AttributeShaderParameter(TEXTURE_COORD_ATTRIBUTE), new UniformShaderParameter(TEXTURE_SAMPLER_UNIFORM0)};
    private int mMeshProgram;
    ShaderParameter[] mOesTextureParameters = new ShaderParameter[]{new AttributeShaderParameter(POSITION_ATTRIBUTE), new UniformShaderParameter(MATRIX_UNIFORM), new UniformShaderParameter(ALPHA_UNIFORM), new UniformShaderParameter(TEXTURE_MATRIX_UNIFORM), new UniformShaderParameter(TEXTURE_SAMPLER_UNIFORM0)};
    private int mOesTextureProgram;
    private float[] mProjectionMatrix = new float[16];
    private IntArray mSaveFlags = new IntArray();
    private final float[] mTempColor = new float[4];
    private final int[] mTempIntArray = new int[1];
    private final float[] mTempMatrix = new float[32];
    private final RectF mTempSourceRect = new RectF();
    private final RectF mTempTargetRect = new RectF();
    private final float[] mTempTextureMatrix = new float[16];
    ShaderParameter[] mTextureParameters = new ShaderParameter[]{new AttributeShaderParameter(POSITION_ATTRIBUTE), new UniformShaderParameter(MATRIX_UNIFORM), new UniformShaderParameter(ALPHA_UNIFORM), new UniformShaderParameter(TEXTURE_MATRIX_UNIFORM), new UniformShaderParameter(TEXTURE_SAMPLER_UNIFORM0)};
    private int mTextureProgram;
    private final IntArray mUnboundTextures = new IntArray();
    private int mWidth;
    ShaderParameter[] mYUVTextureParameters = new ShaderParameter[]{new AttributeShaderParameter(POSITION_ATTRIBUTE), new UniformShaderParameter(MATRIX_UNIFORM), new UniformShaderParameter(ALPHA_UNIFORM), new UniformShaderParameter(TEXTURE_MATRIX_UNIFORM), new UniformShaderParameter(TEXTURE_SAMPLER_UNIFORM0), new UniformShaderParameter(TEXTURE_SAMPLER_UNIFORM1), new UniformShaderParameter(TEXTURE_SAMPLER_UNIFORM2)};
    private int mYUVTextureProgram;

    private static abstract class ShaderParameter {
        public int handle;
        protected final String mName;

        public abstract void loadHandle(int i);

        public ShaderParameter(String str) {
            this.mName = str;
        }
    }

    private static class AttributeShaderParameter extends ShaderParameter {
        public AttributeShaderParameter(String str) {
            super(str);
        }

        public void loadHandle(int i) {
            this.handle = GLES20.glGetAttribLocation(i, this.mName);
            GLES20Canvas.checkError();
        }
    }

    private static class UniformShaderParameter extends ShaderParameter {
        public UniformShaderParameter(String str) {
            super(str);
        }

        public void loadHandle(int i) {
            this.handle = GLES20.glGetUniformLocation(i, this.mName);
            GLES20Canvas.checkError();
        }
    }

    public GLES20Canvas() {
        Matrix.setIdentityM(this.mTempTextureMatrix, 0);
        Matrix.setIdentityM(this.mMatrices, this.mCurrentMatrixIndex);
        this.mAlphas[this.mCurrentAlphaIndex] = 1.0f;
        this.mBoxCoordinates = uploadBuffer(createBuffer(BOX_COORDINATES));
        int loadShader = loadShader(35633, DRAW_VERTEX_SHADER);
        int loadShader2 = loadShader(35633, TEXTURE_VERTEX_SHADER);
        int loadShader3 = loadShader(35633, TEXTURE_VERTEX_SHADER);
        int loadShader4 = loadShader(35633, MESH_VERTEX_SHADER);
        int loadShader5 = loadShader(35632, DRAW_FRAGMENT_SHADER);
        int loadShader6 = loadShader(35632, TEXTURE_FRAGMENT_SHADER);
        int loadShader7 = loadShader(35632, YUV_TEXTURE_FRAGMENT_SHADER);
        int loadShader8 = loadShader(35632, OES_TEXTURE_FRAGMENT_SHADER);
        this.mDrawProgram = assembleProgram(loadShader, loadShader5, this.mDrawParameters);
        this.mTextureProgram = assembleProgram(loadShader2, loadShader6, this.mTextureParameters);
        this.mYUVTextureProgram = assembleProgram(loadShader3, loadShader7, this.mYUVTextureParameters);
        this.mOesTextureProgram = assembleProgram(loadShader2, loadShader8, this.mOesTextureParameters);
        this.mMeshProgram = assembleProgram(loadShader4, loadShader6, this.mMeshParameters);
        GLES20.glBlendFunc(1, 771);
        checkError();
    }

    private static FloatBuffer createBuffer(float[] fArr) {
        FloatBuffer asFloatBuffer = ByteBuffer.allocateDirect(fArr.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        asFloatBuffer.put(fArr, 0, fArr.length).position(0);
        return asFloatBuffer;
    }

    private int assembleProgram(int i, int i2, ShaderParameter[] shaderParameterArr) {
        int i3 = 0;
        int glCreateProgram = GLES20.glCreateProgram();
        checkError();
        if (glCreateProgram == 0) {
            throw new RuntimeException("Cannot create GL program: " + GLES20.glGetError());
        }
        GLES20.glAttachShader(glCreateProgram, i);
        checkError();
        GLES20.glAttachShader(glCreateProgram, i2);
        checkError();
        GLES20.glLinkProgram(glCreateProgram);
        checkError();
        int[] iArr = this.mTempIntArray;
        GLES20.glGetProgramiv(glCreateProgram, 35714, iArr, 0);
        if (iArr[0] != 1) {
            Log.e(TAG, "Could not link program: ");
            Log.e(TAG, GLES20.glGetProgramInfoLog(glCreateProgram));
            GLES20.glDeleteProgram(glCreateProgram);
            glCreateProgram = 0;
        }
        while (i3 < shaderParameterArr.length) {
            shaderParameterArr[i3].loadHandle(glCreateProgram);
            i3++;
        }
        return glCreateProgram;
    }

    private static int loadShader(int i, String str) {
        int glCreateShader = GLES20.glCreateShader(i);
        GLES20.glShaderSource(glCreateShader, str);
        checkError();
        GLES20.glCompileShader(glCreateShader);
        checkError();
        return glCreateShader;
    }

    public void setSize(int i, int i2) {
        this.mWidth = i;
        this.mHeight = i2;
        GLES20.glViewport(0, 0, this.mWidth, this.mHeight);
        checkError();
        Matrix.setIdentityM(this.mMatrices, this.mCurrentMatrixIndex);
        Matrix.orthoM(this.mProjectionMatrix, 0, 0.0f, (float) i, 0.0f, (float) i2, -1.0f, 1.0f);
        Matrix.translateM(this.mMatrices, this.mCurrentMatrixIndex, 0.0f, (float) i2, 0.0f);
        Matrix.scaleM(this.mMatrices, this.mCurrentMatrixIndex, 1.0f, -1.0f, 1.0f);
    }

    public void clearBuffer() {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        checkError();
        GLES20.glClear(16384);
        checkError();
    }

    public void clearBuffer(float f, float f2, float f3, float f4) {
        GLES20.glClearColor(f, f2, f3, f4);
        checkError();
        GLES20.glClear(16384);
        checkError();
    }

    public float getAlpha() {
        return this.mAlphas[this.mCurrentAlphaIndex];
    }

    public void setAlpha(float f) {
        this.mAlphas[this.mCurrentAlphaIndex] = f;
    }

    public void multiplyAlpha(float f) {
        setAlpha(getAlpha() * f);
    }

    public void translate(float f, float f2, float f3) {
        Matrix.translateM(this.mMatrices, this.mCurrentMatrixIndex, f, f2, f3);
    }

    public void translate(float f, float f2) {
        int i = this.mCurrentMatrixIndex;
        float[] fArr = this.mMatrices;
        int i2 = i + 12;
        fArr[i2] = fArr[i2] + ((fArr[i + 0] * f) + (fArr[i + 4] * f2));
        i2 = i + 13;
        fArr[i2] = fArr[i2] + ((fArr[i + 1] * f) + (fArr[i + 5] * f2));
        i2 = i + 14;
        fArr[i2] = fArr[i2] + ((fArr[i + 2] * f) + (fArr[i + 6] * f2));
        i2 = i + 15;
        fArr[i2] = ((fArr[i + 7] * f2) + (fArr[i + 3] * f)) + fArr[i2];
    }

    public void scale(float f, float f2, float f3) {
        Matrix.scaleM(this.mMatrices, this.mCurrentMatrixIndex, f, f2, f3);
    }

    public void rotate(float f, float f2, float f3, float f4) {
        if (f != 0.0f) {
            Object obj = this.mTempMatrix;
            Matrix.setRotateM(obj, 0, f, f2, f3, f4);
            Object obj2 = this.mMatrices;
            int i = this.mCurrentMatrixIndex;
            Matrix.multiplyMM(obj, 16, obj2, i, obj, 0);
            System.arraycopy(obj, 16, obj2, i, 16);
        }
    }

    public void multiplyMatrix(float[] fArr, int i) {
        Object obj = this.mTempMatrix;
        Object obj2 = this.mMatrices;
        int i2 = this.mCurrentMatrixIndex;
        Matrix.multiplyMM(obj, 0, obj2, i2, fArr, i);
        System.arraycopy(obj, 0, obj2, i2, 16);
    }

    public void save() {
        save(-1);
    }

    public void save(int i) {
        Object obj = 1;
        if (((i & 1) == 1 ? 1 : null) != null) {
            float alpha = getAlpha();
            this.mCurrentAlphaIndex++;
            if (this.mAlphas.length <= this.mCurrentAlphaIndex) {
                this.mAlphas = Arrays.copyOf(this.mAlphas, this.mAlphas.length * 2);
            }
            this.mAlphas[this.mCurrentAlphaIndex] = alpha;
        }
        if ((i & 2) != 2) {
            obj = null;
        }
        if (obj != null) {
            int i2 = this.mCurrentMatrixIndex;
            this.mCurrentMatrixIndex += 16;
            if (this.mMatrices.length <= this.mCurrentMatrixIndex) {
                this.mMatrices = Arrays.copyOf(this.mMatrices, this.mMatrices.length * 2);
            }
            System.arraycopy(this.mMatrices, i2, this.mMatrices, this.mCurrentMatrixIndex, 16);
        }
        this.mSaveFlags.add(i);
    }

    public void restore() {
        Object obj = 1;
        int removeLast = this.mSaveFlags.removeLast();
        if (((removeLast & 1) == 1 ? 1 : null) != null) {
            this.mCurrentAlphaIndex--;
        }
        if ((removeLast & 2) != 2) {
            obj = null;
        }
        if (obj != null) {
            this.mCurrentMatrixIndex -= 16;
        }
    }

    public void drawLine(float f, float f2, float f3, float f4, GLPaint gLPaint) {
        draw(3, 4, 2, f, f2, f3 - f, f4 - f2, gLPaint);
        this.mCountDrawLine++;
    }

    public void drawRect(float f, float f2, float f3, float f4, GLPaint gLPaint) {
        draw(2, 6, 4, f, f2, f3, f4, gLPaint);
        this.mCountDrawLine++;
    }

    private void draw(int i, int i2, int i3, float f, float f2, float f3, float f4, GLPaint gLPaint) {
        draw(i, i2, i3, f, f2, f3, f4, gLPaint.getColor(), gLPaint.getLineWidth());
    }

    private void draw(int i, int i2, int i3, float f, float f2, float f3, float f4, int i4, float f5) {
        prepareDraw(i2, i4, f5);
        draw(this.mDrawParameters, i, i3, f, f2, f3, f4);
    }

    private void prepareDraw(int i, int i2, float f) {
        GLES20.glUseProgram(this.mDrawProgram);
        checkError();
        if (f > 0.0f) {
            GLES20.glLineWidth(f);
            checkError();
        }
        float[] color = getColor(i2);
        boolean z = color[3] < 1.0f;
        enableBlending(z);
        if (z) {
            GLES20.glBlendColor(color[0], color[1], color[2], color[3]);
            checkError();
        }
        GLES20.glUniform4fv(this.mDrawParameters[2].handle, 1, color, 0);
        setPosition(this.mDrawParameters, i);
        checkError();
    }

    private float[] getColor(int i) {
        float alpha = (((float) ((i >>> 24) & 255)) / 255.0f) * getAlpha();
        float f = (((float) ((i >>> 8) & 255)) / 255.0f) * alpha;
        float f2 = (((float) (i & 255)) / 255.0f) * alpha;
        this.mTempColor[0] = (((float) ((i >>> 16) & 255)) / 255.0f) * alpha;
        this.mTempColor[1] = f;
        this.mTempColor[2] = f2;
        this.mTempColor[3] = alpha;
        return this.mTempColor;
    }

    private void enableBlending(boolean z) {
        if (z) {
            GLES20.glEnable(3042);
            checkError();
            return;
        }
        GLES20.glDisable(3042);
        checkError();
    }

    private void setPosition(ShaderParameter[] shaderParameterArr, int i) {
        GLES20.glBindBuffer(34962, this.mBoxCoordinates);
        checkError();
        GLES20.glVertexAttribPointer(shaderParameterArr[0].handle, 2, 5126, false, 8, i * 8);
        checkError();
        GLES20.glBindBuffer(34962, 0);
        checkError();
    }

    private void draw(ShaderParameter[] shaderParameterArr, int i, int i2, float f, float f2, float f3, float f4) {
        setMatrix(shaderParameterArr, f, f2, f3, f4);
        int i3 = shaderParameterArr[0].handle;
        GLES20.glEnableVertexAttribArray(i3);
        checkError();
        GLES20.glDrawArrays(i, 0, i2);
        checkError();
        GLES20.glDisableVertexAttribArray(i3);
        checkError();
    }

    private void setMatrix(ShaderParameter[] shaderParameterArr, float f, float f2, float f3, float f4) {
        Matrix.translateM(this.mTempMatrix, 0, this.mMatrices, this.mCurrentMatrixIndex, f, f2, 0.0f);
        Matrix.scaleM(this.mTempMatrix, 0, f3, f4, 1.0f);
        Matrix.multiplyMM(this.mTempMatrix, 16, this.mProjectionMatrix, 0, this.mTempMatrix, 0);
        GLES20.glUniformMatrix4fv(shaderParameterArr[1].handle, 1, false, this.mTempMatrix, 16);
        checkError();
    }

    public void fillRect(float f, float f2, float f3, float f4, int i) {
        draw(5, 0, 4, f, f2, f3, f4, i, 0.0f);
        this.mCountFillRect++;
    }

    public void drawTexture(BasicTexture basicTexture, int i, int i2, int i3, int i4) {
        if (i3 > 0 && i4 > 0) {
            copyTextureCoordinates(basicTexture, this.mTempSourceRect);
            this.mTempTargetRect.set((float) i, (float) i2, (float) (i + i3), (float) (i2 + i4));
            convertCoordinate(this.mTempSourceRect, this.mTempTargetRect, basicTexture);
            drawTextureRect(basicTexture, this.mTempSourceRect, this.mTempTargetRect);
        }
    }

    private static void copyTextureCoordinates(BasicTexture basicTexture, RectF rectF) {
        int sourceLeft = basicTexture.getSourceLeft();
        int sourceTop = basicTexture.getSourceTop();
        rectF.set((float) sourceLeft, (float) sourceTop, (float) (basicTexture.getSourceWidth() + sourceLeft), (float) (basicTexture.getSourceHeight() + sourceTop));
    }

    public void drawTexture(BasicTexture basicTexture, RectF rectF, RectF rectF2) {
        if (rectF2.width() > 0.0f && rectF2.height() > 0.0f) {
            this.mTempSourceRect.set(rectF);
            this.mTempTargetRect.set(rectF2);
            convertCoordinate(this.mTempSourceRect, this.mTempTargetRect, basicTexture);
            drawTextureRect(basicTexture, this.mTempSourceRect, this.mTempTargetRect);
        }
    }

    public void drawTexture(BasicTexture basicTexture, float[] fArr, int i, int i2, int i3, int i4) {
        if (i3 > 0 && i4 > 0) {
            this.mTempTargetRect.set((float) i, (float) i2, (float) (i + i3), (float) (i2 + i4));
            drawTextureRect(basicTexture, fArr, this.mTempTargetRect);
        }
    }

    private void drawTextureRect(BasicTexture basicTexture, RectF rectF, RectF rectF2) {
        setTextureMatrix(rectF);
        drawTextureRect(basicTexture, this.mTempTextureMatrix, rectF2);
    }

    private void setTextureMatrix(RectF rectF) {
        this.mTempTextureMatrix[0] = rectF.width();
        this.mTempTextureMatrix[5] = rectF.height();
        this.mTempTextureMatrix[12] = rectF.left;
        this.mTempTextureMatrix[13] = rectF.top;
    }

    private static void convertCoordinate(RectF rectF, RectF rectF2, BasicTexture basicTexture) {
        int textureWidth = basicTexture.getTextureWidth();
        int textureHeight = basicTexture.getTextureHeight();
        rectF.left /= (float) textureWidth;
        rectF.right /= (float) textureWidth;
        rectF.top /= (float) textureHeight;
        rectF.bottom /= (float) textureHeight;
    }

    private void drawTextureRect(BasicTexture basicTexture, float[] fArr, RectF rectF) {
        ShaderParameter[] prepareTexture = prepareTexture(basicTexture);
        setPosition(prepareTexture, 0);
        GLES20.glUniformMatrix4fv(prepareTexture[3].handle, 1, false, fArr, 0);
        checkError();
        if (basicTexture.isFlippedVertically()) {
            save(2);
            translate(0.0f, rectF.centerY());
            scale(1.0f, -1.0f, 1.0f);
            translate(0.0f, -rectF.centerY());
        }
        draw(prepareTexture, 5, 4, rectF.left, rectF.top, rectF.width(), rectF.height());
        if (basicTexture.isFlippedVertically()) {
            restore();
        }
        this.mCountTextureRect++;
    }

    private ShaderParameter[] prepareTexture(BasicTexture basicTexture) {
        ShaderParameter[] shaderParameterArr;
        int i;
        if (basicTexture.getTarget() != 3553) {
            shaderParameterArr = this.mOesTextureParameters;
            i = this.mOesTextureProgram;
        } else if (basicTexture.getFormatType() == 1) {
            shaderParameterArr = this.mYUVTextureParameters;
            i = this.mYUVTextureProgram;
        } else {
            shaderParameterArr = this.mTextureParameters;
            i = this.mTextureProgram;
        }
        prepareTexture(basicTexture, i, shaderParameterArr);
        return shaderParameterArr;
    }

    private void prepareTexture(BasicTexture basicTexture, int i, ShaderParameter[] shaderParameterArr) {
        boolean z;
        int i2 = 0;
        GLES20.glUseProgram(i);
        checkError();
        if (!basicTexture.isOpaque() || getAlpha() < OPAQUE_ALPHA) {
            z = true;
        } else {
            z = false;
        }
        enableBlending(z);
        basicTexture.onBind(this);
        int[] id = basicTexture.getId();
        while (i2 < id.length) {
            GLES20.glActiveTexture(33984 + i2);
            checkError();
            GLES20.glBindTexture(basicTexture.getTarget(), id[i2]);
            checkError();
            GLES20.glUniform1i(shaderParameterArr[i2 + 4].handle, i2);
            checkError();
            i2++;
        }
        GLES20.glUniform1f(shaderParameterArr[2].handle, getAlpha());
        checkError();
    }

    public void drawMesh(BasicTexture basicTexture, int i, int i2, int i3, int i4, int i5, int i6) {
        prepareTexture(basicTexture, this.mMeshProgram, this.mMeshParameters);
        GLES20.glBindBuffer(34963, i5);
        checkError();
        GLES20.glBindBuffer(34962, i3);
        checkError();
        int i7 = this.mMeshParameters[0].handle;
        GLES20.glVertexAttribPointer(i7, 2, 5126, false, 8, 0);
        checkError();
        GLES20.glBindBuffer(34962, i4);
        checkError();
        int i8 = this.mMeshParameters[2].handle;
        GLES20.glVertexAttribPointer(i8, 2, 5126, false, 8, 0);
        checkError();
        GLES20.glBindBuffer(34962, 0);
        checkError();
        GLES20.glEnableVertexAttribArray(i7);
        checkError();
        GLES20.glEnableVertexAttribArray(i8);
        checkError();
        setMatrix(this.mMeshParameters, (float) i, (float) i2, 1.0f, 1.0f);
        GLES20.glDrawElements(5, i6, 5121, 0);
        checkError();
        GLES20.glDisableVertexAttribArray(i7);
        checkError();
        GLES20.glDisableVertexAttribArray(i8);
        checkError();
        GLES20.glBindBuffer(34963, 0);
        checkError();
        this.mCountDrawMesh++;
    }

    public void drawMixed(BasicTexture basicTexture, int i, float f, int i2, int i3, int i4, int i5) {
        copyTextureCoordinates(basicTexture, this.mTempSourceRect);
        this.mTempTargetRect.set((float) i2, (float) i3, (float) (i2 + i4), (float) (i3 + i5));
        drawMixed(basicTexture, i, f, this.mTempSourceRect, this.mTempTargetRect);
    }

    public void drawMixed(BasicTexture basicTexture, int i, float f, RectF rectF, RectF rectF2) {
        if (rectF2.width() > 0.0f && rectF2.height() > 0.0f) {
            save(1);
            float alpha = getAlpha();
            float min = Math.min(1.0f, Math.max(0.0f, f));
            setAlpha((1.0f - min) * alpha);
            drawTexture(basicTexture, rectF, rectF2);
            setAlpha(alpha * min);
            fillRect(rectF2.left, rectF2.top, rectF2.width(), rectF2.height(), i);
            restore();
        }
    }

    public boolean unloadTexture(BasicTexture basicTexture) {
        boolean isLoaded = basicTexture.isLoaded();
        if (isLoaded) {
            synchronized (this.mUnboundTextures) {
                this.mUnboundTextures.add(basicTexture.getId()[0]);
            }
        }
        return isLoaded;
    }

    public void deleteBuffer(int i) {
        synchronized (this.mUnboundTextures) {
            this.mDeleteBuffers.add(i);
        }
    }

    public void deleteRecycledResources() {
        synchronized (this.mUnboundTextures) {
            IntArray intArray = this.mUnboundTextures;
            if (this.mUnboundTextures.size() > 0) {
                mGLId.glDeleteTextures(null, intArray.size(), intArray.getInternalArray(), 0);
                intArray.clear();
            }
            intArray = this.mDeleteBuffers;
            if (intArray.size() > 0) {
                mGLId.glDeleteBuffers(null, intArray.size(), intArray.getInternalArray(), 0);
                intArray.clear();
            }
        }
    }

    public void dumpStatisticsAndClear() {
        String format = String.format("MESH:%d, TEX_RECT:%d, FILL_RECT:%d, LINE:%d", new Object[]{Integer.valueOf(this.mCountDrawMesh), Integer.valueOf(this.mCountTextureRect), Integer.valueOf(this.mCountFillRect), Integer.valueOf(this.mCountDrawLine)});
        this.mCountDrawMesh = 0;
        this.mCountTextureRect = 0;
        this.mCountFillRect = 0;
        this.mCountDrawLine = 0;
        Log.d(TAG, format);
    }

    public void setTextureParameters(BasicTexture basicTexture) {
        int target = basicTexture.getTarget();
        GLES20.glBindTexture(target, basicTexture.getId()[0]);
        checkError();
        GLES20.glTexParameteri(target, 10242, 33071);
        GLES20.glTexParameteri(target, 10243, 33071);
        GLES20.glTexParameterf(target, 10241, 9729.0f);
        GLES20.glTexParameterf(target, 10240, 9729.0f);
    }

    public void initializeTextureSize(BasicTexture basicTexture, int i, int i2) {
        int target = basicTexture.getTarget();
        GLES20.glBindTexture(target, basicTexture.getId()[0]);
        checkError();
        GLES20.glTexImage2D(target, 0, i, basicTexture.getTextureWidth(), basicTexture.getTextureHeight(), 0, i, i2, null);
    }

    public void initializeTexture(BasicTexture basicTexture, Bitmap bitmap) {
        int target = basicTexture.getTarget();
        GLES20.glBindTexture(target, basicTexture.getId()[0]);
        checkError();
        GLUtils.texImage2D(target, 0, bitmap, 0);
    }

    public void texSubImage2D(BasicTexture basicTexture, int i, int i2, Bitmap bitmap, int i3, int i4) {
        int target = basicTexture.getTarget();
        GLES20.glBindTexture(target, basicTexture.getId()[0]);
        checkError();
        GLUtils.texSubImage2D(target, 0, i, i2, bitmap, i3, i4);
    }

    public int uploadBuffer(FloatBuffer floatBuffer) {
        return uploadBuffer(floatBuffer, 4);
    }

    public int uploadBuffer(ByteBuffer byteBuffer) {
        return uploadBuffer(byteBuffer, 1);
    }

    private int uploadBuffer(Buffer buffer, int i) {
        mGLId.glGenBuffers(1, this.mTempIntArray, 0);
        checkError();
        int i2 = this.mTempIntArray[0];
        GLES20.glBindBuffer(34962, i2);
        checkError();
        GLES20.glBufferData(34962, buffer.capacity() * i, buffer, 35044);
        checkError();
        return i2;
    }

    public static void checkError() {
        int glGetError = GLES20.glGetError();
        if (glGetError != 0) {
            Log.e(TAG, "GL error: " + glGetError, new Throwable());
        }
    }

    private static void printMatrix(String str, float[] fArr, int i) {
        StringBuilder stringBuilder = new StringBuilder(str);
        for (int i2 = 0; i2 < 16; i2++) {
            stringBuilder.append(' ');
            if (i2 % 4 == 0) {
                stringBuilder.append('\n');
            }
            stringBuilder.append(fArr[i + i2]);
        }
        Log.v(TAG, stringBuilder.toString());
    }

    public void recoverFromLightCycle() {
        GLES20.glViewport(0, 0, this.mWidth, this.mHeight);
        GLES20.glDisable(2929);
        GLES20.glBlendFunc(1, 771);
        checkError();
    }

    public void getBounds(Rect rect, int i, int i2, int i3, int i4) {
        Matrix.translateM(this.mTempMatrix, 0, this.mMatrices, this.mCurrentMatrixIndex, (float) i, (float) i2, 0.0f);
        Matrix.scaleM(this.mTempMatrix, 0, (float) i3, (float) i4, 1.0f);
        Matrix.multiplyMV(this.mTempMatrix, 16, this.mTempMatrix, 0, BOUNDS_COORDINATES, 0);
        Matrix.multiplyMV(this.mTempMatrix, 20, this.mTempMatrix, 0, BOUNDS_COORDINATES, 4);
        rect.left = Math.round(this.mTempMatrix[16]);
        rect.right = Math.round(this.mTempMatrix[20]);
        rect.top = Math.round(this.mTempMatrix[17]);
        rect.bottom = Math.round(this.mTempMatrix[21]);
        rect.sort();
    }

    public GLId getGLId() {
        return mGLId;
    }
}
