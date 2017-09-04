package com.qq.reader.module.bookstore.qnative.card.impl;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.qq.reader.common.utils.j;
import com.qq.reader.module.bookstore.qnative.item.g;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.tencent.feedback.proguard.R;

public class BookInfo4Chat_Simple extends LinearLayout {
    private TextView a;
    private TextView b;
    private TextView c;

    public BookInfo4Chat_Simple(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.localstore_card_bookinfo_forchat_simple, this, true);
        a();
    }

    private void a() {
        this.b = (TextView) findViewById(R.id.bookinfo_name);
        this.a = (TextView) findViewById(R.id.bookinfo_no);
        this.c = (TextView) findViewById(R.id.bookinfo_popularity);
    }

    public void setBookInfo(int i, String str, g gVar, String str2) {
        this.a.setBackgroundResource(i);
        this.a.setText(str);
        this.b.setText(gVar.n());
        String a;
        if (str2.equals("updatecol")) {
            CharSequence charSequence;
            a = j.a(gVar.C());
            if (a == null) {
                charSequence = "";
            } else {
                charSequence = a + "更新";
            }
            this.c.setText(charSequence);
            return;
        }
        a = gVar.C();
        try {
            a = s.countTransform(Long.valueOf(a).longValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.c.setText(a + gVar.B());
    }
}
