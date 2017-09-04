package com.qq.reader.module.comic.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.o;
import com.qq.reader.common.widget.SwipeRefreshLayout;
import com.qq.reader.module.bookstore.qnative.a.f;
import com.qq.reader.module.bookstore.qnative.activity.NativeBookStoreConfigBaseActivity;
import com.qq.reader.module.bookstore.qnative.d;
import com.qq.reader.module.bookstore.qnative.e;
import com.qq.reader.module.bookstore.qnative.model.TitlerControlModel;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.comic.card.a;
import com.qq.reader.module.comic.views.ComicListViewFooter;
import com.qq.reader.view.pullupdownlist.XListView;
import com.qq.reader.view.pullupdownlist.XListView$a;
import com.qq.reader.widget.titler.StateChangeTitler;
import com.tencent.feedback.proguard.R;
import com.tencent.qalsdk.im_open.http;

public abstract class NativeComicStoreBaseActivity extends NativeBookStoreConfigBaseActivity implements OnClickListener {
    private static final String a = NativeComicStoreBaseActivity.class.getSimpleName();
    private Bundle b;
    private TitlerControlModel c;
    private StateChangeTitler d;
    private ViewGroup n;
    private Bundle o;
    private b p;
    private final int q = 1;
    private final int r = 2;
    private int s = 2;
    private a t;
    private final BroadcastReceiver u = new BroadcastReceiver(this) {
        final /* synthetic */ NativeComicStoreBaseActivity a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            if (com.qq.reader.common.c.a.cs.equalsIgnoreCase(intent.getAction())) {
                this.a.mHandler.sendEmptyMessage(8012);
            }
        }
    };

    protected abstract String f();

    protected abstract String g();

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.b = getIntent().getExtras();
        setContentView(R.layout.comic_book_store_main_activity);
        a();
        r();
        b();
        i();
        this.mUseAnimation = false;
    }

    private void i() {
        try {
            this.j = e.a().a(this.b, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (this.j != null) {
            if (this.h == null) {
                this.h = new f(this);
            }
            a(true, false);
        }
        if (ao.o(getApplicationContext())) {
            com.qq.reader.cservice.adv.b.a(getApplicationContext()).a();
        }
    }

    protected void a() {
        h();
        super.a();
    }

    protected void h() {
        this.c = new TitlerControlModel();
        this.c.withTitle = true;
        this.c.showDuration = http.Internal_Server_Error;
        this.c.hideDuration = http.Internal_Server_Error;
        this.c.mode = TitlerControlModel.POSITION_Y_MODE;
        this.c.startPosition = 0;
        this.g = (XListView) findViewById(R.id.game_main_list);
        this.n = (ViewGroup) findViewById(R.id.data_container);
        this.d = (StateChangeTitler) findViewById(R.id.titler);
        ((TextView) findViewById(R.id.profile_header_title)).setText(f());
        this.d.post(new Runnable(this) {
            final /* synthetic */ NativeComicStoreBaseActivity a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.c.startY = ao.a(192.5f) - this.a.d.getHeight();
            }
        });
        this.g.setOnScrollListener(new OnScrollListener(this) {
            final /* synthetic */ NativeComicStoreBaseActivity a;

            {
                this.a = r1;
            }

            public void onScrollStateChanged(AbsListView absListView, int i) {
            }

            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                if (this.a.t != null && this.a.d != null) {
                    if (i > 0 || !this.a.t.g()) {
                        this.a.t.d();
                    } else {
                        this.a.t.c();
                    }
                    this.a.d.a(absListView, i, i2, i3);
                }
            }
        });
        this.i = (SwipeRefreshLayout) findViewById(R.id.game_pull_down);
        View findViewById = findViewById(R.id.profile_header_left_back);
        findViewById.setVisibility(0);
        findViewById.setOnClickListener(this);
        ((XListView) this.g).setPullRefreshEnable(false);
        ((XListView) this.g).setPullLoadEnable(true);
        ((XListView) this.g).setShowFooter(true);
        ((XListView) this.g).setXListFooter(new ComicListViewFooter(this));
        ((XListView) this.g).setXListViewListener(new XListView$a(this) {
            final /* synthetic */ NativeComicStoreBaseActivity a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.mHandler.sendEmptyMessage(500005);
            }
        });
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.profile_header_left_back:
                finish();
                return;
            default:
                return;
        }
    }

    private void a(boolean z) {
        if (z) {
            this.d.post(new Runnable(this) {
                final /* synthetic */ NativeComicStoreBaseActivity a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.n.setPadding(0, this.a.d.getHeight(), 0, 0);
                }
            });
            this.d.a();
            this.d.setConTrollerModel(null);
            return;
        }
        this.n.setPadding(0, 0, 0, 0);
        this.d.setConTrollerModel(this.c);
    }

    protected boolean handleMessageImp(Message message) {
        switch (message.what) {
            case 8012:
                if (this.t != null) {
                    this.t.a(com.qq.reader.cservice.adv.b.a(getApplicationContext()).b(g()));
                }
                return true;
            case 500000:
            case 500001:
                try {
                    if (message.obj != null) {
                        b bVar = (b) message.obj;
                        if (bVar.j().getString("URL_BUILD_PERE_SIGNAL", "").equals("nextpage")) {
                            if (this.p != null) {
                                this.p.a(bVar);
                            }
                        } else if (this.j != null) {
                            this.j.a(bVar);
                        }
                    }
                    if (!(this.j == null || this.j.p() != 1002 || this.i == null)) {
                        this.l = true;
                        this.i.setRefreshing(false);
                    }
                    c();
                    p();
                    j();
                } catch (Exception e) {
                    e.printStackTrace();
                    com.qq.reader.common.monitor.f.b(a, e.getMessage());
                }
                return true;
            case 500004:
                this.l = false;
                p();
                d();
                return true;
            case 500005:
                q();
                break;
        }
        return super.handleMessageImp(message);
    }

    private void q() {
        if (this.s != 2 || this.j == null || this.p != null) {
            return;
        }
        if (this.j.s()) {
            if (this.o == null) {
                this.o = new Bundle(this.b);
            }
            this.o.putLong("KEY_PAGEINDEX", this.j.r());
            this.o.putString("URL_BUILD_PERE_SIGNAL", "nextpage");
            if (this.o.containsKey("URL_DATA_QURL")) {
                this.o.putString("URL_DATA_QURL", "");
            }
            this.s = 1;
            this.p = e.a().a(this.o, this);
            this.p.a(1001);
            d.b().a(getApplicationContext(), this.p, this.mHandler, false);
        } else if (this.g != null) {
            ((XListView) this.g).c();
        }
    }

    public void doFunction(Bundle bundle) {
        super.doFunction(bundle);
    }

    private void r() {
        if (this.t == null) {
            this.t = new a(this);
        } else if (this.g.getHeaderViewsCount() > 0) {
            this.g.removeHeaderView(this.t.b());
        }
        this.g.addHeaderView(this.t.b());
        this.t.a(com.qq.reader.cservice.adv.b.a(getApplicationContext()).b(g()));
    }

    protected void d() {
        if (this.g.getVisibility() != 0 || this.g.getAdapter().getCount() - (this.g.getFooterViewsCount() + this.g.getHeaderViewsCount()) <= 0) {
            this.g.setVisibility(8);
            this.e.setVisibility(8);
            this.f.setVisibility(0);
            View findViewById = this.d.findViewById(R.id.common_titler);
            if (findViewById != null && (findViewById instanceof RelativeLayout) && findViewById.getTop() == 0) {
                ((RelativeLayout) findViewById).setGravity(80);
                this.d.requestLayout();
            }
            a(true);
            return;
        }
        if (this.i != null) {
            this.i.setRefreshing(false);
            this.g.setVisibility(0);
            this.e.setVisibility(8);
        }
        if (this.g.getHeaderViewsCount() <= 0 || this.t == null || !this.t.g()) {
            a(true);
        } else {
            a(false);
        }
        if (this.p != null) {
            this.p = null;
            this.s = 2;
            if (this.g != null) {
                ((XListView) this.g).g();
            }
        }
    }

    public void h_() {
        super.h_();
        if (this.g != null && this.g.getHeaderViewsCount() > 0 && this.t != null && this.t.g()) {
            a(false);
        }
        if (this.j != null && this.j.m().size() == 0) {
            d();
        }
        if (this.j != null && !this.j.s()) {
            ((XListView) this.g).c();
        }
    }

    protected void onResume() {
        super.onResume();
        registerReceiver(this.u, new IntentFilter(com.qq.reader.common.c.a.cs));
        if (this.t != null) {
            this.t.c();
        }
    }

    protected void onPause() {
        super.onPause();
        if (this.t != null) {
            this.t.d();
        }
        unregisterReceiver(this.u);
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    public Activity getFromActivity() {
        return this;
    }

    public void j() {
        if (this.p != null && this.p.p() == 1002) {
            if (this.p.m().size() > 0) {
                this.j.addMore(this.p);
            }
            this.p = null;
            this.s = 2;
        }
        super.j();
    }

    public void p() {
        super.p();
        ((XListView) this.g).e();
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

    public void finish() {
        if (this.b.getBoolean("need_start_main", false)) {
            o.b(this, null);
        }
        super.finish();
    }
}
