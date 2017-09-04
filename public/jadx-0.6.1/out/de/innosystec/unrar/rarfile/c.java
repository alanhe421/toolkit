package de.innosystec.unrar.rarfile;

import de.innosystec.unrar.b.b;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/* compiled from: BlockHeader */
public class c extends b {
    private Log g = LogFactory.getLog(c.class.getName());
    private int h;
    private int i;

    public c(c cVar) {
        super((b) cVar);
        this.i = cVar.k();
        this.h = this.i;
        this.b = cVar.e();
    }

    public c(b bVar, byte[] bArr) {
        super(bVar);
        this.i = b.c(bArr, 0);
        this.h = this.i;
    }

    public int k() {
        return this.h;
    }

    public int l() {
        return this.i;
    }

    public void j() {
        super.j();
        this.g.info("DataSize: " + k() + " packSize: " + l());
    }
}
