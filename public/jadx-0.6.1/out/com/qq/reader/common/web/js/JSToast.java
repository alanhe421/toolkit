package com.qq.reader.common.web.js;

import android.app.Activity;
import com.qq.reader.common.web.js.a.a.b;
import com.qq.reader.view.af;
import com.qq.reader.view.c;
import com.tencent.midas.api.APMidasPayAPI;

public class JSToast extends b {
    private Activity a;
    private c b;

    public JSToast(Activity activity) {
        this.a = activity;
    }

    public void showToast(String str) {
        af.a(this.a.getApplicationContext(), (CharSequence) str, 0).a();
    }

    public void showToast() {
        af.a(this.a.getApplicationContext(), APMidasPayAPI.ENV_TEST, 0).a();
    }

    public void showProgress(String str) {
        if (this.b == null) {
            this.b = new c(this.a);
            this.b.a(str);
            this.b.b(false);
        }
        if (!this.b.f()) {
            this.b.f_();
        }
    }

    public boolean cancelProgress() {
        try {
            if (this.b != null && this.b.f()) {
                this.b.cancel();
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }
}
