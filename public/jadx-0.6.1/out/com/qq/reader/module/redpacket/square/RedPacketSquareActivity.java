package com.qq.reader.module.redpacket.square;

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
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderDBTask;
import com.qq.reader.common.utils.o;
import com.qq.reader.common.widget.SwipeRefreshLayout;
import com.qq.reader.module.bookstore.qnative.activity.NativeBookStoreConfigBaseActivity;
import com.qq.reader.module.bookstore.qnative.c.a;
import com.qq.reader.module.redpacket.model.RedPacket;
import com.qq.reader.module.redpacket.square.a.b;
import com.qq.reader.module.redpacket.square.a.c;
import com.qq.reader.module.redpacket.view.RedPacketSquareListFooter;
import com.qq.reader.module.redpacket.view.SquareBillboardView;
import com.qq.reader.view.pullupdownlist.XListView;
import com.qq.reader.view.pullupdownlist.XListView$a;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.HashMap;

public class RedPacketSquareActivity extends NativeBookStoreConfigBaseActivity implements a {
    private SquareBillboardView A;
    private boolean B = true;
    private boolean C = true;
    private boolean D = true;
    private boolean E = true;
    private c F;
    protected SwipeRefreshLayout a;
    protected XListView b = null;
    protected View c = null;
    protected View d = null;
    BroadcastReceiver n = new BroadcastReceiver(this) {
        final /* synthetic */ RedPacketSquareActivity a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                Object action = intent.getAction();
                if (!TextUtils.isEmpty(action) && action.equals("com.qq.reader.redpacket_arrived")) {
                    Bundle extras = intent.getExtras();
                    if (extras == null) {
                        return;
                    }
                    if (extras.containsKey("redpacket")) {
                        action = extras.getParcelableArrayList("redpacket");
                        ArrayList arrayList = new ArrayList();
                        arrayList.addAll(action);
                        if (this.a.z == null) {
                            this.a.z = new ArrayList();
                        }
                        if (this.a.y) {
                            this.a.x = action.size() + this.a.x;
                            if (this.a.t != null) {
                                this.a.t.setVisibility(0);
                                this.a.u.setText("有" + this.a.x + "个新红包");
                            }
                            this.a.z.addAll(arrayList);
                            return;
                        }
                        if (this.a.z.size() > 0) {
                            arrayList.addAll(this.a.z);
                            this.a.z.clear();
                        }
                        if (this.a.F != null) {
                            com.qq.reader.module.redpacket.square.b.a aVar = new com.qq.reader.module.redpacket.square.b.a();
                            aVar.a(arrayList);
                            aVar.a(-1);
                            this.a.F.a(aVar);
                        }
                    } else if (extras.containsKey("redpacketmsg")) {
                        ArrayList parcelableArrayList = extras.getParcelableArrayList("redpacketmsg");
                        if (parcelableArrayList != null && parcelableArrayList.size() > 0 && this.a.A != null) {
                            this.a.A.a(parcelableArrayList);
                        }
                    }
                }
            }
        }
    };
    BroadcastReceiver o = new BroadcastReceiver(this) {
        final /* synthetic */ RedPacketSquareActivity a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                Object action = intent.getAction();
                if (!TextUtils.isEmpty(action) && action.equals("com.qq.reader.push.sync") && this.a.F != null) {
                    this.a.a(1, this.a.F.c());
                }
            }
        }
    };
    private TextView p;
    private Button q;
    private ImageView r;
    private RelativeLayout s;
    private View t = null;
    private TextView u;
    private ServiceConnection v;
    private com.qq.reader.module.redpacket.square.a.a w;
    private int x;
    private boolean y = false;
    private ArrayList<RedPacket> z;

    public Activity getFromActivity() {
        return this;
    }

    public boolean handleMessage(Message message) {
        int c;
        int i;
        switch (message.what) {
            case 8000001:
                c();
                this.a.setRefreshing(false);
                com.qq.reader.module.redpacket.square.b.a aVar = (com.qq.reader.module.redpacket.square.b.a) message.obj;
                ArrayList a = aVar.a();
                int b = aVar.b();
                if (a != null && a.size() > 0) {
                    if (b == 2) {
                        this.C = true;
                    }
                    if (b == 1) {
                        this.E = true;
                    }
                    this.w.a(b.a(a, this));
                }
                if (a != null && a.size() == 0) {
                    this.b.g();
                    break;
                }
                c = aVar.c();
                if (c != 0) {
                    if (c != 1) {
                        if (b == 0) {
                            if (!aVar.d()) {
                                this.b.f();
                                break;
                            }
                            this.b.e();
                            break;
                        }
                        this.b.b();
                        break;
                    }
                    this.b.e();
                    break;
                }
                this.b.c();
                break;
            case 8000002:
                c();
                this.a.setRefreshing(false);
                if (this.w.getCount() != 0) {
                    this.b.d();
                    break;
                }
                this.b.g();
                break;
            case 12345012:
                HashMap hashMap = (HashMap) message.obj;
                if (hashMap.size() != 0) {
                    c = message.arg1;
                    if (c == 2) {
                        this.B = true;
                    }
                    if (c == 1) {
                        this.D = true;
                    }
                    if (this.b.getHeaderViewsCount() == 0) {
                        this.A = new SquareBillboardView(this);
                        this.A.setEventListener(this);
                        this.b.addHeaderView(this.A);
                    }
                    this.A.a(hashMap);
                    break;
                }
                i = message.arg1;
                if (i == 2) {
                    this.B = false;
                }
                if (i == 1) {
                    this.D = false;
                }
                if (!(this.B || this.C || this.D || this.E)) {
                    d();
                    break;
                }
            case 12345013:
                c();
                this.a.setRefreshing(false);
                if (this.w.getCount() <= 0) {
                    i = message.arg1;
                    if (i == 2) {
                        this.C = false;
                    }
                    if (i == 1) {
                        this.E = false;
                        if (!this.B && !this.C && !this.D && !this.E) {
                            d();
                            break;
                        }
                        this.b.g();
                        break;
                    }
                }
                this.b.c();
                break;
                break;
        }
        return super.handleMessage(message);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.redpacket_square_layout);
        i.a("event_D226", null, getApplicationContext());
        if (this.F == null) {
            this.F = new c();
        }
        this.F.a(this.mHandler);
        this.F.a(System.currentTimeMillis() - 259200000);
        f();
        b();
        g.a().a(new ReaderDBTask() {
            public void run() {
                super.run();
                final long c = RedPacketSquareActivity.this.F.c();
                RedPacketSquareActivity.this.getHandler().post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass3 b;

                    public void run() {
                        if (c == -1) {
                            RedPacketSquareActivity.this.C = false;
                        } else {
                            RedPacketSquareActivity.this.C = true;
                        }
                        RedPacketSquareActivity.this.a(2, c);
                    }
                });
            }
        });
        d.a(this).a(this.n, new IntentFilter("com.qq.reader.redpacket_arrived"));
        d.a(this).a(this.o, new IntentFilter("com.qq.reader.push.sync"));
        g();
    }

    private void f() {
        super.a();
        this.c = findViewById(R.id.loading_layout);
        this.d = findViewById(R.id.loading_failed_layout);
        this.d.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ RedPacketSquareActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.F != null) {
                    this.a.F.a(1, -1);
                }
            }
        });
        this.t = findViewById(R.id.redpacket_square_new_packet_rl);
        this.u = (TextView) findViewById(R.id.redpacket_square_new_packet_tv);
        this.a = (SwipeRefreshLayout) findViewById(R.id.pull_down_list);
        this.a.setMannuallySetSchemeColor(getResources().getColor(R.color.redpacket_square_pulldown_scheme_color));
        this.a.setOnRefreshListener(new SwipeRefreshLayout.b(this) {
            final /* synthetic */ RedPacketSquareActivity a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.a(1, this.a.F.d());
            }
        });
        this.p = (TextView) findViewById(R.id.profile_header_title);
        this.q = (Button) findViewById(R.id.profile_header_right_button);
        this.p.setText("红包广场");
        this.q.setVisibility(0);
        this.q.setText("我的红包");
        this.q.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ RedPacketSquareActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (com.qq.reader.common.login.c.b()) {
                    this.a.i();
                    return;
                }
                this.a.setLoginNextTask(new com.qq.reader.common.login.a(this) {
                    final /* synthetic */ AnonymousClass8 a;

                    {
                        this.a = r1;
                    }

                    public void a(int i) {
                        switch (i) {
                            case 1:
                                this.a.a.i();
                                return;
                            default:
                                return;
                        }
                    }
                });
                this.a.startLogin();
            }
        });
        this.r = (ImageView) findViewById(R.id.profile_header_left_back);
        this.r.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ RedPacketSquareActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.finish();
            }
        });
        this.s = (RelativeLayout) findViewById(R.id.common_titler);
        this.s.setBackgroundColor(getResources().getColor(R.color.redpacket_square_title_bg));
        this.b = (XListView) findViewById(R.id.redpacket_square_list_layout);
        this.b.setXListFooter(new RedPacketSquareListFooter(this));
        this.b.setPullLoadEnable(true);
        this.b.setPullRefreshEnable(true);
        this.w = new com.qq.reader.module.redpacket.square.a.a(this, this);
        this.b.setAdapter(this.w);
        this.b.setXListViewListener(new XListView$a(this) {
            final /* synthetic */ RedPacketSquareActivity a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.a(0, this.a.F.e());
            }
        });
        this.b.setOnScrollListener(new OnScrollListener(this) {
            final /* synthetic */ RedPacketSquareActivity a;

            {
                this.a = r1;
            }

            public void onScrollStateChanged(AbsListView absListView, int i) {
            }

            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                if (i < 1) {
                    this.a.y = false;
                    this.a.t.setVisibility(8);
                    this.a.u.setText("");
                    if (this.a.z != null && this.a.z.size() > 0 && this.a.F != null) {
                        com.qq.reader.module.redpacket.square.b.a aVar = new com.qq.reader.module.redpacket.square.b.a();
                        aVar.a(this.a.z);
                        aVar.a(-1);
                        this.a.F.a(aVar);
                        this.a.z.clear();
                    }
                } else if (!this.a.y) {
                    this.a.y = true;
                    this.a.x = 0;
                    this.a.u.setText("");
                }
            }
        });
        this.b.b();
        this.t.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ RedPacketSquareActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.b != null) {
                    this.a.b.setSelection(0);
                }
                if (this.a.z != null && this.a.z.size() > 0 && this.a.F != null) {
                    com.qq.reader.module.redpacket.square.b.a aVar = new com.qq.reader.module.redpacket.square.b.a();
                    aVar.a(this.a.z);
                    aVar.a(-1);
                    this.a.F.a(aVar);
                    this.a.z.clear();
                }
            }
        });
    }

    protected void onDestroy() {
        super.onDestroy();
        try {
            if (this.v != null) {
                com.qq.reader.common.conn.socket.g.b(this, this.v);
            }
            d.a(this).a(this.n);
            d.a(this).a(this.o);
            if (this.F != null) {
                this.F.a();
            }
            if (this.A != null) {
                this.A.a();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void g() {
        if (this.v == null) {
            this.v = new ServiceConnection(this) {
                final /* synthetic */ RedPacketSquareActivity a;

                {
                    this.a = r1;
                }

                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                }

                public void onServiceDisconnected(ComponentName componentName) {
                }
            };
        }
        com.qq.reader.common.conn.socket.g.a(this, this.v);
    }

    private void a(int i, long j) {
        if (this.F != null) {
            this.F.a(i, j);
        }
    }

    protected void b() {
        l();
        if (this.b != null) {
            this.b.setVisibility(8);
        }
        if (this.c != null) {
            this.c.setVisibility(0);
        }
    }

    protected void c() {
        l();
        if (this.b != null) {
            this.b.setVisibility(0);
        }
        if (this.c != null) {
            this.c.setVisibility(8);
        }
    }

    protected void d() {
        if (this.b != null) {
            this.b.setVisibility(8);
        }
        if (this.c != null) {
            this.c.setVisibility(8);
        }
        this.d.setVisibility(0);
    }

    protected void l() {
        this.d.setVisibility(8);
    }

    private void i() {
        o.z(this, null);
    }
}
