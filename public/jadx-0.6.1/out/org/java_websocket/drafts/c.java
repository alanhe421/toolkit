package org.java_websocket.drafts;

import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import org.java_websocket.WebSocket$Role;
import org.java_websocket.b.a;
import org.java_websocket.b.b;
import org.java_websocket.b.f;
import org.java_websocket.b.h;
import org.java_websocket.b.i;
import org.java_websocket.drafts.Draft.CloseHandshakeType;
import org.java_websocket.drafts.Draft.HandshakeState;
import org.java_websocket.exceptions.IncompleteHandshakeException;
import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.exceptions.InvalidFrameException;
import org.java_websocket.exceptions.InvalidHandshakeException;
import org.java_websocket.framing.Framedata;
import org.java_websocket.framing.Framedata.Opcode;

/* compiled from: Draft_76 */
public class c extends b {
    private static final byte[] j;
    private boolean i = false;
    private final Random k = new Random();

    static {
        byte[] bArr = new byte[2];
        bArr[0] = (byte) -1;
        j = bArr;
    }

    public static byte[] a(String str, String str2, byte[] bArr) throws InvalidHandshakeException {
        byte[] a = a(str);
        byte[] a2 = a(str2);
        try {
            return MessageDigest.getInstance("MD5").digest(new byte[]{a[0], a[1], a[2], a[3], a2[0], a2[1], a2[2], a2[3], bArr[0], bArr[1], bArr[2], bArr[3], bArr[4], bArr[5], bArr[6], bArr[7]});
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    private static String e() {
        Random random = new Random();
        long nextInt = (long) (random.nextInt(12) + 1);
        String l = Long.toString(((long) (random.nextInt(Math.abs(new Long(4294967295L / nextInt).intValue())) + 1)) * nextInt);
        int nextInt2 = random.nextInt(12) + 1;
        for (int i = 0; i < nextInt2; i++) {
            int abs = Math.abs(random.nextInt(l.length()));
            char nextInt3 = (char) (random.nextInt(95) + 33);
            if (nextInt3 >= '0' && nextInt3 <= '9') {
                nextInt3 = (char) (nextInt3 - 15);
            }
            l = abs;
        }
        String str = l;
        for (int i2 = 0; ((long) i2) < nextInt; i2++) {
            str = Math.abs(random.nextInt(str.length() - 1) + 1);
        }
        return str;
    }

    private static byte[] a(String str) throws InvalidHandshakeException {
        try {
            long parseLong = Long.parseLong(str.replaceAll("[^0-9]", ""));
            long length = (long) (str.split(" ").length - 1);
            if (length == 0) {
                throw new InvalidHandshakeException("invalid Sec-WebSocket-Key (/key2/)");
            }
            parseLong = new Long(parseLong / length).longValue();
            return new byte[]{(byte) ((int) (parseLong >> 24)), (byte) ((int) ((parseLong << 8) >> 24)), (byte) ((int) ((parseLong << 16) >> 24)), (byte) ((int) ((parseLong << 24) >> 24))};
        } catch (NumberFormatException e) {
            throw new InvalidHandshakeException("invalid Sec-WebSocket-Key (/key1/ or /key2/)");
        }
    }

    public HandshakeState a(a aVar, h hVar) {
        if (this.i) {
            return HandshakeState.NOT_MATCHED;
        }
        try {
            if (!hVar.b("Sec-WebSocket-Origin").equals(aVar.b("Origin")) || !a((f) hVar)) {
                return HandshakeState.NOT_MATCHED;
            }
            byte[] c = hVar.c();
            if (c == null || c.length == 0) {
                throw new IncompleteHandshakeException();
            } else if (Arrays.equals(c, a(aVar.b("Sec-WebSocket-Key1"), aVar.b("Sec-WebSocket-Key2"), aVar.c()))) {
                return HandshakeState.MATCHED;
            } else {
                return HandshakeState.NOT_MATCHED;
            }
        } catch (Throwable e) {
            throw new RuntimeException("bad handshakerequest", e);
        }
    }

    public HandshakeState a(a aVar) {
        if (aVar.b("Upgrade").equals("WebSocket") && aVar.b("Connection").contains("Upgrade") && aVar.b("Sec-WebSocket-Key1").length() > 0 && !aVar.b("Sec-WebSocket-Key2").isEmpty() && aVar.c("Origin")) {
            return HandshakeState.MATCHED;
        }
        return HandshakeState.NOT_MATCHED;
    }

    public b a(b bVar) {
        bVar.a("Upgrade", "WebSocket");
        bVar.a("Connection", "Upgrade");
        bVar.a("Sec-WebSocket-Key1", e());
        bVar.a("Sec-WebSocket-Key2", e());
        if (!bVar.c("Origin")) {
            bVar.a("Origin", "random" + this.k.nextInt());
        }
        byte[] bArr = new byte[8];
        this.k.nextBytes(bArr);
        bVar.a(bArr);
        return bVar;
    }

    public org.java_websocket.b.c a(a aVar, i iVar) throws InvalidHandshakeException {
        iVar.a("WebSocket Protocol Handshake");
        iVar.a("Upgrade", "WebSocket");
        iVar.a("Connection", aVar.b("Connection"));
        iVar.a("Sec-WebSocket-Origin", aVar.b("Origin"));
        iVar.a("Sec-WebSocket-Location", "ws://" + aVar.b("Host") + aVar.a());
        String b = aVar.b("Sec-WebSocket-Key1");
        String b2 = aVar.b("Sec-WebSocket-Key2");
        byte[] c = aVar.c();
        if (b == null || b2 == null || c == null || c.length != 8) {
            throw new InvalidHandshakeException("Bad keys");
        }
        iVar.a(a(b, b2, c));
        return iVar;
    }

    public f d(ByteBuffer byteBuffer) throws InvalidHandshakeException {
        f a = Draft.a(byteBuffer, this.d);
        if ((a.c("Sec-WebSocket-Key1") || this.d == WebSocket$Role.CLIENT) && !a.c("Sec-WebSocket-Version")) {
            byte[] bArr = new byte[(this.d == WebSocket$Role.SERVER ? 8 : 16)];
            try {
                byteBuffer.get(bArr);
                a.a(bArr);
            } catch (BufferUnderflowException e) {
                throw new IncompleteHandshakeException(byteBuffer.capacity() + 16);
            }
        }
        return a;
    }

    public List<Framedata> c(ByteBuffer byteBuffer) throws InvalidDataException {
        byteBuffer.mark();
        List<Framedata> e = super.e(byteBuffer);
        if (e == null) {
            byteBuffer.reset();
            e = this.g;
            this.f = true;
            if (this.h == null) {
                this.h = ByteBuffer.allocate(2);
                if (byteBuffer.remaining() > this.h.remaining()) {
                    throw new InvalidFrameException();
                }
                this.h.put(byteBuffer);
                if (this.h.hasRemaining()) {
                    this.g = new LinkedList();
                } else if (Arrays.equals(this.h.array(), j)) {
                    e.add(new org.java_websocket.framing.b(1000));
                } else {
                    throw new InvalidFrameException();
                }
            }
            throw new InvalidFrameException();
        }
        return e;
    }

    public ByteBuffer a(Framedata framedata) {
        if (framedata.f() == Opcode.CLOSING) {
            return ByteBuffer.wrap(j);
        }
        return super.a(framedata);
    }

    public CloseHandshakeType b() {
        return CloseHandshakeType.ONEWAY;
    }

    public Draft c() {
        return new c();
    }
}
