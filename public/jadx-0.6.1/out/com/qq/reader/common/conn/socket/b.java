package com.qq.reader.common.conn.socket;

import com.qq.reader.common.monitor.debug.c;
import java.net.URI;
import java.nio.channels.NotYetConnectedException;
import org.java_websocket.WebSocket;
import org.java_websocket.a.a;
import org.java_websocket.b.h;
import org.java_websocket.framing.Framedata;

/* compiled from: PushClient */
public class b extends a {
    private static int d = 0;
    private c c;

    public b(URI uri, c cVar) {
        super(uri);
        this.c = cVar;
    }

    public void a(h hVar) {
        c.b("PushClient", "onOpen ->>", true);
        d = 0;
        if (this.c != null) {
            this.c.a();
        }
    }

    public void a(String str) {
        c.b("PushClient", "onMessage ->> " + str, true);
        if (this.c != null) {
            this.c.a(str);
        }
    }

    public void a(int i, String str, boolean z) {
        c.b("PushClient", "onClose ->> " + i, true);
        if (i == 1000) {
            c.b("PushClient", "user close -->>", true);
        }
        if (d == 5) {
            c.b("PushClient", "onClose reached max count -->>", true);
            return;
        }
        if (i != 1000) {
            d++;
        }
        if (this.c != null) {
            this.c.a(i, str, z, a(d));
        }
    }

    private long a(int i) {
        return ((long) (Math.pow(2.0d, (double) Math.max(0, i - 1)) * 2.0d)) * 1000;
    }

    public void a(Exception exception) {
        c.a("PushClient", "onError ->> " + exception, true);
        if (d == 5) {
            c.b("PushClient", "onError reached max count -->>", true);
        } else if (this.c != null) {
            this.c.a(exception, a(d));
        }
    }

    public void a() {
        super.a();
        c.b("PushClient", "connect ->>", true);
    }

    public void b(String str) throws NotYetConnectedException {
        super.b(str);
        c.b("PushClient", "send text ->> " + str, true);
    }

    public void b() {
        super.b();
        c.b("PushClient", "sendPing ->> ", true);
    }

    public void a(WebSocket webSocket, Framedata framedata) {
        super.a(webSocket, framedata);
        c.b("PushClient", "onWebsocketPong ->> ", true);
        if (this.c != null) {
            this.c.b(webSocket, framedata);
        }
    }

    public void b(WebSocket webSocket, Framedata framedata) {
        super.b(webSocket, framedata);
        c.b("PushClient", "onWebsocketPing ->> ", true);
        if (this.c != null) {
            this.c.a(webSocket, framedata);
        }
    }

    public boolean c() throws InterruptedException {
        return super.c();
    }

    public void d() {
        d = 0;
    }
}
