package com.qq.reader.module.bookstore.search.card;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.imageloader.a;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.j;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class SearchAuthorDirectzoneCard extends SearchBaseCard {
    private static final String JSON_KEY_COMMENTS_COUNT = "commentsCount";
    private static final String JSON_KEY_COVER = "cover";
    private static final String JSON_KEY_DIRECT_INTRO = "desc";
    private static final String JSON_KEY_FANS_COUNT = "fansCount";
    private static final String JSON_KEY_LEVEL = "level";
    private static final String JSON_KEY_TITLE = "title";
    public long mCommentsCount;
    public String mDirectDesc;
    public long mFansCount;
    public String mImageUrl;
    public int mLevel;
    public String mTitle;

    public SearchAuthorDirectzoneCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.search_author_card;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        super.parseData(jSONObject);
        this.mQURL = jSONObject.optString("qurl");
        this.mTitle = jSONObject.optString("title");
        this.mDirectDesc = jSONObject.optString("desc");
        if (this.mDirectDesc != null) {
            this.mDirectDesc = this.mDirectDesc.trim();
        }
        this.mImageUrl = jSONObject.optString(JSON_KEY_COVER);
        this.mCommentsCount = jSONObject.optLong(JSON_KEY_COMMENTS_COUNT, 0);
        this.mFansCount = jSONObject.optLong(JSON_KEY_FANS_COUNT, 0);
        this.mLevel = jSONObject.optInt(JSON_KEY_LEVEL, 0);
        return true;
    }

    public void doClickedCard() {
        super.doClickedCard();
        Map hashMap = new HashMap();
        hashMap.put(s.ORIGIN, "7");
        i.a("event_D139", hashMap, ReaderApplication.getApplicationImp());
        i.a("event_B155", this.mLogMap, ReaderApplication.getApplicationImp());
        StatisticsManager.a().a("event_B155", this.mLogMap);
    }

    public void attachView() {
        super.attachView();
        View a = ap.a(getRootView(), R.id.interaction);
        TextView textView = (TextView) ap.a(getRootView(), R.id.author_decs);
        TextView textView2 = (TextView) ap.a(getRootView(), R.id.author_fans);
        TextView textView3 = (TextView) ap.a(getRootView(), R.id.author_comments);
        TextView textView4 = (TextView) ap.a(getRootView(), R.id.author_comments_text);
        ImageView imageView = (ImageView) ap.a(getRootView(), R.id.author_level);
        ImageView imageView2 = (ImageView) ap.a(getRootView(), R.id.author_icon);
        ((TextView) ap.a(getRootView(), R.id.title)).setText(this.mTitle);
        c.a(getEvnetListener().getFromActivity()).a(this.mImageUrl, imageView2, a.a().f());
        switch (this.mLevel) {
            case 4:
                imageView.setVisibility(0);
                imageView.setImageResource(R.drawable.card_platinum);
                break;
            case 5:
                imageView.setVisibility(0);
                imageView.setImageResource(R.drawable.card_god);
                break;
            case 6:
                imageView.setVisibility(0);
                imageView.setImageResource(R.drawable.card_star);
                break;
            case 7:
                imageView.setVisibility(0);
                imageView.setImageResource(R.drawable.card_auther);
                break;
            default:
                imageView.setVisibility(8);
                break;
        }
        if (this.mCommentsCount <= 0) {
            textView3.setVisibility(8);
            textView4.setVisibility(8);
        } else {
            textView3.setText(j.a(this.mCommentsCount));
        }
        if (this.mFansCount <= 0) {
            a.setVisibility(8);
        } else {
            textView2.setText(j.a(this.mFansCount));
        }
        if (TextUtils.isEmpty(this.mDirectDesc)) {
            textView.setVisibility(8);
        } else {
            textView.setText(this.mDirectDesc);
        }
    }

    protected void expose() {
        super.expose();
        i.a("event_B154", this.mLogMap, ReaderApplication.getApplicationImp());
        StatisticsManager.a().a("event_B154", this.mLogMap);
    }
}
