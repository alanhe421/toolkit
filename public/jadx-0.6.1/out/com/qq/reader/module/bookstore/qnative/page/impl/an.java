package com.qq.reader.module.bookstore.qnative.page.impl;

import android.os.Bundle;
import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.card.impl.SearchResultBookCard2;
import com.qq.reader.module.feed.data.impl.FeedBaseCard;
import java.net.URLDecoder;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: NativeServerPageOfPublisherAndAuthor */
public class an extends af {
    public ArrayList<String> a = new ArrayList();

    public an(Bundle bundle) {
        super(bundle);
    }

    public String a(Bundle bundle) {
        c cVar = new c(bundle);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("search?key=" + bundle.getString("searchkey")).append("&needDirect=" + bundle.getInt("needDirect")).append("&searchType=" + bundle.getInt("searchType")).append("&needRelate=1");
        stringBuilder.append("&action=search");
        return cVar.b(stringBuilder.toString());
    }

    public void b(JSONObject jSONObject) {
        try {
            this.o = jSONObject.optLong("pagestamp");
            JSONArray optJSONArray = jSONObject.optJSONArray("directList");
            String string = this.f.getString("searchkey");
            JSONArray optJSONArray2 = jSONObject.optJSONArray("bookList");
            for (int i = 0; i < optJSONArray2.length(); i++) {
                a searchResultBookCard2 = new SearchResultBookCard2(this, "book", URLDecoder.decode(string));
                searchResultBookCard2.setEventListener(l());
                searchResultBookCard2.fillData(optJSONArray2.getJSONObject(i));
                ((FeedBaseCard) searchResultBookCard2).setShowDivider(true);
                this.k.add(searchResultBookCard2);
                this.l.put(searchResultBookCard2.getCardId(), searchResultBookCard2);
            }
            JSONArray optJSONArray3 = jSONObject.optJSONArray("relateRetList");
            if ((optJSONArray == null || optJSONArray.length() == 0) && optJSONArray3 != null) {
                for (int i2 = 0; i2 < optJSONArray3.length(); i2++) {
                    this.a.add(optJSONArray3.optString(i2));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean a() {
        return false;
    }
}
