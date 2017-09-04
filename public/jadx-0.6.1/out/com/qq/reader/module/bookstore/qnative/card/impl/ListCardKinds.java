package com.qq.reader.module.bookstore.qnative.card.impl;

import android.view.View;
import android.widget.ListView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.f;
import com.qq.reader.module.bookstore.qnative.a.d;
import com.qq.reader.module.bookstore.qnative.card.BaseListCard;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.item.w;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import com.tencent.feedback.proguard.R;
import org.json.JSONArray;
import org.json.JSONObject;

public class ListCardKinds extends BaseListCard {
    private static final String tag = "listbookcard2";

    public ListCardKinds(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.localbookcardlistlayout;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        int i = 0;
        JSONArray optJSONArray = jSONObject.optJSONArray(ComicStoreExclusiveItemCard.NET_AD_ATTR_ADVS);
        if (optJSONArray == null) {
            return false;
        }
        getItemList().clear();
        int length = optJSONArray.length();
        if (length <= 0) {
            return false;
        }
        while (i < length) {
            JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
            s wVar = new w();
            wVar.parseData(jSONObject2);
            addItem(wVar);
            i++;
        }
        return true;
    }

    public void attachView(View view) {
        try {
            ListView listView = (ListView) view;
            this.mAdapter = new d(ReaderApplication.getApplicationImp(), getItemList());
            ((d) this.mAdapter).a(getEvnetListener());
            listView.setAdapter(this.mAdapter);
        } catch (Exception e) {
            f.d(tag, "Exception " + e);
        }
    }
}
