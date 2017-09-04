package format.epub.zip;

import com.qq.reader.common.drm.a;
import java.io.IOException;

/* compiled from: LocalFileHeader */
public class c {
    int a;
    int b;
    int c;
    int d;
    int e;
    int f;
    int g;
    int h;
    int i;
    int j;
    int k;
    public String l;
    int m;
    byte[] n = new byte[12];
    int[] o = new int[3];
    boolean p;

    c() {
    }

    void a(d dVar) throws IOException {
        this.a = dVar.c();
        switch (this.a) {
            case 33639248:
                this.b = dVar.c();
                this.c = dVar.b();
                this.d = dVar.b();
                this.e = dVar.b();
                this.f = dVar.b();
                this.g = dVar.c();
                this.h = dVar.c();
                this.i = dVar.c();
                if (this.d == 0 && this.h != this.i) {
                    this.h = this.i;
                }
                this.j = dVar.b();
                this.k = dVar.b();
                int b = dVar.b();
                dVar.b(12);
                this.l = dVar.a(this.j);
                dVar.b(this.k);
                dVar.b(b);
                break;
            case 67324752:
                this.b = dVar.b();
                this.c = dVar.b();
                this.d = dVar.b();
                if (((this.c & 255) & 1) != 0) {
                    this.p = true;
                }
                this.e = dVar.b();
                this.f = dVar.b();
                this.g = dVar.c();
                this.h = dVar.c();
                this.i = dVar.c();
                if (this.p) {
                    this.h -= 12;
                }
                if (this.d == 0 && this.h != this.i) {
                    this.h = this.i;
                }
                this.j = dVar.b();
                this.k = dVar.b();
                this.l = dVar.a(this.j);
                dVar.b(this.k);
                if (this.p) {
                    dVar.read(this.n, 0, 12);
                    a();
                    break;
                }
                break;
            case 101010256:
                dVar.b(16);
                dVar.b(dVar.b());
                break;
            case 134695760:
                this.g = dVar.c();
                this.h = dVar.c();
                this.i = dVar.c();
                break;
        }
        this.m = dVar.a();
    }

    void a() throws IOException {
        String b = a.b();
        if (b != null) {
            b.a(b.getBytes("US-ASCII"), this.o);
            for (int i = 0; i < 12; i++) {
                b.a(this.o, this.n[i]);
            }
            return;
        }
        throw new IOException("zip key is null!!!");
    }
}
