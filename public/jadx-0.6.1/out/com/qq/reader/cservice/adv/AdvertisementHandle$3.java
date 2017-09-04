package com.qq.reader.cservice.adv;

import com.qq.reader.common.readertask.ordinal.ReaderDBTask;

class AdvertisementHandle$3 extends ReaderDBTask {
    final /* synthetic */ b this$0;
    final /* synthetic */ a val$adv;

    AdvertisementHandle$3(b bVar, a aVar) {
        this.this$0 = bVar;
        this.val$adv = aVar;
    }

    public void run() {
        this.this$0.e(this.val$adv);
    }
}
