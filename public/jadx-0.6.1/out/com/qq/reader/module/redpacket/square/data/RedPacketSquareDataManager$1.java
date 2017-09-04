package com.qq.reader.module.redpacket.square.data;

import com.qq.reader.common.readertask.ordinal.ReaderDBTask;
import java.util.ArrayList;

class RedPacketSquareDataManager$1 extends ReaderDBTask {
    final /* synthetic */ d this$0;
    final /* synthetic */ ArrayList val$packets;

    RedPacketSquareDataManager$1(d dVar, ArrayList arrayList) {
        this.this$0 = dVar;
        this.val$packets = arrayList;
    }

    public void run() {
        c.b().a(this.val$packets);
    }
}
