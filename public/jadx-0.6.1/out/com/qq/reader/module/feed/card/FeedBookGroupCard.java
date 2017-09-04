package com.qq.reader.module.feed.card;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.feed.data.impl.FeedBaseCard;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class FeedBookGroupCard extends FeedBaseCard {
    public static String FEED_TYPE_RANK = "feed_type_rank";
    private static final String JSON_KEY_BID = "bid";
    private static final String JSON_KEY_BOOKS = "books";
    private static final String JSON_KEY_DESC = "desc";
    private static final String JSON_KEY_INDEX = "index";
    private static final String JSON_KEY_INFO = "info";
    private static final String JSON_KEY_TAGS = "tags";
    private static final String JSON_KEY_TITLE = "title";
    private int[] imgCoverResIds = new int[]{R.id.img_top1, R.id.img_top2, R.id.img_top3, R.id.img_top4};
    private int[] imgTopMarkerResIds = new int[]{R.id.img_top1_marker, R.id.img_top2_marker, R.id.img_top3_marker};
    private ArrayList<a> mBookList = new ArrayList();
    private int mBookNum;
    private JSONArray mBooks;
    private String mDesc;
    private String mTags;

    class a {
        public int a;
        public int b;
        public String c;
        final /* synthetic */ FeedBookGroupCard d;

        a(FeedBookGroupCard feedBookGroupCard) {
            this.d = feedBookGroupCard;
        }
    }

    public FeedBookGroupCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.concept_hot_rank_layout;
    }

    public void attachView() {
        View rootView = getRootView();
        TextView textView = (TextView) ap.a(rootView, R.id.concept_title);
        textView.setText(this.mTitle);
        if (isClickedStatus()) {
            textView.setSelected(true);
        } else {
            textView.setSelected(false);
        }
        ((TextView) ap.a(rootView, R.id.concept_content)).setText(this.mDesc);
        if (this.mBookList != null && this.mBookList.size() > 0 && this.mBookList.size() <= this.imgCoverResIds.length) {
            for (int i = 0; i < this.mBookList.size(); i++) {
                ImageView imageView = (ImageView) ap.a(rootView, this.imgCoverResIds[i]);
                c.a(getEvnetListener().getFromActivity()).a(ao.g((long) ((a) this.mBookList.get(i)).a), imageView, com.qq.reader.common.imageloader.a.a().j());
            }
        }
        String type = getType();
        if (type == null || !FEED_TYPE_RANK.equals(type)) {
            showTopMarker(false);
        } else {
            showTopMarker(true);
        }
        checkClickEnable();
    }

    public void doClickedCard() {
        super.doClickedCard();
        ap.a(getRootView(), R.id.concept_title).setSelected(true);
    }

    public boolean parseData(JSONObject jSONObject) {
        this.mDesc = jSONObject.optString("desc");
        this.mBooks = jSONObject.optJSONArray(JSON_KEY_BOOKS);
        this.mTags = jSONObject.optString(JSON_KEY_TAGS);
        this.mTitle = jSONObject.optString("title");
        this.mBookNum = this.mBooks.length();
        for (int i = 0; i < this.mBookNum; i++) {
            a aVar = new a(this);
            aVar.a = this.mBooks.optJSONObject(i).optInt("bid");
            aVar.b = this.mBooks.optJSONObject(i).optInt(JSON_KEY_INDEX);
            aVar.c = this.mBooks.optJSONObject(i).optString("title");
            this.mBookList.add(aVar);
        }
        return true;
    }

    private void showTopMarker(boolean z) {
        View rootView = getRootView();
        for (int a : this.imgTopMarkerResIds) {
            ImageView imageView = (ImageView) ap.a(rootView, a);
            if (z) {
                imageView.setVisibility(0);
            } else {
                imageView.setVisibility(8);
            }
        }
    }

    public String getDesc() {
        return this.mDesc;
    }

    public String getTag() {
        return this.mTags;
    }

    public ArrayList<a> getmBookList() {
        return this.mBookList;
    }
}
