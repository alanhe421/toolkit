package com.qq.reader.module.question.card;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.qq.reader.view.EmptyView;
import com.qq.reader.view.pullupdownlist.XListViewFooter;
import com.tencent.feedback.proguard.R;

public class AudioQuizXlistFooter extends XListViewFooter {
    private View a;

    public AudioQuizXlistFooter(Context context) {
        super(context);
    }

    public void setState(int i) {
        this.g.setVisibility(8);
        this.f.setTextColor(getResources().getColor(R.color.listview_footer_normal_color));
        if (i == 5) {
            this.d.setVisibility(8);
            this.a.setVisibility(0);
        } else {
            this.a.setVisibility(8);
            this.d.setVisibility(0);
            if (i == 3) {
                this.g.setVisibility(0);
                this.e.setVisibility(8);
                this.f.setVisibility(0);
                this.f.setText(R.string.xlistview_footer_hint_nonedata);
            } else if (i == 4) {
                this.f.setTextColor(getResources().getColor(R.color.listview_footer_fail_color));
                this.e.setVisibility(8);
                this.f.setText(R.string.xlistview_footer_hint_errordata);
                this.f.setVisibility(0);
            } else {
                this.f.setVisibility(0);
                this.f.setText(R.string.xlistview_header_hint_loading);
                this.e.setVisibility(0);
            }
        }
        this.b = i;
    }

    protected void a(Context context) {
        this.c = context;
        View inflate = LayoutInflater.from(this.c).inflate(R.layout.audio_quiz_xlistview_footer, null);
        this.d = inflate.findViewById(R.id.xlistview_footer_content);
        this.e = inflate.findViewById(R.id.xlistview_footer_progressbar);
        this.f = (TextView) inflate.findViewById(R.id.xlistview_footer_hint_textview);
        this.a = inflate.findViewById(R.id.xlistview_emptyview);
        this.g = inflate.findViewById(R.id.xlistview_footer_divider);
        addView(inflate);
    }

    public void setEmptyTitle(String str) {
        ((EmptyView) this.a).a((CharSequence) str);
    }

    public void setEmptyIcon(int i) {
        ((EmptyView) this.a).b(i);
    }
}
