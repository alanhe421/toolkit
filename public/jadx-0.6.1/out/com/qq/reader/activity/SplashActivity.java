package com.qq.reader.activity;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.content.pm.PackageInfo;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.app.a.a;
import android.text.TextUtils;
import android.view.KeyEvent;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.appconfig.b;
import com.qq.reader.common.db.handle.s;
import com.qq.reader.common.db.handle.v;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.monitor.j;
import com.qq.reader.common.monitor.k;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderShortTask;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.utils.ad;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.n;
import com.qq.reader.common.utils.u;
import com.qq.reader.common.web.js.JSContent;
import com.qq.reader.view.al;
import com.qq.reader.wxapi.WXApiManager;
import com.tencent.feedback.proguard.R;
import java.io.File;
import java.io.IOException;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;

public class SplashActivity extends ReaderBaseActivity implements a, com.qq.reader.module.bookstore.qnative.c.a {
    Context a;
    u b;
    private b c;
    private Handler d = new Handler(this) {
        final /* synthetic */ SplashActivity a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            Intent intent;
            int aV;
            Bundle bundle;
            switch (message.what) {
                case 20:
                    bundle = (Bundle) message.obj;
                    if (VERSION.SDK_INT >= 23) {
                        this.a.showFragmentDialog(801, bundle);
                        return;
                    }
                    return;
                case 200:
                    String string;
                    boolean z;
                    boolean z2;
                    Intent intent2;
                    int aS;
                    String a;
                    ReaderApplication.timeLog.addSplit("MESSAGE_HANDLE_DISMISS start");
                    try {
                        bundle = this.a.getIntent().getExtras();
                        if (bundle != null) {
                            string = bundle.getString("OPENURL");
                            if (string != null && new JSContent(this.a).openDetail(string)) {
                                this.a.finish();
                                return;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        bundle = this.a.getIntent().getExtras();
                        if (bundle != null) {
                            string = bundle.getString("qurl");
                            if (string != null && string.length() > 0 && string.startsWith("uniteqqreader://") && new JSContent(this.a).openDetail(string)) {
                                this.a.finish();
                                return;
                            }
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    Intent a2 = ad.a(this.a.a);
                    if (a2 != null) {
                        string = a2.getExtras().getString("type");
                        Object string2 = a2.getExtras().getString("comicID");
                        if ("READING_SAVE_COMIC".equals(string) && !TextUtils.isEmpty(string2)) {
                            com.qq.reader.module.comic.a.a().a(this.a, string2, "", 0, "", true, 1);
                            z = true;
                            if (a2 == null) {
                                a2.putExtra("is_from_splashactivity", true);
                                a2.setClass(this.a.a, ReaderPageActivity.class);
                                z2 = z;
                                intent = a2;
                                break;
                            }
                            intent2 = new Intent();
                            c.a("guide", "hasWebUserLike " + d.aT(ReaderApplication.getApplicationImp()) + "  " + d.aS(ReaderApplication.getApplicationImp()));
                            aS = d.aS(ReaderApplication.getApplicationImp());
                            z2 = aS >= 4 && aS > 0;
                            a = com.qq.reader.common.utils.d.a(this.a.getApplicationContext());
                            if (!com.qq.reader.common.c.a.V || com.qq.reader.common.c.a.U || com.qq.reader.common.c.a.T) {
                                if (TextUtils.isEmpty(a) && a.contains("promotionpage")) {
                                    string = a.substring(a.indexOf("text"), a.length());
                                    intent2.setClass(this.a, WebBrowserForContents.class);
                                    intent2.putExtra("com.qq.reader.WebContent", "http://pages.book.qq.com/yyb?" + string);
                                    intent2.putExtra("open_promotion", true);
                                    intent2.setFlags(SigType.WLOGIN_QRPUSH);
                                    com.qq.reader.common.utils.d.b(this.a.getApplicationContext());
                                } else {
                                    intent2.setClass(this.a.a, GuideActivity.class);
                                }
                            } else if (z2) {
                                aV = d.aV(ReaderApplication.getApplicationImp());
                                if (aV > 5 || aV <= 0) {
                                    d.H(ReaderApplication.getApplicationImp(), aS);
                                }
                                intent2.setClass(this.a.a, MainActivity.class);
                            } else if (TextUtils.isEmpty(a) || !a.contains("promotionpage")) {
                                intent2.setClass(this.a.a, GuideActivity.class);
                            } else {
                                string = a.substring(a.indexOf("text"), a.length());
                                intent2.setClass(this.a, WebBrowserForContents.class);
                                intent2.putExtra("com.qq.reader.WebContent", "http://pages.book.qq.com/yyb?" + string);
                                intent2.putExtra("open_promotion", true);
                                intent2.setFlags(SigType.WLOGIN_QRPUSH);
                                com.qq.reader.common.utils.d.b(this.a.getApplicationContext());
                            }
                            if (ao.t(this.a.a)) {
                                intent2.putExtra("IS_FIRST_OPEN_TODAY", true);
                            }
                            z2 = z;
                            intent = intent2;
                            break;
                        }
                    }
                    z = false;
                    if (a2 == null) {
                        a2.putExtra("is_from_splashactivity", true);
                        a2.setClass(this.a.a, ReaderPageActivity.class);
                        z2 = z;
                        intent = a2;
                    } else {
                        intent2 = new Intent();
                        c.a("guide", "hasWebUserLike " + d.aT(ReaderApplication.getApplicationImp()) + "  " + d.aS(ReaderApplication.getApplicationImp()));
                        aS = d.aS(ReaderApplication.getApplicationImp());
                        if (aS >= 4) {
                        }
                        a = com.qq.reader.common.utils.d.a(this.a.getApplicationContext());
                        if (com.qq.reader.common.c.a.V) {
                            break;
                        }
                        if (TextUtils.isEmpty(a)) {
                            break;
                        }
                        intent2.setClass(this.a.a, GuideActivity.class);
                        if (ao.t(this.a.a)) {
                            intent2.putExtra("IS_FIRST_OPEN_TODAY", true);
                        }
                        z2 = z;
                        intent = intent2;
                    }
                    break;
                default:
                    intent = null;
                    aV = 0;
                    break;
            }
            ReaderApplication.timeLog.addSplit("MESSAGE_HANDLE_DISMISS end start Activity");
            if (intent == null) {
                intent = new Intent();
                intent.setClass(this.a.a, MainActivity.class);
            }
            if (aV == 0) {
                this.a.startActivity(intent);
                this.a.overridePendingTransition(0, 0);
            }
            this.a.finish();
        }
    };

    private class AppInitTask extends ReaderShortTask {
        private AppInitTask() {
        }

        public void run() {
            SplashActivity.this.getHandler().post(new Runnable(this) {
                final /* synthetic */ AppInitTask a;

                {
                    this.a = r1;
                }

                public void run() {
                    ao.l(SplashActivity.this.getApplicationContext());
                    ((NotificationManager) SplashActivity.this.getSystemService("notification")).cancel(11);
                }
            });
            ReaderApplication.timeLog.addSplit("AppInitTask 0");
            d.au(SplashActivity.this.getApplicationContext());
            j.a(SplashActivity.this.a.getApplicationContext());
            ao.a(SplashActivity.this);
            com.qq.reader.module.feed.mypreference.c.b();
            StatisticsManager.a().a("event_reader", null);
            if (b.b) {
                SplashActivity.this.b();
            }
            ReaderApplication.timeLog.addSplit("AppInitTask doVerifySignature");
            ReaderApplication.timeLog.addSplit("AppInitTask APMidasPayAPI init");
            SplashActivity.this.e();
            ReaderApplication.timeLog.addSplit("AppInitTask doFirstInstallCheck");
            SplashActivity.this.f();
            ReaderApplication.timeLog.addSplit("AppInitTask doDBInit");
            SplashActivity.this.g();
            ReaderApplication.timeLog.addSplit("AppInitTask doDBVerify");
            SplashActivity.this.d();
            ReaderApplication.timeLog.addSplit("AppInitTask copyNativeData");
            WXApiManager.getInstance(SplashActivity.this.a.getApplicationContext()).justRegisterWXNoBroadcast();
            i.a("event_startup", null, SplashActivity.this.getApplicationContext());
            ReaderApplication.timeLog.addSplit("AppInitTask end");
            SplashActivity.this.d.sendEmptyMessageDelayed(200, SplashActivity.this.c.d());
        }
    }

    protected void onCreate(Bundle bundle) {
        ReaderApplication.timeLog.addSplit("SplashActivity onCreate");
        this.isAllowNet = false;
        super.onCreate(bundle);
        setSwipeBackEnable(false);
        ReaderApplication.timeLog.addSplit("SplashActivity onCreate");
        this.a = this;
        com.qq.reader.common.c.a.a(false);
        if (d.bJ(this)) {
            disableUseAnimation();
            ReaderApplication.timeLog.addSplit("SplashActivity disableUseAnimation");
            this.c = new al();
            setContentView(this.c.a());
            ReaderApplication.timeLog.addSplit("SplashActivity setContentView");
            this.c.a(this, this.d);
            getWindow().setFlags(1024, 1024);
            this.b = new u(new u.a(this) {
                final /* synthetic */ SplashActivity a;

                {
                    this.a = r1;
                }

                public void a() {
                    this.a.a();
                }
            });
        } else {
            disableUseAnimation();
            ReaderApplication.timeLog.addSplit("SplashActivity disableUseAnimation");
            this.c = new al();
            setContentView(this.c.a());
            ReaderApplication.timeLog.addSplit("SplashActivity setContentView");
            this.c.a(this, this.d);
            getWindow().setFlags(1024, 1024);
            this.b = new u(/* anonymous class already generated */);
        }
        if (this.b.a((Activity) this)) {
            this.c.b();
        }
        ReaderApplication.timeLog.addSplit("SplashActivity onCreate end");
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        this.b.a(this, i, strArr, iArr);
    }

    public void a() {
        this.c.c();
        ReaderApplication.timeLog.addSplit("onCreateDeal setSplashImage");
        ReaderApplication.timeLog.addSplit("onCreateDeal nm.cancel");
        ReaderApplication.timeLog.addSplit("onCreateDeal initBrightness");
        try {
            if (getIntent().getBooleanExtra("com.qq.reader.SplashActivity.alarm", false)) {
                j.a(52, 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        g.a().a(new AppInitTask(), 50);
        setIsShowNightMask(false);
        d.a = true;
        ReaderApplication.timeLog.addSplit("onCreateDeal end");
    }

    public boolean needSetImmerseMode() {
        return false;
    }

    private void b() {
        try {
            String a = a(getApplication());
            if (a != null && !a.equals(getResources().getString(R.string.app_sinature))) {
                ReaderApplication.getInstance().isVerifySinatureOK = false;
            }
        } catch (Exception e) {
        }
    }

    private String a(Context context) {
        for (PackageInfo packageInfo : context.getPackageManager().getInstalledPackages(64)) {
            if (packageInfo.packageName.equals(getPackageName())) {
                return ao.r(packageInfo.signatures[0].toCharsString());
            }
        }
        return null;
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        switch (i) {
            case 4:
                return true;
            default:
                return false;
        }
    }

    protected void onResume() {
        super.onResume();
        i.a("event_F209", null, ReaderApplication.getApplicationImp());
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
    }

    private void c() {
        Intent intent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        intent.putExtra("android.intent.extra.shortcut.NAME", getString(R.string.app_name));
        intent.putExtra("duplicate", false);
        intent.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", ShortcutIconResource.fromContext(getApplicationContext(), R.drawable.icon));
        Parcelable intent2 = new Intent(getApplicationContext(), SplashActivity.class);
        intent2.setAction("android.intent.action.MAIN");
        intent2.addCategory("android.intent.category.LAUNCHER");
        intent.putExtra("android.intent.extra.shortcut.INTENT", intent2);
        sendBroadcast(intent);
    }

    public void onStart() {
        super.onStart();
    }

    public void onStop() {
        super.onStop();
    }

    protected void onDestroy() {
        super.onDestroy();
        this.c.e();
        com.qq.reader.common.d.a.a((Context) this);
    }

    private void d() {
        File file = new File(com.qq.reader.common.c.a.ae);
        if (com.qq.reader.common.c.a.U || com.qq.reader.common.c.a.T || file == null || !file.exists()) {
            try {
                ao.a(this.a, "nativedata", file.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void e() {
        com.qq.reader.common.c.a.T = false;
        com.qq.reader.common.c.a.U = false;
        if (ReaderApplication.isFirstInstall) {
            com.qq.reader.common.c.a.F = true;
            com.qq.reader.common.c.a.H = true;
            com.qq.reader.common.c.a.I = true;
            com.qq.reader.common.c.a.O = true;
            com.qq.reader.common.c.a.V = true;
            d.t(getApplicationContext(), true);
            d.g(getApplicationContext(), true);
            com.qq.reader.appconfig.a.c.a(this.a, 0);
            com.qq.reader.appconfig.a.c.c(this.a, "");
            com.qq.reader.appconfig.a.c.e(this.a, "");
            com.qq.reader.appconfig.a.c.d(this.a, "");
        } else {
            com.qq.reader.common.c.a.F = false;
            com.qq.reader.common.c.a.H = false;
            com.qq.reader.common.c.a.K = false;
            com.qq.reader.common.c.a.V = false;
            int j = com.qq.reader.appconfig.a.c.j(this.a);
            if (ao.a((Activity) this, "first_run")) {
                com.qq.reader.appconfig.a.c.a(getApplicationContext(), 0);
                com.qq.reader.appconfig.a.c.d(getApplicationContext(), 0);
                j = 0;
            }
            if (j == 1) {
                com.qq.reader.appconfig.a.c.a = j;
                com.qq.reader.appconfig.a.c.b = com.qq.reader.appconfig.a.c.k(this.a);
                com.qq.reader.appconfig.a.c.d = com.qq.reader.appconfig.a.c.m(this.a);
                com.qq.reader.appconfig.a.c.c = com.qq.reader.appconfig.a.c.l(this.a);
            }
        }
        n.a();
        new k(this.a.getApplicationContext()).a();
        if (ReaderApplication.isFirstInstall) {
            ReaderApplication.isFirstInstall = false;
            d.n(getApplicationContext(), false);
        }
        if (com.qq.reader.common.c.a.F) {
            com.qq.reader.common.c.a.F = false;
            c();
        }
    }

    private void f() {
        ao.a(this.a.getApplicationContext());
        ao.b(this.a.getApplicationContext());
    }

    private void g() {
        if (ao.a((Activity) this, "first_check_db_update")) {
            com.qq.reader.common.db.handle.i.c().m();
            v.b().d();
            s.a().b(null);
        }
    }

    public void doFunction(Bundle bundle) {
    }

    public Activity getFromActivity() {
        return this;
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        Intent intent2 = new Intent();
        intent2.setClass(this.a, MainActivity.class);
        intent2.putExtra("IS_FIRST_OPEN_TODAY", true);
        startActivity(intent2);
        finish();
    }
}
