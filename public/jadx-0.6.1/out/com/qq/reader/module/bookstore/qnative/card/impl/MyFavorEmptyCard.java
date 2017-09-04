package com.qq.reader.module.bookstore.qnative.card.impl;

import android.text.TextUtils;
import com.qq.reader.module.bookstore.qnative.card.BaseEmptyCard;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.view.EmptyView;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class MyFavorEmptyCard extends BaseEmptyCard {
    EmptyView emptyView;
    int mImageRes = 0;
    String mTextString;

    public MyFavorEmptyCard(b bVar, String str) {
        super(bVar, str);
        setCardId(str);
        this.mDataState = 1001;
    }

    public int getResLayoutId() {
        return R.layout.myfavor_empty_view;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        return false;
    }

    public void attachView() {
        this.emptyView = (EmptyView) getRootView();
        if (!TextUtils.isEmpty(this.mTextString)) {
            this.emptyView.a(0).a(this.mTextString);
        }
        if (this.mImageRes > 0) {
            this.emptyView.b(this.mImageRes);
        }
    }

    public void setText(String str) {
        this.mTextString = str;
    }

    public void setImage(int i) {
        this.mImageRes = i;
    }
}
