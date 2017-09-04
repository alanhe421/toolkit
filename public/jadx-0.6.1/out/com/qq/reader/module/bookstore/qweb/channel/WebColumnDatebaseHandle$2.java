package com.qq.reader.module.bookstore.qweb.channel;

import com.qq.reader.common.readertask.ordinal.ReaderDBTask;
import java.util.ArrayList;

class WebColumnDatebaseHandle$2 extends ReaderDBTask {
    final /* synthetic */ d this$0;
    final /* synthetic */ ArrayList val$list;

    WebColumnDatebaseHandle$2(d dVar, ArrayList arrayList) {
        this.this$0 = dVar;
        this.val$list = arrayList;
    }

    public void run() {
        this.this$0.b(this.val$list);
    }
}
