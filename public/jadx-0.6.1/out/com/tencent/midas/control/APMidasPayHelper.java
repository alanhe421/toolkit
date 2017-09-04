package com.tencent.midas.control;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.text.TextUtils;
import android.webkit.WebView;
import android.widget.Toast;
import com.pay.tool.APMidasTools;
import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import com.tencent.midas.api.APMidasPayAPI;
import com.tencent.midas.api.APMidasResponse;
import com.tencent.midas.api.IAPMidasNetCallBack;
import com.tencent.midas.api.IAPMidasPayCallBack;
import com.tencent.midas.api.request.APMidasBaseRequest;
import com.tencent.midas.api.request.APMidasNetRequest;
import com.tencent.midas.api.request.APPurchase;
import com.tencent.midas.comm.APLog;
import com.tencent.midas.control.APCallBackResultReceiver.Receiver;
import com.tencent.midas.data.APMidasPluginInfo;
import com.tencent.midas.data.APPluginDataInterface;
import com.tencent.midas.data.APPluginReportManager;
import com.tencent.midas.jsbridge.APWebJSBridgeActivity;
import com.tencent.midas.plugin.APPluginProxyActivity;
import com.tencent.midas.plugin.APPluginStatic;
import com.tencent.midas.plugin.APPluginUtils;
import com.tencent.mobileqq.openpay.constants.OpenConstants;
import com.tencent.open.SocialConstants;
import org.json.JSONException;

public class APMidasPayHelper implements Receiver {
    public static String MED_DISTRIBUTE_CALL = "openMidasCall";
    public static String MED_DISTRIBUTE_CALL2 = "openMidasCall2";
    public static String MED_DISTRIBUTE_H5PAY = "openMidasH5Pay";
    public static String MED_DISTRIBUTE_HF_COUPONS_ROLLBACK = "hfCouponsRollBack";
    public static String MED_DISTRIBUTE_INFO = "openMidasInfo";
    public static String MED_DISTRIBUTE_INIT = "golbalInit";
    public static String MED_DISTRIBUTE_NET = "openMidasNet";
    public static String MED_DISTRIBUTE_PAY = "openMidasPay";
    public static String MED_DISTRIBUTE_WEB = "openMidasWeb";
    public static String MED_DISTRIBUTE_XGAME_CONSUME = "consumeAsync";
    public static String MED_DISTRIBUTE_XGAME_QUERY = "queryInventoryAsync";
    public static int MIDAS_INNER_WEBVIEW = 0;
    public static int MIDAS_OUT_WEBVIEW = 1;
    public static String MIDAS_PLUGIN_NAME = "MidasPay";
    public static final String MIDAS_PLUGIN_VERSION = "1.6.3d";
    public static int MIDAS_WEBVIEW = 0;
    public static String PKG_DISTRIBUTE = "com.tencent.midas.pay.APMidasDistribute";
    public static final int PLUGIN_INITFAIL = 2;
    public static final int PLUGIN_INITING = 0;
    public static final int PLUGIN_INITSUCC = 1;
    private static String c = "release";
    private static boolean d;
    private static String e = "";
    private static int f = 0;
    private static Object g = new Object();
    private static Object h = new Object();
    private static Object i = new Object();
    public static int initState = -1;
    private static WebView j;
    private static boolean k = false;
    private static APMidasBaseRequest l = null;
    private static com.tencent.smtt.sdk.WebView m;
    public static IAPMidasPayCallBack midasCallBack = null;
    private static boolean n = false;
    public static IAPMidasNetCallBack netCallBack = null;
    private static APCallBackResultReceiver o = null;
    private static boolean p = false;
    Object a = null;
    private Activity b;
    public int saveType = 0;
    public int screenType = 0;

    private int a(Activity activity, WebView webView, String str, String str2, String str3, String str4) {
        this.b = activity;
        APLog.i("APMidasPayHelper", "toH5Midas initState: " + initState);
        synchronized (g) {
            final ProgressDialog progressDialog = new ProgressDialog(activity);
            progressDialog.setIndeterminate(false);
            progressDialog.setProgressStyle(0);
            progressDialog.setTitle("温馨提示");
            progressDialog.setMessage("腾讯支付服务初始化中");
            if (initState == -1 || initState == 0) {
                try {
                    progressDialog.show();
                } catch (Throwable th) {
                }
            }
            if (initState == -1) {
                b(activity, str4, null);
            }
            if (initState == 0) {
                final Activity activity2 = activity;
                final String str5 = str;
                final String str6 = str2;
                final String str7 = str3;
                new Thread(new Runnable(this) {
                    final /* synthetic */ APMidasPayHelper f;

                    public void run() {
                        try {
                            synchronized (APMidasPayHelper.i) {
                                APMidasPayHelper.i.wait();
                            }
                        } catch (InterruptedException e) {
                            APLog.w("APMidasPayHelper", "toH5Midas e:" + e.toString());
                        }
                        try {
                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }
                        } catch (Throwable th) {
                        }
                        this.f.a(activity2, str5, str6, str7);
                    }
                }).start();
                return 0;
            }
            int a = a(activity, str, str2, str3);
            return a;
        }
    }

    private int a(Activity activity, APMidasBaseRequest aPMidasBaseRequest, String str, String str2) {
        if (aPMidasBaseRequest == null) {
            APLog.w("APMidasPayHelper", "toMidas pay request is null");
            return -1;
        }
        this.b = activity;
        APLog.i("APMidasPayHelper", "toMidas initState: " + initState);
        synchronized (g) {
            final ProgressDialog progressDialog = new ProgressDialog(activity);
            progressDialog.setIndeterminate(false);
            progressDialog.setProgressStyle(0);
            progressDialog.setTitle("温馨提示");
            progressDialog.setMessage("腾讯支付服务初始化中");
            final long currentTimeMillis = System.currentTimeMillis();
            n = APPluginUtils.isNeedUpdateFromLocal(activity);
            if (n) {
                release(activity);
                synchronized (g) {
                    initState = -1;
                }
            }
            if (!((initState != -1 && initState != 0) || MED_DISTRIBUTE_NET.equals(str) || MED_DISTRIBUTE_INFO.equals(str))) {
                try {
                    progressDialog.show();
                } catch (Throwable th) {
                }
            }
            if (initState == -1) {
                b(activity, str2, null);
            }
            if (initState == 0) {
                final Activity activity2 = activity;
                final APMidasBaseRequest aPMidasBaseRequest2 = aPMidasBaseRequest;
                final String str3 = str;
                final String str4 = str2;
                new Thread(new Runnable(this) {
                    final /* synthetic */ APMidasPayHelper g;

                    public void run() {
                        try {
                            synchronized (APMidasPayHelper.i) {
                                APMidasPayHelper.i.wait();
                            }
                        } catch (InterruptedException e) {
                            APLog.w("APMidasPayHelper", e.toString());
                        }
                        try {
                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }
                        } catch (Throwable th) {
                        }
                        APPluginReportManager.getInstance().insertTimeDataEx(APMidasTools.getCurrentThreadName(Thread.currentThread()), APPluginReportManager.MIDASPLUGIN_TIMENAME_LAUNCHPAY_WAIT_INIT, currentTimeMillis);
                        this.g.b(activity2, aPMidasBaseRequest2, str3, str4);
                    }
                }).start();
                return 0;
            }
            int b = b(activity, aPMidasBaseRequest, str, str2);
            return b;
        }
    }

    private int a(Activity activity, final String str, final String str2, final String str3) {
        synchronized (g) {
            if (initState == 2) {
                APLog.i("APMidasPayHelper", "toH5MidasPay plugin init error");
                j();
                initState = -1;
                return -1;
            }
            activity.runOnUiThread(new Runnable(this) {
                final /* synthetic */ APMidasPayHelper d;

                public void run() {
                    Intent intent = new Intent();
                    intent.putExtra(ComicStoreExclusiveItemCard.NET_AD_ATTR_VERSION, APMidasPayAPI.getMidasPluginVersion());
                    intent.putExtra("env", APMidasPayHelper.c);
                    intent.putExtra("screenType", this.d.screenType);
                    intent.putExtra("logEnable", APMidasPayHelper.d);
                    intent.putExtra("req", "H5Pay");
                    intent.putExtra(SocialConstants.PARAM_URL, str);
                    intent.putExtra("message", str2);
                    intent.putExtra("reqType", APMidasPayHelper.e);
                    if (APMidasPayHelper.p) {
                        APMidasPayHelper.o = new APCallBackResultReceiver(new Handler());
                        APMidasPayHelper.o.setReceiver(this.d);
                        intent.putExtra("remoteReceiver", APMidasPayHelper.o);
                    }
                    this.d.a(intent, str3);
                }
            });
            return 0;
        }
    }

    private void a(Intent intent, String str) {
        Object initPluginInterface;
        try {
            initPluginInterface = new APPluginProxyActivity().initPluginInterface(this.b, MIDAS_PLUGIN_NAME, PKG_DISTRIBUTE, str, new Object[]{this.b, intent});
        } catch (Exception e) {
            APLog.i("APMidasPayHelper", "openPlugin error:" + e.toString());
            initPluginInterface = null;
        }
        try {
            APPluginReportManager.getInstance().dataReport(APPluginDataInterface.singleton().getLaunchInterface());
        } catch (Exception e2) {
            APLog.i("APMidasPayHelper", "openPlugin dataReport:" + e2.toString());
        }
        APLog.i("APMidasPayHelper", "openPlugin obj:" + initPluginInterface);
    }

    static /* synthetic */ int b() {
        int i = f;
        f = i + 1;
        return i;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int b(android.app.Activity r5, final com.tencent.midas.api.request.APMidasBaseRequest r6, final java.lang.String r7, java.lang.String r8) {
        /*
        r4 = this;
        r0 = -1;
        r1 = g;
        monitor-enter(r1);
        r2 = initState;	 Catch:{ all -> 0x0025 }
        r3 = 2;
        if (r2 != r3) goto L_0x001a;
    L_0x0009:
        r2 = "APMidasPayHelper";
        r3 = "toMidasPay plugin init error";
        com.tencent.midas.comm.APLog.i(r2, r3);	 Catch:{ all -> 0x0025 }
        r4.j();	 Catch:{ all -> 0x0025 }
        r2 = -1;
        initState = r2;	 Catch:{ all -> 0x0025 }
        monitor-exit(r1);	 Catch:{ all -> 0x0025 }
    L_0x0019:
        return r0;
    L_0x001a:
        monitor-exit(r1);	 Catch:{ all -> 0x0025 }
        r0 = new com.tencent.midas.control.APMidasPayHelper$6;	 Catch:{ Exception -> 0x0028 }
        r0.<init>(r4, r6, r7);	 Catch:{ Exception -> 0x0028 }
        r5.runOnUiThread(r0);	 Catch:{ Exception -> 0x0028 }
    L_0x0023:
        r0 = 0;
        goto L_0x0019;
    L_0x0025:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0025 }
        throw r0;
    L_0x0028:
        r0 = move-exception;
        goto L_0x0023;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.midas.control.APMidasPayHelper.b(android.app.Activity, com.tencent.midas.api.request.APMidasBaseRequest, java.lang.String, java.lang.String):int");
    }

    private static void b(final Context context, final String str, final IAPInitCallBack iAPInitCallBack) {
        synchronized (g) {
            initState = 0;
        }
        Thread thread = new Thread(new Runnable() {
            public void run() {
                APMidasPayHelper.c(context, str, iAPInitCallBack);
            }
        });
        thread.setName(APPluginDataInterface.singleton().getLaunchInterface());
        thread.start();
    }

    private static void c(final Context context, final String str, final IAPInitCallBack iAPInitCallBack) {
        int i = 0;
        System.currentTimeMillis();
        if (n) {
            APPluginUtils.installFromLocal(context);
            APPluginReportManager.getInstance().insertTimeDataEx(APMidasTools.getCurrentThreadName(Thread.currentThread()), APPluginReportManager.MIDASPLUGIN_TIMENAME_INSTALL_FROM_LOCAL, System.currentTimeMillis());
            n = false;
        }
        long currentTimeMillis = System.currentTimeMillis();
        int isNeedUpdateFromAssets = APPluginUtils.isNeedUpdateFromAssets(context);
        APPluginReportManager.getInstance().insertTimeDataEx(APMidasTools.getCurrentThreadName(Thread.currentThread()), APPluginReportManager.MIDASPLUGIN_TIMENAME_IS_NEED_ASSETS_UPDATE, currentTimeMillis);
        APLog.i("APMidasPayHelper", "preLoadMidasPay isNeedUpdateFromAssets:" + isNeedUpdateFromAssets);
        if (isNeedUpdateFromAssets > 0) {
            currentTimeMillis = System.currentTimeMillis();
            i = APPluginUtils.installPlugin(context, isNeedUpdateFromAssets);
            APPluginReportManager.getInstance().insertTimeDataEx(APMidasTools.getCurrentThreadName(Thread.currentThread()), APPluginReportManager.MIDASPLUGIN_TIMENAME_INSTALL_FROM_ASSETS, currentTimeMillis);
        }
        APLog.i("APMidasPayHelper", "preLoadMidasPay installPlugin ret:" + i + " initRequest:" + l);
        synchronized (g) {
            if (i != 0) {
                f = 0;
                initState = 2;
                if (iAPInitCallBack != null) {
                    iAPInitCallBack.result(-1, APPluginUtils.getInitErrorMsg(), str, null);
                }
            } else {
                currentTimeMillis = System.currentTimeMillis();
                boolean isPluginValid = APPluginUtils.isPluginValid(context);
                APPluginReportManager.getInstance().insertTimeDataEx(APMidasTools.getCurrentThreadName(Thread.currentThread()), APPluginReportManager.MIDASPLUGIN_TIMENAME_PLUGIN_VALID, currentTimeMillis);
                if (isPluginValid) {
                    try {
                        currentTimeMillis = System.currentTimeMillis();
                        APPluginStatic.preCreateClassLoaderByPath(context);
                        APPluginReportManager.getInstance().insertTimeDataEx(APMidasTools.getCurrentThreadName(Thread.currentThread()), APPluginReportManager.MIDASPLUGIN_TIMENAME_LOAD_DEX, currentTimeMillis);
                    } catch (Exception e) {
                        APLog.e("APMidasPayHelper", "preLoadMidasPay preCreateClassLoaderByPath e: " + e.toString());
                        e.printStackTrace();
                    }
                    final String name = Thread.currentThread().getName();
                    if (context instanceof Activity) {
                        ((Activity) context).runOnUiThread(new Runnable() {
                            public void run() {
                                Object initPluginInterface;
                                Exception e;
                                Intent intent = new Intent();
                                intent.putExtra(ComicStoreExclusiveItemCard.NET_AD_ATTR_VERSION, "1.6.3d");
                                intent.putExtra("req", APMidasPayHelper.l);
                                intent.putExtra("env", APMidasPayHelper.c);
                                intent.putExtra("logEnable", APMidasPayHelper.d);
                                intent.putExtra("launchInterfaceName", name);
                                Activity activity = (Activity) context;
                                APPluginProxyActivity aPPluginProxyActivity = new APPluginProxyActivity();
                                try {
                                    long currentTimeMillis = System.currentTimeMillis();
                                    initPluginInterface = aPPluginProxyActivity.initPluginInterface(activity, APMidasPayHelper.MIDAS_PLUGIN_NAME, APMidasPayHelper.PKG_DISTRIBUTE, APMidasPayHelper.MED_DISTRIBUTE_INIT, new Object[]{activity, intent});
                                    try {
                                        APPluginReportManager.getInstance().insertTimeDataEx(name, APPluginReportManager.MIDASPLUGIN_TIMENAME_INIT_KERNEL, currentTimeMillis);
                                        if (iAPInitCallBack != null) {
                                            iAPInitCallBack.result(0, "succ", str, null);
                                        }
                                    } catch (Exception e2) {
                                        e = e2;
                                        e.printStackTrace();
                                        if (iAPInitCallBack != null) {
                                            iAPInitCallBack.result(-2, e.toString(), str, null);
                                        }
                                        APLog.i("APMidasPayHelper", "preLoadMidasPay openPlugin obj:" + initPluginInterface);
                                        APPluginReportManager.getInstance().dataReport(APMidasTools.getCurrentThreadName(Thread.currentThread()));
                                        APPluginReportManager.getInstance().insertTimeData(name, APPluginReportManager.MIDASPLUGIN_TIMENAME_INIT_TOTALTIME);
                                        APLog.i("APMidasPayHelper", "preLoadMidasPay initState = PLUGIN_INITSUCC");
                                        APMidasPayHelper.initState = 1;
                                        synchronized (APMidasPayHelper.i) {
                                            APMidasPayHelper.i.notifyAll();
                                        }
                                    }
                                } catch (Exception e3) {
                                    e = e3;
                                    initPluginInterface = null;
                                    e.printStackTrace();
                                    if (iAPInitCallBack != null) {
                                        iAPInitCallBack.result(-2, e.toString(), str, null);
                                    }
                                    APLog.i("APMidasPayHelper", "preLoadMidasPay openPlugin obj:" + initPluginInterface);
                                    APPluginReportManager.getInstance().dataReport(APMidasTools.getCurrentThreadName(Thread.currentThread()));
                                    APPluginReportManager.getInstance().insertTimeData(name, APPluginReportManager.MIDASPLUGIN_TIMENAME_INIT_TOTALTIME);
                                    APLog.i("APMidasPayHelper", "preLoadMidasPay initState = PLUGIN_INITSUCC");
                                    APMidasPayHelper.initState = 1;
                                    synchronized (APMidasPayHelper.i) {
                                        APMidasPayHelper.i.notifyAll();
                                    }
                                }
                                APLog.i("APMidasPayHelper", "preLoadMidasPay openPlugin obj:" + initPluginInterface);
                                APPluginReportManager.getInstance().dataReport(APMidasTools.getCurrentThreadName(Thread.currentThread()));
                                APPluginReportManager.getInstance().insertTimeData(name, APPluginReportManager.MIDASPLUGIN_TIMENAME_INIT_TOTALTIME);
                                APLog.i("APMidasPayHelper", "preLoadMidasPay initState = PLUGIN_INITSUCC");
                                APMidasPayHelper.initState = 1;
                                synchronized (APMidasPayHelper.i) {
                                    APMidasPayHelper.i.notifyAll();
                                }
                            }
                        });
                    } else {
                        initState = 1;
                        synchronized (i) {
                            i.notifyAll();
                        }
                    }
                } else {
                    APLog.i("APMidasPayHelper", "preLoadMidasPay isPluginValid false");
                    APPluginUtils.unInstallPlugin(context);
                    initState = -1;
                    if (iAPInitCallBack != null) {
                        f = 0;
                        iAPInitCallBack.result(-1, "支付插件校验失败", str, null);
                    }
                    synchronized (i) {
                        i.notifyAll();
                    }
                }
            }
        }
    }

    public static synchronized String getJSContent(Context context) {
        String str;
        synchronized (APMidasPayHelper.class) {
            APLog.i("APMidasPayHelper", "getJSContent");
            init(context, null);
            str = "";
            str = (String) new APMidasPayHelper().callWithContext(context, "getH5JS", new Object[]{context});
        }
        return str;
    }

    public static synchronized void h5Init(final Activity activity, final WebView webView, final com.tencent.smtt.sdk.WebView webView2) {
        synchronized (APMidasPayHelper.class) {
            APLog.i("APMidasPayHelper", "h5Init");
            p = isNewProcess(activity);
            if (f < 1) {
                APPluginUtils.release();
                b(activity, APMidasPluginInfo.LAUNCH_INTERFACE_INIT, new IAPInitCallBack() {
                    public void result(int i, String str, String str2, Bundle bundle) {
                        APLog.i("APMidasPayHelper", "init ret:" + i + " msg:" + str);
                        APPluginReportManager.getInstance().dataReport(APMidasPluginInfo.LAUNCH_INTERFACE_INIT);
                        if (i == 0) {
                            String str3 = "";
                            str3 = (String) new APMidasPayHelper().call(activity, "getH5JS", new Object[]{activity});
                            if (!TextUtils.isEmpty(str3)) {
                                try {
                                    if (webView != null) {
                                        webView.loadUrl("javascript:" + str3);
                                    }
                                    if (webView2 != null) {
                                        webView2.loadUrl("javascript:" + str3);
                                    }
                                } catch (Exception e) {
                                    APLog.w("APMidasPayHelper", "h5Init loadJS error:" + e.toString());
                                }
                            }
                            APMidasPayHelper.b();
                        }
                    }
                });
            } else {
                String str = "";
                str = (String) new APMidasPayHelper().call(activity, "getH5JS", new Object[]{activity});
                if (!TextUtils.isEmpty(str)) {
                    if (webView != null) {
                        try {
                            webView.loadUrl("javascript:" + str);
                        } catch (Exception e) {
                            APLog.w("APMidasPayHelper", "h5Init loadJS error:" + e.toString());
                        }
                    }
                    if (webView2 != null) {
                        webView2.loadUrl("javascript:" + str);
                    }
                }
            }
        }
    }

    public static synchronized void init(Context context, APMidasBaseRequest aPMidasBaseRequest) {
        synchronized (APMidasPayHelper.class) {
            APLog.i("APMidasPayHelper", "init initCount:" + f);
            p = isNewProcess(context);
            l = aPMidasBaseRequest;
            if (f < 1 && !k) {
                APPluginUtils.release();
                b(context, APMidasPluginInfo.LAUNCH_INTERFACE_INIT, new IAPInitCallBack() {
                    public void result(int i, String str, String str2, Bundle bundle) {
                        APLog.i("APMidasPayHelper", "initcallback ret:" + i + " msg:" + str);
                        synchronized (APMidasPayHelper.h) {
                            APMidasPayHelper.k = true;
                            APMidasPayHelper.h.notifyAll();
                        }
                        APPluginReportManager.getInstance().dataReport(APMidasPluginInfo.LAUNCH_INTERFACE_INIT);
                    }
                });
            } else if (k) {
                APLog.i("APMidasPayHelper", "initcallback isInitSucc direct call init function");
                Intent intent = new Intent();
                intent.putExtra(ComicStoreExclusiveItemCard.NET_AD_ATTR_VERSION, "1.6.3d");
                intent.putExtra("req", l);
                intent.putExtra("env", c);
                intent.putExtra("logEnable", d);
                intent.putExtra("launchInterfaceName", Thread.currentThread().getName());
                APPluginProxyActivity aPPluginProxyActivity = new APPluginProxyActivity();
                try {
                    aPPluginProxyActivity.initPluginInterface(context, MIDAS_PLUGIN_NAME, PKG_DISTRIBUTE, MED_DISTRIBUTE_INIT, new Object[]{context, intent});
                } catch (Exception e) {
                    APLog.i("APMidasPayHelper", "initcallback isInitSucc direct call init function e:" + e.toString());
                }
            }
            f++;
        }
    }

    public static boolean isNewProcess(Context context) {
        String str = "com.tencent.midas.proxyactivity.APMidasPayProxyActivity";
        try {
            PackageManager packageManager = context.getApplicationContext().getPackageManager();
            ActivityInfo[] activityInfoArr = packageManager.getPackageInfo(packageManager.getPackageInfo(context.getApplicationContext().getPackageName(), 0).packageName, 1).activities;
            for (ActivityInfo activityInfo : activityInfoArr) {
                if (activityInfo.name.equals("com.tencent.midas.proxyactivity.APMidasPayProxyActivity")) {
                    Object obj = activityInfo.processName;
                    if (!TextUtils.isEmpty(obj) && obj.contains("midasPay")) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
        }
        return false;
    }

    private void j() {
        APLog.i("APMidasPayHelper", "pluginInitErrCallBack" + midasCallBack);
        this.b.runOnUiThread(new Runnable(this) {
            final /* synthetic */ APMidasPayHelper a;

            {
                this.a = r1;
            }

            public void run() {
                APPluginReportManager.getInstance().insertData(APPluginDataInterface.singleton().getLaunchInterface(), APPluginReportManager.MIDASPLUGIN_FORMAT_APKLOAD_ERROR, "", APPluginUtils.getInitErrorMsg());
                Toast.makeText(this.a.b, "腾讯支付服务加载失败，请退出重试", 1).show();
                APMidasResponse aPMidasResponse = new APMidasResponse();
                aPMidasResponse.resultCode = -100;
                if (APMidasPayHelper.midasCallBack != null) {
                    APMidasPayHelper.midasCallBack.MidasPayCallBack(aPMidasResponse);
                    APMidasPayHelper.midasCallBack = null;
                }
                if (APMidasPayHelper.netCallBack != null) {
                    APMidasPayHelper.netCallBack.MidasNetError(APMidasPayHelper.e, -100, "腾讯支付服务加载失败，请退出重试");
                    APMidasPayHelper.netCallBack = null;
                    APMidasPayHelper.e = "";
                }
                APPluginReportManager.getInstance().dataReport(APPluginDataInterface.singleton().getLaunchInterface());
            }
        });
    }

    public static void midasCallBack(APMidasResponse aPMidasResponse) {
        APLog.i("APMidasPayHelper", "midasCallBack resultCode :" + aPMidasResponse.resultCode + " midasCallBack:" + midasCallBack);
        if (midasCallBack != null) {
            midasCallBack.MidasPayCallBack(aPMidasResponse);
            midasCallBack = null;
        }
        if (o != null) {
            o.setReceiver(null);
            o = null;
        }
        APPluginUtils.release();
        APLog.closeLog();
    }

    public static void midasH5CallBack(String str) {
        APLog.i("APMidasPayHelper", "midasH5CallBack params:" + str + " webview:" + j + " x5Webview:" + m);
        if (j != null) {
            try {
                j.loadUrl(str);
            } catch (Exception e) {
                APLog.w("APMidasPayHelper", "midasH5CallBack error:" + e.toString());
            }
        }
        if (m != null) {
            try {
                m.loadUrl(str);
            } catch (Exception e2) {
                APLog.w("APMidasPayHelper", "midasH5CallBack error:" + e2.toString());
            }
        }
        if (o != null) {
            o.setReceiver(null);
            o = null;
        }
        APPluginUtils.release();
    }

    public static void midasLoginExpire() {
        if (midasCallBack != null) {
            midasCallBack.MidasPayNeedLogin();
            midasCallBack = null;
        }
        if (o != null) {
            o.setReceiver(null);
            o = null;
        }
        APPluginUtils.release();
        APLog.closeLog();
    }

    public static void onNetError(String str, Integer num, String str2) {
        if (netCallBack != null) {
            netCallBack.MidasNetError(str, num.intValue(), str2);
            netCallBack = null;
            e = "";
        }
    }

    public static void onNetFinish(String str, String str2) {
        if (netCallBack != null) {
            netCallBack.MidasNetFinish(str, str2);
            netCallBack = null;
            e = "";
        }
    }

    public static void onNetStop(String str) {
        if (netCallBack != null) {
            netCallBack.MidasNetStop(str);
            netCallBack = null;
            e = "";
        }
    }

    public static void release(Context context) {
        APLog.d("APMidasPayHelper", "release");
        APPluginUtils.unInstallPlugin(context);
        synchronized (g) {
            initState = -1;
        }
    }

    public static void setEnv(String str) {
        if (!("release".equals(str) || APMidasPayAPI.ENV_TEST.equals(str) || APMidasPayAPI.ENV_DEV.equals(str) || "debug".equals(str) || APMidasPayAPI.ENV_TESTING.equals(str))) {
            str = "release";
        }
        c = str;
    }

    public static void setLogEnable(boolean z) {
        d = z;
    }

    public static void updateRemoteReceiver(ResultReceiver resultReceiver) {
    }

    public Object call(Activity activity, String str, Object[] objArr) {
        return callWithContext(activity, str, objArr);
    }

    public Object call(Activity activity, String str, Object[] objArr, Class<?>[] clsArr) {
        return callWithContext(activity, str, objArr, clsArr);
    }

    public Object callWithContext(Context context, String str, Object[] objArr) {
        try {
            return new APPluginProxyActivity().initPluginInterface(context, MIDAS_PLUGIN_NAME, PKG_DISTRIBUTE, MED_DISTRIBUTE_CALL, new Object[]{str, objArr});
        } catch (Exception e) {
            APLog.i("APMidasPayHelper", "callWithContext error:" + e.toString());
            return null;
        }
    }

    public Object callWithContext(Context context, String str, Object[] objArr, Class<?>[] clsArr) {
        synchronized (g) {
            APLog.i("callWithContext ", "initState:" + initState);
            if (initState == 0) {
                final Context context2 = context;
                final String str2 = str;
                final Object[] objArr2 = objArr;
                final Class<?>[] clsArr2 = clsArr;
                new Thread(new Runnable(this) {
                    final /* synthetic */ APMidasPayHelper e;

                    public void run() {
                        try {
                            APLog.d("callWithContext ", "PLUGIN_INITING wait");
                            synchronized (APMidasPayHelper.h) {
                                APMidasPayHelper.h.wait();
                            }
                            APLog.d("callWithContext ", "PLUGIN_INITING go");
                            ((Activity) context2).runOnUiThread(new Runnable(this) {
                                final /* synthetic */ AnonymousClass4 a;

                                {
                                    this.a = r1;
                                }

                                public void run() {
                                    APPluginProxyActivity aPPluginProxyActivity = new APPluginProxyActivity();
                                    try {
                                        this.a.e.a = aPPluginProxyActivity.initPluginInterface2(context2, APMidasPayHelper.MIDAS_PLUGIN_NAME, APMidasPayHelper.PKG_DISTRIBUTE, APMidasPayHelper.MED_DISTRIBUTE_CALL2, new Object[]{str2, objArr2, clsArr2});
                                    } catch (Exception e) {
                                        APLog.e("callWithContext", "error1 " + e.toString());
                                    }
                                }
                            });
                        } catch (InterruptedException e) {
                            APLog.e("callWithContext", "error2 " + e.toString());
                        }
                    }
                }).start();
            } else {
                APPluginProxyActivity aPPluginProxyActivity = new APPluginProxyActivity();
                try {
                    this.a = aPPluginProxyActivity.initPluginInterface2(context, MIDAS_PLUGIN_NAME, PKG_DISTRIBUTE, MED_DISTRIBUTE_CALL2, new Object[]{str, objArr, clsArr});
                } catch (Exception e) {
                    APLog.e("callWithContext", "error3 " + e.toString());
                }
            }
        }
        return this.a;
    }

    public int getInfo(Activity activity, String str, APMidasBaseRequest aPMidasBaseRequest, IAPMidasNetCallBack iAPMidasNetCallBack) {
        netCallBack = iAPMidasNetCallBack;
        e = str;
        return a(activity, aPMidasBaseRequest, MED_DISTRIBUTE_INFO, "getInfo");
    }

    public int h5Pay(Activity activity, WebView webView, com.tencent.smtt.sdk.WebView webView2, String str, String str2) {
        j = webView;
        m = webView2;
        APLog.i("APMidasPayHelper", "h5Pay webview:" + j + " x5Webview:" + m + " msg:" + str2);
        if (TextUtils.isEmpty(str2) || !str2.startsWith("midas_js_bridge_")) {
            return -2;
        }
        return a(activity, webView, str, str2, MED_DISTRIBUTE_H5PAY, "h5Pay");
    }

    public void launchWeb(Activity activity, APMidasBaseRequest aPMidasBaseRequest, IAPMidasPayCallBack iAPMidasPayCallBack) {
        Intent intent = new Intent();
        intent.setClass(activity, APWebJSBridgeActivity.class);
        activity.startActivity(intent);
    }

    public int net(Activity activity, APMidasNetRequest aPMidasNetRequest, IAPMidasNetCallBack iAPMidasNetCallBack) {
        netCallBack = iAPMidasNetCallBack;
        e = aPMidasNetRequest.reqType;
        return a(activity, (APMidasBaseRequest) aPMidasNetRequest, MED_DISTRIBUTE_NET, "net");
    }

    public void onReceiveResult(int i, Bundle bundle) {
        APLog.i("APMidasPayHelper", "remotRecevier payHelper resultCode:" + i);
        if (i == 0) {
            progressRemoteInfo(bundle);
        }
    }

    public int pay(Activity activity, APMidasBaseRequest aPMidasBaseRequest, IAPMidasPayCallBack iAPMidasPayCallBack) {
        if (APMidasPayAPI.ENV_TEST.equals(c) && f < 1) {
            Toast.makeText(activity, "腾讯支付尚未初始化，请先调用初始化接口!", 1).show();
        }
        midasCallBack = iAPMidasPayCallBack;
        return a(activity, aPMidasBaseRequest, MED_DISTRIBUTE_PAY, OpenConstants.API_NAME_PAY);
    }

    public void progressRemoteInfo(Bundle bundle) {
        String string = bundle.getString("type");
        APLog.i("APMidasPayHelper", "progressRemoteInfo type:" + string);
        if ("callback".equals(string)) {
            APPurchase aPPurchase;
            APMidasResponse aPMidasResponse = new APMidasResponse();
            aPMidasResponse.resultCode = bundle.getInt("resultCode", -1);
            aPMidasResponse.resultInerCode = bundle.getInt("resultInerCode");
            aPMidasResponse.realSaveNum = bundle.getInt("realSaveNum");
            aPMidasResponse.payChannel = bundle.getInt("payChannel");
            aPMidasResponse.payState = bundle.getInt("payState");
            aPMidasResponse.provideState = bundle.getInt("provideState");
            aPMidasResponse.resultMsg = bundle.getString("resultMsg");
            aPMidasResponse.extendInfo = bundle.getString("extendInfo");
            aPMidasResponse.payReserve1 = bundle.getString("payReserve1");
            aPMidasResponse.payReserve2 = bundle.getString("payReserve2");
            aPMidasResponse.payReserve3 = bundle.getString("payReserve3");
            try {
                aPPurchase = new APPurchase(bundle.getString("purchaseJson"), bundle.getString("purchaseSign"));
            } catch (JSONException e) {
                JSONException jSONException = e;
                aPPurchase = new APPurchase();
                APLog.w("progressRemoteInfo", "purchase creat fail1 " + jSONException.toString());
            } catch (Exception e2) {
                Exception exception = e2;
                aPPurchase = new APPurchase();
                APLog.w("progressRemoteInfo", "purchase creat fail2 " + exception.toString());
            }
            aPMidasResponse.mAPPurchase = aPPurchase;
            midasCallBack(aPMidasResponse);
        } else if ("h5callback".equals(string)) {
            midasH5CallBack(bundle.getString("callbackinfo"));
        } else if ("needlogin".equals(string)) {
            midasLoginExpire();
        }
    }

    public void setScreenType(int i) {
        this.screenType = i;
    }

    public void web(Activity activity, APMidasBaseRequest aPMidasBaseRequest, IAPMidasPayCallBack iAPMidasPayCallBack) {
        midasCallBack = iAPMidasPayCallBack;
        launchWeb(activity, aPMidasBaseRequest, iAPMidasPayCallBack);
    }
}
