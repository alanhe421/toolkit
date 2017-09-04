package com.qq.reader.common.web.js;

import android.app.Activity;
import com.qq.reader.activity.WebBrowserForContents;
import com.qq.reader.common.web.js.a.a.b;

public class JSGoToWeb extends b {
    private Activity a;

    public JSGoToWeb(Activity activity) {
        this.a = activity;
    }

    public void gotoWeb() {
        if (this.a instanceof WebBrowserForContents) {
            ((WebBrowserForContents) this.a).n();
        }
    }
}
