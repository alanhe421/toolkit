package org.java_websocket.framing;

import com.tencent.qalsdk.base.a;
import java.nio.ByteBuffer;
import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.exceptions.InvalidFrameException;
import org.java_websocket.framing.Framedata.Opcode;

/* compiled from: CloseFrameBuilder */
public class b extends d implements a {
    static final ByteBuffer a = ByteBuffer.allocate(0);
    private int f;
    private String g;

    public b() {
        super(Opcode.CLOSING);
        a(true);
    }

    public b(int i) throws InvalidDataException {
        super(Opcode.CLOSING);
        a(true);
        a(i, "");
    }

    public b(int i, String str) throws InvalidDataException {
        super(Opcode.CLOSING);
        a(true);
        a(i, str);
    }

    private void a(int i, String str) throws InvalidDataException {
        String str2;
        if (str == null) {
            str2 = "";
        } else {
            str2 = str;
        }
        if (i == a.e) {
            str2 = "";
            i = 1005;
        }
        if (i != 1005) {
            byte[] a = org.java_websocket.c.b.a(str2);
            ByteBuffer allocate = ByteBuffer.allocate(4);
            allocate.putInt(i);
            allocate.position(2);
            ByteBuffer allocate2 = ByteBuffer.allocate(a.length + 2);
            allocate2.put(allocate);
            allocate2.put(a);
            allocate2.rewind();
            a(allocate2);
        } else if (str2.length() > 0) {
            throw new InvalidDataException(1002, "A close frame must have a closecode if it has a reason");
        }
    }

    private void g() throws InvalidFrameException {
        this.f = 1005;
        ByteBuffer c = super.c();
        c.mark();
        if (c.remaining() >= 2) {
            ByteBuffer allocate = ByteBuffer.allocate(4);
            allocate.position(2);
            allocate.putShort(c.getShort());
            allocate.position(0);
            this.f = allocate.getInt();
            if (this.f == 1006 || this.f == a.e || this.f == 1005 || this.f > 4999 || this.f < 1000 || this.f == 1004) {
                throw new InvalidFrameException("closecode must not be sent over the wire: " + this.f);
            }
        }
        c.reset();
    }

    public int a() {
        return this.f;
    }

    private void h() throws InvalidDataException {
        if (this.f == 1005) {
            this.g = org.java_websocket.c.b.a(super.c());
            return;
        }
        ByteBuffer c = super.c();
        int position = c.position();
        try {
            c.position(c.position() + 2);
            this.g = org.java_websocket.c.b.a(c);
            c.position(position);
        } catch (Throwable e) {
            throw new InvalidFrameException(e);
        } catch (Throwable th) {
            c.position(position);
        }
    }

    public String b() {
        return this.g;
    }

    public String toString() {
        return super.toString() + "code: " + this.f;
    }

    public void a(ByteBuffer byteBuffer) throws InvalidDataException {
        super.a(byteBuffer);
        g();
        h();
    }

    public ByteBuffer c() {
        if (this.f == 1005) {
            return a;
        }
        return super.c();
    }
}
