package com.qq.reader.module.feed.card;

import android.graphics.RectF;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.b;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.feed.data.impl.FeedBaseCard;
import com.qq.reader.module.feed.data.impl.h;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class Feed3BooksGroupCard extends FeedBaseCard implements h {
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
    float currentX;
    float currentY;
    private long downStart;
    float downX;
    float downY;
    private int eachSpace;
    private int imageHeight;
    private View lastPressed;
    private List<a> mBooks;
    private int sMoveEdge;
    private String slogan;
    private String title;

    private class a {
        String a;
        long b;
        String c;
        String d;
        String e;
        int f;
        final /* synthetic */ Feed3BooksGroupCard g;

        private a(Feed3BooksGroupCard feed3BooksGroupCard) {
            this.g = feed3BooksGroupCard;
        }

        public void a() {
            this.a = "菊花怪大战鞠华霞之武林至尊(二)";
            this.b = 389704;
            this.c = "死神阿呆";
            this.e = "9";
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

    public Feed3BooksGroupCard(com.qq.reader.module.bookstore.qnative.page.b bVar, String str) {
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
        this.bookContainerIds = new int[]{R.id.ll_left, R.id.ll_center, R.id.ll_right};
        this.eachSpace = 0;
        this.downX = 0.0f;
        this.downY = 0.0f;
        this.currentX = 0.0f;
        this.currentY = 0.0f;
        this.sMoveEdge = 10;
        this.mBooks = new ArrayList();
        this.isClickEnable = false;
    }

    public int getResLayoutId() {
        return R.layout.concept_three_pic_layout;
    }

    public void attachView() {
        ((TextView) ap.a(getRootView(), R.id.title)).setText(this.title);
        ViewGroup viewGroup = (ViewGroup) ap.a(getRootView(), R.id.ll_container);
        int i = 0;
        while (i < 3) {
            a aVar;
            if (this.mBooks.size() <= 0 || this.mBooks.size() <= i) {
                aVar = null;
            } else {
                aVar = (a) this.mBooks.get(i);
            }
            ViewGroup viewGroup2 = (ViewGroup) ap.a(viewGroup, this.bookContainerIds[i]);
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
                textView3.setText(aVar.e);
                textView3.setVisibility(TextUtils.isEmpty(aVar.e) ? 8 : 0);
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
        try {
            this.bid = jSONObject.optString("bid");
            this.slogan = jSONObject.optString("slogan");
            JSONArray optJSONArray = jSONObject.optJSONArray("books");
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject jSONObject2 = (JSONObject) optJSONArray.get(i);
                a aVar = new a();
                aVar.b = jSONObject2.optLong("bid");
                aVar.c = jSONObject2.optString("author");
                aVar.a = jSONObject2.optString("title");
                aVar.d = jSONObject2.optString("Qurl");
                aVar.f = jSONObject2.optInt("index");
                aVar.e = jSONObject2.optString("score");
                this.mBooks.add(aVar);
            }
            this.title = jSONObject.optString("slogan");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private View getPressedView(MotionEvent motionEvent) {
        float rawX = motionEvent.getRawX();
        float rawY = motionEvent.getRawY();
        RectF rectF = new RectF();
        ViewGroup viewGroup = (ViewGroup) ap.a(getRootView(), R.id.ll_container);
        int childCount = viewGroup.getChildCount();
        int[] iArr = new int[2];
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            childAt.getLocationOnScreen(iArr);
            rectF.set((float) iArr[0], (float) iArr[1], (float) (childAt.getWidth() + iArr[0]), (float) (childAt.getHeight() + iArr[1]));
            if (rectF.contains(rawX, rawY)) {
                return childAt;
            }
        }
        return null;
    }

    public void onDown(MotionEvent motionEvent) {
        if (motionEvent != null) {
            this.lastPressed = getPressedView(motionEvent);
            this.downX = motionEvent.getRawX();
            this.downY = motionEvent.getRawY();
            this.currentX = this.downX;
            this.currentY = this.downY;
            if (this.lastPressed != null) {
                this.downStart = System.currentTimeMillis();
                this.lastPressed.postDelayed(new Runnable(this) {
                    final /* synthetic */ Feed3BooksGroupCard a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        if (!this.a.isMoveOut() && this.a.lastPressed != null) {
                            this.a.lastPressed.setSelected(true);
                        }
                    }
                }, (long) ViewConfiguration.getTapTimeout());
            }
        }
    }

    private boolean isMoveOut() {
        return Math.abs(this.downX - this.currentX) > ((float) this.sMoveEdge) || Math.abs(this.downY - this.currentY) > ((float) this.sMoveEdge);
    }

    public void onMove(MotionEvent motionEvent) {
        if (motionEvent != null) {
            this.currentX = motionEvent.getRawX();
            this.currentY = motionEvent.getRawY();
            if (this.lastPressed != null && outOffset(motionEvent)) {
                this.lastPressed.setSelected(false);
                this.lastPressed = null;
            }
        }
    }

    private boolean outOffset(MotionEvent motionEvent) {
        float rawX = motionEvent.getRawX();
        float rawY = motionEvent.getRawY();
        if (Math.abs(rawX - this.downX) > ((float) this.sMoveEdge) || Math.abs(rawY - this.downY) > ((float) this.sMoveEdge)) {
            return true;
        }
        return false;
    }

    public void onUpOrCancel(MotionEvent motionEvent) {
        if (this.lastPressed != null) {
            boolean z;
            if (System.currentTimeMillis() - this.downStart > 500 || motionEvent == null) {
                z = false;
            } else {
                doClick(this.lastPressed);
                z = true;
            }
            if (!z) {
                this.lastPressed.setSelected(false);
            }
            this.lastPressed = null;
        }
    }

    private void doClick(final View view) {
        if (view != null) {
            Object tag = view.getTag(R.string.obj_tag);
            if (!(tag == null || !(tag instanceof a) || ((a) tag).d == null)) {
                try {
                    com.qq.reader.qurl.c.a(getEvnetListener().getFromActivity(), ((a) tag).d);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            view.setSelected(true);
            view.postDelayed(new Runnable(this) {
                final /* synthetic */ Feed3BooksGroupCard b;

                public void run() {
                    view.setSelected(false);
                }
            }, 100);
        }
    }
}
