package com.qq.reader.readengine.b;

import com.qq.reader.readengine.fileparse.b;
import com.qq.reader.readengine.fileparse.d;
import com.tencent.upload.log.trace.TracerConfig;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/* compiled from: UmdByteReader */
public class f extends a {
    public f(d dVar, String str) {
        super(dVar, str);
    }

    public byte a() throws IOException {
        byte b = (byte) -1;
        if (this.b == null) {
            if (this.c.i()) {
                this.b = this.c.b().c;
                this.d = this.c.b().c.length;
                this.a = 0;
            }
            return b;
        }
        if (this.b != null) {
            byte[] bArr = this.b;
            int i = this.a;
            this.a = i + 1;
            b = bArr[i];
            this.e++;
            if (this.a >= this.d) {
                this.b = null;
            }
        }
        return b;
    }

    public long c() {
        return this.e;
    }

    public void d() {
        this.c.s();
    }

    public long a(long j) {
        return Math.min(Math.max(j - 40, 0), j);
    }

    public long b(long j) {
        int i = this.d - this.a;
        if (i % 2 != 0) {
            i++;
        }
        return Math.max(j, Math.min((((long) Math.min(40, i)) + j) - 1, b() - 1));
    }

    public String a(long j, long j2, long j3) {
        String replaceAll;
        UnsupportedEncodingException e;
        int i = (int) (j % TracerConfig.MEMORY_SIZE);
        int i2 = ((int) (j2 - j)) + 1;
        Object obj = new byte[i2];
        if (((int) (j / TracerConfig.MEMORY_SIZE)) < ((b) this.c.b()).g()) {
            int i3 = 32768 - i;
            System.arraycopy(this.c.c().c, i, obj, 0, i3);
            System.arraycopy(this.c.b().c, 0, obj, i3, i2 - i3);
        } else {
            System.arraycopy(this.c.b().c, i, obj, 0, i2);
        }
        this.g = -1;
        try {
            replaceAll = new String(obj, "UTF-16LE").replaceAll("\r|\n| ", " ");
            try {
                this.g = new String(obj, 0, Math.max((int) (j3 - j), 0), this.f).length();
            } catch (UnsupportedEncodingException e2) {
                e = e2;
                e.printStackTrace();
                return replaceAll;
            }
        } catch (UnsupportedEncodingException e3) {
            UnsupportedEncodingException unsupportedEncodingException = e3;
            replaceAll = null;
            e = unsupportedEncodingException;
            e.printStackTrace();
            return replaceAll;
        }
        return replaceAll;
    }
}
