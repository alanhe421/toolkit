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
import com.qq.reader.common.utils.ao;
import com.qq.reader.module.bookstore.qnative.item.q;
import com.qq.reader.widget.ImageMaskView;
import com.tencent.feedback.proguard.R;

public class HallOfFameAuthorItemView extends LinearLayout {
    private ImageMaskView a;
    private ImageView b;
    private TextView c;
    private TextView d;
    private View e;

    public HallOfFameAuthorItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.localstore_card_author_right, this, true);
        a();
    }

    private void a() {
        this.a = (ImageMaskView) findViewById(R.id.author_head_img);
        this.b = (ImageView) findViewById(R.id.author_head_level);
        this.c = (TextView) findViewById(R.id.author_info_title);
        this.d = (TextView) findViewById(R.id.author_info_introduction);
        this.e = findViewById(R.id.author_divider_line);
    }

    public void setAuthorItemData(q qVar) {
        c.a(getContext()).a(qVar.a(), this.a.getImageView(), a.a().j());
        this.b.setBackgroundResource(a(qVar));
        this.c.setText(qVar.b());
        this.d.setText(qVar.d());
        this.e.setVisibility(0);
    }

    private int a(q qVar) {
        return ao.e(Integer.valueOf(qVar.c()).intValue());
    }
}
