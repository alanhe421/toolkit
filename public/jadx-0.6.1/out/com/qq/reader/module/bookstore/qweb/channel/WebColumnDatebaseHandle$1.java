package com.qq.reader.module.bookstore.qweb.channel;

import com.qq.reader.common.readertask.ordinal.ReaderDBTask;

class WebColumnDatebaseHandle$1 extends ReaderDBTask {
    final /* synthetic */ d this$0;
    final /* synthetic */ boolean val$mIsSelected;
    final /* synthetic */ int val$mWebId;

    WebColumnDatebaseHandle$1(d dVar, int i, boolean z) {
        this.this$0 = dVar;
        this.val$mWebId = i;
        this.val$mIsSelected = z;
    }

    public void run() {
        this.this$0.a(this.val$mWebId, this.val$mIsSelected);
    }
}
