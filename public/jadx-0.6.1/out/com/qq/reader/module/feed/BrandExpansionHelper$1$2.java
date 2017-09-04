package com.qq.reader.module.feed;

import com.qq.reader.common.monitor.i;
import com.qq.reader.common.readertask.ordinal.ReaderShortTask;
import com.qq.reader.module.feed.a.AnonymousClass1;

class BrandExpansionHelper$1$2 extends ReaderShortTask {
    final /* synthetic */ AnonymousClass1 this$1;

    BrandExpansionHelper$1$2(AnonymousClass1 anonymousClass1) {
        this.this$1 = anonymousClass1;
    }

    public void run() {
        super.run();
        i.a("event_D230", null, this.this$1.a.c.getContext());
    }
}
