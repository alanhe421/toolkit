package com.qq.reader.module.comic.d;

import android.os.Bundle;
import com.qq.reader.appconfig.e;
import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.bookstore.qnative.page.d;
import com.qq.reader.module.bookstore.qnative.page.impl.af;
import com.qq.reader.module.comic.card.ComicColumnListCard;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: NativeServerPageOfComicColumn */
public class b extends af {
    private String a;
    private String b;

    public b(Bundle bundle) {
        super(bundle);
    }

    public String a(Bundle bundle) {
        c cVar = new c(bundle);
        this.b = bundle.getString("columnId", "");
        return cVar.a(e.do, "?");
    }

    public void b(JSONObject jSONObject) {
        JSONObject optJSONObject = jSONObject.optJSONObject("data");
        this.o = optJSONObject.optLong("pagestamp");
        JSONObject optJSONObject2 = optJSONObject.optJSONObject("info");
        if (optJSONObject2 != null) {
            this.n = new d();
            this.n.a(optJSONObject2);
        }
        this.a = this.n.h();
        JSONArray optJSONArray = optJSONObject.optJSONArray("bookList");
        ComicColumnListCard comicColumnListCard = new ComicColumnListCard(this, "comiclist");
        comicColumnListCard.fillData(optJSONArray);
        comicColumnListCard.setEventListener(l());
        this.k.add(comicColumnListCard);
        this.l.put(comicColumnListCard.getCardId(), comicColumnListCard);
    }

    public boolean a() {
        return false;
    }

    public boolean b() {
        return true;
    }

    public void a(com.qq.reader.module.bookstore.qnative.page.b bVar) {
        super.a(bVar);
    }
}
