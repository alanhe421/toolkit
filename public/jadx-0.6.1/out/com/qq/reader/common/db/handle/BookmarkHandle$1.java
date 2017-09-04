package com.qq.reader.common.db.handle;

import com.qq.reader.common.readertask.ordinal.ReaderDBTask;

class BookmarkHandle$1 extends ReaderDBTask {
    final /* synthetic */ i this$0;
    final /* synthetic */ String val$id;

    BookmarkHandle$1(i iVar, String str) {
        this.this$0 = iVar;
        this.val$id = str;
    }

    public void run() {
        this.this$0.l(this.val$id);
    }
}
