package com.tencent.av.opengl.glrenderer;

import junit.framework.Assert;

public class GLPaint {
    private int mColor = 0;
    private float mLineWidth = 1.0f;

    public void setColor(int i) {
        this.mColor = i;
    }

    public int getColor() {
        return this.mColor;
    }

    public void setLineWidth(float f) {
        Assert.assertTrue(f >= 0.0f);
        this.mLineWidth = f;
    }

    public float getLineWidth() {
        return this.mLineWidth;
    }
}
