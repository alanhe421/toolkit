package oicq.wlogin_sdk.b;

import oicq.wlogin_sdk.tools.cryptor;
import oicq.wlogin_sdk.tools.util;

/* compiled from: tlv_t */
public class b {
    int b;
    int c;
    int d;
    int e;
    int f;
    byte[] g;
    int h;

    public b() {
        this.b = 128;
        this.c = 0;
        this.d = 0;
        this.e = 4;
        this.f = 0;
        this.g = new byte[this.b];
        this.h = 0;
    }

    public b(int i) {
        this.b = 128;
        this.c = 0;
        this.d = 0;
        this.e = 4;
        this.f = 0;
        this.g = new byte[this.b];
        this.h = 0;
        this.h = i;
    }

    public byte[] b() {
        Object obj = new byte[this.c];
        System.arraycopy(this.g, 0, obj, 0, this.c);
        return obj;
    }

    public byte[] c() {
        Object obj = new byte[this.f];
        System.arraycopy(this.g, this.e, obj, 0, this.f);
        return obj;
    }

    public int d() {
        return this.f;
    }

    public void b(byte[] bArr, int i) {
        if (this.e + i > this.b) {
            this.b = (this.e + i) + 128;
            Object obj = new byte[this.b];
            System.arraycopy(this.g, 0, obj, 0, this.e);
            this.g = obj;
        }
        this.c = this.e + i;
        System.arraycopy(bArr, 0, this.g, this.e, i);
        this.f = i;
        util.int16_to_buf(this.g, 0, this.h);
        util.int16_to_buf(this.g, 2, this.f);
    }

    public void a(byte[] bArr, int i, int i2) {
        if (this.e + i2 > this.b) {
            this.b = (this.e + i2) + 128;
            Object obj = new byte[this.b];
            System.arraycopy(this.g, 0, obj, 0, this.e);
            this.g = obj;
        }
        this.c = this.e + i2;
        System.arraycopy(bArr, i, this.g, this.e, i2);
        this.f = i2;
        util.int16_to_buf(this.g, 0, this.h);
        util.int16_to_buf(this.g, 2, this.f);
    }

    public void b(byte[] bArr, int i, int i2) {
        if (i2 > this.b) {
            this.b = i2 + 128;
            this.g = new byte[this.b];
        }
        this.c = i2;
        System.arraycopy(bArr, i, this.g, 0, i2);
        this.h = util.buf_to_int16(bArr, i);
        this.f = i2 - this.e;
    }

    public void b(int i) {
        util.int16_to_buf(this.g, this.c, i);
        this.c += 2;
        util.int16_to_buf(this.g, this.c, 0);
        this.c += 2;
    }

    public void e() {
        util.int16_to_buf(this.g, 2, this.c - this.e);
    }

    public void c(byte[] bArr, int i) {
        if (i > this.b - this.e) {
            this.b = (this.e + i) + 64;
            Object obj = new byte[this.b];
            System.arraycopy(this.g, 0, obj, 0, this.c);
            this.g = obj;
        }
        this.f = i;
        System.arraycopy(bArr, 0, this.g, this.c, i);
        this.c += i;
    }

    int a(byte[] bArr, int i, int i2, int i3) {
        int length = bArr.length;
        int i4 = i;
        while (i4 < length && i4 + 2 <= length) {
            if (util.buf_to_int16(bArr, i4) == i3) {
                return i4;
            }
            i4 += 2;
            if (i4 + 2 > length) {
                return -1;
            }
            i4 += util.buf_to_int16(bArr, i4) + 2;
        }
        return -1;
    }

    public int c(byte[] bArr, int i, int i2) {
        int a = a(bArr, i, i2, this.h);
        if (a < 0) {
            return -1;
        }
        int i3 = i2 - (a - i);
        if (this.e >= i3) {
            return -1;
        }
        this.f = util.buf_to_int16(bArr, a + 2);
        if (this.e + this.f > i3) {
            return -1;
        }
        b(bArr, a, this.e + this.f);
        if (f().booleanValue()) {
            return (this.e + a) + this.f;
        }
        return -1005;
    }

    int a(byte[] bArr, int i, byte[] bArr2) {
        if (this.e >= i) {
            return -1;
        }
        this.f = util.buf_to_int16(bArr, 2);
        if (this.e + this.f > i) {
            return -1;
        }
        Object decrypt = cryptor.decrypt(bArr, this.e, this.f, bArr2);
        if (decrypt == null) {
            return util.E_TLV_DECRYPT;
        }
        if (this.e + decrypt.length > this.b) {
            this.b = this.e + decrypt.length;
            this.g = new byte[this.b];
        }
        this.c = 0;
        System.arraycopy(bArr, 0, this.g, 0, this.e);
        this.c += this.e;
        System.arraycopy(decrypt, 0, this.g, this.c, decrypt.length);
        this.c += decrypt.length;
        this.f = decrypt.length;
        return !f().booleanValue() ? -1005 : 0;
    }

    public int a(byte[] bArr, int i, int i2, byte[] bArr2) {
        int a = a(bArr, i, i2, this.h);
        if (a < 0) {
            return -1;
        }
        int i3 = i2 - (a - i);
        byte[] bArr3 = new byte[i3];
        System.arraycopy(bArr, a, bArr3, 0, i3);
        return a(bArr3, i3, bArr2);
    }

    public Boolean f() {
        return Boolean.valueOf(true);
    }

    public int d(byte[] bArr, int i) {
        if (bArr == null) {
            return 0;
        }
        if (bArr.length > i) {
            return i;
        }
        return bArr.length;
    }
}
