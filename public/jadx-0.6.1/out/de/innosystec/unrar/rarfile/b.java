package de.innosystec.unrar.rarfile;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/* compiled from: BaseBlock */
public class b {
    Log a = LogFactory.getLog(b.class.getName());
    protected long b;
    protected short c = (short) 0;
    protected byte d = (byte) 0;
    protected short e = (short) 0;
    protected short f = (short) 0;

    public b(b bVar) {
        this.e = bVar.f();
        this.c = bVar.g();
        this.d = bVar.i().getHeaderByte();
        this.f = bVar.h();
        this.b = bVar.e();
    }

    public b(byte[] bArr) {
        this.c = de.innosystec.unrar.b.b.b(bArr, 0);
        this.d = (byte) ((bArr[2] & 255) | this.d);
        this.e = de.innosystec.unrar.b.b.b(bArr, 3);
        this.f = de.innosystec.unrar.b.b.b(bArr, 5);
    }

    public boolean a() {
        return (this.e & 2) != 0;
    }

    public boolean b() {
        return (this.e & 8) != 0;
    }

    public boolean c() {
        return (this.e & 512) != 0;
    }

    public boolean d() {
        if (UnrarHeadertype.SubHeader.equals(this.d)) {
            return true;
        }
        if (!UnrarHeadertype.NewSubHeader.equals(this.d) || (this.e & 16) == 0) {
            return false;
        }
        return true;
    }

    public long e() {
        return this.b;
    }

    public short f() {
        return this.e;
    }

    public short g() {
        return this.c;
    }

    public short h() {
        return this.f;
    }

    public UnrarHeadertype i() {
        return UnrarHeadertype.findType(this.d);
    }

    public void a(long j) {
        this.b = j;
    }

    public void j() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("HeaderType: " + i());
        stringBuilder.append("\nHeadCRC: " + Integer.toHexString(g()));
        stringBuilder.append("\nFlags: " + Integer.toHexString(f()));
        stringBuilder.append("\nHeaderSize: " + h());
        stringBuilder.append("\nPosition in file: " + e());
        this.a.info(stringBuilder.toString());
    }
}
