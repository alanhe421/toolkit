package de.innosystec.unrar.rarfile;

import de.innosystec.unrar.b.b;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/* compiled from: MainHeader */
public class j extends b {
    private Log g = LogFactory.getLog(j.class.getName());
    private short h;
    private int i;
    private byte j;

    public j(b bVar, byte[] bArr) {
        super(bVar);
        this.h = b.b(bArr, 0);
        this.i = b.c(bArr, 2);
        if (c()) {
            this.j = (byte) ((bArr[6] & 255) | this.j);
        }
    }

    public boolean k() {
        return (this.e & 2) != 0;
    }

    public byte l() {
        return this.j;
    }

    public short m() {
        return this.h;
    }

    public int n() {
        return this.i;
    }

    public boolean o() {
        return (this.e & 128) != 0;
    }

    public boolean p() {
        return (this.e & 1) != 0;
    }

    public boolean q() {
        return (this.e & 256) != 0;
    }

    public void j() {
        super.j();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("posav: " + n());
        stringBuilder.append("\nhighposav: " + m());
        stringBuilder.append("\nhasencversion: " + c() + (c() ? Byte.valueOf(l()) : ""));
        stringBuilder.append("\nhasarchcmt: " + k());
        stringBuilder.append("\nisEncrypted: " + o());
        stringBuilder.append("\nisMultivolume: " + p());
        stringBuilder.append("\nisFirstvolume: " + q());
        stringBuilder.append("\nisSolid: " + r());
        stringBuilder.append("\nisLocked: " + s());
        stringBuilder.append("\nisProtected: " + t());
        stringBuilder.append("\nisAV: " + u());
        this.g.info(stringBuilder.toString());
    }

    public boolean r() {
        return (this.e & 8) != 0;
    }

    public boolean s() {
        return (this.e & 4) != 0;
    }

    public boolean t() {
        return (this.e & 64) != 0;
    }

    public boolean u() {
        return (this.e & 32) != 0;
    }

    public boolean v() {
        return (this.e & 16) != 0;
    }
}
