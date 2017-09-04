package com.qq.reader.module.bookstore.qnative.card.impl;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.tencent.feedback.proguard.R;
import com.tencent.smtt.sdk.WebView;

public class CardTitle extends LinearLayout {
    private TextView a;
    private TextView b;
    private TextView c;
    private TextView d;

    public CardTitle(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(getLayoutId(), this, true);
        a();
    }

    private void a() {
        this.a = (TextView) findViewById(R.id.cardtitle_title);
        this.b = (TextView) findViewById(R.id.cardtitle_introduction);
        this.c = (TextView) findViewById(R.id.cardtitle_introduction2);
        this.d = (TextView) findViewById(R.id.cardtitle_introduction_rightend);
    }

    public void setCardTitle(String str) {
        setCardTitle(0, str);
    }

    public void setCardTitle(int i, String str) {
        if (str == null) {
            this.a.setText("");
        } else {
            this.a.setText(str);
        }
    }

    public void setCardIntroductionRightEnd(String str) {
        if (TextUtils.isEmpty(str)) {
            this.d.setVisibility(8);
            return;
        }
        this.d.setVisibility(0);
        this.d.setText(str);
    }

    public void setCardIntroductionBottom(String str) {
        if (TextUtils.isEmpty(str)) {
            this.c.setVisibility(8);
            return;
        }
        this.c.setVisibility(0);
        this.c.setText(str);
    }

    public void setCardIntroduction(String str) {
        if (TextUtils.isEmpty(str)) {
            this.b.setVisibility(8);
            return;
        }
        this.b.setVisibility(0);
        this.b.setText(str);
    }

    public void setCardTitle(int i, String str, String str2, String str3) {
        setCardTitle(i, str);
        if (TextUtils.isEmpty(str2)) {
            this.b.setVisibility(8);
        } else {
            this.b.setVisibility(0);
            this.b.setText(str2);
        }
        if (TextUtils.isEmpty(str3)) {
            this.c.setVisibility(8);
            return;
        }
        this.c.setVisibility(0);
        this.c.setText(str3);
    }

    public void setCardTitleForFreeCard(int i, String str, String str2, String str3) {
        setCardTitle(i, str, str2, str3);
        if (ReaderApplication.getApplicationImp().getApplicationContext().getResources().getColorStateList(R.color.localstore_textcolor_red) != null) {
            this.a.setTextColor(WebView.NIGHT_MODE_COLOR);
        }
    }

    public int getLayoutId() {
        return R.layout.localstore_card_cardtitle;
    }
}
