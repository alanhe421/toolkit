package com.qq.reader.module.bookstore.qnative.card.impl;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.monitor.j;
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

public class RecCard_3NBooks extends a {
    protected static final String JSON_KEY_CID = "cid";
    protected static final String JSON_KEY_PUSHNAME = "pushName";
    protected static final String JSON_KEY_TITLE = "title";
    private boolean dataFromCache = false;
    private BookInfoType_3.a listener = new BookInfoType_3.a(this) {
        final /* synthetic */ RecCard_3NBooks a;

        {
            this.a = r1;
        }

        public void a() {
            this.a.clickStatics();
        }
    };
    private int mColumeId = 0;
    private String mPushName;
    private int[] mRefreshIndex;
    private boolean needRefresh = false;

    public RecCard_3NBooks(b bVar, String str) {
        super(bVar, str);
    }

    private void showStatics() {
        Map hashMap = new HashMap();
        hashMap.put(s.ORIGIN, getCardId());
        i.a("event_B234", hashMap, ReaderApplication.getApplicationImp());
        i.a("event_shown_" + getCardId(), null, ReaderApplication.getApplicationImp());
    }

    private void clickStatics() {
        Map hashMap = new HashMap();
        hashMap.put(s.ORIGIN, getCardId());
        i.a("event_B235", hashMap, ReaderApplication.getApplicationImp());
        i.a("event_clicked_" + getCardId(), null, ReaderApplication.getApplicationImp());
    }

    public void attachView() {
        LinearLayout linearLayout = (LinearLayout) ap.a(getRootView(), R.id.localstore_reccard_3books_layout);
        if (getItemList().size() >= this.mDispaly) {
            linearLayout.setVisibility(0);
            CardTitle cardTitle = (CardTitle) ap.a(getRootView(), R.id.reccard_title_layout);
            cardTitle.setVisibility(0);
            cardTitle.setCardTitle(0, this.mShowTitle, this.mPushName, null);
            CardMoreView cardMoreView = (CardMoreView) ap.a(getRootView(), R.id.localstore_moreaction);
            if (this.mMoreAction == null || getItemList().size() <= 3) {
                cardMoreView.setVisibility(8);
            } else {
                cardMoreView.setVisibility(0);
                cardMoreView.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ RecCard_3NBooks a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        this.a.needRefresh = true;
                        if (this.a.mMoreAction != null) {
                            Bundle a = this.a.mMoreAction.a().a();
                            if (a != null) {
                                a.putString("LOCAL_STORE_IN_TITLE", this.a.mShowTitle);
                            }
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
                }
                for (i = 0; i < this.mDispaly; i++) {
                    arrayList.add((g) arrayList2.get(i));
                }
            } else {
                for (i = 0; i < this.mDispaly; i++) {
                    arrayList.add((g) getItemList().get(this.mRefreshIndex[i]));
                }
            }
            if (arrayList.size() != 0) {
                BookInfoType_3 bookInfoType_3 = (BookInfoType_3) ap.a(getRootView(), R.id.reccard_body_layout);
                bookInfoType_3.setVisibility(0);
                if (arrayList.size() >= 3) {
                    if (((g) arrayList.get(0)).d() > 0) {
                        bookInfoType_3.setListenBook(true);
                    }
                    bookInfoType_3.setBookInfo(arrayList, false);
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

    public int getResLayoutId() {
        return R.layout.localstore_card_rec3books;
    }

    public boolean parseData(JSONObject jSONObject) throws Exception {
        this.mServerTitle = jSONObject.optString("title");
        this.mColumeId = jSONObject.optInt(JSON_KEY_CID);
        JSONArray optJSONArray = jSONObject.optJSONArray("bookList");
        this.mPushName = jSONObject.optString(JSON_KEY_PUSHNAME);
        if (optJSONArray != null) {
            int length = optJSONArray.length();
            if (length > 0) {
                if (getItemList() != null) {
                    getItemList().clear();
                }
                for (int i = 0; i < length; i++) {
                    JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                    s pVar = new p();
                    pVar.parseData(jSONObject2);
                    addItem(pVar);
                }
                this.dataFromCache = jSONObject.optBoolean("data_from_cache", false);
                Bundle j = getBindPage().j();
                if (j != null) {
                    j.getBoolean("SECONDARY_PAGE_EXTRA_KEY_HAS_FIX_BIDS", false);
                    if (this.dataFromCache) {
                        return true;
                    }
                    initRandomItem(true);
                    return true;
                }
                initRandomItem(true);
                return true;
            }
        }
        return false;
    }

    public void refresh() {
        if ("新书速递".equals(this.mShowTitle) || "完本红文".equals(this.mShowTitle)) {
            i.a("event_C94", null, ReaderApplication.getApplicationImp());
            j.a(93, 2);
        } else if (this.mColumeId == 2331) {
            Map hashMap = new HashMap();
            hashMap.put(s.ORIGIN, String.valueOf(this.mColumeId));
            i.a("event_A100", hashMap, ReaderApplication.getApplicationImp());
        }
        if (this.needRefresh) {
            initRandomItem(false);
        } else {
            this.needRefresh = true;
        }
    }

    private void initRandomItem(boolean z) {
        boolean z2 = true;
        int i = 0;
        int size = getItemList().size();
        if (size != 0) {
            int i2;
            if (this.mRefreshIndex != null) {
                List arrayList = new ArrayList();
                for (i2 = 0; i2 < size; i2++) {
                    if (!contains(this.mRefreshIndex, i2)) {
                        arrayList.add(Integer.valueOf(i2));
                    }
                }
                i2 = this.mDispaly;
                size = arrayList.size();
                if (!(z && isExpired())) {
                    z2 = false;
                }
                int[] randomListIndex = getRandomListIndex(i2, size, z2);
                while (i < randomListIndex.length) {
                    this.mRefreshIndex[i] = ((Integer) arrayList.get(randomListIndex[i])).intValue();
                    i++;
                }
                return;
            }
            i2 = this.mDispaly;
            if (!(z && isExpired())) {
                z2 = false;
            }
            this.mRefreshIndex = getRandomListIndex(i2, size, z2);
        }
    }

    private boolean contains(int[] iArr, int i) {
        for (int i2 : iArr) {
            if (i == i2) {
                return true;
            }
        }
        return false;
    }
}
