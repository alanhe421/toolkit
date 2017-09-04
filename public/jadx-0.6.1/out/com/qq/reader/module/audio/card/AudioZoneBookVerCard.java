package com.qq.reader.module.audio.card;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.audio.view.AudioZoneBookVerItemView;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.card.impl.CardLeftTitle;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.feed.card.view.CardMoreView;
import com.qq.reader.qurl.c;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class AudioZoneBookVerCard extends a {
    protected static final String JSON_KEY_BOOK_LIST = "lbookList";
    protected static final String JSON_KEY_CID = "cid";
    protected static final String JSON_KEY_MORE_ACTION = "more";
    protected static final String JSON_KEY_MORE_QURL = "qurl";
    protected static final String JSON_KEY_SUB_TITLE = "subtitle";
    protected static final String JSON_KEY_TITLE = "title";
    private int[] itemIds = new int[]{R.id.book_view1, R.id.book_view2, R.id.book_view3};
    private String mColumeId = "";
    private int mIntroType;
    private int[] mRefreshIndex;
    private int moreType;
    private String moreUrl;

    public AudioZoneBookVerCard(b bVar, String str) {
        super(bVar, str);
    }

    private void showStatics() {
        Map hashMap = new HashMap();
        hashMap.put(s.ORIGIN, this.mColumeId);
        i.a("event_B308", hashMap, ReaderApplication.getApplicationImp());
    }

    private void moreClickStatics() {
        Map hashMap = new HashMap();
        hashMap.put(s.ORIGIN, this.mColumeId);
        i.a("event_B310", hashMap, ReaderApplication.getApplicationImp());
    }

    public int getResLayoutId() {
        return R.layout.audio_zone_book_ver_card;
    }

    public void build(JSONObject jSONObject) {
        super.build(jSONObject);
        this.mIntroType = jSONObject.optInt("introtype");
    }

    public void attachView() {
        if (getItemList().size() > 0) {
            CardLeftTitle cardLeftTitle = (CardLeftTitle) ap.a(getRootView(), R.id.title_layout);
            if (this.mIntroType == 0) {
                cardLeftTitle.setCardTitle(this.mIconIndex, this.mShowTitle, this.mPromotionName, null);
            } else {
                cardLeftTitle.setCardTitle(this.mIconIndex, this.mShowTitle, null, this.mPromotionName);
            }
            CardMoreView cardMoreView = (CardMoreView) ap.a(getRootView(), R.id.more_layout);
            if (this.moreType > 0) {
                cardMoreView.setVisibility(0);
                cardMoreView.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ AudioZoneBookVerCard a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        switch (this.a.moreType) {
                            case 1:
                                try {
                                    c.a(this.a.getEvnetListener().getFromActivity(), this.a.moreUrl);
                                    break;
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    break;
                                }
                            case 2:
                                this.a.refresh();
                                break;
                        }
                        this.a.moreClickStatics();
                    }
                });
                cardMoreView.setText(this.moreType == 1 ? "查看更多" : "换一换");
            } else {
                cardMoreView.setVisibility(8);
            }
            List arrayList = new ArrayList();
            int i;
            if (this.mRefreshIndex != null) {
                for (i = 0; i < this.mDispaly; i++) {
                    arrayList.add((com.qq.reader.module.audio.b.a) getItemList().get(this.mRefreshIndex[i]));
                }
            } else {
                for (i = 0; i < this.mDispaly; i++) {
                    arrayList.add((com.qq.reader.module.audio.b.a) getItemList().get(i));
                }
            }
            for (int i2 = 0; i2 < this.itemIds.length; i2++) {
                final com.qq.reader.module.audio.b.a aVar = (com.qq.reader.module.audio.b.a) arrayList.get(i2);
                AudioZoneBookVerItemView audioZoneBookVerItemView = (AudioZoneBookVerItemView) ap.a(getRootView(), this.itemIds[i2]);
                if (aVar != null) {
                    audioZoneBookVerItemView.setBookInfo(aVar);
                    audioZoneBookVerItemView.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ AudioZoneBookVerCard b;

                        public void onClick(View view) {
                            this.b.reportClick(aVar);
                            if (this.b.getEvnetListener() != null) {
                                aVar.a(this.b.getEvnetListener());
                            }
                        }
                    });
                    if (i2 > 0) {
                        audioZoneBookVerItemView.a(true);
                    }
                    audioZoneBookVerItemView.setVisibility(0);
                } else {
                    audioZoneBookVerItemView.setVisibility(8);
                }
            }
            showStatics();
        }
    }

    public void refresh() {
        Map hashMap = new HashMap();
        hashMap.put(s.ORIGIN, String.valueOf(this.mColumeId));
        i.a("event_A100", hashMap, ReaderApplication.getApplicationImp());
    }

    public boolean parseData(JSONObject jSONObject) throws Exception {
        this.mPromotionName = jSONObject.optString(JSON_KEY_SUB_TITLE);
        this.mServerTitle = jSONObject.optString("title");
        JSONArray optJSONArray = jSONObject.optJSONArray(JSON_KEY_BOOK_LIST);
        this.moreType = jSONObject.optInt(JSON_KEY_MORE_ACTION);
        this.moreUrl = jSONObject.optString(JSON_KEY_MORE_QURL);
        if (optJSONArray != null) {
            int length = optJSONArray.length();
            this.mDispaly = length > this.itemIds.length ? this.itemIds.length : length;
            if (length > 0) {
                if (getItemList() != null) {
                    getItemList().clear();
                }
                for (int i = 0; i < length; i++) {
                    JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                    s aVar = new com.qq.reader.module.audio.b.a();
                    aVar.parseData(jSONObject2);
                    if (this.mType.equals(String.valueOf(6))) {
                        ((com.qq.reader.module.audio.b.a) aVar).a((i + 1) + "." + ((com.qq.reader.module.audio.b.a) aVar).n());
                    }
                    addItem(aVar);
                    if (TextUtils.isEmpty(this.mColumeId)) {
                        this.mColumeId = aVar.getOrigin();
                    }
                }
                if (!this.mType.equals(String.valueOf(6))) {
                    initRandomItem(true);
                }
                return true;
            }
        }
        return false;
    }

    private void reportClick(com.qq.reader.module.audio.b.a aVar) {
        Map hashMap = new HashMap();
        hashMap.put(s.ORIGIN, aVar.getOrigin());
        hashMap.put(s.ALG, aVar.getAlg());
        i.a("event_B309", hashMap, ReaderApplication.getApplicationImp());
    }

    private void initRandomItem(boolean z) {
        int i = this.mDispaly;
        int size = getItemList().size();
        boolean z2 = z && isExpired();
        this.mRefreshIndex = getRandomListIndex(i, size, z2);
    }
}
