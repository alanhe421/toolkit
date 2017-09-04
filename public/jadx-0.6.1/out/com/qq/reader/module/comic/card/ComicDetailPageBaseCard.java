package com.qq.reader.module.comic.card;

import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.comic.entity.e;

public abstract class ComicDetailPageBaseCard<T> extends a {
    protected e<T> item;

    public ComicDetailPageBaseCard(b bVar, String str) {
        super(bVar, str);
    }

    public boolean isValid() {
        return true;
    }
}
