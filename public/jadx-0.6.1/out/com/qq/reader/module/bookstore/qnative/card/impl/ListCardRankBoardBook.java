package com.qq.reader.module.bookstore.qnative.card.impl;

import android.view.View;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.f;
import com.qq.reader.module.bookstore.qnative.a.e;
import com.qq.reader.module.bookstore.qnative.item.aa;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.bookstore.qnative.page.d;
import com.qq.reader.widget.PinnedHeaderListView;
import com.tencent.feedback.proguard.R;
import java.util.LinkedHashMap;
import org.json.JSONArray;
import org.json.JSONObject;

public class ListCardRankBoardBook extends ListCardCommon {
    private boolean istenYearsRankBoard = false;
    public LinkedHashMap<Integer, Integer> mAlphaIndexer = new LinkedHashMap();
    private d mRankInfoItem;
    private int mYear;

    public ListCardRankBoardBook(b bVar, String str) {
        super(bVar, str);
    }

    public void setYears(int i) {
        this.mYear = i;
    }

    public aa createListItem() {
        return new aa(this.istenYearsRankBoard);
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        int i = 0;
        JSONArray optJSONArray = jSONObject.optJSONArray("data");
        if (optJSONArray == null) {
            return false;
        }
        int length = optJSONArray.length();
        if (length > 0) {
            this.mAlphaIndexer.put(Integer.valueOf(this.mYear), Integer.valueOf(getItemList().size()));
            while (i < length) {
                JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                s createListItem = createListItem();
                if (i == 0) {
                    jSONObject2.put("year", this.mYear);
                }
                jSONObject2.put("index", i + 1);
                createListItem.parseData(jSONObject2);
                createListItem.setPageInfo(this.mRankInfoItem);
                addItem(createListItem);
                i++;
            }
        }
        return true;
    }

    public void attachView(View view) {
        try {
            PinnedHeaderListView pinnedHeaderListView = (PinnedHeaderListView) view;
            this.mAdapter = new e(ReaderApplication.getApplicationImp(), this, this.mIsFromCharis, this.mAlphaIndexer);
            ((e) this.mAdapter).a(getEvnetListener());
            pinnedHeaderListView.setAdapter(this.mAdapter);
            pinnedHeaderListView.setOnScrollListener((e) this.mAdapter);
        } catch (Exception e) {
            f.d("listbook", "Exception " + e);
        }
    }

    public int getCardItemLayoutId() {
        return R.layout.plan2_rankboard_item;
    }

    public void setIsTenYearsRankBoard(boolean z) {
        this.istenYearsRankBoard = z;
    }

    public void setActionId(d dVar) {
        this.mRankInfoItem = dVar;
    }
}
