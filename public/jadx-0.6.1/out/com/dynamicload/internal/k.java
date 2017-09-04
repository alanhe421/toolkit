package com.dynamicload.internal;

import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import com.qq.reader.ReaderApplication;

/* compiled from: PluginRescource */
public class k extends Resources {
    public k(AssetManager assetManager, DisplayMetrics displayMetrics, Configuration configuration) {
        super(assetManager, displayMetrics, configuration);
    }

    public Configuration getConfiguration() {
        return ReaderApplication.getApplicationImp().getResources().getConfiguration();
    }

    public DisplayMetrics getDisplayMetrics() {
        return ReaderApplication.getApplicationImp().getResources().getDisplayMetrics();
    }
}
