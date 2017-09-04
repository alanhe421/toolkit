package com.qq.reader.module.comic.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.StrikethroughSpan;
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
import com.qq.reader.activity.ReaderBaseActivity;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.readertask.ReaderTask;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderShortTask;
import com.qq.reader.common.readertask.protocol.QueryUserBalanceTask;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.web.js.JSPay;
import com.qq.reader.module.comic.c.a;
import com.qq.reader.module.comic.entity.ComicShelfInfo;
import com.qq.reader.module.comic.entity.o;
import com.qq.reader.view.AlertDialog;
import com.qq.reader.view.EmptyView;
import com.qq.reader.view.af;
import com.qq.reader.view.m;
import com.qrcomic.a.h;
import com.qrcomic.downloader.e;
import com.qrcomic.e.b.d;
import com.qrcomic.entity.QRComicBuyReqInfo;
import com.qrcomic.entity.f;
import com.qrcomic.entity.k;
import com.qrcomic.entity.l;
import com.qrcomic.entity.n;
import com.qrcomic.manager.QRComicManager;
import com.qrcomic.util.c.b;
import com.qrcomic.util.j;
import com.tencent.feedback.proguard.R;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class NativeBookStoreComicDownloadActivity extends ReaderBaseActivity implements a {
    private TextView A;
    private Button B;
    private ProgressBar C;
    private View D;
    private final List<String> E = new ArrayList();
    private final List<o> F = new ArrayList();
    private String G;
    private Context H;
    private h I;
    private QRComicManager J;
    private l K;
    private com.qq.reader.module.comic.entity.l L;
    private k M;
    private final com.qq.reader.common.charge.voucher.a.a N = new com.qq.reader.common.charge.voucher.a.a();
    private HashMap<String, f> O;
    private int P = 0;
    private int Q = 0;
    private boolean R = false;
    private boolean S = false;
    private boolean T = false;
    private boolean U = false;
    private boolean V = false;
    private ComicShelfInfo W;
    private ProgressDialog X;
    private com.qq.reader.module.comic.e.f Y;
    private af Z;
    public boolean a = false;
    private final List<String> aa = new ArrayList();
    private final List<String> ab = new ArrayList();
    private final e ac = new e(this) {
        final /* synthetic */ NativeBookStoreComicDownloadActivity a;

        {
            this.a = r1;
        }

        private void a(f fVar, int i) {
            if (this.a.c != null && this.a.c.m() != null) {
                int i2 = 0;
                int i3 = 0;
                int i4 = 0;
                for (o oVar : this.a.c.m().h()) {
                    int i5;
                    if (oVar.d().equals(fVar.b)) {
                        oVar.a(i);
                    }
                    if (oVar.j() != 0) {
                        if (oVar.j() == 100 || oVar.j() == 101) {
                            i5 = i2;
                            i2 = i3 + 1;
                            i3 = i4 + 1;
                            i4 = i3;
                            i3 = i2;
                            i2 = i5;
                        } else if (oVar.j() == 102 || oVar.j() == 103) {
                            i5 = i2 + 1;
                            i2 = i3;
                            i3 = i4 + 1;
                            i4 = i3;
                            i3 = i2;
                            i2 = i5;
                        }
                    }
                    i5 = i2;
                    i2 = i3;
                    i3 = i4;
                    i4 = i3;
                    i3 = i2;
                    i2 = i5;
                }
                this.a.c.notifyDataSetChanged();
                if (i3 != 0) {
                    this.a.a("event_F264", this.a.x, this.a.h);
                    this.a.x.setVisibility(0);
                    this.a.x.setText(this.a.h);
                } else if (i4 == 0 || i4 != i2) {
                    this.a.x.setVisibility(8);
                } else {
                    this.a.a("event_F262", this.a.x, this.a.i);
                    this.a.x.setVisibility(0);
                    this.a.x.setText(this.a.i);
                }
                if (this.a.ab.size() == this.a.F.size()) {
                    Message obtainMessage = this.a.getHandler().obtainMessage(21007);
                    obtainMessage.obj = this.a.aa;
                    obtainMessage.sendToTarget();
                }
            }
        }

        public void a(f fVar) {
            if (this.a.G.equals(fVar.c())) {
                a(fVar, 101);
            }
        }

        public void a(f fVar, int i, long j, long j2) {
        }

        public void a(f fVar, boolean z) {
            a(fVar, 102);
        }

        public void a(f fVar, int i, String str) {
            this.a.ab.add(fVar.d());
            a(fVar, 103);
        }

        public void a(f fVar, long j) {
            this.a.ab.add(fVar.d());
            this.a.aa.add(fVar.d());
            a(fVar, 104);
        }

        public void a(HashMap<String, f> hashMap, boolean z) {
            if (z) {
                this.a.O = hashMap;
                this.a.mHandler.sendEmptyMessage(21018);
                return;
            }
            this.a.mHandler.sendEmptyMessage(21019);
            c.d("QRComicDownloaderObserver", "获取漫画下载状态失败");
        }

        public void a() {
            this.a.p();
            Bundle bundle = new Bundle();
            bundle.putString("message", this.a.getString(R.string.dialog_check_net));
            this.a.a(1003, bundle);
        }
    };
    private final com.qrcomic.e.c ad = new com.qrcomic.e.c(this) {
        final /* synthetic */ NativeBookStoreComicDownloadActivity a;

        {
            this.a = r1;
        }

        public void a(Object obj) {
            if (obj != null && (obj instanceof d)) {
                d dVar = (d) obj;
                if (dVar.a != null && !dVar.a.isEmpty()) {
                    l lVar = (l) dVar.a.get(0);
                    if (lVar != null && this.a.G.equals(lVar.a)) {
                        if (this.a.K == null || !(lVar.d == null || b.b(lVar.d).equals(this.a.K.d))) {
                            this.a.K = lVar;
                            this.a.t();
                        }
                    }
                }
            }
        }

        public void b(Object obj) {
            if (obj != null && (obj instanceof com.qrcomic.e.b.c)) {
                StringBuilder stringBuilder = new StringBuilder();
                com.qrcomic.e.b.c cVar = (com.qrcomic.e.b.c) obj;
                stringBuilder.append("onQueryUserBuyInfoFailure, errorcode is " + cVar.b + ", comicId is ");
                if (!(cVar.a == null || cVar.a.isEmpty())) {
                    Iterator it = cVar.a.iterator();
                    while (it.hasNext()) {
                        stringBuilder.append(((QRComicBuyReqInfo) it.next()).a + ",");
                    }
                }
                c.e("NativeBookStoreComicDownloadActivity", stringBuilder.toString());
                this.a.y();
            }
        }

        public void c(Object obj) {
            try {
                if (obj instanceof Object[]) {
                    Object[] objArr = (Object[]) obj;
                    if (objArr.length == 2) {
                        Object[] objArr2 = (Object[]) objArr[1];
                        if (!(objArr2[0] instanceof Integer)) {
                            return;
                        }
                        if (((Integer) objArr2[0]).intValue() == 1004) {
                            this.a.b(this.a.getResources().getString(R.string.comic_book_off_shelf_tips), (int) R.drawable.empty10);
                        } else if (((Integer) objArr2[0]).intValue() == 1005 && !this.a.j.isShown()) {
                            this.a.a((String) objArr2[1], (int) R.drawable.empty08);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    private Dialog ae = null;
    private com.qq.reader.module.comic.e.c b;
    private com.qq.reader.module.comic.a.b c;
    private ExpandableListView d;
    private int e = 0;
    private String f;
    private String g;
    private String h;
    private String i;
    private EmptyView j;
    private LinearLayout k;
    private m l;
    private View m;
    private AlertDialog n;
    private RelativeLayout o;
    private TextView p;
    private TextView q;
    private TextView r;
    private TextView s;
    private TextView t;
    private TextView u;
    private TextView v;
    private Button w;
    private Button x;
    private Button y;
    private TextView z;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.comic_section_pay_choose);
        k();
        h();
    }

    private void h() {
        this.H = this;
        this.I = com.qrcomic.manager.b.a().b();
        if (this.I == null) {
            if (com.qq.reader.module.comic.a.a().a(this)) {
                this.I = com.qrcomic.manager.b.a().b();
            } else {
                af.a(this, "初始化漫画失败，请重试", 0).a();
            }
        }
        if (this.I != null) {
            this.I.a(this.ac, false);
            this.I.a(this.ad, false);
            this.J = (QRComicManager) this.I.a(1);
        }
        this.P = getIntent().getIntExtra("REQUEST_CODE", 0);
        this.Q = getIntent().getIntExtra("RESPONSE_CODE", 0);
        this.G = getIntent().getExtras().getString("KEY_COMIC_ID");
        this.W = (ComicShelfInfo) getIntent().getExtras().getParcelable("KEY_COMIC_SHELF_INFO");
        this.c.a(this.G);
        this.M = new k();
        this.M.d = new ArrayList();
        this.M.e = new ArrayList();
        this.Y = new com.qq.reader.module.comic.e.f(this);
        i();
    }

    private void i() {
        r();
        com.qrcomic.downloader.d.b().b(this.G);
        j();
        l();
    }

    private void j() {
        g.a().a(new ReaderTask() {
            public String getTaskName() {
                return "queryBuyInfo";
            }

            public void run() {
                if (NativeBookStoreComicDownloadActivity.this.I != null) {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(new QRComicBuyReqInfo(NativeBookStoreComicDownloadActivity.this.G, null));
                    com.qrcomic.e.b bVar = (com.qrcomic.e.b) NativeBookStoreComicDownloadActivity.this.I.b(1);
                    bVar.a(arrayList, null, false);
                    bVar.a(arrayList, null, true);
                }
            }
        });
    }

    private void k() {
        this.d = (ExpandableListView) findViewById(R.id.chapter_choose_list);
        this.c = new com.qq.reader.module.comic.a.b(this);
        this.c.a(new com.qq.reader.module.comic.a.b.c(this) {
            final /* synthetic */ NativeBookStoreComicDownloadActivity a;

            {
                this.a = r1;
            }

            public void onClick(int i, boolean z) {
                if (z) {
                    this.a.d.expandGroup(i);
                } else {
                    this.a.d.collapseGroup(i);
                }
            }
        });
        this.d.setAdapter(this.c);
        this.d.setOnGroupClickListener(new OnGroupClickListener(this) {
            final /* synthetic */ NativeBookStoreComicDownloadActivity a;

            {
                this.a = r1;
            }

            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long j) {
                this.a.V = true;
                this.a.c.a(i);
                return true;
            }
        });
        this.d.setOnChildClickListener(new OnChildClickListener(this) {
            final /* synthetic */ NativeBookStoreComicDownloadActivity a;

            {
                this.a = r1;
            }

            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i2, long j) {
                this.a.V = true;
                this.a.c.a(i, i2);
                return true;
            }
        });
        ((ImageView) findViewById(R.id.profile_header_left_back)).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NativeBookStoreComicDownloadActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.s();
                this.a.finish();
            }
        });
        m();
        ((TextView) findViewById(R.id.profile_header_title)).setText("批量下载");
        this.f = getApplicationContext().getResources().getString(R.string.chapter_pay_selectall);
        this.g = getApplicationContext().getResources().getString(R.string.chapter_report_cancel);
        this.h = getApplicationContext().getResources().getString(R.string.chapter_pay_pause);
        this.i = getApplicationContext().getResources().getString(R.string.chapter_pay_resume);
        this.w = (Button) findViewById(R.id.profile_header_right_button);
        this.w.setVisibility(0);
        this.w.setText(R.string.chapter_pay_selectall);
        this.w.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NativeBookStoreComicDownloadActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.V = true;
                String charSequence = this.a.w.getText().toString();
                if (charSequence.equals(this.a.f)) {
                    this.a.w.setText(this.a.g);
                    this.a.a(true);
                } else if (charSequence.equals(this.a.g)) {
                    this.a.w.setText(this.a.f);
                    this.a.a(false);
                }
            }
        });
        this.x = (Button) findViewById(R.id.profile_header_right_button2);
        this.x.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NativeBookStoreComicDownloadActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                String charSequence = this.a.x.getText().toString();
                if (charSequence.equals(this.a.h)) {
                    i.a("event_F265", null, ReaderApplication.getApplicationImp());
                    this.a.c.e();
                    com.qrcomic.downloader.d.b().e(this.a.G);
                    this.a.x.setText(this.a.i);
                    i.a("event_F262", null, ReaderApplication.getApplicationImp());
                } else if (charSequence.equals(this.a.i)) {
                    i.a("event_F263", null, ReaderApplication.getApplicationImp());
                    if (!this.a.c() || !this.a.d()) {
                        return;
                    }
                    if (ao.j(this.a.H) || this.a.a) {
                        this.a.c.f();
                        List arrayList = new ArrayList();
                        arrayList.add(this.a.G);
                        com.qrcomic.downloader.d.b().a(arrayList);
                        this.a.x.setText(this.a.h);
                        i.a("event_F264", null, ReaderApplication.getApplicationImp());
                        return;
                    }
                    this.a.f();
                }
            }
        });
        this.l = new m(this);
        this.l.a("请稍候…");
        this.k = (LinearLayout) findViewById(R.id.chapter_pay_loading);
        this.k.setVisibility(0);
        this.j = (EmptyView) findViewById(R.id.chapter_pay_empyt_layout);
        this.j.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NativeBookStoreComicDownloadActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (ao.d(this.a)) {
                    this.a.j.setVisibility(8);
                    this.a.k.setVisibility(0);
                    this.a.i();
                }
            }
        });
        this.j.setVisibility(8);
        this.n = new AlertDialog.a(this).c(R.drawable.alert_dialog_icon).a("提示").b("下载失败，可免费重新下载").a("下载", new DialogInterface.OnClickListener(this) {
            final /* synthetic */ NativeBookStoreComicDownloadActivity a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                this.a.b(false);
            }
        }).b(R.string.alert_dialog_cancel, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ NativeBookStoreComicDownloadActivity a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                this.a.c.b(true);
                this.a.w.setClickable(true);
                this.a.c.notifyDataSetInvalidated();
                this.a.a();
            }
        }).a();
        this.m = findViewById(R.id.chapter_pay_choose_bottom_download);
        this.D = findViewById(R.id.chapter_pay_choose_download_mask);
        findViewById(R.id.chapter_pay_choose_bottom_download_switchbg).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NativeBookStoreComicDownloadActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.finish();
            }
        });
        this.R = true;
    }

    private void a(final boolean z) {
        this.l.f_();
        g.a().a(new ReaderShortTask() {
            public void run() {
                super.run();
                NativeBookStoreComicDownloadActivity.this.c.a(z);
                NativeBookStoreComicDownloadActivity.this.mHandler.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass12 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        if (!NativeBookStoreComicDownloadActivity.this.isFinishing()) {
                            NativeBookStoreComicDownloadActivity.this.l.dismiss();
                        }
                        NativeBookStoreComicDownloadActivity.this.c.notifyDataSetChanged();
                    }
                });
            }
        });
    }

    private void l() {
        g.a().a(new QueryUserBalanceTask(new QueryUserBalanceTask.a(this) {
            final /* synthetic */ NativeBookStoreComicDownloadActivity a;

            {
                this.a = r1;
            }

            public void a(com.qq.reader.common.charge.voucher.a.a aVar) {
                this.a.N.a(aVar);
                if (this.a.o != null) {
                    this.a.mHandler.sendEmptyMessage(21015);
                }
            }

            public void a() {
                this.a.N.c();
                if (this.a.o != null) {
                    this.a.mHandler.sendEmptyMessage(21015);
                }
            }
        }, this.G, 1));
    }

    private void m() {
        this.y = (Button) findViewById(R.id.btn_buy_confirm);
        this.C = (ProgressBar) findViewById(R.id.pb_user_balance);
        this.o = (RelativeLayout) findViewById(R.id.rl_pay_choose_bottom_price_info);
        this.z = (TextView) findViewById(R.id.tv_balance);
        this.t = (TextView) findViewById(R.id.tv_discount_msg);
        this.q = (TextView) findViewById(R.id.tv_origin_price);
        this.r = (TextView) findViewById(R.id.tv_price);
        this.p = (TextView) findViewById(R.id.tv_selected_chapter_count);
        this.s = (TextView) findViewById(R.id.tv_price_unit);
        this.u = (TextView) findViewById(R.id.tv_bottom_comic_name);
        this.v = (TextView) findViewById(R.id.tv_bottom_already_select);
        this.A = (TextView) findViewById(R.id.tv_bottom_first_item_unit);
        this.B = (Button) findViewById(R.id.btn_charge_ensure_money);
    }

    public synchronized void a() {
        boolean z = false;
        synchronized (this) {
            if (this.L != null && this.R) {
                int g;
                int i;
                boolean z2;
                int j = this.c.j();
                a(j);
                if (!this.L.c()) {
                    g = this.c.g();
                    i = this.c.i();
                } else if (n()) {
                    i = 0;
                    g = 0;
                } else {
                    i = this.L.k();
                    g = i;
                    i = (this.L.j() * i) / 100;
                }
                if (this.K == null || !this.K.h()) {
                    z2 = false;
                } else {
                    z2 = true;
                }
                this.S = z2;
                this.r.setTextSize(18.0f);
                if (this.L.b()) {
                    this.r.setTextSize(14.0f);
                    this.r.setText(R.string.paypage_pay_free);
                    this.s.setVisibility(8);
                } else if (this.S) {
                    this.r.setTextSize(14.0f);
                    this.r.setText(R.string.paypage_pay_all_ok);
                    this.s.setVisibility(8);
                } else {
                    this.e = i;
                    this.r.setVisibility(0);
                    this.r.setText(String.valueOf(i));
                    this.s.setVisibility(0);
                    if (g != i) {
                        Object obj = String.valueOf(g) + "书币";
                        CharSequence spannableString = new SpannableString(obj);
                        spannableString.setSpan(new StrikethroughSpan(), 0, obj.length(), 17);
                        this.q.setVisibility(0);
                        this.q.setText(spannableString);
                        this.t.setVisibility(0);
                        this.t.setText("(" + this.L.l() + ")");
                    } else {
                        this.q.setVisibility(8);
                        this.t.setVisibility(8);
                    }
                }
                g = this.N.a();
                if (g < 0) {
                    this.z.setVisibility(8);
                    this.C.setVisibility(0);
                } else {
                    this.z.setText(this.N.b());
                    this.z.setVisibility(0);
                    this.C.setVisibility(8);
                }
                if (this.N.d > 0) {
                    com.qq.reader.common.charge.voucher.b.a(this, this.z, new OnClickListener(this) {
                        final /* synthetic */ NativeBookStoreComicDownloadActivity a;

                        {
                            this.a = r1;
                        }

                        public void onClick(View view) {
                        }
                    }, new OnDismissListener(this) {
                        final /* synthetic */ NativeBookStoreComicDownloadActivity a;

                        {
                            this.a = r1;
                        }

                        public void onDismiss(DialogInterface dialogInterface) {
                        }
                    }, this.N.g);
                }
                final int i2 = this.S ? 0 : i - g;
                if (i2 <= 0) {
                    z = true;
                }
                com.qq.reader.common.charge.voucher.b.a(this.y, this.B, z);
                if (j == 0) {
                    this.t.setVisibility(8);
                    this.q.setVisibility(8);
                    this.y.setBackgroundResource(R.drawable.chapter_pay_bt_bg_unenable);
                    this.y.setEnabled(false);
                    this.y.setText(getString(R.string.comic_download_tip_none_selected));
                } else {
                    String str;
                    this.y.setEnabled(true);
                    long h = this.c.h();
                    if (h == 0) {
                        str = "";
                    } else {
                        str = "(" + (h / 1000000) + "MB)";
                    }
                    if (i <= 0 || this.S) {
                        this.y.setBackgroundResource(R.drawable.new_button);
                        this.y.setText(getString(R.string.chapter_buy_tip_free_download) + str);
                        this.y.setOnClickListener(new OnClickListener(this) {
                            final /* synthetic */ NativeBookStoreComicDownloadActivity a;

                            {
                                this.a = r1;
                            }

                            public void onClick(View view) {
                                this.a.b(false);
                            }
                        });
                    } else {
                        this.y.setBackgroundResource(R.drawable.selector_orange_button);
                        a("event_F266", this.y, getString(R.string.chapter_buy_confirm_buy_and_dl));
                        this.y.setText(getString(R.string.chapter_buy_confirm_buy_and_dl) + str);
                        this.y.setOnClickListener(new OnClickListener(this) {
                            final /* synthetic */ NativeBookStoreComicDownloadActivity a;

                            {
                                this.a = r1;
                            }

                            public void onClick(View view) {
                                this.a.b(true);
                                i.a("event_F267", null, ReaderApplication.getApplicationImp());
                            }
                        });
                        if (this.T && g >= 0 && this.e > 0 && g >= this.e) {
                            this.T = false;
                            b(true);
                        } else if (g < 0 || i <= g) {
                            a("event_F266", this.y, getString(R.string.chapter_buy_confirm_buy_and_dl));
                            this.y.setText(getString(R.string.chapter_buy_confirm_buy_and_dl) + str);
                            this.y.setOnClickListener(new OnClickListener(this) {
                                final /* synthetic */ NativeBookStoreComicDownloadActivity a;

                                {
                                    this.a = r1;
                                }

                                public void onClick(View view) {
                                    this.a.b(true);
                                    i.a("event_F267", null, ReaderApplication.getApplicationImp());
                                }
                            });
                        } else {
                            a("event_F270", this.y, getString(R.string.alert_dialog_buy_balance_charge_other_count));
                            this.y.setText(getString(R.string.alert_dialog_buy_balance_charge_other_count));
                            this.y.setOnClickListener(new OnClickListener(this) {
                                final /* synthetic */ NativeBookStoreComicDownloadActivity a;

                                {
                                    this.a = r1;
                                }

                                public void onClick(View view) {
                                    this.a.x();
                                    i.a("event_F271", null, ReaderApplication.getApplicationImp());
                                }
                            });
                            a("event_F268", this.B, getString(R.string.chapter_buy_charge_ensure_money));
                            com.qq.reader.common.charge.voucher.b.a(this.B, i2);
                            this.B.setOnClickListener(new OnClickListener(this) {
                                final /* synthetic */ NativeBookStoreComicDownloadActivity b;

                                public void onClick(View view) {
                                    new JSPay(this.b).startChargeDirectly(this.b, i2);
                                    i.a("event_F269", null, ReaderApplication.getApplicationImp());
                                }
                            });
                        }
                    }
                }
            }
        }
    }

    private void a(int i) {
        if (this.L.c()) {
            this.u.setVisibility(0);
            this.u.setText(this.L.g());
            this.v.setVisibility(8);
            this.p.setVisibility(8);
            this.A.setVisibility(8);
            return;
        }
        this.u.setVisibility(8);
        this.v.setVisibility(0);
        this.p.setVisibility(0);
        this.p.setText(String.valueOf(i));
        this.A.setVisibility(0);
    }

    protected void onResume() {
        super.onResume();
        i.a("event_F261", null, ReaderApplication.getApplicationImp());
    }

    protected void onPause() {
        super.onPause();
    }

    private boolean n() {
        for (com.qq.reader.module.comic.entity.m h : this.c.k()) {
            if (h.h()) {
                return false;
            }
        }
        return true;
    }

    private void a(List<String> list) {
        if (list != null && !list.isEmpty()) {
            List<com.qq.reader.module.comic.entity.m> k = this.c.k();
            for (int i = 0; i < list.size(); i++) {
                String str = (String) list.get(i);
                for (com.qq.reader.module.comic.entity.m mVar : k) {
                    if (str.equals(mVar.f())) {
                        mVar.a(true);
                    }
                }
            }
            this.c.notifyDataSetChanged();
        }
    }

    public void b() {
        if (this.c.d()) {
            this.w.setVisibility(4);
            return;
        }
        this.w.setVisibility(0);
        if (this.c.c()) {
            this.w.setText(this.g);
        } else {
            this.w.setText(this.f);
        }
    }

    private void o() {
        this.m.setVisibility(0);
        ((TextView) findViewById(R.id.chapter_pay_choose_bottom_download_progress_txt)).setText("正在下载…");
        this.c.a();
        this.D.setVisibility(0);
        this.c.b(false);
        this.w.setClickable(false);
    }

    private void p() {
        this.a = false;
        this.m.setVisibility(4);
        this.D.setVisibility(8);
        this.c.b(true);
        this.w.setClickable(true);
    }

    private void q() {
        this.m.setVisibility(4);
        this.D.setVisibility(8);
        this.n.show();
    }

    private void r() {
        this.b = new com.qq.reader.module.comic.e.c(getApplicationContext());
        this.b.a(getHandler());
        this.b.a(this.G, true);
    }

    private void s() {
        if (this.b != null) {
            this.b.a();
            this.b = null;
        }
    }

    private synchronized void t() {
        int i = 0;
        synchronized (this) {
            if (!(this.L == null || this.O == null || this.K == null || !this.R)) {
                int i2 = 0;
                int i3 = 0;
                for (o oVar : this.L.h()) {
                    f fVar = (f) this.O.get(oVar.l());
                    if (!(fVar == null || fVar.r == null || fVar.r.d == 0)) {
                        int i4 = fVar.r.d;
                        oVar.a(i4);
                        if (i4 == 101 || i4 == 100) {
                            i3++;
                            i2++;
                        } else if (i4 == 102 || i4 == 103) {
                            i3++;
                            i++;
                        }
                    }
                    if (!(this.K.h() || this.K.c() == null || this.K.c().isEmpty())) {
                        for (n nVar : this.K.c()) {
                            if (oVar.d().equals(nVar.a)) {
                                oVar.a(true);
                            }
                        }
                    }
                }
                this.L.a(this.K.h());
                a(i2, i3, i);
                this.c.a(this.L);
                this.c.notifyDataSetChanged();
                if (!this.V) {
                    b(getIntent().getIntExtra("KEY_SECTION_ID", 0));
                    this.V = true;
                }
                if (this.U) {
                    b(getResources().getString(R.string.comic_book_off_shelf_tips), (int) R.drawable.empty10);
                } else {
                    this.k.setVisibility(8);
                    this.j.setVisibility(8);
                    this.o.setVisibility(0);
                    this.d.setVisibility(0);
                }
            }
        }
    }

    private void a(int i, int i2, int i3) {
        if (i != 0) {
            a("event_F264", this.x, this.h);
            this.x.setVisibility(0);
            this.x.setText(this.h);
        } else if (i2 == 0 || i2 != i3) {
            this.x.setVisibility(8);
        } else {
            a("event_F262", this.x, this.i);
            this.x.setVisibility(0);
            this.x.setText(this.i);
        }
    }

    private void b(int i) {
        int f = this.L.f();
        if (f <= 0) {
            f = 20;
        }
        f = i / f;
        if (f >= this.c.getGroupCount()) {
            f = this.c.getGroupCount() - 1;
        }
        if (f >= 0) {
            this.d.setSelection(f);
            this.c.a(f);
        }
    }

    protected boolean handleMessageImp(Message message) {
        switch (message.what) {
            case 21000:
                this.L = (com.qq.reader.module.comic.entity.l) message.obj;
                t();
                break;
            case 21001:
                if (message.arg1 != 1004) {
                    if (message.arg1 != 1005) {
                        if (this.L == null) {
                            y();
                            break;
                        }
                    }
                    a(getResources().getString(R.string.buy_section_error_area), (int) R.drawable.empty08);
                    break;
                }
                b(getResources().getString(R.string.comic_book_off_shelf_tips), (int) R.drawable.empty10);
                break;
                break;
            case 21004:
                boolean z;
                k kVar = (k) message.obj;
                if (kVar.c == 2) {
                    this.K.i();
                    this.L.a(true);
                    this.c.c(true);
                    z = true;
                } else {
                    a(kVar.d);
                    this.c.a(kVar.d);
                    z = false;
                }
                l();
                v();
                a(kVar, z);
                b(this.M, z);
                break;
            case 21006:
                Bundle bundle = new Bundle();
                String str = null;
                v();
                Bundle data;
                if (message.arg1 == 1006) {
                    data = message.getData();
                    if (data != null) {
                        str = data.getString("ERROR_MSG");
                    }
                    if (TextUtils.isEmpty(str)) {
                        str = getResources().getString(R.string.pay_fail_by_permission);
                    }
                } else if (message.arg1 == 1005) {
                    data = message.getData();
                    if (data != null) {
                        str = data.getString("ERROR_MSG");
                    }
                    if (TextUtils.isEmpty(str)) {
                        str = getResources().getString(R.string.buy_section_error_area);
                    }
                } else {
                    str = "购买失败,请重新购买";
                }
                bundle.putInt("code", message.arg1);
                bundle.putString("message", str);
                a(1000, bundle);
                break;
            case 21007:
                ArrayList arrayList = new ArrayList();
                if (message.obj != null) {
                    arrayList = (ArrayList) message.obj;
                }
                if (!arrayList.isEmpty()) {
                    a("下载成功");
                }
                this.c.a(arrayList);
                p();
                this.c.notifyDataSetChanged();
                break;
            case 21008:
                this.c.b();
                q();
                this.c.notifyDataSetChanged();
                break;
            case 21009:
                o();
                break;
            case 21010:
                if (this.n != null && this.n.isShowing()) {
                    this.n.cancel();
                    this.c.b(true);
                    this.w.setClickable(true);
                    this.c.notifyDataSetInvalidated();
                    a();
                    break;
                }
            case 21011:
                List<String> list = (List) message.obj;
                if (!(list == null || list.isEmpty())) {
                    HashMap l = this.c.l();
                    Collection arrayList2 = new ArrayList();
                    for (String str2 : list) {
                        com.qq.reader.module.comic.entity.m mVar = (com.qq.reader.module.comic.entity.m) l.get(str2);
                        if (mVar != null) {
                            if (mVar.g()) {
                                arrayList2.add(str2);
                            } else {
                                mVar.a(true);
                            }
                        }
                    }
                    list.removeAll(arrayList2);
                    this.c.a((List) list);
                    this.c.notifyDataSetChanged();
                    a();
                    break;
                }
            case 21014:
                Bundle bundle2 = new Bundle();
                bundle2.putString("message", getString(R.string.dialog_check_net));
                a(1002, bundle2);
                break;
            case 21015:
                a();
                break;
            case 21018:
                t();
                break;
            case 21019:
                if (this.O == null) {
                    this.O = new HashMap();
                }
                t();
                break;
        }
        return false;
    }

    private void a(String str, int i) {
        this.U = true;
        this.j.a(0);
        this.j.b(i);
        this.j.a((CharSequence) str);
        this.j.setClickable(false);
        y();
    }

    private void b(String str, int i) {
        a(str, i);
        w();
    }

    private void a(k kVar, boolean z) {
        this.M.c = kVar.c;
        this.M.a = kVar.a;
        if (z) {
            this.M.f = true;
        } else if (kVar.d != null) {
            for (String str : kVar.d) {
                if (!this.M.d.contains(str)) {
                    this.M.d.add(str);
                }
            }
        }
    }

    private void b(final k kVar, final boolean z) {
        g.a().a(new ReaderTask() {
            public String getTaskName() {
                return "savecomicbuy";
            }

            public void run() {
                if (NativeBookStoreComicDownloadActivity.this.P != 0 && NativeBookStoreComicDownloadActivity.this.I != null && kVar != null) {
                    ((QRComicManager) NativeBookStoreComicDownloadActivity.this.I.a(1)).a(z, NativeBookStoreComicDownloadActivity.this.I.a(), kVar.a, kVar.d);
                }
            }
        });
    }

    private void u() {
        if (this.X == null || !this.X.isShowing()) {
            this.X = ProgressDialog.show(this, "", "请稍候...", true);
            this.X.setCancelable(false);
            this.X.setCanceledOnTouchOutside(false);
        }
    }

    private boolean v() {
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

    private void a(String str) {
        if (this.Z == null) {
            this.Z = af.a(getApplicationContext(), "", 0);
        }
        this.Z.a(str);
        this.Z.a();
    }

    private void b(boolean z) {
        if (c() && d()) {
            this.E.clear();
            this.F.clear();
            this.ab.clear();
            this.aa.clear();
            List<com.qq.reader.module.comic.entity.m> k = this.c.k();
            boolean a = this.L.a();
            for (com.qq.reader.module.comic.entity.m mVar : k) {
                String e = mVar.e();
                this.F.add(mVar.a());
                if (!(!mVar.h() || a || this.S)) {
                    this.E.add(e);
                }
            }
            this.Y.a((a) this);
            this.Y.a(this.F);
            if (z) {
                int k2;
                if (this.L.c()) {
                    k2 = this.L.k();
                } else {
                    k2 = this.c.i();
                }
                if (this.E.size() > 0) {
                    this.Y.a(this.G, this.E, this.L.q(), k2, null);
                    u();
                    return;
                }
                this.Y.a();
                return;
            }
            this.Y.a();
        }
    }

    public boolean c() {
        if (ao.d(this)) {
            return true;
        }
        a(getString(R.string.net_not_available));
        return false;
    }

    public boolean d() {
        if (!j.a() || j.b() >= 10485760) {
            return true;
        }
        Bundle bundle = new Bundle();
        bundle.putString("message", getString(R.string.dialog_not_enough_memory));
        a(1004, bundle);
        return false;
    }

    private void w() {
        com.qrcomic.a.j.a().a(new com.qrcomic.a.d(this) {
            final /* synthetic */ NativeBookStoreComicDownloadActivity a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.J.a(this.a.G, true);
            }
        }, null);
    }

    private void a(String str, TextView textView, String str2) {
        if (str2 != null && textView != null) {
            if (!textView.isShown() || !textView.getText().toString().startsWith(str2)) {
                i.a(str, null, ReaderApplication.getApplicationImp());
            }
        }
    }

    public void a(k kVar) {
        getHandler().obtainMessage(21004, kVar).sendToTarget();
    }

    public void a(k kVar, int i, String str) {
        Message obtainMessage = getHandler().obtainMessage(21006, i, 0, kVar);
        Bundle bundle = new Bundle();
        bundle.putString("ERROR_MSG", str);
        obtainMessage.setData(bundle);
        obtainMessage.sendToTarget();
    }

    public void e() {
        getHandler().obtainMessage(21009).sendToTarget();
    }

    public void f() {
        getHandler().obtainMessage(21014).sendToTarget();
    }

    public ComicShelfInfo g() {
        return this.W;
    }

    protected void a(int i, Bundle bundle) {
        CharSequence charSequence = "";
        int i2 = 0;
        if (bundle != null) {
            i2 = bundle.getInt("code");
            charSequence = bundle.getString("message");
        }
        if (this.ae != null && this.ae.isShowing()) {
            this.ae.cancel();
            this.ae = null;
        }
        AlertDialog.a b = new AlertDialog.a(this).c(17301543).a(R.string.dialog_shortcut_title).b(charSequence);
        switch (i) {
            case 1000:
                this.ae = b.a(i2 == 1005 ? R.string.dialog_exit_ok : R.string.dialog_known_confirm, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ NativeBookStoreComicDownloadActivity b;

                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i2 == 1005) {
                            this.b.finish();
                        }
                    }
                }).a();
                break;
            case 1001:
                this.ae = b.a(R.string.charge, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ NativeBookStoreComicDownloadActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        this.a.x();
                    }
                }).b(R.string.alert_dialog_cancel, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ NativeBookStoreComicDownloadActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).a();
                break;
            case 1002:
                this.ae = b.a(R.string.alert_dialog_keep_downloading, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ NativeBookStoreComicDownloadActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        this.a.a = true;
                        this.a.Y.a();
                    }
                }).b(R.string.alert_dialog_cancel, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ NativeBookStoreComicDownloadActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).a();
                break;
            case 1003:
                this.ae = b.a(R.string.alert_dialog_keep_downloading, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ NativeBookStoreComicDownloadActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        com.qrcomic.downloader.g.a(true);
                        if (this.a.c() && this.a.d()) {
                            List arrayList = new ArrayList();
                            arrayList.add(this.a.G);
                            com.qrcomic.downloader.d.b().a(arrayList);
                        }
                    }
                }).b(R.string.alert_dialog_cancel, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ NativeBookStoreComicDownloadActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).a();
                break;
            case 1004:
                this.ae = b.a(R.string.dialog_known_confirm, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ NativeBookStoreComicDownloadActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).a();
                break;
        }
        if (this.ae != null && !isFinishing()) {
            this.ae.show();
        }
    }

    private void x() {
        new JSPay(this).startCharge(this, this.e);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 20001 && i2 == 0) {
            this.T = true;
            l();
        }
    }

    protected void onDestroy() {
        s();
        if (this.I != null) {
            this.I.b(this.ac);
            this.I.b(this.ad);
        }
        super.onDestroy();
    }

    private void y() {
        this.k.setVisibility(8);
        this.j.setVisibility(0);
        this.d.setVisibility(8);
        this.w.setVisibility(4);
        this.x.setVisibility(8);
        this.o.setVisibility(8);
    }

    public void finish() {
        if (!(this.P == 0 || this.Q == 0)) {
            Intent intent = new Intent();
            if (this.M.d.size() > 0 || this.M.f) {
                Bundle bundle = new Bundle();
                bundle.putInt("key_buy_type", this.M.c);
                bundle.putSerializable("sectionIdList", (Serializable) this.M.d);
                intent.putExtras(bundle);
                setResult(this.Q, intent);
            }
        }
        super.finish();
    }
}
