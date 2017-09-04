package com.qq.reader.module.audio.card;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.audio.view.AudioZone3BookHorView;
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

public class AudioZoneBookRecCard extends a {
    private static final int DISPLAY_COUNT = 4;
    protected static final String JSON_KEY_BOOK_LIST = "lbookList";
    protected static final String JSON_KEY_CID = "cid";
    protected static final String JSON_KEY_MORE_ACTION = "more";
    protected static final String JSON_KEY_MORE_QURL = "qurl";
    protected static final String JSON_KEY_SUB_TITLE = "subtitle";
    protected static final String JSON_KEY_TITLE = "title";
    private int imgItemIndex = -1;
    private String mColumeId = "";
    private int[] mRefreshIndex;
    private int moreType;
    private String moreUrl;

    public AudioZoneBookRecCard(b bVar, String str) {
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

    public void attachView() {
        LinearLayout linearLayout = (LinearLayout) ap.a(getRootView(), R.id.content_layout);
        linearLayout.setVisibility(0);
        CardLeftTitle cardLeftTitle = (CardLeftTitle) ap.a(getRootView(), R.id.title_layout);
        cardLeftTitle.setVisibility(0);
        cardLeftTitle.setCardTitle(37, this.mShowTitle, this.mPromotionName, null);
        CardMoreView cardMoreView = (CardMoreView) ap.a(getRootView(), R.id.more_layout);
        if (this.moreType > 0) {
            cardMoreView.setVisibility(0);
            cardMoreView.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ AudioZoneBookRecCard a;

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
        this.imgItemIndex = -1;
        List arrayList = new ArrayList();
        int i;
        if (this.mRefreshIndex == null || this.mDispaly != this.mRefreshIndex.length) {
            i = 0;
            while (i < this.mDispaly) {
                arrayList.add((com.qq.reader.module.audio.b.a) getItemList().get(i));
                if (this.imgItemIndex < 0 && !TextUtils.isEmpty(((com.qq.reader.module.audio.b.a) arrayList.get(i)).g())) {
                    this.imgItemIndex = i;
                }
                i++;
            }
            if (this.imgItemIndex < 0) {
                for (i = this.mDispaly; i < getItemList().size(); i++) {
                    if (!TextUtils.isEmpty(((com.qq.reader.module.audio.b.a) getItemList().get(i)).g())) {
                        this.imgItemIndex = 0;
                        arrayList.set(this.imgItemIndex, (com.qq.reader.module.audio.b.a) getItemList().get(i));
                        break;
                    }
                }
            }
        } else {
            i = 0;
            while (i < this.mDispaly) {
                arrayList.add((com.qq.reader.module.audio.b.a) getItemList().get(this.mRefreshIndex[i]));
                if (this.imgItemIndex < 0 && !TextUtils.isEmpty(((com.qq.reader.module.audio.b.a) arrayList.get(i)).g())) {
                    this.imgItemIndex = i;
                }
                i++;
            }
            if (this.imgItemIndex < 0) {
                i = 0;
                while (i < getItemList().size()) {
                    if (!contains(this.mRefreshIndex, i) && !TextUtils.isEmpty(((com.qq.reader.module.audio.b.a) getItemList().get(i)).g())) {
                        this.imgItemIndex = 0;
                        this.mRefreshIndex[this.imgItemIndex] = i;
                        arrayList.set(this.imgItemIndex, (com.qq.reader.module.audio.b.a) getItemList().get(i));
                        break;
                    }
                    i++;
                }
            }
        }
        if (this.imgItemIndex < 0) {
            this.imgItemIndex = 0;
        }
        if (arrayList.size() > 0) {
            final com.qq.reader.module.audio.b.a aVar = (com.qq.reader.module.audio.b.a) arrayList.get(this.imgItemIndex);
            ImageView imageView = (ImageView) ap.a(getRootView(), R.id.book0_cover);
            TextView textView = (TextView) ap.a(getRootView(), R.id.book0_title);
            TextView textView2 = (TextView) ap.a(getRootView(), R.id.book0_intro);
            if (TextUtils.isEmpty(aVar.n())) {
                textView.setVisibility(8);
            } else {
                textView.setText(aVar.n());
                textView.setVisibility(0);
            }
            if (TextUtils.isEmpty(aVar.s())) {
                textView2.setVisibility(8);
            } else {
                textView2.setText(aVar.s());
                textView2.setVisibility(0);
            }
            com.qq.reader.common.imageloader.c.a(getEvnetListener().getFromActivity()).a(aVar.g(), imageView, com.qq.reader.common.imageloader.a.a().j());
            ap.a(getRootView(), R.id.book0_view).setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ AudioZoneBookRecCard b;

                public void onClick(View view) {
                    if (this.b.getEvnetListener() != null) {
                        aVar.a(this.b.getEvnetListener());
                        Map hashMap = new HashMap();
                        hashMap.put(s.ORIGIN, this.b.mColumeId);
                        i.a("event_B312", hashMap, ReaderApplication.getApplicationImp());
                    }
                }
            });
            AudioZone3BookHorView audioZone3BookHorView = (AudioZone3BookHorView) ap.a(getRootView(), R.id.book_layout);
            audioZone3BookHorView.setVisibility(0);
            if (arrayList.size() >= 4) {
                arrayList.remove(this.imgItemIndex);
                audioZone3BookHorView.setBookInfo(arrayList, this.mDispaly);
                audioZone3BookHorView.setBookOnClickListener(arrayList, getEvnetListener());
            } else {
                audioZone3BookHorView.setVisibility(8);
            }
        } else {
            linearLayout.setVisibility(8);
        }
        showStatics();
    }

    public int getResLayoutId() {
        return R.layout.audio_zone_book_rec_card;
    }

    public boolean parseData(JSONObject jSONObject) throws Exception {
        int i = 4;
        int i2 = 0;
        this.mServerTitle = jSONObject.optString("title");
        this.mPromotionName = jSONObject.optString(JSON_KEY_SUB_TITLE);
        JSONArray optJSONArray = jSONObject.optJSONArray(JSON_KEY_BOOK_LIST);
        this.moreType = jSONObject.optInt(JSON_KEY_MORE_ACTION);
        this.moreUrl = jSONObject.optString(JSON_KEY_MORE_QURL);
        if (optJSONArray != null) {
            int length = optJSONArray.length();
            if (length <= 4) {
                i = length;
            }
            this.mDispaly = i;
            if (length > 0) {
                if (getItemList() != null) {
                    getItemList().clear();
                }
                while (i2 < length) {
                    JSONObject jSONObject2 = optJSONArray.getJSONObject(i2);
                    s aVar = new com.qq.reader.module.audio.b.a();
                    aVar.parseData(jSONObject2);
                    addItem(aVar);
                    if (TextUtils.isEmpty(this.mColumeId)) {
                        this.mColumeId = aVar.getOrigin();
                    }
                    i2++;
                }
                initRandomItem(true);
                return true;
            }
        }
        return false;
    }

    public void refresh() {
        initRandomItem(false);
        attachView();
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
