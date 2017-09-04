package com.qq.reader.module.bookstore.qnative.card.impl;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.card.BaseCommentCard;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.view.EmptyView;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class BookClubTitleCard extends BaseCommentCard {
    private boolean centerOver = false;
    private int mBgColorResID = -1;
    private boolean mIsShowDivider = true;
    private boolean mIsShowTip = false;
    private int mPaddingTop;
    private String mPageType = "";
    private int mReplyCount = 0;
    private String mTitle = null;
    private String mTitleExData;
    private int mTitleHeight;
    private String mType = null;

    public BookClubTitleCard(b bVar, String str, int i) {
        super(bVar, str, i);
    }

    public int getResLayoutId() {
        return R.layout.localstore_card_title;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        this.mType = jSONObject.optString("type");
        setCardId(this.mType);
        this.mTitle = jSONObject.optString("title");
        return true;
    }

    public void attachView() {
        CardTitle cardTitle = (CardTitle) ap.a(getRootView(), R.id.card_title_text);
        if (cardTitle != null) {
            int i;
            if (this.mPaddingTop > 0) {
                cardTitle.setPadding(0, this.mPaddingTop, 0, 0);
            }
            if (this.mTitleHeight > 0) {
                LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
                layoutParams.height = this.mTitleHeight;
                cardTitle.setLayoutParams(layoutParams);
            }
            String str = this.mTitleExData;
            if (TextUtils.isEmpty(str)) {
                str = "";
            }
            if ("bookclubdiscusslist".equals(this.mPageType)) {
                String string;
                if (this.mReplyCount > 0) {
                    string = ReaderApplication.getApplicationImp().getResources().getString(R.string.discusslist_title_xx, new Object[]{Integer.valueOf(this.mReplyCount)});
                } else {
                    string = ReaderApplication.getApplicationImp().getResources().getString(R.string.discusslist_title);
                }
                cardTitle.setCardTitle(37, string, str, null);
            } else {
                cardTitle.setCardTitle(37, this.mTitle, str, null);
            }
            View a = ap.a(getRootView(), R.id.card_title_tip);
            EmptyView emptyView = (EmptyView) a;
            if (this.mIsShowTip) {
                emptyView.a(0);
                if ("bookclubdiscusslist".equals(this.mPageType)) {
                    emptyView.a(ReaderApplication.getApplicationImp().getString(R.string.bookclub_none_discuss));
                } else {
                    emptyView.a(ReaderApplication.getApplicationImp().getString(R.string.bookclub_none_reply));
                }
                a.setVisibility(0);
            } else {
                a.setVisibility(8);
            }
            a = ap.a(getRootView(), R.id.card_title_div);
            if (this.mIsShowDivider) {
                i = 0;
            } else {
                i = 8;
            }
            a.setVisibility(i);
            if (this.mBgColorResID > 0) {
                cardTitle.setBackgroundResource(this.mBgColorResID);
            }
        }
    }

    public void setTitle(String str) {
        this.mTitle = str;
    }

    public void setTitleExData(String str) {
        this.mTitleExData = str;
    }

    public void setDividerVisible(boolean z) {
        this.mIsShowDivider = z;
    }

    public void setTipVisible(boolean z) {
        this.mIsShowTip = z;
    }

    public void refresh() {
        super.refresh();
        attachView();
    }

    public void setBackgroundColor(int i) {
        this.mBgColorResID = i;
    }

    public void setPageType(String str) {
        this.mPageType = str;
    }

    public void setReplyCount(int i) {
        this.mReplyCount = i;
    }

    public void setDataReady() {
        this.mDataState = 1001;
    }

    public void setTitleHeight(int i) {
        this.mTitleHeight = i;
    }

    public void setTitlePaddingTop(int i) {
        this.mPaddingTop = i;
    }
}
