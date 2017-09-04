package com.dynamicload.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.text.TextUtils;
import com.dynamicload.Lib.DLBasePluginService;
import com.dynamicload.Lib.DLException;
import com.dynamicload.Lib.DLPluginPackage;
import com.dynamicload.c;
import java.util.HashMap;
import java.util.Map.Entry;

/* compiled from: DLServiceManager */
public class i {
    private c a;
    private byte[] b = new byte[0];
    private final HashMap<String, DLBasePluginService> c = new HashMap();
    private byte[] d = new byte[0];
    private final HashMap<String, DLBasePluginService> e = new HashMap();
    private final HashMap<ServiceConnection, DLBasePluginService> f = new HashMap();
    private final HashMap<ServiceConnection, Intent> g = new HashMap();

    public i(c cVar) {
        this.a = cVar;
    }

    public void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            synchronized (this.b) {
                for (Entry entry : this.c.entrySet()) {
                    if (((DLBasePluginService) entry.getValue()).getPackageName().equals(str)) {
                        this.c.remove(entry.getKey());
                    }
                }
            }
            synchronized (this.d) {
                for (Entry entry2 : this.f.entrySet()) {
                    DLBasePluginService dLBasePluginService = (DLBasePluginService) entry2.getValue();
                    ServiceConnection serviceConnection = (ServiceConnection) entry2.getKey();
                    if (dLBasePluginService.getPackageName().equals(str)) {
                        this.e.remove(dLBasePluginService.getClass().getName());
                        this.f.remove(serviceConnection);
                        this.g.remove(serviceConnection);
                    }
                }
            }
        }
    }

    public DLBasePluginService b(String str) {
        return (DLBasePluginService) this.c.get(str);
    }

    public ComponentName a(Context context, DLIntent dLIntent) {
        String a = dLIntent.a();
        try {
            String a2 = this.a.a(a, dLIntent.b(), DLBasePluginService.class);
            if (!this.c.containsKey(a2)) {
                Object obj = (DLBasePluginService) this.e.get(a2);
                if (obj == null) {
                    obj = a(context, a2, this.a.a(a));
                }
                if (obj == null) {
                    c.c("createService failed when startPluginService");
                    return null;
                }
                synchronized (this.b) {
                    this.c.put(a2, obj);
                }
            }
            dLIntent.setClass(context.getApplicationContext(), DLAbsPluginService.class);
            c.a("start PluginService context= " + context + " dlIntent= " + dLIntent);
            context.getApplicationContext().startService(dLIntent);
            return new ComponentName(a, a2);
        } catch (Exception e) {
            c.c("checkPluginIntent failed when startPluginService");
            return null;
        }
    }

    public boolean b(Context context, DLIntent dLIntent) {
        try {
            String a = this.a.a(dLIntent.a(), dLIntent.b(), DLBasePluginService.class);
            DLBasePluginService dLBasePluginService = (DLBasePluginService) this.c.get(a);
            if (dLBasePluginService == null) {
                return false;
            }
            if (!this.e.containsKey(a)) {
                dLBasePluginService.onDestroy();
            }
            synchronized (this.b) {
                this.c.remove(a);
            }
            return true;
        } catch (Exception e) {
            c.c("checkPluginIntent failed when stopPluginService");
            return false;
        }
    }

    private DLBasePluginService a(Context context, String str, DLPluginPackage dLPluginPackage) {
        c.a("serviceOnCreate className= " + str + " pluginPackage= " + dLPluginPackage);
        try {
            final Context context2 = (DLBasePluginService) dLPluginPackage.classLoader.loadClass(str).getConstructor(new Class[0]).newInstance(new Object[0]);
            context2.attach(dLPluginPackage);
            c.a(context2, dLPluginPackage.application);
            new Handler(context.getMainLooper()).post(new Runnable(this) {
                final /* synthetic */ i b;

                public void run() {
                    context2.onCreate();
                }
            });
            return context2;
        } catch (Exception e) {
            c.c("start PluginService Exception: e= " + e);
            return null;
        }
    }

    public boolean a(Context context, final DLIntent dLIntent, final ServiceConnection serviceConnection) {
        String a = dLIntent.a();
        String a2 = this.a.a(a, dLIntent.b(), DLBasePluginService.class);
        String packageName = context.getPackageName();
        c.a("bindPluginService: packageName= " + a + " className= " + a2 + " callPkgName= " + packageName);
        if (!a.equals(packageName)) {
            throw new DLException("bindService between plugins is not supported! pkgName= " + a + " callPkgName= " + packageName);
        } else if (serviceConnection == null) {
            throw new DLException("conn should not be null when bindPluginService", 301);
        } else if (this.e.containsKey(a2)) {
            c.c("bindPluginService: service aready binded, please unbind first!");
            return false;
        } else if (this.f.containsKey(serviceConnection)) {
            c.c("bindPluginService: conn aready binded, please unbind first!");
            return false;
        } else {
            DLBasePluginService dLBasePluginService;
            if (this.c.containsKey(a2)) {
                dLBasePluginService = (DLBasePluginService) this.c.get(a2);
            } else {
                dLBasePluginService = a(context, a2, this.a.a(a));
            }
            if (dLBasePluginService == null) {
                c.c("createService failed when bindPluginService");
                return false;
            }
            new Handler(context.getMainLooper()).post(new Runnable(this) {
                final /* synthetic */ i d;

                public void run() {
                    serviceConnection.onServiceConnected(new ComponentName(dLBasePluginService.getClass().getPackage().getName(), dLBasePluginService.getClass().getName()), dLBasePluginService.onBind(dLIntent));
                }
            });
            synchronized (this.d) {
                this.e.put(a2, dLBasePluginService);
                this.f.put(serviceConnection, dLBasePluginService);
                this.g.put(serviceConnection, dLIntent);
            }
            return true;
        }
    }

    public boolean a(Context context, ServiceConnection serviceConnection) {
        if (serviceConnection == null) {
            throw new DLException("conn should not be null when unbindPluginService", 301);
        } else if (!context.getPackageName().equals(serviceConnection.getClass().getPackage().getName())) {
            throw new DLException("unbindService between plugins is not supported! pkgName= " + serviceConnection.getClass().getPackage().getName() + "; callPkgName= " + context.getPackageName());
        } else if (this.f.containsKey(serviceConnection)) {
            final DLBasePluginService dLBasePluginService = (DLBasePluginService) this.f.get(serviceConnection);
            final Intent intent = (Intent) this.g.get(serviceConnection);
            final String name = dLBasePluginService.getClass().getName();
            final String packageName = dLBasePluginService.getPackageName();
            final ServiceConnection serviceConnection2 = serviceConnection;
            new Handler(context.getMainLooper()).post(new Runnable(this) {
                final /* synthetic */ i f;

                public void run() {
                    ComponentName componentName = new ComponentName(packageName, name);
                    dLBasePluginService.onUnbind(intent);
                    serviceConnection2.onServiceDisconnected(componentName);
                    if (!this.f.c.containsValue(dLBasePluginService)) {
                        dLBasePluginService.onDestroy();
                    }
                }
            });
            synchronized (this.d) {
                this.f.remove(serviceConnection);
                this.g.remove(serviceConnection);
                this.e.remove(name);
            }
            return true;
        } else {
            c.c("unbindPluginService: service never binded, please bind first!");
            return false;
        }
    }
}
