package com.qq.reader.module.redpacket.sendpacket;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import com.qq.reader.activity.ReaderBaseActivity;
import com.qq.reader.appconfig.a.b;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.charge.voucher.a.a;
import com.qq.reader.common.login.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.readertask.ReaderTask;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.protocol.FeedDataTask;
import com.qq.reader.common.readertask.protocol.H5GameGrantTicketTask;
import com.qq.reader.common.readertask.protocol.QueryUserBalanceTask;
import com.qq.reader.common.readertask.protocol.SendRedPacketTask;
import com.qq.reader.common.utils.o;
import com.qq.reader.common.web.js.JSPay;
import com.qq.reader.module.readpage.voteview.net.GetVoteUserIconsTask;
import com.qq.reader.view.AlertDialog;
import com.qq.reader.view.af;
import com.qq.reader.view.m;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.feedback.proguard.R;
import com.tencent.qalsdk.im_open.http;
import java.net.URLEncoder;
import org.json.JSONException;
import org.json.JSONObject;

public class SendRedpacketActivity extends ReaderBaseActivity {
    private static final String a = SendRedpacketActivity.class.getSimpleName();
    private int A;
    private int B;
    private int C;
    private String D;
    private String E;
    private int F;
    private double G;
    private String H;
    private int I = 0;
    private long J;
    private String K;
    private String L;
    private View M;
    private boolean N = false;
    private boolean O = false;
    private boolean P = false;
    private final a Q = new a();
    private boolean R = false;
    private OnGlobalLayoutListener S = new OnGlobalLayoutListener(this) {
        boolean a = false;
        final /* synthetic */ SendRedpacketActivity b;

        {
            this.b = r2;
        }

        public void onGlobalLayout() {
            Rect rect = new Rect();
            this.b.M.getWindowVisibleDisplayFrame(rect);
            int i = com.qq.reader.common.c.a.bT - (rect.bottom - rect.top);
            LayoutParams layoutParams = this.b.j.getLayoutParams();
            if (layoutParams == null) {
                return;
            }
            if (i > com.qq.reader.common.c.a.bT / 3) {
                if (!this.a) {
                    this.a = true;
                    i = ((com.qq.reader.common.c.a.bT - this.b.j.getTop()) - i) + com.qq.reader.common.c.a.ca;
                    int height = this.b.j.getHeight() - this.b.m.getBottom();
                    if (height < i) {
                        this.b.q.setPadding(0, 0, 0, i - height);
                    }
                    layoutParams.height = i;
                    this.b.j.setLayoutParams(layoutParams);
                }
            } else if (this.a) {
                this.a = false;
                layoutParams.height = com.qq.reader.common.c.a.bT - this.b.j.getTop();
                this.b.j.setLayoutParams(layoutParams);
            }
        }
    };
    private TextView b;
    private int c = 1;
    private String d;
    private com.qq.reader.common.login.b.a e;
    private View f;
    private EditText g;
    private TextView h;
    private TextView i;
    private ScrollView j;
    private EditText k;
    private TextView l;
    private TextView m;
    private View n;
    private EditText o;
    private String p;
    private TextView q;
    private TextView r;
    private TextView s;
    private TextView t;
    private TextView u;
    private Button v;
    private AlertDialog w;
    private String x;
    private String y;
    private int z;

    protected boolean handleMessageImp(Message message) {
        switch (message.what) {
            case 0:
                if (this.O) {
                    this.v.setText("已被拉黑，无法发红包");
                }
                if (this.N) {
                    this.v.setText("本书暂不支持发红包");
                }
                progressCancel();
                if (!(this.g == null || TextUtils.isEmpty(this.g.getText()))) {
                    a(this.g.getText().toString());
                    this.q.setText(String.format(getResources().getString(R.string.sendpacket_expired_days), new Object[]{Integer.valueOf(this.F * 24)}));
                }
                if (this.c != 0) {
                    this.o.setHint(String.format(this.y, new Object[]{this.D}));
                }
                if (this.R) {
                    b(this.E);
                }
                this.i.setText(String.format(getResources().getString(R.string.sendpacket_min_amount), new Object[]{Integer.valueOf(this.z)}));
                this.m.setText(String.format(getResources().getString(R.string.single_redpacket_amount_min_tip), new Object[]{Integer.valueOf(this.B)}));
                this.s.setText(this.H);
                b.a(this, this.c + "min_portion", this.B);
                b.a(this, this.c + "max_portion", this.C);
                b.a(this, this.c + "max_amount", this.A);
                b.a(this, this.c + "min_amount", this.z);
                b.a(this, this.c + "tax", (float) this.G);
                b.a(this, this.c + "tax_des", this.H);
                b.a(this, this.c + "expired_days", this.F);
                break;
            case 1:
                progressCancel();
                if (this.R) {
                    af.a(this, "网络异常，请稍后重试", 1).a();
                    break;
                }
                break;
            case 2:
                if (!c.b()) {
                    a(2, null);
                    break;
                }
                i();
                d();
                break;
            case 3:
                if (this.w != null && this.w.isShowing()) {
                    this.w.dismiss();
                }
                progressCancel();
                Intent intent = new Intent();
                intent.putExtra("send_packet_success", true);
                intent.putExtra("amount", this.g.getText().toString());
                intent.putExtra("type", this.b.getText().toString());
                intent.putExtra("avatar", this.K);
                intent.putExtra("nickname", this.L);
                intent.putExtra("message", this.p);
                intent.putExtra("rid", this.J);
                setResult(-1, intent);
                finish();
                break;
            case 4:
                af.a(this, "出错了，请稍后重试", 1).a();
                progressCancel();
                break;
            case 5:
                af.a(this, "网络异常，请稍后重试", 1).a();
                progressCancel();
                break;
            case 1001:
                switch (message.arg1) {
                    case R.id.redpacket_amount_edit:
                        this.g.setSelection(this.g.getText().length());
                        break;
                    case R.id.redpacket_sum_edit:
                        this.k.setSelection(this.k.getText().length());
                        break;
                    default:
                        break;
                }
            case 2001:
                progressCancel();
                if (this.O) {
                    af.a(this, "已被拉黑，无法发红包", 1).a();
                }
                if (this.N) {
                    af.a(this, "本书暂不支持发红包", 1).a();
                    break;
                }
                break;
        }
        return super.handleMessageImp(message);
    }

    private void d() {
        int i;
        if (this.e == null) {
            this.e = c.c();
        }
        if (this.Q.b != 0) {
            i = this.Q.b;
        } else {
            i = this.e.g(getContext());
        }
        if (this.e == null || i < this.I) {
            a("余额不足，需充值" + (this.I - i) + "书币", true, this.I - i);
        } else {
            a(getContext().getString(R.string.audio_question_eavesdropping_submit), false, 0);
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.send_redpacket_layout);
        this.M = findViewById(R.id.top_menu);
        this.j = (ScrollView) findViewById(R.id.scrollview);
        Intent intent = getIntent();
        if (intent != null) {
            this.c = intent.getIntExtra("type", 1);
            this.d = intent.getStringExtra("bid");
        }
        e();
        h();
        a();
        f();
        i();
        b();
    }

    private void e() {
        switch (this.c) {
            case 0:
                this.y = getResources().getString(R.string.ordinary_message_tip);
                this.x = this.y;
                return;
            case 1:
                this.x = getResources().getString(R.string.month_redpacket_default_message);
                this.y = getResources().getString(R.string.month_redpacket_message_tip);
                return;
            case 2:
                this.x = getResources().getString(R.string.recommend_redpacket_default_message);
                this.y = getResources().getString(R.string.recommend_redpacket_message_tip);
                return;
            default:
                return;
        }
    }

    private void f() {
        getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(this.S);
        this.f = findViewById(R.id.amount_area);
        this.g = (EditText) findViewById(R.id.redpacket_amount_edit);
        this.h = (TextView) findViewById(R.id.redpacket_amount);
        this.i = (TextView) findViewById(R.id.redpacket_amount_tip);
        this.o = (EditText) findViewById(R.id.redpacket_message);
        this.q = (TextView) findViewById(R.id.expired_days);
        this.i.setText(String.format(getResources().getString(R.string.sendpacket_min_amount), new Object[]{Integer.valueOf(this.z)}));
        this.t = (TextView) findViewById(R.id.amount_unit);
        this.u = (TextView) findViewById(R.id.sum_unit);
        this.q.setText(String.format(getResources().getString(R.string.sendpacket_expired_days), new Object[]{Integer.valueOf(this.F * 24)}));
        this.g.addTextChangedListener(new TextWatcher(this) {
            final /* synthetic */ SendRedpacketActivity a;

            {
                this.a = r1;
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                if (TextUtils.isEmpty(editable)) {
                    this.a.i.setTextColor(this.a.getResources().getColor(R.color.text_color_c103));
                    this.a.h.setTextColor(this.a.getResources().getColor(R.color.text_color_c101));
                    this.a.i.setText(String.format(this.a.getResources().getString(R.string.sendpacket_min_amount), new Object[]{Integer.valueOf(this.a.z)}));
                    this.a.f.setBackgroundResource(R.drawable.redpacket_edit_bg);
                    this.a.g.setTextColor(this.a.getResources().getColor(R.color.text_color_c101));
                    this.a.t.setTextColor(this.a.getResources().getColor(R.color.text_color_c101));
                } else if (editable.length() > 9) {
                    editable.delete(9, editable.length());
                    this.a.g.setText(editable);
                    this.a.g.setSelection(9);
                } else {
                    this.a.a(editable.toString());
                }
            }
        });
        this.k = (EditText) findViewById(R.id.redpacket_sum_edit);
        this.l = (TextView) findViewById(R.id.sum_text);
        this.n = findViewById(R.id.sum_area);
        this.m = (TextView) findViewById(R.id.sum_tip);
        this.m.setText(String.format(getResources().getString(R.string.single_redpacket_amount_min_tip), new Object[]{Integer.valueOf(this.B)}));
        this.r = (TextView) findViewById(R.id.sum_with_tax);
        this.k.addTextChangedListener(new TextWatcher(this) {
            final /* synthetic */ SendRedpacketActivity a;

            {
                this.a = r1;
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                if (TextUtils.isEmpty(editable)) {
                    this.a.m.setTextColor(this.a.getResources().getColor(R.color.text_color_c103));
                    this.a.l.setTextColor(this.a.getResources().getColor(R.color.text_color_c101));
                    this.a.m.setText(String.format(this.a.getResources().getString(R.string.single_redpacket_amount_min_tip), new Object[]{Integer.valueOf(this.a.B)}));
                    this.a.I = 0;
                    this.a.v.setEnabled(false);
                    this.a.r.setText(String.valueOf(this.a.I));
                    this.a.n.setBackgroundResource(R.drawable.redpacket_edit_bg);
                    this.a.k.setTextColor(this.a.getResources().getColor(R.color.text_color_c101));
                    this.a.u.setTextColor(this.a.getResources().getColor(R.color.text_color_c101));
                } else if (editable.length() > 9) {
                    editable.delete(9, editable.length());
                    this.a.k.setText(editable);
                    this.a.k.setSelection(9);
                } else if (!TextUtils.isEmpty(editable) && !TextUtils.isEmpty(this.a.g.getText())) {
                    this.a.a(editable.toString(), Integer.valueOf(this.a.g.getText().toString()).intValue());
                }
            }
        });
        this.o.addTextChangedListener(new TextWatcher(this) {
            final /* synthetic */ SendRedpacketActivity a;

            {
                this.a = r1;
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                if (editable.length() > 30) {
                    editable.delete(30, editable.length());
                    this.a.o.setText(editable);
                    this.a.o.setSelection(30);
                    af.a(this.a, "留言不超过30字", 1).a();
                }
            }
        });
        findViewById(R.id.send_btn).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ SendRedpacketActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (c.b()) {
                    this.a.d();
                } else {
                    this.a.a(2, null);
                }
            }
        });
        this.s = (TextView) findViewById(R.id.process_fee);
        this.s.setText(this.H);
        this.v = (Button) findViewById(R.id.send_btn);
        this.o.setHint(this.x);
        g();
    }

    private void a(String str) {
        if (Integer.valueOf(str.toString()).intValue() < this.z) {
            this.i.setTextColor(getResources().getColor(R.color.text_color_c401));
            this.h.setTextColor(getResources().getColor(R.color.text_color_c401));
            this.i.setText(String.format(getResources().getString(R.string.sendpacket_min_amount), new Object[]{Integer.valueOf(this.z)}));
            this.f.setBackgroundResource(R.drawable.redpacket_edit_dis_bg);
            this.v.setEnabled(false);
            this.P = false;
            this.g.setTextColor(getResources().getColor(R.color.text_color_c401));
            this.t.setTextColor(getResources().getColor(R.color.text_color_c401));
        } else if (Integer.valueOf(str.toString()).intValue() > this.A) {
            this.i.setTextColor(getResources().getColor(R.color.text_color_c401));
            this.h.setTextColor(getResources().getColor(R.color.text_color_c401));
            this.i.setText(String.format(getResources().getString(R.string.sendpacket_max_amount), new Object[]{Integer.valueOf(this.A)}));
            this.f.setBackgroundResource(R.drawable.redpacket_edit_dis_bg);
            this.v.setEnabled(false);
            this.P = false;
            this.g.setTextColor(getResources().getColor(R.color.text_color_c401));
            this.t.setTextColor(getResources().getColor(R.color.text_color_c401));
        } else {
            this.i.setTextColor(getResources().getColor(R.color.text_color_c103));
            this.h.setTextColor(getResources().getColor(R.color.text_color_c101));
            this.i.setText(String.format(getResources().getString(R.string.sendpacket_min_amount), new Object[]{Integer.valueOf(this.z)}));
            this.f.setBackgroundResource(R.drawable.redpacket_edit_bg);
            this.g.setTextColor(getResources().getColor(R.color.text_color_c101));
            this.t.setTextColor(getResources().getColor(R.color.text_color_c101));
            String obj = this.k.getText().toString();
            this.P = true;
            if (!TextUtils.isEmpty(obj)) {
                a(obj, Integer.valueOf(str.toString()).intValue());
            }
        }
    }

    protected void onResume() {
        super.onResume();
        if (this.g != null && TextUtils.isEmpty(this.g.getText())) {
            this.g.requestFocus();
        }
    }

    protected void onPause() {
        super.onPause();
    }

    private void g() {
        this.g.setOnFocusChangeListener(new OnFocusChangeListener(this) {
            final /* synthetic */ SendRedpacketActivity a;

            {
                this.a = r1;
            }

            public void onFocusChange(View view, boolean z) {
                if (z) {
                    this.a.j.smoothScrollBy(0, -this.a.j.getScrollY());
                    Message obtainMessage = this.a.mHandler.obtainMessage(1001);
                    obtainMessage.arg1 = R.id.redpacket_amount_edit;
                    this.a.mHandler.sendMessage(obtainMessage);
                }
            }
        });
        this.k.setOnFocusChangeListener(new OnFocusChangeListener(this) {
            final /* synthetic */ SendRedpacketActivity a;

            {
                this.a = r1;
            }

            public void onFocusChange(View view, boolean z) {
                if (z) {
                    this.a.j.smoothScrollBy(0, this.a.i.getBottom() - this.a.j.getScrollY());
                    Message obtainMessage = this.a.mHandler.obtainMessage(1001);
                    obtainMessage.arg1 = R.id.redpacket_sum_edit;
                    this.a.mHandler.sendMessage(obtainMessage);
                }
            }
        });
        this.o.setOnFocusChangeListener(new OnFocusChangeListener(this) {
            final /* synthetic */ SendRedpacketActivity a;

            {
                this.a = r1;
            }

            public void onFocusChange(View view, boolean z) {
                if (z) {
                    this.a.j.smoothScrollBy(0, this.a.m.getBottom() - this.a.j.getScrollY());
                }
            }
        });
    }

    private void a(String str, int i) {
        if (!TextUtils.isEmpty(str)) {
            if (Integer.valueOf(str.toString()).intValue() < this.B * i) {
                this.m.setTextColor(getResources().getColor(R.color.text_color_c401));
                this.l.setTextColor(getResources().getColor(R.color.text_color_c401));
                this.m.setText(String.format(getResources().getString(R.string.single_redpacket_amount_min_tip), new Object[]{Integer.valueOf(this.B)}));
                this.n.setBackgroundResource(R.drawable.redpacket_edit_dis_bg);
                this.r.setText("0");
                this.k.setTextColor(getResources().getColor(R.color.text_color_c401));
                this.u.setTextColor(getResources().getColor(R.color.text_color_c401));
                this.v.setEnabled(false);
            } else if (Integer.valueOf(str.toString()).intValue() > this.C * i) {
                this.m.setTextColor(getResources().getColor(R.color.text_color_c401));
                this.l.setTextColor(getResources().getColor(R.color.text_color_c401));
                this.m.setText(String.format(getResources().getString(R.string.single_redpacket_amount_max_tip), new Object[]{Integer.valueOf(this.C)}));
                this.n.setBackgroundResource(R.drawable.redpacket_edit_dis_bg);
                this.v.setEnabled(false);
                this.r.setText("0");
                this.k.setTextColor(getResources().getColor(R.color.text_color_c401));
                this.u.setTextColor(getResources().getColor(R.color.text_color_c401));
            } else {
                this.m.setTextColor(getResources().getColor(R.color.text_color_c103));
                this.l.setTextColor(getResources().getColor(R.color.text_color_c101));
                this.m.setText(String.format(getResources().getString(R.string.single_redpacket_amount_min_tip), new Object[]{Integer.valueOf(this.B)}));
                this.I = (int) (((double) Integer.valueOf(str.toString()).intValue()) * (1.0d + this.G));
                this.r.setText(String.valueOf(this.I));
                this.n.setBackgroundResource(R.drawable.redpacket_edit_bg);
                if (!(this.N || this.O || !this.P)) {
                    this.v.setEnabled(true);
                }
                this.k.setTextColor(getResources().getColor(R.color.text_color_c101));
                this.u.setTextColor(getResources().getColor(R.color.text_color_c101));
            }
        }
    }

    private void h() {
        this.G = (double) b.b(this, this.c + "tax", 0.0f);
        this.H = b.b(this, this.c + "tax_des", "活动期间免手续费");
        this.A = b.b(this, this.c + "max_amount", 200);
        this.z = b.b(this, this.c + "min_amount", 5);
        this.C = b.b(this, this.c + "max_portion", 20000);
        this.F = b.b(this, this.c + "expired_days", 3);
        switch (this.c) {
            case 0:
                this.B = b.b(this, this.c + "min_portion", 10);
                return;
            case 1:
                this.B = b.b(this, this.c + "min_portion", http.Internal_Server_Error);
                return;
            case 2:
                this.B = b.b(this, this.c + "min_portion", 20);
                return;
            default:
                return;
        }
    }

    private void a(String str, final boolean z, final int i) {
        AlertDialog.a aVar = new AlertDialog.a(this);
        aVar.a(this.b.getText()).b(getApplicationContext().getString(R.string.send_redpacket_dialog_message, new Object[]{Integer.valueOf(this.I)})).a(str, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ SendRedpacketActivity c;

            public void onClick(DialogInterface dialogInterface, int i) {
                if (z) {
                    this.c.a(i);
                    return;
                }
                this.c.R = true;
                this.c.a("正在发红包", this.c.getResources().getDrawable(R.drawable.red_packet_progress_loading_40x40));
                if (TextUtils.isEmpty(this.c.E)) {
                    this.c.i();
                } else {
                    this.c.b(this.c.E);
                }
            }
        }).a(new OnCancelListener(this) {
            final /* synthetic */ SendRedpacketActivity a;

            {
                this.a = r1;
            }

            public void onCancel(DialogInterface dialogInterface) {
                i.a("event_D121", null, this.a.getApplicationContext());
            }
        });
        if (!z) {
            aVar.b(R.string.alert_dialog_cancel, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ SendRedpacketActivity a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    i.a("event_D120", null, this.a.getApplicationContext());
                }
            });
        }
        this.w = aVar.a();
        if (z) {
            this.w.a(-1, R.drawable.selector_orange_button);
        }
        this.w.show();
    }

    public void a(String str, Drawable drawable) {
        if (!isFinishing()) {
            if (this.mProgressDialog == null) {
                if (str == null) {
                    str = "";
                }
                this.mProgressDialog = new m(this);
                this.mProgressDialog.a(str);
                this.mProgressDialog.a(drawable);
                this.mProgressDialog.a(new OnKeyListener(this) {
                    final /* synthetic */ SendRedpacketActivity a;

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
            }
            this.mProgressDialog.f_();
        }
    }

    private void b(String str) {
        this.p = this.o.getText().toString();
        if (TextUtils.isEmpty(this.p)) {
            this.p = this.o.getHint().toString();
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(e.a.b);
        stringBuilder.append("&uuid=").append(str);
        stringBuilder.append(FeedDataTask.MS_TYPE).append(this.c);
        stringBuilder.append(H5GameGrantTicketTask.COMMON_COUNT).append(this.g.getText().toString());
        stringBuilder.append("&amount=").append(this.k.getText().toString());
        stringBuilder.append(GetVoteUserIconsTask.BID).append(this.d);
        stringBuilder.append("&desc=").append(URLEncoder.encode(this.p));
        g.a().a(new SendRedPacketTask(stringBuilder.toString(), new com.qq.reader.common.readertask.ordinal.c(this) {
            final /* synthetic */ SendRedpacketActivity a;

            {
                this.a = r1;
            }

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                com.qq.reader.common.monitor.debug.c.a(SendRedpacketActivity.a, "str = " + str);
                if (this.a.w != null) {
                    this.a.w.dismiss();
                }
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    int optInt = jSONObject.optInt("code");
                    if (optInt == 0) {
                        this.a.progressCancel();
                        this.a.J = jSONObject.optLong("rid");
                        JSONObject optJSONObject = jSONObject.optJSONObject("ownerInfo");
                        if (jSONObject != null) {
                            this.a.K = optJSONObject.optString(MessageKey.MSG_ICON);
                            this.a.L = optJSONObject.optString("name");
                        }
                        String optString = jSONObject.optString("redPacketDesc");
                        if (!TextUtils.isEmpty(optString)) {
                            this.a.p = optString;
                        }
                        this.a.mHandler.sendEmptyMessage(3);
                        return;
                    }
                    int optInt2 = jSONObject.optInt("errCode");
                    if (optInt == -1 && (optInt2 == -1001001 || optInt2 == -1001002 || optInt2 == -1001003 || optInt2 == -1001004)) {
                        this.a.i();
                    } else if (optInt == -1 && optInt2 == -1000104) {
                        this.a.N = true;
                        this.a.mHandler.sendEmptyMessage(2001);
                    } else if (optInt == -1 && (optInt2 == -1000101 || optInt2 == -1000102 || optInt2 == -1000103)) {
                        this.a.O = true;
                        this.a.mHandler.sendEmptyMessage(2001);
                    } else {
                        this.a.mHandler.sendEmptyMessage(4);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    this.a.mHandler.sendEmptyMessage(4);
                }
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                this.a.mHandler.sendEmptyMessage(5);
            }
        }));
    }

    protected void onDestroy() {
        super.onDestroy();
        getWindow().getDecorView().getViewTreeObserver().removeGlobalOnLayoutListener(this.S);
    }

    private void a(int i) {
        new JSPay(this).startCharge(this, i, true);
    }

    private void a(final int i, final Object obj) {
        this.mLoginNextTask = new com.qq.reader.common.login.a(this) {
            final /* synthetic */ SendRedpacketActivity c;

            public void a(int i) {
                switch (i) {
                    case 1:
                        Message obtain = Message.obtain();
                        obtain.what = i;
                        obtain.obj = obj;
                        this.c.mHandler.sendMessage(obtain);
                        return;
                    default:
                        return;
                }
            }
        };
        startLogin();
    }

    public void a() {
        this.b = (TextView) findViewById(R.id.profile_header_title);
        findViewById(R.id.common_titler).setBackgroundResource(R.color.redpacket_title_bg);
        findViewById(R.id.profile_header_left_back).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ SendRedpacketActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.finish();
            }
        });
        Button button = (Button) findViewById(R.id.profile_header_right_button);
        button.setVisibility(0);
        button.setText("帮助");
        button.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ SendRedpacketActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                o.h(this.a, e.a.l, null);
            }
        });
        switch (this.c) {
            case 0:
                this.b.setText("普通红包");
                return;
            case 1:
                this.b.setText("月票红包");
                return;
            case 2:
                this.b.setText("推荐票红包");
                return;
            default:
                return;
        }
    }

    private void i() {
        ReaderTask readerProtocolJSONTask = new ReaderProtocolJSONTask(new com.qq.reader.common.readertask.ordinal.c(this) {
            final /* synthetic */ SendRedpacketActivity a;

            {
                this.a = r1;
            }

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                com.qq.reader.common.monitor.debug.c.a(SendRedpacketActivity.a, "str = " + str);
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    int optInt = jSONObject.optInt("code");
                    if (optInt == 0) {
                        this.a.G = jSONObject.optDouble("tax");
                        this.a.H = jSONObject.optString("taxDesc");
                        this.a.A = jSONObject.optInt("countMax");
                        this.a.z = jSONObject.optInt("countMin");
                        this.a.B = jSONObject.optInt("portionMin");
                        this.a.C = jSONObject.optInt("portionMax");
                        this.a.D = jSONObject.optString("authorName");
                        this.a.E = jSONObject.optString("uuid");
                        this.a.F = jSONObject.optInt("unUseExpiredDays");
                        this.a.mHandler.sendEmptyMessage(0);
                    } else if (optInt == -1000104) {
                        this.a.N = true;
                        this.a.mHandler.sendEmptyMessage(0);
                    } else if (optInt == -1000101 || optInt == -1000102 || optInt == -1000103) {
                        this.a.O = true;
                        this.a.mHandler.sendEmptyMessage(0);
                    } else {
                        this.a.mHandler.sendEmptyMessage(1);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                this.a.mHandler.sendEmptyMessage(1);
            }
        });
        readerProtocolJSONTask.setUrl(e.a.c + "bid=" + this.d + FeedDataTask.MS_TYPE + this.c);
        g.a().a(readerProtocolJSONTask);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 20001 && i2 == 0) {
            b();
        }
    }

    public void b() {
        g.a().a(new QueryUserBalanceTask(new QueryUserBalanceTask.a(this) {
            final /* synthetic */ SendRedpacketActivity a;

            {
                this.a = r1;
            }

            public void a(a aVar) {
                this.a.Q.a(aVar);
            }

            public void a() {
            }
        }, "", 0));
    }
}
