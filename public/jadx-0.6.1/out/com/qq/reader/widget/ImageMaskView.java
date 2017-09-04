package com.qq.reader.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.qq.reader.common.utils.ao;
import com.tencent.feedback.proguard.R;

public class ImageMaskView extends FrameLayout {
    private ImageView a;
    private ImageView b;
    private ImageView c;
    private Context d;

    public ImageMaskView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = context;
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        this.a = new ImageView(context);
        this.a.setScaleType(ScaleType.FIT_XY);
        this.a.setLayoutParams(new LayoutParams(-1, -1));
        this.b = new ImageView(context);
        this.b.setScaleType(ScaleType.FIT_XY);
        this.b.setLayoutParams(new LayoutParams(-1, -1));
        int attributeResourceValue = attributeSet.getAttributeResourceValue(null, "imagemask", 0);
        if (attributeResourceValue != 0) {
            this.b.setBackgroundResource(attributeResourceValue);
        }
        addView(this.a);
        addView(this.b);
    }

    public ImageView getImageView() {
        return this.a;
    }

    public void setRedTipVisibility(boolean z) {
        if (z) {
            if (this.c == null) {
                this.c = new ImageView(this.d);
                this.c.setImageResource(R.drawable.bookshelf_news_newtip);
                this.c.setLayoutParams(new LayoutParams(ao.a(8.0f), ao.a(8.0f), 53));
                addView(this.c);
            }
            this.c.setVisibility(0);
        } else if (this.c != null) {
            this.c.setVisibility(8);
        }
    }
}
