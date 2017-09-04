package com.qq.reader.module.bookstore.qnative.card.impl;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.qq.reader.common.imageloader.a;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.utils.j;
import com.qq.reader.module.bookstore.qnative.item.g;
import com.tencent.feedback.proguard.R;

public class SingleBookInfo extends LinearLayout {
    private ImageView a;
    private TextView b;
    private TextView c;
    private TextView d;
    private TextView e;
    private TextView f;
    private TextView g;
    private View h;
    private Context i;

    public SingleBookInfo(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.i = context;
        LayoutInflater.from(context).inflate(R.layout.single_bookitem_layout, this, true);
        a();
    }

    private void a() {
        this.a = (ImageView) findViewById(R.id.concept_cover_img);
        this.b = (TextView) findViewById(R.id.concept_title);
        this.c = (TextView) findViewById(R.id.concept_author);
        this.d = (TextView) findViewById(R.id.concept_content);
        this.h = findViewById(R.id.localstore_adv_divider);
        this.e = (TextView) findViewById(R.id.concept_tag_1);
        this.f = (TextView) findViewById(R.id.concept_tag_2);
        this.g = (TextView) findViewById(R.id.concept_tag_3);
    }

    public void setBookInfo(g gVar) {
        this.b.setText(gVar.n());
        this.d.setText(gVar.s());
        this.c.setText(gVar.q());
        c.a(getContext()).a(gVar.f(), this.a, a.a().j());
        if (gVar.E()) {
            if (gVar.p() > 0) {
                this.e.setVisibility(0);
                this.e.setBackgroundColor(getResources().getColor(R.color.translucent));
                this.e.setText(gVar.p() + "人订阅");
                this.e.setTextSize(0, getResources().getDimension(R.dimen.text_size_class_2));
                this.e.setTextColor(getResources().getColor(R.color.text_color_c201));
            } else {
                this.e.setVisibility(8);
            }
            this.g.setVisibility(8);
            return;
        }
        this.e.setVisibility(0);
        this.e.setBackgroundResource(R.drawable.concept_bookitem_tag_level3);
        this.e.setTextSize(0, getResources().getDimension(R.dimen.text_size_class_1));
        this.e.setTextColor(getResources().getColor(R.color.common_textcolor_secondary));
        this.g.setVisibility(0);
        this.e.setText(gVar.r());
        int i = gVar.e;
        this.g.setText(j.a(i));
        if (i <= 0) {
            this.g.setVisibility(8);
        }
    }

    public void setBookInfoCategoryByCategoryType(g gVar, int i) {
        this.b.setText(gVar.n());
        this.d.setText(gVar.s());
        this.c.setText(gVar.q());
        c.a(getContext()).a(gVar.f(), this.a, a.a().j());
        if (gVar.E()) {
            if (gVar.p() > 0) {
                this.e.setVisibility(0);
                this.e.setBackgroundColor(getResources().getColor(R.color.translucent));
                this.e.setText(gVar.p() + "人订阅");
                this.e.setTextSize(0, getResources().getDimension(R.dimen.text_size_class_2));
                this.e.setTextColor(getResources().getColor(R.color.text_color_c201));
            } else {
                this.e.setVisibility(8);
            }
            this.g.setVisibility(8);
            return;
        }
        a(gVar, i);
    }

    private void a(g gVar, int i) {
        this.e.setVisibility(0);
        this.e.setBackgroundResource(R.drawable.concept_bookitem_tag_level3);
        this.e.setTextSize(0, getResources().getDimension(R.dimen.text_size_class_1));
        this.e.setTextColor(getResources().getColor(R.color.common_textcolor_secondary));
        this.g.setVisibility(0);
        int i2;
        switch (i) {
            case 1:
                a(this.e, gVar.F());
                i2 = gVar.e;
                this.g.setText(j.a(i2));
                if (i2 <= 0) {
                    this.g.setVisibility(8);
                    return;
                }
                return;
            case 2:
                a(this.e, gVar.F());
                a(this.g, gVar.G());
                return;
            case 3:
                a(this.e, gVar.G());
                i2 = gVar.e;
                this.g.setText(j.a(i2));
                if (i2 <= 0) {
                    this.g.setVisibility(8);
                    return;
                }
                return;
            case 4:
                a(this.e, gVar.G());
                double o = gVar.o();
                this.g.setText(o + "分");
                if (o < 5.0d) {
                    this.g.setVisibility(8);
                    return;
                }
                return;
            case 5:
                a(this.e, gVar.F());
                a(this.g, gVar.G());
                return;
            default:
                setBookInfo(gVar);
                return;
        }
    }

    private void a(TextView textView, String str) {
        if (TextUtils.isEmpty(str)) {
            textView.setVisibility(8);
            return;
        }
        textView.setVisibility(0);
        textView.setText(str);
    }

    public void setBookInfoCategoryLv2(g gVar) {
        this.b.setText(gVar.n());
        this.d.setText(gVar.s());
        this.c.setText(gVar.q());
        c.a(getContext()).a(gVar.f(), this.a, a.a().j());
        if (gVar.E()) {
            if (gVar.p() > 0) {
                this.e.setVisibility(0);
                this.e.setBackgroundColor(getResources().getColor(R.color.translucent));
                this.e.setText(gVar.p() + "人订阅");
                this.e.setTextSize(0, getResources().getDimension(R.dimen.text_size_class_2));
                this.e.setTextColor(getResources().getColor(R.color.text_color_c201));
            } else {
                this.e.setVisibility(8);
            }
            this.g.setVisibility(8);
            return;
        }
        this.e.setVisibility(0);
        this.e.setBackgroundResource(R.drawable.concept_bookitem_tag_level3);
        this.e.setTextSize(0, getResources().getDimension(R.dimen.text_size_class_1));
        this.e.setTextColor(getResources().getColor(R.color.common_textcolor_secondary));
        this.g.setVisibility(0);
        this.e.setText(gVar.F());
        int i = gVar.e;
        this.g.setText(j.a(i));
        if (i <= 0) {
            this.g.setVisibility(8);
        }
    }

    public void setBookInfoByFeedFirstPage(g gVar) {
        this.b.setText(gVar.n());
        this.d.setText(gVar.s());
        this.c.setText(gVar.q());
        c.a(getContext()).a(gVar.f(), this.a, a.a().j());
        if (TextUtils.isEmpty(gVar.F())) {
            this.e.setVisibility(8);
        } else {
            this.e.setText(gVar.F());
            this.e.setBackgroundDrawable(this.i.getResources().getDrawable(R.drawable.concept_bookitem_tag_level2));
        }
        if (TextUtils.isEmpty(gVar.G())) {
            this.g.setVisibility(8);
        } else {
            this.g.setText(gVar.G());
        }
    }

    public void setBookInfoByRecommendPage(g gVar) {
        this.b.setText(gVar.n());
        this.d.setText(gVar.s());
        this.c.setText(gVar.q());
        c.a(getContext()).a(gVar.f(), this.a, a.a().j());
        int H = gVar.H();
        if (H == 0) {
            if (TextUtils.isEmpty(gVar.F())) {
                this.e.setVisibility(8);
            } else {
                this.e.setText(gVar.F());
                this.e.setBackgroundDrawable(this.i.getResources().getDrawable(R.drawable.concept_bookitem_tag_level2));
            }
            H = gVar.e;
            this.g.setText(j.a(H));
            if (H <= 0) {
                this.g.setVisibility(8);
            }
        } else if (H == 1) {
            if (TextUtils.isEmpty(gVar.F())) {
                this.e.setVisibility(8);
            } else {
                this.e.setText(gVar.F());
                this.e.setBackgroundDrawable(this.i.getResources().getDrawable(R.drawable.concept_bookitem_tag_level2));
            }
            if (TextUtils.isEmpty(gVar.G())) {
                this.g.setVisibility(8);
            } else {
                this.g.setText(gVar.G());
            }
        }
    }

    public void a(boolean z) {
        this.h.setVisibility(z ? 0 : 8);
    }
}
