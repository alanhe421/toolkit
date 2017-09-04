package com.qrcomic.activity.reader;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Transformation;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import b.a.a.a.a.a.c;
import b.a.a.a.a.a.d;
import b.a.a.a.a.a.e;
import com.qrcomic.entity.ComicSectionPicInfo;
import com.qrcomic.f.f;
import com.qrcomic.f.f.a;
import com.qrcomic.manager.b;
import com.qrcomic.util.g;
import com.qrcomic.util.i;
import com.qrcomic.widget.reader.QRComicReaderBottomBar;
import com.qrcomic.widget.reader.QRComicReaderMenu;
import com.qrcomic.widget.reader.QRComicScrollReaderListView;
import java.text.SimpleDateFormat;

public class QRComicReadingLandActivity extends QRComicReadingBaseActivity implements OnClickListener {
    OnSeekBarChangeListener aW = new OnSeekBarChangeListener(this) {
        final /* synthetic */ QRComicReadingLandActivity a;

        {
            this.a = r1;
        }

        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            if (this.a.Z.r != null && this.a.Z.r.size() > 1) {
                this.a.T.setText(this.a.Z.o.c);
                this.a.S.setText((i + 1) + "/" + this.a.Z.r.size());
                if (g.a()) {
                    g.a("QRComicReadingLandActivity", g.d, " mCurrentImageTv " + this.a.S.getText());
                }
            }
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
            int progress = seekBar.getProgress();
            if (this.a.Z.C != progress) {
                if (this.a.Z.r.size() == 1 && progress == 1) {
                    this.a.ba.performClick();
                } else {
                    this.a.Z.d(progress);
                    if (!(this.a.ak == null || this.a.Z.r == null)) {
                        this.a.Z.B = ((ComicSectionPicInfo) this.a.Z.r.get(this.a.Z.C)).picId;
                        this.a.aj.a(this.a.Z.o.b, this.a.Z.C);
                        this.a.aj.e();
                    }
                }
            }
            if (this.a.Z != null && this.a.Z.o != null) {
                String str;
                if (this.a.Z.H == 2) {
                    str = "0";
                } else if (this.a.aF == 0) {
                    str = "2";
                } else {
                    str = "1";
                }
            }
        }
    };
    a<Integer> aX = new a<Integer>(this) {
        final /* synthetic */ QRComicReadingLandActivity a;

        {
            this.a = r1;
        }

        public void a(f<Integer> fVar, float f, Integer num, Transformation transformation) {
            if (!this.a.c) {
                return;
            }
            int intValue;
            if (fVar == this.a.h) {
                intValue = num.intValue() - this.a.au;
                this.a.au = num.intValue();
                RelativeLayout b = this.a.aY;
                if (this.a.aL) {
                    intValue = -intValue;
                }
                b.offsetTopAndBottom(intValue);
            } else if (fVar == this.a.i) {
                intValue = num.intValue() - this.a.av;
                this.a.av = num.intValue();
                QRComicReaderBottomBar qRComicReaderBottomBar = this.a.n;
                if (!this.a.aL) {
                    intValue = -intValue;
                }
                qRComicReaderBottomBar.offsetTopAndBottom(intValue);
            }
        }
    };
    private RelativeLayout aY;
    private TextView aZ;
    private TextView ba;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(b.a.a.a.a.a.f.qr_comic_land_reading_activity);
        getWindow().addFlags(1024);
        b(getWindow().getDecorView());
        getWindow().setBackgroundDrawable(null);
        aa();
        ab();
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    private void aa() {
        this.az = getResources().getDimensionPixelSize(c.land_top_bar_offset);
        this.aC = getResources().getDimensionPixelSize(c.land_bottom_bar_offset);
        this.aY = (RelativeLayout) findViewById(e.top_bar);
        this.n = (QRComicReaderBottomBar) findViewById(e.bottom_bar);
        this.n.a((QRComicReadingBaseActivity) this);
        this.aZ = (TextView) findViewById(e.pre_chapter);
        this.aZ.setOnClickListener(this.aQ);
        this.ba = (TextView) findViewById(e.next_chapter);
        this.ba.setOnClickListener(this.aQ);
        this.S = (TextView) findViewById(e.current_image);
        this.T = (TextView) findViewById(e.infotext);
        this.m = (TextView) findViewById(e.back);
        this.m.setOnClickListener(this.aQ);
        this.r = (ImageView) findViewById(e.more);
        this.r.setOnClickListener(this.aQ);
        this.s = (ImageView) findViewById(e.red_dot);
        b();
        this.t = (ImageView) findViewById(e.msg_red_dot);
        this.t.setVisibility(8);
        this.l = (SeekBar) findViewById(e.reading_progress_bar);
        this.l.setOnSeekBarChangeListener(this.aW);
        this.E = (ImageView) findViewById(e.download);
        this.E.setOnClickListener(this);
        this.E.setBackgroundResource(d.vip_comic_msg_white_bg);
        this.F = (LinearLayout) findViewById(e.section_selector);
        this.F.setOnClickListener(this);
        this.I = (LinearLayout) findViewById(e.reader_comic_comment);
        this.I.setOnClickListener(this);
        this.J = (TextView) this.I.findViewById(e.comment_count);
        this.G = (LinearLayout) findViewById(e.drag_progress);
        this.G.setOnClickListener(this);
        this.H = (LinearLayout) findViewById(e.reader_settings);
        this.H.setOnClickListener(this);
        this.ak = (QRComicScrollReaderListView) findViewById(e.reader_page);
        this.ak.setOnComicPageChangeListener(this.aO);
        this.ak.setOnComicTouchListener(this.aP);
        if (!(this.Z == null || this.Z.i == null)) {
            this.ak.setDividerHeight(this.Z.i.m == 1 ? 0 : 10);
        }
        this.aj = new com.qrcomic.widget.reader.c(this.ak, this, this.as);
        this.ak.setVisibility(8);
        D();
    }

    protected void h() {
        ac();
        if (this.Z != null && this.Z.i != null && System.currentTimeMillis() < this.Z.i.t() && c(this.Z.i.e())) {
            b.a().a("限时免费，" + i.b(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Long.valueOf(this.Z.i.t()))), 2);
        }
    }

    private void ab() {
        this.h = new f(Integer.valueOf(0), Integer.valueOf(this.az), this.aX);
        this.h.setDuration(200);
        this.h.setAnimationListener(this.aN);
        this.i = new f(Integer.valueOf(0), Integer.valueOf(this.aC), this.aX);
        this.i.setDuration(200);
    }

    private void ac() {
        y();
        this.ak.setVisibility(0);
        this.aj.a(this.Z.r, this.Z.o.b, this.Z.C);
        E();
        Q();
    }

    protected void e() {
    }

    protected void f() {
    }

    protected void g() {
    }

    public void i() {
        y();
        z();
        this.aY.setVisibility(0);
        this.aY.startAnimation(this.h);
        this.n.setBarrageBtnBgAlpha(0);
        this.n.startAnimation(this.i);
    }

    public void k() {
        this.aY.startAnimation(this.h);
        this.n.startAnimation(this.i);
        this.ak.setDrawingCacheEnabled(false);
    }

    public void Z() {
        w();
        if (this.o == null) {
            this.o = new QRComicReaderMenu(this, 1, this.N, new OnDismissListener(this) {
                final /* synthetic */ QRComicReadingLandActivity a;

                {
                    this.a = r1;
                }

                public void onDismiss(DialogInterface dialogInterface) {
                    if (this.a.P != null) {
                        this.a.P.setVisibility(8);
                    }
                }
            });
        }
        this.p = true;
        if (!(this.Z == null || this.Z.o == null)) {
            String str;
            if (this.Z.H == 2) {
                str = "0";
            } else if (this.aF == 0) {
                str = "2";
            } else {
                str = "1";
            }
        }
        this.P.setVisibility(0);
    }

    protected void v() {
        this.aY.setVisibility(8);
    }

    public void x() {
    }

    protected void M() {
    }

    public void u() {
    }

    public boolean l() {
        if (this.aY == null || this.aY.getVisibility() != 0) {
            return false;
        }
        return true;
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == e.section_selector) {
            V();
        } else if (id == e.drag_progress) {
        } else {
            if (id == e.reader_settings) {
                Z();
            } else if (id != e.download && id == e.reader_comic_comment) {
            }
        }
    }

    protected void m() {
        this.Z.d(0);
        this.l.setProgress(this.Z.C);
        this.aj.a(this.Z.o.b, 0);
    }

    protected void o() {
    }

    protected void n() {
        int i = 0;
        LayoutParams layoutParams = (LayoutParams) this.aY.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.topMargin = this.aL ? -this.az : 0;
            this.aY.setLayoutParams(layoutParams);
        }
        layoutParams = (LayoutParams) this.n.getLayoutParams();
        if (layoutParams != null) {
            if (this.aL) {
                i = -this.aC;
            }
            layoutParams.bottomMargin = i;
            this.n.setLayoutParams(layoutParams);
        }
    }

    protected void a(int i) {
        if (!super.O()) {
            this.Z.K = false;
        } else if (this.aj != null) {
            this.aj.a(true, "land handleRecommendPage");
        } else if (g.a()) {
            g.a("QRComicReadingLandActivity", g.d, "land handleRecommendPage , mQRComicScrollReaderHelper is null");
        }
    }

    @TargetApi(19)
    public void a(View view) {
        if (!this.aE || view == null) {
            getWindow().clearFlags(1024);
        } else {
            view.setSystemUiVisibility(5380);
        }
    }

    @TargetApi(19)
    public void b(View view) {
        if (!this.aE || view == null) {
            getWindow().addFlags(1024);
        } else {
            getWindow().getDecorView().setSystemUiVisibility(5894);
        }
    }

    public void p() {
    }

    public boolean q() {
        return false;
    }
}
