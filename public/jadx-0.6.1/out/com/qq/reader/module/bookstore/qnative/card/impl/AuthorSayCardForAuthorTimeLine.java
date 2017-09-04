package com.qq.reader.module.bookstore.qnative.card.impl;

import android.view.View;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.question.card.AudioComBaseCardDisablePlay;
import com.tencent.feedback.proguard.R;

public class AuthorSayCardForAuthorTimeLine extends AudioComBaseCardDisablePlay implements a {
    private boolean mIsShowDivider = true;

    public AuthorSayCardForAuthorTimeLine(b bVar, String str) {
        super(bVar, str);
    }

    public void attachView() {
        i.a("event_F72", null, ReaderApplication.getApplicationImp());
        config(0);
        super.attachView();
        View a = ap.a(getRootView(), R.id.localstore_adv_bottom_divider);
        ap.a(getRootView(), R.id.localstore_adv_divider).setVisibility(8);
        if (this.mIsShowDivider) {
            a.setVisibility(0);
        } else {
            a.setVisibility(8);
        }
    }

    protected void onToDetailPageUpLog(boolean z) {
        super.onToDetailPageUpLog(z);
        i.a("event_F73", null, ReaderApplication.getApplicationImp());
    }

    public void setShowDivider(Boolean bool) {
        this.mIsShowDivider = bool.booleanValue();
    }
}
