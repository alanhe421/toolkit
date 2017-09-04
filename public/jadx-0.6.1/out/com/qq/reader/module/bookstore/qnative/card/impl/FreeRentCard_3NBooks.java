package com.qq.reader.module.bookstore.qnative.card.impl;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ap;
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

public class FreeRentCard_3NBooks extends com.qq.reader.module.bookstore.qnative.card.a {
    protected static final String JSON_KEY_BOOKLIST = "bookList";
    protected static final String JSON_KEY_PUSHNAME = "pushName";
    protected static final String JSON_KEY_TITLE = "title";
    private com.qq.reader.module.bookstore.qnative.card.impl.BookInfoType_3.a listener = new com.qq.reader.module.bookstore.qnative.card.impl.BookInfoType_3.a(this) {
        final /* synthetic */ FreeRentCard_3NBooks a;

        {
            this.a = r1;
        }

        public void a() {
            this.a.clickStatics();
        }
    };
    private String mPushName;
    private int[] mRefreshIndex;

    private class a extends p {
        final /* synthetic */ FreeRentCard_3NBooks a;
        private String l;

        private a(FreeRentCard_3NBooks freeRentCard_3NBooks) {
            this.a = freeRentCard_3NBooks;
        }

        public void parseData(JSONObject jSONObject) {
            super.parseData(jSONObject);
            JSONObject optJSONObject = jSONObject.optJSONObject("ext");
            if (optJSONObject != null) {
                this.l = optJSONObject.optString("rentPrice");
            }
        }

        public String a() {
            return this.l;
        }
    }

    public FreeRentCard_3NBooks(b bVar, String str) {
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
                    final /* synthetic */ FreeRentCard_3NBooks a;

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
        } else {
            linearLayout.setVisibility(8);
        }
        showStatics();
    }

    public boolean parseData(JSONObject jSONObject) throws Exception {
        int i = 0;
        this.mServerTitle = jSONObject.optString("title");
        JSONArray optJSONArray = jSONObject.optJSONArray(JSON_KEY_BOOKLIST);
        this.mPushName = jSONObject.optString(JSON_KEY_PUSHNAME);
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
            s aVar = new a();
            aVar.parseData(jSONObject2);
            addItem(aVar);
            i++;
        }
        return true;
    }

    public void refresh() {
        initRandomItem(false);
    }

    private void initRandomItem(boolean z) {
        int size = getItemList().size();
        if (size != 0) {
            int i = this.mDispaly;
            boolean z2 = z && isExpired();
            this.mRefreshIndex = getRandomListIndex(i, size, z2);
        }
    }

    private void showStatics() {
        i.a("event_shown_" + getCardId(), null, ReaderApplication.getApplicationImp());
    }

    private void clickStatics() {
        i.a("event_clicked_" + getCardId(), null, ReaderApplication.getApplicationImp());
    }
}
