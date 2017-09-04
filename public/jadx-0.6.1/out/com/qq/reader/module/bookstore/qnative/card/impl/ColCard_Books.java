package com.qq.reader.module.bookstore.qnative.card.impl;

import android.os.Bundle;
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

public class ColCard_Books extends a {
    public static final int COL_MAX_DISPLAYCOUNT = 3;
    protected static final String JSON_KEY_BOOKLIST = "bookList";
    protected static final String JSON_KEY_PROMOTION_NAME = "pushName";
    protected static final String JSON_KEY_TITLE = "title";
    private int mIntroType;
    private String mPromotionName;
    private int[] mRefreshIndex;

    public ColCard_Books(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.localstore_card_col_0;
    }

    public void build(JSONObject jSONObject) {
        super.build(jSONObject);
        this.mIntroType = jSONObject.optInt("introtype");
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

    public void refresh() {
        initRandomItem(true);
    }

    public void attachView() {
        if (getItemList().size() > 0) {
            final g gVar;
            CardTitle cardTitle = (CardTitle) ap.a(getRootView(), R.id.title_layout);
            if (this.mIntroType == 0) {
                cardTitle.setCardTitle(37, this.mShowTitle, this.mPromotionName, null);
            } else {
                cardTitle.setCardTitle(37, this.mShowTitle, null, this.mPromotionName);
            }
            CardMoreView cardMoreView = (CardMoreView) ap.a(getRootView(), R.id.localstore_moreaction);
            if (this.mMoreAction == null || getItemList().size() <= 3) {
                cardMoreView.setVisibility(8);
            } else {
                cardMoreView.setVisibility(0);
                cardMoreView.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ ColCard_Books a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        Bundle a = this.a.mMoreAction.a().a();
                        if (a != null) {
                            a.putString("LOCAL_STORE_IN_TITLE", this.a.mShowTitle);
                        }
                        this.a.mMoreAction.a(this.a.getEvnetListener());
                        if ("本周热门".equals(this.a.mShowTitle) || "热门推荐".equals(this.a.mShowTitle)) {
                            i.a("event_C93", null, ReaderApplication.getApplicationImp());
                        } else if ("新书速递".equals(this.a.mShowTitle) || "完本红文".equals(this.a.mShowTitle)) {
                            i.a("event_C94", null, ReaderApplication.getApplicationImp());
                        }
                    }
                });
                cardMoreView.setText(this.mMoreAction.e);
            }
            List arrayList = new ArrayList();
            int i;
            if (this.mRefreshIndex != null) {
                for (i = 0; i < this.mDispaly; i++) {
                    arrayList.add((g) getItemList().get(this.mRefreshIndex[i]));
                }
            } else {
                i = 0;
                while (i < this.mDispaly && i < getItemList().size()) {
                    arrayList.add((g) getItemList().get(i));
                    i++;
                }
            }
            final g gVar2 = (g) arrayList.get(0);
            SingleBookInfo singleBookInfo = (SingleBookInfo) ap.a(getRootView(), R.id.body_layout);
            singleBookInfo.setBookInfoCategoryByCategoryType(gVar2, getCategoryType());
            singleBookInfo.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ ColCard_Books b;

                public void onClick(View view) {
                    if (this.b.getEvnetListener() != null) {
                        gVar2.a(this.b.getEvnetListener());
                        this.b.clickStatics();
                    }
                }
            });
            SingleBookInfo singleBookInfo2 = (SingleBookInfo) ap.a(getRootView(), R.id.body_layout_1);
            singleBookInfo2.a(true);
            if (getItemList().size() > 1) {
                gVar = (g) arrayList.get(1);
                singleBookInfo2.setVisibility(0);
                singleBookInfo2.setBookInfoCategoryByCategoryType(gVar, getCategoryType());
                singleBookInfo2.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ ColCard_Books b;

                    public void onClick(View view) {
                        if (this.b.getEvnetListener() != null) {
                            gVar.a(this.b.getEvnetListener());
                            this.b.clickStatics();
                        }
                    }
                });
            } else {
                singleBookInfo2.setVisibility(8);
            }
            singleBookInfo2 = (SingleBookInfo) ap.a(getRootView(), R.id.body_layout_2);
            singleBookInfo2.a(true);
            if (getItemList().size() > 2) {
                gVar = (g) arrayList.get(2);
                singleBookInfo2.setVisibility(0);
                singleBookInfo2.setBookInfoCategoryByCategoryType(gVar, getCategoryType());
                singleBookInfo2.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ ColCard_Books b;

                    public void onClick(View view) {
                        if (this.b.getEvnetListener() != null) {
                            gVar.a(this.b.getEvnetListener());
                            this.b.clickStatics();
                        }
                    }
                });
            } else {
                singleBookInfo2.setVisibility(8);
            }
        }
        showStatics();
    }

    public boolean parseData(JSONObject jSONObject) throws Exception {
        c.a("ColCard", "parseData " + jSONObject.toString());
        this.mDispaly = 3;
        getItemList().clear();
        this.mServerTitle = jSONObject.optString("title");
        this.mPromotionName = jSONObject.optString(JSON_KEY_PROMOTION_NAME);
        JSONArray optJSONArray = jSONObject.optJSONArray(JSON_KEY_BOOKLIST);
        if (optJSONArray != null) {
            int length = optJSONArray.length();
            if (length > 0) {
                for (int i = 0; i < length; i++) {
                    JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                    s gVar = new g();
                    gVar.parseData(jSONObject2);
                    addItem(gVar);
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
