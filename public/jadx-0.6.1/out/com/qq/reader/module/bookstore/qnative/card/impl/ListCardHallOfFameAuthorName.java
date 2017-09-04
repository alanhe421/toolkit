package com.qq.reader.module.bookstore.qnative.card.impl;

import android.annotation.SuppressLint;
import android.view.View;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.f;
import com.qq.reader.module.bookstore.qnative.a.c;
import com.qq.reader.module.bookstore.qnative.a.g;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.item.y;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.view.pullupdownlist.XListView;
import com.tencent.feedback.proguard.R;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

public class ListCardHallOfFameAuthorName extends ListCardCommon {
    public HashMap<Integer, Integer> mAlphaIndexer;

    public ListCardHallOfFameAuthorName(b bVar, String str) {
        super(bVar, str);
    }

    public s createListItem() {
        return new y();
    }

    public int getCardItemLayoutId() {
        return R.layout.localstore_card_author_name;
    }

    @SuppressLint({"UseSparseArrays"})
    protected boolean parseData(JSONObject jSONObject) throws Exception {
        int i = 0;
        JSONArray optJSONArray = jSONObject.optJSONArray("names");
        if (optJSONArray == null) {
            return false;
        }
        int length = optJSONArray.length();
        while (i < length) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            String optString = optJSONObject.optString("name");
            String optString2 = optJSONObject.optString("id");
            s yVar = new y();
            yVar.a(optString, optString2);
            addItem(yVar);
            i++;
        }
        optJSONArray = jSONObject.optJSONArray("index");
        if (optJSONArray != null) {
            this.mAlphaIndexer = new HashMap();
            for (i = 97; i <= 122; i++) {
                this.mAlphaIndexer.put(Integer.valueOf(i), Integer.valueOf(optJSONArray.getInt(i - 97)));
            }
            this.mAlphaIndexer.put(Integer.valueOf(35), Integer.valueOf(optJSONArray.getInt(26)));
        }
        return true;
    }

    public void attachView(View view) {
        try {
            XListView xListView = (XListView) view;
            this.mAdapter = new g(ReaderApplication.getApplicationImp(), this, this.mIsFromCharis, this.mAlphaIndexer);
            ((c) this.mAdapter).a(getEvnetListener());
            xListView.setAdapter(this.mAdapter);
        } catch (Exception e) {
            f.d("listbook", "Exception " + e);
        }
    }
}
