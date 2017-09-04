package com.qq.reader.module.comic.d;

import android.os.Bundle;
import com.qq.reader.appconfig.e;
import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.bookstore.qnative.page.impl.af;
import com.qq.reader.module.comic.card.ComicColumnListCard;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: NativeServerPageOfUpdate */
public class d extends af {
    String a = null;
    String b = "";

    public d(Bundle bundle) {
        super(bundle);
    }

    public String a(Bundle bundle) {
        return new c(bundle).a(e.do, "?");
    }

    public void b(JSONObject jSONObject) {
        JSONObject optJSONObject = jSONObject.optJSONObject("data");
        this.o = optJSONObject.optLong("pagestamp");
        JSONObject optJSONObject2 = optJSONObject.optJSONObject("info");
        if (optJSONObject2 != null) {
            this.n = new com.qq.reader.module.bookstore.qnative.page.d();
            this.n.a(optJSONObject2);
            this.a = this.n.c();
        }
        this.b = this.n.h();
        JSONArray optJSONArray = optJSONObject.optJSONArray("bookList");
        if (optJSONArray != null && optJSONArray.length() != 0) {
            ComicColumnListCard comicColumnListCard = new ComicColumnListCard(this, "comiclist");
            comicColumnListCard.fillData(optJSONArray);
            comicColumnListCard.setEventListener(l());
            this.k.add(comicColumnListCard);
            this.l.put(comicColumnListCard.getCardId(), comicColumnListCard);
        }
    }

    public boolean a() {
        return false;
    }

    public boolean b() {
        return true;
    }

    public void a(b bVar) {
        super.a(bVar);
        if (bVar instanceof d) {
            this.a = ((d) bVar).a;
            if (this.o != 1) {
            }
        }
    }
}
