package com.qq.reader.module.bookstore.qnative.activity;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import com.qq.reader.activity.ReaderBaseActivity;
import com.qq.reader.common.monitor.j;
import com.qq.reader.common.widget.SwipeRefreshLayout;
import com.qq.reader.module.bookstore.qnative.a.f;
import com.qq.reader.module.bookstore.qnative.c.a;
import com.qq.reader.module.bookstore.qnative.d;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import com.tencent.upload.log.trace.TracerConfig;

public abstract class NativeBookStoreConfigBaseActivity extends ReaderBaseActivity implements a {
    protected View e = null;
    protected View f = null;
    protected ListView g;
    protected f h;
    protected SwipeRefreshLayout i;
    protected b j = null;
    protected long k = -1;
    protected boolean l = false;
    protected boolean m = false;

    protected void a() {
        this.e = findViewById(R.id.loading_layout);
        this.f = findViewById(R.id.loading_failed_layout);
        if (this.f != null) {
            this.f.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ NativeBookStoreConfigBaseActivity a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.m();
                }
            });
        }
        if (this.i != null) {
            this.i.setOnRefreshListener(new SwipeRefreshLayout.b(this) {
                final /* synthetic */ NativeBookStoreConfigBaseActivity a;

                {
                    this.a = r1;
                }

                public void a() {
                    this.a.e_();
                }
            });
        }
    }

    public void j() {
        if (this.h != null) {
            this.h.a(this.j);
            if (this.h.b() || this.g.getAdapter() == null) {
                this.g.setAdapter(this.h);
            } else {
                this.h.notifyDataSetChanged();
            }
            h_();
        }
    }

    protected void k() {
        if (this.h != null) {
            this.h.a();
            this.h.notifyDataSetChanged();
        }
    }

    protected void b() {
        l();
        this.g.setVisibility(4);
        this.e.setVisibility(0);
    }

    protected void c() {
        l();
        this.g.setVisibility(0);
        this.e.setVisibility(8);
    }

    protected void d() {
        if (this.g.getVisibility() == 0 || this.g.getAdapter().getCount() - this.g.getFooterViewsCount() > 0) {
            if (this.i != null) {
                this.i.setRefreshing(false);
            }
            this.g.setVisibility(0);
            this.e.setVisibility(8);
            return;
        }
        this.e.setVisibility(8);
        this.f.setVisibility(0);
    }

    protected void l() {
        this.f.setVisibility(8);
    }

    protected boolean handleMessageImp(Message message) {
        switch (message.what) {
            case 500000:
            case 500001:
                try {
                    if (message.obj != null) {
                        this.j.a((b) message.obj);
                    }
                    c();
                    if (this.i != null) {
                        this.l = true;
                        this.i.setRefreshing(false);
                    }
                    p();
                    j();
                } catch (Exception e) {
                    com.qq.reader.common.monitor.f.a("NativeBookStoreConfigBaseActivity", e.getMessage());
                }
                return true;
            case 500003:
                p();
                j();
                return true;
            case 500004:
                this.l = false;
                p();
                d();
                return true;
            case 10000508:
                if (this.h != null) {
                    this.h.notifyDataSetChanged();
                }
                return true;
            default:
                return super.handleMessageImp(message);
        }
    }

    protected void a(boolean z, boolean z2) {
        boolean a = d.b().a(getContext(), this.j, this.mHandler, z);
        if (!z2) {
            if (a) {
                if (!this.m) {
                    j();
                    this.m = false;
                }
                c();
                return;
            }
            this.k = System.currentTimeMillis();
            k();
            b();
        }
    }

    public void h_() {
    }

    public void m() {
        this.j.a(1000);
        a(true, false);
    }

    public void n() {
        for (com.qq.reader.module.bookstore.qnative.card.a expiredTime : this.j.m()) {
            expiredTime.setExpiredTime(System.currentTimeMillis() - TracerConfig.LOG_FLUSH_DURATION);
        }
        this.j.a(1000);
        a(true, false);
    }

    public void o() {
        this.j.a(1001);
        for (com.qq.reader.module.bookstore.qnative.card.a invalidData : this.j.m()) {
            invalidData.setInvalidData();
        }
        a(false, true);
    }

    public void e_() {
        this.m = true;
        this.j.a(1001);
        a(false, true);
    }

    public void p() {
        this.m = false;
        if (this.i != null) {
            this.i.setRefreshing(false);
        }
        j.a(53, 2);
    }

    public void doFunction(Bundle bundle) {
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.j != null) {
            this.j.w();
        }
    }
}
