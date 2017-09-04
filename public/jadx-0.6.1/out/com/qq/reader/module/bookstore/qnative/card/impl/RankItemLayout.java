package com.qq.reader.module.bookstore.qnative.card.impl;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.qq.reader.common.imageloader.a;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.utils.ao;
import com.qq.reader.module.bookstore.qnative.item.ae;
import com.tencent.feedback.proguard.R;

public class RankItemLayout extends LinearLayout {
    ImageView a;
    TextView b;
    TextView c;
    TextView d;
    TextView e;

    public RankItemLayout(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.localstore_card_bankcard_item, this, true);
        a();
    }

    public RankItemLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.localstore_card_bankcard_item, this, true);
        a();
    }

    private void a() {
        this.a = (ImageView) findViewById(R.id.bank_icon);
        this.d = (TextView) findViewById(R.id.item_name);
        this.b = (TextView) findViewById(R.id.book1_title);
        this.c = (TextView) findViewById(R.id.book2_title);
        this.e = (TextView) findViewById(R.id.book3_title);
    }

    public void setItemData(ae aeVar) {
        int charAt = aeVar.d().charAt(0) - 65;
        if (charAt >= 0 && charAt <= 7) {
            c.a(getContext()).a(ao.g(aeVar.f()), this.a, a.a().j());
        }
        if (!TextUtils.isEmpty(aeVar.a())) {
            String[] split = aeVar.c().split(",");
            if (split.length > 0) {
                this.b.setText(split[0]);
            }
            if (split.length > 1) {
                this.c.setText(split[1]);
            }
            if (split.length > 2) {
                this.e.setText(split[2]);
            }
        }
        this.d.setText(aeVar.a());
    }
}
