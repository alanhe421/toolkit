package com.qq.reader.module.feed.card;

import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.imageloader.a;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.feed.data.impl.FeedBaseCard;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class FeedBookReviewCard extends FeedBaseCard {
    private static final String JSON_KEY_AUTHOR = "author";
    private static final String JSON_KEY_AUTHOR_ICON = "authorIcon";
    private static final String JSON_KEY_BID = "bid";
    private static final String JSON_KEY_BOOKTITLE = "booktilte";
    private static final String JSON_KEY_CONTENT = "content";
    private static final String JSON_KEY_TITLE = "title";
    private final int INTRO_MAX_WORDS_LENGTH = 60;
    protected String mAuthor;
    protected String mAuthorIcon;
    protected int mBid;
    protected String mBookTitle;
    protected String mContent;

    public FeedBookReviewCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.concept_card_comment_layout;
    }

    public void attachView() {
        View rootView = getRootView();
        TextView textView = (TextView) ap.a(rootView, R.id.tv_book_intro);
        ImageView imageView = (ImageView) ap.a(rootView, R.id.img_author_avatar);
        TextView textView2 = (TextView) ap.a(rootView, R.id.tv_author_nickname);
        TextView textView3 = (TextView) ap.a(rootView, R.id.tv_book_name);
        ImageView imageView2 = (ImageView) ap.a(rootView, R.id.img_book_cover);
        textView3.setText(this.mBookTitle);
        if (this.mAuthor != null) {
            this.mAuthor = this.mAuthor.trim();
        }
        textView2.setText(this.mAuthor);
        c.a(getEvnetListener().getFromActivity()).a(ao.g((long) this.mBid), imageView2, a.a().j());
        c.a(getEvnetListener().getFromActivity()).a(this.mAuthorIcon, imageView, a.a().j());
        addQuote(textView, this.mContent);
        checkClickEnable();
    }

    protected void addQuote(TextView textView, String str) {
        if (str.length() > 60) {
            str = str.substring(0, 50) + "...";
        }
        String str2 = "\" " + str.toString() + " \"";
        CharSequence spannableString = new SpannableString(str2);
        Drawable drawable = ReaderApplication.getApplicationImp().getResources().getDrawable(R.drawable.card_quote_start);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        com.qq.reader.common.emotion.b.b bVar = new com.qq.reader.common.emotion.b.b(drawable);
        drawable = ReaderApplication.getApplicationImp().getResources().getDrawable(R.drawable.card_quote_end);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        spannableString.setSpan(new com.qq.reader.common.emotion.b.b(drawable), str2.length() - 1, str2.length(), 33);
        spannableString.setSpan(bVar, 0, 1, 33);
        textView.setText(spannableString);
    }

    public boolean parseData(JSONObject jSONObject) {
        this.mTitle = jSONObject.optString("title");
        this.mContent = jSONObject.optString("content");
        this.mAuthor = jSONObject.optString(JSON_KEY_AUTHOR);
        this.mBookTitle = jSONObject.optString(JSON_KEY_BOOKTITLE);
        this.mBid = jSONObject.optInt("bid");
        this.mAuthorIcon = jSONObject.optString(JSON_KEY_AUTHOR_ICON);
        if (!TextUtils.isEmpty(this.mContent)) {
            this.mContent = this.mContent.replaceAll(" ", "");
        }
        return true;
    }

    public String getContent() {
        return this.mContent;
    }

    public String getAuthor() {
        return this.mAuthor;
    }

    public String getBookTitle() {
        return this.mBookTitle;
    }
}
