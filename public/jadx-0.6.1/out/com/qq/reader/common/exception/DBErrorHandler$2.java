package com.qq.reader.common.exception;

import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.db.handle.i;
import com.qq.reader.common.readertask.ordinal.ReaderDBTask;

class DBErrorHandler$2 extends ReaderDBTask {
    final /* synthetic */ a this$0;

    DBErrorHandler$2(a aVar) {
        this.this$0 = aVar;
    }

    public void run() {
        i.c().j();
        i.b();
        d.bi(this.this$0.c.getApplicationContext());
    }
}
