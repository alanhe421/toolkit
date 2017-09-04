package com.qq.reader.module.comic.views;

import android.content.Context;
import com.qq.reader.view.pullupdownlist.XListViewFooter;
import com.tencent.feedback.proguard.R;

public class ComicListViewFooter extends XListViewFooter {
    public ComicListViewFooter(Context context) {
        super(context);
    }

    public void setState(int i) {
        this.g.setVisibility(8);
        this.d.setVisibility(0);
        this.f.setTextColor(getResources().getColor(R.color.listview_footer_normal_color));
        this.h.setBackgroundColor(getResources().getColor(R.color.concept_card_bg));
        if (i == 3) {
            this.h.setBackgroundColor(getResources().getColor(R.color.concept_divider_bg));
            this.e.setVisibility(8);
            this.f.setText(R.string.xlistview_footer_hint_nonedata);
            this.f.setVisibility(0);
            this.g.setVisibility(0);
        } else if (i == 4) {
            this.e.setVisibility(8);
            this.f.setTextColor(getResources().getColor(R.color.listview_footer_fail_color));
            this.f.setText(R.string.xlistview_footer_hint_errordata);
            this.f.setVisibility(0);
        } else if (i == 5) {
            this.d.setVisibility(8);
            this.e.setVisibility(8);
            this.f.setVisibility(8);
        } else {
            this.f.setVisibility(0);
            this.f.setText(R.string.xlistview_header_hint_loading);
            this.e.setVisibility(0);
        }
        this.b = i;
    }
}
