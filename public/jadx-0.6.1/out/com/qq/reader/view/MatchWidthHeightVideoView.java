package com.qq.reader.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.VideoView;

public class MatchWidthHeightVideoView extends VideoView {
    public MatchWidthHeightVideoView(Context context) {
        super(context);
    }

    public MatchWidthHeightVideoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MatchWidthHeightVideoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void onMeasure(int i, int i2) {
        int size = MeasureSpec.getSize(i);
        int mode = MeasureSpec.getMode(i);
        int size2 = MeasureSpec.getSize(i2);
        int mode2 = MeasureSpec.getMode(i2);
        if (mode == 1073741824 && mode2 == 1073741824) {
            setMeasuredDimension(size, size2);
        } else {
            super.onMeasure(i, i2);
        }
    }
}
