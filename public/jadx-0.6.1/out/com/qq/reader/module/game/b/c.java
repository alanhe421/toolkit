package com.qq.reader.module.game.b;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.monitor.i;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.bookstore.qnative.page.impl.af;
import com.qq.reader.module.game.a;
import com.qq.reader.module.game.card.GameAdvBannerCard;
import com.qq.reader.module.game.card.GameCardPlayedGame;
import com.qq.reader.module.game.card.GameEnterCard;
import com.qq.reader.module.game.card.GameGridListCard;
import com.qq.reader.module.game.card.GameHorizontalListCard;
import com.qq.reader.module.game.data.d;
import com.tencent.android.tpush.common.Constants;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: NativeServerPageOfGameMain */
public class c extends af {
    public int a;
    private int b;
    private d c;

    public d x() {
        return this.c;
    }

    public c(Bundle bundle) {
        super(bundle);
    }

    public String a(Bundle bundle) {
        com.qq.reader.module.bookstore.qnative.c cVar = new com.qq.reader.module.bookstore.qnative.c(bundle);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("?");
        return cVar.a(e.cH, stringBuilder.toString());
    }

    public void a(b bVar) {
        super.a(bVar);
        this.b = ((c) bVar).b;
        this.c = ((c) bVar).c;
        i.a("event_A221", null, ReaderApplication.getApplicationImp());
    }

    public void b(JSONObject jSONObject) {
        try {
            super.b(jSONObject);
            this.a = jSONObject.optInt("code");
            this.b = jSONObject.optInt("pageVersion");
            JSONObject optJSONObject = jSONObject.optJSONObject(Constants.FLAG_ACTIVITY_NAME);
            if (optJSONObject != null) {
                com.qq.reader.module.game.data.c cVar = new com.qq.reader.module.game.data.c();
                cVar.b(optJSONObject);
                a.c(cVar);
                this.c = new d(optJSONObject.optString("imgBottom"), optJSONObject.optString("imgButton"), optJSONObject.optString("buttonColor"), cVar);
            }
            if (com.qq.reader.common.login.c.b()) {
                com.qq.reader.module.game.presenter.a.a().a(jSONObject.optJSONObject("ticketsData"));
            }
            JSONArray optJSONArray = jSONObject.optJSONArray("columns");
            com.qq.reader.module.bookstore.qnative.card.a aVar = null;
            int i = 0;
            while (optJSONArray != null && i < optJSONArray.length()) {
                JSONObject optJSONObject2 = optJSONArray.optJSONObject(i);
                int optInt = optJSONObject2.optInt("type");
                switch (optInt) {
                    case 0:
                        aVar = new GameEnterCard(this, optInt + "");
                        break;
                    case 1:
                        aVar = new GameCardPlayedGame(this, optInt + "");
                        break;
                    case 2:
                        aVar = new GameGridListCard(this, optInt + "");
                        break;
                    case 3:
                        aVar = new GameHorizontalListCard(this, optInt + "");
                        break;
                    case 4:
                        aVar = new GameAdvBannerCard(this, optInt + "");
                        break;
                }
                if (aVar != null) {
                    aVar.fillData(optJSONObject2);
                    aVar.setEventListener(l());
                    this.k.add(aVar);
                    this.l.put(optInt + "", aVar);
                }
                i++;
            }
            com.qq.reader.common.monitor.debug.c.e("GAME", "mCardList size is " + this.k.size() + "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void a(JSONObject jSONObject, JSONObject jSONObject2) {
        super.a(jSONObject, jSONObject2);
    }

    public boolean a() {
        return false;
    }

    public boolean b() {
        return true;
    }

    public void a(int i, int i2, Intent intent, Handler handler) {
        super.a(i, i2, intent, handler);
    }
}
