package org.java_websocket.framing;

import java.nio.ByteBuffer;
import java.util.Arrays;
import org.java_websocket.c.b;
import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.framing.Framedata.Opcode;

/* compiled from: FramedataImpl1 */
public class d implements c {
    protected static byte[] b = new byte[0];
    private ByteBuffer a;
    protected boolean c;
    protected Opcode d;
    protected boolean e;

    public d(Opcode opcode) {
        this.d = opcode;
        this.a = ByteBuffer.wrap(b);
    }

    public d(Framedata framedata) {
        this.c = framedata.d();
        this.d = framedata.f();
        this.a = framedata.c();
        this.e = framedata.e();
    }

    public boolean d() {
        return this.c;
    }

    public Opcode f() {
        return this.d;
    }

    public boolean e() {
        return this.e;
    }

    public ByteBuffer c() {
        return this.a;
    }

    public void a(boolean z) {
        this.c = z;
    }

    public void a(Opcode opcode) {
        this.d = opcode;
    }

    public void a(ByteBuffer byteBuffer) throws InvalidDataException {
        this.a = byteBuffer;
    }

    public void b(boolean z) {
        this.e = z;
    }

    public String toString() {
        return "Framedata{ optcode:" + f() + ", fin:" + d() + ", payloadlength:[pos:" + this.a.position() + ", len:" + this.a.remaining() + "], payload:" + Arrays.toString(b.a(new String(this.a.array()))) + "}";
    }
}
