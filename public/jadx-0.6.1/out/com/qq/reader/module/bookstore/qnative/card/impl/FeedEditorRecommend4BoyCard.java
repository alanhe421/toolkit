package com.qq.reader.module.bookstore.qnative.card.impl;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.dynamicload.Lib.DLConstants;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.imageloader.a;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.readertask.protocol.H5GameChargeTask;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.item.g;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.feed.card.FeedMultiClickBaseCard;
import com.qq.reader.module.feed.card.view.CardMoreView;
import com.qq.reader.module.feed.data.impl.b;
import com.qq.reader.qurl.c;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.feedback.proguard.R;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class FeedEditorRecommend4BoyCard extends FeedMultiClickBaseCard implements b {
    protected static final String JSON_KEY_PROMOTION_NAME = "pushName";
    protected static final String JSON_KEY_TITLE = "title";
    private int[] areaResIds = new int[]{R.id.single_book_info, R.id.section_1, R.id.section_2, R.id.section_3, R.id.section_4, R.id.more_view};
    private int[] clickableAreaResIds = new int[]{R.id.single_book_info, R.id.section_1, R.id.section_2, R.id.section_3, R.id.section_4, R.id.concept_more_button};
    private int coverIndex1 = 0;
    private int coverIndex2 = 1;
    private int coverIndex3 = 2;
    private int coverIndex4 = 3;
    private int coverIndex5 = 4;
    private boolean isUsedAsFeedCard = false;
    private boolean mAttached = false;
    private ArrayList<g> mBookItems = new ArrayList();
    private int mCurPushNameIndex = 0;
    private ArrayList<String> mPushNames = new ArrayList();
    private String mQurl;
    private String mTitle;
    private int mTotalPushNameIndex;
    private int[] sectionResIds = new int[]{R.id.section_1, R.id.section_2, R.id.section_3, R.id.section_4};

    public FeedEditorRecommend4BoyCard(com.qq.reader.module.bookstore.qnative.page.b bVar, String str) {
        super(bVar, str);
        if (!this.isUsedAsFeedCard) {
            setShowDivider(true);
        }
    }

    public FeedEditorRecommend4BoyCard(com.qq.reader.module.bookstore.qnative.page.b bVar, String str, boolean z) {
        super(bVar, str);
        this.isUsedAsFeedCard = z;
        if (!z) {
            setShowDivider(true);
        }
    }

    public void doClick(View view) {
        int i = 5;
        switch (view.getId()) {
            case R.id.concept_more_button:
                if (!this.isUsedAsFeedCard) {
                    Bundle a = this.mMoreAction.a().a();
                    if (a != null) {
                        a.putString("LOCAL_STORE_IN_TITLE", this.mShowTitle);
                    }
                    this.mMoreAction.a(getEvnetListener());
                    return;
                } else if (!TextUtils.isEmpty(this.mQurl)) {
                    try {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(this.mQurl).append("&bids=").append(((g) this.mBookItems.get(this.coverIndex1)).m()).append(",").append(((g) this.mBookItems.get(this.coverIndex2)).m()).append(",").append(((g) this.mBookItems.get(this.coverIndex3)).m()).append(",").append(((g) this.mBookItems.get(this.coverIndex4)).m()).append(",").append(((g) this.mBookItems.get(this.coverIndex5)).m());
                        if (this.mBookItems.size() > 5) {
                            int i2 = 0;
                            while (i2 < this.mBookItems.size()) {
                                int i3;
                                if (i2 == this.coverIndex1 || i2 == this.coverIndex2 || i2 == this.coverIndex3 || i2 == this.coverIndex4) {
                                    i3 = i;
                                } else if (i2 == this.coverIndex5) {
                                    i3 = i;
                                } else if (i < this.mBookItems.size() - 1) {
                                    stringBuilder.append(",").append(((g) this.mBookItems.get(i2)).m());
                                    i3 = i + 1;
                                } else {
                                    stringBuilder.append(",").append(((g) this.mBookItems.get(i2)).m());
                                }
                                i2++;
                                i = i3;
                            }
                        }
                        c.a(getEvnetListener().getFromActivity(), stringBuilder.toString(), null);
                        i.a("event_F15", null, ReaderApplication.getApplicationImp().getApplicationContext());
                        return;
                    } catch (Exception e) {
                        return;
                    }
                } else {
                    return;
                }
            case R.id.section_1:
                jumpBookDetail(this.coverIndex2);
                if (this.isUsedAsFeedCard) {
                    i.a("event_F12", null, ReaderApplication.getApplicationImp().getApplicationContext());
                    return;
                }
                return;
            case R.id.section_2:
                jumpBookDetail(this.coverIndex3);
                if (this.isUsedAsFeedCard) {
                    i.a("event_F12", null, ReaderApplication.getApplicationImp().getApplicationContext());
                    return;
                }
                return;
            case R.id.section_3:
                jumpBookDetail(this.coverIndex4);
                if (this.isUsedAsFeedCard) {
                    i.a("event_F12", null, ReaderApplication.getApplicationImp().getApplicationContext());
                    return;
                }
                return;
            case R.id.section_4:
                jumpBookDetail(this.coverIndex5);
                if (this.isUsedAsFeedCard) {
                    i.a("event_F12", null, ReaderApplication.getApplicationImp().getApplicationContext());
                    return;
                }
                return;
            case R.id.single_book_info:
                jumpBookDetail(this.coverIndex1);
                if (this.isUsedAsFeedCard) {
                    i.a("event_F10", null, ReaderApplication.getApplicationImp().getApplicationContext());
                    return;
                }
                return;
            default:
                return;
        }
    }

    protected ArrayList<View> getViews() {
        ArrayList<View> arrayList = new ArrayList();
        for (int a : this.clickableAreaResIds) {
            arrayList.add(ap.a(getRootView(), a));
        }
        return arrayList;
    }

    public int getResLayoutId() {
        return R.layout.feed_editor_recommend_boy;
    }

    public void attachView() {
        this.mAttached = true;
        CardTitle cardTitle = (CardTitle) ap.a(getRootView(), R.id.card_title);
        if (!this.isUsedAsFeedCard) {
            cardTitle.setCardTitle(0, this.mShowTitle, this.mPromotionName, null);
        } else if (this.mPushNames.size() > 0) {
            cardTitle.setCardTitle(0, this.mTitle, (String) this.mPushNames.get(this.mCurPushNameIndex), null);
        } else {
            cardTitle.setCardTitle(0, this.mTitle, "", null);
        }
        initAllViews();
        fillSingleBookInfo();
        fillGridBookInfo();
        setMoreView();
        setOnClickListener();
        showStatics();
        if (this.isUsedAsFeedCard) {
            i.a("event_F7", null, ReaderApplication.getApplicationImp().getApplicationContext());
            statExposure();
        }
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        int i = 0;
        JSONArray optJSONArray;
        if (!this.isUsedAsFeedCard) {
            this.mServerTitle = jSONObject.optString("title");
            this.mPromotionName = jSONObject.optString(JSON_KEY_PROMOTION_NAME);
            List itemList = getItemList();
            if (itemList != null) {
                itemList.clear();
            } else {
                itemList = new ArrayList();
            }
            optJSONArray = jSONObject.optJSONArray("bookList");
            int i2 = 0;
            while (optJSONArray != null && i2 < optJSONArray.length()) {
                s gVar = new g();
                gVar.parseData(optJSONArray.optJSONObject(i2));
                addItem(gVar);
                i2++;
            }
            if (itemList == null || itemList.size() < this.mDispaly) {
                return false;
            }
            return true;
        } else if (jSONObject.optInt("style") != 4) {
            return false;
        } else {
            if (this.mBookItems.size() > 0) {
                this.mBookItems.clear();
            }
            if (this.mPushNames.size() > 0) {
                this.mPushNames.clear();
            }
            JSONObject optJSONObject = jSONObject.optJSONObject(MessageKey.MSG_CONTENT);
            if (optJSONObject != null) {
                this.mTitle = optJSONObject.optString("title");
                this.mQurl = optJSONObject.optString("qurl");
                optJSONArray = optJSONObject.optJSONArray("bookList");
                int i3 = 0;
                while (optJSONArray != null && i3 < optJSONArray.length()) {
                    g gVar2 = new g();
                    gVar2.parseData(optJSONArray.optJSONObject(i3));
                    this.mBookItems.add(gVar2);
                    i3++;
                }
                JSONArray optJSONArray2 = optJSONObject.optJSONArray(JSON_KEY_PROMOTION_NAME);
                if (optJSONArray2 == null || optJSONArray2.length() <= 0) {
                    this.mTotalPushNameIndex = 0;
                } else {
                    while (i < optJSONArray2.length()) {
                        this.mPushNames.add(optJSONArray2.getString(i));
                        i++;
                    }
                    this.mTotalPushNameIndex = optJSONArray2.length();
                }
            }
            return true;
        }
    }

    private void setMoreView() {
        CardMoreView cardMoreView = (CardMoreView) ap.a(getRootView(), R.id.more_view);
        if (this.isUsedAsFeedCard) {
            cardMoreView.setVisibility(0);
        } else if (this.mMoreAction == null || getItemList().size() <= this.mDispaly) {
            cardMoreView.setVisibility(8);
        } else {
            cardMoreView.setVisibility(0);
            cardMoreView.setText(this.mMoreAction.e);
        }
    }

    public void setDisplay(int i) {
        this.mDispaly = i;
    }

    private void initAllViews() {
        for (int a : this.areaResIds) {
            ap.a(getRootView(), a).setVisibility(4);
        }
    }

    private void fillSingleBookInfo() {
        SingleBookInfo singleBookInfo;
        if (!this.isUsedAsFeedCard) {
            List itemList = getItemList();
            if (itemList != null && itemList.size() > 0) {
                singleBookInfo = (SingleBookInfo) ap.a(getRootView(), R.id.single_book_info);
                singleBookInfo.setBookInfoCategoryByCategoryType((g) itemList.get(0), getCategoryType());
                singleBookInfo.setVisibility(0);
            }
        } else if (this.mBookItems != null && this.mBookItems.size() > 0) {
            singleBookInfo = (SingleBookInfo) ap.a(getRootView(), R.id.single_book_info);
            singleBookInfo.setBookInfoCategoryLv2((g) this.mBookItems.get(this.coverIndex1));
            singleBookInfo.setVisibility(0);
        }
    }

    private void fillGridBookInfo() {
        int i;
        if (!this.isUsedAsFeedCard) {
            List itemList = getItemList();
            i = 0;
            while (itemList != null && i < itemList.size() - 1 && i < this.sectionResIds.length) {
                fillSingleGridBookInfo(ap.a(getRootView(), this.sectionResIds[i]), (s) itemList.get(i + 1));
                i++;
            }
        } else if (this.mBookItems != null) {
            i = this.mBookItems.size();
            if (i > 1) {
                fillSingleGridBookInfo(ap.a(getRootView(), this.sectionResIds[0]), (s) this.mBookItems.get(this.coverIndex2));
            }
            if (i > 2) {
                fillSingleGridBookInfo(ap.a(getRootView(), this.sectionResIds[1]), (s) this.mBookItems.get(this.coverIndex3));
            }
            if (i > 3) {
                fillSingleGridBookInfo(ap.a(getRootView(), this.sectionResIds[2]), (s) this.mBookItems.get(this.coverIndex4));
            }
            if (i > 4) {
                fillSingleGridBookInfo(ap.a(getRootView(), this.sectionResIds[3]), (s) this.mBookItems.get(this.coverIndex5));
            }
        }
    }

    private void fillSingleGridBookInfo(View view, s sVar) {
        g gVar = (g) sVar;
        com.qq.reader.common.imageloader.c.a(getEvnetListener().getFromActivity()).a(ao.g(gVar.m()), (ImageView) ap.a(view, R.id.img_bookcover), a.a().j());
        ((TextView) ap.a(view, R.id.tv_title)).setText(gVar.n());
        ((TextView) ap.a(view, R.id.tv_desc)).setText(gVar.s());
        ((TextView) ap.a(view, R.id.tv_author)).setText(gVar.q());
        view.setVisibility(0);
    }

    private void jumpBookDetail(int i) {
        if (!this.isUsedAsFeedCard) {
            List itemList = getItemList();
            if (itemList != null && i < itemList.size()) {
                g gVar = (g) itemList.get(i);
                o.a(getEvnetListener().getFromActivity(), String.valueOf(gVar.m()), gVar.mStatParamString, null, null);
            }
        } else if (this.mBookItems != null && i < this.mBookItems.size()) {
            goToDetail((g) this.mBookItems.get(i));
        }
        clickStatics();
    }

    private void clearOnclickListener() {
        ArrayList views = getViews();
        int i = 0;
        while (views != null && i < views.size()) {
            try {
                ((View) views.get(i)).setOnClickListener(null);
            } catch (Exception e) {
                com.qq.reader.common.monitor.debug.c.e("Error", e.getMessage());
            }
            i++;
        }
    }

    private void setOnClickListener() {
        ArrayList views = getViews();
        int i = 0;
        while (views != null && i < views.size()) {
            try {
                ((View) views.get(i)).setOnClickListener(new com.qq.reader.module.bookstore.qnative.c.c(this) {
                    final /* synthetic */ FeedEditorRecommend4BoyCard a;

                    {
                        this.a = r1;
                    }

                    public void a(View view) {
                        this.a.doClick(view);
                    }
                });
            } catch (Exception e) {
                com.qq.reader.common.monitor.debug.c.e("Error", e.getMessage());
            }
            i++;
        }
    }

    public void change() {
        this.mIsNeedStatAlg = true;
        int size = this.mBookItems.size();
        if (size > 5) {
            this.coverIndex1 = this.coverIndex5 + 1;
            if (this.coverIndex1 == size) {
                this.coverIndex1 = 0;
            }
            this.coverIndex2 = this.coverIndex1 + 1;
            if (this.coverIndex2 == size) {
                this.coverIndex2 = 0;
            }
            this.coverIndex3 = this.coverIndex2 + 1;
            if (this.coverIndex3 == size) {
                this.coverIndex3 = 0;
            }
            this.coverIndex4 = this.coverIndex3 + 1;
            if (this.coverIndex4 == size) {
                this.coverIndex4 = 0;
            }
            this.coverIndex5 = this.coverIndex4 + 1;
            if (this.coverIndex5 == size) {
                this.coverIndex5 = 0;
            }
            if (this.mAttached) {
                fillSingleBookInfo();
                fillGridBookInfo();
                statExposure();
            }
        }
        if (this.mTotalPushNameIndex > 1) {
            this.mCurPushNameIndex++;
            if (this.mCurPushNameIndex == this.mTotalPushNameIndex) {
                this.mCurPushNameIndex = 0;
            }
        }
    }

    public boolean swipeEnable() {
        return false;
    }

    private void showStatics() {
        if (!this.isUsedAsFeedCard) {
            i.a("event_shown_" + getCardId(), null, ReaderApplication.getApplicationImp());
        }
    }

    private void clickStatics() {
        if (!this.isUsedAsFeedCard) {
            i.a("event_clicked_" + getCardId(), null, ReaderApplication.getApplicationImp());
        }
    }

    private void statExposure() {
        if (this.mIsNeedStatAlg) {
            CharSequence feedStatString = getFeedStatString();
            if (!TextUtils.isEmpty(feedStatString)) {
                Map hashMap = new HashMap();
                hashMap.put("event_feed_exposure", feedStatString);
                StatisticsManager.a().a("event_feed_exposure", hashMap);
            }
            this.mIsNeedStatAlg = false;
        }
    }

    private String getFeedStatString() {
        if (this.mBookItems == null || this.mBookItems.size() < 5) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            g gVar;
            if (i == 0) {
                gVar = (g) this.mBookItems.get(this.coverIndex1);
            } else if (i == 1) {
                gVar = (g) this.mBookItems.get(this.coverIndex2);
            } else if (i == 2) {
                gVar = (g) this.mBookItems.get(this.coverIndex3);
            } else if (i == 3) {
                gVar = (g) this.mBookItems.get(this.coverIndex4);
            } else {
                gVar = (g) this.mBookItems.get(this.coverIndex5);
            }
            if (gVar != null) {
                long m = gVar.m();
                String alg = gVar.getAlg();
                if (i == 0) {
                    stringBuilder.append(m).append(":").append(1).append(DLConstants.DEPENDENCY_PACKAGE_DIV).append(alg).append(DLConstants.DEPENDENCY_PACKAGE_DIV);
                } else {
                    stringBuilder.append(",").append(m).append(":").append(1).append(DLConstants.DEPENDENCY_PACKAGE_DIV).append(alg).append(DLConstants.DEPENDENCY_PACKAGE_DIV);
                }
            }
        }
        return stringBuilder.toString();
    }

    private void goToDetail(g gVar) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("uniteqqreader://nativepage/book/detail?").append("bid=").append(gVar.m()).append("&alg=").append(gVar.getAlg()).append(H5GameChargeTask.ITEMID).append(gVar.m());
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("ext_info_id", gVar.m());
            jSONObject.put("itemid", gVar.m());
            jSONObject.put(s.ALG, gVar.getAlg());
            stringBuilder.append("&statInfo=").append(URLEncoder.encode(jSONObject.toString(), "utf-8"));
            c.a(getEvnetListener().getFromActivity(), stringBuilder.toString(), null);
        } catch (Exception e) {
        }
    }
}
