package de.innosystec.unrar.rarfile;

import de.innosystec.unrar.b.b;

/* compiled from: SignHeader */
public class n extends b {
    private int g = 0;
    private short h = (short) 0;
    private short i = (short) 0;

    public n(b bVar, byte[] bArr) {
        super(bVar);
        this.g = b.c(bArr, 0);
        this.h = b.b(bArr, 4);
        this.i = b.b(bArr, 6);
    }
}
