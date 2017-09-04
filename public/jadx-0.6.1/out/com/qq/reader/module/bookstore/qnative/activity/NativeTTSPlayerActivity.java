package com.qq.reader.module.bookstore.qnative.activity;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.StrikethroughSpan;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.iflytek.speech.UtilityConfig;
import com.pay.http.APPluginErrorCode;
import com.qq.reader.ReaderApplication;
import com.qq.reader.activity.AudioBookDownloadActivity;
import com.qq.reader.activity.ReaderBaseActivity;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.db.handle.k;
import com.qq.reader.common.db.handle.r;
import com.qq.reader.common.db.handle.v;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.protocol.ReadOnline.ReadOnlineResult;
import com.qq.reader.common.readertask.ReaderTask;
import com.qq.reader.common.readertask.ordinal.ReaderDBTask;
import com.qq.reader.common.readertask.ordinal.ReaderIOTask;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.ordinal.ReaderShortTask;
import com.qq.reader.common.readertask.protocol.QueryChapterBuyInfoTask;
import com.qq.reader.common.readertask.protocol.QueryUserBalanceTask;
import com.qq.reader.common.utils.q;
import com.qq.reader.common.web.js.JSPay;
import com.qq.reader.cservice.buy.a.b;
import com.qq.reader.cservice.buy.chapter.ChapterPayResult;
import com.qq.reader.cservice.download.audio.AuthCheckTask;
import com.qq.reader.cservice.onlineread.OnlineTag;
import com.qq.reader.framework.mark.Mark;
import com.qq.reader.module.bookchapter.ChapterAdapterItem;
import com.qq.reader.module.bookchapter.online.OnlineChapter;
import com.qq.reader.module.bookchapter.online.f;
import com.qq.reader.module.bookchapter.online.g;
import com.qq.reader.module.bookchapter.online.h;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.plugin.audiobook.core.e;
import com.qq.reader.plugin.tts.n;
import com.qq.reader.readengine.model.BookTing;
import com.qq.reader.readengine.model.IBook;
import com.qq.reader.view.AlertDialog;
import com.qq.reader.view.EmptyView;
import com.qq.reader.view.af;
import com.qq.reader.view.ao;
import com.qq.reader.view.ar;
import com.qq.reader.view.ar$a;
import com.qq.reader.view.c;
import com.tencent.android.tpush.common.Constants;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public class NativeTTSPlayerActivity extends ReaderBaseActivity implements b, ar$a {
    private ImageView A;
    private c B;
    private ao C;
    private View D;
    private TextView E;
    private int F = 0;
    private View G;
    private int H = 0;
    private final int I = 1;
    private final int J = 2;
    private final int K = 3;
    private final int L = 4;
    private Mark M = null;
    private BroadcastReceiver N = new BroadcastReceiver(this) {
        final /* synthetic */ NativeTTSPlayerActivity a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            try {
                String action = intent.getAction();
                if (action.equals("BROADCAST_ACTION_TTS_SWITCH_CHAPTER_SUCEESS")) {
                    OnlineTag onlineTag = (OnlineTag) intent.getParcelableExtra("onlinetag");
                    com.qq.reader.common.utils.ao.t();
                    this.a.l();
                    if (this.a.O != null) {
                        this.a.b(this.a.O.k());
                    }
                    this.a.r();
                } else if (action.equals("BROADCAST_ACTION_TTS_STATE_CHANGE")) {
                    this.a.l();
                } else if (action.equals("BROADCAST_ACTION_TTS_CHAPTER_END")) {
                    int intExtra = intent.getIntExtra("book_chapterid", this.a.O.g());
                    if (intExtra != this.a.O.g()) {
                        this.a.O.g(intExtra);
                        this.a.O.c(intExtra);
                        this.a.a(this.a.O.g());
                    }
                } else if (action.equals(e.m)) {
                    Bundle extras = intent.getExtras();
                    ReadOnlineResult readOnlineResult = (ReadOnlineResult) extras.getParcelable("onlinetag");
                    readOnlineResult.s();
                    extras.getInt("download_type");
                    int i = -1;
                    if (readOnlineResult.d()) {
                        i = 1001;
                    } else if (readOnlineResult.a()) {
                        i = 1000;
                    }
                    Message obtain = Message.obtain();
                    obtain.what = 10000506;
                    obtain.arg1 = i;
                    obtain.obj = readOnlineResult;
                    this.a.mHandler.sendMessage(obtain);
                    this.a.r();
                } else if (action.equals(e.o)) {
                    this.a.mHandler.sendEmptyMessage(10000512);
                    this.a.r();
                } else if (action.equalsIgnoreCase(e.s)) {
                    this.a.b(this.a.O.k());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    private OnlineTag O;
    private IBook P;
    private com.qq.reader.module.readpage.c Q;
    private g R;
    private boolean S = false;
    private boolean T = false;
    private f U;
    private EmptyView V;
    private View W;
    private List<OnlineChapter> X = new ArrayList();
    private boolean Y = false;
    private int Z = -1;
    ImageView a;
    private ListView aa;
    private h ab;
    private OnItemClickListener ac = new OnItemClickListener(this) {
        final /* synthetic */ NativeTTSPlayerActivity a;

        {
            this.a = r1;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            if ((view instanceof ChapterAdapterItem) && this.a.O != null) {
                OnlineChapter onlineChapter = (OnlineChapter) this.a.ab.getItem(i);
                if (onlineChapter != null) {
                    this.a.O.b(onlineChapter.getChapterName());
                }
                this.a.O.a(0);
                if (!(onlineChapter == null || onlineChapter.getChapterId() == this.a.O.g())) {
                    this.a.O.c(onlineChapter.getChapterId());
                    this.a.O.g(onlineChapter.getChapterId());
                    this.a.a(this.a.O);
                    this.a.a(this.a.O.g() - 1);
                }
                if (this.a.G != null) {
                    this.a.G.setVisibility(8);
                }
            }
        }
    };
    private boolean ad = false;
    private ar ae;
    private long af = 0;
    private ao.b ag = new ao.b(this) {
        final /* synthetic */ NativeTTSPlayerActivity a;

        {
            this.a = r1;
        }

        public void b() {
        }

        public void c() {
            this.a.v.setText("定时");
        }

        public void a(long j) {
            if (j == 0) {
                this.a.v.setText("定时");
            } else {
                this.a.v.setText(com.qq.reader.module.c.a.a(j));
            }
        }

        public void a() {
            com.qq.reader.common.utils.ao.u();
            com.qq.reader.module.c.a.a(2);
            this.a.l();
            this.a.v.setText("定时");
        }
    };
    private ProgressDialog ah;
    private ProgressDialog ai;
    TextView b;
    TextView c;
    ImageButton d;
    public String e = "CLOUD_SYN_TASK_RESULT_BOOKID";
    public String f = "CLOUD_SYN_TASK_RESULT_CHAPTERID";
    public String g = "CLOUD_SYN_TASK_RESULT_OFFSET";
    boolean h = false;
    int i = 0;
    int j;
    int k;
    private boolean l = false;
    private String m;
    private g n;
    private TextView o;
    private TextView p;
    private TextView q;
    private boolean r = false;
    private String s;
    private final com.qq.reader.common.charge.voucher.a.a t = new com.qq.reader.common.charge.voucher.a.a();
    private ImageView u;
    private TextView v;
    private TextView w;
    private TextView x;
    private ImageView y;
    private ImageView z;

    class AnonymousClass20 implements com.qq.reader.common.readertask.ordinal.c {
        final /* synthetic */ NativeTTSPlayerActivity a;

        public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
            if (this.a.M != null) {
                this.a.M.setPrivateProperty(0);
            }
            com.qq.reader.common.readertask.g.a().a(new AnonymousClass1(this));
        }

        public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
            this.a.mHandler.post(new Runnable(this) {
                final /* synthetic */ AnonymousClass20 a;

                {
                    this.a = r1;
                }

                public void run() {
                    af.a(this.a.a.getApplicationContext(), (int) R.string.toast_catgory_error, 1).a();
                }
            });
        }
    }

    class AnonymousClass42 implements com.qq.reader.common.readertask.ordinal.c {
        final /* synthetic */ NativeTTSPlayerActivity a;

        public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
            if (this.a.M != null) {
                this.a.M.setPrivateProperty(1);
            }
            com.qq.reader.common.readertask.g.a().a(new AnonymousClass1(this));
        }

        public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
            af.a(this.a.getApplicationContext(), (int) R.string.toast_catgory_error, 1).a();
        }
    }

    private class a {
        AlertDialog a;
        Bundle b;
        final /* synthetic */ NativeTTSPlayerActivity c;

        private a(NativeTTSPlayerActivity nativeTTSPlayerActivity) {
            this.c = nativeTTSPlayerActivity;
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.tts_player_layout);
        TextView textView = (TextView) findViewById(R.id.profile_header_title);
        textView.setText("人声朗读");
        textView.setTextColor(getResources().getColor(R.color.text_color_c101));
        textView.setTextSize(1, 18.0f);
        ImageView imageView = (ImageView) findViewById(R.id.profile_header_left_back);
        imageView.setImageResource(R.drawable.titlebar_icon_back_selector_black_down);
        imageView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NativeTTSPlayerActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                i.a("event_C189", null, this.a.getContext());
                this.a.f();
            }
        });
        imageView.setImageResource(R.drawable.titlebar_icon_back_selector_black_down);
        findViewById(R.id.common_titler).setBackgroundColor(-1);
        ImageButton imageButton = (ImageButton) findViewById(R.id.profile_header_right_collect);
        imageButton.setVisibility(8);
        imageButton.setImageResource(R.drawable.audio_player_tts_icon);
        imageButton.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NativeTTSPlayerActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
            }
        });
        findViewById(R.id.progress_info).setVisibility(8);
        this.D = findViewById(R.id.online_chapter_list);
        this.d = (ImageButton) findViewById(R.id.profile_header_right_image);
        this.d.setVisibility(8);
        d.z((Context) this, false);
        this.y = (ImageView) findViewById(R.id.play_pause_btn);
        this.x = (TextView) findViewById(R.id.tv_price);
        this.E = (TextView) findViewById(R.id.tv_bookname);
        this.a = (ImageView) findViewById(R.id.img_cover);
        this.a.setOnClickListener(new com.qq.reader.module.bookstore.qnative.c.c(this) {
            final /* synthetic */ NativeTTSPlayerActivity a;

            {
                this.a = r1;
            }

            public void a(View view) {
                this.a.mUseAnimation = false;
                this.a.finish();
                this.a.overridePendingTransition(0, R.anim.slide_out_bottom);
            }
        });
        this.w = (TextView) findViewById(R.id.add_bookshell_btn);
        this.w.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NativeTTSPlayerActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.mUseAnimation = false;
                this.a.finish();
                this.a.overridePendingTransition(0, R.anim.slide_out_bottom);
            }
        });
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(e.m);
        intentFilter.addAction(e.o);
        intentFilter.addAction("BROADCAST_ACTION_TTS_SWITCH_CHAPTER_SUCEESS");
        intentFilter.addAction("BROADCAST_ACTION_TTS_STATE_CHANGE");
        intentFilter.addAction("BROADCAST_ACTION_TTS_CHAPTER_END");
        android.support.v4.content.d.a(ReaderApplication.getApplicationImp()).a(this.N, intentFilter);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            CharSequence string = extras.getString("bookname");
            if (!TextUtils.isEmpty(string)) {
                textView.setText(string);
            }
            this.H = extras.getInt("from", 0);
            if (this.H == 1) {
                imageButton.setVisibility(0);
            }
            String string2 = extras.getString("bookrealid");
            this.O = (OnlineTag) extras.getParcelable("com.qq.reader.OnlineTag");
            if (string2 != null) {
                boolean z;
                long j;
                if (this.O == null) {
                    this.O = v.b().a(string2);
                    if (this.O == null) {
                        this.O = new OnlineTag(string2, "", System.currentTimeMillis());
                        this.O.c(1);
                        this.O.j(1);
                        z = true;
                        com.qq.reader.common.db.handle.i.c().e(string2);
                        this.M = com.qq.reader.common.db.handle.i.c().b(string2, true);
                        this.O.j(1);
                        this.Z = extras.getInt("book_chapterid");
                        if (this.Z > 0) {
                            this.O.c(this.Z);
                        }
                        j = 0;
                        j = Long.parseLong(string2);
                        this.P = new BookTing(j, this.O.a(this.O.g()));
                        b(this.O);
                        if (z) {
                            a("正在拉取书籍信息...");
                        }
                        g();
                        this.V = (EmptyView) findViewById(R.id.online_chapter_empyt_layout);
                        this.V.setOnClickListener(new OnClickListener(this) {
                            final /* synthetic */ NativeTTSPlayerActivity a;

                            class AnonymousClass1 extends ReaderDBTask {
                                final /* synthetic */ AnonymousClass20 this$1;

                                AnonymousClass1(AnonymousClass20 anonymousClass20) {
                                    this.this$1 = anonymousClass20;
                                }

                                public void run() {
                                    com.qq.reader.common.db.handle.i.c().b(this.this$1.a.O.k() + "", 0);
                                }
                            }

                            {
                                this.a = r1;
                            }

                            public void onClick(View view) {
                                this.a.V.setVisibility(8);
                                this.a.W.setVisibility(0);
                                this.a.u();
                            }
                        });
                        this.aa = (ListView) findViewById(R.id.online_chapter_list);
                        this.ab = new h();
                        this.aa.setAdapter(this.ab);
                        this.aa.setVisibility(8);
                        this.aa.setOnItemClickListener(this.ac);
                        this.W = findViewById(R.id.chapter_loading);
                        this.W.setVisibility(0);
                    }
                }
                z = false;
                com.qq.reader.common.db.handle.i.c().e(string2);
                this.M = com.qq.reader.common.db.handle.i.c().b(string2, true);
                this.O.j(1);
                this.Z = extras.getInt("book_chapterid");
                if (this.Z > 0) {
                    this.O.c(this.Z);
                }
                j = 0;
                try {
                    j = Long.parseLong(string2);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                this.P = new BookTing(j, this.O.a(this.O.g()));
                b(this.O);
                if (z) {
                    a("正在拉取书籍信息...");
                }
                g();
                this.V = (EmptyView) findViewById(R.id.online_chapter_empyt_layout);
                this.V.setOnClickListener(/* anonymous class already generated */);
                this.aa = (ListView) findViewById(R.id.online_chapter_list);
                this.ab = new h();
                this.aa.setAdapter(this.ab);
                this.aa.setVisibility(8);
                this.aa.setOnItemClickListener(this.ac);
                this.W = findViewById(R.id.chapter_loading);
                this.W.setVisibility(0);
            }
            this.Q = new com.qq.reader.module.readpage.c(getApplicationContext(), this.mHandler, 2);
        }
        this.q = (TextView) findViewById(R.id.tv_download_voucher_tips);
        u();
        com.qq.reader.common.readertask.g.a().a(new ReaderIOTask() {
            public void run() {
                super.run();
                if (NativeTTSPlayerActivity.this.O != null) {
                    NativeTTSPlayerActivity.this.b(NativeTTSPlayerActivity.this.O.k());
                }
            }
        });
        String str = Build.BRAND;
        if (TextUtils.isEmpty(str)) {
            this.ad = false;
            return;
        }
        if (str.toUpperCase().contains("XIAOMI")) {
            com.qq.reader.common.utils.aj.a.a((Activity) this, true);
        } else if (str.toUpperCase().contains("MEIZU")) {
            com.qq.reader.common.utils.aj.a.a(getWindow(), true);
        }
        this.ad = true;
    }

    private void f() {
        this.mUseAnimation = false;
        finish();
        overridePendingTransition(0, R.anim.slide_out_bottom);
    }

    public boolean needSetImmerseMode() {
        return this.ad;
    }

    protected void onResume() {
        super.onResume();
        if (this.Q != null) {
            this.Q.a();
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        android.support.v4.content.d.a(ReaderApplication.getApplicationImp()).a(this.N);
        try {
            v();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        switch (i) {
            case 4:
                f();
                return true;
            default:
                return super.onKeyDown(i, keyEvent);
        }
    }

    private void g() {
        try {
            com.qq.reader.common.imageloader.c.a(getContext()).a(com.qq.reader.common.utils.ao.g(Long.parseLong(this.O.k())), this.a, com.qq.reader.common.imageloader.a.a().j());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private OnlineChapter h() {
        try {
            if (this.X == null || this.X.size() == 0) {
                return null;
            }
            if (this.O != null) {
                int g = this.O.g() - 1;
                if (g < this.X.size()) {
                    return (OnlineChapter) this.X.get(g);
                }
                if (this.X.size() > 0) {
                    return (OnlineChapter) this.X.get(0);
                }
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public ar a() {
        if (this.ae == null) {
            this.ae = new ar(this);
            this.ae.a(this);
        }
        return this.ae;
    }

    private void i() {
        this.b = (TextView) findViewById(R.id.chapter_name);
        this.c = (TextView) findViewById(R.id.author_info);
        this.G = findViewById(R.id.gift_tip_layout);
        this.o = (TextView) findViewById(R.id.current_time);
        this.p = (TextView) findViewById(R.id.total_time);
        this.v = (TextView) findViewById(R.id.tv_timer);
        this.y.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NativeTTSPlayerActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                try {
                    int j = n.f().j();
                    if (j == 3 || j == 5) {
                        com.qq.reader.common.utils.ao.v();
                    } else if (j == 2) {
                        com.qq.reader.common.utils.ao.w();
                    } else if (j == 1) {
                        com.qq.reader.common.utils.ao.t();
                    } else if (j == 4) {
                        com.qq.reader.common.utils.ao.w();
                    }
                } catch (Exception e) {
                }
            }
        });
        this.A = (ImageView) findViewById(R.id.next_btn);
        this.A.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NativeTTSPlayerActivity a;

            class AnonymousClass1 extends ReaderDBTask {
                final /* synthetic */ AnonymousClass42 this$1;

                AnonymousClass1(AnonymousClass42 anonymousClass42) {
                    this.this$1 = anonymousClass42;
                }

                public void run() {
                    com.qq.reader.common.db.handle.i.c().b(this.this$1.a.O.k() + "", 1);
                }
            }

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (!this.a.b()) {
                    this.a.O.g(this.a.O.g() + 1);
                    this.a.O.c(this.a.O.g() + 1);
                    this.a.a(this.a.O);
                    this.a.a(this.a.O.g());
                }
            }
        });
        n();
        this.z = (ImageView) findViewById(R.id.prev_btn);
        this.z.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NativeTTSPlayerActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (!this.a.b()) {
                    this.a.O.g(this.a.O.g() - 1);
                    this.a.O.c(this.a.O.g() - 1);
                    this.a.a(this.a.O);
                    this.a.a(this.a.O.g());
                }
            }
        });
        j();
        this.u = (ImageView) findViewById(R.id.timer_btn);
        q b = com.qq.reader.module.c.a.b(2);
        if (b != null && b.a()) {
            this.v.setText(com.qq.reader.module.c.a.a(b.b()));
            b.a(this.ag);
        }
        findViewById(R.id.layout_timer);
        this.u.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NativeTTSPlayerActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.k();
                i.a("event_B186", null, this.a);
            }
        });
        findViewById(R.id.download_btn).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NativeTTSPlayerActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.a().i();
            }
        });
    }

    public synchronized boolean b() {
        boolean z;
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.af < 1500) {
            z = true;
        } else {
            this.af = currentTimeMillis;
            z = false;
        }
        return z;
    }

    private void j() {
        if (this.O != null && this.E != null) {
            this.E.setText(this.O.b());
            this.x.setText(this.O.h());
        }
    }

    private void k() {
        if (this.C == null) {
            this.C = new ao(this, this.ag, 2);
            this.C.b(true);
        }
        this.C.f_();
    }

    private void l() {
        try {
            int j = n.f().j();
            if (j == 3 || j == 5 || j == 1) {
                this.y.clearAnimation();
                this.y.setImageResource(R.drawable.play_btn);
            } else {
                this.y.clearAnimation();
                this.y.setImageResource(R.drawable.pause_btn);
            }
            j = this.O.g() - 1;
            if (j == 0) {
                this.z.setClickable(false);
                this.z.setImageResource(R.drawable.play_btn_prev_hover);
            } else {
                this.z.setClickable(true);
                this.z.setImageResource(R.drawable.prev_btn);
            }
            if (j == this.X.size() - 1) {
                this.A.setClickable(false);
                this.A.setImageResource(R.drawable.play_btn_next_hover);
            } else {
                this.A.setClickable(true);
                this.A.setImageResource(R.drawable.next_btn);
            }
            if (this.aa != null && j != this.aa.getSelectedItemPosition()) {
                a(j);
            }
        } catch (Exception e) {
        }
    }

    private void a(OnlineChapter onlineChapter) {
        if (onlineChapter != null) {
            int j = n.f().j();
            if (j != 2 && j != 4) {
                if (onlineChapter.getChapterId() != this.O.g()) {
                    this.O.b(onlineChapter.getChapterName());
                    this.O.a(0);
                    this.O.c(onlineChapter.getChapterId());
                    this.O.g(onlineChapter.getChapterId());
                    com.qq.reader.common.utils.ao.b(this.O);
                    return;
                }
                this.O.b(onlineChapter.getChapterName());
                this.O.a(0);
                this.O.c(onlineChapter.getChapterId());
                this.O.g(onlineChapter.getChapterId());
                this.y.performClick();
            }
        }
    }

    protected boolean handleMessageImp(Message message) {
        com.qq.reader.cservice.buy.a.c cVar;
        Intent intent;
        switch (message.what) {
            case 1218:
                i.a("event_B144", null, ReaderApplication.getApplicationImp());
                if (t()) {
                    cVar = (com.qq.reader.cservice.buy.a.c) message.obj;
                    this.O.e(true);
                    if (cVar.e() == 10001) {
                        intent = new Intent();
                        intent.putExtra("com.qq.reader.OnlineTag", this.O);
                        intent.setClass(this, AudioBookDownloadActivity.class);
                        startActivity(intent);
                        break;
                    }
                }
                break;
            case 1219:
                if (t()) {
                    cVar = (com.qq.reader.cservice.buy.a.c) message.obj;
                    int d = cVar.d();
                    Bundle bundle = new Bundle();
                    bundle.putString("message", cVar.a());
                    if (d != -2) {
                        if (d != -6) {
                            showFragmentDialog(607, bundle);
                            break;
                        }
                        showFragmentDialog(608, bundle);
                        break;
                    } else if (!com.qq.reader.common.login.c.a((Activity) this, Boolean.valueOf(true))) {
                        com.qq.reader.common.login.c.a();
                        bundle.putString("message", "支付出现问题，请重试");
                        showFragmentDialog(607, bundle);
                        break;
                    }
                }
                break;
            case 1224:
                o();
                break;
            case 1225:
                o();
                break;
            case 1235:
                o();
                finish();
                break;
            case 1238:
                if (t()) {
                    b(this.U.g());
                    af.a(getApplicationContext(), (CharSequence) "购买成功", 0).a();
                    break;
                }
                break;
            case 1239:
                if (t()) {
                    af.a(getApplicationContext(), ((ChapterPayResult) message.obj).getResultStr(), 0).a();
                    break;
                }
                break;
            case APPluginErrorCode.ERROR_APP_SYSTEM /*2000*/:
                com.qq.reader.cservice.cloud.f fVar = (com.qq.reader.cservice.cloud.f) message.obj;
                Bundle bundle2 = new Bundle();
                bundle2.putString("message", getResources().getString(R.string.colud_info2) + fVar.g() + getResources().getString(R.string.colud_info3));
                bundle2.putLong(this.e, fVar.c());
                bundle2.putInt(this.f, (int) fVar.g());
                bundle2.putInt(this.g, fVar.f());
                showFragmentDialog(605, bundle2);
                break;
            case 2001:
                if (com.qq.reader.common.login.c.b() && !((message.obj instanceof Mark) && ((Mark) message.obj).getBookId() == 0)) {
                    a(message.obj);
                    break;
                }
            case 21000:
                this.U = (f) message.obj;
                this.W.setVisibility(8);
                com.qq.reader.module.bookchapter.online.d y = this.U.y();
                if (y != null) {
                    this.O.d(y.j());
                    this.O.a(y.e());
                    this.O.e(y.G());
                    this.O.h(y.C());
                }
                if (this.U.t()) {
                    af.a((Context) this, getResources().getString(R.string.price_info_limitfree) + "，" + com.qq.reader.common.utils.ao.i(this.R.e().x()), 0).a();
                }
                this.s = this.U.x();
                if (this.O.g() > this.O.n()) {
                    OnlineTag a = v.b().a(this.O.k());
                    if (a == null) {
                        this.O.c(1);
                    } else {
                        this.O.c(a.g());
                    }
                }
                Collection e = this.U.e();
                if (e != null && e.size() > 0) {
                    this.X.clear();
                    this.X.addAll(e);
                    this.S = true;
                    if (this.O.n() == 0) {
                        this.O.d(this.X.size());
                    }
                }
                r.a().a(2, y.d(), y.e());
                o();
                i();
                l();
                b(this.O.k());
                if (e == null || e.size() == 0) {
                    this.aa.setVisibility(8);
                    this.V.setVisibility(0);
                } else {
                    int g = this.O.g() - 1;
                    this.aa.setVisibility(0);
                    this.V.setVisibility(8);
                    this.ab.a(this.U.e());
                    a(g);
                }
                if (message.arg2 == 2) {
                    j();
                    if (e != null && e.size() > 0 && message.arg2 == 2) {
                        this.ab.a(e);
                        this.ab.notifyDataSetChanged();
                    }
                }
                com.qq.reader.common.readertask.g.a().a(new ReaderShortTask() {
                    public void run() {
                        Message obtain = Message.obtain();
                        obtain.what = 200013;
                        obtain.obj = NativeTTSPlayerActivity.this.h();
                        NativeTTSPlayerActivity.this.getHandler().sendMessage(obtain);
                    }
                });
                break;
            case 21001:
                o();
                if (!this.S) {
                    m();
                    break;
                }
                break;
            case 21011:
                ArrayList arrayList = (ArrayList) message.obj;
                if (this.ab != null) {
                    this.ab.a(arrayList);
                    this.ab.notifyDataSetChanged();
                    break;
                }
                break;
            case 21017:
                if (this.O != null) {
                    this.O.e(true);
                    if (this.x != null) {
                        this.x.setText("价格：" + getResources().getString(R.string.paypage_pay_all_ok));
                        break;
                    }
                }
                break;
            case 21103:
                r();
                return true;
            case 200013:
                if (!this.T) {
                    a((OnlineChapter) message.obj);
                    this.T = true;
                    break;
                }
                break;
            case 400008:
                e();
                break;
            case 8000011:
                b((a) message.obj);
                return true;
            case 10000503:
                if (o()) {
                    this.O.e(true);
                    intent = new Intent();
                    intent.putExtra("com.qq.reader.OnlineTag", this.O);
                    intent.setClass(this, AudioBookDownloadActivity.class);
                    startActivity(intent);
                    break;
                }
                break;
            case 10000504:
                if (o()) {
                    Bundle bundle3 = new Bundle();
                    bundle3.putInt("PAY_TYPE", 1001);
                    bundle3.putInt("KEY_BUY_BOOK_FROM", 10001);
                    showFragmentDialog(606, bundle3);
                    break;
                }
                break;
            case 10000505:
                if (o()) {
                    af.a(getApplicationContext(), (CharSequence) "购买验证失败", 0).a();
                    break;
                }
                break;
            case 10000506:
                if (!isFragmentDialogShowing()) {
                    int i = message.arg1;
                    ReadOnlineResult readOnlineResult = (ReadOnlineResult) message.obj;
                    Bundle bundle4 = new Bundle();
                    if (i != -1) {
                        bundle4.putInt("PAY_TYPE", i);
                        bundle4.putInt("PAY_CHAPER_SOURCE_PRICE", readOnlineResult.g());
                        bundle4.putString("PAY_CHAPER_NAME", readOnlineResult.t());
                        bundle4.putInt("PAY_CHAPER_DISCOUNT_PRICE", readOnlineResult.j());
                        bundle4.putString("PAY_CHAPER_DISCOUNT_REASON", readOnlineResult.F());
                        bundle4.putInt("KEY_BUY_BOOK_FROM", Constants.ERRORCODE_UNKNOWN);
                        showFragmentDialog(609, bundle4);
                        break;
                    }
                    bundle4.putInt("CHAPTER_CODE", readOnlineResult.s());
                    bundle4.putString("PAY_ERROR_MSG", readOnlineResult.u());
                    showFragmentDialog(501, bundle4);
                    break;
                }
                break;
            case 10000512:
                showFragmentDialog(303);
                break;
        }
        return super.handleMessageImp(message);
    }

    private void a(int i) {
        if (this.ab != null && i < this.ab.getCount()) {
            this.ab.a(i);
            this.aa.setSelection(i);
            this.ab.notifyDataSetChanged();
            this.aa.smoothScrollToPositionFromTop(i, 0);
            try {
                if (i < this.ab.getCount()) {
                    OnlineChapter onlineChapter = (OnlineChapter) this.ab.getItem(i);
                    if (onlineChapter != null) {
                        this.O.b(onlineChapter.getChapterName());
                    }
                }
            } catch (Exception e) {
                com.qq.reader.common.monitor.debug.c.e("Error", e.getMessage());
            }
        }
        j();
    }

    protected Dialog createDialog(int i, Bundle bundle) {
        AlertDialog alertDialog = (AlertDialog) com.qq.reader.module.readpage.q.a(this, i, null);
        final int i2;
        switch (i) {
            case 303:
                alertDialog.setTitle("提示");
                alertDialog.a((CharSequence) "本集需要登录后才能播放");
                alertDialog.a((int) R.string.login_btn, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ NativeTTSPlayerActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        this.a.mLoginNextTask = new com.qq.reader.common.login.a(this) {
                            final /* synthetic */ AnonymousClass14 a;

                            {
                                this.a = r1;
                            }

                            public void a(int i) {
                                switch (i) {
                                    case 1:
                                        this.a.a.mHandler.postDelayed(new Runnable(this) {
                                            final /* synthetic */ AnonymousClass1 a;

                                            {
                                                this.a = r1;
                                            }

                                            public void run() {
                                                try {
                                                    this.a.a.a.a(this.a.a.a.O);
                                                } catch (Exception e) {
                                                    com.qq.reader.common.monitor.debug.c.e("Error", e.getMessage());
                                                }
                                            }
                                        }, 500);
                                        return;
                                    default:
                                        return;
                                }
                            }
                        };
                        this.a.startLogin();
                    }
                });
                break;
            case 304:
                alertDialog.a((int) R.string.bookinfo_add2bookshelf_ok, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ NativeTTSPlayerActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        i.a("event_C234", null, this.a.getApplicationContext());
                        this.a.Y = true;
                        this.a.finish();
                    }
                });
                alertDialog.b((int) R.string.alert_dialog_cancel, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ NativeTTSPlayerActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        i.a("event_C233", null, this.a.getApplicationContext());
                        this.a.finish();
                    }
                });
                break;
            case 308:
                alertDialog.a((int) R.string.alert_dialog_ok, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ NativeTTSPlayerActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        this.a.O.c(false);
                        d.z(this.a, false);
                        i.a("event_C192", null, this.a.getContext());
                    }
                });
                alertDialog.b((int) R.string.alert_dialog_cancel, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ NativeTTSPlayerActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                break;
            case 501:
                String string = bundle.getString("PAY_ERROR_MSG");
                i2 = bundle.getInt("CHAPTER_CODE");
                if (i2 == -1) {
                    alertDialog.setTitle("提示");
                    alertDialog.a((CharSequence) "本集需要登录后才能播放");
                    alertDialog.a((int) R.string.login_btn, new DialogInterface.OnClickListener(this) {
                        final /* synthetic */ NativeTTSPlayerActivity b;

                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (i2 == -1) {
                                this.b.mLoginNextTask = new com.qq.reader.common.login.a(this) {
                                    final /* synthetic */ AnonymousClass16 a;

                                    {
                                        this.a = r1;
                                    }

                                    public void a(int i) {
                                        switch (i) {
                                            case 1:
                                                this.a.b.mHandler.postDelayed(new Runnable(this) {
                                                    final /* synthetic */ AnonymousClass1 a;

                                                    {
                                                        this.a = r1;
                                                    }

                                                    public void run() {
                                                        try {
                                                            this.a.a.b.a(this.a.a.b.O);
                                                        } catch (Exception e) {
                                                            com.qq.reader.common.monitor.debug.c.e("Error", e.getMessage());
                                                        }
                                                    }
                                                }, 500);
                                                return;
                                            default:
                                                return;
                                        }
                                    }
                                };
                                this.b.startLogin();
                            }
                        }
                    });
                    break;
                }
                alertDialog.a(string + "(" + i2 + ")");
                alertDialog.a((int) R.string.ok, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ NativeTTSPlayerActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                break;
            case 605:
                final int i3 = bundle.getInt(this.f);
                i2 = bundle.getInt(this.g);
                alertDialog.a(bundle.getString("message"));
                alertDialog.a((int) R.string.alert_dialog_cloud_ok, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ NativeTTSPlayerActivity c;

                    public void onClick(DialogInterface dialogInterface, int i) {
                        OnlineTag z = this.c.O.z();
                        if (z != null) {
                            z.c(i3);
                            z.a((long) i2);
                            z.a(false);
                            this.c.a(z);
                        }
                        com.qq.reader.common.monitor.debug.c.a(Constants.LogTag, "TYPE_ONLINE  chapter id = " + i3 + " /  offset = " + i2);
                    }
                });
                alertDialog.b((int) R.string.alert_dialog_cancel, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ NativeTTSPlayerActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                break;
            case 606:
            case 609:
                a(alertDialog, (Activity) this, bundle);
                break;
            case 607:
                alertDialog.a(bundle.getString("message"));
                alertDialog.a((int) R.string.alert_dialog_ok, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ NativeTTSPlayerActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                break;
            case 608:
                alertDialog.a(bundle.getString("message"));
                alertDialog.a((int) R.string.charge, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ NativeTTSPlayerActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        this.a.d();
                    }
                });
                alertDialog.b((int) R.string.alert_dialog_cancel, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ NativeTTSPlayerActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                alertDialog.a(-1, (int) R.drawable.buy_book_dialog_confirm_bg);
                break;
            case 611:
                alertDialog.a((int) R.string.alert_dialog_ok, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ NativeTTSPlayerActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                alertDialog.b((int) R.string.alert_dialog_cancel, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ NativeTTSPlayerActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                break;
            case 802:
                alertDialog.a((int) R.string.retry, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ NativeTTSPlayerActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        this.a.b(this.a.O);
                    }
                });
                alertDialog.b((int) R.string.alert_dialog_cancel, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ NativeTTSPlayerActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                break;
        }
        return alertDialog;
    }

    private void a(OnlineTag onlineTag) {
        try {
            q();
            com.qq.reader.common.utils.ao.u();
            com.qq.reader.common.utils.ao.b(onlineTag);
        } catch (Exception e) {
        }
    }

    private void a(Object obj) {
        try {
            this.Q.a(null, this.P, obj);
        } catch (Exception e) {
        }
    }

    private void b(OnlineTag onlineTag) {
        if (onlineTag != null) {
            if (this.R == null) {
                this.R = new g(getApplicationContext(), onlineTag.z());
            }
            this.R.a(this.mHandler);
            this.R.a(true);
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 100) {
            if (i2 == -1 && intent != null) {
                Bundle extras = intent.getExtras();
                if (extras != null) {
                    i.a("event_C193", null, getContext());
                    OnlineTag onlineTag = (OnlineTag) extras.getParcelable("com.qq.reader.OnlineTag");
                    if (onlineTag != null) {
                        int g = onlineTag.g();
                        if (this.O.g() != g) {
                            this.O.c(g);
                            this.O.a(0);
                            a(onlineTag);
                        }
                    }
                }
            }
        } else if (i == 20001) {
            if (i2 == 0) {
                c();
            }
        } else if (100 == i && intent != null) {
            String stringExtra = intent.getStringExtra("selected_role");
            if (stringExtra != null && stringExtra.length() > 0 && !d.ay(getApplicationContext()).equalsIgnoreCase(stringExtra) && n.f().a(stringExtra)) {
                d.o(getApplicationContext(), stringExtra);
                n.f().m();
            }
        }
    }

    private void m() {
    }

    private void n() {
        this.D.setVisibility(0);
    }

    private void a(String str) {
        if (this.B == null) {
            this.B = new c(this);
            this.B.c(true);
        }
        this.B.a(str);
        this.B.f_();
    }

    private boolean o() {
        try {
            if (this.B != null && this.B.f()) {
                this.B.cancel();
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    private void a(AlertDialog alertDialog, Activity activity, Bundle bundle) {
        try {
            String str;
            int i;
            String str2;
            Object obj;
            int i2;
            View inflate = LayoutInflater.from(activity).inflate(R.layout.book_buy_view_new, null);
            TextView textView = (TextView) inflate.findViewById(R.id.book_discount_msg);
            TextView textView2 = (TextView) inflate.findViewById(R.id.tv_origin_price);
            TextView textView3 = (TextView) inflate.findViewById(R.id.suffix_txt);
            TextView textView4 = (TextView) inflate.findViewById(R.id.tv_price);
            final CheckBox checkBox = (CheckBox) inflate.findViewById(R.id.auto_pay_check);
            TextView textView5 = (TextView) inflate.findViewById(R.id.textViewDownloadType);
            ProgressBar progressBar = (ProgressBar) inflate.findViewById(R.id.pb_user_balance);
            String str3 = "";
            int i3 = 0;
            String str4 = "";
            int i4 = 0;
            str4 = "";
            this.O.k();
            int s = this.R.e().s();
            String r = this.R.e().r();
            this.j = bundle.getInt("KEY_BUY_BOOK_FROM");
            this.k = bundle.getInt("PAY_TYPE");
            int i5;
            if (this.k == 1001) {
                if (!(this.R == null || this.R.e() == null)) {
                    i3 = this.R.e().p();
                    i4 = this.R.e().v();
                    str4 = this.R.e().w();
                }
                if (this.O != null) {
                    str3 = this.O.b();
                }
                textView3.setText("书币");
                textView5.setVisibility(0);
                str = str3;
                i = i3;
                i5 = i4;
                str2 = r;
                obj = String.valueOf(i3) + "书币/本";
                i2 = i3;
                i3 = i5;
            } else {
                str = bundle.getString("PAY_CHAPER_NAME");
                i = bundle.getInt("PAY_CHAPER_SOURCE_PRICE");
                i3 = bundle.getInt("PAY_CHAPER_DISCOUNT_PRICE");
                r = bundle.getString("PAY_CHAPER_DISCOUNT_REASON");
                textView3.setText("书币");
                if (i3 == i) {
                    s = 100;
                } else {
                    s = i3 / i;
                }
                checkBox.setVisibility(0);
                checkBox.setChecked(true);
                i5 = i3;
                i3 = 0;
                str2 = r;
                r = String.valueOf(i) + "书币";
                i2 = i5;
            }
            StringBuilder stringBuilder = new StringBuilder();
            if (s < 100 || i3 > 0) {
                Object obj2;
                CharSequence spannableString;
                if (this.k == 1001) {
                    i2 = (i * s) / 100;
                    if (i3 > 0 && i3 < i2) {
                        i2 = i3;
                        obj2 = str4;
                        if (TextUtils.isEmpty(obj2)) {
                            textView.setText("(" + obj2 + ")");
                            textView.setVisibility(0);
                        } else {
                            textView.setVisibility(8);
                        }
                        textView4.setText(String.valueOf(i2));
                        spannableString = new SpannableString(obj);
                        spannableString.setSpan(new StrikethroughSpan(), 0, obj.length(), 33);
                        textView2.setText(spannableString);
                        if (this.k == 1001) {
                            textView5.setVisibility(0);
                        }
                    }
                }
                String str5 = str2;
                if (TextUtils.isEmpty(obj2)) {
                    textView.setVisibility(8);
                } else {
                    textView.setText("(" + obj2 + ")");
                    textView.setVisibility(0);
                }
                textView4.setText(String.valueOf(i2));
                spannableString = new SpannableString(obj);
                spannableString.setSpan(new StrikethroughSpan(), 0, obj.length(), 33);
                textView2.setText(spannableString);
                if (this.k == 1001) {
                    textView5.setVisibility(0);
                }
            } else {
                textView.setVisibility(8);
                textView2.setVisibility(8);
                textView4.setText(String.valueOf(i));
            }
            textView = (TextView) inflate.findViewById(R.id.book_name);
            textView.setText(String.format(getResources().getString(R.string.buy_book_name), new Object[]{str}));
            int a = this.t.a();
            CharSequence b = this.t.b();
            textView = (TextView) inflate.findViewById(R.id.tv_user_balance);
            if (a < 0) {
                progressBar.setVisibility(0);
                b = "";
            } else {
                progressBar.setVisibility(8);
            }
            textView.setText(b);
            if (this.t.d > 0) {
                com.qq.reader.common.charge.voucher.b.a(this, textView, new OnClickListener(this) {
                    final /* synthetic */ NativeTTSPlayerActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                    }
                }, new OnDismissListener(this) {
                    final /* synthetic */ NativeTTSPlayerActivity a;

                    {
                        this.a = r1;
                    }

                    public void onDismiss(DialogInterface dialogInterface) {
                    }
                }, this.t.g);
            }
            alertDialog.a(inflate);
            alertDialog.setTitle(getString(R.string.alert_dialog_buy));
            final Map hashMap = new HashMap();
            hashMap.put(s.ORIGIN, this.k + "");
            final AlertDialog alertDialog2;
            if (a < 0 || a >= i2) {
                i.a("event_C199", hashMap, getContext());
                alertDialog2 = alertDialog;
                alertDialog.a((int) R.string.alert_dialog_buy_confirm, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ NativeTTSPlayerActivity d;

                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (this.d.k == 1001 || !checkBox.isChecked()) {
                            this.d.O.c(false);
                            com.qq.reader.common.utils.ao.a(false);
                        } else {
                            i.a("event_C190", hashMap, this.d.getContext());
                            this.d.O.c(true);
                            com.qq.reader.common.utils.ao.a(true);
                        }
                        alertDialog2.c();
                        String k = this.d.O.k();
                        i.a("event_C201", null, this.d.getContext());
                        if (this.d.k != 1001) {
                            this.d.p();
                        } else if (!TextUtils.isEmpty(k)) {
                            this.d.a(k, this.d.j);
                        }
                    }
                });
                alertDialog2 = alertDialog;
                alertDialog.b((int) R.string.alert_dialog_cancel, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ NativeTTSPlayerActivity b;

                    public void onClick(DialogInterface dialogInterface, int i) {
                        alertDialog2.c();
                    }
                });
            } else {
                i.a("event_C200", hashMap, getContext());
                this.i = i2;
                alertDialog2 = alertDialog;
                alertDialog.a((int) R.string.alert_dialog_buy_balance_insufficient, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ NativeTTSPlayerActivity c;

                    public void onClick(DialogInterface dialogInterface, int i) {
                        alertDialog2.c();
                        this.c.d();
                        i.a("event_C202", hashMap, this.c.getContext());
                    }
                });
            }
            alertDialog.a(-1, (int) R.drawable.selector_orange_button);
            alertDialog.a(-2, (int) R.drawable.selector_white_button);
            NativeTTSPlayerActivity nativeTTSPlayerActivity = this;
            a aVar = new a();
            aVar.b = bundle;
            aVar.a = alertDialog;
            a(aVar);
        } catch (Exception e) {
            com.qq.reader.common.monitor.debug.c.e("Error", e.getMessage());
        }
    }

    private void a(String str, int i) {
        com.qq.reader.cservice.buy.a.d dVar = new com.qq.reader.cservice.buy.a.d(getApplicationContext(), str);
        dVar.a(i);
        dVar.a((b) this);
        dVar.start();
        s();
    }

    public void O() {
        if (this.ae != null) {
            this.ae.cancel();
            this.ae.g();
        }
        com.qq.reader.common.utils.ao.u();
    }

    public void h(int i) {
        if (this.ae != null) {
            this.ae.cancel();
            this.ae.g();
        }
        com.qq.reader.common.utils.ao.g(i);
    }

    public void P() {
        if (this.ae != null) {
            this.ae.cancel();
            this.ae.g();
        }
        Intent intent = new Intent();
        intent.setAction(UtilityConfig.SETTINGS_ACTION_TTS);
        intent.setPackage(UtilityConfig.COMPONENT_PKG);
        Bundle bundle = new Bundle();
        bundle.putString("abc", d.ay(getApplicationContext()));
        intent.putExtras(bundle);
        try {
            startActivityForResult(intent, 100);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void a(final a aVar) {
        com.qq.reader.common.readertask.g.a().a(new QueryUserBalanceTask(new com.qq.reader.common.readertask.protocol.QueryUserBalanceTask.a(this) {
            final /* synthetic */ NativeTTSPlayerActivity b;

            public void a(com.qq.reader.common.charge.voucher.a.a aVar) {
                this.b.t.a(aVar);
                Message obtainMessage = this.b.mHandler.obtainMessage(8000011);
                obtainMessage.obj = aVar;
                this.b.mHandler.sendMessage(obtainMessage);
            }

            public void a() {
            }
        }, String.valueOf(this.P.getBookNetId()), 0));
    }

    public void c() {
        com.qq.reader.common.readertask.g.a().a(new QueryUserBalanceTask(new com.qq.reader.common.readertask.protocol.QueryUserBalanceTask.a(this) {
            final /* synthetic */ NativeTTSPlayerActivity a;

            {
                this.a = r1;
            }

            public void a(com.qq.reader.common.charge.voucher.a.a aVar) {
                this.a.t.a(aVar);
                Message obtain = Message.obtain();
                obtain.what = 400008;
                this.a.mHandler.sendMessage(obtain);
            }

            public void a() {
            }
        }, String.valueOf(this.P.getBookNetId()), 0));
    }

    public void b(a aVar) {
        int i = 0;
        try {
            final AlertDialog alertDialog = aVar.a;
            Bundle bundle = aVar.b;
            if (alertDialog != null && alertDialog.isShowing()) {
                TextView textView = (TextView) alertDialog.findViewById(R.id.tv_user_balance);
                if (textView != null) {
                    textView.setText(this.t.b());
                }
                if (this.t.d > 0) {
                    com.qq.reader.common.charge.voucher.b.a(this, textView, new OnClickListener(this) {
                        final /* synthetic */ NativeTTSPlayerActivity a;

                        {
                            this.a = r1;
                        }

                        public void onClick(View view) {
                        }
                    }, new OnDismissListener(this) {
                        final /* synthetic */ NativeTTSPlayerActivity a;

                        {
                            this.a = r1;
                        }

                        public void onDismiss(DialogInterface dialogInterface) {
                        }
                    }, this.t.g);
                }
                ProgressBar progressBar = (ProgressBar) alertDialog.findViewById(R.id.pb_user_balance);
                if (progressBar != null) {
                    progressBar.setVisibility(8);
                }
                Button c = alertDialog.c(-1);
                Button c2 = alertDialog.c(-2);
                final CheckBox checkBox = (CheckBox) alertDialog.findViewById(R.id.auto_pay_check);
                if (c != null && c2 != null) {
                    int i2;
                    int i3;
                    OnlineTag onlineTag = this.O;
                    if (this.R == null || this.R.e() == null) {
                        i2 = 100;
                    } else {
                        i2 = this.R.e().s();
                    }
                    this.j = bundle.getInt("KEY_BUY_BOOK_FROM");
                    this.k = bundle.getInt("PAY_TYPE");
                    if (this.k == 1001) {
                        if (this.R == null || this.R.e() == null) {
                            i3 = 0;
                        } else {
                            i3 = this.R.e().p();
                            i = this.R.e().v();
                        }
                        i3 = (i3 * i2) / 100;
                        if (i <= 0 || i >= i3) {
                            i = i3;
                        }
                    } else {
                        bundle.getInt("PAY_CHAPER_SOURCE_PRICE");
                        i = bundle.getInt("PAY_CHAPER_DISCOUNT_PRICE");
                    }
                    i3 = this.t.a();
                    if (i3 < 0 || i3 >= i) {
                        c.setOnClickListener(new OnClickListener(this) {
                            final /* synthetic */ NativeTTSPlayerActivity c;

                            public void onClick(View view) {
                                alertDialog.c();
                                String k = this.c.O.k();
                                if (this.c.k != 1001) {
                                    if (checkBox.isChecked()) {
                                        i.a("event_C190", null, this.c.getContext());
                                        if (this.c.O != null) {
                                            this.c.O.c(true);
                                        }
                                        com.qq.reader.common.utils.ao.a(true);
                                    } else {
                                        if (this.c.O != null) {
                                            this.c.O.c(false);
                                        }
                                        com.qq.reader.common.utils.ao.a(false);
                                    }
                                    this.c.p();
                                } else if (!TextUtils.isEmpty(k)) {
                                    this.c.a(k, this.c.j);
                                }
                                i.a("event_C201", null, this.c.getContext());
                            }
                        });
                        c.setText(getString(R.string.alert_dialog_buy_confirm));
                        c2.setVisibility(0);
                        c2.setText(getString(R.string.alert_dialog_cancel));
                        c2.setOnClickListener(new OnClickListener(this) {
                            final /* synthetic */ NativeTTSPlayerActivity b;

                            public void onClick(View view) {
                                alertDialog.c();
                            }
                        });
                        return;
                    }
                    this.i = i;
                    if (!com.qq.reader.common.utils.ao.s(this.t.e)) {
                        View findViewById;
                        if (getResources().getConfiguration().orientation == 2) {
                            findViewById = alertDialog.findViewById(R.id.activity_info_land);
                            textView = (TextView) alertDialog.findViewById(R.id.activity_text_land);
                        } else {
                            findViewById = alertDialog.findViewById(R.id.activity_info);
                            textView = (TextView) alertDialog.findViewById(R.id.activity_text);
                        }
                        findViewById.setVisibility(0);
                        textView.setText(this.t.e);
                        textView.setVisibility(0);
                        Map hashMap = new HashMap();
                        hashMap.put(s.ORIGIN, "5");
                        i.a("event_A202", hashMap, ReaderApplication.getApplicationImp());
                    }
                    c.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ NativeTTSPlayerActivity b;

                        public void onClick(View view) {
                            i.a("event_C202", null, this.b.getContext());
                            alertDialog.c();
                            this.b.d();
                        }
                    });
                    c.setText(getString(R.string.alert_dialog_buy_balance_insufficient));
                    c2.setVisibility(8);
                }
            }
        } catch (Exception e) {
            com.qq.reader.common.monitor.debug.c.e("Error", e.getMessage());
        }
    }

    private void p() {
        try {
            if (this.X != null && this.O != null && this.O.g() - 1 < this.X.size()) {
                OnlineChapter onlineChapter = (OnlineChapter) this.X.get(this.O.g() - 1);
                int price = (int) ((onlineChapter.getPrice() * ((float) this.U.s())) / 100.0f);
                com.qq.reader.common.monitor.debug.c.e("PlayerActivity", "购买单章价格:" + price);
                List arrayList = new ArrayList();
                arrayList.add(Integer.valueOf(onlineChapter.getChapterId()));
                com.qq.reader.cservice.buy.chapter.c cVar = new com.qq.reader.cservice.buy.chapter.c(this.O, arrayList, price, this);
                cVar.a(new com.qq.reader.cservice.buy.chapter.b(this) {
                    final /* synthetic */ NativeTTSPlayerActivity a;

                    {
                        this.a = r1;
                    }

                    public void a(ChapterPayResult chapterPayResult) {
                        com.qq.reader.common.monitor.debug.c.e("PlayerActivity", "购买单章成功");
                        Message obtain = Message.obtain();
                        obtain.what = 1238;
                        obtain.obj = chapterPayResult;
                        this.a.mHandler.sendMessage(obtain);
                        try {
                            com.qq.reader.common.utils.ao.b(this.a.O);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    public void b(ChapterPayResult chapterPayResult) {
                        com.qq.reader.common.monitor.debug.c.e("PlayerActivity", "购买单章失败");
                        Message obtain = Message.obtain();
                        obtain.what = 1239;
                        obtain.obj = chapterPayResult;
                        this.a.mHandler.sendMessage(obtain);
                    }

                    public void c(ChapterPayResult chapterPayResult) {
                        Bundle bundle = new Bundle();
                        bundle.putString("message", chapterPayResult.getResultStr());
                        bundle.putSerializable("com.qq.reader.pay.ChapterPayResult", chapterPayResult);
                    }
                });
                cVar.start();
                s();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void d() {
        new JSPay(this).startCharge(this, this.i);
    }

    public void b(com.qq.reader.cservice.buy.a.c cVar) {
        Message obtain = Message.obtain();
        obtain.what = 1218;
        obtain.obj = cVar;
        this.mHandler.sendMessage(obtain);
    }

    public void c(com.qq.reader.cservice.buy.a.c cVar) {
        Message obtain = Message.obtain();
        obtain.what = 1219;
        obtain.obj = cVar;
        this.mHandler.sendMessage(obtain);
    }

    private void q() {
        try {
            if (this.ai == null || !this.ai.isShowing()) {
                this.ai = ProgressDialog.show(this, "", "正在加载...", true);
                this.ai.setCancelable(true);
                this.mHandler.sendEmptyMessageDelayed(21103, 30000);
                this.ai.setCanceledOnTouchOutside(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean r() {
        try {
            if (this.ai != null && this.ai.isShowing()) {
                this.ai.cancel();
                this.mHandler.removeMessages(21103);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private void s() {
        try {
            if (this.ah == null || !this.ah.isShowing()) {
                this.ah = ProgressDialog.show(this, "", "正在购买，请稍候...", true);
                this.ah.setCanceledOnTouchOutside(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean t() {
        try {
            if (this.ah != null && this.ah.isShowing()) {
                this.ah.cancel();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void e() {
        int a = this.t.a();
        if (a > 0) {
            if (this.i <= 0 || a >= this.i) {
                String k = this.O.k();
                if (this.k != 1001) {
                    p();
                } else if (!TextUtils.isEmpty(k)) {
                    a(k, this.j);
                }
            }
        }
    }

    private void u() {
        this.n = new g(getApplicationContext(), this.O);
        this.n.a(getHandler());
        this.n.a(true);
    }

    private void v() {
        if (this.n != null) {
            this.n.d();
            this.n = null;
        }
    }

    private void b(final String str) {
        int i = -1;
        if (this.n != null) {
            i = this.n.b();
        }
        if (i == 2) {
            ReaderTask queryChapterBuyInfoTask = new QueryChapterBuyInfoTask(str);
            queryChapterBuyInfoTask.registerNetTaskListener(new com.qq.reader.common.readertask.ordinal.c(this) {
                final /* synthetic */ NativeTTSPlayerActivity b;

                public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                    this.b.a(str, str, true);
                    this.b.m = str;
                    this.b.l = true;
                }

                public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                }
            });
            com.qq.reader.common.readertask.g.a().a(queryChapterBuyInfoTask);
        } else if (i == 1) {
            com.qq.reader.common.readertask.g.a().a(new AuthCheckTask(Long.parseLong(this.O.k()), new com.qq.reader.common.readertask.ordinal.c(this) {
                final /* synthetic */ NativeTTSPlayerActivity b;

                public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                    this.b.a(str, str, false);
                    this.b.m = str;
                    this.b.l = true;
                }

                public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                    this.b.mHandler.sendEmptyMessage(10000505);
                }
            }));
        }
    }

    private void a(String str, String str2, boolean z) {
        Message obtain;
        if (z) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.optInt("retCode") == 0) {
                    List a = com.qq.reader.common.utils.ao.a(jSONObject.optString("cids"));
                    if (a != null) {
                        k.a(getApplicationContext()).a(str2, a);
                        a = k.a(getApplicationContext()).a(str2);
                        obtain = Message.obtain();
                        obtain.what = 21011;
                        obtain.obj = a;
                        this.mHandler.sendMessage(obtain);
                    }
                }
            } catch (Exception e) {
                com.qq.reader.common.monitor.debug.c.e("Err", e.getMessage());
            }
        } else if (new JSONObject(str).optInt("code") == 1) {
            ArrayList arrayList = new ArrayList();
            obtain = Message.obtain();
            obtain.what = 21101;
            obtain.obj = arrayList;
            this.mHandler.sendMessage(obtain);
        }
    }
}
