package com.qq.reader.module.feed.card.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.module.bookstore.qnative.item.r;
import com.tencent.feedback.proguard.R;

public class HallOfFameTabItemView extends LinearLayout {
    private View a;
    private TextView b;
    private View c;
    private LinearLayout d;
    private int e;

    public HallOfFameTabItemView(Context context, AttributeSet attributeSet, View view) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.localstore_card_author_left, null, false);
        a(view);
    }

    private void a(View view) {
        this.a = view.findViewById(R.id.author_tab_line);
        this.b = (TextView) view.findViewById(R.id.author_tab_title);
        this.c = view.findViewById(R.id.author_divider_line);
        this.d = (LinearLayout) view.findViewById(R.id.author_tab_layout);
    }

    public void setTabItemData(r rVar) {
        this.b.setText(rVar.b());
    }

    public void setIndex(int i) {
        this.e = i;
    }

    public int getIndex() {
        return this.e;
    }

    public void a() {
        i.a("event_F324", null, ReaderApplication.getApplicationImp().getApplicationContext());
        this.d.setBackgroundResource(R.color.concept_card_bg);
        this.b.setTextColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.text_color_c301));
        this.a.setVisibility(0);
        this.c.setVisibility(8);
    }

    public void b() {
        this.d.setBackgroundResource(R.color.concept_divider_bg);
        this.b.setTextColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.common_textcolor_primary));
        this.a.setVisibility(8);
        this.c.setVisibility(0);
    }

    public void setTabTitle(String str) {
        this.b.setText(str);
    }
}
