package de.innosystec.unrar.rarfile;

import de.innosystec.unrar.b.b;

/* compiled from: AVHeader */
public class a extends b {
    private byte g;
    private byte h;
    private byte i;
    private int j;

    public a(b bVar, byte[] bArr) {
        super(bVar);
        this.g = (byte) ((bArr[0] & 255) | this.g);
        this.h = (byte) ((bArr[1] & 255) | this.h);
        this.i = (byte) ((bArr[2] & 255) | this.i);
        this.j = b.c(bArr, 3);
    }
}
