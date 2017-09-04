package com.qq.reader.common.web.js;

import android.app.Activity;
import android.content.Intent;
import com.qq.reader.activity.WebBrowserForContents;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.monitor.j;
import com.qq.reader.common.web.js.a.a.b;
import com.qq.reader.view.BaseDialog;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;

public class JSSwitchBrowser extends b {
    private Activity a;
    private BaseDialog b;

    public JSSwitchBrowser(Activity activity, BaseDialog baseDialog) {
        this.a = activity;
        this.b = baseDialog;
    }

    public void jumpTo(String str) {
        if (this.a != null) {
            this.b.cancel();
            j.a(19, 1);
            Intent intent = new Intent();
            intent.putExtra("com.qq.reader.WebContent", e.c + str);
            intent.setClass(this.a, WebBrowserForContents.class);
            intent.setFlags(SigType.WLOGIN_QRPUSH);
            this.a.startActivity(intent);
        }
    }
}
