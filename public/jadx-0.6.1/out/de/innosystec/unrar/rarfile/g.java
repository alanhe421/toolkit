package de.innosystec.unrar.rarfile;

import com.tencent.tinker.android.dex.TableOfContents;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import de.innosystec.unrar.b.b;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/* compiled from: FileHeader */
public class g extends c {
    private final Log g = LogFactory.getLog(g.class.getName());
    private long h;
    private final HostSystem i;
    private final int j;
    private final int k;
    private byte l;
    private byte m;
    private short n;
    private int o;
    private int p;
    private final byte[] q;
    private String r;
    private String s;
    private byte[] t;
    private final byte[] u = new byte[8];
    private Date v;
    private long w;
    private long x;
    private int y;
    private int z = -1;

    public g(c cVar, byte[] bArr) {
        short s;
        short s2 = TableOfContents.SECTION_TYPE_MAPLIST;
        int i = 0;
        super(cVar);
        this.h = (long) b.c(bArr, 0);
        this.i = HostSystem.findHostSystem(bArr[4]);
        this.j = b.c(bArr, 5);
        this.k = b.c(bArr, 9);
        this.l = (byte) (this.l | (bArr[13] & 255));
        this.m = (byte) (this.m | (bArr[14] & 255));
        this.n = b.b(bArr, 15);
        this.y = b.c(bArr, 17);
        int i2 = 21;
        if (C()) {
            this.o = b.c(bArr, 21);
            this.p = b.c(bArr, 25);
            i2 = 29;
        } else {
            this.o = 0;
            this.p = 0;
            if (this.h == -1) {
                this.h = -1;
                this.p = Integer.MAX_VALUE;
            }
        }
        this.w |= (long) this.o;
        this.w <<= 32;
        this.w |= (long) l();
        this.x |= (long) this.p;
        this.x <<= 32;
        this.x |= this.h;
        if (this.n <= TableOfContents.SECTION_TYPE_MAPLIST) {
            s2 = this.n;
        }
        this.n = s2;
        this.q = new byte[this.n];
        int i3 = i2;
        for (s = (short) 0; s < this.n; s++) {
            this.q[s] = bArr[i3];
            i3++;
        }
        if (A()) {
            if (z()) {
                this.r = "";
                this.s = "";
                s = (short) 0;
                while (s < this.q.length && this.q[s] != (byte) 0) {
                    s++;
                }
                Object obj = new byte[s];
                System.arraycopy(this.q, 0, obj, 0, obj.length);
                this.r = new String(obj);
                if (s != this.n) {
                    this.s = h.b(this.q, s + 1);
                }
            } else {
                this.r = new String(this.q);
                this.s = "";
            }
        }
        if (UnrarHeadertype.NewSubHeader.equals(this.d)) {
            int i4;
            i2 = (this.f - 32) - this.n;
            if (B()) {
                i4 = i2 - 8;
            } else {
                i4 = i2;
            }
            if (i4 > 0) {
                this.t = new byte[i4];
                for (i2 = 0; i2 < i4; i2++) {
                    this.t[i2] = bArr[i3];
                    i3++;
                }
            }
            if (l.f.a(this.q)) {
                this.z = ((this.t[8] + (this.t[9] << 8)) + (this.t[10] << 16)) + (this.t[11] << 24);
            }
        }
        if (B()) {
            while (i < 8) {
                this.u[i] = bArr[i3];
                i3++;
                i++;
            }
        }
        this.v = a(this.k);
    }

    public void j() {
        super.j();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("unpSize: " + r());
        stringBuilder.append("\nHostOS: " + this.i.name());
        stringBuilder.append("\nMDate: " + this.v);
        stringBuilder.append("\nFileName: " + n());
        stringBuilder.append("\nunpMethod: " + Integer.toHexString(q()));
        stringBuilder.append("\nunpVersion: " + Integer.toHexString(s()));
        stringBuilder.append("\nfullpackedsize: " + t());
        stringBuilder.append("\nfullunpackedsize: " + u());
        stringBuilder.append("\nisEncrypted: " + y());
        stringBuilder.append("\nisfileHeader: " + A());
        stringBuilder.append("\nisSolid: " + x());
        stringBuilder.append("\nisSplitafter: " + v());
        stringBuilder.append("\nisSplitBefore:" + w());
        stringBuilder.append("\nunpSize: " + r());
        stringBuilder.append("\ndataSize: " + k());
        stringBuilder.append("\nisUnicode: " + z());
        stringBuilder.append("\nhasVolumeNumber: " + b());
        stringBuilder.append("\nhasArchiveDataCRC: " + a());
        stringBuilder.append("\nhasSalt: " + B());
        stringBuilder.append("\nhasEncryptVersions: " + c());
        stringBuilder.append("\nisSubBlock: " + d());
        this.g.info(stringBuilder.toString());
    }

    private Date a(int i) {
        Calendar instance = Calendar.getInstance();
        instance.set(1, (i >>> 25) + 1980);
        instance.set(2, ((i >>> 21) & 15) - 1);
        instance.set(5, (i >>> 16) & 31);
        instance.set(11, (i >>> 11) & 31);
        instance.set(12, (i >>> 5) & 63);
        instance.set(13, (i & 31) * 2);
        return instance.getTime();
    }

    public int m() {
        return this.j;
    }

    public String n() {
        return this.r;
    }

    public String o() {
        return this.s;
    }

    public Date p() {
        return this.v;
    }

    public byte q() {
        return this.m;
    }

    public long r() {
        return this.h;
    }

    public byte s() {
        return this.l;
    }

    public long t() {
        return this.w;
    }

    public long u() {
        return this.x;
    }

    public String toString() {
        return super.toString();
    }

    public boolean v() {
        return (this.e & 2) != 0;
    }

    public boolean w() {
        return (this.e & 1) != 0;
    }

    public boolean x() {
        return (this.e & 16) != 0;
    }

    public boolean y() {
        return (this.e & 4) != 0;
    }

    public boolean z() {
        return (this.e & 512) != 0;
    }

    public boolean A() {
        return UnrarHeadertype.FileHeader.equals(this.d);
    }

    public boolean B() {
        return (this.e & 1024) != 0;
    }

    public boolean C() {
        return (this.e & 256) != 0;
    }

    public boolean D() {
        return (this.e & Opcodes.SHL_INT_LIT8) == Opcodes.SHL_INT_LIT8;
    }
}
