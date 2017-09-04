package com.qq.reader.module.bookstore.qnative.card.impl;

import android.view.View;
import android.view.View.OnClickListener;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.card.BaseAdvCard;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AdvCard_Recommend_Simple extends BaseAdvCard {
    protected static final String JSON_KEY_ADVLIST = "adList";
    protected static final String JSON_KEY_PROMOTION_NAME = "pushName";
    protected static final String JSON_KEY_TITLE = "title";
    private int[] mRefreshIndex;

    public AdvCard_Recommend_Simple(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.localstore_card_adv_recommend_simple;
    }

    public void attachView() {
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
        if (getItemList().size() > 0) {
            final com.qq.reader.module.bookstore.qnative.item.b bVar = (com.qq.reader.module.bookstore.qnative.item.b) arrayList.get(0);
            AdvInfo_Simple advInfo_Simple = (AdvInfo_Simple) ap.a(getRootView(), R.id.localstore_adv_0_body);
            advInfo_Simple.setVisibility(0);
            advInfo_Simple.setAdvItem(bVar);
            advInfo_Simple.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ AdvCard_Recommend_Simple b;

                public void onClick(View view) {
                    bVar.a(this.b.getEvnetListener());
                }
            });
        } else {
            ((AdvInfo_Simple) ap.a(getRootView(), R.id.localstore_adv_0_body)).setVisibility(8);
        }
        if (getItemList().size() > 1) {
            bVar = (com.qq.reader.module.bookstore.qnative.item.b) arrayList.get(1);
            advInfo_Simple = (AdvInfo_Simple) ap.a(getRootView(), R.id.localstore_adv_1_body);
            advInfo_Simple.setVisibility(0);
            advInfo_Simple.setAdvItem(bVar);
            advInfo_Simple.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ AdvCard_Recommend_Simple b;

                public void onClick(View view) {
                    bVar.a(this.b.getEvnetListener());
                }
            });
        } else {
            ((AdvInfo_Simple) ap.a(getRootView(), R.id.localstore_adv_1_body)).setVisibility(8);
        }
        if (getItemList().size() > 2) {
            bVar = (com.qq.reader.module.bookstore.qnative.item.b) arrayList.get(2);
            advInfo_Simple = (AdvInfo_Simple) ap.a(getRootView(), R.id.localstore_adv_2_body);
            advInfo_Simple.setVisibility(0);
            advInfo_Simple.setAdvItem(bVar);
            advInfo_Simple.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ AdvCard_Recommend_Simple b;

                public void onClick(View view) {
                    bVar.a(this.b.getEvnetListener());
                }
            });
        } else {
            ((AdvInfo_Simple) ap.a(getRootView(), R.id.localstore_adv_2_body)).setVisibility(8);
        }
        if (getItemList().size() > 3) {
            bVar = (com.qq.reader.module.bookstore.qnative.item.b) arrayList.get(3);
            advInfo_Simple = (AdvInfo_Simple) ap.a(getRootView(), R.id.localstore_adv_3_body);
            advInfo_Simple.setVisibility(0);
            advInfo_Simple.setAdvItem(bVar);
            advInfo_Simple.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ AdvCard_Recommend_Simple b;

                public void onClick(View view) {
                    bVar.a(this.b.getEvnetListener());
                }
            });
        } else {
            ((AdvInfo_Simple) ap.a(getRootView(), R.id.localstore_adv_3_body)).setVisibility(8);
        }
        if (getItemList().size() > 4) {
            bVar = (com.qq.reader.module.bookstore.qnative.item.b) arrayList.get(4);
            advInfo_Simple = (AdvInfo_Simple) ap.a(getRootView(), R.id.localstore_adv_4_body);
            advInfo_Simple.setVisibility(0);
            advInfo_Simple.setAdvItem(bVar);
            advInfo_Simple.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ AdvCard_Recommend_Simple b;

                public void onClick(View view) {
                    bVar.a(this.b.getEvnetListener());
                }
            });
            return;
        }
        ((AdvInfo_Simple) ap.a(getRootView(), R.id.localstore_adv_4_body)).setVisibility(8);
    }

    protected boolean parseData(JSONObject jSONObject) throws JSONException {
        int i = 0;
        getItemList().clear();
        this.mDispaly = 5;
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
