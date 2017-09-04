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

public class ColCard_Listen_Books extends a {
    public static final int COL_MAX_DISPLAYCOUNT = 3;
    protected static final String JSON_KEY_BOOKLIST = "bookList";
    protected static final String JSON_KEY_CID = "cid";
    protected static final String JSON_KEY_PROMOTION_NAME = "pushName";
    protected static final String JSON_KEY_TITLE = "title";
    private int mColumeId = 0;
    private int mIntroType;
    private String mPromotionName;
    private int[] mRefreshIndex;

    public ColCard_Listen_Books(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.localstore_card_col_listen_0;
    }

    public void build(JSONObject jSONObject) {
        super.build(jSONObject);
        this.mIntroType = jSONObject.optInt("introtype");
    }

    public void attachView() {
        if (getItemList().size() > 0) {
            final g gVar;
            CardTitle cardTitle = (CardTitle) ap.a(getRootView(), R.id.title_layout);
            if (this.mIntroType == 0) {
                cardTitle.setCardTitle(this.mIconIndex, this.mShowTitle, this.mPromotionName, null);
            } else {
                cardTitle.setCardTitle(this.mIconIndex, this.mShowTitle, null, this.mPromotionName);
            }
            CardMoreView cardMoreView = (CardMoreView) ap.a(getRootView(), R.id.localstore_moreaction);
            if (this.mMoreAction != null) {
                cardMoreView.setVisibility(0);
                cardMoreView.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ ColCard_Listen_Books a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        this.a.mMoreAction.a().a().putString("LOCAL_STORE_IN_TITLE", this.a.mShowTitle);
                        this.a.mMoreAction.a(this.a.getEvnetListener());
                        Map hashMap = new HashMap();
                        hashMap.put(s.ORIGIN, String.valueOf(this.a.mColumeId));
                        i.a("event_A100", hashMap, ReaderApplication.getApplicationImp());
                    }
                });
                cardMoreView.setText(this.mMoreAction.e);
            } else {
                cardMoreView.setVisibility(8);
            }
            List arrayList = new ArrayList();
            int i;
            if (this.mRefreshIndex != null) {
                for (i = 0; i < this.mDispaly; i++) {
                    arrayList.add((g) getItemList().get(this.mRefreshIndex[i]));
                }
            } else {
                for (i = 0; i < this.mDispaly; i++) {
                    arrayList.add((g) getItemList().get(i));
                }
            }
            final g gVar2 = (g) arrayList.get(0);
            SingleListenBookInfo singleListenBookInfo = (SingleListenBookInfo) ap.a(getRootView(), R.id.body_layout);
            singleListenBookInfo.setBookInfo(gVar2);
            singleListenBookInfo.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ ColCard_Listen_Books b;

                public void onClick(View view) {
                    this.b.reportListenClick();
                    if (this.b.getEvnetListener() != null) {
                        gVar2.b(this.b.getEvnetListener());
                    }
                }
            });
            SingleListenBookInfo singleListenBookInfo2 = (SingleListenBookInfo) ap.a(getRootView(), R.id.body_layout_1);
            singleListenBookInfo2.a(true);
            if (getItemList().size() > 1) {
                gVar = (g) arrayList.get(1);
                singleListenBookInfo2.setVisibility(0);
                singleListenBookInfo2.setBookInfo(gVar);
                singleListenBookInfo2.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ ColCard_Listen_Books b;

                    public void onClick(View view) {
                        this.b.reportListenClick();
                        if (this.b.getEvnetListener() != null) {
                            gVar.b(this.b.getEvnetListener());
                        }
                    }
                });
            } else {
                singleListenBookInfo2.setVisibility(8);
            }
            singleListenBookInfo2 = (SingleListenBookInfo) ap.a(getRootView(), R.id.body_layout_2);
            singleListenBookInfo2.a(true);
            if (getItemList().size() > 2) {
                gVar = (g) arrayList.get(2);
                singleListenBookInfo2.setVisibility(0);
                singleListenBookInfo2.setBookInfo(gVar);
                singleListenBookInfo2.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ ColCard_Listen_Books b;

                    public void onClick(View view) {
                        this.b.reportListenClick();
                        if (this.b.getEvnetListener() != null) {
                            gVar.b(this.b.getEvnetListener());
                        }
                    }
                });
                return;
            }
            singleListenBookInfo2.setVisibility(8);
        }
    }

    public void refresh() {
        Map hashMap = new HashMap();
        hashMap.put(s.ORIGIN, String.valueOf(this.mColumeId));
        i.a("event_A100", hashMap, ReaderApplication.getApplicationImp());
    }

    public boolean parseData(JSONObject jSONObject) throws Exception {
        int i = 0;
        c.a("ColCard", "parseData " + jSONObject.toString());
        this.mDispaly = 3;
        getItemList().clear();
        this.mColumeId = jSONObject.optInt(JSON_KEY_CID);
        this.mServerTitle = jSONObject.optString("title");
        this.mPromotionName = jSONObject.optString(JSON_KEY_PROMOTION_NAME);
        JSONArray optJSONArray = jSONObject.optJSONArray(JSON_KEY_BOOKLIST);
        if (optJSONArray != null) {
            int length = optJSONArray.length();
            if (length > 0) {
                while (i < length) {
                    JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                    s gVar = new g();
                    gVar.parseData(jSONObject2);
                    addItem(gVar);
                    i++;
                }
                initRandomItem(true);
                return true;
            }
        }
        return false;
    }

    private void reportListenClick() {
        Map hashMap = new HashMap();
        hashMap.put(s.ORIGIN, String.valueOf(this.mColumeId));
        i.a("event_A126", hashMap, ReaderApplication.getApplicationImp());
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
