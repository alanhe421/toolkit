package com.dynamicload.internal;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.res.Resources.Theme;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import com.dynamicload.Lib.DLBasePluginActivity;
import com.dynamicload.Lib.DLConstants;
import com.dynamicload.Lib.DLPluginManager;
import com.dynamicload.Lib.DLPluginPackage;

public class PluginActivityWrapper extends DLBasePluginActivity {
    public PluginActivityWrapper(Context context, DLPluginPackage dLPluginPackage, Context context2, Theme theme) {
        this.mContext = context2;
        this.mTheme = theme;
        this.mPluginPackage = dLPluginPackage;
        this.mPluginManager = DLPluginManager.getInstance();
        this.mFrom = 1;
        attachBaseContext(context);
    }

    public Object getSystemService(String str) {
        Object systemService = this.mContext.getSystemService(str);
        if ("layout_inflater".equals(str)) {
            return ((LayoutInflater) systemService).cloneInContext(this);
        }
        return systemService;
    }

    public void startActivity(Intent intent) {
        if (intent.getBooleanExtra(DLConstants.FROM_AM, false)) {
            this.mContext.startActivity(intent);
        } else {
            DLPluginManager.getInstance(this).startActivity(this, intent);
        }
    }

    public void startActivityForResult(Intent intent, int i) {
        if (intent.getBooleanExtra(DLConstants.FROM_AM, false)) {
            ((Activity) this.mContext).startActivityForResult(intent, i);
        } else {
            DLPluginManager.getInstance(this).startActivityForResult(this, intent, i);
        }
    }

    public Window getWindow() {
        return ((Activity) this.mContext).getWindow();
    }

    public void setContentView(View view) {
        ((Activity) this.mContext).setContentView(view);
    }

    public void setContentView(View view, LayoutParams layoutParams) {
        ((Activity) this.mContext).setContentView(view, layoutParams);
    }

    public void setContentView(int i) {
        ((Activity) this.mContext).setContentView(i);
    }

    public void addContentView(View view, LayoutParams layoutParams) {
        ((Activity) this.mContext).addContentView(view, layoutParams);
    }

    public View findViewById(int i) {
        return ((Activity) this.mContext).findViewById(i);
    }

    public Intent getIntent() {
        return ((Activity) this.mContext).getIntent();
    }

    public LayoutInflater getLayoutInflater() {
        return (LayoutInflater) getSystemService("layout_inflater");
    }

    public MenuInflater getMenuInflater() {
        return ((Activity) this.mContext).getMenuInflater();
    }

    public SharedPreferences getSharedPreferences(String str, int i) {
        return this.mContext.getSharedPreferences(str, i);
    }

    public WindowManager getWindowManager() {
        return ((Activity) this.mContext).getWindowManager();
    }

    public ComponentName getComponentName() {
        return ((Activity) this.mContext).getComponentName();
    }

    public void finish() {
        ((Activity) this.mContext).finish();
    }

    public void setRequestedOrientation(int i) {
        ((Activity) this.mContext).setRequestedOrientation(i);
    }

    public int getRequestedOrientation() {
        return ((Activity) this.mContext).getRequestedOrientation();
    }

    public ComponentName startService(Intent intent) {
        return DLPluginManager.getInstance(this).startService(this, intent);
    }

    public boolean stopService(Intent intent) {
        return DLPluginManager.getInstance(this).stopService(this, intent);
    }

    public boolean bindService(Intent intent, ServiceConnection serviceConnection, int i) {
        return DLPluginManager.getInstance(this).bindService(this, intent, serviceConnection, i);
    }

    public void unbindService(ServiceConnection serviceConnection) {
        DLPluginManager.getInstance(this).unbindService(this, serviceConnection);
    }

    public Intent registerReceiver(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        DLPluginManager.getInstance(this).registerReceiver(this, broadcastReceiver, intentFilter);
        return null;
    }

    public void unregisterReceiver(BroadcastReceiver broadcastReceiver) {
        DLPluginManager.getInstance(this).unregisterReceiver(this, broadcastReceiver);
    }

    public void sendBroadcast(Intent intent) {
        DLPluginManager.getInstance(this).sendBroadcast(this, intent);
    }
}
