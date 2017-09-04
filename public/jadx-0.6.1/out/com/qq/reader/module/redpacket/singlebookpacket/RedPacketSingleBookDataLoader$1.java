package com.qq.reader.module.redpacket.singlebookpacket;

import com.qq.reader.common.readertask.ordinal.ReaderShortTask;
import com.qq.reader.module.bookstore.qnative.c.a;
import java.util.List;

class RedPacketSingleBookDataLoader$1 extends ReaderShortTask {
    final /* synthetic */ a this$0;
    final /* synthetic */ a val$listener;
    final /* synthetic */ List val$redPacketList;

    RedPacketSingleBookDataLoader$1(a aVar, List list, a aVar2) {
        this.this$0 = aVar;
        this.val$redPacketList = list;
        this.val$listener = aVar2;
    }

    public void run() {
        super.run();
        this.this$0.b(this.val$redPacketList, this.val$listener);
    }
}
