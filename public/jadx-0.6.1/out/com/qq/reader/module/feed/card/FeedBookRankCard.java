package com.qq.reader.module.feed.card;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class FeedBookRankCard extends FeedMultiClickBaseCard {
    private static final String JSON_KEY_AUTHOR = "author";
    private static final String JSON_KEY_BID = "bid";
    private static final String JSON_KEY_BOOKS = "books";
    private static final String JSON_KEY_DESC = "desc";
    private static final String JSON_KEY_INDEX = "index";
    private static final String JSON_KEY_INFO = "info";
    private static final String JSON_KEY_QURL = "qurl";
    private static final String JSON_KEY_TAGS = "tags";
    private static final String JSON_KEY_TITLE = "title";
    private int[] bookLayoutResIds;
    private int[] clickableResIds;
    float currentX;
    float currentY;
    private int[] imgCoverResIds;
    private int[] imgTopMarkerResIds;
    private ArrayList<a> mBookList;
    private int mBookNum;
    private JSONArray mBooks;
    private String mDesc;
    private String mQurl;
    private String mTags;
    private String more_view_tag;
    private int[] tvBookAuthorResIds;
    private int[] tvBookNameResIds;

    class a {
        public int a;
        public int b;
        public String c;
        public String d;
        final /* synthetic */ FeedBookRankCard e;

        a(FeedBookRankCard feedBookRankCard) {
            this.e = feedBookRankCard;
        }
    }

    public FeedBookRankCard(b bVar, String str) {
        super(bVar, str);
        this.mBookList = new ArrayList();
        this.imgCoverResIds = new int[]{R.id.img_top1, R.id.img_top2, R.id.img_top3, R.id.img_top4};
        this.imgTopMarkerResIds = new int[]{R.id.img_top1_marker, R.id.img_top2_marker, R.id.img_top3_marker, R.id.img_top4_marker};
        this.tvBookNameResIds = new int[]{R.id.tv_bookname1, R.id.tv_bookname2, R.id.tv_bookname3, R.id.tv_bookname4};
        this.tvBookAuthorResIds = new int[]{R.id.tv_author1, R.id.tv_author2, R.id.tv_author3, R.id.tv_author4};
        this.clickableResIds = new int[]{R.id.ll_book_1, R.id.ll_book_2, R.id.ll_book_3, R.id.ll_book_4, R.id.more_rank};
        this.bookLayoutResIds = new int[]{R.id.ll_book_1, R.id.ll_book_2, R.id.ll_book_3, R.id.ll_book_4};
        this.more_view_tag = "more_view_tag";
        this.currentX = 0.0f;
        this.currentY = 0.0f;
        this.isClickEnable = false;
    }

    public int getResLayoutId() {
        return R.layout.concept_hot_rank_layout_3_books;
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
        if (this.mBookList != null && this.mBookList.size() > 0) {
            int size;
            if (this.mBookList.size() <= this.imgCoverResIds.length) {
                size = this.mBookList.size();
            } else {
                size = this.imgCoverResIds.length;
            }
            for (int i = 0; i < size; i++) {
                a aVar = (a) this.mBookList.get(i);
                ap.a(rootView, this.bookLayoutResIds[i]).setTag(R.string.obj_tag, aVar);
                ImageView imageView = (ImageView) ap.a(rootView, this.imgCoverResIds[i]);
                c.a(getEvnetListener().getFromActivity()).a(ao.g((long) aVar.a), imageView, com.qq.reader.common.imageloader.a.a().j());
                TextView textView2 = (TextView) ap.a(rootView, this.tvBookAuthorResIds[i]);
                ((TextView) ap.a(rootView, this.tvBookNameResIds[i])).setText(aVar.c);
                textView2.setText(aVar.d);
            }
            textView = (TextView) ap.a(rootView, R.id.more_rank);
            textView.setTag(R.string.obj_tag, this.more_view_tag);
            textView.setText("查看完整榜单");
        }
        showTopMarker(true);
        checkClickEnable();
        i.a("event_C251", null, ReaderApplication.getApplicationImp());
    }

    public void doOnFeedClicked(boolean z) {
    }

    public void doClickedCard() {
    }

    protected void checkClickEnable() {
        if (this.isClickEnable) {
            View a = ap.a(getRootView(), R.id.more_rank);
            if (a != null) {
                a.setBackgroundResource(R.drawable.concept_card_bg_selector_nonormal);
                a.setClickable(true);
                a.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ FeedBookRankCard a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        this.a.doMoreViewClick();
                    }
                });
            }
            for (int a2 : this.bookLayoutResIds) {
                View a3 = ap.a(getRootView(), a2);
                if (a3 != null) {
                    a3.setBackgroundResource(R.drawable.concept_card_bg_selector_nonormal);
                    final a aVar = (a) a3.getTag(R.string.obj_tag);
                    if (aVar != null) {
                        a3.setOnClickListener(new OnClickListener(this) {
                            final /* synthetic */ FeedBookRankCard b;

                            public void onClick(View view) {
                                Object valueOf = String.valueOf(aVar.a);
                                if (!TextUtils.isEmpty(valueOf)) {
                                    try {
                                        com.qq.reader.qurl.c.a(this.b.getEvnetListener().getFromActivity(), "uniteqqreader://nativepage/book/detail?bid=" + valueOf);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        });
                    }
                }
            }
        }
    }

    public boolean parseData(JSONObject jSONObject) {
        this.mDesc = jSONObject.optString("desc");
        this.mBooks = jSONObject.optJSONArray(JSON_KEY_BOOKS);
        this.mTags = jSONObject.optString(JSON_KEY_TAGS);
        this.mTitle = jSONObject.optString("title");
        this.mQurl = jSONObject.optString(JSON_KEY_QURL);
        this.mBookNum = this.mBooks.length();
        for (int i = 0; i < this.mBookNum; i++) {
            a aVar = new a(this);
            aVar.a = this.mBooks.optJSONObject(i).optInt("bid");
            aVar.b = this.mBooks.optJSONObject(i).optInt(JSON_KEY_INDEX);
            aVar.c = this.mBooks.optJSONObject(i).optString("title");
            aVar.d = this.mBooks.optJSONObject(i).optString(JSON_KEY_AUTHOR);
            this.mBookList.add(aVar);
        }
        return true;
    }

    private void doMoreViewClick() {
        super.doOnFeedClicked(true);
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

    public ArrayList<View> getViews() {
        ArrayList<View> arrayList = new ArrayList();
        for (int a : this.clickableResIds) {
            arrayList.add(ap.a(getRootView(), a));
        }
        return arrayList;
    }

    protected void doClick(final View view) {
        if (view != null) {
            Object tag = getLastPressedView().getTag(R.string.obj_tag);
            if (tag != null) {
                if (tag instanceof a) {
                    tag = String.valueOf(((a) tag).a);
                    if (!TextUtils.isEmpty(tag)) {
                        try {
                            com.qq.reader.qurl.c.a(getEvnetListener().getFromActivity(), "uniteqqreader://nativepage/book/detail?bid=" + tag);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    i.a("event_C252", null, ReaderApplication.getApplicationImp());
                } else if (tag instanceof String) {
                    if (this.more_view_tag.equals((String) tag)) {
                        super.doOnFeedClicked(true);
                    }
                    i.a("event_C253", null, ReaderApplication.getApplicationImp());
                }
            }
            view.setSelected(true);
            view.postDelayed(new Runnable(this) {
                final /* synthetic */ FeedBookRankCard b;

                public void run() {
                    view.setSelected(false);
                }
            }, 100);
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
