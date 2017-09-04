package com.qq.reader.module.bookstore.qnative.card.impl;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.imageloader.a;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.card.BaseCommentCard;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class SelectedCommentBookInfoCard extends BaseCommentCard {
    private long bid = -1;
    private String mAuthorValue = null;
    private String mCategoryValue = null;
    private String mQurl;
    private float mScore;
    private String mScoreText;
    private Bundle stasticsBundle = null;
    private String title = null;

    public SelectedCommentBookInfoCard(b bVar, String str, int i) {
        super(bVar, str, i);
        setCardId(str);
    }

    public SelectedCommentBookInfoCard(b bVar, String str, Bundle bundle, int i) {
        super(bVar, str, i);
        setCardId(str);
        this.stasticsBundle = bundle;
    }

    public int getResLayoutId() {
        return R.layout.localstore_card_selected_comment_bookinfo;
    }

    public boolean parseData(JSONObject jSONObject) throws Exception {
        if (jSONObject == null) {
            return false;
        }
        this.title = jSONObject.optString("title");
        this.mCategoryValue = jSONObject.optString(ComicStoreExclusiveItemCard.NET_AD_ATTR_CATE);
        this.mAuthorValue = jSONObject.optString("author");
        this.bid = jSONObject.optLong("bid");
        this.mQurl = jSONObject.optString("qurl");
        JSONObject optJSONObject = jSONObject.optJSONObject("scoreInfo");
        if (optJSONObject != null) {
            try {
                this.mScore = Float.valueOf(optJSONObject.optString("score", "0")).floatValue();
            } catch (Exception e) {
                c.e("DetailPageBookItem", e.getMessage());
            }
            this.mScoreText = optJSONObject.optString("scoretext");
        }
        return true;
    }

    public long getBookId() {
        return this.bid;
    }

    public void attachView() {
        if (this.bid > 0) {
            String h;
            ap.a(getRootView(), R.id.bookinfo_view).setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ SelectedCommentBookInfoCard a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    if (TextUtils.isEmpty(this.a.mQurl)) {
                        ao.a((Activity) this.a.getEvnetListener(), this.a.bid, this.a.stasticsBundle);
                    } else {
                        try {
                            com.qq.reader.qurl.c.a((Activity) this.a.getEvnetListener(), this.a.mQurl, null);
                        } catch (Exception e) {
                        }
                    }
                    i.a("event_C73", null, ReaderApplication.getApplicationImp());
                }
            });
            ImageView imageView = (ImageView) ap.a(getRootView(), R.id.bookinfo_cover);
            if (getCtype() == 9) {
                h = ao.h(this.bid);
            } else {
                h = ao.g(this.bid);
            }
            com.qq.reader.common.imageloader.c.a(getEvnetListener().getFromActivity()).a(h, imageView, a.a().j());
            ((TextView) ap.a(getRootView(), R.id.bookinfo_name)).setText(this.title);
            View a = ap.a(getRootView(), R.id.bookinfo_ratinglayout);
            if (getCtype() == 9) {
                a.setVisibility(8);
            } else {
                a.setVisibility(0);
            }
            ((RatingBar) ap.a(getRootView(), R.id.bookinfo_ratingbar)).setRating(this.mScore);
            TextView textView = (TextView) ap.a(getRootView(), R.id.bookinfo_ratingbar_text);
            if (this.mScore > 0.0f) {
                textView.setText(this.mScoreText + "分");
            } else {
                textView.setText("");
            }
            ((TextView) ap.a(getRootView(), R.id.bookinfo_type)).setText(this.mCategoryValue);
            ((TextView) ap.a(getRootView(), R.id.bookinfo_author)).setText(this.mAuthorValue);
            ((TextView) ap.a(getRootView(), R.id.bookinfo_read)).setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ SelectedCommentBookInfoCard a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    if (this.a.getCtype() == 9) {
                        o.a(this.a.getEvnetListener().getFromActivity(), String.valueOf(this.a.bid), "", 0, "", true);
                    } else {
                        o.a(this.a.getEvnetListener().getFromActivity(), String.valueOf(this.a.bid), -1, -1, null);
                    }
                    i.a("event_C74", null, ReaderApplication.getApplicationImp());
                }
            });
        }
    }
}
