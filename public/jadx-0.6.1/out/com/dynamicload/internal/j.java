package com.dynamicload.internal;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import com.dynamicload.Lib.DLException;
import com.dynamicload.Lib.DLPluginManager;
import com.dynamicload.Lib.DLPluginPackage;

/* compiled from: PluginContextWrapper */
public class j extends ContextWrapper {
    private DLPluginPackage a;
    private Theme b;

    public j(Context context, DLPluginPackage dLPluginPackage, Theme theme) {
        super(context);
        this.a = dLPluginPackage;
        this.b = theme;
    }

    public ClassLoader getClassLoader() {
        if (this.a != null && this.a.isMount()) {
            return this.a.classLoader;
        }
        throw new DLException("plugin is not init, please check code");
    }

    public Resources getResources() {
        if (this.a != null && this.a.isMount()) {
            return this.a.resources;
        }
        throw new DLException("plugin is not init, please check code");
    }

    public AssetManager getAssets() {
        if (this.a != null && this.a.isMount()) {
            return this.a.assetManager;
        }
        throw new DLException("plugin is not init, please check code");
    }

    public String getPackageName() {
        if (this.a != null && this.a.isMount()) {
            return this.a.packageName;
        }
        throw new DLException("plugin is not init, please check code");
    }

    public ApplicationInfo getApplicationInfo() {
        if (this.a != null && this.a.isMount()) {
            return this.a.packageInfo.applicationInfo;
        }
        throw new DLException("plugin is not init, please check code");
    }

    public Theme getTheme() {
        return this.b;
    }

    public void startActivity(Intent intent) {
        DLPluginManager.getInstance(this).startActivity(this, intent);
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
