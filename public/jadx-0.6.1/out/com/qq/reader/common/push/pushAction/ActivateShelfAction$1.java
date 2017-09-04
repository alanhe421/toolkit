package com.qq.reader.common.push.pushAction;

import android.content.Intent;
import com.qq.reader.common.db.handle.a;
import com.qq.reader.common.readertask.ordinal.ReaderDBTask;
import com.qq.reader.module.bookshelf.b;

class ActivateShelfAction$1 extends ReaderDBTask {
    final /* synthetic */ c this$0;
    final /* synthetic */ b val$activeShower;

    ActivateShelfAction$1(c cVar, b bVar) {
        this.this$0 = cVar;
        this.val$activeShower = bVar;
    }

    public void run() {
        a.a().a(this.val$activeShower);
        this.this$0.a().sendBroadcast(new Intent(com.qq.reader.common.c.a.cq));
    }
}
