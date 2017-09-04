package com.qq.reader.view;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.activity.ReaderPageActivity;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.db.handle.v;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.monitor.j;
import com.qq.reader.common.utils.aj;
import com.qq.reader.common.utils.ao;
import com.qq.reader.cservice.onlineread.OnlineTag;
import com.qq.reader.cservice.onlineread.e;
import com.qq.reader.module.bookchapter.online.g;
import com.qq.reader.module.readpage.ReaderTextPageView;
import com.qq.reader.module.readpage.z;
import com.tencent.feedback.proguard.R;
import qalsdk.o;

/* compiled from: ReaderSettingDialog */
public class ad extends BaseDialog {
    private RadioButton A;
    private RadioButton B;
    private RadioButton C;
    private RadioButton D;
    private RadioButton E;
    private RadioButton F;
    private RadioGroup G;
    private RadioGroup H;
    private RadioGroup I;
    private RadioGroup J;
    private RadioGroup K;
    private RadioGroup L;
    private RadioGroup M;
    private RadioGroup N;
    private RadioGroup O;
    private RadioGroup P;
    private RadioButton Q;
    private RadioButton R;
    private View S;
    private a T;
    private View U;
    private View V;
    private View W;
    private View X;
    private View Y;
    private View Z;
    ReaderPageActivity a;
    private e aa;
    private g ab;
    af b;
    public int c;
    public int d;
    public int e;
    Activity i;
    private RadioButton j;
    private RadioButton k;
    private RadioButton l;
    private RadioButton m;
    private boolean n;
    private LinearLayout o;
    private RadioButton p;
    private RadioButton q;
    private RadioButton r;
    private RadioButton s;
    private RadioButton t;
    private RadioButton u;
    private RadioButton v;
    private RadioButton w;
    private RadioButton x;
    private RadioButton y;
    private RadioButton z;

    /* compiled from: ReaderSettingDialog */
    public interface a {
        void a(int i);
    }

    public ad(Activity activity, e eVar, g gVar) {
        this(activity);
        this.aa = eVar;
        this.ab = gVar;
    }

    public ad(Activity activity) {
        this.a = null;
        this.b = null;
        this.n = true;
        this.c = 0;
        this.d = 3;
        this.e = 6;
        this.i = activity;
        if (this.f == null) {
            a(activity, null, R.layout.reader_setting_dialog, 0, false);
            LayoutParams attributes = this.f.getWindow().getAttributes();
            LayoutParams attributes2 = activity.getWindow().getAttributes();
            attributes.width = attributes2.width;
            attributes.height = attributes2.height;
            this.f.getWindow().setAttributes(attributes);
            this.f.setOnCancelListener(new OnCancelListener(this) {
                final /* synthetic */ ad a;

                {
                    this.a = r1;
                }

                public void onCancel(DialogInterface dialogInterface) {
                }
            });
            if (d.ao(activity.getApplicationContext())) {
                aj.a(this.f, true);
                if (!d.aq(e().getApplicationContext())) {
                    aj.b(this.f, true);
                }
            }
            ((TextView) this.f.findViewById(R.id.profile_header_title)).setText("更多设置");
            p();
            if (activity instanceof ReaderPageActivity) {
                this.a = (ReaderPageActivity) activity;
            }
            this.V = this.f.findViewById(R.id.reader_setting_key_part);
            this.U = this.f.findViewById(R.id.reader_setting_dialog_line_spacing);
            this.W = this.f.findViewById(R.id.reader_setting_dialog_middle_turnpage);
            this.X = this.f.findViewById(R.id.reader_setting_dialog_middle_doublepage_part);
            this.Y = this.f.findViewById(R.id.reader_setting_dialog_full_screen_part);
            this.Z = this.f.findViewById(R.id.reader_setting_dialog_slide2changeLight_part);
            q();
            s();
            o();
            this.n = aj.a(activity);
            if (this.n) {
                n();
            }
            m();
            r();
            l();
            k();
            j();
            i();
        }
    }

    private void i() {
        this.P = (RadioGroup) a(R.id.reader_setting_dialog_auto_buy_next_chapter);
        this.Q = (RadioButton) a(R.id.reader_setting_dialog_auto_buy_next_chapter_enable);
        this.R = (RadioButton) a(R.id.reader_setting_dialog_auto_buy_next_chapter_disable);
        this.S = a(R.id.reader_setting_dialog_auto_buy_layout);
        this.Q.setChecked(true);
        this.R.setChecked(false);
        this.P.setOnCheckedChangeListener(new OnCheckedChangeListener(this) {
            final /* synthetic */ ad a;

            {
                this.a = r1;
            }

            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                boolean z = true;
                switch (i) {
                    case R.id.reader_setting_dialog_auto_buy_next_chapter_enable:
                        i.a("event_B121", null, ReaderApplication.getApplicationImp());
                        break;
                    case R.id.reader_setting_dialog_auto_buy_next_chapter_disable:
                        z = false;
                        i.a("event_B120", null, ReaderApplication.getApplicationImp());
                        break;
                }
                if (this.a.aa != null && this.a.aa.g() != null) {
                    OnlineTag g = this.a.aa.g();
                    g.c(z);
                    v.b().b(g);
                }
            }
        });
    }

    private void j() {
        this.K = (RadioGroup) this.f.findViewById(R.id.reader_setting_dialog_middle_screen_protect);
        this.v = (RadioButton) this.f.findViewById(R.id.reader_setting_dialog_middle_screen_protect_1_min);
        this.w = (RadioButton) this.f.findViewById(R.id.reader_setting_dialog_middle_screen_protect_3_min);
        this.x = (RadioButton) this.f.findViewById(R.id.reader_setting_dialog_middle_screen_protect_5_min);
        this.y = (RadioButton) this.f.findViewById(R.id.reader_setting_dialog_middle_screen_protect_10_min);
        this.z = (RadioButton) this.f.findViewById(R.id.reader_setting_dialog_middle_screen_protect_none);
        B();
        this.K.setOnCheckedChangeListener(new OnCheckedChangeListener(this) {
            final /* synthetic */ ad a;

            {
                this.a = r1;
            }

            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int ai = d.ai(this.a.e().getApplicationContext());
                j.a(82, 1);
                ao.a(this.a.i.getWindow(), false);
                switch (i) {
                    case R.id.reader_setting_dialog_middle_screen_protect_1_min:
                        if (ai != 1) {
                            try {
                                d.r(this.a.e().getApplicationContext(), 1);
                                z.a(60000, this.a.a.getHandler(), this.a.a);
                                return;
                            } catch (Exception e) {
                                c.e("ReaderSettingDialog", e.getMessage());
                                return;
                            }
                        }
                        return;
                    case R.id.reader_setting_dialog_middle_screen_protect_3_min:
                        if (ai != 3) {
                            d.r(this.a.e().getApplicationContext(), 3);
                            try {
                                z.a(180000, this.a.a.getHandler(), this.a.a);
                                return;
                            } catch (Exception e2) {
                                c.e("ReaderSettingDialog", e2.getMessage());
                                return;
                            }
                        }
                        return;
                    case R.id.reader_setting_dialog_middle_screen_protect_5_min:
                        if (ai != 5) {
                            try {
                                d.r(this.a.e().getApplicationContext(), 5);
                                z.a(o.c, this.a.a.getHandler(), this.a.a);
                                return;
                            } catch (Exception e22) {
                                c.e("ReaderSettingDialog", e22.getMessage());
                                return;
                            }
                        }
                        return;
                    case R.id.reader_setting_dialog_middle_screen_protect_10_min:
                        if (ai != 10) {
                            try {
                                d.r(this.a.e().getApplicationContext(), 10);
                                z.a(600000, this.a.a.getHandler(), this.a.a);
                                return;
                            } catch (Exception e222) {
                                c.e("ReaderSettingDialog", e222.getMessage());
                                return;
                            }
                        }
                        return;
                    case R.id.reader_setting_dialog_middle_screen_protect_none:
                        if (ai != -1) {
                            d.r(this.a.e().getApplicationContext(), -1);
                            z.b(this.a.a.getHandler(), this.a.a);
                            ao.a(this.a.i.getWindow(), true);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        });
    }

    private void k() {
        this.I = (RadioGroup) this.f.findViewById(R.id.reader_setting_volume_key);
        this.t = (RadioButton) this.f.findViewById(R.id.reader_setting_volume_key_open);
        this.u = (RadioButton) this.f.findViewById(R.id.reader_setting_volume_key_close);
        A();
        this.I.setOnCheckedChangeListener(new OnCheckedChangeListener(this) {
            final /* synthetic */ ad a;

            {
                this.a = r1;
            }

            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                j.a(79, 1);
                switch (i) {
                    case R.id.reader_setting_volume_key_open:
                        d.o(this.a.e().getApplicationContext(), true);
                        return;
                    case R.id.reader_setting_volume_key_close:
                        d.o(this.a.e().getApplicationContext(), false);
                        return;
                    default:
                        return;
                }
            }
        });
    }

    private void l() {
        this.J = (RadioGroup) this.f.findViewById(R.id.reader_setting_press_left);
        this.r = (RadioButton) this.f.findViewById(R.id.reader_setting_press_left_open);
        this.s = (RadioButton) this.f.findViewById(R.id.reader_setting_press_left_close);
        z();
        this.J.setOnCheckedChangeListener(new OnCheckedChangeListener(this) {
            final /* synthetic */ ad a;

            {
                this.a = r1;
            }

            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                j.a(81, 1);
                switch (i) {
                    case R.id.reader_setting_press_left_open:
                        d.p(this.a.e().getApplicationContext(), true);
                        return;
                    case R.id.reader_setting_press_left_close:
                        d.p(this.a.e().getApplicationContext(), false);
                        return;
                    default:
                        return;
                }
            }
        });
    }

    private void m() {
        this.M = (RadioGroup) this.f.findViewById(R.id.reader_setting_dialog_full_screen);
        this.l = (RadioButton) this.f.findViewById(R.id.reader_setting_dialog_full_screen_open);
        this.m = (RadioButton) this.f.findViewById(R.id.reader_setting_dialog_full_screen_close);
        this.o = (LinearLayout) this.f.findViewById(R.id.reader_setting_dialog_show_navigation_part);
        x();
        this.M.setOnCheckedChangeListener(new OnCheckedChangeListener(this) {
            final /* synthetic */ ad a;

            {
                this.a = r1;
            }

            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                i.a("event_B40", null, this.a.i);
                switch (i) {
                    case R.id.reader_setting_dialog_full_screen_open:
                        d.q(this.a.e().getApplicationContext(), true);
                        aj.a(this.a.f, true);
                        if (!d.aq(this.a.e().getApplicationContext())) {
                            aj.b(this.a.f, true);
                        }
                        if (this.a.n) {
                            this.a.o.setVisibility(0);
                            this.a.y();
                            return;
                        }
                        return;
                    case R.id.reader_setting_dialog_full_screen_close:
                        d.q(this.a.e().getApplicationContext(), false);
                        aj.b(this.a.f, false);
                        aj.a(this.a.f, false);
                        aj.d(this.a.i);
                        if (this.a.n) {
                            this.a.o.setVisibility(8);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        });
    }

    private void n() {
        this.N = (RadioGroup) this.f.findViewById(R.id.reader_setting_dialog_show_navigation);
        this.p = (RadioButton) this.f.findViewById(R.id.reader_setting_dialog_show_navigation_open);
        this.q = (RadioButton) this.f.findViewById(R.id.reader_setting_dialog_show_navigation_close);
        this.N.setOnCheckedChangeListener(new OnCheckedChangeListener(this) {
            final /* synthetic */ ad a;

            {
                this.a = r1;
            }

            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                i.a("event_B40", null, this.a.i);
                switch (i) {
                    case R.id.reader_setting_dialog_show_navigation_open:
                        d.s(this.a.e().getApplicationContext(), true);
                        aj.b(this.a.f, false);
                        return;
                    case R.id.reader_setting_dialog_show_navigation_close:
                        d.s(this.a.e().getApplicationContext(), false);
                        aj.b(this.a.f, true);
                        return;
                    default:
                        return;
                }
            }
        });
    }

    private void o() {
        this.O = (RadioGroup) this.f.findViewById(R.id.reader_setting_dialog_middle_doublepage);
        this.j = (RadioButton) this.f.findViewById(R.id.reader_setting_dialog_middle_doublepage_open);
        this.k = (RadioButton) this.f.findViewById(R.id.reader_setting_dialog_middle_doublepage_close);
        w();
        this.O.setOnCheckedChangeListener(new OnCheckedChangeListener(this) {
            final /* synthetic */ ad a;

            {
                this.a = r1;
            }

            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                i.a("event_B40", null, this.a.i);
                boolean ag = d.ag(this.a.e().getApplicationContext());
                switch (i) {
                    case R.id.reader_setting_dialog_middle_doublepage_open:
                        if (!ag) {
                            d.m(this.a.e().getApplicationContext(), true);
                            if (this.a.a != null) {
                                if (d.Y(this.a.e().getApplicationContext()) == 0) {
                                    this.a.a.p = true;
                                    this.a.a.t();
                                    this.a.g(false);
                                    return;
                                }
                                CharSequence charSequence = "双翻页模式只在横屏下体现";
                                if (this.a.b == null) {
                                    this.a.b = af.a(this.a.a.getApplicationContext(), charSequence, 0);
                                } else {
                                    this.a.b.a(charSequence);
                                }
                                this.a.b.a();
                                return;
                            }
                            return;
                        }
                        return;
                    case R.id.reader_setting_dialog_middle_doublepage_close:
                        if (ag) {
                            d.m(this.a.e().getApplicationContext(), false);
                            if (this.a.a != null) {
                                this.a.a.p = true;
                                this.a.g(true);
                                this.a.v();
                                return;
                            }
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        });
    }

    private void p() {
        ((ImageView) this.f.findViewById(R.id.profile_header_left_back)).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ad a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.cancel();
            }
        });
    }

    private void q() {
        this.G = (RadioGroup) this.f.findViewById(R.id.reader_setting_dialog_line_spacing_style);
        t();
        this.G.setOnCheckedChangeListener(new OnCheckedChangeListener(this) {
            final /* synthetic */ ad a;

            {
                this.a = r1;
            }

            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.reader_setting_dialog_line_spacing_large:
                        d.h(this.a.e().getApplicationContext(), 2);
                        com.qq.reader.readengine.a.a.b();
                        this.a.h().a(this.a.e);
                        d.d(this.a.e().getApplicationContext(), this.a.e);
                        j.a(70, 1);
                        return;
                    case R.id.reader_setting_dialog_line_spacing_default:
                        d.h(this.a.e().getApplicationContext(), 1);
                        com.qq.reader.readengine.a.a.b();
                        this.a.h().a(this.a.d);
                        d.d(this.a.e().getApplicationContext(), this.a.d);
                        j.a(78, 1);
                        return;
                    case R.id.reader_setting_dialog_line_spacing_small:
                        d.h(this.a.e().getApplicationContext(), 0);
                        com.qq.reader.readengine.a.a.b();
                        this.a.h().a(this.a.c);
                        d.d(this.a.e().getApplicationContext(), this.a.c);
                        j.a(71, 1);
                        return;
                    default:
                        return;
                }
            }
        });
    }

    private void r() {
        this.H = (RadioGroup) this.f.findViewById(R.id.reader_setting_dialog_slide2changeLight);
        this.E = (RadioButton) this.f.findViewById(R.id.reader_setting_dialog_slide2changeLight_open);
        this.F = (RadioButton) this.f.findViewById(R.id.reader_setting_dialog_slide2changeLight_close);
        u();
        this.H.setOnCheckedChangeListener(new OnCheckedChangeListener(this) {
            final /* synthetic */ ad a;

            {
                this.a = r1;
            }

            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.reader_setting_dialog_slide2changeLight_open:
                        d.n(this.a.e().getApplicationContext(), true);
                        d.k(this.a.e(), false);
                        ao.b(this.a.b());
                        break;
                    case R.id.reader_setting_dialog_slide2changeLight_close:
                        d.n(this.a.e().getApplicationContext(), false);
                        break;
                }
                i.a("event_B17", null, this.a.i);
            }
        });
    }

    private void s() {
        this.L = (RadioGroup) this.f.findViewById(R.id.reader_setting_dialog_middle_turnpage_animation_style);
        this.A = (RadioButton) this.f.findViewById(R.id.reader_setting_dialog_middle_turnpage_animation_style_cursor_none);
        this.B = (RadioButton) this.f.findViewById(R.id.reader_setting_dialog_middle_turnpage_animation_style_cursor_slide);
        this.C = (RadioButton) this.f.findViewById(R.id.reader_setting_dialog_middle_turnpage_animation_style_cursor_vertical);
        this.D = (RadioButton) this.f.findViewById(R.id.reader_setting_dialog_middle_turnpage_animation_style_cursor_page);
        v();
        this.L.setOnCheckedChangeListener(new OnCheckedChangeListener(this) {
            final /* synthetic */ ad a;

            {
                this.a = r1;
            }

            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int af = d.af(this.a.e().getApplicationContext());
                ReaderTextPageView topPage;
                switch (i) {
                    case R.id.reader_setting_dialog_middle_turnpage_animation_style_cursor_page:
                        this.a.f(true);
                        if (af != 2) {
                            d.q(this.a.e().getApplicationContext(), 2);
                            if (this.a.a != null) {
                                this.a.a.p = true;
                                topPage = this.a.a.q.getTopPage();
                                if (!topPage.h()) {
                                    topPage.b = null;
                                    topPage.setSize(topPage.getWidth(), topPage.getHeight());
                                }
                            }
                        }
                        i.a("event_B48", null, this.a.i);
                        return;
                    case R.id.reader_setting_dialog_middle_turnpage_animation_style_cursor_slide:
                        this.a.f(true);
                        if (af != 1) {
                            d.q(this.a.e().getApplicationContext(), 1);
                            if (this.a.a != null) {
                                this.a.a.p = true;
                                topPage = this.a.a.q.getTopPage();
                                if (!topPage.h()) {
                                    topPage.b = null;
                                    topPage.setSize(topPage.getWidth(), topPage.getHeight());
                                }
                            }
                        }
                        i.a("event_B50", null, this.a.i);
                        return;
                    case R.id.reader_setting_dialog_middle_turnpage_animation_style_cursor_vertical:
                        this.a.f(false);
                        if (af != 3) {
                            d.q(this.a.e().getApplicationContext(), 3);
                            if (this.a.a != null) {
                                this.a.a.p = true;
                                topPage = this.a.a.q.getTopPage();
                                if (!topPage.h()) {
                                    topPage.b = null;
                                    topPage.setSize(topPage.getWidth(), topPage.getHeight());
                                }
                            }
                        }
                        i.a("event_B53", null, this.a.i);
                        return;
                    case R.id.reader_setting_dialog_middle_turnpage_animation_style_cursor_none:
                        this.a.f(true);
                        if (af != 0) {
                            d.q(this.a.e().getApplicationContext(), 0);
                            if (this.a.a != null) {
                                this.a.a.p = true;
                                topPage = this.a.a.q.getTopPage();
                                if (!topPage.h()) {
                                    topPage.b = null;
                                    topPage.setSize(topPage.getWidth(), topPage.getHeight());
                                }
                            }
                        }
                        i.a("event_B51", null, this.a.i);
                        return;
                    default:
                        return;
                }
            }
        });
    }

    private void t() {
        int G = d.G(e().getApplicationContext());
        this.c = e().getResources().getDimensionPixelSize(R.dimen.line_space_small);
        this.d = e().getResources().getDimensionPixelSize(R.dimen.line_space_normal);
        this.e = e().getResources().getDimensionPixelSize(R.dimen.line_space_big);
        if (G == 0) {
            this.G.check(R.id.reader_setting_dialog_line_spacing_small);
        } else if (G == 1) {
            this.G.check(R.id.reader_setting_dialog_line_spacing_default);
        } else {
            this.G.check(R.id.reader_setting_dialog_line_spacing_large);
        }
    }

    private void u() {
        boolean ah = d.ah(e().getApplicationContext());
        if (d.aa(e())) {
            this.F.setChecked(true);
        } else if (ah) {
            this.E.setChecked(true);
        } else {
            this.F.setChecked(true);
        }
    }

    private void v() {
        switch (d.af(e().getApplicationContext())) {
            case 0:
                this.A.setChecked(true);
                return;
            case 1:
                this.B.setChecked(true);
                return;
            case 3:
                this.C.setChecked(true);
                return;
            default:
                this.D.setChecked(true);
                return;
        }
    }

    private void w() {
        if (d.ag(e().getApplicationContext())) {
            this.j.setChecked(true);
        } else {
            this.k.setChecked(true);
        }
    }

    private void x() {
        if (d.ao(e().getApplicationContext())) {
            this.l.setChecked(true);
            if (this.n) {
                this.o = (LinearLayout) this.f.findViewById(R.id.reader_setting_dialog_show_navigation_part);
                this.o.setVisibility(0);
                y();
                return;
            }
            return;
        }
        this.m.setChecked(true);
    }

    private void y() {
        if (d.aq(e().getApplicationContext())) {
            this.p.setChecked(true);
        } else {
            this.q.setChecked(true);
        }
    }

    private void z() {
        if (d.an(e().getApplicationContext())) {
            this.r.setChecked(true);
        } else {
            this.s.setChecked(true);
        }
    }

    private void f(boolean z) {
        if (z) {
            this.V.setVisibility(0);
        } else {
            this.V.setVisibility(8);
        }
        k(z);
    }

    private void g(boolean z) {
        if (z) {
            this.W.setVisibility(0);
        } else {
            this.W.setVisibility(8);
        }
    }

    private void h(boolean z) {
        if (z) {
            this.U.setVisibility(0);
        } else {
            this.U.setVisibility(8);
        }
    }

    private void i(boolean z) {
        if (z) {
            this.X.setVisibility(0);
        } else {
            this.X.setVisibility(8);
        }
    }

    private void j(boolean z) {
        if (z) {
            this.Y.setVisibility(0);
        } else {
            this.Y.setVisibility(8);
        }
    }

    private void k(boolean z) {
        if (z) {
            this.Z.setVisibility(0);
            u();
            return;
        }
        this.Z.setVisibility(8);
    }

    private void A() {
        if (d.am(e().getApplicationContext())) {
            this.t.setChecked(true);
        } else {
            this.u.setChecked(true);
        }
    }

    private void B() {
        switch (d.ai(e().getApplicationContext())) {
            case 1:
                this.v.setChecked(true);
                return;
            case 3:
                this.w.setChecked(true);
                return;
            case 5:
                this.x.setChecked(true);
                return;
            case 10:
                this.y.setChecked(true);
                return;
            default:
                this.z.setChecked(true);
                return;
        }
    }

    public void g() {
        if (this.C != null) {
            this.C.setVisibility(8);
        }
    }

    public void a(boolean z) {
        boolean z2;
        int af = d.af(e().getApplicationContext());
        g(z);
        j(z);
        if (z) {
            if (af == 3) {
                z2 = false;
            } else {
                z2 = true;
            }
            f(z2);
        } else {
            f(true);
            h(false);
            k(false);
        }
        Context e = e();
        if (e == null || !(e instanceof Activity)) {
            i(false);
        } else if (ao.d((Activity) e)) {
            i(true);
        } else {
            i(false);
        }
        if (this.aa == null || this.aa.g() == null) {
            this.S.setVisibility(8);
            z2 = true;
        } else {
            OnlineTag g = this.aa.g();
            af = -1;
            if (this.ab != null) {
                af = this.ab.c();
            }
            if (g == null || r0 != 2) {
                this.S.setVisibility(8);
                z2 = true;
            } else {
                z2 = g.x();
            }
        }
        if (z2) {
            this.Q.setChecked(true);
            this.R.setChecked(false);
        } else {
            this.Q.setChecked(false);
            this.R.setChecked(true);
        }
        super.f_();
    }

    public a h() {
        return this.T;
    }

    public void a(a aVar) {
        this.T = aVar;
    }
}
