package com.qq.reader.module.redpacket.square.a;

import com.qq.reader.module.bookstore.qnative.c.a;
import com.qq.reader.module.redpacket.model.RedPacket;
import com.qq.reader.module.redpacket.square.card.SquareCommonCard;
import java.util.ArrayList;

/* compiled from: RedPacketSquareCardBuilder */
public class b {
    public static ArrayList<SquareCommonCard> a(ArrayList<RedPacket> arrayList, a aVar) {
        if (arrayList == null || arrayList.size() < 1) {
            return null;
        }
        ArrayList<SquareCommonCard> arrayList2 = new ArrayList();
        for (int i = 0; i < arrayList.size(); i++) {
            RedPacket redPacket = (RedPacket) arrayList.get(i);
            SquareCommonCard squareCommonCard = new SquareCommonCard("");
            squareCommonCard.setEventListener(aVar);
            squareCommonCard.setRedPacket(redPacket);
            arrayList2.add(squareCommonCard);
        }
        return arrayList2;
    }
}
