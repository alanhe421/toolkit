package com.qq.reader.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.webkit.DownloadListener;
import android.webkit.WebBackForwardList;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.monitor.j;
import com.qq.reader.common.offline.OfflineBaseActivity;
import com.qq.reader.common.offline.a.a;
import com.qq.reader.common.readertask.protocol.SearchAutoCompleteTask;
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
import com.qq.reader.view.AlertDialog;
import com.qq.reader.view.FixedWebView;
import com.qq.reader.view.af;
import com.qq.reader.view.linearmenu.b;
import com.qq.reader.view.web.ListenToInputMethodView;
import com.qq.reader.view.web.d;
import com.qq.reader.view.web.e;
import com.tencent.android.tpush.common.Constants;
import com.tencent.feedback.proguard.R;
import com.tencent.mobileqq.openpay.constants.OpenConstants;
import com.tencent.qalsdk.im_open.http;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;

public class WebBrowser extends OfflineBaseActivity implements OnTouchListener, a, c, e {
    private String A = null;
    private volatile boolean B = false;
    private boolean C = false;
    private boolean D = false;
    private volatile boolean E = false;
    private ProgressBar F;
    private TextView G;
    private String H = null;
    private InputMethodManager I = null;
    private int J = 0;
    private String K = "";
    private volatile Handler L;
    private String M = "WEBBROWSER";
    private int N;
    private SearchAutoCompleteTask O;
    private String P;
    private boolean Q;
    private long R;
    private boolean S = false;
    private String T = "";
    private b U;
    private Handler V = new Handler(this) {
        final /* synthetic */ WebBrowser a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    try {
                        Iterator it = this.a.s.iterator();
                        while (it.hasNext()) {
                            this.a.b.a((String) it.next());
                        }
                        this.a.j.onWindowFocusChanged(true);
                        this.a.j.setAdapter(this.a.b);
                        this.a.b.notifyDataSetChanged();
                        if (!this.a.j.isPopupShowing()) {
                            this.a.j.performCompletion();
                            this.a.j.showDropDown();
                        }
                        if (message.obj != null) {
                            this.a.j.setText((String) message.obj);
                        }
                        Editable text = this.a.j.getText();
                        if (text != null) {
                            this.a.j.setSelection(text.length());
                        }
                        this.a.b.getFilter().filter("");
                        return;
                    } catch (Exception e) {
                        return;
                    }
                case 2:
                    try {
                        this.a.j.dismissDropDown();
                    } catch (Exception e2) {
                    }
                    this.a.b.a();
                    return;
                default:
                    return;
            }
        }
    };
    private com.qq.reader.view.web.b W;
    private String X;
    private View Y;
    boolean a = false;
    d<String> b;
    String[] c = new String[]{"书名", "作者"};
    String d = "请输入书名或者作者名";
    protected final int e = 305;
    protected final int f = 304;
    private Context g;
    private ViewGroup h;
    private String i = "";
    private AutoCompleteTextView j;
    private ArrayList<String> s;
    private ImageButton t;
    private ImageButton u;
    private com.qq.reader.view.ProgressBar v;
    private View w;
    private ListenToInputMethodView x;
    private long y;
    private long z = -1;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.g = getApplicationContext();
        setContentView(R.layout.webpage);
        this.L = getHandler();
        j();
        super.C();
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra(com.qq.reader.common.c.a.cO);
        this.k.clearCache(true);
        int intExtra = intent.getIntExtra(com.qq.reader.common.c.a.cP, -1);
        if (intExtra < 0 || intExtra > 2) {
            this.N = 0;
            this.P = b(a(0));
            this.M = "INDEX";
        } else {
            this.N = intExtra;
            this.P = b(a(intExtra));
        }
        a(this.k);
        k();
        l();
        com.qq.reader.common.offline.a.a(this.g).a((a) this);
        com.qq.reader.common.offline.c.a(this.g).a(this.L, this.M);
        this.D = false;
        if (stringExtra == null) {
            a(this.P);
        } else {
            this.E = true;
        }
        this.isReady2Show = false;
    }

    protected void onDestroy() {
        this.p.a();
        super.onDestroy();
    }

    private void m() {
        if (this.u != null) {
            if (this.j.getText().toString().length() > 0) {
                this.u.setVisibility(0);
            } else {
                this.u.setVisibility(4);
            }
        }
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0 && getCurrentFocus() != null) {
            this.I.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return super.onTouchEvent(motionEvent);
    }

    public boolean onTrackballEvent(MotionEvent motionEvent) {
        if (!(motionEvent.getAction() != 2 || this.j == null || this.j.hasFocus() || this.I == null || !this.I.isActive())) {
            this.I.hideSoftInputFromWindow(this.j.getWindowToken(), 0);
        }
        return super.onTrackballEvent(motionEvent);
    }

    private void e(String str) {
    }

    public void a() {
        if (this.k.getSettings().getUseWideViewPort()) {
            this.k.setInitialScale(25);
        }
    }

    private void n() {
        Intent intent = new Intent();
        intent.setClass(this, OnlineHistoryActivity.class);
        com.qq.reader.common.utils.c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        startActivityForResult(intent, Constants.ERRORCODE_UNKNOWN);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i == Constants.ERRORCODE_UNKNOWN && i2 == -1) {
            ((MainActivity) getParent()).a("bookweb_recommend_tab");
        } else if (i != 20001) {
        } else {
            if (i2 == 0) {
                g();
            } else if (i2 == 2) {
                af.a((Context) this, (CharSequence) "订单已经取消,未完成充值", 0).a();
            } else {
                af.a((Context) this, (CharSequence) "充值失败", 0).a();
            }
        }
    }

    protected void onPrepareDialog(int i, Dialog dialog) {
        super.onPrepareDialog(i, dialog);
    }

    protected void a(WebView webView) {
        super.a(webView);
        this.p.a(new JSDownLoad(this), "downloadbook");
        this.p.a(new JSReadOnline(this), "readonline");
        com.qq.reader.common.web.js.a.a.b jSLogin = new JSLogin(this);
        jSLogin.setLoginListener(this);
        this.p.a(jSLogin, "readerlogin");
        this.p.a(new JSContent(this), "JSContent");
        this.p.a(new JSUpdate(this), "JSUpdate");
        this.p.a(new JSSendSMS(this), "sendvip");
        this.p.a(new JSPay(this, this.k), OpenConstants.API_NAME_PAY);
        this.p.a(new JSToast(this), "JSToast");
        this.p.a(new JSReload(this, this), "JSReload");
        this.p.a(new JSGoToWeb(this), "JSGoToWeb");
        this.p.a(new JSReadMusicOnline(this), "readmusiconline");
        this.p.a(new JSAPP(this), "JSApp");
        this.p.a(new JSAddToBookShelf(this), "JSAddToShelf");
        this.p.a(new JSBookDir(this), "bookdir");
        this.p.a(new JSSearch(this), "JSSearch");
        this.p.a(new JSOfflineInterface(this.g, this.L, this.M), "mclient");
        this.p.a(new JSbookshelf(this), "JSbookshelf");
    }

    protected void b() {
        if (this.L != null && !this.L.hasMessages(65537)) {
            Message obtain = Message.obtain();
            obtain.what = 65537;
            this.L.sendMessageDelayed(obtain, 100);
        }
    }

    public void retry() {
        this.k.post(new Runnable(this) {
            final /* synthetic */ WebBrowser a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.z = -1;
                if (this.a.k.copyBackForwardList().getCurrentItem().getUrl().contains("/web_error.html") && this.a.T != null && this.a.T.trim().length() > 0) {
                    this.a.k.b(this.a.T);
                }
            }
        });
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        this.isReady2Show = true;
        if (i == 4) {
            if (this.B) {
                this.k.stopLoading();
            } else {
                j.a(3, 2);
                ((MainActivity) getParent()).a("bookstand_tab");
            }
            return true;
        } else if (i != 82) {
            return super.onKeyDown(i, keyEvent);
        } else {
            if (!this.isReady2Show) {
                return false;
            }
            if (this.w.getVisibility() == 0) {
                this.w.setVisibility(8);
                this.Y.setVisibility(0);
                if (this.I != null && this.I.isActive()) {
                    this.I.hideSoftInputFromWindow(this.j.getWindowToken(), 0);
                }
                this.j.dismissDropDown();
                return true;
            }
            f().f_();
            return true;
        }
    }

    public void reload() {
        WebBackForwardList copyBackForwardList = this.k.copyBackForwardList();
        if (copyBackForwardList != null && copyBackForwardList.getSize() > 0) {
            String url = copyBackForwardList.getCurrentItem().getUrl();
            String a = com.qq.reader.common.login.c.c().a(this.g);
            try {
                if ((a.length() <= 0 && url.indexOf("usid=") != -1) || ((a.length() > 0 && url.indexOf("usid=") == -1) || !(url.indexOf("usid=") == -1 || a.equals(com.qq.reader.appconfig.e.c(url))))) {
                    url = com.qq.reader.appconfig.e.a(url, a);
                    if (this.A != null && this.A.length() > 0) {
                        int indexOf = this.A.indexOf(35);
                        if (indexOf != -1) {
                            int indexOf2 = this.A.indexOf(38, indexOf);
                            if (indexOf2 == -1) {
                                url = url + this.A.substring(indexOf);
                            } else {
                                url = url + this.A.substring(indexOf, indexOf2);
                            }
                        }
                        this.A = null;
                    }
                    this.k.clearView();
                    if (url != null) {
                        a(url.substring(url.lastIndexOf("/") + 1, url.length()));
                    }
                    this.k.clearHistory();
                    return;
                }
            } catch (Exception e) {
                f.a("error", "reload : " + e.toString());
            }
        }
        if (this.Q) {
            this.k.post(new Runnable(this) {
                final /* synthetic */ WebBrowser a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.z = -1;
                    this.a.k.reload();
                    this.a.Q = false;
                }
            });
        }
    }

    public void a(String str) {
        this.X = i(str);
        if (this.k != null && this.X != null && this.X != null) {
            this.k.post(new Runnable(this) {
                final /* synthetic */ WebBrowser a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.k.b(this.a.X);
                }
            });
            this.T = this.X;
        }
    }

    public int a(int i) {
        this.J = i;
        return this.J;
    }

    public String b(int i) {
        switch (i) {
            case 0:
                this.M = "INDEX";
                return "index.html?" + com.qq.reader.appconfig.e.b(this.g) + com.qq.reader.appconfig.e.c(this.g);
            case 1:
                this.M = "SORT";
                return "classify.html?" + com.qq.reader.appconfig.e.b(this.g) + com.qq.reader.appconfig.e.c(this.g);
            default:
                return com.qq.reader.appconfig.e.W + com.qq.reader.appconfig.e.b(this.g) + com.qq.reader.appconfig.e.c(this.g);
        }
    }

    protected void c() {
        if (this.h.getVisibility() != 0) {
            this.h.setVisibility(0);
        }
        this.j.requestFocus();
        this.k.clearHistory();
    }

    public void b(final String str) {
        if (str != null && this.h.getVisibility() != 8) {
            runOnUiThread(new Runnable(this) {
                final /* synthetic */ WebBrowser b;

                public void run() {
                    this.b.a = true;
                    this.b.j.setText(str);
                }
            });
        }
    }

    protected void d() {
        if (this.h.getVisibility() != 8) {
            this.h.setVisibility(8);
        }
    }

    public void c(String str) {
        this.L.obtainMessage(http.Internal_Server_Error, str).sendToTarget();
    }

    protected void e() {
        String trim = this.j.getText().toString().trim();
        if (trim == null || trim.length() <= 0) {
            af.a(this.g, (CharSequence) "请先输入搜索关键词", 0).a();
            return;
        }
        if (this.I != null && this.I.isActive()) {
            this.I.hideSoftInputFromWindow(this.j.getWindowToken(), 0);
        }
        if (this.j.hasFocus()) {
            this.j.clearFocus();
        }
        d();
        this.w.setVisibility(8);
        this.Y.setVisibility(0);
        trim = "/search.html?" + com.qq.reader.appconfig.e.b(this.g) + "&" + "key=" + URLEncoder.encode(trim);
        this.H = trim;
        Intent intent = new Intent();
        intent.setClass(this, WebBrowserForContents.class);
        intent.setFlags(SigType.WLOGIN_QRPUSH);
        intent.putExtra("com.qq.reader.WebContent", trim);
        com.qq.reader.common.utils.c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        startActivity(intent);
    }

    public com.qq.reader.view.linearmenu.a f() {
        this.U = new b(this);
        this.U.a(0, "刷新", null);
        this.U.a(1, getString(R.string.online_history), null);
        this.U.a(new com.qq.reader.view.linearmenu.a.b(this) {
            final /* synthetic */ WebBrowser a;

            {
                this.a = r1;
            }

            public boolean a(int i, Bundle bundle) {
                this.a.U.cancel();
                return this.a.a(i, bundle);
            }
        });
        this.U.a(new OnCancelListener(this) {
            final /* synthetic */ WebBrowser a;

            {
                this.a = r1;
            }

            public void onCancel(DialogInterface dialogInterface) {
                this.a.getWindow().closeAllPanels();
            }
        });
        return this.U;
    }

    public void g() {
        this.z = -1;
        this.k.reload();
    }

    protected boolean a(int i, Bundle bundle) {
        switch (i) {
            case 0:
                g();
                j.a(1, 2);
                return true;
            case 1:
                n();
                j.a(2, 2);
                return true;
            default:
                return false;
        }
    }

    public void onWindowFocusChanged(boolean z) {
        this.isReady2Show = true;
        super.onWindowFocusChanged(z);
    }

    protected void onResume() {
        this.isReady2Show = false;
        com.qq.reader.common.offline.a.a(this.g).a((a) this);
        com.qq.reader.common.offline.c.a(this.g).a(this.L, this.M);
        if (com.qq.reader.appconfig.a.d.az(this.g)) {
            this.k.clearCache(false);
            com.qq.reader.appconfig.a.d.x(this.g, false);
        }
        this.Q = o();
        if (System.currentTimeMillis() - this.R >= 3600000 && this.M.equalsIgnoreCase("INDEX")) {
            this.Q = true;
        }
        reload();
        super.onResume();
        if (this.M.equalsIgnoreCase("INDEX") && this.S) {
            b();
        }
    }

    private boolean o() {
        return com.qq.reader.common.offline.c.a(this.g).b(this.M);
    }

    protected void onPause() {
        super.onPause();
        if (this.O != null) {
            this.O.unregisterNetTaskListener();
        }
        if (this.L.hasMessages(90004)) {
            com.qq.reader.common.offline.c.a(this.g).a(this.M, true);
        }
        com.qq.reader.common.offline.c.a(this.g).a(this.M);
        com.qq.reader.common.offline.a.a(this.g).a();
    }

    protected Dialog onCreateDialog(int i) {
        switch (i) {
            case 304:
                return new AlertDialog.a(this).c(R.drawable.alert_dialog_icon).a((int) R.string.exit).b((int) R.string.dialog_exit).a((int) R.string.alert_dialog_ok, new OnClickListener(this) {
                    final /* synthetic */ WebBrowser a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        this.a.getParent().finish();
                        this.a.quitAll();
                    }
                }).b((int) R.string.alert_dialog_cancel, new OnClickListener(this) {
                    final /* synthetic */ WebBrowser a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).a();
            default:
                return super.onCreateDialog(i);
        }
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        a(intent);
    }

    public void a(Intent intent) {
        String stringExtra = intent.getStringExtra(com.qq.reader.common.c.a.cO);
        String stringExtra2 = getIntent().getStringExtra(com.qq.reader.common.c.a.cO);
        if (stringExtra2 == null || !stringExtra2.equals(stringExtra)) {
            intent.getIntExtra(com.qq.reader.common.c.a.cQ, 0);
        }
    }

    public void setDestUrl(String str) {
        this.A = str;
    }

    protected boolean handleMessageImp(Message message) {
        switch (message.what) {
            case http.Internal_Server_Error /*500*/:
                CharSequence charSequence = (String) message.obj;
                if (charSequence == null) {
                    charSequence = "";
                }
                c();
                if (charSequence.trim().length() > 0) {
                    this.a = true;
                    this.j.setText(charSequence);
                    e();
                    break;
                }
                break;
            case 501:
                a(com.qq.reader.appconfig.e.a(getApplicationContext(), (String) message.obj, ""), 0, "????");
                break;
            case 65537:
                F();
                break;
            case 90004:
                com.qq.reader.common.offline.b bVar = (com.qq.reader.common.offline.b) message.obj;
                this.k.a("javascript:" + bVar.a() + "(" + bVar.b() + ")");
                if (this.z == -1) {
                    this.z = System.currentTimeMillis() - this.y;
                    if (!bVar.b().contains("httpcode:")) {
                        i.a("event_offline_page_firstsection_show", true, this.z, 0, null, ReaderApplication.getApplicationImp());
                        break;
                    }
                    i.a("event_offline_page_firstsection_show", false, 0, 0, null, ReaderApplication.getApplicationImp());
                    break;
                }
                break;
        }
        return super.handleMessageImp(message);
    }

    public void d(String str) {
        this.L.obtainMessage(501, str).sendToTarget();
    }

    private void a(String str, int i, String str2) {
        if (this.W == null) {
            this.W = new com.qq.reader.view.web.b(this, str, str2);
            this.W.a(new OnCancelListener(this) {
                final /* synthetic */ WebBrowser a;

                {
                    this.a = r1;
                }

                public void onCancel(DialogInterface dialogInterface) {
                    if (this.a.W.k()) {
                        this.a.g();
                    }
                }
            });
        }
        if (this.W != null && !this.W.f()) {
            this.W.a(false);
            this.W.a(str, i);
        }
    }

    public void h() {
        if (this.W != null && this.W.f()) {
            this.W.a(true);
        }
    }

    public void i() {
        this.L.post(new Runnable(this) {
            final /* synthetic */ WebBrowser a;

            {
                this.a = r1;
            }

            public void run() {
                if (this.a.W != null && this.a.W.f()) {
                    this.a.W.cancel();
                }
            }
        });
    }

    protected void j() {
        this.I = (InputMethodManager) getSystemService("input_method");
        this.v = (com.qq.reader.view.ProgressBar) findViewById(R.id.webloadprogress);
        this.k = (FixedWebView) findViewById(R.id.webview);
        this.k.setScrollBarStyle(SigType.WLOGIN_DA2);
        this.F = (ProgressBar) findViewById(R.id.default_progress);
        this.G = (TextView) findViewById(R.id.default_loading_text);
        a();
        this.Y = findViewById(R.id.search_Btn);
        this.h = (ViewGroup) findViewById(R.id.websearchBar);
        this.w = findViewById(R.id.websearching_bg);
        this.w.setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ WebBrowser a;

            {
                this.a = r1;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                this.a.h.setVisibility(8);
                this.a.w.setVisibility(8);
                this.a.Y.setVisibility(0);
                if (this.a.I != null && this.a.I.isActive()) {
                    this.a.I.hideSoftInputFromWindow(this.a.j.getWindowToken(), 0);
                }
                return true;
            }
        });
        this.Y.setOnClickListener(new View.OnClickListener(this) {
            final /* synthetic */ WebBrowser a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.Y.setVisibility(4);
                this.a.w.setVisibility(0);
                this.a.c();
                this.a.L.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass5 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.I.toggleSoftInput(0, 2);
                    }
                });
            }
        });
        this.x = (ListenToInputMethodView) findViewById(R.id.web_browser);
        this.x.setKeyImeListener(new ListenToInputMethodView.a(this) {
            final /* synthetic */ WebBrowser a;

            {
                this.a = r1;
            }

            public boolean a(KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == 4 && this.a.w.getVisibility() == 0) {
                    this.a.w.setVisibility(8);
                    this.a.Y.setVisibility(0);
                }
                return false;
            }
        });
        this.s = new ArrayList();
        this.b = new d(this, R.layout.search_keyword_item);
        this.j = (AutoCompleteTextView) findViewById(R.id.searchBar);
        this.j.setHint(this.d);
        if (com.qq.reader.common.c.a.bU > 480) {
            this.j.setDropDownVerticalOffset(this.g.getResources().getDimensionPixelOffset(R.dimen.common_dp_7));
        } else {
            this.j.setDropDownVerticalOffset(this.g.getResources().getDimensionPixelOffset(R.dimen.common_dp_7_5));
        }
        this.j.setDropDownBackgroundResource(R.drawable.autocomplete_dropdown_bg);
        this.j.setText(this.i);
        if (this.j != null) {
            this.j.setSelection(this.i.length());
        }
        this.j.setThreshold(1);
        this.j.setOnTouchListener(new OnTouchListener(this) {
            int a = -1;
            long b = -1;
            final /* synthetic */ WebBrowser c;

            {
                this.c = r3;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case 0:
                        this.b = System.currentTimeMillis();
                        this.a = 1000;
                        break;
                    case 1:
                        long currentTimeMillis = System.currentTimeMillis() - this.b;
                        if (this.a == 1000 && currentTimeMillis < ((long) ViewConfiguration.getLongPressTimeout())) {
                            this.c.w.setVisibility(0);
                            break;
                        }
                    case 3:
                        break;
                    case 4:
                        this.a = -1;
                        break;
                }
                this.a = -1;
                if (motionEvent.getAction() == 3) {
                    this.c.I.hideSoftInputFromWindow(this.c.getCurrentFocus().getWindowToken(), 0);
                    this.c.I.showSoftInput(view, 0);
                }
                return false;
            }
        });
        this.j.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ WebBrowser a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (this.a.b != null && this.a.b.getCount() > i) {
                    String str = (String) this.a.b.getItem(i);
                    this.a.i = str.trim();
                    this.a.b.a();
                    this.a.j.setText(str.trim());
                    this.a.e();
                }
            }
        });
        this.j.addTextChangedListener(new TextWatcher(this) {
            final /* synthetic */ WebBrowser a;

            {
                this.a = r1;
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                this.a.m();
                if (this.a.a) {
                    this.a.a = false;
                } else if (this.a.i == null || !this.a.i.equals(charSequence.toString())) {
                    this.a.i = charSequence.toString();
                    if (this.a.i.length() >= 1 && charSequence != null && charSequence.toString().trim().length() > 0) {
                        this.a.e(charSequence.toString().trim());
                    }
                }
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                this.a.b.a();
            }
        });
        this.j.setOnEditorActionListener(new OnEditorActionListener(this) {
            final /* synthetic */ WebBrowser a;

            {
                this.a = r1;
            }

            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                switch (i) {
                    case 0:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                        this.a.e();
                        return true;
                    default:
                        return false;
                }
            }
        });
        this.t = (ImageButton) findViewById(R.id.searchBtn);
        this.t.setOnClickListener(new com.qq.reader.module.bookstore.qnative.c.c(this) {
            final /* synthetic */ WebBrowser a;

            {
                this.a = r1;
            }

            public void a(View view) {
                this.a.e();
            }
        });
        this.u = (ImageButton) findViewById(R.id.clearTextBtn);
        this.u.setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ WebBrowser a;

            {
                this.a = r1;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 1) {
                    this.a.j.setText("");
                    this.a.i = "";
                }
                return false;
            }
        });
        m();
    }

    protected void k() {
        this.k.setDownloadListener(new DownloadListener(this) {
            final /* synthetic */ WebBrowser a;

            {
                this.a = r1;
            }

            public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
                this.a.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
            }
        });
        this.k.setWebViewClient(new WebViewClient(this) {
            final /* synthetic */ WebBrowser a;

            {
                this.a = r1;
            }

            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                if (str.startsWith("about")) {
                    return false;
                }
                if (this.a.p.a(webView, str)) {
                    return true;
                }
                if (com.qq.reader.qurl.c.b(str)) {
                    try {
                        com.qq.reader.qurl.c.a(this.a, str);
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
                if (this.a.D) {
                    webView.loadUrl(com.qq.reader.appconfig.e.a(1));
                    return;
                }
                webView.loadUrl(str2);
                this.a.D = true;
            }

            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                if (this.a.p.a(webView, str)) {
                    if (!(str == null || str.contains("/web_error.html"))) {
                        this.a.T = str;
                    }
                    this.a.B = true;
                    if (this.a.v.getVisibility() != 0) {
                        this.a.v.setVisibility(0);
                    }
                    this.a.y = System.currentTimeMillis();
                }
                this.a.T = str;
                this.a.B = true;
                if (this.a.v.getVisibility() != 0) {
                    this.a.v.setVisibility(0);
                }
                this.a.y = System.currentTimeMillis();
            }

            public void onPageFinished(WebView webView, String str) {
                com.qq.reader.common.monitor.a.a().a(str);
                this.a.B = false;
                if (this.a.v.getVisibility() != 4) {
                    this.a.v.setVisibility(4);
                }
                if (this.a.E) {
                    this.a.k.clearHistory();
                    this.a.E = false;
                }
                this.a.S = true;
                this.a.R = System.currentTimeMillis();
            }

            public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
                return this.a.j(str);
            }

            public void onLoadResource(WebView webView, String str) {
                super.onLoadResource(webView, str);
                if (this.a.k.getVisibility() == 4) {
                    this.a.F.setVisibility(8);
                    this.a.G.setVisibility(8);
                    this.a.k.setVisibility(0);
                }
            }
        });
    }

    protected void l() {
        this.k.setWebChromeClient(new WebChromeClient(this) {
            final /* synthetic */ WebBrowser a;

            {
                this.a = r1;
            }

            public void onProgressChanged(WebView webView, int i) {
                this.a.v.setProgress((double) i);
                com.qq.reader.common.monitor.a.a().a(i, this.a.getApplicationContext());
            }
        });
    }
}
