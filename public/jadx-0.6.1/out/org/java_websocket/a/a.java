package org.java_websocket.a;

import com.tencent.feedback.eup.BuglyBroadcastRecevier;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.channels.NotYetConnectedException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CountDownLatch;
import org.java_websocket.WebSocket;
import org.java_websocket.b;
import org.java_websocket.b.d;
import org.java_websocket.b.f;
import org.java_websocket.b.h;
import org.java_websocket.drafts.Draft;
import org.java_websocket.exceptions.InvalidHandshakeException;
import org.java_websocket.framing.Framedata;

/* compiled from: WebSocketClient */
public abstract class a extends org.java_websocket.a implements Runnable, WebSocket {
    static final /* synthetic */ boolean b = (!a.class.desiredAssertionStatus());
    protected URI a;
    private b c;
    private Socket d;
    private InputStream e;
    private OutputStream f;
    private Proxy g;
    private Thread h;
    private Draft i;
    private Map<String, String> j;
    private CountDownLatch k;
    private CountDownLatch l;
    private int m;
    private long n;

    public abstract void a(int i, String str, boolean z);

    public abstract void a(Exception exception);

    public abstract void a(String str);

    public abstract void a(h hVar);

    public a(URI uri) {
        this(uri, new org.java_websocket.drafts.a());
    }

    public a(URI uri, Draft draft) {
        this(uri, draft, null, 0);
    }

    public void a(Map<String, String> map) {
        this.j = map;
    }

    public a(URI uri, Draft draft, Map<String, String> map, int i) {
        this.a = null;
        this.c = null;
        this.d = null;
        this.g = Proxy.NO_PROXY;
        this.k = new CountDownLatch(1);
        this.l = new CountDownLatch(1);
        this.m = 0;
        this.n = BuglyBroadcastRecevier.UPLOADLIMITED;
        if (uri == null) {
            throw new IllegalArgumentException();
        } else if (draft == null) {
            throw new IllegalArgumentException("null as draft is permitted for `WebSocketServer` only!");
        } else {
            this.a = uri;
            this.i = draft;
            this.j = map;
            this.m = i;
            this.c = new b(this, draft);
        }
    }

    public void a() {
        if (this.h != null) {
            throw new IllegalStateException("WebSocketClient objects are not reuseable");
        }
        this.h = new Thread(this);
        this.h.start();
    }

    public boolean c() throws InterruptedException {
        a();
        this.k.await();
        return this.c.c();
    }

    public void f() {
        if (this.h != null) {
            this.c.a(1000);
        }
    }

    public void b(String str) throws NotYetConnectedException {
        this.c.a(str);
    }

    public void b() {
        this.c.b();
    }

    public void run() {
        try {
            if (this.d == null) {
                this.d = new Socket(this.g);
            } else if (this.d.isClosed()) {
                throw new IOException();
            }
            if (!this.d.isBound()) {
                this.d.connect(new InetSocketAddress(this.a.getHost(), d()), this.m);
            }
            this.e = this.d.getInputStream();
            this.f = this.d.getOutputStream();
            i();
            this.h = new Thread(new a(this, null));
            this.h.start();
            byte[] bArr = new byte[b.a];
            while (!h()) {
                try {
                    int read = this.e.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    this.c.a(ByteBuffer.wrap(bArr, 0, read));
                } catch (IOException e) {
                    this.c.a();
                } catch (Exception e2) {
                    a(e2);
                    this.c.b(1006, e2.getMessage());
                }
            }
            this.c.a();
            if (!b && !this.d.isClosed()) {
                throw new AssertionError();
            }
        } catch (Exception e22) {
            a(this.c, e22);
            this.c.b(-1, e22.getMessage());
        }
    }

    private int d() {
        int port = this.a.getPort();
        if (port != -1) {
            return port;
        }
        String scheme = this.a.getScheme();
        if (scheme.equals("wss")) {
            return 443;
        }
        if (scheme.equals("ws")) {
            return 80;
        }
        throw new RuntimeException("unkonow scheme" + scheme);
    }

    private void i() throws InvalidHandshakeException {
        String path = this.a.getPath();
        String query = this.a.getQuery();
        if (path == null || path.length() == 0) {
            path = "/";
        }
        if (query != null) {
            path = new StringBuilder(String.valueOf(path)).append("?").append(query).toString();
        }
        int d = d();
        query = new StringBuilder(String.valueOf(this.a.getHost())).append(d != 80 ? ":" + d : "").toString();
        org.java_websocket.b.b dVar = new d();
        dVar.a(path);
        dVar.a("Host", query);
        if (this.j != null) {
            for (Entry entry : this.j.entrySet()) {
                dVar.a((String) entry.getKey(), (String) entry.getValue());
            }
        }
        this.c.a(dVar);
    }

    public final void a(WebSocket webSocket, String str) {
        a(str);
    }

    public final void a(WebSocket webSocket, ByteBuffer byteBuffer) {
        a(byteBuffer);
    }

    public void c(WebSocket webSocket, Framedata framedata) {
        b(framedata);
    }

    public final void a(WebSocket webSocket, f fVar) {
        this.k.countDown();
        a((h) fVar);
    }

    public final void a(WebSocket webSocket, int i, String str, boolean z) {
        this.k.countDown();
        this.l.countDown();
        if (this.h != null) {
            this.h.interrupt();
        }
        try {
            if (this.d != null) {
                this.d.close();
            }
        } catch (Exception e) {
            a((WebSocket) this, e);
        }
        a(i, str, z);
    }

    public final void a(WebSocket webSocket, Exception exception) {
        a(exception);
    }

    public final void b(WebSocket webSocket) {
    }

    public void a(WebSocket webSocket, int i, String str) {
        a(i, str);
    }

    public void b(WebSocket webSocket, int i, String str, boolean z) {
        b(i, str, z);
    }

    public void a(int i, String str) {
    }

    public void b(int i, String str, boolean z) {
    }

    public InetSocketAddress c(WebSocket webSocket) {
        if (this.d != null) {
            return (InetSocketAddress) this.d.getLocalSocketAddress();
        }
        return null;
    }

    public void a(ByteBuffer byteBuffer) {
    }

    public void b(Framedata framedata) {
    }

    public boolean g() {
        return this.c.c();
    }

    public boolean h() {
        return this.c.g();
    }

    public void a(Framedata framedata) {
        this.c.a(framedata);
    }

    public InetSocketAddress e() {
        return this.c.e();
    }
}
