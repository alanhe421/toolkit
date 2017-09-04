package com.qq.reader.module.bookstore.qnative.card.impl;

import android.os.Bundle;
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
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class FreeCard_3NBooks extends a {
    protected static final String JSON_KEY_BOOKLIST = "bookList";
    protected static final String JSON_KEY_PUSHNAME = "pushName";
    protected static final String JSON_KEY_TITLE = "title";
    private BookInfoType_3.a listener = new BookInfoType_3.a(this) {
        final /* synthetic */ FreeCard_3NBooks a;

        {
            this.a = r1;
        }

        public void a() {
            this.a.clickStatics();
        }
    };
    private String mPushName;
    private int[] mRefreshIndex;

    public FreeCard_3NBooks(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.localstore_card_free3books;
    }

    public void attachView() {
        LinearLayout linearLayout = (LinearLayout) ap.a(getRootView(), R.id.localstore_freecard_3books_layout);
        if (getItemList().size() >= this.mDispaly) {
            linearLayout.setVisibility(0);
            CardTitle cardTitle = (CardTitle) ap.a(getRootView(), R.id.freecard_title_layout);
            cardTitle.setVisibility(0);
            cardTitle.setCardTitleForFreeCard(37, this.mShowTitle, this.mPushName, null);
            CardMoreView cardMoreView = (CardMoreView) ap.a(getRootView(), R.id.localstore_moreaction);
            if (this.mMoreAction == null || getItemList().size() <= this.mDispaly) {
                cardMoreView.setVisibility(8);
            } else {
                cardMoreView.setVisibility(0);
                cardMoreView.setText(this.mMoreAction.e);
                cardMoreView.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ FreeCard_3NBooks a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        if (this.a.mMoreAction != null) {
                            this.a.mMoreAction.a(this.a.getEvnetListener());
                        }
                    }
                });
            }
            List arrayList = new ArrayList();
            int i;
            if (this.mRefreshIndex == null || this.mDispaly != this.mRefreshIndex.length) {
                for (i = 0; i < this.mDispaly; i++) {
                    arrayList.add((g) getItemList().get(i));
                }
            } else {
                for (i = 0; i < this.mDispaly; i++) {
                    arrayList.add((g) getItemList().get(this.mRefreshIndex[i]));
                }
            }
            if (arrayList.size() != 0) {
                BookInfoType_3 bookInfoType_3 = (BookInfoType_3) ap.a(getRootView(), R.id.freecard_body_layout);
                bookInfoType_3.setVisibility(0);
                if (arrayList.size() >= 3) {
                    if (((g) arrayList.get(0)).d() > 0) {
                        bookInfoType_3.setListenBook(true);
                    }
                    bookInfoType_3.setBookInfo(arrayList, true);
                    bookInfoType_3.setReportListener(this.listener);
                    bookInfoType_3.setBookOnClickListener(arrayList, getEvnetListener());
                } else {
                    linearLayout.setVisibility(8);
                }
            }
        } else if (linearLayout != null) {
            linearLayout.setVisibility(8);
        }
        showStatics();
    }

    public boolean parseData(JSONObject jSONObject) throws Exception {
        this.mServerTitle = jSONObject.optString("title");
        JSONArray optJSONArray = jSONObject.optJSONArray(JSON_KEY_BOOKLIST);
        this.mPushName = jSONObject.optString(JSON_KEY_PUSHNAME);
        if (optJSONArray != null) {
            int length = optJSONArray.length();
            if (length > 0 && length >= this.mDispaly) {
                if (getItemList() != null) {
                    getItemList().clear();
                }
                for (int i = 0; i < length; i++) {
                    JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                    s pVar = new p();
                    pVar.parseData(jSONObject2);
                    addItem(pVar);
                }
                boolean optBoolean = jSONObject.optBoolean("data_from_cache", false);
                Bundle j = getBindPage().j();
                if (j == null) {
                    initRandomItem(true);
                    return true;
                } else if (j.getBoolean("SECONDARY_PAGE_EXTRA_KEY_HAS_FIX_BIDS", false) || optBoolean) {
                    return true;
                } else {
                    initRandomItem(true);
                    return true;
                }
            }
        }
        return false;
    }

    public void refresh() {
    }

    public void initRandomItem(boolean z) {
        int size = getItemList().size();
        if (size != 0) {
            int i = this.mDispaly;
            boolean z2 = z && isExpired();
            this.mRefreshIndex = getRandomListIndex(i, size, z2);
        }
    }

    protected void showStatics() {
        i.a("event_shown_" + getCardId(), null, ReaderApplication.getApplicationImp());
    }

    protected void clickStatics() {
        i.a("event_clicked_" + getCardId(), null, ReaderApplication.getApplicationImp());
    }
}
