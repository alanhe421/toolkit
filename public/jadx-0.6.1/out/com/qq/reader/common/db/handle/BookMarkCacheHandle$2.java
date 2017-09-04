package com.qq.reader.common.db.handle;

import com.qq.reader.common.readertask.ordinal.ReaderDBTask;
import com.qq.reader.framework.mark.Mark;

class BookMarkCacheHandle$2 extends ReaderDBTask {
    final /* synthetic */ d this$0;
    final /* synthetic */ Mark val$saveMark;

    BookMarkCacheHandle$2(d dVar, Mark mark) {
        this.this$0 = dVar;
        this.val$saveMark = mark;
    }

    public void run() {
        i.c().a(new Mark[]{this.val$saveMark});
    }
}
