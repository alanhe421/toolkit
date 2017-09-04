package com.qrcomic.widget.reader;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.LinearLayout;
import b.a.a.a.a.a.e;
import b.a.a.a.a.a.f;
import b.a.a.a.a.a.i;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qrcomic.activity.reader.QRComicReadingBaseActivity;
import com.qrcomic.util.g;
import com.qrcomic.util.k;
import com.tencent.feedback.eup.BuglyBroadcastRecevier;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class QRComicReaderBottomBar extends LinearLayout implements Callback, OnClickListener {
    private static final String a = QRComicReaderBottomBar.class.getSimpleName();
    private View b;
    private LinearLayout c;
    private k d;
    private WeakReference<QRComicReadingBaseActivity> e;
    private boolean f;
    private boolean g;
    private HandlerThread h;
    private volatile long i;
    private volatile long j;
    private volatile long k;
    private AtomicBoolean l;
    private Runnable m;
    private AnimationListener n;

    static /* synthetic */ long a(QRComicReaderBottomBar qRComicReaderBottomBar, long j) {
        long j2 = qRComicReaderBottomBar.i + j;
        qRComicReaderBottomBar.i = j2;
        return j2;
    }

    static /* synthetic */ long b(QRComicReaderBottomBar qRComicReaderBottomBar, long j) {
        long j2 = qRComicReaderBottomBar.j + j;
        qRComicReaderBottomBar.j = j2;
        return j2;
    }

    public QRComicReaderBottomBar(Context context) {
        this(context, null);
    }

    public QRComicReaderBottomBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f = false;
        this.g = false;
        this.l = new AtomicBoolean(false);
        this.m = new Runnable(this) {
            final /* synthetic */ QRComicReaderBottomBar a;

            {
                this.a = r1;
            }

            public void run() {
                synchronized (QRComicReaderBottomBar.class) {
                    long currentTimeMillis = System.currentTimeMillis();
                    if (this.a.j <= 120000 && this.a.k != 0) {
                        QRComicReaderBottomBar.a(this.a, currentTimeMillis - this.a.k);
                        QRComicReaderBottomBar.b(this.a, currentTimeMillis - this.a.k);
                        if (g.a()) {
                            g.a(QRComicReaderBottomBar.a, g.d, "处理计时操作 ， 本次时间为 pageTime=" + this.a.j + " bookTime=" + this.a.i);
                        }
                        if (this.a.i >= 180000) {
                            if (this.a.e != null) {
                                QRComicReadingBaseActivity qRComicReadingBaseActivity = (QRComicReadingBaseActivity) this.a.e.get();
                                if (!(qRComicReadingBaseActivity == null || qRComicReadingBaseActivity.Z == null || qRComicReadingBaseActivity.a == null)) {
                                    if (this.a.i <= 5000 || this.a.i >= 3600000) {
                                        Map hashMap = new HashMap();
                                        hashMap.put(s.ORIGIN, this.a.i <= 5000 ? "0" : "1");
                                        qRComicReadingBaseActivity.a.f().c().a("event_F347", hashMap, qRComicReadingBaseActivity.getApplicationContext());
                                    } else {
                                        qRComicReadingBaseActivity.a.f().c().a(qRComicReadingBaseActivity.Z.n, this.a.i);
                                        qRComicReadingBaseActivity.a.f().c().a("event_F348", null, qRComicReadingBaseActivity.getApplicationContext());
                                    }
                                }
                            }
                            if (g.a()) {
                                g.a(QRComicReaderBottomBar.a, g.d, "上报阅读时长 ， 本次时间为 pageTime=" + this.a.j + " bookTime=" + this.a.i);
                            }
                            this.a.i = 0;
                        }
                    }
                    if (!(this.a.l.get() || this.a.h == null || this.a.h.isInterrupted() || this.a.d == null)) {
                        this.a.d.postDelayed(this.a.m, (BuglyBroadcastRecevier.UPLOADLIMITED + currentTimeMillis) - System.currentTimeMillis());
                        if (g.a()) {
                            g.a(QRComicReaderBottomBar.a, g.d, "发送阅读时长 ， 本次时间为 pageTime=" + this.a.j + " bookTime=" + this.a.i);
                        }
                    }
                    this.a.k = currentTimeMillis;
                }
            }
        };
        this.n = new AnimationListener(this) {
            final /* synthetic */ QRComicReaderBottomBar a;

            {
                this.a = r1;
            }

            public void onAnimationStart(Animation animation) {
                this.a.g = true;
            }

            public void onAnimationEnd(Animation animation) {
                this.a.g = false;
            }

            public void onAnimationRepeat(Animation animation) {
            }
        };
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, i.QRComicReaderBottomBar);
        if ("land".equalsIgnoreCase(obtainStyledAttributes.getString(i.QRComicReaderBottomBar_readerMode))) {
            this.f = true;
        }
        obtainStyledAttributes.recycle();
        a(context);
    }

    private void a(Context context) {
        setOrientation(1);
        this.b = LayoutInflater.from(context).inflate(this.f ? f.qr_comic_bottom_bar_land : f.qr_comic_bottom_bar, this);
        this.c = (LinearLayout) this.b.findViewById(e.mode_select_layout);
    }

    public void a(QRComicReadingBaseActivity qRComicReadingBaseActivity) {
        this.e = new WeakReference(qRComicReadingBaseActivity);
    }

    public void b(QRComicReadingBaseActivity qRComicReadingBaseActivity) {
        this.e = null;
    }

    public void a() {
        synchronized (this) {
            this.k = 0;
            this.j = 0;
            this.i = 0;
            this.h = new HandlerThread("collect-comic-reading-time");
            this.l.set(false);
            this.h.start();
            this.d = new k(this.h.getLooper(), this);
            this.d.post(this.m);
        }
    }

    public void b() {
        synchronized (this) {
            this.l.set(true);
            if (this.d != null) {
                this.d.removeCallbacksAndMessages(null);
                this.d = null;
            }
            if (this.h != null) {
                this.h.interrupt();
                this.h.quit();
                this.h = null;
            }
        }
    }

    public void c() {
        if (g.a()) {
            g.a(a, g.d, "某一页的阅读时长重置 ， 本次时间为 pageTime=" + this.j + " bookTime=" + this.i);
        }
        synchronized (QRComicReaderBottomBar.class) {
            this.j = 0;
        }
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
    }

    @TargetApi(9)
    public void onClick(View view) {
    }

    public void setBarrageBtnBgAlpha(int i) {
        setBarrageBtnBgAlpha(i, false, 0, 0);
    }

    public void setBarrageBtnBgAlpha(int i, boolean z, int i2, long j) {
    }

    public void d() {
    }

    public boolean handleMessage(Message message) {
        return false;
    }

    public long getBookReadingTimeAndRest() {
        long j = 0;
        synchronized (this) {
            long currentTimeMillis = System.currentTimeMillis();
            if (this.k != 0) {
                this.i = (currentTimeMillis - this.k) + this.i;
                j = this.i;
                this.i = 0;
            } else {
                this.i = 0;
            }
        }
        return j;
    }

    public void setBackgroundResource(int i) {
        this.c.setBackgroundResource(i);
    }

    public Drawable getBackground() {
        return this.c.getBackground();
    }
}
