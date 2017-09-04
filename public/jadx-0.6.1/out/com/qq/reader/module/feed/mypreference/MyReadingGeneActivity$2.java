package com.qq.reader.module.feed.mypreference;

import com.qq.reader.module.feed.mypreference.c.a;

class MyReadingGeneActivity$2 implements Runnable {
    final /* synthetic */ MyReadingGeneActivity a;

    MyReadingGeneActivity$2(MyReadingGeneActivity myReadingGeneActivity) {
        this.a = myReadingGeneActivity;
    }

    public void run() {
        c.b().a(new a(this) {
            final /* synthetic */ MyReadingGeneActivity$2 a;

            {
                this.a = r1;
            }

            public void a(d dVar) {
                MyReadingGeneActivity.a(this.a.a, dVar);
                MyReadingGeneActivity.b(this.a.a).sendEmptyMessage(241);
            }
        });
    }
}
