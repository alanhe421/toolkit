package com.qq.reader.module.bookstore.qnative.card.impl;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.j;
import com.qq.reader.common.utils.k;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class ArticleCardForAuthorTimeLine extends a implements a {
    private String authorIcon;
    private long authorId;
    private String authorName;
    private String content;
    private String imgUrl;
    private String jumpurl;
    private boolean mIsShowDivider = true;
    private String newsId;
    private long publishTime;
    private long readTimes;
    private String title;

    public ArticleCardForAuthorTimeLine(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.author_time_line_article_card;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        if (jSONObject != null) {
            this.imgUrl = jSONObject.optString("imageUrl");
            this.newsId = jSONObject.optString("newsId");
            this.publishTime = jSONObject.optLong("publishTime");
            this.authorId = jSONObject.optLong("centerId");
            this.content = jSONObject.optString(MessageKey.MSG_CONTENT);
            this.title = jSONObject.optString("title");
            this.readTimes = jSONObject.optLong("readTimes");
            this.jumpurl = jSONObject.optString("articleUrl");
            this.authorName = jSONObject.optString("authorName");
            this.authorIcon = jSONObject.optString("authorIcon");
        }
        return true;
    }

    public void setShowDivider(Boolean bool) {
        this.mIsShowDivider = bool.booleanValue();
    }

    public void attachView() {
        i.a("event_F74", null, ReaderApplication.getApplicationImp());
        View rootView = getRootView();
        ImageView imageView = (ImageView) ap.a(rootView, R.id.icon);
        View a = ap.a(rootView, R.id.icon_mask);
        c.a(getEvnetListener().getFromActivity()).a(this.authorIcon, imageView, com.qq.reader.common.imageloader.a.a().f());
        a.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ArticleCardForAuthorTimeLine a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                i.a("event_F75", null, ReaderApplication.getApplicationImp());
                o.c(this.a.getEvnetListener().getFromActivity(), String.valueOf(this.a.authorId), this.a.authorName, this.a.authorIcon, null);
            }
        });
        ((TextView) ap.a(rootView, R.id.article_card_name)).setText(this.authorName);
        rootView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ArticleCardForAuthorTimeLine a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                try {
                    com.qq.reader.qurl.c.a(this.a.getEvnetListener().getFromActivity(), this.a.jumpurl, null);
                    i.a("event_F75", null, ReaderApplication.getApplicationImp());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        imageView = (ImageView) ap.a(rootView, R.id.img_news_cover);
        TextView textView = (TextView) ap.a(rootView, R.id.tv_title);
        TextView textView2 = (TextView) ap.a(rootView, R.id.article_card_content);
        TextView textView3 = (TextView) ap.a(rootView, R.id.article_card_time);
        TextView textView4 = (TextView) ap.a(rootView, R.id.article_card_read_count);
        c.a(getEvnetListener().getFromActivity()).a(this.imgUrl, imageView, com.qq.reader.common.imageloader.a.a().j());
        if (TextUtils.isEmpty(this.content)) {
            textView2.setVisibility(8);
        } else {
            textView2.setText(this.content);
            textView2.setVisibility(0);
        }
        textView.setText(this.title);
        if (this.readTimes > 0) {
            textView4.setText("阅读" + j.a(this.readTimes));
            textView4.setVisibility(0);
        } else {
            textView4.setVisibility(8);
        }
        textView3.setText(k.c(this.publishTime));
        View a2 = ap.a(getRootView(), R.id.card_divider);
        if (this.mIsShowDivider) {
            a2.setVisibility(0);
        } else {
            a2.setVisibility(8);
        }
    }
}
