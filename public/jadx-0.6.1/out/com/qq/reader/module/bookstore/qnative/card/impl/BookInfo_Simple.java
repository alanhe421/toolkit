package com.qq.reader.module.bookstore.qnative.card.impl;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.qq.reader.common.c.a;
import com.qq.reader.module.bookstore.qnative.item.g;
import com.tencent.feedback.proguard.R;

public class BookInfo_Simple extends LinearLayout {
    private TextView a;
    private TextView b;
    private TextView c;
    private ImageView d;

    public BookInfo_Simple(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.localstore_card_bookinfo_simple, this, true);
        a();
    }

    private void a() {
        this.a = (TextView) findViewById(R.id.bookinfo_name);
        this.c = (TextView) findViewById(R.id.bookinfo_introduction);
        this.b = (TextView) findViewById(R.id.bookinfo_typeandauthor);
        this.d = (ImageView) findViewById(R.id.bookinfo_recommended);
    }

    public void setBookInfo(g gVar) {
        String q = gVar.q();
        CharSequence n = gVar.n();
        float dimensionPixelOffset = (float) (a.bU - getContext().getResources().getDimensionPixelOffset(R.dimen.common_dp_20));
        float a = a(n, q, gVar.r());
        if (a > dimensionPixelOffset) {
            q = a(this.b, q, a - dimensionPixelOffset, 6);
            a = a(n, q, gVar.r());
            if (a > dimensionPixelOffset) {
                n = a(this.a, n, a - dimensionPixelOffset, 2);
            }
        }
        this.a.setText(n);
        this.b.setText(gVar.r() + " | " + q);
        this.c.setText(gVar.s());
        if (gVar.v() == 1) {
            this.d.setVisibility(0);
        } else {
            this.d.setVisibility(8);
        }
    }

    private float a(String str, String str2, String str3) {
        return (a(this.a, str) + ((float) getContext().getResources().getDimensionPixelOffset(R.dimen.common_dp_31))) + a(this.b, str3 + " | " + str2);
    }

    private String a(TextView textView, String str, float f, int i) {
        float f2 = 1080.0f;
        float a = a(textView, str);
        while (str.length() >= i && a - r0 < f) {
            str = str.substring(0, str.length() - 2) + "…";
            f2 = a(textView, str);
        }
        return str;
    }

    public static float a(TextView textView, String str) {
        LayoutParams layoutParams = (LayoutParams) textView.getLayoutParams();
        return ((float) layoutParams.rightMargin) + (textView.getPaint().measureText(str) + ((float) layoutParams.leftMargin));
    }
}
