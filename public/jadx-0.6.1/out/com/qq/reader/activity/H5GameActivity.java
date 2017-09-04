package com.qq.reader.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.dynamicload.Lib.DLConstants;
import com.qq.reader.common.db.handle.r;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.monitor.j;
import com.qq.reader.common.readertask.ReaderTask;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.protocol.H5GameGetOpenidTask;
import com.qq.reader.common.readertask.protocol.ProfileNetTask;
import com.qq.reader.common.utils.aj;
import com.qq.reader.common.web.js.JSGame;
import com.qq.reader.common.web.js.JSSns;
import com.qq.reader.common.web.js.a.d;
import com.qq.reader.module.dicovery.a.b;
import com.qq.reader.module.dicovery.a.c;
import com.qq.reader.view.ProgressBar;
import com.qq.reader.view.af;
import com.tencent.feedback.proguard.R;
import com.tencent.smtt.sdk.CookieManager;
import com.tencent.smtt.sdk.CookieSyncManager;
import com.tencent.smtt.sdk.DownloadListener;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import com.tencent.smtt.sdk.WebBackForwardList;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebHistoryItem;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebSettings.RenderPriority;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;
import org.json.JSONException;
import org.json.JSONObject;

public class H5GameActivity extends ReaderBaseActivity {
    private Context a;
    private RelativeLayout b;
    private ProgressBar c;
    private View d;
    private TextView e;
    private ImageView f;
    private View g;
    private View h;
    private WebView i;
    private d j = null;
    private boolean k;
    private boolean l;
    private String m;
    private String n = "";
    private WebChromeClient o;
    private c p;
    private com.qq.reader.module.dicovery.a.a q;
    private com.qq.reader.module.dicovery.a.d r;
    private com.qq.reader.module.dicovery.a.d s;
    private b t;
    private JSGame u;
    private boolean v = true;
    private ServiceConnection w;
    private com.qq.reader.common.web.a.a x;
    private int y = 0;

    public class a extends WebChromeClient {
        final /* synthetic */ H5GameActivity a;

        public a(H5GameActivity h5GameActivity) {
            this.a = h5GameActivity;
        }

        public void onProgressChanged(WebView webView, int i) {
            com.qq.reader.common.monitor.a.a().a(i, this.a.getApplicationContext());
            this.a.c.setProgress((double) i);
        }

        public void onReceivedTitle(WebView webView, String str) {
            super.onReceivedTitle(webView, str);
            if (this.a.c.getVisibility() != 8) {
                this.a.c.setVisibility(8);
            }
            this.a.e.setText(str);
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
        setSwipeBackEnable(false);
        this.a = getApplicationContext();
        if (!"Meizu_M040".equals(com.qq.reader.common.c.a.E)) {
            getWindow().addFlags(SigType.WLOGIN_PF);
        }
        setContentView(R.layout.activity_h5_game);
        l();
        b(this.i);
        c(this.i);
        a(this.i);
        h();
        j();
        a(getIntent());
        r();
        WebView.setWebContentsDebuggingEnabled(com.qq.reader.appconfig.b.a);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    private String i() {
        Object r = com.qq.reader.common.login.define.a.r(this.a);
        if (TextUtils.isEmpty(r)) {
            return null;
        }
        return "qqxsToken=" + r;
    }

    public boolean a(String str) {
        String i = i();
        if (i == null) {
            return false;
        }
        StringBuilder stringBuilder = new StringBuilder(i);
        stringBuilder.append(";Domain=.game.book.qq.com");
        stringBuilder.append(";Path = /");
        if (VERSION.SDK_INT < 21) {
            CookieSyncManager.createInstance(this.a);
        }
        CookieManager instance = CookieManager.getInstance();
        instance.setCookie(str, stringBuilder.toString());
        if (TextUtils.isEmpty(instance.getCookie(str))) {
            return false;
        }
        return true;
    }

    private void a(Intent intent) {
        String stringExtra = intent.getStringExtra("com.qq.reader.WebContent_encode");
        if (stringExtra == null || "".equals(stringExtra)) {
            this.n = intent.getStringExtra("com.qq.reader.WebContent");
        } else {
            try {
                this.n = URLDecoder.decode(stringExtra, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        this.m = this.n;
        d(this.n);
    }

    private void j() {
        com.qq.reader.appconfig.a.d.A = true;
        com.qq.reader.appconfig.a.d.z = true;
        if (!k()) {
            p();
        }
    }

    private boolean k() {
        if (this.x != null) {
            try {
                String b = this.x.b();
                if (b != null && b.trim().length() > 0) {
                    return true;
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private void d(String str) {
        a(str);
        this.i.loadUrl(str);
        j.c("H5 request url: " + str);
        f.a("H5GameActivity", "H5 request url: " + str);
        if (this.g.getVisibility() == 8) {
            this.g.setVisibility(0);
        }
        if (this.i.getVisibility() == 0) {
            this.i.setVisibility(4);
        }
    }

    public boolean needSetImmerseMode() {
        if (VERSION.SDK_INT >= 19) {
            return true;
        }
        return false;
    }

    protected boolean handleMessageImp(Message message) {
        String str = "unknown result";
        Bundle data = message.getData();
        int i;
        switch (message.what) {
            case 101:
                if (message.obj != null) {
                    if (data != null) {
                        if (message.arg1 == 0) {
                            i = data.getInt("H5GAME_CHARGE_MONEY");
                            try {
                                if (this.x.g() >= i) {
                                    this.x.a(this.x.g() - i);
                                }
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        }
                        str = (String) data.get("H5GAME_CHARGE_MESSAGE");
                    }
                    b(message.obj.toString(), message.arg1, str);
                    break;
                }
                return false;
            case 102:
                r();
                if (message.obj != null) {
                    b(message.arg1, message.obj.toString());
                    break;
                }
                this.i.reload();
                return false;
            case 103:
                if (message.obj != null) {
                    if (data != null) {
                        if (message.arg1 == 0) {
                            i = data.getInt("H5GAME_GRANTTICKET_COUNT");
                            if (i > 0) {
                                try {
                                    this.x.b(i + this.x.h());
                                } catch (RemoteException e2) {
                                    e2.printStackTrace();
                                }
                            }
                        } else {
                            com.qq.reader.common.monitor.debug.c.e("TAG", "grant coins fail");
                        }
                        str = (String) data.get("H5GAME_GRANTTICKET_MESSAGE");
                        switch (message.arg2) {
                            case 110:
                                a(message.obj.toString(), message.arg1, str);
                                break;
                            case 120:
                                a(message.obj.toString(), message.arg1, str);
                                break;
                            default:
                                break;
                        }
                    }
                }
                return false;
                break;
            case 104:
                this.u = new JSGame(this, this.x);
                this.p = new c(this.mHandler);
                this.q = new com.qq.reader.module.dicovery.a.a(this.mHandler);
                this.r = new com.qq.reader.module.dicovery.a.d(this.mHandler);
                this.t = new b(this.mHandler);
                this.s = new com.qq.reader.module.dicovery.a.d(this.mHandler);
                this.u.setGameGetOpenidHandler(this.p);
                this.u.setGameChargeHandler(this.q);
                this.u.setGameGrantTicketHandler(this.r);
                this.u.setGameGetBalanceHandler(this.t);
                this.u.setGameGrantCoinHandler(this.s);
                this.u.setNextLoginTask(m());
                this.j.a(this.u, "JSGame");
                break;
            case 105:
                if (message.obj != null) {
                    switch (message.arg1) {
                        case DLConstants.LOAD_ERR_SIGNATURES /*-101*/:
                        case -100:
                            af.a((Context) this, (CharSequence) "获取余额失败", 0).a();
                            break;
                        case 0:
                            if (data != null) {
                                a(message.obj.toString(), data.getInt("H5GEME_GET_BALANCE_BOOKCOIN"), data.getInt("H5GEME_GET_BALANCE_GAMECOIN"), data.getString("H5GAME_GET_BALANCE_MSG"));
                                break;
                            }
                            break;
                        default:
                            break;
                    }
                }
                return false;
        }
        return super.handleMessageImp(message);
    }

    private void l() {
        this.b = (RelativeLayout) findViewById(R.id.h5game_content);
        this.c = (ProgressBar) findViewById(R.id.webprogress);
        this.d = findViewById(R.id.common_titler);
        this.g = findViewById(R.id.loading_layout);
        this.h = findViewById(R.id.loading_failed_layout);
        this.i = (WebView) findViewById(R.id.h5game_webview);
        this.e = (TextView) findViewById(R.id.profile_header_title);
        if (com.qq.reader.common.c.a.bT <= 1000 && com.qq.reader.common.c.a.bU <= ErrorCode.INFO_STATIC_TBS_INSTALL_ERR_CODE_BASE) {
            this.e.addTextChangedListener(new TextWatcher(this) {
                final /* synthetic */ H5GameActivity a;

                {
                    this.a = r1;
                }

                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    if (i3 > 5) {
                        this.a.e.setText(charSequence.subSequence(0, 4) + "…");
                    }
                }

                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                public void afterTextChanged(Editable editable) {
                }
            });
        }
        this.h.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ H5GameActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.h.setVisibility(8);
                this.a.g.setVisibility(0);
                this.a.d(this.a.n);
            }
        });
        ((ImageView) findViewById(R.id.profile_header_left_back)).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ H5GameActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.v) {
                    this.a.g();
                } else {
                    this.a.f();
                }
            }
        });
        this.f = (ImageView) findViewById(R.id.profile_header_right_image);
        this.f.setImageResource(R.drawable.titlebar_icon_share);
        this.f.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ H5GameActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.a();
            }
        });
    }

    private void a(Activity activity) {
        try {
            if (this.w == null) {
                this.w = new ServiceConnection(this) {
                    final /* synthetic */ H5GameActivity a;

                    {
                        this.a = r1;
                    }

                    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                        this.a.x = com.qq.reader.common.web.a.a.a.a(iBinder);
                        this.a.r();
                        Message obtainMessage = this.a.mHandler.obtainMessage();
                        obtainMessage.what = 104;
                        this.a.mHandler.sendMessage(obtainMessage);
                    }

                    public void onServiceDisconnected(ComponentName componentName) {
                        this.a.x = null;
                    }
                };
            }
            Intent intent = new Intent();
            intent.setAction("com.qq.reader.common.web.service.GameAidlService");
            intent.setPackage(activity.getPackageName());
            activity.bindService(intent, this.w, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void b(WebView webView) {
        this.j = new d();
        this.j.b(webView);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setRenderPriority(RenderPriority.HIGH);
        settings.setDomStorageEnabled(true);
        this.j.a(webView);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setCacheMode(-1);
        a((Activity) this);
    }

    public void a(int i, String str, String str2) {
        this.i.loadUrl("javascript:" + str + "(" + i + ",'" + str2 + "')");
    }

    public String a(int i, String str) {
        String str2 = null;
        try {
            if (this.x != null) {
                str2 = this.x.f();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        if ("".equals(str)) {
            return str2;
        }
        this.i.loadUrl("javascript:" + str + "(" + i + ",'" + str2 + "')");
        return "";
    }

    public String b(int i, String str) {
        String f;
        RemoteException e;
        JSONObject jSONObject;
        String str2 = null;
        try {
            if (this.x != null) {
                f = this.x.f();
                try {
                    str2 = this.x.c();
                } catch (RemoteException e2) {
                    e = e2;
                    e.printStackTrace();
                    if ("".equals(str)) {
                        jSONObject = new JSONObject();
                        try {
                            jSONObject.put("code", i);
                            jSONObject.put("openid", f);
                            jSONObject.put("ywguid", str2);
                        } catch (JSONException e3) {
                            e3.printStackTrace();
                        }
                        return jSONObject.toString();
                    }
                    this.i.loadUrl("javascript:" + str + "(" + i + ",'" + f + "','" + str2 + "')");
                    return "";
                }
            }
            f = str2;
        } catch (RemoteException e4) {
            e = e4;
            f = str2;
            e.printStackTrace();
            if ("".equals(str)) {
                this.i.loadUrl("javascript:" + str + "(" + i + ",'" + f + "','" + str2 + "')");
                return "";
            }
            jSONObject = new JSONObject();
            jSONObject.put("code", i);
            jSONObject.put("openid", f);
            jSONObject.put("ywguid", str2);
            return jSONObject.toString();
        }
        if ("".equals(str)) {
            jSONObject = new JSONObject();
            jSONObject.put("code", i);
            jSONObject.put("openid", f);
            jSONObject.put("ywguid", str2);
            return jSONObject.toString();
        }
        this.i.loadUrl("javascript:" + str + "(" + i + ",'" + f + "','" + str2 + "')");
        return "";
    }

    public void a(String str, int i, String str2) {
        this.i.loadUrl("javascript:" + str + "(" + i + ",'" + str2 + "')");
    }

    private void a(String str, int i, int i2, String str2) {
        this.i.loadUrl("javascript:" + str + "(" + i + "," + i2 + ",'" + str2 + "'" + ")");
    }

    public void a() {
        this.i.loadUrl("javascript:game.share()");
    }

    public String b() {
        String str = "";
        try {
            str = this.x.d();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return str;
    }

    public String c() {
        int i = 0;
        try {
            i = this.x.g();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return String.valueOf(i);
    }

    public String d() {
        try {
            String[] split = "qqreader_6.5.3.0888_android".split("_");
            return split[1].substring(0, split[1].length() - 4).replace(".", "");
        } catch (Exception e) {
            return "6.5.3".replace(".", "");
        }
    }

    public void a(String str, String str2, String str3, String str4) {
        new JSSns(this).sharePage(str4, str3, str, str2, "");
    }

    public String e() {
        int i = 0;
        try {
            i = this.x.h();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return String.valueOf(i);
    }

    private void b(String str, int i, String str2) {
        this.i.loadUrl("javascript:" + str + "(" + i + ",'" + str2 + "')");
    }

    public void f() {
        setResult(40000);
        finish();
    }

    private void a(int i) {
        this.i.loadUrl("javascript:chargeBookMoneyCallback(" + i + ")");
    }

    private void b(int i) {
        this.i.loadUrl("javascript:ticketsCallback(" + i + ")");
    }

    private com.qq.reader.common.login.a m() {
        return new com.qq.reader.common.login.a(this) {
            final /* synthetic */ H5GameActivity a;

            {
                this.a = r1;
            }

            public void a(int i) {
                switch (i) {
                    case 1:
                        this.a.q();
                        return;
                    case 2:
                        this.a.b("登录失败");
                        return;
                    case 3:
                        this.a.b("");
                        return;
                    default:
                        return;
                }
            }
        };
    }

    public void b(String str) {
        if (this.n.contains("/htmlv2/mine/board.html")) {
            this.i.goBack();
            this.i.invalidate();
        }
        if (!"".equals(str)) {
            af.a(this.a, (CharSequence) str, 0).a();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_h5_game, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != R.id.action_settings) {
            return super.onOptionsItemSelected(menuItem);
        }
        d(this.n);
        return true;
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        f.d("H5GameActivity", "chargebookmoney requestCode is " + i + " and resultCode is " + i2);
        if (i == 20001 && i2 == 0) {
            r();
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        g();
        return true;
    }

    public void g() {
        if (o()) {
            this.i.goBack();
            n();
            this.i.invalidate();
            return;
        }
        f();
    }

    private void n() {
        int i;
        String str = "/mine/board.html";
        WebBackForwardList copyBackForwardList = this.i.copyBackForwardList();
        int currentIndex = copyBackForwardList.getCurrentIndex();
        if (currentIndex == this.y) {
            f.a("H5GameActivity", "curIndex = " + currentIndex + " list size = " + copyBackForwardList.getSize());
            currentIndex--;
        }
        String url = copyBackForwardList.getItemAtIndex(currentIndex).getUrl();
        String url2 = copyBackForwardList.getItemAtIndex(currentIndex + 1).getUrl();
        if (url.contains("/mine/board.html")) {
            f.a("H5GameActivity", "empty url = " + url);
            if (o()) {
                i = currentIndex - 1;
                if (copyBackForwardList.getItemAtIndex(i) == null) {
                    f();
                    return;
                }
                String str2 = url2;
                url2 = copyBackForwardList.getItemAtIndex(i).getUrl();
                CharSequence charSequence = str2;
            } else {
                f();
                return;
            }
        }
        Object url3;
        if (url2.contains("/mine/board.html")) {
            f.c("H5GameActivity", "lastUrl = " + url2);
            WebHistoryItem itemAtIndex = copyBackForwardList.getItemAtIndex(copyBackForwardList.getCurrentIndex() + 2);
            if (itemAtIndex != null) {
                url2 = url;
                i = currentIndex;
                url3 = itemAtIndex.getUrl();
            }
        }
        str2 = url2;
        url2 = url;
        i = currentIndex;
        url3 = str2;
        while (!TextUtils.isEmpty(url2) && !TextUtils.isEmpty(r0) && url2.equals(r0)) {
            if (o()) {
                f.a("H5GameActivity", "same url = " + url2);
                i--;
                if (copyBackForwardList.getItemAtIndex(i) == null) {
                    f();
                    return;
                }
                url2 = copyBackForwardList.getItemAtIndex(i).getUrl();
            } else {
                f();
                return;
            }
        }
        if (i != copyBackForwardList.getCurrentIndex()) {
            this.i.loadUrl(url2);
        }
    }

    private boolean o() {
        boolean z = true;
        if (!this.i.canGoBack()) {
            return false;
        }
        WebBackForwardList copyBackForwardList = this.i.copyBackForwardList();
        String url = copyBackForwardList.getCurrentItem().getUrl();
        this.y = copyBackForwardList.getCurrentIndex();
        if (url.equals(this.m) || e(url)) {
            return false;
        }
        if (copyBackForwardList.getCurrentIndex() != 1) {
            return true;
        }
        Object originalUrl = copyBackForwardList.getItemAtIndex(0).getOriginalUrl();
        if (TextUtils.isEmpty(originalUrl)) {
            return true;
        }
        if (originalUrl.equals(copyBackForwardList.getItemAtIndex(1).getOriginalUrl())) {
            z = false;
        }
        return z;
    }

    boolean c(String str) {
        return !TextUtils.isEmpty(str) && str.toLowerCase().contains("platformapi/startapp");
    }

    private void c(WebView webView) {
        webView.setDownloadListener(new DownloadListener(this) {
            final /* synthetic */ H5GameActivity a;

            {
                this.a = r1;
            }

            public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
                this.a.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
            }
        });
        webView.setWebViewClient(new WebViewClient(this) {
            final /* synthetic */ H5GameActivity b;

            {
                this.b = r1;
            }

            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                if (!this.b.j.a(webView, str)) {
                    if (com.qq.reader.qurl.c.b(str)) {
                        try {
                            com.qq.reader.qurl.c.a(this.b, str);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (this.b.c(str)) {
                        try {
                            Intent parseUri = Intent.parseUri(str, 1);
                            parseUri.addCategory("android.intent.category.BROWSABLE");
                            parseUri.setComponent(null);
                            parseUri.setFlags(tencent.tls.platform.SigType.TLS);
                            this.b.a.startActivity(parseUri);
                        } catch (Exception e2) {
                            e2.printStackTrace();
                            com.qq.reader.common.monitor.debug.c.e("H5GameActivity", e2.toString());
                        }
                    } else {
                        webView.loadUrl(str);
                        f.a("H5GameActivity", "shouldOverrideUrlLoading url = " + str);
                    }
                }
                return true;
            }

            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                super.onPageStarted(webView, str, bitmap);
                f.d("H5GameActivity", "--->onPageStarted");
                this.b.l = true;
                if (!this.b.e(str)) {
                    this.b.n = str;
                }
                if (this.b.c.getVisibility() == 8) {
                    this.b.c.setVisibility(0);
                }
            }

            public void onLoadResource(WebView webView, String str) {
                super.onLoadResource(webView, str);
                if (this.b.i.getVisibility() == 4) {
                    this.b.i.setVisibility(0);
                }
            }

            public void onReceivedError(WebView webView, int i, String str, String str2) {
                f.d("H5GameActivity", "--->onReceivedError");
                if (this.b.i.getVisibility() == 0) {
                    this.b.i.setVisibility(4);
                }
                com.qq.reader.common.monitor.a.a().a(str2, i, str);
                this.b.l = false;
                if (this.b.k) {
                    this.b.k = false;
                    webView.loadUrl("file:///android_asset/empty.html");
                    return;
                }
                this.b.k = true;
                this.b.i.loadUrl(str2);
            }

            public void onPageFinished(WebView webView, String str) {
                super.onPageFinished(webView, str);
                f.d("H5GameActivity", "--->onPageFinished");
                com.qq.reader.common.monitor.debug.c.e("timetest", "page finish");
                if (this.b.l) {
                    this.b.a(this.b.e(str));
                }
            }
        });
    }

    private boolean e(String str) {
        if (str == null) {
            return false;
        }
        return str.startsWith("file:///");
    }

    protected void a(WebView webView) {
        this.o = new a(this);
        webView.setWebChromeClient(this.o);
    }

    public void h() {
        WebSettings settings = this.i.getSettings();
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
    }

    private void a(boolean z) {
        if (z) {
            this.h.setVisibility(0);
        } else {
            this.h.setVisibility(8);
        }
        if (this.c.getVisibility() == 0) {
            this.c.setVisibility(8);
        }
        if (this.g.getVisibility() == 0) {
            this.g.setVisibility(8);
        }
    }

    private void p() {
        CookieSyncManager.createInstance(this);
        CookieManager.getInstance().removeAllCookie();
        CookieSyncManager.getInstance().sync();
    }

    protected void onDestroy() {
        super.onDestroy();
        this.j.a();
        this.i.destroy();
        if (this.u != null) {
            try {
                if (this.w != null) {
                    unbindService(this.w);
                    this.w = null;
                }
                this.x = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.u.unBindAIDL();
        }
    }

    private void q() {
        ReaderTask h5GameGetOpenidTask = new H5GameGetOpenidTask(this.x);
        h5GameGetOpenidTask.setListener(this.p);
        g.a().a(h5GameGetOpenidTask);
    }

    private void r() {
        g.a().a(new ProfileNetTask(new com.qq.reader.common.readertask.ordinal.c(this) {
            final /* synthetic */ H5GameActivity a;

            {
                this.a = r1;
            }

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    final int optInt = jSONObject.optInt("balance");
                    final int optInt2 = jSONObject.optInt("bookTicket");
                    this.a.x.a(optInt);
                    this.a.x.b(optInt2);
                    this.a.runOnUiThread(new Runnable(this) {
                        final /* synthetic */ AnonymousClass9 c;

                        public void run() {
                            this.c.a.a(optInt);
                            this.c.a.b(optInt2);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                f.a("LoginHelper", "error");
            }
        }, this.x));
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        a(intent);
    }

    public void a(String str, String str2) {
        f.a("testjs", "setTitleBar " + str + " " + str2);
        if (str.equals("true")) {
            if (this.d.getVisibility() != 0) {
                this.d.setVisibility(0);
                aj.a((Activity) this, false);
            }
            if (str2.equals("true")) {
                this.f.setVisibility(0);
            } else if (str2.equals("false")) {
                this.f.setVisibility(8);
            }
        } else if (str.equals("false")) {
            aj.a((Activity) this, true);
            this.d.setVisibility(8);
        }
    }
}
