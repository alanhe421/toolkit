package de.innosystec.unrar.rarfile;

import de.innosystec.unrar.b.b;

/* compiled from: CommentHeader */
public class d extends b {
    private short g;
    private byte h;
    private byte i;
    private short j;

    public d(b bVar, byte[] bArr) {
        super(bVar);
        this.g = b.b(bArr, 0);
        this.h = (byte) ((bArr[2] & 255) | this.h);
        this.i = (byte) ((bArr[3] & 255) | this.i);
        this.j = b.b(bArr, 4);
    }
}
