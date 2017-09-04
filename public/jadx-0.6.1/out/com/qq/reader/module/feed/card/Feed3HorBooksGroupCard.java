package com.qq.reader.module.feed.card;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.dynamicload.Lib.DLConstants;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.b;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.tencent.feedback.proguard.R;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class Feed3HorBooksGroupCard extends FeedMultiClickBaseCard {
    private final String JSON_BID;
    private final String JSON_BOOKS;
    private final String JSON_BOOK_AUTHOR;
    private final String JSON_BOOK_ICONURL;
    private final String JSON_BOOK_ID;
    private final String JSON_BOOK_INDEX;
    private final String JSON_BOOK_QURL;
    private final String JSON_BOOK_SCORE;
    private final String JSON_BOOK_TITLE;
    private final String JSON_SLOGAN;
    private String bid;
    private int[] bookContainerIds;
    private int eachSpace;
    private int imageHeight;
    private List<a> mBooks;
    private String slogan;
    private String title;
    private ArrayList<View> views;

    private class a {
        String a;
        long b;
        String c;
        String d;
        String e;
        String f;
        String g;
        String h;
        String i;
        final /* synthetic */ Feed3HorBooksGroupCard j;

        private a(Feed3HorBooksGroupCard feed3HorBooksGroupCard) {
            this.j = feed3HorBooksGroupCard;
            this.g = "";
            this.h = "";
            this.i = "";
        }

        public void a() {
        }
    }

    private void buildTestData() {
        if (b.a) {
            this.title = "测试用数据";
            for (int i = 0; i < 3; i++) {
                a aVar = new a();
                aVar.a();
                this.mBooks.add(aVar);
            }
        }
    }

    public Feed3HorBooksGroupCard(com.qq.reader.module.bookstore.qnative.page.b bVar, String str) {
        super(bVar, str);
        this.JSON_BID = "bid";
        this.JSON_BOOKS = "books";
        this.JSON_SLOGAN = "slogan";
        this.JSON_BOOK_ID = "bid";
        this.JSON_BOOK_TITLE = "title";
        this.JSON_BOOK_AUTHOR = "author";
        this.JSON_BOOK_ICONURL = "iconUrl";
        this.JSON_BOOK_SCORE = "score";
        this.JSON_BOOK_QURL = "Qurl";
        this.JSON_BOOK_INDEX = "index";
        this.views = new ArrayList();
        this.bookContainerIds = new int[]{R.id.ll_left, R.id.ll_center, R.id.ll_right};
        this.eachSpace = 0;
        this.mBooks = new ArrayList();
        this.isClickEnable = false;
    }

    public int getResLayoutId() {
        return R.layout.concept_three_pic_layout;
    }

    public void attachView() {
        ((TextView) ap.a(getRootView(), R.id.title)).setText(this.title);
        ViewGroup viewGroup = (ViewGroup) ap.a(getRootView(), R.id.ll_container);
        if (this.views.size() > 0) {
            this.views.clear();
        }
        int i = 0;
        while (i < 3) {
            a aVar;
            if (this.mBooks.size() <= 0 || this.mBooks.size() <= i) {
                aVar = null;
            } else {
                aVar = (a) this.mBooks.get(i);
            }
            ViewGroup viewGroup2 = (ViewGroup) ap.a(viewGroup, this.bookContainerIds[i]);
            this.views.add(viewGroup2);
            ImageView imageView = (ImageView) ap.a(viewGroup2, R.id.iv_cover);
            TextView textView = (TextView) ap.a(viewGroup2, R.id.tv_bk_name);
            TextView textView2 = (TextView) ap.a(viewGroup2, R.id.tv_bk_des);
            TextView textView3 = (TextView) ap.a(viewGroup2, R.id.feed_books_score);
            if (aVar != null) {
                viewGroup2.setVisibility(0);
                c.a(getEvnetListener().getFromActivity()).a(ao.g(aVar.b), imageView, com.qq.reader.common.imageloader.a.a().j());
                textView.setText(aVar.a);
                textView2.setText(aVar.c);
                viewGroup2.setTag(R.string.obj_tag, aVar);
                textView3.setText(aVar.f);
                textView3.setVisibility(TextUtils.isEmpty(aVar.f) ? 8 : 0);
                viewGroup2.getLayoutParams();
            } else {
                viewGroup2.setVisibility(8);
            }
            i++;
        }
    }

    private int computeSpace() {
        if (this.eachSpace != 0) {
            return this.eachSpace;
        }
        this.eachSpace = ((ReaderApplication.getApplicationImp().getResources().getDisplayMetrics().widthPixels - (ao.a(ReaderApplication.getApplicationImp().getResources().getDimension(R.dimen.feed_3books_left_right_margin)) * 2)) - (ao.a(ReaderApplication.getApplicationImp().getResources().getDimension(R.dimen.feed_3books_img_width)) * 3)) / 4;
        return this.eachSpace;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        if (jSONObject == null) {
            return false;
        }
        try {
            this.title = jSONObject.optString("title");
            JSONArray optJSONArray = jSONObject.optJSONArray("data");
            if (optJSONArray != null && optJSONArray.length() > 0) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    a aVar = new a();
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    if (optJSONObject != null) {
                        aVar.g = optJSONObject.optString("item_id");
                        aVar.h = optJSONObject.optString("alg_info");
                        if (optJSONObject.has("ext_info")) {
                            optJSONObject = optJSONObject.optJSONObject("ext_info");
                            aVar.c = optJSONObject.optString("author");
                            aVar.d = optJSONObject.optString("qurl");
                            aVar.a = optJSONObject.optString("showTitle");
                            aVar.e = optJSONObject.optString("cover");
                            aVar.b = optJSONObject.optLong("bid");
                            aVar.f = optJSONObject.optString("score");
                            aVar.i = optJSONObject.optString("info_id");
                            this.mBooks.add(aVar);
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
        if (view != null) {
            Object tag = getLastPressedView().getTag(R.string.obj_tag);
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("itemid", ((a) this.mBooks.get(getClickIndex())).g);
                jSONObject.put(s.ALG, ((a) this.mBooks.get(getClickIndex())).h);
                jSONObject.put("ext_info_id", ((a) this.mBooks.get(getClickIndex())).i);
                if (!(tag == null || !(tag instanceof a) || ((a) tag).d == null)) {
                    try {
                        com.qq.reader.qurl.c.a(getEvnetListener().getFromActivity(), ((a) tag).d + "&statInfo=" + URLEncoder.encode(jSONObject.toString(), "utf-8"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                view.setSelected(true);
                view.postDelayed(new Runnable(this) {
                    final /* synthetic */ Feed3HorBooksGroupCard b;

                    public void run() {
                        view.setSelected(false);
                    }
                }, 100);
                Map hashMap = new HashMap();
                hashMap.put("event_feed_click", getStatString(getClickIndex()));
                StatisticsManager.a().a("event_feed_click", hashMap);
            } catch (Exception e2) {
            }
        }
    }

    private String getStatString(int i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(((a) this.mBooks.get(i)).g).append(DLConstants.DEPENDENCY_PACKAGE_DIV).append(((a) this.mBooks.get(i)).h).append(DLConstants.DEPENDENCY_PACKAGE_DIV).append(i);
        String str = ((a) this.mBooks.get(i)).i;
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        stringBuilder.append(DLConstants.DEPENDENCY_PACKAGE_DIV).append(str);
        return stringBuilder.toString();
    }

    public ArrayList<View> getViews() {
        return this.views;
    }
}
