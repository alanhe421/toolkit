package com.dynamicload.internal;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.dynamicload.Lib.DLConstants;
import com.dynamicload.Lib.DLException;
import com.dynamicload.Lib.DLPluginManager.HostBroadcastReceiver;
import com.dynamicload.Lib.DLPluginManager.OnApkLoadListener;
import com.dynamicload.Lib.DLPluginPackage;
import com.dynamicload.a;
import com.qq.reader.appconfig.b;
import com.qq.reader.common.utils.ab;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;

/* compiled from: DLApkManager */
public class c {
    private b a;
    private final HashMap<String, DLPluginPackage> b = new HashMap();
    private final HashMap<String, DLPluginPackage> c = new HashMap();
    private final List<HostBroadcastReceiver> d = new ArrayList();

    public c(Context context) {
        this.a = new b(context);
        e();
    }

    private void e() {
        PackageConfigList a = a.a();
        if (a == null) {
            throw new DLException("config is not init");
        }
        for (PackageConfig packageConfig : a.configPlugins.values()) {
            DLPluginPackage dLPluginPackage = new DLPluginPackage(packageConfig);
            if (!this.c.containsKey(packageConfig.apkName) && !this.b.containsKey(packageConfig.packageName)) {
                this.c.put(packageConfig.apkName, dLPluginPackage);
                this.b.put(packageConfig.packageName, dLPluginPackage);
                if (packageConfig.preLoad && !dLPluginPackage.isBanned) {
                    this.a.a(dLPluginPackage.apkName, dLPluginPackage, null);
                }
            } else if (b.a) {
                throw new DLException("duplicate apk name or package name");
            }
        }
        this.a.a();
    }

    public DLPluginPackage a(DLPluginPackage dLPluginPackage) {
        dLPluginPackage.unmountApk();
        b(dLPluginPackage.packageName);
        c(dLPluginPackage.packageName);
        return dLPluginPackage;
    }

    public String a(String str, String str2, Class<?> cls) {
        com.dynamicload.c.a("checkPluginIntent: packageName= " + str + " className= " + str2 + " superClass= " + cls);
        if (TextUtils.isEmpty(str)) {
            throw new NullPointerException("disallow null packageName.");
        }
        DLPluginPackage a = a(str);
        if (a == null) {
            throw new DLException("package not found: " + str, 203);
        } else if (a.isMount()) {
            if (Activity.class.isAssignableFrom(cls) && str2 == null) {
                str2 = a.defaultActivity;
            }
            if (TextUtils.isEmpty(str2)) {
                throw new NullPointerException("disallow null className.");
            }
            if (str2.startsWith(".")) {
                str2 = str + str2;
            }
            Class a2 = a(a.classLoader, str2);
            if (a2 == null) {
                throw new DLException("class not found: " + str2, 204);
            } else if (cls.isAssignableFrom(a2)) {
                return str2;
            } else {
                throw new DLException(str2 + " is not assignable from " + cls.getName(), 205);
            }
        } else {
            throw new DLException("package not mount: " + str, 206);
        }
    }

    public List<DLPluginPackage> a() {
        List arrayList = new ArrayList();
        for (Entry value : this.b.entrySet()) {
            DLPluginPackage dLPluginPackage = (DLPluginPackage) value.getValue();
            if (dLPluginPackage.isMount()) {
                arrayList.add(dLPluginPackage);
            }
        }
        return arrayList;
    }

    public DLPluginPackage a(String str) {
        return (DLPluginPackage) this.b.get(str);
    }

    Class<?> a(ClassLoader classLoader, String str) {
        try {
            return Class.forName(str, true, classLoader);
        } catch (ClassNotFoundException e) {
            throw new DLException("can not load class {" + str + "} are you use compile to build the plugin apk " + "or class name is wrong");
        }
    }

    public void b() {
        File file = this.a.b;
        File file2 = this.a.c;
        if (file != null && file.exists() && file.isDirectory() && this.c != null) {
            for (File file3 : file.listFiles()) {
                if (!file3.isFile() || !this.c.containsKey(file3.getName())) {
                    File file4 = new File(file2, file3.getName());
                    ab.a(file3, true);
                    ab.a(file4, true);
                }
            }
        }
    }

    public void b(DLPluginPackage dLPluginPackage) {
        File file = this.a.b;
        File file2 = this.a.c;
        if (file != null && file.exists() && file.isDirectory()) {
            String str = dLPluginPackage.apkName;
            for (File file3 : file.listFiles()) {
                if (file3.isFile() && file3.getName().equals(str)) {
                    file = new File(file2, file3.getName());
                    ab.a(file3, true);
                    ab.a(file, true);
                    return;
                }
            }
        }
    }

    public void a(HostBroadcastReceiver hostBroadcastReceiver) {
        if (!this.d.contains(hostBroadcastReceiver)) {
            this.d.add(hostBroadcastReceiver);
        }
    }

    public void b(HostBroadcastReceiver hostBroadcastReceiver) {
        if (this.d.contains(hostBroadcastReceiver)) {
            this.d.remove(hostBroadcastReceiver);
        }
    }

    public void c() {
        for (int i = 0; i < this.d.size(); i++) {
            ((HostBroadcastReceiver) this.d.get(i)).onReceive(100, null);
        }
    }

    private void b(String str) {
        Collection hashSet = new HashSet();
        for (int i = 0; i < this.d.size(); i++) {
            HostBroadcastReceiver hostBroadcastReceiver = (HostBroadcastReceiver) this.d.get(i);
            if (str.equals(hostBroadcastReceiver.getClass().getPackage().getName())) {
                hashSet.add(hostBroadcastReceiver);
            }
        }
        if (hashSet.size() > 0) {
            this.d.removeAll(hashSet);
        }
    }

    private void c(String str) {
        for (int i = 0; i < this.d.size(); i++) {
            Bundle bundle = new Bundle();
            bundle.putString(DLConstants.HOST_BROADCAST_EXTRA_PLUGIN_PKG, str);
            ((HostBroadcastReceiver) this.d.get(i)).onReceive(102, bundle);
        }
    }

    public void a(String str, OnApkLoadListener onApkLoadListener) {
        this.a.a(str, (DLPluginPackage) this.c.get(str), onApkLoadListener);
    }

    public synchronized String d() {
        StringBuilder stringBuilder;
        stringBuilder = new StringBuilder();
        stringBuilder.append("name holder >>>>>>>>>>>>>>>>> \n");
        for (Entry value : this.b.entrySet()) {
            DLPluginPackage dLPluginPackage = (DLPluginPackage) value.getValue();
            stringBuilder.append("apk name: " + dLPluginPackage.apkName + "\n");
            stringBuilder.append("package name: " + dLPluginPackage.packageName + "\n");
            stringBuilder.append("apk size: " + dLPluginPackage.apkSize + "\n");
            stringBuilder.append("classLoader: " + dLPluginPackage.classLoader + "\n");
            stringBuilder.append("resource: " + dLPluginPackage.resources + "\n");
        }
        stringBuilder.append(">>>>>>>>>>>>>>>>>  name holder \n");
        stringBuilder.append("package holder >>>>>>>>>>>>>>>>>  \n");
        for (Entry value2 : this.c.entrySet()) {
            dLPluginPackage = (DLPluginPackage) value2.getValue();
            stringBuilder.append("apk name: " + dLPluginPackage.apkName + "\n");
            stringBuilder.append("package name: " + dLPluginPackage.packageName + "\n");
            stringBuilder.append("apk size: " + dLPluginPackage.apkSize + "\n");
            stringBuilder.append("classLoader: " + dLPluginPackage.classLoader + "\n");
            stringBuilder.append("resource: " + dLPluginPackage.resources + "\n");
        }
        stringBuilder.append(">>>>>>>>>>>>>>>>>  package holder \n");
        stringBuilder.append(this.a.b());
        return stringBuilder.toString();
    }
}
