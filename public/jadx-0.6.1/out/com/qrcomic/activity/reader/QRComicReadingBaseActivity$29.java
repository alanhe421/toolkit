package com.qrcomic.activity.reader;

import android.view.View;
import android.view.View.OnClickListener;
import b.a.a.a.a.a.e;
import b.a.a.a.a.a.g;
import com.qrcomic.manager.QRComicManager;
import com.qrcomic.manager.b;
import com.qrcomic.util.f;

class QRComicReadingBaseActivity$29 implements OnClickListener {
    final /* synthetic */ View a;
    final /* synthetic */ QRComicReadingBaseActivity b;

    QRComicReadingBaseActivity$29(QRComicReadingBaseActivity qRComicReadingBaseActivity, View view) {
        this.b = qRComicReadingBaseActivity;
        this.a = view;
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == e.secret_container) {
            if (this.b.c(this.b.Z.n)) {
                if (!f.a(this.b.ac)) {
                    this.b.a.f().d().a(this.b.ac, "网络异常，请稍后重试", 0);
                } else if (this.b.a.f().a().a(this.b.ac)) {
                    QRComicReadingBaseActivity.a(this.b, this.a);
                } else {
                    this.b.S();
                    this.b.a(new QRComicReadingBaseActivity$a(this) {
                        final /* synthetic */ QRComicReadingBaseActivity$29 a;

                        {
                            this.a = r1;
                        }

                        public void a(boolean z) {
                            QRComicReadingBaseActivity.a(this.a.b, this.a.a);
                        }
                    });
                }
                this.b.w();
            } else {
                this.b.a.f().d().a(this.b.ac, "该漫画未在书架", 0);
                return;
            }
        } else if (id == e.reader_detail) {
            QRComicManager.a(this.b.ac, this.b.Z.n, "read", this.b.Z.H);
            this.b.w();
        } else if (id == e.reader_share && this.b.Z.K) {
            b.a().a(this.b.getResources().getString(g.not_allow_share), 2);
        }
        QRComicReadingBaseActivity.k(this.b).dismiss();
    }
}
