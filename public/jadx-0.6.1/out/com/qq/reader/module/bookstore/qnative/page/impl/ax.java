package com.qq.reader.module.bookstore.qnative.page.impl;

import android.os.Bundle;
import android.text.TextUtils;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.module.bookstore.qnative.card.impl.ListCard4RealtimeRecFreeDiscountBook;
import com.qq.reader.module.bookstore.qnative.card.impl.ListCardCommon;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: NativeServerRealtimeRecBookListPage */
public class ax extends af {
    public ax(Bundle bundle) {
        super(bundle);
    }

    public void b(JSONObject jSONObject) {
        super.b(jSONObject);
        this.k.clear();
        this.l.clear();
        String string = j().getString("key_data");
        if (!TextUtils.isEmpty(string)) {
            this.p = string;
            try {
                JSONArray optJSONArray = new JSONObject(string).optJSONArray("bookList");
                if (optJSONArray != null) {
                    ListCardCommon listCard4RealtimeRecFreeDiscountBook = new ListCard4RealtimeRecFreeDiscountBook(this, "bookList");
                    listCard4RealtimeRecFreeDiscountBook.setEventListener(l());
                    listCard4RealtimeRecFreeDiscountBook.fillData(optJSONArray);
                    this.k.add(listCard4RealtimeRecFreeDiscountBook);
                    this.l.put(listCard4RealtimeRecFreeDiscountBook.getCardId(), listCard4RealtimeRecFreeDiscountBook);
                }
            } catch (Exception e) {
                c.e("Error", e.getMessage());
            }
            this.o = 0;
        }
    }
}
