package com.xiaomi.push.service;

import android.os.Process;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.b.c;
import com.xiaomi.d.f;
import com.xiaomi.network.d;
import com.xiaomi.push.b.a.a;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class h {
    private static final Pattern a = Pattern.compile("([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3})");
    private static long b = 0;
    private static ThreadPoolExecutor c = new ThreadPoolExecutor(1, 1, 20, TimeUnit.SECONDS, new LinkedBlockingQueue());

    public static void a() {
        long currentTimeMillis = System.currentTimeMillis();
        if ((c.getActiveCount() <= 0 || currentTimeMillis - b >= 1800000) && f.a().c()) {
            a d = v.a().d();
            if (d != null && d.m() > 0) {
                b = currentTimeMillis;
                a(d.l(), true);
            }
        }
    }

    public static void a(List<String> list, boolean z) {
        c.execute(new i(list, z));
    }

    public static void b() {
        Object c = c("/proc/self/net/tcp");
        if (!TextUtils.isEmpty(c)) {
            c.a("dump tcp for uid = " + Process.myUid());
            c.a(c);
        }
        c = c("/proc/self/net/tcp6");
        if (!TextUtils.isEmpty(c)) {
            c.a("dump tcp6 for uid = " + Process.myUid());
            c.a(c);
        }
    }

    private static boolean b(String str) {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            c.a("ConnectivityTest: begin to connect to " + str);
            Socket socket = new Socket();
            socket.connect(d.b(str, 5222), 5000);
            socket.setTcpNoDelay(true);
            c.a("ConnectivityTest: connect to " + str + " in " + (System.currentTimeMillis() - currentTimeMillis));
            socket.close();
            return true;
        } catch (Throwable th) {
            c.d("ConnectivityTest: could not connect to:" + str + " exception: " + th.getClass().getSimpleName() + " description: " + th.getMessage());
            return false;
        }
    }

    private static String c(String str) {
        Reader bufferedReader;
        Throwable th;
        String str2 = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(new File(str)));
            try {
                StringBuilder stringBuilder = new StringBuilder();
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    stringBuilder.append("\n");
                    stringBuilder.append(readLine);
                }
                str2 = stringBuilder.toString();
                com.xiaomi.channel.commonutils.a.a.a(bufferedReader);
            } catch (Exception e) {
                com.xiaomi.channel.commonutils.a.a.a(bufferedReader);
                return str2;
            } catch (Throwable th2) {
                th = th2;
                com.xiaomi.channel.commonutils.a.a.a(bufferedReader);
                throw th;
            }
        } catch (Exception e2) {
            Object obj = str2;
            com.xiaomi.channel.commonutils.a.a.a(bufferedReader);
            return str2;
        } catch (Throwable th3) {
            Throwable th4 = th3;
            bufferedReader = str2;
            th = th4;
            com.xiaomi.channel.commonutils.a.a.a(bufferedReader);
            throw th;
        }
        return str2;
    }
}
