package com.qq.reader.module.bookstore.qnative.card.impl;

import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.qurl.c;
import com.qq.reader.view.TagContainerLayout;
import com.tencent.android.tpush.common.Constants;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class LabelCard_Interest extends com.qq.reader.module.bookstore.qnative.card.a {
    protected static final String JSON_KEY_PROMOTION_NAME = "pushName";
    protected static final String JSON_KEY_TITLE = "title";

    private class a extends s {
        public String a;
        public String b;
        public int c;
        final /* synthetic */ LabelCard_Interest d;

        private a(LabelCard_Interest labelCard_Interest) {
            this.d = labelCard_Interest;
        }

        public void parseData(JSONObject jSONObject) {
            if (jSONObject != null) {
                this.a = jSONObject.optString(Constants.FLAG_TAG_NAME);
                this.b = jSONObject.optString("tagId");
                this.c = jSONObject.optInt("tagType", 0);
            }
        }
    }

    public LabelCard_Interest(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.localstore_card_guse_you_like;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        this.mServerTitle = jSONObject.optString("title");
        this.mPromotionName = jSONObject.optString(JSON_KEY_PROMOTION_NAME);
        List itemList = getItemList();
        if (itemList != null) {
            itemList.clear();
        } else {
            itemList = new ArrayList();
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("data");
        int i = 0;
        while (optJSONArray != null && i < optJSONArray.length()) {
            s aVar = new a();
            aVar.parseData(optJSONArray.optJSONObject(i));
            addItem(aVar);
            i++;
        }
        if (itemList == null || itemList.size() <= 0) {
            return false;
        }
        return true;
    }

    public void attachView() {
        List arrayList = new ArrayList();
        List itemList = getItemList();
        int i = 0;
        while (itemList != null && i < itemList.size()) {
            arrayList.add(((a) itemList.get(i)).a);
            i++;
        }
        ((CardTitle) ap.a(getRootView(), R.id.title)).setCardTitle(0, this.mShowTitle, this.mPromotionName, null);
        getTagContainerLayout().setTags(arrayList);
        getTagContainerLayout().setOnTagClickListener(new com.qq.reader.view.TagView.a(this) {
            final /* synthetic */ LabelCard_Interest a;

            {
                this.a = r1;
            }

            public void a(int i, String str) {
                List itemList = this.a.getItemList();
                if (itemList != null && i < itemList.size()) {
                    try {
                        String str2;
                        a aVar = (a) itemList.get(i);
                        String str3 = "";
                        if (aVar.c == 0) {
                            str2 = (("uniteqqreader://nativepage/tag?key=" + aVar.a) + "&actionTag=") + aVar.b + ",-1,-1,-1,-1,6";
                        } else if (aVar.c == 1) {
                            str2 = "uniteqqreader://nativepage/category/list?" + "actionId=" + aVar.b;
                        } else {
                            str2 = str3;
                        }
                        c.a(this.a.getEvnetListener().getFromActivity(), str2, null);
                        this.a.clickStatics();
                    } catch (Exception e) {
                        com.qq.reader.common.monitor.debug.c.e("Error", e.getMessage());
                    }
                }
            }

            public void b(int i, String str) {
            }

            public void a(int i) {
            }
        });
        showStatics();
    }

    private void showStatics() {
        i.a("event_shown_" + getCardId(), null, ReaderApplication.getApplicationImp());
    }

    private void clickStatics() {
        i.a("event_clicked_" + getCardId(), null, ReaderApplication.getApplicationImp());
    }

    private TagContainerLayout getTagContainerLayout() {
        return (TagContainerLayout) ap.a(getRootView(), R.id.tag_container_layout);
    }
}
