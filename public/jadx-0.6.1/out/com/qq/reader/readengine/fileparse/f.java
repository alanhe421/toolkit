package com.qq.reader.readengine.fileparse;

import com.qq.reader.plugin.tts.model.a;
import com.qq.reader.readengine.kernel.c.e;
import com.qq.reader.readengine.kernel.g;
import com.qq.reader.readengine.model.BookTxt;
import com.qq.reader.readengine.model.IBook;
import com.qq.reader.readengine.model.MulitFile;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/* compiled from: ReaderOnlineInput */
public class f extends d {
    private String g = f.class.getName();
    private g h = null;
    private MulitFile i = null;

    public f(IBook iBook) {
        this.f = iBook;
        this.i = this.f.getMulitFile();
    }

    public g a(String str, int i) throws FileNotFoundException {
        if (this.h != null) {
            this.h.s();
        }
        if (str == null) {
            this.h = null;
        } else {
            String str2 = str;
            BookTxt bookTxt = new BookTxt(this.f.getBookName(), str2, this.f.getAuthor(), 4, "", this.f.getBookNetId());
            bookTxt.setEncodingStr(this.f.getEncodingStr());
            bookTxt.setReadType(1);
            bookTxt.setmMulitFile(this.f.getMulitFile());
            this.h = new g(bookTxt);
        }
        this.i.setIndex(i);
        return this.h;
    }

    public boolean i() {
        if (this.h == null || this.h.a(this.h.b(), 0)) {
            return false;
        }
        return this.h.i();
    }

    public boolean j() throws IOException {
        if (this.h == null || this.h.a(this.h.b())) {
            return false;
        }
        return this.h.j();
    }

    public void k() {
        if (this.h != null && !this.h.a(this.h.b(), 0)) {
            this.h.k();
        }
    }

    public boolean a(g gVar) {
        if (this.h == null) {
            return false;
        }
        gVar.a(gVar.g());
        return this.h.d(gVar);
    }

    public boolean a(g gVar, boolean z) {
        if (gVar.f() != this.f.getCurIndex() || this.h == null) {
            return false;
        }
        gVar.a(gVar.g());
        return this.h.d(gVar);
    }

    public long v() {
        if (this.f == null) {
            return 0;
        }
        return this.i.getFileLength(this.f.getCurIndex() - 1);
    }

    public boolean a(c cVar) {
        if (this.h == null) {
            return true;
        }
        return this.h.a(cVar);
    }

    public boolean a(c cVar, int i) {
        if (this.h == null) {
            return true;
        }
        return this.h.a(cVar, i);
    }

    public boolean c(c cVar) {
        if (this.h == null) {
            return false;
        }
        return this.h.c(cVar);
    }

    public boolean q() {
        if (this.h == null) {
            return false;
        }
        return this.h.q();
    }

    public boolean s() {
        if (this.h == null) {
            return true;
        }
        return this.h.s();
    }

    public IBook t() {
        return this.f;
    }

    public c b() {
        return this.h == null ? null : this.h.b();
    }

    public c d() {
        return this.h == null ? null : this.h.d();
    }

    public c c() {
        return this.h == null ? null : this.h.c();
    }

    public void b(g gVar) {
        if (this.h == null) {
            gVar.a(this.f.getCurIndex(), 0);
        }
    }

    public e u() throws Exception {
        return this;
    }

    public synchronized boolean h() throws IOException {
        boolean z;
        if (this.h == null) {
            z = false;
        } else {
            z = this.h.h();
        }
        return z;
    }

    public g a(double d) {
        int listCount;
        if (d == 1.0d) {
            listCount = this.i.getListCount();
        } else {
            listCount = ((int) (((double) this.i.getListCount()) * d)) + 1;
        }
        g gVar = new g();
        gVar.a(listCount, 0);
        return gVar;
    }

    public long a(long j) {
        long totalLength = this.i.getTotalLength(j);
        if (totalLength == 0) {
            return (long) this.i.getListCount();
        }
        return totalLength;
    }

    public boolean b(c cVar) {
        if (this.h == null) {
            return this.i.isFirstFile();
        }
        return a(cVar) && this.i.isFirstFile();
    }

    public boolean b(c cVar, int i) {
        if (this.h == null) {
            return this.i.isLastFile();
        }
        return a(cVar, i) && this.i.isLastFile();
    }

    public boolean l() {
        if (this.f.getCurIndex() > this.f.getFileCount()) {
            return true;
        }
        return false;
    }

    public boolean m() {
        return this.i.nextFileExist();
    }

    public boolean n() {
        return this.i.prevFileExist();
    }

    public boolean g() {
        return this.h != null && this.h.g();
    }

    public g p() {
        g gVar = new g();
        if (this.h == null) {
            gVar.a(this.f.getCurIndex(), 0);
        } else {
            gVar.a(this.f.getCurIndex(), b().d);
        }
        return gVar;
    }

    public boolean a(int i) {
        return this.i.checkExist(i);
    }

    public int o() {
        if (this.i.getBookFileList() != null) {
            return this.i.getBookFileList().size();
        }
        return 1;
    }

    public boolean a(boolean z, c cVar) {
        if (this.h == null) {
            return false;
        }
        this.h = (g) cVar.b;
        return this.h.a(z, cVar);
    }

    public int d(g gVar) {
        return (int) (gVar.g() - b().d);
    }

    public a c(g gVar) {
        try {
            int d = d(gVar);
            byte[] bytes = b().g.getBytes(t().getEncodingStr());
            return new com.qq.reader.plugin.tts.model.g(new String(bytes, d, bytes.length - d, t().getEncodingStr()), gVar, t().getEncodingStr());
        } catch (Exception e) {
            com.qq.reader.common.monitor.f.a(this.g, e.getMessage());
            return null;
        }
    }

    public a r() {
        c b = b();
        g gVar = new g();
        gVar.a(b.m, b.d);
        return c(gVar);
    }

    public int d(c cVar) {
        List list = cVar.o;
        int size = list.size();
        long j = cVar.n;
        cVar.n = -1;
        if (j <= 0) {
            return 0;
        }
        int i;
        int i2 = size - 1;
        int i3 = 0;
        int i4 = 0;
        while (i4 <= i2) {
            int i5 = (i4 + i2) >>> 1;
            long d = ((e) list.get(i5)).d();
            if (j <= d) {
                if (j >= d) {
                    i = i5;
                    break;
                }
                i2 = i4;
                i = i3;
                i3 = i5 - 1;
            } else {
                i4 = i5 + 1;
                if (i4 > size - 1) {
                    i = size - 1;
                    i3 = i2;
                    i2 = i4;
                } else if (j < ((e) list.get(i4)).d()) {
                    i = i5;
                    break;
                } else {
                    i = i3;
                    i3 = i2;
                    i2 = i4;
                }
            }
            i4 = i2;
            i2 = i3;
            i3 = i;
        }
        i = i3;
        if (i < 0) {
            i = 0;
        }
        if (i >= size) {
            return size - 1;
        }
        return i;
    }
}
