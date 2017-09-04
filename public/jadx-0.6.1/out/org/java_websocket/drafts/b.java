package org.java_websocket.drafts;

import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import org.java_websocket.b.a;
import org.java_websocket.b.f;
import org.java_websocket.b.h;
import org.java_websocket.b.i;
import org.java_websocket.drafts.Draft.CloseHandshakeType;
import org.java_websocket.drafts.Draft.HandshakeState;
import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.exceptions.InvalidFrameException;
import org.java_websocket.exceptions.InvalidHandshakeException;
import org.java_websocket.exceptions.LimitExedeedException;
import org.java_websocket.exceptions.NotSendableException;
import org.java_websocket.framing.Framedata;
import org.java_websocket.framing.Framedata.Opcode;
import org.java_websocket.framing.c;
import org.java_websocket.framing.d;

/* compiled from: Draft_75 */
public class b extends Draft {
    protected boolean f = false;
    protected List<Framedata> g = new LinkedList();
    protected ByteBuffer h;
    private final Random i = new Random();

    public HandshakeState a(a aVar, h hVar) {
        return (aVar.b("WebSocket-Origin").equals(hVar.b("Origin")) && a((f) hVar)) ? HandshakeState.MATCHED : HandshakeState.NOT_MATCHED;
    }

    public HandshakeState a(a aVar) {
        if (aVar.c("Origin") && a((f) aVar)) {
            return HandshakeState.MATCHED;
        }
        return HandshakeState.NOT_MATCHED;
    }

    public ByteBuffer a(Framedata framedata) {
        if (framedata.f() != Opcode.TEXT) {
            throw new RuntimeException("only text frames supported");
        }
        ByteBuffer c = framedata.c();
        ByteBuffer allocate = ByteBuffer.allocate(c.remaining() + 2);
        allocate.put((byte) 0);
        c.mark();
        allocate.put(c);
        c.reset();
        allocate.put((byte) -1);
        allocate.flip();
        return allocate;
    }

    public List<Framedata> a(String str, boolean z) {
        c dVar = new d();
        try {
            dVar.a(ByteBuffer.wrap(org.java_websocket.c.b.a(str)));
            dVar.a(true);
            dVar.a(Opcode.TEXT);
            dVar.b(z);
            return Collections.singletonList(dVar);
        } catch (Throwable e) {
            throw new NotSendableException(e);
        }
    }

    public org.java_websocket.b.b a(org.java_websocket.b.b bVar) throws InvalidHandshakeException {
        bVar.a("Upgrade", "WebSocket");
        bVar.a("Connection", "Upgrade");
        if (!bVar.c("Origin")) {
            bVar.a("Origin", "random" + this.i.nextInt());
        }
        return bVar;
    }

    public org.java_websocket.b.c a(a aVar, i iVar) throws InvalidHandshakeException {
        iVar.a("Web Socket Protocol Handshake");
        iVar.a("Upgrade", "WebSocket");
        iVar.a("Connection", aVar.b("Connection"));
        iVar.a("WebSocket-Origin", aVar.b("Origin"));
        iVar.a("WebSocket-Location", "ws://" + aVar.b("Host") + aVar.a());
        return iVar;
    }

    protected List<Framedata> e(ByteBuffer byteBuffer) throws InvalidDataException {
        while (byteBuffer.hasRemaining()) {
            byte b = byteBuffer.get();
            if (b == (byte) 0) {
                if (this.f) {
                    throw new InvalidFrameException("unexpected START_OF_FRAME");
                }
                this.f = true;
            } else if (b == (byte) -1) {
                if (this.f) {
                    if (this.h != null) {
                        this.h.flip();
                        d dVar = new d();
                        dVar.a(this.h);
                        dVar.a(true);
                        dVar.a(Opcode.TEXT);
                        this.g.add(dVar);
                        this.h = null;
                        byteBuffer.mark();
                    }
                    this.f = false;
                } else {
                    throw new InvalidFrameException("unexpected END_OF_FRAME");
                }
            } else if (!this.f) {
                return null;
            } else {
                if (this.h == null) {
                    this.h = d();
                } else if (!this.h.hasRemaining()) {
                    this.h = f(this.h);
                }
                this.h.put(b);
            }
        }
        List<Framedata> list = this.g;
        this.g = new LinkedList();
        return list;
    }

    public List<Framedata> c(ByteBuffer byteBuffer) throws InvalidDataException {
        List<Framedata> e = e(byteBuffer);
        if (e != null) {
            return e;
        }
        throw new InvalidDataException(1002);
    }

    public void a() {
        this.f = false;
        this.h = null;
    }

    public CloseHandshakeType b() {
        return CloseHandshakeType.NONE;
    }

    public ByteBuffer d() {
        return ByteBuffer.allocate(b);
    }

    public ByteBuffer f(ByteBuffer byteBuffer) throws LimitExedeedException, InvalidDataException {
        byteBuffer.flip();
        ByteBuffer allocate = ByteBuffer.allocate(a(byteBuffer.capacity() * 2));
        allocate.put(byteBuffer);
        return allocate;
    }

    public Draft c() {
        return new b();
    }
}
