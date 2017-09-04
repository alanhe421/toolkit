package com.qq.reader.module.feed.card;

import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.dynamicload.Lib.DLConstants;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.feed.card.view.FeedCardSingleView;
import com.qq.reader.qurl.c;
import com.tencent.feedback.proguard.R;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class Feed3VerBooksGroupCard extends FeedMultiClickBaseCard {
    private static final String JSON_KEY_BK_ID = "id";
    private static final String JSON_KEY_BK_LIST = "list";
    private static final String JSON_KEY_DESC = "desc";
    private static final String JSON_KEY_IMAGE0 = "img";
    private static final String JSON_KEY_PICS = "pics";
    private static final String JSON_KEY_PICS_URL = "url";
    private static final String JSON_KEY_TITLE = "title";
    private SparseArray<FeedCardSingleView> feedCardsCacheSA;
    private int imageHeight;
    private ArrayList<JSONObject> jsonObjectArrayList;
    private List<a> mBooks;
    private String title;

    private class a {
        String a;
        String b;
        final /* synthetic */ Feed3VerBooksGroupCard c;

        private a(Feed3VerBooksGroupCard feed3VerBooksGroupCard) {
            this.c = feed3VerBooksGroupCard;
        }
    }

    public Feed3VerBooksGroupCard(b bVar, String str) {
        super(bVar, str);
        this.jsonObjectArrayList = new ArrayList();
        this.feedCardsCacheSA = new SparseArray();
        this.mBooks = new ArrayList();
        this.isClickEnable = true;
    }

    public int getResLayoutId() {
        return R.layout.concept_three_pic_ver_layout;
    }

    public void attachView() {
        ((TextView) ap.a(getRootView(), R.id.title)).setText(this.title);
        LinearLayout linearLayout = (LinearLayout) ap.a(getRootView(), R.id.ll_item_container);
        for (int i = 0; i < this.jsonObjectArrayList.size(); i++) {
            FeedCardSingleView feedCardSingleView;
            if (linearLayout.getChildAt(i) == null) {
                View feedCardSingleView2;
                if (this.feedCardsCacheSA.get(i) == null) {
                    feedCardSingleView2 = new FeedCardSingleView(getEvnetListener().getFromActivity());
                    if (i == 0) {
                        feedCardSingleView2.setTopDividerVisibility(false);
                    } else {
                        feedCardSingleView2.setTopDividerVisibility(true);
                    }
                    this.feedCardsCacheSA.put(i, feedCardSingleView2);
                } else {
                    FeedCardSingleView feedCardSingleView3 = (FeedCardSingleView) this.feedCardsCacheSA.get(i);
                }
                linearLayout.addView(feedCardSingleView2);
                feedCardSingleView = feedCardSingleView2;
            } else {
                feedCardSingleView = (FeedCardSingleView) linearLayout.getChildAt(i);
            }
            feedCardSingleView.a((JSONObject) this.jsonObjectArrayList.get(i), "");
        }
        if (linearLayout.getChildCount() > this.jsonObjectArrayList.size()) {
            for (int size = this.jsonObjectArrayList.size(); size < linearLayout.getChildCount(); size++) {
                linearLayout.removeViewAt(size);
            }
        }
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        try {
            this.title = jSONObject.optString("title");
            if (jSONObject.has("data")) {
                JSONArray optJSONArray = jSONObject.optJSONArray("data");
                if (optJSONArray != null && optJSONArray.length() > 0) {
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                        if (optJSONObject != null) {
                            a aVar = new a();
                            aVar.b = optJSONObject.optString("alg_info");
                            aVar.a = optJSONObject.optString("item_id");
                            optJSONObject = optJSONObject.optJSONObject("ext_info");
                            if (optJSONObject != null) {
                                this.jsonObjectArrayList.add(optJSONObject);
                                this.mBooks.add(aVar);
                            }
                        }
                    }
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    protected void doClick(final View view) {
        int clickIndex = getClickIndex();
        if (view != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                if (((JSONObject) this.jsonObjectArrayList.get(clickIndex)).optString("info_id") != null) {
                    jSONObject.put("ext_info_id", ((JSONObject) this.jsonObjectArrayList.get(clickIndex)).optString("info_id"));
                }
                a aVar = (a) this.mBooks.get(clickIndex);
                if (aVar != null) {
                    jSONObject.put("itemid", aVar.a);
                    jSONObject.put(s.ALG, aVar.b);
                }
                try {
                    c.a(getEvnetListener().getFromActivity(), ((JSONObject) this.jsonObjectArrayList.get(clickIndex)).optString("qurl") + "&statInfo=" + URLEncoder.encode(jSONObject.toString(), "utf-8"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                view.setSelected(true);
                view.postDelayed(new Runnable(this) {
                    final /* synthetic */ Feed3VerBooksGroupCard b;

                    public void run() {
                        view.setSelected(false);
                    }
                }, 100);
                Map hashMap = new HashMap();
                hashMap.put("event_feed_click", getStatString(clickIndex));
                StatisticsManager.a().a("event_feed_click", hashMap);
            } catch (Exception e2) {
            }
        }
    }

    private String getStatString(int i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(((a) this.mBooks.get(i)).a).append(DLConstants.DEPENDENCY_PACKAGE_DIV).append(((a) this.mBooks.get(i)).b).append(DLConstants.DEPENDENCY_PACKAGE_DIV).append(i);
        String optString = ((JSONObject) this.jsonObjectArrayList.get(getClickIndex())).optString("info_id");
        if (TextUtils.isEmpty(optString)) {
            optString = "";
        }
        stringBuilder.append(DLConstants.DEPENDENCY_PACKAGE_DIV).append(optString);
        return stringBuilder.toString();
    }

    public ArrayList<View> getViews() {
        ArrayList<View> arrayList = new ArrayList();
        if (this.jsonObjectArrayList.size() > 0) {
            ViewGroup viewGroup = (ViewGroup) ap.a(getRootView(), R.id.ll_item_container);
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                arrayList.add(viewGroup.getChildAt(i));
            }
        }
        return arrayList;
    }
}
