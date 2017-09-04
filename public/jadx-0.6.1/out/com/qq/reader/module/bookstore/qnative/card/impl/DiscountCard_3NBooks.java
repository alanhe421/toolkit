package com.qq.reader.module.bookstore.qnative.card.impl;

import android.os.Bundle;
import android.text.TextUtils;
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

public class DiscountCard_3NBooks extends com.qq.reader.module.bookstore.qnative.card.a {
    protected static final String JSON_KEY_BOOKLIST = "bookList";
    protected static final String JSON_KEY_PUSHNAME = "pushName";
    protected static final String JSON_KEY_TITLE = "title";
    private String mPushName;
    private int[] mRefreshIndex;

    public class a extends p {
        final /* synthetic */ DiscountCard_3NBooks a;

        public a(DiscountCard_3NBooks discountCard_3NBooks) {
            this.a = discountCard_3NBooks;
        }

        public void parseData(JSONObject jSONObject) {
            super.parseData(jSONObject);
            this.k = jSONObject.optString("discount");
        }
    }

    public DiscountCard_3NBooks(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.localstore_card_discount3books;
    }

    public void attachView() {
        LinearLayout linearLayout = (LinearLayout) ap.a(getRootView(), R.id.localstore_freecard_3books_layout);
        if (getItemList().size() >= this.mDispaly) {
            linearLayout.setVisibility(0);
            CardTitle cardTitle = (CardTitle) ap.a(getRootView(), R.id.freecard_title_layout);
            cardTitle.setVisibility(0);
            cardTitle.setCardTitleForFreeCard(37, this.mShowTitle, this.mPushName, null);
            CardMoreView cardMoreView = (CardMoreView) ap.a(getRootView(), R.id.localstore_moreaction);
            if (this.mMoreAction == null || getItemList().size() <= 3) {
                cardMoreView.setVisibility(8);
            } else {
                cardMoreView.setVisibility(0);
                cardMoreView.setText(this.mMoreAction.e);
                cardMoreView.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ DiscountCard_3NBooks a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        if (this.a.mMoreAction != null) {
                            Bundle a = this.a.mMoreAction.a().a();
                            if (a != null) {
                                a.putString("KEY_JUMP_PAGENAME", "PAGE_NAME_DISCOUNT_BOOK_LIST");
                            }
                            this.a.mMoreAction.a(this.a.getEvnetListener());
                        }
                    }
                });
            }
            List arrayList = new ArrayList();
            int i = 0;
            while (getItemList() != null && i < getItemList().size()) {
                arrayList.add((g) getItemList().get(i));
                i++;
            }
            if (arrayList != null && arrayList.size() > 0) {
                BookInfoType_3_For_Discount bookInfoType_3_For_Discount = (BookInfoType_3_For_Discount) ap.a(getRootView(), R.id.freecard_body_layout);
                bookInfoType_3_For_Discount.setVisibility(0);
                if (arrayList.size() > 0) {
                    if (((g) arrayList.get(0)).d() > 0) {
                        bookInfoType_3_For_Discount.setListenBook(true);
                    }
                    bookInfoType_3_For_Discount.setBookInfoFlexible(arrayList);
                    bookInfoType_3_For_Discount.setBookOnClickListener(arrayList, getEvnetListener());
                    bookInfoType_3_For_Discount.setReportListener(new com.qq.reader.module.bookstore.qnative.card.impl.BookInfoType_3_For_Discount.a(this) {
                        final /* synthetic */ DiscountCard_3NBooks a;

                        {
                            this.a = r1;
                        }

                        public void a() {
                            this.a.clickStatics();
                        }
                    });
                } else {
                    linearLayout.setVisibility(8);
                }
            }
        } else {
            linearLayout.setVisibility(8);
        }
        showStatics();
    }

    private void clickStatics() {
        CharSequence cardId = getCardId();
        if (!TextUtils.isEmpty(cardId)) {
            if ("10001".equals(cardId)) {
                i.a("event_B188", null, ReaderApplication.getApplicationImp());
            } else if ("10002".equals(cardId)) {
                i.a("event_B190", null, ReaderApplication.getApplicationImp());
            }
        }
    }

    private void showStatics() {
        CharSequence cardId = getCardId();
        if (!TextUtils.isEmpty(cardId)) {
            if ("10001".equals(cardId)) {
                i.a("event_B187", null, ReaderApplication.getApplicationImp());
            } else if ("10002".equals(cardId)) {
                i.a("event_B189", null, ReaderApplication.getApplicationImp());
            }
        }
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
        if (length <= 0) {
            return false;
        }
        if (getItemList() != null) {
            getItemList().clear();
        }
        while (i < length) {
            JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
            s aVar = new a(this);
            aVar.parseData(jSONObject2);
            addItem(aVar);
            i++;
        }
        return true;
    }

    public void refresh() {
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
