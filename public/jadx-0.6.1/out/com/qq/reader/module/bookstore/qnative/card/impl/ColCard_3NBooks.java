package com.qq.reader.module.bookstore.qnative.card.impl;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.item.g;
import com.qq.reader.module.bookstore.qnative.item.p;
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

public class ColCard_3NBooks extends a {
    protected static final String JSON_KEY_BOOKLIST = "bookList";
    protected static final String JSON_KEY_PUSHNAME = "pushName";
    private final String JSON_KEY_TITLE = "title";
    private BookInfoType_3.a listener = new BookInfoType_3.a(this) {
        final /* synthetic */ ColCard_3NBooks a;

        {
            this.a = r1;
        }

        public void a() {
            this.a.clickStatics();
        }
    };
    private String mPushName;
    private int[] mRefreshIndex;
    private boolean mShowFreeInfo;

    public ColCard_3NBooks(b bVar, String str) {
        super(bVar, str);
    }

    private void showStatics() {
        if ("下周特价预告".equals(this.mShowTitle)) {
            i.a("event_shown_" + getCardId(), null, ReaderApplication.getApplicationImp());
            return;
        }
        Map hashMap = new HashMap();
        hashMap.put(s.ORIGIN, getCardId());
        i.a("event_B234", hashMap, ReaderApplication.getApplicationImp());
    }

    private void clickStatics() {
        if ("下周特价预告".equals(this.mShowTitle)) {
            i.a("event_clicked_" + getCardId(), null, ReaderApplication.getApplicationImp());
            return;
        }
        Map hashMap = new HashMap();
        hashMap.put(s.ORIGIN, getCardId());
        i.a("event_B235", hashMap, ReaderApplication.getApplicationImp());
    }

    public int getResLayoutId() {
        return R.layout.localstore_card_col3books;
    }

    public void attachView() {
        LinearLayout linearLayout = (LinearLayout) ap.a(getRootView(), R.id.localstore_colcard_3books_layout);
        if (getItemList().size() >= this.mDispaly) {
            linearLayout.setVisibility(0);
            CardTitle cardTitle = (CardTitle) ap.a(getRootView(), R.id.colcard_title_layout);
            cardTitle.setVisibility(0);
            cardTitle.setCardTitle(37, this.mShowTitle, this.mPushName, null);
            CardMoreView cardMoreView = (CardMoreView) ap.a(getRootView(), R.id.localstore_moreaction);
            if (this.mMoreAction == null || getItemList().size() <= this.mDispaly) {
                cardMoreView.setVisibility(8);
            } else {
                cardMoreView.setVisibility(0);
                cardMoreView.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ ColCard_3NBooks a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        if (this.a.mMoreAction != null) {
                            this.a.mMoreAction.a(this.a.getEvnetListener());
                        }
                    }
                });
                cardMoreView.setText(this.mMoreAction.e);
            }
            List arrayList = new ArrayList();
            int i;
            if (this.mRefreshIndex == null || this.mDispaly != this.mRefreshIndex.length) {
                Bundle j = getBindPage().j();
                List arrayList2 = new ArrayList(getItemList());
                if (j != null) {
                    Object string = j.getString("bids");
                    if (!TextUtils.isEmpty(string)) {
                        String[] split = string.split(",");
                        for (i = 0; i < split.length; i++) {
                            for (int i2 = 0; i2 < getItemList().size(); i2++) {
                                g gVar = (g) getItemList().get(i2);
                                if (split[i].equals(gVar.m() + "")) {
                                    arrayList2.remove(gVar);
                                    arrayList2.add(i, gVar);
                                    break;
                                }
                            }
                        }
                    }
                    j.putString("bids", "");
                }
                for (i = 0; i < this.mDispaly; i++) {
                    arrayList.add((g) arrayList2.get(i));
                }
            } else {
                for (i = 0; i < this.mDispaly; i++) {
                    arrayList.add((g) getItemList().get(this.mRefreshIndex[i]));
                }
            }
            this.mShowFreeInfo = hasFreeInfo(arrayList);
            BookInfoType_3 bookInfoType_3 = (BookInfoType_3) ap.a(getRootView(), R.id.colcard_body_layout_1);
            BookInfoType_3 bookInfoType_32 = (BookInfoType_3) ap.a(getRootView(), R.id.colcard_body_layout_2);
            if (arrayList.size() != 0) {
                bookInfoType_3.setVisibility(0);
                bookInfoType_32.setVisibility(8);
                if (arrayList.size() >= 3) {
                    bookInfoType_3.setBookInfo(arrayList.subList(0, 3), this.mShowFreeInfo);
                    bookInfoType_3.setReportListener(this.listener);
                    bookInfoType_3.setBookOnClickListener(arrayList.subList(0, 3), getEvnetListener());
                } else {
                    linearLayout.setVisibility(8);
                }
                if (this.mDispaly == 6 && arrayList.size() >= 6) {
                    bookInfoType_3.setVisibility(0);
                    bookInfoType_32.setVisibility(0);
                    bookInfoType_32.setReportListener(this.listener);
                    bookInfoType_32.setBookInfo(arrayList.subList(3, 6), this.mShowFreeInfo);
                    bookInfoType_32.setBookOnClickListener(arrayList.subList(3, 6), getEvnetListener());
                }
            }
        } else {
            linearLayout.setVisibility(8);
        }
        showStatics();
    }

    public boolean parseData(JSONObject jSONObject) throws Exception {
        int i = 0;
        this.mServerTitle = jSONObject.optString("title");
        this.mPushName = jSONObject.optString(JSON_KEY_PUSHNAME);
        JSONArray optJSONArray = jSONObject.optJSONArray(JSON_KEY_BOOKLIST);
        if (optJSONArray != null) {
            int length = optJSONArray.length();
            if (length > 0 && length >= this.mDispaly) {
                if (getItemList() != null) {
                    getItemList().clear();
                }
                while (i < length) {
                    JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                    s pVar = new p();
                    pVar.b(this.mFromBid);
                    pVar.parseData(jSONObject2);
                    addItem(pVar);
                    i++;
                }
                if (!TextUtils.isEmpty(getBindPage().j().getString("bids"))) {
                    return true;
                }
                initRandomItem(true);
                return true;
            }
        }
        return false;
    }

    public void refresh() {
        initRandomItem(false);
    }

    private boolean hasFreeInfo(List<g> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (!((p) list.get(i)).c()) {
                return false;
            }
        }
        return true;
    }

    private void initRandomItem(boolean z) {
        int size = getItemList().size();
        if (size != 0) {
            int i = this.mDispaly;
            boolean z2 = z && isExpired();
            this.mRefreshIndex = getRandomListIndex(i, size, z2);
        }
    }
}
