package com.qq.reader.module.bookshelf;

import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.readertask.ordinal.ReaderDBTask;
import com.qq.reader.cservice.adv.a;
import com.qq.reader.cservice.adv.b;
import java.util.List;

class AbsBaseTabBookShelf$6 extends ReaderDBTask {
    final /* synthetic */ a this$0;

    AbsBaseTabBookShelf$6(a aVar) {
        this.this$0 = aVar;
    }

    public void run() {
        boolean z;
        super.run();
        String a = this.this$0.k();
        List list = null;
        if (a != null) {
            List<a> c = b.a(this.this$0.b.getApplicationContext()).c(a);
            if (c != null && c.size() > 0) {
                for (a a2 : c) {
                    if (a2.a()) {
                        List list2 = c;
                        z = true;
                        list = list2;
                        break;
                    }
                }
            }
            list = c;
            z = false;
        } else {
            z = false;
        }
        if (this.this$0.g != null) {
            this.this$0.g.post(new Runnable(this) {
                final /* synthetic */ AbsBaseTabBookShelf$6 c;

                public void run() {
                    if ((z || b.a(this.c.this$0.b.getApplicationContext()).e()) && list != null && list.size() > 0) {
                        this.c.this$0.m = (this.c.this$0.m + 1) % list.size();
                        if (!d.j(ReaderApplication.getApplicationImp())) {
                            this.c.this$0.a((a) list.get(this.c.this$0.m));
                        }
                    }
                }
            });
        }
    }
}
