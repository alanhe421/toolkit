package com.qrcomic.widget.reader;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import b.a.a.a.a.a.e;
import com.qrcomic.activity.reader.QRComicReadingBaseActivity;
import com.qrcomic.activity.reader.QRComicReadingVerticalActivity;
import com.qrcomic.activity.reader.QRComicReadingVerticalActivity.b;
import com.qrcomic.entity.ComicSectionPicInfo;
import com.qrcomic.entity.f;
import com.qrcomic.util.g;
import java.util.List;

public class QRComicReaderViewPager extends ViewPager {
    public boolean a = false;
    private boolean b = true;
    private QRComicReadingBaseActivity c;
    private ComicSectionPicInfo d;
    private int e = 0;
    private int f = 1;
    private int g = 2;
    private float h;
    private int i = this.e;
    private float j;
    private a k;

    public interface a {
        void a();

        void b();
    }

    public QRComicReaderViewPager(Context context) {
        super(context);
    }

    public QRComicReaderViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setCanScroll(boolean z) {
        this.b = z;
    }

    public void setAttachedActivity(QRComicReadingBaseActivity qRComicReadingBaseActivity) {
        this.c = qRComicReadingBaseActivity;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        try {
            if (this.b) {
                a(motionEvent);
                if (this.i == this.f) {
                    if (!l()) {
                        return super.onTouchEvent(motionEvent);
                    }
                    if (!(this.a || this.k == null)) {
                        this.a = true;
                        this.k.a();
                    }
                    if (g.a()) {
                        g.a("VipComicReaderViewPager", g.d, "onTouchEvent MOVE_PRE");
                    }
                } else if (this.i != this.g) {
                    return super.onTouchEvent(motionEvent);
                } else {
                    if (!k()) {
                        return super.onTouchEvent(motionEvent);
                    }
                    if (!this.a) {
                        this.a = true;
                        this.k.b();
                    }
                    if (g.a()) {
                        g.a("VipComicReaderViewPager", g.d, "onTouchEvent MOVE_NEXT");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean k() {
        try {
            j();
            if (!(this.d == null || this.c == null || this.c.Z == null || this.c.Z.r == null || this.d.index != this.c.Z.r.size() - 1)) {
                if (this.c.Z.E == this.c.Z.u.size() - 1) {
                    return false;
                }
                try {
                    f fVar = (f) this.c.Z.w.get(this.c.Z.E + 1);
                    if (fVar == null) {
                        return true;
                    }
                    if (this.c.Z.d(fVar)) {
                        return false;
                    }
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                    return true;
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return false;
    }

    private boolean l() {
        try {
            j();
            if (!(this.d == null || this.c == null || this.c.Z == null || this.c.Z.r == null || this.d.index != 0)) {
                if (this.c.Z.E == 0) {
                    return false;
                }
                try {
                    f fVar = (f) this.c.Z.w.get(this.c.Z.E - 1);
                    if (fVar == null) {
                        return true;
                    }
                    if (this.c.Z.d(fVar)) {
                        return false;
                    }
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                    return true;
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return false;
    }

    public void j() {
        Object tag = getCurContainer().getTag();
        if (tag instanceof b) {
            this.d = ((b) tag).c;
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        try {
            if (!this.b) {
                return false;
            }
            a(motionEvent);
            if (this.i == this.f) {
                if (!l()) {
                    return super.onInterceptTouchEvent(motionEvent);
                }
                if (!(this.a || this.k == null)) {
                    this.a = true;
                    this.k.a();
                }
                if (!g.a()) {
                    return false;
                }
                g.a("VipComicReaderViewPager", g.d, "onInterceptTouchEvent MOVE_PRE");
                return false;
            } else if (this.i != this.g) {
                return super.onInterceptTouchEvent(motionEvent);
            } else {
                if (!k()) {
                    return super.onInterceptTouchEvent(motionEvent);
                }
                if (!this.a) {
                    this.a = true;
                    this.k.b();
                }
                if (!g.a()) {
                    return false;
                }
                g.a("VipComicReaderViewPager", g.d, "onInterceptTouchEvent MOVE_NEXT");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public QRComicTouchImageView getCurImageView() {
        return (QRComicTouchImageView) getCurContainer().findViewById(e.img_view);
    }

    public View getCurContainer() {
        QRComicReadingVerticalActivity qRComicReadingVerticalActivity = (QRComicReadingVerticalActivity) this.c;
        List list = qRComicReadingVerticalActivity.aY;
        return (View) list.get(qRComicReadingVerticalActivity.aX % list.size());
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (this.c != null && this.c.aE) {
            if (i > this.c.as) {
                this.c.as = i;
            }
            if (i2 > this.c.at) {
                if (this.c.at > 0 && (this.c instanceof QRComicReadingVerticalActivity)) {
                    QRComicReadingVerticalActivity qRComicReadingVerticalActivity = (QRComicReadingVerticalActivity) this.c;
                    int i5 = i2 - this.c.at;
                    if (g.a()) {
                        g.a("VipComicReaderViewPager", g.d, "mNavigationBarHeight = " + qRComicReadingVerticalActivity.aD + " , now height = " + i5);
                    }
                    if (qRComicReadingVerticalActivity.aD != i5) {
                        qRComicReadingVerticalActivity.c(i5);
                    }
                }
                this.c.at = i2;
            }
        }
        if (g.a()) {
            g.a("VipComicReaderViewPager", g.d, "new width = " + i + " , new height = " + i2 + " , old width = " + i3 + " , old height = " + i4);
        }
    }

    private void a(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                this.h = motionEvent.getRawX();
                this.j = 0.0f;
                this.a = false;
                return;
            case 1:
            case 3:
                this.i = this.e;
                this.a = false;
                return;
            case 2:
                float rawX = motionEvent.getRawX();
                this.j += this.h - rawX;
                this.h = rawX;
                if (Math.abs(this.j) < ((float) ViewConfiguration.get(this.c).getScaledTouchSlop())) {
                    return;
                }
                if (this.j > 0.0f) {
                    this.i = this.g;
                    return;
                } else {
                    this.i = this.f;
                    return;
                }
            default:
                return;
        }
    }

    public void setPageChangeListener(a aVar) {
        this.k = aVar;
    }

    public a getPageChangeListener() {
        return this.k;
    }
}
