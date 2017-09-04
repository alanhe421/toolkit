package com.qq.reader.module.redpacket.singlebookpacket;

import com.qq.reader.common.readertask.ordinal.ReaderShortTask;
import com.qq.reader.module.bookstore.qnative.c.a;
import com.qq.reader.module.redpacket.model.RedPacket;
import com.qq.reader.module.redpacket.singlebookpacket.card.SingleBookValidCard;

class RedPacketSingleBookDataLoader$2 extends ReaderShortTask {
    final /* synthetic */ a this$0;
    final /* synthetic */ a val$listener;

    RedPacketSingleBookDataLoader$2(a aVar, a aVar2) {
        this.this$0 = aVar;
        this.val$listener = aVar2;
    }

    public void run() {
        c cVar;
        super.run();
        synchronized (this.this$0.b) {
            cVar = new c(3, this.val$listener);
            cVar.h().d().clear();
            for (RedPacket redPacket : this.this$0.b) {
                if (redPacket.d() > this.this$0.c) {
                    SingleBookValidCard singleBookValidCard = new SingleBookValidCard(null, SingleBookValidCard.class.getSimpleName());
                    singleBookValidCard.setItem(redPacket);
                    singleBookValidCard.setEventListener(this.val$listener);
                    cVar.h().d().add(singleBookValidCard);
                }
            }
            this.this$0.b.clear();
        }
        this.this$0.a(this.this$0.a, cVar);
    }
}
