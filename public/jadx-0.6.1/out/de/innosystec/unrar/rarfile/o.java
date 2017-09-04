package de.innosystec.unrar.rarfile;

import de.innosystec.unrar.b.b;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/* compiled from: SubBlockHeader */
public class o extends c {
    private Log g = LogFactory.getLog(getClass());
    private short h;
    private byte i;

    public o(o oVar) {
        super(oVar);
        this.h = oVar.n().getSubblocktype();
        this.i = oVar.m();
    }

    public o(c cVar, byte[] bArr) {
        super(cVar);
        this.h = b.b(bArr, 0);
        this.i = (byte) ((bArr[2] & 255) | this.i);
    }

    public byte m() {
        return this.i;
    }

    public SubBlockHeaderType n() {
        return SubBlockHeaderType.findSubblockHeaderType(this.h);
    }

    public void j() {
        super.j();
        this.g.info("subtype: " + n());
        this.g.info("level: " + this.i);
    }
}
