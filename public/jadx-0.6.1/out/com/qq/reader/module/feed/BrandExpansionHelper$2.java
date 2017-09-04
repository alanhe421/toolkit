package com.qq.reader.module.feed;

import android.text.TextUtils;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.e;
import com.qq.reader.common.readertask.ordinal.ReaderIOTask;
import com.qq.reader.cservice.adv.a;

class BrandExpansionHelper$2 extends ReaderIOTask {
    final /* synthetic */ a this$0;

    BrandExpansionHelper$2(a aVar) {
        this.this$0 = aVar;
    }

    public void run() {
        super.run();
        a a = a.b(Boolean.valueOf(false), "103484");
        if (a != null) {
            this.this$0.j = true;
        } else {
            a = a.b(Boolean.valueOf(false), "103469");
            if (a != null) {
                this.this$0.j = false;
            } else {
                return;
            }
        }
        this.this$0.g = a;
        Object a2 = e.a(ReaderApplication.getApplicationImp());
        if (TextUtils.isEmpty(a2) || !a2.equals(this.this$0.g.h())) {
            this.this$0.b.post(new Runnable(this) {
                final /* synthetic */ BrandExpansionHelper$2 a;

                {
                    this.a = r1;
                }

                public void run() {
                    try {
                        this.a.this$0.f();
                        this.a.this$0.g();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            return;
        }
        this.this$0.h = true;
        this.this$0.b.post(new Runnable(this) {
            final /* synthetic */ BrandExpansionHelper$2 a;

            {
                this.a = r1;
            }

            public void run() {
                if (this.a.this$0.i != null) {
                    this.a.this$0.i.a();
                }
            }
        });
    }
}
