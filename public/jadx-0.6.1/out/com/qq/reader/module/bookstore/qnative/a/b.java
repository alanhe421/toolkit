package com.qq.reader.module.bookstore.qnative.a;

import android.content.Context;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.card.impl.BookClubReplyCard;
import com.qq.reader.module.bookstore.qnative.item.l;

/* compiled from: NativeBookStoreIndexAdapter */
public class b extends f {
    public b(Context context) {
        super(context);
    }

    public int a(int i) {
        int size = this.c.size();
        for (int i2 = 0; i2 < size; i2++) {
            a aVar = (a) this.c.get(i2);
            if ((aVar instanceof BookClubReplyCard) && i == a((BookClubReplyCard) aVar)) {
                return i2;
            }
        }
        return 0;
    }

    private int a(BookClubReplyCard bookClubReplyCard) {
        return ((l) bookClubReplyCard.getItemList().get(0)).h();
    }
}
