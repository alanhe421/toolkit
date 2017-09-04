package de.innosystec.unrar;

import de.innosystec.unrar.b.c;
import de.innosystec.unrar.exception.RarException;
import de.innosystec.unrar.exception.RarException.RarExceptionType;
import de.innosystec.unrar.rarfile.SubBlockHeaderType;
import de.innosystec.unrar.rarfile.UnrarHeadertype;
import de.innosystec.unrar.rarfile.b;
import de.innosystec.unrar.rarfile.d;
import de.innosystec.unrar.rarfile.e;
import de.innosystec.unrar.rarfile.f;
import de.innosystec.unrar.rarfile.g;
import de.innosystec.unrar.rarfile.i;
import de.innosystec.unrar.rarfile.j;
import de.innosystec.unrar.rarfile.k;
import de.innosystec.unrar.rarfile.m;
import de.innosystec.unrar.rarfile.n;
import de.innosystec.unrar.rarfile.o;
import de.innosystec.unrar.rarfile.p;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: Archive */
public class a implements Closeable {
    private static Logger a = Logger.getLogger(a.class.getName());
    private static /* synthetic */ int[] q;
    private static /* synthetic */ int[] r;
    private File b;
    private de.innosystec.unrar.b.a c;
    private final b d;
    private final de.innosystec.unrar.unpack.a e;
    private final List<b> f;
    private k g;
    private j h;
    private f i;
    private de.innosystec.unrar.unpack.b j;
    private long k;
    private int l;
    private boolean m;
    private int n;
    private long o;
    private long p;

    static /* synthetic */ int[] h() {
        int[] iArr = q;
        if (iArr == null) {
            iArr = new int[SubBlockHeaderType.values().length];
            try {
                iArr[SubBlockHeaderType.BEEA_HEAD.ordinal()] = 4;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[SubBlockHeaderType.EA_HEAD.ordinal()] = 1;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[SubBlockHeaderType.MAC_HEAD.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[SubBlockHeaderType.NTACL_HEAD.ordinal()] = 5;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[SubBlockHeaderType.STREAM_HEAD.ordinal()] = 6;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[SubBlockHeaderType.UO_HEAD.ordinal()] = 2;
            } catch (NoSuchFieldError e6) {
            }
            q = iArr;
        }
        return iArr;
    }

    static /* synthetic */ int[] i() {
        int[] iArr = r;
        if (iArr == null) {
            iArr = new int[UnrarHeadertype.values().length];
            try {
                iArr[UnrarHeadertype.AvHeader.ordinal()] = 5;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[UnrarHeadertype.CommHeader.ordinal()] = 4;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[UnrarHeadertype.EndArcHeader.ordinal()] = 10;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[UnrarHeadertype.FileHeader.ordinal()] = 3;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[UnrarHeadertype.MainHeader.ordinal()] = 1;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[UnrarHeadertype.MarkHeader.ordinal()] = 2;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[UnrarHeadertype.NewSubHeader.ordinal()] = 9;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr[UnrarHeadertype.ProtectHeader.ordinal()] = 7;
            } catch (NoSuchFieldError e8) {
            }
            try {
                iArr[UnrarHeadertype.SignHeader.ordinal()] = 8;
            } catch (NoSuchFieldError e9) {
            }
            try {
                iArr[UnrarHeadertype.SubHeader.ordinal()] = 6;
            } catch (NoSuchFieldError e10) {
            }
            r = iArr;
        }
        return iArr;
    }

    public a(File file) throws RarException, IOException {
        this(file, null);
    }

    public a(File file, b bVar) throws RarException, IOException {
        this.f = new ArrayList();
        this.g = null;
        this.h = null;
        this.i = null;
        this.k = -1;
        this.m = false;
        this.n = 0;
        this.o = 0;
        this.p = 0;
        a(file);
        this.d = bVar;
        this.e = new de.innosystec.unrar.unpack.a(this);
    }

    public File a() {
        return this.b;
    }

    void a(File file) throws IOException {
        this.b = file;
        this.o = 0;
        this.p = 0;
        close();
        this.c = new c(file);
        try {
            j();
        } catch (Throwable e) {
            a.log(Level.WARNING, "exception in archive constructor maybe file is encrypted or currupt", e);
        }
        for (b bVar : this.f) {
            if (bVar.i() == UnrarHeadertype.FileHeader) {
                this.o += ((g) bVar).t();
            }
        }
        if (this.d != null) {
            this.d.a(this.p, this.o);
        }
    }

    public void a(int i) {
        if (i > 0) {
            this.p += (long) i;
            if (this.d != null) {
                this.d.a(this.p, this.o);
            }
        }
    }

    public de.innosystec.unrar.b.a b() {
        return this.c;
    }

    public List<g> c() {
        List<g> arrayList = new ArrayList();
        for (b bVar : this.f) {
            if (bVar.i().equals(UnrarHeadertype.FileHeader)) {
                arrayList.add((g) bVar);
            }
        }
        return arrayList;
    }

    public g d() {
        int size = this.f.size();
        while (this.l < size) {
            List list = this.f;
            int i = this.l;
            this.l = i + 1;
            b bVar = (b) list.get(i);
            if (bVar.i() == UnrarHeadertype.FileHeader) {
                return (g) bVar;
            }
        }
        return null;
    }

    public b e() {
        return this.d;
    }

    private void j() throws IOException, RarException {
        this.g = null;
        this.h = null;
        this.i = null;
        this.f.clear();
        this.l = 0;
        long length = this.b.length();
        while (true) {
            byte[] bArr = new byte[7];
            long a = this.c.a();
            if (a < length && this.c.a(bArr, 7) != 0) {
                b bVar = new b(bArr);
                bVar.a(a);
                int i;
                byte[] bArr2;
                switch (i()[bVar.i().ordinal()]) {
                    case 1:
                        if (bVar.c()) {
                            i = 7;
                        } else {
                            i = 6;
                        }
                        bArr2 = new byte[i];
                        this.c.a(bArr2, i);
                        j jVar = new j(bVar, bArr2);
                        this.f.add(jVar);
                        this.h = jVar;
                        if (!this.h.o()) {
                            break;
                        }
                        throw new RarException(RarExceptionType.rarEncryptedException);
                    case 2:
                        this.g = new k(bVar);
                        if (this.g.l()) {
                            this.f.add(this.g);
                            break;
                        }
                        throw new RarException(RarExceptionType.badRarArchive);
                    case 4:
                        bArr2 = new byte[6];
                        this.c.a(bArr2, 6);
                        d dVar = new d(bVar, bArr2);
                        this.f.add(dVar);
                        this.c.a(dVar.e() + ((long) dVar.h()));
                        break;
                    case 5:
                        bArr = new byte[7];
                        this.c.a(bArr, 7);
                        this.f.add(new de.innosystec.unrar.rarfile.a(bVar, bArr));
                        break;
                    case 8:
                        bArr = new byte[8];
                        this.c.a(bArr, 8);
                        this.f.add(new n(bVar, bArr));
                        break;
                    case 10:
                        f fVar;
                        if (bVar.a()) {
                            i = 4;
                        } else {
                            i = 0;
                        }
                        if (bVar.b()) {
                            i += 2;
                        }
                        if (i > 0) {
                            byte[] bArr3 = new byte[i];
                            this.c.a(bArr3, i);
                            fVar = new f(bVar, bArr3);
                        } else {
                            fVar = new f(bVar, null);
                        }
                        this.f.add(fVar);
                        this.i = fVar;
                        return;
                    default:
                        bArr = new byte[4];
                        this.c.a(bArr, 4);
                        de.innosystec.unrar.rarfile.c cVar = new de.innosystec.unrar.rarfile.c(bVar, bArr);
                        byte[] bArr4;
                        switch (i()[cVar.i().ordinal()]) {
                            case 3:
                            case 9:
                                i = (cVar.h() - 7) - 4;
                                bArr4 = new byte[i];
                                this.c.a(bArr4, i);
                                g gVar = new g(cVar, bArr4);
                                this.f.add(gVar);
                                this.c.a((gVar.e() + ((long) gVar.h())) + gVar.t());
                                break;
                            case 6:
                                bArr = new byte[3];
                                this.c.a(bArr, 3);
                                o oVar = new o(cVar, bArr);
                                oVar.j();
                                switch (h()[oVar.n().ordinal()]) {
                                    case 1:
                                        bArr = new byte[10];
                                        this.c.a(bArr, 10);
                                        e eVar = new e(oVar, bArr);
                                        eVar.j();
                                        this.f.add(eVar);
                                        break;
                                    case 2:
                                        i = ((oVar.h() - 7) - 4) - 3;
                                        bArr2 = new byte[i];
                                        this.c.a(bArr2, i);
                                        p pVar = new p(oVar, bArr2);
                                        pVar.j();
                                        this.f.add(pVar);
                                        break;
                                    case 3:
                                        bArr = new byte[8];
                                        this.c.a(bArr, 8);
                                        i iVar = new i(oVar, bArr);
                                        iVar.j();
                                        this.f.add(iVar);
                                        break;
                                    case 4:
                                    case 5:
                                    case 6:
                                        break;
                                    default:
                                        break;
                                }
                            case 7:
                                i = (cVar.h() - 7) - 4;
                                bArr4 = new byte[i];
                                this.c.a(bArr4, i);
                                m mVar = new m(cVar, bArr4);
                                this.c.a(mVar.e() + ((long) mVar.h()));
                                break;
                            default:
                                a.warning("Unknown Header");
                                throw new RarException(RarExceptionType.notRarArchive);
                        }
                }
            }
            return;
        }
    }

    public void a(g gVar, OutputStream outputStream) throws RarException {
        if (this.f.contains(gVar)) {
            try {
                b(gVar, outputStream);
                return;
            } catch (Exception e) {
                if (e instanceof RarException) {
                    throw ((RarException) e);
                }
                throw new RarException(e);
            }
        }
        throw new RarException(RarExceptionType.headerNotInArchive);
    }

    private void b(g gVar, OutputStream outputStream) throws RarException, IOException {
        this.e.a(outputStream);
        this.e.a(gVar);
        this.e.a((long) (g() ? 0 : -1));
        if (this.j == null) {
            this.j = new de.innosystec.unrar.unpack.b(this.e);
        }
        if (!gVar.x()) {
            this.j.a(null);
        }
        this.j.a(gVar.u());
        try {
            long a;
            this.j.a(gVar.s(), gVar.x());
            g c = this.e.c();
            if (c.v()) {
                a = this.e.a() ^ -1;
            } else {
                a = this.e.b() ^ -1;
            }
            if (a != ((long) c.m())) {
                throw new RarException(RarExceptionType.crcError);
            }
        } catch (Exception e) {
            this.j.b();
            if (e instanceof RarException) {
                throw ((RarException) e);
            }
            throw new RarException(e);
        }
    }

    public j f() {
        return this.h;
    }

    public boolean g() {
        return this.g.m();
    }

    public void close() throws IOException {
        if (this.c != null) {
            this.c.close();
            this.c = null;
        }
    }
}
