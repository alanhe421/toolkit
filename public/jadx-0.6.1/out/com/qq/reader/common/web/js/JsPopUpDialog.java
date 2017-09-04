package com.qq.reader.common.web.js;

import android.app.Activity;
import android.content.Intent;
import com.qq.reader.activity.WebBrowserForContents;
import com.qq.reader.common.web.js.a.a.b;
import com.qq.reader.view.ag;
import com.qq.reader.view.ag.a;
import com.tencent.connect.common.Constants;

public class JsPopUpDialog extends b {
    private Activity a;
    private ag b;

    public JsPopUpDialog(Activity activity) {
        this.a = activity;
    }

    public void showDialog(String str, String str2, String str3, final String str4, final String str5) {
        ag dialog = getDialog();
        dialog.a(str, str2, str3);
        dialog.a(new a(this) {
            final /* synthetic */ JsPopUpDialog c;

            public void a() {
                if (str4.equals(Constants.DEFAULT_UIN)) {
                    Intent intent = new Intent(this.c.a, WebBrowserForContents.class);
                    intent.putExtra("com.qq.reader.WebContent", str5);
                    this.c.a.startActivity(intent);
                }
            }
        });
        dialog.f_();
    }

    public ag getDialog() {
        if (this.b == null) {
            this.b = new ag(this.a);
        }
        return this.b;
    }
}
