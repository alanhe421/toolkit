package com.tencent.qalsdk.util;

public class Cryptor {
    a impl = new a();

    public byte[] decrypt(byte[] bArr, int i, int i2, byte[] bArr2) {
        return this.impl.a(bArr, i, i2, bArr2);
    }

    public byte[] decrypt(byte[] bArr, byte[] bArr2) {
        return this.impl.a(bArr, bArr2);
    }

    public byte[] encrypt(byte[] bArr, byte[] bArr2) {
        return this.impl.b(bArr, bArr2);
    }
}
