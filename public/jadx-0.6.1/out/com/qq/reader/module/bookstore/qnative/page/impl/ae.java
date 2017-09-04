package com.qq.reader.module.bookstore.qnative.page.impl;

import android.os.Bundle;
import com.qq.reader.common.monitor.f;
import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.card.impl.MyFavorEmptyCard;
import com.qq.reader.module.feed.data.impl.FeedBaseCard;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: NativeServerFavouritePage */
public class ae extends af {
    public ae(Bundle bundle) {
        super(bundle);
    }

    public String a(Bundle bundle) {
        c cVar = new c(bundle);
        StringBuffer stringBuffer = new StringBuffer("listDispatch?");
        stringBuffer.append("action=myfocus");
        return cVar.b(stringBuffer.toString());
    }

    public void b(JSONObject jSONObject) {
        try {
            f.a("FAV", jSONObject.toString());
            JSONArray optJSONArray = jSONObject.optJSONArray("infos");
            this.o = jSONObject.optLong("pagestamp");
            if (this.o == 0 && (optJSONArray == null || optJSONArray.length() == 0)) {
                this.k.add(new MyFavorEmptyCard(this, "MyFavourEmpty"));
                return;
            }
            for (a aVar : com.qq.reader.module.feed.data.impl.c.a(this, optJSONArray)) {
                FeedBaseCard feedBaseCard = (FeedBaseCard) aVar;
                feedBaseCard.setEventListener(l());
                feedBaseCard.setShowDivider(true);
                feedBaseCard.isClickEnable = true;
                this.k.add(feedBaseCard);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean a() {
        return false;
    }
}
