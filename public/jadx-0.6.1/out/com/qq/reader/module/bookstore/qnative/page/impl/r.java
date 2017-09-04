package com.qq.reader.module.bookstore.qnative.page.impl;

import android.os.Bundle;
import com.qq.reader.module.bookstore.qnative.card.impl.ListCard4AudioBook;
import com.qq.reader.module.bookstore.qnative.card.impl.ListCardCommon;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: NativeServerAudioCategoryListPage */
public class r extends af {
    public r(Bundle bundle) {
        super(bundle);
    }

    public void b(JSONObject jSONObject) {
        super.b(jSONObject);
        JSONArray optJSONArray = jSONObject.optJSONArray("bookList");
        this.k.clear();
        this.l.clear();
        if (optJSONArray != null) {
            ListCardCommon listCard4AudioBook = new ListCard4AudioBook(this, "bookList", this.f);
            listCard4AudioBook.setEventListener(l());
            listCard4AudioBook.fillData(optJSONArray);
            this.k.add(listCard4AudioBook);
            this.l.put(listCard4AudioBook.getCardId(), listCard4AudioBook);
        }
    }
}
