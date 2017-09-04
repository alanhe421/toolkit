package com.qq.reader.module.bookstore.qnative.a;

import com.qq.reader.module.bookstore.qnative.card.c;

/* compiled from: NativeAutoViewTypeAdapter */
public abstract class a extends com.qq.reader.widget.a {
    public c a;

    public a(int i) {
        this.a = new c(i);
    }

    public int getViewTypeCount() {
        return this.a.a;
    }

    public int getItemViewType(int i) {
        int b = this.a.b(getItem(i));
        return b > 0 ? b : 1;
    }
}
