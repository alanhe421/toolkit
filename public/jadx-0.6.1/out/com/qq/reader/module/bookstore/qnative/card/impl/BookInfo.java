package com.qq.reader.module.bookstore.qnative.card.impl;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import com.qq.reader.common.imageloader.a;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.utils.j;
import com.qq.reader.module.bookstore.qnative.item.g;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.tencent.feedback.proguard.R;

public class BookInfo extends LinearLayout {
    private ImageView a;
    private TextView b;
    private TextView c;
    private TextView d;
    private RatingBar e;
    private TextView f;
    private TextView g;
    private View h;
    private View i;
    private TextView j;
    private TextView k;

    public BookInfo(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.localstore_card_bookinfo, this, true);
        a();
    }

    private void a() {
        this.a = (ImageView) findViewById(R.id.bookinfo_cover);
        this.b = (TextView) findViewById(R.id.bookinfo_name);
        this.c = (TextView) findViewById(R.id.bookinfo_typeandauthor);
        this.d = (TextView) findViewById(R.id.bookinfo_introduction);
        this.e = (RatingBar) findViewById(R.id.bookinfo_ratingbar);
        this.f = (TextView) findViewById(R.id.bookinfo_ratingbar_text);
        this.g = (TextView) findViewById(R.id.bookinfo_popularity);
        this.h = findViewById(R.id.bookinfo_ratinglayout);
        this.i = findViewById(R.id.bookinfo_textinfolayout);
        this.j = (TextView) findViewById(R.id.bookinfo_text1);
        this.k = (TextView) findViewById(R.id.bookinfo_text2);
    }

    public void setBookInfo(g gVar) {
        this.b.setText(gVar.n());
        this.d.setText(gVar.s());
        this.c.setText(gVar.r() + " | " + gVar.q());
        c.a(getContext()).a(gVar.f(), this.a, a.a().j());
        if (TextUtils.isEmpty(gVar.w())) {
            this.h.setVisibility(8);
            this.i.setVisibility(8);
        } else if (gVar.w().equals("mark")) {
            a(gVar.x(), a(gVar));
        } else if (gVar.w().equals("lastChapter")) {
            r0 = new StringBuilder();
            if (gVar.i() == 0) {
                r0.append("连载至");
            } else {
                r0.append("完结共");
            }
            r0.append(gVar.x()).append("章");
            b(r0.toString(), a(gVar));
        } else if (gVar.w().equals("updateDate")) {
            r0 = new StringBuilder();
            String a = j.a(gVar.x());
            if (a == null) {
                r0.append("");
            } else {
                r0.append(a).append("更新：").append(gVar.A());
            }
            b(r0.toString(), null);
        } else {
            this.h.setVisibility(8);
            this.i.setVisibility(8);
        }
    }

    private String a(g gVar) {
        long longValue;
        StringBuilder stringBuilder = new StringBuilder();
        String z = gVar.z();
        if (z != null) {
            try {
                if (z.length() > 0) {
                    longValue = Long.valueOf(z).longValue();
                    z = s.countTransform(longValue);
                    if (longValue == 0) {
                        return null;
                    }
                    if (gVar.y().equals("favor")) {
                        stringBuilder.append(z).append("人收藏");
                    } else if (gVar.y().equals("follow")) {
                        stringBuilder.append(z).append("人追更");
                    }
                    return stringBuilder.toString();
                }
            } catch (Exception e) {
                e.printStackTrace();
                longValue = 0;
            }
        }
        longValue = 0;
        if (longValue == 0) {
            return null;
        }
        if (gVar.y().equals("favor")) {
            stringBuilder.append(z).append("人收藏");
        } else if (gVar.y().equals("follow")) {
            stringBuilder.append(z).append("人追更");
        }
        return stringBuilder.toString();
    }

    private void a(String str, String str2) {
        float floatValue;
        this.h.setVisibility(0);
        this.i.setVisibility(8);
        try {
            floatValue = Float.valueOf(str).floatValue();
        } catch (Exception e) {
            e.printStackTrace();
            floatValue = 0.0f;
        }
        if (floatValue == 0.0f) {
            this.e.setVisibility(8);
            this.f.setVisibility(8);
        } else {
            this.e.setVisibility(0);
            this.f.setVisibility(0);
            this.e.setRating(floatValue);
            this.f.setText(str + "分");
        }
        if (str2 == null) {
            this.g.setVisibility(8);
            return;
        }
        this.g.setVisibility(0);
        this.g.setText(str2);
    }

    private void b(String str, String str2) {
        this.h.setVisibility(8);
        this.i.setVisibility(0);
        this.j.setText(str);
        if (str2 == null) {
            this.k.setVisibility(8);
            return;
        }
        this.k.setVisibility(0);
        this.k.setText(str2);
    }
}
