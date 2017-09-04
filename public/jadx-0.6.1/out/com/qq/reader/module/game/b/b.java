package com.qq.reader.module.game.b;

import android.os.Bundle;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.monitor.i;
import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.d;
import com.qq.reader.module.bookstore.qnative.page.impl.af;
import com.qq.reader.module.game.card.GameCategoryAdvCard;
import com.qq.reader.module.game.card.forlog.GameHorizontalCardForRDM;
import com.qq.reader.module.game.fragment.GameCategoryFragment;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: NativeServerPageOfColumn */
public class b extends af {
    private String a;
    private String b;

    public b(Bundle bundle) {
        super(bundle);
    }

    public String a(Bundle bundle) {
        c cVar = new c(bundle);
        this.b = bundle.getString("CID", "");
        return cVar.a(e.cJ, "?categoryId=".concat(this.b));
    }

    protected void a(JSONObject jSONObject) {
        super.a(jSONObject);
        this.a = jSONObject.optString("columnName");
        JSONObject optJSONObject = jSONObject.optJSONObject("ad");
        if (optJSONObject != null && 1 == this.f.getLong("KEY_PAGEINDEX", 1)) {
            a gameCategoryAdvCard = new GameCategoryAdvCard(this, "ad", 2, this.b);
            gameCategoryAdvCard.fillData(optJSONObject);
            gameCategoryAdvCard.setEventListener(l());
            this.k.add(gameCategoryAdvCard);
            this.l.put("ad", gameCategoryAdvCard);
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("games");
        if (optJSONArray != null && optJSONArray.length() > 0) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                a gameHorizontalCardForRDM = new GameHorizontalCardForRDM(this, "games", this.b, 2);
                gameHorizontalCardForRDM.fillData(optJSONArray.optJSONObject(i));
                gameHorizontalCardForRDM.setEventListener(l());
                this.k.add(gameHorizontalCardForRDM);
                this.l.put("games", gameHorizontalCardForRDM);
            }
        }
        this.n = new d();
        this.n.b(this.a);
    }

    public boolean a() {
        return false;
    }

    public boolean b() {
        return true;
    }

    public Class c() {
        return GameCategoryFragment.class;
    }

    public void a(com.qq.reader.module.bookstore.qnative.page.b bVar) {
        super.a(bVar);
        String string = this.f.getString("CID", null);
        if (1 == this.f.getLong("KEY_PAGEINDEX", 1)) {
            Map hashMap = new HashMap();
            hashMap.put(s.ORIGIN, string);
            i.a("event_A227", hashMap, ReaderApplication.getApplicationImp());
        }
    }
}
