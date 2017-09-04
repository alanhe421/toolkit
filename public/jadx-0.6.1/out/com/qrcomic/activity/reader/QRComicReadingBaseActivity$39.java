package com.qrcomic.activity.reader;

import android.view.MotionEvent;
import com.qrcomic.entity.f;
import com.qrcomic.util.g;
import com.qrcomic.widget.reader.a;

class QRComicReadingBaseActivity$39 implements a {
    final /* synthetic */ QRComicReadingBaseActivity a;

    QRComicReadingBaseActivity$39(QRComicReadingBaseActivity qRComicReadingBaseActivity) {
        this.a = qRComicReadingBaseActivity;
    }

    public void a() {
        if (g.a()) {
            g.a(QRComicReadingBaseActivity.Y(), g.d, "scrollReaderTouchListener singleTab");
        }
        this.a.w();
    }

    public boolean a(MotionEvent motionEvent) {
        boolean z;
        if (g.a()) {
            g.a(QRComicReadingBaseActivity.Y(), g.d, "scrollReaderTouchListener onTouchDown");
        }
        if (this.a.l()) {
            this.a.w();
            z = true;
        } else {
            z = false;
        }
        if (this.a.q()) {
            this.a.x();
            z = true;
        }
        if (z) {
            return true;
        }
        this.a.ap = true;
        if (motionEvent.getPointerCount() == 1) {
            QRComicReadingBaseActivity.c(this.a).b = motionEvent;
            this.a.an.removeCallbacks(QRComicReadingBaseActivity.c(this.a));
            this.a.an.postDelayed(QRComicReadingBaseActivity.c(this.a), 750);
            this.a.ar = true;
        } else {
            this.a.an.removeCallbacks(QRComicReadingBaseActivity.c(this.a));
            this.a.ar = false;
        }
        return false;
    }

    public void a(float f, float f2) {
        int i = 1;
        if (!this.a.l() && this.a.aF == 0) {
            float f3 = f - f2;
            if (!this.a.Z.A) {
                f fVar = null;
                if (this.a.ak.a() && f3 < -30.0f) {
                    fVar = this.a.Z.p;
                } else if (this.a.ak.b() && f3 > 30.0f) {
                    fVar = this.a.Z.q;
                }
                if (!(fVar == null || this.a.Z.a(fVar))) {
                    if (this.a.Z.b(fVar)) {
                        QRComicReadingBaseActivity qRComicReadingBaseActivity = this.a;
                        if (!this.a.ak.a()) {
                            i = 2;
                        }
                        qRComicReadingBaseActivity.a(fVar, i);
                    } else {
                        this.a.Z.h();
                    }
                }
            }
        }
        this.a.ap = false;
        if (this.a.ar) {
            this.a.an.removeCallbacks(QRComicReadingBaseActivity.c(this.a));
            this.a.ar = false;
        }
        if (QRComicReadingBaseActivity.aV) {
            QRComicReadingBaseActivity.aV = false;
        }
    }

    public void b() {
        this.a.ap = false;
    }

    public void c() {
        this.a.ap = false;
    }

    public void b(MotionEvent motionEvent) {
    }

    public void a(int i, int i2) {
        if (this.a.ar) {
            if (g.a()) {
                g.b(QRComicReadingBaseActivity.Y(), g.d, "dX:" + i + ", dY:" + i2);
            }
            if (Math.abs(i) >= QRComicReadingBaseActivity.d(this.a) || Math.abs(i2) >= QRComicReadingBaseActivity.d(this.a)) {
                this.a.an.removeCallbacks(QRComicReadingBaseActivity.c(this.a));
                this.a.ar = false;
            }
        }
    }

    public void c(MotionEvent motionEvent) {
        if (g.a()) {
            g.b(QRComicReadingBaseActivity.Y(), g.d, "Pointer Count: " + motionEvent.getPointerCount());
        }
        if (this.a.ar && motionEvent.getPointerCount() > 1) {
            this.a.an.removeCallbacks(QRComicReadingBaseActivity.c(this.a));
            this.a.ar = false;
        }
    }
}
