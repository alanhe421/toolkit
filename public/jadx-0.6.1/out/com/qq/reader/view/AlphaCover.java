package com.qq.reader.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class AlphaCover extends FrameLayout {
    private ImageView a;

    public AlphaCover(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    public AlphaCover(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public AlphaCover(Context context) {
        super(context);
        a();
    }

    private void a() {
        this.a = new ImageView(getContext());
        this.a.setLayoutParams(new LayoutParams(-1, -1));
    }

    public void setImageResource(int i) {
        this.a.setImageResource(i);
    }

    public void setScaleType(ScaleType scaleType) {
        this.a.setScaleType(scaleType);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        super.setOnClickListener(onClickListener);
    }
}
