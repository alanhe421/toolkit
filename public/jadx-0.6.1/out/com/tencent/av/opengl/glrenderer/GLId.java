package com.tencent.av.opengl.glrenderer;

import javax.microedition.khronos.opengles.GL11;
import javax.microedition.khronos.opengles.GL11ExtensionPack;

public interface GLId {
    int generateTexture();

    void glDeleteBuffers(GL11 gl11, int i, int[] iArr, int i2);

    void glDeleteFramebuffers(GL11ExtensionPack gL11ExtensionPack, int i, int[] iArr, int i2);

    void glDeleteTextures(GL11 gl11, int i, int[] iArr, int i2);

    void glGenBuffers(int i, int[] iArr, int i2);
}
