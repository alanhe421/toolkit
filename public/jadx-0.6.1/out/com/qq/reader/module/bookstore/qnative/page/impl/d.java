package com.qq.reader.module.bookstore.qnative.page.impl;

import android.os.Bundle;
import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.bookstore.qnative.card.impl.ListCard4BigBook;
import com.qq.reader.module.bookstore.qnative.card.impl.ListCardCommon;
import com.qq.reader.module.bookstore.qnative.fragment.NativePageFragmentforClassic;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: NativeLocalClassicalBookPage */
public class d extends af {
    public d(Bundle bundle) {
        super(bundle);
    }

    public void b(JSONObject jSONObject) {
        this.k.clear();
        this.l.clear();
        this.p = jSONObject.toString();
        this.q = jSONObject;
        jSONObject.optJSONObject("topicinfo");
        a(jSONObject.optLong("expireTime") * 1000);
        this.o = jSONObject.optLong("pagestamp");
        this.r = jSONObject.optInt("nextPage");
        JSONObject optJSONObject = jSONObject.optJSONObject("info");
        if (optJSONObject != null) {
            this.n = new com.qq.reader.module.bookstore.qnative.page.d();
            this.n.a(optJSONObject);
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("bookList");
        if (optJSONArray != null) {
            ListCardCommon listCard4BigBook = new ListCard4BigBook(this, "list");
            listCard4BigBook.setEventListener(l());
            listCard4BigBook.fillData(optJSONArray);
            this.k.add(listCard4BigBook);
            this.l.put(listCard4BigBook.getCardId(), listCard4BigBook);
            return;
        }
        a(jSONObject);
    }

    public String a(Bundle bundle) {
        return new c(bundle).b("listDispatch?");
    }

    public Class c() {
        return NativePageFragmentforClassic.class;
    }
}
