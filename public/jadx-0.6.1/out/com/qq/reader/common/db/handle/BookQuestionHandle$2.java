package com.qq.reader.common.db.handle;

import com.qq.reader.common.readertask.ordinal.ReaderDBTask;

class BookQuestionHandle$2 extends ReaderDBTask {
    final /* synthetic */ g this$0;

    BookQuestionHandle$2(g gVar) {
        this.this$0 = gVar;
    }

    public void run() {
        int i = 0;
        super.run();
        if (!this.this$0.a(false) && this.this$0.g != null) {
            g gVar = this.this$0;
            long d = this.this$0.g.d();
            String b = this.this$0.k;
            if (this.this$0.j > 0) {
                i = this.this$0.j;
            }
            gVar.a(d, b, i);
        }
    }
}
