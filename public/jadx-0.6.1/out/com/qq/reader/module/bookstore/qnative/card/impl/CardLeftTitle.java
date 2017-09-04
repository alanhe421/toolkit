package com.qq.reader.module.bookstore.qnative.card.impl;

import android.content.Context;
import android.util.AttributeSet;
import com.tencent.feedback.proguard.R;

public class CardLeftTitle extends CardTitle {
    public CardLeftTitle(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public int getLayoutId() {
        return R.layout.localstore_card_cardtitle_left;
    }
}
