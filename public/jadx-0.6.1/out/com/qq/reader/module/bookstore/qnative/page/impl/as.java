package com.qq.reader.module.bookstore.qnative.page.impl;

import android.os.Bundle;
import com.qq.reader.appconfig.e;
import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.bookstore.qnative.card.impl.SurroundItemCard;
import com.qq.reader.module.bookstore.qnative.fragment.NativePageFragmentForSurroundingAll;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: NativeServerPageOfSurroundingAll */
public class as extends af {
    public as(Bundle bundle) {
        super(bundle);
    }

    public String a(Bundle bundle) {
        return new c(bundle).a(e.dh, "");
    }

    public void b(JSONObject jSONObject) {
        try {
            this.k.clear();
            this.l.clear();
            JSONArray optJSONArray = jSONObject.optJSONArray("dynamicInfo");
            this.o = jSONObject.optLong("pagestamp");
            if (optJSONArray != null && optJSONArray.length() > 0) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    SurroundItemCard surroundItemCard = new SurroundItemCard(this, "");
                    if (surroundItemCard != null) {
                        surroundItemCard.setEventListener(l());
                        surroundItemCard.fillData(optJSONObject);
                        this.k.add(surroundItemCard);
                        this.l.put(surroundItemCard.getCardId(), surroundItemCard);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean a() {
        return false;
    }

    public boolean b() {
        return true;
    }

    public Class c() {
        return NativePageFragmentForSurroundingAll.class;
    }
}
