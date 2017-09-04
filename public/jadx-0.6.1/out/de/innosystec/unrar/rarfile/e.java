package de.innosystec.unrar.rarfile;

import de.innosystec.unrar.b.b;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/* compiled from: EAHeader */
public class e extends o {
    private Log g = LogFactory.getLog(getClass());
    private int h;
    private byte i;
    private byte j;
    private int k;

    public e(o oVar, byte[] bArr) {
        super(oVar);
        this.h = b.c(bArr, 0);
        this.i = (byte) ((bArr[4] & 255) | this.i);
        this.j = (byte) ((bArr[5] & 255) | this.j);
        this.k = b.c(bArr, 6);
    }

    public void j() {
        super.j();
        this.g.info("unpSize: " + this.h);
        this.g.info("unpVersion: " + this.i);
        this.g.info("method: " + this.j);
        this.g.info("EACRC:" + this.k);
    }
}
