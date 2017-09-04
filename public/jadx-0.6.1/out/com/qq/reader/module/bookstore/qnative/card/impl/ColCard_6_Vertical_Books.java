package com.qq.reader.module.bookstore.qnative.card.impl;

import android.view.View;
import android.view.View.OnClickListener;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.item.g;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.feed.card.view.CardMoreView;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class ColCard_6_Vertical_Books extends a {
    public static final int COL_MAX_DISPLAYCOUNT = 6;
    protected static final String JSON_KEY_BOOKLIST = "bookList";
    protected static final String JSON_KEY_PROMOTION_NAME = "pushName";
    protected static final String JSON_KEY_TITLE = "title";
    private int[] layoutIds = new int[]{R.id.body_layout, R.id.body_layout_1, R.id.body_layout_2, R.id.body_layout_3, R.id.body_layout_4, R.id.body_layout_5};
    private int mIntroType;
    private String mPromotionName;
    private int[] mRefreshIndex;

    public ColCard_6_Vertical_Books(b bVar, String str) {
        super(bVar, str);
    }

    private void showStatics() {
        Map hashMap = new HashMap();
        hashMap.put(s.ORIGIN, getCardId());
        i.a("event_B234", hashMap, ReaderApplication.getApplicationImp());
    }

    private void clickStatics() {
        Map hashMap = new HashMap();
        hashMap.put(s.ORIGIN, getCardId());
        i.a("event_B235", hashMap, ReaderApplication.getApplicationImp());
    }

    public int getResLayoutId() {
        return R.layout.localstore_card_col_6_vertical_books;
    }

    public void build(JSONObject jSONObject) {
        super.build(jSONObject);
        this.mIntroType = jSONObject.optInt("introtype");
    }

    private void fillSingleItem(List<g> list, int i) {
        if (i < this.layoutIds.length) {
            SingleBookInfo singleBookInfo = (SingleBookInfo) ap.a(getRootView(), this.layoutIds[i]);
            if (i == 0) {
                singleBookInfo.a(false);
            } else {
                singleBookInfo.a(true);
            }
            if (list == null || i >= list.size()) {
                singleBookInfo.setVisibility(8);
                return;
            }
            final g gVar = (g) list.get(i);
            singleBookInfo.setVisibility(0);
            singleBookInfo.setBookInfo(gVar);
            singleBookInfo.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ ColCard_6_Vertical_Books b;

                public void onClick(View view) {
                    if (this.b.getEvnetListener() != null) {
                        this.b.clickStatics();
                        gVar.a(this.b.getEvnetListener());
                    }
                }
            });
        }
    }

    public void attachView() {
        int i = 0;
        if (getItemList().size() > 0) {
            CardTitle cardTitle = (CardTitle) ap.a(getRootView(), R.id.title_layout);
            if (this.mIntroType == 0) {
                cardTitle.setCardTitle(37, this.mShowTitle, this.mPromotionName, null);
            } else {
                cardTitle.setCardTitle(37, this.mShowTitle, null, this.mPromotionName);
            }
            CardMoreView cardMoreView = (CardMoreView) ap.a(getRootView(), R.id.localstore_moreaction);
            if (this.mMoreAction == null || getItemList().size() <= 6) {
                cardMoreView.setVisibility(8);
            } else {
                cardMoreView.setVisibility(0);
                cardMoreView.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ ColCard_6_Vertical_Books a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        this.a.mMoreAction.a(this.a.getEvnetListener());
                    }
                });
                cardMoreView.setText(this.mMoreAction.e);
            }
            List arrayList = new ArrayList();
            int i2;
            if (this.mRefreshIndex != null) {
                for (i2 = 0; i2 < this.mDispaly; i2++) {
                    arrayList.add((g) getItemList().get(this.mRefreshIndex[i2]));
                }
            } else {
                for (i2 = 0; i2 < this.mDispaly; i2++) {
                    arrayList.add((g) getItemList().get(i2));
                }
            }
            while (i < 6) {
                fillSingleItem(arrayList, i);
                i++;
            }
        }
        showStatics();
    }

    public boolean parseData(JSONObject jSONObject) throws Exception {
        c.a("ColCard", "parseData " + jSONObject.toString());
        this.mDispaly = 6;
        getItemList().clear();
        this.mServerTitle = jSONObject.optString("title");
        this.mPromotionName = jSONObject.optString(JSON_KEY_PROMOTION_NAME);
        JSONArray optJSONArray = jSONObject.optJSONArray(JSON_KEY_BOOKLIST);
        if (optJSONArray == null) {
            return false;
        }
        int length = optJSONArray.length();
        if (length <= 0 || length < 6) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
            s gVar = new g();
            gVar.parseData(jSONObject2);
            addItem(gVar);
        }
        initRandomItem(false);
        return true;
    }

    private void initRandomItem(boolean z) {
        int size = getItemList().size();
        if (size != 0) {
            if (size < this.mDispaly) {
                this.mDispaly = size;
            }
            int i = this.mDispaly;
            boolean z2 = z && isExpired();
            this.mRefreshIndex = getRandomListIndex(i, size, z2);
        }
    }
}
