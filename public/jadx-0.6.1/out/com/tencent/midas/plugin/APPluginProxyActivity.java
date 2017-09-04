package com.tencent.midas.plugin;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.res.Configuration;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.Toast;
import com.tencent.midas.comm.APLog;
import com.tencent.midas.data.APPluginReportManager;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

public class APPluginProxyActivity extends Activity {
    private static String e = null;
    private static Method f = null;
    private static Field g = null;
    protected static String gPluginName = "MidasPay";
    public static boolean mAppForground = true;
    private String a = null;
    private IAPPluginActivity b = null;
    private String c = null;
    private String d = null;
    protected String mCreateErrorInfo = null;
    protected int mStopFlag = 0;

    private Class<?> a(Context context, String str, String str2, String str3) {
        Class<?> loadClass;
        if (TextUtils.isEmpty(this.c)) {
            this.d = str;
            try {
                this.c = APPluginUtils.getInstallPath(context, this.d).getCanonicalPath();
            } catch (Exception e) {
            }
        }
        try {
            if (((PackageInfo) APPluginStatic.b.get(this.c)) == null) {
                PackageInfo packageInfo = APApkFileParser.getPackageInfo(context, this.c, 1);
                if (packageInfo == null) {
                    return null;
                }
                APPluginStatic.b.put(this.c, packageInfo);
            }
            loadClass = APPluginStatic.a(context, this.d, this.c).loadClass(str2);
        } catch (Exception e2) {
            loadClass = null;
        }
        return loadClass;
    }

    private void a(Activity activity, String str, Intent intent, int i) {
        APLog.i("APPLuginProxyActivity", "APPluginProxyActivity startPluginActivityForResult.private");
        Intent intent2 = new Intent(activity, getProxyActivity(str));
        intent2.putExtra(APPluginStatic.PARAM_PLUGIN_NAME, this.d);
        intent2.putExtra(APPluginStatic.PARAM_PLUGIN_PATH, this.c);
        intent2.putExtra(APPluginStatic.PARAM_LAUNCH_ACTIVITY, str);
        if (intent != null) {
            intent2.addFlags(intent.getFlags());
            intent2.putExtras(intent);
        }
        activity.startActivityForResult(intent2, i);
    }

    private void a(Context context) {
        if (this.mCreateErrorInfo == null || !(this.mCreateErrorInfo.contains("空间") || this.mCreateErrorInfo.contains("Space"))) {
            Toast.makeText(context, "系统繁忙，请退出重试", 0).show();
        } else {
            Toast.makeText(context, "系统可用内存不足，请退出重试", 0).show();
        }
    }

    private static void a(Bundle bundle, ClassLoader classLoader) {
        if (bundle != null) {
            bundle.setClassLoader(classLoader);
            if (f == null || g == null) {
                Class cls = bundle.getClass();
                f = cls.getDeclaredMethod("unparcel", new Class[0]);
                f.setAccessible(true);
                g = cls.getDeclaredField("mMap");
                g.setAccessible(true);
            }
            f.invoke(bundle, new Object[0]);
            Map map = (Map) g.get(bundle);
            if (map != null) {
                for (Object next : map.values()) {
                    if (next instanceof Bundle) {
                        a((Bundle) next, classLoader);
                    }
                }
            }
        }
    }

    private void a(String str) {
        boolean z = true;
        if (str.contains("permission") || str.contains("filenotfoundexception")) {
            APLog.i("logStartPluginErrInfo", "mPluginApkFilePath" + this.c);
            ApplicationInfo applicationInfo = getApplicationInfo();
            if (applicationInfo != null) {
                boolean z2 = (applicationInfo.flags & 1) > 0;
                if ((applicationInfo.flags & 128) <= 0) {
                    z = false;
                }
                APLog.d("", "UID: " + applicationInfo.uid + ", IsSystemApp: " + z2 + ", IsUpdateSystemApp: " + z);
            }
        } else if (!str.contains("resources$notfoundexception") && !str.contains("resourcesnotfoundexception") && str.contains("classnotfoundexception")) {
        }
    }

    private boolean a(Intent intent) {
        Object action = intent.getAction();
        if (!TextUtils.isEmpty(action) && action.equals("android.media.action.IMAGE_CAPTURE")) {
            return true;
        }
        if (!TextUtils.isEmpty(action) && action.equals("android.intent.action.GET_CONTENT")) {
            return true;
        }
        ComponentName component = intent.getComponent();
        if (component != null) {
            Object packageName = component.getPackageName();
            if (!TextUtils.isEmpty(packageName) && packageName.equals("com.tencent.midas.pay")) {
                return true;
            }
            action = component.getClassName();
            if (!TextUtils.isEmpty(action) && action.equals("com.qzone")) {
                return true;
            }
        }
        return false;
    }

    public static boolean isMoveTaskToBack(Context context, Intent intent) {
        return intent.getComponent() == null || !intent.getComponent().getPackageName().equals(context.getPackageName());
    }

    public static void openActivityForResult(Activity activity, String str, String str2, String str3, Intent intent, int i) {
        APLog.i("APPLuginProxyActivity", "APPluginProxyActivity openActivityForResult pluginName：" + str);
        APLog.i("APPLuginProxyActivity", "APPluginProxyActivity openActivityForResult contextActivity：" + activity);
        APLog.i("APPLuginProxyActivity", "APPluginProxyActivity openActivityForResult apkFilePath：" + str2);
        APLog.i("APPLuginProxyActivity", "APPluginProxyActivity openActivityForResult startIntent：" + intent.getClass().getSimpleName());
        APLog.i("APPLuginProxyActivity", "APPluginProxyActivity openActivityForResult startIntent：" + intent.getClass().getCanonicalName());
        APLog.i("APPLuginProxyActivity", "APPluginProxyActivity openActivityForResult startIntent：" + intent.getClass().getClassLoader());
        gPluginName = str;
        try {
            e = APPluginUtils.getInstallPath(activity, str).getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        intent.putExtra(APPluginStatic.PARAM_PLUGIN_NAME, str);
        intent.putExtra(APPluginStatic.PARAM_LAUNCH_ACTIVITY, str3);
        intent.putExtra(APPluginStatic.PARAM_PLUGIN_PATH, str2);
        try {
            activity.startActivityForResult(intent, i);
        } catch (Throwable th) {
            APLog.i("APPLuginProxyActivity", "APPluginProxyActivity openActivityForResult Throwable:" + th.toString());
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return this.b != null ? this.b.IDispatchTouchEvent(motionEvent) : super.dispatchTouchEvent(motionEvent);
    }

    public Class<?>[] getParamsType(Object[] objArr) {
        if (objArr == null) {
            return null;
        }
        Class<?>[] clsArr = new Class[objArr.length];
        for (int i = 0; i < clsArr.length; i++) {
            if (objArr[i] != null) {
                if (Activity.class.isAssignableFrom(objArr[i].getClass())) {
                    clsArr[i] = Activity.class;
                } else {
                    clsArr[i] = objArr[i].getClass();
                }
            }
        }
        return clsArr;
    }

    protected Class<? extends APPluginProxyActivity> getProxyActivity(String str) {
        return getClass();
    }

    public String initPlugin() {
        PackageInfo packageInfo;
        APLog.i("APPLuginProxyActivity", "APPluginProxyActivity initPlugin mPluginApkFilePath:" + this.c);
        PackageInfo packageInfo2 = (PackageInfo) APPluginStatic.b.get(this.c);
        if (packageInfo2 == null) {
            packageInfo = APApkFileParser.getPackageInfo(this, this.c, 1);
            if (packageInfo == null) {
                return "Get Package Info Failed!";
            }
            APPluginStatic.b.put(this.c, packageInfo);
        } else {
            packageInfo = packageInfo2;
        }
        if (this.a == null || this.a.length() == 0) {
            if (packageInfo.activities == null || packageInfo.activities.length == 0) {
                return "Activity Not Found!";
            }
            this.a = packageInfo.activities[0].name;
        }
        ClassLoader a = APPluginStatic.a(this, this.d, this.c);
        getIntent().setExtrasClassLoader(a);
        this.b = (IAPPluginActivity) a.loadClass(this.a).newInstance();
        this.b.IInit(this.d, this.c, this, a, packageInfo);
        this.b.ISetIntent(getIntent());
        return null;
    }

    public Object initPluginInterface(Context context, String str, String str2, String str3, Object[] objArr) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            APLog.i("APPLuginProxyActivity", "initPluginInterface objClass param is null");
            return null;
        }
        Class a = a(context, str, str2, str3);
        if (a == null) {
            APPluginReportManager.getInstance().insertData(APPluginReportManager.MIDASPLUGIN_TIMENAME_LAUNCHPAY, APPluginReportManager.MIDASPLUGIN_FORMAT_APKLOAD_FAIL, this.d, "objClassIsNull");
            a(context.getApplicationContext());
            APPluginStatic.removeAll();
            APPluginUtils.unInstallPlugin(context);
            return null;
        }
        Method method = a.getMethod(str3, getParamsType(objArr));
        method.setAccessible(true);
        long currentTimeMillis = System.currentTimeMillis();
        Object invoke = method.invoke(a, objArr);
        APLog.i("APPLuginProxyActivity", "initPluginInterface method=" + method + " obj=" + invoke + " time=" + (System.currentTimeMillis() - currentTimeMillis));
        return invoke;
    }

    public Object initPluginInterface2(Context context, String str, String str2, String str3, Object[] objArr) {
        int i = 0;
        APLog.i("APPLuginProxyActivity", "initPluginInterface2 pluginName=" + str);
        APLog.i("APPLuginProxyActivity", "initPluginInterface2 interfaceClass=" + str2);
        APLog.i("APPLuginProxyActivity", "initPluginInterface2 methodName=" + str3);
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            APLog.i("APPLuginProxyActivity", "initPluginInterface2 objClass param is null");
            return null;
        }
        Class a = a(context, str, str2, str3);
        if (a == null) {
            APLog.i("APPLuginProxyActivity", "initPluginInterface2 objClass is null");
            APPluginReportManager.getInstance().insertData(APPluginReportManager.MIDASPLUGIN_TIMENAME_LAUNCHPAY, APPluginReportManager.MIDASPLUGIN_FORMAT_APKLOAD_FAIL, this.d, "objClassIsNull");
            a(context.getApplicationContext());
            APPluginStatic.removeAll();
            APPluginUtils.unInstallPlugin(context);
            return null;
        }
        Class[] clsArr = new Class[]{objArr[0].getClass(), objArr[1].getClass(), objArr[2].getClass()};
        APLog.i("APPLuginProxyActivity", "initPluginInterface2 getMethod Name:" + str3);
        APLog.i("APPLuginProxyActivity", "initPluginInterface2 getMethod objClass:" + a);
        while (i < clsArr.length) {
            APLog.i("APPLuginProxyActivity", "initPluginInterface2 getMethod paraTypes:" + clsArr[i]);
            i++;
        }
        Method method = a.getMethod(str3, clsArr);
        APLog.i("APPLuginProxyActivity", "initPluginInterface2 getMethod method:" + method);
        method.setAccessible(true);
        Object invoke = method.invoke(a, objArr);
        APLog.i("APPLuginProxyActivity", "initPluginInterface2 obj=" + invoke);
        return invoke;
    }

    protected boolean isWrapContent() {
        return this.b != null ? this.b.IIsWrapContent() : true;
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        APLog.i("APPLuginProxyActivity", "onActivityResult requestCode:" + i + " resultCode:" + i2 + " mPluginActivity:" + this.b);
        super.onActivityResult(i, i2, intent);
        if (this.b != null) {
            try {
                ClassLoader a = APPluginStatic.a(this.d, APPluginUtils.getMD5FromPath(APPluginUtils.getInstallPath(this, this.d).getCanonicalPath()));
                if (!(a == null || intent == null)) {
                    intent.setExtrasClassLoader(a);
                }
                this.b.IOnActivityResult(i, i2, intent);
            } catch (Exception e) {
                APLog.w("APPLuginProxyActivity onActivityResult", e.toString());
                e.printStackTrace();
            }
        }
    }

    public void onBackPressed() {
        try {
            super.onBackPressed();
        } catch (IllegalStateException e) {
            finish();
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.b != null) {
            try {
                this.b.IOnConfigurationChanged(configuration);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        APLog.i("APPLuginProxyActivity", "APPluginProxyActivity onCreate ");
        APLog.i("APPLuginProxyActivity", "APPluginProxyActivity onCreate gPluginName:" + gPluginName + " gPluginApkFilePath1:" + e);
        if (TextUtils.isEmpty(gPluginName)) {
            super.onCreate(bundle);
            APLog.w("APPLuginProxyActivity", "gPluginName is null");
            finish();
            APPluginStatic.removeAll();
            return;
        }
        Bundle bundle2;
        ClassLoader a = APPluginStatic.a(gPluginName, APPluginUtils.getMD5FromPath(APPluginUtils.getInstallPathString(this, gPluginName)));
        APLog.i("APPLuginProxyActivity", "APPluginProxyActivity onCreate savedInstanceState=" + bundle);
        APLog.i("APPLuginProxyActivity", "APPluginProxyActivity onCreate classLoader=" + a);
        if (!(bundle == null || a == null)) {
            bundle.setClassLoader(a);
        }
        Intent intent = getIntent();
        if (bundle != null) {
            bundle2 = bundle;
        } else {
            if (a != null) {
                intent.setExtrasClassLoader(a);
            }
            bundle2 = getIntent().getExtras();
        }
        try {
            this.d = bundle2.getString(APPluginStatic.PARAM_PLUGIN_NAME);
            this.a = bundle2.getString(APPluginStatic.PARAM_LAUNCH_ACTIVITY);
            this.c = bundle2.getString(APPluginStatic.PARAM_PLUGIN_PATH);
        } catch (Exception e) {
            APLog.w("APPLuginProxyActivity", "bundle exception:" + e.toString());
            e.printStackTrace();
            finish();
            APPluginStatic.removeAll();
        }
        APLog.i("APPLuginProxyActivity", "APPluginProxyActivity onCreate mPluginName：" + this.d);
        APLog.i("APPLuginProxyActivity", "APPluginProxyActivity onCreate mLaunchActivity：" + this.a);
        APLog.i("APPLuginProxyActivity", "APPluginProxyActivity onCreate mPluginApkFilePath：" + this.c);
        if (TextUtils.isEmpty(this.c)) {
            try {
                this.c = APPluginUtils.getInstallPath(this, this.d).getCanonicalPath();
            } catch (Exception e2) {
            }
        }
        if (TextUtils.isEmpty(this.a)) {
            APLog.e("Midas", "APPluginProxyActivity onCreate mLaunchActivity is null");
            super.onCreate(bundle);
            finish();
            APPluginStatic.removeAll();
            return;
        }
        String str;
        Object obj = null;
        if (this.d == null || this.d.length() == 0) {
            str = "Param mPluingLocation missing!";
        } else {
            try {
                str = initPlugin();
                if (str == null) {
                    if (VERSION.SDK_INT >= 11) {
                        getLayoutInflater().setFactory2(new APLayoutInflaterFactory());
                    }
                    super.onCreate(bundle);
                    obj = 1;
                    this.b.IOnCreate(bundle);
                }
            } catch (Throwable e3) {
                Throwable th = e3;
                Object obj2 = null;
                Throwable th2 = th;
                APLog.e("Midas", "APPluginProxyActivity onCreate:" + th2.toString());
                th2.printStackTrace();
                Object obj3 = obj2;
                str = APPluginUtils.getExceptionInfo(th2);
                obj = obj3;
            }
        }
        if (obj == null) {
            super.onCreate(bundle);
        }
        if (str != null) {
            this.mCreateErrorInfo = str;
            APLog.e("Midas", "APPluginProxyActivity onCreate activity failed:" + str);
            a(this.mCreateErrorInfo);
            if (!shouldHandleStartPluginFailed(this.mCreateErrorInfo)) {
                APPluginReportManager.getInstance().insertData(APPluginReportManager.MIDASPLUGIN_TIMENAME_LAUNCHPAY, APPluginReportManager.MIDASPLUGIN_FORMAT_APKLOAD_FAIL, this.d, str);
                a((Context) this);
                finish();
                APPluginStatic.removeAll();
            }
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return this.b != null ? this.b.IOnCreateOptionsMenu(menu) : super.onCreateOptionsMenu(menu);
    }

    protected void onDestroy() {
        super.onDestroy();
        APLog.i("APPluginProxyActivity", "onDestroy mPluginActivity:" + this.b);
        if (this.b != null) {
            try {
                this.b.IOnDestroy();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        boolean z = false;
        if (this.b != null) {
            z = this.b.IOnKeyDown(i, keyEvent);
        }
        return !z ? super.onKeyDown(i, keyEvent) : z;
    }

    public boolean onMenuItemSelected(int i, MenuItem menuItem) {
        return this.b != null ? this.b.IOnMenuItemSelected(i, menuItem) : super.onMenuItemSelected(i, menuItem);
    }

    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        ClassLoader a = APPluginStatic.a(this.d, APPluginUtils.getMD5FromPath(APPluginUtils.getInstallPathString(this, this.d)));
        APLog.i("APPLuginProxyActivity", "APPluginProxyActivity onNewIntent mPluginName:" + this.d + " classLoader: " + a);
        if (a != null) {
            intent.setExtrasClassLoader(a);
        }
        if (this.b != null && intent.getBooleanExtra(APPluginStatic.PARAM_CLEAR_TOP, false)) {
            this.b.IOnNewIntent(intent);
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        return this.b != null ? this.b.IOnOptionsItemSelected(menuItem) : super.onOptionsItemSelected(menuItem);
    }

    protected void onPause() {
        super.onPause();
        if (this.b != null) {
            this.b.IOnPause();
        }
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        return this.b != null ? this.b.IOnPrepareOptionsMenu(menu) : super.onPrepareOptionsMenu(menu);
    }

    protected void onRestoreInstanceState(Bundle bundle) {
        ClassLoader a = APPluginStatic.a(this.d, APPluginUtils.getMD5FromPath(APPluginUtils.getInstallPathString(this, this.d)));
        if (a != null) {
            try {
                a(bundle, a);
            } catch (Exception e) {
            }
        }
        super.onRestoreInstanceState(bundle);
        if (this.b != null) {
            this.b.IOnRestoreInstanceState(bundle);
        }
    }

    protected void onResume() {
        super.onResume();
        if (this.b != null) {
            this.b.IOnResume();
        }
    }

    protected void onSaveInstanceState(Bundle bundle) {
        if (this.b != null) {
            this.b.IOnSaveInstanceState(bundle);
        }
        bundle.putString(APPluginStatic.PARAM_PLUGIN_NAME, this.d);
        bundle.putString(APPluginStatic.PARAM_PLUGIN_LOCATION, this.d);
        bundle.putString(APPluginStatic.PARAM_PLUGIN_PATH, this.c);
        bundle.putString(APPluginStatic.PARAM_LAUNCH_ACTIVITY, this.a);
        super.onSaveInstanceState(bundle);
    }

    protected void onStart() {
        super.onStart();
        if (this.b != null) {
            this.b.IOnStart();
        }
    }

    protected void onStop() {
        super.onStop();
        if (this.b != null) {
            this.b.IOnStop();
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.b != null ? this.b.IOnTouchEvent(motionEvent) : super.onTouchEvent(motionEvent);
    }

    public void onUserInteraction() {
        if (this.b != null) {
            this.b.IOnUserInteraction();
        } else {
            super.onUserInteraction();
        }
    }

    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (this.b != null) {
            this.b.IOnWindowFocusChanged(z);
        }
    }

    public void setRequestedOrientation(int i) {
        APLog.i("APPluginProxyActivity", "setRequestedOrientation requestedOrientation:" + i);
        super.setRequestedOrientation(i);
    }

    protected boolean shouldHandleStartPluginFailed(String str) {
        if (str.contains("permission") || str.contains("filenotfoundexception")) {
            showNeedUninstanllAndInstallDialog();
            return true;
        } else if (!str.contains("resources$notfoundexception") && !str.contains("resourcesnotfoundexception")) {
            return false;
        } else {
            showNeedUninstanllAndInstallDialog();
            return true;
        }
    }

    protected void showNeedUninstanllAndInstallDialog() {
        Builder builder = new Builder(this);
        builder.setTitle("温馨提示");
        builder.setMessage("系统繁忙" + this.d + "失败，请卸载重装~");
        builder.setPositiveButton("我知道了", new OnClickListener(this) {
            final /* synthetic */ APPluginProxyActivity a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                this.a.finish();
            }
        });
        try {
            builder.create().show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startActivityForResult(Intent intent, int i) {
        APLog.i("APPLuginProxyActivity", "startActivityForResult.Override");
        if (intent.getBooleanExtra("pluginsdk_IsPluginActivity", false)) {
            String str = null;
            ComponentName component = intent.getComponent();
            if (component != null) {
                str = component.getClassName();
            }
            intent.putExtra("pluginsdk_IsPluginActivity", false);
            if (str != null && str.length() > 0) {
                a((Activity) this, str, intent, i);
            }
        } else {
            super.startActivityForResult(intent, i);
        }
        this.mStopFlag = 2;
    }

    public void startActivityForResult(Intent intent, int i, int i2) {
        APLog.i("APPLuginProxyActivity", "startActivityForResult.public");
        this.mStopFlag = 2;
        if (a(intent)) {
        }
        if (!isMoveTaskToBack(this, intent)) {
            intent.addFlags(262144);
        }
        super.startActivityForResult(intent, i);
    }
}
