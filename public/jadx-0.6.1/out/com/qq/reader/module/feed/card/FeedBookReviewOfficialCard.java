package com.qq.reader.module.feed.card;

import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import com.qq.reader.common.imageloader.a;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class FeedBookReviewOfficialCard extends FeedBookReviewCard {
    private static final String JSON_KEY_BOOK_ICON = "bookicon";
    private String mBookIcon;

    public FeedBookReviewOfficialCard(b bVar, String str) {
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
        imageView2.setBackgroundDrawable(null);
        imageView2.setScaleType(ScaleType.FIT_CENTER);
        c.a(getEvnetListener().getFromActivity()).a(this.mBookIcon, imageView2, a.a().j());
        c.a(getEvnetListener().getFromActivity()).a(this.mAuthorIcon, imageView, a.a().j());
        addQuote(textView, this.mContent);
        checkClickEnable();
    }

    public boolean parseData(JSONObject jSONObject) {
        super.parseData(jSONObject);
        this.mBookIcon = jSONObject.optString(JSON_KEY_BOOK_ICON);
        return true;
    }
}
