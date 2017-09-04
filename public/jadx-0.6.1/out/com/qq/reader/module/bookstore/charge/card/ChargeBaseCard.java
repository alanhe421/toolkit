package com.qq.reader.module.bookstore.charge.card;

import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.page.b;

public abstract class ChargeBaseCard extends a {
    public ChargeBaseCard(b bVar, String str) {
        super(bVar, str);
    }

    protected boolean isInflateViewWithParent() {
        return true;
    }
}
