package com.qq.reader.view.pullupdownlist;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tencent.feedback.proguard.R;

public class XListViewFooter extends FrameLayout {
    public int b = 0;
    protected Context c;
    protected View d;
    protected View e;
    protected TextView f;
    protected View g;
    protected RelativeLayout h;

    public XListViewFooter(Context context) {
        super(context);
        a(context);
    }

    public XListViewFooter(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public void setState(int i) {
        this.g.setVisibility(8);
        this.d.setVisibility(0);
        this.f.setTextColor(getResources().getColor(R.color.listview_footer_normal_color));
        if (i == 3) {
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

    public void setBottomMargin(int i) {
    }

    public int getBottomMargin() {
        return ((LayoutParams) this.d.getLayoutParams()).bottomMargin;
    }

    protected void a(Context context) {
        this.c = context;
        this.h = (RelativeLayout) LayoutInflater.from(this.c).inflate(R.layout.xlistview_footer, null);
        addView(this.h);
        this.d = this.h.findViewById(R.id.xlistview_footer_content);
        this.e = this.h.findViewById(R.id.xlistview_footer_progressbar);
        this.f = (TextView) this.h.findViewById(R.id.xlistview_footer_hint_textview);
        this.g = findViewById(R.id.xlistview_footer_divider);
    }

    public int getState() {
        return this.b;
    }

    public void setProgressBarIndeterminateDrawable(int i) {
        if (this.e instanceof ProgressBar) {
            ProgressBar progressBar = (ProgressBar) this.e;
            if (i != 0) {
                progressBar.setIndeterminateDrawable(getResources().getDrawable(i));
            }
        }
    }
}
