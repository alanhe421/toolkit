package com.qq.reader.module.bookstore.qnative.card.impl;

import android.view.View;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.view.EmptyView;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class BookClubChapterEndTitleCard extends BookClubTitleCard {
    private boolean mIsShowTip = false;
    private int mReplyCount = 0;
    private String mTitle = null;
    private String mType = null;

    public BookClubChapterEndTitleCard(b bVar, String str, int i) {
        super(bVar, str, i);
    }

    public int getResLayoutId() {
        return R.layout.localstore_card_title_chapterend;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        this.mType = jSONObject.optString("type");
        setCardId(this.mType);
        this.mTitle = jSONObject.optString("title");
        return true;
    }

    public void attachView() {
        TextView textView = (TextView) ap.a(getRootView(), R.id.card_title_text);
        if (textView != null) {
            if (this.mReplyCount > 0) {
                textView.setText(this.mTitle + "(" + this.mReplyCount + ")");
            } else {
                textView.setText(this.mTitle);
            }
            View a = ap.a(getRootView(), R.id.card_title_tip);
            EmptyView emptyView = (EmptyView) a;
            if (this.mIsShowTip) {
                emptyView.a(0);
                emptyView.a(ReaderApplication.getApplicationImp().getString(R.string.bookclub_none_reply));
                a.setVisibility(0);
                return;
            }
            a.setVisibility(8);
        }
    }

    public void setTitle(String str) {
        this.mTitle = str;
    }

    public void setTipVisible(boolean z) {
        this.mIsShowTip = z;
    }

    public void refresh() {
        super.refresh();
        attachView();
    }

    public void setReplyCount(int i) {
        this.mReplyCount = i;
    }
}
