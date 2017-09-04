package com.tencent.android.tpush.a;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.l;
import com.tencent.android.tpush.service.channel.protocol.TpnsPushClientReport;
import com.tencent.android.tpush.service.channel.protocol.TpnsPushMsg;
import com.tencent.android.tpush.service.d.b;
import com.tencent.android.tpush.service.d.f;
import com.tencent.android.tpush.service.m;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: ProGuard */
public class a {
    public static boolean a = false;
    public static String b = ("tencent" + File.separator + Constants.LogTag + File.separator + "Logs");
    protected static volatile ExecutorService c = Executors.newSingleThreadExecutor(new c());
    public static AtomicInteger d = new AtomicInteger();
    public static AtomicInteger e = new AtomicInteger();
    public static AtomicInteger f = new AtomicInteger();
    public static AtomicInteger g = new AtomicInteger();
    public static AtomicInteger h = new AtomicInteger();
    public static AtomicInteger i = new AtomicInteger();
    public static AtomicInteger j = new AtomicInteger();
    public static AtomicInteger k = new AtomicInteger();
    public static AtomicInteger l = new AtomicInteger();
    public static AtomicInteger m = new AtomicInteger();
    public static AtomicInteger n = new AtomicInteger();
    private static boolean o = false;
    private static Boolean p = null;
    private static final SimpleDateFormat q = new SimpleDateFormat("MM.dd_HH:mm:ss_SSS");
    private static List r = Collections.synchronizedList(new ArrayList());
    private static boolean s = false;
    private static boolean t = false;
    private static String u = null;

    public static void a(int i) {
        switch (i) {
            case 0:
                a = true;
                return;
            case 1:
                o = true;
                return;
            case 2:
                o = true;
                a = true;
                return;
            case 3:
                o = false;
                a = false;
                return;
            default:
                Log.e("XGLogger", "TLogger ->setLogToFile unknown cmd " + i);
                return;
        }
    }

    public static boolean a(Context context) {
        if (context == null) {
            return false;
        }
        if (p == null) {
            if (f.b(context, "_isReportEnable", -1) == 1) {
                p = Boolean.valueOf(true);
            } else {
                p = Boolean.valueOf(false);
            }
        }
        return p.booleanValue();
    }

    public static void a(String str, String str2) {
        if (a && b(2)) {
            Log.v("XINGE", "[" + str + "] " + str2);
        }
        a("TRACE", str, str2, null);
    }

    public static void b(String str, String str2) {
        if (b(2)) {
            Log.v("XINGE", "[" + str + "] " + str2);
        }
        a("TRACE", str, str2, null);
    }

    public static void c(String str, String str2) {
        if (a && b(3)) {
            Log.d("XINGE", "[" + str + "] " + str2);
        }
        a("DEBUG", str, str2, null);
    }

    public static void d(String str, String str2) {
        if (a && b(4)) {
            Log.i("XINGE", "[" + str + "] " + str2);
        }
        a("INFO", str, str2, null);
    }

    public static void e(String str, String str2) {
        if (b(4)) {
            Log.i("XINGE", "[" + str + "] " + str2);
        }
        a("INFO", str, str2, null);
    }

    public static void f(String str, String str2) {
        if (a && b(5)) {
            Log.w("XINGE", "[" + str + "] " + str2);
        }
        a("WARN", str, str2, null);
    }

    public static void g(String str, String str2) {
        if (b(5)) {
            Log.w("XINGE", "[" + str + "] " + str2);
        }
        a("WARN", str, str2, null);
    }

    public static void h(String str, String str2) {
        if (a && b(6)) {
            Log.e("XINGE", "[" + str + "] " + str2);
        }
        a("ERROR", str, str2, null);
    }

    public static void i(String str, String str2) {
        if (b(6)) {
            Log.e("XINGE", "[" + str + "] " + str2);
        }
        a("ERROR", str, str2, null);
    }

    public static void a(String str, String str2, Throwable th) {
        if (a && b(2)) {
            Log.v("XINGE", "[" + str + "] " + str2, th);
        }
        a("TRACE", str, str2, th);
    }

    public static void b(String str, String str2, Throwable th) {
        if (a && b(5)) {
            Log.w("XINGE", "[" + str + "] " + str2, th);
        }
        a("WARN", str, str2, th);
    }

    public static void c(String str, String str2, Throwable th) {
        if (a && b(6)) {
            Log.e("XINGE", "[" + str + "] " + str2, th);
        }
        a("ERROR", str, str2, th);
    }

    public static void d(String str, String str2, Throwable th) {
        if (b(6)) {
            Log.e("XINGE", "[" + str + "] " + str2, th);
        }
        a("ERROR", str, str2, th);
    }

    private static boolean b(int i) {
        return true;
    }

    private static void a(String str, String str2, String str3, Throwable th) {
        if (o || a(m.e())) {
            if (str2 == null || str2.trim().equals("")) {
                str2 = "XGLogger";
            }
            String format = q.format(new Date());
            if (str3 == null) {
                str3 = "";
            }
            BufferedReader bufferedReader = new BufferedReader(new StringReader(str3), 256);
            String a = f.a("[" + str2 + "]", 24);
            while (true) {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    a(format + " " + f.a(str, 5) + " " + a + " " + readLine);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (th != null) {
                Writer stringWriter = new StringWriter();
                th.printStackTrace(new PrintWriter(stringWriter));
                a(format + " " + str + stringWriter.toString());
            }
        }
    }

    private static void a(String str) {
        if (!t) {
            r.add(str);
            if (r.size() == 100) {
                List list = r;
                r = Collections.synchronizedList(new ArrayList());
                s = f.f();
                if (s) {
                    Log.v("XGLogger", "have writable external storage, write log file!");
                    a(list);
                    return;
                }
                Log.v("XGLogger", "no writable external storage");
            }
        }
    }

    private static String c() {
        if (u != null) {
            return u;
        }
        try {
            u = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + b;
            return u;
        } catch (Throwable th) {
            Log.e("XGLogger", "TLogger ->getFileNamePre", th);
            return null;
        }
    }

    private static void a(List list) {
        if (a(m.e())) {
        }
        if (o) {
            try {
                c.execute(new b(list));
            } catch (Throwable e) {
                Log.e("XGLogger", "savelog error", e);
            }
        }
    }

    private static void d() {
        try {
            String c = c();
            if (c != null) {
                File file = new File(c);
                if (file.exists()) {
                    int length = c.length() + 5;
                    int length2 = length + b.a.length();
                    for (File file2 : file.listFiles()) {
                        try {
                            if (file2.isFile()) {
                                String absolutePath = file2.getAbsolutePath();
                                if (b.a(b.a(absolutePath.substring(length, length2)), 7)) {
                                    Log.d("XGLogger", "delete logs file " + absolutePath);
                                    file2.delete();
                                }
                            }
                        } catch (Exception e) {
                            Log.e("XGLogger", "removeOldDebugLogFiles" + e);
                        }
                    }
                }
            }
        } catch (Throwable e2) {
            Log.e("XGLogger", "removeOldDebugLogFiles", e2);
        }
    }

    public static void a(int i, List list) {
        if (o) {
            List arrayList = new ArrayList();
            if (list != null && list.size() > 0) {
                for (TpnsPushClientReport tpnsPushClientReport : list) {
                    arrayList.add(Long.valueOf(tpnsPushClientReport.msgId));
                }
            }
            if (arrayList != null && arrayList.size() > 0) {
                c(i, arrayList);
            }
        }
    }

    public static void b(int i, List list) {
        if (o) {
            List arrayList = new ArrayList();
            if (list != null && list.size() > 0) {
                for (TpnsPushMsg tpnsPushMsg : list) {
                    arrayList.add(Long.valueOf(tpnsPushMsg.msgId));
                }
            }
            if (arrayList != null && arrayList.size() > 0) {
                c(i, arrayList);
            }
        }
    }

    public static void a(int i, long j) {
        if (o) {
            List arrayList = new ArrayList();
            arrayList.add(Long.valueOf(j));
            if (arrayList != null && arrayList.size() > 0) {
                c(i, arrayList);
            }
        }
    }

    public static synchronized void c(int i, List list) {
        FileWriter fileWriter;
        Throwable th;
        synchronized (a.class) {
            if (o) {
                FileWriter fileWriter2 = null;
                try {
                    int andIncrement;
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String str = Environment.getExternalStorageDirectory() + "/";
                    String str2;
                    switch (i) {
                        case 0:
                            str2 = str + "_0ServerSendToService.txt";
                            andIncrement = d.getAndIncrement();
                            str = str2;
                            break;
                        case 1:
                            str2 = str + "_1ServiceAckToServer.txt";
                            andIncrement = e.getAndIncrement();
                            str = str2;
                            break;
                        case 2:
                            str2 = str + "_2XgSdkReceiveFromXGService.txt";
                            andIncrement = f.getAndIncrement();
                            str = str2;
                            break;
                        case 3:
                            str2 = str + "_3SdkSendAckToService.txt";
                            andIncrement = g.getAndIncrement();
                            str = str2;
                            break;
                        case 4:
                            str2 = str + "_4ServiceRecAckFromSdk1.txt";
                            andIncrement = h.getAndIncrement();
                            str = str2;
                            break;
                        case 5:
                            str2 = str + "_5ServiceRecAckFromSdk2.txt";
                            andIncrement = i.getAndIncrement();
                            str = str2;
                            break;
                        case 6:
                            str2 = str + "_6ServiceRecAckFromSdk3.txt";
                            andIncrement = j.getAndIncrement();
                            str = str2;
                            break;
                        case 7:
                            str2 = str + "_7ServiceRecAckFromServer.txt";
                            andIncrement = k.getAndIncrement();
                            str = str2;
                            break;
                        case 8:
                            str2 = str + "_8ServiceRecAckFromServer_failed";
                            andIncrement = l.getAndIncrement();
                            str = str2;
                            break;
                        case 11:
                            str2 = str + "_11unequal";
                            andIncrement = m.getAndIncrement();
                            str = str2;
                            break;
                        case 12:
                            andIncrement = n.getAndIncrement();
                            str = str + "_12notList";
                            break;
                        default:
                            h("XGLogger", "unknown case");
                            if (fileWriter2 != null) {
                                try {
                                    fileWriter2.close();
                                    break;
                                } catch (IOException e) {
                                    break;
                                }
                            }
                            break;
                    }
                    if (l.a("android.permission.WRITE_EXTERNAL_STORAGE")) {
                        fileWriter = new FileWriter(str, true);
                        try {
                            for (Long l : list) {
                                fileWriter.write("" + andIncrement + "\t" + simpleDateFormat.format(new Date()) + "\t" + "msgid: " + l + "\n");
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            fileWriter2 = fileWriter;
                            if (fileWriter2 != null) {
                                fileWriter2.close();
                            }
                            throw th;
                        }
                    } else {
                        fileWriter = fileWriter2;
                    }
                    if (fileWriter != null) {
                        try {
                            fileWriter.close();
                        } catch (IOException e2) {
                        }
                    }
                } catch (Throwable th3) {
                    th = th3;
                    c("XGLogger", "writeMsgSession error", th);
                    if (fileWriter2 != null) {
                        fileWriter2.close();
                    }
                    return;
                }
            }
        }
        return;
    }
}
