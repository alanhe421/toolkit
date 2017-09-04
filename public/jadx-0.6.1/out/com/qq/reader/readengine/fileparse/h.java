package com.qq.reader.readengine.fileparse;

import com.qq.reader.common.monitor.f;
import com.qq.reader.common.utils.c.b;
import com.qq.reader.plugin.tts.model.a;
import com.qq.reader.readengine.kernel.g;
import com.qq.reader.readengine.model.BookUmd;
import com.qq.reader.readengine.model.Chunk;
import com.qq.reader.readengine.model.IBook;
import com.tencent.upload.log.trace.TracerConfig;
import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/* compiled from: ReaderUmdFileInput */
public class h extends d {
    j g;
    private int h = 32768;
    private long i = 0;

    public /* synthetic */ IBook t() {
        return w();
    }

    public h(BookUmd bookUmd) throws FileNotFoundException {
        if (bookUmd == null || bookUmd.getBookPath().length() == 0) {
            throw new FileNotFoundException();
        }
        this.f = bookUmd;
        this.f.setEncodingStr(b.a(this.f.getEncoding()));
        this.a = new RandomAccessFile(this.f.getBookPath(), "r");
        this.g = new j(this.a, w());
        if (this.g.b()) {
            this.i = this.f.getLength();
        } else {
            f.b("ReaderUmdFileInput", "In Constructor decodeAttribute return FALSE!!!ERROR");
        }
        this.e = new b();
    }

    private h() {
    }

    public void a() {
        super.a();
        this.e = new b();
    }

    public e u() throws FileNotFoundException {
        e hVar = new h();
        hVar.f = this.f;
        hVar.a = new RandomAccessFile(this.f.getBookPath(), "r");
        hVar.i = this.f.getLength();
        hVar.e = new b();
        hVar.g = new j(hVar.a, w());
        return hVar;
    }

    public BookUmd w() {
        return (BookUmd) this.f;
    }

    public long v() {
        return this.i;
    }

    public synchronized void k() {
        if (this.b != null) {
            if (e(this.b)) {
                ((b) this.e).b(((b) this.b).g() + 1);
                ((b) this.e).a(0);
            } else {
                ((b) this.e).b(((b) this.b).g());
                ((b) this.e).a(this.b.c.length);
            }
        }
        if (b(0) != null) {
            this.d = this.e.c();
        } else {
            this.d = null;
        }
    }

    public boolean a(g gVar) {
        try {
            a(gVar, false);
            this.c = null;
            this.d = null;
            return true;
        } catch (IOException e) {
            f.a("UMD", "reReadBuffFromRightPage e: " + e.toString());
            e.printStackTrace();
            return false;
        }
    }

    public synchronized boolean a(g gVar, boolean z) throws IOException {
        boolean y;
        if (this.a != null) {
            long e = gVar.e();
            if (z) {
                e = b(e);
            }
            a();
            if (c(e)) {
                y = y();
            }
        }
        y = false;
        return y;
    }

    private long b(long j) throws IOException {
        if (j % 2 == 0) {
            return j;
        }
        return j + 1;
    }

    private boolean c(long j) {
        if (j < 0) {
            return false;
        }
        if (j == 0) {
            ((b) this.e).b(0);
            ((b) this.e).a(0);
            return true;
        }
        ((b) this.e).b((int) (j / TracerConfig.MEMORY_SIZE));
        ((b) this.e).a((int) (j % TracerConfig.MEMORY_SIZE));
        return true;
    }

    public ArrayList<Chunk> x() {
        return w().getChunks();
    }

    private synchronized String b(int i) {
        String str;
        try {
            ArrayList x = x();
            int g = ((b) this.e).g();
            Chunk chunk = (Chunk) x.get(g);
            this.a.seek(chunk.getStartPointZip());
            this.e.d = chunk.getStartPointZip() - w().getContentStartPoint();
            byte[] a = this.g.a();
            if (g >= x.size() - 1) {
                a = a(a);
                this.h = a.length;
            }
            byte[] bArr = a;
            this.e.c = a(bArr, ((b) this.e).f(), i);
            this.e.g = b(this.e.c);
            str = this.e.g;
        } catch (Exception e) {
            e.printStackTrace();
            f.a("UMD", "readBuffFromFile e: " + e.toString());
            str = null;
        }
        return str;
    }

    private byte[] a(byte[] bArr, int i, int i2) {
        if (i == 0 && i2 == 0) {
            return bArr;
        }
        byte[] bArr2 = new byte[((bArr.length - i) - i2)];
        int i3 = 0;
        while (i < bArr.length && i3 < bArr2.length) {
            bArr2[i3] = bArr[i];
            i++;
            i3++;
        }
        return bArr2;
    }

    private String b(byte[] bArr) throws IOException {
        InputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        StringBuffer stringBuffer = new StringBuffer();
        while (true) {
            try {
                stringBuffer.append(a(byteArrayInputStream));
            } catch (EOFException e) {
                return stringBuffer.toString();
            }
        }
    }

    private final char a(InputStream inputStream) throws IOException {
        int read = inputStream.read();
        int read2 = inputStream.read();
        if ((read2 | read) < 0) {
            throw new EOFException();
        }
        return (char) ((read << 0) + (read2 << 8));
    }

    private boolean y() {
        if (b(0) == null) {
            return false;
        }
        if (this.b != null) {
            this.c = this.b;
        }
        this.b = this.e.c();
        return true;
    }

    public synchronized boolean i() {
        boolean z = false;
        synchronized (this) {
            if (this.g != null && (this.b == null || !a(this.b, 0))) {
                if (this.d != null) {
                    this.c = this.b;
                    this.b = this.d;
                    this.d = null;
                    z = true;
                } else {
                    if (this.b != null) {
                        if (e(this.b)) {
                            ((b) this.e).b(((b) this.b).g() + 1);
                            ((b) this.e).a(0);
                        } else {
                            ((b) this.e).b(((b) this.b).g());
                            ((b) this.e).a(this.b.c.length);
                        }
                    }
                    z = y();
                }
            }
        }
        return z;
    }

    public synchronized boolean j() {
        boolean z;
        if (this.g != null && ((b) this.e).g() >= 0) {
            if (this.c != null) {
                this.d = this.b;
                this.b = this.c;
                this.c = null;
                z = true;
            } else {
                int length;
                int f = ((b) this.b).f();
                int abs = Math.abs(f);
                if (f > 0) {
                    if (this.b != null) {
                        ((b) this.e).b(((b) this.b).g());
                    }
                    length = ((b) this.b).c.length;
                } else if (this.b != null) {
                    ((b) this.e).b(((b) this.b).g() - 1);
                    length = abs;
                } else {
                    length = abs;
                }
                ((b) this.e).a(0);
                if (b(length) != null) {
                    if (this.b != null) {
                        this.d = this.b;
                    }
                    this.b = this.e.c();
                    z = true;
                }
            }
        }
        z = false;
        return z;
    }

    public boolean s() {
        try {
            this.a.close();
            return true;
        } catch (IOException e) {
            f.a("ReaderUmdFileInput", "close failed!" + e);
            return false;
        }
    }

    public boolean a(c cVar, int i) {
        int g;
        boolean e;
        int size = x().size();
        if (i == 0) {
            if (this.b == null) {
                return true;
            }
            g = ((b) cVar).g();
            e = e(cVar);
        } else if (this.d == null) {
            return true;
        } else {
            g = ((b) cVar).g();
            e = e(cVar);
        }
        if (g < size - 1 || !r0) {
            e = false;
        } else {
            e = true;
        }
        return e;
    }

    public boolean e(c cVar) {
        int f;
        int length = ((b) cVar).c.length;
        if (((b) cVar).f() > 0) {
            f = ((b) cVar).f() + length;
        } else {
            f = length;
        }
        return this.h <= f;
    }

    public void b(g gVar) {
    }

    public boolean c(c cVar) {
        int size = cVar.j().size();
        if (size >= cVar.k) {
            return true;
        }
        ArrayList arrayList = new ArrayList();
        while (true) {
            int i = size - 1;
            if (size > 0) {
                arrayList.add(cVar.d(i));
                size = i;
            } else {
                ((b) cVar).a(arrayList);
                ((b) cVar).a();
                return false;
            }
        }
    }

    public boolean q() {
        try {
            if (this.c == null || !((b) this.c).d()) {
                return false;
            }
            this.c = null;
            return true;
        } catch (ClassCastException e) {
            return false;
        }
    }

    public byte[] a(byte[] bArr) {
        int i;
        int length = bArr.length;
        int i2 = length - 1;
        while (bArr[i2] == (byte) 0) {
            i2 = length - 1;
            length = i2;
        }
        if (i2 % 2 == 0) {
            i = i2 + 1;
        } else {
            i = i2;
        }
        if (i == length - 1) {
            return bArr;
        }
        byte[] bArr2 = new byte[(i + 1)];
        for (length = 0; length <= i; length++) {
            bArr2[length] = bArr[length];
        }
        return bArr2;
    }

    public g a(double d) {
        g gVar = new g();
        gVar.a((long) (((double) v()) * d));
        return gVar;
    }

    public long a(long j) {
        return this.i;
    }

    public boolean b(c cVar) {
        return a(cVar);
    }

    public boolean b(c cVar, int i) {
        return a(cVar, i);
    }

    public boolean m() {
        return false;
    }

    public boolean n() {
        return false;
    }

    public g p() {
        g gVar = new g();
        gVar.a(b().d);
        return gVar;
    }

    public int d(g gVar) {
        int e = ((int) gVar.e()) % 32768;
        int f = ((b) b()).f();
        if (f > 0) {
            f = e - f;
        } else {
            f = (f * -1) + e;
        }
        return f % 32768;
    }

    public a c(g gVar) {
        try {
            int d = d(gVar);
            byte[] bytes = b().g.getBytes(w().getEncodingStr());
            return new com.qq.reader.plugin.tts.model.g(new String(bytes, d, bytes.length - d, w().getEncodingStr()), gVar, w().getEncodingStr());
        } catch (UnsupportedEncodingException e) {
            f.a("ReaderUmdFileInput", e.getMessage());
            return null;
        }
    }

    public a r() {
        int g;
        b bVar = (b) b();
        g gVar = new g();
        int f = bVar.f();
        if (f > 0) {
            g = (bVar.g() * 32768) + f;
        } else {
            g = ((bVar.g() + 1) * 32768) + f;
        }
        gVar.a((long) g);
        return c(gVar);
    }
}
