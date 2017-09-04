package com.qq.reader.module.game.b;

import android.os.Bundle;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.monitor.i;
import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.bookstore.qnative.page.impl.af;
import com.qq.reader.module.game.card.GameCategoryAdvCard;
import com.qq.reader.module.game.card.forlog.GameHorizontalCardForRDM;
import com.qq.reader.module.game.fragment.GameCategoryFragment;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: NativeServerPageOfCategory */
public class a extends af {
    String a = null;

    public a(Bundle bundle) {
        super(bundle);
    }

    public String a(Bundle bundle) {
        return new c(bundle).a(e.cK, "?");
    }

    protected void a(JSONObject jSONObject) {
        super.a(jSONObject);
        JSONObject optJSONObject = jSONObject.optJSONObject("ad");
        try {
            if (this.n != null) {
                this.a = this.n.c();
            }
            if (optJSONObject != null && 1 == this.f.getLong("KEY_PAGEINDEX", 1)) {
                com.qq.reader.module.bookstore.qnative.card.a gameCategoryAdvCard = new GameCategoryAdvCard(this, "games", 1, this.a);
                gameCategoryAdvCard.setEventListener(l());
                gameCategoryAdvCard.fillData(optJSONObject);
                this.k.add(gameCategoryAdvCard);
                this.l.put("ad", gameCategoryAdvCard);
            }
            JSONArray optJSONArray = jSONObject.optJSONArray("games");
            int i = 0;
            while (optJSONArray != null && i < optJSONArray.length()) {
                JSONObject optJSONObject2 = optJSONArray.optJSONObject(i);
                com.qq.reader.module.bookstore.qnative.card.a gameHorizontalCardForRDM = new GameHorizontalCardForRDM(this, "games", this.a, 1);
                gameHorizontalCardForRDM.setEventListener(l());
                gameHorizontalCardForRDM.fillData(optJSONObject2);
                this.k.add(gameHorizontalCardForRDM);
                this.l.put("games", gameHorizontalCardForRDM);
                i++;
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
        return GameCategoryFragment.class;
    }

    public void a(b bVar) {
        super.a(bVar);
        if (1 == this.f.getLong("KEY_PAGEINDEX", 1) && (bVar instanceof a)) {
            Map hashMap = new HashMap();
            hashMap.put(s.ORIGIN, ((a) bVar).a);
            i.a("event_A233", hashMap, ReaderApplication.getApplicationImp());
        }
    }
}
