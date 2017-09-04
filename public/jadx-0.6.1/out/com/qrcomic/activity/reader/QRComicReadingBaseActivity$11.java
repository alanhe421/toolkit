package com.qrcomic.activity.reader;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.qrcomic.c.c.a;
import com.qrcomic.entity.e;
import com.qrcomic.util.g;

class QRComicReadingBaseActivity$11 implements a {
    final /* synthetic */ QRComicReadingBaseActivity a;

    QRComicReadingBaseActivity$11(QRComicReadingBaseActivity qRComicReadingBaseActivity) {
        this.a = qRComicReadingBaseActivity;
    }

    public void a(final e eVar) {
        this.a.X = true;
        this.a.Y.set(false);
        if (g.a()) {
            g.a(QRComicReadingBaseActivity.Y(), g.d, "阅读进度回来了。。。");
        }
        this.a.runOnUiThread(new Runnable(this) {
            final /* synthetic */ QRComicReadingBaseActivity$11 b;

            public void run() {
                try {
                    if (eVar != null && this.b.a.Z.n.equals(eVar.c)) {
                        if (!this.b.a.Z.B.equals(eVar.f) || !this.b.a.Z.o.b.equals(eVar.a)) {
                            this.b.a.T();
                            QRComicReadingBaseActivity.a(this.b.a, this.b.a.a.f().f().a(this.b.a.ac, "第" + eVar.m + "话" + " " + eVar.e, new OnClickListener(this) {
                                final /* synthetic */ AnonymousClass1 a;

                                {
                                    this.a = r1;
                                }

                                public void onClick(DialogInterface dialogInterface, int i) {
                                    this.a.b.a.T();
                                }
                            }, new OnClickListener(this) {
                                final /* synthetic */ AnonymousClass1 a;

                                {
                                    this.a = r1;
                                }

                                public void onClick(DialogInterface dialogInterface, int i) {
                                    this.a.b.a.a(eVar);
                                }
                            }));
                            QRComicReadingBaseActivity.g(this.b.a).show();
                        }
                    }
                } catch (Exception e) {
                    if (g.a()) {
                        g.a(QRComicReadingBaseActivity.Y(), g.d, "同步服务器的阅读进度失败了。。。");
                    }
                    e.printStackTrace();
                }
            }
        });
    }
}
