package com.qq.reader.module.bookstore.qnative.card.impl;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.bookstore.qnative.card.BaseAdvCard;
import com.qq.reader.module.bookstore.qnative.item.g;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class AdvCard_Recommend extends BaseAdvCard {
    protected static final String JSON_KEY_ADVLIST = "adList";
    protected static final String JSON_KEY_PROMOTION_NAME = "pushName";
    protected static final String JSON_KEY_TITLE = "title";
    c mAction;
    private int[] mRefreshIndex;

    public AdvCard_Recommend(b bVar, String str) {
        super(bVar, str);
    }

    public void build(JSONObject jSONObject) {
        super.build(jSONObject);
        String optString = jSONObject.optString("jumpPageName");
        String optString2 = jSONObject.optString("controllerTitle");
        if (optString.length() > 0) {
            this.mAction = new c(null);
            Bundle a = this.mAction.a();
            a.putString("LOCAL_STORE_IN_TITLE", optString2);
            a.putString("KEY_JUMP_PAGENAME", optString);
        }
    }

    public int getResLayoutId() {
        return R.layout.localstore_card_adv_recommend;
    }

    public void attachView() {
        if (getItemList().size() > 0) {
            int size;
            final g gVar;
            ((CardTitle) ap.a(getRootView(), R.id.title_layout)).setCardTitle(this.mIconIndex, this.mShowTitle, this.mPromotionName, null);
            List arrayList = new ArrayList();
            int i;
            if (this.mRefreshIndex != null) {
                for (i = 0; i < this.mDispaly; i++) {
                    arrayList.add((com.qq.reader.module.bookstore.qnative.item.b) getItemList().get(this.mRefreshIndex[i]));
                }
            } else {
                for (i = 0; i < this.mDispaly; i++) {
                    arrayList.add((com.qq.reader.module.bookstore.qnative.item.b) getItemList().get(i));
                }
            }
            com.qq.reader.module.bookstore.qnative.item.b bVar = (com.qq.reader.module.bookstore.qnative.item.b) arrayList.get(0);
            AdvInfo advInfo = (AdvInfo) ap.a(getRootView(), R.id.localstore_adv_0_body);
            advInfo.setAdvItem(bVar);
            advInfo.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ AdvCard_Recommend a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    if (this.a.mAction != null && this.a.getEvnetListener() != null) {
                        this.a.mAction.a(this.a.getEvnetListener());
                    }
                }
            });
            if (bVar.g() != null) {
                size = bVar.g().size();
            } else {
                size = 0;
            }
            BookInfo_Simple bookInfo_Simple = (BookInfo_Simple) ap.a(getRootView(), R.id.localstore_book_0_body);
            if (size > 0) {
                gVar = (g) bVar.g().get(0);
                bookInfo_Simple.setBookInfo(gVar);
                bookInfo_Simple.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ AdvCard_Recommend b;

                    public void onClick(View view) {
                        gVar.a(this.b.getEvnetListener());
                    }
                });
            } else {
                bookInfo_Simple.setVisibility(8);
            }
            bookInfo_Simple = (BookInfo_Simple) ap.a(getRootView(), R.id.localstore_book_1_body);
            if (size > 1) {
                gVar = (g) bVar.g().get(1);
                bookInfo_Simple.setBookInfo(gVar);
                bookInfo_Simple.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ AdvCard_Recommend b;

                    public void onClick(View view) {
                        gVar.a(this.b.getEvnetListener());
                    }
                });
            } else {
                bookInfo_Simple.setVisibility(8);
            }
            bookInfo_Simple = (BookInfo_Simple) ap.a(getRootView(), R.id.localstore_book_2_body);
            if (size > 2) {
                gVar = (g) bVar.g().get(2);
                bookInfo_Simple.setBookInfo(gVar);
                bookInfo_Simple.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ AdvCard_Recommend b;

                    public void onClick(View view) {
                        gVar.a(this.b.getEvnetListener());
                    }
                });
            } else {
                bookInfo_Simple.setVisibility(8);
            }
            bookInfo_Simple = (BookInfo_Simple) ap.a(getRootView(), R.id.localstore_book_3_body);
            if (size > 3) {
                gVar = (g) bVar.g().get(3);
                bookInfo_Simple.setBookInfo(gVar);
                bookInfo_Simple.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ AdvCard_Recommend b;

                    public void onClick(View view) {
                        gVar.a(this.b.getEvnetListener());
                    }
                });
            } else {
                bookInfo_Simple.setVisibility(8);
            }
            bookInfo_Simple = (BookInfo_Simple) ap.a(getRootView(), R.id.localstore_book_4_body);
            if (size > 4) {
                final g gVar2 = (g) bVar.g().get(4);
                bookInfo_Simple.setBookInfo(gVar2);
                bookInfo_Simple.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ AdvCard_Recommend b;

                    public void onClick(View view) {
                        gVar2.a(this.b.getEvnetListener());
                    }
                });
                return;
            }
            bookInfo_Simple.setVisibility(8);
        }
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        int i = 0;
        getItemList().clear();
        this.mDispaly = 1;
        this.mServerTitle = jSONObject.optString("title");
        this.mPromotionName = jSONObject.optString(JSON_KEY_PROMOTION_NAME);
        JSONArray optJSONArray = jSONObject.optJSONArray("adList");
        if (optJSONArray != null) {
            int length = optJSONArray.length();
            if (length > 0) {
                while (i < length) {
                    JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                    s bVar = new com.qq.reader.module.bookstore.qnative.item.b();
                    bVar.parseData(jSONObject2);
                    addItem(bVar);
                    i++;
                }
                initRandomItem(true);
                return true;
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
