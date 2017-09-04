package com.qq.reader.module.findhome.base;

import android.app.Activity;
import android.support.v7.widget.RecyclerView.s;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.imageloader.a;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.utils.ao;
import com.tencent.feedback.proguard.R;
import java.util.HashMap;
import java.util.Map;

/* compiled from: FindHomeExpandBaseVH */
public class b<T extends a> extends s {
    protected View l;
    protected TextView m;
    protected TextView n;
    protected ImageView o;
    protected View p;

    public b(View view) {
        super(view);
        this.l = view;
        this.o = (ImageView) view.findViewById(R.id.findhome_img);
        this.m = (TextView) view.findViewById(R.id.findhome_title);
        this.n = (TextView) view.findViewById(R.id.findhome_desc);
        this.p = view.findViewById(R.id.findhome_mask);
    }

    public void a(final T t) {
        LayoutParams layoutParams = this.l.getLayoutParams();
        if (t.e()) {
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
        c.e("FindHome Glide", t.a());
        com.qq.reader.common.imageloader.c.a(this.l.getContext()).a(t.a(), this.o, a.a().w());
        this.m.setText(t.b());
        this.n.setText(t.c());
        this.l.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ b b;

            public void onClick(View view) {
                try {
                    com.qq.reader.qurl.c.a((Activity) this.b.l.getContext(), t.d(), null);
                } catch (Exception e) {
                    c.e("FindHomeExpandBaseVH", e.getMessage());
                }
                this.b.b(t.f(), t.g(), t.h());
            }
        });
        a(t.f(), t.g(), t.h());
    }

    protected void a(int i, boolean z, int i2) {
        g.a().a(new FindHomeExpandBaseVH$2(this, i, z, i2));
    }

    protected void b(int i, boolean z, int i2) {
        Map hashMap;
        switch (i) {
            case 0:
                if (z) {
                    i.a("event_B355", null, ReaderApplication.getApplicationImp());
                    return;
                }
                hashMap = new HashMap();
                hashMap.put("pos", String.valueOf(i2));
                i.a("event_B362", hashMap, ReaderApplication.getApplicationImp());
                return;
            case 1:
                if (z) {
                    i.a("event_B352", null, ReaderApplication.getApplicationImp());
                    return;
                }
                hashMap = new HashMap();
                hashMap.put("pos", String.valueOf(i2));
                i.a("event_B360", hashMap, ReaderApplication.getApplicationImp());
                return;
            case 2:
                if (z) {
                    i.a("event_B353", null, ReaderApplication.getApplicationImp());
                    return;
                }
                hashMap = new HashMap();
                hashMap.put("pos", String.valueOf(i2));
                i.a("event_B361", hashMap, ReaderApplication.getApplicationImp());
                return;
            case 3:
                hashMap = new HashMap();
                hashMap.put("pos", String.valueOf(i2));
                i.a("event_B354", hashMap, ReaderApplication.getApplicationImp());
                return;
            case 4:
                if (z) {
                    i.a("event_B351", null, ReaderApplication.getApplicationImp());
                    return;
                }
                hashMap = new HashMap();
                hashMap.put("pos", String.valueOf(i2));
                i.a("event_B359", hashMap, ReaderApplication.getApplicationImp());
                return;
            default:
                return;
        }
    }
}
