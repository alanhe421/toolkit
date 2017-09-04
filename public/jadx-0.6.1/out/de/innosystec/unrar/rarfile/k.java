package de.innosystec.unrar.rarfile;

import de.innosystec.unrar.b.b;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/* compiled from: MarkHeader */
public class k extends b {
    private Log g = LogFactory.getLog(k.class.getName());
    private boolean h = false;

    public k(b bVar) {
        super(bVar);
    }

    public boolean k() {
        if (g() == (short) 24914 && i() == UnrarHeadertype.MarkHeader && f() == (short) 6689 && h() == (short) 7) {
            return true;
        }
        return false;
    }

    public boolean l() {
        byte[] bArr = new byte[7];
        b.a(bArr, 0, this.c);
        bArr[2] = this.d;
        b.a(bArr, 3, this.e);
        b.a(bArr, 5, this.f);
        if (bArr[0] == (byte) 82) {
            if (bArr[1] == (byte) 69 && bArr[2] == (byte) 126 && bArr[3] == (byte) 94) {
                this.h = true;
                return true;
            } else if (bArr[1] == (byte) 97 && bArr[2] == (byte) 114 && bArr[3] == (byte) 33 && bArr[4] == (byte) 26 && bArr[5] == (byte) 7 && bArr[6] == (byte) 0) {
                this.h = false;
                return true;
            }
        }
        return false;
    }

    public boolean m() {
        return this.h;
    }

    public void j() {
        super.j();
        this.g.info("valid: " + k());
    }
}
