package com.qq.reader.module.bookstore.qweb.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import com.qq.reader.appconfig.b;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.c.a;
import com.qq.reader.common.offline.f;
import com.qq.reader.common.readertask.ordinal.ReaderDownloadTask;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.web.js.a.c;
import com.qq.reader.view.FixedWebView;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public abstract class OfflineBaseFragment extends BaseFragment {
    private Context mContext;
    protected volatile Handler mHandler;
    public c mJsEx = null;
    protected int mPageType = 0;
    protected FixedWebView mWebPage;
    protected WebSettings mWebSettings;
    Map<String, String> md5List = new HashMap();

    protected abstract void bindWebChromeClient();

    protected abstract void bindWebViewClient();

    protected abstract View initFragmentUI(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle);

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mContext = getApplicationContext();
        this.mHandler = getHandler();
    }

    protected void initWebView() {
        try {
            this.mWebSettings = this.mWebPage.getSettings();
            ao.a(this.mContext, this.mWebSettings);
            this.mWebSettings.setRenderPriority(RenderPriority.HIGH);
            this.mWebSettings.setJavaScriptEnabled(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View initFragmentUI = initFragmentUI(layoutInflater, viewGroup, bundle);
        initWebView();
        super.onCreateView(layoutInflater, viewGroup, bundle);
        return initFragmentUI;
    }

    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    protected void bindJavaScript(WebView webView) {
        this.mJsEx = new c();
        this.mJsEx.b(webView);
        webView.getSettings().setJavaScriptEnabled(true);
        this.mJsEx.a(webView);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
    }

    private String getTestUrl(String str) {
        return str + (str.contains("?") ? "&dotest=1" : "?dotest=1");
    }

    protected String getLoadUrl(String str) {
        if (str != null && str.startsWith(com.tencent.qalsdk.core.c.d)) {
            return getLoadUrlNew(str);
        }
        String testUrl;
        if (b.a) {
            testUrl = getTestUrl(str);
        } else {
            testUrl = str;
        }
        if (testUrl == null || testUrl.equals("")) {
            return e.c + "index.html";
        }
        if (testUrl.startsWith("http://")) {
            return testUrl;
        }
        String str2 = a.cY + "/" + testUrl;
        if (str2.indexOf("?") != -1) {
            str2 = str2.substring(0, str2.indexOf("?"));
        }
        File file = new File(str2);
        com.qq.reader.common.offline.a.a(getApplicationContext()).b();
        if (b.a) {
            return e.c + "/" + testUrl;
        }
        if (canLoadUrl() && file.exists()) {
            return "file:///" + a.cY + "/" + testUrl;
        }
        return e.c + "/" + testUrl;
    }

    protected String getLoadUrlNew(String str) {
        if (str == null) {
            return str;
        }
        String testUrl;
        String substring = str.substring(str.lastIndexOf("/") + 1, str.length());
        if (b.a) {
            testUrl = getTestUrl(substring);
        } else {
            testUrl = substring;
        }
        if (testUrl == null || testUrl.equals("")) {
            return e.c + "index.html";
        }
        substring = a.cY + "/" + testUrl;
        if (substring.indexOf("?") != -1) {
            substring = substring.substring(0, substring.indexOf("?"));
        }
        File file = new File(substring);
        com.qq.reader.common.offline.a.a(getApplicationContext()).b();
        if (!b.a && canLoadUrl() && file.exists()) {
            return "file:///" + a.cY + "/" + testUrl;
        }
        return str;
    }

    private String getAssetUrl(String str) {
        String substring;
        int i = 0;
        if (str.indexOf("?") != -1) {
            substring = str.substring(0, str.indexOf("?"));
        } else {
            substring = str;
        }
        try {
            String[] list = getResources().getAssets().list("bookstore");
            int length = list.length;
            while (i < length) {
                if (list[i].equalsIgnoreCase(substring)) {
                    return "file:///android_asset/bookstore/" + str;
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected boolean isLoadSDFile(String str) {
        if (str.contains("android_asset")) {
            return false;
        }
        return true;
    }

    protected boolean checkUrlLegality(WebView webView, String str) {
        try {
            if (!new URL(str).getHost().contains(".qq.com")) {
                return false;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return true;
    }

    protected WebResourceResponse getResource(String str) {
        if (str.startsWith("http://") && (str.endsWith(".jpg") || str.endsWith(".png"))) {
            InputStream image = getImage(str);
            if (image != null) {
                return new WebResourceResponse("image/*", "base64", image);
            }
        }
        return null;
    }

    public InputStream getImage(String str) {
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
            com.qq.reader.common.offline.c.a(getApplicationContext()).a(new ReaderDownloadTask(this.mContext, file.getAbsolutePath(), str));
        }
        return null;
    }

    public boolean canLoadUrl() {
        if (new File(a.cW).exists()) {
            return false;
        }
        return true;
    }

    private WebResourceResponse getLocalResource(String str) {
        return null;
    }

    private WebResourceResponse getRemoteResource(String str) {
        return null;
    }

    protected void refreshBookShelf() {
        this.mWebPage.loadUrl("javascript:doUpdate()");
    }
}
