package com.qq.reader.module.bookstore.qnative.card.impl;

import android.view.View;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.monitor.f;
import com.qq.reader.module.bookstore.qnative.card.BaseListCard;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.view.pullupdownlist.XListView;
import org.json.JSONArray;
import org.json.JSONObject;

public abstract class ListCardCommon extends BaseListCard {
    public abstract s createListItem();

    public abstract int getCardItemLayoutId();

    public ListCardCommon(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        c.a("listbook", "getResourceId ");
        return 0;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        int i = 0;
        JSONArray optJSONArray = jSONObject.optJSONArray("data");
        if (optJSONArray == null) {
            return false;
        }
        int length = optJSONArray.length();
        getItemList().clear();
        if (length <= 0) {
            return false;
        }
        while (i < length) {
            JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
            s createListItem = createListItem();
            createListItem.parseData(jSONObject2);
            addItem(createListItem);
            i++;
        }
        return true;
    }

    public void attachView(View view) {
        c.a("listbook", "attachView " + System.currentTimeMillis());
        try {
            XListView xListView = (XListView) view;
            this.mAdapter = new com.qq.reader.module.bookstore.qnative.a.c(ReaderApplication.getApplicationImp(), this, this.mIsFromCharis);
            ((com.qq.reader.module.bookstore.qnative.a.c) this.mAdapter).a(getEvnetListener());
            xListView.setAdapter(this.mAdapter);
        } catch (Exception e) {
            f.d("listbook", "Exception " + e);
        }
    }
}
