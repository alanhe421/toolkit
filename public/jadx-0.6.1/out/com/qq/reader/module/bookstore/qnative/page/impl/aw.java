package com.qq.reader.module.bookstore.qnative.page.impl;

import android.os.Bundle;
import com.qq.reader.appconfig.e;
import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.bookstore.qnative.card.impl.ListCardRankBoardBook;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.bookstore.qnative.page.d;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: NativeServerRankDetailListBPage */
public class aw extends af {
    private ArrayList<s> a = new ArrayList();
    private boolean b = false;
    private ArrayList<String> c = new ArrayList();

    public aw(Bundle bundle) {
        super(bundle);
    }

    public String a(Bundle bundle) {
        if (this.f != null) {
            this.f.putString("ABTEST_PARAM", "1");
        }
        c cVar = new c(this.f);
        StringBuffer stringBuffer = new StringBuffer("listDispatch?");
        stringBuffer.append("action=rank");
        return cVar.a(e.a, stringBuffer.toString());
    }

    public void a(b bVar) {
        super.a(bVar);
        this.b = ((aw) bVar).x();
    }

    public void b(JSONObject jSONObject) {
        if (jSONObject != null) {
            JSONArray optJSONArray = jSONObject.optJSONArray("bookList");
            this.a.clear();
            JSONObject optJSONObject = jSONObject.optJSONObject("info");
            if (optJSONObject != null) {
                this.n = new d();
                this.n.a(optJSONObject);
            }
            this.o = jSONObject.optLong("pagestamp");
            a(jSONObject.optLong("expireTime", 0) * 1000);
            if (optJSONArray == null || optJSONArray.length() <= 0) {
                a(jSONObject);
                return;
            }
            ListCardRankBoardBook listCardRankBoardBook = new ListCardRankBoardBook(this, "bookList");
            for (int i = 0; i < optJSONArray.length(); i++) {
                try {
                    JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                    int optInt = jSONObject2.optInt("first");
                    if (optInt != 0) {
                        this.c.add(String.valueOf(optInt));
                        JSONArray optJSONArray2 = jSONObject2.optJSONArray("second");
                        if (optJSONArray2 != null && optJSONArray2.length() > 0) {
                            listCardRankBoardBook.setEventListener(l());
                            listCardRankBoardBook.setIsTenYearsRankBoard(true);
                            listCardRankBoardBook.setYears(optInt);
                            listCardRankBoardBook.setActionId(this.n);
                            listCardRankBoardBook.fillData(optJSONArray2);
                            if (i == optJSONArray.length() - 1) {
                                this.k.add(listCardRankBoardBook);
                                this.l.put(listCardRankBoardBook.getCardId(), listCardRankBoardBook);
                            }
                            this.b = true;
                        }
                    } else if (jSONObject2 != null) {
                        listCardRankBoardBook.setEventListener(l());
                        listCardRankBoardBook.setActionId(this.n);
                        listCardRankBoardBook.fillData(optJSONArray);
                        this.k.add(listCardRankBoardBook);
                        this.l.put(listCardRankBoardBook.getCardId(), listCardRankBoardBook);
                        return;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean x() {
        return this.b;
    }

    public ArrayList<String> y() {
        return this.c;
    }
}
