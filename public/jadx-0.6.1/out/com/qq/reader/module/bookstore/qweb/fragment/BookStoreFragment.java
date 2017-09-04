package com.qq.reader.module.bookstore.qweb.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.webkit.DownloadListener;
import android.webkit.WebBackForwardList;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.activity.BookStoreActivity;
import com.qq.reader.activity.MainActivity;
import com.qq.reader.activity.OnlineHistoryActivity;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.monitor.j;
import com.qq.reader.common.offline.a.a;
import com.qq.reader.common.web.c;
import com.qq.reader.common.web.js.JSAPP;
import com.qq.reader.common.web.js.JSAddToBookShelf;
import com.qq.reader.common.web.js.JSBookDir;
import com.qq.reader.common.web.js.JSContent;
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
import com.qq.reader.common.web.js.JSToast;
import com.qq.reader.common.web.js.JSUpdate;
import com.qq.reader.common.web.js.JSbookshelf;
import com.qq.reader.common.web.js.JsAdEvent;
import com.qq.reader.common.web.js.JsSubscribe;
import com.qq.reader.view.FixedWebView;
import com.qq.reader.view.af;
import com.qq.reader.view.web.ListenToInputMethodView;
import com.qq.reader.view.web.b;
import com.qq.reader.view.web.e;
import com.tencent.android.tpush.common.Constants;
import com.tencent.feedback.proguard.R;
import com.tencent.mobileqq.openpay.constants.OpenConstants;
import com.tencent.qalsdk.im_open.http;
import java.util.Timer;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;

public class BookStoreFragment extends OfflineBaseFragment implements OnTouchListener, a, c, com.qq.reader.common.web.js.a, e {
    public static final int MENU_ID_ABOUT = 2;
    public static final int MENU_ID_EXIT = 3;
    public static final int MENU_ID_ONLINE_HISTORY = 1;
    public static final int MENU_ID_REFRESH = 0;
    private static final String TAG = "BookStoreFragment";
    public static final int WEB_OTHERS = 2;
    public static final int WEB_RECOMMOND = 0;
    public static final int WEB_SORT = 1;
    protected final int DIALOG_CANCEL = 304;
    protected final int DIALOG_ONLINE_HISTORY = 305;
    private String NAME = "WEBBROWSER";
    private String destUrl = null;
    private boolean isNeedClear = false;
    private volatile boolean isNeedClearHistory = false;
    private boolean isRetry = false;
    boolean isTouchWebViewHotWord = false;
    private String lastRequestUrl = "";
    private String mBaseSearchTopUrl = "";
    private volatile Handler mBookHandler;
    private b mBuyDlg;
    private Context mContext;
    private ProgressBar mDefaultProgress;
    private TextView mDefaultTextView;
    private long mFirstSectionLoadTime = -1;
    private InputMethodManager mInputMethodManager = null;
    private boolean mIsLoadingFinish = false;
    private volatile boolean mIsloading = false;
    private JSLogin mJSLogin;
    private ListenToInputMethodView mListenToInputMethodView;
    private com.qq.reader.view.ProgressBar mLoadProgress;
    private String mLoadingUrl;
    private long mOnPauseTime = -1;
    private long mPageStartTime;
    private com.qq.reader.view.linearmenu.b mReaderMenu;
    private Timer mRefreshPageTimer;
    private int mSelectedItem;
    private int mTabTag = 0;
    private int mTitleId = 0;
    private String mUrl;
    private boolean needRefresh;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mContext = getApplicationContext();
        this.mBookHandler = getHandler();
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
        if (this.mWebPage.getSettings().getUseWideViewPort()) {
            this.mWebPage.setInitialScale(25);
        }
    }

    public void onCreateHisDialog() {
        Intent intent = new Intent();
        intent.setClass(getActivity(), OnlineHistoryActivity.class);
        com.qq.reader.common.utils.c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        startActivityForResult(intent, Constants.ERRORCODE_UNKNOWN);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == Constants.ERRORCODE_UNKNOWN && i2 == -1) {
            ((MainActivity) getActivity()).a("bookweb_recommend_tab");
        }
        if (i == 20001) {
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

    protected void bindJavaScript(WebView webView) {
        super.bindJavaScript(webView);
        this.mJsEx.a(new JSDownLoad(getBaseActivity()), "downloadbook");
        this.mJsEx.a(new JSReadOnline(getBaseActivity()), "readonline");
        if (this.mJSLogin == null) {
            this.mJSLogin = new JSLogin(getBaseActivity());
            this.mJSLogin.setLoginListener(this);
            this.mJSLogin.setNextLoginTask(getLoginNextTask());
        }
        this.mJsEx.a(this.mJSLogin, "readerlogin");
        this.mJsEx.a(new JSContent(getBaseActivity()), "JSContent");
        this.mJsEx.a(new JSUpdate(getBaseActivity()), "JSUpdate");
        this.mJsEx.a(new JSSendSMS(getBaseActivity()), "sendvip");
        this.mJsEx.a(new JSPay(getBaseActivity(), this.mWebPage), OpenConstants.API_NAME_PAY);
        this.mJsEx.a(new JSToast(getBaseActivity()), "JSToast");
        this.mJsEx.a(new JSReload(getBaseActivity(), this), "JSReload");
        this.mJsEx.a(new JSGoToWeb(getBaseActivity()), "JSGoToWeb");
        this.mJsEx.a(new JSReadMusicOnline(getBaseActivity()), "readmusiconline");
        this.mJsEx.a(new JSAPP(getBaseActivity()), "JSApp");
        this.mJsEx.a(new JSAddToBookShelf(getBaseActivity()), "JSAddToShelf");
        this.mJsEx.a(new JSBookDir(getBaseActivity()), "bookdir");
        this.mJsEx.a(new JSSearch(getBaseActivity()), "JSSearch");
        this.mJsEx.a(new JSOfflineInterface(getBaseActivity(), this.mBookHandler, this.NAME), "mclient");
        this.mJsEx.a(new JSbookshelf(getBaseActivity()), "JSbookshelf");
        this.mJsEx.a(new JsAdEvent((JsAdEvent.a) getActivity()), "JsAdEvent");
        this.mJsEx.a(new JsSubscribe(this), "JsSubscribe");
    }

    protected void sendRefreshBookShelfMsg() {
        if (this.mBookHandler != null && !this.mBookHandler.hasMessages(65537)) {
            Message obtain = Message.obtain();
            obtain.what = 65537;
            this.mBookHandler.sendMessageDelayed(obtain, 100);
        }
    }

    public void retry() {
        this.mWebPage.post(new Runnable(this) {
            final /* synthetic */ BookStoreFragment a;

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

    public void goBack() {
        this.mWebPage.goBack();
        this.mWebPage.invalidate();
    }

    public void reload() {
        WebBackForwardList copyBackForwardList = this.mWebPage.copyBackForwardList();
        if (copyBackForwardList != null && copyBackForwardList.getSize() > 0) {
            String url = copyBackForwardList.getCurrentItem().getUrl();
            String a = com.qq.reader.common.login.c.c().a(this.mContext);
            try {
                if ((a.length() <= 0 && url.indexOf("usid=") != -1) || ((a.length() > 0 && url.indexOf("usid=") == -1) || !(url.indexOf("usid=") == -1 || a.equals(com.qq.reader.appconfig.e.c(url))))) {
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
                    if (url != null) {
                        loadUrl(url.substring(url.lastIndexOf("/") + 1, url.length()));
                    }
                    this.mWebPage.clearHistory();
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
                f.a("error", "reload : " + e);
            }
        }
        if (this.needRefresh) {
            this.mWebPage.post(new Runnable(this) {
                final /* synthetic */ BookStoreFragment a;

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

    public void goDefaultSearchPage() {
        if (this.mBaseSearchTopUrl != null && this.mBaseSearchTopUrl.length() > 0) {
            this.mWebPage.b(this.mBaseSearchTopUrl);
        }
    }

    public void loadUrl(String str) {
        this.mLoadingUrl = getLoadUrl(str);
        if (this.mWebPage != null && this.mLoadingUrl != null && this.mLoadingUrl != null) {
            this.mWebPage.post(new Runnable(this) {
                final /* synthetic */ BookStoreFragment a;

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

    public int reSetTab(int i) {
        this.mTabTag = i;
        return this.mTabTag;
    }

    public String getUrlFromCurTab(int i) {
        switch (i) {
            case 0:
                this.NAME = "INDEX";
                return "index.html?" + com.qq.reader.appconfig.e.b(this.mContext) + com.qq.reader.appconfig.e.c(this.mContext);
            case 1:
                this.NAME = "SORT";
                return "classify.html?" + com.qq.reader.appconfig.e.b(this.mContext) + com.qq.reader.appconfig.e.c(this.mContext);
            default:
                return com.qq.reader.appconfig.e.W + com.qq.reader.appconfig.e.b(this.mContext) + com.qq.reader.appconfig.e.c(this.mContext);
        }
    }

    public void clearHistory() {
        this.isNeedClear = true;
    }

    public void jumpSreach(String str) {
        this.mBookHandler.obtainMessage(http.Internal_Server_Error, str).sendToTarget();
    }

    public com.qq.reader.view.linearmenu.a getMenu() {
        this.mReaderMenu = new com.qq.reader.view.linearmenu.b(getActivity());
        this.mReaderMenu.a(0, "刷新", null);
        this.mReaderMenu.a(1, getString(R.string.online_history), null);
        this.mReaderMenu.a(new com.qq.reader.view.linearmenu.a.b(this) {
            final /* synthetic */ BookStoreFragment a;

            {
                this.a = r1;
            }

            public boolean a(int i, Bundle bundle) {
                this.a.mReaderMenu.cancel();
                return this.a.menuSelected(i, bundle);
            }
        });
        this.mReaderMenu.a(new OnCancelListener(this) {
            final /* synthetic */ BookStoreFragment a;

            {
                this.a = r1;
            }

            public void onCancel(DialogInterface dialogInterface) {
                this.a.getActivity().getWindow().closeAllPanels();
            }
        });
        return this.mReaderMenu;
    }

    public void refresh() {
        this.mFirstSectionLoadTime = -1;
        this.needRefresh = true;
        reload();
    }

    public void onPayDone(int i, String str) {
        refresh();
    }

    private com.qq.reader.common.login.a getLoginNextTask() {
        return new com.qq.reader.common.login.a(this) {
            final /* synthetic */ BookStoreFragment a;

            {
                this.a = r1;
            }

            public void a(int i) {
                switch (i) {
                    case 1:
                        this.a.refresh();
                        return;
                    default:
                        return;
                }
            }
        };
    }

    protected boolean menuSelected(int i, Bundle bundle) {
        switch (i) {
            case 0:
                refresh();
                j.a(1, 2);
                return true;
            case 1:
                onCreateHisDialog();
                j.a(2, 2);
                return true;
            default:
                return false;
        }
    }

    public void onResume() {
        com.qq.reader.common.offline.a.a(this.mContext).a((a) this);
        com.qq.reader.common.offline.c.a(this.mContext).a(this.mBookHandler, this.NAME);
        if (d.az(this.mContext)) {
            this.mWebPage.clearCache(false);
            d.x(this.mContext, false);
        }
        this.needRefresh = needRefresh();
        if (this.mOnPauseTime != -1 && System.currentTimeMillis() - this.mOnPauseTime >= 3600000) {
            this.needRefresh = true;
        }
        reload();
        super.onResume();
    }

    private boolean needRefresh() {
        return com.qq.reader.common.offline.c.a(this.mContext).b(this.NAME);
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

    public void loadUrlFromIntent(Intent intent) {
        String stringExtra = intent.getStringExtra(com.qq.reader.common.c.a.cO);
        String stringExtra2 = getActivity().getIntent().getStringExtra(com.qq.reader.common.c.a.cO);
        if (stringExtra2 == null || !stringExtra2.equals(stringExtra)) {
            intent.getIntExtra(com.qq.reader.common.c.a.cQ, 0);
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
            case http.Internal_Server_Error /*500*/:
                if (((String) message.obj) == null) {
                    String str = "";
                }
                return true;
            case 501:
                buyChapterYB(com.qq.reader.appconfig.e.a(getApplicationContext(), (String) message.obj, ""), 0, "????");
                return true;
            case 65537:
                refreshBookShelf();
                return true;
            case 90004:
                com.qq.reader.common.offline.b bVar = (com.qq.reader.common.offline.b) message.obj;
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

    public void buyBook(String str) {
        this.mBookHandler.obtainMessage(501, str).sendToTarget();
    }

    private void buyChapterYB(String str, int i, String str2) {
        if (this.mBuyDlg == null) {
            this.mBuyDlg = new b(getActivity(), str, str2);
            this.mBuyDlg.a(new OnCancelListener(this) {
                final /* synthetic */ BookStoreFragment a;

                {
                    this.a = r1;
                }

                public void onCancel(DialogInterface dialogInterface) {
                    if (this.a.mBuyDlg.k()) {
                        this.a.refresh();
                    }
                }
            });
        }
        if (this.mBuyDlg != null && !this.mBuyDlg.f()) {
            this.mBuyDlg.a(false);
            this.mBuyDlg.a(str, i);
        }
    }

    public void paySuccess() {
        if (this.mBuyDlg != null && this.mBuyDlg.f()) {
            this.mBuyDlg.a(true);
        }
    }

    public void cancelDlg() {
        this.mBookHandler.post(new Runnable(this) {
            final /* synthetic */ BookStoreFragment a;

            {
                this.a = r1;
            }

            public void run() {
                if (this.a.mBuyDlg != null && this.a.mBuyDlg.f()) {
                    this.a.mBuyDlg.cancel();
                }
            }
        });
    }

    protected View initFragmentUI(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.bookstore_page_item, null);
        this.mInputMethodManager = (InputMethodManager) getActivity().getSystemService("input_method");
        this.mLoadProgress = (com.qq.reader.view.ProgressBar) inflate.findViewById(R.id.webloadprogress);
        this.mWebPage = (FixedWebView) inflate.findViewById(R.id.webview);
        this.mWebPage.setScrollBarStyle(SigType.WLOGIN_DA2);
        this.mDefaultProgress = (ProgressBar) inflate.findViewById(R.id.default_progress);
        this.mDefaultTextView = (TextView) inflate.findViewById(R.id.default_loading_text);
        autoSetZoom();
        this.mListenToInputMethodView = (ListenToInputMethodView) inflate.findViewById(R.id.web_browser);
        this.mListenToInputMethodView.setKeyImeListener(new ListenToInputMethodView.a(this) {
            final /* synthetic */ BookStoreFragment a;

            {
                this.a = r1;
            }

            public boolean a(KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == 4) {
                }
                return false;
            }
        });
        return inflate;
    }

    protected void bindWebViewClient() {
        this.mWebPage.setDownloadListener(new DownloadListener(this) {
            final /* synthetic */ BookStoreFragment a;

            {
                this.a = r1;
            }

            public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
                this.a.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
            }
        });
        this.mWebPage.setWebViewClient(new WebViewClient(this) {
            final /* synthetic */ BookStoreFragment a;

            {
                this.a = r1;
            }

            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
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
                try {
                    if (this.a.isRetry) {
                        webView.loadUrl(com.qq.reader.appconfig.e.a(1));
                        return;
                    }
                    webView.loadUrl(str2);
                    this.a.isRetry = true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                if (this.a.mJsEx.a(webView, str)) {
                    if (!(str == null || str.contains("/web_error.html"))) {
                        this.a.lastRequestUrl = str;
                    }
                    this.a.mIsloading = true;
                    if (this.a.mLoadProgress.getVisibility() == 0) {
                        this.a.mPageStartTime = System.currentTimeMillis();
                    } else {
                        this.a.mPageStartTime = System.currentTimeMillis();
                    }
                }
                this.a.lastRequestUrl = str;
                this.a.mIsloading = true;
                if (this.a.mLoadProgress.getVisibility() == 0) {
                    this.a.mPageStartTime = System.currentTimeMillis();
                } else {
                    this.a.mPageStartTime = System.currentTimeMillis();
                }
            }

            public void onPageFinished(WebView webView, String str) {
                com.qq.reader.common.monitor.a.a().a(str);
                this.a.mIsloading = false;
                if (this.a.mLoadProgress.getVisibility() != 4) {
                }
                if (this.a.isNeedClearHistory) {
                    this.a.mWebPage.clearHistory();
                    this.a.isNeedClearHistory = false;
                }
                this.a.mIsLoadingFinish = true;
                this.a.mOnPauseTime = System.currentTimeMillis();
            }

            public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
                return this.a.getResource(str);
            }

            public void onLoadResource(WebView webView, String str) {
                super.onLoadResource(webView, str);
            }
        });
    }

    protected void bindWebChromeClient() {
        this.mWebPage.setWebChromeClient(new WebChromeClient(this) {
            final /* synthetic */ BookStoreFragment a;

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
        bindWebViewClient();
        bindWebChromeClient();
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mUrl = (String) arguments.get(com.qq.reader.common.c.a.cO);
            this.NAME = arguments.getString("titlename");
            this.mUrl = getRightUrl(this.mUrl);
        }
        com.qq.reader.common.offline.a.a(this.mContext).a((a) this);
        com.qq.reader.common.offline.c.a(this.mContext).a(this.mBookHandler, this.NAME);
        bindJavaScript(this.mWebPage);
        this.isRetry = false;
    }

    private String getRightUrl(String str) {
        if (str.contains("?")) {
            return str + "&" + com.qq.reader.appconfig.e.b(this.mContext) + com.qq.reader.appconfig.e.c(this.mContext);
        }
        return str + "?" + com.qq.reader.appconfig.e.b(this.mContext) + com.qq.reader.appconfig.e.c(this.mContext);
    }

    private String getUrlWithSid(String str) {
        return str + com.qq.reader.appconfig.e.b(this.mContext) + com.qq.reader.appconfig.e.c(this.mContext);
    }

    public void onLoading() {
        if (this.mUrl != null) {
            loadUrl(this.mUrl);
        } else {
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

    public void doSuccess() {
        af.a(getApplicationContext(), (CharSequence) "订阅成功", 0).a();
        ((BookStoreActivity) getActivity()).a();
        refresh();
    }
}
