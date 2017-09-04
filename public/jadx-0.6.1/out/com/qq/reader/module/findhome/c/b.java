package com.qq.reader.module.findhome.c;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import com.qq.reader.common.imageloader.a;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.utils.ao;

/* compiled from: FindHomeExpandAuthorSayVH */
public class b extends com.qq.reader.module.findhome.base.b<com.qq.reader.module.findhome.a.b> {
    public b(View view) {
        super(view);
    }

    public void a(final com.qq.reader.module.findhome.a.b bVar) {
        LayoutParams layoutParams = this.l.getLayoutParams();
        if (bVar.e()) {
            layoutParams.width = ao.a(200.0f);
            if (this.p != null) {
                this.p.setVisibility(8);
            }
        } else {
            layoutParams.width = ao.a(93.0f);
            if (this.p != null) {
                this.p.setVisibility(0);
            }
        }
        this.l.setLayoutParams(layoutParams);
        c.e("FindHome Glide", bVar.a());
        com.qq.reader.common.imageloader.c.a(this.l.getContext()).a(bVar.a(), this.o, a.a().w());
        this.m.setText(bVar.b());
        this.n.setText(bVar.c());
        this.l.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ b b;

            public void onClick(View view) {
                try {
                    com.qq.reader.qurl.c.a((Activity) this.b.l.getContext(), bVar.d(), null);
                } catch (Exception e) {
                    c.e("FindHomeExpandBaseVH", e.getMessage());
                }
                this.b.b(bVar.f(), bVar.g(), bVar.h());
            }
        });
        a(bVar.f(), bVar.g(), bVar.h());
    }
}
