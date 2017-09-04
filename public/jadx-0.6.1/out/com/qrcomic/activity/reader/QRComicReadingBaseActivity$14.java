package com.qrcomic.activity.reader;

import android.content.Context;
import android.text.TextUtils;
import b.a.a.a.a.a.g;
import com.qrcomic.activity.reader.a.a;
import com.qrcomic.e.b;
import com.qrcomic.entity.f;
import com.qrcomic.entity.k;
import java.util.List;

class QRComicReadingBaseActivity$14 implements a {
    final /* synthetic */ QRComicReadingBaseActivity a;

    QRComicReadingBaseActivity$14(QRComicReadingBaseActivity qRComicReadingBaseActivity) {
        this.a = qRComicReadingBaseActivity;
    }

    public void a(k kVar) {
        List list = kVar.d;
        List list2 = kVar.e;
        String str = "";
        if (list != null && list.size() > 0) {
            str = (String) list.get(0);
        }
        if (TextUtils.isEmpty(str) && list2 != null && list2.size() > 0) {
            str = (String) list2.get(0);
        }
        if (!TextUtils.isEmpty(str)) {
            this.a.d(this.a.Z.b(str), 0);
        }
        this.a.E();
    }

    public void a(final b.a aVar) {
        try {
            f b = this.a.Z.b((String) aVar.c.get(0));
            if (b != null) {
                this.a.b(b, 1);
                if (aVar.a == 1006) {
                    this.a.an.post(new Runnable(this) {
                        final /* synthetic */ QRComicReadingBaseActivity$14 b;

                        public void run() {
                            Context b = com.qrcomic.manager.b.a().b().b();
                            if (TextUtils.isEmpty(aVar.e)) {
                                aVar.e = b.getResources().getString(g.pay_fail_by_permission);
                            }
                            com.qrcomic.manager.b.a().b().f().d().a(b, aVar.e, 0);
                        }
                    });
                } else if (aVar.a == 1005) {
                    this.a.a(aVar.e, aVar.a, false);
                } else {
                    this.a.a(b, 0);
                    if (this.a.Z.H == 1) {
                        this.a.an.postDelayed(new Runnable(this) {
                            final /* synthetic */ QRComicReadingBaseActivity$14 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                this.a.a.E();
                            }
                        }, 500);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
