package com.qq.reader.common.db.handle;

import com.qq.reader.common.readertask.ordinal.ReaderDBTask;

class BookmarkHandle$2 extends ReaderDBTask {
    final /* synthetic */ i this$0;
    final /* synthetic */ String val$id;
    final /* synthetic */ int val$sortIndex;

    BookmarkHandle$2(i iVar, String str, int i) {
        this.this$0 = iVar;
        this.val$id = str;
        this.val$sortIndex = i;
    }

    public void run() {
        this.this$0.a(this.val$id, this.val$sortIndex);
    }
}
