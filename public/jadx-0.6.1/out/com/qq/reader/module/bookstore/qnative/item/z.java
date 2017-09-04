package com.qq.reader.module.bookstore.qnative.item;

import android.view.View;
import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.bookstore.qnative.c.a;

/* compiled from: ListItem */
public abstract class z extends s {
    protected c l = null;

    public abstract void a(View view, int i, boolean z);

    public void a(a aVar) {
        if (this.l != null) {
            this.l.a(aVar);
        }
    }
}
