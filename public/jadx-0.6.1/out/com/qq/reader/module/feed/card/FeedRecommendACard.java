package com.qq.reader.module.feed.card;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.dynamicload.Lib.DLConstants;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import com.tencent.feedback.proguard.R;
import com.tencent.open.SocialConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class FeedRecommendACard extends FeedMultiClickBaseCard {
    private final int LENGTH_ONE = 232;
    private final int LENGTH_TWO = 294;
    private ArrayList<a> bookList = new ArrayList();
    private LinearLayout mLeftLayout;
    private RelativeLayout mRightBottomLayout;
    private RelativeLayout mRightTopLayout;
    private String title;

    public class a {
        public String a;
        public String b;
        public String c;
        public long d;
        public String e;
        public String f;
        public String g;
        public String h;
        public String i;
        final /* synthetic */ FeedRecommendACard j;

        public a(FeedRecommendACard feedRecommendACard) {
            this.j = feedRecommendACard;
        }
    }

    public FeedRecommendACard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.concept_recommend_a_layout;
    }

    public void attachView() {
        TextView textView = (TextView) ap.a(getRootView(), R.id.title);
        ImageView imageView = (ImageView) ap.a(getRootView(), R.id.left_icon);
        TextView textView2 = (TextView) ap.a(getRootView(), R.id.left_title);
        TextView textView3 = (TextView) ap.a(getRootView(), R.id.left_intro);
        ImageView imageView2 = (ImageView) ap.a(getRootView(), R.id.right_top_icon);
        TextView textView4 = (TextView) ap.a(getRootView(), R.id.right_top_title);
        TextView textView5 = (TextView) ap.a(getRootView(), R.id.right_top_intro);
        ImageView imageView3 = (ImageView) ap.a(getRootView(), R.id.right_bottom_icon);
        TextView textView6 = (TextView) ap.a(getRootView(), R.id.right_bottom_title);
        TextView textView7 = (TextView) ap.a(getRootView(), R.id.right_bottom_intro);
        this.mLeftLayout = (LinearLayout) ap.a(getRootView(), R.id.left_layout);
        this.mRightTopLayout = (RelativeLayout) ap.a(getRootView(), R.id.right_top_layout);
        this.mRightBottomLayout = (RelativeLayout) ap.a(getRootView(), R.id.right_bottom_layout);
        if (this.bookList != null && this.bookList.size() >= 3) {
            textView2.setText(((a) this.bookList.get(0)).e);
            textView3.setText(((a) this.bookList.get(0)).i);
            c.a(getEvnetListener().getFromActivity()).a(ao.g(((a) this.bookList.get(0)).d), imageView, com.qq.reader.common.imageloader.a.a().j());
            textView4.setText(((a) this.bookList.get(1)).e);
            textView5.setText(((a) this.bookList.get(1)).i);
            c.a(getEvnetListener().getFromActivity()).a(ao.g(((a) this.bookList.get(1)).d), imageView2, com.qq.reader.common.imageloader.a.a().j());
            textView6.setText(((a) this.bookList.get(2)).e);
            textView7.setText(((a) this.bookList.get(2)).i);
            c.a(getEvnetListener().getFromActivity()).a(ao.g(((a) this.bookList.get(2)).d), imageView3, com.qq.reader.common.imageloader.a.a().j());
            if (!TextUtils.isEmpty(this.title)) {
                textView.setText(this.title);
            }
        }
    }

    public boolean parseData(JSONObject jSONObject) {
        if (jSONObject != null) {
            this.title = jSONObject.optString("title");
            JSONArray optJSONArray = jSONObject.optJSONArray("data");
            if (optJSONArray != null && optJSONArray.length() > 0) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    if (optJSONObject != null) {
                        a aVar = new a(this);
                        aVar.f = optJSONObject.optString("item_id");
                        aVar.g = optJSONObject.optString("alg_info");
                        optJSONObject = optJSONObject.optJSONObject("ext_info");
                        if (optJSONObject != null) {
                            aVar.a = optJSONObject.optString(ComicStoreExclusiveItemCard.NET_AD_BOOK_TITLE);
                            aVar.b = optJSONObject.optString("cover");
                            aVar.c = optJSONObject.optString(ComicStoreExclusiveItemCard.NET_AD_BOOK_INTRO);
                            aVar.e = optJSONObject.optString("title");
                            aVar.d = optJSONObject.optLong("bid");
                            aVar.h = optJSONObject.optString("info_id");
                            aVar.i = optJSONObject.optString(SocialConstants.PARAM_APP_DESC);
                            this.bookList.add(aVar);
                        }
                    }
                }
            }
        }
        return true;
    }

    public boolean isLongClickEnable() {
        return false;
    }

    protected void setImage(ImageView imageView, long j, OnClickListener onClickListener) {
        c.a(getEvnetListener().getFromActivity()).a(ao.g(j), imageView, com.qq.reader.common.imageloader.a.a().j());
        if (onClickListener != null) {
            imageView.setOnClickListener(onClickListener);
        }
    }

    public ArrayList<View> getViews() {
        ArrayList<View> arrayList = new ArrayList();
        arrayList.add(this.mLeftLayout);
        arrayList.add(this.mRightTopLayout);
        arrayList.add(this.mRightBottomLayout);
        return arrayList;
    }

    protected void doClick(final View view) {
        if (view != null) {
            int clickIndex = getClickIndex();
            Bundle bundle = new Bundle();
            bundle.putString("KEY_ACTION", "itembooks");
            bundle.putString("KEY_CARD_ID", ((a) this.bookList.get(clickIndex)).h);
            bundle.putString("KEY_ACTIONID", ((a) this.bookList.get(clickIndex)).h);
            bundle.putString("KEY_ACTIONTAG", String.valueOf(clickIndex));
            bundle.putString("KEY_JUMP_PAGENAME", "feed_column_list_a");
            bundle.putString("LOCAL_STORE_IN_TITLE", ((a) this.bookList.get(clickIndex)).e);
            bundle.putInt("function_type", 0);
            bundle.putString("algInfo", ((a) this.bookList.get(clickIndex)).g);
            bundle.putString("extInfoId", ((a) this.bookList.get(clickIndex)).h);
            new com.qq.reader.module.bookstore.qnative.c(bundle).a(getEvnetListener());
            view.setSelected(true);
            view.postDelayed(new Runnable(this) {
                final /* synthetic */ FeedRecommendACard b;

                public void run() {
                    view.setSelected(false);
                }
            }, 100);
            Map hashMap = new HashMap();
            hashMap.put("event_feed_click", getStatString(clickIndex));
            StatisticsManager.a().a("event_feed_click", hashMap);
        }
    }

    private String getStatString(int i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(((a) this.bookList.get(i)).f).append(DLConstants.DEPENDENCY_PACKAGE_DIV).append(((a) this.bookList.get(i)).g).append(DLConstants.DEPENDENCY_PACKAGE_DIV).append(i);
        String str = ((a) this.bookList.get(i)).h;
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        stringBuilder.append(DLConstants.DEPENDENCY_PACKAGE_DIV).append(str);
        return stringBuilder.toString();
    }
}
