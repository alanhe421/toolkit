package com.qq.reader.module.findhome;

import com.qq.reader.module.bookstore.qnative.page.impl.af;

/* compiled from: FindHomeItemFactory */
public class e {
    public static FindHomeItemCard a(af afVar, int i) {
        switch (i) {
            case 4:
                return new FindHomeGameItemCard(afVar, "FindHomeGameItemCard");
            default:
                return new FindHomeItemCard(afVar, "FindHomeItemCard");
        }
    }
}
