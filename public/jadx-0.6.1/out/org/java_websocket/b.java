package org.java_websocket;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.SelectionKey;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.java_websocket.b.a;
import org.java_websocket.b.f;
import org.java_websocket.b.h;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft.CloseHandshakeType;
import org.java_websocket.drafts.Draft.HandshakeState;
import org.java_websocket.drafts.Draft_10;
import org.java_websocket.drafts.c;
import org.java_websocket.exceptions.IncompleteHandshakeException;
import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.exceptions.InvalidHandshakeException;
import org.java_websocket.exceptions.WebsocketNotConnectedException;
import org.java_websocket.framing.Framedata;
import org.java_websocket.framing.Framedata.Opcode;
import org.java_websocket.framing.d;

/* compiled from: WebSocketImpl */
public class b implements WebSocket {
    public static int a = 16384;
    public static boolean b = false;
    public static final List<Draft> c = new ArrayList(4);
    static final /* synthetic */ boolean h = (!b.class.desiredAssertionStatus());
    public SelectionKey d;
    public ByteChannel e;
    public final BlockingQueue<ByteBuffer> f;
    public final BlockingQueue<ByteBuffer> g;
    private volatile boolean i = false;
    private WebSocket$READYSTATE j = WebSocket$READYSTATE.NOT_YET_CONNECTED;
    private final c k;
    private List<Draft> l;
    private Draft m = null;
    private WebSocket$Role n;
    private Opcode o = null;
    private ByteBuffer p = ByteBuffer.allocate(0);
    private a q = null;
    private String r = null;
    private Integer s = null;
    private Boolean t = null;
    private String u = null;

    static {
        c.add(new org.java_websocket.drafts.a());
        c.add(new Draft_10());
        c.add(new c());
        c.add(new org.java_websocket.drafts.b());
    }

    public b(c cVar, Draft draft) {
        if (cVar == null || (draft == null && this.n == WebSocket$Role.SERVER)) {
            throw new IllegalArgumentException("parameters must not be null");
        }
        this.f = new LinkedBlockingQueue();
        this.g = new LinkedBlockingQueue();
        this.k = cVar;
        this.n = WebSocket$Role.CLIENT;
        if (draft != null) {
            this.m = draft.c();
        }
    }

    public void a(ByteBuffer byteBuffer) {
        if (h || byteBuffer.hasRemaining()) {
            if (b) {
                System.out.println("process(" + byteBuffer.remaining() + "): {" + (byteBuffer.remaining() > 1000 ? "too big to display" : new String(byteBuffer.array(), byteBuffer.position(), byteBuffer.remaining())) + "}");
            }
            if (this.j != WebSocket$READYSTATE.NOT_YET_CONNECTED) {
                c(byteBuffer);
            } else if (b(byteBuffer)) {
                if (!h && this.p.hasRemaining() == byteBuffer.hasRemaining() && byteBuffer.hasRemaining()) {
                    throw new AssertionError();
                } else if (byteBuffer.hasRemaining()) {
                    c(byteBuffer);
                } else if (this.p.hasRemaining()) {
                    c(this.p);
                }
            }
            if (!h && !d() && !f() && byteBuffer.hasRemaining()) {
                throw new AssertionError();
            }
            return;
        }
        throw new AssertionError();
    }

    private boolean b(ByteBuffer byteBuffer) {
        ByteBuffer byteBuffer2;
        if (this.p.capacity() == 0) {
            byteBuffer2 = byteBuffer;
        } else {
            if (this.p.remaining() < byteBuffer.remaining()) {
                ByteBuffer allocate = ByteBuffer.allocate(this.p.capacity() + byteBuffer.remaining());
                this.p.flip();
                allocate.put(this.p);
                this.p = allocate;
            }
            this.p.put(byteBuffer);
            this.p.flip();
            byteBuffer2 = this.p;
        }
        byteBuffer2.mark();
        try {
            if (this.m == null && d(byteBuffer2) == HandshakeState.MATCHED) {
                try {
                    e(ByteBuffer.wrap(org.java_websocket.c.b.a(this.k.a(this))));
                    a(-3, "");
                } catch (InvalidDataException e) {
                    c(1006, "remote peer closed connection before flashpolicy could be transmitted", true);
                }
                return false;
            }
            try {
                f d;
                if (this.n != WebSocket$Role.SERVER) {
                    if (this.n == WebSocket$Role.CLIENT) {
                        this.m.a(this.n);
                        d = this.m.d(byteBuffer2);
                        if (d instanceof h) {
                            d = (h) d;
                            if (this.m.a(this.q, (h) d) == HandshakeState.MATCHED) {
                                try {
                                    this.k.a(this, this.q, d);
                                    a(d);
                                    return true;
                                } catch (InvalidDataException e2) {
                                    b(e2.getCloseCode(), e2.getMessage(), false);
                                    return false;
                                } catch (Exception e3) {
                                    this.k.a(this, e3);
                                    b(-1, e3.getMessage(), false);
                                    return false;
                                }
                            }
                            a(1002, "draft " + this.m + " refuses handshake");
                        } else {
                            b(1002, "wrong http function", false);
                            return false;
                        }
                    }
                    return false;
                } else if (this.m == null) {
                    for (Draft c : this.l) {
                        Draft c2 = c.c();
                        try {
                            c2.a(this.n);
                            byteBuffer2.reset();
                            d = c2.d(byteBuffer2);
                            if (d instanceof a) {
                                d = (a) d;
                                if (c2.a((a) d) == HandshakeState.MATCHED) {
                                    this.u = d.a();
                                    try {
                                        a(c2.a(c2.a((a) d, this.k.a(this, c2, d)), this.n));
                                        this.m = c2;
                                        a(d);
                                        return true;
                                    } catch (InvalidDataException e22) {
                                        b(e22.getCloseCode(), e22.getMessage(), false);
                                        return false;
                                    } catch (Exception e32) {
                                        this.k.a(this, e32);
                                        b(-1, e32.getMessage(), false);
                                        return false;
                                    }
                                }
                                continue;
                            } else {
                                b(1002, "wrong http function", false);
                                return false;
                            }
                        } catch (InvalidHandshakeException e4) {
                        }
                    }
                    if (this.m == null) {
                        a(1002, "no draft matches");
                    }
                    return false;
                } else {
                    d = this.m.d(byteBuffer2);
                    if (d instanceof a) {
                        d = (a) d;
                        if (this.m.a((a) d) == HandshakeState.MATCHED) {
                            a(d);
                            return true;
                        }
                        a(1002, "the handshake did finaly not match");
                        return false;
                    }
                    b(1002, "wrong http function", false);
                    return false;
                }
            } catch (InvalidDataException e222) {
                a(e222);
            }
        } catch (IncompleteHandshakeException e5) {
            IncompleteHandshakeException incompleteHandshakeException = e5;
            if (this.p.capacity() == 0) {
                byteBuffer2.reset();
                int preferedSize = incompleteHandshakeException.getPreferedSize();
                if (preferedSize == 0) {
                    preferedSize = byteBuffer2.capacity() + 16;
                } else if (!h && incompleteHandshakeException.getPreferedSize() < byteBuffer2.remaining()) {
                    throw new AssertionError();
                }
                this.p = ByteBuffer.allocate(preferedSize);
                this.p.put(byteBuffer);
            } else {
                this.p.position(this.p.limit());
                this.p.limit(this.p.capacity());
            }
        }
    }

    private void c(ByteBuffer byteBuffer) {
        try {
            for (Framedata framedata : this.m.c(byteBuffer)) {
                if (b) {
                    System.out.println("matched frame: " + framedata);
                }
                Opcode f = framedata.f();
                boolean d = framedata.d();
                if (f == Opcode.CLOSING) {
                    int a;
                    String b;
                    String str = "";
                    if (framedata instanceof org.java_websocket.framing.a) {
                        org.java_websocket.framing.a aVar = (org.java_websocket.framing.a) framedata;
                        a = aVar.a();
                        b = aVar.b();
                    } else {
                        b = str;
                        a = 1005;
                    }
                    if (this.j == WebSocket$READYSTATE.CLOSING) {
                        a(a, b, true);
                    } else if (this.m.b() == CloseHandshakeType.TWOWAY) {
                        c(a, b, true);
                    } else {
                        b(a, b, false);
                    }
                } else if (f == Opcode.PING) {
                    this.k.b(this, framedata);
                } else if (f == Opcode.PONG) {
                    this.k.a(this, framedata);
                } else if (!d || f == Opcode.CONTINUOUS) {
                    if (f != Opcode.CONTINUOUS) {
                        if (this.o != null) {
                            throw new InvalidDataException(1002, "Previous continuous frame sequence not completed.");
                        }
                        this.o = f;
                    } else if (d) {
                        if (this.o == null) {
                            throw new InvalidDataException(1002, "Continuous frame sequence was not started.");
                        }
                        this.o = null;
                    } else if (this.o == null) {
                        throw new InvalidDataException(1002, "Continuous frame sequence was not started.");
                    }
                    try {
                        this.k.c(this, framedata);
                    } catch (Exception e) {
                        this.k.a(this, e);
                    }
                } else if (this.o != null) {
                    throw new InvalidDataException(1002, "Continuous frame sequence not completed.");
                } else if (f == Opcode.TEXT) {
                    try {
                        this.k.a(this, org.java_websocket.c.b.a(framedata.c()));
                    } catch (Exception e2) {
                        this.k.a(this, e2);
                    }
                } else if (f == Opcode.BINARY) {
                    try {
                        this.k.a(this, framedata.c());
                    } catch (Exception e22) {
                        this.k.a(this, e22);
                    }
                } else {
                    throw new InvalidDataException(1002, "non control or continious frame expected");
                }
            }
        } catch (InvalidDataException e3) {
            this.k.a(this, e3);
            a(e3);
        }
    }

    private void c(int i, String str, boolean z) {
        if (this.j != WebSocket$READYSTATE.CLOSING && this.j != WebSocket$READYSTATE.CLOSED) {
            if (this.j == WebSocket$READYSTATE.OPEN) {
                if (i != 1006) {
                    if (this.m.b() != CloseHandshakeType.NONE) {
                        if (!z) {
                            try {
                                this.k.a(this, i, str);
                            } catch (Exception e) {
                                this.k.a(this, e);
                            }
                        }
                        try {
                            a(new org.java_websocket.framing.b(i, str));
                        } catch (Exception e2) {
                            this.k.a(this, e2);
                            b(1006, "generated frame is invalid", false);
                        }
                    }
                    b(i, str, z);
                } else if (h || !z) {
                    this.j = WebSocket$READYSTATE.CLOSING;
                    b(i, str, false);
                    return;
                } else {
                    throw new AssertionError();
                }
            } else if (i != -3) {
                b(-1, str, false);
            } else if (h || z) {
                b(-3, str, true);
            } else {
                throw new AssertionError();
            }
            if (i == 1002) {
                b(i, str, z);
            }
            this.j = WebSocket$READYSTATE.CLOSING;
            this.p = null;
        }
    }

    public void a(int i, String str) {
        c(i, str, false);
    }

    protected synchronized void a(int i, String str, boolean z) {
        if (this.j != WebSocket$READYSTATE.CLOSED) {
            if (this.d != null) {
                this.d.cancel();
            }
            if (this.e != null) {
                try {
                    this.e.close();
                } catch (Exception e) {
                    this.k.a(this, e);
                }
            }
            try {
                this.k.a(this, i, str, z);
            } catch (Exception e2) {
                this.k.a(this, e2);
            }
            if (this.m != null) {
                this.m.a();
            }
            this.q = null;
            this.j = WebSocket$READYSTATE.CLOSED;
            this.f.clear();
        }
    }

    protected void a(int i, boolean z) {
        a(i, "", z);
    }

    public void b(int i, String str) {
        a(i, str, false);
    }

    protected synchronized void b(int i, String str, boolean z) {
        if (!this.i) {
            this.s = Integer.valueOf(i);
            this.r = str;
            this.t = Boolean.valueOf(z);
            this.i = true;
            this.k.b(this);
            try {
                this.k.b(this, i, str, z);
            } catch (Exception e) {
                this.k.a(this, e);
            }
            if (this.m != null) {
                this.m.a();
            }
            this.q = null;
        }
    }

    public void a() {
        if (h() == WebSocket$READYSTATE.NOT_YET_CONNECTED) {
            a(-1, true);
        } else if (this.i) {
            a(this.s.intValue(), this.r, this.t.booleanValue());
        } else if (this.m.b() == CloseHandshakeType.NONE) {
            a(1000, true);
        } else if (this.m.b() != CloseHandshakeType.ONEWAY) {
            a(1006, true);
        } else if (this.n == WebSocket$Role.SERVER) {
            a(1006, true);
        } else {
            a(1000, true);
        }
    }

    public void a(int i) {
        c(i, "", false);
    }

    public void a(InvalidDataException invalidDataException) {
        c(invalidDataException.getCloseCode(), invalidDataException.getMessage(), false);
    }

    public void a(String str) throws WebsocketNotConnectedException {
        if (str == null) {
            throw new IllegalArgumentException("Cannot send 'null' data to a WebSocketImpl.");
        }
        a(this.m.a(str, this.n == WebSocket$Role.CLIENT));
    }

    private void a(Collection<Framedata> collection) {
        if (c()) {
            for (Framedata a : collection) {
                a(a);
            }
            return;
        }
        throw new WebsocketNotConnectedException();
    }

    public void b() {
        Framedata dVar = new d(Opcode.PING);
        dVar.a(true);
        a(dVar);
    }

    public void a(Framedata framedata) {
        if (b) {
            System.out.println("send frame: " + framedata);
        }
        e(this.m.a(framedata));
    }

    private HandshakeState d(ByteBuffer byteBuffer) throws IncompleteHandshakeException {
        byteBuffer.mark();
        if (byteBuffer.limit() > Draft.c.length) {
            return HandshakeState.NOT_MATCHED;
        }
        if (byteBuffer.limit() < Draft.c.length) {
            throw new IncompleteHandshakeException(Draft.c.length);
        }
        int i = 0;
        while (byteBuffer.hasRemaining()) {
            if (Draft.c[i] != byteBuffer.get()) {
                byteBuffer.reset();
                return HandshakeState.NOT_MATCHED;
            }
            i++;
        }
        return HandshakeState.MATCHED;
    }

    public void a(org.java_websocket.b.b bVar) throws InvalidHandshakeException {
        if (h || this.j != WebSocket$READYSTATE.CONNECTING) {
            this.q = this.m.a(bVar);
            this.u = bVar.a();
            if (h || this.u != null) {
                try {
                    this.k.a(this, this.q);
                    a(this.m.a(this.q, this.n));
                    return;
                } catch (InvalidDataException e) {
                    throw new InvalidHandshakeException("Handshake data rejected by client.");
                } catch (Exception e2) {
                    this.k.a(this, e2);
                    throw new InvalidHandshakeException("rejected because of" + e2);
                }
            }
            throw new AssertionError();
        }
        throw new AssertionError("shall only be called once");
    }

    private void e(ByteBuffer byteBuffer) {
        if (b) {
            System.out.println("write(" + byteBuffer.remaining() + "): {" + (byteBuffer.remaining() > 1000 ? "too big to display" : new String(byteBuffer.array())) + "}");
        }
        this.f.add(byteBuffer);
        this.k.b(this);
    }

    private void a(List<ByteBuffer> list) {
        for (ByteBuffer e : list) {
            e(e);
        }
    }

    private void a(f fVar) {
        if (b) {
            System.out.println("open using draft: " + this.m.getClass().getSimpleName());
        }
        this.j = WebSocket$READYSTATE.OPEN;
        try {
            this.k.a(this, fVar);
        } catch (Exception e) {
            this.k.a(this, e);
        }
    }

    public boolean c() {
        if (h || this.j != WebSocket$READYSTATE.OPEN || !this.i) {
            return this.j == WebSocket$READYSTATE.OPEN;
        } else {
            throw new AssertionError();
        }
    }

    public boolean d() {
        return this.j == WebSocket$READYSTATE.CLOSING;
    }

    public boolean f() {
        return this.i;
    }

    public boolean g() {
        return this.j == WebSocket$READYSTATE.CLOSED;
    }

    public WebSocket$READYSTATE h() {
        return this.j;
    }

    public int hashCode() {
        return super.hashCode();
    }

    public String toString() {
        return super.toString();
    }

    public InetSocketAddress e() {
        return this.k.c(this);
    }
}
