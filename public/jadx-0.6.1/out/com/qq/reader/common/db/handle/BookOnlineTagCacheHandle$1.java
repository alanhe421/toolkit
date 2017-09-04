package com.qq.reader.common.db.handle;

import com.qq.reader.common.readertask.ordinal.ReaderDBTask;
import com.qq.reader.cservice.onlineread.OnlineTag;

class BookOnlineTagCacheHandle$1 extends ReaderDBTask {
    final /* synthetic */ f this$0;
    final /* synthetic */ OnlineTag val$saveTag;

    BookOnlineTagCacheHandle$1(f fVar, OnlineTag onlineTag) {
        this.this$0 = fVar;
        this.val$saveTag = onlineTag;
    }

    public void run() {
        v.b().e(this.val$saveTag);
    }
}
