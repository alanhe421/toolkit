package com.qq.reader.module.bookstore.qnative.card.impl;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.qq.reader.common.imageloader.a;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.utils.j;
import com.qq.reader.module.bookstore.qnative.item.g;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.tencent.feedback.proguard.R;

public class BookInfo4Chat extends LinearLayout {
    private ImageView a;
    private TextView b;
    private TextView c;
    private TextView d;
    private TextView e;

    public BookInfo4Chat(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.localstore_card_bookinfo_forchat, this, true);
        a();
    }

    private void a() {
        this.a = (ImageView) findViewById(R.id.bookinfo_cover);
        this.b = (TextView) findViewById(R.id.bookinfo_name);
        this.c = (TextView) findViewById(R.id.bookinfo_typeandauthor);
        this.d = (TextView) findViewById(R.id.bookinfo_popularity);
        this.e = (TextView) findViewById(R.id.bookinfo_introduction);
    }

    public void setBookInfo(g gVar, String str) {
        this.b.setText(gVar.n());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(gVar.r()).append(" | ").append(gVar.q());
        this.c.setText(stringBuilder.toString());
        String a;
        if (str.equals("updatecol")) {
            CharSequence charSequence;
            a = j.a(gVar.C());
            if (a == null) {
                charSequence = "";
            } else {
                charSequence = a + "更新";
            }
            this.d.setText(charSequence);
        } else {
            a = gVar.C();
            try {
                a = s.countTransform(Long.valueOf(a).longValue());
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.d.setText(a + gVar.B());
        }
        this.e.setText(gVar.s());
        c.a(getContext()).a(gVar.f(), this.a, a.a().j());
    }
}
