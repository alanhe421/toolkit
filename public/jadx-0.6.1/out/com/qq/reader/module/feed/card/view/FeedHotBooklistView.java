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
import com.qq.reader.module.bookstore.qnative.item.u;
import com.tencent.feedback.proguard.R;

public class FeedHotBooklistView extends LinearLayout {
    private ImageView a;
    private TextView b;
    private TextView c;
    private View d;

    public FeedHotBooklistView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.concept_feedhotbooklistview, this, true);
        a();
    }

    private void a() {
        this.a = (ImageView) findViewById(R.id.concept_cover_img);
        this.b = (TextView) findViewById(R.id.concept_title);
        this.c = (TextView) findViewById(R.id.concept_content);
        this.d = findViewById(R.id.concept_divider);
    }

    public void setDividerVisible(boolean z) {
        if (z) {
            this.d.setVisibility(0);
        } else {
            this.d.setVisibility(8);
        }
    }

    public void setBookCollectListItemData(u uVar) {
        c.a(getContext()).a(uVar.b(), this.a, a.a().j());
        this.b.setText(uVar.a());
        this.c.setText(uVar.c());
    }
}
