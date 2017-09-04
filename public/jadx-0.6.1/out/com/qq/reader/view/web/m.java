package com.qq.reader.view.web;

import android.app.Activity;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import com.qq.reader.common.utils.ao.e;
import com.qq.reader.common.web.js.JSAdv;
import com.qq.reader.common.web.js.JSContent;
import com.qq.reader.common.web.js.JSOfflineInterface;
import com.qq.reader.common.web.js.a.c;
import com.qq.reader.cservice.adv.a;
import com.qq.reader.view.BaseDialog;
import com.qq.reader.view.FixedWebView;
import com.tencent.feedback.proguard.R;

/* compiled from: PopWebDialog */
public class m extends BaseDialog {
    private FixedWebView a;
    private ImageView b;
    private View c;
    private Activity d;
    private c e = null;
    private final a i = new a(this);

    public m(Activity activity, int i) {
        this.d = activity;
        if (this.f == null) {
            a(activity, null, R.layout.nativeadv_window, 14, false);
            this.f.setCanceledOnTouchOutside(false);
            this.f.setCancelable(true);
            this.a = (FixedWebView) this.f.findViewById(R.id.advwebview);
            this.a.setBackgroundColor(0);
            this.c = this.f.findViewById(R.id.adv_native);
            switch (i) {
                case 0:
                    this.a.setVisibility(0);
                    this.c.setVisibility(8);
                    break;
                case 1:
                    this.a.setVisibility(8);
                    this.c.setVisibility(0);
                    this.b = (ImageView) this.f.findViewById(R.id.adv_img);
                    this.f.setCanceledOnTouchOutside(true);
                    break;
            }
            g();
            i();
            try {
                h();
            } catch (Throwable th) {
                th.printStackTrace();
            }
            com.qq.reader.common.offline.c.a(this.d).a(this.i, "WEBDIALOG");
        }
        this.f.getWindow().addFlags(2);
    }

    public void a(a aVar, Handler handler) {
        com.qq.reader.common.imageloader.c.a(e()).a(aVar.g(), this.b, com.qq.reader.common.imageloader.a.a().c(com.qq.reader.common.c.a.bU), new 1(this, aVar, handler));
    }

    public void f_() {
        if (this.d != null && !this.d.isFinishing()) {
            h();
            com.qq.reader.common.offline.c.a(this.d).a(this.i, "WEBDIALOG");
            super.f_();
        }
    }

    public void dismiss() {
        this.e.a();
        com.qq.reader.common.offline.c.a(this.d).a("WEBDIALOG");
        if (!this.d.isFinishing()) {
            super.dismiss();
        }
    }

    public void cancel() {
        this.e.a();
        com.qq.reader.common.offline.c.a(this.d).a("WEBDIALOG");
        if (!this.d.isFinishing()) {
            super.cancel();
        }
    }

    private void h() {
        this.e = new c();
        this.e.b(this.a);
        this.a.getSettings().setJavaScriptEnabled(true);
        e.a(this.d);
        this.e.a(this.a);
        this.e.a(new JSContent(this.d), "JSContent");
        this.e.a(new JSAdv(this.i), "JSAdv");
        this.e.a(new JSOfflineInterface(this.d, this.i, "WEBDIALOG"), "mclient");
    }

    protected void g() {
        this.a.setWebChromeClient(new 2(this));
    }

    private void i() {
        this.a.setWebViewClient(new 3(this));
    }
}
