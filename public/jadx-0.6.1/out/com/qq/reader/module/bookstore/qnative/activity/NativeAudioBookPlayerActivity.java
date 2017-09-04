package com.qq.reader.module.bookstore.qnative.activity;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.StrikethroughSpan;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import com.pay.http.APPluginErrorCode;
import com.qq.reader.ReaderApplication;
import com.qq.reader.activity.AudioBookDownloadActivity;
import com.qq.reader.activity.ReaderBaseActivity;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.db.handle.k;
import com.qq.reader.common.db.handle.r;
import com.qq.reader.common.db.handle.v;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.readertask.ReaderTask;
import com.qq.reader.common.readertask.ordinal.ReaderDBTask;
import com.qq.reader.common.readertask.ordinal.ReaderDownloadTask;
import com.qq.reader.common.readertask.ordinal.ReaderIOTask;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.ordinal.ReaderShortTask;
import com.qq.reader.common.readertask.protocol.ProfileNetTask;
import com.qq.reader.common.readertask.protocol.QueryUserBalanceTask;
import com.qq.reader.common.utils.o;
import com.qq.reader.common.utils.q;
import com.qq.reader.common.web.js.JSPay;
import com.qq.reader.cservice.buy.a.b;
import com.qq.reader.cservice.buy.chapter.ChapterPayResult;
import com.qq.reader.cservice.download.audio.AudioAuthCheckTask;
import com.qq.reader.cservice.onlineread.OnlineTag;
import com.qq.reader.framework.mark.Mark;
import com.qq.reader.module.audio.loader.QueryAudioChapterBuyInfoTask;
import com.qq.reader.module.bookchapter.ChapterAdapterItem;
import com.qq.reader.module.bookchapter.online.OnlineChapter;
import com.qq.reader.module.bookchapter.online.f;
import com.qq.reader.module.bookchapter.online.g;
import com.qq.reader.module.bookchapter.online.h;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.plugin.PlugInDefaultActivity;
import com.qq.reader.plugin.audiobook.core.SongInfo;
import com.qq.reader.plugin.audiobook.core.e;
import com.qq.reader.plugin.audiobook.core.l;
import com.qq.reader.plugin.audiobook.core.m;
import com.qq.reader.plugin.tts.n;
import com.qq.reader.qplugin.local.TingBookMark;
import com.qq.reader.readengine.model.BookTing;
import com.qq.reader.readengine.model.IBook;
import com.qq.reader.view.AlertDialog;
import com.qq.reader.view.EmptyView;
import com.qq.reader.view.af;
import com.qq.reader.view.ao;
import com.qq.reader.view.aq;
import com.qq.reader.view.c;
import com.tencent.android.tpush.common.Constants;
import com.tencent.feedback.proguard.R;
import com.tencent.upload.log.trace.TracerConfig;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class NativeAudioBookPlayerActivity extends ReaderBaseActivity implements b {
    private OnlineTag A;
    private final com.qq.reader.common.charge.voucher.a.a B = new com.qq.reader.common.charge.voucher.a.a();
    private ImageView C;
    private TextView D;
    private TextView E;
    private TextView F;
    private TextView G;
    private ImageView H;
    private ImageView I;
    private ImageView J;
    private c K;
    private ao L;
    private View M;
    private boolean N = true;
    private TextView O;
    private TextView P;
    private int Q = 0;
    private View R;
    private int S = 0;
    private final int T = 1;
    private final int U = 2;
    private final int V = 3;
    private final int W = 4;
    private Mark X = null;
    private boolean Y = false;
    private View Z;
    ImageView a;
    private boolean aa = false;
    private BroadcastReceiver ab = new BroadcastReceiver(this) {
        final /* synthetic */ NativeAudioBookPlayerActivity a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            if (l.a != null) {
                try {
                    String action = intent.getAction();
                    if (action.equals(e.h)) {
                        this.a.q();
                        this.a.a(1);
                    } else if (action.equals(e.j)) {
                        this.a.q();
                    } else if (action.equals(e.l)) {
                        this.a.q();
                    } else if (action.equals(e.t)) {
                        af.a(this.a, (CharSequence) "网络连接失败", 0).a();
                    } else if (action.equals(com.qq.reader.common.c.a.cJ)) {
                        this.a.showFragmentDialog(611);
                    } else if (action.equals(e.n)) {
                        SongInfo o = l.a.o();
                        if (o != null && this.a.ah != null) {
                            OnlineChapter onlineChapter = o.a;
                            int i = -1;
                            int j = this.a.ah.j();
                            if (j == 2) {
                                i = 1001;
                            } else if (j == 3) {
                                i = 1000;
                            }
                            Message obtain = Message.obtain();
                            obtain.what = 10000506;
                            obtain.arg1 = i;
                            obtain.obj = onlineChapter;
                            this.a.mHandler.sendMessage(obtain);
                        }
                    } else if (action.equals(e.r)) {
                        this.a.mHandler.sendEmptyMessage(10000512);
                    } else if (action.equalsIgnoreCase(e.s)) {
                        this.a.b(this.a.ac.k());
                    } else if (action.equals(e.q)) {
                        af.a(this.a, this.a.getString(R.string.audio_check_level_limit), 0).a();
                    } else if (action.equals(e.p)) {
                        af.a(this.a, this.a.getString(R.string.audio_off_market_tip), 0).a();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    };
    private OnlineTag ac;
    private IBook ad;
    private com.qq.reader.module.readpage.c ae;
    private g af;
    private boolean ag = false;
    private f ah;
    private EmptyView ai;
    private View aj;
    private List<OnlineChapter> ak = new ArrayList();
    private boolean al = false;
    private int am = -1;
    private ListView an;
    private h ao;
    private aq ap;
    private String aq = "";
    private OnItemClickListener ar = new OnItemClickListener(this) {
        final /* synthetic */ NativeAudioBookPlayerActivity a;

        {
            this.a = r1;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            if (view instanceof ChapterAdapterItem) {
                OnlineChapter onlineChapter = (OnlineChapter) this.a.ao.getItem(i);
                this.a.ac.b(onlineChapter.getChapterName());
                this.a.ac.a(0);
                if (onlineChapter.getChapterId() != this.a.ac.g() || this.a.R.getVisibility() == 0) {
                    com.qq.reader.common.utils.ao.a(2, Long.parseLong(this.a.ac.k()), false, this.a.ac.b());
                    this.a.r();
                    this.a.ac.c(onlineChapter.getChapterId());
                    this.a.a(this.a.ac);
                    this.a.a(this.a.ac.g() - 1);
                }
                if (this.a.R != null && this.a.R.getVisibility() == 0) {
                    this.a.R.setVisibility(8);
                    this.a.q();
                }
            }
        }
    };
    private long as = 0;
    private ao.b at = new ao.b(this) {
        final /* synthetic */ NativeAudioBookPlayerActivity a;

        {
            this.a = r1;
        }

        public void a(long j) {
            if (j == 0) {
                this.a.D.setText("定时");
            } else {
                this.a.D.setText(com.qq.reader.module.c.a.a(j));
            }
        }

        public void a() {
            this.a.q();
            com.qq.reader.module.c.a.a(2);
            this.a.D.setText("定时");
            if (l.a != null) {
                try {
                    l.a.c();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }

        public void b() {
        }

        public void c() {
            this.a.D.setText("定时");
        }
    };
    private OnSeekBarChangeListener au = new OnSeekBarChangeListener(this) {
        final /* synthetic */ NativeAudioBookPlayerActivity a;

        {
            this.a = r1;
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
            this.a.x = true;
            i.a("event_C181", null, this.a.getContext());
        }

        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            if (this.a.v > 0 && this.a.x) {
                this.a.s.setText(m.a(((this.a.v * ((long) i)) / TracerConfig.LOG_FLUSH_DURATION) / 1000));
            }
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
            if (l.a != null) {
                try {
                    if (this.a.x) {
                        l.a.a((((long) seekBar.getProgress()) * this.a.v) / TracerConfig.LOG_FLUSH_DURATION);
                    }
                } catch (RemoteException e) {
                }
                this.a.x = false;
            }
        }
    };
    private Runnable av = new Runnable(this) {
        final /* synthetic */ NativeAudioBookPlayerActivity a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.mHandler.postDelayed(this, this.a.s());
        }
    };
    private ProgressDialog aw;
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
    int l;
    long m;
    int n;
    int o;
    private boolean p = false;
    private String q;
    private final String r = getClass().getSimpleName();
    private TextView s;
    private TextView t;
    private TextView u;
    private long v;
    private SeekBar w;
    private boolean x = false;
    private String y;
    private String z;

    private class a {
        AlertDialog a;
        Bundle b;
        final /* synthetic */ NativeAudioBookPlayerActivity c;

        private a(NativeAudioBookPlayerActivity nativeAudioBookPlayerActivity) {
            this.c = nativeAudioBookPlayerActivity;
        }
    }

    private aq e() {
        Mark b;
        if (this.ap == null) {
            this.ap = new aq(this, 13, (int) getResources().getDimension(R.dimen.book_shelf_top_bar_width), 0);
            this.ap.a(getApplicationContext().getString(R.string.audio_player_top_menu_auto_buy), R.drawable.readpage_topbar_icon_auto_buy, R.drawable.readpage_topbar_icon_auto_buy_night, 1, this.ac.x());
            b = com.qq.reader.common.db.handle.i.c().b(this.ac.k(), true);
            if (b != null) {
                if (b.getPrivateProperty() == 1) {
                    this.ap.a(getString(R.string.audio_player_top_menu_read_private), R.drawable.readpage_topbar_read_private, R.drawable.readpage_topbar_read_private_night, 2, false);
                } else if (b.getPrivateProperty() == 0) {
                    this.ap.a(getString(R.string.audio_player_top_menu_read_private), R.drawable.readpage_topbar_read_private, R.drawable.readpage_topbar_read_private_night, 2, true);
                }
            }
            this.ap.a(getString(R.string.audio_player_top_menu_audio_detail), R.drawable.readpage_topbar_detail, R.drawable.readpage_topbar_detail_night, 3, false);
            this.ap.a(getString(R.string.audio_player_top_menu_share), R.drawable.readpage_topbar_share, R.drawable.readpage_topbar_share_night, 4, false);
            this.ap.a(new com.qq.reader.view.a.a(this) {
                final /* synthetic */ NativeAudioBookPlayerActivity a;

                {
                    this.a = r1;
                }

                public boolean a(int i) {
                    switch (i) {
                        case 1:
                            i.a("event_B278", null, ReaderApplication.getApplicationImp());
                            if (!this.a.ac.x()) {
                                this.a.ac.c(true);
                                af.a(this.a, this.a.getString(R.string.dialog_open_autopay), 0).a();
                            } else if (d.ce(this.a)) {
                                this.a.ac.c(false);
                                af.a(this.a, this.a.getString(R.string.dialog_shut_down_autopay), 0).a();
                            } else {
                                this.a.showFragmentDialog(308);
                                d.O(this.a, true);
                            }
                            d.z(this.a, this.a.ac.x());
                            this.a.ap.b(this.a.getApplicationContext().getString(R.string.audio_player_top_menu_auto_buy), R.drawable.readpage_topbar_icon_auto_buy, R.drawable.readpage_topbar_icon_auto_buy, 1, this.a.ac.x());
                            return true;
                        case 2:
                            i.a("event_B278", null, ReaderApplication.getApplicationImp());
                            this.a.f();
                            return true;
                        case 3:
                            i.a("event_B280", null, ReaderApplication.getApplicationImp());
                            this.a.finish();
                            o.b(this.a, this.a.ac.k(), null, null, null);
                            return true;
                        case 4:
                            i.a("event_B281", null, ReaderApplication.getApplicationImp());
                            this.a.getShareDialog().a(this.a.ac.k(), this.a.ac.b());
                            this.a.getShareDialog().a(true);
                            this.a.getShareDialog().f_();
                            return true;
                        default:
                            return false;
                    }
                }
            });
        }
        if (this.ac != null) {
            this.ap.b(getApplicationContext().getString(R.string.audio_player_top_menu_auto_buy), R.drawable.readpage_topbar_icon_auto_buy, R.drawable.readpage_topbar_icon_auto_buy, 1, this.ac.x());
            b = com.qq.reader.common.db.handle.i.c().b(this.ac.k(), true);
            if (b != null) {
                this.ap.b(2);
                if (b.getPrivateProperty() == 1) {
                    this.ap.a(getString(R.string.audio_player_top_menu_read_private), R.drawable.readpage_topbar_read_private, R.drawable.readpage_topbar_read_private_night, 2, false);
                } else if (b.getPrivateProperty() == 0) {
                    this.ap.a(getString(R.string.audio_player_top_menu_read_private), R.drawable.readpage_topbar_read_private, R.drawable.readpage_topbar_read_private_night, 2, true);
                }
            }
        }
        return this.ap;
    }

    public boolean needSetImmerseMode() {
        return !this.Y;
    }

    private void f() {
        if (this.X == null) {
            this.X = com.qq.reader.common.db.handle.i.c().b(this.ac.k(), true);
        }
        if (this.X != null) {
            Object hashMap = new HashMap();
            if (this.X.getPrivateProperty() == 1) {
                com.qq.reader.module.bookshelf.d.b((ReaderBaseActivity) this, Long.parseLong(this.ac.k()), new com.qq.reader.common.readertask.ordinal.c(this) {
                    final /* synthetic */ NativeAudioBookPlayerActivity a;

                    {
                        this.a = r1;
                    }

                    public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                        if (this.a.X != null) {
                            this.a.X.setPrivateProperty(0);
                        }
                        com.qq.reader.common.readertask.g.a().a(new AnonymousClass1(this));
                        this.a.runOnUiThread(new Runnable(this) {
                            final /* synthetic */ AnonymousClass34 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                af.a(this.a.a, (CharSequence) "已开启私密阅读", 0).a();
                            }
                        });
                    }

                    public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                        this.a.mHandler.post(new Runnable(this) {
                            final /* synthetic */ AnonymousClass34 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                af.a(this.a.a.getApplicationContext(), (int) R.string.pulldownview_failed, 0).a();
                            }
                        });
                    }
                }, new Runnable(this) {
                    final /* synthetic */ NativeAudioBookPlayerActivity a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                    }
                });
                hashMap.put("readPrivate", "1");
            } else {
                com.qq.reader.module.bookshelf.d.a((ReaderBaseActivity) this, Long.parseLong(this.ac.k()), new com.qq.reader.common.readertask.ordinal.c(this) {
                    final /* synthetic */ NativeAudioBookPlayerActivity a;

                    {
                        this.a = r1;
                    }

                    public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                        if (this.a.X != null) {
                            this.a.X.setPrivateProperty(1);
                        }
                        com.qq.reader.common.readertask.g.a().a(new AnonymousClass1(this));
                        this.a.runOnUiThread(new Runnable(this) {
                            final /* synthetic */ AnonymousClass56 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                af.a(this.a.a, (CharSequence) "已关闭私密阅读", 0).a();
                            }
                        });
                    }

                    public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                        af.a(this.a.getApplicationContext(), (int) R.string.pulldownview_failed, 0).a();
                    }
                }, new Runnable(this) {
                    final /* synthetic */ NativeAudioBookPlayerActivity a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                    }
                });
                hashMap.put("readPrivate", "0");
            }
            i.a("event_B184", hashMap, this);
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.audio_book_player_layout);
        this.R = findViewById(R.id.gift_tip_layout);
        TextView textView = (TextView) findViewById(R.id.profile_header_title);
        textView.setTextColor(getResources().getColor(R.color.text_color_c101));
        textView.setTextSize(1, 18.0f);
        ImageView imageView = (ImageView) findViewById(R.id.profile_header_left_back);
        imageView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NativeAudioBookPlayerActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.g();
            }
        });
        imageView.setImageResource(R.drawable.titlebar_icon_back_selector_black_down);
        findViewById(R.id.common_titler).setBackgroundColor(-1);
        String str = Build.BRAND;
        if (TextUtils.isEmpty(str)) {
            this.Y = true;
        } else {
            if (str.toUpperCase().contains("XIAOMI")) {
                com.qq.reader.common.utils.aj.a.a((Activity) this, true);
            } else if (str.toUpperCase().contains("MEIZU")) {
                com.qq.reader.common.utils.aj.a.a(getWindow(), true);
            }
            this.Y = false;
        }
        ImageButton imageButton = (ImageButton) findViewById(R.id.profile_header_right_collect);
        imageButton.setVisibility(8);
        imageButton.setImageResource(R.drawable.audio_player_tts_icon);
        imageButton.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NativeAudioBookPlayerActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
            }
        });
        this.M = findViewById(R.id.online_chapter_list);
        this.d = (ImageButton) findViewById(R.id.profile_header_right_image);
        this.d.setImageResource(R.drawable.audio_player_more_icon);
        this.d.setVisibility(0);
        this.d.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NativeAudioBookPlayerActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                i.a("event_B276", null, ReaderApplication.getApplicationImp());
                this.a.e().f_();
            }
        });
        d.z((Context) this, false);
        this.H = (ImageView) findViewById(R.id.play_pause_btn);
        this.Z = findViewById(R.id.layout_off_market);
        findViewById(R.id.chapter_loading).setVisibility(8);
        this.O = (TextView) findViewById(R.id.tv_action);
        this.F = (TextView) findViewById(R.id.tv_price);
        this.G = (TextView) findViewById(R.id.tv_show_price);
        this.P = (TextView) findViewById(R.id.tv_bookname);
        this.a = (ImageView) findViewById(R.id.img_cover);
        this.E = (TextView) findViewById(R.id.add_bookshell_btn);
        this.E.setOnClickListener(new com.qq.reader.module.bookstore.qnative.c.c(this) {
            final /* synthetic */ NativeAudioBookPlayerActivity a;

            {
                this.a = r1;
            }

            public void a(View view) {
                this.a.l();
            }
        });
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(e.j);
        intentFilter.addAction(e.h);
        intentFilter.addAction(e.l);
        intentFilter.addAction(e.n);
        intentFilter.addAction(e.r);
        intentFilter.addAction(e.t);
        intentFilter.addAction(e.s);
        intentFilter.addAction(e.p);
        intentFilter.addAction(e.q);
        intentFilter.addAction(com.qq.reader.common.c.a.cJ);
        registerReceiver(this.ab, new IntentFilter(intentFilter));
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            CharSequence string = extras.getString("bookname");
            this.S = extras.getInt("from", 0);
            this.aq = extras.getString(s.STATPARAM_KEY);
            if (TextUtils.isEmpty(string) || this.S == 1) {
                textView.setText("高品质听书");
            } else {
                textView.setText(string);
            }
            Map hashMap = new HashMap();
            hashMap.put(s.ORIGIN, String.valueOf(this.S));
            i.a("event_B263", hashMap, getContext());
            this.z = extras.getString("AUDIO_PLAY_RELATED_BOOK_BID");
            this.A = (OnlineTag) extras.getParcelable("related_onlinetag");
            String string2 = extras.getString("filepath");
            if (TextUtils.isEmpty(string2)) {
                com.qq.reader.common.monitor.debug.c.e("PlayerActivity", "bid error : " + string2);
                finish();
                return;
            }
            if (this.S == 1) {
                i.a("event_B275", null, ReaderApplication.getApplicationImp());
                imageButton.setVisibility(0);
                imageButton.setOnClickListener(new com.qq.reader.module.bookstore.qnative.c.c(this) {
                    final /* synthetic */ NativeAudioBookPlayerActivity a;

                    class AnonymousClass1 extends ReaderDBTask {
                        final /* synthetic */ AnonymousClass34 this$1;

                        AnonymousClass1(AnonymousClass34 anonymousClass34) {
                            this.this$1 = anonymousClass34;
                        }

                        public void run() {
                            com.qq.reader.common.db.handle.i.c().b(this.this$1.a.ac.k() + "", 0);
                        }
                    }

                    {
                        this.a = r1;
                    }

                    public void a(View view) {
                        try {
                            if (!TextUtils.isEmpty(this.a.z)) {
                                this.a.mUseAnimation = false;
                                this.a.overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_bottom);
                                if (this.a.B()) {
                                    n.f().a(this.a);
                                } else {
                                    n.f().a(this.a);
                                }
                                this.a.C();
                                if (n.f().q()) {
                                    if (this.a.A == null || this.a.ac == null) {
                                        o.d(this.a, this.a.z, this.a.ac.g(), null);
                                    } else {
                                        this.a.A.g(this.a.am);
                                        this.a.A.c(this.a.am);
                                        o.a(this.a, this.a.A, null);
                                    }
                                    l.a.b();
                                    this.a.finish();
                                    this.a.overridePendingTransition(0, R.anim.slide_out_bottom);
                                    i.a("event_B276", null, ReaderApplication.getApplicationImp());
                                    return;
                                }
                                n.f().a(this.a.mHandler);
                                n.f().b();
                            }
                        } catch (Exception e) {
                            com.qq.reader.common.monitor.debug.c.e("Error", e.getMessage());
                        }
                    }
                });
                this.Q = 1;
            }
            this.ac = (OnlineTag) extras.getParcelable("com.qq.reader.OnlineTag");
            if (string2 != null) {
                boolean z;
                Mark e;
                long j;
                if (this.ac == null) {
                    this.ac = v.b().a(string2);
                    if (this.ac == null) {
                        this.ac = new OnlineTag(string2, "", System.currentTimeMillis());
                        this.ac.c(1);
                        this.ac.j(2);
                        this.ac.k("mp3");
                        z = true;
                        e = com.qq.reader.common.db.handle.i.c().e(string2);
                        this.X = com.qq.reader.common.db.handle.i.c().b(string2, true);
                        if (e != null) {
                            this.E.setAlpha(0.5f);
                            this.E.setEnabled(false);
                            this.E.setText("已在书架");
                        }
                        this.ac.j(2);
                        this.am = extras.getInt("book_chapterid", -1);
                        if (this.am != -1) {
                            this.ac.c(this.am);
                        }
                        j = 0;
                        j = Long.parseLong(string2);
                        this.ad = new BookTing(j, this.ac.a(this.ac.g()));
                        b(this.ac);
                        if (z) {
                            a("正在拉取书籍信息...");
                        }
                        h();
                        this.ai = (EmptyView) findViewById(R.id.online_chapter_empyt_layout);
                        this.ai.setOnClickListener(new OnClickListener(this) {
                            final /* synthetic */ NativeAudioBookPlayerActivity a;

                            {
                                this.a = r1;
                            }

                            public void onClick(View view) {
                                this.a.ai.setVisibility(8);
                                this.a.aj.setVisibility(0);
                                this.a.z();
                            }
                        });
                        this.an = (ListView) findViewById(R.id.online_chapter_list);
                        this.ao = new h();
                        this.an.setAdapter(this.ao);
                        this.an.setVisibility(8);
                        this.an.setOnItemClickListener(this.ar);
                        this.aj = findViewById(R.id.chapter_loading);
                        this.aj.setVisibility(0);
                    }
                }
                z = false;
                e = com.qq.reader.common.db.handle.i.c().e(string2);
                this.X = com.qq.reader.common.db.handle.i.c().b(string2, true);
                if (e != null) {
                    this.E.setAlpha(0.5f);
                    this.E.setEnabled(false);
                    this.E.setText("已在书架");
                }
                this.ac.j(2);
                this.am = extras.getInt("book_chapterid", -1);
                if (this.am != -1) {
                    this.ac.c(this.am);
                }
                j = 0;
                try {
                    j = Long.parseLong(string2);
                } catch (NumberFormatException e2) {
                    e2.printStackTrace();
                }
                this.ad = new BookTing(j, this.ac.a(this.ac.g()));
                b(this.ac);
                if (z) {
                    a("正在拉取书籍信息...");
                }
                h();
                this.ai = (EmptyView) findViewById(R.id.online_chapter_empyt_layout);
                this.ai.setOnClickListener(/* anonymous class already generated */);
                this.an = (ListView) findViewById(R.id.online_chapter_list);
                this.ao = new h();
                this.an.setAdapter(this.ao);
                this.an.setVisibility(8);
                this.an.setOnItemClickListener(this.ar);
                this.aj = findViewById(R.id.chapter_loading);
                this.aj.setVisibility(0);
            }
            this.ae = new com.qq.reader.module.readpage.c(getApplicationContext(), this.mHandler, 2);
        }
        this.u = (TextView) findViewById(R.id.tv_download_voucher_tips);
        com.qq.reader.common.readertask.g.a().a(new ReaderIOTask() {
            public void run() {
                super.run();
                if (NativeAudioBookPlayerActivity.this.ac != null) {
                    NativeAudioBookPlayerActivity.this.b(NativeAudioBookPlayerActivity.this.ac.k());
                }
            }
        });
        Map hashMap2 = new HashMap();
        hashMap2.put(s.ORIGIN, this.S == 1 ? "1" : "0");
        i.a("event_B273", hashMap2, ReaderApplication.getApplicationImp());
    }

    private void g() {
        boolean z;
        if (this.ah == null || this.ah.y() == null) {
            z = false;
        } else {
            z = com.qq.reader.common.utils.ao.a(this.ah.y().U(), this.ah.y().V());
        }
        if (com.qq.reader.common.db.handle.i.c().e(this.ac.k()) != null || r0) {
            if (this.af != null) {
                this.af = null;
            }
            i.a("event_C189", null, getContext());
            this.mUseAnimation = false;
            finish();
            overridePendingTransition(0, R.anim.slide_out_bottom);
            return;
        }
        showFragmentDialog(304);
    }

    protected void onResume() {
        super.onResume();
        if (this.ae != null) {
            this.ae.a();
        }
    }

    protected void onPause() {
        super.onPause();
        a(false);
    }

    protected void onDestroy() {
        super.onDestroy();
        this.mHandler.removeCallbacks(this.av);
        unregisterReceiver(this.ab);
        try {
            l.b(this);
            A();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.at = null;
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        switch (i) {
            case 4:
                g();
                return true;
            default:
                return super.onKeyDown(i, keyEvent);
        }
    }

    private void h() {
        long j = 0;
        try {
            j = Long.parseLong(this.ac.k());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        com.qq.reader.common.imageloader.c.a(getContext()).a(com.qq.reader.common.utils.ao.a(j, true, 180), this.a, com.qq.reader.common.imageloader.a.a().j());
    }

    private SongInfo[] i() {
        try {
            if (this.ak == null || this.ak.size() == 0) {
                return null;
            }
            String a = com.qq.reader.common.login.c.c().a((Context) this);
            List arrayList = new ArrayList();
            for (int i = 0; i < this.ak.size(); i++) {
                OnlineChapter onlineChapter = (OnlineChapter) this.ak.get(i);
                SongInfo songInfo = new SongInfo(this.ac.a(com.qq.reader.appconfig.c.r, a) + onlineChapter.getUUID() + "&checkFree=" + this.Q, onlineChapter.getUUID());
                songInfo.b(onlineChapter.getChapterId());
                songInfo.c(onlineChapter.getBookId());
                songInfo.a = onlineChapter;
                songInfo.a.setBookName(onlineChapter.getBookName());
                songInfo.c = true;
                if (!(this.ah == null || this.ah.y() == null || this.ah.y().Q() != 2000000804)) {
                    songInfo.c = false;
                }
                songInfo.b = this.aq;
                songInfo.a(onlineChapter.getChapterName());
                arrayList.add(songInfo);
            }
            SongInfo[] songInfoArr = new SongInfo[arrayList.size()];
            arrayList.toArray(songInfoArr);
            return songInfoArr;
        } catch (Exception e) {
            return null;
        }
    }

    private void j() {
        k();
        this.b = (TextView) findViewById(R.id.chapter_name);
        this.c = (TextView) findViewById(R.id.author_info);
        this.R = findViewById(R.id.gift_tip_layout);
        this.s = (TextView) findViewById(R.id.current_time);
        this.t = (TextView) findViewById(R.id.total_time);
        this.w = (SeekBar) findViewById(R.id.progressSeekBar);
        this.w.setOnSeekBarChangeListener(this.au);
        this.w.setMax(Constants.ERRORCODE_UNKNOWN);
        this.D = (TextView) findViewById(R.id.tv_timer);
        this.H.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NativeAudioBookPlayerActivity a;

            class AnonymousClass1 extends ReaderDBTask {
                final /* synthetic */ AnonymousClass56 this$1;

                AnonymousClass1(AnonymousClass56 anonymousClass56) {
                    this.this$1 = anonymousClass56;
                }

                public void run() {
                    com.qq.reader.common.db.handle.i.c().b(this.this$1.a.ac.k() + "", 1);
                }
            }

            {
                this.a = r1;
            }

            public void onClick(View view) {
                try {
                    if (l.a != null) {
                        i.a("event_C180", null, this.a.getContext());
                        if (l.a.a() || l.a.k() == 4 || l.a.k() == 5) {
                            l.a.c();
                            com.qq.reader.plugin.audiobook.core.b.a = 0;
                        } else {
                            int k = l.a.k();
                            if (k == 1 || k == 6) {
                                l.a.g();
                            } else {
                                l.a.d();
                            }
                        }
                        this.a.q();
                    }
                } catch (RemoteException e) {
                }
            }
        });
        this.J = (ImageView) findViewById(R.id.next_btn);
        this.J.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NativeAudioBookPlayerActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (!this.a.a() && l.a != null) {
                    i.a("event_C183", null, this.a.getContext());
                    try {
                        l.a.f();
                    } catch (RemoteException e) {
                    }
                }
            }
        });
        u();
        this.I = (ImageView) findViewById(R.id.prev_btn);
        this.I.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NativeAudioBookPlayerActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (!this.a.a() && l.a != null) {
                    i.a("event_C182", null, this.a.getContext());
                    try {
                        l.a.e();
                    } catch (RemoteException e) {
                    }
                }
            }
        });
        this.C = (ImageView) findViewById(R.id.timer_btn);
        q b = com.qq.reader.module.c.a.b(1);
        if (b != null && b.a()) {
            this.D.setText(com.qq.reader.module.c.a.a(b.b()));
            b.a(this.at);
        }
        findViewById(R.id.layout_timer);
        this.C.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NativeAudioBookPlayerActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.n();
                i.a("event_B186", null, this.a);
            }
        });
        findViewById(R.id.layout_download).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NativeAudioBookPlayerActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.o();
            }
        });
    }

    public synchronized boolean a() {
        boolean z;
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.as < 1500) {
            z = true;
        } else {
            this.as = currentTimeMillis;
            z = false;
        }
        return z;
    }

    private void k() {
        if (!(this.ac == null || this.P == null || TextUtils.isEmpty(this.ac.b()))) {
            this.P.setText(this.ac.b());
        }
        if (this.ah != null) {
            com.qq.reader.module.bookchapter.online.d y = this.ah.y();
            if (y != null) {
                this.F.setVisibility(0);
                this.G.setVisibility(0);
                CharSequence R = y.R();
                CharSequence S = y.S();
                CharSequence T = y.T();
                CharSequence e = y.e();
                MarginLayoutParams marginLayoutParams = (MarginLayoutParams) this.G.getLayoutParams();
                int i = marginLayoutParams.leftMargin;
                if (i != 0) {
                    this.G.setTag(R.string.obj_tag, Integer.valueOf(i));
                }
                if (TextUtils.isEmpty(R)) {
                    this.F.setText("");
                    marginLayoutParams.leftMargin = 0;
                    this.G.setLayoutParams(marginLayoutParams);
                } else {
                    this.F.setText(R);
                    this.F.getPaint().setFlags(16);
                    if ((this.G.getTag(R.string.obj_tag) instanceof Integer) && ((Integer) this.G.getTag(R.string.obj_tag)).intValue() != i) {
                        marginLayoutParams.leftMargin = i;
                        this.G.setLayoutParams(marginLayoutParams);
                    }
                }
                if (S != null) {
                    this.G.setText(S);
                }
                if (T != null) {
                    this.O.setText(T);
                }
                if (!TextUtils.isEmpty(e)) {
                    this.P.setText(e);
                }
            }
            if (this.ah.y().q()) {
                this.O.setTextColor(getResources().getColorStateList(R.color.localstore_textcolor_detail_orange_selector));
                this.O.setVisibility(0);
                i.a("event_A192", null, getContext());
                this.O.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ NativeAudioBookPlayerActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        if (com.qq.reader.common.login.c.b()) {
                            new JSPay(this.a).openVip();
                            i.a("event_A191", null, this.a.getContext());
                            return;
                        }
                        this.a.mLoginNextTask = new com.qq.reader.common.login.a(this) {
                            final /* synthetic */ AnonymousClass12 a;

                            {
                                this.a = r1;
                            }

                            public void a(int i) {
                                switch (i) {
                                    case 1:
                                        com.qq.reader.common.readertask.g.a().a(new ProfileNetTask(new com.qq.reader.common.readertask.ordinal.c(this) {
                                            final /* synthetic */ AnonymousClass1 a;

                                            {
                                                this.a = r1;
                                            }

                                            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                                                try {
                                                    JSONObject jSONObject = new JSONObject(str);
                                                    if (jSONObject != null) {
                                                        com.qq.reader.common.login.b.a.a(com.qq.reader.common.login.c.c(), jSONObject);
                                                        new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                                                            final /* synthetic */ AnonymousClass1 a;

                                                            {
                                                                this.a = r1;
                                                            }

                                                            public void run() {
                                                                com.qq.reader.common.login.b.a c = com.qq.reader.common.login.c.c();
                                                                if (c != null) {
                                                                    if (c.j(this.a.a.a.a)) {
                                                                        this.a.a.a.a.b(this.a.a.a.a.ac);
                                                                    } else {
                                                                        new JSPay(this.a.a.a.a).openVip();
                                                                    }
                                                                }
                                                            }
                                                        });
                                                    }
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                            }

                                            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                                            }
                                        }));
                                        return;
                                    default:
                                        return;
                                }
                            }
                        };
                        this.a.startLogin();
                    }
                });
                return;
            }
            this.O.setTextColor(getResources().getColorStateList(R.color.text_color_c102));
        }
    }

    private void l() {
        if (com.qq.reader.common.login.c.b()) {
            a(true);
            this.E.setAlpha(0.5f);
            this.E.setEnabled(false);
            this.E.setText("已在书架");
            af.a((Context) this, getString(R.string.bookshelf_add_success), 0).a();
            return;
        }
        this.mLoginNextTask = new com.qq.reader.common.login.a(this) {
            final /* synthetic */ NativeAudioBookPlayerActivity a;

            {
                this.a = r1;
            }

            public void a(int i) {
                switch (i) {
                    case 1:
                        this.a.l();
                        return;
                    default:
                        return;
                }
            }
        };
        startLogin();
    }

    private boolean m() {
        if (this.ac == null || com.qq.reader.common.db.handle.i.c().e(String.valueOf(this.ac.k())) == null) {
            return false;
        }
        return true;
    }

    private void n() {
        if (this.L == null) {
            this.L = new ao(this, this.at, 1);
            this.L.b(true);
        }
        this.L.f_();
    }

    private void o() {
        if (!com.qq.reader.common.login.c.b()) {
            this.mLoginNextTask = new com.qq.reader.common.login.a(this) {
                final /* synthetic */ NativeAudioBookPlayerActivity a;

                {
                    this.a = r1;
                }

                public void a(int i) {
                    switch (i) {
                        case 1:
                            this.a.o();
                            return;
                        default:
                            return;
                    }
                }
            };
            startLogin();
        } else if (this.ah != null) {
            i.a("event_C185", null, getContext());
            if (this.ah.j() != 2 || this.ah.t()) {
                Intent intent = new Intent();
                intent.putExtra("com.qq.reader.OnlineTag", this.ac);
                intent.setClass(this, AudioBookDownloadActivity.class);
                startActivity(intent);
                return;
            }
            p();
        }
    }

    private void p() {
        com.qq.reader.common.readertask.g.a().a(new AudioAuthCheckTask(Long.parseLong(this.ac.k()), new com.qq.reader.common.readertask.ordinal.c(this) {
            final /* synthetic */ NativeAudioBookPlayerActivity a;

            {
                this.a = r1;
            }

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                try {
                    int optInt = new JSONObject(str).optInt("code");
                    if (optInt == 1) {
                        this.a.mHandler.sendEmptyMessage(10000503);
                    } else if (optInt == 0) {
                        this.a.mHandler.sendEmptyMessage(10000504);
                    } else {
                        this.a.mHandler.sendEmptyMessage(10000505);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                this.a.mHandler.sendEmptyMessage(10000505);
            }
        }));
        a("正在进行购买验证...");
    }

    private void q() {
        try {
            if (l.a != null) {
                int k = l.a.k();
                if (k == 1 || k == 6 || k == 3 || k == 2) {
                    this.H.clearAnimation();
                    this.H.setImageResource(R.drawable.play_btn);
                } else if (k == 4 || k == 5) {
                    this.H.setImageResource(R.drawable.play_btn_loading);
                    if (this.H.getAnimation() == null || !this.H.getAnimation().hasStarted()) {
                        Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.rotaterepeate);
                        loadAnimation.setInterpolator(new LinearInterpolator());
                        this.H.startAnimation(loadAnimation);
                    }
                } else {
                    this.H.clearAnimation();
                    this.H.setImageResource(R.drawable.pause_btn);
                }
                k = l.a.r();
                if (k == 0) {
                    this.I.setClickable(false);
                    this.I.setImageResource(R.drawable.play_btn_prev_hover);
                } else {
                    this.I.setClickable(true);
                    this.I.setImageResource(R.drawable.prev_btn);
                }
                if (k == l.a.q() - 1) {
                    this.J.setClickable(false);
                    this.J.setImageResource(R.drawable.play_btn_next_hover);
                } else {
                    this.J.setClickable(true);
                    this.J.setImageResource(R.drawable.next_btn);
                }
                if (this.an != null && k != this.an.getSelectedItemPosition()) {
                    a(k);
                }
            }
        } catch (RemoteException e) {
        }
    }

    private void r() {
        if (this.w != null) {
            this.w.setProgress(0);
            this.w.setSecondaryProgress(0);
        }
        if (this.t != null) {
            this.t.setText(m.a(0));
        }
        if (this.s != null) {
            this.s.setText(m.a(0));
        }
    }

    private long s() {
        if (l.a == null) {
            return 500;
        }
        try {
            switch (l.a.k()) {
                case 0:
                case 1:
                case 5:
                    long m = l.a.m();
                    long l = l.a.l();
                    if (m <= 0) {
                        this.w.setSecondaryProgress(0);
                    } else if (l == m) {
                        this.w.setSecondaryProgress(Constants.ERRORCODE_UNKNOWN);
                    } else {
                        this.w.setSecondaryProgress((int) ((l * TracerConfig.LOG_FLUSH_DURATION) / m));
                    }
                    l = l.a.j();
                    this.v = l.a.i();
                    if (this.v < 0) {
                        this.v = 0;
                    }
                    this.t.setText(m.a(this.v / 1000));
                    if (l < 0 || this.v <= 0) {
                        this.s.setText(R.string.default_time);
                        this.w.setProgress(0);
                        return 500;
                    }
                    if (!this.x) {
                        m = l / 1000;
                        if (m < 0) {
                            m = 0;
                        }
                        if (m > this.v) {
                            m = this.v;
                        }
                        this.s.setText(m.a(m));
                    }
                    if (this.x) {
                        return 500;
                    }
                    this.w.setProgress((int) ((l * TracerConfig.LOG_FLUSH_DURATION) / this.v));
                    return 500;
            }
        } catch (Exception e) {
        }
        return 0;
    }

    private void a(long j) {
        if (!this.h) {
            this.mHandler.postDelayed(this.av, j);
            this.h = true;
        }
    }

    private void a(final SongInfo[] songInfoArr) {
        if (songInfoArr != null && songInfoArr.length != 0 && !this.aa) {
            try {
                l.a(this, new ServiceConnection(this) {
                    final /* synthetic */ NativeAudioBookPlayerActivity b;

                    /* JADX WARNING: inconsistent code. */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public void onServiceConnected(android.content.ComponentName r10, android.os.IBinder r11) {
                        /*
                        r9 = this;
                        r1 = 0;
                        r8 = -1;
                        r4 = 0;
                        r0 = 1;
                        r2 = com.qq.reader.plugin.audiobook.core.l.a;	 Catch:{ Exception -> 0x0121 }
                        r3 = 12;
                        r2.a(r3);	 Catch:{ Exception -> 0x0121 }
                        r2 = com.qq.reader.plugin.audiobook.core.l.a;	 Catch:{ Exception -> 0x0121 }
                        r2.p();	 Catch:{ Exception -> 0x0121 }
                        r2 = r9.b;	 Catch:{ Exception -> 0x0121 }
                        r2 = r2.am;	 Catch:{ Exception -> 0x0121 }
                        if (r2 <= 0) goto L_0x003e;
                    L_0x0018:
                        r2 = r9.b;	 Catch:{ Exception -> 0x0121 }
                        r2 = r2.am;	 Catch:{ Exception -> 0x0121 }
                        r3 = r9.b;	 Catch:{ Exception -> 0x0121 }
                        r3 = r3.ac;	 Catch:{ Exception -> 0x0121 }
                        r3 = r3.n();	 Catch:{ Exception -> 0x0121 }
                        if (r2 > r3) goto L_0x003e;
                    L_0x002a:
                        r2 = com.qq.reader.plugin.audiobook.core.l.a;	 Catch:{ Exception -> 0x0121 }
                        r2 = r2.o();	 Catch:{ Exception -> 0x0121 }
                        if (r2 == 0) goto L_0x003e;
                    L_0x0032:
                        r2 = r2.f();	 Catch:{ Exception -> 0x0121 }
                        r3 = r9.b;	 Catch:{ Exception -> 0x0121 }
                        r3 = r3.am;	 Catch:{ Exception -> 0x0121 }
                        if (r2 == r3) goto L_0x003e;
                    L_0x003e:
                        r2 = r9.b;	 Catch:{ Exception -> 0x0121 }
                        r2 = r2.S;	 Catch:{ Exception -> 0x0121 }
                        if (r2 != r0) goto L_0x0098;
                    L_0x0046:
                        r0 = com.qq.reader.plugin.audiobook.core.l.a;	 Catch:{ Exception -> 0x0121 }
                        r1 = r2;	 Catch:{ Exception -> 0x0121 }
                        r2 = 0;
                        r0.a(r1, r2);	 Catch:{ Exception -> 0x0121 }
                        r0 = r9.b;	 Catch:{ Exception -> 0x0121 }
                        r0 = r0.am;	 Catch:{ Exception -> 0x0121 }
                        r0 = r0 + -1;
                        r1 = com.qq.reader.plugin.audiobook.core.l.a;	 Catch:{ Exception -> 0x0121 }
                        r1.d(r0);	 Catch:{ Exception -> 0x0121 }
                        r0 = com.qq.reader.plugin.audiobook.core.l.a;	 Catch:{ Exception -> 0x0121 }
                        r0.b();	 Catch:{ Exception -> 0x0121 }
                        r0 = r9.b;	 Catch:{ Exception -> 0x0121 }
                        r0.q();	 Catch:{ Exception -> 0x0121 }
                        r0 = r9.b;	 Catch:{ Exception -> 0x0121 }
                        r0 = r0.ac;	 Catch:{ Exception -> 0x0121 }
                        if (r0 == 0) goto L_0x007a;
                    L_0x006d:
                        r0 = r9.b;	 Catch:{ Exception -> 0x0121 }
                        r0 = r0.am;	 Catch:{ Exception -> 0x0121 }
                        r0 = r0 + -1;
                        r1 = r9.b;	 Catch:{ Exception -> 0x0121 }
                        r1.a(r0);	 Catch:{ Exception -> 0x0121 }
                    L_0x007a:
                        r0 = r9.b;
                        r0 = r0.mHandler;
                        r1 = r9.b;
                        r1 = r1.mHandler;
                        r2 = 2001; // 0x7d1 float:2.804E-42 double:9.886E-321;
                        r3 = r9.b;
                        r3 = r3.ac;
                        r1 = r1.obtainMessage(r2, r3);
                        r2 = 500; // 0x1f4 float:7.0E-43 double:2.47E-321;
                        r0.sendMessageDelayed(r1, r2);
                        return;
                    L_0x0098:
                        r2 = r9.b;	 Catch:{ Exception -> 0x0121 }
                        r2 = r2.ac;	 Catch:{ Exception -> 0x0121 }
                        r2 = r2.k();	 Catch:{ Exception -> 0x0121 }
                        r2 = java.lang.Long.parseLong(r2);	 Catch:{ Exception -> 0x0121 }
                        r5 = com.qq.reader.plugin.audiobook.core.l.a;	 Catch:{ Exception -> 0x0121 }
                        r5 = r5.o();	 Catch:{ Exception -> 0x0121 }
                        r6 = com.qq.reader.plugin.audiobook.core.l.a;	 Catch:{ Exception -> 0x0121 }
                        r6 = r6.k();	 Catch:{ Exception -> 0x0121 }
                        if (r6 != 0) goto L_0x00c4;
                    L_0x00b4:
                        if (r5 == 0) goto L_0x013b;
                    L_0x00b6:
                        r6 = r5.a;	 Catch:{ Exception -> 0x0121 }
                        if (r6 == 0) goto L_0x013b;
                    L_0x00ba:
                        r5 = r5.a;	 Catch:{ Exception -> 0x0121 }
                        r6 = r5.getBookId();	 Catch:{ Exception -> 0x0121 }
                        r2 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1));
                        if (r2 == 0) goto L_0x0127;
                    L_0x00c4:
                        if (r0 == 0) goto L_0x0152;
                    L_0x00c6:
                        r2 = 0;
                        r0 = com.qq.reader.plugin.audiobook.core.l.a;	 Catch:{ Exception -> 0x0121 }
                        r0 = r0.a();	 Catch:{ Exception -> 0x0121 }
                        if (r0 == 0) goto L_0x0160;
                    L_0x00d0:
                        r0 = com.qq.reader.plugin.audiobook.core.l.a;	 Catch:{ Exception -> 0x0121 }
                        r0 = r0.o();	 Catch:{ Exception -> 0x0121 }
                        r2 = com.qq.reader.plugin.audiobook.core.l.a;	 Catch:{ Exception -> 0x0121 }
                        r2 = r2.j();	 Catch:{ Exception -> 0x0121 }
                    L_0x00dc:
                        r4 = com.qq.reader.plugin.audiobook.core.l.a;	 Catch:{ Exception -> 0x0121 }
                        r5 = r2;	 Catch:{ Exception -> 0x0121 }
                        r6 = 0;
                        r4.a(r5, r6);	 Catch:{ Exception -> 0x0121 }
                        r4 = r9.b;	 Catch:{ Exception -> 0x0121 }
                        r4 = r4.am;	 Catch:{ Exception -> 0x0121 }
                        if (r4 == r8) goto L_0x013d;
                    L_0x00ec:
                        r1 = r9.b;	 Catch:{ Exception -> 0x0121 }
                        r1 = r1.am;	 Catch:{ Exception -> 0x0121 }
                        r1 = r1 + -1;
                        r4 = r9.b;	 Catch:{ Exception -> 0x0121 }
                        r4 = r4.ac;	 Catch:{ Exception -> 0x0121 }
                        r5 = r9.b;	 Catch:{ Exception -> 0x0121 }
                        r5 = r5.am;	 Catch:{ Exception -> 0x0121 }
                        r4.c(r5);	 Catch:{ Exception -> 0x0121 }
                    L_0x0103:
                        r4 = r9.b;	 Catch:{ Exception -> 0x0121 }
                        r4.a(r1);	 Catch:{ Exception -> 0x0121 }
                        r4 = com.qq.reader.plugin.audiobook.core.l.a;	 Catch:{ Exception -> 0x0121 }
                        r4.b(r1);	 Catch:{ Exception -> 0x0121 }
                        r1 = r9.b;	 Catch:{ Exception -> 0x0121 }
                        r4 = 1;
                        r1.aa = r4;	 Catch:{ Exception -> 0x0121 }
                        r1 = r9.b;	 Catch:{ Exception -> 0x0121 }
                        r4 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
                        r1.a(r4);	 Catch:{ Exception -> 0x0121 }
                        if (r0 == 0) goto L_0x007a;
                    L_0x011c:
                        com.qq.reader.plugin.audiobook.core.n.a(r0, r2);	 Catch:{ Exception -> 0x0121 }
                        goto L_0x007a;
                    L_0x0121:
                        r0 = move-exception;
                        r0.printStackTrace();
                        goto L_0x007a;
                    L_0x0127:
                        r2 = r9.b;	 Catch:{ Exception -> 0x0121 }
                        r2 = r2.am;	 Catch:{ Exception -> 0x0121 }
                        if (r2 == r8) goto L_0x013b;
                    L_0x012f:
                        r2 = r9.b;	 Catch:{ Exception -> 0x0121 }
                        r2 = r2.am;	 Catch:{ Exception -> 0x0121 }
                        r3 = r5.getChapterId();	 Catch:{ Exception -> 0x0121 }
                        if (r2 != r3) goto L_0x00c4;
                    L_0x013b:
                        r0 = r1;
                        goto L_0x00c4;
                    L_0x013d:
                        r4 = r9.b;	 Catch:{ Exception -> 0x0121 }
                        r4 = r4.ac;	 Catch:{ Exception -> 0x0121 }
                        if (r4 == 0) goto L_0x0103;
                    L_0x0145:
                        r1 = r9.b;	 Catch:{ Exception -> 0x0121 }
                        r1 = r1.ac;	 Catch:{ Exception -> 0x0121 }
                        r1 = r1.g();	 Catch:{ Exception -> 0x0121 }
                        r1 = r1 + -1;
                        goto L_0x0103;
                    L_0x0152:
                        r0 = r9.b;	 Catch:{ Exception -> 0x0121 }
                        r2 = 100;
                        r0.a(r2);	 Catch:{ Exception -> 0x0121 }
                        r0 = r9.b;	 Catch:{ Exception -> 0x0121 }
                        r0.q();	 Catch:{ Exception -> 0x0121 }
                        goto L_0x007a;
                    L_0x0160:
                        r0 = r4;
                        goto L_0x00dc;
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.module.bookstore.qnative.activity.NativeAudioBookPlayerActivity.20.onServiceConnected(android.content.ComponentName, android.os.IBinder):void");
                    }

                    public void onServiceDisconnected(ComponentName componentName) {
                    }
                });
            } catch (Exception e) {
            }
        }
    }

    protected boolean handleMessageImp(Message message) {
        int i = 0;
        com.qq.reader.cservice.buy.a.c cVar;
        Intent intent;
        Bundle bundle;
        int g;
        switch (message.what) {
            case 1218:
                i.a("event_B144", null, ReaderApplication.getApplicationImp());
                if (y()) {
                    cVar = (com.qq.reader.cservice.buy.a.c) message.obj;
                    this.ac.e(true);
                    int e = cVar.e();
                    this.ao.notifyDataSetChanged();
                    b(this.ac.k());
                    if (!m()) {
                        l();
                    }
                    af.a(ReaderApplication.getApplicationImp(), getString(R.string.audio_book_pay_success), 0).a();
                    if (e != 10001) {
                        try {
                            if (l.a.k() != 1 && l.a.k() != 6) {
                                l.a.d();
                                break;
                            }
                            l.a.g();
                            break;
                        } catch (Exception e2) {
                            com.qq.reader.common.monitor.debug.c.e("Error", e2.getMessage());
                            break;
                        }
                    }
                    intent = new Intent();
                    intent.putExtra("com.qq.reader.OnlineTag", this.ac);
                    intent.setClass(this, AudioBookDownloadActivity.class);
                    startActivity(intent);
                    break;
                }
                break;
            case 1219:
                if (y()) {
                    cVar = (com.qq.reader.cservice.buy.a.c) message.obj;
                    i = cVar.d();
                    bundle = new Bundle();
                    bundle.putString("message", cVar.a());
                    if (i != -2) {
                        if (i != -6) {
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
                v();
                break;
            case 1225:
                v();
                break;
            case 1235:
                v();
                finish();
                break;
            case 1238:
                try {
                    if (l.a.k() == 1 || l.a.k() == 6) {
                        l.a.g();
                        if (y()) {
                            b(this.ah.g());
                            if (!m()) {
                                l();
                            }
                            af.a(getApplicationContext(), (CharSequence) "购买成功", 0).a();
                            break;
                        }
                    }
                    l.a.d();
                    if (y()) {
                        b(this.ah.g());
                        if (m()) {
                            l();
                        }
                        af.a(getApplicationContext(), (CharSequence) "购买成功", 0).a();
                    }
                } catch (Exception e22) {
                    com.qq.reader.common.monitor.debug.c.e("Error", e22.getMessage());
                }
                break;
            case 1239:
                if (y()) {
                    af.a(getApplicationContext(), ((ChapterPayResult) message.obj).getResultStr(), 0).a();
                    break;
                }
                break;
            case APPluginErrorCode.ERROR_APP_SYSTEM /*2000*/:
                com.qq.reader.cservice.cloud.f fVar = (com.qq.reader.cservice.cloud.f) message.obj;
                Bundle bundle2 = new Bundle();
                bundle2.putString("message", getResources().getString(R.string.audio_colud_info2) + fVar.g() + getResources().getString(R.string.audio_colud_info3));
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
                this.ah = (f) message.obj;
                this.aj.setVisibility(8);
                com.qq.reader.module.bookchapter.online.d y = this.ah.y();
                if (y != null) {
                    if (this.S != 1) {
                        ((TextView) findViewById(R.id.profile_header_title)).setText(y.e());
                    }
                    this.ac.d(y.j());
                    this.ac.a(y.e());
                    this.ac.e(y.G());
                    this.ac.h(y.C());
                    if (y.P() == 1 && this.S == 1) {
                        this.R.setVisibility(0);
                        i.a("event_B269", null, ReaderApplication.getApplicationContext());
                    }
                    if (y.V() == -1 || y.U() == -1) {
                        this.Z.setVisibility(0);
                    }
                }
                this.y = this.ah.x();
                if (this.ac.g() > this.ac.n()) {
                    OnlineTag a = v.b().a(this.ac.k());
                    if (a == null) {
                        this.ac.c(1);
                    } else {
                        this.ac.c(a.g());
                    }
                }
                Collection e3 = this.ah.e();
                if (e3 != null && e3.size() > 0) {
                    this.ak.clear();
                    this.ak.addAll(e3);
                    this.ag = true;
                    if (this.ac.n() == 0) {
                        this.ac.d(this.ak.size());
                    }
                }
                r.a().a(2, y.d(), y.e());
                v();
                j();
                q();
                b(this.ac.k());
                if (e3 == null || e3.size() == 0) {
                    com.qq.reader.common.monitor.debug.c.e("Error", "tempList null");
                    this.an.setVisibility(8);
                    this.ai.setVisibility(0);
                } else {
                    g = this.ac.g() - 1;
                    this.an.setVisibility(0);
                    this.ai.setVisibility(8);
                    this.ao.a(this.ah.e());
                    a(g);
                }
                if (message.arg2 == 2) {
                    k();
                    if (e3 != null && e3.size() > 0 && message.arg2 == 2) {
                        this.ao.a(e3);
                        this.ao.notifyDataSetChanged();
                    }
                }
                com.qq.reader.common.readertask.g.a().a(new ReaderShortTask() {
                    public void run() {
                        Object N = NativeAudioBookPlayerActivity.this.i();
                        Message obtain = Message.obtain();
                        obtain.what = 200013;
                        obtain.obj = N;
                        NativeAudioBookPlayerActivity.this.getHandler().sendMessage(obtain);
                    }
                });
                break;
            case 21001:
                v();
                if (!this.ag) {
                    com.qq.reader.common.monitor.debug.c.e("Error", "get online chapter error");
                    t();
                    break;
                }
                break;
            case 21011:
                final ArrayList arrayList = (ArrayList) message.obj;
                com.qq.reader.common.readertask.g.a().a(new ReaderIOTask() {
                    public void run() {
                        com.qq.reader.common.db.handle.h.a(arrayList, NativeAudioBookPlayerActivity.this.ac, NativeAudioBookPlayerActivity.this.ah);
                    }
                });
                if (this.ao != null) {
                    this.ao.a(arrayList);
                    this.ao.notifyDataSetChanged();
                    break;
                }
                break;
            case 21017:
                if (this.ac != null) {
                    this.ac.e(true);
                    if (this.F != null) {
                        this.F.setText(getResources().getString(R.string.audio_paypage_pay_all_ok));
                        break;
                    }
                }
                break;
            case 21101:
                if (this.ao != null) {
                    this.ao.a(true);
                    this.ao.notifyDataSetChanged();
                }
                return true;
            case 200008:
                af.a(ReaderApplication.getApplicationImp(), (CharSequence) "请安装最新版讯飞语记", 0).a();
                com.qq.reader.common.utils.ao.u();
                D();
                break;
            case 200013:
                a((SongInfo[]) message.obj);
                break;
            case 400008:
                d();
                break;
            case 8000011:
                b((a) message.obj);
                return true;
            case 10000503:
                if (v()) {
                    this.ac.e(true);
                    intent = new Intent();
                    intent.putExtra("com.qq.reader.OnlineTag", this.ac);
                    intent.setClass(this, AudioBookDownloadActivity.class);
                    startActivity(intent);
                    break;
                }
                break;
            case 10000504:
                try {
                    l.a.b();
                } catch (Exception e222) {
                    com.qq.reader.common.monitor.debug.c.e("Error", e222.getMessage());
                }
                if (v()) {
                    Bundle bundle3 = new Bundle();
                    bundle3.putInt("PAY_TYPE", 1001);
                    if (!(this.ah == null || this.ah.y() == null)) {
                        bundle3.putLong("cpid", this.ah.y().Q());
                        bundle3.putInt("download_type", this.ah.y().h());
                        bundle3.putInt("estimate_max_chapters", this.ah.y().w());
                        bundle3.putInt("cur_max_chapters", this.ah.y().j());
                    }
                    bundle3.putInt("KEY_BUY_BOOK_FROM", 10001);
                    showFragmentDialog(606, bundle3);
                    break;
                }
                break;
            case 10000505:
                if (v()) {
                    af.a(getApplicationContext(), (CharSequence) "购买验证失败", 0).a();
                    break;
                }
                break;
            case 10000506:
                try {
                    l.a.b();
                    com.qq.reader.common.utils.ao.a(2, Long.parseLong(this.ac.k()), false, this.ac.b());
                    r();
                } catch (Exception e2222) {
                    com.qq.reader.common.monitor.debug.c.e("Error", e2222.getMessage());
                }
                if (!isFragmentDialogShowing()) {
                    g = message.arg1;
                    OnlineChapter onlineChapter = (OnlineChapter) message.obj;
                    bundle = new Bundle();
                    if (!(this.ah == null || this.ah.y() == null)) {
                        bundle.putLong("cpid", this.ah.y().Q());
                        bundle.putInt("download_type", this.ah.y().h());
                        bundle.putInt("estimate_max_chapters", this.ah.y().w());
                        bundle.putInt("cur_max_chapters", this.ah.y().j());
                    }
                    if (g != -1) {
                        if (onlineChapter != null) {
                            bundle.putInt("PAY_TYPE", g);
                            g = this.ah.j();
                            if (g == 2) {
                                i = this.ah.y().z();
                            } else if (g == 3) {
                                i = this.ah.y().r();
                            }
                            bundle.putInt("PAY_CHAPER_SOURCE_PRICE", i);
                            bundle.putString("PAY_CHAPER_NAME", onlineChapter.getChapterName());
                            if (this.ah.y().Q() == 2000000804) {
                                bundle.putInt("PAY_CHAPER_DISCOUNT_PRICE", this.ah.y().s());
                            } else {
                                bundle.putInt("PAY_CHAPER_DISCOUNT_PRICE", (i * this.ah.y().x()) / 100);
                            }
                            bundle.putString("PAY_CHAPER_DISCOUNT_REASON", this.ah.y().B());
                            bundle.putInt("KEY_BUY_BOOK_FROM", Constants.ERRORCODE_UNKNOWN);
                            showFragmentDialog(609, bundle);
                            break;
                        }
                    }
                    showFragmentDialog(501, bundle);
                    break;
                }
                break;
            case 10000512:
                try {
                    l.a.b();
                    com.qq.reader.common.utils.ao.a(2, Long.parseLong(this.ac.k()), false, this.ac.b());
                    r();
                } catch (Exception e22222) {
                    com.qq.reader.common.monitor.debug.c.e("Error", e22222.getMessage());
                }
                showFragmentDialog(303);
                break;
        }
        return super.handleMessageImp(message);
    }

    private void a(int i) {
        if (this.ao != null && i < this.ao.getCount()) {
            this.ao.a(i);
            this.an.setSelection(i);
            this.ao.notifyDataSetChanged();
            this.an.smoothScrollToPositionFromTop(i, 0);
        } else if (i >= this.ao.getCount()) {
            this.ao.a(0);
            this.an.setSelection(0);
            this.ao.notifyDataSetChanged();
            this.an.smoothScrollToPositionFromTop(0, 0);
        }
    }

    protected Dialog createDialog(int i, Bundle bundle) {
        AlertDialog alertDialog = (AlertDialog) com.qq.reader.module.readpage.q.a(this, i, null);
        final int i2;
        switch (i) {
            case 303:
                alertDialog.setTitle("提示");
                alertDialog.a((CharSequence) "本集需要登录后才能播放");
                alertDialog.a((int) R.string.login_btn, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ NativeAudioBookPlayerActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        this.a.mLoginNextTask = new com.qq.reader.common.login.a(this) {
                            final /* synthetic */ AnonymousClass24 a;

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
                                                    l.a.d();
                                                    this.a.a.a.b(this.a.a.a.ac);
                                                } catch (RemoteException e) {
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
                    final /* synthetic */ NativeAudioBookPlayerActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        i.a("event_C234", null, this.a.getApplicationContext());
                        this.a.al = true;
                        this.a.finish();
                    }
                });
                alertDialog.b((int) R.string.alert_dialog_cancel, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ NativeAudioBookPlayerActivity a;

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
                    final /* synthetic */ NativeAudioBookPlayerActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        this.a.ac.c(false);
                        d.z(this.a, false);
                        af.a(this.a, this.a.getString(R.string.dialog_shut_down_autopay), 0).a();
                        i.a("event_C192", null, this.a.getContext());
                    }
                });
                alertDialog.b((int) R.string.alert_dialog_cancel, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ NativeAudioBookPlayerActivity a;

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
                        final /* synthetic */ NativeAudioBookPlayerActivity b;

                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (i2 == -1) {
                                this.b.mLoginNextTask = new com.qq.reader.common.login.a(this) {
                                    final /* synthetic */ AnonymousClass26 a;

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
                                                            l.a.d();
                                                        } catch (RemoteException e) {
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
                    final /* synthetic */ NativeAudioBookPlayerActivity a;

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
                    final /* synthetic */ NativeAudioBookPlayerActivity c;

                    public void onClick(DialogInterface dialogInterface, int i) {
                        OnlineTag z = this.c.ac.z();
                        if (z != null) {
                            z.c(i3);
                            this.c.a(z);
                        }
                        com.qq.reader.common.monitor.debug.c.a(Constants.LogTag, "TYPE_ONLINE  chapter id = " + i3 + " /  offset = " + i2);
                    }
                });
                alertDialog.b((int) R.string.alert_dialog_cancel, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ NativeAudioBookPlayerActivity a;

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
                    final /* synthetic */ NativeAudioBookPlayerActivity a;

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
                    final /* synthetic */ NativeAudioBookPlayerActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        this.a.c();
                    }
                });
                alertDialog.b((int) R.string.alert_dialog_cancel, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ NativeAudioBookPlayerActivity a;

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
                    final /* synthetic */ NativeAudioBookPlayerActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        try {
                            if (l.a != null) {
                                if (l.a.k() == 1 || l.a.k() == 6) {
                                    l.a.g();
                                } else {
                                    l.a.d();
                                }
                                com.qq.reader.plugin.audiobook.core.b.a = 1;
                            }
                        } catch (RemoteException e) {
                        }
                    }
                });
                alertDialog.b((int) R.string.alert_dialog_cancel, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ NativeAudioBookPlayerActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        try {
                            if (l.a != null) {
                                l.a.b();
                            }
                        } catch (RemoteException e) {
                        }
                    }
                });
                break;
            case 802:
                alertDialog.a((int) R.string.retry, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ NativeAudioBookPlayerActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        this.a.b(this.a.ac);
                    }
                });
                alertDialog.b((int) R.string.alert_dialog_cancel, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ NativeAudioBookPlayerActivity a;

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
            int g = onlineTag.g();
            this.s.setText(m.a(onlineTag.i()));
            if (l.a.r() != g - 1) {
                l.a.b(g - 1);
            }
            com.qq.reader.module.bookchapter.online.d y = this.ah.y();
            if (y != null && y.P() == 1 && this.S == 1) {
                if (this.ak == null || g >= this.ak.size() || ((OnlineChapter) this.ak.get(g)).getPrice() <= 0.0f) {
                    i.a("event_B271", null, ReaderApplication.getApplicationImp());
                } else {
                    i.a("event_B270", null, ReaderApplication.getApplicationImp());
                }
            }
            Map hashMap = new HashMap();
            hashMap.put(s.ORIGIN, this.S == 1 ? "1" : "0");
            i.a("event_B273", hashMap, ReaderApplication.getApplicationImp());
        } catch (Exception e) {
        }
    }

    private void a(boolean z) {
        Object obj = 1;
        try {
            SongInfo o = l.a.o();
            if (o != null) {
                Mark mark;
                int f = o.f();
                getIntent().getExtras().putInt("book_chapterid", f);
                if (this.ac != null) {
                    this.ac.c(f);
                    this.ac.a(l.a.j());
                }
                v.b().b(this.ac);
                Mark e = com.qq.reader.common.db.handle.i.c().e(this.ac.k());
                if (e != null) {
                    mark = e;
                } else {
                    if (!z) {
                        if (!this.al) {
                            obj = null;
                            mark = e;
                        }
                    }
                    mark = new TingBookMark(Long.parseLong(this.ac.k()), this.ac.b());
                }
                if (obj != null) {
                    long currentTimeMillis = System.currentTimeMillis();
                    mark.setAuthor(this.ac.o());
                    mark.setBookName(this.ac.b());
                    mark.setTotalChapterCount(this.ac.n());
                    mark.setFinished(this.ac.w());
                    mark.setReadTime(currentTimeMillis);
                    mark.setOperateTime(currentTimeMillis);
                    mark.setHasNewContent(false);
                    mark.setBookId(Long.parseLong(this.ac.k()));
                    mark.setCoverUrl(this.ac.u());
                    mark.setPercentStr("第" + f + "集");
                    mark.setLimitFreeEndTime(this.y);
                    mark.setLastReadChapterName(o.h());
                    com.qq.reader.common.db.handle.i.c().a(mark, true);
                    b(this.ac);
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void a(Object obj) {
        try {
            this.ae.a(null, this.ad, obj);
        } catch (Exception e) {
        }
    }

    private void b(Object obj) {
        try {
            this.ae.c(null, this.ad, obj);
        } catch (Exception e) {
        }
    }

    private void b(OnlineTag onlineTag) {
        if (onlineTag != null) {
            if (this.af == null) {
                this.af = new g(getApplicationContext(), onlineTag.z());
            }
            this.af.a(this.mHandler);
            this.af.a(true);
            this.H.setImageResource(R.drawable.play_btn_loading);
            Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.rotaterepeate);
            loadAnimation.setInterpolator(new LinearInterpolator());
            this.H.startAnimation(loadAnimation);
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
                        if (this.ac.g() != g) {
                            this.ac.c(g);
                            this.ac.a(0);
                            a(onlineTag);
                        }
                    }
                }
            }
        } else if (i == 20001 && i2 == 0) {
            b();
        }
    }

    private void t() {
        this.ai.setVisibility(0);
    }

    private void u() {
        this.M.setVisibility(0);
    }

    private void a(String str) {
        if (this.K == null) {
            this.K = new c(this);
            this.K.c(true);
        }
        this.K.a(str);
        this.K.f_();
    }

    private boolean v() {
        try {
            if (this.K != null && this.K.f()) {
                this.K.cancel();
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    private void a(AlertDialog alertDialog, Activity activity, Bundle bundle) {
        try {
            int i;
            String str;
            String str2;
            int i2;
            String str3;
            Object obj;
            int i3;
            CharSequence spannableString;
            CharSequence charSequence;
            View inflate = LayoutInflater.from(activity).inflate(R.layout.book_buy_view_new, null);
            TextView textView = (TextView) inflate.findViewById(R.id.book_discount_msg);
            TextView textView2 = (TextView) inflate.findViewById(R.id.tv_origin_price);
            TextView textView3 = (TextView) inflate.findViewById(R.id.suffix_txt);
            TextView textView4 = (TextView) inflate.findViewById(R.id.tv_price);
            final CheckBox checkBox = (CheckBox) inflate.findViewById(R.id.auto_pay_check);
            checkBox.setChecked(this.N);
            checkBox.setText(getString(R.string.auto_pay_next_audio));
            TextView textView5 = (TextView) inflate.findViewById(R.id.textViewDownloadType);
            ProgressBar progressBar = (ProgressBar) inflate.findViewById(R.id.pb_user_balance);
            String str4 = "";
            int i4 = 0;
            String str5 = "";
            int i5 = 0;
            str5 = "";
            this.ac.k();
            if (this.af == null || this.af.e() == null) {
                i = 100;
                str = "";
            } else {
                i = this.af.e().s();
                str = this.af.e().r();
            }
            this.j = bundle.getInt("KEY_BUY_BOOK_FROM");
            this.k = bundle.getInt("PAY_TYPE");
            this.m = bundle.getLong("cpid");
            this.l = bundle.getInt("download_type");
            this.n = bundle.getInt("estimate_max_chapters");
            this.o = bundle.getInt("cur_max_chapters");
            alertDialog.setCanceledOnTouchOutside(true);
            int i6;
            if (this.k == 1001) {
                String b = this.ac.b();
                if (!(this.af == null || this.af.e() == null)) {
                    i4 = this.af.e().p();
                    i5 = this.af.e().v();
                    str5 = this.af.e().w();
                }
                textView3.setText("书币");
                textView5.setText("");
                String str6 = String.valueOf(i4) + "书币";
                textView5.setVisibility(0);
                str2 = b;
                i2 = i4;
                i6 = i5;
                str3 = str;
                obj = str6;
                i3 = i4;
                i4 = i6;
            } else {
                str2 = bundle.getString("PAY_CHAPER_NAME");
                i2 = bundle.getInt("PAY_CHAPER_SOURCE_PRICE");
                i4 = bundle.getInt("PAY_CHAPER_DISCOUNT_PRICE");
                str = bundle.getString("PAY_CHAPER_DISCOUNT_REASON");
                textView3.setText("书币");
                if (i4 == i2) {
                    i = 100;
                } else if (i2 > 0) {
                    i = i4 / i2;
                } else {
                    i = 100;
                }
                checkBox.setVisibility(0);
                i6 = i4;
                i4 = 0;
                str3 = str;
                str = String.valueOf(i2) + "书币";
                i3 = i6;
            }
            StringBuilder stringBuilder = new StringBuilder();
            if (i < 100 || i4 > 0 || (this.af != null && this.af.e() != null && this.af.e().y() != null && this.af.e().y().Q() == 2000000804 && this.af.e().y().s() > 0 && i2 > this.af.e().y().s())) {
                Object obj2;
                if (this.k == 1001) {
                    if (this.af == null || this.af.e() == null || this.af.e().y() == null || this.af.e().y().Q() != 2000000804 || this.af.e().y().s() <= 0) {
                        i3 = (i2 * i) / 100;
                    } else {
                        i3 = this.af.e().y().s();
                    }
                    if (i4 > 0 && i4 < r4) {
                        i3 = i4;
                        obj2 = str5;
                        if (TextUtils.isEmpty(obj2)) {
                            textView.setText("(" + obj2 + ")");
                            textView.setVisibility(0);
                        } else {
                            textView.setVisibility(8);
                        }
                        textView4.setText(String.valueOf(i3));
                        spannableString = new SpannableString(obj);
                        spannableString.setSpan(new StrikethroughSpan(), 0, obj.length(), 33);
                        textView2.setText(spannableString);
                        if (this.k == 1001) {
                            textView5.setVisibility(0);
                        }
                    }
                }
                str4 = str3;
                if (TextUtils.isEmpty(obj2)) {
                    textView.setVisibility(8);
                } else {
                    textView.setText("(" + obj2 + ")");
                    textView.setVisibility(0);
                }
                textView4.setText(String.valueOf(i3));
                spannableString = new SpannableString(obj);
                spannableString.setSpan(new StrikethroughSpan(), 0, obj.length(), 33);
                textView2.setText(spannableString);
                if (this.k == 1001) {
                    textView5.setVisibility(0);
                }
            } else {
                textView.setVisibility(8);
                textView2.setVisibility(8);
                textView4.setText(String.valueOf(i2));
            }
            ((TextView) inflate.findViewById(R.id.book_name)).setText(String.format(getResources().getString(R.string.buy_book_name), new Object[]{str2}));
            if (this.m == 2000000804) {
                if (i2 > 0 && i3 > 0) {
                    ((TextView) inflate.findViewById(R.id.limitText)).setVisibility(0);
                }
                if (this.l == 2) {
                    charSequence = "预计更新" + this.n + "集，已更新至第" + this.o + "集";
                    textView = (TextView) inflate.findViewById(R.id.preText);
                    textView.setText(charSequence);
                    textView.setVisibility(0);
                }
            }
            int a = this.B.a();
            charSequence = this.B.b();
            textView = (TextView) inflate.findViewById(R.id.tv_user_balance);
            if (a < 0) {
                progressBar.setVisibility(0);
                charSequence = "";
            } else {
                progressBar.setVisibility(8);
            }
            textView.setText(charSequence);
            if (this.B.d > 0) {
                com.qq.reader.common.charge.voucher.b.a(this, textView, new OnClickListener(this) {
                    final /* synthetic */ NativeAudioBookPlayerActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                    }
                }, new OnDismissListener(this) {
                    final /* synthetic */ NativeAudioBookPlayerActivity a;

                    {
                        this.a = r1;
                    }

                    public void onDismiss(DialogInterface dialogInterface) {
                    }
                }, this.B.g);
            }
            spannableString = getString(R.string.alert_dialog_audio_buy);
            if (this.m == 2000000804 && this.l == 2) {
                spannableString = getString(R.string.alert_dialog_audio_subscribe);
            }
            alertDialog.a(inflate);
            alertDialog.setTitle(spannableString);
            Map hashMap = new HashMap();
            hashMap.put(s.ORIGIN, this.k + "");
            final AlertDialog alertDialog2;
            if (a < 0 || a >= i3) {
                alertDialog2 = alertDialog;
                alertDialog.a((int) R.string.alert_dialog_buy_confirm, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ NativeAudioBookPlayerActivity c;

                    public void onClick(DialogInterface dialogInterface, int i) {
                        this.c.N = checkBox.isChecked();
                        if (this.c.k == 1001 || !checkBox.isChecked()) {
                            this.c.ac.c(false);
                            d.z(this.c, false);
                        } else {
                            this.c.ac.c(true);
                            d.z(this.c, true);
                        }
                        alertDialog2.c();
                        String k = this.c.ac.k();
                        i.a("event_C201", null, this.c.getContext());
                        if (this.c.m == 2000000804) {
                            if (this.c.k == 1001) {
                                i.a("event_B262", null, this.c.getContext());
                            }
                        } else if (this.c.k == 1001) {
                            i.a("event_B261", null, this.c.getContext());
                        } else {
                            i.a("event_B260", null, this.c.getContext());
                        }
                        if (this.c.k != 1001) {
                            this.c.w();
                        } else if (!TextUtils.isEmpty(k)) {
                            this.c.a(k, this.c.j);
                        }
                    }
                });
                alertDialog2 = alertDialog;
                alertDialog.b((int) R.string.alert_dialog_cancel, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ NativeAudioBookPlayerActivity c;

                    public void onClick(DialogInterface dialogInterface, int i) {
                        this.c.N = checkBox.isChecked();
                        alertDialog2.c();
                    }
                });
            } else {
                i.a("event_C200", hashMap, getContext());
                this.i = i3;
                alertDialog2 = alertDialog;
                alertDialog.a((int) R.string.alert_dialog_buy_balance_insufficient, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ NativeAudioBookPlayerActivity c;

                    public void onClick(DialogInterface dialogInterface, int i) {
                        this.c.N = checkBox.isChecked();
                        alertDialog2.c();
                        this.c.c();
                        if (this.c.m == 2000000804) {
                            if (this.c.k == 1001) {
                                i.a("event_B262", null, this.c.getContext());
                            }
                        } else if (this.c.k == 1001) {
                            i.a("event_B261", null, this.c.getContext());
                        } else {
                            i.a("event_B260", null, this.c.getContext());
                        }
                    }
                });
            }
            alertDialog.a(-1, (int) R.drawable.selector_orange_button);
            alertDialog.a(-2, (int) R.drawable.selector_white_button);
            NativeAudioBookPlayerActivity nativeAudioBookPlayerActivity = this;
            a aVar = new a();
            aVar.b = bundle;
            aVar.a = alertDialog;
            a(aVar);
        } catch (Exception e) {
            com.qq.reader.common.monitor.debug.c.e("Error", e.getMessage());
        }
    }

    private void a(final String str, final int i) {
        if (com.qq.reader.common.login.c.b()) {
            com.qq.reader.cservice.buy.a.a aVar = new com.qq.reader.cservice.buy.a.a(getApplicationContext(), str);
            aVar.a(i);
            aVar.a((b) this);
            aVar.start();
            x();
            return;
        }
        setLoginNextTask(new com.qq.reader.common.login.a(this) {
            final /* synthetic */ NativeAudioBookPlayerActivity c;

            public void a(int i) {
                switch (i) {
                    case 1:
                        this.c.a(str, i);
                        return;
                    default:
                        return;
                }
            }
        });
        startLogin();
    }

    protected void onFragmentDialgoCancel(DialogInterface dialogInterface) {
        if (dialogInterface != null && (dialogInterface instanceof AlertDialog)) {
            AlertDialog alertDialog = (AlertDialog) dialogInterface;
            if (alertDialog != null) {
                CheckBox checkBox = (CheckBox) alertDialog.findViewById(R.id.auto_pay_check);
                if (checkBox != null) {
                    this.N = checkBox.isChecked();
                }
            }
        }
    }

    public void a(final a aVar) {
        com.qq.reader.common.readertask.g.a().a(new QueryUserBalanceTask(new com.qq.reader.common.readertask.protocol.QueryUserBalanceTask.a(this) {
            final /* synthetic */ NativeAudioBookPlayerActivity b;

            public void a(com.qq.reader.common.charge.voucher.a.a aVar) {
                this.b.B.a(aVar);
                Message obtainMessage = this.b.mHandler.obtainMessage(8000011);
                obtainMessage.obj = aVar;
                this.b.mHandler.sendMessage(obtainMessage);
            }

            public void a() {
            }
        }, String.valueOf(this.ad.getBookNetId()), 2));
    }

    public void b() {
        com.qq.reader.common.readertask.g.a().a(new QueryUserBalanceTask(new com.qq.reader.common.readertask.protocol.QueryUserBalanceTask.a(this) {
            final /* synthetic */ NativeAudioBookPlayerActivity a;

            {
                this.a = r1;
            }

            public void a(com.qq.reader.common.charge.voucher.a.a aVar) {
                this.a.B.a(aVar);
                Message obtain = Message.obtain();
                obtain.what = 400008;
                this.a.mHandler.sendMessage(obtain);
            }

            public void a() {
            }
        }, String.valueOf(this.ad.getBookNetId()), 2));
    }

    public void b(a aVar) {
        int i = 0;
        try {
            final AlertDialog alertDialog = aVar.a;
            Bundle bundle = aVar.b;
            if (alertDialog != null && alertDialog.isShowing()) {
                TextView textView = (TextView) alertDialog.findViewById(R.id.tv_user_balance);
                if (textView != null) {
                    textView.setText(this.B.b());
                }
                if (this.B.d > 0) {
                    com.qq.reader.common.charge.voucher.b.a(this, textView, new OnClickListener(this) {
                        final /* synthetic */ NativeAudioBookPlayerActivity a;

                        {
                            this.a = r1;
                        }

                        public void onClick(View view) {
                        }
                    }, new OnDismissListener(this) {
                        final /* synthetic */ NativeAudioBookPlayerActivity a;

                        {
                            this.a = r1;
                        }

                        public void onDismiss(DialogInterface dialogInterface) {
                        }
                    }, this.B.g);
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
                    OnlineTag onlineTag = this.ac;
                    if (this.af == null || this.af.e() == null) {
                        i2 = 100;
                    } else {
                        i2 = this.af.e().s();
                    }
                    this.j = bundle.getInt("KEY_BUY_BOOK_FROM");
                    this.k = bundle.getInt("PAY_TYPE");
                    if (this.k == 1001) {
                        if (this.af == null || this.af.e() == null) {
                            i3 = 0;
                        } else {
                            i3 = this.af.e().p();
                            i = this.af.e().v();
                        }
                        if (this.af == null || this.af.e() == null || this.af.e().y() == null || this.af.e().y().Q() != 2000000804 || this.af.e().y().s() <= 0) {
                            i3 = (i3 * i2) / 100;
                        } else {
                            i3 = this.af.e().y().s();
                        }
                        if (i <= 0 || i >= i3) {
                            i = i3;
                        }
                    } else {
                        bundle.getInt("PAY_CHAPER_SOURCE_PRICE");
                        i = bundle.getInt("PAY_CHAPER_DISCOUNT_PRICE");
                    }
                    i3 = this.B.a();
                    if (i3 < 0 || i3 >= i) {
                        c.setOnClickListener(new OnClickListener(this) {
                            final /* synthetic */ NativeAudioBookPlayerActivity c;

                            public void onClick(View view) {
                                this.c.N = checkBox.isChecked();
                                alertDialog.c();
                                if (this.c.m == 2000000804) {
                                    if (this.c.k == 1001) {
                                        i.a("event_B262", null, this.c.getContext());
                                    }
                                } else if (this.c.k == 1001) {
                                    i.a("event_B261", null, this.c.getContext());
                                } else {
                                    i.a("event_B260", null, this.c.getContext());
                                }
                                String k = this.c.ac.k();
                                if (this.c.k != 1001) {
                                    if (checkBox.isChecked()) {
                                        i.a("event_C190", null, this.c.getContext());
                                        this.c.ac.c(true);
                                        d.z(this.c, true);
                                    } else {
                                        this.c.ac.c(false);
                                        d.z(this.c, false);
                                    }
                                    this.c.w();
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
                            final /* synthetic */ NativeAudioBookPlayerActivity c;

                            public void onClick(View view) {
                                this.c.N = checkBox.isChecked();
                                alertDialog.c();
                            }
                        });
                        return;
                    }
                    this.i = i;
                    if (!com.qq.reader.common.utils.ao.s(this.B.e)) {
                        View findViewById;
                        TextView textView2;
                        if (getResources().getConfiguration().orientation == 2) {
                            findViewById = alertDialog.findViewById(R.id.activity_info_land);
                            textView2 = (TextView) alertDialog.findViewById(R.id.activity_text_land);
                        } else {
                            findViewById = alertDialog.findViewById(R.id.activity_info);
                            textView2 = (TextView) alertDialog.findViewById(R.id.activity_text);
                        }
                        findViewById.setVisibility(0);
                        textView2.setText(this.B.e);
                        textView2.setVisibility(0);
                        Map hashMap = new HashMap();
                        hashMap.put(s.ORIGIN, "5");
                        i.a("event_A202", hashMap, ReaderApplication.getApplicationImp());
                    }
                    c.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ NativeAudioBookPlayerActivity c;

                        public void onClick(View view) {
                            this.c.N = checkBox.isChecked();
                            alertDialog.c();
                            this.c.c();
                            if (this.c.m == 2000000804) {
                                if (this.c.k == 1001) {
                                    i.a("event_B262", null, this.c.getContext());
                                }
                            } else if (this.c.k == 1001) {
                                i.a("event_B261", null, this.c.getContext());
                            } else {
                                i.a("event_B260", null, this.c.getContext());
                            }
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

    private void w() {
        try {
            if (com.qq.reader.common.login.c.b()) {
                OnlineChapter onlineChapter = l.a.o().a;
                int price = (int) ((onlineChapter.getPrice() * ((float) this.ah.s())) / 100.0f);
                com.qq.reader.common.monitor.debug.c.e(this.r, "购买单章价格:" + price);
                List arrayList = new ArrayList();
                arrayList.add(Integer.valueOf(onlineChapter.getChapterId()));
                com.qq.reader.cservice.buy.chapter.a aVar = new com.qq.reader.cservice.buy.chapter.a(this.ac, arrayList, price, this);
                aVar.a(new com.qq.reader.cservice.buy.chapter.b(this) {
                    final /* synthetic */ NativeAudioBookPlayerActivity a;

                    {
                        this.a = r1;
                    }

                    public void a(ChapterPayResult chapterPayResult) {
                        com.qq.reader.common.monitor.debug.c.e(this.a.r, "购买单章成功");
                        Message obtain = Message.obtain();
                        obtain.what = 1238;
                        obtain.obj = chapterPayResult;
                        this.a.mHandler.sendMessage(obtain);
                    }

                    public void b(ChapterPayResult chapterPayResult) {
                        com.qq.reader.common.monitor.debug.c.e(this.a.r, "购买单章失败");
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
                aVar.start();
                x();
                return;
            }
            setLoginNextTask(new com.qq.reader.common.login.a(this) {
                final /* synthetic */ NativeAudioBookPlayerActivity a;

                {
                    this.a = r1;
                }

                public void a(int i) {
                    switch (i) {
                        case 1:
                            this.a.w();
                            return;
                        default:
                            return;
                    }
                }
            });
            startLogin();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void c() {
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

    private void x() {
        try {
            if (this.aw == null || !this.aw.isShowing()) {
                this.aw = ProgressDialog.show(this, "", "正在购买，请稍候...", true);
                this.aw.setCanceledOnTouchOutside(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean y() {
        try {
            if (this.aw != null && this.aw.isShowing()) {
                this.aw.cancel();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void d() {
        int a = this.B.a();
        if (a > 0) {
            if (this.i <= 0 || a >= this.i) {
                String k = this.ac.k();
                if (this.k != 1001) {
                    w();
                } else if (!TextUtils.isEmpty(k)) {
                    a(k, this.j);
                }
            }
        }
    }

    private void z() {
        this.af = new g(getApplicationContext(), this.ac);
        this.af.a(getHandler());
        this.af.a(true);
    }

    private void A() {
        if (this.af != null) {
            this.af.d();
            this.af = null;
        }
    }

    private void b(final String str) {
        int i = -1;
        if (this.af != null) {
            i = this.af.b();
        }
        if (i == 3) {
            ReaderTask queryAudioChapterBuyInfoTask = new QueryAudioChapterBuyInfoTask(str);
            queryAudioChapterBuyInfoTask.registerNetTaskListener(new com.qq.reader.common.readertask.ordinal.c(this) {
                final /* synthetic */ NativeAudioBookPlayerActivity b;

                public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                    this.b.a(str, str, true);
                    this.b.q = str;
                    this.b.p = true;
                }

                public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                }
            });
            com.qq.reader.common.readertask.g.a().a(queryAudioChapterBuyInfoTask);
        } else if (i == 2) {
            com.qq.reader.common.readertask.g.a().a(new AudioAuthCheckTask(Long.parseLong(str), new com.qq.reader.common.readertask.ordinal.c(this) {
                final /* synthetic */ NativeAudioBookPlayerActivity b;

                public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                    this.b.a(str, str, false);
                    this.b.q = str;
                    this.b.p = true;
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

    private boolean B() {
        if (d.ay(this).contains("baidu")) {
            return true;
        }
        return false;
    }

    private void C() {
        File file = new File(com.qq.reader.common.c.a.aX + "bdttsplugin.zip");
        if (!file.exists()) {
            com.qq.reader.common.readertask.g.a().a(new ReaderDownloadTask(getContext(), file.getPath(), "http://wfqqreader.3g.qq.com/bd/20170308/bdttsplugin.zip"));
        }
    }

    private void D() {
        Intent intent = new Intent();
        intent.setClass(this, PlugInDefaultActivity.class);
        intent.putExtra("PLUGIN_TYPE", "7");
        startActivity(intent);
    }
}
