package com.qq.reader.module.readpage;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import com.qq.reader.common.c.a;
import com.qq.reader.common.utils.ao;
import com.qq.reader.module.readpage.ReaderPageSwither.d;
import com.qq.reader.readengine.kernel.b;
import com.qq.reader.view.BatterView;
import com.qq.reader.view.PageHeader;
import com.tencent.feedback.proguard.R;
import com.tencent.util.WeakReferenceHandler;
import java.util.Calendar;

public class PageFooter extends RelativeLayout implements d {
    BatterView a;
    PageHeader b;
    Context c;
    int d = 0;
    volatile long e = 0;
    long f;
    WeakReferenceHandler g;
    private Calendar h;
    private Runnable i;
    private boolean j = false;
    private int k = 60000;
    private String l;
    private Double m = Double.valueOf(0.0d);
    private String n = "";
    private Handler o;
    private String p = "";

    public void setmFootInfo(String str) {
        this.p = str;
        if (this.p.length() > 25) {
            this.p = this.p.substring(0, 25);
        }
        this.b.setChapterName(this.p);
        d();
    }

    public PageFooter(Context context) {
        super(context);
        this.c = context;
    }

    public PageFooter(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = context;
    }

    public void setPercent(Double d) {
        this.m = d;
    }

    public void a(Context context, int i, int i2, b bVar, PageHeader pageHeader, Handler handler) {
        this.a = (BatterView) findViewById(R.id.batter_view);
        this.a.a(bVar);
        this.b = pageHeader;
        setAllColor(context.getResources().getColor(R.color.defualt_readerpage_info_text_color));
        if (this.h == null) {
            this.h = Calendar.getInstance();
        }
        this.g = (WeakReferenceHandler) handler;
        e();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    public void a() {
        this.o = new Handler();
        this.j = false;
        this.e = 0;
        this.d = 0;
        this.f = 0;
        this.i = new Runnable(this) {
            final /* synthetic */ PageFooter a;

            {
                this.a = r1;
            }

            public void run() {
                if (!this.a.j) {
                    this.a.c();
                    this.a.invalidate();
                    long uptimeMillis = SystemClock.uptimeMillis();
                    long b = ((long) this.a.k) + uptimeMillis;
                    if (this.a.d < 3) {
                        PageFooter pageFooter;
                        if (this.a.f != 0) {
                            pageFooter = this.a;
                            pageFooter.e += uptimeMillis - this.a.f;
                        }
                        if (this.a.e > 180000) {
                            Message message = new Message();
                            message.what = 1234;
                            message.arg1 = (int) this.a.e;
                            this.a.g.sendMessage(message);
                            this.a.e = 0;
                        }
                        pageFooter = this.a;
                        pageFooter.d++;
                    }
                    this.a.f = uptimeMillis;
                    this.a.o.postAtTime(this.a.i, b);
                }
            }
        };
        this.i.run();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    public void b() {
        this.j = true;
        if (!(this.o == null || this.i == null)) {
            this.o.removeCallbacks(this.i);
        }
        long uptimeMillis = SystemClock.uptimeMillis();
        if (this.d < 3) {
            this.e = (uptimeMillis - this.f) + this.e;
        }
    }

    private boolean get24HourMode() {
        return DateFormat.is24HourFormat(getContext());
    }

    private void e() {
        if (get24HourMode()) {
            this.l = "k:mm";
        } else {
            this.l = "h:mm aa";
        }
    }

    protected void c() {
        this.h.setTimeInMillis(System.currentTimeMillis());
        d();
    }

    public void a(double d) {
        this.m = Double.valueOf(d);
        this.d = 0;
        d();
    }

    public void a(String str) {
        this.n = str;
        d();
    }

    public void setType(int i) {
        this.a.setType(i);
    }

    public void a(boolean z) {
        this.a.setShow(z);
        this.b.setShow(z);
    }

    public void a(int i, int i2) {
        this.a.setValue((i * 100) / i2);
    }

    protected void d() {
        this.a.setTime(DateFormat.format(this.l, this.h));
        this.a.setPercent(this.p + " " + ao.a(this.m.doubleValue()));
        this.a.setRealPage(this.p + " " + this.n);
        this.b.postInvalidate();
        if (a.x) {
            this.a.postInvalidate();
        }
    }

    public void a(int i) {
        setAllColor(i);
    }

    private void setAllColor(int i) {
        this.a.setColor(i);
        this.b.setColor(i);
        this.a.postInvalidate();
        this.b.postInvalidate();
    }

    public int getReadingTime() {
        return (int) this.e;
    }
}
