package com.qq.reader.readengine.a;

import android.text.TextPaint;

/* compiled from: LayoutValueCache */
public class b {
    private float a;

    public float a(char c, TextPaint textPaint) {
        if (c <= 'ÿ' || c == '“' || c == '”' || c == '‘' || c == '’' || c == '…') {
            return textPaint.measureText(new char[]{c}, 0, 1);
        }
        if (this.a <= 0.0f) {
            this.a = textPaint.measureText("中");
        }
        return this.a;
    }

    public void a() {
        this.a = -1.0f;
    }
}
