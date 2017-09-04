package org.java_websocket.drafts;

import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.java_websocket.WebSocket$Role;
import org.java_websocket.b.a;
import org.java_websocket.b.c;
import org.java_websocket.b.d;
import org.java_websocket.b.e;
import org.java_websocket.b.f;
import org.java_websocket.b.h;
import org.java_websocket.b.i;
import org.java_websocket.c.b;
import org.java_websocket.exceptions.IncompleteHandshakeException;
import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.exceptions.InvalidHandshakeException;
import org.java_websocket.exceptions.LimitExedeedException;
import org.java_websocket.framing.Framedata;
import org.java_websocket.framing.Framedata.Opcode;

public abstract class Draft {
    public static int a = 1000;
    public static int b = 64;
    public static final byte[] c = b.a("<policy-file-request/>\u0000");
    protected WebSocket$Role d = null;
    protected Opcode e = null;

    public enum CloseHandshakeType {
        NONE,
        ONEWAY,
        TWOWAY
    }

    public enum HandshakeState {
        MATCHED,
        NOT_MATCHED
    }

    public abstract ByteBuffer a(Framedata framedata);

    public abstract List<Framedata> a(String str, boolean z);

    public abstract org.java_websocket.b.b a(org.java_websocket.b.b bVar) throws InvalidHandshakeException;

    public abstract c a(a aVar, i iVar) throws InvalidHandshakeException;

    public abstract HandshakeState a(a aVar) throws InvalidHandshakeException;

    public abstract HandshakeState a(a aVar, h hVar) throws InvalidHandshakeException;

    public abstract void a();

    public abstract CloseHandshakeType b();

    public abstract List<Framedata> c(ByteBuffer byteBuffer) throws InvalidDataException;

    public abstract Draft c();

    public static ByteBuffer a(ByteBuffer byteBuffer) {
        ByteBuffer allocate = ByteBuffer.allocate(byteBuffer.remaining());
        byte b = (byte) 48;
        while (byteBuffer.hasRemaining()) {
            byte b2 = byteBuffer.get();
            allocate.put(b2);
            if (b == (byte) 13 && b2 == (byte) 10) {
                allocate.limit(allocate.position() - 2);
                allocate.position(0);
                return allocate;
            }
            b = b2;
        }
        byteBuffer.position(byteBuffer.position() - allocate.position());
        return null;
    }

    public static String b(ByteBuffer byteBuffer) {
        ByteBuffer a = a(byteBuffer);
        return a == null ? null : b.a(a.array(), 0, a.limit());
    }

    public static c a(ByteBuffer byteBuffer, WebSocket$Role webSocket$Role) throws InvalidHandshakeException, IncompleteHandshakeException {
        String b = b(byteBuffer);
        if (b == null) {
            throw new IncompleteHandshakeException(byteBuffer.capacity() + 128);
        }
        String[] split = b.split(" ", 3);
        if (split.length != 3) {
            throw new InvalidHandshakeException();
        }
        c eVar;
        if (webSocket$Role == WebSocket$Role.CLIENT) {
            eVar = new e();
            i iVar = (i) eVar;
            iVar.a(Short.parseShort(split[1]));
            iVar.a(split[2]);
        } else {
            eVar = new d();
            eVar.a(split[1]);
        }
        b = b(byteBuffer);
        while (b != null && b.length() > 0) {
            String[] split2 = b.split(":", 2);
            if (split2.length != 2) {
                throw new InvalidHandshakeException("not an http header");
            }
            eVar.a(split2[0], split2[1].replaceFirst("^ +", ""));
            b = b(byteBuffer);
        }
        if (b != null) {
            return eVar;
        }
        throw new IncompleteHandshakeException();
    }

    protected boolean a(f fVar) {
        return fVar.b("Upgrade").equalsIgnoreCase("websocket") && fVar.b("Connection").toLowerCase(Locale.ENGLISH).contains("upgrade");
    }

    public List<ByteBuffer> a(f fVar, WebSocket$Role webSocket$Role) {
        return a(fVar, webSocket$Role, true);
    }

    public List<ByteBuffer> a(f fVar, WebSocket$Role webSocket$Role, boolean z) {
        StringBuilder stringBuilder = new StringBuilder(100);
        if (fVar instanceof a) {
            stringBuilder.append("GET ");
            stringBuilder.append(((a) fVar).a());
            stringBuilder.append(" HTTP/1.1");
        } else if (fVar instanceof h) {
            stringBuilder.append("HTTP/1.1 101 " + ((h) fVar).a());
        } else {
            throw new RuntimeException("unknow role");
        }
        stringBuilder.append("\r\n");
        Iterator b = fVar.b();
        while (b.hasNext()) {
            String str = (String) b.next();
            String b2 = fVar.b(str);
            stringBuilder.append(str);
            stringBuilder.append(": ");
            stringBuilder.append(b2);
            stringBuilder.append("\r\n");
        }
        stringBuilder.append("\r\n");
        byte[] b3 = b.b(stringBuilder.toString());
        byte[] c = z ? fVar.c() : null;
        ByteBuffer allocate = ByteBuffer.allocate((c == null ? 0 : c.length) + b3.length);
        allocate.put(b3);
        if (c != null) {
            allocate.put(c);
        }
        allocate.flip();
        return Collections.singletonList(allocate);
    }

    public f d(ByteBuffer byteBuffer) throws InvalidHandshakeException {
        return a(byteBuffer, this.d);
    }

    public int a(int i) throws LimitExedeedException, InvalidDataException {
        if (i >= 0) {
            return i;
        }
        throw new InvalidDataException(1002, "Negative count");
    }

    public void a(WebSocket$Role webSocket$Role) {
        this.d = webSocket$Role;
    }
}
