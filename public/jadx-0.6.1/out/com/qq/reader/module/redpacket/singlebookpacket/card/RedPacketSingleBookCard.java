package com.qq.reader.module.redpacket.singlebookpacket.card;

import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.redpacket.model.RedPacket;

public abstract class RedPacketSingleBookCard extends a {
    private boolean mIsShowDivider = true;
    private RedPacket mItem;

    public RedPacketSingleBookCard(b bVar, String str) {
        super(bVar, str);
    }

    public RedPacket getItem() {
        return this.mItem;
    }

    public void setItem(RedPacket redPacket) {
        this.mItem = redPacket;
    }

    public boolean isShowDivider() {
        return this.mIsShowDivider;
    }

    public void setShowDivider(boolean z) {
        this.mIsShowDivider = z;
    }
}
