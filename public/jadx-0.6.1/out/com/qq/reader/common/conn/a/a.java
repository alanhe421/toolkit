package com.qq.reader.common.conn.a;

import com.qq.reader.ReaderApplication;
import com.qq.reader.common.conn.http.a.b;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.StatisticsManager;
import com.tencent.connect.common.Constants;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* compiled from: CheckOnlineChapterIPUtil */
public class a {
    private static a c;
    private static String d = null;
    final String a = "OKHTTP_RDM";
    private ExecutorService b;

    /* compiled from: CheckOnlineChapterIPUtil */
    private class a implements Runnable {
        final /* synthetic */ a a;
        private long b = 0;
        private String c;

        public a(a aVar, String str) throws Throwable {
            this.a = aVar;
            this.c = str;
        }

        public void run() {
            try {
                a(this.c);
            } catch (Throwable th) {
                c.a("OKHTTP_RDM", "CheckHijacking run() " + th.toString());
            }
        }

        public void a(String str) {
            if (str != null && str.length() != 0) {
                try {
                    CharSequence b = b(str);
                    if (a.d == null) {
                        String a = com.qq.reader.appconfig.a.c.a();
                        if (a.length() > 0) {
                            String[] split = a.split("\\|");
                            this.b = Long.valueOf(split[0]).longValue();
                            a.d = split[1];
                            c.a("OKHTTP_RDM", "ips ：" + split[1]);
                            if (this.b < System.currentTimeMillis()) {
                                a();
                            }
                        } else {
                            a();
                            return;
                        }
                    }
                    if (!a.d.contains(b)) {
                        c.a("OKHTTP_RDM", "RDMEvent NGIX IP isHijacking");
                        i.a("hijackingIP", true, 0, 0, null, ReaderApplication.getApplicationContext());
                        Map hashMap = new HashMap();
                        hashMap.put("hijackingIP_ip", b);
                        StatisticsManager.a().a("hijackingIP", hashMap);
                    }
                } catch (Throwable th) {
                    c.a("OKHTTP_RDM", "checkHijacking " + th.toString());
                }
            }
        }

        private void a() throws Throwable {
            c.a("OKHTTP_RDM", "obtainOnlineChapterIPList is called");
            com.qq.reader.common.conn.http.c.a(new b(this) {
                final /* synthetic */ a c;

                {
                    this.c = r1;
                }

                public void b(int i, String str) {
                    if (str != null && str.length() > 0) {
                        a.d = str;
                        com.qq.reader.appconfig.a.c.a(str);
                    }
                }

                public void a(int i, String str) {
                    c.a("OKHTTP_RDM", "obtainOnlineChapterIPList onError code = " + i + " message = " + str);
                }

                public void a(Exception exception) {
                    c.a("OKHTTP_RDM", "obtainOnlineChapterIPList onFailure " + exception.toString());
                }
            }, "http://wfqqreader.3g.qq.com/dwnbookip.html", null, Constants.HTTP_GET, null, null, null, null);
        }

        public String b(String str) throws Exception {
            InetAddress inetAddress = null;
            try {
                inetAddress = InetAddress.getByName(new URL(str).getHost());
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            String hostAddress = inetAddress.getHostAddress();
            c.a("OKHTTP_RDM", "obtainIpWithUrl IP: " + hostAddress);
            return hostAddress;
        }
    }

    private a() {
        if (this.b != null) {
            this.b.shutdownNow();
            this.b = null;
        }
        this.b = Executors.newFixedThreadPool(2);
    }

    public static a a() {
        if (c == null) {
            synchronized (a.class) {
                if (c == null) {
                    c = new a();
                }
            }
        }
        return c;
    }

    public void a(String str) {
        try {
            this.b.submit(new a(this, str));
        } catch (Throwable th) {
            c.a("OKHTTP_RDM", "CheckOnlineChapterIPUtil checkUrl() " + th.toString());
        }
    }
}
