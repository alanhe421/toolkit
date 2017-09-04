package com.qq.reader.readengine.b;

import com.etrump.jni.ETConverter;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/* compiled from: CharReader */
public class b {
    private a a = null;
    private ByteBuffer b;
    private CharBuffer c;
    private CharsetDecoder d;
    private String e;

    public b(a aVar, String str) {
        this.a = aVar;
        this.d = Charset.forName(str).newDecoder();
        this.c = CharBuffer.allocate(1);
        this.b = ByteBuffer.allocate(10);
        this.e = str;
    }

    public char a() throws IOException {
        this.c.position(0);
        this.c.limit(1);
        this.b.position(0);
        this.b.limit(10);
        byte a;
        byte a2;
        if (this.e.equals("UTF-16BE") || this.e.equals("UTF-16LE")) {
            a = this.a.a();
            a2 = this.a.a();
            this.b.put(a);
            this.b.put(a2);
        } else if (this.e.equals("GBK")) {
            a = this.a.a();
            this.b.put(a);
            if ((a & 255) > Opcodes.INT_TO_LONG) {
                this.b.put(this.a.a());
            }
        } else if (this.e.equals("UTF-8")) {
            a = this.a.a();
            int i = a & 255;
            if (i <= Opcodes.NEG_FLOAT) {
                this.b.put(a);
            } else if ((i & Opcodes.SHL_INT_LIT8) == Opcodes.AND_LONG_2ADDR) {
                a2 = this.a.a();
                this.b.put(a);
                this.b.put(a2);
            } else if ((i & ETConverter.ET_CONVERTER_GLYPH_CACHE_SIZE_MASK) == Opcodes.SHL_INT_LIT8) {
                a2 = this.a.a();
                r2 = this.a.a();
                this.b.put(a);
                this.b.put(a2);
                this.b.put(r2);
            } else if ((i & 248) == ETConverter.ET_CONVERTER_GLYPH_CACHE_SIZE_MASK) {
                a2 = this.a.a();
                r2 = this.a.a();
                r3 = this.a.a();
                this.b.put(a);
                this.b.put(a2);
                this.b.put(r2);
                this.b.put(r3);
            } else if ((i & 252) == 248) {
                a2 = this.a.a();
                r2 = this.a.a();
                r3 = this.a.a();
                r4 = this.a.a();
                this.b.put(a);
                this.b.put(a2);
                this.b.put(r2);
                this.b.put(r3);
                this.b.put(r4);
            } else if ((i & 254) == 252) {
                a2 = this.a.a();
                r2 = this.a.a();
                r3 = this.a.a();
                r4 = this.a.a();
                byte a3 = this.a.a();
                this.b.put(a);
                this.b.put(a2);
                this.b.put(r2);
                this.b.put(r3);
                this.b.put(r4);
                this.b.put(a3);
            }
        }
        this.b.flip();
        this.d.decode(this.b, this.c, true);
        this.c.position(0);
        return this.c.charAt(0);
    }
}
