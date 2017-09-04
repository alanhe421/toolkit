package com.dynamicload.Lib;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;
import com.dynamicload.internal.PackageConfig;
import dalvik.system.DexClassLoader;

public class DLPluginPackage {
    private boolean a = false;
    public String apkName;
    public long apkSize;
    public int apkVersion;
    public Application application;
    public AssetManager assetManager;
    public DexClassLoader classLoader;
    public String defaultActivity;
    public boolean isBanned = false;
    public PackageInfo packageInfo;
    public String packageName;
    public Resources resources;

    public DLPluginPackage(PackageConfig packageConfig) {
        this.packageName = packageConfig.packageName;
        this.apkName = packageConfig.apkName;
        this.apkSize = packageConfig.apkSize;
        this.apkVersion = packageConfig.apkVersion;
    }

    private final String a() {
        if (this.packageInfo.activities == null || this.packageInfo.activities.length <= 0) {
            return "";
        }
        return this.packageInfo.activities[0].name;
    }

    public synchronized void mountApk(DexClassLoader dexClassLoader, Resources resources, PackageInfo packageInfo) {
        this.packageInfo = packageInfo;
        this.classLoader = dexClassLoader;
        this.assetManager = resources.getAssets();
        this.resources = resources;
        this.defaultActivity = a();
        this.a = true;
    }

    public synchronized void unmountApk() {
        if (this.a) {
            this.assetManager.close();
            this.packageInfo = null;
            this.classLoader = null;
            this.assetManager = null;
            this.resources = null;
            this.defaultActivity = null;
            this.application = null;
            this.a = false;
        }
    }

    public synchronized boolean isMount() {
        return this.a;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("apkName= ").append(this.apkName).append("; packageName= ").append(this.packageName).append("; apkSize= ").append(this.apkSize).append("; apkVersion= ").append(this.apkVersion).append("; isMounted= ").append(this.a).append("; defaultActivity= ").append(this.defaultActivity).append("; classLoader= ").append(this.classLoader);
        if (this.application != null) {
            stringBuffer.append(";application: ").append(this.application.getApplicationContext().getPackageName()).append(" | ").append(this.application.getPackageName());
        }
        return stringBuffer.toString();
    }
}
