package com.dynamicload.internal;

import android.app.Application;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.ServiceInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.os.Bundle;
import android.os.Handler;
import com.dynamicload.Lib.DLConstants;
import com.dynamicload.Lib.DLException;
import com.dynamicload.Lib.DLPluginManager.OnApkLoadListener;
import com.dynamicload.Lib.DLPluginPackage;
import com.dynamicload.c;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import dalvik.system.DexClassLoader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Semaphore;

/* compiled from: DLApkLoader */
public class b {
    final Semaphore a = new Semaphore(0);
    File b;
    File c;
    private final Context d;
    private a e = new a();
    private a f = this.e;
    private byte[] g = new byte[0];
    private ArrayList<a> h = new ArrayList();
    private ArrayList<a> i = new ArrayList();
    private ArrayList<a> j = new ArrayList();
    private Handler k;
    private Thread l = new Thread(new Runnable(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public void run() {
            while (true) {
                synchronized (this.a.g) {
                    a b = this.a.f;
                    while (b.i != null) {
                        b = b.i;
                        this.a.h.add(b);
                    }
                    this.a.f = b;
                }
                Iterator it = this.a.h.iterator();
                while (it.hasNext()) {
                    this.a.c((a) it.next());
                    it.remove();
                }
                it = this.a.i.iterator();
                while (it.hasNext()) {
                    this.a.b((a) it.next());
                    it.remove();
                }
                it = this.a.j.iterator();
                while (it.hasNext()) {
                    this.a.a((a) it.next());
                    it.remove();
                }
                try {
                    this.a.a.acquire(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }, "DLApkLoader");

    /* compiled from: DLApkLoader */
    private static class a {
        public DLPluginPackage a;
        public ArrayList<OnApkLoadListener> b;
        public int c;
        public Throwable d;
        public int e;
        public File f;
        public File g;
        public a h;
        public a i;

        private a() {
            this.b = new ArrayList();
            this.c = 100;
        }
    }

    public b(Context context) {
        this.d = context;
        this.k = new Handler(this.d.getMainLooper());
        a(context);
        this.l.setDaemon(true);
        this.l.setPriority(1);
    }

    public void a() {
        this.l.start();
    }

    private void a(Context context) {
        this.b = context.getDir(DLConstants.APK_CACHE_DIR, 0);
        this.c = context.getDir(DLConstants.APK_LIB_DIR, 0);
        if (!this.b.isDirectory()) {
            this.b.delete();
            this.b.mkdirs();
        }
        if (!this.c.isDirectory()) {
            this.c.delete();
            this.c.mkdirs();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(final java.lang.String r5, final com.dynamicload.Lib.DLPluginPackage r6, final com.dynamicload.Lib.DLPluginManager.OnApkLoadListener r7) {
        /*
        r4 = this;
        r0 = "testdl";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "begin load apk [";
        r1 = r1.append(r2);
        r1 = r1.append(r5);
        r2 = "]";
        r1 = r1.append(r2);
        r1 = r1.toString();
        com.qq.reader.common.monitor.debug.c.a(r0, r1);
        if (r7 == 0) goto L_0x0071;
    L_0x0023:
        r0 = r7.getNotifyHandler();
        if (r0 != 0) goto L_0x0032;
    L_0x0029:
        r0 = new android.os.Handler;
        r1 = android.os.Looper.getMainLooper();
        r0.<init>(r1);
    L_0x0032:
        if (r6 != 0) goto L_0x003d;
    L_0x0034:
        r1 = new com.dynamicload.internal.b$1;
        r1.<init>(r4, r7, r5);
        r0.post(r1);
    L_0x003c:
        return;
    L_0x003d:
        r1 = r6.isBanned;
        if (r1 == 0) goto L_0x004a;
    L_0x0041:
        r1 = new com.dynamicload.internal.b$2;
        r1.<init>(r4, r7, r5);
        r0.post(r1);
        goto L_0x003c;
    L_0x004a:
        r1 = r4.b;
        r1 = r1.isDirectory();
        if (r1 == 0) goto L_0x005a;
    L_0x0052:
        r1 = r4.c;
        r1 = r1.isDirectory();
        if (r1 != 0) goto L_0x0062;
    L_0x005a:
        r1 = new com.dynamicload.internal.b$3;
        r1.<init>(r4, r7, r6);
        r0.post(r1);
    L_0x0062:
        r1 = r6.isMount();
        if (r1 == 0) goto L_0x0071;
    L_0x0068:
        r1 = new com.dynamicload.internal.b$4;
        r1.<init>(r4, r7, r6);
        r0.post(r1);
        goto L_0x003c;
    L_0x0071:
        if (r6 != 0) goto L_0x007a;
    L_0x0073:
        r0 = "尝试加载非法包";
        com.dynamicload.c.d(r0);
        goto L_0x003c;
    L_0x007a:
        r1 = r4.g;
        monitor-enter(r1);
        r0 = r4.e;	 Catch:{ all -> 0x0098 }
    L_0x007f:
        r2 = r0.i;	 Catch:{ all -> 0x0098 }
        if (r2 == 0) goto L_0x009b;
    L_0x0083:
        r0 = r0.i;	 Catch:{ all -> 0x0098 }
        r2 = r0.a;	 Catch:{ all -> 0x0098 }
        if (r2 != r6) goto L_0x007f;
    L_0x0089:
        r2 = r0.b;	 Catch:{ all -> 0x0098 }
        r2 = r2.contains(r7);	 Catch:{ all -> 0x0098 }
        if (r2 != 0) goto L_0x0096;
    L_0x0091:
        r0 = r0.b;	 Catch:{ all -> 0x0098 }
        r0.add(r7);	 Catch:{ all -> 0x0098 }
    L_0x0096:
        monitor-exit(r1);	 Catch:{ all -> 0x0098 }
        goto L_0x003c;
    L_0x0098:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0098 }
        throw r0;
    L_0x009b:
        monitor-exit(r1);	 Catch:{ all -> 0x0098 }
        r1 = new com.dynamicload.internal.b$a;
        r0 = 0;
        r1.<init>();
        r1.a = r6;
        if (r7 == 0) goto L_0x00ab;
    L_0x00a6:
        r0 = r1.b;
        r0.add(r7);
    L_0x00ab:
        r0 = new java.io.File;
        r2 = r4.b;
        r3 = r6.apkName;
        r0.<init>(r2, r3);
        r1.f = r0;
        r0 = new java.io.File;
        r2 = r4.c;
        r3 = r6.apkName;
        r0.<init>(r2, r3);
        r1.g = r0;
        r0 = r1.g;
        r0.mkdirs();
        r0 = r1.f;
        r0.mkdirs();
        r2 = r4.g;
        monitor-enter(r2);
        r0 = r4.f;	 Catch:{ all -> 0x00e4 }
    L_0x00d0:
        r3 = r0.i;	 Catch:{ all -> 0x00e4 }
        if (r3 == 0) goto L_0x00d7;
    L_0x00d4:
        r0 = r0.i;	 Catch:{ all -> 0x00e4 }
        goto L_0x00d0;
    L_0x00d7:
        r0.i = r1;	 Catch:{ all -> 0x00e4 }
        r1.h = r0;	 Catch:{ all -> 0x00e4 }
        monitor-exit(r2);	 Catch:{ all -> 0x00e4 }
        r0 = r4.a;
        r1 = 1;
        r0.release(r1);
        goto L_0x003c;
    L_0x00e4:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x00e4 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.dynamicload.internal.b.a(java.lang.String, com.dynamicload.Lib.DLPluginPackage, com.dynamicload.Lib.DLPluginManager$OnApkLoadListener):void");
    }

    private void a(a aVar) {
        DLPluginPackage dLPluginPackage = aVar.a;
        if (this.b.exists() && this.c.exists()) {
            Closeable closeable = null;
            try {
                closeable = this.d.getAssets().open(DLConstants.PLUGIN + File.separator + dLPluginPackage.apkName);
                c.a(closeable, dLPluginPackage.apkName, aVar.f, aVar.g);
                this.h.add(aVar);
                this.a.release(1);
            } catch (Throwable e) {
                aVar.d = e;
                aVar.e = e.errno;
                c.d(aVar.a.apkName + " export fail ");
                d(aVar);
            } catch (Throwable e2) {
                aVar.d = e2;
                aVar.e = DLConstants.LOAD_ERR_IO_FAIL;
                c.d(aVar.a.apkName + " export fail IO Exception");
                d(aVar);
            } finally {
                c.a(closeable);
            }
            return;
        }
        aVar.e = DLConstants.LOAD_ERR_DIR_NOT_EXIST;
        c.d(aVar.a.apkName + " export fail dir is not exist");
        d(aVar);
    }

    private void b(a aVar) {
        Closeable fileInputStream;
        Object e;
        String str = aVar.a.apkName;
        File file = new File(com.qq.reader.common.c.a.aJ, str);
        c.a(aVar.a.apkName + " debug loading from file");
        if (file.exists()) {
            try {
                fileInputStream = new FileInputStream(file);
                try {
                    c.a(fileInputStream, str, aVar.f, aVar.g);
                    this.h.add(aVar);
                    this.a.release(1);
                    file.delete();
                    return;
                } catch (Exception e2) {
                    e = e2;
                }
            } catch (Exception e3) {
                e = e3;
                fileInputStream = null;
                c.c("tryDownloadApk Exception e= " + e);
                c.a(fileInputStream);
                c.a(aVar.a.apkName + " download fail try loading from assert");
                aVar.c = 102;
                this.j.add(aVar);
            }
        }
        c.a(aVar.a.apkName + " download fail try loading from assert");
        aVar.c = 102;
        this.j.add(aVar);
    }

    private void c(a aVar) {
        DLPluginPackage dLPluginPackage = aVar.a;
        c.a(dLPluginPackage.apkName + " mount begin");
        if (this.b.exists() && this.c.exists()) {
            try {
                PackageInfo a = c.a(this.d, dLPluginPackage.apkName, aVar.f, aVar.g, dLPluginPackage);
                if (a != null) {
                    c.a(dLPluginPackage.apkName + " mount success pre");
                    a(aVar, a(a, aVar.f.getAbsolutePath(), aVar.g.getAbsolutePath(), dLPluginPackage));
                    c.a(dLPluginPackage.apkName + " mount success");
                    return;
                }
            } catch (Throwable e) {
                c.d("tryMountApk verifyApk e= " + e);
                aVar.e = e.errno;
                aVar.d = e;
            } catch (Throwable e2) {
                c.d("tryMountApk verifyApk ex= " + e2);
                aVar.d = e2;
            }
            aVar.f.delete();
            aVar.g.delete();
            if (aVar.c == 100) {
                aVar.c = 101;
                c.a(aVar.a.apkName + " mount fail try loading from net");
                this.i.add(aVar);
                return;
            }
            c.a(aVar.a.apkName + " mount fail errno " + aVar.e + " exception " + aVar.d);
            d(aVar);
            return;
        }
        c.a(aVar.a.apkName + " mount fail dir is not exist");
        aVar.e = DLConstants.LOAD_ERR_IO_FAIL;
        d(aVar);
    }

    private void d(final a aVar) {
        synchronized (this.g) {
            aVar.h.i = aVar.i;
            if (aVar.i != null) {
                aVar.i.h = aVar.h;
            }
            if (aVar == this.f) {
                this.f = aVar.h;
            }
        }
        aVar.a.unmountApk();
        ArrayList arrayList = aVar.b;
        final String str = aVar.a.apkName;
        com.qq.reader.common.monitor.debug.c.e(DLConstants.TAG, "can not load pluginApk [" + str + "] errno " + aVar.e + "\n PluginDump: \n" + b());
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                final OnApkLoadListener onApkLoadListener = (OnApkLoadListener) it.next();
                if (onApkLoadListener != null) {
                    Handler notifyHandler = onApkLoadListener.getNotifyHandler();
                    if (notifyHandler == null) {
                        notifyHandler = this.k;
                    }
                    notifyHandler.post(new Runnable(this) {
                        final /* synthetic */ b d;

                        public void run() {
                            onApkLoadListener.onLoadError(str, aVar.e, aVar.d);
                        }
                    });
                }
            }
        }
    }

    private void a(a aVar, Application application) {
        synchronized (this.g) {
            aVar.h.i = aVar.i;
            if (aVar.i != null) {
                aVar.i.h = aVar.h;
            }
            if (aVar == this.f) {
                this.f = aVar.h;
            }
        }
        final ArrayList arrayList = aVar.b;
        final String str = aVar.a.apkName;
        final Application application2 = application;
        final a aVar2 = aVar;
        this.k.post(new Runnable(this) {
            final /* synthetic */ b e;

            public void run() {
                try {
                    application2.onCreate();
                    if (arrayList != null) {
                        Iterator it = arrayList.iterator();
                        while (it.hasNext()) {
                            final OnApkLoadListener onApkLoadListener = (OnApkLoadListener) it.next();
                            if (onApkLoadListener != null) {
                                Handler notifyHandler = onApkLoadListener.getNotifyHandler();
                                if (notifyHandler == null) {
                                    notifyHandler = this.e.k;
                                }
                                notifyHandler.post(new Runnable(this) {
                                    final /* synthetic */ AnonymousClass7 b;

                                    public void run() {
                                        onApkLoadListener.onLoadSuccess(str, aVar2.a);
                                    }
                                });
                            }
                        }
                    }
                } catch (Throwable th) {
                    aVar2.e = DLConstants.LOAD_ERR_INIT_FAIL;
                    aVar2.d = th;
                    this.e.d(aVar2);
                }
            }
        });
    }

    private Application a(PackageInfo packageInfo, String str, String str2, DLPluginPackage dLPluginPackage) {
        Application a;
        synchronized (dLPluginPackage) {
            DexClassLoader a2 = a(str, str2);
            Resources a3 = a(b(str));
            dLPluginPackage.mountApk(a2, a3, packageInfo);
            ServiceInfo[] serviceInfoArr = packageInfo.services;
            ActivityInfo[] activityInfoArr = packageInfo.receivers;
            Bundle bundle = packageInfo.applicationInfo.metaData;
            Theme newTheme = a3.newTheme();
            if (packageInfo.applicationInfo.theme > 0) {
                newTheme.applyStyle(packageInfo.applicationInfo.theme, true);
            } else {
                newTheme.applyStyle(a("com.android.internal.R.style.Theme"), true);
            }
            a = a(a2, dLPluginPackage, newTheme);
        }
        return a;
    }

    private int a(String str) {
        int i = -1;
        try {
            String substring = str.substring(0, str.indexOf(".R.") + 2);
            int lastIndexOf = str.lastIndexOf(".");
            String substring2 = str.substring(lastIndexOf + 1, str.length());
            String substring3 = str.substring(0, lastIndexOf);
            i = Class.forName(substring + "$" + substring3.substring(substring3.lastIndexOf(".") + 1, substring3.length())).getDeclaredField(substring2).getInt(null);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return i;
    }

    private Application a(DexClassLoader dexClassLoader, DLPluginPackage dLPluginPackage, Theme theme) {
        String str;
        String str2 = dLPluginPackage.packageInfo.applicationInfo.className;
        if (str2 == null) {
            str = "android.app.Application";
        } else {
            str = str2;
        }
        try {
            Context context = (Application) dexClassLoader.loadClass(str).newInstance();
            c.a(context, new j(this.d, dLPluginPackage, theme));
            dLPluginPackage.application = context;
            return context;
        } catch (Exception e) {
            throw new DLException("Unable to instantiate application " + str + ": " + e.toString());
        }
    }

    private DexClassLoader a(String str, String str2) {
        return new DexClassLoader(str, this.d.getDir(ShareConstants.DEX_PATH, 0).getAbsolutePath(), str2, this.d.getClassLoader());
    }

    private AssetManager b(String str) {
        try {
            AssetManager assetManager = (AssetManager) AssetManager.class.newInstance();
            assetManager.getClass().getMethod("addAssetPath", new Class[]{String.class}).invoke(assetManager, new Object[]{str});
            return assetManager;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Resources a(AssetManager assetManager) {
        Resources resources = this.d.getResources();
        return new k(assetManager, resources.getDisplayMetrics(), resources.getConfiguration());
    }

    public String b() {
        String stringBuilder;
        synchronized (this.g) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("loading apk : \n");
            a aVar = this.e;
            while (aVar.i != null) {
                aVar = aVar.i;
                DLPluginPackage dLPluginPackage = aVar.a;
                stringBuilder2.append("apk name: " + dLPluginPackage.apkName + "\n");
                stringBuilder2.append("package name: " + dLPluginPackage.packageName + "\n");
                stringBuilder2.append("statues: " + aVar.c + "\n");
                stringBuilder2.append("errno: " + aVar.e + "\n");
            }
            stringBuilder2.append("mount queue: \n");
            Iterator it = this.h.iterator();
            while (it.hasNext()) {
                aVar = (a) it.next();
                DLPluginPackage dLPluginPackage2 = aVar.a;
                stringBuilder2.append("apk name: " + dLPluginPackage2.apkName + "\n");
                stringBuilder2.append("package name: " + dLPluginPackage2.packageName + "\n");
                stringBuilder2.append("file : " + aVar.f.getPath() + "\n");
                stringBuilder2.append("so : " + aVar.g.getPath() + "\n");
            }
            stringBuilder2.append("net queue: \n");
            it = this.i.iterator();
            while (it.hasNext()) {
                aVar = (a) it.next();
                dLPluginPackage2 = aVar.a;
                stringBuilder2.append("apk name: " + dLPluginPackage2.apkName + "\n");
                stringBuilder2.append("package name: " + dLPluginPackage2.packageName + "\n");
                stringBuilder2.append("file : " + aVar.f.getPath() + "\n");
                stringBuilder2.append("so : " + aVar.g.getPath() + "\n");
            }
            stringBuilder2.append("assets queue: \n");
            it = this.j.iterator();
            while (it.hasNext()) {
                aVar = (a) it.next();
                dLPluginPackage2 = aVar.a;
                stringBuilder2.append("apk name: " + dLPluginPackage2.apkName + "\n");
                stringBuilder2.append("package name: " + dLPluginPackage2.packageName + "\n");
                stringBuilder2.append("file : " + aVar.f.getPath() + "\n");
                stringBuilder2.append("so : " + aVar.g.getPath() + "\n");
            }
            stringBuilder = stringBuilder2.toString();
        }
        return stringBuilder;
    }
}
