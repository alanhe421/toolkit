package de.innosystec.unrar.rarfile;

import de.innosystec.unrar.b.b;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/* compiled from: UnixOwnersHeader */
public class p extends o {
    private Log g = LogFactory.getLog(p.class);
    private int h;
    private int i;
    private String j;
    private String k;

    public p(o oVar, byte[] bArr) {
        super(oVar);
        this.h = b.b(bArr, 0) & 65535;
        this.i = b.b(bArr, 2) & 65535;
        if (this.h + 4 < bArr.length) {
            Object obj = new byte[this.h];
            System.arraycopy(bArr, 4, obj, 0, this.h);
            this.j = new String(obj);
        }
        int i = this.h + 4;
        if (this.i + i < bArr.length) {
            obj = new byte[this.i];
            System.arraycopy(bArr, i, obj, 0, this.i);
            this.k = new String(obj);
        }
    }

    public void j() {
        super.j();
        this.g.info("ownerNameSize: " + this.h);
        this.g.info("owner: " + this.j);
        this.g.info("groupNameSize: " + this.i);
        this.g.info("group: " + this.k);
    }
}
