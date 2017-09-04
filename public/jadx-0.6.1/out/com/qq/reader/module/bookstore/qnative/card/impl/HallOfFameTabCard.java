package com.qq.reader.module.bookstore.qnative.card.impl;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.item.r;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import com.qq.reader.module.feed.card.view.HallOfFameTabItemView;
import com.tencent.feedback.proguard.R;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class HallOfFameTabCard extends a {
    private LinearLayout mContainerView;
    private int mCurShowIndex = 0;
    private List<s> mLastItemList;
    private int mLastShowIndex = 0;

    public HallOfFameTabCard(b bVar, String str) {
        super(bVar, str);
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        int i = 0;
        JSONArray optJSONArray = jSONObject.optJSONArray(ComicStoreExclusiveItemCard.NET_AD_ATTR_CATE);
        if (optJSONArray == null) {
            return false;
        }
        int length = optJSONArray.length();
        getItemList().clear();
        if (length <= 0) {
            return false;
        }
        while (i < length) {
            if (optJSONArray.get(i) != null) {
                JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                s rVar = new r();
                rVar.parseData(jSONObject2);
                addItem(rVar);
            }
            i++;
        }
        return true;
    }

    public int getResLayoutId() {
        return R.layout.nativestore_halloffame_card;
    }

    public void attachView() {
        List itemList = getItemList();
        if (this.mLastItemList == null || this.mLastItemList.hashCode() != itemList.hashCode()) {
            this.mLastItemList = getItemList();
            this.mContainerView = (LinearLayout) ap.a(getRootView(), R.id.haffoffame_tab_list_container);
            if (this.mContainerView.getChildCount() >= 0) {
                this.mContainerView.removeAllViews();
            }
            if (this.mContainerView.getChildCount() <= 0) {
                int size = getItemList().size();
                for (int i = 0; i < size; i++) {
                    final r rVar = (r) getItemList().get(i);
                    View hallOfFameTabItemView = new HallOfFameTabItemView(ReaderApplication.getApplicationImp().getApplicationContext(), null, null);
                    hallOfFameTabItemView.setIndex(i);
                    hallOfFameTabItemView.setTabItemData(rVar);
                    hallOfFameTabItemView.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ HallOfFameTabCard c;

                        public void onClick(View view) {
                            this.c.mCurShowIndex = ((HallOfFameTabItemView) view).getIndex();
                            Bundle bundle = new Bundle();
                            bundle.putString("KEY_ACTIONID", rVar.a());
                            bundle.putInt(ComicStoreExclusiveItemCard.NET_AD_ATTR_POSITION, i);
                            this.c.getEvnetListener().doFunction(bundle);
                            ((HallOfFameTabItemView) this.c.mContainerView.getChildAt(this.c.mLastShowIndex)).b();
                            this.c.mLastShowIndex = this.c.mCurShowIndex;
                            ((HallOfFameTabItemView) this.c.mContainerView.getChildAt(this.c.mCurShowIndex)).a();
                        }
                    });
                    this.mContainerView.addView(hallOfFameTabItemView);
                    ((HallOfFameTabItemView) this.mContainerView.getChildAt(0)).a();
                }
            }
        }
    }
}
