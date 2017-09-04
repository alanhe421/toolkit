package com.qq.reader.module.bookstore.qnative.card.impl;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.qurl.c;
import com.tencent.feedback.proguard.R;

public class SingleReplyCardForAuthorTimeLine extends SingleReplyCard implements a {
    private boolean mIsShowDivider = true;

    public SingleReplyCardForAuthorTimeLine(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.author_time_line_reply_card;
    }

    public void attachView() {
        super.attachView();
        i.a("event_F70", null, ReaderApplication.getApplicationImp());
        View a = ap.a(getRootView(), R.id.card_divider);
        if (this.mIsShowDivider) {
            a.setVisibility(0);
        } else {
            a.setVisibility(8);
        }
        ((ImageView) ap.a(getRootView(), R.id.icon_mask)).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ SingleReplyCardForAuthorTimeLine a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                i.a("event_F71", null, ReaderApplication.getApplicationImp());
                if (this.a.item.a == null || this.a.item.a.m != 1 || TextUtils.isEmpty(this.a.item.a.n)) {
                    o.d(this.a.getEvnetListener().getFromActivity(), this.a.item.a.g, this.a.item.a.a, this.a.item.a.b, null);
                } else {
                    o.c(this.a.getEvnetListener().getFromActivity(), this.a.item.a.n, this.a.item.a.a, this.a.item.a.b, null);
                }
            }
        });
        getRootView().setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ SingleReplyCardForAuthorTimeLine a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                try {
                    c.a(this.a.getEvnetListener().getFromActivity(), this.a.item.w, null);
                    i.a("event_F71", null, ReaderApplication.getApplicationImp());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void setShowDivider(Boolean bool) {
        this.mIsShowDivider = bool.booleanValue();
    }
}
