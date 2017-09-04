package com.qq.reader.module.feed.card.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import com.tencent.feedback.proguard.R;

public class CardMoreView extends LinearLayout {
    private Button a;

    public CardMoreView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.concept_card_more_layout, this, true);
        setOrientation(1);
        a();
    }

    private void a() {
        this.a = (Button) findViewById(R.id.concept_more_button);
    }

    public void setText(CharSequence charSequence) {
        this.a.setText(charSequence);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.a.setOnClickListener(onClickListener);
    }
}
