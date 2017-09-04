package com.qq.reader.activity;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.WebBackForwardList;
import android.webkit.WebChromeClient;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebSettings.TextSize;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.db.handle.r;
import com.qq.reader.common.download.task.l;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.offline.OfflineBaseActivity;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.o;
import com.qq.reader.common.web.c;
import com.qq.reader.common.web.d;
import com.qq.reader.common.web.js.AndroidJS;
import com.qq.reader.common.web.js.JSAPP;
import com.qq.reader.common.web.js.JSAddToBookShelf;
import com.qq.reader.common.web.js.JSBookDir;
import com.qq.reader.common.web.js.JSContent;
import com.qq.reader.common.web.js.JSDetail;
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
import com.qq.reader.common.web.js.JsSubscribe;
import com.qq.reader.cservice.buy.a.b;
import com.qq.reader.cservice.download.book.f;
import com.qq.reader.cservice.download.book.g;
import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import com.qq.reader.module.readpage.q;
import com.qq.reader.plugin.audiobook.MusicAllTag;
import com.qq.reader.plugin.audiobook.MusicDownloadMark;
import com.qq.reader.plugin.audiobook.MusicDownloadTask;
import com.qq.reader.plugin.audiobook.MusicOnlineTag;
import com.qq.reader.plugin.audiobook.j;
import com.qq.reader.plugin.audiobook.k;
import com.qq.reader.view.AlertDialog;
import com.qq.reader.view.FixedWebView;
import com.qq.reader.view.ProgressBar;
import com.qq.reader.view.af;
import com.qq.reader.view.u;
import com.qq.reader.view.u$a;
import com.qq.reader.view.v;
import com.qq.reader.view.web.e;
import com.qq.reader.view.web.h;
import com.qq.reader.view.web.n;
import com.tencent.feedback.proguard.R;
import com.tencent.mobileqq.openpay.constants.OpenConstants;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import com.tencent.tesla.soload.SoLoadCore;
import com.tencent.util.VersionUtils;
import java.lang.reflect.Method;
import java.util.List;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WebBrowserForContents extends OfflineBaseActivity implements com.qq.reader.common.web.a, c, com.qq.reader.common.web.js.JSContent.a, com.qq.reader.common.web.js.a, b, f, u$a, e, com.qq.reader.view.web.n.a {
    private MusicAllTag A = null;
    private com.qq.reader.view.c B;
    private ProgressDialog C;
    private j D;
    private com.qq.reader.plugin.audiobook.b E;
    private com.qq.reader.view.web.b F;
    private d G = null;
    private int H = -1;
    private View I = null;
    private TextView J = null;
    private TextView K = null;
    private TextView L = null;
    private n M = null;
    private u N = null;
    private final int O = 1000;
    private final int P = 1001;
    private final int Q = 1002;
    private int[] R = new int[]{1000, 1001, 1002};
    private String[] S = new String[]{"周榜", "月榜", "总榜"};
    private int T = -1;
    private int U = 0;
    private String[] V = null;
    private String[] W = null;
    private int X = 1000;
    private String[] Y = null;
    private String[] Z = null;
    protected TextView a;
    private String[] aa = null;
    private String[] ab = null;
    private String[] ac = null;
    private String ad = "WEBCONTENTS";
    private OnClickListener ae = null;
    private long af;
    private long ag = -1;
    private boolean ah = false;
    private h ai;
    private Button aj;
    private int ak;
    private FrameLayout al;
    private View am;
    private CustomViewCallback an;
    private WebChromeClient ao;
    private boolean ap = false;
    private boolean aq = false;
    private String ar = "";
    private BroadcastReceiver as = new BroadcastReceiver(this) {
        final /* synthetic */ WebBrowserForContents a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            if (com.qq.reader.common.c.a.cM.equals(intent.getAction())) {
                Message obtainMessage = this.a.getHandler().obtainMessage();
                obtainMessage.what = 508;
                obtainMessage.obj = intent;
                this.a.getHandler().sendMessage(obtainMessage);
            }
        }
    };
    private String at = null;
    private com.qq.reader.view.linearmenu.b au;
    private Handler av = new Handler(this) {
        final /* synthetic */ WebBrowserForContents a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            com.qq.reader.cservice.buy.a.c cVar;
            switch (message.what) {
                case 112:
                    if (this.a.y == null) {
                        this.a.y = (RelativeLayout) this.a.findViewById(R.id.web_browser_content);
                        return;
                    }
                    return;
                case 501:
                    this.a.a(com.qq.reader.appconfig.e.a(this.a.getApplicationContext(), (String) message.obj, ""), 0, "购买");
                    return;
                case 508:
                    Intent intent = (Intent) message.obj;
                    if (intent.getBooleanExtra("success", false)) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("javascript:afterShare(\"").append(intent.getStringExtra("sharedurl")).append("\",true)");
                        this.a.k.b(stringBuilder.toString());
                        return;
                    }
                    return;
                case 1203:
                    if (this.a.V()) {
                        g gVar = (g) message.obj;
                        if (this.a.G != null) {
                            this.a.G.a(gVar.c());
                            if (this.a.G.d() == Long.parseLong(gVar.e())) {
                                this.a.Q();
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    return;
                case 1204:
                case 21001:
                    if (this.a.V()) {
                        af.a(this.a.getApplicationContext(), this.a.getString(R.string.fail_get_downloadfile), 0).a();
                        return;
                    }
                    return;
                case 1205:
                    if (this.a.V()) {
                        this.a.showFragmentDialog(606);
                        return;
                    }
                    return;
                case 1217:
                    this.a.a(String.valueOf(this.a.G.d()), this.a.G.c());
                    return;
                case 1218:
                    i.a("event_B144", null, ReaderApplication.getApplicationImp());
                    if (this.a.X()) {
                        cVar = (com.qq.reader.cservice.buy.a.c) message.obj;
                        try {
                            if (this.a.G != null) {
                                this.a.G.a(cVar.b());
                                if (this.a.G.d() == Long.parseLong(cVar.c())) {
                                    this.a.Q();
                                    return;
                                }
                                return;
                            }
                            return;
                        } catch (Exception e) {
                            e.printStackTrace();
                            return;
                        }
                    }
                    return;
                case 1219:
                    if (this.a.X()) {
                        cVar = (com.qq.reader.cservice.buy.a.c) message.obj;
                        int d = cVar.d();
                        Bundle bundle = new Bundle();
                        bundle.putString("message", cVar.a());
                        if (d == -2) {
                            if (!com.qq.reader.common.login.c.a(this.a, Boolean.valueOf(true))) {
                                com.qq.reader.common.login.c.a();
                                bundle.putString("message", "支付出现问题，请重试");
                                this.a.showFragmentDialog(607, bundle);
                                return;
                            }
                            return;
                        } else if (d == -6) {
                            this.a.showFragmentDialog(608, bundle);
                            return;
                        } else {
                            this.a.showFragmentDialog(607, bundle);
                            return;
                        }
                    }
                    return;
                case 5008:
                    this.a.c.a((CharSequence) "已经添加到下载列表中");
                    this.a.c.a();
                    return;
                case 5009:
                    this.a.c.a((CharSequence) "下载列表中已存有本章节");
                    this.a.c.a();
                    return;
                case 5010:
                    MusicOnlineTag musicOnlineTag = (MusicOnlineTag) message.obj;
                    if (this.a.F == null) {
                        this.a.F = new com.qq.reader.view.web.b(this.a, musicOnlineTag.getBuyUrl(), "购买");
                        this.a.F.a(new OnCancelListener(this) {
                            final /* synthetic */ AnonymousClass10 a;

                            {
                                this.a = r1;
                            }

                            public void onCancel(DialogInterface dialogInterface) {
                                this.a.a.reload();
                            }
                        });
                    }
                    if (this.a.F != null && !this.a.F.f()) {
                        this.a.F.a(musicOnlineTag.getBuyUrl(), 5);
                        return;
                    }
                    return;
                case 5011:
                    this.a.c.a((String) message.obj);
                    this.a.c.a();
                    return;
                case 90004:
                    if (this.a.k != null && message.obj != null) {
                        com.qq.reader.common.offline.b bVar = (com.qq.reader.common.offline.b) message.obj;
                        this.a.k.a("javascript:" + bVar.a() + "(" + bVar.b() + ")");
                        if (this.a.ag == -1) {
                            this.a.ag = System.currentTimeMillis() - this.a.af;
                            if (bVar.b().contains("httpcode:")) {
                                i.a("event_offline_page_firstsection_show", false, 0, 0, null, ReaderApplication.getApplicationImp());
                                return;
                            } else {
                                i.a("event_offline_page_firstsection_show", true, this.a.ag, 0, null, ReaderApplication.getApplicationImp());
                                return;
                            }
                        }
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    };
    private JSLogin aw = new JSLogin(this);
    private com.qq.reader.view.c ax;
    protected ImageView b;
    af c;
    protected ImageView d = null;
    protected ImageView e;
    protected ImageView f;
    protected String g;
    protected String h;
    com.qq.reader.common.widget.viewpager.c i;
    private Context j;
    private ProgressBar s;
    private boolean t = false;
    private boolean u = false;
    private volatile boolean v = false;
    private String w;
    private String x;
    private RelativeLayout y = null;
    private MusicOnlineTag z = null;

    public class a extends WebChromeClient {
        final /* synthetic */ WebBrowserForContents a;

        public a(WebBrowserForContents webBrowserForContents) {
            this.a = webBrowserForContents;
        }

        public void onShowCustomView(View view, CustomViewCallback customViewCallback) {
            this.a.setRequestedOrientation(0);
            this.a.k.setVisibility(8);
            if (this.a.am != null) {
                customViewCallback.onCustomViewHidden();
                return;
            }
            this.a.al.addView(view);
            this.a.am = view;
            this.a.an = customViewCallback;
            this.a.al.setVisibility(0);
        }

        public void onHideCustomView() {
            if (this.a.am != null) {
                this.a.setRequestedOrientation(1);
                this.a.am.setVisibility(8);
                this.a.al.removeView(this.a.am);
                this.a.am = null;
                this.a.al.setVisibility(8);
                this.a.an.onCustomViewHidden();
                this.a.k.setVisibility(0);
            }
        }

        public void onProgressChanged(WebView webView, int i) {
            this.a.s.setProgress((double) i);
            com.qq.reader.common.monitor.a.a().a(i, this.a.getApplicationContext());
        }

        public void onReceivedTitle(WebView webView, String str) {
            super.onReceivedTitle(webView, str);
            if (this.a.s.getVisibility() != 8) {
                this.a.s.setVisibility(8);
            }
            if (!(TextUtils.isEmpty(str) || str.startsWith("iyuedu.qq.com"))) {
                this.a.d(str);
            }
            try {
                Bundle extras = this.a.getIntent().getExtras();
                if (extras != null && extras.getBoolean("com.qq.reader.Need_record_history")) {
                    r.a().a(1, extras.getString("com.qq.reader.WebContent"), str);
                }
            } catch (Exception e) {
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.j = getApplicationContext();
        if (!"Meizu_M040".equals(com.qq.reader.common.c.a.E)) {
            getWindow().addFlags(SigType.WLOGIN_PF);
        }
        setContentView(R.layout.webpage_content);
        com.qq.reader.common.utils.b.a((Activity) this);
        i.a("event_reader_bookstore", null, getApplicationContext());
        B();
        super.C();
        h();
        this.ad = String.valueOf(hashCode());
        com.qq.reader.common.offline.c.a(this.j).a(this.n, this.ad);
        if (com.qq.reader.appconfig.a.d.az(this.j)) {
            this.k.clearCache(false);
            com.qq.reader.appconfig.a.d.x(this.j, false);
        }
        a(this.k);
        d();
        e();
        c();
        this.c = af.a(getApplicationContext(), (CharSequence) "", 0);
        this.ae = new OnClickListener(this) {
            final /* synthetic */ WebBrowserForContents a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.T();
            }
        };
        if (VersionUtils.isKitKat()) {
            try {
            } catch (Throwable th) {
                com.qq.reader.common.monitor.debug.c.e("WebbrowserForContents", th.getMessage());
                return;
            }
        }
        registerReceiver(this.as, new IntentFilter(com.qq.reader.common.c.a.cM));
    }

    private void A() {
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

    public void a(boolean z) {
        this.ap = z;
    }

    public void a(String str) {
        this.ar = str;
    }

    private void B() {
        this.s = (ProgressBar) findViewById(R.id.webprogress);
        this.a = (TextView) findViewById(R.id.profile_header_title);
        if (com.qq.reader.common.c.a.bT <= 1000 && com.qq.reader.common.c.a.bU <= ErrorCode.INFO_STATIC_TBS_INSTALL_ERR_CODE_BASE) {
            this.a.addTextChangedListener(new TextWatcher(this) {
                final /* synthetic */ WebBrowserForContents a;

                {
                    this.a = r1;
                }

                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    if (i3 > 5) {
                        this.a.a.setText(charSequence.subSequence(0, 4) + "…");
                    }
                }

                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                public void afterTextChanged(Editable editable) {
                }
            });
        }
        this.k = (FixedWebView) findViewById(R.id.webview);
        this.k.setScrollBarStyle(SigType.WLOGIN_DA2);
        this.k.getSettings().setTextSize(TextSize.NORMAL);
        if (com.qq.reader.appconfig.b.a && VERSION.SDK_INT >= 19) {
            try {
                Class.forName("android.webkit.WebView").getMethod("setWebContentsDebuggingEnabled", new Class[]{Boolean.TYPE}).invoke(this.k, new Object[]{Boolean.valueOf(true)});
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.b = (ImageView) findViewById(R.id.profile_header_left_back);
        this.b.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ WebBrowserForContents a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (!this.a.ap) {
                    this.a.K();
                } else if (this.a.f()) {
                    this.a.g();
                } else {
                    this.a.K();
                }
            }
        });
        this.f = (ImageView) findViewById(R.id.profile_header_right_collect);
        this.f.setImageResource(R.drawable.titlebar_icon_collect);
        this.f.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ WebBrowserForContents a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (com.qq.reader.common.login.c.b()) {
                    this.a.G();
                    return;
                }
                this.a.mLoginNextTask = new com.qq.reader.common.login.a(this) {
                    final /* synthetic */ AnonymousClass25 a;

                    {
                        this.a = r1;
                    }

                    public void a(int i) {
                        switch (i) {
                            case 1:
                                this.a.a.G();
                                return;
                            default:
                                return;
                        }
                    }
                };
                this.a.startLogin();
            }
        });
        this.e = (ImageView) findViewById(R.id.profile_header_right_image);
        this.e.setImageResource(R.drawable.titlebar_icon_share);
        this.e.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ WebBrowserForContents a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.k != null) {
                    this.a.k.b("javascript:" + this.a.g + "()");
                }
            }
        });
        this.aj = (Button) findViewById(R.id.profile_header_right_button);
        Intent intent = getIntent();
        if (intent != null) {
            Bundle extras = intent.getExtras();
            if (extras != null && extras.containsKey("open_promotion")) {
                this.aq = extras.getBoolean("open_promotion", false);
            }
        }
        this.N = new u(this, (GridView) findViewById(R.id.webpage_gridmenu), new v(this, R.layout.webpage_gridmenu_item_text));
        this.N.a(this);
        this.I = findViewById(R.id.web_detail_btns);
        this.d = (ImageView) findViewById(R.id.profile_header_title_sort);
        this.J = (TextView) findViewById(R.id.web_detail_btn_read);
        this.J.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ WebBrowserForContents a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.P();
                this.a.ah = true;
                com.qq.reader.common.monitor.j.a(this.a.k, 0, 0);
            }
        });
        this.K = (TextView) findViewById(R.id.web_detail_btn_download);
        this.K.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ WebBrowserForContents a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.Q();
                this.a.ah = true;
                com.qq.reader.common.monitor.j.a(this.a.k, 0, 1);
            }
        });
        this.L = (TextView) findViewById(R.id.web_detail_btn_add2shelf);
        this.L.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ WebBrowserForContents a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.R();
                this.a.ah = true;
                com.qq.reader.common.monitor.j.a(this.a.k, 0, 2);
            }
        });
        this.I.setVisibility(8);
        this.al = (FrameLayout) findViewById(R.id.video_view);
        A();
    }

    private void G() {
        if (com.qq.reader.appconfig.a.d.bD(this.j)) {
            this.k.b("javascript:" + this.h + "(" + this.ak + ")");
            return;
        }
        this.k.b("javascript:" + this.h + "(" + this.ak + ", 'fistAdd')");
        com.qq.reader.appconfig.a.d.H(this.j, true);
    }

    protected void b(WebView webView) {
        this.I.setVisibility(8);
        this.J.setVisibility(8);
        this.K.setVisibility(8);
        this.L.setVisibility(8);
        this.N.a(false);
        c(webView);
        this.I.setVisibility(8);
    }

    protected void c(WebView webView) {
        if (webView != null) {
            LayoutParams layoutParams = (LayoutParams) webView.getLayoutParams();
            layoutParams.topMargin = (int) getResources().getDimension(R.dimen.bookstore_titlerbar_height);
            layoutParams.bottomMargin = 0;
            webView.setLayoutParams(layoutParams);
        }
    }

    public void a() {
        if (!TextUtils.isEmpty(this.ar)) {
            this.k.a("javascript:" + this.ar + "()");
        } else if (f()) {
            g();
        } else {
            K();
        }
    }

    public void b() {
        this.n.sendEmptyMessage(112);
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        c();
    }

    protected void c() {
        String H = H();
        if (H != null) {
            try {
                JSONObject jSONObject = new JSONObject(H);
                this.x = com.qq.reader.appconfig.e.c + jSONObject.getString("PART_URL") + "bid=" + jSONObject.getString("BOOK_ID") + "&" + com.qq.reader.appconfig.e.b(this.j);
                com.qq.reader.common.monitor.f.a("openurl:", this.x);
            } catch (JSONException e) {
                this.x = com.qq.reader.appconfig.e.a(0);
                e.printStackTrace();
            }
        } else {
            try {
                Bundle extras = getIntent().getExtras();
                if (extras != null) {
                    if (extras.getBoolean("ForServerLog")) {
                        com.qq.reader.common.monitor.j.a(74, 0);
                    }
                    this.x = b(extras.getString("com.qq.reader.WebContent"));
                    com.qq.reader.common.monitor.f.a("mUrl:", this.x);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        this.x = b(this.x);
        com.qq.reader.common.web.b.a().a(this, this.x);
        this.w = this.x;
        this.t = false;
        this.k.post(new Runnable(this) {
            final /* synthetic */ WebBrowserForContents a;

            {
                this.a = r1;
            }

            public void run() {
                if (this.a.x == null) {
                    af.a(this.a.getApplicationContext(), this.a.getString(R.string.url_invalid), 0).a();
                } else if (this.a.k != null) {
                    this.a.k.b(this.a.x);
                }
            }
        });
    }

    private String H() {
        try {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                return extras.getString("OPENURL");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String b(String str) {
        if (str == null) {
            return i(str);
        }
        return (str.toLowerCase().startsWith("http://") || str.toLowerCase().startsWith("https://") || str.toLowerCase().startsWith("file:///")) ? str : i(str);
    }

    public void c(String str) {
        com.qq.reader.common.offline.c.a(this.j).a(this.ad);
        this.p.a("mclient");
        this.x = b(str);
        this.p.a(new JSOfflineInterface(this.j, this.n, this.ad), "mclient");
        com.qq.reader.common.offline.c.a(this.j).a(this.n, this.ad);
        if (this.k != null && str != null) {
            this.k.post(new Runnable(this) {
                final /* synthetic */ WebBrowserForContents a;

                {
                    this.a = r1;
                }

                public void run() {
                    if (this.a.k != null && this.a.x != null) {
                        this.a.k.b(this.a.x);
                    }
                }
            });
        }
    }

    protected void d() {
        this.k.setDownloadListener(new DownloadListener(this) {
            final /* synthetic */ WebBrowserForContents a;

            {
                this.a = r1;
            }

            public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
                try {
                    this.a.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                } catch (Throwable th) {
                    com.qq.reader.common.monitor.debug.c.e("WebBrowserForContents", th.getMessage());
                }
            }
        });
        this.k.setWebViewClient(new WebViewClient(this) {
            final /* synthetic */ WebBrowserForContents a;

            {
                this.a = r1;
            }

            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                String str2 = null;
                com.qq.reader.common.monitor.f.d("webbrowserforcontents", "shouldOverrideUrlLoading  " + str);
                if (str == null) {
                    return false;
                }
                if (!(TextUtils.isEmpty(str) || !str.startsWith("jsbridge://JSContent/openDetail") || str.contains("pagecode%22%3A%221001") || str.contains("pagecode%22%3A1010"))) {
                    this.a.f.setVisibility(8);
                    this.a.e.setVisibility(8);
                }
                com.qq.reader.common.monitor.f.a("shouldOverrideUrlLoading:" + str, CookieManager.getInstance().getCookie(str));
                if (str.startsWith("about")) {
                    return false;
                }
                if (this.a.p.a(webView, str)) {
                    return true;
                }
                if (com.qq.reader.qurl.c.b(str)) {
                    try {
                        com.qq.reader.qurl.c.a(this.a, str);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return true;
                } else if (str.toLowerCase().startsWith("sms:")) {
                    String substring;
                    int indexOf = str.indexOf("?");
                    if (indexOf != -1) {
                        substring = str.substring(4, indexOf);
                        if (indexOf < str.length()) {
                            str2 = str.substring(indexOf + 6, str.length());
                        }
                    } else {
                        substring = null;
                    }
                    Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
                    if (!(substring == null || str2 == null)) {
                        intent.putExtra("sms_body", str2);
                        intent.putExtra("address", substring);
                        intent.setType("vnd.android-dir/mms-sms");
                    }
                    this.a.startActivity(intent);
                    return true;
                } else if (this.a.l(str)) {
                    try {
                        Intent intent2 = new Intent("android.intent.action.VIEW", Uri.parse(str));
                        intent2.addFlags(tencent.tls.platform.SigType.TLS);
                        this.a.startActivity(intent2);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    return true;
                } else if (this.a.r || !str.startsWith(com.tencent.qalsdk.core.c.d)) {
                    return false;
                } else {
                    webView.loadUrl(str);
                    return true;
                }
            }

            public void onReceivedError(WebView webView, int i, String str, String str2) {
                com.qq.reader.common.monitor.a.a().a(str2, i, str);
                if (!this.a.t) {
                    if (!this.a.r) {
                        webView.loadUrl(str2);
                    }
                    this.a.t = true;
                } else if (!this.a.r && !this.a.u) {
                    webView.loadUrl(com.qq.reader.appconfig.e.a(1));
                    this.a.u = true;
                    if (str2 != null && str2.contains("helpIndex")) {
                        this.a.aj.setVisibility(0);
                        this.a.aj.setText(this.a.getString(R.string.title_check_net));
                        this.a.aj.setOnClickListener(new OnClickListener(this) {
                            final /* synthetic */ AnonymousClass5 a;

                            {
                                this.a = r1;
                            }

                            public void onClick(View view) {
                                o.x(this.a.a, null);
                                i.a("event_A240", null, ReaderApplication.getApplicationImp());
                            }
                        });
                    }
                }
            }

            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                if (this.a.s.getVisibility() != 0) {
                    this.a.s.setVisibility(0);
                    this.a.d(this.a.getString(R.string.web_title_loading));
                }
                this.a.b(webView);
                com.qq.reader.common.monitor.a.a().a(2, str, this.a.getApplicationContext());
                this.a.af = System.currentTimeMillis();
                this.a.ag = -1;
            }

            public void onPageFinished(WebView webView, String str) {
                com.qq.reader.common.monitor.a.a().a(str);
                com.qq.reader.common.monitor.f.a("onPageFinished", CookieManager.getInstance().getCookie(str));
                if (webView.getVisibility() == 4) {
                    webView.setVisibility(0);
                }
                if (this.a.s.getVisibility() != 8) {
                    this.a.s.setVisibility(8);
                }
                if (this.a.v) {
                    this.a.v = false;
                }
                if (webView.getSettings().getCacheMode() == 2) {
                    webView.getSettings().setCacheMode(-1);
                }
                if (this.a.a != null && this.a.d != null && this.a.d.getVisibility() != 0) {
                    String title = webView.getTitle();
                    if (!TextUtils.isEmpty(title) && !title.startsWith("iyuedu.qq.com")) {
                        this.a.d(title);
                    }
                }
            }

            public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
                return this.a.j(str);
            }

            public void onLoadResource(WebView webView, String str) {
                super.onLoadResource(webView, str);
                if (webView.getVisibility() == 4) {
                    webView.setVisibility(0);
                }
            }
        });
    }

    protected void d(String str) {
        this.a.setText(str);
    }

    private boolean l(String str) {
        if (str == null || (!str.startsWith("mqqapi://") && !str.startsWith("mqqwpa://"))) {
            return false;
        }
        return true;
    }

    protected void e() {
        this.ao = new a(this);
        this.k.setWebChromeClient(this.ao);
    }

    public void setDestUrl(String str) {
        this.at = str;
    }

    public void reload() {
        WebBackForwardList copyBackForwardList = this.k.copyBackForwardList();
        if (copyBackForwardList != null && copyBackForwardList.getSize() > 0) {
            String url = copyBackForwardList.getCurrentItem().getUrl();
            String a = com.qq.reader.common.login.c.c().a(this.j);
            try {
                if ((a.length() > 0 || url.indexOf("usid=") == -1) && (a.length() <= 0 || url.indexOf("usid=") != -1)) {
                    this.k.b(url);
                    return;
                }
                int indexOf = url.indexOf(35);
                if (indexOf != -1) {
                    url = url.substring(0, indexOf);
                }
                url = com.qq.reader.appconfig.e.a(url, a);
                if (this.at != null && this.at.length() > 0) {
                    int indexOf2 = this.at.indexOf(35);
                    if (indexOf2 != -1) {
                        indexOf = this.at.indexOf(38, indexOf2);
                        if (indexOf == -1) {
                            url = url + this.at.substring(indexOf2);
                        } else {
                            url = url + this.at.substring(indexOf2, indexOf);
                        }
                    }
                    this.at = null;
                }
                this.k.b(url);
            } catch (Exception e) {
                com.qq.reader.common.monitor.f.a("error", "reload : " + e.toString());
            }
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        this.isReady2Show = true;
        if (i == 4) {
            if (I()) {
                this.ao.onHideCustomView();
                return true;
            } else if (t()) {
                return true;
            } else {
                a();
                return true;
            }
        } else if (i != 82) {
            return super.onKeyDown(i, keyEvent);
        } else {
            if (!this.isReady2Show) {
                return false;
            }
            if (this.i != null && this.i.a()) {
                return true;
            }
            i().f_();
            return true;
        }
    }

    private boolean I() {
        return this.al != null && this.k.getVisibility() == 8;
    }

    private boolean J() {
        try {
            Bundle extras = getIntent().getExtras();
            if (extras != null && extras.getInt("com.qq.reader.WebContent.from", 0) == 1) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private void K() {
        int i = 0;
        if (J()) {
            Intent intent = new Intent();
            intent.setClass(this.j, MainActivity.class);
            intent.setFlags(SoLoadCore.IF_SO_CONFIG_EXIST);
            startActivity(intent);
        } else if (this.aq) {
            Intent intent2 = new Intent();
            intent2.setClass(this.j, GuideActivity.class);
            startActivity(intent2);
            finish();
            this.aq = false;
        } else {
            if (this.ah) {
                i = -1;
            }
            setResult(i);
            finish();
        }
    }

    public boolean f() {
        if (!this.k.canGoBack()) {
            return false;
        }
        WebBackForwardList copyBackForwardList = this.k.copyBackForwardList();
        String url = copyBackForwardList.getCurrentItem().getUrl();
        if (url.equals(this.w) || url.contains("/error.html") || url.contains("/web_error.html") || (this.w != null && this.w.startsWith("http://wap.iciba.com/cword/"))) {
            return false;
        }
        if (copyBackForwardList.getCurrentIndex() != 1) {
            return true;
        }
        Object originalUrl = copyBackForwardList.getItemAtIndex(0).getOriginalUrl();
        if (TextUtils.isEmpty(originalUrl) || !originalUrl.equals(copyBackForwardList.getItemAtIndex(1).getOriginalUrl())) {
            return true;
        }
        return false;
    }

    public void g() {
        this.k.goBack();
        this.k.invalidate();
    }

    public void h() {
        WebSettings settings = this.k.getSettings();
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        if (VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(0);
        }
        if (VERSION.SDK_INT >= 16) {
            settings.setAllowFileAccess(true);
            settings.setAllowContentAccess(true);
            settings.setAllowFileAccessFromFileURLs(true);
            settings.setAllowUniversalAccessFromFileURLs(true);
        }
    }

    public com.qq.reader.view.linearmenu.a i() {
        this.au = new com.qq.reader.view.linearmenu.b(this);
        this.au.a(0, "刷新", null);
        this.au.a(new com.qq.reader.view.linearmenu.a.b(this) {
            final /* synthetic */ WebBrowserForContents a;

            {
                this.a = r1;
            }

            public boolean a(int i, Bundle bundle) {
                this.a.au.cancel();
                return this.a.a(i, bundle);
            }
        });
        this.au.a(new OnCancelListener(this) {
            final /* synthetic */ WebBrowserForContents a;

            {
                this.a = r1;
            }

            public void onCancel(DialogInterface dialogInterface) {
                this.a.getWindow().closeAllPanels();
            }
        });
        return this.au;
    }

    protected boolean a(int i, Bundle bundle) {
        switch (i) {
            case 0:
                m();
                com.qq.reader.common.monitor.j.a(1, 2);
                return true;
            default:
                return false;
        }
    }

    public void m() {
        try {
            String str;
            switch (this.H) {
                case 1001:
                    int b = this.N.b() + 2;
                    str = "javascript:Rank.changePage('" + this.V[this.M.h()] + "','" + b + "')";
                    if (!this.r) {
                        this.k.loadUrl(str);
                        return;
                    }
                    return;
                case 1002:
                    int h = this.M.h();
                    str = "javascript:CategoryBooks.changePage('" + this.Y[h] + "','" + this.aa[h] + "','" + this.ab[this.N.b()] + "')";
                    if (!this.r) {
                        this.k.loadUrl(str);
                        return;
                    }
                    return;
                default:
                    if (!this.r) {
                        if (TextUtils.isEmpty(this.at)) {
                            this.k.reload();
                            return;
                        } else {
                            reload();
                            return;
                        }
                    }
                    return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        e.printStackTrace();
    }

    public void n() {
        setResult(-1);
        finish();
    }

    public void o() {
        if (this.F != null && this.F.f()) {
            this.F.cancel();
        }
    }

    public void a(MusicOnlineTag musicOnlineTag) {
        if (this.F != null && this.F.f()) {
            this.F.cancel();
        }
        this.z = musicOnlineTag;
        r();
        k kVar = new k(this.z.clone());
        kVar.a(q());
        kVar.start();
    }

    public void a(MusicAllTag musicAllTag) {
        if (this.F != null && this.F.f()) {
            this.F.cancel();
        }
        this.A = musicAllTag;
        r();
        com.qq.reader.plugin.audiobook.c cVar = new com.qq.reader.plugin.audiobook.c(this.A.clone());
        cVar.a(p());
        cVar.start();
    }

    public com.qq.reader.plugin.audiobook.b p() {
        if (this.E == null) {
            this.E = new com.qq.reader.plugin.audiobook.b(this) {
                final /* synthetic */ WebBrowserForContents a;

                {
                    this.a = r1;
                }

                public void a(MusicAllTag musicAllTag) {
                    if (this.a.A != null && this.a.A.getBookId() == musicAllTag.getBookId()) {
                        List onlineTags = musicAllTag.getOnlineTags();
                        Object obj = null;
                        int i = 0;
                        while (i < onlineTags.size()) {
                            Object obj2;
                            MusicOnlineTag musicOnlineTag = (MusicOnlineTag) onlineTags.get(i);
                            String a = ao.a(musicOnlineTag);
                            MusicDownloadMark musicDownloadMark = new MusicDownloadMark(a, musicOnlineTag.getBname(), musicOnlineTag.getCname(), musicOnlineTag.getBid(), musicOnlineTag.getCid(), musicOnlineTag.getCtime(), musicOnlineTag.getCsize(), 0);
                            musicDownloadMark.setReadTime(System.currentTimeMillis());
                            musicDownloadMark.setAuthor(musicOnlineTag.getAuthor());
                            if (com.qq.reader.plugin.audiobook.i.a().b(a) == null) {
                                com.qq.reader.plugin.audiobook.e eVar = (com.qq.reader.plugin.audiobook.e) l.b(1004);
                                Object musicDownloadTask = new MusicDownloadTask(musicOnlineTag.getDownloadUrl(), musicOnlineTag.getBid(), musicOnlineTag.getCid(), 0, musicOnlineTag.getFileFormat(), musicOnlineTag.getmDrmFlag());
                                if (eVar.a((com.qq.reader.common.download.task.g) musicDownloadTask)) {
                                    musicDownloadMark.setDownloadTask(musicDownloadTask);
                                    com.qq.reader.plugin.audiobook.i.a().a(musicDownloadMark, musicOnlineTag.getChapterCount());
                                    obj2 = 1;
                                    i++;
                                    obj = obj2;
                                } else {
                                    this.a.n.obtainMessage(5011, "下载失败，请稍后再试").sendToTarget();
                                }
                            }
                            obj2 = obj;
                            i++;
                            obj = obj2;
                        }
                        if (obj != null) {
                            this.a.n.sendEmptyMessage(5008);
                        } else {
                            this.a.n.sendEmptyMessage(5009);
                        }
                        this.a.A = null;
                        this.a.s();
                    }
                }

                public Context a() {
                    return this.a.getApplicationContext();
                }

                public void a(MusicAllTag musicAllTag, String str) {
                    if (this.a.A != null && this.a.A.getBookId() == musicAllTag.getBookId()) {
                        this.a.n.obtainMessage(5011, str).sendToTarget();
                        this.a.A = null;
                        this.a.s();
                    }
                }

                public void b(MusicAllTag musicAllTag) {
                }
            };
        }
        return this.E;
    }

    public void statPageResume() {
        if (!TextUtils.isEmpty(this.mStatPageName)) {
            com.qq.reader.common.monitor.g.a(getApplicationContext(), getMTAStatPageName());
        }
    }

    public void statPagePause() {
        if (!TextUtils.isEmpty(this.mStatPageName)) {
            com.qq.reader.common.monitor.g.c(getApplicationContext(), getMTAStatPageName());
        }
    }

    public j q() {
        if (this.D == null) {
            this.D = new j(this) {
                final /* synthetic */ WebBrowserForContents a;

                {
                    this.a = r1;
                }

                public void a(MusicOnlineTag musicOnlineTag) {
                    if (this.a.z != null && this.a.z.getBid() == musicOnlineTag.getBid() && this.a.z.getCid() == musicOnlineTag.getCid()) {
                        String a = ao.a(this.a.z);
                        MusicDownloadMark musicDownloadMark = new MusicDownloadMark(a, this.a.z.getBname(), this.a.z.getCname(), this.a.z.getBid(), this.a.z.getCid(), this.a.z.getCtime(), this.a.z.getCsize(), 0);
                        musicDownloadMark.setReadTime(System.currentTimeMillis());
                        musicDownloadMark.setAuthor(this.a.z.getAuthor());
                        if (com.qq.reader.plugin.audiobook.i.a().b(a) == null) {
                            com.qq.reader.plugin.audiobook.e eVar = (com.qq.reader.plugin.audiobook.e) l.b(1004);
                            Object musicDownloadTask = new MusicDownloadTask(musicOnlineTag.getDownloadUrl(), musicOnlineTag.getBid(), musicOnlineTag.getCid(), 0, musicOnlineTag.getFileFormat(), musicOnlineTag.getmDrmFlag());
                            if (eVar.a((com.qq.reader.common.download.task.g) musicDownloadTask)) {
                                musicDownloadMark.setDownloadTask(musicDownloadTask);
                                com.qq.reader.plugin.audiobook.i.a().a(musicDownloadMark, musicOnlineTag.getChapterCount());
                                this.a.n.sendEmptyMessage(5008);
                            } else {
                                this.a.n.obtainMessage(5011, "下载失败，请稍后再试").sendToTarget();
                            }
                        } else {
                            this.a.n.sendEmptyMessage(5009);
                        }
                        this.a.z = null;
                        this.a.s();
                    }
                }

                public Context a() {
                    return this.a.getApplicationContext();
                }

                public void a(MusicOnlineTag musicOnlineTag, String str) {
                    if (this.a.z != null && this.a.z.getBid() == musicOnlineTag.getBid() && this.a.z.getCid() == musicOnlineTag.getCid()) {
                        this.a.n.obtainMessage(5011, str).sendToTarget();
                        this.a.z = null;
                        this.a.s();
                    }
                }

                public void b(MusicOnlineTag musicOnlineTag) {
                    if (this.a.z != null && this.a.z.getBid() == musicOnlineTag.getBid() && this.a.z.getCid() == musicOnlineTag.getCid()) {
                        this.a.n.obtainMessage(5010, musicOnlineTag).sendToTarget();
                        this.a.z = null;
                        this.a.s();
                    }
                }
            };
        }
        return this.D;
    }

    protected void r() {
        if (this.B == null) {
            this.B = new com.qq.reader.view.c(this);
            this.B.c(true);
            this.B.a(getResources().getString(R.string.get_book_music_feed_loading));
        }
        if (!this.ax.f()) {
            this.ax.f_();
        }
    }

    protected boolean s() {
        if (this.B == null || !this.B.f()) {
            return false;
        }
        this.B.cancel();
        return true;
    }

    protected void onResume() {
        super.onResume();
        this.k.onResume();
    }

    protected void onPause() {
        super.onPause();
        this.k.onPause();
    }

    public Handler getHandler() {
        return this.av;
    }

    protected void onDestroy() {
        t();
        this.p.a();
        com.qq.reader.common.offline.c.a(this.j).a(this.ad);
        this.r = true;
        if (this.k != null) {
            ((ViewGroup) this.k.getParent()).removeView(this.k);
            this.k.destroy();
            this.k = null;
        }
        super.onDestroy();
        try {
            unregisterReceiver(this.as);
        } catch (Throwable th) {
            com.qq.reader.common.monitor.debug.c.e("WebbrowserForContents", th.getMessage());
        }
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        this.isReady2Show = true;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.i != null && this.i.a()) {
            this.i.a(motionEvent);
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public boolean t() {
        if (this.i == null || !this.i.a()) {
            return false;
        }
        this.i.b();
        return true;
    }

    public void e(String str) {
        this.n.obtainMessage(501, str).sendToTarget();
    }

    private void a(String str, int i, String str2) {
        if (this.F == null) {
            this.F = new com.qq.reader.view.web.b(this, str, str2);
            this.F.a(new OnCancelListener(this) {
                final /* synthetic */ WebBrowserForContents a;

                {
                    this.a = r1;
                }

                public void onCancel(DialogInterface dialogInterface) {
                    if (this.a.F.k()) {
                        this.a.m();
                    }
                }
            });
        }
        if (this.F != null && !this.F.f()) {
            this.F.a(false);
            this.F.a(str, i);
        }
    }

    public void u() {
        if (this.F != null && this.F.f()) {
            this.F.a(true);
        }
    }

    public void v() {
        this.n.post(new Runnable(this) {
            final /* synthetic */ WebBrowserForContents a;

            {
                this.a = r1;
            }

            public void run() {
                if (this.a.F != null && this.a.F.f()) {
                    this.a.F.cancel();
                }
            }
        });
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 20001) {
            if (i2 == 0) {
                m();
            } else if (i2 == 5) {
                this.aw.toLogin();
            }
        } else if (i == 20002) {
            if (i2 == 0) {
                m();
            } else if (i2 == 5) {
                this.aw.toLogin();
            }
        } else if (i == 1002 && i2 == 30) {
            intent.getLongExtra("URL_BUILD_PERE_BOOK_ID", 0);
            intent.getStringExtra("COMMIT_COMMENT_CONTENT");
        }
    }

    private void m(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.V = jSONObject.optString("rankIdList").split(",");
            this.W = jSONObject.optString("rankTitleList").split(",");
            this.T = p(jSONObject.optString("showRankId"));
            this.a.setText(this.W[this.T]);
            L();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void n(String str) {
        int i = 0;
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONArray optJSONArray = jSONObject.optJSONArray("categoryList");
            int length = optJSONArray.length();
            this.Y = new String[length];
            this.Z = new String[length];
            this.aa = new String[length];
            for (int i2 = 0; i2 < length; i2++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i2);
                this.Y[i2] = optJSONObject.optString(ComicStoreExclusiveItemCard.NET_AD_ATTR_CATE);
                this.Z[i2] = optJSONObject.optString("categoryName");
                this.aa[i2] = optJSONObject.optString("categoryLevel");
            }
            this.T = p(jSONObject.optString("showCategory"));
            JSONArray optJSONArray2 = jSONObject.optJSONArray("sortList");
            int length2 = optJSONArray2.length();
            this.ab = new String[length2];
            this.ac = new String[length2];
            while (i < length2) {
                JSONObject jSONObject2 = optJSONArray2.getJSONObject(i);
                this.ab[i] = jSONObject2.optString("sort");
                this.ac[i] = jSONObject2.optString("sortName");
                i++;
            }
            this.U = o(jSONObject.optString("showsort"));
            this.a.setText(this.Z[this.T]);
            N();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int o(String str) {
        for (int i = 0; i < this.ab.length; i++) {
            if (this.ab[i].equalsIgnoreCase(str)) {
                return i;
            }
        }
        return 0;
    }

    private int p(String str) {
        int i;
        switch (this.H) {
            case 1001:
                for (i = 0; i < this.V.length; i++) {
                    if (this.V[i].equalsIgnoreCase(str)) {
                        return i;
                    }
                }
                break;
            case 1002:
                for (i = 0; i < this.Y.length; i++) {
                    if (this.Y[i].equalsIgnoreCase(str)) {
                        return i;
                    }
                }
                break;
        }
        return 0;
    }

    private void L() {
        this.U = this.N.b();
        this.N.a();
        this.N.a(this.R, this.S);
        this.N.b(this.U);
        M();
        O();
        this.M.g();
        for (int i = 0; i < this.W.length; i++) {
            this.M.a(this.X + Integer.parseInt(this.V[i]), this.W[i], null);
        }
        this.M.a((com.qq.reader.view.web.n.a) this);
        this.d.setOnClickListener(this.ae);
        this.a.setOnClickListener(this.ae);
    }

    private void M() {
        this.N.a(true);
        w();
    }

    protected void w() {
        LayoutParams layoutParams = (LayoutParams) this.k.getLayoutParams();
        layoutParams.topMargin = ((int) getResources().getDimension(R.dimen.bookstore_titlerbar_height)) + ((int) getResources().getDimension(R.dimen.bookshelf_tabs_height));
        layoutParams.bottomMargin = 0;
        this.k.setLayoutParams(layoutParams);
    }

    private void N() {
        int i = 0;
        this.N.a();
        for (int i2 = 0; i2 < this.ac.length; i2++) {
            this.N.a(Integer.parseInt(this.ab[i2]), this.ac[i2]);
        }
        this.N.a(this.ac.length);
        this.N.b(this.U);
        M();
        O();
        this.M.g();
        while (i < this.Z.length) {
            this.M.a(this.Z[i].hashCode(), this.Z[i], null);
            i++;
        }
        this.M.a((com.qq.reader.view.web.n.a) this);
        this.d.setOnClickListener(this.ae);
        this.a.setOnClickListener(this.ae);
    }

    private void O() {
        if (this.M == null) {
            this.M = new n(this, R.layout.webpage_popup_menu);
            this.M.a(new OnCancelListener(this) {
                final /* synthetic */ WebBrowserForContents a;

                {
                    this.a = r1;
                }

                public void onCancel(DialogInterface dialogInterface) {
                    this.a.d.setBackgroundResource(R.drawable.bookstore_title_arrow_white);
                }
            });
        }
        this.M.b(this.T);
        this.d.setVisibility(0);
        this.d.setBackgroundResource(R.drawable.bookstore_title_arrow_white);
    }

    private void P() {
        this.G.i();
    }

    private void Q() {
        this.G.j();
        this.mLoginNextTask = this.G.l();
    }

    private void R() {
        this.G.k();
        this.mLoginNextTask = this.G.l();
    }

    private com.qq.reader.common.login.a S() {
        return new com.qq.reader.common.login.a(this) {
            final /* synthetic */ WebBrowserForContents a;

            {
                this.a = r1;
            }

            public void a(int i) {
                switch (i) {
                    case 1:
                        this.a.m();
                        return;
                    default:
                        return;
                }
            }
        };
    }

    private void T() {
        if (this.M.f()) {
            this.d.setBackgroundResource(R.drawable.bookstore_title_arrow_white);
            this.M.cancel();
            return;
        }
        this.d.setBackgroundResource(R.drawable.bookstore_title_arrow_up_white);
        this.M.f_();
    }

    public void a(int i) {
        switch (this.H) {
            case 1001:
                switch (i) {
                    case 1000:
                        this.k.b("javascript:Rank.changePage('" + this.V[this.T] + "','" + 2 + "')");
                        this.N.c();
                        return;
                    case 1001:
                        this.k.b("javascript:Rank.changePage('" + this.V[this.T] + "','" + 3 + "')");
                        this.N.c();
                        return;
                    case 1002:
                        this.k.b("javascript:Rank.changePage('" + this.V[this.T] + "','" + 4 + "')");
                        this.N.c();
                        return;
                    default:
                        return;
                }
            case 1002:
                this.U = o(String.valueOf(i));
                this.k.b("javascript:CategoryBooks.changePage('" + this.Y[this.T] + "','" + this.aa[this.T] + "','" + i + "')");
                this.N.c();
                return;
            default:
                return;
        }
    }

    public boolean b(int i, Bundle bundle) {
        int i2;
        switch (this.H) {
            case 1001:
                int i3 = i - this.X;
                i2 = 0;
                while (i2 < this.V.length) {
                    if (Integer.parseInt(this.V[i2]) == i3) {
                        if (i2 >= 0 && i2 != this.T) {
                            this.T = i2;
                            this.a.setText(this.W[this.T]);
                            this.N.b(0);
                            this.M.b(i2);
                            this.N.c();
                            this.k.b("javascript:Rank.changePage('" + this.V[this.T] + "','" + 2 + "')");
                            break;
                        }
                    }
                    i2++;
                }
                i2 = -1;
                this.T = i2;
                this.a.setText(this.W[this.T]);
                this.N.b(0);
                this.M.b(i2);
                this.N.c();
                this.k.b("javascript:Rank.changePage('" + this.V[this.T] + "','" + 2 + "')");
                break;
            case 1002:
                i2 = 0;
                while (i2 < this.Z.length) {
                    if (i == this.Z[i2].hashCode()) {
                        if (i2 >= 0 && i2 != this.T) {
                            this.T = i2;
                            this.a.setText(this.Z[this.T]);
                            this.N.b(0);
                            this.M.b(i2);
                            this.N.c();
                            this.k.b("javascript:CategoryBooks.changePage('" + this.Y[this.T] + "','" + this.aa[this.T] + "','" + this.ab[0] + "')");
                            break;
                        }
                    }
                    i2++;
                }
                i2 = -1;
                this.T = i2;
                this.a.setText(this.Z[this.T]);
                this.N.b(0);
                this.M.b(i2);
                this.N.c();
                this.k.b("javascript:CategoryBooks.changePage('" + this.Y[this.T] + "','" + this.aa[this.T] + "','" + this.ab[0] + "')");
                break;
        }
        return true;
    }

    public void doPageAction(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.H = jSONObject.optInt("actioncode");
            switch (this.H) {
                case 1000:
                    this.G = new d(this, this.n, jSONObject);
                    boolean a = this.G.a();
                    int b = this.G.b();
                    this.I.setVisibility(0);
                    this.J.setVisibility(0);
                    LayoutParams layoutParams = (LayoutParams) this.k.getLayoutParams();
                    layoutParams.bottomMargin = getContext().getResources().getDimensionPixelOffset(R.dimen.common_dp_50);
                    this.k.setLayoutParams(layoutParams);
                    this.L.setVisibility(0);
                    if (a) {
                        this.K.setVisibility(0);
                    } else {
                        this.K.setVisibility(8);
                    }
                    if (b == 0) {
                        this.J.setText(R.string.webpage_bookinfo_readonline_free);
                        return;
                    } else {
                        this.J.setText(R.string.webpage_bookinfo_readonline_pay);
                        return;
                    }
                case 1001:
                    m(jSONObject.optString("pageinfo"));
                    return;
                case 1002:
                    n(jSONObject.optString("pageinfo"));
                    return;
                default:
                    return;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        e.printStackTrace();
    }

    protected void j() {
    }

    protected void k() {
    }

    protected void l() {
    }

    protected void a(WebView webView) {
        super.a(webView);
        this.p.a(new AndroidJS(this), "AndroidJS");
        this.p.a(new JSDownLoad(this), "downloadbook");
        this.p.a(new JSReadOnline(this), "readonline");
        this.aw.setNextLoginTask(S());
        this.aw.setLoginListener(this);
        this.p.a(this.aw, "readerlogin");
        com.qq.reader.common.web.js.a.a.b jSContent = new JSContent(this);
        jSContent.setDialogCloseCallBack(this);
        this.p.a(jSContent, "JSContent");
        this.p.a(new JSDetail(this), "JSDetail");
        this.p.a(new JSUpdate(this), "JSUpdate");
        this.p.a(new JSSendSMS(this), "sendvip");
        this.p.a(new JSPay(this, this.k), OpenConstants.API_NAME_PAY);
        this.p.a(new JSToast(this), "JSToast");
        this.p.a(new JSGoToWeb(this), "JSGoToWeb");
        this.p.a(new JSAPP(this), "JSApp");
        this.p.a(new JSReadMusicOnline(this), "readmusiconline");
        this.p.a(new JSAddToBookShelf(this), "JSAddToShelf");
        this.p.a(new JSBookDir(this), "bookdir");
        this.p.a(new JSSearch(this), "JSSearch");
        this.p.a(new JSOfflineInterface(this.j, this.n, this.ad), "mclient");
        this.p.a(new JsSubscribe(this), "JsSubscribe");
        this.p.a(new JSSns(this), "JSSns");
        this.p.a(new JSReload(this, this), "JSReload");
        this.p.a(new JSDialog(this), "JSDialog");
        this.p.a(new JSbookshelf(this), "JSbookshelf");
    }

    private void a(String str, String str2) {
        g gVar = new g(str);
        gVar.e(str2);
        com.qq.reader.cservice.download.book.h hVar = new com.qq.reader.cservice.download.book.h(getApplicationContext(), gVar);
        hVar.a(this);
        U();
        hVar.start();
    }

    public void a(g gVar) {
        this.n.obtainMessage(1203, gVar).sendToTarget();
    }

    public void b(g gVar) {
        this.n.obtainMessage(1204, gVar).sendToTarget();
    }

    public void c(g gVar) {
        this.n.obtainMessage(1205, gVar).sendToTarget();
    }

    private void U() {
        if (this.ax == null) {
            this.ax = new com.qq.reader.view.c(this);
            this.ax.c(true);
            this.ax.a(getResources().getString(R.string.get_book_music_feed_loading));
        }
        if (!this.ax.f()) {
            this.ax.f_();
        }
    }

    private boolean V() {
        try {
            if (this.ax != null && this.ax.f()) {
                this.ax.cancel();
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public void doSuccess() {
        Toast.makeText(getApplicationContext(), "订阅成功", 0).show();
        m();
    }

    private void W() {
        if (this.C == null || !this.C.isShowing()) {
            this.C = ProgressDialog.show(this, "", "正在购买，请稍候...", true);
            this.C.setCanceledOnTouchOutside(false);
        }
    }

    private boolean X() {
        try {
            if (this.C != null && this.C.isShowing()) {
                this.C.cancel();
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    protected Dialog createDialog(int i, Bundle bundle) {
        AlertDialog alertDialog = (AlertDialog) q.a(this, i, null);
        String str = "";
        switch (i) {
            case 606:
                View inflate = LayoutInflater.from(this).inflate(R.layout.book_buy_view, null);
                TextView textView = (TextView) inflate.findViewById(R.id.book_discount_msg);
                final String valueOf = String.valueOf(this.G.d());
                String h = this.G.h();
                int e = this.G.e();
                int f = this.G.f();
                CharSequence stringBuilder = new StringBuilder();
                Object spannableStringBuilder = new SpannableStringBuilder();
                stringBuilder.append("价　　格：");
                if (f < 100) {
                    textView.setVisibility(0);
                    f = (f * e) / 100;
                    stringBuilder.append(e);
                    stringBuilder.append("书币");
                    e = stringBuilder.length();
                    stringBuilder.append(" ");
                    stringBuilder.append(f);
                    stringBuilder.append("书币　");
                    spannableStringBuilder.append(stringBuilder);
                    spannableStringBuilder.setSpan(new StrikethroughSpan(), 5, e, 17);
                    spannableStringBuilder.setSpan(new ForegroundColorSpan(-8355712), 5, e, 17);
                    String g = this.G.g();
                    if (g != null && g.trim().length() > 0) {
                        CharSequence stringBuilder2 = new StringBuilder();
                        stringBuilder2.append("　　　　　(");
                        stringBuilder2.append(g);
                        stringBuilder2.append(")");
                        textView.setText(stringBuilder2);
                    }
                } else {
                    textView.setVisibility(8);
                    stringBuilder.append(e);
                    stringBuilder.append("书币");
                    spannableStringBuilder.append(stringBuilder);
                }
                textView = (TextView) inflate.findViewById(R.id.book_name);
                textView.setText(String.format(getResources().getString(R.string.buy_book_name), new Object[]{h}));
                ((TextView) inflate.findViewById(R.id.book_price)).setText(spannableStringBuilder);
                alertDialog.a(-1, (int) R.drawable.selector_orange_button);
                alertDialog.a(-2, (int) R.drawable.selector_white_button);
                alertDialog.a(inflate);
                alertDialog.a((int) R.string.alert_dialog_buy, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ WebBrowserForContents b;

                    public void onClick(DialogInterface dialogInterface, int i) {
                        com.qq.reader.cservice.buy.a.d dVar = new com.qq.reader.cservice.buy.a.d(this.b.j, valueOf);
                        dVar.a(this.b);
                        dVar.start();
                        this.b.W();
                    }
                });
                alertDialog.b((int) R.string.alert_dialog_cancel, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ WebBrowserForContents a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                break;
            case 607:
                alertDialog.a(bundle.getString("message"));
                alertDialog.a((int) R.string.alert_dialog_ok, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ WebBrowserForContents a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                break;
            case 608:
                alertDialog.a(bundle.getString("message"));
                alertDialog.a((int) R.string.charge, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ WebBrowserForContents a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        this.a.x();
                    }
                });
                alertDialog.b((int) R.string.alert_dialog_cancel, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ WebBrowserForContents a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                break;
        }
        return alertDialog;
    }

    public void b(com.qq.reader.cservice.buy.a.c cVar) {
        Message obtain = Message.obtain();
        obtain.what = 1218;
        obtain.obj = cVar;
        this.n.sendMessage(obtain);
    }

    public void c(com.qq.reader.cservice.buy.a.c cVar) {
        Message obtain = Message.obtain();
        obtain.what = 1219;
        obtain.obj = cVar;
        this.n.sendMessage(obtain);
    }

    public void x() {
        new JSPay(this).startCharge(this, 0);
    }

    public void finish() {
        y();
        if (H() != null) {
            backRootActivity();
        }
        z();
        super.finish();
    }

    public void y() {
        if (this.k != null) {
            this.k.stopLoading();
        }
    }

    public void z() {
        if (!(this.w == null || this.w.indexOf("userPayLog.html?") == -1)) {
            com.qq.reader.common.monitor.j.a(52, 3);
        }
        if (!(this.w == null || this.w.indexOf("userCostLog.html?") == -1)) {
            com.qq.reader.common.monitor.j.a(53, 3);
        }
        if (this.w != null && this.w.indexOf("buyhistory.html?") != -1) {
            com.qq.reader.common.monitor.j.a(33, 3);
        }
    }

    public void f(String str) {
    }

    public void g(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject != null) {
                    String str2 = "buyPack.html?pid=" + jSONObject.optString("packageid");
                    if (this.ai == null) {
                        this.ai = new h(this);
                        this.ai.a((Activity) this);
                        this.ai.a(new com.qq.reader.view.web.h.a(this) {
                            final /* synthetic */ WebBrowserForContents a;

                            {
                                this.a = r1;
                            }

                            public void a(String str, boolean z) {
                                try {
                                    com.qq.reader.common.monitor.f.d("readerpage", "OnDialogClose " + str);
                                    this.a.ai.dismiss();
                                    this.a.ai = null;
                                    if (z) {
                                        this.a.m();
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    if (!this.r) {
                        this.ai.a(str2);
                    }
                    this.ai.f_();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void a(Boolean bool) {
        if (bool.booleanValue()) {
            this.aj.setVisibility(0);
            this.aj.setText(getString(R.string.title_check_net));
            this.aj.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ WebBrowserForContents a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    o.x(this.a, null);
                    i.a("event_A240", null, ReaderApplication.getApplicationImp());
                }
            });
            return;
        }
        this.aj.setVisibility(8);
    }

    public void a(int i, String str, String str2) {
        this.k.loadUrl("javascript:" + str + "(" + i + ",'" + str2 + "')");
    }

    public void h(String str) {
        this.g = str;
        this.e.setVisibility(0);
    }

    public void a(String str, int i, boolean z) {
        this.h = str;
        this.f.setVisibility(0);
        this.ak = i;
        if (this.ak == 1) {
            this.f.setImageResource(R.drawable.titlebar_icon_collected);
        } else {
            this.f.setImageResource(R.drawable.titlebar_icon_collect);
        }
        if (z) {
            com.qq.reader.appconfig.a.d.H(this.j, false);
        }
    }

    public void retry() {
        WebBackForwardList copyBackForwardList = this.k.copyBackForwardList();
        String str = null;
        if (copyBackForwardList != null && copyBackForwardList.getSize() > 0) {
            str = copyBackForwardList.getItemAtIndex(copyBackForwardList.getCurrentIndex() - 1).getUrl();
        }
        this.k.b(str);
        this.k.clearHistory();
    }
}
