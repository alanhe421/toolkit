package com.qq.reader.common.charge;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.qq.reader.activity.ReaderBaseActivity;
import com.qq.reader.common.login.c;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.web.js.JSPay;
import com.qq.reader.module.bookstore.charge.b;
import com.qq.reader.view.af;
import com.tencent.feedback.proguard.R;

public class BookPayVipActivity extends ReaderBaseActivity implements OnClickListener {
    private boolean A;
    private Dialog B;
    private Context C;
    private int D = 100;
    private b E;
    private String F;
    private TextView G;
    private int H;
    private Dialog I;
    private int a;
    private int b;
    private int c;
    private int d;
    private String e;
    private String f;
    private Intent g;
    private TextView h;
    private TextView i;
    private TextView j;
    private TextView k;
    private TextView l;
    private TextView m;
    private TextView n;
    private TextView o;
    private TextView p;
    private TextView q;
    private TextView r;
    private TextView s;
    private ImageView t;
    private View u;
    private View v;
    private RelativeLayout w;
    private RelativeLayout x;
    private RelativeLayout y;
    private RelativeLayout z;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setIsShowNightMask(false);
        setContentView(R.layout.book_pay_vip);
        this.g = getIntent();
        this.C = this;
        this.a = this.g.getIntExtra("open_month", 0);
        this.H = c.c().d();
        this.A = this.g.getBooleanExtra("auto_pay", false);
        this.E = new b();
        this.E.a(this.mHandler);
        b();
        disableUseAnimation();
        this.mHandler.sendEmptyMessage(400009);
    }

    public boolean handleMessage(Message message) {
        if (this.B != null && this.B.isShowing()) {
            this.B.cancel();
        }
        switch (message.what) {
            case 400003:
                setResult(20003);
                this.E.b(this.a);
                finish();
                break;
            case 400004:
                a();
                break;
            case 400005:
                CharSequence charSequence = (String) message.obj;
                if (charSequence == null || charSequence.trim().length() == 0) {
                    charSequence = "开通失败，请稍后重试";
                }
                af.a((Context) this, charSequence, 0).a();
                break;
            case 400009:
                try {
                    this.I = a(this.C, R.string.bookopenvip_queryconfig);
                    this.I.show();
                } catch (Exception e) {
                }
                this.E.a(this.a);
                break;
            case 400010:
                if (this.I != null) {
                    this.I.dismiss();
                }
                if (message.obj != null && (message.obj instanceof b)) {
                    b bVar = (b) message.obj;
                    this.b = bVar.g();
                    this.c = bVar.a();
                    this.d = bVar.h();
                    this.F = bVar.c();
                    this.e = bVar.i();
                    this.f = bVar.j();
                    c();
                    break;
                }
            case 400011:
                if (this.I != null) {
                    this.I.dismiss();
                }
                af.a(this.C, (int) R.string.not_available, 0).a();
                setResult(2);
                finish();
                break;
        }
        return false;
    }

    private void a() {
        this.y.setVisibility(8);
        this.w.setVisibility(0);
        i.a("event_D92", null, this.C);
    }

    private void b() {
        this.G = (TextView) findViewById(R.id.bookcoin_charge_tile);
        this.G.setText(getString(R.string.literature_brand) + getString(R.string.openmonth));
        this.x = (RelativeLayout) findViewById(R.id.monthConfirmLayout);
        this.w = (RelativeLayout) findViewById(R.id.resultLayout);
        this.h = (TextView) findViewById(R.id.monthCount);
        this.i = (TextView) findViewById(R.id.costMoney);
        this.j = (TextView) findViewById(R.id.balance);
        this.k = (TextView) findViewById(R.id.confirm);
        this.k.setOnClickListener(this);
        this.u = findViewById(R.id.cancel_bookcoin_charge);
        this.u.setOnClickListener(this);
        this.l = (TextView) findViewById(R.id.chargeConfirm);
        this.m = (TextView) findViewById(R.id.chargeCancel);
        this.l.setOnClickListener(this);
        this.m.setOnClickListener(this);
        this.y = (RelativeLayout) findViewById(R.id.monthConfirmLayout_qqvip);
        this.z = (RelativeLayout) findViewById(R.id.confirm_qqvip);
        this.z.setOnClickListener(this);
        this.n = (TextView) findViewById(R.id.monthCount_qqvip);
        this.o = (TextView) findViewById(R.id.costMoney_qqvip);
        this.p = (TextView) findViewById(R.id.balance_qqvip);
        this.q = (TextView) findViewById(R.id.confirm_qqvip_detail);
        this.r = (TextView) findViewById(R.id.confirm_qqvip_count);
        this.t = (ImageView) findViewById(R.id.recommend_qq_img);
        this.s = (TextView) findViewById(R.id.confirm_qqvip_other);
        this.s.setOnClickListener(this);
        this.v = findViewById(R.id.cancel_charge_qqvip);
        this.v.setOnClickListener(this);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        setResult(2);
        finish();
        return true;
    }

    private void c() {
        if (this.H == 2 || this.H == 10 || this.H == 50) {
            this.x.setVisibility(0);
            this.h.setText(this.a + getString(R.string.month_vip_tail));
            this.i.setText(this.b + getString(R.string.charge_gift_bookcoin));
            this.j.setText("余额:" + this.d + getString(R.string.charge_gift_bookcoin));
            if (this.b <= this.d) {
                this.k.setText("确认支付");
            } else {
                this.k.setText("书币余额不足，请先充值");
            }
            i.a("event_D76", null, this.C);
        } else if (this.H == 1) {
            this.y.setVisibility(0);
            this.n.setText(this.a + getString(R.string.month_vip_tail));
            this.o.setText(getString(R.string.charge_value) + this.c);
            this.p.setText("(余额:" + this.d + getString(R.string.charge_gift_bookcoin) + ")");
            this.q.setText("需支付:" + this.b + getString(R.string.charge_gift_bookcoin));
            i.a("event_D79", null, this.C);
        }
    }

    private Dialog a(Context context, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.book_open_vip_progress_dialog, null);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.bookcoin_charge_dialog);
        ((TextView) inflate.findViewById(R.id.custom_progress_dialog_loading_text)).setText(i);
        Dialog dialog = new Dialog(context, R.style.BookCoinChargeDialog);
        dialog.setCancelable(true);
        dialog.setContentView(linearLayout);
        return dialog;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cancel_bookcoin_charge:
                setResult(2);
                finish();
                return;
            case R.id.confirm:
                break;
            case R.id.chargeConfirm:
                new JSPay(this).startCharge(this, 0);
                i.a("event_D93", null, this.C);
                return;
            case R.id.chargeCancel:
            case R.id.cancel_charge_qqvip:
                setResult(2);
                finish();
                return;
            case R.id.confirm_qqvip:
                i.a("event_D80", null, this.C);
                break;
            case R.id.confirm_qqvip_other:
                f.d("MonthVip", "confirm_other is month " + this.a + " and isAutoPay is " + this.A);
                b.a(this, this.A, this.e, this.f);
                i.a("event_D81", null, this.C);
                return;
            default:
                return;
        }
        if (this.H == 1 || this.b <= this.d) {
            if (this.B == null || !this.B.isShowing()) {
                this.B = a(this.C, R.string.bookopenvip_loading);
                this.B.show();
            }
            this.E.a(this.a, this.A);
            return;
        }
        if (this.H == 2 || this.H == 10 || this.H == 50) {
            i.a("event_D77", null, this.C);
        }
        b.a(this, String.valueOf(this.b - this.d), null);
    }

    public boolean needSetImmerseMode() {
        return false;
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        f.d("MonthVip", "bookpayvip requestCode is " + i + " and resultCode is " + i2);
        if (i == 20001) {
            if (i2 == 0 && intent != null) {
                this.d += intent.getIntExtra("realSaveNum", 0);
                c();
            }
        } else if (i == 20002 && i2 == 0) {
            if (intent != null) {
                intent.putExtra("charge_openfrombookcoin", true);
            }
            this.E.b(this.a);
            setResult(i2, intent);
            finish();
        }
    }

    protected boolean isLayoutFillWindow() {
        return false;
    }
}
