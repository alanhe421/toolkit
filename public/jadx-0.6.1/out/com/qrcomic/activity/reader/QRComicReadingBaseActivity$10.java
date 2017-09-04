package com.qrcomic.activity.reader;

import android.view.View;
import android.view.View.OnClickListener;
import b.a.a.a.a.a.e;
import com.qrcomic.util.g;

class QRComicReadingBaseActivity$10 implements OnClickListener {
    final /* synthetic */ QRComicReadingBaseActivity a;

    QRComicReadingBaseActivity$10(QRComicReadingBaseActivity qRComicReadingBaseActivity) {
        this.a = qRComicReadingBaseActivity;
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == e.back) {
            QRComicReadingBaseActivity.a(this.a, this.a.U());
            this.a.B();
        } else if (id == e.more) {
            if (!this.a.c) {
                this.a.a.f().c().a("event_F278", null, this.a.getApplicationContext());
                this.a.an.removeCallbacks(QRComicReadingBaseActivity.f(this.a));
                this.a.an.postDelayed(QRComicReadingBaseActivity.f(this.a), 100);
            }
        } else if (id == e.pre_chapter) {
            if (g.a()) {
                g.a(QRComicReadingBaseActivity.Y(), g.d, "准备开始拉取 已经点击 上一话");
            }
            this.a.Z.b = System.currentTimeMillis();
            if (this.a.Z.E > 0) {
                this.a.D();
            }
            this.a.Z.a(c.e().b().c().a().d().e(), this.a.aS);
            if (this.a.Z.o == null) {
                return;
            }
            if (this.a.Z.H == 2) {
                r0 = "0";
            } else if (this.a.aF == 0) {
                r0 = "2";
            } else {
                r0 = "1";
            }
        } else if (id == e.next_chapter) {
            if (g.a()) {
                g.a(QRComicReadingBaseActivity.Y(), g.d, "准备开始拉取 已经点击 下一话");
            }
            this.a.Z.b = System.currentTimeMillis();
            if (this.a.Z.E < this.a.Z.u.size() - 1 || this.a.Z.J) {
                this.a.D();
            }
            this.a.Z.b(c.e().b().c().a().d().e(), this.a.aS);
            if (this.a.Z.o == null) {
                return;
            }
            if (this.a.Z.H == 2) {
                r0 = "0";
            } else if (this.a.aF == 0) {
                r0 = "2";
            } else {
                r0 = "1";
            }
        }
    }
}
