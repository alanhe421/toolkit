package com.qq.reader.module.bookstore.qnative.card.impl;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.qq.reader.common.imageloader.a;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.module.bookstore.qnative.item.b;
import com.tencent.feedback.proguard.R;

public class AdvInfo extends RelativeLayout {
    private ImageView a;
    private TextView b;
    private TextView c;

    public AdvInfo(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.localstore_card_advinfo, this, true);
        a();
    }

    private void a() {
        this.a = (ImageView) findViewById(R.id.advinfo_cover);
        this.b = (TextView) findViewById(R.id.advinfo_title);
        this.c = (TextView) findViewById(R.id.advinfo_introduction);
    }

    public void setAdvItem(b bVar) {
        this.b.setText(bVar.d());
        this.c.setText(bVar.c());
        c.a(getContext()).a(bVar.f(), this.a, a.a().q());
    }

    public void setAdv(String str, String str2) {
        this.b.setText(str);
        this.c.setText(str2);
    }
}
