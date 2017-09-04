package com.qq.reader.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Message;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.StrikethroughSpan;
import android.util.SparseArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.charge.voucher.a.a;
import com.qq.reader.common.db.handle.k;
import com.qq.reader.common.db.handle.v;
import com.qq.reader.common.download.task.state.TaskStateEnum;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.monitor.j;
import com.qq.reader.common.readertask.ReaderTask;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.ordinal.ReaderShortTask;
import com.qq.reader.common.readertask.protocol.QueryUserBalanceTask;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.web.js.JSPay;
import com.qq.reader.cservice.buy.chapter.ChapterPayResult;
import com.qq.reader.cservice.download.audio.AudioAuthCheckTask;
import com.qq.reader.cservice.download.chapter.b;
import com.qq.reader.cservice.onlineread.OnlineTag;
import com.qq.reader.framework.mark.Mark;
import com.qq.reader.module.audio.loader.QueryAudioChapterBuyInfoTask;
import com.qq.reader.module.bookchapter.online.OnlineChapter;
import com.qq.reader.module.bookchapter.online.f;
import com.qq.reader.module.bookchapter.online.g;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.qplugin.local.TingBookMark;
import com.qq.reader.view.AlertDialog;
import com.qq.reader.view.EmptyView;
import com.qq.reader.view.af;
import com.qq.reader.view.m;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public class AudioBookDownloadActivity extends ReaderBaseActivity implements b {
    private TextView A;
    private TextView B;
    private TextView C;
    private TextView D;
    private TextView E;
    private TextView F;
    private TextView G;
    private TextView H;
    private TextView I;
    private Button J;
    private Button K;
    private final a L = new a();
    private ProgressBar M;
    private ProgressBar N;
    private View O;
    private List<Integer> P = new ArrayList();
    private List<Integer> Q = new ArrayList();
    private List<OnlineChapter> R = new ArrayList();
    private OnlineTag S = null;
    private View T = null;
    private TaskStateEnum[] U = new TaskStateEnum[]{TaskStateEnum.Installing, TaskStateEnum.Removed};
    private BroadcastReceiver V = new BroadcastReceiver(this) {
        final /* synthetic */ AudioBookDownloadActivity a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("com.qq.reader.chapter.DownloadSucess".equalsIgnoreCase(action)) {
                Message obtainMessage = this.a.getHandler().obtainMessage(21007);
                obtainMessage.obj = intent.getIntegerArrayListExtra("ONLINE_DOWNLOAD_CHAP_KEY");
                obtainMessage.sendToTarget();
            } else if ("com.qq.reader.chapter.DownloadFailed".equalsIgnoreCase(action)) {
                this.a.getHandler().obtainMessage(21008).sendToTarget();
            } else if ("com.qq.reader.chapter.Restart".equalsIgnoreCase(action)) {
                action = intent.getStringExtra("bid");
                if (action != null && this.a.S != null && action.equals(this.a.S.k())) {
                    this.a.getHandler().obtainMessage(21010).sendToTarget();
                }
            }
        }
    };
    private boolean W = false;
    private ProgressDialog X;
    private com.qq.reader.module.audio.a.a Y;
    private af Z;
    Context a;
    private Dialog aa = null;
    int b = 0;
    boolean c = false;
    private m d;
    private f e;
    private g f;
    private com.qq.reader.module.bookchapter.online.a g;
    private ExpandableListView h;
    private Button i;
    private Button j;
    private String k;
    private String l;
    private String m;
    private String n;
    private int o = -1;
    private TextView p;
    private EmptyView q;
    private LinearLayout r;
    private View s = null;
    private TextView t = null;
    private View u = null;
    private TextView v = null;
    private TextView w = null;
    private View x = null;
    private AlertDialog y = null;
    private RelativeLayout z;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.chapter_pay_choose);
        this.a = this;
        f();
        if (getIntent().getBooleanExtra("chaper_pay_orientation_vertical", false)) {
            b(1);
        } else {
            b(d.Y(getApplicationContext()));
        }
        this.h = (ExpandableListView) findViewById(R.id.chapter_choose_list);
        this.g = new com.qq.reader.module.bookchapter.online.a(this, this.S.F());
        this.g.c(this.S.G());
        this.g.a(new com.qq.reader.module.bookchapter.online.a.a(this) {
            final /* synthetic */ AudioBookDownloadActivity a;

            {
                this.a = r1;
            }

            public void onClick(int i, boolean z) {
                if (z) {
                    this.a.h.expandGroup(i);
                } else {
                    this.a.h.collapseGroup(i);
                }
            }
        });
        this.h.setAdapter(this.g);
        this.h.setOnGroupClickListener(new OnGroupClickListener(this) {
            final /* synthetic */ AudioBookDownloadActivity a;

            {
                this.a = r1;
            }

            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long j) {
                this.a.g.a(i);
                return true;
            }
        });
        this.h.setOnChildClickListener(new OnChildClickListener(this) {
            final /* synthetic */ AudioBookDownloadActivity a;

            {
                this.a = r1;
            }

            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i2, long j) {
                this.a.g.a(i, i2);
                return true;
            }
        });
        ((ImageView) findViewById(R.id.profile_header_left_back)).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ AudioBookDownloadActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.m();
                this.a.finish();
            }
        });
        b();
        this.p = (TextView) findViewById(R.id.profile_header_title);
        this.p.setText("批量下载");
        this.k = getApplicationContext().getResources().getString(R.string.chapter_pay_selectall);
        this.l = getApplicationContext().getResources().getString(R.string.chapter_report_cancel);
        this.m = getApplicationContext().getResources().getString(R.string.chapter_pay_pause);
        this.n = getApplicationContext().getResources().getString(R.string.chapter_pay_resume);
        this.i = (Button) findViewById(R.id.profile_header_right_button);
        this.i.setVisibility(0);
        this.i.setText(R.string.chapter_pay_selectall);
        this.i.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ AudioBookDownloadActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                String charSequence = this.a.i.getText().toString();
                if (charSequence.equals(this.a.k)) {
                    this.a.i.setText(this.a.l);
                    this.a.d.f_();
                    com.qq.reader.common.readertask.g.a().a(new AnonymousClass1(this));
                    j.a(63, 1);
                    i.a("event_C196", null, this.a.getContext());
                } else if (charSequence.equals(this.a.l)) {
                    this.a.i.setText(this.a.k);
                    this.a.d.f_();
                    com.qq.reader.common.readertask.g.a().a(new AnonymousClass2(this));
                }
            }
        });
        this.j = (Button) findViewById(R.id.profile_header_right_button2);
        this.j.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ AudioBookDownloadActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                String charSequence = this.a.j.getText().toString();
                if (charSequence.equals(this.a.m)) {
                    i.a("event_C197", null, this.a.getContext());
                    com.qq.reader.cservice.download.audio.a.a().h();
                    this.a.j.setText(this.a.n);
                } else if (charSequence.equals(this.a.n)) {
                    com.qq.reader.cservice.download.audio.a.a().i();
                    this.a.j.setText(this.a.m);
                }
            }
        });
        this.r = (LinearLayout) findViewById(R.id.chapter_pay_loading);
        this.r.setVisibility(0);
        this.q = (EmptyView) findViewById(R.id.chapter_pay_empyt_layout);
        this.q.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ AudioBookDownloadActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.q.setVisibility(8);
                this.a.r.setVisibility(0);
                this.a.l();
            }
        });
        this.s = findViewById(R.id.chapter_pay_choose_bottom);
        this.t = (TextView) findViewById(R.id.chapter_pay_choose_bottom_text);
        this.u = findViewById(R.id.chapter_pay_choose_bottom_price_info);
        this.v = (TextView) findViewById(R.id.chapter_pay_choose_bottom_price_info_discount);
        this.w = (TextView) findViewById(R.id.chapter_pay_choose_bottom_price_info_original);
        this.u.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ AudioBookDownloadActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                j.a(58, 1);
                i.a("event_B59", null, this.a.a);
                StatisticsManager.a().a("event_B59", null);
                this.a.a(false);
            }
        });
        this.y = new AlertDialog.a(this).c(R.drawable.alert_dialog_icon).a((CharSequence) "提示").b((CharSequence) "下载失败，可免费重新下载").a((CharSequence) "下载", new DialogInterface.OnClickListener(this) {
            final /* synthetic */ AudioBookDownloadActivity a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                this.a.a(true);
            }
        }).b((int) R.string.alert_dialog_cancel, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ AudioBookDownloadActivity a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                this.a.g.b(true);
                this.a.i.setClickable(true);
                this.a.g.notifyDataSetInvalidated();
                this.a.c();
            }
        }).a();
        this.x = findViewById(R.id.chapter_pay_choose_bottom_download);
        this.O = findViewById(R.id.chapter_pay_choose_download_mask);
        this.T = findViewById(R.id.chapter_pay_choose_bottom_download_switchbg);
        this.T.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ AudioBookDownloadActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.finish();
            }
        });
        android.support.v4.content.d.a((Context) this).a(this.V, new IntentFilter("com.qq.reader.chapter.DownloadSucess"));
        android.support.v4.content.d.a((Context) this).a(this.V, new IntentFilter("com.qq.reader.chapter.DownloadFailed"));
        android.support.v4.content.d.a((Context) this).a(this.V, new IntentFilter("com.qq.reader.chapter.Restart"));
        a();
        this.d = new m(this);
        this.d.a("请稍候…");
        com.qq.reader.cservice.download.audio.a.a().a(this.mHandler);
    }

    public void a() {
        com.qq.reader.common.readertask.g.a().a(new QueryUserBalanceTask(new QueryUserBalanceTask.a(this) {
            final /* synthetic */ AudioBookDownloadActivity a;

            {
                this.a = r1;
            }

            public void a(a aVar) {
                this.a.L.a(aVar);
                if (this.a.z != null) {
                    this.a.mHandler.sendEmptyMessage(21015);
                }
            }

            public void a() {
                this.a.L.c();
                if (this.a.z != null) {
                    this.a.mHandler.sendEmptyMessage(21015);
                }
            }
        }, this.S.k(), 2));
    }

    public void b() {
        this.J = (Button) findViewById(R.id.btn_buy_confirm);
        this.M = (ProgressBar) findViewById(R.id.pb_user_balance);
        this.z = (RelativeLayout) findViewById(R.id.rl_pay_choose_bottom_price_info);
        this.z.setVisibility(8);
        this.G = (TextView) findViewById(R.id.tv_balance);
        this.F = (TextView) findViewById(R.id.tv_discount_msg);
        this.B = (TextView) findViewById(R.id.tv_origin_price);
        this.C = (TextView) findViewById(R.id.tv_price);
        this.A = (TextView) findViewById(R.id.tv_selected_chapter_count);
        this.D = (TextView) findViewById(R.id.textView18);
        this.E = (TextView) findViewById(R.id.textViewDownloadType);
        ((TextView) findViewById(R.id.textView14)).setText("集");
        this.H = (TextView) findViewById(R.id.textView14a);
        this.K = (Button) findViewById(R.id.btn_charge_ensure_money);
        this.I = (TextView) findViewById(R.id.limitText);
    }

    public synchronized void c() {
        if (this.e != null) {
            int z;
            View findViewById;
            TextView textView;
            this.D.setVisibility(0);
            this.E.setVisibility(8);
            int f = this.g.f();
            int b = this.g.b();
            int j = this.e.j();
            if (j == 2 && b == 0) {
                z = this.e.y().z();
            } else {
                z = b;
            }
            float round = ((float) Math.round(this.g.c() * 100.0f)) / 100.0f;
            int e = this.g.e();
            Object d = this.g.d();
            CharSequence charSequence = "0";
            this.s.setVisibility(4);
            this.u.setVisibility(4);
            int a = this.L.a();
            if (a < 0) {
                this.G.setVisibility(8);
                this.M.setVisibility(0);
            } else {
                this.G.setVisibility(0);
                this.M.setVisibility(8);
            }
            this.G.setText(this.L.b());
            this.C.setTextSize(18.0f);
            if (this.L.d > 0) {
                com.qq.reader.common.charge.voucher.b.a(this, this.G, new OnClickListener(this) {
                    final /* synthetic */ AudioBookDownloadActivity a;

                    class AnonymousClass1 extends ReaderShortTask {
                        final /* synthetic */ AnonymousClass26 this$1;

                        AnonymousClass1(AnonymousClass26 anonymousClass26) {
                            this.this$1 = anonymousClass26;
                        }

                        public void run() {
                            super.run();
                            c.d("CHECK", " check in thread");
                            this.this$1.a.g.a(true);
                            this.this$1.a.mHandler.post(new Runnable(this) {
                                final /* synthetic */ AnonymousClass1 a;

                                {
                                    this.a = r1;
                                }

                                public void run() {
                                    c.d("CHECK", " notify data set change");
                                    if (!this.a.this$1.a.isFinishing()) {
                                        this.a.this$1.a.d.dismiss();
                                    }
                                    this.a.this$1.a.g.notifyDataSetChanged();
                                }
                            });
                        }
                    }

                    class AnonymousClass2 extends ReaderShortTask {
                        final /* synthetic */ AnonymousClass26 this$1;

                        AnonymousClass2(AnonymousClass26 anonymousClass26) {
                            this.this$1 = anonymousClass26;
                        }

                        public void run() {
                            super.run();
                            c.d("CHECK", " uncheck in thread");
                            this.this$1.a.g.a(false);
                            this.this$1.a.mHandler.post(new Runnable(this) {
                                final /* synthetic */ AnonymousClass2 a;

                                {
                                    this.a = r1;
                                }

                                public void run() {
                                    c.d("CHECK", " notify data set change");
                                    if (!this.a.this$1.a.isFinishing()) {
                                        this.a.this$1.a.d.dismiss();
                                    }
                                    this.a.this$1.a.g.notifyDataSetChanged();
                                }
                            });
                        }
                    }

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                    }
                }, new OnDismissListener(this) {
                    final /* synthetic */ AudioBookDownloadActivity a;

                    {
                        this.a = r1;
                    }

                    public void onDismiss(DialogInterface dialogInterface) {
                    }
                }, this.L.g);
            }
            if (getResources().getConfiguration().orientation == 2) {
                findViewById = findViewById(R.id.activity_info_land);
                textView = (TextView) findViewById(R.id.activity_text_land);
            } else {
                findViewById = findViewById(R.id.activity_info);
                textView = (TextView) findViewById(R.id.activity_text);
            }
            findViewById.setVisibility(8);
            textView.setVisibility(8);
            b(this.g.k(), this.g.j());
            boolean z2 = true;
            final int min = Math.min(this.g.b(), this.g.e()) - a;
            if (min > 0) {
                z2 = false;
            }
            com.qq.reader.common.charge.voucher.b.a(this.J, this.K, z2);
            this.C.setText(charSequence);
            if (j == 1) {
                this.C.setTextSize(14.0f);
                this.C.setText(R.string.paypage_pay_free);
                this.D.setVisibility(8);
            } else if (this.S.G()) {
                this.C.setText(R.string.audio_paypage_pay_all_ok);
                this.C.setTextSize(14.0f);
                this.D.setVisibility(8);
            }
            if (f == 0) {
                this.F.setVisibility(8);
                this.B.setVisibility(8);
                this.A.setText(charSequence);
                this.J.setBackgroundResource(R.drawable.chapter_pay_bt_bg_unenable);
                this.J.setEnabled(false);
                this.J.setText(getString(R.string.chapter2_buy_tip_none_selected));
                if (this.I.getVisibility() == 0) {
                    this.I.setVisibility(8);
                }
            } else {
                this.J.setEnabled(true);
                this.A.setText(String.valueOf(f));
                if (e <= 0) {
                    this.B.setVisibility(8);
                    this.C.setText(charSequence);
                    this.F.setVisibility(8);
                    this.J.setBackgroundResource(R.drawable.new_button);
                    this.J.setText(getString(R.string.chapter_buy_tip_free_download) + "(" + round + "MB)");
                    i.a("event_C204", null, this.a);
                    this.J.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ AudioBookDownloadActivity a;

                        {
                            this.a = r1;
                        }

                        public void onClick(View view) {
                            j.a(59, 1);
                            i.a("event_C207", null, this.a.a);
                            StatisticsManager.a().a("event_B60", null);
                            this.a.a(false);
                        }
                    });
                } else {
                    this.J.setBackgroundResource(R.drawable.selector_orange_button);
                    this.J.setText(getString(R.string.chapter_buy_confirm_buy_and_dl) + "(" + round + "MB)");
                    if (this.c && a >= 0 && this.b > 0 && a > this.b) {
                        this.c = false;
                        a(false);
                    } else if (a < 0 || a(a)) {
                        i.a("event_C203", null, ReaderApplication.getApplicationImp());
                        this.J.setText(getString(R.string.chapter_buy_confirm_buy_and_dl) + "(" + round + "MB)");
                        this.J.setOnClickListener(new OnClickListener(this) {
                            final /* synthetic */ AudioBookDownloadActivity a;

                            {
                                this.a = r1;
                            }

                            public void onClick(View view) {
                                j.a(58, 1);
                                i.a("event_C206", null, this.a.a);
                                StatisticsManager.a().a("event_B59", null);
                                this.a.a(false);
                            }
                        });
                    } else {
                        i.a("event_C205", null, ReaderApplication.getApplicationImp());
                        if (!ao.s(this.L.e)) {
                            findViewById.setVisibility(0);
                            textView.setText(this.L.e);
                            textView.setVisibility(0);
                            Map hashMap = new HashMap();
                            hashMap.put(s.ORIGIN, "3");
                            i.a("event_A202", hashMap, getApplicationContext());
                        }
                        this.J.setText(getString(R.string.alert_dialog_buy_balance_charge_other_count));
                        this.J.setOnClickListener(new OnClickListener(this) {
                            final /* synthetic */ AudioBookDownloadActivity a;

                            {
                                this.a = r1;
                            }

                            public void onClick(View view) {
                                i.a("event_C208", null, ReaderApplication.getApplicationImp());
                                this.a.b(null);
                            }
                        });
                        com.qq.reader.common.charge.voucher.b.a(this.K, min);
                        this.K.setOnClickListener(new OnClickListener(this) {
                            final /* synthetic */ AudioBookDownloadActivity b;

                            public void onClick(View view) {
                                new JSPay(this.b).startChargeDirectly(this.b, min);
                            }
                        });
                    }
                }
                this.C.setTextSize(18.0f);
                if (j == 1) {
                    this.C.setTextSize(14.0f);
                    this.C.setText(R.string.paypage_pay_free);
                    this.D.setVisibility(8);
                    if (this.I.getVisibility() == 0) {
                        this.I.setVisibility(8);
                    }
                } else if (this.S.G()) {
                    this.C.setTextSize(14.0f);
                    this.C.setText(R.string.audio_paypage_pay_all_ok);
                    this.D.setVisibility(8);
                    if (this.I.getVisibility() == 0) {
                        this.I.setVisibility(8);
                    }
                } else if (z > e) {
                    this.b = e;
                    Object obj = String.valueOf(z) + "书币";
                    CharSequence spannableString = new SpannableString(obj);
                    spannableString.setSpan(new StrikethroughSpan(), 0, obj.length(), 17);
                    this.B.setVisibility(0);
                    this.B.setText(spannableString);
                    this.C.setVisibility(0);
                    this.C.setText(String.valueOf(e));
                    if (j == 2) {
                        this.E.setVisibility(0);
                    }
                    if (TextUtils.isEmpty(d)) {
                        this.F.setVisibility(8);
                    } else {
                        this.F.setVisibility(0);
                        this.F.setText("(" + d + ")");
                    }
                    if (this.e.y().Q() != 2000000804 || z <= 0 || e <= 0) {
                        this.I.setVisibility(8);
                    } else {
                        this.I.setVisibility(0);
                    }
                } else {
                    this.B.setVisibility(8);
                    this.F.setVisibility(8);
                    this.b = z;
                    this.C.setText(String.valueOf(z));
                    if (this.e.y().Q() != 2000000804 || z <= 0) {
                        this.I.setVisibility(8);
                    } else {
                        this.I.setVisibility(0);
                    }
                }
            }
        }
    }

    public boolean a(int i) {
        int b = this.g.b();
        if (b != this.g.e()) {
            b = this.g.e();
        }
        return b <= i;
    }

    protected void onResume() {
        super.onResume();
        if (this.Y != null) {
            this.Y.b();
        }
    }

    protected void onPause() {
        super.onPause();
        if (this.Y != null) {
            this.Y.c();
        }
    }

    private void b(int i) {
        setRequestedOrientation(i);
    }

    private void f() {
        this.S = (OnlineTag) getIntent().getExtras().getParcelable("com.qq.reader.OnlineTag");
        this.Y = new com.qq.reader.module.audio.a.a(this.S, getApplicationContext());
        this.f = new g(getApplicationContext(), this.S);
        this.f.a(getHandler());
        this.f.a(true);
        this.N = (ProgressBar) findViewById(R.id.profile_header_progress);
        g();
    }

    private void g() {
        final String k = this.S.k();
        if (this.f != null) {
            this.o = this.f.b();
        }
        if (this.o == 3) {
            ReaderTask queryAudioChapterBuyInfoTask = new QueryAudioChapterBuyInfoTask(k);
            queryAudioChapterBuyInfoTask.registerNetTaskListener(new com.qq.reader.common.readertask.ordinal.c(this) {
                final /* synthetic */ AudioBookDownloadActivity b;

                public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                    this.b.a(str, k, true);
                }

                public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                    this.b.mHandler.sendEmptyMessage(10000505);
                }
            });
            com.qq.reader.common.readertask.g.a().a(queryAudioChapterBuyInfoTask);
        } else if (this.o == 2) {
            com.qq.reader.common.readertask.g.a().a(new AudioAuthCheckTask(Long.parseLong(k), new com.qq.reader.common.readertask.ordinal.c(this) {
                final /* synthetic */ AudioBookDownloadActivity b;

                public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                    this.b.a(str, k, false);
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
                    List a = ao.a(jSONObject.optString("cids"));
                    if (a != null) {
                        k.a(getApplicationContext()).a(str2, a);
                        a = k.a(getApplicationContext()).a(str2);
                        obtain = Message.obtain();
                        obtain.what = 21011;
                        obtain.obj = a;
                        this.mHandler.sendMessageDelayed(obtain, 500);
                    }
                }
            } catch (Exception e) {
                c.e("Err", e.getMessage());
            }
        } else if (new JSONObject(str).optInt("code") == 1) {
            ArrayList arrayList = new ArrayList();
            obtain = Message.obtain();
            obtain.what = 21101;
            obtain.obj = arrayList;
            this.mHandler.sendMessage(obtain);
        }
    }

    private void a(List<Integer> list) {
        List<com.qq.reader.cservice.buy.chapter.d> g = this.g.g();
        for (int i = 0; i < list.size(); i++) {
            int intValue = ((Integer) list.get(i)).intValue();
            for (com.qq.reader.cservice.buy.chapter.d dVar : g) {
                if (intValue == dVar.e()) {
                    dVar.a(true);
                    break;
                }
            }
        }
        this.g.notifyDataSetChanged();
    }

    public void a(boolean z, boolean z2) {
        if (z) {
            this.i.setText(this.l);
        } else {
            this.i.setText(this.k);
        }
        if (com.qq.reader.cservice.download.audio.a.a().b(this.S.k())) {
            this.j.setVisibility(0);
            this.j.setText(this.m);
        } else if (com.qq.reader.cservice.download.audio.a.a().a(this.S.k())) {
            this.j.setVisibility(0);
            this.j.setText(this.n);
        } else {
            this.j.setVisibility(8);
        }
        if (z2) {
            this.i.setVisibility(8);
            this.s.setVisibility(8);
            return;
        }
        this.i.setVisibility(0);
    }

    private void h() {
        this.x.setVisibility(0);
        ((TextView) findViewById(R.id.chapter_pay_choose_bottom_download_progress_txt)).setText("听书下载中");
        this.O.setVisibility(0);
        this.s.setVisibility(4);
        this.u.setVisibility(4);
        this.g.b(false);
        this.i.setClickable(false);
        this.g.notifyDataSetInvalidated();
    }

    private void i() {
        this.g.b(true);
        this.i.setClickable(true);
    }

    private void j() {
        this.s.setVisibility(4);
        this.u.setVisibility(4);
        this.x.setVisibility(4);
        this.O.setVisibility(8);
        this.y.show();
    }

    private synchronized void k() {
        if (com.qq.reader.common.db.handle.i.c().e(String.valueOf(this.S.k())) == null) {
            new Thread(new Runnable(this) {
                final /* synthetic */ AudioBookDownloadActivity a;

                {
                    this.a = r1;
                }

                public void run() {
                    OnlineTag f = v.b().f(this.a.S.k());
                    if (f == null) {
                        f = this.a.S;
                        f.a(0);
                        f.b(System.currentTimeMillis());
                        f.c(1);
                        v.b().b(f);
                    }
                    Mark tingBookMark = new TingBookMark(Long.parseLong(this.a.S.k()), this.a.S.b());
                    tingBookMark.setPercentStr("0.0%").setAuthor(f.o()).setDescriptionStr("");
                    tingBookMark.setHasNewContent(false);
                    tingBookMark.setId(f.k());
                    tingBookMark.setBookId(Long.valueOf(f.k()).longValue());
                    tingBookMark.setCoverUrl(f.u());
                    com.qq.reader.common.db.handle.j.a().a(new com.qq.reader.common.monitor.a.a(this.a.S.k(), this.a.S.y()));
                    com.qq.reader.common.db.handle.i.c().a(tingBookMark, true);
                    d.h(this.a.a.getApplicationContext(), String.valueOf(tingBookMark.getBookId()));
                    com.qq.reader.common.db.handle.i.c();
                    com.qq.reader.common.imageloader.c.a(this.a.a).c(f.u());
                }
            }).start();
        }
    }

    private void l() {
        this.f = new g(getApplicationContext(), this.S);
        this.f.a(getHandler());
        this.f.a(true);
    }

    private void m() {
        if (this.f != null) {
            this.f.d();
            this.f = null;
        }
    }

    protected boolean handleMessageImp(Message message) {
        int k;
        ChapterPayResult chapterPayResult;
        Bundle bundle;
        switch (message.what) {
            case 21000:
                this.e = (f) message.obj;
                if (message.arg1 == 1) {
                }
                if (this.o < 0) {
                    g();
                }
                if (this.W && message.arg2 != 2) {
                    if (!(this.g == null || this.e == null)) {
                        this.g.b(this.e);
                        break;
                    }
                }
                this.g.a(this.e);
                this.g.notifyDataSetChanged();
                this.r.setVisibility(8);
                this.q.setVisibility(8);
                this.z.setVisibility(0);
                this.h.setVisibility(0);
                k = this.e.k();
                if (k <= 0) {
                    k = 20;
                }
                k = this.S.g() / k;
                if (k >= this.g.getGroupCount()) {
                    k = this.g.getGroupCount() - 1;
                }
                if (k >= 0) {
                    this.h.setSelection(k);
                    this.g.a(k);
                }
                this.W = true;
                break;
                break;
            case 21001:
                this.r.setVisibility(8);
                if (!this.W) {
                    this.q.setVisibility(0);
                    this.h.setVisibility(8);
                    this.i.setVisibility(8);
                    this.z.setVisibility(8);
                }
                m();
                break;
            case 21004:
                chapterPayResult = (ChapterPayResult) message.obj;
                a(chapterPayResult.getPayedChapters());
                this.g.a(chapterPayResult.getPayedChapters());
                o();
                break;
            case 21005:
                o();
                chapterPayResult = (ChapterPayResult) message.obj;
                a(chapterPayResult.getPayedChapters());
                this.g.a(chapterPayResult.getPayedChapters());
                c();
                Bundle bundle2 = new Bundle();
                bundle2.putString("message", chapterPayResult.getResultStr());
                bundle2.putSerializable("com.qq.reader.pay.ChapterPayResult", chapterPayResult);
                a(1002, bundle2);
                break;
            case 21006:
                chapterPayResult = (ChapterPayResult) message.obj;
                int code = chapterPayResult.getCode();
                Bundle bundle3 = new Bundle();
                bundle3.putString("message", chapterPayResult.getResultStr());
                bundle3.putSerializable("com.qq.reader.pay.ChapterPayResult", chapterPayResult);
                if (code != -6) {
                    if (code != -2) {
                        o();
                        a(1000, bundle3);
                        break;
                    }
                    o();
                    a(1003, bundle3);
                    break;
                }
                o();
                a(1001, bundle3);
                break;
            case 21007:
                ArrayList arrayList = new ArrayList();
                if (message.obj != null) {
                    arrayList = (ArrayList) message.obj;
                }
                this.g.a(arrayList);
                i();
                a("成功下载" + arrayList.size() + "集");
                this.x.setVisibility(4);
                this.O.setVisibility(8);
                this.L.c();
                a();
                this.g.notifyDataSetChanged();
                break;
            case 21008:
                this.g.a();
                j();
                this.g.notifyDataSetChanged();
                String str = "章节下载失败";
                if (message.obj != null) {
                    k = message.arg1;
                    str = message.obj.toString();
                }
                a(str);
                break;
            case 21009:
                h();
                break;
            case 21010:
                if (this.y != null && this.y.isShowing()) {
                    this.y.cancel();
                    this.g.b(true);
                    this.i.setClickable(true);
                    this.g.notifyDataSetInvalidated();
                    c();
                    break;
                }
            case 21011:
                List<Integer> list = (List) message.obj;
                SparseArray h = this.g.h();
                Collection arrayList2 = new ArrayList();
                for (Integer num : list) {
                    com.qq.reader.cservice.buy.chapter.d dVar = (com.qq.reader.cservice.buy.chapter.d) h.get(num.intValue());
                    if (dVar != null) {
                        if (dVar.f()) {
                            arrayList2.add(num);
                        } else {
                            dVar.a(true);
                        }
                    }
                }
                list.removeAll(arrayList2);
                this.g.a((List) list);
                this.g.notifyDataSetChanged();
                break;
            case 21014:
                bundle = new Bundle();
                bundle.putString("message", getString(R.string.dialog_check_net));
                a(1004, bundle);
                break;
            case 21015:
                c();
                return true;
            case 21101:
                if (this.S != null) {
                    this.S.e(true);
                }
                if (this.g != null) {
                    this.g.c(true);
                    this.g.notifyDataSetChanged();
                }
                return true;
            case 1500001:
                if (this.e != null) {
                    if (message.arg1 == -6) {
                        af.a(this.a, (CharSequence) "存储已满", 0).a();
                    }
                    this.g.notifyDataSetChanged();
                    break;
                }
                return false;
            case 6000003:
                bundle = new Bundle();
                bundle.putString("message", "登录态失效，请重新登录！");
                o();
                a(1003, bundle);
                break;
        }
        return false;
    }

    private void n() {
        if (this.X == null || !this.X.isShowing()) {
            this.X = ProgressDialog.show(this, "", "请稍候...", true);
            this.X.setCancelable(false);
            this.X.setCanceledOnTouchOutside(false);
        }
    }

    private boolean o() {
        if (this.X == null || !this.X.isShowing()) {
            return false;
        }
        try {
            this.X.cancel();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public void a(String str) {
        if (this.Z == null) {
            this.Z = af.a(getApplicationContext(), (CharSequence) "", 0);
        }
        this.Z.a((CharSequence) str);
        this.Z.a();
    }

    private void a(boolean z) {
        if (z) {
            this.Y.a((b) this);
            this.Y.c(this.P);
            return;
        }
        this.P.clear();
        this.Q.clear();
        this.R.clear();
        this.Y.a((b) this);
        boolean t = this.e.t();
        List<com.qq.reader.cservice.buy.chapter.d> g = this.g.g();
        int e = this.g.e();
        this.R = new ArrayList();
        for (com.qq.reader.cservice.buy.chapter.d dVar : g) {
            int e2 = dVar.e();
            this.P.add(Integer.valueOf(e2));
            this.R.add(dVar.a());
            if (!(!dVar.g() || t || this.S.G())) {
                this.Q.add(Integer.valueOf(e2));
            }
        }
        this.Y.b(this.P);
        this.Y.d(this.R);
        if (this.Q.size() > 0) {
            this.Y.a(this.Q, e);
            j.f += this.Q.size();
            n();
        } else {
            this.Y.c(this.P);
            j.g += this.P.size();
        }
        k();
    }

    public void a(ChapterPayResult chapterPayResult) {
        getHandler().obtainMessage(21004, chapterPayResult).sendToTarget();
    }

    public void b(ChapterPayResult chapterPayResult) {
        getHandler().obtainMessage(21005, chapterPayResult).sendToTarget();
    }

    public void c(ChapterPayResult chapterPayResult) {
        getHandler().obtainMessage(21006, chapterPayResult).sendToTarget();
    }

    public void d() {
        getHandler().obtainMessage(21009).sendToTarget();
    }

    public void e() {
        getHandler().obtainMessage(21014).sendToTarget();
    }

    public void a(int i, String str) {
        getHandler().obtainMessage(21008, i, 0, str).sendToTarget();
    }

    protected void a(int i, final Bundle bundle) {
        String str = "";
        if (bundle != null) {
            CharSequence string = bundle.getString("message");
        } else {
            Object obj = str;
        }
        if (this.aa != null && this.aa.isShowing()) {
            this.aa.cancel();
            this.aa = null;
        }
        switch (i) {
            case 1000:
                this.aa = new AlertDialog.a(this).c(17301543).a((int) R.string.dialog_shortcut_title).b(string).a((int) R.string.alert_dialog_ok, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ AudioBookDownloadActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).a();
                break;
            case 1001:
                this.aa = new AlertDialog.a(this).c(17301543).a((int) R.string.dialog_shortcut_title).b(string).a((int) R.string.charge, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ AudioBookDownloadActivity b;

                    public void onClick(DialogInterface dialogInterface, int i) {
                        this.b.b(((ChapterPayResult) bundle.getSerializable("com.qq.reader.pay.ChapterPayResult")).getChargeUrl());
                    }
                }).b((int) R.string.alert_dialog_cancel, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ AudioBookDownloadActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).a();
                break;
            case 1002:
                final ChapterPayResult chapterPayResult = (ChapterPayResult) bundle.getSerializable("com.qq.reader.pay.ChapterPayResult");
                this.aa = new AlertDialog.a(this).c(17301543).a((int) R.string.dialog_shortcut_title).b(string).a((int) R.string.confirm_go_on, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ AudioBookDownloadActivity b;

                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (chapterPayResult.getCode() == -5) {
                            this.b.Y.c(this.b.P);
                            this.b.a("开始下载章节");
                            return;
                        }
                        this.b.Y.a(chapterPayResult.getNeedBuyChapters(), chapterPayResult.getRealCost());
                        this.b.n();
                    }
                }).b((int) R.string.alert_dialog_cancel, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ AudioBookDownloadActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).a();
                break;
            case 1003:
                this.aa = new AlertDialog.a(this).c(17301543).a((int) R.string.dialog_shortcut_title).b(string).a((int) R.string.alert_dialog_ok, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ AudioBookDownloadActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        this.a.startLogin();
                    }
                }).a();
                break;
            case 1004:
                this.aa = new AlertDialog.a(this).c(17301543).a((int) R.string.dialog_shortcut_title).b(string).a((int) R.string.alert_dialog_keep_downloading, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ AudioBookDownloadActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        com.qq.reader.plugin.audiobook.core.b.a = 1;
                        this.a.Y.d();
                    }
                }).a();
                break;
        }
        if (this.aa != null && !isFinishing()) {
            this.aa.show();
        }
    }

    private void b(String str) {
        new JSPay(this).startCharge(this, this.b);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 20001 && i2 == 0) {
            this.L.c();
            c();
            this.c = true;
            a();
        }
    }

    protected void onDestroy() {
        k.b();
        m();
        android.support.v4.content.d.a((Context) this).a(this.V);
        super.onDestroy();
    }

    public void onLoginSuccess(final int i) {
        super.onLoginSuccess(i);
        runOnUiThread(new Runnable(this) {
            final /* synthetic */ AudioBookDownloadActivity b;

            public void run() {
                if (i == 3) {
                    try {
                        wait(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    this.b.a(false);
                }
            }
        });
    }

    public void onLoginError(String str, int i, int i2) {
        this.mHandler.sendEmptyMessage(6000003);
    }

    private void b(int i, String str) {
        if (i > 0) {
            this.H.setVisibility(0);
            String str2 = "";
            if (!TextUtils.isEmpty(str)) {
                str2 = "，";
            }
            this.H.setText("（返" + i + "抵扣券" + str2 + str + "）");
        } else if (this.e == null || this.e.y() == null || TextUtils.isEmpty(this.e.y().N())) {
            this.H.setVisibility(8);
        } else {
            this.H.setVisibility(0);
            if (TextUtils.isEmpty(str)) {
                this.H.setText("（" + this.e.y().N() + "）");
            } else {
                this.H.setText("（" + str + "）");
            }
        }
    }
}
