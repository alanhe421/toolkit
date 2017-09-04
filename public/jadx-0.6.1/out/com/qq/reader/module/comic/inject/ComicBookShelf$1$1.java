package com.qq.reader.module.comic.inject;

import com.qq.reader.ReaderApplication;
import com.qq.reader.common.db.handle.i;
import com.qq.reader.common.readertask.ordinal.ReaderDBTask;
import com.qq.reader.view.af;
import com.tencent.feedback.proguard.R;

class ComicBookShelf$1$1 extends ReaderDBTask {
    final /* synthetic */ com.qq.reader.module.comic.inject.b.AnonymousClass1 this$1;

    ComicBookShelf$1$1(com.qq.reader.module.comic.inject.b.AnonymousClass1 anonymousClass1) {
        this.this$1 = anonymousClass1;
    }

    public void run() {
        try {
            if (i.c().b(this.this$1.a.getBookId() + "", 0)) {
                this.this$1.b.post(new Runnable(this) {
                    final /* synthetic */ ComicBookShelf$1$1 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        try {
                            this.a.this$1.c.a(true);
                            af.a(ReaderApplication.getApplicationContext(), R.string.read_private_opened, 1).a();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            } else {
                this.this$1.b.post(new Runnable(this) {
                    final /* synthetic */ ComicBookShelf$1$1 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        try {
                            this.a.this$1.c.a(false);
                            af.a(ReaderApplication.getApplicationContext(), R.string.user_interactive_privacy_update_server_error, 1).a();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
