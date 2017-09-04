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
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.charge.voucher.a.a;
import com.qq.reader.common.db.handle.h;
import com.qq.reader.common.db.handle.k;
import com.qq.reader.common.db.handle.v;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.monitor.j;
import com.qq.reader.common.readertask.ordinal.ReaderDBTask;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.ordinal.ReaderShortTask;
import com.qq.reader.common.readertask.protocol.QueryChapterBuyInfoTask;
import com.qq.reader.common.readertask.protocol.QueryUserBalanceTask;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.web.js.JSPay;
import com.qq.reader.cservice.buy.chapter.ChapterPayResult;
import com.qq.reader.cservice.download.chapter.b;
import com.qq.reader.cservice.onlineread.OnlineTag;
import com.qq.reader.framework.mark.LocalMark;
import com.qq.reader.framework.mark.Mark;
import com.qq.reader.module.bookchapter.online.f;
import com.qq.reader.module.bookchapter.online.g;
import com.qq.reader.module.bookstore.qnative.item.s;
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

public class ChapterBatDownloadActivity extends ReaderBaseActivity implements b {
    private TextView A;
    private TextView B;
    private Button C;
    private Button D;
    private final a E = new a();
    private ProgressBar F;
    private ProgressBar G;
    private View H;
    private List<Integer> I = new ArrayList();
    private List<Integer> J = new ArrayList();
    private OnlineTag K = null;
    private View L = null;
    private BroadcastReceiver M = new BroadcastReceiver(this) {
        final /* synthetic */ ChapterBatDownloadActivity a;

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
                if (action != null && this.a.K != null && action.equals(this.a.K.k())) {
                    this.a.getHandler().obtainMessage(21010).sendToTarget();
                }
            }
        }
    };
    private boolean N = false;
    private ProgressDialog O;
    private com.qq.reader.cservice.download.chapter.a P;
    private af Q;
    private Dialog R = null;
    Context a;
    int b = 0;
    boolean c = false;
    private m d;
    private f e;
    private g f;
    private com.qq.reader.module.bookchapter.online.a g;
    private ExpandableListView h;
    private Button i;
    private String j;
    private String k;
    private TextView l;
    private EmptyView m;
    private View n;
    private View o = null;
    private TextView p = null;
    private View q = null;
    private TextView r = null;
    private TextView s = null;
    private View t = null;
    private AlertDialog u = null;
    private RelativeLayout v;
    private TextView w;
    private TextView x;
    private TextView y;
    private TextView z;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.chapter_pay_choose);
        this.a = this;
        try {
            f();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (getIntent().getBooleanExtra("chaper_pay_orientation_vertical", false)) {
            b(1);
        } else {
            b(d.Y(getApplicationContext()));
        }
        this.h = (ExpandableListView) findViewById(R.id.chapter_choose_list);
        this.g = new com.qq.reader.module.bookchapter.online.a(this);
        this.g.a(new com.qq.reader.module.bookchapter.online.a.a(this) {
            final /* synthetic */ ChapterBatDownloadActivity a;

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
            final /* synthetic */ ChapterBatDownloadActivity a;

            {
                this.a = r1;
            }

            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long j) {
                this.a.g.a(i);
                return true;
            }
        });
        this.h.setOnChildClickListener(new OnChildClickListener(this) {
            final /* synthetic */ ChapterBatDownloadActivity a;

            {
                this.a = r1;
            }

            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i2, long j) {
                this.a.g.a(i, i2);
                return true;
            }
        });
        ((ImageView) findViewById(R.id.profile_header_left_back)).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ChapterBatDownloadActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.l();
                this.a.finish();
            }
        });
        a();
        this.l = (TextView) findViewById(R.id.profile_header_title);
        this.l.setText("批量下载");
        this.j = getApplicationContext().getResources().getString(R.string.chapter_pay_selectall);
        this.k = getApplicationContext().getResources().getString(R.string.chapter_report_cancel);
        this.i = (Button) findViewById(R.id.profile_header_right_button);
        this.i.setVisibility(0);
        this.i.setText(R.string.chapter_pay_selectall);
        this.i.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ChapterBatDownloadActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                String charSequence = this.a.i.getText().toString();
                if (charSequence.equals(this.a.j)) {
                    i.a("event_F170", null, ReaderApplication.getApplicationImp());
                    this.a.i.setText(this.a.k);
                    long currentTimeMillis = System.currentTimeMillis();
                    this.a.d.f_();
                    c.e("SELECT", " START ");
                    com.qq.reader.common.readertask.g.a().a(new AnonymousClass1(this));
                    c.e("SELECT", " END USE TIME " + (System.currentTimeMillis() - currentTimeMillis) + "毫秒");
                    j.a(63, 1);
                } else if (charSequence.equals(this.a.k)) {
                    this.a.i.setText(this.a.j);
                    this.a.d.f_();
                    com.qq.reader.common.readertask.g.a().a(new AnonymousClass2(this));
                }
            }
        });
        this.n = findViewById(R.id.chapter_pay_loading);
        this.n.setVisibility(0);
        this.m = (EmptyView) findViewById(R.id.chapter_pay_empyt_layout);
        this.m.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ChapterBatDownloadActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.m.setVisibility(8);
                this.a.n.setVisibility(0);
                this.a.k();
            }
        });
        this.o = findViewById(R.id.chapter_pay_choose_bottom);
        this.p = (TextView) findViewById(R.id.chapter_pay_choose_bottom_text);
        this.q = findViewById(R.id.chapter_pay_choose_bottom_price_info);
        this.r = (TextView) findViewById(R.id.chapter_pay_choose_bottom_price_info_discount);
        this.s = (TextView) findViewById(R.id.chapter_pay_choose_bottom_price_info_original);
        this.q.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ChapterBatDownloadActivity a;

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
        this.u = new AlertDialog.a(this).c(R.drawable.alert_dialog_icon).a((CharSequence) "提示").b((CharSequence) "下载失败，可免费重新下载").a((CharSequence) "下载", new DialogInterface.OnClickListener(this) {
            final /* synthetic */ ChapterBatDownloadActivity a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                this.a.a(true);
            }
        }).b((int) R.string.alert_dialog_cancel, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ ChapterBatDownloadActivity a;

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
        this.t = findViewById(R.id.chapter_pay_choose_bottom_download);
        this.H = findViewById(R.id.chapter_pay_choose_download_mask);
        this.L = findViewById(R.id.chapter_pay_choose_bottom_download_switchbg);
        this.L.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ChapterBatDownloadActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.finish();
            }
        });
        android.support.v4.content.d.a((Context) this).a(this.M, new IntentFilter("com.qq.reader.chapter.DownloadSucess"));
        android.support.v4.content.d.a((Context) this).a(this.M, new IntentFilter("com.qq.reader.chapter.DownloadFailed"));
        android.support.v4.content.d.a((Context) this).a(this.M, new IntentFilter("com.qq.reader.chapter.Restart"));
        this.d = new m(this);
        this.d.a("请稍候…");
        b();
        i.a("event_F208", null, ReaderApplication.getApplicationImp());
    }

    public void a() {
        this.C = (Button) findViewById(R.id.btn_buy_confirm);
        this.F = (ProgressBar) findViewById(R.id.pb_user_balance);
        this.v = (RelativeLayout) findViewById(R.id.rl_pay_choose_bottom_price_info);
        this.A = (TextView) findViewById(R.id.tv_balance);
        this.z = (TextView) findViewById(R.id.tv_discount_msg);
        this.x = (TextView) findViewById(R.id.tv_origin_price);
        this.y = (TextView) findViewById(R.id.tv_price);
        this.w = (TextView) findViewById(R.id.tv_selected_chapter_count);
        this.B = (TextView) findViewById(R.id.textView14a);
        this.D = (Button) findViewById(R.id.btn_charge_ensure_money);
    }

    public void b() {
        com.qq.reader.common.readertask.g.a().a(new QueryUserBalanceTask(new QueryUserBalanceTask.a(this) {
            final /* synthetic */ ChapterBatDownloadActivity a;

            {
                this.a = r1;
            }

            public void a(a aVar) {
                this.a.E.a(aVar);
                if (this.a.v != null) {
                    this.a.mHandler.sendEmptyMessage(21015);
                }
            }

            public void a() {
                this.a.E.c();
                if (this.a.v != null) {
                    this.a.mHandler.sendEmptyMessage(21015);
                }
            }
        }, String.valueOf(this.K.k()), 0));
    }

    public void c() {
        View findViewById;
        TextView textView;
        int f = this.g.f();
        int b = this.g.b();
        int e = this.g.e();
        Object d = this.g.d();
        CharSequence charSequence = "0";
        this.o.setVisibility(4);
        this.q.setVisibility(4);
        this.t.setVisibility(4);
        this.H.setVisibility(8);
        int a = this.E.a();
        if (a < 0) {
            this.A.setVisibility(8);
            this.F.setVisibility(0);
        } else {
            this.A.setVisibility(0);
            this.F.setVisibility(8);
        }
        this.A.setText(this.E.b());
        if (this.E.d > 0) {
            com.qq.reader.common.charge.voucher.b.a(this, this.A, new OnClickListener(this) {
                final /* synthetic */ ChapterBatDownloadActivity a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                }
            }, new OnDismissListener(this) {
                final /* synthetic */ ChapterBatDownloadActivity a;

                class AnonymousClass1 extends ReaderShortTask {
                    final /* synthetic */ AnonymousClass24 this$1;

                    AnonymousClass1(AnonymousClass24 anonymousClass24) {
                        this.this$1 = anonymousClass24;
                    }

                    public void run() {
                        super.run();
                        c.d("CHECK", " check in thread ");
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
                    final /* synthetic */ AnonymousClass24 this$1;

                    AnonymousClass2(AnonymousClass24 anonymousClass24) {
                        this.this$1 = anonymousClass24;
                    }

                    public void run() {
                        super.run();
                        c.d("CHECK", "uncheck in thread ");
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

                public void onDismiss(DialogInterface dialogInterface) {
                }
            }, this.E.g);
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
        boolean z = true;
        final int min = Math.min(this.g.b(), this.g.e()) - a;
        if (min > 0) {
            z = false;
        }
        com.qq.reader.common.charge.voucher.b.a(this.C, this.D, z);
        if (f == 0) {
            this.y.setText(charSequence);
            this.z.setVisibility(8);
            this.x.setVisibility(8);
            this.w.setText(charSequence);
            this.C.setBackgroundResource(R.drawable.selector_orange_button);
            this.C.setEnabled(false);
            this.C.setText(getString(R.string.chapter_buy_tip_none_selected));
            return;
        }
        this.C.setEnabled(true);
        this.w.setText(String.valueOf(f));
        if (e <= 0) {
            this.x.setVisibility(8);
            this.y.setText(charSequence);
            this.z.setVisibility(8);
            this.C.setBackgroundResource(R.drawable.new_button);
            this.C.setText(getString(R.string.chapter_buy_tip_free_download));
            i.a("event_F172", null, ReaderApplication.getApplicationImp());
            this.C.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ ChapterBatDownloadActivity a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    i.a("event_F171", null, ReaderApplication.getApplicationImp());
                    j.a(59, 1);
                    i.a("event_B60", null, this.a.a);
                    Map hashMap = new HashMap();
                    hashMap.put(s.ORIGIN, String.valueOf(this.a.g.f()));
                    i.a("event_F190", hashMap, ReaderApplication.getApplicationImp());
                    StatisticsManager.a().a("event_B60", null);
                    this.a.a(false);
                }
            });
        } else {
            this.C.setBackgroundResource(R.drawable.selector_orange_button);
            this.C.setText(getString(R.string.chapter_buy_confirm_buy));
            i.a("event_F174", null, ReaderApplication.getApplicationImp());
            if (this.c && a >= 0 && this.b > 0 && a > this.b) {
                this.c = false;
                a(false);
                return;
            } else if (a < 0 || a(a)) {
                this.C.setText(getString(R.string.alert_dialog_buy_confirm));
                this.C.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ ChapterBatDownloadActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        j.a(58, 1);
                        i.a("event_F173", null, ReaderApplication.getApplicationImp());
                        i.a("event_B59", null, this.a.a);
                        StatisticsManager.a().a("event_B59", null);
                        this.a.a(false);
                    }
                });
            } else {
                i.a("event_F165", null, ReaderApplication.getApplicationImp());
                i.a("event_F167", null, ReaderApplication.getApplicationImp());
                i.a("event_B123", null, ReaderApplication.getApplicationImp());
                if (!ao.s(this.E.e)) {
                    findViewById.setVisibility(0);
                    textView.setText(this.E.e);
                    textView.setVisibility(0);
                    Map hashMap = new HashMap();
                    hashMap.put(s.ORIGIN, "3");
                    i.a("event_A202", hashMap, getApplicationContext());
                }
                this.C.setText(getString(R.string.alert_dialog_buy_balance_charge_other_count));
                this.C.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ ChapterBatDownloadActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        i.a("event_F168", null, ReaderApplication.getApplicationImp());
                        i.a("event_B124", null, ReaderApplication.getApplicationImp());
                        this.a.b(null);
                    }
                });
                com.qq.reader.common.charge.voucher.b.a(this.D, min);
                this.D.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ ChapterBatDownloadActivity b;

                    public void onClick(View view) {
                        i.a("event_F166", null, ReaderApplication.getApplicationImp());
                        new JSPay(this.b).startChargeDirectly(this.b, min);
                        this.b.setChargeNextTask(new com.qq.reader.common.charge.a(this) {
                            final /* synthetic */ AnonymousClass8 a;

                            {
                                this.a = r1;
                            }

                            public void a() {
                                this.a.b.a(false);
                            }

                            public void b() {
                            }

                            public void c() {
                            }
                        });
                    }
                });
            }
        }
        if (b != e) {
            this.b = e;
            Object obj = String.valueOf(b) + "书币";
            CharSequence spannableString = new SpannableString(obj);
            spannableString.setSpan(new StrikethroughSpan(), 0, obj.length(), 17);
            this.x.setVisibility(0);
            this.x.setText(spannableString);
            this.y.setVisibility(0);
            this.y.setText(String.valueOf(e));
            if (TextUtils.isEmpty(d)) {
                this.z.setVisibility(8);
                return;
            }
            this.z.setVisibility(0);
            this.z.setText("(" + d + ")");
            return;
        }
        this.x.setVisibility(8);
        this.z.setVisibility(8);
        this.b = b;
        this.y.setText(String.valueOf(b));
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
        if (this.P != null) {
            this.P.b();
        }
    }

    protected void onPause() {
        super.onPause();
        if (this.P != null) {
            this.P.c();
        }
    }

    private void b(int i) {
        setRequestedOrientation(i);
    }

    private void f() {
        this.K = (OnlineTag) getIntent().getExtras().getParcelable("com.qq.reader.OnlineTag");
        this.P = new com.qq.reader.cservice.download.chapter.a(this.K, getApplicationContext());
        this.f = new g(getApplicationContext(), this.K);
        this.f.a(getHandler());
        this.f.a(true);
        this.G = (ProgressBar) findViewById(R.id.profile_header_progress);
        final String k = this.K.k();
        final QueryChapterBuyInfoTask queryChapterBuyInfoTask = new QueryChapterBuyInfoTask(k);
        queryChapterBuyInfoTask.registerNetTaskListener(new com.qq.reader.common.readertask.ordinal.c(this) {
            final /* synthetic */ ChapterBatDownloadActivity b;

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    if (jSONObject.optInt("code") == 0) {
                        List a = ao.a(jSONObject.optString("cids"));
                        if (a != null) {
                            k.a(this.b.getApplicationContext()).a(k, a);
                            a = k.a(this.b.getApplicationContext()).a(k);
                            Message obtain = Message.obtain();
                            obtain.what = 21011;
                            obtain.obj = a;
                            this.b.mHandler.sendMessage(obtain);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
            }
        });
        com.qq.reader.common.readertask.g.a().a(new ReaderDBTask() {
            public void run() {
                super.run();
                if (h.b().b(k)) {
                    h.b().a(k + "", -1);
                }
                com.qq.reader.common.readertask.g.a().a(queryChapterBuyInfoTask);
            }
        });
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
            this.i.setText(this.k);
        } else {
            this.i.setText(this.j);
        }
        if (z2) {
            this.i.setVisibility(8);
            this.o.setVisibility(8);
            return;
        }
        this.i.setVisibility(0);
    }

    private void g() {
        this.t.setVisibility(0);
        this.H.setVisibility(0);
        this.o.setVisibility(4);
        this.q.setVisibility(4);
        this.g.b(false);
        this.i.setClickable(false);
        this.g.notifyDataSetInvalidated();
    }

    private void h() {
        this.g.b(true);
        this.i.setClickable(true);
    }

    private void i() {
        this.o.setVisibility(4);
        this.q.setVisibility(4);
        this.t.setVisibility(4);
        this.H.setVisibility(8);
        try {
            if (!isFinishing()) {
                this.u.show();
            }
        } catch (Exception e) {
            c.e("ChapterBatDownloadActivity", e.getMessage());
        }
    }

    private synchronized void j() {
        if (com.qq.reader.common.db.handle.i.c().e(String.valueOf(this.K.k())) == null) {
            new Thread(new Runnable(this) {
                final /* synthetic */ ChapterBatDownloadActivity a;

                {
                    this.a = r1;
                }

                public void run() {
                    OnlineTag f = v.b().f(this.a.K.k());
                    if (f == null) {
                        f = this.a.K;
                        f.a(0);
                        f.b(System.currentTimeMillis());
                        f.c(1);
                        v.b().b(f);
                    }
                    Mark localMark = new LocalMark(f.b(), f.f(), 0, 4, false);
                    localMark.setPercentStr("0.0%").setAuthor(f.o()).setDescriptionStr("");
                    localMark.setStarPointStr(Mark.HEADPAGE_FLAG);
                    localMark.setHasNewContent(false);
                    localMark.setId(f.k());
                    localMark.setBookId(Long.valueOf(f.k()).longValue());
                    localMark.setCoverUrl(f.u());
                    com.qq.reader.common.db.handle.j.a().a(new com.qq.reader.common.monitor.a.a(this.a.K.k(), this.a.K.y()));
                    com.qq.reader.common.db.handle.i.c().a(localMark, true);
                    d.h(this.a.a.getApplicationContext(), String.valueOf(localMark.getBookId()));
                    com.qq.reader.common.imageloader.c.a(this.a.a).c(f.u());
                }
            }).start();
        }
    }

    private void k() {
        this.f = new g(getApplicationContext(), this.K);
        this.f.a(getHandler());
        this.f.a(true);
    }

    private void l() {
        if (this.f != null) {
            this.f.d();
            this.f = null;
        }
    }

    protected boolean handleMessageImp(Message message) {
        ChapterPayResult chapterPayResult;
        switch (message.what) {
            case 21000:
                this.e = (f) message.obj;
                if (message.arg1 == 1) {
                }
                if (this.g != null) {
                    this.g.c(this.e.y().O());
                }
                if (this.N && message.arg2 != 2) {
                    if (!(this.g == null || this.e == null)) {
                        this.g.b(this.e);
                        break;
                    }
                }
                this.g.a(this.e);
                this.g.notifyDataSetChanged();
                this.n.setVisibility(8);
                this.m.setVisibility(8);
                this.v.setVisibility(0);
                this.h.setVisibility(0);
                int k = this.e.k();
                if (k <= 0) {
                    k = 20;
                }
                k = this.K.g() / k;
                if (k >= this.g.getGroupCount()) {
                    k = this.g.getGroupCount() - 1;
                }
                if (k >= 0) {
                    this.h.setSelection(k);
                    this.g.a(k);
                }
                this.N = true;
                break;
                break;
            case 21001:
                this.n.setVisibility(8);
                if (!this.N) {
                    this.m.setVisibility(0);
                    this.h.setVisibility(8);
                    this.i.setVisibility(8);
                    this.v.setVisibility(8);
                }
                l();
                break;
            case 21004:
                chapterPayResult = (ChapterPayResult) message.obj;
                a(chapterPayResult.getPayedChapters());
                this.g.a(chapterPayResult.getPayedChapters());
                n();
                break;
            case 21005:
                n();
                chapterPayResult = (ChapterPayResult) message.obj;
                a(chapterPayResult.getPayedChapters());
                this.g.a(chapterPayResult.getPayedChapters());
                c();
                Bundle bundle = new Bundle();
                bundle.putString("message", chapterPayResult.getResultStr());
                bundle.putSerializable("com.qq.reader.pay.ChapterPayResult", chapterPayResult);
                a(1002, bundle);
                break;
            case 21006:
                chapterPayResult = (ChapterPayResult) message.obj;
                int code = chapterPayResult.getCode();
                Bundle bundle2 = new Bundle();
                bundle2.putString("message", chapterPayResult.getResultStr());
                bundle2.putSerializable("com.qq.reader.pay.ChapterPayResult", chapterPayResult);
                if (code != -6) {
                    if (code != -2) {
                        n();
                        a(1000, bundle2);
                        break;
                    }
                    n();
                    a(1003, bundle2);
                    break;
                }
                n();
                a(1001, bundle2);
                break;
            case 21007:
                ArrayList arrayList = new ArrayList();
                if (message.obj != null) {
                    arrayList = (ArrayList) message.obj;
                }
                this.g.a(arrayList);
                h();
                this.g.notifyDataSetChanged();
                a("成功下载" + arrayList.size() + "章");
                this.E.c();
                c();
                b();
                break;
            case 21008:
                this.g.a();
                i();
                this.g.notifyDataSetChanged();
                break;
            case 21009:
                g();
                break;
            case 21010:
                if (this.u != null && this.u.isShowing()) {
                    this.u.cancel();
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
                c();
                break;
            case 21015:
                c();
                return true;
            case 6000003:
                Bundle bundle3 = new Bundle();
                bundle3.putString("message", "登录态失效，请重新登录！");
                n();
                a(1003, bundle3);
                break;
        }
        return false;
    }

    private void m() {
        if (this.O == null || !this.O.isShowing()) {
            this.O = ProgressDialog.show(this, "", "请稍候...", true);
            this.O.setCancelable(false);
            this.O.setCanceledOnTouchOutside(false);
        }
    }

    private boolean n() {
        if (this.O == null || !this.O.isShowing()) {
            return false;
        }
        try {
            this.O.cancel();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public void a(String str) {
        if (this.Q == null) {
            this.Q = af.a(getApplicationContext(), (CharSequence) "", 0);
        }
        this.Q.a((CharSequence) str);
        this.Q.a();
    }

    private void a(boolean z) {
        if (z) {
            this.P.a((b) this);
            this.P.e();
            return;
        }
        this.I.clear();
        this.J.clear();
        this.P.a((b) this);
        boolean t = this.e.t();
        List<com.qq.reader.cservice.buy.chapter.d> g = this.g.g();
        int e = this.g.e();
        for (com.qq.reader.cservice.buy.chapter.d dVar : g) {
            int e2 = dVar.e();
            this.I.add(Integer.valueOf(e2));
            if (!(this.K.G() || !dVar.g() || t)) {
                this.J.add(Integer.valueOf(e2));
            }
        }
        this.P.b(this.I);
        if (this.J.size() > 0) {
            this.P.a(this.J, e);
            j.f += this.J.size();
            m();
        } else {
            this.P.e();
            j.g += this.I.size();
        }
        j();
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
    }

    public void a(int i, String str) {
    }

    protected void a(int i, final Bundle bundle) {
        String str = "";
        if (bundle != null) {
            CharSequence string = bundle.getString("message");
            if (this.R != null && this.R.isShowing()) {
                this.R.cancel();
                this.R = null;
            }
            switch (i) {
                case 1000:
                    this.R = new AlertDialog.a(this).c(17301543).a((int) R.string.dialog_shortcut_title).b(string).a((int) R.string.alert_dialog_ok, new DialogInterface.OnClickListener(this) {
                        final /* synthetic */ ChapterBatDownloadActivity a;

                        {
                            this.a = r1;
                        }

                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    }).a();
                    break;
                case 1001:
                    this.R = new AlertDialog.a(this).c(17301543).a((int) R.string.dialog_shortcut_title).b(string).a((int) R.string.charge, new DialogInterface.OnClickListener(this) {
                        final /* synthetic */ ChapterBatDownloadActivity b;

                        public void onClick(DialogInterface dialogInterface, int i) {
                            this.b.b(((ChapterPayResult) bundle.getSerializable("com.qq.reader.pay.ChapterPayResult")).getChargeUrl());
                        }
                    }).b((int) R.string.alert_dialog_cancel, new DialogInterface.OnClickListener(this) {
                        final /* synthetic */ ChapterBatDownloadActivity a;

                        {
                            this.a = r1;
                        }

                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    }).a();
                    break;
                case 1002:
                    final ChapterPayResult chapterPayResult = (ChapterPayResult) bundle.getSerializable("com.qq.reader.pay.ChapterPayResult");
                    this.R = new AlertDialog.a(this).c(17301543).a((int) R.string.dialog_shortcut_title).b(string).a((int) R.string.confirm_go_on, new DialogInterface.OnClickListener(this) {
                        final /* synthetic */ ChapterBatDownloadActivity b;

                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (chapterPayResult.getCode() == -5) {
                                this.b.P.e();
                                this.b.a("开始下载章节");
                                return;
                            }
                            this.b.P.a(chapterPayResult.getNeedBuyChapters(), chapterPayResult.getRealCost());
                            this.b.m();
                        }
                    }).b((int) R.string.alert_dialog_cancel, new DialogInterface.OnClickListener(this) {
                        final /* synthetic */ ChapterBatDownloadActivity a;

                        {
                            this.a = r1;
                        }

                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    }).a();
                    break;
                case 1003:
                    this.R = new AlertDialog.a(this).c(17301543).a((int) R.string.dialog_shortcut_title).b(string).a((int) R.string.alert_dialog_ok, new DialogInterface.OnClickListener(this) {
                        final /* synthetic */ ChapterBatDownloadActivity a;

                        {
                            this.a = r1;
                        }

                        public void onClick(DialogInterface dialogInterface, int i) {
                            this.a.startLogin();
                        }
                    }).a();
                    break;
            }
            if (this.R != null && !isFinishing()) {
                this.R.show();
            }
        }
    }

    private void b(String str) {
        new JSPay(this).startCharge(this, this.b);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 20001 && i2 == 0) {
            this.E.c();
            c();
            this.c = true;
            b();
            i.a("event_F176", null, ReaderApplication.getApplicationImp());
        }
    }

    protected void onDestroy() {
        k.b();
        l();
        android.support.v4.content.d.a((Context) this).a(this.M);
        super.onDestroy();
    }

    public void onLoginSuccess(final int i) {
        super.onLoginSuccess(i);
        runOnUiThread(new Runnable(this) {
            final /* synthetic */ ChapterBatDownloadActivity b;

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
            this.B.setVisibility(0);
            String str2 = "";
            if (!TextUtils.isEmpty(str)) {
                str2 = "，";
            }
            this.B.setText("（返" + i + "抵扣券" + str2 + str + "）");
        } else if (this.e == null || this.e.y() == null || TextUtils.isEmpty(this.e.y().N())) {
            this.B.setVisibility(8);
        } else {
            this.B.setVisibility(0);
            if (TextUtils.isEmpty(str)) {
                i.a("event_F175", null, ReaderApplication.getApplicationImp());
                this.B.setText("（" + this.e.y().N() + "）");
                return;
            }
            this.B.setText("（" + str + "）");
        }
    }
}
