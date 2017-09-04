package com.qq.reader.module.feed.card.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.qq.reader.common.imageloader.a;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.module.bookstore.qnative.item.t;
import com.tencent.feedback.proguard.R;

public class FeedBookPackView extends LinearLayout {
    private ImageView a;
    private TextView b;
    private TextView c;
    private TextView d;
    private View e;

    public FeedBookPackView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.concept_bookpackview, this, true);
        a();
    }

    private void a() {
        this.a = (ImageView) findViewById(R.id.concept_cover_img);
        this.b = (TextView) findViewById(R.id.concept_title);
        this.c = (TextView) findViewById(R.id.concept_size);
        this.d = (TextView) findViewById(R.id.concept_content);
        this.e = findViewById(R.id.concept_divider);
    }

    public void setDividerVisible(boolean z) {
        if (z) {
            this.e.setVisibility(0);
        } else {
            this.e.setVisibility(8);
        }
    }

    public void setBookBagItemData(t tVar) {
        c.a(getContext()).a(tVar.b(), this.a, a.a().j());
        this.b.setText(tVar.a());
        this.c.setText(tVar.c() + "本");
        this.d.setText(tVar.d());
    }
}
