package com.dynamicload.internal;

import android.os.Bundle;
import android.text.TextUtils;
import com.dynamicload.Lib.DLBaseIpcOperator;
import com.dynamicload.Lib.DLBasePluginCallBack;
import com.dynamicload.Lib.DLException;
import com.dynamicload.b;
import com.dynamicload.c;
import java.util.HashMap;
import java.util.Map.Entry;

/* compiled from: DLIPCManager */
public class d {
    private c a;
    private DLBaseIpcOperator b = null;
    private byte[] c = new byte[0];
    private byte[] d = new byte[0];
    private HashMap<String, DLBaseIpcOperator> e = new HashMap();
    private HashMap<String, DLBasePluginCallBack> f = new HashMap();

    public d(c cVar) {
        this.a = cVar;
    }

    public void a(DLBaseIpcOperator dLBaseIpcOperator) {
        this.b = dLBaseIpcOperator;
    }

    public boolean b(DLBaseIpcOperator dLBaseIpcOperator) {
        if (dLBaseIpcOperator == null) {
            throw new DLException("operator is null!");
        }
        String name = dLBaseIpcOperator.getClass().getPackage().getName();
        if (this.e.containsKey(name)) {
            c.a("registerPluginIpcOperator this package aready register oprater");
            return false;
        }
        synchronized (this.c) {
            this.e.put(name, dLBaseIpcOperator);
        }
        return true;
    }

    public int a(String str, int i, Bundle bundle, Bundle bundle2) {
        if (TextUtils.isEmpty(str)) {
            throw new NullPointerException("pkgName should not be null!");
        }
        DLBaseIpcOperator dLBaseIpcOperator = (DLBaseIpcOperator) this.e.get(str);
        if (dLBaseIpcOperator == null) {
            return 501;
        }
        return dLBaseIpcOperator.handleSyncRequest(i, bundle, bundle2);
    }

    public int a(int i, Bundle bundle, Bundle bundle2) {
        if (this.b == null) {
            return 501;
        }
        return this.b.handleSyncRequest(i, bundle, bundle2);
    }

    public int a(DLBasePluginCallBack dLBasePluginCallBack) {
        if (dLBasePluginCallBack == null) {
            throw new NullPointerException("callback is null");
        } else if (this.b == null) {
            return 501;
        } else {
            synchronized (this.d) {
                if (!this.f.containsKey(dLBasePluginCallBack.getClass().getName())) {
                    this.f.put(dLBasePluginCallBack.getClass().getName(), dLBasePluginCallBack);
                }
            }
            return this.b.handleAddOuterCallBack(new b(dLBasePluginCallBack));
        }
    }

    public int b(DLBasePluginCallBack dLBasePluginCallBack) {
        if (dLBasePluginCallBack == null) {
            throw new NullPointerException("callback is null");
        } else if (this.b == null) {
            return 501;
        } else {
            synchronized (this.d) {
                if (this.f.containsKey(dLBasePluginCallBack.getClass().getName())) {
                    this.f.remove(dLBasePluginCallBack.getClass().getName());
                }
            }
            return this.b.handleRemoveOuterCallBack(new b(dLBasePluginCallBack));
        }
    }

    public int a(String str, DLBasePluginCallBack dLBasePluginCallBack) {
        if (TextUtils.isEmpty(str)) {
            throw new NullPointerException("destPkgName should not be null!");
        } else if (dLBasePluginCallBack == null) {
            throw new NullPointerException("callback is null");
        } else {
            DLBaseIpcOperator dLBaseIpcOperator = (DLBaseIpcOperator) this.e.get(str);
            if (dLBaseIpcOperator == null) {
                return 501;
            }
            synchronized (this.d) {
                if (!this.f.containsKey(dLBasePluginCallBack.getClass().getName())) {
                    this.f.put(dLBasePluginCallBack.getClass().getName(), dLBasePluginCallBack);
                }
            }
            return dLBaseIpcOperator.handleAddOuterCallBack(new b(dLBasePluginCallBack));
        }
    }

    public int b(String str, DLBasePluginCallBack dLBasePluginCallBack) {
        if (TextUtils.isEmpty(str)) {
            throw new NullPointerException("pkgName should not be null!");
        } else if (dLBasePluginCallBack == null) {
            throw new NullPointerException("callback is null");
        } else {
            DLBaseIpcOperator dLBaseIpcOperator = (DLBaseIpcOperator) this.e.get(str);
            if (dLBaseIpcOperator == null) {
                return 501;
            }
            synchronized (this.d) {
                if (this.f.containsKey(dLBasePluginCallBack.getClass().getName())) {
                    this.f.remove(dLBasePluginCallBack.getClass().getName());
                }
            }
            return dLBaseIpcOperator.handleRemoveOuterCallBack(new b(dLBasePluginCallBack));
        }
    }

    public DLBasePluginCallBack a(String str) {
        return (DLBasePluginCallBack) this.f.get(str);
    }

    public void b(String str) {
        if (!TextUtils.isEmpty(str)) {
            synchronized (this.c) {
                this.e.remove(str);
            }
            synchronized (this.d) {
                for (Entry entry : this.f.entrySet()) {
                    DLBasePluginCallBack dLBasePluginCallBack = (DLBasePluginCallBack) entry.getValue();
                    String str2 = (String) entry.getKey();
                    if (dLBasePluginCallBack.getClass().getPackage().getName().equals(str)) {
                        this.f.remove(str2);
                    }
                }
            }
        }
    }
}
