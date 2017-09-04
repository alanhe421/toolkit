package com.qq.reader.view.web;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WebPagePopupMenuItemView extends LinearLayout {
    private TextView a;
    private ImageView b;

    public WebPagePopupMenuItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setText(String str) {
        this.a.setText(str);
    }

    public void setIsSel(boolean z) {
        this.b.setVisibility(z ? 0 : 8);
    }
}
