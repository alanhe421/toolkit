package com.qq.reader.common.offline;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import com.qq.reader.activity.ReaderBaseActivity;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.c.a;
import com.qq.reader.common.monitor.a.b;
import com.qq.reader.common.readertask.ordinal.ReaderDownloadTask;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.web.js.a.c;
import com.qq.reader.view.FixedWebView;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public abstract class OfflineBaseActivity extends ReaderBaseActivity {
    private boolean a = true;
    protected FixedWebView k;
    protected WebSettings l;
    protected Context m;
    protected volatile Handler n;
    Map<String, String> o = new HashMap();
    public c p = null;
    protected int q = 0;
    public boolean r;

    protected abstract void j();

    protected abstract void k();

    protected abstract void l();

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.m = getApplicationContext();
        this.n = getHandler();
    }

    protected void C() {
        try {
            this.l = this.k.getSettings();
            ao.a(this.m, this.l);
            this.l.setRenderPriority(RenderPriority.HIGH);
            this.l.setJavaScriptEnabled(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Handler D() {
        return super.getHandler();
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onPause() {
        super.onPause();
    }

    protected void onDestroy() {
        b.a();
        super.onDestroy();
    }

    protected void a(WebView webView) {
        this.p = new c();
        this.p.b(webView);
        webView.getSettings().setJavaScriptEnabled(true);
        this.p.a(webView);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setCacheMode(2);
    }

    private String a(String str) {
        return str + (str.contains("?") ? "&dotest=1" : "?dotest=1");
    }

    protected String i(String str) {
        String str2;
        if (str == null || str.equals("")) {
            str2 = e.c + "/index.html";
        } else if (str.startsWith("http://")) {
            return str;
        } else {
            str2 = a.cY + "/" + str;
            if (str2.indexOf("?") != -1) {
                str2 = str2.substring(0, str2.indexOf("?"));
            }
            File file = new File(str2);
            a.a(getApplicationContext()).b();
            if (com.qq.reader.appconfig.b.a) {
                str2 = e.c + "/" + str;
            } else if (E() && file.exists()) {
                str2 = "file:///" + a.cY + "/" + str;
            } else {
                str2 = e.c + "/" + str;
            }
        }
        if (com.qq.reader.appconfig.b.a) {
            str2 = a(str2);
        }
        return str2;
    }

    protected WebResourceResponse j(String str) {
        if (str.startsWith("http://") && (str.endsWith(".jpg") || str.endsWith(".png"))) {
            InputStream k = k(str);
            if (k != null) {
                return new WebResourceResponse("image/*", "base64", k);
            }
        }
        return null;
    }

    public InputStream k(String str) {
        File file = new File(a.dc + ao.r(str));
        if (file.exists()) {
            try {
                return new f(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        }
        if (com.qq.reader.common.utils.networkUtil.e.a()) {
            c.a(getApplicationContext()).a(new ReaderDownloadTask(this.m, file.getAbsolutePath(), str));
        }
        return null;
    }

    public boolean E() {
        if (new File(a.cW).exists()) {
            return false;
        }
        return true;
    }

    protected void F() {
        if (!this.r) {
            this.k.loadUrl("javascript:doUpdate()");
        }
    }
}
