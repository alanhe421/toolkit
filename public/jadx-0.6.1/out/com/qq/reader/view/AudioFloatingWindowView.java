package com.qq.reader.view;

import android.content.Context;
import android.util.AttributeSet;

public class AudioFloatingWindowView extends QRImageView {
    private long a;

    public AudioFloatingWindowView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setAdid(long j) {
        this.a = j;
    }
}
