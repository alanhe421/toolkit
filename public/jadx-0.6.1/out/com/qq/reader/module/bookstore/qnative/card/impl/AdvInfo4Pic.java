package com.qq.reader.module.bookstore.qnative.card.impl;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.module.bookstore.qnative.c.a;
import com.qq.reader.module.bookstore.qnative.item.b;
import com.tencent.feedback.proguard.R;

public class AdvInfo4Pic extends LinearLayout {
    private ImageView a;
    private ImageView b;
    private View c;
    private View d;

    public AdvInfo4Pic(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.localstore_card_advinfo4pic, this, true);
        a();
    }

    private void a() {
        this.a = (ImageView) findViewById(R.id.advinfo_img_0);
        this.b = (ImageView) findViewById(R.id.advinfo_img_1);
        this.c = findViewById(R.id.advinfo_layout_img_0);
        this.d = findViewById(R.id.advinfo_layout_img_1);
    }

    public void setAdv(final b bVar, final b bVar2, int i, final a aVar) {
        this.c.getLayoutParams().height = i;
        c.a(getContext()).a(bVar.f(), this.a, com.qq.reader.common.imageloader.a.a().j());
        this.c.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ AdvInfo4Pic c;

            public void onClick(View view) {
                bVar.a(aVar);
            }
        });
        this.d.getLayoutParams().height = i;
        c.a(getContext()).a(bVar2.f(), this.b, com.qq.reader.common.imageloader.a.a().j());
        this.d.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ AdvInfo4Pic c;

            public void onClick(View view) {
                bVar2.a(aVar);
            }
        });
    }
}
