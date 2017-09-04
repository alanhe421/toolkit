package com.qq.reader.module.findhome;

import android.view.View;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.qurl.c;
import org.json.JSONObject;

public class FindHomeGameItemCard extends FindHomeItemCard {
    public FindHomeGameItemCard(b bVar, String str) {
        super(bVar, str);
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        this.findHomePageItem = new d();
        this.findHomePageItem.parseData(jSONObject);
        JSONObject optJSONObject = jSONObject.optJSONObject("expand");
        if (optJSONObject != null) {
            a cVar = new c();
            if (cVar.a(optJSONObject)) {
                this.findHomePageItem.a(cVar);
            }
        }
        return true;
    }

    protected void onItemCardClick(View view) {
        try {
            c.a(getEvnetListener().getFromActivity(), this.findHomePageItem.c(), null);
        } catch (Exception e) {
            com.qq.reader.common.monitor.debug.c.e("FindhomeItemCard", e.getMessage());
        }
        handleClickStatistics(this.findHomePageItem.h());
    }
}
