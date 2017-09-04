package com.tencent.av.opengl.glrenderer;

import android.opengl.GLES20;
import javax.microedition.khronos.opengles.GL11;
import javax.microedition.khronos.opengles.GL11ExtensionPack;

public class GLES20Id implements GLId {
    private final int[] mTempIntArray = new int[1];

    public int generateTexture() {
        GLES20.glGenTextures(1, this.mTempIntArray, 0);
        GLES20Canvas.checkError();
        return this.mTempIntArray[0];
    }

    public void glGenBuffers(int i, int[] iArr, int i2) {
        GLES20.glGenBuffers(i, iArr, i2);
        GLES20Canvas.checkError();
    }

    public void glDeleteTextures(GL11 gl11, int i, int[] iArr, int i2) {
        GLES20.glDeleteTextures(i, iArr, i2);
        GLES20Canvas.checkError();
    }

    public void glDeleteBuffers(GL11 gl11, int i, int[] iArr, int i2) {
        GLES20.glDeleteBuffers(i, iArr, i2);
        GLES20Canvas.checkError();
    }

    public void glDeleteFramebuffers(GL11ExtensionPack gL11ExtensionPack, int i, int[] iArr, int i2) {
        GLES20.glDeleteFramebuffers(i, iArr, i2);
        GLES20Canvas.checkError();
    }
}
