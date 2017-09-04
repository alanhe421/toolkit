package com.qq.reader.module.game.activity;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.os.Process;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewStub;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.bumptech.glide.request.b.j;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.login.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.o;
import com.qq.reader.common.utils.t;
import com.qq.reader.common.widget.SwipeRefreshLayout;
import com.qq.reader.module.bookstore.qnative.a.f;
import com.qq.reader.module.bookstore.qnative.activity.NativeBookStoreConfigBaseActivity;
import com.qq.reader.module.bookstore.qnative.e;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.model.TitlerControlModel;
import com.qq.reader.module.game.card.view.b;
import com.qq.reader.module.game.data.GameTopBannerData;
import com.qq.reader.module.game.data.d;
import com.qq.reader.module.game.loader.GameBannerTask.a;
import com.qq.reader.view.BaseDialog;
import com.qq.reader.view.r;
import com.qq.reader.widget.titler.StateChangeTitler;
import com.tencent.android.tpush.common.Constants;
import com.tencent.feedback.proguard.R;
import com.tencent.qalsdk.im_open.http;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NativeGameCenterMainActivity extends NativeBookStoreConfigBaseActivity implements OnClickListener, a {
    final Intent a = new Intent();
    private BaseDialog b = null;
    private Bundle c;
    private View d;
    private b n;
    private StateChangeTitler o;
    private ViewGroup p;
    private TitlerControlModel q;
    private ImageView r;
    private ImageView s;
    private TextView t;
    private long u;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.nativestore_game_center_main_layout);
        this.c = getIntent().getExtras();
        a();
        b();
        g();
        com.qq.reader.module.game.a.b().a((a) this);
        com.qq.reader.module.game.a.b().a(false);
        i.a("event_A219", null, ReaderApplication.getApplicationImp());
        this.a.setAction("com.qq.reader.common.web.service.GameAidlService");
        this.a.setPackage(getPackageName());
        startService(this.a);
        f();
    }

    private void f() {
        Intent intent = new Intent();
        intent.setAction("com.qq.reader.module.game.activity.H5GameProcessService");
        intent.setPackage(getPackageName());
        startService(intent);
    }

    private void g() {
        try {
            this.j = e.a().a(this.c, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (this.j != null) {
            if (this.h == null) {
                this.h = new f(this);
            }
            t();
            this.h.a(this.j);
            this.g.setAdapter(this.h);
            a(true, false);
        }
    }

    protected void a() {
        i();
        r();
        super.a();
    }

    private void i() {
        this.q = new TitlerControlModel();
        this.q.withTitle = true;
        this.q.showDuration = http.Internal_Server_Error;
        this.q.hideDuration = http.Internal_Server_Error;
        this.q.mode = TitlerControlModel.POSITION_Y_MODE;
        this.q.startPosition = 0;
        this.g = (ListView) findViewById(R.id.game_main_list);
        this.p = (ViewGroup) findViewById(R.id.data_container);
        this.o = (StateChangeTitler) findViewById(R.id.titler);
        this.t = (TextView) findViewById(R.id.profile_header_title);
        this.t.setText(R.string.game_center_title);
        this.o.post(new Runnable(this) {
            final /* synthetic */ NativeGameCenterMainActivity a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.q.startY = ao.a(192.5f) - this.a.o.getHeight();
            }
        });
        this.g.setOnScrollListener(new OnScrollListener(this) {
            final /* synthetic */ NativeGameCenterMainActivity a;

            {
                this.a = r1;
            }

            public void onScrollStateChanged(AbsListView absListView, int i) {
            }

            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                if (this.a.n != null && this.a.o != null) {
                    if (i > 0 || !this.a.n.g()) {
                        this.a.n.d();
                    } else {
                        this.a.n.c();
                    }
                    this.a.o.a(absListView, i, i2, i3);
                }
            }
        });
        this.i = (SwipeRefreshLayout) findViewById(R.id.game_pull_down);
        this.d = findViewById(R.id.profile_header_left_back);
        this.d.setVisibility(0);
        this.d.setOnClickListener(this);
        ViewStub viewStub = (ViewStub) findViewById(R.id.right_icon_stub);
        if (this.r == null) {
            View inflate = viewStub.inflate();
            this.r = (ImageView) inflate.findViewById(R.id.user_right_icon);
            this.r.setVisibility(0);
            this.s = (ImageView) inflate.findViewById(R.id.user_right_icon_cover);
            this.r.setOnClickListener(this);
        }
    }

    private void q() {
        if (c.b()) {
            com.qq.reader.common.imageloader.c.a(getFromActivity()).a(c.c().b(), this.r, com.qq.reader.common.imageloader.a.a().b(), new com.bumptech.glide.request.e<String, com.bumptech.glide.load.resource.a.b>(this) {
                final /* synthetic */ NativeGameCenterMainActivity a;

                {
                    this.a = r1;
                }

                public boolean a(Exception exception, String str, j<com.bumptech.glide.load.resource.a.b> jVar, boolean z) {
                    this.a.s.setVisibility(4);
                    LayoutParams layoutParams = this.a.r.getLayoutParams();
                    layoutParams.height = ao.a(27.0f);
                    layoutParams.width = ao.a(27.0f);
                    this.a.r.setLayoutParams(layoutParams);
                    return false;
                }

                public boolean a(com.bumptech.glide.load.resource.a.b bVar, String str, j<com.bumptech.glide.load.resource.a.b> jVar, boolean z, boolean z2) {
                    this.a.s.setVisibility(0);
                    LayoutParams layoutParams = this.a.r.getLayoutParams();
                    layoutParams.height = ao.a(32.5f);
                    layoutParams.width = ao.a(32.5f);
                    this.a.r.setLayoutParams(layoutParams);
                    return false;
                }
            });
            this.s.setVisibility(0);
            return;
        }
        this.s.setVisibility(4);
    }

    protected boolean handleMessageImp(Message message) {
        switch (message.what) {
            case 65555:
                if (this.isOnResume) {
                    this.b.f_();
                }
                return true;
            case 500000:
                try {
                    if (message.obj != null) {
                        this.j.a((com.qq.reader.module.bookstore.qnative.page.b) message.obj);
                    }
                    if (this.j != null && this.j.p() == 1002) {
                        com.qq.reader.common.monitor.debug.c.e("PAGE", this.j.m().size() + "         00000");
                        if (this.i != null) {
                            this.l = true;
                            this.i.setRefreshing(false);
                        }
                        c();
                        p();
                        j();
                    }
                } catch (Exception e) {
                    com.qq.reader.common.monitor.debug.c.e("NativeGameCenterMainActivity", e.getMessage());
                }
                return true;
            case 500001:
                try {
                    if (message.obj != null) {
                        this.j.a((com.qq.reader.module.bookstore.qnative.page.b) message.obj);
                    }
                    if (this.j != null && this.j.p() == 1002) {
                        com.qq.reader.common.monitor.debug.c.e("PAGE", this.j.m().size() + "         00000");
                        if (this.i != null) {
                            this.l = true;
                            this.i.setRefreshing(false);
                        }
                        c();
                        p();
                        j();
                        if (com.qq.reader.cservice.adv.c.a("103578")) {
                            com.qq.reader.cservice.adv.c.b("103578");
                            a(((com.qq.reader.module.game.b.c) this.j).x());
                        } else if (c.b()) {
                            com.qq.reader.module.game.presenter.a.a().a(new com.qq.reader.module.game.presenter.a.a(this) {
                                final /* synthetic */ NativeGameCenterMainActivity a;

                                {
                                    this.a = r1;
                                }

                                public void a(List<com.qq.reader.module.game.data.b> list) {
                                    if (list != null && list.size() > 0) {
                                        this.a.b = new com.qq.reader.module.game.view.a(this.a);
                                        ((com.qq.reader.module.game.view.a) this.a.b).a(list, com.qq.reader.module.game.presenter.a.a().c(), com.qq.reader.module.game.presenter.a.a().d(), this.a.mHandler);
                                        this.a.b.a(new 1(this));
                                    }
                                }
                            });
                        }
                    }
                } catch (Exception e2) {
                    com.qq.reader.common.monitor.debug.c.e("NativeGameCenterMainActivity", e2.getMessage());
                }
                return true;
            case 500004:
                this.l = false;
                p();
                d();
                return true;
            default:
                return super.handleMessageImp(message);
        }
    }

    public void doFunction(Bundle bundle) {
        super.doFunction(bundle);
        if ("go_h5_game".equals(bundle.getString("function_type"))) {
            setLoginNextTask(com.qq.reader.module.game.a.a(bundle, (Activity) this));
            startLogin();
        }
    }

    protected void onResume() {
        super.onResume();
        e_();
        if (this.n != null) {
            this.n.c();
        }
        q();
    }

    protected void onPause() {
        super.onPause();
        if (this.n != null) {
            this.n.d();
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        com.qq.reader.module.game.a.b().a(null);
        if (!com.qq.reader.module.game.a.b().a()) {
            i.a("event_A220", null, ReaderApplication.getApplicationImp());
        }
        stopService(this.a);
        if (!com.qq.reader.appconfig.b.a) {
            s();
        }
    }

    public Activity getFromActivity() {
        return this;
    }

    private void r() {
    }

    private void a(d dVar) {
        if (dVar != null && !TextUtils.isEmpty(dVar.a())) {
            this.b = new com.qq.reader.view.web.j(this);
            ((com.qq.reader.view.web.j) this.b).a(dVar, this.mHandler);
            this.b.a(new OnCancelListener(this) {
                final /* synthetic */ NativeGameCenterMainActivity a;

                {
                    this.a = r1;
                }

                public void onCancel(DialogInterface dialogInterface) {
                    Map hashMap = new HashMap();
                    hashMap.put(s.ORIGIN, "3");
                    i.a("event_A208", hashMap, ReaderApplication.getApplicationImp());
                }
            });
            this.b.a(new r(this) {
                final /* synthetic */ NativeGameCenterMainActivity a;

                {
                    this.a = r1;
                }

                public t a() {
                    return this.a.b.c();
                }

                public void onDismiss(DialogInterface dialogInterface) {
                    super.onDismiss(dialogInterface);
                    com.qq.reader.cservice.adv.b.b = false;
                }
            });
        }
    }

    private void s() {
        for (RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) getSystemService(Constants.FLAG_ACTIVITY_NAME)).getRunningAppProcesses()) {
            if (runningAppProcessInfo.uid == Process.myUid() && "com.qq.reader:game_process".equals(runningAppProcessInfo.processName)) {
                Process.killProcess(runningAppProcessInfo.pid);
                com.qq.reader.common.monitor.debug.c.e("killGameProcess", "name is " + runningAppProcessInfo.processName + " pid = " + runningAppProcessInfo.pid);
                return;
            }
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.profile_header_left_back:
                finish();
                return;
            case R.id.user_right_icon:
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - this.u > 500) {
                    this.u = currentTimeMillis;
                    i.a("event_A238", null, ReaderApplication.getApplicationImp());
                    if (c.b()) {
                        o.u(this, null);
                    } else {
                        startLogin();
                        this.mLoginNextTask = new com.qq.reader.common.login.a(this) {
                            final /* synthetic */ NativeGameCenterMainActivity a;

                            {
                                this.a = r1;
                            }

                            public void a(int i) {
                                if (i == 1) {
                                    o.u(this.a, null);
                                    this.a.q();
                                }
                            }
                        };
                    }
                    com.qq.reader.module.game.a.b().a(true);
                    return;
                }
                return;
            default:
                return;
        }
    }

    private synchronized void t() {
        if (!(this.h == null || this.g == null || isFinishing())) {
            if (this.n != null) {
                this.n.d();
            }
            if (this.n == null) {
                this.n = new b(this);
            }
            if (this.g.getHeaderViewsCount() > 0) {
                this.g.removeHeaderView(this.n.b());
            }
            this.g.addHeaderView(this.n.b());
            b(null);
        }
    }

    private synchronized void b(List<GameTopBannerData> list) {
        List ch;
        if (list == null) {
            ch = com.qq.reader.appconfig.a.d.ch(this);
        } else {
            List<GameTopBannerData> list2 = list;
        }
        List a = com.qq.reader.module.game.a.a(ch);
        if (list == null && a.size() > 0) {
            b.a((com.qq.reader.cservice.adv.a) a.get(0), 0);
        }
        boolean i = this.n.i();
        com.qq.reader.common.monitor.debug.c.e("ADV", a + "");
        this.n.a(a);
        if (a.size() > 0) {
            this.n.f();
            a(false);
        } else {
            this.n.e();
            a(true);
        }
        if (this.isOnResume && list == null) {
            this.n.c();
        }
        if (!(list == null || i || this.isOnResume)) {
            this.n.d();
        }
    }

    private void a(boolean z) {
        if (z) {
            this.o.post(new Runnable(this) {
                final /* synthetic */ NativeGameCenterMainActivity a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.p.setPadding(0, this.a.o.getHeight(), 0, 0);
                }
            });
            this.o.a();
            this.o.setConTrollerModel(null);
            return;
        }
        this.p.setPadding(0, 0, 0, 0);
        this.o.setConTrollerModel(this.q);
    }

    public void a(List<GameTopBannerData> list) {
        com.qq.reader.common.monitor.debug.c.e("ADV", list == null ? "" : list.toString());
        b((List) list);
    }

    protected void d() {
        if (this.g.getVisibility() != 0 || this.g.getAdapter().getCount() - (this.g.getFooterViewsCount() + this.g.getHeaderViewsCount()) <= 0) {
            this.g.setVisibility(8);
            this.e.setVisibility(8);
            this.f.setVisibility(0);
            a(true);
            return;
        }
        if (this.i != null) {
            this.i.setRefreshing(false);
            this.g.setVisibility(0);
            this.e.setVisibility(8);
        }
        if (this.g.getHeaderViewsCount() <= 0 || this.n == null || !this.n.g()) {
            a(true);
        } else {
            a(false);
        }
    }

    public void h_() {
        super.h_();
        if (this.g != null && this.g.getHeaderViewsCount() > 0 && this.n != null && this.n.g()) {
            a(false);
        }
        if (this.j != null && this.j.m().size() == 0) {
            d();
        }
    }
}
