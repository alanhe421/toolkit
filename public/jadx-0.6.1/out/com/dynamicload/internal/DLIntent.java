package com.dynamicload.internal;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.dynamicload.Lib.DLConstants;
import com.dynamicload.Lib.DLException;
import com.dynamicload.Lib.DLPluginManager;
import com.dynamicload.c;

public class DLIntent extends Intent {
    private String a;
    private String b;
    private ClassLoader c;

    public DLIntent() {
        throw new DLException("DLIntent can't be new without Intent!");
    }

    public DLIntent(Intent intent) {
        ComponentName component = intent.getComponent();
        if (component != null) {
            this.a = component.getPackageName();
            this.b = component.getClassName();
        } else {
            this.a = intent.getPackage();
        }
        putExtra(DLConstants.EXTRA_PACKAGE, this.a);
        putExtra(DLConstants.EXTRA_CLASS, this.b);
        this.c = a(this.a);
        if (this.c != null) {
            c.b("DLIntent componentName= " + component + " mClassLoader= " + this.c);
            intent.setExtrasClassLoader(this.c);
            setExtrasClassLoader(this.c);
            super.setData(Uri.parse(DLConstants.DLINTENT_DATA_SCHEME + this.a));
        }
        Object action = intent.getAction();
        if (!TextUtils.isEmpty(action)) {
            setAction(action);
        }
        Bundle extras = intent.getExtras();
        if (extras != null) {
            super.putExtras(extras);
        }
    }

    private ClassLoader a(String str) {
        try {
            return DLPluginManager.getInstance().getPackage(str).classLoader;
        } catch (Exception e) {
            c.b("getExtraClassLoader Exception: " + e + " pluginPackage= " + str);
            return null;
        }
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("DLIntent: ").append(super.toString()).append("; mPluginPackage= ").append(this.a).append("; mPluginClass= ").append(this.b);
        return stringBuilder.toString();
    }
}
