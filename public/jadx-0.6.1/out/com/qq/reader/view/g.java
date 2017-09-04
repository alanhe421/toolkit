package com.qq.reader.view;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.monitor.j;
import com.qq.reader.plugin.w;
import com.qq.reader.plugin.x;
import com.tencent.connect.common.Constants;
import com.tencent.feedback.proguard.R;

/* compiled from: CommonSettingDialog */
public class g extends BaseDialog implements OnClickListener, OnLongClickListener {
    private ImageButton A;
    private ImageButton B;
    private ImageButton C;
    private ImageButton D;
    private ImageButton E;
    private ImageButton F;
    private CustomCircle G;
    private c H;
    private HorizontalScrollView I;
    private int J;
    private View K;
    private View L;
    private View M;
    private TextView N;
    private TextView O;
    private TextView P;
    private TextView Q;
    private boolean R = false;
    private d S;
    private View T;
    private View U;
    private View V;
    private TextView W;
    private boolean X = true;
    private Handler Y = new Handler(this) {
        final /* synthetic */ g a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 700:
                    synchronized (this.a.i) {
                        if (com.qq.reader.appconfig.a.d.aa(this.a.d)) {
                            com.qq.reader.appconfig.a.d.k(this.a.d, false);
                            this.a.m();
                        }
                        if (com.qq.reader.appconfig.a.d.n) {
                            com.qq.reader.appconfig.a.d.s = this.a.i.getProgress() + com.qq.reader.appconfig.a.d.as(this.a.d);
                        } else {
                            com.qq.reader.appconfig.a.d.q = this.a.i.getProgress() + com.qq.reader.appconfig.a.d.as(this.a.d);
                        }
                        if (this.a.m != null) {
                            this.a.m.a();
                        }
                    }
                    return;
                case 701:
                    this.a.r();
                    return;
                case 702:
                    this.a.k.setEnabled(false);
                    return;
                case 703:
                    this.a.l.setEnabled(false);
                    return;
                default:
                    return;
            }
        }
    };
    public int a = 0;
    public int b = 3;
    public int c = 6;
    private Activity d;
    private int e;
    private SeekBar i;
    private TextView j;
    private ImageView k;
    private ImageView l;
    private b m;
    private final int n = 10;
    private Button o;
    private Button p;
    private TextView q;
    private TextView r;
    private Drawable s;
    private a t;
    private int u = 2;
    private final int v = 0;
    private final int w = 1;
    private final int x = 2;
    private ImageButton y;
    private ImageButton z;

    /* compiled from: CommonSettingDialog */
    public interface a {
        void a();

        void a(float f);
    }

    /* compiled from: CommonSettingDialog */
    public interface b {
        void a();

        void b();
    }

    /* compiled from: CommonSettingDialog */
    public interface c {
        void a(int i);
    }

    /* compiled from: CommonSettingDialog */
    public interface d {
        void onClick(int i);
    }

    public g(Activity activity, int i) {
        this.d = activity;
        a(activity, null, R.layout.commonsetttingdialog, true, false, true);
        this.e = i;
        i();
        j();
        k();
        d(false);
        a(com.qq.reader.appconfig.a.d.n);
    }

    private void i() {
        this.i = (SeekBar) this.f.findViewById(R.id.progress);
        this.i.setFocusable(false);
        this.i.setMax(255 - com.qq.reader.appconfig.a.d.as(this.d));
        this.k = (ImageView) this.f.findViewById(R.id.left_button);
        this.l = (ImageView) this.f.findViewById(R.id.right_button);
        this.j = (TextView) this.f.findViewById(R.id.commonsetting_light_followsys);
        this.W = (TextView) this.f.findViewById(R.id.commonsetting_opt_pdf_zoom_text);
        this.o = (Button) this.f.findViewById(R.id.zoominButton);
        this.p = (Button) this.f.findViewById(R.id.zoomoutButton);
        this.r = (TextView) this.f.findViewById(R.id.zoom_FontChoose);
        if (this.e == 0 || this.e == 3) {
            this.f.findViewById(R.id.commonsetting_opt_TXT).setVisibility(0);
            this.f.findViewById(R.id.commonsetting_opt_PDF).setVisibility(8);
            this.q = (TextView) this.f.findViewById(R.id.zoomTextSize);
            this.y = (ImageButton) this.f.findViewById(R.id.commonsetting_bg_0);
            this.z = (ImageButton) this.f.findViewById(R.id.commonsetting_bg_1);
            this.A = (ImageButton) this.f.findViewById(R.id.commonsetting_bg_2);
            this.B = (ImageButton) this.f.findViewById(R.id.commonsetting_bg_3);
            this.C = (ImageButton) this.f.findViewById(R.id.commonsetting_bg_4);
            this.D = (ImageButton) this.f.findViewById(R.id.commonsetting_bg_5);
            this.E = (ImageButton) this.f.findViewById(R.id.commonsetting_bg_6);
            this.G = (CustomCircle) this.f.findViewById(R.id.commonsetting_bg_7);
            this.F = (ImageButton) this.f.findViewById(R.id.commonsetting_theme_set_bg);
            x b = w.b().b(ReaderApplication.getApplicationImp());
            if (b == null || Constants.DEFAULT_UIN.equals(b.i())) {
                this.F.setVisibility(8);
            } else {
                this.F.setVisibility(0);
            }
            this.I = (HorizontalScrollView) this.f.findViewById(R.id.commonsetting_bg_scrollview);
            this.K = this.f.findViewById(R.id.commonsetting_opt_0);
            this.L = this.f.findViewById(R.id.commonsetting_opt_1);
            this.M = this.f.findViewById(R.id.commonsetting_opt_2);
            this.M.setVisibility(8);
            this.N = (TextView) this.f.findViewById(R.id.commonsetting_opt_3);
            this.O = (TextView) this.f.findViewById(R.id.commonsetting_opt_4);
            this.P = (TextView) this.f.findViewById(R.id.commonsetting_opt_nightmode);
            this.Q = (TextView) this.f.findViewById(R.id.commonsetting_opt_landscapePortrait);
            if (this.e == 3) {
                this.L.setVisibility(8);
            }
        } else if (this.e == 1) {
            this.f.findViewById(R.id.commonsetting_opt_TXT).setVisibility(8);
            this.f.findViewById(R.id.commonsetting_opt_PDF).setVisibility(0);
            this.T = this.f.findViewById(R.id.commonsetting_opt_pdf_cut);
            this.U = this.f.findViewById(R.id.commonsetting_opt_pdf_zoom);
            this.V = this.f.findViewById(R.id.commonsetting_opt_pdf_more);
        } else {
            this.f.findViewById(R.id.commonsetting_opt_TXT).setVisibility(8);
            this.f.findViewById(R.id.commonsetting_opt_PDF).setVisibility(8);
        }
    }

    private void j() {
        s();
        m();
        if (this.e == 0 || this.e == 3) {
            this.u = this.d.getResources().getDimensionPixelSize(R.dimen.zoom_font_step_size);
            w();
            int I = (int) com.qq.reader.appconfig.a.d.I(this.d);
            if (((float) I) <= com.qq.reader.appconfig.a.d.J(this.d)) {
                this.o.setEnabled(false);
            } else if (((float) I) >= com.qq.reader.appconfig.a.d.K(e())) {
                this.p.setEnabled(false);
            }
            this.J = com.qq.reader.appconfig.a.d.L(this.d);
            c(this.J);
        }
    }

    private void k() {
        this.k.setOnClickListener(this);
        this.l.setOnClickListener(this);
        this.k.setOnLongClickListener(this);
        this.l.setOnLongClickListener(this);
        this.i.setOnSeekBarChangeListener(new OnSeekBarChangeListener(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                if (com.qq.reader.appconfig.a.d.n) {
                    com.qq.reader.appconfig.a.d.s = this.a.i.getProgress() + com.qq.reader.appconfig.a.d.as(this.a.d);
                } else {
                    com.qq.reader.appconfig.a.d.q = this.a.i.getProgress() + com.qq.reader.appconfig.a.d.as(this.a.d);
                }
                if (z) {
                    if (com.qq.reader.appconfig.a.d.aa(this.a.d)) {
                        com.qq.reader.appconfig.a.d.k(this.a.d, false);
                        this.a.j.setSelected(false);
                        this.a.m();
                    }
                    if (this.a.m != null) {
                        this.a.m.a();
                    }
                }
                if (!this.a.Y.hasMessages(701)) {
                    this.a.Y.sendEmptyMessage(701);
                }
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                i.a("event_B11", null, this.a.d);
            }
        });
        this.j.setOnClickListener(this);
        if (this.e == 0 || this.e == 3) {
            this.o.setOnClickListener(this);
            this.p.setOnClickListener(this);
            this.r.setOnClickListener(this);
            this.y.setOnClickListener(this);
            this.z.setOnClickListener(this);
            this.A.setOnClickListener(this);
            this.B.setOnClickListener(this);
            this.C.setOnClickListener(this);
            this.D.setOnClickListener(this);
            this.E.setOnClickListener(this);
            this.G.setOnClickListener(this);
            this.G.setOnLongClickListener(this);
            this.F.setOnClickListener(this);
            this.K.setOnClickListener(this);
            this.L.setOnClickListener(this);
            this.M.setOnClickListener(this);
            this.N.setOnClickListener(this);
            this.O.setOnClickListener(this);
        } else if (this.e == 1) {
            this.T.setOnClickListener(this);
            this.U.setOnClickListener(this);
            this.V.setOnClickListener(this);
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.right_button:
                q();
                if (!this.Y.hasMessages(701)) {
                    this.Y.sendEmptyMessage(701);
                }
                l();
                return;
            case R.id.left_button:
                p();
                if (!this.Y.hasMessages(701)) {
                    this.Y.sendEmptyMessage(701);
                }
                l();
                return;
            case R.id.commonsetting_light_followsys:
                boolean z = !this.j.isSelected();
                this.j.setSelected(z);
                if (z) {
                    com.qq.reader.appconfig.a.d.k(this.d, true);
                    j.a(67, 1);
                } else {
                    com.qq.reader.appconfig.a.d.k(this.d, false);
                    j.a(68, 1);
                }
                o();
                return;
            case R.id.zoominButton:
                u();
                return;
            case R.id.zoomoutButton:
                v();
                return;
            case R.id.zoom_FontChoose:
                dismiss();
                if (this.t != null) {
                    this.t.a();
                    return;
                }
                return;
            case R.id.commonsetting_theme_set_bg:
                b(7);
                i.a("event_B131", null, ReaderApplication.getApplicationImp());
                return;
            case R.id.commonsetting_bg_0:
                b(0);
                return;
            case R.id.commonsetting_bg_2:
                b(2);
                return;
            case R.id.commonsetting_bg_1:
                b(1);
                return;
            case R.id.commonsetting_bg_3:
                b(3);
                return;
            case R.id.commonsetting_bg_4:
                b(4);
                return;
            case R.id.commonsetting_bg_5:
                b(5);
                return;
            case R.id.commonsetting_bg_6:
                b(6);
                return;
            case R.id.commonsetting_bg_7:
                if (com.qq.reader.appconfig.a.d.m) {
                    b(8);
                    return;
                } else {
                    b(9);
                    return;
                }
            case R.id.commonsetting_opt_0:
                dismiss();
                if (this.m != null) {
                    this.m.b();
                    return;
                }
                return;
            case R.id.commonsetting_opt_1:
                d(1);
                return;
            case R.id.commonsetting_opt_2:
                d(2);
                return;
            case R.id.commonsetting_opt_3:
                d(3);
                return;
            case R.id.commonsetting_opt_4:
                d(4);
                return;
            case R.id.commonsetting_opt_pdf_cut:
                d(5);
                return;
            case R.id.commonsetting_opt_pdf_zoom:
                d(6);
                return;
            case R.id.commonsetting_opt_pdf_more:
                d(7);
                return;
            default:
                return;
        }
    }

    public boolean onLongClick(View view) {
        switch (view.getId()) {
            case R.id.right_button:
                this.k.setEnabled(true);
                new Thread(this) {
                    final /* synthetic */ g a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        while (this.a.l.isPressed()) {
                            try {
                                AnonymousClass3.sleep(100);
                                if (!this.a.q()) {
                                    this.a.Y.sendEmptyMessage(703);
                                    break;
                                }
                            } catch (InterruptedException e) {
                                f.b("longClick Left Exception", e.toString());
                            }
                        }
                        if (!this.a.Y.hasMessages(701)) {
                            this.a.Y.sendEmptyMessage(701);
                        }
                        this.a.l();
                    }
                }.start();
                break;
            case R.id.left_button:
                this.l.setEnabled(true);
                new Thread(this) {
                    final /* synthetic */ g a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        while (this.a.k.isPressed()) {
                            try {
                                AnonymousClass2.sleep(100);
                                if (!this.a.p()) {
                                    this.a.Y.sendEmptyMessage(702);
                                    break;
                                }
                            } catch (InterruptedException e) {
                                f.b("longClick Left Exception", e.toString());
                            }
                        }
                        if (!this.a.Y.hasMessages(701)) {
                            this.a.Y.sendEmptyMessage(701);
                        }
                        this.a.l();
                    }
                }.start();
                break;
            case R.id.commonsetting_bg_7:
                if (this.H != null) {
                    this.H.a(9);
                }
                c(8);
                break;
        }
        return true;
    }

    private void l() {
        this.Y.sendEmptyMessage(700);
    }

    private void m() {
        com.qq.reader.appconfig.a.d.aa(this.d);
    }

    private void f(boolean z) {
        Drawable drawable = this.d.getResources().getDrawable(R.drawable.seekbar_style);
        Drawable drawable2 = this.d.getResources().getDrawable(R.drawable.seekbar_thumb);
        if (z) {
            drawable = this.d.getResources().getDrawable(R.drawable.seekbar_style_night);
            drawable2 = this.d.getResources().getDrawable(R.drawable.seekbar_thumb_night);
        }
        drawable2.setBounds(0, 0, drawable2.getIntrinsicWidth(), drawable2.getIntrinsicHeight());
        this.i.setProgressDrawable(drawable);
        this.i.setThumb(drawable2);
    }

    private void n() {
        this.j.setSelected(com.qq.reader.appconfig.a.d.aa(this.d));
        o();
        s();
    }

    private void o() {
        m();
        r();
        if (this.m != null) {
            this.m.a();
        }
    }

    private boolean p() {
        int progress = this.i.getProgress();
        if (progress == 0) {
            return false;
        }
        if (progress > 10) {
            this.i.setProgress(progress - 10);
        } else {
            this.i.setProgress(0);
        }
        this.i.postInvalidate();
        return true;
    }

    private boolean q() {
        int progress = this.i.getProgress();
        if (progress >= this.i.getMax()) {
            return false;
        }
        if (progress < this.i.getMax() - 10) {
            this.i.setProgress(progress + 10);
        } else {
            this.i.setProgress(this.i.getMax());
        }
        this.i.postInvalidate();
        return true;
    }

    private void r() {
        synchronized (this.i) {
            if (this.i.getMax() == this.i.getProgress()) {
                this.l.setEnabled(false);
            } else {
                this.l.setEnabled(true);
            }
            if (this.i.getProgress() == 0) {
                this.k.setEnabled(false);
            } else {
                this.k.setEnabled(true);
            }
        }
    }

    private void s() {
        if (com.qq.reader.appconfig.a.d.n) {
            this.i.setProgress(com.qq.reader.appconfig.a.d.s - com.qq.reader.appconfig.a.d.as(this.d));
        } else {
            this.i.setProgress(com.qq.reader.appconfig.a.d.q - com.qq.reader.appconfig.a.d.as(this.d));
        }
    }

    private void a(TextView textView, int i, int i2, int i3) {
        Drawable drawable;
        Drawable drawable2;
        Drawable drawable3;
        Drawable drawable4 = null;
        switch (i) {
            case 0:
                drawable = null;
                drawable2 = this.d.getResources().getDrawable(i2);
                drawable3 = null;
                break;
            case 1:
                drawable = this.d.getResources().getDrawable(i2);
                drawable2 = null;
                drawable3 = null;
                break;
            case 2:
                drawable3 = this.d.getResources().getDrawable(i2);
                drawable = null;
                drawable2 = null;
                break;
            case 3:
                drawable = null;
                drawable2 = null;
                drawable3 = null;
                drawable4 = this.d.getResources().getDrawable(i2);
                break;
            default:
                drawable3 = null;
                drawable = null;
                drawable2 = null;
                break;
        }
        textView.setTextColor(this.d.getResources().getColorStateList(i3));
        textView.setCompoundDrawablesWithIntrinsicBounds(drawable2, drawable, drawable3, drawable4);
    }

    public void f_() {
        if (this.e == 0 || this.e == 3) {
            g();
            if (this.e != 3) {
                t();
            }
            this.G.setCustomColor();
            h();
        } else if (this.e == 1) {
            if (this.s == null) {
                this.s = this.d.getResources().getDrawable(R.drawable.menu_icon_zoomin);
                this.s.setBounds(0, 0, this.s.getIntrinsicWidth(), this.s.getIntrinsicHeight());
            }
            this.W.setCompoundDrawables(null, this.s, null, null);
            this.W.setText("缩小");
            this.X = false;
        }
        n();
        this.f.show();
    }

    public void g() {
        if (com.qq.reader.appconfig.a.d.n) {
            a(this.P, 1, R.drawable.commonsetting_opt_0_daymode_selector, R.color.skin_set_read_page_menu_item_nightmode_selector);
            this.P.setText("白天");
            return;
        }
        a(this.P, 1, R.drawable.commonsetting_opt_0_nightmode_selector, R.color.skin_set_read_page_menu_item_daymode_textcolor_selector);
        this.P.setText("夜间");
    }

    private void t() {
        DisplayMetrics displayMetrics = b().getResources().getDisplayMetrics();
        if (displayMetrics.widthPixels > displayMetrics.heightPixels) {
            if (com.qq.reader.appconfig.a.d.n) {
                a(this.Q, 1, R.drawable.commonsetting_opt_1_landscape_selector_night, R.color.skin_set_read_page_menu_item_nightmode_selector);
            } else {
                a(this.Q, 1, R.drawable.commonsetting_opt_1_landscape_selector, R.color.skin_set_read_page_menu_item_daymode_textcolor_selector);
            }
            this.Q.setText("竖屏");
            this.R = true;
            return;
        }
        if (com.qq.reader.appconfig.a.d.n) {
            a(this.Q, 1, R.drawable.commonsetting_opt_1_portrait_selector_night, R.color.skin_set_read_page_menu_item_nightmode_selector);
        } else {
            a(this.Q, 1, R.drawable.commonsetting_opt_1_portrait_selector, R.color.skin_set_read_page_menu_item_daymode_textcolor_selector);
        }
        this.Q.setText("横屏");
        this.R = false;
    }

    public void a(b bVar) {
        this.m = bVar;
    }

    public void a(boolean z) {
        if (z) {
            this.f.findViewById(R.id.top_shadow).setVisibility(8);
            this.f.findViewById(R.id.settingdlg_content).setBackgroundResource(R.color.commonsetting_bg_color_night);
            this.k.setBackgroundResource(R.drawable.light_button_left_night);
            this.l.setBackgroundResource(R.drawable.light_button_right_night);
            f(true);
            a(this.j, 0, R.drawable.commonsetting_dialog_followsys_bg_selector_night, R.color.skin_set_read_page_menu_item_nightmode_selector);
            this.f.findViewById(R.id.settingdlg_divider1).setBackgroundResource(R.color.commonset_dlg_divider_night);
            this.f.findViewById(R.id.portrait_divider1).setBackgroundResource(R.color.commonset_dlg_divider_night);
            this.f.findViewById(R.id.settingdlg_divider2).setBackgroundResource(R.color.commonset_dlg_divider_night);
            this.f.findViewById(R.id.portrait_divider2).setBackgroundResource(R.color.commonset_dlg_divider_night);
            this.f.findViewById(R.id.portrait_divider3).setBackgroundResource(R.color.commonset_dlg_divider_night);
            this.o.setBackgroundResource(R.drawable.btn_zoom_in_night);
            this.p.setBackgroundResource(R.drawable.btn_zoom_out_night);
            a(this.r, 2, R.drawable.zoom_fontchoose_indicator_night, R.color.skin_set_read_page_menu_item_nightmode_selector);
            if (this.R) {
                a(this.Q, 1, R.drawable.commonsetting_opt_1_portrait_selector_night, R.color.skin_set_read_page_menu_item_nightmode_selector);
            } else {
                a(this.Q, 1, R.drawable.commonsetting_opt_1_landscape_selector_night, R.color.skin_set_read_page_menu_item_nightmode_selector);
            }
            a((TextView) this.f.findViewById(R.id.commonsetting_opt_2_reading), 1, R.drawable.commonsetting_opt_2_selector_night, R.color.skin_set_read_page_menu_item_nightmode_selector);
            a(this.N, 1, R.drawable.commonsetting_opt_3_selector_night, R.color.skin_set_read_page_menu_item_nightmode_selector);
            a(this.O, 1, R.drawable.commonsetting_opt_4_selector_night, R.color.skin_set_read_page_menu_item_nightmode_selector);
            if (this.X) {
                a(this.W, 1, R.drawable.menu_icon_zoomout_night, R.color.skin_set_read_page_menu_item_nightmode_selector);
            } else {
                a(this.W, 1, R.drawable.menu_icon_zoomin_night, R.color.skin_set_read_page_menu_item_nightmode_selector);
            }
            a((TextView) this.f.findViewById(R.id.commonsetting_opt_pdf_more_text), 1, R.drawable.commonsetting_opt_4_selector_night, R.color.skin_set_read_page_menu_item_nightmode_selector);
            return;
        }
        this.f.findViewById(R.id.top_shadow).setVisibility(0);
        this.f.findViewById(R.id.settingdlg_content).setBackgroundResource(R.color.commonsetting_bg_color);
        this.l.setBackgroundResource(R.drawable.light_button_right);
        this.k.setBackgroundResource(R.drawable.light_button_left);
        f(false);
        a(this.j, 0, R.drawable.commonsetting_dialog_followsys_bg_selector, R.color.skin_set_read_page_menu_item_daymode_textcolor_selector);
        this.f.findViewById(R.id.settingdlg_divider1).setBackgroundResource(R.color.commonset_dlg_divider);
        this.f.findViewById(R.id.portrait_divider1).setBackgroundResource(R.color.commonset_dlg_divider);
        this.f.findViewById(R.id.settingdlg_divider2).setBackgroundResource(R.color.commonset_dlg_divider);
        this.f.findViewById(R.id.portrait_divider2).setBackgroundResource(R.color.commonset_dlg_divider);
        this.f.findViewById(R.id.portrait_divider3).setBackgroundResource(R.color.commonset_dlg_divider);
        this.o.setBackgroundResource(R.drawable.btn_zoom_in);
        this.p.setBackgroundResource(R.drawable.btn_zoom_out);
        a(this.r, 2, R.drawable.zoom_fontchoose_indicator, R.color.skin_set_read_page_menu_item_daymode_textcolor_selector);
        if (this.R) {
            a(this.Q, 1, R.drawable.commonsetting_opt_1_portrait_selector, R.color.skin_set_read_page_menu_item_daymode_textcolor_selector);
        } else {
            a(this.Q, 1, R.drawable.commonsetting_opt_1_landscape_selector, R.color.skin_set_read_page_menu_item_daymode_textcolor_selector);
        }
        if (this.X) {
            a(this.W, 1, R.drawable.menu_icon_zoomout, R.color.skin_set_read_page_menu_item_daymode_textcolor_selector);
        } else {
            a(this.W, 1, R.drawable.menu_icon_zoomin, R.color.skin_set_read_page_menu_item_daymode_textcolor_selector);
        }
        a((TextView) this.f.findViewById(R.id.commonsetting_opt_2_reading), 1, R.drawable.commonsetting_opt_2_selector, R.color.skin_set_read_page_menu_item_daymode_textcolor_selector);
        a(this.N, 1, R.drawable.commonsetting_opt_3_selector, R.color.skin_set_read_page_menu_item_daymode_textcolor_selector);
        a(this.O, 1, R.drawable.commonsetting_opt_4_selector, R.color.skin_set_read_page_menu_item_daymode_textcolor_selector);
        a((TextView) this.f.findViewById(R.id.commonsetting_opt_pdf_more_text), 1, R.drawable.commonsetting_opt_4_selector, R.color.skin_set_read_page_menu_item_daymode_textcolor_selector);
    }

    private void u() {
        if (this.t != null) {
            float I = com.qq.reader.appconfig.a.d.I(this.d);
            switch (a(I)) {
                case 0:
                    this.t.a(c(I));
                    w();
                    this.p.setEnabled(true);
                    return;
                case 1:
                    this.t.a(c(I));
                    w();
                    this.o.setEnabled(false);
                    return;
                case 2:
                    this.o.setEnabled(false);
                    this.p.setEnabled(true);
                    return;
                default:
                    return;
            }
        }
    }

    private void v() {
        if (this.t != null) {
            float I = com.qq.reader.appconfig.a.d.I(this.d);
            switch (b(I)) {
                case 0:
                    this.t.a(d(I));
                    w();
                    this.o.setEnabled(true);
                    return;
                case 1:
                    this.t.a(d(I));
                    w();
                    this.p.setEnabled(false);
                    return;
                case 2:
                    this.o.setEnabled(true);
                    this.p.setEnabled(false);
                    return;
                default:
                    return;
            }
        }
    }

    public int a(float f) {
        int H = com.qq.reader.appconfig.a.d.H(e());
        if (H > 0) {
            com.qq.reader.appconfig.a.d.i(e(), H - 1);
        }
        if (c(f) > com.qq.reader.appconfig.a.d.J(e())) {
            com.qq.reader.appconfig.a.d.c(this.d, c(f));
            return 0;
        } else if (c(f) != com.qq.reader.appconfig.a.d.J(e())) {
            return 2;
        } else {
            com.qq.reader.appconfig.a.d.c(e(), c(f));
            return 1;
        }
    }

    public int b(float f) {
        int H = com.qq.reader.appconfig.a.d.H(e());
        if (H < 11) {
            com.qq.reader.appconfig.a.d.i(e(), H + 1);
        }
        if (d(f) < com.qq.reader.appconfig.a.d.K(e())) {
            com.qq.reader.appconfig.a.d.c(e(), d(f));
            return 0;
        } else if (d(f) != com.qq.reader.appconfig.a.d.K(e())) {
            return 2;
        } else {
            com.qq.reader.appconfig.a.d.c(e(), d(f));
            return 1;
        }
    }

    public float c(float f) {
        return f - ((float) this.u);
    }

    public float d(float f) {
        return ((float) this.u) + f;
    }

    private void w() {
        this.q.setText(String.valueOf((int) com.qq.reader.appconfig.a.d.I(this.d)));
    }

    public void h() {
        this.r.setText(com.qq.reader.appconfig.a.d.p(this.d.getApplicationContext()));
    }

    public void a(a aVar) {
        this.t = aVar;
    }

    private void b(int i) {
        if (this.J != i) {
            if (this.H != null) {
                this.H.a(i);
            }
            c(i);
        }
    }

    private void c(int i) {
        switch (this.J) {
            case 0:
                this.y.setSelected(false);
                this.y.setImageBitmap(null);
                break;
            case 1:
                this.z.setSelected(false);
                this.z.setImageBitmap(null);
                break;
            case 2:
                this.A.setSelected(false);
                this.A.setImageBitmap(null);
                break;
            case 3:
                this.B.setSelected(false);
                this.B.setImageBitmap(null);
                break;
            case 4:
                this.C.setSelected(false);
                this.C.setImageBitmap(null);
                break;
            case 5:
                this.D.setSelected(false);
                this.D.setImageBitmap(null);
                break;
            case 6:
                this.E.setSelected(false);
                this.E.setImageBitmap(null);
                break;
            case 7:
                this.F.setSelected(false);
                this.F.setImageBitmap(null);
                break;
            case 8:
                this.G.setSelected(false);
                break;
        }
        switch (i) {
            case 0:
                this.y.setSelected(true);
                this.y.setImageResource(R.drawable.readerpage_skin_selected_icon);
                break;
            case 1:
                this.z.setSelected(true);
                this.z.setImageResource(R.drawable.readerpage_skin_selected_icon);
                break;
            case 2:
                this.A.setSelected(true);
                this.A.setImageResource(R.drawable.readerpage_skin_selected_icon);
                break;
            case 3:
                this.B.setSelected(true);
                this.B.setImageResource(R.drawable.readerpage_skin_selected_icon);
                break;
            case 4:
                this.C.setSelected(true);
                this.C.setImageResource(R.drawable.readerpage_skin_selected_icon);
                break;
            case 5:
                this.D.setSelected(true);
                this.D.setImageResource(R.drawable.readerpage_skin_selected_icon);
                break;
            case 6:
                this.E.setSelected(true);
                this.E.setImageResource(R.drawable.readerpage_skin_selected_icon);
                break;
            case 7:
                this.F.setSelected(true);
                this.F.setImageResource(R.drawable.readerpage_skin_selected_icon);
                break;
            case 8:
                this.G.setSelected(true);
                break;
            case 9:
                this.G.setSelected(true);
                i = 8;
                break;
        }
        this.J = i;
    }

    public void a(c cVar) {
        this.H = cVar;
    }

    private void d(int i) {
        dismiss();
        if (this.S != null) {
            this.S.onClick(i);
        }
    }

    public void a(d dVar) {
        this.S = dVar;
    }
}
