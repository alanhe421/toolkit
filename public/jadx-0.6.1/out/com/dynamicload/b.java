package com.dynamicload;

import android.os.Message;
import com.dynamicload.Lib.DLBasePluginCallBack;
import com.dynamicload.Lib.DLPluginManager;

/* compiled from: DLProxyCallBack */
public final class b extends DLBasePluginCallBack {
    private String a;

    public b(DLBasePluginCallBack dLBasePluginCallBack) {
        this.a = dLBasePluginCallBack.getClass().getName();
        this.mType = dLBasePluginCallBack.getType();
        this.mBundle = dLBasePluginCallBack.getBundle();
        try {
            ClassLoader classLoader = DLPluginManager.getInstance().getPackage(dLBasePluginCallBack.getClass().getPackage().getName()).classLoader;
            if (classLoader != null) {
                this.mBundle.setClassLoader(classLoader);
            }
        } catch (Exception e) {
            c.b("DLProxyCallBack Exception e= " + e);
        }
    }

    public String a() {
        return this.a;
    }

    public boolean handleMessage(Message message) {
        if (DLPluginManager.getInstance() != null) {
            DLBasePluginCallBack pluginCallback = DLPluginManager.getInstance().getPluginCallback(this.a);
            if (pluginCallback != null) {
                return pluginCallback.handleMessage(message);
            }
        }
        return false;
    }

    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        return this.a.equals(((b) obj).a());
    }

    public int hashCode() {
        return super.hashCode();
    }
}
