package com.qq.reader.view.web;

import android.app.Activity;
import com.qq.reader.common.utils.ao;
import com.qq.reader.view.RoundWebView;
import com.tencent.feedback.proguard.R;

/* compiled from: IntroductionDialog */
public class f extends a {
    private RoundWebView d;

    public f(Activity activity) {
        super(activity);
    }

    protected void g() {
        a(this.c, null, R.layout.introduction_dialog, 0, false);
        this.f.getWindow().addFlags(2);
    }

    protected void h() {
        super.h();
        this.d = (RoundWebView) this.a;
        this.d.setRadius((float) ao.a(5.0f));
    }
}
