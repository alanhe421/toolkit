package com.qq.reader.liveshow.utils;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.dynamicload.Lib.DLConstants;
import com.tencent.feedback.eup.BuglyBroadcastRecevier;
import com.tencent.imsdk.MyLinkedBlockingDeque;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/* compiled from: SxbLogImpl */
public class o {
    static MyLinkedBlockingDeque<String> a = new MyLinkedBlockingDeque(15000);
    public static final SimpleDateFormat b = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
    static String c = "";
    static final ReentrantLock d = new ReentrantLock();
    protected static Object e = new Object();
    static long f = 0;
    static Thread g = new Thread() {
        public void run() {
            while (true) {
                try {
                    String str = (String) o.a.take();
                    if (str != null) {
                        o.d(str);
                    }
                } catch (InterruptedException e) {
                    System.out.println("write log fe error: " + e.toString());
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }
    };
    public static Runnable h = new Runnable() {
        public void run() {
            if (o.j != null) {
                new Thread(this, "QLVBLogInitThread") {
                    final /* synthetic */ AnonymousClass2 a;

                    /* JADX WARNING: inconsistent code. */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public void run() {
                        /*
                        r6 = this;
                        r0 = com.qq.reader.liveshow.utils.o.j;	 Catch:{ Exception -> 0x0029 }
                        r0 = r0.getPackageName();	 Catch:{ Exception -> 0x0029 }
                        com.qq.reader.liveshow.utils.o.k = r0;	 Catch:{ Exception -> 0x0029 }
                    L_0x000b:
                        r0 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x0031 }
                        com.qq.reader.liveshow.utils.o.a(r0);	 Catch:{ Exception -> 0x0031 }
                        r0 = com.qq.reader.liveshow.utils.o.g;	 Catch:{ Exception -> 0x0031 }
                        r1 = "logWriteThread";
                        r0.setName(r1);	 Catch:{ Exception -> 0x0031 }
                        r0 = com.qq.reader.liveshow.utils.o.g;	 Catch:{ Exception -> 0x0031 }
                        r0.start();	 Catch:{ Exception -> 0x0031 }
                        r0 = com.qq.reader.liveshow.utils.o.s;	 Catch:{ Exception -> 0x0031 }
                        r1 = com.qq.reader.liveshow.utils.o.h;	 Catch:{ Exception -> 0x0031 }
                        r0.removeCallbacks(r1);	 Catch:{ Exception -> 0x0031 }
                    L_0x0028:
                        return;
                    L_0x0029:
                        r0 = move-exception;
                        r0 = "unknown";
                        com.qq.reader.liveshow.utils.o.k = r0;	 Catch:{ Exception -> 0x0031 }
                        goto L_0x000b;
                    L_0x0031:
                        r0 = move-exception;
                        r0 = com.qq.reader.liveshow.utils.o.m;
                        r0 = r0.get();
                        r1 = java.lang.System.out;
                        r2 = new java.lang.StringBuilder;
                        r2.<init>();
                        r3 = "QLog init post retry ";
                        r2 = r2.append(r3);
                        r2 = r2.append(r0);
                        r3 = " times, interval ";
                        r2 = r2.append(r3);
                        r3 = com.qq.reader.liveshow.utils.o.l;
                        r3 = r3[r0];
                        r2 = r2.append(r3);
                        r2 = r2.toString();
                        r1.println(r2);
                        r1 = com.qq.reader.liveshow.utils.o.s;
                        r2 = com.qq.reader.liveshow.utils.o.h;
                        r1.removeCallbacks(r2);
                        r1 = com.qq.reader.liveshow.utils.o.s;
                        r2 = com.qq.reader.liveshow.utils.o.h;
                        r3 = com.qq.reader.liveshow.utils.o.l;
                        r3 = r3[r0];
                        r4 = 60000; // 0xea60 float:8.4078E-41 double:2.9644E-319;
                        r3 = r3 * r4;
                        r4 = (long) r3;
                        r1.postDelayed(r2, r4);
                        r0 = r0 + 1;
                        r1 = com.qq.reader.liveshow.utils.o.l;
                        r1 = r1.length;
                        if (r0 < r1) goto L_0x008b;
                    L_0x008a:
                        r0 = 0;
                    L_0x008b:
                        r1 = com.qq.reader.liveshow.utils.o.m;
                        r1.set(r0);
                        goto L_0x0028;
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.liveshow.utils.o.2.1.run():void");
                    }
                }.start();
            }
        }
    };
    private static boolean i = false;
    private static volatile Context j;
    private static String k = "";
    private static final int[] l = new int[]{1, 2, 4, 8, 16, 29};
    private static AtomicInteger m = new AtomicInteger(0);
    private static String n = "";
    private static String o = "";
    private static long p;
    private static long q;
    private static FileWriter r;
    private static Handler s = new Handler(Looper.getMainLooper());

    public static synchronized void a(Context context) {
        synchronized (o.class) {
            if (!i) {
                i = true;
                j = context;
                h.run();
            }
        }
    }

    private static void d(String str) {
        try {
            if (!"mounted".equals(Environment.getExternalStorageState())) {
                System.out.println("writeLogToFile not ready");
            } else if (r == null) {
                System.out.println("can not write SxbLog.");
                long currentTimeMillis = System.currentTimeMillis();
                if (f == 0) {
                    f = currentTimeMillis;
                } else if (currentTimeMillis - f > BuglyBroadcastRecevier.UPLOADLIMITED) {
                    try {
                        a(System.currentTimeMillis());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    f = currentTimeMillis;
                }
            } else {
                long currentTimeMillis2 = System.currentTimeMillis();
                if (currentTimeMillis2 > p) {
                    a(currentTimeMillis2);
                }
                if (d.tryLock()) {
                    r.write(str);
                    r.flush();
                    d.unlock();
                } else if (!f(str)) {
                    System.out.println("insertLogToCacheHead failed!");
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private static String b(long j) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy.MM.dd.HH");
        n = new SimpleDateFormat("yy-MM-dd HH:mm:ss").format(Long.valueOf(j));
        String format = simpleDateFormat.format(instance.getTime());
        b(instance);
        a(instance);
        return format;
    }

    private static void a(Calendar calendar) {
        calendar.add(11, 1);
        calendar.set(12, 0);
        calendar.set(13, 0);
        p = calendar.getTimeInMillis();
    }

    private static void b(Calendar calendar) {
        calendar.set(14, 0);
        q = calendar.getTimeInMillis() + 1000;
    }

    public static String a(String str) {
        return k + "_" + str + ".log";
    }

    private static synchronized void c(long j) {
        synchronized (o.class) {
            if (j > q) {
                synchronized (e) {
                    n = b.format(Long.valueOf(j));
                    q += 1000;
                }
            }
        }
    }

    static synchronized void a(long j) throws IOException {
        File file;
        Throwable th;
        synchronized (o.class) {
            o = Environment.getExternalStorageDirectory().getPath() + "/tencent/sxblog/" + k.replace(".", "/") + "/";
            File file2 = new File(o);
            if (!file2.exists()) {
                file2.mkdirs();
            }
            c = o + a(b(j));
            try {
                file = new File(c);
                try {
                    if (!file.exists()) {
                        boolean createNewFile = file.createNewFile();
                        if (r != null) {
                            r.write(n + DLConstants.DEPENDENCY_PACKAGE_DIV + "|D|" + Build.MODEL + " " + VERSION.RELEASE + " create newLogFile " + file.getName() + " " + createNewFile + "\n");
                            r.flush();
                        }
                    } else if (r != null) {
                        r.write(n + DLConstants.DEPENDENCY_PACKAGE_DIV + "|E|" + Build.MODEL + " " + VERSION.RELEASE + "|newLogFile " + file.getName() + " is existed.\n");
                        r.flush();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    th.printStackTrace();
                    r = new FileWriter(file, true);
                }
            } catch (Throwable th3) {
                th = th3;
                file = file2;
                th.printStackTrace();
                r = new FileWriter(file, true);
            }
            r = new FileWriter(file, true);
        }
    }

    public static void a(String str, String str2, String str3, Throwable th) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis >= q) {
            c(currentTimeMillis);
        }
        String str4 = n + DLConstants.DEPENDENCY_PACKAGE_DIV + str + DLConstants.DEPENDENCY_PACKAGE_DIV + String.valueOf(Thread.currentThread().getId()) + DLConstants.DEPENDENCY_PACKAGE_DIV + str2 + DLConstants.DEPENDENCY_PACKAGE_DIV + str3 + "\n";
        if (th != null) {
            str4 = str3 + "\n" + Log.getStackTraceString(th) + "\n";
        }
        e(str4);
    }

    private static boolean e(String str) {
        try {
            a.add(str);
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    private static boolean f(String str) {
        try {
            a.addFirst(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
