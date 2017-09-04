package com.qq.reader.liveshow.views;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.dynamicload.Lib.DLConstants;
import com.pay.http.APPluginErrorCode;
import com.qq.reader.liveshow.a.h;
import com.qq.reader.liveshow.b.l;
import com.qq.reader.liveshow.b.m;
import com.qq.reader.liveshow.b.n;
import com.qq.reader.liveshow.c.b.c;
import com.qq.reader.liveshow.c.b.d;
import com.qq.reader.liveshow.c.b.e;
import com.qq.reader.liveshow.c.b.f;
import com.qq.reader.liveshow.c.b.g;
import com.qq.reader.liveshow.c.j;
import com.qq.reader.liveshow.c.k;
import com.qq.reader.liveshow.model.RoomDetail;
import com.qq.reader.liveshow.utils.LogConstants;
import com.qq.reader.liveshow.utils.LogConstants.STATUS;
import com.qq.reader.liveshow.utils.SxbLog;
import com.qq.reader.liveshow.views.customviews.BaseActivity;
import com.qq.reader.liveshow.views.customviews.WaitingDialog;
import com.qq.reader.liveshow.views.roomdialog.LiveEnterRoomDialog;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.tencent.av.TIMAvManager.StreamRes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class LiveActivity extends BaseActivity implements com.qq.reader.liveshow.avcontrollers.c.a, com.qq.reader.liveshow.c.b.a, com.qq.reader.liveshow.c.b.b, c, e, f, g {
    private static int W = 0;
    private static final String c = LiveActivity.class.getSimpleName();
    private View A;
    private com.qq.reader.liveshow.c.a B;
    private boolean C = false;
    private boolean D = false;
    private boolean E = false;
    private j F;
    private List<d> G = new ArrayList();
    private a H;
    private b I;
    private com.qq.reader.liveshow.c.a.c.a J;
    private com.qq.reader.liveshow.c.a.d.a K;
    private a L = new a(this);
    private com.qq.reader.liveshow.c.g.b M;
    private boolean N = false;
    private LiveEnterRoomDialog O;
    private boolean P;
    private boolean Q;
    private Handler R = new Handler(new Callback(this) {
        final /* synthetic */ LiveActivity a;

        {
            this.a = r1;
        }

        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    this.a.p();
                    break;
                case 4:
                    if (!this.a.f.j()) {
                        this.a.I.c(com.qq.reader.liveshow.a.d.icon_mic_open);
                        this.a.f.k();
                        break;
                    }
                    this.a.I.c(com.qq.reader.liveshow.a.d.icon_mic_close);
                    this.a.f.l();
                    break;
                case 6:
                    this.a.f.i();
                    break;
                case 7:
                    if (!this.a.f.h()) {
                        this.a.f.m();
                        break;
                    }
                    m.a(this.a, "前置摄像头不能开启闪光灯…", 0);
                    break;
                case 8:
                    if (!(com.qq.reader.liveshow.model.c.a().h() != 1 || this.a.Q || com.qq.reader.liveshow.avcontrollers.c.a().h() == null)) {
                        SxbLog.e("CheckCamera", "checkMySf mLiveHelper.resume()");
                        this.a.f.g();
                        break;
                    }
                case 11:
                    int i = message.arg1;
                    i = message.arg2;
                    "" + message.obj;
                    break;
            }
            return false;
        }
    });
    private BroadcastReceiver S = new BroadcastReceiver(this) {
        final /* synthetic */ LiveActivity a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action != null) {
                int intExtra;
                ArrayList stringArrayListExtra;
                Iterator it;
                String str;
                if ("com.qq.reader.liveshow.ACTION_START_CONTEXT_COMPLETE".equals(action)) {
                    if (intent.getIntExtra("av_error_result", -1) == 0) {
                        SxbLog.e("START", "ACTION_START_CONTEXT_COMPLETE  error = " + this.a.p + "  bInAvRoom = " + this.a.o);
                        if (!(!com.qq.reader.liveshow.avcontrollers.c.a().g() || this.a.p || this.a.o)) {
                            this.a.B.a(this.a);
                        }
                    } else {
                        intExtra = intent.getIntExtra("av_error_result_code", 0);
                        m.a(context, context.getString(h.init_av_context_dead_error) + "：" + (intExtra == 0 ? "" : intExtra + ""), 0);
                        this.a.E();
                        this.a.b(false);
                    }
                }
                if ("com.reader.live.im_logout".equals(action)) {
                    boolean z;
                    String str2 = "IN";
                    StringBuilder append = new StringBuilder().append("LOCAL_IM_EXIT_SUCCESS_ACTION   !isFinishing()=");
                    if (this.a.isFinishing()) {
                        z = false;
                    } else {
                        z = true;
                    }
                    SxbLog.e(str2, append.append(z).append("    LiveShowQuitManager.getInstance().isInRoom()").append(com.qq.reader.liveshow.utils.a.a.a().j()).toString());
                    if (!this.a.isFinishing() && com.qq.reader.liveshow.utils.a.a.a().j()) {
                        this.a.ad.a(2, null);
                    }
                }
                if (n.a().e().d().equals(action)) {
                    if (com.qq.reader.liveshow.model.c.a().k() && !com.qq.reader.liveshow.utils.a.a.a().g() && this.a.o && !this.a.p) {
                        com.qq.reader.liveshow.utils.a.a.a().b(true);
                        this.a.T.setMessage(this.a.getString(h.msg_loading));
                        this.a.a(this.a.T.getDialog());
                        this.a.E = true;
                        this.a.a(true, false);
                        this.a.a(true);
                    }
                } else if (!n.a().e().e().equals(action) && n.a().e().f().equals(action)) {
                }
                if (!(!"com.reader.live.room_reload".equals(action) || this.a.w == null || com.qq.reader.liveshow.utils.a.a.a().g())) {
                    this.a.w.a(com.qq.reader.liveshow.model.a.i());
                }
                if (action.equals("com.qq.reader.liveshow.ACTION_SURFACE_CREATED")) {
                    if (com.qq.reader.liveshow.model.c.a().h() == 1) {
                        SxbLog.b(LiveActivity.c, "open camera .........");
                        this.a.f.b();
                    } else {
                        this.a.R.postDelayed(new Runnable(this) {
                            final /* synthetic */ AnonymousClass19 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                this.a.a.A();
                            }
                        }, 1000);
                    }
                }
                if (action.equals("com.qq.reader.liveshow.ACTION_CAMERA_OPEN_IN_LIVE")) {
                    this.a.r = false;
                    stringArrayListExtra = intent.getStringArrayListExtra("ids");
                    it = stringArrayListExtra.iterator();
                    while (it.hasNext()) {
                        str = (String) it.next();
                        if (!this.a.u.contains(str)) {
                            this.a.u.add(str);
                        }
                        this.a.A();
                        if (str.equals(com.qq.reader.liveshow.model.c.a().b())) {
                            this.a.a(true, str);
                            return;
                        }
                    }
                    SxbLog.c(LiveActivity.c, LogConstants.e + LogConstants.a + com.qq.reader.liveshow.model.c.a().b() + LogConstants.a + "somebody open camera,need req data" + LogConstants.a + STATUS.SUCCEED + LogConstants.a + "ids " + stringArrayListExtra.toString());
                    intExtra = com.qq.reader.liveshow.model.a.a();
                    this.a.f.a(stringArrayListExtra);
                    com.qq.reader.liveshow.model.a.a(intExtra + stringArrayListExtra.size());
                }
                if (action.equals("com.qq.reader.liveshow.ACTION_SCREEN_SHARE_IN_LIVE")) {
                    stringArrayListExtra = intent.getStringArrayListExtra("ids");
                    it = stringArrayListExtra.iterator();
                    while (it.hasNext()) {
                        str = (String) it.next();
                        if (!this.a.u.contains(str)) {
                            this.a.u.add(str);
                        }
                        this.a.A();
                        if (str.equals(com.qq.reader.liveshow.model.c.a().b())) {
                            this.a.a(true, str);
                            return;
                        }
                    }
                    SxbLog.c(LiveActivity.c, LogConstants.e + LogConstants.a + com.qq.reader.liveshow.model.c.a().b() + LogConstants.a + "somebody open camera,need req data" + LogConstants.a + STATUS.SUCCEED + LogConstants.a + "ids " + stringArrayListExtra.toString());
                    intExtra = com.qq.reader.liveshow.model.a.a();
                    this.a.f.b(stringArrayListExtra);
                    this.a.r = true;
                    com.qq.reader.liveshow.model.a.a(intExtra + stringArrayListExtra.size());
                }
                if (action.equals("com.qq.reader.liveshow.ACTION_CAMERA_CLOSE_IN_LIVE")) {
                    Iterator it2 = intent.getStringArrayListExtra("ids").iterator();
                    while (it2.hasNext()) {
                        this.a.u.remove((String) it2.next());
                    }
                    this.a.A();
                    this.a.w();
                }
                if (action.equals("com.qq.reader.liveshow.ACTION_SWITCH_VIDEO")) {
                    this.a.s = intent.getStringExtra("identifier");
                    SxbLog.a(LiveActivity.c, "switch video enter with id:" + this.a.s);
                    this.a.A();
                    this.a.I.b(this.a.s);
                }
                if (action.equals("com.qq.reader.liveshow.ACTION_HOST_LEAVE")) {
                    this.a.N();
                }
            }
        }
    };
    private WaitingDialog T;
    private Dialog U;
    private boolean V;
    private boolean X = false;
    private boolean Y = false;
    private com.qq.reader.liveshow.views.roomdialog.a.a.a Z;
    public boolean a = false;
    private com.qq.reader.liveshow.views.roomdialog.a.a.a aa;
    private com.qq.reader.liveshow.views.roomdialog.a.a.b ab;
    private com.qq.reader.liveshow.views.roomdialog.a.a.a ac;
    private final com.qq.reader.liveshow.views.roomdialog.LiveEnterRoomDialog.a ad = new com.qq.reader.liveshow.views.roomdialog.LiveEnterRoomDialog.a(this) {
        final /* synthetic */ LiveActivity a;

        {
            this.a = r1;
        }

        public void a(int i, Object obj) {
            this.a.d = i;
            this.a.a(obj);
        }

        public void a(String str) {
            if (this.a.O != null && !this.a.isFinishing()) {
                this.a.O.a(str);
            }
        }
    };
    private boolean ae;
    public boolean b = true;
    private int d = 0;
    private com.qq.reader.liveshow.c.b e;
    private com.qq.reader.liveshow.c.g f;
    private View g;
    private RelativeLayout h;
    private TextView i;
    private String j;
    private Timer k;
    private b l;
    private boolean m = false;
    private boolean n = true;
    private boolean o = false;
    private boolean p = false;
    private boolean q = false;
    private boolean r = false;
    private String s;
    private Dialog t;
    private ArrayList<String> u = new ArrayList();
    private k v;
    private com.qq.reader.liveshow.c.n w;
    private Dialog x;
    private Dialog y;
    private View z;

    class a implements OnClickListener {
        final /* synthetic */ LiveActivity a;

        a(LiveActivity liveActivity) {
            this.a = liveActivity;
        }

        public void onClick(View view) {
            if (view.getId() == com.qq.reader.liveshow.a.e.host_start_live_show_btn && com.qq.reader.liveshow.model.c.a().h() == 1) {
                l.a("event_Z27", null, this.a);
                this.a.L();
            }
        }
    }

    private class b extends TimerTask {
        final /* synthetic */ LiveActivity a;

        private b(LiveActivity liveActivity) {
            this.a = liveActivity;
        }

        public void run() {
            SxbLog.b(LiveActivity.c, "timeTask ");
            com.qq.reader.liveshow.model.a.a(com.qq.reader.liveshow.model.a.k() + 1);
            if (com.qq.reader.liveshow.model.c.a().h() == 1) {
                this.a.R.sendEmptyMessage(1);
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        getWindow().setFlags(128, 128);
        com.qq.reader.liveshow.utils.k.a(this);
        setContentView(com.qq.reader.liveshow.a.g.activity_live);
        v();
        u();
        com.qq.reader.liveshow.utils.a.a.a().c(true);
        this.e = new com.qq.reader.liveshow.c.b(this, this);
        this.f = new com.qq.reader.liveshow.c.g(this, this, this.F, com.qq.reader.liveshow.model.a.i());
        this.B = new com.qq.reader.liveshow.c.a(this, this);
        this.v = new k(this, this, this);
        this.w = new com.qq.reader.liveshow.c.n(this, this);
        y();
        this.O = new LiveEnterRoomDialog(this);
        this.O.setOnKeyListener(new OnKeyListener(this) {
            final /* synthetic */ LiveActivity a;

            {
                this.a = r1;
            }

            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                com.qq.reader.liveshow.views.roomdialog.a.a.a b = this.a.O.b();
                if (b == null || i != 4 || keyEvent.getAction() != 0) {
                    return false;
                }
                b.d();
                return true;
            }
        });
        t();
        if (!com.qq.reader.liveshow.utils.g.a(this)) {
            m.a((Context) this, h.live_net_error, 0);
            b(false);
        } else if (com.qq.reader.liveshow.utils.a.a.a().h()) {
            this.ad.a(8, null);
        } else {
            this.ad.a(2, null);
        }
    }

    private void t() {
        this.Z = new com.qq.reader.liveshow.views.roomdialog.a.a.c(this.ad, this, this.O.a());
        this.aa = new com.qq.reader.liveshow.views.roomdialog.a.a.d(this.ad, this, this.O.a());
        this.ab = new com.qq.reader.liveshow.views.roomdialog.a.a.b(this.ad, this, this.O.a());
    }

    private void u() {
        this.F = new j();
        this.H = new a(this, this.F);
        this.I = new b(this, this.F);
        this.J = new c(this, this.F);
        this.K = new d(this, this.F);
        this.G.add(this.H);
        this.G.add(this.I);
        this.G.add(this.J);
        this.G.add(this.K);
        this.I.a(new com.qq.reader.liveshow.views.b.b(this) {
            final /* synthetic */ LiveActivity a;

            {
                this.a = r1;
            }

            public void a(boolean z) {
                if (!z) {
                    this.a.A();
                } else if (this.a.h.getVisibility() == 0) {
                    this.a.h.setVisibility(8);
                }
            }
        });
    }

    private void v() {
        com.qq.reader.liveshow.model.c.a().a(false);
        this.g = findViewById(com.qq.reader.liveshow.a.e.av_video_layer_ui);
        this.h = (RelativeLayout) findViewById(com.qq.reader.liveshow.a.e.ll_host_leave);
        this.i = (TextView) findViewById(com.qq.reader.liveshow.a.e.room_tips);
        ((TextView) findViewById(com.qq.reader.liveshow.a.e.btn_back)).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ LiveActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.onBackPressed();
                Map hashMap = new HashMap();
                hashMap.put(s.ORIGIN, com.qq.reader.liveshow.model.c.a().h() == 1 ? "1" : "2");
                l.a("event_Z3", hashMap, this.a.getApplicationContext());
                hashMap = new HashMap();
                hashMap.put(s.ORIGIN, com.qq.reader.liveshow.utils.g.a(this.a) ? "1" : "2");
                if (com.qq.reader.liveshow.model.c.a().i()) {
                    l.a("event_Z31", hashMap, this.a.getApplicationContext());
                } else {
                    l.a("event_Z32", hashMap, this.a.getApplicationContext());
                }
            }
        });
        F();
        G();
    }

    private void w() {
        if (!com.qq.reader.liveshow.utils.a.a.a().g()) {
            SxbLog.e("CheckCamera", "checkMySf message");
            this.R.removeMessages(8);
            this.R.sendEmptyMessageDelayed(8, 2000);
        }
    }

    private void x() {
        Dialog dialog;
        if (com.qq.reader.liveshow.model.c.a().i()) {
            dialog = this.x;
        } else {
            dialog = this.y;
        }
        if (!dialog.isShowing()) {
            dialog.show();
        }
    }

    private void y() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.qq.reader.liveshow.ACTION_SURFACE_CREATED");
        intentFilter.addAction("com.qq.reader.liveshow.ACTION_HOST_ENTER");
        intentFilter.addAction("com.qq.reader.liveshow.ACTION_CAMERA_OPEN_IN_LIVE");
        intentFilter.addAction("com.qq.reader.liveshow.ACTION_CAMERA_CLOSE_IN_LIVE");
        intentFilter.addAction("com.qq.reader.liveshow.ACTION_SWITCH_VIDEO");
        intentFilter.addAction("com.qq.reader.liveshow.ACTION_HOST_LEAVE");
        intentFilter.addAction("com.qq.reader.liveshow.ACTION_SCREEN_SHARE_IN_LIVE");
        intentFilter.addAction(n.a().e().d());
        intentFilter.addAction("com.qq.reader.liveshow.ACTION_START_CONTEXT_COMPLETE");
        intentFilter.addAction("com.reader.live.im_logout");
        intentFilter.addAction("com.reader.live.room_reload");
        registerReceiver(this.S, intentFilter);
    }

    private void z() {
        unregisterReceiver(this.S);
    }

    private void A() {
        SxbLog.b(c, " update room state layout ");
        if (com.qq.reader.liveshow.utils.g.a(this)) {
            this.i.setText("");
            if (com.qq.reader.liveshow.model.c.a().h() == 0) {
                this.i.setText(getResources().getString(h.live_host_leave));
                if (!this.o || (com.qq.reader.liveshow.model.a.c().equals(this.s) && !this.u.contains(this.s))) {
                    this.h.setVisibility(0);
                    return;
                } else {
                    this.h.setVisibility(8);
                    return;
                }
            }
            this.h.setVisibility(8);
            return;
        }
        this.i.setText(getResources().getString(h.live_net_error));
        this.h.setVisibility(0);
    }

    private void B() {
        for (d i : this.G) {
            i.i();
        }
        if (com.qq.reader.liveshow.model.c.a().h() == 1) {
            this.A = findViewById(com.qq.reader.liveshow.a.e.host_start_live_show);
            this.z = findViewById(com.qq.reader.liveshow.a.e.host_start_live_show_btn);
            this.z.setOnClickListener(this.L);
            H();
        }
    }

    protected void onResume() {
        super.onResume();
        this.Q = false;
        if (com.qq.reader.liveshow.avcontrollers.c.a().h() != null) {
            if (this.o) {
                com.qq.reader.liveshow.avcontrollers.c.a().h().getAudioCtrl().enableSpeaker(true);
            }
            this.f.g();
            com.qq.reader.liveshow.avcontrollers.c.a().k();
        }
        for (d f : this.G) {
            f.f();
        }
    }

    protected void onPause() {
        super.onPause();
        this.Q = true;
        if (com.qq.reader.liveshow.avcontrollers.c.a().h() != null) {
            if (this.o) {
                com.qq.reader.liveshow.avcontrollers.c.a().h().getAudioCtrl().enableSpeaker(false);
            }
            this.f.f();
            com.qq.reader.liveshow.avcontrollers.c.a().l();
        }
        for (d g : this.G) {
            g.g();
        }
    }

    public void k() {
        if (!com.qq.reader.liveshow.utils.a.a.a().g()) {
            com.qq.reader.liveshow.utils.a.a.a().b(false);
            if (this.w != null) {
                this.w.a(com.qq.reader.liveshow.model.a.i());
            }
            SxbLog.e("START", "login success ");
            if (com.qq.reader.liveshow.model.c.a().h() == 1 && this.M == null) {
                this.M = new com.qq.reader.liveshow.c.g.b(this) {
                    final /* synthetic */ LiveActivity a;

                    {
                        this.a = r1;
                    }

                    public void a(com.qq.reader.liveshow.model.d.c cVar) {
                        if (this.a.K != null) {
                            this.a.K.a();
                        }
                        this.a.p();
                    }
                };
                if (this.f != null) {
                    this.f.a(this.M);
                }
            }
            B();
            this.B.a((Activity) this);
        }
    }

    public void l() {
        b(false);
    }

    public void c(int i) {
        SxbLog.b(c, "login room fail ");
        if (i != 0 && i != 3) {
            b(this.T.getDialog());
            switch (i) {
                case -201:
                    b(false);
                    return;
                case DLConstants.LOAD_ERR_IO_FAIL /*-103*/:
                    N();
                    return;
                case DLConstants.LOAD_ERR_SIGNATURES /*-101*/:
                    this.ad.a(3, null);
                    return;
                case -100:
                    b(false);
                    return;
                default:
                    b(false);
                    return;
            }
        }
    }

    public void m() {
        SxbLog.e("OUT", "activity logout success xx s");
        if (!com.qq.reader.liveshow.utils.a.a.a().g()) {
            if (this.E && !isFinishing()) {
                M();
            }
            if (this.D) {
                b(this.T.getDialog());
                finish();
            }
            this.D = false;
            this.E = false;
        }
    }

    public void n() {
        if (com.qq.reader.liveshow.utils.a.a.a().g() && this.d != 5) {
            b(this.T.getDialog());
            finish();
        }
    }

    public void e() {
        A();
    }

    public void a(int i) {
        if (!com.qq.reader.liveshow.model.c.a().g() && ((this.p || this.o) && !com.qq.reader.liveshow.utils.a.a.a().g())) {
            x();
        }
        if (this.o) {
            A();
        }
    }

    public void f() {
        SxbLog.e(c, "if is host will reenter room ");
        if (this.o) {
            A();
        }
        b(this.y);
    }

    public void a(String[] strArr) {
        SxbLog.e("Permission", Arrays.toString(strArr));
        b(this.t);
        this.t = n.a().e().a((Context) this, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ LiveActivity a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                n.a().e().a(this.a, 1002);
            }
        }, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ LiveActivity a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                this.a.b(false);
            }
        }, strArr);
        this.t.setCancelable(false);
        this.t.setCanceledOnTouchOutside(false);
        this.t.setOnKeyListener(new OnKeyListener(this) {
            final /* synthetic */ LiveActivity a;

            {
                this.a = r1;
            }

            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                if (i == 84 || i == 4) {
                    return true;
                }
                return false;
            }
        });
        this.t.show();
    }

    public void g() {
        if (!com.qq.reader.liveshow.utils.g.a(this)) {
            d(1003);
        } else if (com.qq.reader.liveshow.utils.g.b(this) == 1 || com.qq.reader.liveshow.model.c.a().g()) {
            C();
        } else {
            x();
        }
    }

    private void a(Dialog dialog) {
        if (dialog != null && !dialog.isShowing() && !isFinishing()) {
            dialog.show();
            SxbLog.c(c, "show dialog xxx = " + dialog);
        }
    }

    private void C() {
        SxbLog.e("START", "prepare startEnterRoom isEnterRooming = " + this.p + "  bInAvRoom = " + this.o);
        if (!com.qq.reader.liveshow.avcontrollers.c.a().g() || this.p || this.o) {
            SxbLog.e("START", "startEnterRoom error = " + this.p + "  bInAvRoom = " + this.o);
            return;
        }
        SxbLog.e("START", "really startEnterRoom isEnterRooming = " + this.p + "  bInAvRoom = " + this.o);
        this.p = true;
        this.e.a();
        this.f.a();
    }

    public void a() {
        SxbLog.c(c, "onSlideUp");
    }

    public void b() {
        SxbLog.c(c, "onSlideDown");
    }

    public void c() {
        SxbLog.c(c, "onSlideLeft");
    }

    public void d() {
        SxbLog.c(c, "onSlideRight");
    }

    public void a(RoomDetail roomDetail) {
        if (roomDetail == null) {
            n.a().e().a((Context) this, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ LiveActivity a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    if (this.a.w != null) {
                        this.a.w.a(com.qq.reader.liveshow.model.a.i());
                    }
                    dialogInterface.dismiss();
                }
            }, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ LiveActivity a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }, 1002).show();
        } else if (this.F != null) {
            Message b = this.F.b();
            b.what = APPluginErrorCode.ERROR_APP_SYSTEM;
            this.F.a(b);
            if (this.o && com.qq.reader.liveshow.model.c.a().h() == 1 && com.qq.reader.liveshow.model.a.o() == 1 && this.z.getTag() == null) {
                this.z.performClick();
                this.z.setTag(Boolean.valueOf(true));
            }
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        E();
        if (this.l != null) {
            this.l.cancel();
            this.l = null;
        }
        if (this.k != null) {
            this.k.cancel();
            this.k = null;
        }
        for (d h : this.G) {
            h.h();
        }
        this.F.a();
        this.f.p();
        this.e.c();
        this.v.d();
        com.qq.reader.liveshow.avcontrollers.c.a().c();
        com.qq.reader.liveshow.avcontrollers.c.a().m();
        this.p = false;
        this.B.a();
        this.w.a();
        com.qq.reader.liveshow.model.b.b();
        com.qq.reader.liveshow.utils.b.a((Context) this);
        com.qq.reader.liveshow.model.a.x();
        D();
    }

    private void D() {
        if (this.Z != null) {
            this.Z.e();
        }
        if (this.aa != null) {
            this.aa.e();
        }
        if (this.ab != null) {
            this.ab.e();
        }
        if (this.ac != null) {
            this.ac.e();
        }
    }

    public void onBackPressed() {
        if (!this.I.j()) {
            if (com.qq.reader.liveshow.model.c.a().h() == 1 && this.N) {
                a(this.U);
            } else {
                b(true);
            }
        }
    }

    private void E() {
        try {
            o();
            b(this.x);
            b(this.y);
            b(this.T.getDialog());
            b(this.U);
            b(this.x);
            b(this.t);
            b(this.O);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void b(Dialog dialog) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            SxbLog.c(c, "hide dialog xxx = " + dialog);
        }
    }

    private void a(boolean z) {
        boolean z2 = true;
        boolean a = com.qq.reader.liveshow.utils.g.a(this);
        if (com.qq.reader.liveshow.model.c.a().h() == 1) {
            com.qq.reader.liveshow.c.g gVar = this.f;
            if (!(z && a && this.o)) {
                z2 = false;
            }
            gVar.a(z2);
            return;
        }
        gVar = this.f;
        if (!(z && a && this.o)) {
            z2 = false;
        }
        gVar.a(z2);
    }

    private void F() {
        this.T = new WaitingDialog(this, false);
    }

    private void G() {
        this.x = n.a().e().a((Context) this, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ LiveActivity a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                com.qq.reader.liveshow.model.c.a().a(true);
                if (!this.a.p && !this.a.o) {
                    Map hashMap = new HashMap();
                    hashMap.put("orign", "1");
                    l.a("event_Z30", hashMap, this.a);
                    this.a.B.a(this.a);
                }
            }
        }, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ LiveActivity a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                if (this.a.N) {
                    this.a.a(this.a.U);
                } else {
                    this.a.b(false);
                }
            }
        });
        this.x.setCancelable(false);
        this.x.setOnKeyListener(new OnKeyListener(this) {
            final /* synthetic */ LiveActivity a;

            {
                this.a = r1;
            }

            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                if (i == 84 || i == 4) {
                    return true;
                }
                return false;
            }
        });
        this.y = n.a().e().b((Context) this, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ LiveActivity a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                com.qq.reader.liveshow.model.c.a().a(true);
                if (!this.a.p && !this.a.o) {
                    Map hashMap = new HashMap();
                    hashMap.put("orign", "2");
                    l.a("event_Z30", hashMap, this.a);
                    this.a.B.a(this.a);
                }
            }
        }, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ LiveActivity a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                this.a.b(true);
            }
        });
    }

    private void H() {
        this.U = n.a().e().c(this, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ LiveActivity a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                this.a.I();
            }
        }, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ LiveActivity a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                this.a.b(this.a.U);
            }
        });
    }

    private void I() {
        if (!this.V) {
            this.V = true;
            com.qq.reader.liveshow.model.a.b(true);
            this.f.a(true);
            if (this.N) {
                this.b = false;
                this.f.b(new com.qq.reader.liveshow.c.m<Boolean>(this) {
                    final /* synthetic */ LiveActivity a;

                    {
                        this.a = r1;
                    }

                    public void a(int i, Boolean bool) {
                        this.a.b = true;
                        this.a.J();
                    }

                    public void a(int i, String str) {
                        this.a.b = true;
                        this.a.J();
                    }

                    public void a(Exception exception) {
                        this.a.b = true;
                        this.a.J();
                    }
                });
            } else {
                this.b = true;
            }
            this.T.setMessage(getString(h.msg_exiting));
            a(this.T.getDialog());
            J();
        }
    }

    private void J() {
        if (this.a && this.b) {
            b(this.T.getDialog());
            N();
        }
    }

    public void h() {
        if (!com.qq.reader.liveshow.utils.a.a.a().g()) {
            this.a = true;
            if (com.qq.reader.liveshow.model.c.a().i()) {
                J();
                return;
            }
            if (this.o) {
                this.e.b();
            } else {
                this.v.b();
            }
            SxbLog.e("OUT", "start quit room");
        }
    }

    public void a(int i, boolean z) {
        if (!com.qq.reader.liveshow.utils.a.a.a().g()) {
            SxbLog.e(c, "enterRoomComplete");
            this.e.a(this.g);
            com.qq.reader.liveshow.avcontrollers.c.a().a((com.qq.reader.liveshow.avcontrollers.c.a) this);
            this.o = true;
            this.p = false;
            this.a = false;
            this.f.a();
            if (z) {
                this.f.a("" + com.qq.reader.liveshow.model.a.i());
                if (i == 1) {
                    SxbLog.b(c, "createlive enterRoomComplete isSucc" + z);
                } else {
                    this.f.w();
                }
            }
            this.q = false;
            b(this.T.getDialog());
            if (com.qq.reader.liveshow.model.c.a().h() != 1) {
                com.qq.reader.liveshow.model.a.a(true);
            } else if (com.qq.reader.liveshow.model.a.o() == 1 && this.z.getTag() == null) {
                this.z.performClick();
                this.z.setTag(Boolean.valueOf(true));
            }
            this.ad.a(0, "");
        }
    }

    public void a(int i, boolean z, int i2) {
        if (!com.qq.reader.liveshow.utils.a.a.a().g()) {
            SxbLog.e(c, "quitRoomComplete live info " + i2);
            this.o = false;
            if (this.e == null || !(this.e.a == 0 || i2 == 1001)) {
                if (this.q) {
                    K();
                }
                SxbLog.e("OUT", "start logout");
                if (this.C) {
                    this.v.b();
                    this.C = false;
                }
            } else if (this.e.a == 0) {
                b(false);
            } else {
                m.a((Context) this, h.network_unavailable, 0);
                d(i2);
            }
        }
    }

    private void d(int i) {
        n.a().e().a((Context) this, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ LiveActivity a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                this.a.B.a(this.a);
                dialogInterface.dismiss();
            }
        }, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ LiveActivity a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                this.a.b(false);
                dialogInterface.dismiss();
            }
        }, i).show();
    }

    public void i() {
        b(false);
    }

    public void b(int i) {
        m.a(getApplicationContext(), i, 0);
    }

    public void b(String[] strArr) {
    }

    public void c(String[] strArr) {
    }

    public void d(String[] strArr) {
        for (String str : strArr) {
            if (str.equals(com.qq.reader.liveshow.model.c.a().b())) {
                com.qq.reader.liveshow.avcontrollers.c.a().a(com.qq.reader.liveshow.model.c.a().b());
                com.qq.reader.liveshow.avcontrollers.c.a().a(true, com.qq.reader.liveshow.model.c.a().b());
            } else {
                com.qq.reader.liveshow.avcontrollers.c.a().a(true, str, 1);
            }
        }
    }

    public void a(boolean z, String str) {
        SxbLog.b(c, "showVideoView " + str);
        if (z) {
            SxbLog.b(c, "showVideoView host :" + com.qq.reader.liveshow.model.c.a().b());
            com.qq.reader.liveshow.avcontrollers.c.a().a(com.qq.reader.liveshow.model.c.a().b());
            com.qq.reader.liveshow.avcontrollers.c.a().a(true, com.qq.reader.liveshow.model.c.a().b());
        } else if (this.r) {
            com.qq.reader.liveshow.avcontrollers.c.a().a(true, str, 2);
            this.r = false;
        } else {
            com.qq.reader.liveshow.avcontrollers.c.a().a(true, str, 1);
        }
    }

    public void o() {
    }

    public void a(StreamRes streamRes) {
        this.P = true;
    }

    public void a(boolean z, int i) {
        if (z) {
            this.P = false;
        } else {
            SxbLog.b("RECORD_PUSH", "stop push stream fail");
        }
    }

    public void b(boolean z, int i) {
        if (z) {
            this.Y = true;
        } else {
            SxbLog.b("RECORD_PUSH", "start record fail ");
        }
    }

    public void a(boolean z, List<String> list, int i) {
        SxbLog.b(c, "stopRecordCallback   " + z + " files " + list);
        if (z) {
            this.Y = false;
        } else {
            SxbLog.b("RECORD_PUSH", "stop record fial ");
        }
    }

    public void p() {
        String str;
        String str2;
        String str3;
        long k = com.qq.reader.liveshow.model.a.k();
        long j = k / 3600;
        long j2 = (k % 3600) / 60;
        long j3 = (k % 3600) % 60;
        if (j < 10) {
            str = "0" + j;
        } else {
            str = "" + j;
        }
        if (j2 < 10) {
            str2 = "0" + j2;
        } else {
            str2 = "" + j2;
        }
        if (j3 < 10) {
            str3 = "0" + j3;
        } else {
            str3 = "" + j3;
        }
        if (str.equals("00")) {
            this.j = str2 + ":" + str3;
        } else {
            this.j = str + ":" + str2 + ":" + str3;
        }
        this.K.a(this.j);
    }

    private void K() {
        this.H.l();
        com.qq.reader.liveshow.avcontrollers.c.a().j();
    }

    private void b(boolean z) {
        SxbLog.b(c, "quitRoom4Finish notify  = " + z + "  xxxx " + this.T.a());
        if (com.qq.reader.liveshow.utils.a.a.a().g()) {
            SxbLog.b(c, " is quit  ?????????   ");
            finish();
            return;
        }
        finish();
        c(z);
    }

    public com.qq.reader.liveshow.c.g q() {
        return this.f;
    }

    private void L() {
        if (!com.qq.reader.liveshow.utils.g.a(this)) {
            m.a((Context) this, getResources().getString(h.live_net_error), 0);
        } else if (com.qq.reader.liveshow.model.c.a().h() == 1 && this.n) {
            this.f.a(new com.qq.reader.liveshow.c.m<com.qq.reader.liveshow.model.d.d>(this) {
                final /* synthetic */ LiveActivity a;

                {
                    this.a = r1;
                }

                public void a(int i, com.qq.reader.liveshow.model.d.d dVar) {
                    if (dVar == null) {
                        return;
                    }
                    if ((dVar.a == 0 || dVar.a == DLConstants.LOAD_ERR_NAME_NOT_FOUND) && !this.a.N) {
                        if (this.a.F != null) {
                            Message b = this.a.F.b();
                            b.what = 2001;
                            this.a.F.a(b);
                        }
                        com.qq.reader.liveshow.model.a.a(true);
                        this.a.N = true;
                        this.a.A.setVisibility(8);
                        if (this.a.k != null) {
                            this.a.k.cancel();
                        }
                        if (this.a.l != null) {
                            this.a.l.cancel();
                        }
                        this.a.k = new Timer(true);
                        this.a.l = new b();
                        this.a.k.schedule(this.a.l, 1000, 1000);
                        this.a.n = false;
                    }
                }

                public void a(int i, String str) {
                    this.a.A.setVisibility(0);
                    m.a(this.a, h.tip_open_live_error, 0);
                }

                public void a(Exception exception) {
                    this.a.A.setVisibility(0);
                    m.a(this.a, h.tip_open_live_error, 0);
                }
            });
        }
    }

    private void M() {
        if (this.v != null) {
            this.v.a(true);
        }
        a(this.T.getDialog());
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        this.B.a(i, strArr, iArr);
    }

    protected void r() {
        super.r();
        b(false);
    }

    private void N() {
        if (this.d != 5) {
            com.qq.reader.liveshow.model.a.g(2);
            this.ad.a(5, null);
        }
    }

    public void j() {
        SxbLog.b(c, "receive the end msg");
        if (!com.qq.reader.liveshow.utils.a.a.a().g()) {
            if (com.qq.reader.liveshow.model.c.a().i()) {
                if (!this.V) {
                    m.a((Context) this, h.host_live_close_by_manager, 0);
                }
                I();
                return;
            }
            N();
        }
    }

    private void a(boolean z, boolean z2) {
        this.C = z;
        this.D = z2;
    }

    private void a(Object obj) {
        boolean z = true;
        if (!isFinishing()) {
            switch (this.d) {
                case -1:
                    if (!(obj == null || !(obj instanceof String) || TextUtils.isEmpty((CharSequence) obj))) {
                        m.a((Context) this, obj.toString(), 0);
                    }
                    b(false);
                    b(this.O);
                    return;
                case 0:
                    b(this.O);
                    return;
                case 2:
                    com.qq.reader.liveshow.c.c.a(getApplicationContext());
                    a(this.Z);
                    a(this.O);
                    com.qq.reader.liveshow.model.a.b(false);
                    com.qq.reader.liveshow.utils.a.a.a().a(this.e, this.v, this.f);
                    return;
                case 3:
                    this.O.a(null);
                    a(this.ab);
                    a(this.O);
                    return;
                case 4:
                    return;
                case 5:
                    c(false);
                    this.O.a(null);
                    com.qq.reader.liveshow.views.roomdialog.a.a.a b;
                    if (com.qq.reader.liveshow.model.c.a().h() == 1) {
                        if (this.ac != null) {
                            this.ac.e();
                        }
                        b = n.a().e().b(this.ad, (Activity) this, this.O.a());
                        b.setName(com.qq.reader.liveshow.model.a.d());
                        b.setAvatarUrl(com.qq.reader.liveshow.model.a.e());
                        b.setRoomId(com.qq.reader.liveshow.model.a.i());
                        a(b);
                        this.ac = b;
                    } else {
                        if (this.ac != null) {
                            this.ac.e();
                        }
                        b = n.a().e().a(this.ad, (Activity) this, this.O.a());
                        b.setName(com.qq.reader.liveshow.model.a.d());
                        b.setAvatarUrl(com.qq.reader.liveshow.model.a.e());
                        b.setRoomId(com.qq.reader.liveshow.model.a.i());
                        a(b);
                        this.ac = b;
                    }
                    a(this.O);
                    return;
                case 6:
                    if (!this.ae) {
                        this.ae = true;
                        this.s = com.qq.reader.liveshow.model.a.c();
                        this.v.a(com.qq.reader.liveshow.model.c.a().b(), com.qq.reader.liveshow.model.c.a().c());
                        return;
                    }
                    return;
                case 7:
                    if (com.qq.reader.liveshow.model.c.a().i() || !this.o) {
                        z = false;
                    }
                    b(z);
                    return;
                case 8:
                    a(this.aa);
                    a(this.O);
                    return;
                default:
                    return;
            }
        }
    }

    private void a(com.qq.reader.liveshow.views.roomdialog.a.a.a aVar) {
        this.O.a(aVar);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        switch (i) {
            case 1002:
                this.B.a((Activity) this);
                return;
            default:
                return;
        }
    }

    private void c(boolean z) {
        z();
        if (com.qq.reader.liveshow.model.c.a().i()) {
            com.qq.reader.liveshow.utils.a.a.a().a(this.P, this.Y);
        } else {
            com.qq.reader.liveshow.utils.a.a.a().a(z);
        }
    }
}
