package com.qq.reader.module.bookstore.qnative.card.impl;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.qq.reader.common.imageloader.a;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.module.bookstore.qnative.item.g;
import com.tencent.feedback.proguard.R;

public class SingleListenBookInfo extends RelativeLayout {
    private ImageView a;
    private TextView b;
    private TextView c;
    private TextView d;
    private View e;

    public SingleListenBookInfo(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.single_bookitem_listen_layout, this, true);
        a();
    }

    private void a() {
        this.a = (ImageView) findViewById(R.id.concept_cover_img);
        this.b = (TextView) findViewById(R.id.concept_title);
        this.c = (TextView) findViewById(R.id.concept_anchor);
        this.d = (TextView) findViewById(R.id.concept_content);
        this.e = findViewById(R.id.localstore_adv_divider);
    }

    public void setBookInfo(g gVar) {
        this.b.setText(gVar.n());
        this.d.setText(gVar.s());
        this.c.setText(gVar.h());
        c.a(getContext()).a(gVar.f(), this.a, a.a().j());
    }

    public void a(boolean z) {
        this.e.setVisibility(z ? 0 : 8);
    }
}
