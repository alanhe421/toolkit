package com.qq.reader.view;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.qq.reader.activity.ReaderBaseActivity;
import com.qq.reader.activity.ReaderPageActivity;
import com.qq.reader.common.login.b.a;
import com.qq.reader.common.login.c;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.protocol.VoteInfoGetTask;
import com.qq.reader.common.readertask.protocol.VoteTask;
import com.qq.reader.common.readertask.protocol.VoteTicketQueryTask;
import com.qq.reader.common.utils.ao;
import com.qq.reader.module.readpage.voteview.net.b;
import com.tencent.feedback.proguard.R;
import com.tencent.util.WeakReferenceHandler;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: VoteAbstractDialog */
public abstract class as extends BaseDialog implements Callback, OnClickListener {
    public static String E = "";
    TextView A;
    TextView B;
    TextView C;
    LinearLayout D;
    int F = 0;
    a a;
    int b;
    int c;
    int d;
    Activity e;
    WeakReferenceHandler i;
    Handler j;
    String k = new String();
    long l;
    int m;
    boolean n;
    TextView o;
    TextView p;
    TextView q;
    View r;
    TextView s;
    View t;
    TextView u;
    View v;
    TextView w;
    ImageView x;
    TextView y;
    TextView z;

    protected abstract CharSequence a(int i, int i2);

    protected abstract void a(String str);

    protected abstract void b(int i);

    protected abstract void b(String str);

    protected abstract String j();

    protected abstract void l();

    protected abstract void m();

    protected abstract void n();

    public as(Activity activity, long j, int i, String str, boolean z) {
        this.e = activity;
        this.i = new WeakReferenceHandler(this);
        this.j = ((ReaderBaseActivity) activity).getHandler();
        if (i < 0) {
            this.m = -1;
        } else {
            this.m = i;
        }
        this.l = j;
        if (str != null) {
            this.k = str;
        }
        this.n = z;
        if (this.f == null) {
            a(this.e, null, h(), 1, true);
        }
        i();
    }

    protected void g() {
        String b = b.b(j(), String.valueOf(this.l));
        if (!TextUtils.isEmpty(b)) {
            a(b);
        }
    }

    protected int h() {
        return R.layout.vote_detail_layout;
    }

    protected void i() {
        this.o = (TextView) this.f.findViewById(R.id.title);
        this.p = (TextView) this.f.findViewById(R.id.title_extra_info);
        this.q = (TextView) this.f.findViewById(R.id.button_one);
        this.s = (TextView) this.f.findViewById(R.id.button_two);
        this.u = (TextView) this.f.findViewById(R.id.button_three);
        this.w = (TextView) this.f.findViewById(R.id.button_all);
        this.z = (TextView) this.f.findViewById(R.id.tv_ticket_count);
        this.y = (TextView) this.f.findViewById(R.id.tv_ticket_title);
        this.B = (TextView) this.f.findViewById(R.id.tv_ticket_rank);
        this.A = (TextView) this.f.findViewById(R.id.tv_ticket_rank_title);
        this.C = (TextView) this.f.findViewById(R.id.tv_rank_desc);
        this.x = (ImageView) this.f.findViewById(R.id.img_book_cover);
        this.D = (LinearLayout) this.f.findViewById(R.id.ll_vote_options);
        this.r = this.f.findViewById(R.id.divider_1);
        this.t = this.f.findViewById(R.id.divider_2);
        this.v = this.f.findViewById(R.id.divider_3);
        this.q.setOnClickListener(this);
        this.s.setOnClickListener(this);
        this.u.setOnClickListener(this);
        this.w.setOnClickListener(this);
        l();
        if (c.b()) {
            a(0, "VOTE_TYPE_REWARD");
            this.a = c.c();
            if (this.a != null) {
                m();
            }
            a(false);
        } else {
            a(4, "VOTE_TYPE_REWARD");
        }
        com.qq.reader.common.imageloader.c.a(e()).a(ao.g(this.l), this.x, com.qq.reader.common.imageloader.a.a().j());
    }

    protected void k() {
        g.a().a(new VoteInfoGetTask(new com.qq.reader.common.readertask.ordinal.c(this) {
            final /* synthetic */ as a;

            {
                this.a = r1;
            }

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, final String str, long j) {
                try {
                    if (new JSONObject(str).optInt("code") == 0) {
                        b.a(str, this.a.j(), String.valueOf(this.a.l));
                        this.a.i.post(new Runnable(this) {
                            final /* synthetic */ AnonymousClass1 b;

                            public void run() {
                                this.b.a.a(str);
                            }
                        });
                    }
                } catch (Exception e) {
                    com.qq.reader.common.monitor.debug.c.e(getClass().getSimpleName(), e.getMessage());
                }
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                com.qq.reader.common.monitor.debug.c.e(getClass().getSimpleName(), exception.getMessage());
            }
        }, String.valueOf(this.l), j()));
    }

    protected CharSequence a(String str, int i, int i2) {
        CharSequence spannableString = new SpannableString(str);
        if (str != null && str.length() > 0) {
            spannableString.setSpan(new ForegroundColorSpan(i2), 0, str.length(), 33);
            spannableString.setSpan(new AbsoluteSizeSpan(i, true), 0, str.length(), 33);
        }
        return spannableString;
    }

    public void f_() {
        if (!this.e.isFinishing()) {
            super.f_();
        }
    }

    public void a(final boolean z) {
        g.a().a(new VoteTicketQueryTask(new com.qq.reader.common.readertask.ordinal.c(this) {
            final /* synthetic */ as b;

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    if (jSONObject != null) {
                        this.b.a = c.c();
                        a.a(this.b.a, jSONObject);
                        if (this.b.a == null) {
                            return;
                        }
                        if (z) {
                            this.b.b(this.b.d);
                        } else {
                            this.b.i.post(new Runnable(this) {
                                final /* synthetic */ AnonymousClass2 a;

                                {
                                    this.a = r1;
                                }

                                public void run() {
                                    this.a.b.m();
                                }
                            });
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
            }
        }));
    }

    protected void a(int i, String str) {
        if (i == 4) {
            a(this.w, true);
            a(this.u, true);
            a(this.s, true);
            a(this.q, true);
        } else if (i == 3) {
            a(this.w, false);
            a(this.u, true);
            a(this.s, true);
            a(this.q, true);
        } else if (i == 2) {
            a(this.w, false);
            a(this.u, false);
            a(this.s, true);
            a(this.q, true);
        } else if (i == 1) {
            a(this.w, false);
            a(this.u, false);
            a(this.s, false);
            a(this.q, true);
        } else if (i == 0) {
            a(this.w, false);
            a(this.u, false);
            a(this.s, false);
            a(this.q, false);
        }
        if (str != "VOTE_TYPE_REWARD" && i > 0) {
            a(this.w, true);
        }
    }

    private void a(TextView textView, boolean z) {
        if (z) {
            textView.setOnClickListener(this);
            textView.setBackgroundResource(R.drawable.selector_round_blue_button);
            return;
        }
        textView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ as a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (!(this.a instanceof aw)) {
                    this.a.cancel();
                }
                this.a.n();
            }
        });
        textView.setBackgroundResource(R.drawable.shap_disable_round_white_button);
    }

    public void onClick(View view) {
        boolean b = c.b();
        dismiss();
        this.d = view.getId();
        if (b) {
            b(this.d);
        } else {
            p();
        }
    }

    private void p() {
        ((ReaderBaseActivity) this.e).mLoginNextTask = new com.qq.reader.common.login.a(this) {
            final /* synthetic */ as a;

            {
                this.a = r1;
            }

            public void a(int i) {
                switch (i) {
                    case 1:
                        this.a.a(true);
                        Activity b = this.a.b();
                        if (b instanceof ReaderPageActivity) {
                            ((ReaderPageActivity) b).F();
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        };
        ((ReaderBaseActivity) this.e).startLogin();
    }

    protected void c(int i) {
        g.a().a(new VoteTask(i, this.l, this.m, this.c, new com.qq.reader.common.readertask.ordinal.c(this) {
            final /* synthetic */ as a;

            {
                this.a = r1;
            }

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                this.a.b(str);
                if (this.a.n) {
                    JSONObject jSONObject;
                    try {
                        jSONObject = new JSONObject(str);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        jSONObject = null;
                    }
                    if (jSONObject.optInt("code") == 0) {
                        this.a.j.sendEmptyMessage(1232);
                    }
                }
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                this.a.c("网络异常，请稍后重试");
            }
        }));
    }

    protected void c(final String str) {
        this.i.post(new Runnable(this) {
            final /* synthetic */ as b;

            public void run() {
                af.a(this.b.e, str, 0).a();
            }
        });
    }

    protected boolean d(String str) {
        SharedPreferences sharedPreferences = this.e.getSharedPreferences("vote_tip", 0);
        boolean z = sharedPreferences.getBoolean(str, false);
        if (!z) {
            Editor edit = sharedPreferences.edit();
            edit.putBoolean(str, true);
            edit.commit();
        }
        return z;
    }

    public boolean handleMessage(Message message) {
        return false;
    }

    public void d() {
        super.d();
    }

    public WeakReferenceHandler o() {
        return this.i;
    }
}
