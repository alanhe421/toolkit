package com.qq.reader.common.db.handle;

import com.qq.reader.common.readertask.ordinal.ReaderDBTask;
import com.qq.reader.module.bookchapter.online.OnlineChapter;

class BookChapterInfoHandle$1 extends ReaderDBTask {
    final /* synthetic */ b this$0;
    final /* synthetic */ String val$bid;
    final /* synthetic */ int val$chapterId;

    BookChapterInfoHandle$1(b bVar, String str, int i) {
        this.this$0 = bVar;
        this.val$bid = str;
        this.val$chapterId = i;
    }

    public void run() {
        super.run();
        OnlineChapter a = this.this$0.b(this.val$bid, this.val$chapterId);
        if (a != null) {
            this.this$0.c.put(this.val$bid + "_" + this.val$chapterId, a);
        }
    }
}
