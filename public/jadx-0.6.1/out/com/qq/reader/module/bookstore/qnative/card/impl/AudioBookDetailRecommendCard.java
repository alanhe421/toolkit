package com.qq.reader.module.bookstore.qnative.card.impl;

import com.hmt.analytics.UpdateManager;
import com.qq.reader.common.monitor.i;
import com.qq.reader.module.audio.card.AudioZoneBookHorCard;
import com.qq.reader.module.audio.view.AudioZone3BookHorView.a;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import org.json.JSONArray;
import org.json.JSONObject;

public class AudioBookDetailRecommendCard extends AudioZoneBookHorCard {
    public AudioBookDetailRecommendCard(b bVar, String str) {
        super(bVar, str);
    }

    public void attachView() {
        super.attachView();
        i.a("event_B253", null, UpdateManager.b);
    }

    protected a getReportOnclickListener() {
        return new a(this) {
            final /* synthetic */ AudioBookDetailRecommendCard a;

            {
                this.a = r1;
            }

            public void onClick() {
                i.a("event_B254", null, UpdateManager.b);
            }
        };
    }

    public boolean parseData(JSONObject jSONObject) throws Exception {
        int i = 0;
        this.mServerTitle = "相关推荐";
        this.mDispaly = 3;
        JSONArray optJSONArray = jSONObject.optJSONArray("recAudios");
        if (optJSONArray == null) {
            return false;
        }
        int length = optJSONArray.length();
        if (length <= 0 || length < this.mDispaly) {
            return false;
        }
        if (getItemList() != null) {
            getItemList().clear();
        }
        while (i < length) {
            JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
            s aVar = new com.qq.reader.module.audio.b.a();
            aVar.parseData(jSONObject2);
            addItem(aVar);
            i++;
        }
        return true;
    }
}
