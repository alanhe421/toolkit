package oicq.wlogin_sdk.request;

import java.net.InetSocketAddress;

/* compiled from: DNS_resolver */
public class a implements Runnable {
    private String a;
    private int b;
    private InetSocketAddress c;

    public a(String str, int i) {
        this.a = str;
        this.b = i;
    }

    public void run() {
        try {
            a(new InetSocketAddress(this.a, this.b));
        } catch (Exception e) {
        }
    }

    public synchronized void a(InetSocketAddress inetSocketAddress) {
        this.c = inetSocketAddress;
    }

    public synchronized InetSocketAddress a() {
        return this.c;
    }

    public static InetSocketAddress a(String str, int i, long j) {
        try {
            Object aVar = new a(str, i);
            Thread thread = new Thread(aVar);
            thread.start();
            thread.join(j);
            return aVar.a();
        } catch (Exception e) {
            return null;
        }
    }
}
