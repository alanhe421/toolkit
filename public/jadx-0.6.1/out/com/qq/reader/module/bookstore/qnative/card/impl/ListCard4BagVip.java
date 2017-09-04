package com.qq.reader.module.bookstore.qnative.card.impl;

import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.item.t;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;

public class ListCard4BagVip extends ListCardCommon {
    public ListCard4BagVip(b bVar, String str) {
        super(bVar, str);
    }

    public s createListItem() {
        return new t();
    }

    public int getCardItemLayoutId() {
        return R.layout.nativestore_bookbaglist_item;
    }
}
