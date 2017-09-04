package org.java_websocket.drafts;

import com.tencent.tinker.android.dx.instruction.Opcodes;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import org.java_websocket.WebSocket$Role;
import org.java_websocket.b.a;
import org.java_websocket.b.f;
import org.java_websocket.b.h;
import org.java_websocket.b.i;
import org.java_websocket.c.b;
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

public class Draft_10 extends Draft {
    static final /* synthetic */ boolean f = (!Draft_10.class.desiredAssertionStatus());
    private ByteBuffer g;
    private Framedata h = null;
    private final Random i = new Random();

    private class IncompleteException extends Throwable {
        private static final long serialVersionUID = 7330519489840500997L;
        private int preferedsize;

        public IncompleteException(int i) {
            this.preferedsize = i;
        }

        public int getPreferedSize() {
            return this.preferedsize;
        }
    }

    public static int b(f fVar) {
        int i = -1;
        String b = fVar.b("Sec-WebSocket-Version");
        if (b.length() > 0) {
            try {
                i = new Integer(b.trim()).intValue();
            } catch (NumberFormatException e) {
            }
        }
        return i;
    }

    public HandshakeState a(a aVar, h hVar) throws InvalidHandshakeException {
        if (!aVar.c("Sec-WebSocket-Key") || !hVar.c("Sec-WebSocket-Accept")) {
            return HandshakeState.NOT_MATCHED;
        }
        if (a(aVar.b("Sec-WebSocket-Key")).equals(hVar.b("Sec-WebSocket-Accept"))) {
            return HandshakeState.MATCHED;
        }
        return HandshakeState.NOT_MATCHED;
    }

    public HandshakeState a(a aVar) throws InvalidHandshakeException {
        int b = b(aVar);
        if (b == 7 || b == 8) {
            return a((f) aVar) ? HandshakeState.MATCHED : HandshakeState.NOT_MATCHED;
        } else {
            return HandshakeState.NOT_MATCHED;
        }
    }

    public ByteBuffer a(Framedata framedata) {
        int i;
        int i2 = -128;
        int i3 = 0;
        ByteBuffer c = framedata.c();
        int i4 = this.d == WebSocket$Role.CLIENT ? 1 : 0;
        int i5 = c.remaining() <= Opcodes.NEG_LONG ? 1 : c.remaining() <= 65535 ? 2 : 8;
        if (i5 > 1) {
            i = i5 + 1;
        } else {
            i = i5;
        }
        ByteBuffer allocate = ByteBuffer.allocate(((i4 != 0 ? 4 : 0) + (i + 1)) + c.remaining());
        byte a = a(framedata.f());
        if (framedata.d()) {
            i = -128;
        } else {
            i = 0;
        }
        allocate.put((byte) (((byte) i) | a));
        byte[] a2 = a((long) c.remaining(), i5);
        if (f || a2.length == i5) {
            if (i5 == 1) {
                byte b = a2[0];
                if (i4 == 0) {
                    i2 = 0;
                }
                allocate.put((byte) (b | i2));
            } else if (i5 == 2) {
                if (i4 == 0) {
                    i2 = 0;
                }
                allocate.put((byte) (i2 | Opcodes.NOT_LONG));
                allocate.put(a2);
            } else if (i5 == 8) {
                if (i4 == 0) {
                    i2 = 0;
                }
                allocate.put((byte) (i2 | Opcodes.NEG_FLOAT));
                allocate.put(a2);
            } else {
                throw new RuntimeException("Size representation not supported/specified");
            }
            if (i4 != 0) {
                ByteBuffer allocate2 = ByteBuffer.allocate(4);
                allocate2.putInt(this.i.nextInt());
                allocate.put(allocate2.array());
                while (c.hasRemaining()) {
                    allocate.put((byte) (c.get() ^ allocate2.get(i3 % 4)));
                    i3++;
                }
            } else {
                allocate.put(c);
            }
            if (f || allocate.remaining() == 0) {
                allocate.flip();
                return allocate;
            }
            throw new AssertionError(allocate.remaining());
        }
        throw new AssertionError();
    }

    public List<Framedata> a(String str, boolean z) {
        c dVar = new d();
        try {
            dVar.a(ByteBuffer.wrap(b.a(str)));
            dVar.a(true);
            dVar.a(Opcode.TEXT);
            dVar.b(z);
            return Collections.singletonList(dVar);
        } catch (Throwable e) {
            throw new NotSendableException(e);
        }
    }

    private byte a(Opcode opcode) {
        if (opcode == Opcode.CONTINUOUS) {
            return (byte) 0;
        }
        if (opcode == Opcode.TEXT) {
            return (byte) 1;
        }
        if (opcode == Opcode.BINARY) {
            return (byte) 2;
        }
        if (opcode == Opcode.CLOSING) {
            return (byte) 8;
        }
        if (opcode == Opcode.PING) {
            return (byte) 9;
        }
        if (opcode == Opcode.PONG) {
            return (byte) 10;
        }
        throw new RuntimeException("Don't know how to handle " + opcode.toString());
    }

    private String a(String str) {
        String str2 = str.trim() + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";
        try {
            return org.java_websocket.c.a.a(MessageDigest.getInstance("SHA1").digest(str2.getBytes()));
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public org.java_websocket.b.b a(org.java_websocket.b.b bVar) {
        bVar.a("Upgrade", "websocket");
        bVar.a("Connection", "Upgrade");
        bVar.a("Sec-WebSocket-Version", "8");
        byte[] bArr = new byte[16];
        this.i.nextBytes(bArr);
        bVar.a("Sec-WebSocket-Key", org.java_websocket.c.a.a(bArr));
        return bVar;
    }

    public org.java_websocket.b.c a(a aVar, i iVar) throws InvalidHandshakeException {
        iVar.a("Upgrade", "websocket");
        iVar.a("Connection", aVar.b("Connection"));
        iVar.a("Switching Protocols");
        String b = aVar.b("Sec-WebSocket-Key");
        if (b == null) {
            throw new InvalidHandshakeException("missing Sec-WebSocket-Key");
        }
        iVar.a("Sec-WebSocket-Accept", a(b));
        return iVar;
    }

    private byte[] a(long j, int i) {
        byte[] bArr = new byte[i];
        int i2 = (i * 8) - 8;
        for (int i3 = 0; i3 < i; i3++) {
            bArr[i3] = (byte) ((int) (j >>> (i2 - (i3 * 8))));
        }
        return bArr;
    }

    private Opcode a(byte b) throws InvalidFrameException {
        switch (b) {
            case (byte) 0:
                return Opcode.CONTINUOUS;
            case (byte) 1:
                return Opcode.TEXT;
            case (byte) 2:
                return Opcode.BINARY;
            case (byte) 8:
                return Opcode.CLOSING;
            case (byte) 9:
                return Opcode.PING;
            case (byte) 10:
                return Opcode.PONG;
            default:
                throw new InvalidFrameException("unknow optcode " + ((short) b));
        }
    }

    public List<Framedata> c(ByteBuffer byteBuffer) throws LimitExedeedException, InvalidDataException {
        List<Framedata> linkedList = new LinkedList();
        if (this.g != null) {
            try {
                byteBuffer.mark();
                int remaining = byteBuffer.remaining();
                int remaining2 = this.g.remaining();
                if (remaining2 > remaining) {
                    this.g.put(byteBuffer.array(), byteBuffer.position(), remaining);
                    byteBuffer.position(remaining + byteBuffer.position());
                    return Collections.emptyList();
                }
                this.g.put(byteBuffer.array(), byteBuffer.position(), remaining2);
                byteBuffer.position(byteBuffer.position() + remaining2);
                linkedList.add(e((ByteBuffer) this.g.duplicate().position(0)));
                this.g = null;
            } catch (IncompleteException e) {
                this.g.limit();
                r0 = ByteBuffer.allocate(a(e.getPreferedSize()));
                if (f || r0.limit() > this.g.limit()) {
                    ByteBuffer allocate;
                    this.g.rewind();
                    allocate.put(this.g);
                    this.g = allocate;
                    return c(byteBuffer);
                }
                throw new AssertionError();
            }
        }
        while (byteBuffer.hasRemaining()) {
            byteBuffer.mark();
            try {
                linkedList.add(e(byteBuffer));
            } catch (IncompleteException e2) {
                byteBuffer.reset();
                this.g = ByteBuffer.allocate(a(e2.getPreferedSize()));
                this.g.put(byteBuffer);
            }
        }
        return linkedList;
    }

    public Framedata e(ByteBuffer byteBuffer) throws IncompleteException, InvalidDataException {
        int i = 2;
        int i2 = 0;
        int remaining = byteBuffer.remaining();
        if (remaining < 2) {
            throw new IncompleteException(2);
        }
        boolean z;
        byte b = byteBuffer.get();
        if ((b >> 8) != 0) {
            z = true;
        } else {
            z = false;
        }
        byte b2 = (byte) ((b & Opcodes.NEG_FLOAT) >> 4);
        if (b2 != (byte) 0) {
            throw new InvalidFrameException("bad rsv " + b2);
        }
        byte b3 = byteBuffer.get();
        int i3 = (b3 & -128) != 0 ? 1 : 0;
        int i4 = (byte) (b3 & Opcodes.NEG_FLOAT);
        Opcode a = a((byte) (b & 15));
        if (z || !(a == Opcode.PING || a == Opcode.PONG || a == Opcode.CLOSING)) {
            int i5;
            if (i4 < 0 || i4 > Opcodes.NEG_LONG) {
                if (a == Opcode.PING || a == Opcode.PONG || a == Opcode.CLOSING) {
                    throw new InvalidFrameException("more than 125 octets");
                } else if (i4 == Opcodes.NOT_LONG) {
                    if (remaining < 4) {
                        throw new IncompleteException(4);
                    }
                    byte[] bArr = new byte[3];
                    bArr[1] = byteBuffer.get();
                    bArr[2] = byteBuffer.get();
                    i4 = new BigInteger(bArr).intValue();
                    i = 4;
                } else if (remaining < 10) {
                    throw new IncompleteException(10);
                } else {
                    byte[] bArr2 = new byte[8];
                    for (i5 = 0; i5 < 8; i5++) {
                        bArr2[i5] = byteBuffer.get();
                    }
                    long longValue = new BigInteger(bArr2).longValue();
                    if (longValue > 2147483647L) {
                        throw new LimitExedeedException("Payloadsize is to big...");
                    }
                    i = 10;
                    i4 = (int) longValue;
                }
            }
            if (i3 != 0) {
                i5 = 4;
            } else {
                i5 = 0;
            }
            i5 = (i5 + i) + i4;
            if (remaining < i5) {
                throw new IncompleteException(i5);
            }
            Framedata bVar;
            ByteBuffer allocate = ByteBuffer.allocate(a(i4));
            if (i3 != 0) {
                byte[] bArr3 = new byte[4];
                byteBuffer.get(bArr3);
                while (i2 < i4) {
                    allocate.put((byte) (byteBuffer.get() ^ bArr3[i2 % 4]));
                    i2++;
                }
            } else {
                allocate.put(byteBuffer.array(), byteBuffer.position(), allocate.limit());
                byteBuffer.position(byteBuffer.position() + allocate.limit());
            }
            if (a == Opcode.CLOSING) {
                bVar = new org.java_websocket.framing.b();
            } else {
                bVar = new d();
                bVar.a(z);
                bVar.a(a);
            }
            allocate.flip();
            bVar.a(allocate);
            return bVar;
        }
        throw new InvalidFrameException("control frames may no be fragmented");
    }

    public void a() {
        this.g = null;
    }

    public Draft c() {
        return new Draft_10();
    }

    public CloseHandshakeType b() {
        return CloseHandshakeType.TWOWAY;
    }
}
