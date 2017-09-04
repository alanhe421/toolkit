package com.dynamicload.Lib;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.ActivityInfo;
import android.content.pm.ServiceInfo;
import android.content.res.Resources.NotFoundException;
import android.content.res.Resources.Theme;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.dynamicload.internal.DLAbsPluginBroadCastReceiver;
import com.dynamicload.internal.DLIntent;
import com.dynamicload.internal.PackageConfig;
import com.dynamicload.internal.PackageConfigList;
import com.dynamicload.internal.PluginActivityWrapper;
import com.dynamicload.internal.a;
import com.dynamicload.internal.c;
import com.dynamicload.internal.d;
import com.dynamicload.internal.e;
import com.dynamicload.internal.i;
import com.qq.reader.ReaderApplication;
import java.util.List;
import java.util.Map.Entry;

public class DLPluginManager {
    private static DLPluginManager a;
    private Context b;
    private c c = new c(this.b);
    private d d = new d(this.c);
    private a e = new a(this.c);
    private i f = new i(this.c);
    private e g = new e(this.c);
    private DLAbsPluginBroadCastReceiver h = new DLAbsPluginBroadCastReceiver();

    public interface OnApkLoadListener {
        Handler getNotifyHandler();

        void onLoadError(String str, int i, Throwable th);

        void onLoadSuccess(String str, DLPluginPackage dLPluginPackage);
    }

    public interface HostBroadcastReceiver {
        void onReceive(int i, Bundle bundle);
    }

    public View findViewByID(View view, int i) {
        if (i == view.getId()) {
            return view;
        }
        if ((view instanceof ViewGroup) && !view.getClass().getName().startsWith(DLConstants.EXPORT_PACKAGE_VIEW)) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View findViewByID = findViewByID(viewGroup.getChildAt(i2), i);
                if (findViewByID != null) {
                    return findViewByID;
                }
            }
        }
        return null;
    }

    private DLPluginManager(Context context) {
        this.b = context.getApplicationContext();
    }

    public static DLPluginManager getInstance(Context context) {
        if (a == null) {
            synchronized (DLPluginManager.class) {
                if (a == null) {
                    a = new DLPluginManager(context);
                }
            }
        }
        return a;
    }

    public static DLPluginManager getInstance() {
        return getInstance(ReaderApplication.getApplicationImp());
    }

    public void asyncLoadApk(String str, OnApkLoadListener onApkLoadListener) {
        this.c.a(str, onApkLoadListener);
    }

    public void asyncLoadPackage(String str, OnApkLoadListener onApkLoadListener) {
        this.c.a(com.dynamicload.a.a(str), onApkLoadListener);
    }

    public void freeApk(DLPluginPackage dLPluginPackage) {
        if (dLPluginPackage != null) {
            this.c.a(dLPluginPackage);
            this.e.a(dLPluginPackage);
            this.f.a(dLPluginPackage.packageName);
            this.d.b(dLPluginPackage.packageName);
            this.c.b(dLPluginPackage);
        }
    }

    public void regHostBroadcastMsg(HostBroadcastReceiver hostBroadcastReceiver) {
        this.c.a(hostBroadcastReceiver);
    }

    public void unregHostBroadcastMsg(HostBroadcastReceiver hostBroadcastReceiver) {
        this.c.b(hostBroadcastReceiver);
    }

    public void broadcastForeProcessKilledEvent() {
        this.c.c();
    }

    public DLPluginPackage getPackage(String str) {
        return this.c.a(str);
    }

    public List<DLPluginPackage> getLoadedPluginPackages() {
        return this.c.a();
    }

    public int startActivity(Context context, Intent intent) {
        return startActivityForResult(context, intent, -1);
    }

    public int startActivityForResult(Context context, Intent intent, int i) {
        ComponentName component = intent.getComponent();
        if (component == null) {
            this.e.a(context, intent, i);
            return 201;
        }
        String packageName = component.getPackageName();
        CharSequence className = component.getClassName();
        DLPluginPackage dLPluginPackage = getPackage(packageName);
        com.dynamicload.c.a("startActivityForResult: componentName= " + component + " pluginPackage= " + dLPluginPackage);
        if (dLPluginPackage == null || !dLPluginPackage.isMount() || TextUtils.isEmpty(className)) {
            this.e.a(context, intent, i);
            return 201;
        }
        ActivityInfo[] activityInfoArr = dLPluginPackage.packageInfo.activities;
        if (activityInfoArr != null && activityInfoArr.length > 0) {
            for (ActivityInfo activityInfo : activityInfoArr) {
                if (activityInfo.name.equals(className)) {
                    return this.e.a(context, new DLIntent(intent), i);
                }
            }
        }
        com.qq.reader.common.monitor.debug.c.e(DLConstants.TAG, "can not start pluginActivity [" + component + "]\n PluginDump: \n" + dump());
        throw new DLException("can't found this activity in plugins, have you declare in manifest? ComponentName: [" + component + "]");
    }

    public void pushActivity(IDLPluginActivity iDLPluginActivity) {
        this.e.a(iDLPluginActivity);
    }

    public void popActivity(IDLPluginActivity iDLPluginActivity) {
        this.e.b(iDLPluginActivity);
    }

    public String dump() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("dump config >>>>>>>>>>>>>>>>>>>>>>>>>\n");
        PackageConfigList a = com.dynamicload.a.a();
        if (a == null) {
            stringBuilder.append("plugin framework is not inited");
            return stringBuilder.toString();
        }
        for (Entry value : a.configPlugins.entrySet()) {
            PackageConfig packageConfig = (PackageConfig) value.getValue();
            stringBuilder.append("apk name: " + packageConfig.apkName + "\n");
            stringBuilder.append("package name: " + packageConfig.packageName + "\n");
            stringBuilder.append("apk size: " + packageConfig.apkSize + "\n");
            stringBuilder.append("version: " + packageConfig.apkVersion + "\n");
            stringBuilder.append("preload: " + packageConfig.preLoad + "\n");
        }
        stringBuilder.append("<<<<<<<<<<<<<<<<<<<<<<<<dump config\n");
        stringBuilder.append(this.c.d());
        return stringBuilder.toString();
    }

    public DLBaseFragmenView getDLBaseFragmenViewSafely(Context context, String str, String str2, OnApkLoadListener onApkLoadListener) {
        DLBaseFragmenView dLBaseFragmenView = getDLBaseFragmenView(context, str, str2);
        if (dLBaseFragmenView == null) {
            getInstance().asyncLoadPackage(str, onApkLoadListener);
        }
        return dLBaseFragmenView;
    }

    public DLBaseFragmenView getDLBaseFragmenView(Context context, String str, String str2) {
        DLPluginPackage dLPluginPackage = getPackage(str);
        if (dLPluginPackage == null) {
            com.dynamicload.c.d("package is not exist when export plugin view");
            return null;
        }
        try {
            ClassLoader classLoader = dLPluginPackage.classLoader;
            PluginActivityWrapper pluginActivityWrapper = (PluginActivityWrapper) classLoader.loadClass(PluginActivityWrapper.class.getName()).getConstructor(new Class[]{Context.class, DLPluginPackage.class, Context.class, Theme.class}).newInstance(new Object[]{dLPluginPackage.application, dLPluginPackage, context, dLPluginPackage.application.getTheme()});
            return (DLBaseFragmenView) classLoader.loadClass(str2).getConstructor(new Class[]{Context.class}).newInstance(new Object[]{pluginActivityWrapper});
        } catch (Exception e) {
            com.dynamicload.c.d("export plugin view fail");
            e.printStackTrace();
            return null;
        }
    }

    public Class<?> getPluginClass(String str, String str2) {
        Class<?> cls = null;
        DLPluginPackage dLPluginPackage = getPackage(str);
        if (dLPluginPackage == null) {
            com.dynamicload.c.d("package is not exist when get object [" + str2 + "]");
        } else {
            try {
                cls = dLPluginPackage.classLoader.loadClass(str2);
            } catch (Exception e) {
                com.dynamicload.c.d(" get object [" + str2 + "] fail");
                e.printStackTrace();
            }
        }
        return cls;
    }

    public PluginActivityWrapper clonePluginContext(Context context, String str) {
        DLPluginPackage dLPluginPackage = getPackage(str);
        if (dLPluginPackage != null) {
            return a(context, dLPluginPackage);
        }
        com.dynamicload.c.d("package is not exist when export plugin view");
        return null;
    }

    private PluginActivityWrapper a(Context context, DLPluginPackage dLPluginPackage) {
        try {
            return (PluginActivityWrapper) dLPluginPackage.classLoader.loadClass(PluginActivityWrapper.class.getName()).getConstructor(new Class[]{Context.class, DLPluginPackage.class, Context.class, Theme.class}).newInstance(new Object[]{dLPluginPackage.application, dLPluginPackage, context, dLPluginPackage.application.getTheme()});
        } catch (Exception e) {
            com.dynamicload.c.d("export plugin view fail");
            e.printStackTrace();
            return null;
        }
    }

    public void startActivitySafty(final Context context, final Intent intent) {
        String str = intent.getPackage();
        if (str == null && intent.getComponent() != null) {
            str = intent.getComponent().getPackageName();
        }
        getInstance().asyncLoadPackage(str, new OnApkLoadListener(this) {
            final /* synthetic */ DLPluginManager c;

            public void onLoadSuccess(String str, DLPluginPackage dLPluginPackage) {
                DLPluginManager.getInstance().startActivity(context, intent);
            }

            public void onLoadError(String str, int i, Throwable th) {
            }

            public Handler getNotifyHandler() {
                return null;
            }
        });
    }

    public void cleanPluginApk() {
        this.c.b();
    }

    public DLBasePluginService getService(String str) {
        return this.f.b(str);
    }

    public ComponentName startService(Context context, Intent intent) {
        ComponentName component = intent.getComponent();
        String packageName = component.getPackageName();
        CharSequence className = component.getClassName();
        DLPluginPackage dLPluginPackage = getPackage(packageName);
        com.dynamicload.c.a("startService: componentName= " + component + " pluginPackage= " + dLPluginPackage);
        if (dLPluginPackage == null || !dLPluginPackage.isMount() || TextUtils.isEmpty(className)) {
            return context.startService(intent);
        }
        ServiceInfo[] serviceInfoArr = dLPluginPackage.packageInfo.services;
        if (serviceInfoArr != null && serviceInfoArr.length > 0) {
            for (ServiceInfo serviceInfo : serviceInfoArr) {
                if (serviceInfo.name.equals(className)) {
                    return this.f.a(context, new DLIntent(intent));
                }
            }
        }
        return null;
    }

    public boolean stopService(Context context, Intent intent) {
        ComponentName component = intent.getComponent();
        String packageName = component.getPackageName();
        CharSequence className = component.getClassName();
        DLPluginPackage dLPluginPackage = getPackage(packageName);
        com.dynamicload.c.a("stopService: componentName= " + component + " pluginPackage= " + dLPluginPackage);
        if (dLPluginPackage == null || !dLPluginPackage.isMount() || TextUtils.isEmpty(className)) {
            return context.stopService(intent);
        }
        ServiceInfo[] serviceInfoArr = dLPluginPackage.packageInfo.services;
        if (serviceInfoArr == null || serviceInfoArr.length <= 0) {
            return false;
        }
        for (ServiceInfo serviceInfo : serviceInfoArr) {
            if (serviceInfo.name.equals(className)) {
                return this.f.b(context, new DLIntent(intent));
            }
        }
        return false;
    }

    public boolean bindService(Context context, Intent intent, ServiceConnection serviceConnection, int i) {
        ComponentName component = intent.getComponent();
        String packageName = component.getPackageName();
        CharSequence className = component.getClassName();
        DLPluginPackage dLPluginPackage = getPackage(packageName);
        com.dynamicload.c.a("bindService: componentName= " + component + " pluginPackage= " + dLPluginPackage);
        if (dLPluginPackage == null || !dLPluginPackage.isMount() || TextUtils.isEmpty(className)) {
            throw new DLException("you can only bind local Service ComponentName: [" + component + "]");
        }
        ServiceInfo[] serviceInfoArr = dLPluginPackage.packageInfo.services;
        if (serviceInfoArr != null && serviceInfoArr.length > 0) {
            for (ServiceInfo serviceInfo : serviceInfoArr) {
                if (serviceInfo.name.equals(className)) {
                    return this.f.a(context, new DLIntent(intent), serviceConnection);
                }
            }
        }
        com.qq.reader.common.monitor.debug.c.e(DLConstants.TAG, "can not bindService [" + component + "]\n PluginDump: \n" + dump());
        throw new DLException("can't found this service in plugins, have you declare in manifest? ComponentName: [" + component + "]");
    }

    public boolean unbindService(Context context, ServiceConnection serviceConnection) {
        com.dynamicload.c.a("unbindService: conn= " + serviceConnection);
        return this.f.a(context, serviceConnection);
    }

    public void registerReceiver(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        android.support.v4.content.d.a(context).a(broadcastReceiver, intentFilter);
        context.getApplicationContext().registerReceiver(this.h, intentFilter);
    }

    public void unregisterReceiver(Context context, BroadcastReceiver broadcastReceiver) {
        android.support.v4.content.d.a(context).a(broadcastReceiver);
    }

    public void sendBroadcast(Context context, Intent intent) {
        DLPluginPackage dLPluginPackage = getPackage(context.getPackageName());
        if (dLPluginPackage != null && dLPluginPackage.isMount()) {
            intent.setExtrasClassLoader(dLPluginPackage.classLoader);
        }
        android.support.v4.content.d.a(context).a(intent);
    }

    public void notifyPluginNotification(Context context, int i, String str, String str2, PendingIntent pendingIntent, int i2) {
        this.g.a(context, i, str, str2, pendingIntent, i2);
    }

    public void cancelPluginNotifitication(Context context, int i) {
        this.g.a(context, i);
    }

    public PendingIntent getActivity(Context context, int i, Intent intent, int i2) {
        return this.g.a(context, i, new DLIntent(intent), i2);
    }

    public PendingIntent getService(Context context, int i, Intent intent, int i2) {
        return this.g.b(context, i, new DLIntent(intent), i2);
    }

    public PendingIntent getBroadCast(Context context, int i, Intent intent, int i2) {
        return this.g.c(context, i, new DLIntent(intent), i2);
    }

    public void setHostIpcOperator(DLBaseIpcOperator dLBaseIpcOperator) {
        this.d.a(dLBaseIpcOperator);
    }

    public int sendSyncRequestToHandleByHost(int i, Bundle bundle, Bundle bundle2) {
        return this.d.a(i, bundle, bundle2);
    }

    public int addCallBackToHandleByHost(DLBasePluginCallBack dLBasePluginCallBack) {
        return this.d.a(dLBasePluginCallBack);
    }

    public int removeCallBackToHandleByHost(DLBasePluginCallBack dLBasePluginCallBack) {
        return this.d.b(dLBasePluginCallBack);
    }

    public boolean registerPluginIpcOperator(DLBaseIpcOperator dLBaseIpcOperator) {
        return this.d.b(dLBaseIpcOperator);
    }

    public int sendSyncRequestToHandleByPlugin(String str, int i, Bundle bundle, Bundle bundle2) {
        return this.d.a(str, i, bundle, bundle2);
    }

    public int addCallBackToHandleByPlugin(String str, DLBasePluginCallBack dLBasePluginCallBack) {
        return this.d.a(str, dLBasePluginCallBack);
    }

    public int removeCallBackToHandleByPlugin(String str, DLBasePluginCallBack dLBasePluginCallBack) {
        return this.d.b(str, dLBasePluginCallBack);
    }

    public DLBasePluginCallBack getPluginCallback(String str) {
        return this.d.a(str);
    }

    public static Toast makeText(Context context, CharSequence charSequence, int i) {
        return Toast.makeText(context.getApplicationContext(), charSequence, i);
    }

    public static Toast makeText(Context context, int i, int i2) throws NotFoundException {
        CharSequence charSequence = "";
        DLPluginPackage dLPluginPackage = getInstance(context).getPackage(context.getPackageName());
        if (dLPluginPackage != null && dLPluginPackage.isMount()) {
            charSequence = dLPluginPackage.resources.getString(i);
        }
        return makeText(context, charSequence, i2);
    }

    public static Toast makeView(Context context, View view, int i) {
        Toast toast = new Toast(context.getApplicationContext());
        toast.setView(view);
        toast.setGravity(17, 0, 0);
        toast.setDuration(i);
        return toast;
    }
}
