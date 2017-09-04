package com.qq.reader.module.feed.card;

import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.dynamicload.Lib.DLConstants;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.readertask.protocol.H5GameChargeTask;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.card.impl.CardTitle;
import com.qq.reader.module.bookstore.qnative.card.impl.SingleBookInfo;
import com.qq.reader.module.bookstore.qnative.item.g;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.feed.card.view.FeedCardSingleView;
import com.qq.reader.module.feed.data.impl.b;
import com.qq.reader.qurl.c;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.feedback.proguard.R;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class FeedColumn3VerListCard extends FeedMultiClickBaseCard implements b {
    private SingleBookInfo bookInfo_1;
    private SingleBookInfo bookInfo_2;
    private SingleBookInfo bookInfo_3;
    private CardTitle cardTitle;
    private TextView columnDes;
    private SparseArray<FeedCardSingleView> feedCardsCacheSA = new SparseArray();
    private ArrayList<JSONObject> jsonObjectArrayList = new ArrayList();
    private boolean mAttached = false;
    private int mBookIndex1 = 0;
    private int mBookIndex2 = 1;
    private int mBookIndex3 = 2;
    private ArrayList<g> mBookItems = new ArrayList();
    private int mCurPushNameIndex = 0;
    private ArrayList<String> mPushNames = new ArrayList();
    private String mQurl;
    private String mTitle;

    public FeedColumn3VerListCard(com.qq.reader.module.bookstore.qnative.page.b bVar, String str) {
        super(bVar, str);
    }

    public void attachView() {
        this.mAttached = true;
        this.cardTitle = (CardTitle) ap.a(getRootView(), R.id.card_title);
        if (this.mPushNames.size() > 0) {
            this.cardTitle.setCardTitle(0, this.mTitle, (String) this.mPushNames.get(this.mCurPushNameIndex), null);
        } else {
            this.cardTitle.setCardTitle(0, this.mTitle, "", null);
        }
        this.bookInfo_1 = (SingleBookInfo) ap.a(getRootView(), R.id.body_layout_1);
        if (this.mBookItems.size() > 0) {
            this.bookInfo_1.setBookInfoByFeedFirstPage((g) this.mBookItems.get(this.mBookIndex1));
            this.bookInfo_1.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ FeedColumn3VerListCard a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    i.a("event_F14", null, ReaderApplication.getApplicationImp().getApplicationContext());
                    this.a.goToDetail((g) this.a.mBookItems.get(this.a.mBookIndex1));
                }
            });
        }
        this.bookInfo_2 = (SingleBookInfo) ap.a(getRootView(), R.id.body_layout_2);
        this.bookInfo_2.a(true);
        if (this.mBookItems.size() > 1) {
            g gVar = (g) this.mBookItems.get(this.mBookIndex2);
            this.bookInfo_2.setVisibility(0);
            this.bookInfo_2.setBookInfoByFeedFirstPage(gVar);
            this.bookInfo_2.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ FeedColumn3VerListCard a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    i.a("event_F14", null, ReaderApplication.getApplicationImp().getApplicationContext());
                    this.a.goToDetail((g) this.a.mBookItems.get(this.a.mBookIndex2));
                }
            });
        } else {
            this.bookInfo_2.setVisibility(8);
        }
        this.bookInfo_3 = (SingleBookInfo) ap.a(getRootView(), R.id.body_layout_3);
        this.bookInfo_3.a(true);
        if (this.mBookItems.size() > 2) {
            gVar = (g) this.mBookItems.get(this.mBookIndex3);
            this.bookInfo_3.setVisibility(0);
            this.bookInfo_3.setBookInfoByFeedFirstPage(gVar);
            this.bookInfo_3.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ FeedColumn3VerListCard a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    i.a("event_F14", null, ReaderApplication.getApplicationImp().getApplicationContext());
                    this.a.goToDetail((g) this.a.mBookItems.get(this.a.mBookIndex3));
                }
            });
        } else {
            this.bookInfo_3.setVisibility(8);
        }
        View a = ap.a(getRootView(), R.id.localstore_moreaction);
        if (a != null) {
            a.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ FeedColumn3VerListCard a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    int i = 3;
                    try {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(this.a.mQurl).append("&bids=").append(((g) this.a.mBookItems.get(this.a.mBookIndex1)).m()).append(",").append(((g) this.a.mBookItems.get(this.a.mBookIndex2)).m()).append(",").append(((g) this.a.mBookItems.get(this.a.mBookIndex3)).m());
                        if (this.a.mBookItems.size() > 3) {
                            int i2 = 0;
                            while (i2 < this.a.mBookItems.size()) {
                                int i3;
                                if (i2 != this.a.mBookIndex1 && i2 != this.a.mBookIndex2 && i2 != this.a.mBookIndex3) {
                                    if (i >= this.a.mBookItems.size() - 1) {
                                        stringBuilder.append(",").append(((g) this.a.mBookItems.get(i2)).m());
                                        break;
                                    } else {
                                        stringBuilder.append(",").append(((g) this.a.mBookItems.get(i2)).m());
                                        i3 = i + 1;
                                    }
                                } else {
                                    i3 = i;
                                }
                                i2++;
                                i = i3;
                            }
                        }
                        i.a("event_F17", null, ReaderApplication.getApplicationImp().getApplicationContext());
                        c.a(this.a.getEvnetListener().getFromActivity(), stringBuilder.toString(), null);
                    } catch (Exception e) {
                    }
                }
            });
        }
        i.a("event_F9", null, ReaderApplication.getApplicationImp().getApplicationContext());
        statExposure();
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

    public int getResLayoutId() {
        return R.layout.feed_column_3ver_list_card_layout;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        int i = 0;
        if (jSONObject == null) {
            return false;
        }
        JSONObject optJSONObject = jSONObject.optJSONObject(MessageKey.MSG_CONTENT);
        if (optJSONObject == null) {
            return false;
        }
        this.mTitle = optJSONObject.optString("title");
        this.mQurl = optJSONObject.optString("qurl");
        JSONArray optJSONArray = optJSONObject.optJSONArray("pushName");
        if (optJSONArray != null && optJSONArray.length() > 0) {
            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                this.mPushNames.add(optJSONArray.getString(i2));
            }
        }
        JSONArray optJSONArray2 = optJSONObject.optJSONArray("bookList");
        if (optJSONArray2 != null) {
            int length = optJSONArray2.length();
            if (length > 0) {
                if (this.mBookItems.size() > 0) {
                    this.mBookItems.clear();
                }
                while (i < length) {
                    JSONObject jSONObject2 = optJSONArray2.getJSONObject(i);
                    g gVar = new g();
                    gVar.parseData(jSONObject2);
                    this.mBookItems.add(gVar);
                    i++;
                }
                return true;
            }
        }
        return true;
    }

    protected ArrayList<View> getViews() {
        ArrayList<View> arrayList = new ArrayList();
        if (this.jsonObjectArrayList.size() > 0) {
            ViewGroup viewGroup = (ViewGroup) ap.a(getRootView(), R.id.view_container);
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                arrayList.add(viewGroup.getChildAt(i));
            }
        }
        return arrayList;
    }

    protected void doClick(View view) {
    }

    public boolean swipeEnable() {
        return false;
    }

    public void change() {
        this.mIsNeedStatAlg = true;
        this.mBookIndex1 = this.mBookIndex3 + 1;
        if (this.mBookIndex1 == this.mBookItems.size()) {
            this.mBookIndex1 = 0;
        }
        this.mBookIndex2 = this.mBookIndex1 + 1;
        if (this.mBookIndex2 == this.mBookItems.size()) {
            this.mBookIndex2 = 0;
        }
        this.mBookIndex3 = this.mBookIndex2 + 1;
        if (this.mBookIndex3 == this.mBookItems.size()) {
            this.mBookIndex3 = 0;
        }
        if (this.mAttached) {
            if (this.mPushNames.size() > 1) {
                this.mCurPushNameIndex++;
                if (this.mCurPushNameIndex == this.mPushNames.size()) {
                    this.mCurPushNameIndex = 0;
                }
                this.cardTitle.setCardTitle(0, this.mTitle, (String) this.mPushNames.get(this.mCurPushNameIndex), null);
            }
            if (this.mBookItems.size() > 3) {
                this.bookInfo_1.setBookInfoByFeedFirstPage((g) this.mBookItems.get(this.mBookIndex1));
                this.bookInfo_2.setBookInfoByFeedFirstPage((g) this.mBookItems.get(this.mBookIndex2));
                this.bookInfo_3.setBookInfoByFeedFirstPage((g) this.mBookItems.get(this.mBookIndex3));
                statExposure();
            }
        }
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

    private String getFeedStatString() {
        if (this.mBookItems == null || this.mBookItems.size() < 3) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            g gVar;
            if (i == 0) {
                gVar = (g) this.mBookItems.get(this.mBookIndex1);
            } else if (i == 1) {
                gVar = (g) this.mBookItems.get(this.mBookIndex2);
            } else {
                gVar = (g) this.mBookItems.get(this.mBookIndex3);
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
}
