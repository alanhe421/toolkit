package com.qq.reader.module.bookstore.qnative.card.impl;

import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.item.u;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;

public class ListCard4BookCollectList extends ListCardCommon {
    public ListCard4BookCollectList(b bVar, String str) {
        super(bVar, str);
    }

    public s createListItem() {
        return new u();
    }

    public int getCardItemLayoutId() {
        return R.layout.nativestore_bookcollectlist_item;
    }
}
