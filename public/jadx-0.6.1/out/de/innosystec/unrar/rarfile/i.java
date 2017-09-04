package de.innosystec.unrar.rarfile;

import de.innosystec.unrar.b.b;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/* compiled from: MacInfoHeader */
public class i extends o {
    private Log g = LogFactory.getLog(getClass());
    private int h;
    private int i;

    public i(o oVar, byte[] bArr) {
        super(oVar);
        this.h = b.c(bArr, 0);
        this.i = b.c(bArr, 4);
    }

    public void j() {
        super.j();
        this.g.info("filetype: " + this.h);
        this.g.info("creator :" + this.i);
    }
}
