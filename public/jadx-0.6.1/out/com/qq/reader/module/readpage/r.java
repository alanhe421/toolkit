package com.qq.reader.module.readpage;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import com.bumptech.glide.request.b.j;
import com.bumptech.glide.request.b.k;
import com.bumptech.glide.request.e;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ao;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.rookie.a.b;
import com.qq.reader.module.rookie.presenter.a;
import com.tencent.feedback.proguard.R;
import com.tencent.util.WeakReferenceHandler;
import java.util.HashMap;
import java.util.Map;

/* compiled from: ReaderPageGiftHandler */
public class r {
    Activity a;
    WeakReferenceHandler b;
    View c = null;
    private final String d = "ReaderPageGiftHandler";
    private long e = -1;
    private boolean f = false;

    public r(Activity activity, WeakReferenceHandler weakReferenceHandler) {
        this.a = activity;
        this.b = weakReferenceHandler;
    }

    public void a(long j, boolean z) {
        c(j, z);
        if (!this.a.isFinishing()) {
            b b = b(j, z);
            if (b == null || ao.s(b.c) || ao.s(b.d)) {
                a();
                return;
            }
            if (this.c == null) {
                this.c = a(b);
                ((ViewGroup) this.a.getWindow().getDecorView()).addView(this.c, new LayoutParams(-1, -1));
                this.c.setVisibility(0);
            } else {
                this.c.setVisibility(0);
            }
            try {
                Map hashMap = new HashMap();
                hashMap.put(s.ORIGIN, String.valueOf(b.a));
                i.a("event_A268", hashMap, ReaderApplication.getApplicationImp());
            } catch (Exception e) {
                c.e("ReaderPageGiftHandler", e.getLocalizedMessage());
            }
        }
    }

    private b b(long j, boolean z) {
        return a.a().a("p3", j, z);
    }

    public void a() {
        c();
        if (!this.a.isFinishing() && this.c != null) {
            this.c.setVisibility(8);
        }
    }

    public void b() {
        c();
    }

    private void c(long j, boolean z) {
        this.e = j;
        this.f = z;
    }

    private void c() {
        this.e = -1;
        this.f = false;
    }

    private View a(final b bVar) {
        View inflate = LayoutInflater.from(this.a).inflate(R.layout.gift_peck_layout, null, false);
        com.qq.reader.common.imageloader.c.a(this.a).a(bVar.c, (ImageView) inflate.findViewById(R.id.gift_peck_image), new e<String, com.bumptech.glide.load.resource.a.b>(this) {
            final /* synthetic */ r b;

            public boolean a(Exception exception, String str, j<com.bumptech.glide.load.resource.a.b> jVar, boolean z) {
                return false;
            }

            public boolean a(com.bumptech.glide.load.resource.a.b bVar, String str, j<com.bumptech.glide.load.resource.a.b> jVar, boolean z, boolean z2) {
                if (jVar instanceof k) {
                    View b_ = ((k) jVar).b_();
                    if (b_ != null) {
                        b_.setOnClickListener(new OnClickListener(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public void onClick(View view) {
                                try {
                                    a.a().a(this.a.b.a, bVar);
                                    Map hashMap = new HashMap();
                                    hashMap.put(s.ORIGIN, String.valueOf(bVar.a));
                                    i.a("event_A269", hashMap, ReaderApplication.getApplicationImp());
                                } catch (Exception e) {
                                    c.e("ReaderPageGiftHandler", e.getMessage());
                                }
                            }
                        });
                    }
                }
                return false;
            }
        });
        return inflate;
    }
}
