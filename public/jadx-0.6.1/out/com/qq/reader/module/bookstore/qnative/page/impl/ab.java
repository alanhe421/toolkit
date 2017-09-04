package com.qq.reader.module.bookstore.qnative.page.impl;

import android.os.Bundle;
import com.qq.reader.module.bookstore.qnative.card.impl.ListCard4DiscountBook;
import com.qq.reader.module.bookstore.qnative.card.impl.ListCardCommon;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: NativeServerDiscountBookListPage */
public class ab extends af {
    public ab(Bundle bundle) {
        super(bundle);
    }

    public void b(JSONObject jSONObject) {
        super.b(jSONObject);
        this.k.clear();
        this.l.clear();
        this.p = jSONObject.toString();
        JSONArray optJSONArray = jSONObject.optJSONArray("bookList");
        if (optJSONArray != null) {
            ListCardCommon listCard4DiscountBook = new ListCard4DiscountBook(this, "bookList");
            listCard4DiscountBook.setEventListener(l());
            listCard4DiscountBook.fillData(optJSONArray);
            this.k.add(listCard4DiscountBook);
            this.l.put(listCard4DiscountBook.getCardId(), listCard4DiscountBook);
        }
    }
}
