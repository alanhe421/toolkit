package com.qq.reader.module.bookstore.qnative.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import com.qq.reader.ReaderApplication;
import com.qq.reader.activity.WebBrowserForContents;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.login.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ao;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.readpage.q;
import com.qq.reader.plugin.w;
import com.qq.reader.plugin.w.b;
import com.qq.reader.plugin.x;
import com.qq.reader.view.AlertDialog;
import com.tencent.connect.common.Constants;
import com.tencent.feedback.proguard.R;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import java.util.HashMap;
import java.util.Map;

public class NativeSkinDetailActivity extends WebBrowserForContents implements b {
    private String j;
    private RelativeLayout s;
    private NativeSkinManageActivity.b t = null;
    private x u;
    private Toast v;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.j = extras.getString("plugin_id");
            this.u = w.b().c(this.j);
        }
        G();
    }

    public void excuteOnSwitchAccount(Context context) {
        super.excuteOnSwitchAccount(context);
        w.b().a((b) this);
        if (this.j != null) {
            this.u = w.b().c(this.j);
            a(this.u, this.t);
        }
    }

    protected void onResume() {
        super.onResume();
        w.b().a((b) this);
        a(this.u, this.t);
    }

    protected void onPause() {
        super.onPause();
        w.b().b((b) this);
    }

    private void G() {
        ((LayoutParams) ((RelativeLayout) findViewById(R.id.webview_layout)).getLayoutParams()).setMargins(0, 0, 0, (int) TypedValue.applyDimension(1, 50.0f, getResources().getDisplayMetrics()));
        this.s = (RelativeLayout) findViewById(R.id.detail_btn_layout);
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, -2);
        layoutParams.addRule(12, -1);
        layoutParams.addRule(9, -1);
        this.s.setLayoutParams(layoutParams);
        this.s.removeAllViews();
        this.t = null;
        H();
    }

    private void H() {
        View inflate = View.inflate(this, R.layout.skin_detail_bottom_ui, this.s);
        this.t = new NativeSkinManageActivity.b();
        this.t.g = inflate.findViewById(R.id.ll_btn);
        this.t.e = (TextView) inflate.findViewById(R.id.tv_state);
        this.t.d = (TextView) inflate.findViewById(R.id.tv_percent);
        this.t.f = (ProgressBar) inflate.findViewById(R.id.pb_percent);
        this.t.g.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NativeSkinDetailActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (!c.b()) {
                    Handler D = this.a.D();
                    if (D != null) {
                        D.sendEmptyMessage(10000406);
                    }
                } else if (this.a.u != null) {
                    switch (this.a.u.d()) {
                        case 0:
                        case 1:
                        case 6:
                            w.b().a(this.a.u);
                            if (this.a.u != null) {
                                Map hashMap = new HashMap();
                                hashMap.put(s.ORIGIN, this.a.u.i());
                                i.a("event_A171", hashMap, ReaderApplication.getApplicationImp());
                                return;
                            }
                            return;
                        case 2:
                        case 3:
                            w.b().b(this.a.u);
                            return;
                        case 4:
                            this.a.J();
                            return;
                        case 7:
                            if (ao.a(this.a.u)) {
                                w.b().e(Constants.DEFAULT_UIN);
                                ao.v(Constants.DEFAULT_UIN);
                                this.a.a(this.a.u, this.a.t);
                            }
                            w.b().a(this.a.u);
                            return;
                        default:
                            return;
                    }
                }
            }
        });
        a(this.u, this.t);
    }

    private void I() {
        if (this.u == null || Constants.DEFAULT_UIN.equals(this.u.i())) {
            d.j(ReaderApplication.getApplicationImp(), 0);
            return;
        }
        d.j(ReaderApplication.getApplicationImp(), 7);
        Map hashMap = new HashMap();
        hashMap.put(s.ORIGIN, this.u.i());
        i.a("event_A172", hashMap, ReaderApplication.getApplicationImp());
    }

    private void J() {
        w.b().b(this.u, (Activity) this);
    }

    private void a(x xVar, NativeSkinManageActivity.b bVar) {
        if (bVar != null && xVar != null) {
            int d = xVar.d();
            bVar.g.setEnabled(true);
            bVar.g.setBackgroundResource(R.drawable.selector_blue_button);
            bVar.f.setVisibility(8);
            bVar.e.setTextColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.localstore_textcolor_white));
            CharSequence charSequence = null;
            CharSequence string = ReaderApplication.getApplicationImp().getString(R.string.skin_execute_text_download);
            switch (d) {
                case 0:
                case 1:
                case 6:
                    string = ReaderApplication.getApplicationImp().getString(R.string.skin_execute_text_download);
                    String trim = xVar.o().trim();
                    if (trim != null && trim.length() > 0) {
                        if (w.b().d(xVar) > 0) {
                            string = "继续下载";
                            bVar.f.setVisibility(0);
                        } else {
                            string = "下载";
                        }
                        bVar.d.setVisibility(8);
                        bVar.e.setVisibility(0);
                        bVar.e.setText(string);
                        bVar.f.setProgress(w.b().d(xVar));
                        return;
                    }
                case 2:
                case 3:
                    bVar.f.setVisibility(0);
                    charSequence = w.b().c(xVar);
                    bVar.f.setProgress(w.b().d(xVar));
                    string = "";
                    break;
                case 4:
                    if (!xVar.i().equals(d.bS(ReaderApplication.getApplicationImp()))) {
                        bVar.g.setBackgroundResource(R.drawable.skin_detail_btn_use_bg);
                        string = ReaderApplication.getApplicationImp().getString(R.string.skin_execute_text_switch);
                        break;
                    }
                    string = ReaderApplication.getApplicationImp().getString(R.string.skin_execute_text_used);
                    bVar.g.setEnabled(false);
                    bVar.g.setBackgroundResource(R.drawable.skin_detail_btn_inuse_bg);
                    break;
                case 7:
                    string = ReaderApplication.getApplicationImp().getString(R.string.skin_execute_text_update);
                    break;
            }
            if (TextUtils.isEmpty(charSequence)) {
                bVar.d.setVisibility(8);
                bVar.d.setText("");
            } else {
                bVar.d.setVisibility(0);
                if ("正在安装...".equals(charSequence)) {
                    bVar.d.setText("正在切换...");
                } else {
                    bVar.d.setText(charSequence);
                }
            }
            if (TextUtils.isEmpty(string)) {
                bVar.e.setText("");
                bVar.e.setVisibility(8);
                return;
            }
            bVar.e.setText(string);
            bVar.e.setVisibility(0);
        }
    }

    public String A() {
        return this.j;
    }

    public Handler B() {
        return D();
    }

    protected Dialog createDialog(int i, Bundle bundle) {
        switch (i) {
            case ErrorCode.ERROR_SDKENGINE_ISCOMPATIBLE /*320*/:
                AlertDialog alertDialog = (AlertDialog) q.a(this, i, null);
                if (bundle == null || alertDialog == null) {
                    return null;
                }
                CharSequence string = bundle.getString("message");
                CharSequence string2 = bundle.getString("buttonok");
                final String string3 = bundle.getString("qurl");
                if (TextUtils.isEmpty(string) || TextUtils.isEmpty(string2)) {
                    return null;
                }
                alertDialog.a(string);
                alertDialog.a(-1, (int) R.drawable.buy_book_dialog_confirm_bg);
                alertDialog.a(string2, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ NativeSkinDetailActivity b;

                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        try {
                            com.qq.reader.qurl.c.a(this.b, string3);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (this.b.u != null) {
                            Map hashMap = new HashMap();
                            hashMap.put(s.ORIGIN, this.b.u.i());
                            i.a("event_A175", hashMap, ReaderApplication.getApplicationImp());
                        }
                    }
                });
                alertDialog.b((CharSequence) "取消", new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ NativeSkinDetailActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        if (this.a.u != null) {
                            Map hashMap = new HashMap();
                            hashMap.put(s.ORIGIN, this.a.u.i());
                            i.a("event_A176", hashMap, ReaderApplication.getApplicationImp());
                        }
                    }
                });
                if (this.u == null) {
                    return alertDialog;
                }
                Map hashMap = new HashMap();
                hashMap.put(s.ORIGIN, this.u.i());
                i.a("event_A174", hashMap, ReaderApplication.getApplicationImp());
                return alertDialog;
            default:
                return super.createDialog(i, bundle);
        }
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 10000404:
                this.u = w.b().c(this.j);
                if (!(this.u == null || this.t == null)) {
                    a(this.u, this.t);
                    if (this.u.d() == 4) {
                        Map hashMap = new HashMap();
                        hashMap.put(s.ORIGIN, this.u.i());
                        i.a("event_A173", hashMap, ReaderApplication.getApplicationImp());
                    }
                }
                if (4 == this.u.d() && !this.j.equals(d.bS(ReaderApplication.getApplicationImp()))) {
                    J();
                } else if (4 == this.u.d() && this.j.equals(d.bS(ReaderApplication.getApplicationImp()))) {
                    I();
                }
                return true;
            case 10000405:
                try {
                    String str = (String) message.obj;
                    if (this.v == null) {
                        this.v = Toast.makeText(this, str, 0);
                    } else {
                        this.v.setText(str);
                    }
                    this.v.show();
                } catch (Exception e) {
                    com.qq.reader.common.monitor.debug.c.e(getClass().getSimpleName(), e.toString());
                }
                return true;
            case 10000406:
                startLogin();
                return true;
            case 10000407:
                return true;
            case 10000408:
                try {
                    this.u = w.b().c(this.j);
                    if (!(this.u == null || this.t == null)) {
                        a(this.u, this.t);
                    }
                    showFragmentDialog(ErrorCode.ERROR_SDKENGINE_ISCOMPATIBLE, (Bundle) message.obj);
                } catch (Exception e2) {
                    com.qq.reader.common.monitor.debug.c.c(getClass().getSimpleName(), e2.toString(), true);
                }
                return true;
            default:
                return false;
        }
    }
}
