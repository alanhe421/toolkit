package com.dynamicload.internal;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import com.dynamicload.Lib.DLConstants;
import com.dynamicload.Lib.DLPluginManager;
import com.dynamicload.Lib.DLPluginPackage;
import com.dynamicload.c;

/* compiled from: DLProxyImpl */
public class h {
    protected Context c;
    protected String d;
    protected String e;
    protected DLPluginPackage f;
    protected DLPluginManager g;
    protected AssetManager h;
    protected Resources i;

    public h(Context context) {
        this.c = context;
    }

    public void c(Intent intent) {
        if (intent != null) {
            try {
                this.g = DLPluginManager.getInstance(this.c);
                this.e = intent.getStringExtra(DLConstants.EXTRA_PACKAGE);
                this.d = intent.getStringExtra(DLConstants.EXTRA_CLASS);
                this.f = this.g.getPackage(this.e);
                if (this.f != null && this.f.isMount()) {
                    this.h = this.f.assetManager;
                    this.i = this.f.resources;
                }
            } catch (Exception e) {
                c.d("DLProxyImpl init Exception= " + e);
            }
        }
    }
}
