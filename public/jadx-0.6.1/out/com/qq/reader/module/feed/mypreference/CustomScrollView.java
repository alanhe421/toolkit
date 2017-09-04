package com.qq.reader.module.feed.mypreference;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class CustomScrollView extends ScrollView {
    private a a;

    public interface a {
        void a(int i, int i2, int i3, int i4);
    }

    public CustomScrollView(Context context) {
        super(context);
    }

    public CustomScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CustomScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setOnScrollListener(a aVar) {
        this.a = aVar;
    }

    public void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        if (this.a != null) {
            this.a.a(i, i2, i3, i4);
        }
    }
}
