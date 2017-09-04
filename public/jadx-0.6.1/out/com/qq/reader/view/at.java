package com.qq.reader.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.activity.ReaderBaseActivity;
import com.qq.reader.activity.ReaderPageActivity;
import com.qq.reader.activity.WebBrowserForContents;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.login.b.a;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.protocol.VoteTask;
import com.qq.reader.common.readertask.protocol.VoteTicketQueryTask;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.tencent.feedback.proguard.R;
import com.tencent.util.WeakReferenceHandler;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: VoteChooseDialog */
public class at extends BaseDialog implements Callback, OnClickListener {
    public static String b = "";
    a a;
    as c;
    private Activity d;
    private Handler e;
    private WeakReferenceHandler i;
    private long j;
    private int k;
    private String l;
    private boolean m;
    private boolean n;
    private boolean o;
    private boolean p;
    private View q;
    private View r;
    private View s;
    private View t;
    private TextView u;
    private ImageView v;

    public at(Activity activity, long j) {
        this.d = null;
        this.j = 0;
        this.m = false;
        this.n = false;
        this.o = false;
        this.p = false;
        this.d = activity;
        this.e = ((ReaderBaseActivity) activity).getHandler();
        this.i = new WeakReferenceHandler(this);
        this.j = j;
        if (this.f == null) {
            a(this.d, null, R.layout.vote_choose_layout, 1, true);
        }
        i();
    }

    public at(Activity activity, long j, String str) {
        this(activity, j);
        this.l = str;
    }

    private void h() {
        this.v = (ImageView) this.f.findViewById(R.id.img_author_avatar);
        c.a(e()).a(this.l, this.v, com.qq.reader.common.imageloader.a.a().c());
    }

    private void i() {
        this.q = this.f.findViewById(R.id.reward);
        this.q.setOnClickListener(this);
        this.r = this.f.findViewById(R.id.recommend);
        this.r.setOnClickListener(this);
        this.s = this.f.findViewById(R.id.month);
        this.s.setOnClickListener(this);
        this.t = this.f.findViewById(R.id.fans);
        this.t.setOnClickListener(this);
        this.u = (TextView) this.f.findViewById(R.id.vote_one_ticket_button);
        this.u.setOnClickListener(this);
        this.a = com.qq.reader.common.login.c.c();
    }

    public void f_() {
        if (!this.d.isFinishing()) {
            super.f_();
            Map hashMap = new HashMap();
            hashMap.put(s.ORIGIN, b);
            i.a("event_D25", hashMap, ReaderApplication.getApplicationImp());
            h();
            if (com.qq.reader.common.login.c.b()) {
                if (this.a == null) {
                    this.a = com.qq.reader.common.login.c.c();
                }
                if (this.a == null) {
                    a(false);
                } else if (this.a.d(this.d.getApplicationContext()) > 0) {
                    a(true);
                } else {
                    a(false);
                }
                g();
                return;
            }
            a(true);
        }
    }

    public void g() {
        g.a().a(new VoteTicketQueryTask(new com.qq.reader.common.readertask.ordinal.c(this) {
            final /* synthetic */ at a;

            {
                this.a = r1;
            }

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    if (jSONObject != null) {
                        this.a.a = com.qq.reader.common.login.c.c();
                        a.a(this.a.a, jSONObject);
                        if (this.a.a == null) {
                            this.a.i.post(new Runnable(this) {
                                final /* synthetic */ AnonymousClass1 a;

                                {
                                    this.a = r1;
                                }

                                public void run() {
                                    this.a.a.a(false);
                                }
                            });
                        } else if (this.a.a.d(this.a.d.getApplicationContext()) > 0) {
                            this.a.i.post(new Runnable(this) {
                                final /* synthetic */ AnonymousClass1 a;

                                {
                                    this.a = r1;
                                }

                                public void run() {
                                    this.a.a.a(true);
                                }
                            });
                        } else {
                            this.a.i.post(new Runnable(this) {
                                final /* synthetic */ AnonymousClass1 a;

                                {
                                    this.a = r1;
                                }

                                public void run() {
                                    this.a.a.a(false);
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

    private void a(boolean z) {
        if (z) {
            this.u.setEnabled(true);
            this.u.setOnClickListener(this);
            return;
        }
        this.u.setEnabled(false);
        this.u.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ at a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.a(this.a.d.getString(R.string.vote_toast_no_ticket));
                this.a.cancel();
            }
        });
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.recommend:
                a(2, this.n, this.d.getString(R.string.not_support_recommend));
                break;
            case R.id.vote_one_ticket_button:
                boolean b = com.qq.reader.common.login.c.b();
                i.a("event_D106", null, ReaderApplication.getApplicationImp());
                if (!b) {
                    j();
                    break;
                } else {
                    k();
                    break;
                }
            case R.id.reward:
                a(1, this.m, this.d.getString(R.string.not_support_reward));
                break;
            case R.id.month:
                a(3, this.o, this.d.getString(R.string.not_support_month));
                break;
            case R.id.fans:
                com.qq.reader.common.c.c.a anonymousClass3 = new com.qq.reader.common.c.c.a(this) {
                    final /* synthetic */ at a;

                    {
                        this.a = r1;
                    }

                    public void a() {
                        i.a("event_D105", null, ReaderApplication.getApplicationImp());
                        Intent intent = new Intent();
                        intent.setClass(this.a.d, WebBrowserForContents.class);
                        intent.putExtra("com.qq.reader.WebContent", e.e(this.a.d, this.a.j));
                        com.qq.reader.common.utils.c.a(R.anim.slide_in_right, R.anim.slide_out_left);
                        this.a.d.startActivity(intent);
                    }
                };
                if (this.d instanceof ReaderPageActivity) {
                    if (!((ReaderPageActivity) this.d).h()) {
                        anonymousClass3.a();
                        break;
                    } else {
                        ((ReaderPageActivity) this.d).a(anonymousClass3);
                        break;
                    }
                }
                anonymousClass3.a();
                break;
        }
        cancel();
    }

    private void a(final int i, final boolean z, final String str) {
        if (this.p) {
            this.i.post(new Runnable(this) {
                final /* synthetic */ at d;

                public void run() {
                    if (z) {
                        if (this.d.c != null) {
                            if (this.d.c.f()) {
                                this.d.c.cancel();
                            }
                            this.d.c = null;
                        }
                        this.d.c = o.a(i, this.d.d, this.d.j, this.d.k, this.d.l, false);
                        return;
                    }
                    this.d.a(str);
                }
            });
            return;
        }
        Message obtain = Message.obtain();
        obtain.what = 1231;
        obtain.arg1 = i;
        obtain.arg2 = 1;
        this.e.sendMessage(obtain);
    }

    private void j() {
        ((ReaderBaseActivity) this.d).mLoginNextTask = new com.qq.reader.common.login.a(this) {
            final /* synthetic */ at a;

            {
                this.a = r1;
            }

            public void a(int i) {
                switch (i) {
                    case 1:
                        this.a.k();
                        return;
                    default:
                        return;
                }
            }
        };
        ((ReaderBaseActivity) this.d).startLogin();
    }

    private void k() {
        g.a().a(new VoteTask(1, this.j, -1, 1, new com.qq.reader.common.readertask.ordinal.c(this) {
            final /* synthetic */ at a;

            {
                this.a = r1;
            }

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                try {
                    int optInt = new JSONObject(str).optInt("code");
                    if (optInt == 0) {
                        this.a.a(this.a.d.getString(R.string.vote_toast_success));
                        this.a.a.b(this.a.d.getApplicationContext(), this.a.a.d(this.a.d.getApplicationContext()) - 1);
                    } else if (optInt == 101) {
                        this.a.a(this.a.d.getString(R.string.vote_toast_no_ticket));
                    } else if (optInt == 102) {
                        this.a.a(this.a.d.getString(R.string.vote_toast_username_error));
                    } else if (optInt == 104) {
                        this.a.a(this.a.d.getString(R.string.vote_toast_forbidden));
                    } else {
                        this.a.a(this.a.d.getString(R.string.vote_toast_error));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                this.a.i.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass6 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        try {
                            this.a.a.cancel();
                        } catch (Exception e) {
                            com.qq.reader.common.monitor.debug.c.e("VoteChooseDialog", e.getMessage());
                        }
                    }
                });
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                this.a.a("网络异常，请稍后重试");
                this.a.i.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass6 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        try {
                            this.a.a.cancel();
                        } catch (Exception e) {
                            com.qq.reader.common.monitor.debug.c.e("VoteChooseDialog", e.getMessage());
                        }
                    }
                });
            }
        }));
    }

    public void d() {
        super.d();
    }

    private void a(final String str) {
        this.i.post(new Runnable(this) {
            final /* synthetic */ at b;

            public void run() {
                af.a(this.b.d.getApplicationContext(), str, 0).a();
            }
        });
    }

    public boolean handleMessage(Message message) {
        return false;
    }

    public void a(int i, String str, boolean z, boolean z2, boolean z3) {
        this.p = true;
        this.k = i;
        this.l = str;
        this.m = z;
        this.n = z2;
        this.o = z3;
    }
}
