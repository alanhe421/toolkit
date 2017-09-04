package com.qq.reader.view.web;

import android.app.Activity;
import android.os.Message;
import android.webkit.WebSettings;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.offline.b;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.web.js.JSAddToBookShelf;
import com.qq.reader.common.web.js.JSAdv;
import com.qq.reader.common.web.js.JSContent;
import com.qq.reader.common.web.js.JSLocalStorage;
import com.qq.reader.common.web.js.JSOfflineInterface;
import com.qq.reader.common.web.js.JSPay;
import com.qq.reader.common.web.js.JSToast;
import com.qq.reader.common.web.js.a.c;
import com.qq.reader.view.BaseDialog;
import com.qq.reader.view.FixedWebView;
import com.tencent.feedback.proguard.R;
import com.tencent.mobileqq.openpay.constants.OpenConstants;
import java.io.File;

/* compiled from: OpenMonthlyDialog */
public class h extends BaseDialog implements com.qq.reader.common.web.js.JSContent.a {
    protected c a = null;
    protected c b = null;
    protected b c = new b(this);
    protected a d;
    private FixedWebView e;
    private TextView i;
    private ImageView j;
    private Activity k;
    private WebSettings l = null;

    /* compiled from: OpenMonthlyDialog */
    public interface a {
        void a(String str, boolean z);
    }

    public h(Activity activity) {
        this.k = activity;
        if (this.f == null) {
            a(activity, null, R.layout.openmonthlyweb, 0, true);
            this.f.setCanceledOnTouchOutside(false);
            this.e = (FixedWebView) this.f.findViewById(R.id.advwebview);
            this.j = (ImageView) this.f.findViewById(R.id.dialog_close);
            this.j.setOnClickListener(new 1(this));
            this.i = (TextView) this.f.findViewById(R.id.dialog_title);
            this.e.setBackgroundColor(0);
            this.l = this.e.getSettings();
            ao.a(this.k, this.l, 2);
            this.l.setJavaScriptEnabled(true);
            g();
            i();
            com.qq.reader.common.offline.c.a(this.k).a(this.c, "WEBDIALOG");
        }
        this.f.getWindow().addFlags(2);
    }

    public void a(String str) {
        this.e.post(new 2(this, b(str)));
    }

    public void f_() {
        if (this.k != null && !this.k.isFinishing()) {
            com.qq.reader.common.offline.c.a(this.k).a(this.c, "WEBDIALOG");
            c().a((int) R.id.root_layout);
            super.f_();
        }
    }

    public void dismiss() {
        this.a.a();
        com.qq.reader.common.offline.c.a(this.k).a("WEBDIALOG");
        super.dismiss();
    }

    public void cancel() {
        this.a.a();
        com.qq.reader.common.offline.c.a(this.k).a("WEBDIALOG");
        super.cancel();
    }

    protected void a(Message message) {
        switch (message.what) {
            case 65539:
                cancel();
                return;
            case 65540:
                cancel();
                if (this.b != null) {
                    this.b.a();
                    return;
                }
                return;
            case 90004:
                b bVar = (b) message.obj;
                this.e.loadUrl("javascript:" + bVar.a() + "(" + bVar.b() + ")");
                return;
            default:
                return;
        }
    }

    public void a(Activity activity) {
        this.a = new c();
        this.a.b(this.e);
        this.e.getSettings().setJavaScriptEnabled(true);
        this.a.a(this.e);
        this.a.a(new JSOfflineInterface(activity, this.c, "WEBDIALOG"), "mclient");
        this.a.a(new JSToast(activity), "JSToast");
        this.a.a(new JSLocalStorage(activity), "JSLocalStorage");
        this.a.a(new JSPay(activity), OpenConstants.API_NAME_PAY);
        this.a.a(new JSAdv(this.c), "JSAdv");
        this.a.a(new JSAddToBookShelf(activity), "JSAddToShelf");
        com.qq.reader.common.web.js.a.a.b jSContent = new JSContent(activity);
        jSContent.setDialogCloseCallBack(this);
        this.a.a(jSContent, "JSContent");
    }

    protected void g() {
        this.e.setWebChromeClient(new 3(this));
    }

    private void i() {
        this.e.setWebViewClient(new 4(this));
    }

    private String c(String str) {
        return str + (str.contains("?") ? "&dotest=1" : "?dotest=1");
    }

    public boolean h() {
        if (new File(com.qq.reader.common.c.a.cW).exists()) {
            return false;
        }
        return true;
    }

    protected String b(String str) {
        String c;
        if (com.qq.reader.appconfig.b.a) {
            c = c(str);
        } else {
            c = str;
        }
        if (c == null || c.equals("")) {
            return e.c + "/index.html";
        }
        if (c.startsWith("http://")) {
            return c;
        }
        String str2 = com.qq.reader.common.c.a.cY + c;
        if (str2.indexOf("?") != -1) {
            str2 = str2.substring(0, str2.indexOf("?"));
        }
        File file = new File(str2);
        com.qq.reader.common.offline.a.a(this.k).b();
        if (com.qq.reader.appconfig.b.a) {
            return e.c + "/" + c;
        }
        if (h() && file.exists()) {
            return "file://" + com.qq.reader.common.c.a.cY + c;
        }
        return e.c + "/" + c;
    }

    public void a(a aVar) {
        this.d = aVar;
    }

    public void f(String str) {
        f.d("openmonth", "onDialogClose ");
        if (this.d != null) {
            this.d.a(str, true);
        }
    }

    public void g(String str) {
    }
}
