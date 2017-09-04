package com.qrcomic.activity.reader;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.e;
import android.text.TextUtils;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewStub;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import com.qrcomic.downloader.j;
import com.qrcomic.entity.ComicRecommendPageInfo;
import com.qrcomic.entity.ComicSectionPicInfo;
import com.qrcomic.entity.RecommendComicInfo;
import com.qrcomic.f.f;
import com.qrcomic.manager.QRComicManager;
import com.qrcomic.util.g;
import com.qrcomic.util.i;
import com.qrcomic.widget.reader.QRComicPagerLoading;
import com.qrcomic.widget.reader.QRComicReaderBottomBar;
import com.qrcomic.widget.reader.QRComicReaderMenu;
import com.qrcomic.widget.reader.QRComicReaderViewPager;
import com.qrcomic.widget.reader.QRComicScrollReaderListView;
import com.qrcomic.widget.reader.QRComicTouchImageView;
import com.sina.weibo.sdk.exception.WeiboAuthException;
import com.tencent.av.mediacodec.HWColorFormat;
import com.tencent.smtt.sdk.WebView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class QRComicReadingVerticalActivity extends QRComicReadingBaseActivity implements OnClickListener, com.qrcomic.widget.reader.QRComicPagerLoading.a {
    protected Context aW;
    public int aX = 8888;
    public List<View> aY = new ArrayList();
    OnSeekBarChangeListener aZ = new OnSeekBarChangeListener(this) {
        final /* synthetic */ QRComicReadingVerticalActivity a;

        {
            this.a = r1;
        }

        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            if (this.a.Z.r != null && this.a.Z.r.size() > 1) {
                this.a.T.setText(this.a.Z.o.c);
                this.a.S.setText((i + 1) + "/" + this.a.Z.r.size());
                if (g.a()) {
                    g.a("QRComicReadingVerticalActivity", g.d, " mCurrentImageTv " + this.a.S.getText());
                }
            }
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
            int progress = seekBar.getProgress();
            if (this.a.Z.C != progress) {
                if (this.a.Z.r.size() == 1 && progress == 1) {
                    this.a.be.performClick();
                } else {
                    this.a.Z.d(progress);
                    if (this.a.aF != 0) {
                        if (super.P()) {
                            this.a.Z.K = true;
                        } else {
                            this.a.Z.K = false;
                        }
                        this.a.aa();
                    } else if (!(this.a.ak == null || this.a.Z.r == null || this.a.Z.C >= this.a.Z.r.size())) {
                        this.a.Z.B = ((ComicSectionPicInfo) this.a.Z.r.get(this.a.Z.C)).picId;
                        this.a.aj.a(this.a.Z.o.b, this.a.Z.C);
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
    com.qrcomic.f.f.a<Integer> ba = new com.qrcomic.f.f.a<Integer>(this) {
        final /* synthetic */ QRComicReadingVerticalActivity a;

        {
            this.a = r1;
        }

        public void a(f<Integer> fVar, float f, Integer num, Transformation transformation) {
            int intValue;
            if (this.a.c) {
                int intValue2;
                if (fVar == this.a.h) {
                    intValue2 = num.intValue() - this.a.au;
                    this.a.au = num.intValue();
                    this.a.bc.offsetTopAndBottom(this.a.aL ? -intValue2 : intValue2);
                    if (g.a()) {
                        g.a("QRComicReadingVerticalActivity", g.d, " topAnimLastValue = " + this.a.au + " delta = " + intValue2 + " needDestroy =" + this.a.aL);
                    }
                } else if (fVar == this.a.i) {
                    intValue2 = num.intValue() - this.a.av;
                    this.a.av = num.intValue();
                    this.a.n.offsetTopAndBottom(this.a.aL ? intValue2 : -intValue2);
                    g.a("QRComicReadingVerticalActivity", g.d, " bottomAnimLastValue = " + this.a.av + " delta = " + intValue2 + " needDestroy =" + this.a.aL);
                } else if (fVar == this.a.j) {
                    intValue = num.intValue() - this.a.ax;
                    if (this.a.aL) {
                        intValue = -intValue;
                    }
                    if (g.a()) {
                        g.a("QRComicReadingVerticalActivity", g.d, " lightAnimLastValue = " + this.a.ax + " delta = " + intValue + " needDestroy =" + this.a.aL);
                    }
                    this.a.ax = num.intValue();
                    this.a.K.offsetTopAndBottom(intValue);
                }
            }
            if (this.a.d && fVar == this.a.k) {
                intValue = num.intValue() - this.a.ay;
                if (this.a.aM) {
                    intValue = -intValue;
                }
                this.a.ay = num.intValue();
                this.a.Q.offsetTopAndBottom(intValue);
                if (g.a()) {
                    g.a("QRComicReadingVerticalActivity", g.d, " mProgressAnim offset = " + intValue);
                }
            }
        }
    };
    private d bb;
    private LinearLayout bc;
    private TextView bd;
    private TextView be;
    private a bf;
    private com.qrcomic.widget.reader.QRComicReaderViewPager.a bg;
    private j bh = new j(this) {
        final /* synthetic */ QRComicReadingVerticalActivity a;

        {
            this.a = r1;
        }

        public void a(ComicSectionPicInfo comicSectionPicInfo, long j, long j2) {
            if (!(comicSectionPicInfo == null || comicSectionPicInfo.bitmap == null || comicSectionPicInfo.bitmap.isRecycled())) {
                comicSectionPicInfo.mState = 0;
            }
            for (View tag : this.a.aY) {
                b bVar = (b) tag.getTag();
                if (!(bVar == null || comicSectionPicInfo == null || bVar.d == null || bVar.e == null || !bVar.d.equals(comicSectionPicInfo.picId) || !bVar.e.equals(comicSectionPicInfo.sectionId) || bVar.f != comicSectionPicInfo.pagerIndex)) {
                    if (comicSectionPicInfo.mComicRecommendPageInfo != null) {
                        this.a.a(bVar, comicSectionPicInfo);
                    } else {
                        this.a.a(bVar, comicSectionPicInfo, false);
                    }
                    if (comicSectionPicInfo.index == this.a.Z.C && comicSectionPicInfo.sectionId.equals(this.a.Z.o.b)) {
                        this.a.Z.a(comicSectionPicInfo);
                    }
                }
            }
        }

        public void a(ComicSectionPicInfo comicSectionPicInfo, String str) {
        }

        public void a(ComicSectionPicInfo comicSectionPicInfo, int i, String str) {
            if (comicSectionPicInfo != null) {
                comicSectionPicInfo.mState = 1;
                this.a.b(comicSectionPicInfo);
            }
        }
    };

    public class a implements e {
        final /* synthetic */ QRComicReadingVerticalActivity a;
        private int b = this.a.al.getScrollX();
        private ComicSectionPicInfo c;

        public a(QRComicReadingVerticalActivity qRComicReadingVerticalActivity) {
            this.a = qRComicReadingVerticalActivity;
        }

        public void a(ComicSectionPicInfo comicSectionPicInfo) {
            this.c = comicSectionPicInfo;
        }

        public void onPageScrolled(int i, float f, int i2) {
            int scrollX = this.a.al.getScrollX();
            int i3 = scrollX - this.b;
            this.b = scrollX;
        }

        public void onPageSelected(int i) {
            if (g.a()) {
                g.a("QRComicReadingVerticalActivity", g.d, "on page selected position = " + i);
            }
            this.a.e(i);
            if (this.a.n != null) {
                this.a.n.c();
            }
        }

        public void onPageScrollStateChanged(int i) {
            if (i == 0) {
                try {
                    if (this.a.Z.C >= 0 && this.a.Z.C < this.a.Z.r.size()) {
                        ComicSectionPicInfo comicSectionPicInfo = (ComicSectionPicInfo) this.a.Z.r.get(this.a.Z.C);
                        if (!comicSectionPicInfo.equals(this.c)) {
                            a(comicSectionPicInfo);
                        }
                    } else if (!((ComicSectionPicInfo) this.a.Z.r.get(this.a.Z.r.size() - 1)).equals(this.c)) {
                    }
                    this.a.ap = false;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class b {
        public QRComicTouchImageView a;
        public QRComicPagerLoading b;
        public ComicSectionPicInfo c;
        public String d;
        public String e;
        public int f;
        public View g;
        public View h;
        public ViewStub i;
        public View j;
        public c k;
    }

    public static class c {
        public TextView a;
        public TextView b;
        public Button c;
        public TextView d;
        public TextView e;
        public TextView f;
        public ImageView g;
        public ImageView h;
        public ImageView i;
    }

    public class d extends PagerAdapter {
        final /* synthetic */ QRComicReadingVerticalActivity a;
        private com.qrcomic.widget.b b = new com.qrcomic.widget.b();

        public d(QRComicReadingVerticalActivity qRComicReadingVerticalActivity) {
            this.a = qRComicReadingVerticalActivity;
        }

        public int a() {
            return Integer.MAX_VALUE;
        }

        public int a(Object obj) {
            return -2;
        }

        public boolean a(View view, Object obj) {
            return view == obj;
        }

        public Object a(ViewGroup viewGroup, int i) {
            ComicSectionPicInfo comicSectionPicInfo;
            View view;
            b bVar;
            int abs = Math.abs(this.a.aX - i);
            if (this.a.aX <= i || this.a.Z.r == null) {
                if (this.a.aX >= i || this.a.Z.r == null) {
                    if (this.a.Z.r != null) {
                        comicSectionPicInfo = (ComicSectionPicInfo) this.a.Z.r.get(this.a.Z.C % this.a.Z.r.size());
                    }
                    comicSectionPicInfo = null;
                } else if (this.a.Z.C + abs < this.a.Z.r.size()) {
                    comicSectionPicInfo = (ComicSectionPicInfo) this.a.Z.r.get((abs + this.a.Z.C) % this.a.Z.r.size());
                } else if (!this.a.Z.d(this.a.Z.q) || this.a.Z.t == null || this.a.Z.t.size() <= 0) {
                    if (!this.a.Z.J && abs + this.a.Z.C == this.a.Z.r.size() && this.a.Z.E + 1 >= this.a.Z.u.size()) {
                        ComicSectionPicInfo a = this.a.d("viewPager instantiateItem");
                        if (g.a()) {
                            g.a("QRComicReadingVerticalActivity", g.d, "instantiateItem , add RecommendPage");
                            comicSectionPicInfo = a;
                        } else {
                            comicSectionPicInfo = a;
                        }
                    }
                    comicSectionPicInfo = null;
                } else {
                    comicSectionPicInfo = (ComicSectionPicInfo) this.a.Z.t.get(0);
                }
            } else if (this.a.Z.C - abs < 0) {
                if (this.a.Z.d(this.a.Z.p) && this.a.Z.s != null && this.a.Z.s.size() > 0) {
                    comicSectionPicInfo = (ComicSectionPicInfo) this.a.Z.s.get(this.a.Z.s.size() - 1);
                }
                comicSectionPicInfo = null;
            } else {
                comicSectionPicInfo = this.a.Z.C > this.a.Z.r.size() ? (ComicSectionPicInfo) this.a.Z.r.get(this.a.Z.r.size() - 1) : (ComicSectionPicInfo) this.a.Z.r.get((this.a.Z.C - abs) % this.a.Z.r.size());
            }
            View a2 = this.b.a();
            if (a2 == null) {
                b bVar2 = new b();
                View inflate = this.a.getLayoutInflater().inflate(b.a.a.a.a.a.f.qr_comic_fragment, null);
                bVar2.a = (QRComicTouchImageView) inflate.findViewById(b.a.a.a.a.a.e.img_view);
                bVar2.a.setAttachedActivity(this.a);
                bVar2.a.setOnComicTouchListener(this.a.aP);
                bVar2.b = (QRComicPagerLoading) inflate.findViewById(b.a.a.a.a.a.e.loading_layout);
                bVar2.b.setAttachedActivity(this.a);
                bVar2.b.setTag(bVar2);
                bVar2.b.setOnClickForSubText(this.a);
                bVar2.h = inflate.findViewById(b.a.a.a.a.a.e.recommend_container);
                bVar2.i = (ViewStub) inflate.findViewById(b.a.a.a.a.a.e.recommend_layout);
                bVar2.g = inflate;
                inflate.setTag(bVar2);
                view = inflate;
                bVar = bVar2;
            } else {
                bVar = (b) a2.getTag();
                view = a2;
            }
            this.a.a(bVar);
            this.a.ah();
            if (view.getParent() != viewGroup && i < a()) {
                viewGroup.addView(view);
            }
            bVar.f = i;
            this.a.aY.set(i % this.a.aY.size(), view);
            if (comicSectionPicInfo != null) {
                if (comicSectionPicInfo.mComicRecommendPageInfo != null) {
                    this.a.a(bVar, comicSectionPicInfo);
                } else {
                    this.a.a(bVar, comicSectionPicInfo, true);
                }
            }
            return view;
        }

        public void a(ViewGroup viewGroup, int i, Object obj) {
            if (obj != null) {
                View view = (View) obj;
                viewGroup.removeView(view);
                this.b.a(view);
                if (view.getTag() != null && (view.getTag() instanceof b)) {
                    b bVar = (b) view.getTag();
                }
            }
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (this.a != null) {
            setContentView(b.a.a.a.a.a.f.qr_comic_portrait_reading_activity);
            b(getWindow().getDecorView());
            getWindow().setBackgroundDrawable(null);
            getWindow().setSoftInputMode(32);
            ab();
            ad();
        }
    }

    private void ab() {
        this.aW = this;
        C();
        int G = G();
        this.az = getResources().getDimensionPixelSize(b.a.a.a.a.a.c.portrait_top_bar_height) + G;
        this.aC = getResources().getDimensionPixelSize(b.a.a.a.a.a.c.portrait_bottom_bar_offset);
        this.aA = getResources().getDimensionPixelOffset(b.a.a.a.a.a.c.portrait_top_light_btn_offset);
        this.aB = getResources().getDimensionPixelOffset(b.a.a.a.a.a.c.portrait_bottom_progress_view_height);
        this.Q = (RelativeLayout) findViewById(b.a.a.a.a.a.e.page_change_layout);
        this.P = findViewById(b.a.a.a.a.a.e.status_bar_top);
        this.O = findViewById(b.a.a.a.a.a.e.status_bar);
        LayoutParams layoutParams = this.O.getLayoutParams();
        layoutParams.height = G;
        this.O.setLayoutParams(layoutParams);
        LayoutParams layoutParams2 = this.P.getLayoutParams();
        layoutParams.height = G;
        this.P.setLayoutParams(layoutParams2);
        this.K = (LinearLayout) findViewById(b.a.a.a.a.a.e.night_mode);
        this.L = (ImageView) findViewById(b.a.a.a.a.a.e.night_mode_btn);
        this.K.setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ QRComicReadingVerticalActivity a;

            {
                this.a = r1;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    if (this.a.aG == 1) {
                        this.a.L.setImageResource(b.a.a.a.a.a.d.comic_btn_night_down);
                    } else {
                        this.a.L.setImageResource(b.a.a.a.a.a.d.comic_btn_day_down);
                    }
                    this.a.a.f().c().a("event_F279", null, this.a.getApplicationContext());
                } else if (motionEvent.getAction() == 1) {
                    if (this.a.aG == 1) {
                        this.a.b(0);
                        this.a.L.setImageResource(b.a.a.a.a.a.d.comic_btn_day_up);
                    } else {
                        this.a.b(1);
                        this.a.L.setImageResource(b.a.a.a.a.a.d.comic_btn_night_up);
                    }
                    this.a.w();
                }
                return true;
            }
        });
        e();
        this.bc = (LinearLayout) findViewById(b.a.a.a.a.a.e.top_bar);
        f();
        this.n = (QRComicReaderBottomBar) findViewById(b.a.a.a.a.a.e.bottom_bar);
        this.n.a((QRComicReadingBaseActivity) this);
        this.S = (TextView) findViewById(b.a.a.a.a.a.e.current_image);
        this.T = (TextView) findViewById(b.a.a.a.a.a.e.infotext);
        this.U = (ViewGroup) findViewById(b.a.a.a.a.a.e.progress_bar_container);
        this.R = (ViewGroup) findViewById(b.a.a.a.a.a.e.popup_box_view);
        this.E = (ImageView) findViewById(b.a.a.a.a.a.e.download);
        this.E.setOnClickListener(this);
        this.m = (TextView) findViewById(b.a.a.a.a.a.e.back);
        this.m.setOnClickListener(this.aQ);
        this.r = (ImageView) findViewById(b.a.a.a.a.a.e.more);
        this.r.setOnClickListener(this.aQ);
        this.s = (ImageView) findViewById(b.a.a.a.a.a.e.red_dot);
        b();
        this.t = (ImageView) findViewById(b.a.a.a.a.a.e.msg_red_dot);
        this.t.setVisibility(8);
        this.bd = (TextView) findViewById(b.a.a.a.a.a.e.pre_chapter);
        this.bd.setOnClickListener(this.aQ);
        this.be = (TextView) findViewById(b.a.a.a.a.a.e.next_chapter);
        this.be.setOnClickListener(this.aQ);
        this.l = (SeekBar) findViewById(b.a.a.a.a.a.e.reading_progress_bar);
        this.l.setOnSeekBarChangeListener(this.aZ);
        this.F = (LinearLayout) findViewById(b.a.a.a.a.a.e.section_selector);
        this.F.setOnClickListener(this);
        this.G = (LinearLayout) findViewById(b.a.a.a.a.a.e.drag_progress);
        this.G.setOnClickListener(this);
        this.H = (LinearLayout) findViewById(b.a.a.a.a.a.e.reader_settings);
        this.H.setOnClickListener(this);
        this.I = (LinearLayout) findViewById(b.a.a.a.a.a.e.reader_comic_comment);
        this.I.setOnClickListener(this);
        this.J = (TextView) this.I.findViewById(b.a.a.a.a.a.e.comment_count);
        D();
        u();
        ac();
        g();
    }

    private void ac() {
        if (this.V == -1 && VERSION.SDK_INT >= 21) {
            TypedArray obtainStyledAttributes = getTheme().obtainStyledAttributes(new int[]{16843828});
            this.V = obtainStyledAttributes.getColor(0, HWColorFormat.COLOR_FormatVendorStartUnused);
            obtainStyledAttributes.recycle();
        }
        if (this.V != -1 && this.V != 0) {
            this.bc.getChildAt(0).setVisibility(0);
            this.bc.getChildAt(0).setBackgroundColor(this.V);
            this.P.setBackgroundColor(this.V);
        }
    }

    protected void f() {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.bc.getLayoutParams();
        layoutParams.topMargin = -this.az;
        this.bc.setLayoutParams(layoutParams);
    }

    protected void e() {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.K.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.topMargin = -this.aA;
            this.K.setLayoutParams(layoutParams);
        }
    }

    protected void g() {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.n.getLayoutParams();
        layoutParams.bottomMargin = (int) (-((long) (this.aC + this.aD)));
        this.n.setLayoutParams(layoutParams);
        layoutParams = (FrameLayout.LayoutParams) this.Q.getLayoutParams();
        layoutParams.bottomMargin = (int) (-((long) (this.aB + this.aD)));
        this.Q.setLayoutParams(layoutParams);
    }

    private void ad() {
        this.h = new f(Integer.valueOf(0), Integer.valueOf(this.az), this.ba);
        this.h.setDuration(350);
        this.h.setAnimationListener(this.aN);
        this.i = new f(Integer.valueOf(0), Integer.valueOf(this.aC + this.aD), this.ba);
        this.i.setDuration(350);
        this.j = new f(Integer.valueOf(0), Integer.valueOf((int) (((float) this.aA) + com.qrcomic.util.a.a(72, getResources()))), this.ba);
        this.j.setDuration(350);
        this.k = new f(Integer.valueOf(0), Integer.valueOf(this.aB + this.aD), this.ba);
        this.k.setAnimationListener(this.aN);
        this.k.setDuration(350);
    }

    public void c(int i) {
        if (i != this.aD) {
            this.aD = i;
            this.i = new f(Integer.valueOf(0), Integer.valueOf(this.aC + i), this.ba);
            this.i.setDuration(350);
            this.k = new f(Integer.valueOf(0), Integer.valueOf(this.aB + this.aD), this.ba);
            this.k.setAnimationListener(this.aN);
            this.k.setDuration(350);
        }
    }

    protected void h() {
        ae();
        if (!TextUtils.isEmpty(getIntent().getStringExtra("key_share_flag"))) {
            this.aR = true;
            J();
        }
        this.an.postDelayed(new Runnable(this) {
            final /* synthetic */ QRComicReadingVerticalActivity a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.I();
            }
        }, 2000);
        if (this.Z != null && this.Z.i != null && System.currentTimeMillis() < this.Z.i.t() && c(this.Z.i.e())) {
            com.qrcomic.manager.b.a().a("限时免费，" + i.b(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Long.valueOf(this.Z.i.t()))), 2);
        }
    }

    private void ae() {
        y();
        H();
        d(this.aF);
        Q();
    }

    public void d(int i) {
        if (this.Z != null) {
            this.aF = i;
            int childCount;
            int i2;
            Object tag;
            if (i == 0) {
                boolean z;
                if (this.al != null) {
                    this.al.setVisibility(8);
                    childCount = this.al.getChildCount();
                    for (i2 = 0; i2 < childCount; i2++) {
                        tag = this.al.getChildAt(i2).getTag();
                        if (tag instanceof b) {
                            ((b) tag).a.setImageDrawable(null);
                            if (g.a()) {
                                g.a("QRComicReadingVerticalActivity", g.d, "回收 viewReaderPager 图片资源 child index = " + i2);
                            }
                        }
                    }
                }
                if (this.ak == null) {
                    this.ak = (QRComicScrollReaderListView) findViewById(b.a.a.a.a.a.e.scroll_reader_page);
                    this.ak.setOnComicPageChangeListener(this.aO);
                    this.ak.setOnComicTouchListener(this.aP);
                    if (this.Z.i != null) {
                        this.ak.setDividerHeight(this.Z.i.m == 1 ? 0 : 10);
                    }
                    this.aj = new com.qrcomic.widget.reader.c(this.ak, this, this.as);
                    z = true;
                } else {
                    z = false;
                }
                if (this.ao) {
                    this.ak.setAdapter(null);
                    this.aj = new com.qrcomic.widget.reader.c(this.ak, this, this.as);
                    this.ao = false;
                    z = true;
                }
                this.ak.setVisibility(0);
                if (this.Z == null || this.Z.r == null || this.Z.o == null || this.Z.o.b == null) {
                    a("数据不全,请重试", -1, true);
                } else {
                    if (z) {
                        this.aj.a(this.Z.r, this.Z.o.b, this.Z.C);
                    } else {
                        this.aj.a(this.Z.o.b, this.Z.C);
                    }
                    E();
                }
            } else if (i == 1) {
                E();
                if (this.ak != null) {
                    this.ak.setVisibility(8);
                    childCount = this.ak.getChildCount();
                    for (i2 = 0; i2 < childCount; i2++) {
                        tag = this.ak.getChildAt(i2).getTag();
                        if (tag instanceof com.qrcomic.widget.reader.c.c) {
                            ((com.qrcomic.widget.reader.c.c) tag).a.setImageDrawable(null);
                            if (g.a()) {
                                g.a("QRComicReadingVerticalActivity", g.d, "回收 scrollReaderPager 图片资源 childe index = " + i2);
                            }
                        }
                    }
                }
                if (this.al == null) {
                    this.al = (QRComicReaderViewPager) findViewById(b.a.a.a.a.a.e.view_reader_pager);
                    this.al.setAttachedActivity(this);
                    this.aY.add(new View(this.aW));
                    this.aY.add(new View(this.aW));
                    this.aY.add(new View(this.aW));
                    this.bb = new d(this);
                    this.al.setPageMargin(10);
                    this.al.setAdapter(this.bb);
                    this.bf = new a(this);
                    this.bg = new com.qrcomic.widget.reader.QRComicReaderViewPager.a(this) {
                        final /* synthetic */ QRComicReadingVerticalActivity a;

                        {
                            this.a = r1;
                        }

                        public void a() {
                            this.a.b(false);
                        }

                        public void b() {
                            this.a.c(false);
                        }
                    };
                    this.al.setOnPageChangeListener(this.bf);
                    this.al.setPageChangeListener(this.bg);
                } else {
                    aa();
                }
                this.al.setCurrentItem(this.aX);
                this.al.setVisibility(0);
                this.bf.a((ComicSectionPicInfo) this.Z.r.get(this.Z.C));
                if (this.aF == 1) {
                    this.aq = true;
                }
            }
            com.qrcomic.util.h.c.c(this.aF);
            Q();
        } else if (g.a()) {
            g.c("QRComicReadingVerticalActivity", g.d, "rs is null when calling switchReadMode!!!!");
        }
    }

    private ComicSectionPicInfo d(String str) {
        if (g.a()) {
            g.a("QRComicReadingVerticalActivity", g.d, "getRecommendPageInPagerMode , from " + str);
        }
        ComicSectionPicInfo i = this.Z.i();
        if (i == null && this.Z.L == null) {
            this.Z.l();
        }
        return i;
    }

    protected void a(int i) {
        if (this.Z != null && this.Z.u != null) {
            if (this.aF == 1 && this.Z.r != null && !this.Z.J && this.Z.E + 1 >= this.Z.u.size()) {
                ComicSectionPicInfo d = d("portrait handleRecommendPage from = " + i);
                if (this.Z.C == this.Z.r.size()) {
                    if (d != null) {
                        a(this.aX, d);
                        if (i != 1) {
                        }
                    }
                } else if (this.Z.C + 1 == this.Z.r.size()) {
                    this.Z.K = false;
                    a(this.aX + 1, d);
                } else {
                    this.Z.K = false;
                }
            } else if (!super.O()) {
                this.Z.K = false;
            } else if (this.aj != null) {
                this.aj.a(true, "portrait handleRecommendPage");
            } else if (g.a()) {
                g.a("QRComicReadingVerticalActivity", g.d, "portrait handleRecommendPage , mQRComicScrollReaderHelper is null");
            }
        }
    }

    @TargetApi(19)
    public void a(View view) {
        com.qrcomic.util.a.a((Activity) this, false);
    }

    @TargetApi(19)
    public void b(View view) {
        com.qrcomic.util.a.a((Activity) this, true);
    }

    public boolean q() {
        return this.Q != null && this.Q.getVisibility() == 0;
    }

    public void p() {
        this.aM = true;
        this.Q.setVisibility(0);
        this.Q.startAnimation(this.k);
        this.P.setVisibility(0);
    }

    public void x() {
        if (!this.c) {
            this.aM = false;
            this.Q.startAnimation(this.k);
            this.P.setVisibility(8);
        }
    }

    public boolean l() {
        if (this.bc == null || this.bc.getVisibility() != 0) {
            return false;
        }
        return true;
    }

    public void i() {
        a(false);
        y();
        z();
        this.bc.setVisibility(0);
        this.n.setBarrageBtnBgAlpha(0);
        this.n.startAnimation(this.i);
        this.bc.startAnimation(this.h);
        this.K.setVisibility(0);
        this.K.startAnimation(this.j);
        this.a.f().c().a("event_F273", null, getApplicationContext());
    }

    public void k() {
        j();
        a(true);
        this.bc.startAnimation(this.h);
        this.n.startAnimation(this.i);
        if (this.aF == 0) {
            this.ak.setDrawingCacheEnabled(false);
        } else {
            this.al.setDrawingCacheEnabled(false);
        }
        this.K.startAnimation(this.j);
    }

    public void Z() {
        w();
        if (this.o == null) {
            this.o = new QRComicReaderMenu(this, 0, this.N, new OnDismissListener(this) {
                final /* synthetic */ QRComicReadingVerticalActivity a;

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
        if (this.P != null) {
            this.P.setVisibility(0);
        }
    }

    public void a(boolean z) {
        if (this.al != null) {
            this.al.setCanScroll(z);
        }
    }

    protected void v() {
        this.bc.setVisibility(8);
        this.K.setVisibility(8);
        if (this.aF == 0) {
            this.ak.destroyDrawingCache();
            this.ak.setDrawingCacheEnabled(false);
            return;
        }
        this.al.destroyDrawingCache();
        this.al.setDrawingCacheEnabled(false);
    }

    public void u() {
        super.u();
        this.bc.getChildAt(1).setBackgroundResource(this.M.d(false));
        this.m.setBackgroundResource(this.M.a(false));
        this.r.setBackgroundResource(this.M.c(false));
        this.E.setImageResource(this.M.b(false));
        this.L.setImageResource(this.aG == 1 ? b.a.a.a.a.a.d.comic_btn_night_up : b.a.a.a.a.a.d.comic_btn_day_up);
        this.be.setTextColor(getResources().getColorStateList(this.N.e(false)));
        this.bd.setTextColor(getResources().getColorStateList(this.N.e(false)));
        this.U.setBackgroundResource(this.M.d(false));
        this.S.setTextColor(getResources().getColor(this.N.e(false)));
        this.T.setTextColor(getResources().getColor(this.N.e(false)));
        this.R.setBackgroundResource(this.N.f(false));
        int[] j = this.N.j(false);
        this.l.setThumb(getResources().getDrawable(j[0]));
        Drawable drawable = getResources().getDrawable(j[1]);
        this.l.setProgressDrawable(drawable);
        if (this.l.getTag(b.a.a.a.a.a.g.key_id) != null) {
            int a = (int) (((float) com.qrcomic.util.c.a.a(this, 3)) / 4.0f);
            int height = (this.l.getHeight() / 2) - a;
            drawable.setBounds(0, height, this.l.getProgressDrawable().getBounds().right, (a * 3) + height);
            drawable.invalidateSelf();
        }
        this.l.setTag(b.a.a.a.a.a.g.key_id, "FUCK_THE_Android_SEEK_BAR");
    }

    public void aa() {
        for (View view : this.aY) {
            if (!(view == null || view.getTag() == null)) {
                a((b) view.getTag());
            }
        }
        if (this.Z.r != null && this.Z.C >= 0 && this.Z.C < this.Z.r.size()) {
            ComicSectionPicInfo comicSectionPicInfo = (ComicSectionPicInfo) this.Z.r.get(this.Z.C);
            this.Z.B = comicSectionPicInfo.picId;
            a(this.aX, comicSectionPicInfo);
            if (this.Z.C >= 1) {
                a(this.aX - 1, (ComicSectionPicInfo) this.Z.r.get(this.Z.C - 1));
            } else if (this.Z.s != null && this.Z.s.size() > 0 && this.Z.d(this.Z.p)) {
                a(this.aX - 1, (ComicSectionPicInfo) this.Z.s.get(this.Z.s.size() - 1));
            }
            if (this.Z.C + 1 < this.Z.r.size()) {
                a(this.aX + 1, (ComicSectionPicInfo) this.Z.r.get(this.Z.C + 1));
            } else if (this.Z.t != null && this.Z.t.size() > 0 && this.Z.d(this.Z.q)) {
                a(this.aX + 1, (ComicSectionPicInfo) this.Z.t.get(0));
            } else if (!this.Z.J && this.Z.C + 1 == this.Z.r.size() && this.Z.E + 1 >= this.Z.u.size()) {
                a(this.aX + 1, d("jumpToViewPagerComic"));
                if (g.a()) {
                    g.a("QRComicReadingVerticalActivity", g.d, "jumpToViewPagerComic , handle RecommendPage");
                }
            }
        }
    }

    private void a(int i, ComicSectionPicInfo comicSectionPicInfo) {
        if (comicSectionPicInfo != null && this.aY.size() > 0 && i > -1) {
            b bVar = (b) ((View) this.aY.get(i % this.aY.size())).getTag();
            if (bVar == null) {
                return;
            }
            if (comicSectionPicInfo.mComicRecommendPageInfo != null) {
                a(bVar, comicSectionPicInfo);
            } else if (bVar.a != null) {
                bVar.a.b();
                a(bVar, comicSectionPicInfo, true);
            }
        }
    }

    private void e(int i) {
        if (this.Z.r != null) {
            if (this.aX < i) {
                this.Z.d(this.Z.C + 1);
            } else if (this.aX > i) {
                this.Z.d(this.Z.C - 1);
            }
            this.aX = i;
            this.Z.K = false;
            if (this.Z.C < 0) {
                if (ag()) {
                    b(true);
                }
            } else if (this.Z.C < this.Z.r.size()) {
                this.ad++;
                this.ag++;
                ComicSectionPicInfo comicSectionPicInfo = (ComicSectionPicInfo) this.Z.r.get(this.Z.C);
                this.Z.B = comicSectionPicInfo.picId;
                this.T.setText(this.Z.o.c);
                this.S.setText((this.Z.C + 1) + "/" + this.Z.r.size());
                if (g.a()) {
                    g.a("QRComicReadingVerticalActivity", g.d, " mCurrentImageTv " + this.S.getText());
                }
                this.l.setProgress(this.Z.C);
                if (comicSectionPicInfo.bitmap == null || comicSectionPicInfo.bitmap.isRecycled()) {
                    a(this.aX, comicSectionPicInfo);
                }
                b(this.Z.n, this.Z.o.b);
            } else if (super.P()) {
                this.Z.K = true;
                if (d("changePage ,check is has ComicRecommendPageInfo") == null) {
                    L();
                    this.al.setCurrentItem(this.aX - 1);
                }
            } else if (af()) {
                c(true);
            }
        }
    }

    protected void M() {
        try {
            if (this.Z != null) {
                if (this.Z.E <= 0) {
                    this.bd.setEnabled(false);
                } else {
                    this.bd.setEnabled(true);
                }
            }
            if (this.Z != null && this.Z.u != null) {
                if (this.Z.E >= this.Z.u.size() - 1) {
                    this.be.setEnabled(false);
                } else {
                    this.be.setEnabled(true);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean af() {
        if (this.Z.E + 1 < this.Z.u.size() || this.Z.J) {
            return true;
        }
        L();
        this.al.setCurrentItem(this.aX - 1);
        return false;
    }

    private boolean ag() {
        if (this.Z.E - 1 >= 0) {
            return true;
        }
        K();
        this.al.setCurrentItem(this.aX + 1);
        return false;
    }

    private void b(final boolean z) {
        if (g.a()) {
            g.a("QRComicReadingVerticalActivity", g.d, "interceptPreSectionTouchEvent fromPageChange = " + z);
        }
        c e = c.e().b().c().a().e();
        if (z) {
            this.Z.d(this.Z.C + 1);
        }
        this.Z.a(e, new com.qrcomic.activity.reader.a.f(this) {
            final /* synthetic */ QRComicReadingVerticalActivity b;

            public void a() {
                if (!z) {
                    this.b.Z.d(this.b.Z.C + 1);
                    this.b.al.setCurrentItem(this.b.aX - 1);
                }
                this.b.aa();
                this.b.b(this.b.Z.o.c);
                this.b.a(this.b.Z.o);
                QRComicReadingVerticalActivity qRComicReadingVerticalActivity = this.b;
                qRComicReadingVerticalActivity.ad++;
                qRComicReadingVerticalActivity = this.b;
                qRComicReadingVerticalActivity.ag++;
                this.b.z();
                this.b.b(this.b.Z.n, this.b.Z.o.b);
            }

            public void b() {
                if (z) {
                    this.b.Z.d(this.b.Z.C - 1);
                    this.b.al.setCurrentItem(this.b.aX + 1);
                }
            }

            public void c() {
                if (!this.b.Z.A) {
                    this.b.an.postDelayed(new Runnable(this) {
                        final /* synthetic */ AnonymousClass7 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.b.a(this.a.b.Z.p, 1);
                        }
                    }, 0);
                    if (z) {
                        this.b.Z.d(this.b.Z.C - 1);
                        this.b.al.setCurrentItem(this.b.aX + 1);
                    }
                }
            }
        });
    }

    private void c(final boolean z) {
        if (g.a()) {
            g.a("QRComicReadingVerticalActivity", g.d, "interceptNextSectionTouchEvent fromPageChange = " + z);
        }
        c e = c.e().b().c().a().e();
        if (z) {
            this.Z.d(this.Z.C - 1);
        }
        this.Z.b(e, new com.qrcomic.activity.reader.a.f(this) {
            final /* synthetic */ QRComicReadingVerticalActivity b;

            public void a() {
                if (!z) {
                    this.b.Z.d(this.b.Z.C - 1);
                    this.b.al.setCurrentItem(this.b.aX + 1);
                }
                this.b.aa();
                this.b.b(this.b.Z.o.c);
                this.b.a(this.b.Z.o);
                QRComicReadingVerticalActivity qRComicReadingVerticalActivity = this.b;
                qRComicReadingVerticalActivity.ad++;
                qRComicReadingVerticalActivity = this.b;
                qRComicReadingVerticalActivity.ag++;
                this.b.z();
                this.b.b(this.b.Z.n, this.b.Z.o.b);
            }

            public void b() {
                this.b.L();
                if (z) {
                    this.b.Z.d(this.b.Z.C + 1);
                    this.b.al.setCurrentItem(this.b.aX - 1);
                }
            }

            public void c() {
                if (!this.b.Z.A) {
                    this.b.an.postDelayed(new Runnable(this) {
                        final /* synthetic */ AnonymousClass8 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.b.a(this.a.b.Z.q, 2);
                        }
                    }, 0);
                    if (z) {
                        this.b.Z.d(this.b.Z.C + 1);
                        this.b.al.setCurrentItem(this.b.aX - 1);
                    }
                }
            }
        });
    }

    private void a(b bVar) {
        bVar.a.setImageBitmap(null);
        bVar.a.setVisibility(8);
        bVar.b.setVisibility(0);
        bVar.b.setText("");
        bVar.b.setSubTextVisible(8);
        if (bVar.h != null) {
            bVar.h.setVisibility(8);
        }
    }

    private void ah() {
        for (View tag : this.aY) {
            b bVar = (b) tag.getTag();
            if (!(bVar == null || bVar.a == null)) {
                bVar.a.b();
            }
        }
    }

    private void a(b bVar, ComicSectionPicInfo comicSectionPicInfo, boolean z) {
        if (bVar != null && comicSectionPicInfo != null) {
            if (!(bVar.c == null || bVar.c == comicSectionPicInfo)) {
                a(comicSectionPicInfo, bVar.c, null);
            }
            if (bVar.a != null && bVar.a.getVisibility() == 0) {
                bVar.a.setVisibility(8);
            }
            if (bVar.b != null && bVar.b.getVisibility() == 0) {
                bVar.b.setVisibility(8);
            }
            if (bVar.g != null) {
                bVar.g.setBackgroundColor(WebView.NIGHT_MODE_COLOR);
            }
            if (bVar.h != null && bVar.h.getVisibility() == 0) {
                bVar.h.setVisibility(8);
            }
            bVar.d = comicSectionPicInfo.picId;
            bVar.e = comicSectionPicInfo.sectionId;
            bVar.c = comicSectionPicInfo;
            if (this.Z != null && bVar.e != null) {
                com.qrcomic.entity.f b = this.Z.b(bVar.e);
                if (b != null && bVar.a != null && bVar.b != null) {
                    comicSectionPicInfo.pagerIndex = bVar.f;
                    if (b.t == 0) {
                        if (comicSectionPicInfo.bitmap == null || comicSectionPicInfo.bitmap.isRecycled()) {
                            bVar.b.a(comicSectionPicInfo);
                            bVar.b.setTextSize(getResources().getDimensionPixelSize(b.a.a.a.a.a.c.main_text_size));
                            bVar.b.setVisibility(0);
                            bVar.b.setSubTextVisible(0);
                            bVar.b.setText(String.format(getResources().getString(b.a.a.a.a.a.g.current_pic_txt), new Object[]{String.valueOf(comicSectionPicInfo.index + 1)}));
                            if (bVar.c.mState == 1) {
                                bVar.b.setSubText("加载失败, 点击重试");
                            } else {
                                bVar.b.setSubText("图片加载中");
                            }
                        } else {
                            bVar.a.setVisibility(0);
                            a(comicSectionPicInfo, null, bVar.a);
                        }
                        if (z) {
                            a(comicSectionPicInfo);
                            return;
                        }
                        return;
                    }
                    bVar.b.a(comicSectionPicInfo);
                    bVar.b.setTextSize(getResources().getDimensionPixelSize(b.a.a.a.a.a.c.buy_text_size));
                    bVar.b.setVisibility(0);
                    bVar.b.setSubTextVisible(0);
                    if (b.t == 1) {
                        bVar.b.setText(String.format(getResources().getString(b.a.a.a.a.a.g.comic_paying_txt), new Object[]{String.valueOf(b.f + 1)}));
                        bVar.b.setSubTextVisible(8);
                    } else if (b.t == 2) {
                        bVar.b.setText(String.format(getResources().getString(b.a.a.a.a.a.g.comic_paying_fail_txt), new Object[]{String.valueOf(b.f + 1)}));
                        bVar.b.setSubText("付费失败, 重新购买");
                        bVar.b.setSubTextVisible(0);
                    } else {
                        bVar.b.setText(String.format(getResources().getString(b.a.a.a.a.a.g.comic_paying_cancel_txt), new Object[]{String.valueOf(b.f + 1)}));
                        bVar.b.setSubText("付费失败, 重新购买");
                        bVar.b.setSubTextVisible(0);
                    }
                }
            }
        }
    }

    private void a(b bVar, ComicSectionPicInfo comicSectionPicInfo) {
        if (bVar != null && comicSectionPicInfo != null && comicSectionPicInfo.mComicRecommendPageInfo != null) {
            bVar.d = comicSectionPicInfo.picId;
            bVar.e = comicSectionPicInfo.sectionId;
            bVar.c = comicSectionPicInfo;
            ComicRecommendPageInfo comicRecommendPageInfo = comicSectionPicInfo.mComicRecommendPageInfo;
            if (bVar.k == null) {
                bVar.k = new c();
                bVar.j = bVar.i.inflate();
                bVar.h.setOnTouchListener(new OnTouchListener(this) {
                    final /* synthetic */ QRComicReadingVerticalActivity a;
                    private GestureDetector b = new GestureDetector(new SimpleOnGestureListener(this) {
                        final /* synthetic */ AnonymousClass11 a;

                        {
                            this.a = r1;
                        }

                        public boolean onDown(MotionEvent motionEvent) {
                            return true;
                        }

                        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
                            int i = (int) (((float) this.a.a.at) * 0.117f);
                            int i2 = (int) (((float) this.a.a.as) * 0.21f);
                            if (this.a.a.l() || (motionEvent.getX() >= ((float) i2) && (((float) this.a.a.as) - motionEvent.getX() <= ((float) i2) || motionEvent.getY() >= ((float) i)))) {
                                this.a.a.w();
                            } else {
                                this.a.a.al.setCurrentItem(this.a.a.aX - 1);
                                if (!(this.a.a.Z == null || this.a.a.Z.o == null)) {
                                    String str;
                                    if (this.a.a.Z.H == 2) {
                                        str = "0";
                                    } else if (this.a.a.aF == 0) {
                                        str = "2";
                                    } else {
                                        str = "1";
                                    }
                                }
                            }
                            return super.onSingleTapConfirmed(motionEvent);
                        }
                    });

                    {
                        this.a = r3;
                    }

                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        return this.b.onTouchEvent(motionEvent);
                    }
                });
                bVar.k.a = (TextView) bVar.j.findViewById(b.a.a.a.a.a.e.finish_info);
                bVar.k.b = (TextView) bVar.j.findViewById(b.a.a.a.a.a.e.collection_info);
                bVar.k.c = (Button) bVar.j.findViewById(b.a.a.a.a.a.e.collection_button);
                bVar.k.c.setOnClickListener(this);
                bVar.k.g = (ImageView) bVar.j.findViewById(b.a.a.a.a.a.e.recommend_comic_left_image);
                bVar.k.d = (TextView) bVar.j.findViewById(b.a.a.a.a.a.e.recommend_comic_left_title);
                bVar.k.h = (ImageView) bVar.j.findViewById(b.a.a.a.a.a.e.recommend_comic_middle_image);
                bVar.k.e = (TextView) bVar.j.findViewById(b.a.a.a.a.a.e.recommend_comic_middle_title);
                bVar.k.i = (ImageView) bVar.j.findViewById(b.a.a.a.a.a.e.recommend_comic_right_image);
                bVar.k.f = (TextView) bVar.j.findViewById(b.a.a.a.a.a.e.recommend_comic_right_title);
                bVar.k.g.setOnClickListener(this);
                bVar.k.h.setOnClickListener(this);
                bVar.k.i.setOnClickListener(this);
                if (this.as != 0) {
                    int round = Math.round((((float) this.as) - (com.qrcomic.screenshot.a.b.b(12.0f) * 4.0f)) / 3.0f);
                    if (bVar.k.g.getLayoutParams() != null) {
                        bVar.k.g.getLayoutParams().width = round;
                    }
                    if (bVar.k.d.getLayoutParams() != null) {
                        bVar.k.d.getLayoutParams().width = round;
                    }
                    if (bVar.k.h != null) {
                        bVar.k.h.getLayoutParams().width = round;
                    }
                    if (bVar.k.e != null) {
                        bVar.k.e.getLayoutParams().width = round;
                    }
                    if (bVar.k.i != null) {
                        bVar.k.i.getLayoutParams().width = round;
                    }
                    if (bVar.k.f != null) {
                        bVar.k.f.getLayoutParams().width = round;
                    }
                }
            }
            if (bVar.a != null && bVar.a.getVisibility() == 0) {
                bVar.a.setVisibility(8);
            }
            if (bVar.b != null && bVar.b.getVisibility() == 0) {
                bVar.b.setVisibility(8);
            }
            if (bVar.g != null) {
                bVar.g.setBackgroundColor(-1);
            }
            if (bVar.h != null && bVar.h.getVisibility() == 8) {
                bVar.h.setVisibility(0);
            }
            if (TextUtils.isEmpty(comicRecommendPageInfo.d)) {
                bVar.k.a.setText("");
            } else {
                bVar.k.a.setText(comicRecommendPageInfo.d);
            }
            if (comicRecommendPageInfo.c == 2) {
                bVar.k.c.setVisibility(8);
                bVar.k.b.setVisibility(0);
                bVar.k.b.setText(getString(b.a.a.a.a.a.g.recommend_page_to_comment));
                bVar.k.b.setTextColor(-19456);
                bVar.k.b.setOnClickListener(this);
            } else {
                bVar.k.b.setOnClickListener(null);
                if (super.c()) {
                    bVar.k.b.setVisibility(8);
                    bVar.k.c.setVisibility(8);
                } else {
                    bVar.k.b.setVisibility(0);
                    bVar.k.c.setVisibility(0);
                    bVar.k.b.setText(getString(b.a.a.a.a.a.g.collection_tip));
                    bVar.k.b.setTextColor(-8947849);
                    bVar.k.c.setEnabled(true);
                    bVar.k.c.setText(getString(b.a.a.a.a.a.g.reader_add_fav));
                    bVar.k.c.setOnClickListener(this);
                }
            }
            if (comicRecommendPageInfo.e != null && comicRecommendPageInfo.e.size() > 0) {
                a(bVar.k.d, bVar.k.g, (RecommendComicInfo) comicRecommendPageInfo.e.get(0), 0);
                if (comicRecommendPageInfo.e.size() > 1) {
                    a(bVar.k.e, bVar.k.h, (RecommendComicInfo) comicRecommendPageInfo.e.get(1), 1);
                }
                if (comicRecommendPageInfo.e.size() > 2) {
                    a(bVar.k.f, bVar.k.i, (RecommendComicInfo) comicRecommendPageInfo.e.get(2), 2);
                }
            }
        }
    }

    public void a(ComicSectionPicInfo comicSectionPicInfo) {
        this.u.a((QRComicReadingBaseActivity) this, comicSectionPicInfo, this.bh);
    }

    public void b(ComicSectionPicInfo comicSectionPicInfo) {
        if (comicSectionPicInfo != null) {
            for (View tag : this.aY) {
                b bVar = (b) tag.getTag();
                if (!(bVar == null || bVar.d == null || bVar.e == null || !bVar.d.equals(comicSectionPicInfo.picId) || !bVar.e.equals(comicSectionPicInfo.sectionId) || bVar.b == null || bVar.b.getVisibility() != 0)) {
                    bVar.b.setSubText("加载失败, 点击重试");
                }
            }
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == b.a.a.a.a.a.e.section_selector) {
            this.a.f().c().a("event_F274", null, getApplicationContext());
            V();
        } else if (id == b.a.a.a.a.a.e.drag_progress) {
            this.a.f().c().a("event_F275", null, getApplicationContext());
            this.q = true;
            w();
        } else if (id == b.a.a.a.a.a.e.reader_settings) {
            this.a.f().c().a("event_F276", null, getApplicationContext());
            Z();
        } else if (id == b.a.a.a.a.a.e.download) {
            this.a.f().c().a("event_F277", null, getApplicationContext());
            if (com.qrcomic.util.f.a((Context) this)) {
                if (this.a.f().a().a(this.aW)) {
                    this.a.f().f().a((Activity) this, W());
                } else {
                    S();
                    a(new QRComicReadingBaseActivity$a(this) {
                        final /* synthetic */ QRComicReadingVerticalActivity a;

                        {
                            this.a = r1;
                        }

                        public void a(boolean z) {
                            this.a.a.f().f().a(this.a, this.a.W());
                        }
                    });
                }
                w();
                return;
            }
            this.a.f().d().a((Context) this, b.a.a.a.a.a.g.reader_net_work_error_toast, 0);
        } else if (id != b.a.a.a.a.a.e.recommend && id != b.a.a.a.a.a.e.collection_button) {
            if (id == b.a.a.a.a.a.e.collection_info) {
                if (this.Z != null) {
                    QRComicManager.a((Activity) this, this.Z.n, "read", this.Z.i.m);
                }
            } else if (id == b.a.a.a.a.a.e.recommend_comic_left_image || id == b.a.a.a.a.a.e.recommend_comic_middle_image || id == b.a.a.a.a.a.e.recommend_comic_right_image) {
                if (view.getTag() != null && (view.getTag() instanceof RecommendComicInfo)) {
                    RecommendComicInfo recommendComicInfo = (RecommendComicInfo) view.getTag();
                    QRComicManager.a((Activity) this, recommendComicInfo.a, "read", recommendComicInfo.d);
                    if (this.Z.L != null) {
                        String str = WeiboAuthException.DEFAULT_AUTH_ERROR_CODE;
                        if (recommendComicInfo.e == 1) {
                            str = "2";
                        } else if (recommendComicInfo.e == 2) {
                            str = "1";
                        }
                        str = WeiboAuthException.DEFAULT_AUTH_ERROR_CODE;
                        String str2;
                        if (recommendComicInfo.f == 1) {
                            str2 = "1";
                        } else if (recommendComicInfo.f == 2) {
                            str2 = "2";
                        }
                    }
                }
            } else if (id == b.a.a.a.a.a.e.reader_comic_comment) {
                X();
            }
        }
    }

    public void c(View view) {
        if ((view.getTag() instanceof b) && (view instanceof QRComicPagerLoading)) {
            b bVar = (b) view.getTag();
            QRComicPagerLoading qRComicPagerLoading = (QRComicPagerLoading) view;
            if (bVar.c == null || !(this.Z.b(bVar.c.sectionId).t == 3 || this.Z.b(bVar.c.sectionId).t == 2)) {
                if (bVar.c != null && bVar.c.mState == 1) {
                    a(bVar.c);
                    qRComicPagerLoading.setSubText("图片加载中");
                }
            } else if (this.Z.i.F <= 8) {
                this.a.f().d().a((Context) this, b.a.a.a.a.a.g.pay_fail_by_permission, 0);
            } else {
                a(this.Z.b(bVar.c.sectionId), 0);
            }
        }
    }

    protected void m() {
        this.Z.d(0);
        this.l.setProgress(this.Z.C);
        if (this.aF == 0) {
            this.aj.a(this.Z.o.b, this.Z.C);
        } else {
            aa();
        }
    }

    protected void o() {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.Q.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.bottomMargin = this.aM ? 0 : (-this.aB) - this.aD;
            this.Q.setLayoutParams(layoutParams);
        }
        if (this.aM) {
            this.Q.setVisibility(0);
        } else {
            this.Q.setVisibility(8);
        }
        if (g.a()) {
            g.a("QRComicReadingVerticalActivity", g.d, " layoutProgressBar needShowProgress = " + this.aM);
        }
    }

    protected void n() {
        int i = 0;
        if (g.a()) {
            g.a("QRComicReadingVerticalActivity", g.d, "layout tool bar");
        }
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.bc.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.topMargin = this.aL ? -this.az : 0;
            this.bc.setLayoutParams(layoutParams);
        }
        layoutParams = (FrameLayout.LayoutParams) this.n.getLayoutParams();
        if (layoutParams != null) {
            if (this.aL) {
                i = (-this.aC) - this.aD;
            }
            layoutParams.bottomMargin = i;
            this.n.setLayoutParams(layoutParams);
        }
        layoutParams = (FrameLayout.LayoutParams) this.K.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.topMargin = this.aL ? -this.aA : this.bc.getHeight();
            this.K.setLayoutParams(layoutParams);
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        this.bh = null;
        if (this.n != null) {
            this.n.b((QRComicReadingBaseActivity) this);
        }
    }
}
