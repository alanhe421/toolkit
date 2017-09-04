package com.qq.reader.module.redpacket.singlebookpacket;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.content.d;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.activity.ReaderBaseActivity;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.conn.socket.g;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.CustomArrayList;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.o;
import com.qq.reader.common.widget.SwipeRefreshLayout;
import com.qq.reader.common.widget.SwipeRefreshLayout.b;
import com.qq.reader.module.bookstore.qnative.c.a;
import com.qq.reader.module.redpacket.model.RedPacket;
import com.qq.reader.module.redpacket.singlebookpacket.card.RedPacketSingleBookCard;
import com.qq.reader.module.redpacket.singlebookpacket.card.RedPacketSingleBookDividerCard;
import com.qq.reader.module.redpacket.singlebookpacket.card.RedPacketSingleBookListFooter;
import com.qq.reader.module.redpacket.singlebookpacket.card.SingleBookInValidCard;
import com.qq.reader.module.redpacket.singlebookpacket.card.SingleBookValidCard;
import com.qq.reader.view.BaseDialog;
import com.qq.reader.view.EmptyView;
import com.qq.reader.view.aj;
import com.qq.reader.view.pullupdownlist.XListView;
import com.qq.reader.view.pullupdownlist.XListView$a;
import com.qq.reader.view.pullupdownlist.XListViewFooter;
import com.tencent.feedback.proguard.R;
import com.tencent.util.WeakReferenceHandler;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class RedPacketSingleBookActivity extends ReaderBaseActivity implements a {
    private static final String f = RedPacketSingleBookActivity.class.getSimpleName();
    protected View a = null;
    protected EmptyView b = null;
    protected SwipeRefreshLayout c;
    protected RelativeLayout d;
    protected XListView e = null;
    private TextView g;
    private Button h;
    private b i = null;
    private TextView j;
    private c k;
    private d l = null;
    private a m;
    private long n = -1;
    private long o = -1;
    private long p = -1;
    private boolean q;
    private BaseDialog r;
    private String s;
    private ServiceConnection t;
    private RedPacketSingleBookDividerCard u = new RedPacketSingleBookDividerCard(null);
    private BroadcastReceiver v = new BroadcastReceiver(this) {
        final /* synthetic */ RedPacketSingleBookActivity a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("com.qq.reader.redpacket_arrived") && this.a.m != null) {
                ArrayList parcelableArrayList = intent.getExtras().getParcelableArrayList("redpacket");
                if (parcelableArrayList != null) {
                    ArrayList arrayList = new ArrayList();
                    Iterator it = parcelableArrayList.iterator();
                    while (it.hasNext()) {
                        RedPacket redPacket = (RedPacket) it.next();
                        redPacket.a(2);
                        if (redPacket.e() == this.a.n) {
                            arrayList.add(redPacket);
                        }
                    }
                    this.a.a(arrayList);
                }
            }
        }
    };
    private BroadcastReceiver w = new BroadcastReceiver(this) {
        final /* synthetic */ RedPacketSingleBookActivity a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("com.qq.reader.push.sync")) {
                this.a.a(1);
            }
        }
    };

    private void a(ArrayList<RedPacket> arrayList) {
        int i = 0;
        this.m.a((List) arrayList, (a) this);
        if (this.m != null) {
            if (this.e.getChildCount() > 0 && (this.e.getFirstVisiblePosition() > 0 || this.e.getChildAt(0).getTop() < this.e.getPaddingTop())) {
                i = 1;
            }
            if (!this.m.b()) {
                if (i != 0) {
                    j();
                } else {
                    this.m.a((a) this);
                }
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.redpacket_singlebook_layout);
        a();
        i();
        k();
        this.mHandler = new WeakReferenceHandler(this);
        a(2);
    }

    protected void onResume() {
        super.onResume();
        i.a("event_D213", null, this);
    }

    private void i() {
        if (this.t == null) {
            this.t = new ServiceConnection(this) {
                final /* synthetic */ RedPacketSingleBookActivity a;

                {
                    this.a = r1;
                }

                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                }

                public void onServiceDisconnected(ComponentName componentName) {
                }
            };
        }
        g.a(this, this.t);
    }

    private void j() {
        if (this.m.a() > 0) {
            this.j.setText("有" + this.m.a() + "个新红包");
            this.j.setVisibility(0);
            return;
        }
        this.j.setVisibility(8);
    }

    private void a(int i) {
        int c;
        if (i == 2) {
            e();
        }
        this.k = new c(i, this);
        this.k.a(this.n);
        this.k.c(this.o);
        this.k.b(this.p);
        if (i == 0 && this.l.d().size() > 0) {
            com.qq.reader.module.bookstore.qnative.card.a aVar = (com.qq.reader.module.bookstore.qnative.card.a) this.l.d().get(this.l.d().size() - 1);
            if (aVar instanceof RedPacketSingleBookCard) {
                c = ((RedPacketSingleBookCard) aVar).getItem().c();
                this.k.a(c);
                if (this.m == null) {
                    this.m = new a(this.mHandler);
                }
                this.m.a(this.k);
            }
        }
        c = 2;
        this.k.a(c);
        if (this.m == null) {
            this.m = new a(this.mHandler);
        }
        this.m.a(this.k);
    }

    protected void a() {
        this.g = (TextView) findViewById(R.id.profile_header_title);
        this.h = (Button) findViewById(R.id.profile_header_right_button);
        findViewById(R.id.common_titler).setBackgroundColor(getResources().getColor(R.color.redpacket_square_title_bg));
        this.j = (TextView) findViewById(R.id.red_packet_single_book_new_packet_tip);
        this.j.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ RedPacketSingleBookActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.m.a(this.a);
                this.a.j.setVisibility(8);
                this.a.e.smoothScrollToPosition(0);
            }
        });
        ((ProgressBar) findViewById(R.id.default_progress)).setIndeterminateDrawable(getResources().getDrawable(R.drawable.redpacket_square_progress_loading));
        this.s = getIntent().getStringExtra("bookName");
        this.g.setText(this.s);
        this.h.setVisibility(0);
        this.n = getIntent().getLongExtra("bookId", 0);
        this.o = getIntent().getLongExtra("cbookId", 0);
        findViewById(R.id.profile_header_left_back).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ RedPacketSingleBookActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.finish();
            }
        });
        this.a = findViewById(R.id.loading_layout);
        this.c = (SwipeRefreshLayout) findViewById(R.id.booklist_pull_down_list);
        this.c.setMannuallySetSchemeColor(getResources().getColor(R.color.redpacket_square_pulldown_scheme_color));
        this.c.setOnRefreshListener(new b(this) {
            final /* synthetic */ RedPacketSingleBookActivity a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.d();
            }
        });
        this.b = (EmptyView) findViewById(R.id.loading_failed_layout);
        this.d = (RelativeLayout) findViewById(R.id.rl_parent);
        this.b.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ RedPacketSingleBookActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.l();
            }
        });
        b();
        if (getIntent().getBooleanExtra("isFromReadPage", false)) {
            this.h.setText("红包广场");
            this.h.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ RedPacketSingleBookActivity a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    o.A(this.a, null);
                    i.a("event_D215", null, this.a);
                }
            });
        } else {
            this.h.setText("书籍详情");
            this.h.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ RedPacketSingleBookActivity a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    o.a(this.a, String.valueOf(this.a.n), null, null, null);
                    i.a("event_D216", null, this.a);
                }
            });
        }
        findViewById(R.id.red_packet_single_book_send).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ RedPacketSingleBookActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                o.a(this.a, String.valueOf(this.a.n));
            }
        });
        this.e.setOnScrollListener(new OnScrollListener(this) {
            final /* synthetic */ RedPacketSingleBookActivity a;

            {
                this.a = r1;
            }

            public void onScrollStateChanged(AbsListView absListView, int i) {
            }

            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                if (absListView.getChildCount() <= 0 || (absListView.getFirstVisiblePosition() <= 0 && absListView.getChildAt(0).getTop() >= absListView.getPaddingTop())) {
                    this.a.m.a(this.a);
                    this.a.j.setVisibility(8);
                }
            }
        });
    }

    private void k() {
        IntentFilter intentFilter = new IntentFilter("com.qq.reader.redpacket_arrived");
        d.a(this).a(this.v, intentFilter);
        d.a(this).a(this.w, intentFilter);
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.t != null) {
            g.b(this, this.t);
        }
        d.a(this).a(this.v);
        d.a(this).a(this.w);
    }

    protected boolean handleMessageImp(Message message) {
        try {
            c cVar;
            switch (message.what) {
                case 500005:
                    c();
                    return true;
                case 8000001:
                    cVar = (c) message.obj;
                    if (cVar.f() == 1) {
                        this.c.setRefreshing(false);
                    }
                    if (cVar.f() == 2) {
                        f();
                    }
                    RedPacketSingleBookCard redPacketSingleBookCard;
                    if (cVar.f() == 0) {
                        if (this.l.d().size() > 0 && (this.l.d().get(this.l.d().size() - 1) instanceof RedPacketSingleBookCard)) {
                            ((RedPacketSingleBookCard) this.l.d().get(this.l.d().size() - 1)).setShowDivider(true);
                        }
                        if (this.l.d().size() > 0) {
                            ((RedPacketSingleBookCard) this.l.d().get(this.l.d().size() - 1)).setShowDivider(true);
                        }
                        this.l.a(cVar.h().d());
                        if (this.l.d().size() > 0) {
                            redPacketSingleBookCard = (RedPacketSingleBookCard) this.l.d().get(this.l.d().size() - 1);
                            this.p = redPacketSingleBookCard.getItem().d();
                            redPacketSingleBookCard.setShowDivider(false);
                        }
                        a(this.l, cVar.b(), cVar.f());
                    } else if (cVar.f() == 3) {
                        this.l.a(cVar.h().d(), true);
                        a(this.l, true, cVar.f());
                    } else {
                        a(this.l, cVar.h());
                        this.l = cVar.h();
                        this.q = false;
                        if (cVar.g() > 0) {
                            redPacketSingleBookCard = (RedPacketSingleBookCard) this.l.d().get(this.l.d().size() - 1);
                            this.p = redPacketSingleBookCard.getItem().d();
                            redPacketSingleBookCard.setShowDivider(false);
                        }
                        a(this.l, cVar.b(), cVar.f());
                        if (this.l.d().size() == 0 && !ao.d(ReaderApplication.getApplicationImp())) {
                            if (this.e != null) {
                                this.e.setVisibility(4);
                            }
                            g();
                        }
                        if (this.l.b()) {
                            m();
                        }
                    }
                    return true;
                case 8000002:
                    cVar = (c) message.obj;
                    if (cVar.f() == 2) {
                        f();
                        g();
                    } else if (cVar.f() == 1) {
                        this.c.setRefreshing(false);
                    } else if (cVar.f() == 0) {
                        this.e.d();
                    }
                    return true;
                default:
                    return super.handleMessageImp(message);
            }
        } catch (Exception e) {
            return super.handleMessageImp(message);
        }
        return super.handleMessageImp(message);
    }

    private void a(d dVar, d dVar2) {
        if (dVar != null && dVar2 != null && dVar.d().size() != 0 && dVar2.d().size() != 0) {
            SingleBookValidCard singleBookValidCard;
            Set hashSet = new HashSet();
            for (com.qq.reader.module.bookstore.qnative.card.a aVar : dVar.d()) {
                if (aVar instanceof SingleBookValidCard) {
                    singleBookValidCard = (SingleBookValidCard) aVar;
                    RedPacket item = singleBookValidCard.getItem();
                    if (singleBookValidCard.isIsClicked()) {
                        hashSet.add(Long.valueOf(item.d()));
                    }
                }
            }
            for (com.qq.reader.module.bookstore.qnative.card.a aVar2 : dVar2.d()) {
                if (aVar2 instanceof SingleBookValidCard) {
                    singleBookValidCard = (SingleBookValidCard) aVar2;
                    if (hashSet.contains(Long.valueOf(singleBookValidCard.getItem().d()))) {
                        singleBookValidCard.setIsClicked(true);
                    }
                }
            }
        }
    }

    public void a(d dVar, boolean z, int i) {
        if (dVar != null) {
            a(dVar);
            if (this.i == null) {
                this.i = new b(getApplicationContext());
                this.e.setAdapter(this.i);
            }
            this.i.a(dVar);
            this.i.a();
            if (i == 0) {
                if (z) {
                    this.e.c();
                } else {
                    this.e.e();
                }
            } else if (dVar.d().size() == 0) {
                this.e.g();
            } else if (z) {
                this.e.c();
            } else {
                this.e.b();
            }
            this.i.notifyDataSetChanged();
        }
    }

    private void a(d dVar) {
        if (dVar != null && dVar.d().size() != 0 && !this.q) {
            boolean z = true;
            for (com.qq.reader.module.bookstore.qnative.card.a aVar : dVar.d()) {
                boolean z2;
                if (aVar instanceof SingleBookInValidCard) {
                    if (dVar.d().indexOf(aVar) == 0) {
                        z2 = false;
                        z = z2;
                    } else {
                        z = dVar.d().indexOf(aVar);
                        ((RedPacketSingleBookCard) dVar.d().get(z - 1)).setShowDivider(false);
                    }
                }
                z2 = z;
                z = z2;
            }
            if (z < false) {
                dVar.d().add(z, this.u);
                this.q = true;
            }
        }
    }

    public void b() {
        if (this.e == null) {
            this.e = (XListView) findViewById(R.id.list_layout);
            this.e.setCrashTag(CustomArrayList.Class_NativePageFragmentforOther);
            this.e.setPullRefreshEnable(true);
            this.e.setPullLoadEnable(true);
        }
        this.e.setVisibility(0);
        XListViewFooter redPacketSingleBookListFooter = new RedPacketSingleBookListFooter(this);
        redPacketSingleBookListFooter.setEmptyViewClickListener(new OnClickListener(this) {
            final /* synthetic */ RedPacketSingleBookActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                o.A(this.a, null);
            }
        });
        this.e.setXListFooter(redPacketSingleBookListFooter);
        this.e.setXListViewListener(new XListView$a(this) {
            final /* synthetic */ RedPacketSingleBookActivity a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.mHandler.sendEmptyMessage(500005);
            }
        });
        this.e.setOnScrollListener(new com.qq.reader.common.imageloader.core.a.a(c.a(this).a(), true, true));
        this.e.b();
    }

    public void c() {
        a(0);
    }

    public void d() {
        a(1);
    }

    private void l() {
        a(2);
    }

    protected void e() {
        h();
        if (this.e != null) {
            this.e.setVisibility(8);
        }
        if (this.a != null) {
            this.a.setVisibility(0);
        }
    }

    protected void f() {
        h();
        if (this.e != null) {
            this.e.setVisibility(0);
        }
        if (this.a != null) {
            this.a.setVisibility(8);
        }
    }

    protected void g() {
        if (this.a != null) {
            this.a.setVisibility(8);
        }
        this.b.setVisibility(0);
    }

    private void m() {
        if (this.a != null) {
            this.a.setVisibility(8);
        }
        this.b.setVisibility(0);
        this.b.a(0);
        this.b.setOnClickListener(null);
        this.b.a((CharSequence) "书籍已下架，相关内容无法查看");
        this.b.b((int) R.drawable.empty08);
    }

    protected void h() {
        this.b.setVisibility(8);
    }

    public void doFunction(Bundle bundle) {
    }

    public Activity getFromActivity() {
        return this;
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (intent != null && intent.getBooleanExtra("send_packet_success", false)) {
            d();
            a(intent);
        }
    }

    private void a(Intent intent) {
        String stringExtra = intent.getStringExtra("type");
        String stringExtra2 = intent.getStringExtra("amount");
        String stringExtra3 = intent.getStringExtra("avatar");
        CharSequence stringExtra4 = intent.getStringExtra("nickname");
        final Object stringExtra5 = intent.getStringExtra("message");
        long longExtra = intent.getLongExtra("rid", 0);
        this.r = new BaseDialog(R.style.Dialog_Fullscreen);
        this.r.a(this, null, R.layout.redpacket_share_dialog_layout, 0, false, false, true);
        ((TextView) this.r.a(R.id.send_redpacket_amount_tip)).setText(String.format(getResources().getString(R.string.sendpacket_success_amount), new Object[]{Integer.valueOf(stringExtra2), stringExtra}));
        c.a(this).a(stringExtra3, (ImageView) this.r.a(R.id.user_icon), com.qq.reader.common.imageloader.a.a().e());
        ((TextView) this.r.a(R.id.username)).setText(stringExtra4);
        ((TextView) this.r.a(R.id.redpacket_message)).setText(stringExtra5);
        final String str = e.a.g + "&rid=" + longExtra + "&userQQ=" + com.qq.reader.common.login.c.c().c();
        this.r.a(R.id.share_btn).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ RedPacketSingleBookActivity c;

            public void onClick(View view) {
                new aj(this.c, str, "http://wfqqreader.3g.qq.com/activity/hb_130x130.png", "抢红包：" + this.c.s, stringExtra5, "" + this.c.n).f_();
                i.a("event_D219", null, ReaderApplication.getApplicationImp());
            }
        });
        this.r.a(R.id.close_btn).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ RedPacketSingleBookActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.r != null && this.a.r.f()) {
                    this.a.r.dismiss();
                }
            }
        });
        this.r.f_();
    }
}
