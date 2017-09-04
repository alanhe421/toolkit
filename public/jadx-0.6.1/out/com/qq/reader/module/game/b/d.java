package com.qq.reader.module.game.b;

import android.os.Bundle;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.monitor.i;
import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.bookstore.qnative.page.impl.af;
import com.qq.reader.module.game.card.MyGameListCard;
import com.qq.reader.module.game.card.forlog.GameHorizontalCardForRDM;
import com.qq.reader.module.game.fragment.GameCategoryFragment;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: NativeServerPageOfMyGame */
public class d extends af {
    public d(Bundle bundle) {
        super(bundle);
    }

    public String a(Bundle bundle) {
        String str = "nativepage/game/getMyGameList?";
        return new c(bundle).a(e.a, "nativepage/game/getMyGameList?");
    }

    public boolean a() {
        return false;
    }

    public Class c() {
        return GameCategoryFragment.class;
    }

    protected void a(JSONObject jSONObject) {
        JSONArray optJSONArray = jSONObject.optJSONArray("games");
        if (optJSONArray != null && optJSONArray.length() > 0) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    MyGameListCard gameHorizontalCardForRDM = new GameHorizontalCardForRDM(this, "my_game", null, 3);
                    gameHorizontalCardForRDM.setEventListener(l());
                    gameHorizontalCardForRDM.fillData(optJSONObject);
                    this.k.add(gameHorizontalCardForRDM);
                    this.l.put(gameHorizontalCardForRDM.getCardId(), gameHorizontalCardForRDM);
                }
            }
        }
    }

    public void a(b bVar) {
        super.a(bVar);
        if (1 == this.f.getLong("KEY_PAGEINDEX", 1)) {
            i.a("event_A213", null, ReaderApplication.getApplicationImp());
        }
    }

    public boolean b() {
        return true;
    }
}
