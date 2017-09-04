package de.innosystec.unrar.rarfile;

import de.innosystec.unrar.b.b;

/* compiled from: EndArcHeader */
public class f extends b {
    private int g;
    private short h;

    public f(b bVar, byte[] bArr) {
        super(bVar);
        int i = 0;
        if (a()) {
            this.g = b.c(bArr, 0);
            i = 4;
        }
        if (b()) {
            this.h = b.b(bArr, i);
        }
    }
}
