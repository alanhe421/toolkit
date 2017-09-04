package com.qq.reader.activity;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.t;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.db.handle.i;
import com.qq.reader.common.monitor.j;
import com.qq.reader.common.readertask.ReaderTask;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderDBTask;
import com.qq.reader.common.readertask.ordinal.ReaderIOTask;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.ordinal.ReaderShortTask;
import com.qq.reader.common.readertask.protocol.ObtainGiftPackageTask;
import com.qq.reader.common.receiver.WXBroadcastReceiver;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.o;
import com.qq.reader.common.widget.TabGroup;
import com.qq.reader.cservice.protocol.UserProtocolRedPointManger;
import com.qq.reader.framework.mark.LocalMark;
import com.qq.reader.framework.mark.Mark;
import com.qq.reader.module.bookstore.qnative.activity.NativeBookStoreConfigFindPageFragment;
import com.qq.reader.module.bookstore.qnative.activity.NativeBookStoreConfigStackTabFragment;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.feed.activity.FeedGoogleCardBaseFragment;
import com.qq.reader.plugin.PlugInDefaultActivity;
import com.qq.reader.plugin.b;
import com.qq.reader.plugin.c;
import com.qq.reader.plugin.w;
import com.qq.reader.view.CustomDrawerLayout;
import com.qq.reader.view.ap;
import com.qq.reader.view.k;
import com.qq.reader.view.l;
import com.qq.reader.view.web.m;
import com.tencent.feedback.proguard.R;
import com.tencent.theme.SkinnableActivityProcesser;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;

public class MainActivity extends ReaderBaseActivity implements Callback, com.qq.reader.common.widget.TabGroup.a, com.qq.reader.module.bookstore.qnative.c.a, l {
    public static boolean b = true;
    private BroadcastReceiver A = new BroadcastReceiver(this) {
        final /* synthetic */ MainActivity a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (com.qq.reader.common.c.a.cs.equals(action)) {
                if (this.a.a != null) {
                    this.a.a.schedule(new a(), 600000);
                }
                this.a.getHandler().sendEmptyMessage(8);
            } else if (!com.qq.reader.common.c.a.cr.equals(action)) {
                if (com.qq.reader.common.c.a.cx.equalsIgnoreCase(action)) {
                    this.a.getHandler().sendEmptyMessage(5);
                } else if (com.qq.reader.common.c.a.cz.equalsIgnoreCase(action)) {
                    this.a.getHandler().sendEmptyMessage(8);
                }
            }
        }
    };
    private ArrayList<Object> B = new ArrayList();
    private Map<String, String> C = new HashMap();
    private BroadcastReceiver D = new BroadcastReceiver(this) {
        final /* synthetic */ MainActivity a;

        class AnonymousClass1 extends ReaderShortTask {
            final /* synthetic */ AnonymousClass7 this$1;

            AnonymousClass1(AnonymousClass7 anonymousClass7) {
                this.this$1 = anonymousClass7;
            }

            public void run() {
                com.qq.reader.common.offline.a.a(ReaderApplication.getApplicationImp().getApplicationContext()).c();
            }
        }

        class AnonymousClass2 extends ReaderIOTask {
            final /* synthetic */ AnonymousClass7 this$1;

            AnonymousClass2(AnonymousClass7 anonymousClass7) {
                this.this$1 = anonymousClass7;
            }

            public void run() {
                c.a();
            }
        }

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            if (com.qq.reader.common.c.a.co.equals(intent.getAction())) {
                this.a.a("bookweb_recommend_tab");
            }
        }
    };
    private k E;
    Timer a = new Timer();
    public c c = null;
    final BroadcastReceiver d = new BroadcastReceiver(this) {
        final /* synthetic */ MainActivity a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            int intExtra = intent.getIntExtra("userType", 0);
            if (com.qq.reader.common.c.a.O) {
                g.a().a(new AnonymousClass1(this, intExtra));
                com.qq.reader.common.c.a.O = false;
            }
        }
    };
    BroadcastReceiver e = new BroadcastReceiver(this) {
        final /* synthetic */ MainActivity a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            this.a.f();
        }
    };
    private TabGroup f;
    private b g;
    private ImageView h;
    private ImageView i;
    private ImageView j;
    private Context k;
    private CustomDrawerLayout l = null;
    private com.qq.reader.view.b.b m;
    private View n;
    private SkinnableActivityProcesser o;
    private final int p = 4;
    private BookShelfFragment q;
    private FeedGoogleCardBaseFragment r;
    private NativeBookStoreConfigStackTabFragment s;
    private NativeBookStoreConfigFindPageFragment t;
    private ReaderBaseFragment u;
    private int v = 0;
    private boolean w = false;
    private ProgressDialog x;
    private m y;
    private Animation z;

    private class a extends TimerTask {
        final /* synthetic */ MainActivity a;

        private a(MainActivity mainActivity) {
            this.a = mainActivity;
        }

        public void run() {
            List b = com.qq.reader.cservice.adv.b.a(this.a.getApplicationContext()).b("100111");
            if (b != null && b.size() > 0) {
                com.qq.reader.cservice.adv.a aVar = (com.qq.reader.cservice.adv.a) b.get(0);
                if (aVar != null) {
                    this.a.getHandler().obtainMessage(3, aVar).sendToTarget();
                }
            }
        }
    }

    public Activity getFromActivity() {
        return this;
    }

    public void doFunction(Bundle bundle) {
        if (!bundle.getBoolean("fromFeedAction", false)) {
            return;
        }
        if (this.r != null) {
            this.r.doFunction(this.r, bundle);
        } else {
            o.a((Activity) this, null);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected void onCreate(android.os.Bundle r12) {
        /*
        r11 = this;
        r10 = 8;
        r9 = 0;
        r8 = 2;
        r1 = 0;
        r0 = com.qq.reader.ReaderApplication.timeLog;
        r2 = "MainFragActivity onCreate";
        r0.addSplit(r2);
        super.onCreate(r12);
        r11.setSwipeBackEnable(r1);
        r0 = "hook";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r4 = java.lang.System.currentTimeMillis();
        r6 = com.qq.reader.ReaderApplication.startTime;
        r4 = r4 - r6;
        r2 = r2.append(r4);
        r3 = "";
        r2 = r2.append(r3);
        r2 = r2.toString();
        android.util.Log.e(r0, r2);
        r2 = 0;
        com.qq.reader.ReaderApplication.startTime = r2;
        r0 = new com.tencent.theme.SkinnableActivityProcesser;
        r0.<init>(r11, r9);
        r11.o = r0;
        r0 = r11.getApplicationContext();
        r11.k = r0;
        r0 = "Meizu_M040";
        r2 = com.qq.reader.common.c.a.E;
        r0 = r0.equals(r2);
        if (r0 != 0) goto L_0x0059;
    L_0x0050:
        r0 = r11.getWindow();
        r2 = 16777216; // 0x1000000 float:2.3509887E-38 double:8.289046E-317;
        r0.addFlags(r2);
    L_0x0059:
        r0 = com.qq.reader.common.c.b.e;
        if (r0 == 0) goto L_0x0061;
    L_0x005d:
        r0 = com.qq.reader.common.c.b.e;
        if (r0 != r8) goto L_0x01cc;
    L_0x0061:
        r0 = r11.getLayoutInflater();
        r2 = 2130969114; // 0x7f04021a float:1.75469E38 double:1.0528386316E-314;
        r0 = r0.inflate(r2, r9);
        r0 = (com.qq.reader.view.CustomDrawerLayout) r0;
        r11.l = r0;
        r0 = r11.l;
        r2 = new com.qq.reader.activity.MainActivity$1;
        r2.<init>(r11);
        r0.setDrawerListener(r2);
        r0 = r11.b();
        r2 = r11.l;
        r11.setContentView(r2);
        r2 = 2131495118; // 0x7f0c08ce float:1.8613764E38 double:1.053098512E-314;
        r2 = r0.findViewById(r2);
        r2.setVisibility(r10);
        r2 = com.qq.reader.common.c.b.e;
        if (r2 != r8) goto L_0x009b;
    L_0x0091:
        r2 = 2131495114; // 0x7f0c08ca float:1.8613755E38 double:1.05309851E-314;
        r2 = r0.findViewById(r2);
        r2.setVisibility(r10);
    L_0x009b:
        r2 = 2131495112; // 0x7f0c08c8 float:1.8613751E38 double:1.053098509E-314;
        r0 = r0.findViewById(r2);
        r11.n = r0;
        r11.h();
        if (r12 == 0) goto L_0x01fd;
    L_0x00a9:
        r0 = "tabIndex";
        r0 = r12.getInt(r0);
    L_0x00b0:
        r2 = com.qq.reader.appconfig.a.d.e(r11);
        if (r2 <= 0) goto L_0x00ba;
    L_0x00b6:
        com.qq.reader.appconfig.a.d.b(r11, r1);
        r0 = r2;
    L_0x00ba:
        r1 = r11.getIntent();	 Catch:{ Exception -> 0x01de }
        r2 = "IS_FIRST_OPEN_TODAY";
        r3 = 0;
        r1 = r1.getBooleanExtra(r2, r3);	 Catch:{ Exception -> 0x01de }
        if (r1 == 0) goto L_0x00c9;
    L_0x00c8:
        r0 = 1;
    L_0x00c9:
        r1 = r11.getIntent();	 Catch:{ Exception -> 0x01e4 }
        if (r1 == 0) goto L_0x00da;
    L_0x00cf:
        r1 = r11.getIntent();	 Catch:{ Exception -> 0x01e4 }
        r2 = "main_tab_tag";
        r0 = r1.getIntExtra(r2, r0);	 Catch:{ Exception -> 0x01e4 }
    L_0x00da:
        r1 = r0;
    L_0x00db:
        r0 = r11.getIntent();	 Catch:{ Exception -> 0x01eb }
        if (r0 == 0) goto L_0x01ef;
    L_0x00e1:
        r0 = r11.getIntent();	 Catch:{ Exception -> 0x01eb }
        r2 = "fromNotification";
        r3 = -1;
        r0 = r0.getIntExtra(r2, r3);	 Catch:{ Exception -> 0x01eb }
        if (r0 < 0) goto L_0x01ef;
    L_0x00ef:
        r2 = 4;
        if (r0 >= r2) goto L_0x01ef;
    L_0x00f2:
        r1 = r11.f;
        r1.setCurrentTab(r0);
        if (r0 != r8) goto L_0x0100;
    L_0x00f9:
        r0 = r11.getIntent();
        r11.a(r0);
    L_0x0100:
        r0 = r11.getIntent();	 Catch:{ Exception -> 0x01f2 }
        r11.b(r0);	 Catch:{ Exception -> 0x01f2 }
    L_0x0107:
        r0 = r11.getApplicationContext();
        r0 = com.qq.reader.common.receiver.WXBroadcastReceiver.a(r0);
        r0.a();
        r0 = r11.A;
        r1 = new android.content.IntentFilter;
        r2 = com.qq.reader.common.c.a.cs;
        r1.<init>(r2);
        r11.registerReceiver(r0, r1);
        r0 = r11.A;
        r1 = new android.content.IntentFilter;
        r2 = com.qq.reader.common.c.a.cr;
        r1.<init>(r2);
        r11.registerReceiver(r0, r1);
        r0 = r11.A;
        r1 = new android.content.IntentFilter;
        r2 = com.qq.reader.common.c.a.cx;
        r1.<init>(r2);
        r11.registerReceiver(r0, r1);
        r0 = r11.D;
        r1 = new android.content.IntentFilter;
        r2 = com.qq.reader.common.c.a.co;
        r1.<init>(r2);
        r11.registerReceiver(r0, r1);
        r0 = com.qq.reader.ReaderApplication.getApplicationImp();
        r0 = android.support.v4.content.d.a(r0);
        r1 = r11.A;
        r2 = new android.content.IntentFilter;
        r3 = com.qq.reader.common.c.a.cz;
        r2.<init>(r3);
        r0.a(r1, r2);
        r0 = r11.getIntent();	 Catch:{ Exception -> 0x01f8 }
        r1 = "com.qq.reader.MainActivity.gift";
        r2 = 0;
        r0 = r0.getBooleanExtra(r1, r2);	 Catch:{ Exception -> 0x01f8 }
        if (r0 == 0) goto L_0x018b;
    L_0x0164:
        r0 = new android.content.Intent;	 Catch:{ Exception -> 0x01f8 }
        r0.<init>();	 Catch:{ Exception -> 0x01f8 }
        r1 = com.qq.reader.activity.WebBrowserForContents.class;
        r0.setClass(r11, r1);	 Catch:{ Exception -> 0x01f8 }
        r1 = "com.qq.reader.WebContent";
        r2 = r11.getIntent();	 Catch:{ Exception -> 0x01f8 }
        r3 = "com.qq.reader.WebContent";
        r2 = r2.getStringExtra(r3);	 Catch:{ Exception -> 0x01f8 }
        r0.putExtra(r1, r2);	 Catch:{ Exception -> 0x01f8 }
        r1 = 2131034156; // 0x7f05002c float:1.7678822E38 double:1.0528707666E-314;
        r2 = 2131034159; // 0x7f05002f float:1.7678828E38 double:1.052870768E-314;
        com.qq.reader.common.utils.c.a(r1, r2);	 Catch:{ Exception -> 0x01f8 }
        r11.startActivity(r0);	 Catch:{ Exception -> 0x01f8 }
    L_0x018b:
        com.qq.reader.common.utils.aj.a(r11);
        r0 = android.os.Looper.myQueue();
        r1 = new com.qq.reader.activity.MainActivity$7;
        r1.<init>(r11);
        r0.addIdleHandler(r1);
        com.qq.reader.cservice.download.audio.a.e();
        r0 = com.qq.reader.common.c.b.e;
        if (r0 == 0) goto L_0x01a5;
    L_0x01a1:
        r0 = com.qq.reader.common.c.b.e;
        if (r0 != r8) goto L_0x01b5;
    L_0x01a5:
        r0 = r11.getWindow();
        r0 = r0.getDecorView();
        r1 = new com.qq.reader.activity.MainActivity$8;
        r1.<init>(r11);
        r0.post(r1);
    L_0x01b5:
        com.qq.reader.cservice.adv.c.d();
        r0 = r11.getApplicationContext();
        r0 = com.qq.reader.cservice.protocol.UserProtocolRedPointManger.a(r0);
        r0.a();
        r0 = com.qq.reader.ReaderApplication.timeLog;
        r1 = "MainFragActivity onCreate end";
        r0.addSplit(r1);
        return;
    L_0x01cc:
        r0 = r11.getLayoutInflater();
        r2 = 2130969117; // 0x7f04021d float:1.7546907E38 double:1.052838633E-314;
        r0 = r0.inflate(r2, r9);
        r0 = (android.view.ViewGroup) r0;
        r11.setContentView(r0);
        goto L_0x009b;
    L_0x01de:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x00c9;
    L_0x01e4:
        r1 = move-exception;
        r1.printStackTrace();
        r1 = r0;
        goto L_0x00db;
    L_0x01eb:
        r0 = move-exception;
        r0.printStackTrace();
    L_0x01ef:
        r0 = r1;
        goto L_0x00f2;
    L_0x01f2:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0107;
    L_0x01f8:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x018b;
    L_0x01fd:
        r0 = r1;
        goto L_0x00b0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.activity.MainActivity.onCreate(android.os.Bundle):void");
    }

    private void a(Intent intent) {
        final String stringExtra = intent.getStringExtra(NativeBookStoreConfigStackTabFragment.INTETNT_CATEGORY_TYPE_KEY);
        if (!TextUtils.isEmpty(stringExtra)) {
            int categoryShowIndex = NativeBookStoreConfigStackTabFragment.getCategoryShowIndex(stringExtra);
            if (categoryShowIndex >= 0) {
                d.a(getContext(), categoryShowIndex);
                this.f.post(new Runnable(this) {
                    final /* synthetic */ MainActivity b;

                    class AnonymousClass1 extends ReaderShortTask {
                        final /* synthetic */ AnonymousClass14 this$1;
                        final /* synthetic */ int val$type;

                        AnonymousClass1(AnonymousClass14 anonymousClass14, int i) {
                            this.this$1 = anonymousClass14;
                            this.val$type = i;
                        }

                        public void run() {
                            super.run();
                            ao.a(this.val$type, this.this$1.a.k);
                        }
                    }

                    public void run() {
                        if (this.b.s != null) {
                            this.b.s.switchCategoryByType(stringExtra);
                        }
                    }
                });
            }
        }
    }

    private void g() {
        g.a().a(new ReaderShortTask() {
            public void run() {
                com.qq.reader.cservice.download.game.a aVar = (com.qq.reader.cservice.download.game.a) com.qq.reader.common.download.task.l.d(1006);
                if (aVar != null) {
                    aVar.a(ReaderApplication.getApplicationImp());
                    aVar.e();
                }
            }
        });
    }

    public CustomDrawerLayout a() {
        return this.l;
    }

    public ViewGroup b() {
        ViewGroup viewGroup = (ViewGroup) getLayoutInflater().inflate(R.layout.maintabs, null);
        ((ViewGroup) this.l.findViewById(R.id.content_frame)).addView(viewGroup);
        return viewGroup;
    }

    public void a(View view) {
        ((ViewGroup) this.l.findViewById(R.id.left_drawer)).addView(view);
    }

    protected void onStart() {
        super.onStart();
        if (this.w) {
            d.b((Context) this, 0);
            this.w = false;
        }
    }

    protected void onStop() {
        super.onStop();
    }

    protected void onDestroy() {
        unregisterReceiver(this.D);
        unregisterReceiver(this.A);
        android.support.v4.content.d.a(ReaderApplication.getApplicationImp()).a(this.A);
        if (this.a != null) {
            this.a.cancel();
            this.a = null;
        }
        WXBroadcastReceiver.a(getApplicationContext()).b();
        super.onDestroy();
        if (this.o != null) {
            this.o.destory();
        }
        b = true;
        getHandler().removeMessages(300019);
        com.qq.reader.cservice.adv.c.a(null, false);
        UserProtocolRedPointManger.a(getApplicationContext()).f();
        com.qq.reader.common.d.a.a((Context) this);
        com.qq.reader.common.d.a.a((Activity) this);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        try {
            if ((com.qq.reader.common.c.b.e != 0 && com.qq.reader.common.c.b.e != 2) || this.c == null || !this.c.a(i, i2, intent)) {
                Fragment k = k();
                if (k != null && (i >> 16) == 0) {
                    k.onActivityResult(i, i2, intent);
                }
                super.onActivityResult(i, i2, intent);
                if (this.c != null && i == 4101) {
                    this.c.i();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void b(Intent intent) throws Exception {
        if ("android.intent.action.VIEW".equals(intent.getAction())) {
            ao.a(getApplicationContext());
            String path = intent.getData().getPath();
            if (path != null && !path.equals("")) {
                String substring = path.substring(path.lastIndexOf("/") + 1, path.length());
                if (!path.startsWith("/mnt") && com.qq.reader.common.c.a.l.startsWith("/mnt")) {
                    path = "/mnt" + path;
                }
                Bundle bundle = new Bundle();
                bundle.putString("filepath", path);
                bundle.putString("filename", substring);
                if (i.c().b(bundle.getString("filepath"), true) == null) {
                    Mark localMark = new LocalMark(substring, path, 100, 1, true);
                    localMark.setStartPoint(0);
                    if (localMark != null) {
                        i.c().a(localMark, true);
                    }
                }
                if (this.g == null) {
                    com.qq.reader.plugin.l b = com.qq.reader.plugin.k.b().b("1");
                    if (b != null) {
                        this.g = (b) com.qq.reader.plugin.m.c().b(getApplicationContext(), b);
                    }
                }
                Intent intent2;
                if (this.g != null && this.g.i() && this.g.n()) {
                    intent2 = new Intent();
                    intent2.setClass(this, PluginBrigeActivity.class);
                    intent2.putExtra("pluginname", "pdf");
                    bundle.putString("uri", path);
                    intent2.putExtras(bundle);
                    startActivity(intent2);
                    j.a(65, 0);
                    return;
                }
                intent2 = new Intent();
                intent2.setClass(this, PlugInDefaultActivity.class);
                bundle.putString("PLUGIN_TYPE", "1");
                intent2.putExtras(bundle);
                startActivity(intent2);
            }
        }
    }

    private void h() {
        this.f = (TabGroup) findViewById(R.id.main_radio);
        this.f.setOnTabChangedListener(this);
        this.h = (ImageView) findViewById(R.id.maintab_myself_tip);
        this.i = (ImageView) findViewById(R.id.maintab_bookstand_tip);
        this.j = (ImageView) findViewById(R.id.maintab_web_classify_tip);
        if (d.n((Context) this, "NEW_MAIN_TAB_PROFILE") || d.n((Context) this, "NEW_SIGN_UP")) {
            f(true);
        } else {
            f(false);
        }
    }

    public void a(boolean z) {
    }

    public void a(String str) {
        if ("bookstand_tab".equals(str)) {
            if (this.l == null || !this.l.e(3)) {
                this.f.setCurrentTab(0);
            } else {
                this.l.b();
            }
        } else if ("bookweb_recommend_tab".equals(str)) {
            this.f.setCurrentTab(1);
        } else if ("stacks_tab".equals(str)) {
            this.f.setCurrentTab(2);
        } else if ("bookweb_classify_tab".equals(str)) {
            this.f.setCurrentTab(3);
        }
    }

    private void b(boolean z) {
        if (!z) {
            android.support.v4.app.k supportFragmentManager = getSupportFragmentManager();
            if (supportFragmentManager != null) {
                i();
                android.support.v4.app.m a = supportFragmentManager.a();
                if (this.q == null || !this.q.isAdded()) {
                    if (this.q == null) {
                        this.q = new BookShelfFragment();
                    }
                    if (this.u == null) {
                        a.a(16908305, this.q, "bookShelf");
                    } else {
                        a.b(this.u).a(16908305, this.q, "bookShelf");
                    }
                } else {
                    this.q.setHidden(false);
                    this.q.onResume();
                    if (this.u != null) {
                        a.b(this.u).c(this.q);
                    } else {
                        a.c(this.q);
                    }
                }
                a.b();
            }
            g(false);
            this.C.clear();
            this.C.put("islogin", com.qq.reader.common.login.c.b() ? "1" : "0");
            com.qq.reader.common.monitor.i.a("event_A67", this.C, this.k);
            StatisticsManager.a().a("event_A67", this.C);
            try {
                if (getIntent().getBooleanExtra("widget", false)) {
                    j.a(91, 0);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (this.q != null) {
            Handler handler = this.q.getHandler();
            if (handler != null) {
                handler.sendEmptyMessage(8000007);
            }
        }
    }

    private void c(boolean z) {
        if (!z) {
            com.qq.reader.common.c.a.I = false;
            android.support.v4.app.k supportFragmentManager = getSupportFragmentManager();
            if (supportFragmentManager != null) {
                i();
                android.support.v4.app.m a = supportFragmentManager.a();
                if (this.r == null || !this.r.isAdded()) {
                    if (this.r == null) {
                        this.r = FeedGoogleCardBaseFragment.witchActivityWithABTest();
                    }
                    if (this.u == null) {
                        a.a(16908305, this.r, "feedGoogleCard");
                    } else {
                        a.b(this.u).a(16908305, this.r, "feedGoogleCard");
                    }
                } else {
                    this.r.setHidden(false);
                    this.r.onResume();
                    if (this.u != null) {
                        a.b(this.u).c(this.r);
                    } else {
                        a.c(this.r);
                    }
                }
                a.b();
            }
            com.qq.reader.common.monitor.i.a("event_A7", null, this.k);
            j.a(6, 0);
            StatisticsManager.a().a("event_A7", null);
            try {
                if (getIntent().getBooleanExtra("widget", false)) {
                    j.a(92, 0);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (this.r != null) {
            Handler handler = this.r.getHandler(this.r);
            if (handler != null) {
                handler.sendEmptyMessage(8000006);
                d.br(this);
            }
        }
    }

    private void d(boolean z) {
        if (!z) {
            android.support.v4.app.k supportFragmentManager = getSupportFragmentManager();
            if (supportFragmentManager != null) {
                i();
                android.support.v4.app.m a = supportFragmentManager.a();
                if (this.s == null || !this.s.isAdded()) {
                    if (this.s == null) {
                        this.s = new NativeBookStoreConfigStackTabFragment();
                    }
                    if (this.u == null) {
                        a.a(16908305, this.s, "stackTab");
                    } else {
                        a.b(this.u).a(16908305, this.s, "stackTab");
                    }
                } else {
                    this.s.setHidden(false);
                    this.s.onResume();
                    if (this.u != null) {
                        a.b(this.u).c(this.s);
                    } else {
                        a.c(this.s);
                    }
                }
                a.b();
            }
            Map hashMap = new HashMap();
            hashMap.put(s.ORIGIN, "908");
            com.qq.reader.common.monitor.i.a("event_A6", hashMap, this.k);
            j.a(5, 0);
            StatisticsManager.a().a("event_A6", hashMap);
        } else if (this.s != null) {
            Handler handler = this.s.getHandler();
            if (handler != null) {
                handler.sendEmptyMessage(8000009);
            }
        }
    }

    private void e(boolean z) {
        if (!z) {
            android.support.v4.app.k supportFragmentManager = getSupportFragmentManager();
            if (supportFragmentManager != null) {
                i();
                android.support.v4.app.m a = supportFragmentManager.a();
                if (this.t == null || !this.t.isAdded()) {
                    if (this.t == null) {
                        this.t = new NativeBookStoreConfigFindPageFragment();
                    }
                    if (this.u == null) {
                        a.a(16908305, this.t, "findPage");
                    } else {
                        a.b(this.u).a(16908305, this.t, "findPage");
                    }
                } else {
                    this.t.setHidden(false);
                    this.t.onResume();
                    if (this.u != null) {
                        a.b(this.u).c(this.t);
                    } else {
                        a.c(this.t);
                    }
                }
                a.b();
            }
            Map hashMap = new HashMap();
            hashMap.put("rankboard", "abtest_B");
            com.qq.reader.common.monitor.i.a("event_A144", hashMap, this.k);
            j.a((int) Opcodes.INT_TO_SHORT, 0);
            StatisticsManager.a().a("event_A144", null);
        } else if (this.t != null) {
            Handler handler = this.t.getHandler();
            if (handler != null) {
                handler.sendEmptyMessage(8000008);
            }
        }
    }

    private void i() {
        if (this.u != null) {
            this.u.setHidden(true);
            this.u.onPause();
        }
    }

    public boolean handleMessageImp(Message message) {
        if ((com.qq.reader.common.c.b.e == 0 || com.qq.reader.common.c.b.e == 2) && this.c != null && this.c.a(message)) {
            return true;
        }
        com.qq.reader.cservice.adv.a aVar;
        switch (message.what) {
            case 3:
                aVar = (com.qq.reader.cservice.adv.a) message.obj;
                aVar.a(0);
                com.qq.reader.cservice.adv.b.a(getApplicationContext()).d(aVar);
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), WebBrowserForContents.class);
                intent.setFlags(335544320);
                String h = aVar.h();
                String str = "";
                if (h == null) {
                    h = str;
                }
                if (h.indexOf("=") != -1) {
                    h = h + "&" + e.b(getApplicationContext());
                } else {
                    if (!h.endsWith("?")) {
                        h = h + "?";
                    }
                    h = h + e.b(getApplicationContext());
                }
                intent.putExtra("com.qq.reader.WebContent", h);
                intent.putExtra("ForServerLog", true);
                PendingIntent activity = PendingIntent.getActivity(getApplicationContext(), 0, intent, SigType.WLOGIN_PT4Token);
                t.d y = ao.y(getApplicationContext());
                y.c(aVar.e());
                y.b(aVar.e());
                y.a(activity);
                ((NotificationManager) getSystemService("notification")).notify(12, y.a());
                j.a(73, 0);
                return true;
            case 5:
                g(true);
                return true;
            case 8:
                h(j());
                return true;
            case com.tencent.qalsdk.base.a.cd /*115*/:
                progressCancel();
                Bundle bundle = new Bundle();
                switch (message.arg1) {
                    case -2:
                        bundle.putString("title", "领取失败");
                        bundle.putString("message", "网络错误");
                        break;
                    case -1:
                        bundle.putString("title", "领取失败");
                        bundle.putString("message", String.valueOf(message.obj));
                        break;
                    case 0:
                        bundle.putString("title", "领取成功");
                        bundle.putString("message", "你已获得" + String.valueOf(message.obj) + "\n请到“个人中心”查看");
                        d.bZ(this.k);
                        break;
                    case 1:
                        bundle.putString("title", "领取失败");
                        bundle.putString("message", "您已领取过" + String.valueOf(message.obj) + "\n本活动仅限参与一次");
                        d.bZ(this.k);
                        break;
                }
                showFragmentDialog(800, bundle);
                break;
            case 116:
                if (!com.qq.reader.common.login.c.b()) {
                    loginWithTask(116);
                    break;
                }
                showPorgress("正在领取礼包中");
                l();
                break;
            case Opcodes.INVOKE_SUPER_RANGE /*117*/:
                aVar = (com.qq.reader.cservice.adv.a) message.obj;
                this.y = new m(this, 1);
                this.y.a(aVar, getHandler());
                d.M(this.k, false);
                d.N(this.k, false);
                if (d.bY(this.k) >= 2) {
                    aVar.a(0);
                    com.qq.reader.cservice.adv.b.a(getApplicationContext()).d(aVar);
                    break;
                }
                break;
            case 65538:
                if (this.y != null) {
                    aVar = (com.qq.reader.cservice.adv.a) message.obj;
                    if (!aVar.f().equalsIgnoreCase("102668")) {
                        aVar.a(0);
                        com.qq.reader.cservice.adv.b.a(getApplicationContext()).d(aVar);
                    }
                    this.y.f_();
                    j.a((int) Opcodes.NOT_INT, 0);
                    break;
                }
                break;
        }
        return super.handleMessageImp(message);
    }

    public void showFragmentDialog(int i, Bundle bundle) {
        switch (i) {
            case 800:
                com.qq.reader.view.AlertDialog.a aVar = new com.qq.reader.view.AlertDialog.a(this);
                aVar.b(bundle.getString("message"));
                aVar.a(bundle.getString("title"));
                aVar.a(false);
                aVar.a((CharSequence) "我知道了", new OnClickListener(this) {
                    final /* synthetic */ MainActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                aVar.a().show();
                return;
            default:
                return;
        }
    }

    public void showPorgress(String str) {
        if (!isFinishing()) {
            if (this.x == null) {
                if (str == null) {
                    str = "";
                }
                this.x = ProgressDialog.show(this, null, str, true);
                this.x.setCanceledOnTouchOutside(false);
                this.x.setCancelable(true);
                this.x.setOnKeyListener(new OnKeyListener(this) {
                    final /* synthetic */ MainActivity a;

                    {
                        this.a = r1;
                    }

                    public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                        switch (i) {
                            case 4:
                                this.a.progressCancel();
                                break;
                        }
                        return false;
                    }
                });
                return;
            }
            this.x.show();
        }
    }

    public void progressCancel() {
        if (!isFinishing() && this.x != null && this.x.isShowing()) {
            try {
                this.x.cancel();
                this.x = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (this.v == 0 && this.q != null) {
            return this.q.onKeyDown(i, keyEvent);
        }
        if (this.v == 1 && this.r != null) {
            return this.r.onKeyDown(this.r, i, keyEvent);
        }
        if (this.v == 2 && this.s != null) {
            return this.s.onKeyDown(i, keyEvent);
        }
        if (this.v != 3 || this.t == null) {
            return false;
        }
        return this.t.onKeyDown(i, keyEvent);
    }

    protected void onResume() {
        ReaderApplication.timeLog.addSplit("MainFragActivity onResume");
        super.onResume();
        if ((com.qq.reader.common.c.b.e == 0 || com.qq.reader.common.c.b.e == 2) && this.c != null) {
            this.c.c();
        }
        registerReceiver(this.d, new IntentFilter("com.qq.reader.selectpreference.mainactivity"));
        new com.qq.reader.common.monitor.k(getApplicationContext()).a();
        StatisticsManager.a().a("event_reader", null);
        registerReceiver(this.e, new IntentFilter(com.qq.reader.common.c.a.di));
        Intent intent = new Intent();
        intent.setAction(com.qq.reader.common.c.a.di);
        sendBroadcast(intent);
        com.qq.reader.tinker.b.b().a();
        ReaderApplication.timeLog.addSplit("MainFragActivity onResume end");
        w.b();
        w.h();
    }

    protected void onPause() {
        new com.qq.reader.common.monitor.k(getApplicationContext()).a();
        com.qq.reader.common.offline.c.a(getApplicationContext()).a();
        super.onPause();
        if (this.m != null && this.m.c()) {
            this.m.b();
        }
        try {
            unregisterReceiver(this.d);
            unregisterReceiver(this.e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        com.qq.reader.common.c.a.de = intent.getBooleanExtra("IS_GOTO_BOOKSHELF", false);
        int intExtra = intent.getIntExtra("main_tab_tag", 0);
        this.f.setCurrentTab(intExtra);
        if (intExtra == 2) {
            String stringExtra = intent.getStringExtra(NativeBookStoreConfigStackTabFragment.INTETNT_CATEGORY_TYPE_KEY);
            if (this.s != null) {
                this.s.switchCategoryByType(stringExtra);
            }
        }
        if (intent.getBooleanExtra("fromjump", false) && this.l != null && this.l.e(3)) {
            this.l.b();
        }
        try {
            b(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
    }

    protected void onSaveInstanceState(Bundle bundle) {
        d.b((Context) this, this.v);
        this.w = true;
    }

    private void f(boolean z) {
    }

    private void g(boolean z) {
    }

    private void h(boolean z) {
        if (z) {
            this.j.setVisibility(0);
        } else {
            this.j.setVisibility(4);
        }
    }

    public void c() {
        if (this.m != null && this.m.c()) {
            this.m.b();
        }
        this.m = ap.a(7, this);
        this.m.a((l) this);
        this.m.a();
    }

    public void dismiss(int i) {
    }

    public k getHighLightArea(int i) {
        if (this.E == null) {
            View view = this.f;
            r1 = new int[4];
            view.getLocationOnScreen(r1);
            r1[2] = r1[0] + view.getWidth();
            r1[3] = view.getHeight() + r1[1];
            this.E = new k();
            this.E.a = new Rect(r1[0], r1[1], r1[2], r1[3]);
            this.E.b = 1;
        }
        return this.E;
    }

    private boolean j() {
        return com.qq.reader.cservice.adv.c.e();
    }

    private Fragment k() {
        switch (this.v) {
            case 0:
                return this.q;
            case 1:
                return this.r;
            case 2:
                return this.s;
            case 3:
                return this.t;
            default:
                return null;
        }
    }

    public int d() {
        return this.v;
    }

    public void a(int i, int i2) {
        boolean z = true;
        if (i != i2) {
            a(i2);
        }
        this.v = i2;
        android.support.v4.app.k supportFragmentManager = getSupportFragmentManager();
        this.q = (BookShelfFragment) supportFragmentManager.a("bookShelf");
        this.r = (FeedGoogleCardBaseFragment) supportFragmentManager.a("feedGoogleCard");
        this.s = (NativeBookStoreConfigStackTabFragment) supportFragmentManager.a("stackTab");
        this.t = (NativeBookStoreConfigFindPageFragment) supportFragmentManager.a("findPage");
        if (i == 0) {
            this.u = this.q;
        } else if (i == 1) {
            this.u = this.r;
        } else if (i == 2) {
            this.u = this.s;
        } else if (i == 3) {
            this.u = this.t;
        }
        if (i2 == 0) {
            boolean z2;
            if (i == i2) {
                z2 = true;
            } else {
                z2 = false;
            }
            b(z2);
        } else if (i2 == 1) {
            if (i != i2) {
                z = false;
            }
            c(z);
        } else if (i2 == 2) {
            if (i != i2) {
                z = false;
            }
            d(z);
        } else if (i2 == 3) {
            if (i != i2) {
                z = false;
            }
            e(z);
        }
    }

    private void a(int i) {
        int[] iArr = new int[]{R.id.img_front_icon_1, R.id.img_front_icon_2, R.id.img_front_icon_3, R.id.img_front_icon_4};
        for (int findViewById : iArr) {
            findViewById(findViewById).setVisibility(8);
        }
        if (i < iArr.length) {
            if (!(this.z == null || this.z.hasEnded())) {
                this.z.cancel();
            }
            final View findViewById2 = findViewById(iArr[i]);
            this.z = AnimationUtils.loadAnimation(this, R.anim.maintab_front_icon_enter_anim);
            this.z.setAnimationListener(new AnimationListener(this) {
                final /* synthetic */ MainActivity b;

                public void onAnimationStart(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                    if (findViewById2 == null) {
                        return;
                    }
                    if (animation == null || this.b.z == null || animation.hashCode() == this.b.z.hashCode()) {
                        findViewById2.setVisibility(0);
                    } else {
                        findViewById2.setVisibility(8);
                    }
                }

                public void onAnimationRepeat(Animation animation) {
                }
            });
            findViewById2.setVisibility(0);
            findViewById2.startAnimation(this.z);
        }
    }

    public void e() {
        if (this.q != null) {
            this.q.startCloudService();
        }
    }

    protected void f() {
        if (!d.ca(this.k) && !d.cb(this.k)) {
            g.a().a(new ReaderDBTask() {
                public void run() {
                    List b = com.qq.reader.cservice.adv.b.a(MainActivity.this.getApplicationContext()).b("102668");
                    if (b.size() > 0) {
                        com.qq.reader.cservice.adv.a aVar = (com.qq.reader.cservice.adv.a) b.get(0);
                        Message obtainMessage = MainActivity.this.getHandler().obtainMessage();
                        obtainMessage.what = Opcodes.INVOKE_SUPER_RANGE;
                        obtainMessage.obj = aVar;
                        MainActivity.this.getHandler().sendMessage(obtainMessage);
                    }
                }
            });
        }
    }

    private void l() {
        ReaderTask obtainGiftPackageTask = new ObtainGiftPackageTask();
        obtainGiftPackageTask.registerNetTaskListener(new com.qq.reader.common.readertask.ordinal.c(this) {
            final /* synthetic */ MainActivity a;

            {
                this.a = r1;
            }

            /* JADX WARNING: inconsistent code. */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onConnectionRecieveData(com.qq.reader.common.readertask.ordinal.ReaderProtocolTask r5, java.lang.String r6, long r7) {
                /*
                r4 = this;
                r3 = -1;
                r0 = r4.a;
                r0 = r0.getHandler();
                r1 = r0.obtainMessage();
                r0 = 115; // 0x73 float:1.61E-43 double:5.7E-322;
                r1.what = r0;
                r0 = new org.json.JSONObject;	 Catch:{ Exception -> 0x003b }
                r0.<init>(r6);	 Catch:{ Exception -> 0x003b }
                r2 = "code";
                r2 = r0.getInt(r2);	 Catch:{ Exception -> 0x003b }
                switch(r2) {
                    case 0: goto L_0x002e;
                    case 1: goto L_0x0042;
                    case 2: goto L_0x0042;
                    default: goto L_0x001e;
                };	 Catch:{ Exception -> 0x003b }
            L_0x001e:
                r2 = "msg";
                r0 = r0.optString(r2);	 Catch:{ Exception -> 0x003b }
                r1.obj = r0;	 Catch:{ Exception -> 0x003b }
                r0 = -1;
                r1.arg1 = r0;	 Catch:{ Exception -> 0x003b }
            L_0x002a:
                r1.sendToTarget();
                return;
            L_0x002e:
                r2 = 0;
                r1.arg1 = r2;	 Catch:{ Exception -> 0x003b }
                r2 = "gift";
                r0 = r0.optString(r2);	 Catch:{ Exception -> 0x003b }
                r1.obj = r0;	 Catch:{ Exception -> 0x003b }
                goto L_0x002a;
            L_0x003b:
                r0 = move-exception;
                r0.printStackTrace();
                r1.arg1 = r3;
                goto L_0x002a;
            L_0x0042:
                r2 = 1;
                r1.arg1 = r2;	 Catch:{ Exception -> 0x003b }
                r2 = "gift";
                r0 = r0.optString(r2);	 Catch:{ Exception -> 0x003b }
                r1.obj = r0;	 Catch:{ Exception -> 0x003b }
                goto L_0x002a;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.activity.MainActivity.4.onConnectionRecieveData(com.qq.reader.common.readertask.ordinal.ReaderProtocolTask, java.lang.String, long):void");
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                exception.printStackTrace();
                Message obtainMessage = this.a.getHandler().obtainMessage();
                obtainMessage.arg1 = -2;
                obtainMessage.what = com.tencent.qalsdk.base.a.cd;
                this.a.getHandler().sendMessage(obtainMessage);
            }
        });
        g.a().a(obtainGiftPackageTask);
    }
}
