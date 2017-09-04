package com.qq.reader.module.bookstore.qnative.card;

import com.qq.reader.common.monitor.f;
import com.qq.reader.module.bookstore.qnative.card.impl.DefaultCard;

/* compiled from: CardFactory */
public class b {
    private static volatile b b;
    public String a;

    public static b a() {
        if (b == null) {
            synchronized (b.class) {
                if (b == null) {
                    b = new b();
                }
            }
        }
        return b;
    }

    private b() {
        this.a = "";
        this.a = getClass().getPackage().getName() + ".impl.";
    }

    public a a(com.qq.reader.module.bookstore.qnative.page.b bVar, int i, String str, String str2) {
        a aVar;
        try {
            aVar = (a) Class.forName(this.a + str).getDeclaredConstructor(new Class[]{com.qq.reader.module.bookstore.qnative.page.b.class, String.class}).newInstance(new Object[]{bVar, str2});
        } catch (Exception e) {
            e.printStackTrace();
            aVar = null;
        }
        if (aVar == null) {
            f.a("LocalStore", "Found unDefined Type : " + str);
            aVar = new DefaultCard(bVar, str2);
        }
        aVar.setPageCacheKey(i);
        return aVar;
    }
}
