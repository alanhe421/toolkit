package com.tencent.qalsdk.sdk;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.dynamicload.Lib.DLConstants;
import com.tencent.android.tpush.common.Constants;
import com.tencent.feedback.eup.BuglyBroadcastRecevier;
import com.tencent.imsdk.QLogImpl;
import com.tencent.qalsdk.QALLogListener;
import com.tencent.qalsdk.util.QLog;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/* compiled from: LogToFileHelper */
public class q {
    private static final String N = "MSF.D.QLogImpl";
    protected static int a = 1;
    protected static final boolean b = false;
    protected static boolean c = true;
    public static final String e = "LOGLEVEL_";
    public static final String f = "LOGLEVELTIME";
    public static final String g = "LOGSAVETIME";
    private String A = "";
    private FileWriter B;
    private AtomicBoolean C = new AtomicBoolean(false);
    private int D;
    private long E = 0;
    private AtomicBoolean F = new AtomicBoolean(false);
    private QALLogListener G;
    private String H;
    private final int[] I = new int[]{1, 2, 4, 8, 16, 29};
    private AtomicInteger J = new AtomicInteger(0);
    private Handler K = new Handler(Looper.getMainLooper());
    private volatile Context L = null;
    private String M = "";
    protected Object d = new Object();
    ae<String> h = new ae(15000);
    final ReentrantLock i = new ReentrantLock();
    public Runnable j = new r(this);
    String k = "";
    long l = 0;
    Thread m = new t(this);
    Thread n = new u(this);
    public final String o = QLogImpl.TAG_REPORTLEVEL_DEVELOPER;
    public final String p = QLogImpl.TAG_REPORTLEVEL_COLORUSER;
    public final String q = QLogImpl.TAG_REPORTLEVEL_USER;
    HashSet<String> r = new HashSet();
    long s = 0;
    private final String t = "appMemory";
    private int u = a;
    private String v = "";
    private String w;
    private long x;
    private long y;
    private String z = "";

    /* compiled from: LogToFileHelper */
    protected class a {
        String a;
        String b;
        String c;
        Throwable d;
        final /* synthetic */ q e;

        public a(q qVar, String str, String str2, String str3, Throwable th) {
            this.e = qVar;
            this.a = str;
            this.b = str2;
            this.c = str3;
            this.d = th;
        }
    }

    public void a(QALLogListener qALLogListener) {
        this.G = qALLogListener;
    }

    public void a(Context context, String str) {
        this.H = str;
        this.L = context.getApplicationContext();
        if (VERSION.SDK_INT >= 23 && context.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            c = false;
            QLog.i(N, "no WRITE_EXTERNAL_STORAGE permission,can't log to file");
        }
        this.j.run();
        b();
    }

    public String a() {
        return this.w;
    }

    String b() {
        try {
            this.A = this.L.getPackageName();
        } catch (Exception e) {
            this.A = "unknow";
        }
        this.w = Environment.getExternalStorageDirectory().getPath() + "/tencent/qalsdklogs/" + this.A.replace(".", "/") + "/";
        return this.w;
    }

    public String a(long j) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy.MM.dd.HH");
        this.M = new SimpleDateFormat("yy-MM-dd HH:mm:ss").format(Long.valueOf(j));
        String format = simpleDateFormat.format(instance.getTime());
        b(instance);
        a(instance);
        return format;
    }

    private void a(Calendar calendar) {
        calendar.add(11, 1);
        calendar.set(12, 0);
        calendar.set(13, 0);
        this.x = calendar.getTimeInMillis();
    }

    private void b(Calendar calendar) {
        calendar.set(14, 0);
        this.y = calendar.getTimeInMillis() + 1000;
    }

    private synchronized void c(long j) {
        if (j > this.y) {
            synchronized (this.d) {
                this.M = MsfSdkUtils.timeFormatter.format(Long.valueOf(j));
                this.y += 1000;
            }
        }
    }

    public String a(String str) {
        return this.H + "." + str + ".log";
    }

    public String c() {
        int indexOf = this.z.indexOf(":");
        if (indexOf > 0) {
            return this.z.substring(0, indexOf);
        }
        return this.z;
    }

    synchronized void b(long j) throws IOException {
        Throwable th;
        File file;
        if (this.w == null) {
            b();
        }
        this.v = this.w + this.H + "/";
        File file2 = new File(this.v);
        if (!file2.exists()) {
            file2.mkdirs();
        }
        this.k = this.v + a(a(j));
        try {
            file = new File(this.k);
            try {
                if (file.exists()) {
                    j();
                    if (this.B != null) {
                        this.B.write(this.M + DLConstants.DEPENDENCY_PACKAGE_DIV + this.z + "|E|" + N + DLConstants.DEPENDENCY_PACKAGE_DIV + Build.MODEL + " " + VERSION.RELEASE + "|newLogFile " + file.getName() + " is existed.\n");
                        this.B.flush();
                    }
                } else {
                    boolean createNewFile = file.createNewFile();
                    j();
                    if (this.B != null) {
                        this.B.write(this.M + DLConstants.DEPENDENCY_PACKAGE_DIV + this.z + "|D|" + N + DLConstants.DEPENDENCY_PACKAGE_DIV + Build.MODEL + " " + VERSION.RELEASE + " create newLogFile " + file.getName() + " " + createNewFile + "\n");
                        this.B.flush();
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                th.printStackTrace();
                this.B = new FileWriter(file, true);
                j();
            }
        } catch (Throwable th3) {
            th = th3;
            file = file2;
            th.printStackTrace();
            this.B = new FileWriter(file, true);
            j();
        }
        this.B = new FileWriter(file, true);
        j();
    }

    private void j() throws IOException {
        if (this.B != null && !"".equals(QLog.sBuildNumber)) {
            this.B.write(this.M + DLConstants.DEPENDENCY_PACKAGE_DIV + this.z + "|D|" + "|QQ_Version: " + QLog.sBuildNumber + "\r\n");
            this.B.flush();
        }
    }

    public void a(String str, String str2, Throwable th) {
        if (c) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis >= this.y) {
                c(currentTimeMillis);
            }
            if (this.s != 0 && currentTimeMillis - this.s > 1800000) {
                this.s = 0;
                this.r.clear();
            }
            String str3 = this.M + DLConstants.DEPENDENCY_PACKAGE_DIV + String.valueOf(Thread.currentThread().getId()) + DLConstants.DEPENDENCY_PACKAGE_DIV + str + DLConstants.DEPENDENCY_PACKAGE_DIV + str2 + "\n";
            if (th != null) {
                str3 = str3 + "\n" + Log.getStackTraceString(th) + "\n";
            }
            if (b(str3) && this.L != null && System.currentTimeMillis() - this.E > 180000) {
                this.E = System.currentTimeMillis();
                k();
            }
        }
    }

    private void k() {
        try {
            ((ActivityManager) this.L.getSystemService(Constants.FLAG_ACTIVITY_NAME)).getMemoryInfo(new MemoryInfo());
        } catch (Exception e) {
        }
    }

    private boolean b(String str) {
        try {
            this.h.add(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean c(String str) {
        try {
            this.h.a((Object) str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void d(String str) {
        try {
            if (c && "mounted".equals(Environment.getExternalStorageState())) {
                if (this.B == null) {
                    System.out.println("can not write log.");
                    long currentTimeMillis = System.currentTimeMillis();
                    if (this.l == 0) {
                        this.l = currentTimeMillis;
                    } else if (currentTimeMillis - this.l > BuglyBroadcastRecevier.UPLOADLIMITED) {
                        try {
                            b(System.currentTimeMillis());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        this.l = currentTimeMillis;
                    }
                } else {
                    long currentTimeMillis2 = System.currentTimeMillis();
                    if (currentTimeMillis2 > this.x) {
                        b(currentTimeMillis2);
                    }
                    if (this.i.tryLock()) {
                        this.B.write(str);
                        this.B.flush();
                        this.i.unlock();
                    } else if (!c(str)) {
                        Log.d("QLogImpl", "insertLogToCacheHead failed!");
                    }
                }
                this.F.compareAndSet(true, false);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public String a(int i) {
        switch (i) {
            case 1:
                return QLogImpl.TAG_REPORTLEVEL_USER;
            case 2:
                return QLogImpl.TAG_REPORTLEVEL_COLORUSER;
            case 4:
                return QLogImpl.TAG_REPORTLEVEL_DEVELOPER;
            default:
                return QLogImpl.TAG_REPORTLEVEL_USER;
        }
    }

    public String d() {
        return this.v;
    }

    public SimpleDateFormat e() {
        return new SimpleDateFormat("yy.MM.dd.HH");
    }

    public int f() {
        return this.u;
    }

    public void b(int i) {
        this.u = i;
    }

    public String g() {
        return this.z;
    }

    public boolean h() {
        return this.u > 1;
    }

    public boolean i() {
        return this.u >= 4;
    }
}
