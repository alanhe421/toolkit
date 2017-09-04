package com.qrcomic.activity.reader;

import android.content.SharedPreferences;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import com.qrcomic.a.e;

class QRComicReadingBaseActivity$37 implements AnimationListener {
    final /* synthetic */ QRComicReadingBaseActivity a;

    QRComicReadingBaseActivity$37(QRComicReadingBaseActivity qRComicReadingBaseActivity) {
        this.a = qRComicReadingBaseActivity;
    }

    public void onAnimationStart(Animation animation) {
        if (animation == this.a.h) {
            this.a.c = true;
            this.a.au = 0;
            this.a.av = 0;
            this.a.aw = 0;
            this.a.ax = 0;
            if (!(this.a.aL || this.a.Z == null)) {
                this.a.a(this.a.getWindow().getDecorView());
            }
        }
        if (animation == this.a.k) {
            this.a.d = true;
            this.a.ay = 0;
        }
    }

    public void onAnimationEnd(Animation animation) {
        boolean z = true;
        if (animation == this.a.h) {
            this.a.c = false;
            this.a.n();
            if (this.a.aL) {
                this.a.v();
                if (!(this.a.Z == null || this.a.p || this.a.q)) {
                    this.a.b(this.a.getWindow().getDecorView());
                }
            }
            if (this.a.aL) {
                if (this.a.o == null || !this.a.p) {
                    this.a.n.setBarrageBtnBgAlpha(255, true, 0, 150);
                } else {
                    this.a.o.b();
                }
                this.a.p = false;
                if (this.a.q) {
                    this.a.p();
                }
                this.a.q = false;
            } else {
                SharedPreferences sharedPreferences = this.a.getSharedPreferences(e.a(), 4);
                if (sharedPreferences.getBoolean("is_reader_bottom_bar_first_show", true)) {
                    this.a.n.d();
                    sharedPreferences.edit().putBoolean("is_reader_bottom_bar_first_show", false).commit();
                }
            }
            QRComicReadingBaseActivity qRComicReadingBaseActivity = this.a;
            if (this.a.aL) {
                z = false;
            }
            qRComicReadingBaseActivity.aL = z;
        }
        if (animation == this.a.k) {
            this.a.d = false;
            this.a.o();
            if (!this.a.aM) {
                this.a.b(this.a.getWindow().getDecorView());
            }
        }
    }

    public void onAnimationRepeat(Animation animation) {
    }
}
