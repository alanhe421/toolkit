package com.qq.reader.common.web.js;

import android.app.Activity;
import android.content.Intent;
import com.qq.reader.activity.WebBrowserForContents;
import com.qq.reader.common.utils.c;
import com.qq.reader.common.web.js.a.a.b;
import com.tencent.feedback.proguard.R;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;

public class JSDetail extends b {
    private Activity a;

    public JSDetail(Activity activity) {
        this.a = activity;
    }

    public void openDetail(String str) {
        if (this.a instanceof WebBrowserForContents) {
            ((WebBrowserForContents) this.a).c(str);
            return;
        }
        Intent intent = new Intent();
        intent.setClass(this.a, WebBrowserForContents.class);
        intent.setFlags(SigType.WLOGIN_QRPUSH);
        intent.putExtra("com.qq.reader.WebContent", str);
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        this.a.startActivity(intent);
    }
}
