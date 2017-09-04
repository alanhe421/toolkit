package com.qq.reader.module.bookchapter;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tencent.feedback.proguard.R;

public class ChapterAdapterItem extends RelativeLayout {
    TextView a;
    TextView b = null;
    ImageView c = null;

    public ChapterAdapterItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ChapterAdapterItem(Context context) {
        super(context);
    }

    public void a() {
        this.a = (TextView) findViewById(R.id.tv);
        this.b = (TextView) findViewById(R.id.imageView_choice);
        this.b.setVisibility(8);
        this.c = (ImageView) findViewById(R.id.lock);
    }

    public void setText(String str) {
        if (str != null) {
            this.a.setText(str);
        }
    }

    public void setCurChapter(boolean z) {
        if (z) {
            this.a.setTextColor(getResources().getColor(R.color.text_color_c301));
        }
    }

    public void setIsFree(boolean z) {
        if (z) {
            this.c.setVisibility(8);
        }
    }

    public void setPurchased(boolean z) {
        if (z) {
            this.c.setVisibility(8);
        } else {
            this.c.setVisibility(0);
        }
    }

    public void setIsDownloaded(boolean z) {
        if (z) {
            this.a.setTextColor(getResources().getColor(R.color.text_color_c101));
        } else {
            this.a.setTextColor(getResources().getColor(R.color.text_color_c103));
        }
    }
}
