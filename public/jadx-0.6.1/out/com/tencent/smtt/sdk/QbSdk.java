package com.tencent.smtt.sdk;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.ValueCallback;
import com.dynamicload.Lib.DLConstants;
import com.tencent.smtt.export.external.interfaces.IX5WebViewBase;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import com.tencent.smtt.sdk.a.d;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.TbsLogClient;
import com.tencent.smtt.utils.a;
import com.tencent.smtt.utils.r;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

@SuppressLint({"NewApi"})
public class QbSdk {
    public static final String LOGIN_TYPE_KEY_PARTNER_CALL_POS = "PosID";
    public static final String LOGIN_TYPE_KEY_PARTNER_ID = "ChannelID";
    public static final String PARAM_KEY_FEATUREID = "param_key_featureid";
    public static final String PARAM_KEY_FUNCTIONID = "param_key_functionid";
    public static final String PARAM_KEY_POSITIONID = "param_key_positionid";
    public static final int SVNVERSION = 400614;
    public static final int VERSION = 1;
    static boolean a = false;
    static boolean b = false;
    static boolean c = false;
    static String d;
    static boolean e = false;
    static long f = 0;
    static long g = 0;
    static Object h = new Object();
    static volatile boolean i = a;
    static Map<String, Object> j = null;
    private static int k = 0;
    private static String l = "";
    private static Class<?> m;
    private static Object n;
    private static boolean o = false;
    private static String[] p;
    private static String q = "NULL";
    private static String r = "UNKNOWN";
    private static boolean s = true;
    public static boolean sIsVersionPrinted = false;

    public interface PreInitCallback {
        void onCoreInitFinished();

        void onViewInitFinished(boolean z);
    }

    public enum WebviewInitType {
        FIRSTUSE_AND_PRELOAD,
        FIRSTUSE_ONLY,
        PRELOAD_ONLY
    }

    static String a() {
        return l;
    }

    static synchronized void a(Context context, String str) {
        synchronized (QbSdk.class) {
            if (!a) {
                a = true;
                r = "forceSysWebViewInner: " + str;
                TbsLog.e("QbSdk", "QbSdk.SysWebViewForcedInner..." + r);
                TbsCoreLoadStat.getInstance().a(context, 401, new Throwable(r));
            }
        }
    }

    private static boolean a(Context context) {
        try {
            if (m != null) {
                return true;
            }
            File e = z.a().e(context);
            if (e == null) {
                TbsLog.e("QbSdk", "QbSdk initForX5DisableConfig (false) optDir == null");
                return false;
            }
            File file;
            if (!TbsShareManager.isThirdPartyApp(context)) {
                file = new File(z.a().e(context), "tbs_sdk_extension_dex.jar");
            } else if (TbsShareManager.g(context)) {
                file = new File(TbsShareManager.a(context), "tbs_sdk_extension_dex.jar");
            } else {
                TbsCoreLoadStat.getInstance().a(context, 1001);
                return false;
            }
            if (file.exists()) {
                m = new DexClassLoader(file.getAbsolutePath(), e.getAbsolutePath(), file.getAbsolutePath(), QbSdk.class.getClassLoader()).loadClass("com.tencent.tbs.sdk.extension.TbsSDKExtension");
                Constructor constructor = m.getConstructor(new Class[]{Context.class, Context.class});
                if (TbsShareManager.isThirdPartyApp(context)) {
                    n = constructor.newInstance(new Object[]{context, TbsShareManager.c(context)});
                } else {
                    n = constructor.newInstance(new Object[]{context, context});
                }
                r.a(n, "putInfo", new Class[]{String.class, String.class, String.class, String.class}, new Object[]{a.a, a.b, a.c, a.d});
                r.a(n, "setClientVersion", new Class[]{Integer.TYPE}, new Object[]{Integer.valueOf(1)});
                return true;
            }
            TbsCoreLoadStat.getInstance().a(context, 403, new Exception("initForX5DisableConfig failure -- tbs_sdk_extension_dex.jar is not exist!"));
            return false;
        } catch (Throwable th) {
            TbsLog.e("QbSdk", "initForX5DisableConfig sys WebView: " + Log.getStackTraceString(th));
            return false;
        }
    }

    static boolean a(Context context, int i) {
        return a(context, i, 20000);
    }

    static boolean a(Context context, int i, int i2) {
        if (!a(context)) {
            return true;
        }
        Object a = r.a(n, "isX5Disabled", new Class[]{Integer.TYPE, Integer.TYPE, Integer.TYPE}, new Object[]{Integer.valueOf(i), Integer.valueOf(36801), Integer.valueOf(i2)});
        if (a != null) {
            return ((Boolean) a).booleanValue();
        }
        a = r.a(n, "isX5Disabled", new Class[]{Integer.TYPE, Integer.TYPE}, new Object[]{Integer.valueOf(i), Integer.valueOf(36801)});
        return a != null ? ((Boolean) a).booleanValue() : true;
    }

    @SuppressLint({"NewApi"})
    private static boolean a(Context context, boolean z) {
        File file = null;
        TbsLog.initIfNeed(context);
        if (!sIsVersionPrinted) {
            TbsLog.i("QbSdk", "svn revision: 400614; SDK_VERSION_CODE: 36801; SDK_VERSION_NAME: 2.3.0.1048");
            sIsVersionPrinted = true;
        }
        if (a && !z) {
            TbsLog.e("QbSdk", "QbSdk init: " + r, false);
            return false;
        } else if (b) {
            TbsLog.e("QbSdk", "QbSdk init mIsSysWebViewForcedByOuter = true", true);
            TbsCoreLoadStat.getInstance().a(context, 402, new Throwable(q));
            return false;
        } else {
            try {
                File e = z.a().e(context);
                if (e == null) {
                    TbsLog.e("QbSdk", "QbSdk init (false) optDir == null");
                    TbsCoreLoadStat.getInstance().a(context, ErrorCode.ERROR_TBSCORE_SHARE_DIR, new Throwable("QbSdk.init (false) TbsCoreShareDir is null"));
                    return false;
                }
                if (TbsShareManager.isThirdPartyApp(context)) {
                    if (k == 0 || k == TbsShareManager.b(context)) {
                        k = TbsShareManager.b(context);
                    } else {
                        m = null;
                        n = null;
                        TbsLog.e("QbSdk", "QbSdk init (false) ERROR_UNMATCH_TBSCORE_VER_THIRDPARTY!");
                        TbsCoreLoadStat.getInstance().a(context, 302, new Throwable("sTbsVersion: " + k + "; AvailableTbsCoreVersion: " + TbsShareManager.b(context)));
                        return false;
                    }
                }
                if (m != null) {
                    return true;
                }
                if (TbsShareManager.isThirdPartyApp(context)) {
                    if (TbsShareManager.g(context)) {
                        file = new File(TbsShareManager.a(context), "tbs_sdk_extension_dex.jar");
                    } else {
                        TbsCoreLoadStat.getInstance().a(context, 304, new Throwable("isShareTbsCoreAvailable false!"));
                        return false;
                    }
                }
                if (file.exists()) {
                    m = new DexClassLoader(file.getAbsolutePath(), e.getAbsolutePath(), file.getAbsolutePath(), QbSdk.class.getClassLoader()).loadClass("com.tencent.tbs.sdk.extension.TbsSDKExtension");
                    Constructor constructor = m.getConstructor(new Class[]{Context.class, Context.class});
                    if (TbsShareManager.isThirdPartyApp(context)) {
                        n = constructor.newInstance(new Object[]{context, TbsShareManager.c(context)});
                    }
                    r.a(n, "putInfo", new Class[]{String.class, String.class, String.class, String.class}, new Object[]{a.a, a.b, a.c, a.d});
                    r.a(n, "setClientVersion", new Class[]{Integer.TYPE}, new Object[]{Integer.valueOf(1)});
                    return true;
                }
                TbsLog.e("QbSdk", "QbSdk init (false) tbs_sdk_extension_dex.jar is not exist!");
                TbsCoreLoadStat.getInstance().a(context, 403, new Exception("QbSdk.init false, tbs_sdk_extension_dex not exist(NO tbs core)!"));
                return false;
            } catch (Throwable th) {
                TbsLog.e("QbSdk", "QbSdk init Throwable: " + Log.getStackTraceString(th));
                TbsCoreLoadStat.getInstance().a(context, 306, th);
                return false;
            }
        }
    }

    static boolean a(Context context, boolean z, boolean z2) {
        boolean z3 = false;
        if (TbsShareManager.isThirdPartyApp(context) && !TbsShareManager.f(context)) {
            TbsCoreLoadStat.getInstance().a(context, 302);
        } else if (a(context, z)) {
            Object a = r.a(n, "canLoadX5Core", new Class[]{Integer.TYPE}, new Object[]{Integer.valueOf(36801)});
            if (a == null) {
                a = r.a(n, "canLoadX5", new Class[]{Integer.TYPE}, new Object[]{Integer.valueOf(a.a())});
                if (a == null) {
                    TbsCoreLoadStat.getInstance().a(context, 308);
                } else if ((a instanceof String) && ((String) a).equalsIgnoreCase("AuthenticationFail")) {
                    TbsLog.e(TbsListener.tag_load_error, "317");
                } else if (a instanceof Boolean) {
                    k = f.c();
                    boolean a2 = a(context, f.c());
                    if (((Boolean) a).booleanValue() && !a2) {
                        z3 = true;
                    }
                    if (!z3) {
                        TbsLog.e(TbsListener.tag_load_error, "318");
                        TbsLog.w(TbsListener.tag_load_error, "isX5Disable:" + a2);
                        TbsLog.w(TbsListener.tag_load_error, "(Boolean) ret:" + ((Boolean) a));
                    }
                }
            } else if ((a instanceof String) && ((String) a).equalsIgnoreCase("AuthenticationFail")) {
                TbsLog.e(TbsListener.tag_load_error, "317");
            } else if (a instanceof Bundle) {
                Bundle bundle = (Bundle) a;
                int i = bundle.getInt("result_code", -1);
                boolean z4 = i == 0;
                if (TbsShareManager.isThirdPartyApp(context)) {
                    f.a(TbsShareManager.b(context));
                    l = String.valueOf(TbsShareManager.b(context));
                    if (l.length() == 5) {
                        l = "0" + l;
                    }
                    if (l.length() != 6) {
                        l = "";
                    }
                }
                try {
                    p = bundle.getStringArray("tbs_jarfiles");
                    if (p instanceof String[]) {
                        d = bundle.getString("tbs_librarypath");
                        switch (i) {
                            case -2:
                                if (TbsShareManager.isThirdPartyApp(context)) {
                                    TbsCoreLoadStat.getInstance().a(context, 404);
                                    break;
                                }
                                break;
                            case -1:
                                a = null;
                                try {
                                    a = r.a(n, "getErrorCodeForLogReport", new Class[0], new Object[0]);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                TbsCoreLoadStat.getInstance().a(context, 307, new Throwable("detail: " + a));
                                break;
                        }
                        z3 = z4;
                    } else {
                        TbsCoreLoadStat.getInstance().a(context, 307, new Throwable("sJarFiles not instanceof String[]: " + p));
                    }
                } catch (Throwable th) {
                    TbsCoreLoadStat.getInstance().a(context, ErrorCode.ERROR_GETSTRINGARRAY_JARFILE, th);
                }
            } else {
                TbsCoreLoadStat.getInstance().a(context, ErrorCode.ERROR_QBSDK_INIT_ERROR_RET_TYPE_NOT_BUNDLE, new Throwable("" + a));
                TbsLog.e(TbsListener.tag_load_error, "ret not instance of bundle");
            }
            if (!z3) {
                TbsLog.e(TbsListener.tag_load_error, "319");
            }
        } else {
            TbsLog.e("QbSdk", "QbSdk.init failure!");
        }
        return z3;
    }

    public static boolean canLoadX5FirstTimeThirdApp(Context context) {
        try {
            if (m == null) {
                File e = z.a().e(context);
                if (e == null) {
                    TbsLog.e("QbSdk", "QbSdk canLoadX5FirstTimeThirdApp (false) optDir == null");
                    return false;
                }
                File file = new File(TbsShareManager.a(context), "tbs_sdk_extension_dex.jar");
                if (file.exists()) {
                    m = new DexClassLoader(file.getAbsolutePath(), e.getAbsolutePath(), file.getAbsolutePath(), QbSdk.class.getClassLoader()).loadClass("com.tencent.tbs.sdk.extension.TbsSDKExtension");
                } else {
                    TbsLog.e("QbSdk", "QbSdk canLoadX5FirstTimeThirdApp (false) dexFile.exists()=false", true);
                    return false;
                }
            }
            if (n == null) {
                n = m.getConstructor(new Class[]{Context.class, Context.class}).newInstance(new Object[]{context, context});
            }
            Object a = r.a(n, "canLoadX5CoreForThirdApp", new Class[0], new Object[0]);
            return (a == null || !(a instanceof Boolean)) ? false : ((Boolean) a).booleanValue();
        } catch (Throwable th) {
            TbsLog.e("QbSdk", "canLoadX5FirstTimeThirdApp sys WebView: " + Log.getStackTraceString(th));
            return false;
        }
    }

    public static void forceSysWebView() {
        b = true;
        q = "SysWebViewForcedByOuter: " + Log.getStackTraceString(new Throwable());
        TbsLog.e("QbSdk", "sys WebView: SysWebViewForcedByOuter");
    }

    public static String[] getDexLoaderFileList(Context context, Context context2, String str) {
        int i = 0;
        if (p instanceof String[]) {
            int length = p.length;
            String[] strArr = new String[length];
            while (i < length) {
                strArr[i] = str + p[i];
                i++;
            }
            return strArr;
        }
        Object a = r.a(n, "getJarFiles", new Class[]{Context.class, Context.class, String.class}, new Object[]{context, context2, str});
        if (a instanceof String[]) {
            a = "";
        }
        return (String[]) a;
    }

    public static String getMiniQBVersion() {
        aq a = aq.a();
        return (a == null || !a.b()) ? null : a.c().f();
    }

    public static int getTbsVersion(Context context) {
        return TbsShareManager.isThirdPartyApp(context) ? TbsShareManager.a(context, false) : z.a().c(context);
    }

    public static void initTbsSettings(Map<String, Object> map) {
        j = map;
    }

    public static void initX5Environment(Context context, WebviewInitType webviewInitType, PreInitCallback preInitCallback) {
        if (webviewInitType == WebviewInitType.FIRSTUSE_ONLY || webviewInitType == WebviewInitType.FIRSTUSE_AND_PRELOAD) {
            TbsDownloader.a(context, false, new e(webviewInitType, context, preInitCallback));
        } else {
            preInit(context, preInitCallback, Boolean.valueOf(false));
        }
    }

    public static boolean installLocalQbApk(Context context, String str, String str2, Bundle bundle) {
        f a = f.a(true);
        a.a(context, false, false, null);
        return (a == null || !a.b()) ? false : a.a().a(context, str, str2, bundle);
    }

    public static boolean isTbsCoreInited() {
        f a = f.a(false);
        return a != null && a.d();
    }

    public static synchronized void preInit(Context context) {
        synchronized (QbSdk.class) {
            preInit(context, null);
        }
    }

    public static synchronized void preInit(Context context, PreInitCallback preInitCallback) {
        synchronized (QbSdk.class) {
            preInit(context, preInitCallback, Boolean.valueOf(true));
        }
    }

    public static synchronized void preInit(Context context, PreInitCallback preInitCallback, Boolean bool) {
        synchronized (QbSdk.class) {
            TbsLog.initIfNeed(context);
            i = a;
            y yVar = new y();
            yVar.a("init_all", (byte) 1);
            if (!o) {
                new d(context, yVar, bool, new c(Looper.getMainLooper(), context, preInitCallback, yVar)).start();
                o = true;
            }
        }
    }

    public static void setDeviceInfo(String str, String str2, String str3, String str4) {
        a.a = str;
        a.b = str2;
        a.c = str3;
        a.d = str4;
    }

    public static void setTbsLogClient(TbsLogClient tbsLogClient) {
        TbsLog.setTbsLogClient(tbsLogClient);
    }

    public static int startMiniQBToLoadUrl(Context context, String str, HashMap<String, String> hashMap, ValueCallback<String> valueCallback) {
        if (context == null) {
            return -100;
        }
        aq a = aq.a();
        a.a(context, null);
        return a.b() ? (context == null || !context.getApplicationInfo().packageName.equals("com.nd.android.pandahome2") || getTbsVersion(context) >= 25487) ? a.c().a(context, str, hashMap, null, valueCallback) : DLConstants.LOAD_ERR_SIGNATURES : DLConstants.LOAD_ERR_NAME_NOT_FOUND;
    }

    public static boolean startQBForDoc(Context context, String str, int i, int i2, String str2, Bundle bundle) {
        HashMap hashMap = new HashMap();
        hashMap.put(LOGIN_TYPE_KEY_PARTNER_ID, context.getApplicationContext().getApplicationInfo().processName);
        hashMap.put(LOGIN_TYPE_KEY_PARTNER_CALL_POS, Integer.toString(i));
        return d.a(context, str, i2, str2, hashMap, bundle);
    }

    public static boolean startQBForVideo(Context context, String str, int i) {
        HashMap hashMap = new HashMap();
        hashMap.put(LOGIN_TYPE_KEY_PARTNER_ID, context.getApplicationInfo().processName);
        hashMap.put(LOGIN_TYPE_KEY_PARTNER_CALL_POS, Integer.toString(i));
        return d.a(context, str, hashMap);
    }

    public static boolean startQBToLoadurl(Context context, String str, int i, WebView webView) {
        HashMap hashMap = new HashMap();
        hashMap.put(LOGIN_TYPE_KEY_PARTNER_ID, context.getApplicationInfo().processName);
        hashMap.put(LOGIN_TYPE_KEY_PARTNER_CALL_POS, Integer.toString(i));
        if (webView == null) {
            try {
                String str2 = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).packageName;
                if (str2 == "com.tencent.mm" || str2 == "com.tencent.mobileqq") {
                    aq a = aq.a();
                    if (a != null && a.b()) {
                        Object invokeStaticMethod = a.c().b().invokeStaticMethod("com.tencent.smtt.webkit.WebViewList", "getCurrentMainWebviewJustForQQandWechat", new Class[0], new Object[0]);
                        if (invokeStaticMethod != null) {
                            IX5WebViewBase iX5WebViewBase = (IX5WebViewBase) invokeStaticMethod;
                            if (iX5WebViewBase != null) {
                                webView = (WebView) iX5WebViewBase.getView().getParent();
                            }
                        }
                    }
                }
            } catch (Exception e) {
            }
        }
        return d.a(context, str, hashMap, "QbSdk.startQBToLoadurl", webView) == 0;
    }

    public static boolean startQbOrMiniQBToLoadUrl(Context context, String str, HashMap<String, String> hashMap, ValueCallback<String> valueCallback) {
        if (context == null) {
            return false;
        }
        String string;
        aq a = aq.a();
        a.a(context, null);
        String str2 = "QbSdk.startMiniQBToLoadUrl";
        if (hashMap != null && "5".equals(hashMap.get(LOGIN_TYPE_KEY_PARTNER_CALL_POS)) && a.b()) {
            Bundle bundle = (Bundle) a.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "getAdWebViewInfoFromX5Core", new Class[0], new Object[0]);
            if (bundle != null) {
                string = bundle.getString("ad_webview_detail_url");
                String string2 = bundle.getString("ad_webview_click_stat_posid_for_qb");
                String string3 = bundle.getString("ad_webview_click_stat_entryid_for_miniqb");
                if (!TextUtils.isEmpty(string)) {
                    if (hashMap != null) {
                        hashMap.put(LOGIN_TYPE_KEY_PARTNER_CALL_POS, string2);
                        hashMap.put("entryId", string3);
                    }
                    str = string;
                    if (d.a(context, str, hashMap, str2, null) != 0) {
                        return true;
                    }
                    if (a.b()) {
                        if (context == null && context.getApplicationInfo().packageName.equals("com.nd.android.pandahome2") && getTbsVersion(context) < 25487) {
                            return false;
                        }
                        if (a.c().a(context, string, hashMap, null, valueCallback) == 0) {
                            return true;
                        }
                    }
                    return false;
                }
            }
        }
        string = str;
        if (d.a(context, str, hashMap, str2, null) != 0) {
            return true;
        }
        if (a.b()) {
            if (context == null) {
            }
            if (a.c().a(context, string, hashMap, null, valueCallback) == 0) {
                return true;
            }
        }
        return false;
    }

    public static void unForceSysWebView() {
        b = false;
        TbsLog.e("QbSdk", "sys WebView: unForceSysWebView called");
    }
}
