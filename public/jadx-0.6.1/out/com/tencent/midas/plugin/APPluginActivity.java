package com.tencent.midas.plugin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.tencent.midas.comm.APLog;
import java.lang.reflect.Field;
import java.util.List;

public class APPluginActivity extends Activity implements IAPPluginActivity {
    private ClassLoader a = null;
    private boolean b = false;
    public Activity mActivity = null;
    protected String mApkFilePath = "";
    public Context mContext = null;
    protected boolean mIsRunInPlugin = false;
    protected PackageInfo mPackageInfo = null;
    protected String mPluginName = "";
    public Activity mProxyActivity = null;
    protected View mProxyContentView = null;

    public static final Bitmap getDrawableBitmap(Drawable drawable) {
        return (drawable != null && (drawable instanceof BitmapDrawable)) ? ((BitmapDrawable) drawable).getBitmap() : null;
    }

    public boolean IDispatchTouchEvent(MotionEvent motionEvent) {
        boolean z = true;
        try {
            z = dispatchTouchEvent(motionEvent);
        } catch (Exception e) {
        }
        return z;
    }

    public void IFinish() {
        finish();
    }

    public View IGetContentView() {
        return this.mProxyContentView;
    }

    public Handler IGetInHandler() {
        return null;
    }

    public Resources IGetResource() {
        return this.mContext != null ? this.mContext.getResources() : this.mActivity.getResources();
    }

    public void IInit(String str, String str2, Activity activity, ClassLoader classLoader, PackageInfo packageInfo) {
        this.mIsRunInPlugin = true;
        this.a = classLoader;
        this.mProxyActivity = activity;
        this.mPluginName = str;
        this.mApkFilePath = str2;
        this.mPackageInfo = packageInfo;
        this.mContext = new a(activity, 0, this.mApkFilePath, this.a);
        APLog.i("APPluginActivity", "APPluginActivity IInit mContext:" + this.mContext + " mDexClassLoader:" + this.a.hashCode());
        attachBaseContext(this.mContext);
    }

    public boolean IIsWrapContent() {
        return true;
    }

    public void IOnActivityResult(int i, int i2, Intent intent) {
        onActivityResult(i, i2, intent);
    }

    public void IOnConfigurationChanged(Configuration configuration) {
        onConfigurationChanged(configuration);
    }

    public void IOnCreate(Bundle bundle) {
        onCreate(bundle);
    }

    public boolean IOnCreateOptionsMenu(Menu menu) {
        return onCreateOptionsMenu(menu);
    }

    public void IOnDestroy() {
        onDestroy();
    }

    public boolean IOnKeyDown(int i, KeyEvent keyEvent) {
        return onKeyDown(i, keyEvent);
    }

    public boolean IOnKeyMultiple(int i, int i2, KeyEvent keyEvent) {
        return onKeyMultiple(i, i2, keyEvent);
    }

    public boolean IOnKeyUp(int i, KeyEvent keyEvent) {
        return onKeyUp(i, keyEvent);
    }

    public boolean IOnMenuItemSelected(int i, MenuItem menuItem) {
        return onMenuItemSelected(i, menuItem);
    }

    public void IOnNewIntent(Intent intent) {
        onNewIntent(intent);
    }

    public boolean IOnOptionsItemSelected(MenuItem menuItem) {
        return onOptionsItemSelected(menuItem);
    }

    public void IOnPause() {
        onPause();
    }

    public boolean IOnPrepareOptionsMenu(Menu menu) {
        return onPrepareOptionsMenu(menu);
    }

    public void IOnRestart() {
        onRestart();
    }

    public void IOnRestoreInstanceState(Bundle bundle) {
    }

    public void IOnResume() {
        onResume();
    }

    public void IOnSaveInstanceState(Bundle bundle) {
    }

    public void IOnStart() {
        onStart();
    }

    public void IOnStop() {
        onStop();
    }

    public boolean IOnTouchEvent(MotionEvent motionEvent) {
        return onTouchEvent(motionEvent);
    }

    public void IOnUserInteraction() {
        onUserInteraction();
    }

    public void IOnWindowFocusChanged(boolean z) {
        onWindowFocusChanged(z);
    }

    public void ISetIntent(Intent intent) {
        setIntent(intent);
    }

    public void ISetOutHandler(Handler handler) {
    }

    public View findViewById(int i) {
        if (!this.mIsRunInPlugin || this.mProxyContentView == null) {
            return super.findViewById(i);
        }
        View findViewById = this.mProxyContentView.findViewById(i);
        return findViewById == null ? super.findViewById(i) : findViewById;
    }

    public void finish() {
        APLog.i("APPluginActivity", "APPluginActivity finish");
        if (this.mIsRunInPlugin) {
            Intent intent;
            int i = 0;
            synchronized (this) {
                try {
                    Field declaredField = Activity.class.getDeclaredField("mResultCode");
                    declaredField.setAccessible(true);
                    i = ((Integer) declaredField.get(this)).intValue();
                    declaredField = Activity.class.getDeclaredField("mResultData");
                    declaredField.setAccessible(true);
                    intent = (Intent) declaredField.get(this);
                } catch (Exception e) {
                    Log.e("Midas", "APPluginActivity finish Exception:" + e.toString());
                    intent = null;
                }
            }
            this.mProxyActivity.setResult(i, intent);
            this.mProxyActivity.finish();
            this.b = true;
            return;
        }
        super.finish();
    }

    public Context getApplicationContext() {
        APLog.i("APPluginActivity", "APPluginActivity getApplicationContext mProxyActivity:" + this.mProxyActivity);
        return this.mIsRunInPlugin ? this.mProxyActivity.getApplicationContext() : super.getApplicationContext();
    }

    public ApplicationInfo getApplicationInfo() {
        return this.mIsRunInPlugin ? this.mPackageInfo.applicationInfo : super.getApplicationInfo();
    }

    public int getChangingConfigurations() {
        return this.mIsRunInPlugin ? this.mProxyActivity.getChangingConfigurations() : super.getChangingConfigurations();
    }

    public Resources getHostResources() {
        return this.mProxyActivity.getResources();
    }

    public LayoutInflater getLayoutInflater() {
        return this.mContext != null ? LayoutInflater.from(this.mContext) : LayoutInflater.from(this.mActivity);
    }

    public Activity getOutActivity() {
        return this.mProxyActivity;
    }

    public Resources getOutResources() {
        return this.mIsRunInPlugin ? this.mProxyActivity.getResources() : this.mActivity.getResources();
    }

    public PackageInfo getPackageInfo() {
        return this.mIsRunInPlugin ? this.mPackageInfo : null;
    }

    public String getPackageName() {
        return this.mIsRunInPlugin ? this.mPackageInfo.packageName : super.getPackageName();
    }

    public Object getSystemService(String str) {
        return ("window".equals(str) || "search".equals(str)) ? this.mIsRunInPlugin ? this.mProxyActivity.getSystemService(str) : super.getSystemService(str) : this.mContext != null ? this.mContext.getSystemService(str) : super.getSystemService(str);
    }

    public Window getWindow() {
        return this.mIsRunInPlugin ? this.mProxyActivity.getWindow() : super.getWindow();
    }

    public WindowManager getWindowManager() {
        return this.mIsRunInPlugin ? this.mProxyActivity.getWindowManager() : super.getWindowManager();
    }

    public boolean isFinishing() {
        return this.mIsRunInPlugin ? this.b : super.isFinishing();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        APLog.i("APPluginActivity", "APPluginActivity onActivityResult");
        if (!this.mIsRunInPlugin) {
            super.onActivityResult(i, i2, intent);
        }
    }

    protected void onCreate(Bundle bundle) {
        if (this.mIsRunInPlugin) {
            this.mActivity = this.mProxyActivity;
            APPluginStatic.a(this);
            return;
        }
        super.onCreate(bundle);
        this.mActivity = this;
    }

    protected void onDestroy() {
        APLog.i("APPluginActivity", "onDestroy mIsRunInPlugin:" + this.mIsRunInPlugin);
        if (this.mIsRunInPlugin) {
            this.a = null;
            APPluginStatic.b(this);
            return;
        }
        super.onDestroy();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return this.mIsRunInPlugin ? false : super.onKeyDown(i, keyEvent);
    }

    public boolean onKeyMultiple(int i, int i2, KeyEvent keyEvent) {
        return this.mIsRunInPlugin ? false : super.onKeyMultiple(i, i2, keyEvent);
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        return this.mIsRunInPlugin ? false : super.onKeyUp(i, keyEvent);
    }

    protected void onPause() {
        if (!this.mIsRunInPlugin) {
            super.onPause();
        }
    }

    protected void onRestart() {
        if (!this.mIsRunInPlugin) {
            super.onRestart();
        }
    }

    protected void onResume() {
        if (!this.mIsRunInPlugin) {
            super.onResume();
        }
    }

    protected void onStart() {
        if (!this.mIsRunInPlugin) {
            super.onStart();
        }
    }

    protected void onStop() {
        if (!this.mIsRunInPlugin) {
            super.onStop();
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.mIsRunInPlugin ? false : super.onTouchEvent(motionEvent);
    }

    public void onWindowFocusChanged(boolean z) {
        if (!this.mIsRunInPlugin) {
            super.onWindowFocusChanged(z);
        }
    }

    public void openOptionsMenu() {
        if (this.mIsRunInPlugin) {
            this.mProxyActivity.openOptionsMenu();
        } else {
            super.openOptionsMenu();
        }
    }

    public void overridePendingTransition(int i, int i2) {
        if (this.mIsRunInPlugin) {
            this.mActivity.overridePendingTransition(i, i2);
        } else {
            super.overridePendingTransition(i, i2);
        }
    }

    public void setContentView(int i) {
        if (this.mIsRunInPlugin) {
            this.mProxyContentView = LayoutInflater.from(this).inflate(i, null);
            this.mActivity.setContentView(this.mProxyContentView);
            return;
        }
        super.setContentView(i);
    }

    public void setContentView(View view) {
        if (this.mIsRunInPlugin) {
            this.mProxyContentView = view;
            this.mActivity.setContentView(this.mProxyContentView);
            return;
        }
        super.setContentView(view);
    }

    public void setRequestedOrientation(int i) {
        super.setRequestedOrientation(i);
    }

    public void setTheme(int i) {
        if (this.mIsRunInPlugin) {
            this.mProxyActivity.setTheme(i);
        } else {
            super.setTheme(i);
        }
    }

    public void startActivityForResult(Intent intent, int i) {
        boolean z = false;
        if (this.mIsRunInPlugin) {
            if (intent.hasExtra(APPluginStatic.PARAM_PLUGIN_INTERNAL_ACTIVITIES_ONLY)) {
                z = intent.getBooleanExtra(APPluginStatic.PARAM_PLUGIN_INTERNAL_ACTIVITIES_ONLY, false);
            } else {
                List queryIntentActivities = this.mActivity.getPackageManager().queryIntentActivities(intent, 65536);
                if (queryIntentActivities == null || queryIntentActivities.size() == 0) {
                    z = true;
                }
            }
            if (z) {
                intent.putExtra("pluginsdk_IsPluginActivity", true);
                this.mActivity.startActivityForResult(intent, i);
                return;
            }
            this.mActivity.startActivityForResult(intent, i);
            return;
        }
        super.startActivityForResult(intent, i);
    }
}
