package com.qq.reader.module.bookstore.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.qq.reader.view.pullupdownlist.XListViewFooter;
import com.tencent.feedback.proguard.R;

public class SearchXListFooter extends XListViewFooter {
    private View a;

    public SearchXListFooter(Context context) {
        super(context);
    }

    public void setState(int i) {
        this.f.setTextColor(getResources().getColor(R.color.listview_footer_normal_color));
        if (i == 5) {
            this.d.setVisibility(8);
            this.a.setVisibility(0);
        } else {
            this.a.setVisibility(8);
            if (i == 3) {
                this.d.setVisibility(8);
            } else if (i == 4) {
                this.d.setVisibility(0);
                this.e.setVisibility(8);
                this.f.setTextColor(getResources().getColor(R.color.listview_footer_fail_color));
                this.f.setText(R.string.xlistview_footer_hint_errordata);
                this.f.setVisibility(0);
            } else {
                this.d.setVisibility(0);
                this.f.setVisibility(0);
                this.f.setText(R.string.xlistview_header_hint_loading);
                this.e.setVisibility(0);
            }
        }
        this.b = i;
    }

    protected void a(Context context) {
        this.c = context;
        View inflate = LayoutInflater.from(this.c).inflate(R.layout.searchxlistview_footer, null);
        this.d = inflate.findViewById(R.id.xlistview_footer_content);
        this.e = inflate.findViewById(R.id.xlistview_footer_progressbar);
        this.f = (TextView) inflate.findViewById(R.id.xlistview_footer_hint_textview);
        this.a = inflate.findViewById(R.id.xlistview_emptyview);
        addView(inflate);
    }
}
