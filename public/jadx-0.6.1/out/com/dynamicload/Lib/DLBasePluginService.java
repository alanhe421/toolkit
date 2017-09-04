package com.dynamicload.Lib;

import android.app.Service;
import android.content.pm.ApplicationInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;

public abstract class DLBasePluginService extends Service {
    private DLPluginPackage a;
    protected int mFrom = 0;

    public void attach(DLPluginPackage dLPluginPackage) {
        this.mFrom = 1;
        this.a = dLPluginPackage;
    }

    public String getPackageName() {
        if (this.mFrom == 0) {
            return super.getPackageName();
        }
        return getBaseContext().getPackageName();
    }

    public ApplicationInfo getApplicationInfo() {
        if (this.mFrom == 0) {
            return super.getApplicationInfo();
        }
        return this.a.application.getApplicationInfo();
    }

    public AssetManager getAssets() {
        if (this.mFrom == 0) {
            return super.getAssets();
        }
        return this.a.assetManager;
    }

    public Resources getResources() {
        if (this.mFrom == 0) {
            return super.getResources();
        }
        return this.a.resources;
    }

    public ClassLoader getClassLoader() {
        if (this.mFrom == 0) {
            return super.getClassLoader();
        }
        return this.a.classLoader;
    }
}
