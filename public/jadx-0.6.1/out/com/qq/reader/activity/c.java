package com.qq.reader.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.charge.PayBridgeActivity;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.monitor.j;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.protocol.ProfileNetTask;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.aq;
import com.qq.reader.common.utils.ar;
import com.qq.reader.common.utils.o;
import com.qq.reader.common.web.js.JSPay;
import com.qq.reader.cservice.protocol.UserProtocolRedPointManger;
import com.qq.reader.module.b.b;
import com.qq.reader.module.bookstore.qnative.activity.NativeBookStoreTwoLevelActivity;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.feed.mypreference.MyReadingGeneActivity;
import com.qq.reader.module.qmessage.MessageActivity;
import com.tencent.connect.common.Constants;
import com.tencent.feedback.proguard.R;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ProfileViewHolder */
public class c {
    Dialog a = null;
    private View b;
    private TextView c = null;
    private Map<String, WeakReference<Bitmap>> d = null;
    private View e;
    private Handler f;
    private Context g;
    private ReaderBaseActivity h;
    private int i = 0;
    private a j = new a(this);

    @ar(a = 2130969204)
    /* compiled from: ProfileViewHolder */
    class a {
        @ar(a = 2131495480)
        ImageView A;
        @ar(a = 2131495481)
        TextView B;
        @ar(a = 2131495366)
        View C;
        @ar(a = 2131495427)
        View D;
        @ar(a = 2131495432)
        View E;
        @ar(a = 2131495444)
        View F;
        @ar(a = 2131495373)
        View G;
        @ar(a = 2131495486)
        View H;
        @ar(a = 2131495411)
        View I;
        @ar(a = 2131495413)
        View J;
        @ar(a = 2131495437)
        View K;
        @ar(a = 2131495441)
        View L;
        @ar(a = 2131495368)
        TextView M;
        @ar(a = 2131495390)
        View N;
        @ar(a = 2131495391)
        View O;
        @ar(a = 2131495392)
        TextView P;
        @ar(a = 2131495393)
        View Q;
        @ar(a = 2131495394)
        TextView R;
        @ar(a = 2131495395)
        View S;
        @ar(a = 2131495396)
        TextView T;
        final /* synthetic */ c U;
        @ar(a = 2131495375)
        ImageView a;
        @ar(a = 2131495365)
        ImageView b;
        @ar(a = 2131495388)
        View c;
        @ar(a = 2131495376)
        ImageView d;
        @ar(a = 2131495370)
        TextView e;
        @ar(a = 2131495371)
        TextView f;
        @ar(a = 2131495403)
        View g;
        @ar(a = 2131495369)
        View h;
        @ar(a = 2131495401)
        TextView i;
        @ar(a = 2131495404)
        TextView j;
        @ar(a = 2131492906)
        View k;
        @ar(a = 2131495374)
        View l;
        @ar(a = 2131495398)
        View m;
        @ar(a = 2131495389)
        View n;
        @ar(a = 2131495410)
        View o;
        @ar(a = 2131495385)
        TextView p;
        @ar(a = 2131495386)
        TextView q;
        @ar(a = 2131495420)
        TextView r;
        @ar(a = 2131495377)
        View s;
        @ar(a = 2131495379)
        TextView t;
        @ar(a = 2131495380)
        ImageView u;
        @ar(a = 2131495417)
        View v;
        @ar(a = 2131495406)
        View w;
        @ar(a = 2131495372)
        TextView x;
        @ar(a = 2131495483)
        View y;
        @ar(a = 2131495479)
        View z;

        a(c cVar) {
            this.U = cVar;
        }
    }

    public c(Handler handler, ReaderBaseActivity readerBaseActivity) {
        this.f = handler;
        this.h = readerBaseActivity;
        this.g = this.h;
    }

    public View a() {
        return this.b;
    }

    public void b() {
        this.b = aq.a(this.j, this.h.getLayoutInflater(), null);
        this.j.D.setOnClickListener(new com.qq.reader.module.bookstore.qnative.c.c(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void a(View view) {
                this.a.f.obtainMessage(3018).sendToTarget();
            }
        });
        this.j.E.setOnClickListener(new com.qq.reader.module.bookstore.qnative.c.c(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void a(View view) {
                this.a.f.obtainMessage(3019).sendToTarget();
            }
        });
        this.j.I.setOnClickListener(new com.qq.reader.module.bookstore.qnative.c.c(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void a(View view) {
                this.a.f.obtainMessage(3022).sendToTarget();
            }
        });
        this.j.F.setOnClickListener(new com.qq.reader.module.bookstore.qnative.c.c(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void a(View view) {
                this.a.f.obtainMessage(3020).sendToTarget();
            }
        });
        this.j.K.setOnClickListener(new com.qq.reader.module.bookstore.qnative.c.c(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void a(View view) {
                this.a.f.obtainMessage(3023).sendToTarget();
            }
        });
        this.j.L.setOnClickListener(new com.qq.reader.module.bookstore.qnative.c.c(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void a(View view) {
                this.a.f.obtainMessage(3024).sendToTarget();
            }
        });
        this.j.w.setOnClickListener(new com.qq.reader.module.bookstore.qnative.c.c(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void a(View view) {
                this.a.f.obtainMessage(3013).sendToTarget();
            }
        });
        this.j.m.setOnClickListener(new com.qq.reader.module.bookstore.qnative.c.c(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void a(View view) {
                i.a("event_F330", null, ReaderApplication.getApplicationContext());
                this.a.f.obtainMessage(3010).sendToTarget();
                i.a("event_D71", null, ReaderApplication.getApplicationImp());
                StatisticsManager.a().a("event_D71", null);
                j.a(70, 3);
            }
        });
        this.j.d.setOnClickListener(new com.qq.reader.module.bookstore.qnative.c.c(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void a(View view) {
                this.a.f.obtainMessage(3010).sendToTarget();
            }
        });
        this.j.g.setOnClickListener(new com.qq.reader.module.bookstore.qnative.c.c(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void a(View view) {
                this.a.f.obtainMessage(3012).sendToTarget();
            }
        });
        this.j.c.setOnClickListener(new com.qq.reader.module.bookstore.qnative.c.c(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void a(View view) {
                this.a.f.obtainMessage(3011).sendToTarget();
            }
        });
        this.e = this.b.findViewById(R.id.profile_account);
        this.e.setOnClickListener(new com.qq.reader.module.bookstore.qnative.c.c(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void a(View view) {
                this.a.f.obtainMessage(3001).sendToTarget();
            }
        });
        this.c = (TextView) this.b.findViewById(R.id.profile_account_nickname);
        this.j.v.setOnClickListener(new com.qq.reader.module.bookstore.qnative.c.c(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void a(View view) {
                this.a.f.obtainMessage(3014).sendToTarget();
            }
        });
        this.j.O.setOnClickListener(new com.qq.reader.module.bookstore.qnative.c.c(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void a(View view) {
                this.a.f.obtainMessage(3001).sendToTarget();
            }
        });
        this.j.Q.setOnClickListener(new com.qq.reader.module.bookstore.qnative.c.c(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void a(View view) {
                this.a.f.obtainMessage(3001).sendToTarget();
            }
        });
        this.j.S.setOnClickListener(new com.qq.reader.module.bookstore.qnative.c.c(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void a(View view) {
                this.a.f.obtainMessage(3025).sendToTarget();
            }
        });
        if (com.qq.reader.common.login.c.b()) {
            JSONObject b = b.a().b();
            if (b != null) {
                try {
                    com.qq.reader.common.login.b.a.a(b);
                    C();
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            } else {
                D();
            }
        } else {
            D();
        }
        this.j.y.setOnClickListener(new com.qq.reader.module.bookstore.qnative.c.c(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void a(View view) {
                this.a.f.obtainMessage(3008).sendToTarget();
            }
        });
        this.j.z.setOnClickListener(new com.qq.reader.module.bookstore.qnative.c.c(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void a(View view) {
                this.a.f.obtainMessage(3016).sendToTarget();
            }
        });
    }

    public void c() {
        z();
        if (com.qq.reader.common.login.c.b()) {
            C();
        } else {
            D();
        }
        e();
        f();
        g();
    }

    public void d() {
        this.i++;
        e();
    }

    private void j() {
        if (this.d != null) {
            Set<String> keySet = this.d.keySet();
            if (keySet != null && keySet.size() > 0) {
                for (String str : keySet) {
                    WeakReference weakReference = (WeakReference) this.d.get(str);
                    if (weakReference != null) {
                        if (weakReference.get() != null) {
                            ((Bitmap) weakReference.get()).recycle();
                        }
                        weakReference.clear();
                        ao.c(new File(ao.p(str) + "avatar.p"));
                    }
                }
            }
        }
    }

    public boolean a(int i, int i2, Intent intent) {
        Message obtainMessage;
        if (i == 20001) {
            if (i2 == 0) {
                i();
                obtainMessage = this.f.obtainMessage();
                obtainMessage.what = 10000301;
                obtainMessage.obj = intent;
                this.f.sendMessage(obtainMessage);
                i.a("profile_charge_success", null, ReaderApplication.getApplicationImp());
                return true;
            } else if (i2 == 2) {
                i.a("profile_charge_cancel", null, ReaderApplication.getApplicationImp());
                return true;
            } else if (i2 == 5) {
                this.h.startLogin();
                i.a("profile_charge_user_expired", null, ReaderApplication.getApplicationImp());
                return true;
            } else {
                String a = PayBridgeActivity.a(intent);
                Map hashMap = new HashMap();
                hashMap.put("errorMsg", a);
                i.a("profile_charge_fail", hashMap, ReaderApplication.getApplicationImp());
                return true;
            }
        } else if (i != 20002) {
            return false;
        } else {
            if (i2 == 0) {
                this.f.sendEmptyMessageDelayed(3021, 2000);
                obtainMessage = this.f.obtainMessage();
                obtainMessage.what = 10000301;
                obtainMessage.obj = intent;
                this.f.sendMessage(obtainMessage);
                i.a("profile_pay_success", null, ReaderApplication.getApplicationImp());
                return true;
            } else if (i2 == 2) {
                i.a("profile_pay_cancel", null, ReaderApplication.getApplicationImp());
                return true;
            } else if (i2 == 5) {
                this.h.startLogin();
                i.a("profile_open_vip_user_expired", null, ReaderApplication.getApplicationImp());
                return true;
            } else if (i2 == 20003) {
                i();
                i.a("profile_open_vip_success", null, ReaderApplication.getApplicationImp());
                return true;
            } else {
                Map hashMap2 = new HashMap();
                hashMap2.put("resultCode", String.valueOf(i2));
                i.a("profile_open_vip_fail", hashMap2, ReaderApplication.getApplicationImp());
                return true;
            }
        }
    }

    private void k() {
        Intent intent = new Intent();
        intent.setClass(this.g, OnlineHistoryActivity.class);
        if (com.qq.reader.appconfig.b.l) {
            intent.putExtra("NATIVE_BG_COLOR_Resource", R.color.concept_divider_bg);
        }
        com.qq.reader.common.utils.c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        this.h.startActivity(intent);
    }

    private void l() {
        Intent intent = new Intent();
        intent.putExtra("KEY_JUMP_PAGENAME", "myfocus");
        intent.putExtra("LOCAL_STORE_IN_TITLE", "我的收藏");
        if (com.qq.reader.appconfig.b.l) {
            intent.putExtra("NATIVE_BG_COLOR_Resource", R.color.concept_divider_bg);
        }
        intent.setClass(this.g, NativeBookStoreTwoLevelActivity.class);
        com.qq.reader.common.utils.c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        this.h.startActivity(intent);
        i.a("event_D69", null, this.h);
    }

    private void m() {
        Intent intent = new Intent();
        intent.setClass(this.g, MessageActivity.class);
        com.qq.reader.common.utils.c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        this.h.startActivity(intent);
        i.a("event_C151", null, this.h);
    }

    private void n() {
        Intent intent = new Intent();
        intent.setClass(this.g, MyReadingGeneActivity.class);
        com.qq.reader.common.utils.c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        this.h.startActivity(intent);
        i.a("event_D68", null, this.h);
    }

    private void o() {
        try {
            com.qq.reader.qurl.c.a(this.h, "https://page.write.qq.com/intro?ADTAG=yd.az");
            i.a("event_D228", null, ReaderApplication.getApplicationImp());
        } catch (Exception e) {
            com.qq.reader.common.monitor.debug.c.e("Error", e.getMessage());
        }
    }

    private void p() {
        Intent intent = new Intent();
        intent.setClass(this.g, ProfileSettingActivity.class);
        intent.setFlags(SigType.WLOGIN_QRPUSH);
        com.qq.reader.common.utils.c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        this.h.startActivity(intent);
        i.a("event_D64", null, this.g);
    }

    private void q() {
        o.i(this.h, null);
        j.a(59, 3);
    }

    private void r() {
        new JSPay(this.h).openVip();
        i.a("event_D6", null, this.g);
        StatisticsManager.a().a("event_D6", null);
        j.a(5, 3);
    }

    private void s() {
        new JSPay(this.h).startCharge(this.h, 0);
        j.a(3, 3);
        i.a("event_D4", null, this.g);
        StatisticsManager.a().a("event_D4", null);
    }

    private void t() {
        Intent intent = new Intent();
        intent.setClass(this.g, WebBrowserForContents.class);
        intent.putExtra("com.qq.reader.WebContent", e.as + e.b(this.g));
        intent.setFlags(SigType.WLOGIN_QRPUSH);
        com.qq.reader.common.utils.c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        this.h.startActivityForResult(intent, 10004);
    }

    private void u() {
        o.h(this.h, null);
    }

    private void v() {
        Intent intent = new Intent();
        intent.setClass(this.g, ProfileAssetsActivity.class);
        intent.putExtra("com.qq.reader.WebContent", "/mybuypacks.html");
        intent.setFlags(SigType.WLOGIN_QRPUSH);
        com.qq.reader.common.utils.c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        this.h.startActivity(intent);
    }

    private void w() {
        d.f(ReaderApplication.getApplicationImp(), false);
        Intent intent = new Intent();
        intent.setClass(this.g, WebBrowserForContents.class);
        intent.putExtra("com.qq.reader.WebContent", "/mybuypacks.html");
        intent.setFlags(SigType.WLOGIN_QRPUSH);
        com.qq.reader.common.utils.c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        this.h.startActivity(intent);
        i.a("event_D62", null, this.g);
        StatisticsManager.a().a("event_D62", null);
        j.a(61, 3);
    }

    private void x() {
        Intent intent = new Intent();
        intent.setClass(this.h, ProfileAccountActivity.class);
        com.qq.reader.common.utils.c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        this.h.startActivity(intent);
    }

    private void y() {
        o.E(this.h, null);
    }

    private void z() {
        if (com.qq.reader.common.login.c.b()) {
            this.j.C.setVisibility(0);
            this.j.a.setVisibility(0);
            this.c.setText(com.qq.reader.common.login.c.c().a());
            this.c.setVisibility(0);
            this.j.x.setVisibility(8);
            this.j.G.setVisibility(8);
            this.j.M.setVisibility(0);
            this.j.N.setVisibility(0);
            B();
            Drawable drawable;
            int a;
            if (com.qq.reader.common.login.c.c().d() == 2) {
                drawable = ReaderApplication.getApplicationImp().getResources().getDrawable(R.drawable.wechat_icon);
                a = ao.a(6.0f);
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                this.c.setCompoundDrawablePadding(a);
                this.c.setCompoundDrawables(null, null, drawable, null);
            } else if (com.qq.reader.common.login.c.c().d() == 50) {
                drawable = ReaderApplication.getApplicationImp().getResources().getDrawable(R.drawable.qidian_icon);
                a = ao.a(4.0f);
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                this.c.setCompoundDrawablePadding(a);
                this.c.setCompoundDrawables(null, null, drawable, null);
            } else {
                this.c.setCompoundDrawables(null, null, null, null);
            }
        } else {
            this.j.C.setVisibility(8);
            this.c.setVisibility(8);
            this.j.G.setVisibility(0);
            this.j.x.setVisibility(0);
            this.j.M.setVisibility(8);
            this.j.N.setVisibility(8);
            this.j.b.setImageResource(R.drawable.profile_default_avatar);
            this.j.a.setVisibility(8);
        }
        F();
    }

    private void a(final int i, final Object obj) {
        this.h.mLoginNextTask = new com.qq.reader.common.login.a(this) {
            final /* synthetic */ c c;

            public void a(int i) {
                switch (i) {
                    case 1:
                        Message obtain = Message.obtain();
                        obtain.what = i;
                        obtain.obj = obj;
                        this.c.f.sendMessage(obtain);
                        if (com.qq.reader.module.rookie.presenter.a.a().c) {
                            com.qq.reader.module.rookie.presenter.a.a().a(this.c.h);
                            i.a("event_F205", null, this.c.g);
                            break;
                        }
                        break;
                }
                this.c.i();
            }
        };
        this.h.startLogin();
    }

    public boolean a(Message message) {
        int i = message.what;
        boolean b = com.qq.reader.common.login.c.b();
        switch (i) {
            case 3001:
                if (b) {
                    x();
                    i.a("event_D10", null, ReaderApplication.getApplicationImp());
                    j.a(9, 3);
                } else {
                    a(3001, null);
                }
                return true;
            case 3002:
                if (b) {
                    t();
                } else {
                    a(3002, null);
                }
                return true;
            case 3006:
                B();
                return false;
            case 3007:
                A();
                com.qq.reader.cservice.adv.a aVar = (com.qq.reader.cservice.adv.a) message.obj;
                String h = aVar.h();
                if (com.qq.reader.qurl.c.a(h)) {
                    try {
                        com.qq.reader.qurl.c.a(this.h, h);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    String str = "";
                    if (h == null) {
                        h = str;
                    }
                    String str2 = h + "&origin=" + aVar.f();
                    if (str2 != null && str2.length() > 0) {
                        Intent intent = new Intent();
                        intent.setClass(this.h, WebBrowserForContents.class);
                        intent.setFlags(SigType.WLOGIN_QRPUSH);
                        intent.putExtra("com.qq.reader.WebContent", str2);
                        this.h.startActivity(intent);
                    }
                }
                return true;
            case 3008:
                p();
                return true;
            case 3010:
                if (b) {
                    u();
                } else {
                    a(3010, null);
                }
                return true;
            case 3011:
                if (b) {
                    s();
                    i.a("profile_charge", null, ReaderApplication.getApplicationImp());
                } else {
                    a(3011, null);
                }
                return true;
            case 3012:
                if (b) {
                    r();
                    i.a("profile_open_vip", null, ReaderApplication.getApplicationImp());
                } else {
                    a(3012, null);
                }
                return true;
            case 3013:
                if (b) {
                    w();
                } else {
                    a(3013, null);
                }
                return true;
            case 3014:
                if (b) {
                    i.a("event_D60", null, ReaderApplication.getApplicationImp());
                    q();
                } else {
                    a(3014, null);
                }
                return true;
            case 3015:
                if (b) {
                    v();
                } else {
                    a(3015, null);
                }
                return true;
            case 3016:
                StatisticsManager.a().a("event_D70", null);
                i.a("event_D70", null, ReaderApplication.getApplicationImp());
                E();
                F();
                return true;
            case 3017:
                C();
                return true;
            case 3018:
                i.a("event_D29", null, ReaderApplication.getApplicationImp());
                k();
                return true;
            case 3019:
                if (b) {
                    l();
                } else {
                    a(3019, null);
                }
                return true;
            case 3020:
                o.r(this.h, null);
                return true;
            case 3021:
                i();
                return true;
            case 3022:
                if (b) {
                    m();
                } else {
                    a(3022, null);
                }
                return true;
            case 3023:
                if (b) {
                    n();
                } else {
                    a(3023, null);
                }
                return true;
            case 3024:
                if (b) {
                    o();
                } else {
                    a(3024, null);
                }
                return true;
            case 3025:
                y();
                return true;
            case 6000001:
                j();
                D();
                com.qq.reader.common.login.c.a();
                z();
                Toast.makeText(this.g, "登录态失效，请重新登录", 0).show();
                return true;
            default:
                return false;
        }
    }

    private void A() {
        com.qq.reader.cservice.adv.a a = com.qq.reader.cservice.adv.c.a(this.i);
        if (a != null) {
            a.a(0);
            com.qq.reader.cservice.adv.b.a(ReaderApplication.getApplicationImp()).a(a, false);
            com.qq.reader.cservice.adv.c.a((Object) a, false);
            a(a);
        }
    }

    private void a(com.qq.reader.cservice.adv.a aVar) {
        if (aVar == null) {
            return;
        }
        if (com.qq.reader.cservice.adv.c.a((Object) aVar)) {
            this.j.u.setVisibility(0);
        } else {
            this.j.u.setVisibility(8);
        }
    }

    private void b(final com.qq.reader.cservice.adv.a aVar) {
        if (aVar.f().equalsIgnoreCase("102425")) {
            a(aVar);
            this.j.t.setText(aVar.e());
            this.j.t.setVisibility(0);
            this.j.t.requestLayout();
            this.j.s.setVisibility(0);
            this.j.s.setOnClickListener(new com.qq.reader.module.bookstore.qnative.c.c(this) {
                final /* synthetic */ c b;

                public void a(View view) {
                    Message obtainMessage = this.b.f.obtainMessage(3007);
                    obtainMessage.obj = aVar;
                    obtainMessage.sendToTarget();
                    j.a(54, 3);
                    i.a("event_D55", null, this.b.g);
                    Map hashMap = new HashMap();
                    hashMap.put(s.ORIGIN, String.valueOf(aVar.d()));
                    i.a("event_D63", hashMap, this.b.g);
                }
            });
        }
    }

    private void B() {
        String str = "";
        if (com.qq.reader.common.login.c.b()) {
            try {
                str = com.qq.reader.common.login.c.c().b();
            } catch (Exception e) {
            }
            com.qq.reader.common.imageloader.c.a(this.h).a(str, this.j.b, com.qq.reader.common.imageloader.a.a().b());
            this.j.a.setVisibility(0);
            return;
        }
        this.j.b.setImageResource(R.drawable.profile_default_avatar);
        this.j.a.setVisibility(8);
    }

    public void e() {
        com.qq.reader.cservice.adv.a a = com.qq.reader.cservice.adv.c.a(this.i);
        if (a != null) {
            b(a);
        }
    }

    public void f() {
        this.j.H.setVisibility(8);
        if (com.qq.reader.cservice.adv.c.a((Object) "TYPE_SKIN_LIST_UPDATE") || d.t || ((com.qq.reader.common.protocol.c.a(this.g) && d.a) || UserProtocolRedPointManger.a(this.g.getApplicationContext()).d())) {
            this.j.H.setVisibility(0);
        }
    }

    public void g() {
        this.j.J.setVisibility(8);
        if (com.qq.reader.common.login.c.c() == null) {
            this.j.J.setVisibility(8);
        } else if (d.aE(this.g)) {
            this.j.J.setVisibility(0);
        } else {
            this.j.J.setVisibility(8);
        }
    }

    public void h() {
        if (com.qq.reader.common.login.c.b()) {
            try {
                JSONObject b = b.a().b();
                if (b != null) {
                    com.qq.reader.common.login.b.a.a(b);
                    C();
                } else {
                    D();
                }
            } catch (JSONException e) {
                D();
                e.printStackTrace();
            }
            i();
            return;
        }
        D();
    }

    public void i() {
        g.a().a(new ProfileNetTask(new com.qq.reader.common.readertask.ordinal.c(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                Object obj = null;
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    if (!jSONObject.optBoolean("isLogin")) {
                        obj = 1;
                    }
                    int optInt = jSONObject.optInt("code", 0);
                    if (obj == null && optInt != -100) {
                        com.qq.reader.common.login.b.a.a(jSONObject);
                        this.a.f.sendEmptyMessage(3017);
                    } else if (com.qq.reader.common.login.c.b()) {
                        this.a.f.sendEmptyMessage(6000001);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                d.c(this.a.g, System.currentTimeMillis());
            }
        }));
    }

    private void C() {
        com.qq.reader.common.login.b.a c = com.qq.reader.common.login.c.c();
        if (com.qq.reader.common.login.c.b()) {
            Map hashMap;
            this.j.k.setOnClickListener(new com.qq.reader.module.bookstore.qnative.c.c(this) {
                final /* synthetic */ c a;

                {
                    this.a = r1;
                }

                public void a(View view) {
                    Map hashMap = new HashMap();
                    if (com.qq.reader.common.login.c.c().l(this.a.h) != 0) {
                        hashMap.put(s.ORIGIN, Constants.VIA_REPORT_TYPE_SHARE_TO_QQ);
                        i.a("event_D139", hashMap, ReaderApplication.getApplicationImp());
                        o.c(this.a.h, String.valueOf(com.qq.reader.common.login.c.c().l(ReaderApplication.getApplicationImp())), "", "", null);
                    } else {
                        hashMap.put(s.ORIGIN, "1");
                        i.a("event_C286", hashMap, ReaderApplication.getApplicationImp());
                        o.d(this.a.h, d.R(this.a.h), com.qq.reader.common.login.c.c().a(), com.qq.reader.common.login.c.c().b(), null);
                    }
                    i.a("event_D122", null, ReaderApplication.getApplicationImp());
                }
            });
            this.j.r.setVisibility(0);
            this.j.c.setVisibility(0);
            this.j.l.setVisibility(0);
            this.j.o.setVisibility(0);
            this.e.setVisibility(0);
            this.j.w.setVisibility(0);
            this.j.h.setVisibility(0);
            this.j.e.setVisibility(0);
            if (ao.s(c.g())) {
                this.j.p.setText(c.g(this.g) + "书币 + " + c.i(this.g) + "书劵");
                this.j.p.setVisibility(0);
                this.j.q.setVisibility(8);
            } else {
                this.j.q.setText(c.g());
                this.j.q.setVisibility(0);
                this.j.p.setVisibility(8);
                hashMap = new HashMap();
                hashMap.put(s.ORIGIN, "1");
                i.a("event_A202", hashMap, ReaderApplication.getApplicationImp());
            }
            this.j.v.setVisibility(8);
            this.j.r.setText(c.h());
            this.j.f.setText("LV" + c.f());
            this.j.e.setText("VIP" + c.e());
            this.j.d.setVisibility(0);
            ao.a(c.k(ReaderApplication.getApplicationImp().getApplicationContext()), this.j.d, true);
            if (c.c(ReaderApplication.getApplicationImp().getApplicationContext()) == 2) {
                this.j.j.setVisibility(0);
                this.j.j.setText("续 费");
            } else if (c.c(ReaderApplication.getApplicationImp().getApplicationContext()) == 0) {
                this.j.j.setVisibility(0);
                this.j.j.setText("开 通");
            } else if (c.c(ReaderApplication.getApplicationImp().getApplicationContext()) == 1) {
                this.j.j.setVisibility(8);
            }
            if (this.j.i.getVisibility() != 0) {
                this.j.i.setVisibility(0);
            }
            com.qq.reader.cservice.adv.a a = com.qq.reader.cservice.adv.c.a();
            if (a != null) {
                hashMap = new HashMap();
                hashMap.put(s.ORIGIN, String.valueOf(a.d()));
                i.a("event_F186", hashMap, ReaderApplication.getApplicationImp());
                this.j.i.setText(a.e());
                this.j.i.setTextColor(this.h.getResources().getColorStateList(R.color.skin_set_profile_account_activity_info_textcolor));
            } else {
                CharSequence n = com.qq.reader.common.login.b.a.n(ReaderApplication.getApplicationImp());
                if (TextUtils.isEmpty(n)) {
                    this.j.i.setText("");
                } else {
                    this.j.i.setText(n);
                    this.j.i.setTextColor(this.h.getResources().getColorStateList(R.color.skin_set_profile_account_balance_textcolor));
                }
            }
            this.j.m.setVisibility(0);
            this.j.n.setVisibility(0);
            c = com.qq.reader.common.login.c.c();
            long m = c.m(this.h);
            if (m > 0) {
                this.j.M.setText("累计阅读" + com.qq.reader.common.utils.j.c(m));
            } else {
                this.j.M.setText("暂无阅读时长，去畅读好书");
            }
            this.j.P.setText(String.valueOf(c.e(this.h)));
            this.j.R.setText(String.valueOf(c.d(this.h)));
            this.j.T.setText(String.valueOf(c.f(this.h)));
        }
    }

    private void D() {
        this.j.k.setOnClickListener(new com.qq.reader.module.bookstore.qnative.c.c(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void a(View view) {
                j.a(8, 3);
                this.a.a(3017, null);
            }
        });
        this.j.r.setVisibility(8);
        this.j.j.setVisibility(8);
        this.j.c.setVisibility(8);
        this.j.i.setText("");
        this.j.i.setVisibility(8);
        this.j.l.setVisibility(8);
        this.j.h.setVisibility(8);
        this.j.e.setVisibility(8);
        this.j.d.setVisibility(8);
        this.j.p.setVisibility(8);
        this.j.q.setVisibility(8);
        this.j.P.setText(String.valueOf(0));
        this.j.R.setText(String.valueOf(0));
        this.j.T.setText(String.valueOf(0));
    }

    private void E() {
        if (d.n) {
            d.n = false;
            d.i(this.g, d.n);
            ((MainActivity) this.h).showNightMode(false);
            return;
        }
        d.n = true;
        d.i(this.g, d.n);
        ((MainActivity) this.h).showNightMode(true);
    }

    private void F() {
        this.j.B.setText(d.n ? "白天" : "夜间");
        this.j.A.setBackgroundResource(d.n ? R.drawable.profile_day_icon : R.drawable.profile_night_icon);
    }
}
