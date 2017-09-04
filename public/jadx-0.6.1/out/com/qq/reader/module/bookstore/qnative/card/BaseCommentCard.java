package com.qq.reader.module.bookstore.qnative.card;

import com.qq.reader.module.bookstore.qnative.page.b;

public abstract class BaseCommentCard extends a {
    private int mCtype;

    public BaseCommentCard(b bVar, String str, int i) {
        super(bVar, str);
        this.mCtype = i;
    }

    public int getCtype() {
        return this.mCtype;
    }
}
