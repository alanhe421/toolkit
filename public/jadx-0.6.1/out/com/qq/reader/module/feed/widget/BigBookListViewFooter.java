package com.qq.reader.module.feed.widget;

import android.content.Context;
import com.qq.reader.view.pullupdownlist.XListViewFooter;
import com.tencent.feedback.proguard.R;

public class BigBookListViewFooter extends XListViewFooter {
    public BigBookListViewFooter(Context context) {
        super(context);
        this.f.setText(R.string.xlistview_header_hint_loading);
    }

    public void setState(int i) {
        super.setState(i);
        if (i == 3) {
            this.g.setVisibility(8);
        } else if (i == 0) {
            this.d.setVisibility(0);
            this.f.setVisibility(0);
            this.f.setText(R.string.xlistview_header_hint_loading);
            this.e.setVisibility(0);
        }
    }
}
