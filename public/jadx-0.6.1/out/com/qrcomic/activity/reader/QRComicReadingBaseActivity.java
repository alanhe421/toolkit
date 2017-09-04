package com.qrcomic.activity.reader;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation.AnimationListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import b.a.a.a.a.a.e;
import com.google.gson.Gson;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qrcomic.a.g;
import com.qrcomic.a.h;
import com.qrcomic.a.j;
import com.qrcomic.activity.reader.a.f;
import com.qrcomic.activity.reader.startup.QRComicReadPageDirector;
import com.qrcomic.activity.reader.startup.QRComicReadPageDirector.QueryComicAndSectionListInfoException;
import com.qrcomic.activity.reader.startup.QRComicReadPageDirector.QuerySectionListInfoException;
import com.qrcomic.activity.reader.startup.QRComicReadPageDirector.QuerySectionPicInfoException;
import com.qrcomic.activity.reader.startup.QRComicReadPageDirector.QueryUserBuyInfoException;
import com.qrcomic.c.c.a;
import com.qrcomic.c.c.c;
import com.qrcomic.downloader.d;
import com.qrcomic.downloader.i;
import com.qrcomic.entity.ComicRecommendPageInfo;
import com.qrcomic.entity.ComicSectionPicInfo;
import com.qrcomic.entity.QRComicBuyReqInfo;
import com.qrcomic.entity.RecommendComicInfo;
import com.qrcomic.entity.b;
import com.qrcomic.manager.QRComicManager;
import com.qrcomic.util.k;
import com.qrcomic.widget.LoadingDialog;
import com.qrcomic.widget.reader.QRComicReaderBottomBar;
import com.qrcomic.widget.reader.QRComicReaderMenu;
import com.qrcomic.widget.reader.QRComicReaderViewPager;
import com.qrcomic.widget.reader.QRComicScrollReaderListView;
import com.tencent.theme.SkinEngine;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.atomic.AtomicBoolean;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;

public abstract class QRComicReadingBaseActivity extends FragmentActivity implements Callback, Observer {
    public static boolean aV = false;
    private static String aW = "QRComicReadingBaseActivity";
    protected ImageView A;
    protected TextView B;
    protected TextView C;
    protected TextView D;
    protected ImageView E;
    protected LinearLayout F;
    protected LinearLayout G;
    protected LinearLayout H;
    protected LinearLayout I;
    protected TextView J;
    protected LinearLayout K;
    protected ImageView L;
    protected c M;
    protected a N;
    protected View O;
    protected View P;
    protected RelativeLayout Q;
    protected ViewGroup R;
    protected TextView S;
    protected TextView T;
    protected ViewGroup U;
    protected int V = -1;
    protected View W;
    protected boolean X;
    protected AtomicBoolean Y = new AtomicBoolean(false);
    public a Z;
    public h a;
    protected int aA;
    protected int aB;
    protected int aC;
    public int aD = 0;
    public boolean aE = false;
    public int aF = 0;
    public int aG = 0;
    com.qrcomic.screenshot.d.c aH;
    DisplayMetrics aI;
    protected boolean aJ = true;
    public boolean aK = true;
    protected boolean aL;
    protected boolean aM;
    AnimationListener aN = new 37(this);
    QRComicScrollReaderListView.c aO = new 38(this);
    com.qrcomic.widget.reader.a aP = new 39(this);
    OnClickListener aQ = new 10(this);
    protected boolean aR = false;
    f aS = new 13(this);
    com.qrcomic.activity.reader.a.a aT = new 14(this);
    BroadcastReceiver aU = new 21(this);
    private boolean aX = false;
    private PopupWindow aY;
    private Dialog aZ;
    public a aa;
    public b ab = null;
    public Activity ac;
    public int ad = 0;
    public long ae = 0;
    public long af = 0;
    public long ag = 0;
    c ah = new c(this);
    public boolean ai = false;
    public com.qrcomic.widget.reader.c aj;
    public QRComicScrollReaderListView ak;
    public QRComicReaderViewPager al;
    public Dialog am;
    protected Handler an = new k(Looper.getMainLooper(), this);
    public boolean ao = false;
    public boolean ap = false;
    public boolean aq = false;
    public boolean ar = false;
    public int as;
    public int at;
    public int au = 0;
    public int av = 0;
    public int aw = 0;
    public int ax = 0;
    public int ay = 0;
    protected int az;
    protected float b;
    private com.qrcomic.screenshot.d.a ba;
    private LoadingDialog bb;
    private a bc;
    private boolean bd;
    private int be = 0;
    private boolean bf = true;
    private int bg = 0;
    private b bh = new 1(this);
    private com.qrcomic.e.c bi = new 12(this);
    private g.a bj = new 35(this);
    private Runnable bk = new 9(this);
    private ColorDrawable bl = null;
    private ColorDrawable bm = null;
    private ColorDrawable bn = null;
    private StateListDrawable bo = null;
    private StateListDrawable bp = null;
    private StateListDrawable bq = null;
    public boolean c;
    public boolean d;
    public boolean e;
    public boolean f;
    public boolean g = false;
    protected com.qrcomic.f.f<Integer> h;
    protected com.qrcomic.f.f<Integer> i;
    protected com.qrcomic.f.f<Integer> j;
    protected com.qrcomic.f.f<Integer> k;
    protected SeekBar l;
    protected TextView m;
    public QRComicReaderBottomBar n;
    public QRComicReaderMenu o;
    protected boolean p;
    protected boolean q;
    protected ImageView r;
    protected ImageView s;
    protected ImageView t;
    protected com.qrcomic.manager.c u;
    protected QRComicManager v;
    protected com.qrcomic.e.b w;
    protected d x;
    protected com.qrcomic.manager.a y;
    protected View z;

    protected abstract void M();

    @TargetApi(19)
    public abstract void a(View view);

    @TargetApi(19)
    public abstract void b(View view);

    protected abstract void e();

    protected abstract void f();

    protected abstract void g();

    protected abstract void h();

    public abstract void i();

    public abstract void k();

    public abstract boolean l();

    protected abstract void m();

    protected abstract void n();

    protected abstract void o();

    public abstract void p();

    public abstract boolean q();

    protected abstract void v();

    public abstract void x();

    public void a() {
    }

    @TargetApi(11)
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.ac = this;
        this.ba = new com.qrcomic.screenshot.d.a();
        this.a = com.qrcomic.manager.b.a().b();
        if (this.a == null) {
            Toast.makeText(this, "出现错误，请重试", 0).show();
            A();
            return;
        }
        this.M = this.a.f().f().a();
        this.N = this.a.f().f().c();
        this.b = getResources().getDisplayMetrics().density;
        this.a = com.qrcomic.manager.b.a().b();
        this.aK = false;
        this.u = (com.qrcomic.manager.c) this.a.a(3);
        this.v = (QRComicManager) this.a.a(1);
        this.w = (com.qrcomic.e.b) this.a.b(1);
        this.x = d.b();
        this.y = (com.qrcomic.manager.a) this.a.a(5);
        this.aI = getResources().getDisplayMetrics();
        this.as = this.aI.widthPixels;
        this.at = this.aI.heightPixels;
        this.bg = (int) this.aI.density;
        a(getIntent(), false);
        if (VERSION.SDK_INT >= 11) {
            getWindow().setFlags(SigType.WLOGIN_PF, SigType.WLOGIN_PF);
        }
        getWindow().addFlags(Opcodes.FILL_ARRAY_DATA_PAYLOAD);
        this.aE = F();
        if (this.aE) {
            this.aD = a((Context) this);
            if (com.qrcomic.util.g.a()) {
                com.qrcomic.util.g.a(aW, com.qrcomic.util.g.d, "mNavigationBarHeight = " + this.aD);
            }
        }
        this.a.a(this.Z.n, true);
        this.aJ = com.qrcomic.util.h.c.b();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.qq.reader.openVip");
        intentFilter.addAction("com.qq.reader.loginok");
        registerReceiver(this.aU, intentFilter);
        this.aH = new com.qrcomic.screenshot.d.c(this);
        this.aH.a(new 23(this));
        this.ba.a(this, this.a.e());
        this.bb = new LoadingDialog(this);
        this.a.a(this.bi);
        this.a.f().c().a("event_F272", null, getApplicationContext());
        this.aG = this.a.f().a().a() ? 1 : 0;
    }

    protected void onStart() {
        super.onStart();
        if (this.a != null) {
            R();
        }
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.ao = true;
        this.aa = this.Z;
        a(intent, true);
        if (com.qrcomic.util.g.a()) {
            com.qrcomic.util.g.b(aW, com.qrcomic.util.g.d, "onNewIntent");
        }
        if (this.aa != null) {
            if (!(this.aa.N == null || this.aa.N.isInterrupted())) {
                this.aa.N.a();
            }
            this.aa.deleteObservers();
        }
    }

    protected void b() {
        if (this.s == null) {
            return;
        }
        if (com.qrcomic.util.h.a.a(com.qrcomic.util.h.b.a, this.Z.n)) {
            this.s.setVisibility(0);
        } else {
            this.s.setVisibility(8);
        }
    }

    private void a(Intent intent, boolean z) {
        this.Z = new a();
        this.Z.Q = intent.getBooleanExtra("key_switch_flag", false);
        if (this.Z.Q) {
            Collection collection;
            try {
                collection = (ArrayList) intent.getSerializableExtra("key_pay_section_list");
            } catch (Exception e) {
                collection = null;
            }
            if (!(collection == null || collection.isEmpty())) {
                this.Z.y.clear();
                this.Z.y.addAll(collection);
            }
            Parcelable parcelableExtra = intent.getParcelableExtra("comic_recommend_page_info");
            if (parcelableExtra != null && (parcelableExtra instanceof ComicRecommendPageInfo)) {
                this.Z.L = (ComicRecommendPageInfo) parcelableExtra;
                if (!this.Z.L.a()) {
                    this.Z.L = null;
                }
            }
        }
        this.a.e().addObserver(this.bj);
        this.Z.addObserver(this);
        this.Z.n = intent.getStringExtra("key_comic_id");
        this.Z.j = intent.getStringExtra("key_section_id");
        if ("0".equals(this.Z.j)) {
            this.Z.j = "";
        }
        this.Z.k = intent.getIntExtra("key_pay_flag", 0);
        this.Z.B = intent.getStringExtra("key_pic_id");
        this.Z.E = intent.getIntExtra("key_section_index", -1);
        this.Z.D = this.Z.j;
        this.e = intent.getBooleanExtra("key_pay_req_from_land", false);
        this.f = !TextUtils.isEmpty(intent.getStringExtra("key_share_flag"));
        if (TextUtils.isEmpty(this.Z.n)) {
            if (com.qrcomic.util.g.a()) {
                com.qrcomic.util.g.b(aW, com.qrcomic.util.g.d, "user :" + this.a.a() + ", errMsg: comicId is null");
            }
            finish();
        } else {
            if (this.z != null) {
                D();
            }
            if (com.qrcomic.util.g.a()) {
                com.qrcomic.util.g.b(aW, com.qrcomic.util.g.d, "user :" + this.a.a() + ", comicId:" + this.Z.n + ", sectionId:" + this.Z.j);
            }
            this.ae = System.currentTimeMillis();
            this.Z.N = new b(this.Z, this.aT);
            this.Z.N.start();
            if (!(this.e || this.f)) {
                ab();
            }
            this.Z.a(this.a, this.v, this.y, intent.getBooleanExtra("key_load_data_force_net", false));
            if (!this.Z.J) {
                this.an.sendEmptyMessage(8);
            }
        }
        this.bd = false;
    }

    public void a(String str) {
        if (this.v != null) {
            j.a().a(new 34(this, str), null, true);
        }
    }

    public boolean c() {
        return this.ab != null && this.ab.k == 1;
    }

    public void d() {
        if (this.Z != null && this.Z.i != null) {
            if (this.ab == null) {
                this.ab = new b();
                this.ab.b = this.Z.n;
                this.ab.a = com.qrcomic.manager.b.a().b().a();
                this.ab.k = 1;
                this.ab.f = System.currentTimeMillis();
                this.ab.n = this.Z.i.m;
            } else if (this.ab.k != 1) {
                this.ab.k = 1;
                this.ab.f = System.currentTimeMillis();
            } else {
                return;
            }
            List arrayList = new ArrayList();
            arrayList.add(this.ab);
            this.w.a(arrayList, false);
            if (this.Z.o != null) {
                String str;
                if (this.Z.H == 2) {
                    str = "0";
                } else if (this.aF == 0) {
                    str = "2";
                } else {
                    str = "1";
                }
                if (this.a == null) {
                }
            }
        } else if (com.qrcomic.util.g.a()) {
            com.qrcomic.util.g.b(aW, com.qrcomic.util.g.d, "Invalid Call.");
        }
    }

    private void Z() {
        if (this.w != null && com.qrcomic.util.f.a(this)) {
            this.w.d();
        }
    }

    private void aa() {
        if (!(this.ak == null || this.ak.getVisibility() != 0 || this.aj == null)) {
            this.aj.a();
        }
        if (this.al != null && this.al.getVisibility() == 0 && (this instanceof QRComicReadingVerticalActivity) && this.Z.r != null && this.Z.C >= 0 && this.Z.C < this.Z.r.size()) {
            ((QRComicReadingVerticalActivity) this).a((ComicSectionPicInfo) this.Z.r.get(this.Z.C));
        }
    }

    private void ab() {
        if (this instanceof QRComicReadingLandActivity) {
            this.Z.H = 2;
        } else if (this instanceof QRComicReadingVerticalActivity) {
            this.Z.H = 1;
        }
        com.qrcomic.util.h.c.b(this.Z.H);
    }

    private void ac() {
        a(false);
    }

    private void a(boolean z) {
        try {
            if (this.Z.i != null && this.Z.o != null && this.Z.r != null && this.Z.B != null && this.Z.o.t == 0) {
                String str = this.Z.n;
                String str2 = this.Z.i.b;
                String str3 = this.Z.o.b;
                int i = this.Z.E;
                String str4 = this.Z.o.c;
                String str5 = this.Z.B;
                float f = 0.0f;
                if (this.Z.C >= 0 && this.Z.C < this.Z.r.size()) {
                    f = (float) ((ComicSectionPicInfo) this.Z.r.get(this.Z.C)).top;
                }
                try {
                    j.b(new 36(this, str, str2, str3, i, str4, str5, (int) f, this.Z.C, z));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    protected void j() {
        this.an.removeCallbacks(this.bk);
        if (this.aY != null && this.aY.isShowing()) {
            this.aY.dismiss();
        }
    }

    public void a(a aVar) {
        this.bc = aVar;
    }

    protected void r() {
        a(this.ac, this.Z.n, this.Z.D, this.Z.E, this.Z.B, 0, false, true, null, true, getIntent().getIntExtra("key_back_to_root_activity", 0));
    }

    protected void s() {
        a(this.ac, this.Z.n, this.Z.D, this.Z.E, this.Z.B, 0, false, true, null, true, getIntent().getIntExtra("key_back_to_root_activity", 0));
    }

    protected void t() {
        if (this.Z != null && this.Z.P != null) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new QRComicBuyReqInfo(this.Z.n));
            this.Z.l = false;
            this.Z.P.a(this.Z, arrayList);
        }
    }

    public void u() {
        this.n.setBackgroundResource(this.N.d(false));
        ((ImageView) this.F.getChildAt(0)).setImageResource(this.N.a(false));
        ((ImageView) this.G.getChildAt(0)).setImageResource(this.N.b(false));
        ((ImageView) this.H.getChildAt(0)).setImageResource(this.N.c(false));
        ((ImageView) this.I.getChildAt(0)).setImageResource(this.N.k(false));
        ColorStateList colorStateList = getResources().getColorStateList(this.N.e(false));
        ((TextView) this.F.getChildAt(1)).setTextColor(colorStateList);
        ((TextView) this.G.getChildAt(1)).setTextColor(colorStateList);
        ((TextView) this.H.getChildAt(1)).setTextColor(colorStateList);
        ((TextView) this.I.getChildAt(1)).setTextColor(colorStateList);
        if (this.o != null) {
            this.o.f();
        }
        if (this.C != null) {
            this.C.setCompoundDrawablesWithIntrinsicBounds(this.M.a(false), 0, 0, 0);
        }
        b(false);
    }

    private void b(boolean z) {
        if (this.aY != null) {
            this.aY.setBackgroundDrawable(getResources().getDrawable(this.M.e(false)));
            View contentView = this.aY.getContentView();
            ColorStateList colorStateList = getResources().getColorStateList(this.M.h(false));
            int color = getResources().getColor(this.M.h(false));
            TextView textView = (TextView) contentView.findViewById(e.reader_secret);
            textView.setCompoundDrawablesWithIntrinsicBounds(this.M.f(false), 0, 0, 0);
            textView.setTextColor(color);
            textView = (TextView) contentView.findViewById(e.reader_detail);
            textView.setCompoundDrawablesWithIntrinsicBounds(this.M.g(false), 0, 0, 0);
            textView.setTextColor(color);
            textView = (TextView) contentView.findViewById(e.reader_secret_state);
            textView.setTextColor(colorStateList);
            textView.setEnabled(false);
            if (this.a.f().b().b(this.Z.n, this.a.b())) {
                textView.setVisibility(0);
            } else {
                textView.setVisibility(4);
            }
        }
    }

    public void w() {
        if (!this.c) {
            if ((this.o != null && this.o.a()) || q()) {
                return;
            }
            if (l()) {
                k();
                return;
            }
            i();
            if (!this.bf && com.qrcomic.util.f.a(this) && this.be <= 3) {
                Z();
            }
        }
    }

    public void y() {
        if (!(this.Z == null || this.Z.o == null || this.Z.r == null)) {
            if (this.Z.r.size() == 1) {
                this.l.setMax(1);
            } else {
                this.l.setMax(this.Z.r.size() - 1);
            }
            if (this.Z.C >= this.Z.r.size()) {
                this.l.setProgress(this.Z.r.size() - 1);
                this.S.setText(this.Z.r.size() + "/" + this.Z.r.size());
                if (com.qrcomic.util.g.a()) {
                    com.qrcomic.util.g.a(aW, com.qrcomic.util.g.d, " mCurrentImageTv " + this.S.getText());
                }
            } else {
                this.l.setProgress(this.Z.C);
                this.T.setText(this.Z.o.c);
                this.S.setText((this.Z.C + 1) + "/" + this.Z.r.size());
                if (com.qrcomic.util.g.a()) {
                    com.qrcomic.util.g.a(aW, com.qrcomic.util.g.d, " mCurrentImageTv " + this.S.getText());
                }
            }
            z();
        }
        M();
        if (this.Z != null && this.Z.i != null && this.Z.i.J != 0) {
            this.J.setText(com.qq.reader.common.utils.j.a(this.Z.i.J));
        } else if (!"评论".equals(this.J.getText().toString())) {
            this.J.setText("评论");
        }
    }

    public void z() {
    }

    public static int a(Context context, String str, String str2, int i, String str3, int i2, boolean z, boolean z2, List<String> list, boolean z3, int i3) {
        if (TextUtils.isEmpty(str)) {
            if (com.qrcomic.util.g.a()) {
                com.qrcomic.util.g.a(aW, com.qrcomic.util.g.d, "openReadingActivity fail: comicId is null");
            }
            return -1;
        }
        Intent intent = new Intent(context, QRComicReadingVerticalActivity.class);
        intent.putExtra("key_comic_id", str);
        intent.putExtra("key_section_id", str2);
        intent.putExtra("key_section_index", i);
        intent.putExtra("key_pic_id", str3);
        intent.putExtra("params_remote_connect_at_launch", true);
        intent.putExtra("key_switch_flag", z);
        intent.putExtra("key_pay_section_list", (Serializable) list);
        intent.putExtra("key_load_data_force_net", z3);
        intent.putExtra("key_back_to_root_activity", i3);
        if (z2 || !(context instanceof Activity)) {
            intent.addFlags(SigType.WLOGIN_QRPUSH);
        }
        if (!(context instanceof Activity)) {
            intent.addFlags(tencent.tls.platform.SigType.TLS);
        }
        context.startActivity(intent);
        return 1;
    }

    public void a(Class<?> cls, String str, String str2, int i, String str3, int i2) {
        Intent intent = new Intent(this, cls);
        intent.putExtra("key_comic_id", str);
        intent.putExtra("key_section_id", str2);
        intent.putExtra("key_section_index", i);
        intent.putExtra("key_pic_id", str3);
        intent.putExtra("key_pay_flag", i2);
        intent.putExtra("params_remote_connect_at_launch", true);
        intent.putExtra("key_switch_flag", true);
        if (this.Z != null) {
            intent.putExtra("key_pay_section_list", (Serializable) this.Z.y);
            intent.putExtra("comic_recommend_page_info", this.Z.L);
        }
        startActivity(intent);
        if (this.o != null) {
            this.o.d();
        }
        this.g = true;
        finish();
    }

    public void a(String str, String str2, int i, String str3) {
        int i2 = 1;
        this.y.a();
        Intent intent = new Intent(this, QRComicReadingVerticalActivity.class);
        intent.putExtra("key_comic_id", str);
        intent.putExtra("key_section_id", str2);
        intent.putExtra("key_section_index", i);
        intent.putExtra("key_pic_id", str3);
        intent.putExtra("key_pay_req_from_land", true);
        String str4 = "comicExtraCode";
        if (this.Z.A) {
            i2 = 0;
        }
        intent.putExtra(str4, i2);
        startActivityForResult(intent, 4097);
    }

    public void A() {
        if (!(this.Z == null || this.Z.o == null || this.a == null)) {
            this.a.f().b().a(this.Z.o, this.Z.i);
        }
        if (this.a != null) {
            this.a.f().b().b(this.Z, this.ac);
            this.a.h.c();
            this.a.i.c();
        }
        try {
            if (this.x != null) {
                this.x.f();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        finish();
    }

    public void B() {
        com.qrcomic.c.c b = this.a.f().b();
        if (c(this.Z.n)) {
            A();
            return;
        }
        com.qrcomic.c.a f = this.a.f().f();
        if (f != null) {
            T();
            this.aZ = f.a(this.ac, new 2(this, b), new 3(this));
            this.aZ.setOnKeyListener(new 4(this));
            if (this.aZ != null) {
                this.a.f().c().a("event_F286", null, getApplicationContext());
                this.aZ.show();
            }
        }
    }

    protected void C() {
        if (this.z == null) {
            this.z = getLayoutInflater().inflate(b.a.a.a.a.a.f.qr_comic_loading_view, null);
            this.z.setVisibility(8);
            this.A = (ImageView) this.z.findViewById(e.loading_gif);
            this.B = (TextView) this.z.findViewById(e.loading_msg);
            this.C = (TextView) this.z.findViewById(e.loading_back);
            this.D = (TextView) this.z.findViewById(e.loading_reload);
            this.D.setOnClickListener(new 5(this));
            this.C.setOnClickListener(new 6(this));
            addContentView(this.z, new LayoutParams(-1, -1));
        }
        ad();
    }

    private void ad() {
        if (this.W == null) {
            this.W = new ImageView(this);
            this.W.setBackgroundColor(1996488704);
            this.W.setClickable(false);
            this.W.setFocusable(false);
            this.W.setFocusableInTouchMode(false);
            this.W.setVisibility(8);
            addContentView(this.W, new LayoutParams(-1, -1));
        }
    }

    public void D() {
        if (l()) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.C.getLayoutParams();
            layoutParams.topMargin = ((int) getResources().getDimension(b.a.a.a.a.a.c.loading_back_top_offset)) + G();
            this.C.setLayoutParams(layoutParams);
        }
        if (this.z.getVisibility() == 8) {
            this.z.setVisibility(0);
        }
        if (this.D.getVisibility() == 0) {
            this.D.setVisibility(4);
            this.D.setClickable(false);
        }
        this.A.setBackgroundResource(b.a.a.a.a.a.d.vip_comic_loading_drawable);
        this.B.setText(b.a.a.a.a.a.g.comic_reader_loading_msg);
        Drawable background = this.A.getBackground();
        if (background instanceof AnimationDrawable) {
            AnimationDrawable animationDrawable = (AnimationDrawable) background;
            animationDrawable.setOneShot(false);
            animationDrawable.start();
        }
        if (com.qrcomic.util.g.a()) {
            com.qrcomic.util.g.a(aW, com.qrcomic.util.g.d, "show loading view ");
        }
    }

    public void E() {
        if (this.z != null && this.z.getVisibility() == 0) {
            Drawable background = this.A.getBackground();
            if (background instanceof AnimationDrawable) {
                AnimationDrawable animationDrawable = (AnimationDrawable) background;
                animationDrawable.setOneShot(true);
                animationDrawable.stop();
            }
            this.z.setVisibility(8);
        }
    }

    public void a(String str, int i, boolean z) {
        this.an.removeMessages(2);
        Message obtain = Message.obtain();
        obtain.what = 2;
        obtain.obj = str;
        obtain.arg1 = i;
        obtain.arg2 = z ? 1 : 0;
        this.an.sendMessageDelayed(obtain, 100);
        if (com.qrcomic.util.g.a()) {
            com.qrcomic.util.g.a(aW, com.qrcomic.util.g.d, "showLoadingFail comicMsg = " + str);
        }
    }

    public void b(String str) {
        if (!TextUtils.isEmpty(str)) {
        }
    }

    @TargetApi(19)
    public boolean F() {
        boolean z = false;
        if (VERSION.SDK_INT >= 19 && h.a) {
            boolean hasPermanentMenuKey = ViewConfiguration.get(this.ac).hasPermanentMenuKey();
            boolean deviceHasKey = KeyCharacterMap.deviceHasKey(4);
            boolean b = b((Context) this);
            if (!hasPermanentMenuKey && b) {
                z = true;
            }
            if (com.qrcomic.util.g.a()) {
                com.qrcomic.util.g.a(aW, com.qrcomic.util.g.d, "hasMenuKey = " + hasPermanentMenuKey + " , hasBackKey = " + deviceHasKey + " , hasNavigationBar = " + b + " , ishasNavigationBar = " + z);
            }
        } else if (com.qrcomic.util.g.a()) {
            com.qrcomic.util.g.a(aW, com.qrcomic.util.g.d, "API = " + VERSION.SDK_INT + " , isHandleNavigationBar = " + h.a);
        }
        return z;
    }

    private boolean b(Context context) {
        boolean z;
        boolean z2;
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("config_showNavigationBar", "bool", "android");
        if (identifier > 0) {
            z = resources.getBoolean(identifier);
            if (com.qrcomic.util.g.a()) {
                com.qrcomic.util.g.a(aW, com.qrcomic.util.g.d, "hasNavigationBar one = " + z);
            }
        } else {
            z = false;
        }
        try {
            Class cls = Class.forName("android.os.SystemProperties");
            Method method = cls.getMethod("get", new Class[]{String.class});
            method.setAccessible(true);
            String str = (String) method.invoke(cls, new Object[]{"qemu.hw.mainkeys"});
            if (com.qrcomic.util.g.a()) {
                com.qrcomic.util.g.a(aW, com.qrcomic.util.g.d, "navBarOverride =  " + str);
            }
            if ("1".equals(str)) {
                z2 = false;
            } else {
                if ("0".equals(str)) {
                    z2 = true;
                }
                z2 = z;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if (com.qrcomic.util.g.a()) {
            com.qrcomic.util.g.a(aW, com.qrcomic.util.g.d, "hasNavigationBar two = " + z2);
        }
        return z2;
    }

    public int G() {
        int dimensionPixelSize;
        try {
            dimensionPixelSize = Resources.getSystem().getDimensionPixelSize(Resources.getSystem().getIdentifier("status_bar_height", "dimen", "android"));
        } catch (Exception e) {
            dimensionPixelSize = (int) (((double) (this.aI.density * 25.0f)) + 0.5d);
        }
        if (com.qrcomic.util.g.a()) {
            com.qrcomic.util.g.a(aW, com.qrcomic.util.g.d, "获取系统状态栏高度 = " + dimensionPixelSize);
        }
        return dimensionPixelSize;
    }

    protected int a(Context context) {
        int i = (int) (((double) (this.aI.density * 48.0f)) + 0.5d);
        try {
            Resources resources = context.getResources();
            int identifier = resources.getIdentifier("navigation_bar_height", "dimen", "android");
            if (identifier > 0) {
                i = resources.getDimensionPixelSize(identifier);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (com.qrcomic.util.g.a()) {
            com.qrcomic.util.g.a(aW, com.qrcomic.util.g.d, "getNavigationBarHeight = " + i);
        }
        return i;
    }

    public void a(com.qrcomic.entity.f fVar, int i) {
        if (fVar != null && !this.ai) {
            new ArrayList().add(fVar.b);
            if (this.Z.H == 2 && this.Z.i != null) {
                CharSequence charSequence = "";
                if (i == 0) {
                    charSequence = getResources().getString(b.a.a.a.a.a.g.req_cur_section);
                } else if (i == 2) {
                    charSequence = getResources().getString(b.a.a.a.a.a.g.req_next_section);
                } else if (i == 1) {
                    charSequence = getResources().getString(b.a.a.a.a.a.g.req_pre_section);
                }
                this.am = new Builder(this).setMessage(charSequence).setNegativeButton("取消", new 8(this, fVar)).setPositiveButton("支付", new 7(this, fVar)).create();
                this.am.show();
            } else if (this.Z.i != null) {
                H();
                QRComicManager.a(this, this.Z.n, this.Z.i.b, fVar.b, fVar.c, fVar.c, fVar.g, 4097, 4098, this.Z.i.g, this.aF, W());
            }
            this.ai = true;
            if (this.ak != null) {
                this.ak.setTouchEventEnabled(false);
            }
        }
    }

    protected void H() {
        if (this.Z != null && this.Z.i != null) {
            if (this.Z.i.m == 1) {
                this.aF = 0;
            } else {
                this.aF = com.qrcomic.util.h.c.c();
            }
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (!hasWindowFocus()) {
            return false;
        }
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        a(U());
        B();
        return true;
    }

    public void I() {
        try {
            if (!this.X && !this.Y.getAndSet(true)) {
                if (com.qrcomic.util.g.a()) {
                    com.qrcomic.util.g.a(aW, com.qrcomic.util.g.d, "开始同步阅读进度。。。");
                }
                if (this.a.f().b() != null) {
                    this.a.f().b().a(this, this.Z.n, new 11(this));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void a(com.qrcomic.entity.e eVar) {
        a(this, eVar.c, eVar.a, eVar.m, eVar.f, eVar.h, false, false, null, true, getIntent().getIntExtra("key_back_to_root_activity", 0));
    }

    public void J() {
        if (!isFinishing() && this.aR && !isFinishing()) {
            finish();
        }
    }

    public boolean handleMessage(Message message) {
        boolean z = false;
        String str;
        switch (message.what) {
            case 0:
                m();
                if (this.Z.o.t != 1) {
                    E();
                    break;
                }
                break;
            case 1:
                this.a.f().b().a(this.Z.i, this);
                h();
                if (this.Z.o != null && (this.Z.o.t == 3 || this.Z.o.t == 2)) {
                    this.a.f().d().a(this, "当前话别付费失败", 0);
                    break;
                }
                ac();
                break;
                break;
            case 2:
                str = (String) message.obj;
                int i = message.arg1;
                boolean z2 = message.arg2 == 1;
                if (this.z != null && this.z.getVisibility() == 0) {
                    Drawable background = this.A.getBackground();
                    if (background instanceof AnimationDrawable) {
                        AnimationDrawable animationDrawable = (AnimationDrawable) background;
                        animationDrawable.setOneShot(true);
                        animationDrawable.stop();
                    }
                }
                if (this.B != null) {
                    this.B.setText(str);
                }
                if (this.z != null && this.z.getVisibility() == 8) {
                    this.z.setVisibility(0);
                }
                if (this.D != null && i == -6) {
                    this.D.setVisibility(0);
                    this.D.setClickable(true);
                }
                this.A.setBackgroundResource(b.a.a.a.a.a.d.comic_readpage_loading_error);
                if (com.qrcomic.util.g.a()) {
                    com.qrcomic.util.g.c(aW, com.qrcomic.util.g.d, " needQuit=" + z2 + " msg = " + message);
                }
                if (i == 1001 || i == 1004) {
                    this.a.f().b().e(this.Z.n, this);
                }
                if (z2) {
                    d(str + "，正在退出…");
                    break;
                }
                break;
            case 4:
                boolean z3;
                if (message.arg1 == 1) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (!z3) {
                    E();
                    break;
                }
                a("需要购买", -4, false);
                a(this.Z.o, 0);
                break;
            case 6:
                try {
                    Integer num;
                    Integer valueOf = Integer.valueOf(1001);
                    String string = getResources().getString(b.a.a.a.a.a.g.comic_error);
                    if (message.obj instanceof Object[]) {
                        Object[] objArr;
                        Object[] objArr2 = (Object[]) message.obj;
                        if (objArr2.length < 2 || !(objArr2[1] instanceof Object[])) {
                            objArr = objArr2;
                        } else {
                            objArr = (Object[]) objArr2[1];
                        }
                        if (objArr.length > 0 && (objArr[0] instanceof Integer)) {
                            valueOf = (Integer) objArr[0];
                        }
                        if (objArr.length > 1 && (objArr[1] instanceof String)) {
                            string = String.valueOf(objArr[1]);
                        }
                        if (objArr.length > 2 && (objArr[2] instanceof Boolean)) {
                            z = ((Boolean) objArr[2]).booleanValue();
                            str = string;
                            num = valueOf;
                            a(str, num.intValue(), z);
                            break;
                        }
                    }
                    str = string;
                    num = valueOf;
                    a(str, num.intValue(), z);
                } catch (Exception e) {
                    e.printStackTrace();
                    break;
                }
                break;
            case 7:
                ((f) message.obj).b();
                break;
            case 8:
                try {
                    ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService("connectivity");
                    State state = connectivityManager.getNetworkInfo(1).getState();
                    State state2 = connectivityManager.getNetworkInfo(0).getState();
                    if (!(state == null || state2 == null || State.CONNECTED == state || State.CONNECTED != state2)) {
                        this.Z.I = true;
                        com.qrcomic.manager.b.a().a(getResources().getString(b.a.a.a.a.a.g.mobile_net), 2);
                        break;
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                    break;
                }
            case 9:
                b((String) message.obj);
                break;
            case 10:
                a(message.obj);
                break;
            case 11:
                ac();
                break;
            case 13:
                com.qrcomic.manager.b.a().a("分享失败", 2);
                break;
            case 14:
                com.qrcomic.manager.b.a().a("分享成功", com.qrcomic.a.k.a);
                break;
            case 15:
                com.qrcomic.manager.b.a().a("取消分享", 2);
                break;
        }
        return true;
    }

    public void K() {
        com.qrcomic.manager.b.a().a(getResources().getString(b.a.a.a.a.a.g.the_begin_msg), com.qrcomic.a.k.a);
    }

    public void L() {
        if (this.Z != null && this.Z.i != null) {
            if (this.Z.i.e == 1) {
                com.qrcomic.manager.b.a().a(getResources().getString(b.a.a.a.a.a.g.waite_continue), com.qrcomic.a.k.a);
            } else if (this.Z.i.e == 2) {
                com.qrcomic.manager.b.a().a(getResources().getString(b.a.a.a.a.a.g.comic_finished), com.qrcomic.a.k.a);
            } else {
                com.qrcomic.manager.b.a().a(getResources().getString(b.a.a.a.a.a.g.the_end_msg), com.qrcomic.a.k.a);
            }
        }
    }

    protected void b(com.qrcomic.entity.f fVar, int i) {
        int i2;
        if (i == 0) {
            i2 = 3;
        } else if (i == 1) {
            i2 = 2;
        } else {
            E();
            return;
        }
        if (fVar.t != i2) {
            fVar.t = i2;
            if (this.ak != null && this.ak.getVisibility() == 0) {
                ae();
            } else if (this.al != null && this.al.getVisibility() == 0 && this.Z.o.b.equals(fVar.b) && (this.ac instanceof QRComicReadingVerticalActivity)) {
                ((QRComicReadingVerticalActivity) this.ac).aa();
            }
        }
        E();
    }

    public void c(com.qrcomic.entity.f fVar, int i) {
        if (fVar == null) {
            return;
        }
        if (fVar.b.equals(this.Z.j) && this.Z.k == 0) {
            if (this.e) {
                getIntent().putExtra("key_pay_ressult", 0);
                setResult(4098, getIntent());
            }
            if (this.aa == null || fVar.f == 0 || !TextUtils.equals(this.Z.n, this.aa.n)) {
                finish();
                return;
            }
            a(this, this.aa.n, this.aa.D, this.aa.E, this.aa.B, 0, this.aa.Q, true, this.aa.y, false, getIntent().getIntExtra("key_back_to_root_activity", 0));
            this.aa = null;
            return;
        }
        if (this.Z.N != null) {
            this.Z.N.b();
        }
        if (fVar.b.equals(this.Z.o.b)) {
            b(fVar, i);
        } else {
            b(fVar, i);
        }
    }

    private void ae() {
        if (this.ak != null && this.ak.getVisibility() == 0) {
            int childCount = this.ak.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = this.ak.getChildAt(i);
                if (childAt.getTag() instanceof com.qrcomic.widget.reader.c.c) {
                    com.qrcomic.widget.reader.c.c cVar = (com.qrcomic.widget.reader.c.c) childAt.getTag();
                    if (cVar.d != null) {
                        ComicSectionPicInfo comicSectionPicInfo = cVar.d;
                        if (comicSectionPicInfo.mComicRecommendPageInfo != null && cVar.f != null && this.aj != null) {
                            OnClickListener onClickListener;
                            if (this.ak.getAdapter() instanceof OnClickListener) {
                                onClickListener = (OnClickListener) this.ak.getAdapter();
                            } else {
                                onClickListener = null;
                            }
                            this.aj.a(cVar.f, comicSectionPicInfo.mComicRecommendPageInfo, onClickListener);
                        } else if (!(cVar.a == null || cVar.b == null)) {
                            cVar.a.setVisibility(8);
                            cVar.b.setVisibility(8);
                            LayoutParams layoutParams = new FrameLayout.LayoutParams(this.as, comicSectionPicInfo.dstHeight);
                            if (comicSectionPicInfo.bitmap == null || comicSectionPicInfo.bitmap.isRecycled()) {
                                CharSequence charSequence = "第" + (comicSectionPicInfo.index + 1) + "页";
                                CharSequence charSequence2 = "图片加载中";
                                com.qrcomic.entity.f b = this.Z.b(comicSectionPicInfo.sectionId);
                                if (b != null) {
                                    if (b.t == 1) {
                                        charSequence = "正在购买" + b.c;
                                    } else if (b.t == 2) {
                                        charSequence = b.c + "购买失败";
                                        charSequence2 = "付费失败, 重新购买";
                                    } else if (b.t == 3) {
                                        charSequence = b.c + "购买已取消";
                                        charSequence2 = "付费失败, 重新购买";
                                    } else if (comicSectionPicInfo.mState == 1) {
                                        charSequence2 = "加载失败, 点击重试";
                                    }
                                } else if (com.qrcomic.util.g.a()) {
                                    com.qrcomic.util.g.a(aW, com.qrcomic.util.g.d, "getView section = null, sectionId=" + comicSectionPicInfo.sectionId + ", comicId = " + this.Z.n);
                                }
                                cVar.b.setLayoutParams(layoutParams);
                                cVar.b.setMainText(charSequence);
                                cVar.b.setSubText(charSequence2);
                                cVar.b.setVisibility(0);
                            } else {
                                cVar.a.setVisibility(0);
                                cVar.a.setLayoutParams(layoutParams);
                                a(comicSectionPicInfo, null, cVar.a);
                            }
                        }
                    }
                }
            }
        }
    }

    public void d(com.qrcomic.entity.f fVar, int i) {
        if (fVar != null) {
            this.Z.A = com.qrcomic.util.h.a.b(this.Z.n, this.a.a());
            fVar.t = 0;
            if (!this.Z.y.contains(fVar.b)) {
                this.Z.y.add(fVar.b);
            }
            if (!fVar.b.equals(this.Z.j)) {
                if (i == 1) {
                    c e = c.e().b().c().a().e();
                    if (fVar.f == this.Z.E - 1) {
                        this.Z.a(e, new 15(this));
                    } else if (fVar.f == this.Z.E + 1) {
                        this.Z.b(e, new 16(this));
                    } else if (fVar.f == this.Z.E) {
                        if (this.ak != null && this.ak.getVisibility() == 0 && this.aj != null) {
                            this.aj.a(this.Z.o.b, this.Z.C);
                        } else if (this.al != null && this.al.getVisibility() == 0) {
                            ((QRComicReadingVerticalActivity) this.ac).aa();
                        }
                        ac();
                    }
                } else if (i == 0) {
                    if (this.ak != null && this.ak.getVisibility() == 0 && this.aj != null) {
                        this.aj.a();
                    } else if (this.al != null && this.al.getVisibility() == 0) {
                        ((QRComicReadingVerticalActivity) this.ac).aa();
                    }
                    ac();
                }
                E();
            } else if (this.Z.P != null) {
                this.Z.P.c(this.Z);
            } else {
                com.qrcomic.activity.reader.b.e.a(3, this.Z).a(null);
            }
        } else if (com.qrcomic.util.g.a()) {
            com.qrcomic.util.g.a(aW, com.qrcomic.util.g.d, "handleSectionPayedSuccess section=null");
        }
    }

    public void a(com.qrcomic.entity.f fVar) {
        if (fVar != null && this.Z.J && !this.Z.I && !this.Z.c(fVar)) {
            this.an.sendEmptyMessage(8);
        }
    }

    public void N() {
        if (this.ak != null && this.aj != null) {
            this.aj.a(this.Z.r);
            this.ak.setAdapter(null);
            this.aj.a(this.Z.o.b, this.Z.r.size() - 1);
        }
    }

    public void update(Observable observable, Object obj) {
        if (obj != null) {
            if (com.qrcomic.util.g.a()) {
                com.qrcomic.util.g.b(aW, com.qrcomic.util.g.d, "QRComicReadingBaseActivity update data is null");
            }
            Message.obtain(this.an, 10, obj).sendToTarget();
        }
    }

    protected void a(Object obj) {
        int i = -6;
        boolean z = true;
        int i2 = 0;
        if (obj instanceof Object[]) {
            Object[] objArr = (Object[]) obj;
            Object[] objArr2 = (Object[]) objArr[1];
            int intValue;
            switch (((Integer) objArr[0]).intValue()) {
                case 0:
                    if (this.ak != null && this.aj != null) {
                        this.aj.c((List) objArr2[0]);
                        return;
                    }
                    return;
                case 1:
                    Message.obtain(this.an, 4, ((Integer) objArr2[1]).intValue(), 0).sendToTarget();
                    if (objArr2[0] != null) {
                        ((f) objArr2[0]).c();
                        return;
                    }
                    return;
                case 2:
                    c cVar;
                    f fVar;
                    i = ((Integer) objArr2[0]).intValue();
                    if (objArr2[1] != null) {
                        cVar = (c) objArr2[1];
                    } else {
                        cVar = null;
                    }
                    if (objArr2[2] != null) {
                        fVar = (f) objArr2[2];
                    } else {
                        fVar = null;
                    }
                    if (((Boolean) objArr2[3]).booleanValue()) {
                        N();
                    }
                    a(i, cVar, fVar);
                    return;
                case 3:
                    if (objArr2[0] != null) {
                        ((f) objArr2[0]).b();
                    }
                    E();
                    if (objArr2.length <= 1) {
                        return;
                    }
                    if (((Integer) objArr2[1]).intValue() == 0) {
                        K();
                        return;
                    } else {
                        L();
                        return;
                    }
                case 4:
                    if (((Integer) objArr2[0]).intValue() == 0) {
                        if (!(this.ak == null || this.aj == null)) {
                            this.aj.c(this.Z.s);
                        }
                    } else if (!(this.ak == null || this.aj == null)) {
                        this.aj.b(this.Z.t);
                    }
                    if (objArr2.length >= 2) {
                        if (objArr2[1] != null) {
                            i2 = ((Boolean) objArr2[1]).booleanValue();
                        }
                        if (i2 == 0) {
                            return;
                        }
                        if (this.ak != null && this.ak.getVisibility() == 0 && this.aj != null) {
                            if (com.qrcomic.util.g.a()) {
                                com.qrcomic.util.g.a(aW, com.qrcomic.util.g.d, "预加载后,主动下载图片 - - - good");
                            }
                            this.aj.a();
                            return;
                        } else if (this.al != null && this.al.getVisibility() == 0 && (this instanceof QRComicReadingVerticalActivity) && this.Z.r != null && this.Z.C >= 0 && this.Z.C < this.Z.r.size()) {
                            ((QRComicReadingVerticalActivity) this).a((ComicSectionPicInfo) this.Z.r.get(this.Z.C));
                            return;
                        } else {
                            return;
                        }
                    }
                    return;
                case 5:
                    this.an.obtainMessage(6, objArr2).sendToTarget();
                    return;
                case 6:
                    if (objArr2[0] != null) {
                        ComicSectionPicInfo comicSectionPicInfo = (ComicSectionPicInfo) objArr2[0];
                        if (com.qrcomic.util.g.a()) {
                            com.qrcomic.util.g.a(aW, com.qrcomic.util.g.d, "comic image fetch error: picId:" + comicSectionPicInfo.picId + ",sectionId:" + comicSectionPicInfo.sectionId + ", comicId:" + comicSectionPicInfo.comicId);
                            return;
                        }
                        return;
                    }
                    return;
                case 7:
                    this.an.sendEmptyMessage(1);
                    return;
                case 8:
                    intValue = ((Integer) objArr2[0]).intValue();
                    if (intValue != 1 || objArr2.length < 2) {
                        if (intValue == 0) {
                            K();
                        } else {
                            L();
                        }
                    } else if (((Boolean) objArr2[1]).booleanValue()) {
                        a(1);
                        L();
                    }
                    E();
                    return;
                case 10:
                    A();
                    return;
                case 11:
                    a((com.qrcomic.entity.f) objArr2[1], ((Integer) objArr2[0]).intValue() == 1 ? 2 : 1);
                    if (this.Z.H == 1) {
                        this.an.postDelayed(new 17(this), 1000);
                    }
                    if (((Boolean) objArr2[2]).booleanValue()) {
                        N();
                        return;
                    }
                    return;
                case 12:
                    com.qrcomic.manager.b.a().b().f().d().a(this, "未知错误", 0);
                    return;
                case 14:
                    if (objArr2[0] != null) {
                        Throwable th = (Throwable) objArr2[0];
                        String str = "";
                        if (th instanceof QueryUserBuyInfoException) {
                            str = "出错啦，请重新加载";
                            z = false;
                            intValue = 0;
                        } else {
                            if (th instanceof QueryComicAndSectionListInfoException) {
                                str = "出错啦，请重新加载";
                                QueryComicAndSectionListInfoException queryComicAndSectionListInfoException = (QueryComicAndSectionListInfoException) th;
                                if (queryComicAndSectionListInfoException.mInfo instanceof QRComicReadPageDirector.a) {
                                    com.qrcomic.entity.a aVar = ((QRComicReadPageDirector.a) queryComicAndSectionListInfoException.mInfo).a;
                                    if (aVar != null) {
                                        if (aVar.y == 1004) {
                                            intValue = aVar.y;
                                            str = aVar.x;
                                        } else if (aVar.y == 1005) {
                                            intValue = aVar.y;
                                            str = aVar.x;
                                            z = false;
                                        } else if (aVar.y == 1001) {
                                            z = false;
                                            str = aVar.x;
                                            intValue = -6;
                                        }
                                    }
                                }
                            } else if (th instanceof QuerySectionPicInfoException) {
                                str = "出错啦，请重新加载";
                                z = false;
                                intValue = 0;
                            } else if (th instanceof QuerySectionListInfoException) {
                                str = "出错啦，请重新加载";
                                z = false;
                                intValue = 0;
                            }
                            z = false;
                            intValue = 0;
                        }
                        if (!com.qrcomic.util.f.a(this.a.b())) {
                            str = "网络异常，请重新加载";
                        }
                        if (com.qrcomic.util.g.a()) {
                            StringBuilder stringBuilder = new StringBuilder();
                            StackTraceElement[] stackTrace = th.getStackTrace();
                            int length = stackTrace.length;
                            while (i2 < length) {
                                stringBuilder.append(stackTrace[i2].toString()).append("\n");
                                i2++;
                            }
                            com.qrcomic.util.g.c(aW, com.qrcomic.util.g.d, " 漫画出错了，需要退出 出现了异常 " + stringBuilder);
                        }
                        if (intValue != 0) {
                            i = intValue;
                        }
                        a(str, i, z);
                        return;
                    }
                    return;
                case 15:
                    if (this.Z.L != null) {
                        a(2);
                        return;
                    }
                    return;
                case 16:
                    if (this.ak != null) {
                        QRComicScrollReaderListView qRComicScrollReaderListView = this.ak;
                        if (this.Z.i.m != 1) {
                            i2 = 10;
                        }
                        qRComicScrollReaderListView.setDividerHeight(i2);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    private void d(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.bb.a(str);
        }
        if (!isFinishing()) {
            if (!this.bb.isShowing()) {
                this.bb.show();
            }
            this.an.postDelayed(new 18(this), 1000);
        }
    }

    public boolean O() {
        if (this.Z == null || this.Z.u == null || this.aF != 0 || this.Z.J || this.Z.E + 1 < this.Z.u.size()) {
            return false;
        }
        return true;
    }

    public boolean P() {
        if (this.Z == null || this.Z.r == null || this.Z.u == null) {
            return false;
        }
        if (this.aF != 1 || this.Z.J || this.Z.C != this.Z.r.size() || this.Z.E + 1 < this.Z.u.size()) {
            return false;
        }
        return true;
    }

    protected void a(int i) {
    }

    public void a(TextView textView, ImageView imageView, RecommendComicInfo recommendComicInfo, int i) {
        if (textView != null && imageView != null && recommendComicInfo != null) {
            recommendComicInfo.g = i + 1;
            imageView.setTag(recommendComicInfo);
            if (TextUtils.isEmpty(recommendComicInfo.b)) {
                textView.setText("");
            } else {
                textView.setText(recommendComicInfo.b);
            }
            if (this.bl == null) {
                this.bl = new ColorDrawable(-1513240);
            }
            if (TextUtils.isEmpty(recommendComicInfo.c)) {
                imageView.setImageDrawable(this.bl);
                return;
            }
            try {
                if (this.bm == null) {
                    this.bm = new ColorDrawable(SkinEngine.TYPE_FILE);
                }
                if (this.bn == null) {
                    this.bn = new ColorDrawable(-2130706433);
                }
                if (i == 0) {
                    if (this.bo == null) {
                        this.bo = new StateListDrawable();
                        this.bo.addState(new int[]{16842919, 16842910}, this.bn);
                        this.bo.addState(new int[0], this.bm);
                    }
                    imageView.setImageDrawable(this.bo);
                } else if (i == 1) {
                    if (this.bp == null) {
                        this.bp = new StateListDrawable();
                        this.bp.addState(new int[]{16842919, 16842910}, this.bn);
                        this.bp.addState(new int[0], this.bm);
                    }
                    imageView.setImageDrawable(this.bp);
                } else if (i == 2) {
                    if (this.bq == null) {
                        this.bq = new StateListDrawable();
                        this.bq.addState(new int[]{16842919, 16842910}, this.bn);
                        this.bq.addState(new int[0], this.bm);
                    }
                    imageView.setImageDrawable(this.bq);
                } else {
                    Drawable stateListDrawable = new StateListDrawable();
                    stateListDrawable.addState(new int[]{16842919, 16842910}, this.bn);
                    stateListDrawable.addState(new int[0], this.bm);
                    imageView.setImageDrawable(stateListDrawable);
                }
            } catch (Exception e) {
                imageView.setImageDrawable(this.bl);
                e.printStackTrace();
            }
        }
    }

    private void a(int i, c cVar, f fVar) {
        if (!isFinishing()) {
            if (!(!cVar.b() || this.ak == null || this.aj == null)) {
                if (i == 0) {
                    this.aj.c(this.Z.s);
                } else {
                    this.aj.b(this.Z.t);
                }
            }
            if (cVar.c()) {
                this.Z.c(i);
                ac();
                Message.obtain(this.an, 12).sendToTarget();
            }
            if (cVar.a()) {
                y();
            }
            if (fVar != null) {
                fVar.a();
            }
            this.Z.k();
        }
    }

    private void c(int i) {
        c e = c.e().c().e();
        if (i == 0) {
            this.Z.a(e, new 19(this));
        } else {
            this.Z.b(e, new 20(this));
        }
    }

    public void a(Intent intent, com.qrcomic.entity.f fVar) {
        intent.getIntExtra("comicExtraCode", 1);
        com.qrcomic.manager.b.a().a("购买成功", com.qrcomic.a.k.a);
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (this.aL) {
            a(getWindow().getDecorView());
        } else if (!q()) {
            if (this.o == null || !this.o.a()) {
                b(getWindow().getDecorView());
            } else {
                return;
            }
        } else {
            return;
        }
        this.bd = true;
    }

    private void af() {
        this.Z.A = com.qrcomic.util.h.a.b(this.Z.n, this.a.a());
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        boolean z = false;
        super.onActivityResult(i, i2, intent);
        if (this.ak != null) {
            this.ak.setTouchEventEnabled(true);
        }
        List<String> list;
        if (i == 4097) {
            if (intent != null) {
                this.ai = false;
                com.qrcomic.entity.f b;
                int intExtra;
                int intExtra2;
                if (i2 == 4098) {
                    if (this.am != null) {
                        this.am.dismiss();
                        this.ai = false;
                    }
                    b = this.Z.b(intent.getExtras().getString("key_section_id"));
                    if (b != null) {
                        b.t = 0;
                        int intExtra3 = intent.getIntExtra("key_pay_ressult", 0);
                        if (intExtra3 == 0 || intExtra3 == 1) {
                            if (TextUtils.isEmpty(intent.getExtras().getString("key_pay_error_msg", ""))) {
                                c(b, intExtra3);
                            } else {
                                c(b, intExtra3);
                            }
                            return;
                        } else if (intExtra3 == 2) {
                            boolean z2;
                            intExtra = intent.getIntExtra("comicExtraCode", 1);
                            intExtra2 = intent.getIntExtra("auto_buy_new", this.Z.A ? 0 : 1);
                            if (intExtra2 == 1) {
                                z2 = true;
                            } else {
                                z2 = false;
                            }
                            com.qrcomic.util.h.a.a(z2, this.Z.n, this.a.a());
                            a aVar = this.Z;
                            if (intExtra2 == 0) {
                                z = true;
                            }
                            aVar.A = z;
                            if (com.qrcomic.util.g.a()) {
                                com.qrcomic.util.g.a(aW, com.qrcomic.util.g.d, " 购买成功，花费 的数据为 点券=" + intent.getExtras().getInt("section_cost"));
                            }
                            if (intent.getExtras().getInt("key_buy_type", this.Z.i.g) == 1) {
                                list = (List) intent.getSerializableExtra("sectionIdList");
                                if (list != null) {
                                    for (String str : list) {
                                        if (!this.Z.y.contains(str)) {
                                            this.Z.y.add(str);
                                        }
                                    }
                                    j.a().a(new 22(this, list));
                                }
                            } else {
                                this.Z.z = true;
                                j.a().a(new 24(this));
                            }
                            d(b, intExtra);
                            a(intent, b);
                            return;
                        } else if (intExtra3 == 4 || intExtra3 == 3) {
                            if (com.qrcomic.util.g.a()) {
                                com.qrcomic.util.g.a(aW, com.qrcomic.util.g.d, " 用户购买状态需要刷新了。。。。");
                            }
                            r();
                            this.v.i(this.Z.n, this.a.a());
                            return;
                        } else {
                            return;
                        }
                    }
                    return;
                }
                list = (List) intent.getExtras().getSerializable("sectionIdList");
                if (list == null || list.size() <= 0) {
                    b = null;
                } else {
                    b = this.Z.b((String) list.get(0));
                }
                if (b != null) {
                    b.t = 0;
                    if (i2 == 0 || i2 == 1) {
                        Object string = intent.getExtras().getString("key_pay_error_msg", "");
                        if (!TextUtils.isEmpty(string)) {
                            this.a.f().d().a(this, string, 0);
                        }
                        c(b, i2);
                    } else if (i2 == 2) {
                        boolean z3;
                        intExtra = intent.getExtras().getInt("comicExtraCode", 1);
                        int i3 = intent.getExtras().getInt("auto_buy_new", this.Z.A ? 0 : 1);
                        int i4 = intent.getExtras().getInt("key_buy_type", this.Z.i.g);
                        if (i3 == 0) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        com.qrcomic.util.h.a.a(z3, this.Z.n, this.a.a());
                        a aVar2 = this.Z;
                        if (i3 == 0) {
                            z = true;
                        }
                        aVar2.A = z;
                        if (this.e) {
                            getIntent().putExtra("key_pay_ressult", i2);
                            getIntent().putExtra("comicExtraCode", intExtra);
                            getIntent().putExtra("auto_buy_new", i3);
                            getIntent().putExtra("sectionIdList", (Serializable) list);
                            setResult(4098, getIntent());
                            finish();
                            return;
                        }
                        if (i4 == 1) {
                            for (String str2 : list) {
                                if (!this.Z.y.contains(str2)) {
                                    this.Z.y.add(str2);
                                }
                            }
                            j.a().a(new 25(this, list));
                        } else {
                            this.Z.z = true;
                            j.a().a(new 26(this));
                        }
                        d(b, intExtra);
                        a(intent, b);
                    } else if (i2 != 4 && i2 != 3) {
                    } else {
                        if (this.e) {
                            intExtra2 = intent.getExtras().getInt("comicExtraCode", 1);
                            int i5 = intent.getExtras().getInt("auto_buy_new");
                            getIntent().putExtra("key_pay_ressult", i2);
                            getIntent().putExtra("comicExtraCode", intExtra2);
                            getIntent().putExtra("auto_buy_new", i5);
                            getIntent().putExtra("sectionIdList", (Serializable) list);
                            setResult(4098, getIntent());
                            finish();
                            return;
                        }
                        if (com.qrcomic.util.g.a()) {
                            com.qrcomic.util.g.a(aW, com.qrcomic.util.g.d, " 用户购买状态需要刷新了。。。。");
                        }
                        this.v.i(this.Z.n, this.a.a());
                        r();
                    }
                }
            }
        } else if (i != 4100 && i == 4101 && intent != null) {
            list = (List) intent.getExtras().getSerializable("sectionIdList");
            if (intent.getIntExtra("key_buy_type", this.Z.i.g) == 1) {
                for (String str3 : list) {
                    if (!this.Z.y.contains(str3)) {
                        this.Z.y.add(str3);
                        com.qrcomic.entity.f b2 = this.Z.b(str3);
                        if (b2 != null) {
                            b2.t = 0;
                        }
                    }
                }
                return;
            }
            this.Z.z = true;
        }
    }

    protected void onResume() {
        super.onResume();
        this.af = System.currentTimeMillis();
        if (this.Z != null) {
            a(this.Z.n);
        }
        Z();
        this.ag = 1;
        if (this.aH != null) {
            this.aH.a();
        }
        if (this.Z != null) {
            String str = this.Z.j;
            if (!(this.Z.o == null || this.Z.o.b == null)) {
                str = this.Z.o.b;
            }
            a(this.Z.n, str);
        }
        if (this.a != null) {
            ag();
        }
        if (this.a != null) {
            this.a.e().b();
        }
    }

    protected void onStop() {
        super.onStop();
        if (this.a != null) {
            this.a.e().a();
        }
    }

    private void ag() {
        if (this.ar) {
            this.an.removeCallbacks(this.bh);
            this.ar = false;
        }
        if (this.n != null) {
            this.n.a();
        }
    }

    protected void onPause() {
        super.onPause();
        if (this.aH != null) {
            this.aH.b();
        }
        if (this.n != null) {
            long bookReadingTimeAndRest = this.n.getBookReadingTimeAndRest();
            if (!(this.Z == null || this.a == null || this.a.f() == null || bookReadingTimeAndRest <= 5000 || bookReadingTimeAndRest >= 3600000)) {
                this.a.f().c().a(this.Z.n, bookReadingTimeAndRest);
            }
            this.n.b();
        }
        a(U());
        if (isFinishing()) {
            if (this.a != null) {
                j.b(new 28(this));
            }
        } else if (this.a != null) {
            Intent intent = getIntent();
            intent.putExtra("comicID", getIntent().getStringExtra("key_comic_id"));
            j.b(new 27(this, intent));
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        this.an.removeCallbacksAndMessages(null);
        this.aT = null;
        if (!(this.Z == null || this.Z.N == null)) {
            this.Z.N.a();
        }
        if (this.a != null) {
            this.a.e().deleteObserver(this.bj);
            this.a.e().g();
        }
        if (this.Z != null) {
            this.Z.deleteObserver(this);
            this.Z.m();
        }
        if (this.f || this.e || !this.g) {
        }
        if (this.an != null) {
            this.an.removeCallbacksAndMessages(null);
        }
        if (this.Z == null || this.Z.F < 0 || this.Z.G < 0 || this.Z.G >= this.Z.F) {
        }
        if (this.ae > 0) {
        }
        if (this.a != null) {
            this.a.a(this.Z.n, false);
        }
        try {
            unregisterReceiver(this.aU);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        try {
            if (this.ba != null) {
                this.ba.a(this);
            }
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
        }
        if (this.aZ != null && this.aZ.isShowing()) {
            this.aZ.dismiss();
        }
        if (this.a != null) {
            this.a.b(this.bi);
        }
        if (com.qrcomic.util.g.a() && this.a != null) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("开始遍历多余的观察者\n");
            List<Object> list = this.a.g;
            stringBuffer.append(" app.bgObservers.size = ").append(list.size()).append("\n");
            for (Object toJson : list) {
                stringBuffer.append(" bg item = ").append(new Gson().toJson(toJson)).append("\n");
            }
            stringBuffer.append("------------------------\n");
            list = this.a.f;
            stringBuffer.append(" app.uiObservers.size = ").append(list.size()).append("\n");
            for (Object toJson2 : list) {
                stringBuffer.append(" ui item = ").append(new Gson().toJson(toJson2)).append("\n");
            }
            com.qrcomic.util.g.c(aW, com.qrcomic.util.g.d, stringBuffer.toString());
        }
        if (this.a != null && this.a.f() != null && this.a.f().f() != null && getIntent().getExtras().getInt("key_back_to_root_activity") == 1) {
            this.a.f().f().a(this);
        }
    }

    protected void Q() {
    }

    public void a(MotionEvent motionEvent) {
        if (this != null && this.Z != null && this.Z.o == null) {
        }
    }

    protected boolean a(String str, String str2) {
        if (this.ah == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        this.ah.a = str;
        this.ah.b = str2;
        this.ah.e = 1;
        this.ah.c = com.qrcomic.util.e.b();
        return true;
    }

    protected boolean b(String str, String str2) {
        if (TextUtils.isEmpty(this.ah.b)) {
            a(this.Z.n, this.Z.D);
        }
        if (this.ah == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        if (str.equals(this.ah.a) && str2.equals(this.ah.b)) {
            c cVar = this.ah;
            cVar.e++;
        } else {
            this.ah.d = com.qrcomic.util.e.b();
            if (this.a != null) {
                this.ah.a = str;
                this.ah.b = str2;
                this.ah.e = 1;
                this.ah.c = com.qrcomic.util.e.b();
            } else {
                this.ah.a = str;
                this.ah.b = str2;
                this.ah.e = 1;
                this.ah.c = com.qrcomic.util.e.b();
            }
        }
        return true;
    }

    public void b(int i) {
        boolean z = true;
        this.aG = i;
        com.qrcomic.c.b a = this.a.f().a();
        if (i != 1) {
            z = false;
        }
        a.a(this, z);
        R();
        if (this.Z.o != null) {
            u();
        } else {
            u();
        }
    }

    public void R() {
        this.aG = this.a.f().a().a() ? 1 : 0;
        if (this.aG == 0) {
            this.W.setVisibility(8);
        } else {
            this.W.setVisibility(0);
        }
        if (com.qrcomic.util.h.c.b()) {
            com.qrcomic.util.c.c.b(this);
        } else {
            com.qrcomic.util.c.c.a(this, (int) ((float) com.qrcomic.util.h.c.a()));
        }
    }

    public void S() {
        this.a.f().a().a(this.ac);
    }

    private void ah() {
        boolean z = false;
        if (!isFinishing()) {
            if (this.aY == null) {
                View inflate = LayoutInflater.from(this).inflate(b.a.a.a.a.a.f.qr_comic_reader_popmenu, null);
                OnClickListener 29 = new 29(this, inflate);
                boolean c = c(this.Z.n);
                View findViewById = inflate.findViewById(e.secret_line);
                View findViewById2 = inflate.findViewById(e.secret_container);
                if (c) {
                    findViewById2.setVisibility(0);
                    findViewById.setVisibility(0);
                } else {
                    findViewById2.setVisibility(8);
                    findViewById.setVisibility(8);
                }
                findViewById2.setOnClickListener(29);
                inflate.findViewById(e.reader_detail).setOnClickListener(29);
                inflate.findViewById(e.reader_share).setOnClickListener(29);
                this.aY = new PopupWindow(inflate, -2, -2);
                if (this.aG == 1) {
                    z = true;
                }
                b(z);
                this.aY.setFocusable(true);
            }
            if (!this.aY.isShowing()) {
                int[] iArr = new int[2];
                this.r.getLocationInWindow(iArr);
                this.aY.showAtLocation(this.r, 53, com.qrcomic.util.c.a.a(this, 12), (int) ((((float) iArr[1]) + (16.0f * this.b)) + ((float) this.r.getHeight())));
            }
            if (this.Z != null && this.Z.o != null) {
            }
        }
    }

    private void c(View view) {
        com.qrcomic.c.c b = this.a.f().b();
        if (b == null) {
            return;
        }
        if (b.c(this.Z.n, this.ac)) {
            a(b, view.findViewById(e.reader_secret_state));
            return;
        }
        T();
        this.aZ = this.a.f().f().b(this, new 30(this, b, view), new 31(this));
        if (this.aZ != null) {
            this.aZ.show();
            this.a.f().b().d(this.Z.n, this.ac);
        }
    }

    private void a(com.qrcomic.c.c cVar, View view) {
        cVar.a(this.Z.n, !cVar.b(this.Z.n, this.a.b()), this.an, this, new 32(this, view));
    }

    protected void T() {
        if (this.aZ != null && this.aZ.isShowing() && !isFinishing()) {
            this.aZ.dismiss();
        }
    }

    public boolean c(String str) {
        return this.a.f().b().a(str, this.ac);
    }

    public boolean U() {
        if (this.Z == null) {
            return false;
        }
        return c(this.Z.n);
    }

    public void V() {
        if (this.Z == null || this.Z.i == null || this.Z.o == null) {
            com.qrcomic.manager.b.a().a(getResources().getString(b.a.a.a.a.a.g.reader_net_work_error_toast), 2);
        } else {
            this.a.f().f().a(this, this.Z.n, this.Z.E, 4100);
            w();
        }
        if (this.Z != null && this.Z.o == null) {
        }
    }

    protected com.qrcomic.c.b.a W() {
        if (this.Z == null) {
            return null;
        }
        return new 33(this);
    }

    protected void X() {
        if (this.a == null) {
            return;
        }
        if (this.Z == null || this.Z.i == null) {
            this.a.f().d().a(this, "漫画出错了...", 0);
            return;
        }
        Map hashMap = new HashMap();
        hashMap.put(s.ORIGIN, this.Z.i.a);
        this.a.f().c().a("event_F336", hashMap, getApplicationContext());
        this.a.f().f().a(this.Z.i, this);
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.Z != null) {
            bundle.putString("comicID", this.Z.n);
        }
    }

    @Deprecated
    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
    }

    public void a(ComicSectionPicInfo comicSectionPicInfo, ComicSectionPicInfo comicSectionPicInfo2, ImageView imageView) {
        if (imageView != null) {
            if (comicSectionPicInfo == null) {
                imageView.setImageDrawable(null);
            } else {
                imageView.setImageDrawable(new com.qrcomic.widget.reader.b(getResources(), comicSectionPicInfo.bitmap));
            }
        }
        a(comicSectionPicInfo);
        b(comicSectionPicInfo2);
    }

    private void a(ComicSectionPicInfo comicSectionPicInfo) {
        if (this.a != null && comicSectionPicInfo != null) {
            com.qrcomic.downloader.a.d.a aVar = new com.qrcomic.downloader.a.d.a(comicSectionPicInfo.picUrl, this.x.d.incrementAndGet(), i.f.incrementAndGet());
            aVar.a(comicSectionPicInfo);
            com.qqcomic.bitmaphelper.b bVar = (com.qqcomic.bitmaphelper.b) this.a.h.a(aVar);
            if (bVar != null) {
                synchronized (this.a.h) {
                    this.a.h.a(aVar, false);
                }
                synchronized (this.a.i) {
                    this.a.i.c(aVar, bVar);
                }
            }
            if (com.qrcomic.util.g.a()) {
                com.qrcomic.util.g.a("PIC_CACHE", com.qrcomic.util.g.d, " onComicPicUseOnView active" + this.a.i.b() + " size=" + ((this.a.i.a() / 1024) / 1024) + "MB  mem" + this.a.h.b() + " size=" + ((this.a.h.a() / 1024) / 1024) + "MB");
            }
        }
    }

    private void b(ComicSectionPicInfo comicSectionPicInfo) {
        if (this.a != null && comicSectionPicInfo != null) {
            com.qqcomic.bitmaphelper.b bVar;
            long min = Math.min(this.a.i.f()[0] - 1, this.a.h.f()[0] - 1);
            com.qrcomic.downloader.a.d.a aVar = new com.qrcomic.downloader.a.d.a(comicSectionPicInfo.picUrl, this.x.d.incrementAndGet(), i.f.incrementAndGet());
            aVar.a(comicSectionPicInfo);
            synchronized (this.a.i) {
                bVar = (com.qqcomic.bitmaphelper.b) this.a.i.a(aVar);
                this.a.i.a(aVar, false);
            }
            if (bVar != null) {
                synchronized (this.a.h) {
                    aVar.b = min;
                    this.a.h.c(aVar, bVar);
                }
            }
            if (com.qrcomic.util.g.a()) {
                com.qrcomic.util.g.a("PIC_CACHE", com.qrcomic.util.g.d, " onComicPicReleaseOnView active" + this.a.i.b() + "MB size=" + ((this.a.i.a() / 1024) / 1024) + "  mem" + this.a.h.b() + " size=" + ((this.a.h.a() / 1024) / 1024) + "MB");
            }
        }
    }
}
