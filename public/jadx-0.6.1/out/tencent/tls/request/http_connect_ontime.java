package tencent.tls.request;

import java.net.HttpURLConnection;

public class http_connect_ontime implements Runnable {
    private HttpURLConnection conn;
    private boolean ret = false;

    public http_connect_ontime(HttpURLConnection httpURLConnection) {
        this.conn = httpURLConnection;
    }

    public void run() {
        try {
            this.conn.connect();
            this.ret = true;
        } catch (Exception e) {
        }
    }

    public synchronized void set(boolean z) {
        this.ret = z;
    }

    public synchronized boolean get() {
        return this.ret;
    }

    public static boolean connect_ontime(HttpURLConnection httpURLConnection, long j) {
        try {
            Object tencent_tls_request_http_connect_ontime = new http_connect_ontime(httpURLConnection);
            Thread thread = new Thread(tencent_tls_request_http_connect_ontime);
            thread.start();
            thread.join(j);
            return tencent_tls_request_http_connect_ontime.get();
        } catch (Throwable th) {
            return false;
        }
    }
}
