package com.tencent.av.opengl.glrenderer;

import javax.microedition.khronos.opengles.GL11;
import javax.microedition.khronos.opengles.GL11ExtensionPack;

public class GLES11Id implements GLId {
    private static Object sLock = new Object();
    private static int sNextId = 1;

    public int generateTexture() {
        int i;
        synchronized (sLock) {
            i = sNextId;
            sNextId = i + 1;
        }
        return i;
    }

    public void glGenBuffers(int i, int[] iArr, int i2) {
        synchronized (sLock) {
            while (true) {
                int i3 = i - 1;
                if (i > 0) {
                    int i4 = i2 + i3;
                    int i5 = sNextId;
                    sNextId = i5 + 1;
                    iArr[i4] = i5;
                    i = i3;
                }
            }
        }
    }

    public void glDeleteTextures(GL11 gl11, int i, int[] iArr, int i2) {
        synchronized (sLock) {
            gl11.glDeleteTextures(i, iArr, i2);
        }
    }

    public void glDeleteBuffers(GL11 gl11, int i, int[] iArr, int i2) {
        synchronized (sLock) {
            gl11.glDeleteBuffers(i, iArr, i2);
        }
    }

    public void glDeleteFramebuffers(GL11ExtensionPack gL11ExtensionPack, int i, int[] iArr, int i2) {
        synchronized (sLock) {
            gL11ExtensionPack.glDeleteFramebuffersOES(i, iArr, i2);
        }
    }
}
