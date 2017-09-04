package com.qq.reader.module.bookstore.qnative.card.impl;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.load.resource.a.b;
import com.bumptech.glide.request.e;
import com.qq.reader.common.imageloader.a;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.utils.ao;
import com.qq.reader.module.bookstore.qnative.item.o;
import com.tencent.feedback.proguard.R;

public class BookInfo4Detail extends RelativeLayout {
    private ImageView a;
    private TextView b;
    private TextView c;
    private RatingBar d;
    private TextView e;
    private TextView f;
    private TextView g;
    private TextView h;
    private TextView i;
    private TextView j;
    private TextView k;
    private ImageView l;
    private View m;

    public BookInfo4Detail(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.localstore_card_bookinfo_fordetail, this, true);
        a();
    }

    private void a() {
        this.a = (ImageView) findViewById(R.id.bookinfo_cover);
        this.b = (TextView) findViewById(R.id.bookinfo_status);
        this.c = (TextView) findViewById(R.id.bookinfo_name);
        this.d = (RatingBar) findViewById(R.id.bookinfo_ratingbar);
        this.d.setNumStars(5);
        this.e = (TextView) findViewById(R.id.bookinfo_ratingbar_text);
        this.f = (TextView) findViewById(R.id.bookinfo_ratingbar_text_extra);
        this.h = (TextView) findViewById(R.id.bookinfo_category);
        this.g = (TextView) findViewById(R.id.bookinfo_author);
        this.i = (TextView) findViewById(R.id.bookinfo_words);
        this.j = (TextView) findViewById(R.id.bookinfo_price);
        this.k = (TextView) findViewById(R.id.bookinfo_action);
        this.l = (ImageView) findViewById(R.id.bookinfo_action_right_arrow);
        this.m = findViewById(R.id.bookinfo_ratinglayout);
    }

    public void setImageClickListener(OnClickListener onClickListener) {
        this.a.setOnClickListener(onClickListener);
    }

    public void setBookInfo(o oVar, e<String, b> eVar) {
        c.a(getContext()).a(oVar.a(), this.a, a.a().j(), (e) eVar);
        if (ao.m(oVar.e())) {
            this.c.setText(oVar.f());
            setTextBold(this.c);
            int a = a(oVar.o());
            if (a == 0) {
                this.b.setBackgroundDrawable(null);
            } else {
                this.b.setBackgroundResource(a);
            }
            if (oVar.x() < 0.0f) {
                this.d.setRating(0.0f);
            } else {
                this.d.setRating(oVar.x());
            }
            if (TextUtils.isEmpty(oVar.y())) {
                this.e.setVisibility(8);
            } else {
                this.e.setText(oVar.y() + "分");
                this.e.setVisibility(0);
            }
            this.f.setText(oVar.w());
            this.h.setText(oVar.h());
            this.g.setText(oVar.g());
            this.i.setText(oVar.j());
            this.j.setText(oVar.p());
            String q = oVar.q();
            if (ao.s(q)) {
                this.k.setVisibility(8);
            } else {
                this.k.setText(q);
                this.k.setVisibility(0);
            }
            if (oVar.l() || oVar.k() || oVar.n() || oVar.m()) {
                this.k.setTextColor(getResources().getColorStateList(R.color.localstore_textcolor_detail_orange_selector));
                this.l.setVisibility(0);
                return;
            }
            this.k.setTextColor(getResources().getColor(R.color.text_color_c102));
            this.l.setVisibility(8);
            return;
        }
        this.e.setVisibility(8);
        this.d.setVisibility(8);
        this.f.setVisibility(8);
        this.i.setVisibility(8);
        this.c.setText(oVar.f());
        setTextBold(this.c);
        this.h.setText(oVar.h());
        this.g.setText(oVar.g());
        this.l.setVisibility(4);
        this.k.setVisibility(0);
        this.k.setText("本书暂未上架,我们正在努力采购中");
        this.k.setTextColor(getResources().getColor(R.color.text_color_c103));
    }

    private int a(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        if (str.equals("包月")) {
            return R.drawable.detail_status_bg_month_free;
        }
        if (str.equals("特价")) {
            return R.drawable.detail_status_bg_discount;
        }
        if (str.equals("限免")) {
            return R.drawable.detail_status_bg_limit_free;
        }
        if (str.equals("免费")) {
            return R.drawable.detail_status_bg_free;
        }
        return 0;
    }

    public TextView getAction() {
        return this.k;
    }

    public TextView getAuthor() {
        return this.g;
    }

    public TextView getCategory() {
        return this.h;
    }

    private void setTextBold(TextView textView) {
        if (textView != null) {
            textView.getPaint().setFakeBoldText(true);
        }
    }
}
