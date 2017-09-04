package com.qq.reader.module.readpage;

import android.graphics.Rect;

/* compiled from: QRActiveElement */
public class m {
    private Rect a;

    public m(Rect rect) {
        this.a = rect;
    }

    public boolean a(int i, int i2) {
        if (this.a != null) {
            return this.a.contains(i, i2);
        }
        return false;
    }
}
