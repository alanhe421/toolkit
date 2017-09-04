package com.tencent.midas.api;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.webkit.JsResult;
import android.webkit.WebView;
import android.widget.Toast;
import com.pay.tool.APMidasCommMethod;
import com.pay.tool.APMidasTools;
import com.tencent.midas.api.request.APIabResult;
import com.tencent.midas.api.request.APMidasBaseRequest;
import com.tencent.midas.api.request.APMidasGoodsRequest;
import com.tencent.midas.api.request.APMidasNetRequest;
import com.tencent.midas.api.request.APPurchase;
import com.tencent.midas.api.request.APQueryInventoryFinishedListener;
import com.tencent.midas.api.request.OnAPConsumeFinishedListener;
import com.tencent.midas.comm.APLog;
import com.tencent.midas.comm.APLogInfo;
import com.tencent.midas.control.APMidasPayHelper;
import com.tencent.midas.data.APMidasPluginInfo;
import com.tencent.midas.data.APPluginReportManager;
import com.tencent.midas.plugin.APPluginStatic;
import com.tencent.midas.plugin.APPluginUtils;
import dalvik.system.DexClassLoader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class APMidasPayAPI {
    public static final String ACCOUNT_TYPE_COMMON = "common";
    public static final String ACCOUNT_TYPE_SECURITY = "secrety";
    public static final String ENV_DEV = "dev";
    public static final String ENV_RELEASE = "release";
    public static final String ENV_TEST = "test";
    public static final String ENV_TESTING = "testing";
    public static final int LANDSCAPE = 0;
    public static final String PAY_CHANNEL_BANK = "bank";
    public static final String PAY_CHANNEL_QQWALLET = "qqwallet";
    public static final String PAY_CHANNEL_WECHAT = "wechat";
    public static final int PORTRAINT = 1;
    public static final String WX_COUPONS = "wechatAddCardToWXCardPackage";
    private static boolean a = true;
    private static int b = -1;
    private static String c = "";
    public static String env = "release";
    public static Context fromContext = null;

    public static void InnerH5PayInit(Activity activity, WebView webView) {
        APLog.i("APMidasPayAPI", "InnerH5PayInit enter");
        APMidasPayHelper.MIDAS_WEBVIEW = APMidasPayHelper.MIDAS_INNER_WEBVIEW;
        APMidasPayHelper.h5Init(activity, webView, null);
    }

    private static boolean a(Context context, APMidasBaseRequest aPMidasBaseRequest) {
        if (!env.equals("release")) {
            if (aPMidasBaseRequest == null) {
                try {
                    Toast.makeText(context, "初始化request不能为空", 1).show();
                    return false;
                } catch (Exception e) {
                }
            } else if (TextUtils.isEmpty(aPMidasBaseRequest.offerId)) {
                Toast.makeText(context, "初始化offerid不能为空", 1).show();
                return false;
            } else if (TextUtils.isEmpty(aPMidasBaseRequest.openId)) {
                Toast.makeText(context, "初始化openId不能为空", 1).show();
                return false;
            } else if (TextUtils.isEmpty(aPMidasBaseRequest.openKey)) {
                Toast.makeText(context, "初始化openKey不能为空", 1).show();
                return false;
            } else if (TextUtils.isEmpty(aPMidasBaseRequest.sessionId)) {
                Toast.makeText(context, "初始化sessionId不能为空", 1).show();
                return false;
            } else if (TextUtils.isEmpty(aPMidasBaseRequest.sessionType)) {
                Toast.makeText(context, "初始化sessionType不能为空", 1).show();
                return false;
            } else if (TextUtils.isEmpty(aPMidasBaseRequest.pf)) {
                Toast.makeText(context, "初始化pf不能为空", 1).show();
                return false;
            } else if (TextUtils.isEmpty(aPMidasBaseRequest.pfKey)) {
                Toast.makeText(context, "初始化pfKey不能为空", 1).show();
                return false;
            }
        }
        return true;
    }

    public static void closeAll() {
        APPluginStatic.removeAll();
    }

    public static void consumeAsync(Activity activity, List<APPurchase> list, OnAPConsumeFinishedListener onAPConsumeFinishedListener) {
        Class cls;
        Class cls2 = null;
        APLog.i("APMidasPayAPI", "consumeAsync enter");
        APMidasPayHelper aPMidasPayHelper = new APMidasPayHelper();
        try {
            cls = Class.forName(List.class.getName());
        } catch (ClassNotFoundException e) {
            APLog.d("APMidasPayAPI", "consumeAsync setEnv e:" + e.toString());
            cls = cls2;
        }
        try {
            cls2 = Class.forName("com.tencent.midas.api.request.OnAPConsumeFinishedListener");
        } catch (ClassNotFoundException e2) {
            APLog.d("APMidasPayAPI", "consumeAsync OnAPConsumeFinishedListener e:" + e2.toString());
        }
        Class[] clsArr = new Class[]{cls, cls2};
        Object call = aPMidasPayHelper.call(activity, APMidasPayHelper.MED_DISTRIBUTE_XGAME_CONSUME, new Object[]{list, onAPConsumeFinishedListener}, clsArr);
        APLog.i("APMidasPayAPI", "consumeAsync ret " + call);
        if (call == null) {
            onAPConsumeFinishedListener.onConsumeFinished(new ArrayList());
        }
    }

    public static void getInfo(Activity activity, String str, APMidasBaseRequest aPMidasBaseRequest, IAPMidasNetCallBack iAPMidasNetCallBack) {
        try {
            fromContext = activity.getApplicationContext();
        } catch (Exception e) {
            APLog.i("fromContext", e.toString());
        }
        APPluginReportManager.payDataRelease();
        APPluginReportManager.getInstance().payInterfaceInit(aPMidasBaseRequest, APMidasPluginInfo.LAUNCH_INTERFACE_GETINFO);
        APPluginReportManager.getInstance().insertTimeData(APMidasPluginInfo.LAUNCH_INTERFACE_GETINFO, APPluginReportManager.MIDASPLUGIN_TIMENAME_LAUNCHINFO);
        APMidasPayHelper aPMidasPayHelper = new APMidasPayHelper();
        APMidasPayHelper.setEnv(env);
        APMidasPayHelper.setLogEnable(a);
        aPMidasPayHelper.getInfo(activity, str, aPMidasBaseRequest, iAPMidasNetCallBack);
    }

    public static String getJSContent(Context context) {
        return APMidasPayHelper.getJSContent(context);
    }

    public static String getMidasCoreVersion(Activity activity) {
        APLog.i("APMidasPayAPI", "getMidasCoreVersion enter");
        return APPluginUtils.getMidasCoreVersionName(activity);
    }

    public static String getMidasPluginVersion() {
        APLog.i("APMidasPayAPI", "getMidasPluginVersion enter");
        String str = "";
        return APMidasCommMethod.getApplicationPackageName().equals("com.tencent.unipay") ? APMidasCommMethod.getApplicationVersion() : "1.6.3d";
    }

    public static String getMidasSDKVersion(Activity activity) {
        APLog.i("APMidasPayAPI", "getMidasSDKVersion enter");
        return (String) new APMidasPayHelper().call(activity, Thread.currentThread().getStackTrace()[2].getMethodName(), new Object[0]);
    }

    public static String getPath() {
        return c;
    }

    public static int h5PayHook(Activity activity, WebView webView, String str, String str2, JsResult jsResult) {
        APLog.i("APMidasPayAPI", "h5PayHook enter");
        APMidasPayHelper aPMidasPayHelper = new APMidasPayHelper();
        APMidasPayHelper.setEnv(env);
        APMidasPayHelper.setLogEnable(a);
        aPMidasPayHelper.setScreenType(b);
        return aPMidasPayHelper.h5Pay(activity, webView, null, str, str2);
    }

    public static int h5PayHookX5(Activity activity, com.tencent.smtt.sdk.WebView webView, String str, String str2, com.tencent.smtt.export.external.interfaces.JsResult jsResult) {
        APLog.i("APMidasPayAPI", "h5PayHookX5 enter");
        APMidasPayHelper aPMidasPayHelper = new APMidasPayHelper();
        APMidasPayHelper.setEnv(env);
        APMidasPayHelper.setLogEnable(a);
        aPMidasPayHelper.setScreenType(b);
        return aPMidasPayHelper.h5Pay(activity, null, webView, str, str2);
    }

    public static void h5PayInit(Activity activity, WebView webView) {
        APLog.i("APMidasPayAPI", "h5PayInit enter");
        APMidasPayHelper.MIDAS_WEBVIEW = APMidasPayHelper.MIDAS_OUT_WEBVIEW;
        APMidasPayHelper.h5Init(activity, webView, null);
    }

    public static void h5PayInitX5(Activity activity, com.tencent.smtt.sdk.WebView webView) {
        APLog.i("APMidasPayAPI", "h5PayInitX5 enter");
        APMidasPayHelper.h5Init(activity, null, webView);
    }

    public static void hfCouponsRollBack(Activity activity, String str) {
        APLog.i("APMidasPayAPI", "hfCouponsRollBack enter");
        new APMidasPayHelper().call(activity, APMidasPayHelper.MED_DISTRIBUTE_HF_COUPONS_ROLLBACK, new Object[]{str});
    }

    public static void init(Context context, APMidasBaseRequest aPMidasBaseRequest) {
        APLogInfo aPLogInfo = new APLogInfo();
        if (context != null) {
            try {
                fromContext = context.getApplicationContext();
                aPLogInfo.setContext(fromContext);
            } catch (Exception e) {
                APLog.i("APMidasPayAPI init", e.toString());
            }
        }
        aPLogInfo.setLogEnable(a);
        APLog.init(aPLogInfo);
        APLog.i("APMidasPayAPI", "init new enter");
        APPluginReportManager.initDataRelease();
        APPluginReportManager.getInstance().initInterfaceInit(APMidasPluginInfo.LAUNCH_INTERFACE_INIT, aPMidasBaseRequest);
        APPluginReportManager.getInstance().insertTimeData(APMidasPluginInfo.LAUNCH_INTERFACE_INIT, APPluginReportManager.MIDASPLUGIN_TIMENAME_INIT);
        APMidasPayHelper.setEnv(env);
        APMidasPayHelper.setLogEnable(a);
        if (a(context, aPMidasBaseRequest)) {
            APMidasPayHelper.init(context, aPMidasBaseRequest);
        }
    }

    public static void launchNet(Activity activity, APMidasNetRequest aPMidasNetRequest, IAPMidasNetCallBack iAPMidasNetCallBack) {
        APLog.i("APMidasPayAPI", "launchNet enter");
        try {
            fromContext = activity.getApplicationContext();
        } catch (Exception e) {
            APLog.i("fromContext", e.toString());
        }
        APPluginReportManager.payDataRelease();
        APPluginReportManager.getInstance().payInterfaceInit(aPMidasNetRequest, APMidasPluginInfo.LAUNCH_INTERFACE_LAUNCHNET);
        APPluginReportManager.getInstance().insertTimeData(APMidasPluginInfo.LAUNCH_INTERFACE_LAUNCHNET, APPluginReportManager.MIDASPLUGIN_TIMENAME_LAUNCHNET);
        APMidasPayHelper aPMidasPayHelper = new APMidasPayHelper();
        APMidasPayHelper.setEnv(env);
        APMidasPayHelper.setLogEnable(a);
        aPMidasPayHelper.net(activity, aPMidasNetRequest, iAPMidasNetCallBack);
    }

    public static void launchPay(Activity activity, APMidasBaseRequest aPMidasBaseRequest, IAPMidasPayCallBack iAPMidasPayCallBack) {
        APLog.i("APMidasPayAPI", "launchPay enter");
        try {
            fromContext = activity.getApplicationContext();
        } catch (Exception e) {
            APLog.i("fromContext", e.toString());
        }
        if (!APMidasTools.isFastClick() || APMidasPayHelper.midasCallBack == null) {
            APPluginReportManager.payDataRelease();
            APPluginReportManager.getInstance().payInterfaceInit(aPMidasBaseRequest, APMidasPluginInfo.LAUNCH_INTERFACE_LAUNCHPAY);
            APPluginReportManager.getInstance().insertTimeData(APMidasPluginInfo.LAUNCH_INTERFACE_LAUNCHPAY, APPluginReportManager.MIDASPLUGIN_TIMENAME_LAUNCHPAY);
            APMidasPayHelper aPMidasPayHelper = new APMidasPayHelper();
            APMidasPayHelper.setEnv(env);
            APMidasPayHelper.setLogEnable(a);
            aPMidasPayHelper.setScreenType(b);
            aPMidasPayHelper.pay(activity, aPMidasBaseRequest, iAPMidasPayCallBack);
            return;
        }
        APLog.i("launchPay", "isfast");
    }

    public static void launchPurchaseFlow(Activity activity, APMidasBaseRequest aPMidasBaseRequest, final APOnIabPurchaseFinished aPOnIabPurchaseFinished) {
        APLog.i("APMidasPayAPI", "launchPurchaseFlow enter");
        APMidasPayHelper.setEnv(env);
        APMidasPayHelper.setLogEnable(a);
        if (aPMidasBaseRequest == null || !(aPMidasBaseRequest instanceof APMidasGoodsRequest)) {
            aPOnIabPurchaseFinished.onIabPurchaseFinished(new APIabResult(3, ""), null);
            APLog.e("launchPurchaseFlow", "parameter is error");
            return;
        }
        if (aPMidasBaseRequest instanceof APMidasGoodsRequest) {
            ((APMidasGoodsRequest) aPMidasBaseRequest).mIsReceiptMode = true;
        }
        ((APMidasGoodsRequest) aPMidasBaseRequest).isCanChange = false;
        ((APMidasGoodsRequest) aPMidasBaseRequest).saveValue = "1";
        ((APMidasGoodsRequest) aPMidasBaseRequest).tokenType = 2;
        launchPay(activity, aPMidasBaseRequest, new IAPMidasPayCallBack() {
            public void MidasPayCallBack(APMidasResponse aPMidasResponse) {
                int resultCode = aPMidasResponse.getResultCode();
                if (aPMidasResponse.resultCode == 100) {
                    resultCode = 101;
                }
                aPOnIabPurchaseFinished.onIabPurchaseFinished(new APIabResult(resultCode, aPMidasResponse.getResultMsg()), aPMidasResponse.getReceipt());
            }

            public void MidasPayNeedLogin() {
                aPOnIabPurchaseFinished.onIabyNeedLogin();
            }
        });
    }

    public static void launchWeb(Activity activity, APMidasBaseRequest aPMidasBaseRequest, IAPMidasPayCallBack iAPMidasPayCallBack) {
        APLog.i("APMidasPayAPI", "launchWeb enter");
        try {
            fromContext = activity.getApplicationContext();
        } catch (Exception e) {
            APLog.i("fromContext", e.toString());
        }
        APPluginReportManager.payDataRelease();
        APPluginReportManager.getInstance().payInterfaceInit(aPMidasBaseRequest, APMidasPluginInfo.LAUNCH_INTERFACE_LAUNCHWEB);
        APPluginReportManager.getInstance().insertTimeData(APMidasPluginInfo.LAUNCH_INTERFACE_LAUNCHWEB, APPluginReportManager.MIDASPLUGIN_TIMENAME_LAUNCHWEB);
        APMidasPayHelper aPMidasPayHelper = new APMidasPayHelper();
        APMidasPayHelper.setEnv(env);
        APMidasPayHelper.setLogEnable(a);
        aPMidasPayHelper.setScreenType(b);
        aPMidasPayHelper.web(activity, aPMidasBaseRequest, iAPMidasPayCallBack);
    }

    public static void queryInventoryAsync(Activity activity, boolean z, List<?> list, APQueryInventoryFinishedListener aPQueryInventoryFinishedListener) {
        Class cls;
        Class cls2;
        APLog.i("APMidasPayAPI", "queryInventoryAsync enter");
        APMidasPayHelper aPMidasPayHelper = new APMidasPayHelper();
        try {
            cls = Class.forName(List.class.getName());
        } catch (ClassNotFoundException e) {
            APLog.d("APMidasPayAPI", "queryInventoryAsync setEnv e:" + e.toString());
            cls = null;
        }
        try {
            cls2 = Class.forName("com.tencent.midas.api.request.APQueryInventoryFinishedListener");
        } catch (ClassNotFoundException e2) {
            APLog.d("APMidasPayAPI", "queryInventoryAsync APQueryInventoryFinishedListener e:" + e2.toString());
            cls2 = null;
        }
        Class[] clsArr = new Class[]{Boolean.class, cls, cls2};
        Object call = aPMidasPayHelper.call(activity, APMidasPayHelper.MED_DISTRIBUTE_XGAME_QUERY, new Object[]{Boolean.valueOf(z), list, aPQueryInventoryFinishedListener}, clsArr);
        APLog.i("APMidasPayAPI", "queryInventoryAsync ret " + call);
        if (call == null) {
            aPQueryInventoryFinishedListener.onQueryInventoryFinished(new APIabResult(-1, ""), null);
        }
    }

    public static void setEnv(String str) {
        Method method = null;
        env = str;
        try {
            Class cls = Class.forName("com.pay.tool.APAppDataInterface");
            if (cls != null) {
                Method declaredMethod;
                Object invoke;
                try {
                    declaredMethod = cls.getDeclaredMethod("singleton", new Class[0]);
                } catch (NoSuchMethodException e) {
                    APLog.d("APMidasPayAPI", "com.pay.tool.APAppDataInterface ClassNotFound");
                    declaredMethod = method;
                }
                try {
                    invoke = declaredMethod.invoke(null, new Object[0]);
                } catch (Exception e2) {
                    APLog.d("APMidasPayAPI", "com.pay.tool.APAppDataInterface invoke error");
                    declaredMethod = method;
                }
                try {
                    method = cls.getMethod("setEnv", new Class[]{String.class});
                } catch (NoSuchMethodException e3) {
                    APLog.d("APMidasPayAPI", "setEnv no such method");
                }
                try {
                    method.invoke(invoke, new Object[]{str});
                } catch (Exception e4) {
                    APLog.d("APMidasPayAPI", "setEnv invoke error " + e4.toString());
                }
            }
        } catch (Exception e42) {
            APLog.d("APMidasPayAPI", "setEnv exception e:" + e42.toString());
        }
        APLog.d("MidasPluginSDK", "env= " + env);
    }

    public static void setLogEnable(boolean z) {
        a = z;
    }

    public static void setParentClassloader(DexClassLoader dexClassLoader) {
        APLog.i("APMidasPayAPI", "setParentClassloader enter classLoader:" + dexClassLoader);
        APPluginStatic.setParentClassLoader(dexClassLoader);
    }

    public static void setPath(String str) {
        APLog.i("APMidasPayAPI", "setPath enter path:" + str);
        c = str;
    }

    public static void setScreenType(Activity activity, int i) {
        b = i;
    }
}
