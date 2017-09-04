package com.qrcomic.activity.reader;

import android.os.Message;
import com.qrcomic.a.d;
import com.qrcomic.a.j;
import com.qrcomic.e.c;

class QRComicReadingBaseActivity$12 extends c {
    final /* synthetic */ QRComicReadingBaseActivity a;

    QRComicReadingBaseActivity$12(QRComicReadingBaseActivity qRComicReadingBaseActivity) {
        this.a = qRComicReadingBaseActivity;
    }

    public void c(Object obj) {
        Message.obtain(this.a.an, 6, obj).sendToTarget();
        try {
            if (obj instanceof Object[]) {
                Object[] objArr = (Object[]) obj;
                if (objArr.length == 2) {
                    Object[] objArr2 = (Object[]) objArr[1];
                    if ((objArr2[0] instanceof Integer) && ((Integer) objArr2[0]).intValue() == 1004) {
                        this.a.a.f().b().e(this.a.Z.n, this.a.getApplicationContext());
                        j.a().a(new d(this) {
                            final /* synthetic */ QRComicReadingBaseActivity$12 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                this.a.a.v.a(this.a.a.Z.n, true);
                            }
                        }, null);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
