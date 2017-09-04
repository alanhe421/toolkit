package com.qq.reader.readengine.fileparse;

import com.google.zxing.common.StringUtils;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.utils.c.b;
import com.qq.reader.plugin.tts.model.a;
import com.qq.reader.readengine.model.BookTxt;
import com.qq.reader.readengine.model.IBook;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;

/* compiled from: ReaderTxtFileInput */
public class g extends d {
    private long g = 0;
    private long h = 0;
    private int i = 0;

    public /* synthetic */ IBook t() {
        return w();
    }

    public g(BookTxt bookTxt) throws FileNotFoundException {
        if (bookTxt == null || bookTxt.getBookPath().length() == 0) {
            throw new FileNotFoundException();
        }
        this.f = bookTxt;
        this.a = new RandomAccessFile(this.f.getBookPath(), "r");
        x();
        bookTxt.setLength(this.h);
        this.e = new a();
    }

    public void a() {
        super.a();
        this.e = new a();
    }

    public e u() throws FileNotFoundException {
        return this;
    }

    private void x() {
        if (this.h <= 0) {
            try {
                this.h = this.a.length();
            } catch (IOException e) {
                f.a("ReaderTxtFileInput", "read failed!" + e);
            }
        }
    }

    public long v() {
        return this.h;
    }

    public synchronized boolean d(com.qq.reader.readengine.kernel.g gVar) {
        boolean z = false;
        synchronized (this) {
            if (this.a != null) {
                try {
                    this.c = null;
                    this.b = null;
                    this.d = null;
                    this.e.d = 0;
                    this.e.e = 0;
                    this.e.g = "";
                    x();
                    this.a.seek(0);
                    z = y();
                    if (z && this.b != null) {
                        this.b.n = gVar.g();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return z;
    }

    public synchronized boolean a(com.qq.reader.readengine.kernel.g gVar, boolean z) {
        boolean z2 = false;
        synchronized (this) {
            if (this.a != null) {
                long e = gVar.e();
                if (z) {
                    try {
                        e = b(e);
                    } catch (Exception e2) {
                        f.a("ReaderTxtFileInput", "getBestPoint is error", e2);
                    }
                }
                this.c = null;
                this.b = null;
                this.d = null;
                this.e.d = 0;
                this.e.e = 0;
                this.e.g = "";
                x();
                try {
                    this.a.seek(e);
                    z2 = z();
                } catch (Exception e22) {
                    f.a("ReaderTxtFileInput", "file seek error", e22);
                }
            }
        }
        return z2;
    }

    private void e(c cVar) throws IOException {
        boolean z;
        long filePointer = this.a.getFilePointer();
        if (cVar.d > 0) {
            long min = cVar.d - ((long) ((int) Math.min(20, cVar.d)));
            if (min > 0) {
                min = b(min);
            }
            int i = (int) (cVar.d - min);
            if (i > 0) {
                this.a.seek(min);
                byte[] bArr = new byte[i];
                this.a.read(bArr);
                char[] toCharArray = a(i, bArr).toCharArray();
                z = false;
                for (char c : toCharArray) {
                    if (c == '\n' || c == '\r' || c == ' ') {
                        z = true;
                    } else if (z && (c == ' ' || c == '　' || c == ' ')) {
                        z = true;
                    } else {
                        z = false;
                    }
                }
            } else {
                z = false;
            }
            this.a.seek(filePointer);
        } else {
            z = true;
        }
        cVar.f = z;
    }

    private long b(long j) throws IOException {
        String encodingStr = this.f.getEncodingStr();
        if (encodingStr.equals("UTF-16BE") || encodingStr.equals("UTF-16LE")) {
            return j - (j % 2);
        }
        if (encodingStr.equals("UTF-8")) {
            return c(j);
        }
        this.i = 0;
        long a = a(j, 200);
        if (!encodingStr.equals("GBK") && !encodingStr.equals(StringUtils.GB2312)) {
            return j;
        }
        this.a.seek(a);
        return a(a, j);
    }

    private long a(long j, long j2) throws IOException {
        long j3 = j;
        while (j3 < j2) {
            int read = this.a.read();
            j3++;
            if (read > Opcodes.INT_TO_LONG) {
                this.a.skipBytes(1);
                j3++;
            } else if (read < 0) {
                return j3 - 1;
            }
        }
        return j3;
    }

    private long a(long j, int i) throws IOException {
        byte[] bArr = new byte[i];
        while (j > 0) {
            long j2 = j - ((long) i);
            long j3 = j2 > 0 ? j2 : 0;
            if (j3 <= 0) {
                this.a.seek(0);
                return 0;
            }
            this.a.seek(j3);
            this.a.read(bArr);
            int length = bArr.length - 1;
            while (length > 0) {
                if (bArr[length] >= (byte) 0 && bArr[length] < (byte) 64) {
                    return (((long) length) + j3) + 1;
                }
                length--;
            }
            j = j3;
        }
        return 0;
    }

    private long c(long j) throws IOException {
        this.a.seek(j);
        byte[] bArr = new byte[50];
        this.a.read(bArr);
        int i = 0;
        do {
            byte b = bArr[i];
            i++;
            if ((b & Opcodes.AND_LONG_2ADDR) != 128) {
                break;
            }
        } while (i < 6);
        return ((long) (i - 1)) + j;
    }

    public synchronized boolean i() {
        boolean z = false;
        synchronized (this) {
            if (this.a != null) {
                x();
                if (this.b == null || !a(this.b, 0)) {
                    if (this.d != null) {
                        this.c = this.b;
                        this.b = this.d;
                        this.d = null;
                        z = true;
                    } else {
                        if (this.b != null) {
                            try {
                                this.a.seek(this.b.d + ((long) this.b.e));
                            } catch (Exception e) {
                                f.a("readNextBuff", "seek failed:" + this.b.d + this.b.e, e);
                            }
                        }
                        z = z();
                    }
                }
            }
        }
        return z;
    }

    public synchronized boolean h() throws IOException {
        boolean y;
        if (this.a != null) {
            x();
            this.a.seek(0);
            y = y();
        } else {
            y = false;
        }
        return y;
    }

    private boolean y() {
        String c = c(this.e, (int) this.h);
        if (c == null || c.length() <= 0) {
            if (this.b == null) {
                this.b = this.e.c();
            }
            return false;
        }
        if (this.b != null) {
            this.c = this.b;
        }
        this.b = this.e.c();
        return true;
    }

    private boolean z() {
        String c = c(this.e, c.a);
        if (c == null || c.length() <= 0) {
            if (this.b == null) {
                this.b = this.e.c();
            }
            return false;
        }
        if (this.b != null) {
            this.c = this.b;
        }
        this.b = this.e.c();
        return true;
    }

    public boolean a(com.qq.reader.readengine.kernel.g gVar) {
        try {
            this.a.seek(gVar.e());
            z();
            this.c = null;
            this.d = null;
            return true;
        } catch (IOException e) {
            f.a("ReaderTxtFileInput", "read next buff failed!");
            return false;
        }
    }

    public synchronized void k() {
        try {
            this.a.seek(b().d + ((long) b().e));
            if (c(this.e, c.a) != null) {
                this.d = this.e.c();
            } else {
                this.d = null;
            }
        } catch (Exception e) {
            f.a("ReaderTxtFileInput", "read next buff failed!");
        }
    }

    public synchronized boolean j() throws IOException {
        boolean z = true;
        synchronized (this) {
            if (this.a != null) {
                x();
                if (this.g > 0) {
                    if (this.c != null) {
                        this.d = this.b;
                        this.b = this.c;
                        this.c = null;
                    } else {
                        int i;
                        long j = this.b.d;
                        int i2 = c.a;
                        if (j > ((long) c.a)) {
                            j -= (long) c.a;
                            long b = b(j);
                            i = ((int) (j - b)) + i2;
                            this.a.seek(b);
                        } else if (j > 0) {
                            i = (int) j;
                            this.a.seek(0);
                        } else {
                            z = false;
                        }
                        if (c(this.e, i) != null) {
                            if (this.b != null && this.b.d < this.h && this.b.e > 0) {
                                this.d = this.b;
                            }
                            this.b = this.e.c();
                        }
                    }
                }
            }
            z = false;
        }
        return z;
    }

    private synchronized String c(c cVar, int i) {
        String str;
        try {
            cVar.d = this.a.getFilePointer();
            if (i > cVar.c.length) {
                cVar.c(i);
            }
            int read = this.a.read(cVar.c, 0, i);
            if (read > 0) {
                cVar.e = read;
                this.g = this.a.getFilePointer();
                a(read, cVar);
                e(cVar);
                str = cVar.g;
            } else {
                read = (int) (v() - this.a.getFilePointer());
                if (read > 0) {
                    cVar.e = read;
                    this.g = this.a.getFilePointer();
                    cVar.g = a(cVar.e, cVar.c);
                    e(cVar);
                    str = cVar.g;
                } else {
                    str = "";
                }
            }
            cVar.m = w().getCurIndex();
            cVar.b = this;
        } catch (IOException e) {
            f.a("ReaderTxtFileInput", "read buff failed!" + e);
            str = null;
        }
        return str;
    }

    private String a(int i, c cVar) throws UnsupportedEncodingException, IOException {
        String a = a(i, cVar.c);
        cVar.g = a;
        return a;
    }

    private String a(int i, byte[] bArr) throws UnsupportedEncodingException {
        int i2;
        String a;
        byte[] bArr2 = new byte[i];
        for (i2 = 0; i2 < bArr2.length; i2++) {
            bArr2[i2] = bArr[i2];
        }
        if (-1 != this.f.getEncoding()) {
            if (this.f.getEncoding() == 0) {
                a = b.a(bArr2, 14);
            } else {
                a = b.a(bArr2, this.f.getEncoding());
            }
            if (this.f.getEncodingStr() == null) {
                this.f.setEncodingStr(b.a(this.f.getEncoding()));
            }
        } else {
            i2 = b.a(bArr2);
            if (i2 != 0) {
                this.f.setEncoding(i2);
                this.f.setEncodingStr(b.a(i2));
                a = b.a(bArr2, i2);
            } else {
                this.f.setEncoding(1);
                this.f.setEncodingStr("UTF-16LE");
                b.a(bArr2, 1);
                a = null;
            }
        }
        if (a != null) {
            return a;
        }
        throw new UnsupportedEncodingException();
    }

    public boolean s() {
        try {
            this.a.close();
            return true;
        } catch (IOException e) {
            f.a("ReaderTxtFileInput", "close failed!" + e);
            return false;
        }
    }

    public BookTxt w() {
        return (BookTxt) this.f;
    }

    public boolean a(c cVar, int i) {
        if (cVar == null) {
            return false;
        }
        return ((a) cVar).a(v());
    }

    public void b(com.qq.reader.readengine.kernel.g gVar) {
    }

    public boolean c(c cVar) {
        return true;
    }

    public boolean q() {
        return false;
    }

    public com.qq.reader.readengine.kernel.g a(double d) {
        com.qq.reader.readengine.kernel.g gVar = new com.qq.reader.readengine.kernel.g();
        gVar.a((long) (((double) v()) * d));
        return gVar;
    }

    public long a(long j) {
        return this.h;
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

    public com.qq.reader.readengine.kernel.g p() {
        com.qq.reader.readengine.kernel.g gVar = new com.qq.reader.readengine.kernel.g();
        gVar.a(b().d);
        return gVar;
    }

    public int e(com.qq.reader.readengine.kernel.g gVar) {
        return (int) (gVar.e() - b().d);
    }

    public a c(com.qq.reader.readengine.kernel.g gVar) {
        try {
            int e = e(gVar);
            byte[] bytes = b().g.getBytes(w().getEncodingStr());
            return new com.qq.reader.plugin.tts.model.g(new String(bytes, e, bytes.length - e, w().getEncodingStr()), gVar, w().getEncodingStr());
        } catch (UnsupportedEncodingException e2) {
            f.a("ReaderTxtFileInput", e2.getMessage());
            return null;
        }
    }

    public a r() {
        c b = b();
        com.qq.reader.readengine.kernel.g gVar = new com.qq.reader.readengine.kernel.g();
        gVar.a(b.d);
        return c(gVar);
    }
}
