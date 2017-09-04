package com.qq.reader.view.metro;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tencent.feedback.proguard.R;

public class MetroItemView extends RelativeLayout {
    TextView a;
    ImageView b;

    public MetroItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void a() {
        this.a = (TextView) findViewById(R.id.category_name);
        this.b = (ImageView) findViewById(R.id.category_selected);
    }

    public void setPosition(int i) {
        setTag(new Integer(i));
    }

    public void setDisplayName(String str) {
        this.a.setText(str);
    }

    public void setTextViewBackground(Drawable drawable) {
        this.a.setBackgroundDrawable(drawable);
    }

    public void setTextViewBackgroundRes(int i) {
        this.a.setBackgroundResource(i);
    }

    public void setSelected(boolean z) {
        if (z) {
            this.b.setVisibility(0);
        } else {
            this.b.setVisibility(4);
        }
    }
}
