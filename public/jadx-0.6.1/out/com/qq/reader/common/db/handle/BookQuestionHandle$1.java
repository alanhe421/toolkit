package com.qq.reader.common.db.handle;

import com.qq.reader.common.readertask.ordinal.ReaderDBTask;

class BookQuestionHandle$1 extends ReaderDBTask {
    final /* synthetic */ g this$0;
    final /* synthetic */ String val$question_id;

    BookQuestionHandle$1(g gVar, String str) {
        this.this$0 = gVar;
        this.val$question_id = str;
    }

    public void run() {
        super.run();
        this.this$0.a(this.val$question_id, 1);
        if (this.this$0.g != null) {
            this.this$0.a(this.this$0.g.d(), this.this$0.k, this.this$0.j > 0 ? this.this$0.j : 0);
        }
    }
}
