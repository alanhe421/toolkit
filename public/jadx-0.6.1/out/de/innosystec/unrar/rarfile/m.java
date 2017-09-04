package de.innosystec.unrar.rarfile;

import de.innosystec.unrar.b.b;

/* compiled from: ProtectHeader */
public class m extends c {
    private byte g;
    private short h;
    private int i;
    private byte j;

    public m(c cVar, byte[] bArr) {
        super(cVar);
        this.g = (byte) (this.g | (bArr[0] & 255));
        this.h = b.b(bArr, 0);
        this.i = b.c(bArr, 2);
        this.j = (byte) ((bArr[6] & 255) | this.j);
    }
}
