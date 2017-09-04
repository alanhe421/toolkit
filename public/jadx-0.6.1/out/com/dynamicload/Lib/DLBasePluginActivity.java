package com.dynamicload.Lib;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.res.Configuration;
import android.content.res.Resources.Theme;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import com.dynamicload.DLProxyActivity;
import com.dynamicload.c;
import com.qq.reader.common.monitor.f;
import com.tencent.feedback.proguard.R;
import java.lang.reflect.Field;

public abstract class DLBasePluginActivity extends Activity implements IDLPluginActivity {
    private View a = null;
    private boolean b = false;
    protected Context mContext;
    protected int mFrom = 0;
    protected DLPluginManager mPluginManager;
    protected DLPluginPackage mPluginPackage;
    protected IDLProxyActivity mProxyActivity;
    protected Theme mTheme = null;

    public void attach(IDLProxyActivity iDLProxyActivity, DLPluginPackage dLPluginPackage, Theme theme) {
        this.mProxyActivity = iDLProxyActivity;
        this.mPluginPackage = dLPluginPackage;
        this.mContext = iDLProxyActivity.getContext();
        if (theme != null) {
            this.mTheme = theme;
        }
        if (this.a != null) {
            this.mProxyActivity.setContentView(this.a);
        }
    }

    public void saveContentView(View view) {
        this.a = view;
    }

    public void disAttach() {
        this.mProxyActivity = null;
        this.mContext = null;
    }

    public void peformOnConfigurationChanged(Configuration configuration) {
        if (this.mFrom == 0) {
            super.onConfigurationChanged(configuration);
        } else {
            this.a.dispatchConfigurationChanged(configuration);
        }
    }

    public void onCreate(Bundle bundle) {
        if (bundle != null) {
            this.mFrom = bundle.getInt(DLConstants.FROM, 0);
        }
        if (this.mFrom == 0) {
            super.onCreate(bundle);
            this.mContext = this;
        }
        c.a("package name: " + getPackageName() + " application info: " + getApplicationInfo() + " application context: " + getApplicationContext());
        this.mPluginManager = DLPluginManager.getInstance(this.mContext);
    }

    public void setContentView(View view) {
        if (this.mFrom == 0) {
            super.setContentView(view);
        } else {
            this.mProxyActivity.setContentView(view);
        }
    }

    public void setContentView(View view, LayoutParams layoutParams) {
        if (this.mFrom == 0) {
            super.setContentView(view, layoutParams);
        } else {
            this.mProxyActivity.setContentView(view, layoutParams);
        }
    }

    public void setContentView(int i) {
        if (this.mFrom == 0) {
            super.setContentView(i);
        } else {
            this.mProxyActivity.setContentView(i);
        }
    }

    public void addContentView(View view, LayoutParams layoutParams) {
        if (this.mFrom == 0) {
            super.addContentView(view, layoutParams);
        } else {
            this.mProxyActivity.addContentView(view, layoutParams);
        }
    }

    public View findViewById(int i) {
        if (this.mFrom == 0) {
            return super.findViewById(i);
        }
        return this.mProxyActivity.findViewById(i);
    }

    public Intent getIntent() {
        if (this.mFrom == 0) {
            return super.getIntent();
        }
        return this.mProxyActivity.getIntent();
    }

    public LayoutInflater getLayoutInflater() {
        if (this.mFrom == 0) {
            return super.getLayoutInflater();
        }
        return this.mProxyActivity.getHostLayoutInflater();
    }

    public MenuInflater getMenuInflater() {
        if (this.mFrom == 0) {
            return super.getMenuInflater();
        }
        return this.mProxyActivity.getMenuInflater();
    }

    public SharedPreferences getSharedPreferences(String str, int i) {
        if (this.mFrom == 0) {
            return super.getSharedPreferences(str, i);
        }
        return this.mProxyActivity.getSharedPreferences(str, i);
    }

    public WindowManager getWindowManager() {
        if (this.mFrom == 0) {
            return super.getWindowManager();
        }
        return this.mProxyActivity.getWindowManager();
    }

    public Window getWindow() {
        if (this.mFrom == 0) {
            return super.getWindow();
        }
        return this.mProxyActivity.getWindow();
    }

    public Object getSystemService(String str) {
        if (this.mFrom == 0) {
            return super.getSystemService(str);
        }
        return this.mProxyActivity.getHostSystemService(str);
    }

    public ComponentName getComponentName() {
        if (this.mFrom == 0) {
            return super.getComponentName();
        }
        return ((Activity) this.mProxyActivity).getComponentName();
    }

    public Theme getTheme() {
        if (this.mFrom == 0) {
            return super.getTheme();
        }
        return this.mTheme;
    }

    public void setTheme(Theme theme) {
        this.mTheme = theme;
    }

    public void setTheme(int i) {
    }

    public void finish() {
        Exception exception;
        if (this.mFrom == 0) {
            super.finish();
            super.overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
            return;
        }
        Intent intent;
        int i = 0;
        Intent intent2 = null;
        synchronized (this) {
            try {
                Field declaredField = Activity.class.getDeclaredField("mResultCode");
                declaredField.setAccessible(true);
                i = ((Integer) declaredField.get(this)).intValue();
                declaredField = Activity.class.getDeclaredField("mResultData");
                declaredField.setAccessible(true);
                intent = (Intent) declaredField.get(this);
                if (intent != null) {
                    try {
                        intent.setExtrasClassLoader(this.mPluginPackage.classLoader);
                    } catch (Exception e) {
                        Exception exception2 = e;
                        intent2 = intent;
                        exception = exception2;
                        f.a("DLBasePluginActivity", "BasePluginActivity.finish", exception);
                        intent = intent2;
                        this.mProxyActivity.setResult(i, intent);
                        this.mProxyActivity.finish();
                        this.mProxyActivity.overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                        this.b = true;
                    }
                }
            } catch (Exception e2) {
                exception = e2;
                f.a("DLBasePluginActivity", "BasePluginActivity.finish", exception);
                intent = intent2;
                this.mProxyActivity.setResult(i, intent);
                this.mProxyActivity.finish();
                this.mProxyActivity.overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                this.b = true;
            }
        }
        this.mProxyActivity.setResult(i, intent);
        this.mProxyActivity.finish();
        this.mProxyActivity.overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
        this.b = true;
    }

    public boolean isFinishing() {
        if (this.mFrom == 0) {
            return super.isFinishing();
        }
        return this.b;
    }

    public DLPluginPackage getPackage() {
        return this.mPluginPackage;
    }

    public void onBackPressed() {
        if (this.mFrom == 0) {
            super.onBackPressed();
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (this.mFrom == 0) {
            super.onActivityResult(i, i2, intent);
        }
        c.a("onActivityResult->this= " + this + " requestCode= " + i + " resultCode= " + i2 + " data= " + intent);
    }

    public void onStart() {
        if (this.mFrom == 0) {
            super.onStart();
        }
    }

    public void onRestart() {
        if (this.mFrom == 0) {
            super.onRestart();
        }
    }

    public void onRestoreInstanceState(Bundle bundle) {
        if (this.mFrom == 0) {
            super.onRestoreInstanceState(bundle);
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        if (this.mFrom == 0) {
            super.onSaveInstanceState(bundle);
        }
    }

    public void onNewIntent(Intent intent) {
        if (this.mFrom == 0) {
            super.onNewIntent(intent);
        }
    }

    public void onResume() {
        if (this.mFrom == 0) {
            super.onResume();
        }
    }

    public void onPause() {
        if (this.mFrom == 0) {
            super.onPause();
        }
    }

    public void onStop() {
        if (this.mFrom == 0) {
            super.onStop();
        }
    }

    public void onDestroy() {
        if (this.mFrom == 0) {
            super.onDestroy();
        }
        this.mPluginManager = null;
        this.mPluginPackage = null;
        this.mProxyActivity = null;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.mFrom == 0) {
            return super.onTouchEvent(motionEvent);
        }
        return false;
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (this.mFrom == 0) {
            return super.onKeyUp(i, keyEvent);
        }
        return this.mProxyActivity.onSuperKeyUp(i, keyEvent);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (this.mFrom == 0) {
            return super.onKeyDown(i, keyEvent);
        }
        return this.mProxyActivity.onSuperKeyDown(i, keyEvent);
    }

    public void onWindowAttributesChanged(WindowManager.LayoutParams layoutParams) {
        if (this.mFrom == 0) {
            super.onWindowAttributesChanged(layoutParams);
        }
    }

    public void onWindowFocusChanged(boolean z) {
        if (this.mFrom == 0) {
            super.onWindowFocusChanged(z);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        if (this.mFrom == 0) {
            return super.onCreateOptionsMenu(menu);
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (this.mFrom == 0) {
            return super.onOptionsItemSelected(menuItem);
        }
        return false;
    }

    public void startActivity(Intent intent) {
        if (this.mProxyActivity == null) {
            super.startActivity(intent);
        } else if (intent.getBooleanExtra(DLConstants.FROM_AM, false)) {
            ((DLProxyActivity) this.mContext).startActivity(intent);
        } else {
            DLPluginManager.getInstance(this).startActivity(this, intent);
        }
    }

    public void startActivityForResult(Intent intent, int i) {
        if (this.mProxyActivity == null) {
            super.startActivityForResult(intent, i);
        } else if (intent.getBooleanExtra(DLConstants.FROM_AM, false)) {
            ((DLProxyActivity) this.mContext).startActivityForResult(intent, i);
        } else {
            DLPluginManager.getInstance(this).startActivityForResult(this, intent, i);
        }
    }

    public void setRequestedOrientation(int i) {
        if (this.mFrom == 0) {
            super.setRequestedOrientation(i);
        } else {
            this.mProxyActivity.setRequestedOrientation(i);
        }
    }

    public int getRequestedOrientation() {
        if (this.mFrom == 0) {
            return super.getRequestedOrientation();
        }
        return this.mProxyActivity.getRequestedOrientation();
    }

    public ComponentName startService(Intent intent) {
        if (this.mProxyActivity != null) {
            return DLPluginManager.getInstance(this).startService(this, intent);
        }
        return super.startService(intent);
    }

    public boolean stopService(Intent intent) {
        if (this.mProxyActivity != null) {
            return DLPluginManager.getInstance(this).stopService(this, intent);
        }
        return super.stopService(intent);
    }

    public boolean bindService(Intent intent, ServiceConnection serviceConnection, int i) {
        if (this.mProxyActivity != null) {
            return DLPluginManager.getInstance(this).bindService(this, intent, serviceConnection, i);
        }
        return super.bindService(intent, serviceConnection, i);
    }

    public void unbindService(ServiceConnection serviceConnection) {
        if (this.mProxyActivity != null) {
            DLPluginManager.getInstance(this).unbindService(this, serviceConnection);
        } else {
            super.unbindService(serviceConnection);
        }
    }

    public Intent registerReceiver(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        if (this.mProxyActivity != null) {
            DLPluginManager.getInstance(this).registerReceiver(this, broadcastReceiver, intentFilter);
        } else {
            super.registerReceiver(broadcastReceiver, intentFilter);
        }
        return null;
    }

    public void unregisterReceiver(BroadcastReceiver broadcastReceiver) {
        if (this.mProxyActivity != null) {
            DLPluginManager.getInstance(this).unregisterReceiver(this, broadcastReceiver);
        } else {
            super.unregisterReceiver(broadcastReceiver);
        }
    }

    public void sendBroadcast(Intent intent) {
        if (this.mProxyActivity != null) {
            DLPluginManager.getInstance(this).sendBroadcast(this, intent);
        } else {
            super.sendBroadcast(intent);
        }
    }

    public void setPluginResult(int i, Intent intent) {
        if (this.mFrom == 1) {
            if (intent != null) {
                intent.setExtrasClassLoader(this.mPluginPackage.classLoader);
            }
            this.mProxyActivity.setResult(i, intent);
            return;
        }
        super.setResult(i, intent);
    }

    public void setPluginResult(int i) {
        setPluginResult(i, null);
    }

    public void peformOnCreate(Bundle bundle) {
        onCreate(bundle);
    }

    public void peformOnStart() {
        onStart();
    }

    public void peformOnRestart() {
        onRestart();
    }

    public void peformOnActivityResult(int i, int i2, Intent intent) {
        if (intent != null) {
            intent.setExtrasClassLoader(this.mPluginPackage.classLoader);
        }
        onActivityResult(i, i2, intent);
    }

    public void peformOnResume() {
        onResume();
    }

    public void peformOnPause() {
        onPause();
    }

    public void peformOnStop() {
        onStop();
    }

    public void peformOnDestroy() {
        onDestroy();
    }

    public void peformOnSaveInstanceState(Bundle bundle) {
        onSaveInstanceState(bundle);
    }

    public void peformOnNewIntent(Intent intent) {
        onNewIntent(intent);
    }

    public void peformOnRestoreInstanceState(Bundle bundle) {
        onRestoreInstanceState(bundle);
    }

    public boolean peformOnTouchEvent(MotionEvent motionEvent) {
        return onTouchEvent(motionEvent);
    }

    public boolean peformOnKeyUp(int i, KeyEvent keyEvent) {
        return onKeyUp(i, keyEvent);
    }

    public boolean peformOnKeyDown(int i, KeyEvent keyEvent) {
        return onKeyDown(i, keyEvent);
    }

    public void peformOnWindowAttributesChanged(WindowManager.LayoutParams layoutParams) {
        onWindowAttributesChanged(layoutParams);
    }

    public void peformOnWindowFocusChanged(boolean z) {
        onWindowFocusChanged(z);
    }

    public void peformOnBackPressed() {
        onBackPressed();
    }

    public boolean peformOnCreateOptionsMenu(Menu menu) {
        return onCreateOptionsMenu(menu);
    }

    public boolean peformOnOptionsItemSelected(MenuItem menuItem) {
        return onOptionsItemSelected(menuItem);
    }

    public ApplicationInfo getApplicationInfo() {
        return this.mPluginPackage.application.getApplicationInfo();
    }

    public boolean isGesture() {
        return true;
    }

    public void targetActivity() {
        finish();
    }
}
