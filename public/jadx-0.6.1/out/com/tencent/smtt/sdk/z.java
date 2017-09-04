package com.tencent.smtt.sdk;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.i;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileLock;
import java.util.Properties;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class z {
    public static ThreadLocal<Integer> a = new aa();
    static boolean b = false;
    private static z c = null;
    private static final Lock d = new ReentrantLock();
    private static final Lock e = new ReentrantLock();
    private static Handler f = null;
    private static final Long[][] g;
    private static boolean h = false;

    static {
        r0 = new Long[7][];
        r0[0] = new Long[]{Long.valueOf(25413), Long.valueOf(11460320)};
        r0[1] = new Long[]{Long.valueOf(25436), Long.valueOf(12009376)};
        r0[2] = new Long[]{Long.valueOf(25437), Long.valueOf(11489180)};
        r0[3] = new Long[]{Long.valueOf(25438), Long.valueOf(11489180)};
        r0[4] = new Long[]{Long.valueOf(25439), Long.valueOf(12013472)};
        r0[5] = new Long[]{Long.valueOf(25440), Long.valueOf(11489180)};
        r0[6] = new Long[]{Long.valueOf(25442), Long.valueOf(11489180)};
        g = r0;
    }

    private z() {
    }

    static synchronized z a() {
        z zVar;
        synchronized (z.class) {
            if (c == null) {
                c = new z();
            }
            zVar = c;
        }
        return zVar;
    }

    static File g(Context context) {
        File file = new File(context.getDir("tbs", 0), "core_private");
        return file != null ? (file.isDirectory() || file.mkdir()) ? file : null : null;
    }

    public int a(boolean z, Context context) {
        if (z || ((Integer) a.get()).intValue() <= 0) {
            a.set(Integer.valueOf(c(context)));
        }
        return ((Integer) a.get()).intValue();
    }

    void a(Context context) {
        boolean z = false;
        TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore currentProcessName=" + context.getApplicationInfo().processName);
        TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore currentProcessId=" + Process.myPid());
        StringBuilder append = new StringBuilder().append("TbsInstaller-continueInstallTbsCore currentThreadName=");
        String name = Thread.currentThread().getName();
        TbsLog.i("TbsInstaller", append.append(name).toString());
        FileOutputStream b = i.b(context, true, "tbslock.txt");
        if (b != null) {
            FileLock a = i.a(context, b);
            if (a != null) {
                int f;
                int e;
                int a2;
                int tryLock = d.tryLock();
                if (tryLock == true) {
                    try {
                        f = u.a(context).f();
                        e = u.a(context).e();
                        name = u.a(context).d();
                        tryLock = u.a(context).b();
                        a2 = u.a(context).a();
                    } finally {
                        z = d;
                        z.unlock();
                    }
                } else {
                    tryLock = -1;
                    name = null;
                    e = 0;
                    f = -1;
                    a2 = 0;
                }
                i.a(a, b);
                TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore installStatus=" + f);
                TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore tbsCoreInstallVer=" + e);
                TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore tbsApkPath=" + name);
                TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore copyStatus=" + tryLock);
                TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore tbsCoreCopyVer=" + a2);
                if (TbsShareManager.isThirdPartyApp(context)) {
                    b(context, TbsShareManager.a(context, z));
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putInt("operation", 10001);
                a(context, bundle);
                if (f > -1 && f < 2) {
                    a(context, name, e);
                }
                if (tryLock == 0) {
                    a(context, a2);
                }
            }
        }
    }

    void a(Context context, Bundle bundle) {
    }

    void a(Context context, String str, int i) {
    }

    void a(Context context, boolean z) {
    }

    boolean a(Context context, int i) {
        return false;
    }

    public synchronized boolean a(Context context, Context context2) {
        TbsLog.i("TbsInstaller", "TbsInstaller--quickDexOptForThirdPartyApp");
        if (!h) {
            h = true;
            new ab(this, context2, context).start();
        }
        return true;
    }

    public boolean a(Context context, File[] fileArr) {
        return false;
    }

    File b(Context context, Context context2) {
        File file = new File(context2.getDir("tbs", 0), "core_share");
        return file != null ? (file.isDirectory() || ((context != null && TbsShareManager.isThirdPartyApp(context)) || file.mkdir())) ? file : null : null;
    }

    void b(Context context, int i) {
        TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCoreForThirdPartyApp");
        if (i > 0) {
            int c = c(context);
            if (c != i) {
                Context c2 = TbsShareManager.c(context);
                if (c2 != null) {
                    TbsLog.i("TbsInstaller", "TbsInstaller--quickDexOptForThirdPartyApp hostContext != null");
                    a(context, c2);
                } else if (c <= 0) {
                    TbsLog.i("TbsInstaller", "TbsInstaller--installTbsCoreForThirdPartyApp hostContext == null");
                    QbSdk.a(context, "TbsInstaller::installTbsCoreForThirdPartyApp forceSysWebViewInner #2");
                }
            }
        }
    }

    boolean b(Context context) {
        FileInputStream fileInputStream;
        Throwable th;
        Throwable th2;
        boolean z = false;
        File file = new File(e(context), "tbs.conf");
        if (file != null && file.exists()) {
            Properties properties = new Properties();
            try {
                fileInputStream = new FileInputStream(file);
                try {
                    properties.load(fileInputStream);
                    z = Boolean.valueOf(properties.getProperty("tbs_local_installation", "false")).booleanValue();
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Throwable th3) {
                    th = th3;
                    try {
                        th.printStackTrace();
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                        return z;
                    } catch (Throwable th4) {
                        th2 = th4;
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e22) {
                                e22.printStackTrace();
                            }
                        }
                        throw th2;
                    }
                }
            } catch (Throwable th5) {
                th2 = th5;
                fileInputStream = null;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                throw th2;
            }
        }
        return z;
    }

    int c(Context context) {
        FileInputStream fileInputStream;
        Exception e;
        Throwable th;
        int i = 0;
        FileInputStream fileInputStream2 = null;
        try {
            File file = new File(e(context), "tbs.conf");
            if (file == null || !file.exists()) {
                if (fileInputStream2 != null) {
                    try {
                        fileInputStream2.close();
                    } catch (IOException e2) {
                        TbsLog.i("TbsInstaller", "TbsInstaller--getTbsCoreInstalledVerInNolock IOException=" + e2.toString());
                    }
                }
                return i;
            }
            Properties properties = new Properties();
            fileInputStream = new FileInputStream(file);
            try {
                properties.load(fileInputStream);
                fileInputStream.close();
                String property = properties.getProperty("tbs_core_version");
                if (property != null) {
                    i = Integer.parseInt(property);
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e22) {
                            TbsLog.i("TbsInstaller", "TbsInstaller--getTbsCoreInstalledVerInNolock IOException=" + e22.toString());
                        }
                    }
                } else if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e222) {
                        TbsLog.i("TbsInstaller", "TbsInstaller--getTbsCoreInstalledVerInNolock IOException=" + e222.toString());
                    }
                }
            } catch (Exception e3) {
                e = e3;
                try {
                    TbsLog.i("TbsInstaller", "TbsInstaller--getTbsCoreInstalledVerInNolock Exception=" + e.toString());
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e2222) {
                            TbsLog.i("TbsInstaller", "TbsInstaller--getTbsCoreInstalledVerInNolock IOException=" + e2222.toString());
                        }
                    }
                    return i;
                } catch (Throwable th2) {
                    th = th2;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e22222) {
                            TbsLog.i("TbsInstaller", "TbsInstaller--getTbsCoreInstalledVerInNolock IOException=" + e22222.toString());
                        }
                    }
                    throw th;
                }
            }
            return i;
        } catch (Exception e4) {
            e = e4;
            fileInputStream = fileInputStream2;
            TbsLog.i("TbsInstaller", "TbsInstaller--getTbsCoreInstalledVerInNolock Exception=" + e.toString());
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            return i;
        } catch (Throwable th3) {
            th = th3;
            fileInputStream = fileInputStream2;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            throw th;
        }
    }

    int d(Context context) {
        FileInputStream fileInputStream;
        Exception e;
        Throwable th;
        FileOutputStream b = i.b(context, true, "tbslock.txt");
        if (b == null) {
            return -1;
        }
        FileLock a = i.a(context, b);
        if (a == null) {
            return -1;
        }
        boolean tryLock = d.tryLock();
        TbsLog.i("TbsInstaller", "TbsInstaller--getTbsCoreInstalledVer locked=" + tryLock);
        if (tryLock) {
            FileInputStream fileInputStream2 = null;
            try {
                File file = new File(e(context), "tbs.conf");
                if (file == null || !file.exists()) {
                    if (fileInputStream2 != null) {
                        try {
                            fileInputStream2.close();
                        } catch (IOException e2) {
                            TbsLog.i("TbsInstaller", "TbsInstaller--getTbsCoreInstalledVer IOException=" + e2.toString());
                        }
                    }
                    d.unlock();
                    i.a(a, b);
                    return 0;
                }
                Properties properties = new Properties();
                fileInputStream = new FileInputStream(file);
                try {
                    properties.load(fileInputStream);
                    fileInputStream.close();
                    String property = properties.getProperty("tbs_core_version");
                    if (property == null) {
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e22) {
                                TbsLog.i("TbsInstaller", "TbsInstaller--getTbsCoreInstalledVer IOException=" + e22.toString());
                            }
                        }
                        d.unlock();
                        i.a(a, b);
                        return 0;
                    }
                    a.set(Integer.valueOf(Integer.parseInt(property)));
                    int intValue = ((Integer) a.get()).intValue();
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e3) {
                            TbsLog.i("TbsInstaller", "TbsInstaller--getTbsCoreInstalledVer IOException=" + e3.toString());
                        }
                    }
                    d.unlock();
                    i.a(a, b);
                    return intValue;
                } catch (Exception e4) {
                    e = e4;
                    try {
                        TbsLog.i("TbsInstaller", "TbsInstaller--getTbsCoreInstalledVer Exception=" + e.toString());
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e222) {
                                TbsLog.i("TbsInstaller", "TbsInstaller--getTbsCoreInstalledVer IOException=" + e222.toString());
                            }
                        }
                        d.unlock();
                        i.a(a, b);
                        return 0;
                    } catch (Throwable th2) {
                        th = th2;
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e32) {
                                TbsLog.i("TbsInstaller", "TbsInstaller--getTbsCoreInstalledVer IOException=" + e32.toString());
                            }
                        }
                        d.unlock();
                        i.a(a, b);
                        throw th;
                    }
                }
            } catch (Exception e5) {
                e = e5;
                fileInputStream = fileInputStream2;
                TbsLog.i("TbsInstaller", "TbsInstaller--getTbsCoreInstalledVer Exception=" + e.toString());
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                d.unlock();
                i.a(a, b);
                return 0;
            } catch (Throwable th3) {
                th = th3;
                fileInputStream = fileInputStream2;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                d.unlock();
                i.a(a, b);
                throw th;
            }
        }
        i.a(a, b);
        return 0;
    }

    File e(Context context) {
        return b(null, context);
    }

    File f(Context context) {
        File file = new File(context.getDir("tbs", 0), "share");
        return file != null ? (file.isDirectory() || file.mkdir()) ? file : null : null;
    }
}
