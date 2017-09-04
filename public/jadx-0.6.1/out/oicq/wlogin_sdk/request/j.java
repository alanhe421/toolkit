package oicq.wlogin_sdk.request;

import java.net.HttpURLConnection;
import oicq.wlogin_sdk.tools.util;

/* compiled from: http_connect_ontime */
public class j implements Runnable {
    private HttpURLConnection a;
    private boolean b = false;

    public j(HttpURLConnection httpURLConnection) {
        this.a = httpURLConnection;
    }

    public void run() {
        try {
            this.a.connect();
            this.b = true;
        } catch (Throwable th) {
            util.printThrowable(th, "");
        }
    }

    public boolean a() {
        return this.b;
    }

    public static boolean a(HttpURLConnection httpURLConnection, long j) {
        try {
            Object jVar = new j(httpURLConnection);
            Thread thread = new Thread(jVar);
            thread.start();
            thread.join(j);
            return jVar.a();
        } catch (Throwable th) {
            return false;
        }
    }
}
