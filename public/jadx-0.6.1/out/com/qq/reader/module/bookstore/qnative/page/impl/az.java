package com.qq.reader.module.bookstore.qnative.page.impl;

import android.os.Bundle;
import android.text.TextUtils;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.card.impl.SearchResultBookCard;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: NativeServerSearchToolPage */
public class az extends af {
    private int a = 0;

    public Bundle x() {
        return this.f;
    }

    public az(Bundle bundle) {
        super(bundle);
    }

    public void b(JSONObject jSONObject) {
        try {
            this.o = jSONObject.optLong("pagestamp");
            JSONArray optJSONArray = jSONObject.optJSONArray("bookList");
            this.a = jSONObject.optInt("bookCount");
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                    a searchResultBookCard = new SearchResultBookCard(this, "book");
                    searchResultBookCard.setEventListener(l());
                    searchResultBookCard.fillData(jSONObject2);
                    this.k.add(searchResultBookCard);
                    this.l.put(searchResultBookCard.getCardId(), searchResultBookCard);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean a() {
        return false;
    }

    public int y() {
        return this.a;
    }

    public String a(Bundle bundle) {
        StringBuilder stringBuilder = new StringBuilder();
        Object string = bundle.getString("search_option");
        int i = bundle.getInt("search_result_order", -1);
        if (i < 0) {
            i = d.aR(ReaderApplication.getApplicationImp());
        } else {
            d.E(ReaderApplication.getApplicationImp(), i);
        }
        if (TextUtils.isEmpty(string)) {
            stringBuilder.append("findbook/find?categories=-1&tags=-1&totalwords=-1&updatetime=-1&finished=-1&free=-1&sort=-1");
        } else {
            stringBuilder.append("findbook/find?" + string + "&sort=" + i);
        }
        return new c(this.f).b(stringBuilder.toString());
    }
}
