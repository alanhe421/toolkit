package com.qq.reader.activity;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.monitor.j;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.web.a;
import com.qq.reader.common.web.d;
import com.qq.reader.common.web.js.JSPay;
import com.qq.reader.cservice.buy.a.b;
import com.qq.reader.cservice.download.book.f;
import com.qq.reader.cservice.download.book.g;
import com.qq.reader.cservice.download.book.h;
import com.qq.reader.module.bookstore.qweb.TabInfo;
import com.qq.reader.module.bookstore.qweb.fragment.WebBrowserFragment;
import com.qq.reader.module.readpage.q;
import com.qq.reader.view.AlertDialog;
import com.qq.reader.view.af;
import com.qq.reader.view.c;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Stack;
import org.json.JSONException;
import org.json.JSONObject;

@Deprecated
public class WebBookDetailActivity extends BaseWebTabActivity implements a, b, f {
    private d k = null;
    private int l = -1;
    private View m = null;
    private TextView n = null;
    private TextView o = null;
    private TextView p = null;
    private View q = null;
    private ProgressDialog r;
    private Context s;
    private Stack<Bundle> t = new Stack();
    private c u;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        l();
        this.s = getApplicationContext();
    }

    private void l() {
        this.n = (TextView) findViewById(R.id.web_detail_btn_read);
        this.n.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ WebBookDetailActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.m();
                this.a.c(0);
            }
        });
        this.o = (TextView) findViewById(R.id.web_detail_btn_download);
        this.o.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ WebBookDetailActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.n();
                this.a.c(1);
            }
        });
        this.p = (TextView) findViewById(R.id.web_detail_btn_add2shelf);
        this.q = findViewById(R.id.divide_line);
        this.p.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ WebBookDetailActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.o();
                this.a.c(2);
            }
        });
        this.m = findViewById(R.id.web_detail_btns);
        this.m.setVisibility(8);
        LayoutParams layoutParams = (LayoutParams) this.d.getLayoutParams();
        layoutParams.height = getResources().getDimensionPixelOffset(R.dimen.common_dp_40);
        this.d.setLayoutParams(layoutParams);
        this.a.setIndicatorBottomPadding(0);
    }

    private void c(int i) {
        try {
            WebBrowserFragment webBrowserFragment = (WebBrowserFragment) d();
            if (webBrowserFragment != null) {
                j.a(webBrowserFragment.getWebView(), 0, i);
            }
        } catch (Exception e) {
        }
    }

    private void m() {
        this.k.i();
    }

    private void n() {
        j.a(9, 2);
        i.a("event_C10", null, this.s);
        StatisticsManager.a().a("event_C10", null);
        this.k.j();
        this.mLoginNextTask = this.k.l();
    }

    private void o() {
        this.k.k();
        this.mLoginNextTask = this.k.l();
    }

    public void doPageAction(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.l = jSONObject.optInt("actioncode");
            switch (this.l) {
                case 1000:
                    this.k = new d(this, this.mHandler, jSONObject);
                    boolean a = this.k.a();
                    int b = this.k.b();
                    View findViewById = findViewById(R.id.tabs);
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) findViewById.getLayoutParams();
                    layoutParams.bottomMargin = getResources().getDimensionPixelOffset(R.dimen.common_dp_50);
                    findViewById.setLayoutParams(layoutParams);
                    this.m.setVisibility(0);
                    this.n.setVisibility(0);
                    this.p.setVisibility(0);
                    this.q.setVisibility(0);
                    LayoutParams layoutParams2 = (LayoutParams) this.p.getLayoutParams();
                    LayoutParams layoutParams3 = (LayoutParams) this.n.getLayoutParams();
                    LayoutParams layoutParams4 = (LayoutParams) this.o.getLayoutParams();
                    if (a) {
                        this.o.setVisibility(0);
                        layoutParams3.width = (int) (((double) com.qq.reader.common.c.a.bU) * 0.4d);
                        layoutParams4.width = (int) (((double) com.qq.reader.common.c.a.bU) * 0.3d);
                        layoutParams2.width = (int) (((double) com.qq.reader.common.c.a.bU) * 0.3d);
                        layoutParams2.leftMargin = 0;
                    } else {
                        this.o.setVisibility(8);
                        layoutParams3.width = (int) (((double) com.qq.reader.common.c.a.bU) * 0.5d);
                        layoutParams2.width = (int) (((double) com.qq.reader.common.c.a.bU) * 0.5d);
                    }
                    if (b == 0) {
                        this.n.setText(R.string.webpage_bookinfo_readonline_free);
                        return;
                    } else {
                        this.n.setText(R.string.webpage_bookinfo_readonline_pay);
                        return;
                    }
                default:
                    return;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        e.printStackTrace();
    }

    protected void a() {
        this.mHandler.sendEmptyMessage(1220);
    }

    protected boolean handleMessageImp(Message message) {
        com.qq.reader.cservice.buy.a.c cVar;
        switch (message.what) {
            case 1203:
                if (q()) {
                    g gVar = (g) message.obj;
                    if (this.k != null) {
                        this.k.a(gVar.c());
                        if (this.k.d() == Long.parseLong(gVar.e())) {
                            n();
                            break;
                        }
                    }
                }
                break;
            case 1204:
            case 21001:
                if (q()) {
                    af.a(getApplicationContext(), getString(R.string.download_faile), 0).a();
                    break;
                }
                break;
            case 1205:
                if (q()) {
                    showFragmentDialog(606);
                    break;
                }
                break;
            case 1217:
                a(String.valueOf(this.k.d()), this.k.c());
                break;
            case 1218:
                i.a("event_B144", null, ReaderApplication.getApplicationImp());
                if (s()) {
                    cVar = (com.qq.reader.cservice.buy.a.c) message.obj;
                    try {
                        if (this.k != null) {
                            this.k.a(cVar.b());
                            if (this.k.d() == Long.parseLong(cVar.c())) {
                                n();
                                break;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        break;
                    }
                }
                break;
            case 1219:
                if (s()) {
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
            case 1220:
                Bundle bundle2;
                if (message.arg1 == 1) {
                    bundle2 = (Bundle) message.obj;
                    t();
                    if (message.arg2 != 1) {
                        this.t.clear();
                    }
                    this.t.add(bundle2);
                } else if (this.t.size() == 1) {
                    finish();
                    break;
                } else {
                    t();
                    this.t.pop();
                    bundle2 = (Bundle) this.t.peek();
                }
                c(bundle2);
                break;
        }
        return true;
    }

    protected void a(Bundle bundle) {
        Bundle bundle2;
        if (bundle != null) {
            Collection parcelableArrayList = bundle.getParcelableArrayList("urlstack");
            this.t.clear();
            this.t.addAll(parcelableArrayList);
            int size = this.t.size();
            if (size > 0) {
                bundle2 = (Bundle) this.t.get(size - 1);
            } else {
                bundle2 = getIntent().getExtras();
                this.t.add(bundle2);
            }
        } else {
            bundle2 = getIntent().getExtras();
            this.t.add(bundle2);
        }
        c(bundle2);
    }

    protected String e() {
        return "书籍详情";
    }

    protected int f() {
        return R.layout.webpage_bookdetail_layout;
    }

    public void setTouched(boolean z) {
    }

    private void a(String str, String str2) {
        g gVar = new g(str);
        gVar.e(str2);
        h hVar = new h(getApplicationContext(), gVar);
        hVar.a(this);
        p();
        hVar.start();
    }

    public void a(g gVar) {
        this.mHandler.obtainMessage(1203, gVar).sendToTarget();
    }

    public void b(g gVar) {
        this.mHandler.obtainMessage(1204, gVar).sendToTarget();
    }

    public void c(g gVar) {
        this.mHandler.obtainMessage(1205, gVar).sendToTarget();
    }

    private void p() {
        try {
            if (this.u == null) {
                this.u = new c(this);
                this.u.c(true);
                this.u.a(getResources().getString(R.string.get_book_music_feed_loading));
            }
            if (!this.u.f()) {
                this.u.f_();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean q() {
        try {
            if (this.u != null && this.u.f()) {
                this.u.cancel();
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    private void r() {
        try {
            if (this.r == null || !this.r.isShowing()) {
                this.r = ProgressDialog.show(this, "", "正在购买，请稍候...", true);
                this.r.setCanceledOnTouchOutside(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean s() {
        try {
            if (this.r != null && this.r.isShowing()) {
                this.r.cancel();
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    protected Dialog createDialog(int i, Bundle bundle) {
        AlertDialog alertDialog = (AlertDialog) q.a(this, i, null);
        String str = "";
        switch (i) {
            case 606:
                View inflate = LayoutInflater.from(this).inflate(R.layout.book_buy_view, null);
                TextView textView = (TextView) inflate.findViewById(R.id.book_discount_msg);
                final String valueOf = String.valueOf(this.k.d());
                String h = this.k.h();
                int e = this.k.e();
                int f = this.k.f();
                CharSequence stringBuilder = new StringBuilder();
                Object spannableStringBuilder = new SpannableStringBuilder();
                stringBuilder.append("价　　格：");
                if (f < 100) {
                    textView.setVisibility(0);
                    f = (f * e) / 100;
                    stringBuilder.append(e);
                    stringBuilder.append("书币");
                    e = stringBuilder.length();
                    stringBuilder.append(" ");
                    stringBuilder.append(f);
                    stringBuilder.append("书币　");
                    spannableStringBuilder.append(stringBuilder);
                    spannableStringBuilder.setSpan(new StrikethroughSpan(), 5, e, 17);
                    spannableStringBuilder.setSpan(new ForegroundColorSpan(-8355712), 5, e, 17);
                    String g = this.k.g();
                    if (g != null && g.trim().length() > 0) {
                        CharSequence stringBuilder2 = new StringBuilder();
                        stringBuilder2.append("　　　　　(");
                        stringBuilder2.append(g);
                        stringBuilder2.append(")");
                        textView.setText(stringBuilder2);
                    }
                } else {
                    textView.setVisibility(8);
                    stringBuilder.append(e);
                    stringBuilder.append("书币");
                    spannableStringBuilder.append(stringBuilder);
                }
                textView = (TextView) inflate.findViewById(R.id.book_name);
                textView.setText(String.format(getResources().getString(R.string.buy_book_name), new Object[]{h}));
                ((TextView) inflate.findViewById(R.id.book_price)).setText(spannableStringBuilder);
                alertDialog.a(inflate);
                alertDialog.a((int) R.string.alert_dialog_buy, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ WebBookDetailActivity b;

                    public void onClick(DialogInterface dialogInterface, int i) {
                        com.qq.reader.cservice.buy.a.d dVar = new com.qq.reader.cservice.buy.a.d(this.b.s, valueOf);
                        dVar.a(this.b);
                        dVar.start();
                        this.b.r();
                    }
                });
                alertDialog.b((int) R.string.alert_dialog_cancel, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ WebBookDetailActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                alertDialog.a(-1, (int) R.drawable.selector_orange_button);
                alertDialog.a(-2, (int) R.drawable.selector_white_button);
                break;
            case 607:
                alertDialog.a(bundle.getString("message"));
                alertDialog.a((int) R.string.alert_dialog_ok, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ WebBookDetailActivity a;

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
                    final /* synthetic */ WebBookDetailActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        this.a.j();
                    }
                });
                alertDialog.b((int) R.string.alert_dialog_cancel, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ WebBookDetailActivity a;

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

    public void j() {
        new JSPay(this).startCharge(this, 0);
    }

    public void b(int i) {
        if (i >= 0 && i < this.g.size()) {
            this.b.setCurrentItem(i);
        }
    }

    public void b(Bundle bundle) {
        Message obtain = Message.obtain();
        obtain.what = 1220;
        obtain.obj = bundle;
        obtain.arg1 = 1;
        obtain.arg2 = 1;
        this.mHandler.sendMessage(obtain);
    }

    private void t() {
        this.m.setVisibility(8);
        this.n.setVisibility(8);
        this.o.setVisibility(8);
        this.p.setVisibility(8);
        this.q.setVisibility(8);
        View findViewById = findViewById(R.id.tabs);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) findViewById.getLayoutParams();
        layoutParams.bottomMargin = 0;
        findViewById.setLayoutParams(layoutParams);
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        Bundle extras = intent.getExtras();
        if (extras != null) {
            Message obtain = Message.obtain();
            obtain.what = 1220;
            obtain.obj = extras;
            obtain.arg1 = 1;
            this.mHandler.sendMessage(obtain);
        }
    }

    private void c(Bundle bundle) {
        long j = bundle.getLong("bid", 0);
        String string = bundle.getString("extraurl");
        HashMap hashMap = new HashMap();
        hashMap.put("book_url", e.a((Context) this, j) + string);
        this.g.clear();
        this.g.add(new TabInfo(WebBrowserFragment.class, "", "简介", hashMap));
        c();
    }

    protected void onSaveInstanceState(Bundle bundle) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.t);
        bundle.putParcelableArrayList("urlstack", arrayList);
    }

    public void finish() {
        k();
        super.finish();
    }

    public void k() {
        try {
            WebBrowserFragment webBrowserFragment = (WebBrowserFragment) d();
            if (webBrowserFragment != null) {
                webBrowserFragment.getWebView().stopLoading();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean b() {
        return true;
    }

    public View a(int i) {
        TabInfo tabInfo = (TabInfo) this.g.get(i);
        View inflate = getLayoutInflater().inflate(R.layout.profileaccount_tab_item, null);
        TextView textView = (TextView) inflate.findViewById(R.id.tab_text);
        textView.setText(tabInfo.title);
        textView.setTextColor(getResources().getColor(R.color.textcolor_white));
        if (this.j.size() > i) {
            this.j.set(i, inflate);
        } else {
            while (this.j.size() <= i) {
                this.j.add(null);
            }
            this.j.set(i, inflate);
        }
        if (b()) {
            if (this.b.getCurrentItem() == i) {
                textView.setTextColor(getResources().getColor(R.color.textcolor_green));
            } else {
                textView.setTextColor(getResources().getColor(R.color.textcolor_black));
            }
        }
        return inflate;
    }
}
