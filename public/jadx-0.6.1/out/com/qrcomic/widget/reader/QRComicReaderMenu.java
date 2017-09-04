package com.qrcomic.widget.reader;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.DialogInterface.OnKeyListener;
import android.content.DialogInterface.OnShowListener;
import android.graphics.drawable.Drawable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import b.a.a.a.a.a.d;
import b.a.a.a.a.a.e;
import b.a.a.a.a.a.f;
import b.a.a.a.a.a.g;
import b.a.a.a.a.a.h;
import com.qrcomic.activity.reader.QRComicReadingBaseActivity;
import com.qrcomic.activity.reader.QRComicReadingLandActivity;
import com.qrcomic.activity.reader.QRComicReadingVerticalActivity;
import com.qrcomic.c.c.a;
import com.qrcomic.entity.ComicSectionPicInfo;
import com.qrcomic.manager.b;
import com.qrcomic.util.h.c;

public class QRComicReaderMenu implements OnDismissListener, OnShowListener, OnClickListener, AnimationListener, OnCheckedChangeListener, OnSeekBarChangeListener {
    private Animation a;
    private Animation b;
    private boolean c;
    private boolean d;
    private View e;
    private LinearLayout f;
    private LinearLayout g;
    private LinearLayout h;
    private SeekBar i;
    private QRComicReadingBaseActivity j;
    private DialogEx k;
    private ImageView l;
    private ImageView m;
    private a n;
    private CheckBox o;
    private RadioGroup p;
    private RadioButton q;
    private RadioButton r;
    private TextView s;
    private View t;
    private View u;
    private View v;
    private View w;
    private OnDismissListener x;

    private static class DialogEx extends Dialog {
        public DialogEx(Context context, int i) {
            super(context, i);
        }

        public void a() {
            super.dismiss();
        }
    }

    public QRComicReaderMenu(QRComicReadingBaseActivity qRComicReadingBaseActivity, int i, a aVar, OnDismissListener onDismissListener) {
        this.n = aVar;
        this.j = qRComicReadingBaseActivity;
        this.e = LayoutInflater.from(qRComicReadingBaseActivity).inflate(i == 1 ? f.qr_comic_land_reader_menu : f.qr_comic_port_reader_menu, null);
        this.e.setBackgroundResource(d.vip_comic_gradient_up_menu);
        this.l = (ImageView) this.e.findViewById(e.day_icon);
        this.m = (ImageView) this.e.findViewById(e.night_icon);
        this.l.setOnClickListener(this);
        this.m.setOnClickListener(this);
        this.o = (CheckBox) this.e.findViewById(e.light_checkbox);
        this.o.setOnClickListener(this);
        this.s = (TextView) this.e.findViewById(e.light_fit_system);
        this.s.setSelected(true);
        this.s.setOnClickListener(this);
        this.u = this.e.findViewById(e.reader_mode);
        this.t = this.e.findViewById(e.reader_mode_line);
        this.w = this.e.findViewById(e.pay_mode);
        this.v = this.e.findViewById(e.reader_pay_mode_line);
        e();
        this.f = (LinearLayout) this.e.findViewById(e.reader_mode_port_scroll);
        this.f.setOnClickListener(this);
        this.g = (LinearLayout) this.e.findViewById(e.reader_mode_port_pager);
        this.g.setOnClickListener(this);
        this.h = (LinearLayout) this.e.findViewById(e.reader_mode_land_scroll);
        this.h.setOnClickListener(this);
        this.i = (SeekBar) this.e.findViewById(e.brightness_bar);
        this.i.setMax(255);
        g();
        this.i.setOnSeekBarChangeListener(this);
        this.p = (RadioGroup) this.e.findViewById(e.reader_setting_dialog_auto_buy_next_chapter);
        this.q = (RadioButton) this.e.findViewById(e.reader_setting_dialog_auto_buy_next_chapter_enable);
        this.r = (RadioButton) this.e.findViewById(e.reader_setting_dialog_auto_buy_next_chapter_disable);
        this.q.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ QRComicReaderMenu a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                b.a().b().f().c().a("event_F283", null, this.a.j.getApplicationContext());
            }
        });
        this.r.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ QRComicReaderMenu a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                b.a().b().f().c().a("event_F284", null, this.a.j.getApplicationContext());
            }
        });
        this.p.setOnCheckedChangeListener(this);
        this.a = AnimationUtils.loadAnimation(this.j, b.a.a.a.a.a.a.menu_slide_in);
        this.b = AnimationUtils.loadAnimation(this.j, b.a.a.a.a.a.a.menu_slide_out);
        this.b.setAnimationListener(this);
        h();
        f();
        this.k = new DialogEx(this, qRComicReadingBaseActivity, h.comicTransparentDialog) {
            final /* synthetic */ QRComicReaderMenu a;

            public void dismiss() {
                this.a.c();
            }

            public void cancel() {
                this.a.c();
            }
        };
        this.k.setCanceledOnTouchOutside(true);
        this.k.setCancelable(true);
        this.k.setContentView(this.e);
        this.k.setOnDismissListener(this);
        this.k.setOnShowListener(this);
        this.k.setOnKeyListener(new OnKeyListener(this) {
            final /* synthetic */ QRComicReaderMenu a;

            {
                this.a = r1;
            }

            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() == 1 && i == 4 && this.a.j != null && !this.a.j.isFinishing() && this.a.k.isShowing()) {
                    this.a.k.dismiss();
                }
                return true;
            }
        });
        Window window = this.k.getWindow();
        window.setWindowAnimations(0);
        LayoutParams attributes = window.getAttributes();
        attributes.width = qRComicReadingBaseActivity.getResources().getDisplayMetrics().widthPixels;
        attributes.gravity = 80;
        window.setAttributes(attributes);
        this.x = onDismissListener;
    }

    private void g() {
        this.i.setProgress(c.a());
        if (c.b()) {
            this.o.setSelected(true);
        } else {
            this.o.setSelected(false);
        }
    }

    public boolean a() {
        if (this.k == null) {
            return false;
        }
        return this.k.isShowing();
    }

    @TargetApi(11)
    public void b() {
        if (this.j != null && !this.j.isFinishing() && this.k != null && !this.k.isShowing()) {
            g();
            h();
            i();
            e();
            if (this.j.aE) {
                this.k.getWindow().getDecorView().setSystemUiVisibility(5380);
            }
            this.k.show();
        }
    }

    private void h() {
        if (com.qrcomic.util.h.a.b(this.j.Z.n, this.j.a.a())) {
            this.p.check(e.reader_setting_dialog_auto_buy_next_chapter_enable);
        } else {
            this.p.check(e.reader_setting_dialog_auto_buy_next_chapter_disable);
        }
    }

    public void c() {
        if (this.e != null && this.j != null && !this.j.isFinishing() && !this.d) {
            this.e.startAnimation(this.b);
        }
    }

    public void d() {
        if (this.k != null) {
            this.k.a();
        }
    }

    public void onClick(View view) {
        if (this.j != null && this.j.Z != null) {
            com.qrcomic.activity.reader.a aVar = this.j.Z;
            if (aVar.C >= aVar.r.size()) {
                aVar.d(aVar.r.size() - 1);
                aVar.B = ((ComicSectionPicInfo) aVar.r.get(aVar.C)).picId;
            }
            this.j.Z.K = false;
            int id = view.getId();
            QRComicReadingVerticalActivity qRComicReadingVerticalActivity;
            if (id == e.reader_mode_port_scroll) {
                if (aVar.H == 2) {
                    c.c(0);
                    ((QRComicReadingVerticalActivity) this.j).aq = true;
                    this.j.a(QRComicReadingVerticalActivity.class, aVar.n, aVar.o.b, aVar.E, aVar.B, aVar.o.t);
                } else if (this.j.aF != 0 && (this.j instanceof QRComicReadingVerticalActivity)) {
                    c.c(1);
                    qRComicReadingVerticalActivity = (QRComicReadingVerticalActivity) this.j;
                    qRComicReadingVerticalActivity.aq = true;
                    qRComicReadingVerticalActivity.d(0);
                }
                i();
                if (aVar.o != null) {
                    b.a().b().f().c().a("event_F282", null, this.j.getApplicationContext());
                } else {
                    b.a().b().f().c().a("event_F282", null, this.j.getApplicationContext());
                }
            } else if (id == e.reader_mode_port_pager) {
                if (aVar.H == 2) {
                    c.c(1);
                    this.j.a(QRComicReadingVerticalActivity.class, aVar.n, aVar.o.b, aVar.E, aVar.B, aVar.o.t);
                } else if (this.j.aF != 1 && (this.j instanceof QRComicReadingVerticalActivity)) {
                    qRComicReadingVerticalActivity = (QRComicReadingVerticalActivity) this.j;
                    qRComicReadingVerticalActivity.aq = true;
                    qRComicReadingVerticalActivity.d(1);
                }
                i();
                if (aVar.o != null) {
                    b.a().b().f().c().a("event_F281", null, this.j.getApplicationContext());
                } else {
                    b.a().b().f().c().a("event_F281", null, this.j.getApplicationContext());
                }
            } else if (id == e.reader_mode_land_scroll) {
                if (aVar.H != 2) {
                    this.j.a(QRComicReadingLandActivity.class, aVar.n, aVar.o.b, aVar.E, aVar.B, aVar.o.t);
                }
                if (aVar.o == null) {
                }
            } else if (id == e.light_fit_system || id == e.light_checkbox) {
                if (this.o.isSelected()) {
                    this.o.setSelected(false);
                    c.a(false);
                    this.j.R();
                    return;
                }
                this.o.setSelected(true);
                com.qrcomic.util.c.c.a(this.j);
                c.a(true);
                com.qrcomic.util.c.c.b(this.j);
            } else if (id == e.day_icon) {
                c.a(false);
                this.o.setSelected(false);
                a(true);
            } else if (id == e.night_icon) {
                c.a(false);
                this.o.setSelected(false);
                a(false);
            }
        }
    }

    private void i() {
        switch (j()) {
            case 0:
                this.f.setSelected(true);
                this.g.setSelected(false);
                this.h.setSelected(false);
                return;
            case 1:
                this.g.setSelected(true);
                this.f.setSelected(false);
                this.h.setSelected(false);
                return;
            case 2:
                this.h.setSelected(true);
                this.f.setSelected(false);
                this.g.setSelected(false);
                return;
            default:
                this.f.setSelected(false);
                this.g.setSelected(false);
                this.h.setSelected(false);
                return;
        }
    }

    private int j() {
        if (this.j == null) {
            return -1;
        }
        if (this.j.Z.H == 2) {
            return 2;
        }
        if (this.j.aF == 0) {
            return 0;
        }
        if (this.j.aF == 1) {
            return 1;
        }
        return -1;
    }

    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        if (this.j != null) {
            if (z) {
                c.a(false);
                this.o.setSelected(false);
            }
            c.a(i);
            this.j.R();
        }
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
        if (this.j != null && this.j.Z != null && this.j.Z.o != null) {
        }
    }

    public void onDismiss(DialogInterface dialogInterface) {
        if (this.j != null && !this.j.isFinishing() && this.j.n != null) {
            this.j.n.setBarrageBtnBgAlpha(255);
            Animation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
            alphaAnimation.setDuration(300);
            alphaAnimation.setFillAfter(true);
            this.j.n.startAnimation(alphaAnimation);
            if (this.x != null) {
                this.x.onDismiss(dialogInterface);
            }
        }
    }

    public void onShow(DialogInterface dialogInterface) {
        if (this.e != null && this.j != null && !this.j.isFinishing()) {
            if (this.j.n != null) {
                Animation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
                alphaAnimation.setDuration(300);
                alphaAnimation.setFillAfter(true);
                this.j.n.startAnimation(alphaAnimation);
            }
            if (!this.c) {
                this.e.startAnimation(this.a);
            }
        }
    }

    public void onAnimationStart(Animation animation) {
        if (animation == this.b) {
            this.d = true;
        }
        if (animation == this.a) {
            this.c = true;
        }
    }

    @TargetApi(11)
    public void onAnimationEnd(Animation animation) {
        if (animation == this.b) {
            this.d = false;
        }
        if (animation == this.a) {
            this.c = false;
        }
        if (this.j != null && !this.j.isFinishing()) {
            this.j.b(null);
            this.j.runOnUiThread(new Runnable(this) {
                final /* synthetic */ QRComicReaderMenu a;

                {
                    this.a = r1;
                }

                public void run() {
                    if (this.a.j != null && !this.a.j.isFinishing() && this.a.k != null && this.a.k.isShowing()) {
                        this.a.k.a();
                    }
                }
            });
        }
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if (this.q.isChecked()) {
            com.qrcomic.util.h.a.a(true, this.j.Z.n, this.j.a.a());
            this.j.Z.A = true;
        } else if (this.r.isChecked()) {
            com.qrcomic.util.h.a.a(false, this.j.Z.n, this.j.a.a());
            this.j.Z.A = false;
        }
    }

    public void a(boolean z) {
        int max = this.i.getMax() / 10;
        if (!z) {
            max = -max;
        }
        this.i.setProgress(max + this.i.getProgress());
    }

    public void e() {
        try {
            if (this.j.Z.i.m == 1) {
                this.u.setVisibility(8);
                this.t.setVisibility(8);
            } else {
                this.u.setVisibility(0);
                this.t.setVisibility(0);
                b.a().b().f().c().a("event_F280", null, this.j.getApplicationContext());
            }
            if (this.j.Z.i.g == 2 || !b.a().b().f().a().a(this.j)) {
                this.w.setVisibility(8);
                this.v.setVisibility(8);
                return;
            }
            this.w.setVisibility(0);
            this.v.setVisibility(0);
            b.a().b().f().c().a("event_F285", null, this.j.getApplicationContext());
        } catch (Exception e) {
        }
    }

    public void f() {
        this.e.setBackgroundResource(this.n.d(false));
        int[] g = this.n.g(false);
        this.m.setImageResource(g[0]);
        this.l.setImageResource(g[1]);
        this.o.setBackgroundResource(this.n.h(false));
        this.s.setTextColor(this.j.getResources().getColorStateList(this.n.e(false)));
        g = this.n.i(false);
        this.p.setBackgroundResource(g[0]);
        this.q.setBackgroundResource(g[1]);
        this.r.setBackgroundResource(g[1]);
        this.q.setTextColor(this.j.getResources().getColorStateList(g[2]));
        this.r.setTextColor(this.j.getResources().getColorStateList(g[2]));
        g = this.n.j(false);
        this.i.setThumb(this.j.getResources().getDrawable(g[0]));
        Drawable drawable = this.j.getResources().getDrawable(g[1]);
        this.i.setProgressDrawable(drawable);
        if (this.i.getTag(g.key_id) != null) {
            int a = (int) (((float) com.qrcomic.util.c.a.a(this.j, 3)) / 4.0f);
            int height = (this.i.getHeight() / 2) - a;
            drawable.setBounds(0, height, this.i.getProgressDrawable().getBounds().right, (a * 3) + height);
            drawable.invalidateSelf();
        }
        this.i.setTag(g.key_id, "FUCK_THE_Android_SEEK_BAR");
    }
}
