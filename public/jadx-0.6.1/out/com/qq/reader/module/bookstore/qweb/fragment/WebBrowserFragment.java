package com.qq.reader.module.bookstore.qweb.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.DownloadListener;
import android.webkit.WebBackForwardList;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.activity.BaseWebTabActivity;
import com.qq.reader.activity.MainActivity;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.offline.a.a;
import com.qq.reader.common.offline.b;
import com.qq.reader.common.web.c;
import com.qq.reader.common.web.js.AndroidJS;
import com.qq.reader.common.web.js.JSAPP;
import com.qq.reader.common.web.js.JSAddToBookShelf;
import com.qq.reader.common.web.js.JSBookDir;
import com.qq.reader.common.web.js.JSContent;
import com.qq.reader.common.web.js.JSDialog;
import com.qq.reader.common.web.js.JSDownLoad;
import com.qq.reader.common.web.js.JSGoToWeb;
import com.qq.reader.common.web.js.JSLogin;
import com.qq.reader.common.web.js.JSOfflineInterface;
import com.qq.reader.common.web.js.JSPay;
import com.qq.reader.common.web.js.JSReadMusicOnline;
import com.qq.reader.common.web.js.JSReadOnline;
import com.qq.reader.common.web.js.JSReload;
import com.qq.reader.common.web.js.JSSearch;
import com.qq.reader.common.web.js.JSSendSMS;
import com.qq.reader.common.web.js.JSSns;
import com.qq.reader.common.web.js.JSToast;
import com.qq.reader.common.web.js.JSUpdate;
import com.qq.reader.common.web.js.JSbookshelf;
import com.qq.reader.common.web.js.JsAdEvent;
import com.qq.reader.view.FixedWebView;
import com.qq.reader.view.web.e;
import com.tencent.android.tpush.common.Constants;
import com.tencent.feedback.proguard.R;
import com.tencent.mobileqq.openpay.constants.OpenConstants;
import com.tencent.qalsdk.im_open.http;
import java.lang.reflect.Method;
import java.util.HashMap;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;

public class WebBrowserFragment extends OfflineBaseFragment implements OnTouchListener, a, c, e {
    protected final int DIALOG_ONLINE_HISTORY = 305;
    protected String NAME = "WEBBROWSER";
    private String destUrl = null;
    private boolean isNeedClear = false;
    private volatile boolean isNeedClearHistory = false;
    private boolean isRetry = false;
    boolean isTouchWebViewHotWord = false;
    private String lastRequestUrl = "";
    protected Context mContext;
    private ProgressBar mDefaultProgress;
    private TextView mDefaultTextView;
    private long mFirstSectionLoadTime = -1;
    protected volatile Handler mHandler;
    private InputMethodManager mInputMethodManager = null;
    private volatile boolean mIsloading = false;
    private JSLogin mJSLogin = null;
    private com.qq.reader.view.ProgressBar mLoadProgress;
    private String mLoadingUrl;
    private long mPageStartTime;
    protected String mUrl;
    private boolean needRefresh;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mContext = getApplicationContext();
        this.mHandler = getHandler();
    }

    private void disableAccessibility() {
        if (VERSION.SDK_INT == 17) {
            Context context = getContext();
            if (context != null) {
                try {
                    AccessibilityManager accessibilityManager = (AccessibilityManager) context.getSystemService("accessibility");
                    if (accessibilityManager.isEnabled()) {
                        Method declaredMethod = accessibilityManager.getClass().getDeclaredMethod("setState", new Class[]{Integer.TYPE});
                        declaredMethod.setAccessible(true);
                        declaredMethod.invoke(accessibilityManager, new Object[]{Integer.valueOf(0)});
                    }
                } catch (Throwable th) {
                    com.qq.reader.common.monitor.debug.c.e("Webbrowserforcontents", th.getMessage());
                }
            }
        }
    }

    public void onDestroy() {
        if (this.mJsEx != null) {
            this.mJsEx.a();
        }
        com.qq.reader.common.offline.c.a(this.mContext).a(this.NAME);
        super.onDestroy();
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0 && getActivity().getCurrentFocus() != null) {
            this.mInputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
        }
        return getActivity().onTouchEvent(motionEvent);
    }

    public boolean onTrackballEvent(MotionEvent motionEvent) {
        return getActivity().onTrackballEvent(motionEvent);
    }

    public void autoSetZoom() {
        WebSettings settings = this.mWebPage.getSettings();
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        if (this.mWebPage.getSettings().getUseWideViewPort()) {
            this.mWebPage.setInitialScale(25);
        }
        if (VERSION.SDK_INT >= 16) {
            settings.setAllowFileAccess(true);
            settings.setAllowContentAccess(true);
            settings.setAllowFileAccessFromFileURLs(true);
            settings.setAllowUniversalAccessFromFileURLs(true);
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == Constants.ERRORCODE_UNKNOWN && i2 == -1) {
            ((MainActivity) getActivity().getParent()).a("bookweb_recommend_tab");
        } else if (i == 20001) {
            if (i2 == 0) {
                refresh();
            } else if (i2 == 5) {
                this.mJSLogin.toLogin();
            }
        } else if (i != 20002) {
        } else {
            if (i2 == 0) {
                refresh();
            } else if (i2 == 5) {
                this.mJSLogin.toLogin();
            }
        }
    }

    private com.qq.reader.common.login.a getLoginNextTask() {
        return new com.qq.reader.common.login.a(this) {
            final /* synthetic */ WebBrowserFragment a;

            {
                this.a = r1;
            }

            public void a(int i) {
                switch (i) {
                    case 1:
                        this.a.reload();
                        return;
                    default:
                        return;
                }
            }
        };
    }

    protected void bindJavaScript(WebView webView) {
        super.bindJavaScript(webView);
        Context baseActivity = getBaseActivity();
        if (baseActivity instanceof BaseWebTabActivity) {
            this.mJsEx.a(new AndroidJS((BaseWebTabActivity) baseActivity), "AndroidJS");
        }
        if (this.mJSLogin == null) {
            this.mJSLogin = new JSLogin(baseActivity);
            this.mJSLogin.setLoginListener(this);
        }
        this.mJSLogin.setNextLoginTask(getLoginNextTask());
        this.mJsEx.a(new JSDownLoad(baseActivity), "downloadbook");
        this.mJsEx.a(new JSReadOnline(baseActivity), "readonline");
        this.mJsEx.a(this.mJSLogin, "readerlogin");
        this.mJsEx.a(new JSContent(baseActivity), "JSContent");
        this.mJsEx.a(new JSUpdate(baseActivity), "JSUpdate");
        this.mJsEx.a(new JSSendSMS(baseActivity), "sendvip");
        this.mJsEx.a(new JSPay(baseActivity, this.mWebPage), OpenConstants.API_NAME_PAY);
        this.mJsEx.a(new JSToast(baseActivity), "JSToast");
        this.mJsEx.a(new JSDialog(baseActivity), "JSDialog");
        this.mJsEx.a(new JSReload(baseActivity, this), "JSReload");
        this.mJsEx.a(new JSGoToWeb(baseActivity), "JSGoToWeb");
        this.mJsEx.a(new JSReadMusicOnline(baseActivity), "readmusiconline");
        this.mJsEx.a(new JSAPP(baseActivity), "JSApp");
        this.mJsEx.a(new JSAddToBookShelf(baseActivity), "JSAddToShelf");
        this.mJsEx.a(new JSBookDir(baseActivity), "bookdir");
        this.mJsEx.a(new JSSearch(baseActivity), "JSSearch");
        this.mJsEx.a(new JSOfflineInterface(this.mContext, this.mHandler, this.NAME), "mclient");
        this.mJsEx.a(new JSbookshelf(baseActivity), "JSbookshelf");
        this.mJsEx.a(new JsAdEvent((JsAdEvent.a) getActivity()), "JsAdEvent");
        this.mJsEx.a(new JSSns(baseActivity), "JSSns");
    }

    public void retry() {
        this.mWebPage.post(new Runnable(this) {
            final /* synthetic */ WebBrowserFragment a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.mFirstSectionLoadTime = -1;
                if (this.a.mWebPage.copyBackForwardList().getCurrentItem().getUrl().contains("/web_error.html") && this.a.lastRequestUrl != null && this.a.lastRequestUrl.trim().length() > 0) {
                    this.a.mWebPage.b(this.a.lastRequestUrl);
                }
            }
        });
    }

    public void doSomeRefreshThing() {
        reload();
    }

    public void reload() {
        WebBackForwardList copyBackForwardList = this.mWebPage.copyBackForwardList();
        if (copyBackForwardList != null && copyBackForwardList.getSize() > 0) {
            String url = copyBackForwardList.getCurrentItem().getUrl();
            String a = com.qq.reader.common.login.c.c().a(this.mContext);
            try {
                if ((a.length() <= 0 && url.indexOf("usid=") != -1) || ((a.length() > 0 && url.indexOf("usid=") == -1) || (url.indexOf("usid=") != -1 && !a.equals(com.qq.reader.appconfig.e.c(url))))) {
                    url = com.qq.reader.appconfig.e.a(url, a);
                    if (this.destUrl != null && this.destUrl.length() > 0) {
                        int indexOf = this.destUrl.indexOf(35);
                        if (indexOf != -1) {
                            int indexOf2 = this.destUrl.indexOf(38, indexOf);
                            if (indexOf2 == -1) {
                                url = url + this.destUrl.substring(indexOf);
                            } else {
                                url = url + this.destUrl.substring(indexOf, indexOf2);
                            }
                        }
                        this.destUrl = null;
                    }
                    this.mWebPage.clearView();
                    loadUrl(url);
                    this.mWebPage.clearHistory();
                    return;
                } else if (isNeedForceRefresh()) {
                    this.mWebPage.reload();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (this.needRefresh) {
            this.mWebPage.post(new Runnable(this) {
                final /* synthetic */ WebBrowserFragment a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.mFirstSectionLoadTime = -1;
                    this.a.mWebPage.reload();
                    this.a.needRefresh = false;
                }
            });
        }
    }

    public void loadUrl(String str) {
        this.mLoadingUrl = getLoadUrl(str);
        if (this.mWebPage != null && this.mLoadingUrl != null && this.mLoadingUrl != null) {
            this.mWebPage.post(new Runnable(this) {
                final /* synthetic */ WebBrowserFragment a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.mWebPage.b(this.a.mLoadingUrl);
                }
            });
            this.lastRequestUrl = this.mLoadingUrl;
        }
    }

    public void loadJavascript(String str) {
        this.mWebPage.b(str);
    }

    public void clearHistory() {
        this.isNeedClear = true;
    }

    public void jumpSreach(String str) {
        this.mHandler.obtainMessage(http.Internal_Server_Error, str).sendToTarget();
    }

    public void refresh() {
        this.mFirstSectionLoadTime = -1;
        this.mWebPage.stopLoading();
        this.mWebPage.reload();
    }

    public void onResume() {
        com.qq.reader.common.offline.a.a(this.mContext).a((a) this);
        com.qq.reader.common.offline.c.a(this.mContext).a(this.mHandler, this.NAME);
        if (d.az(this.mContext)) {
            this.mWebPage.clearCache(false);
            d.x(this.mContext, false);
        }
        reload();
        super.onResume();
    }

    public void onPause() {
        super.onPause();
        com.qq.reader.common.offline.a.a(this.mContext).a();
    }

    protected void doClear(WebView webView) {
        if (this.isNeedClear) {
            webView.clearHistory();
        }
    }

    public String getDestUrl() {
        return this.destUrl;
    }

    public void setDestUrl(String str) {
        this.destUrl = str;
    }

    public boolean slideRightAction() {
        return false;
    }

    public boolean slideLeftAction() {
        return true;
    }

    protected boolean handleMessageImp(Message message) {
        switch (message.what) {
            case 65537:
                refreshBookShelf();
                return true;
            case 90004:
                b bVar = (b) message.obj;
                this.mWebPage.a("javascript:" + bVar.a() + "(" + bVar.b() + ")");
                if (this.mFirstSectionLoadTime == -1) {
                    this.mFirstSectionLoadTime = System.currentTimeMillis() - this.mPageStartTime;
                    if (bVar.b().contains("httpcode:")) {
                        i.a("event_offline_page_firstsection_show", false, 0, 0, null, ReaderApplication.getApplicationImp());
                    } else {
                        i.a("event_offline_page_firstsection_show", true, this.mFirstSectionLoadTime, 0, null, ReaderApplication.getApplicationImp());
                    }
                }
                return true;
            default:
                return super.handleMessageImp(message);
        }
    }

    protected View initFragmentUI(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.weblayout, null);
        this.mInputMethodManager = (InputMethodManager) getActivity().getSystemService("input_method");
        this.mLoadProgress = (com.qq.reader.view.ProgressBar) inflate.findViewById(R.id.webloadprogress);
        this.mWebPage = (FixedWebView) inflate.findViewById(R.id.webview);
        this.mWebPage.setScrollBarStyle(SigType.WLOGIN_DA2);
        this.mDefaultProgress = (ProgressBar) inflate.findViewById(R.id.default_progress);
        this.mDefaultTextView = (TextView) inflate.findViewById(R.id.default_loading_text);
        autoSetZoom();
        disableAccessibility();
        return inflate;
    }

    protected void bindWebViewClient() {
        this.mWebPage.setDownloadListener(new DownloadListener(this) {
            final /* synthetic */ WebBrowserFragment a;

            {
                this.a = r1;
            }

            public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
                if (this.a.isAdded()) {
                    this.a.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                }
            }
        });
        this.mWebPage.setWebViewClient(new WebViewClient(this) {
            final /* synthetic */ WebBrowserFragment a;

            {
                this.a = r1;
            }

            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                f.d("zxc", "shouldOverrideUrlLoading " + str);
                if (str.startsWith("about")) {
                    return false;
                }
                if (this.a.mJsEx.a(webView, str)) {
                    return true;
                }
                if (com.qq.reader.qurl.c.b(str)) {
                    try {
                        com.qq.reader.qurl.c.a(this.a.getActivity(), str);
                        return true;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return true;
                    }
                }
                webView.loadUrl(str);
                return true;
            }

            public void onReceivedError(WebView webView, int i, String str, String str2) {
                com.qq.reader.common.monitor.a.a().a(str2, i, str);
                if (this.a.isRetry) {
                    webView.loadUrl(com.qq.reader.appconfig.e.a(1));
                    return;
                }
                webView.loadUrl(str2);
                this.a.isRetry = true;
            }

            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                if (this.a.mJsEx.a(webView, str)) {
                    if (!(str == null || str.contains("/web_error.html"))) {
                        this.a.lastRequestUrl = str;
                    }
                    this.a.mIsloading = true;
                    this.a.mPageStartTime = System.currentTimeMillis();
                }
                this.a.lastRequestUrl = str;
                this.a.mIsloading = true;
                this.a.mPageStartTime = System.currentTimeMillis();
            }

            public void onPageFinished(WebView webView, String str) {
                com.qq.reader.common.monitor.a.a().a(str);
                this.a.mIsloading = false;
                if (this.a.isNeedClearHistory) {
                    this.a.mWebPage.clearHistory();
                    this.a.isNeedClearHistory = false;
                }
                if (this.a.mFragmentLoadListener != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("title", this.a.mWebPage.getTitle());
                    this.a.mFragmentLoadListener.a(bundle);
                }
            }

            public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
                return this.a.getResource(str);
            }

            public void onLoadResource(WebView webView, String str) {
                super.onLoadResource(webView, str);
                if (this.a.mWebPage.getVisibility() == 4) {
                    this.a.mDefaultProgress.setVisibility(8);
                    this.a.mDefaultTextView.setVisibility(8);
                    this.a.mWebPage.setVisibility(0);
                }
            }
        });
    }

    protected void bindWebChromeClient() {
        this.mWebPage.setWebChromeClient(new WebChromeClient(this) {
            final /* synthetic */ WebBrowserFragment a;

            {
                this.a = r1;
            }

            public void onProgressChanged(WebView webView, int i) {
                this.a.mLoadProgress.setProgress((double) i);
                com.qq.reader.common.monitor.a.a().a(i, this.a.getApplicationContext());
            }
        });
    }

    public void onDownloadFinished() {
    }

    public void onCopyFinished() {
        loadUrl(this.mUrl);
    }

    public void onPreLoad() {
        HashMap hashArguments = getHashArguments();
        if (hashArguments != null) {
            String str = (String) hashArguments.get(com.qq.reader.common.c.a.cO);
            this.mUrl = str;
            this.NAME = str;
        }
        bindJavaScript(this.mWebPage);
        bindWebViewClient();
        bindWebChromeClient();
        com.qq.reader.common.offline.a.a(this.mContext).a((a) this);
        com.qq.reader.common.offline.c.a(this.mContext).a(this.mHandler, this.NAME);
    }

    public void onLoading() {
        if (this.mUrl != null && !this.mUrl.equalsIgnoreCase("")) {
            String str = this.mUrl;
            String a = com.qq.reader.common.login.c.c().a(this.mContext);
            if ((a.length() <= 0 && str.indexOf("usid=") != -1) || ((a.length() > 0 && str.indexOf("usid=") == -1) || !(str.indexOf("usid=") == -1 || a.equals(com.qq.reader.appconfig.e.c(str))))) {
                str = com.qq.reader.appconfig.e.a(str, a);
            }
            this.mUrl = str;
            loadUrl(this.mUrl);
        }
    }

    public void onLoadFinished() {
    }

    public boolean stop() {
        if (!this.mIsloading) {
            return false;
        }
        this.mWebPage.stopLoading();
        return true;
    }

    public WebView getWebView() {
        return this.mWebPage;
    }

    protected boolean isNeedForceRefresh() {
        return false;
    }
}
