package format.epub.common.c.a;

import format.epub.common.b.c;
import java.io.InputStreamReader;

/* compiled from: CSSInputStream */
public class a extends c {
    private final int a = 0;
    private final int b = 1;
    private final int c = 2;
    private final int d = 3;
    private final int e = 4;
    private final int f = 5;
    private int g;
    private InputStreamReader h;
    private a i;
    private a j;

    /* compiled from: CSSInputStream */
    public class a {
        int a;
        int b = 0;
        int c = 0;
        char[] d;
        final /* synthetic */ a e;

        public a(a aVar, int i) {
            this.e = aVar;
            this.d = new char[i];
            this.a = i;
        }

        boolean a() {
            return this.b == this.c;
        }

        boolean b() {
            return this.c >= this.a;
        }
    }

    public a(InputStreamReader inputStreamReader) {
        this.h = inputStreamReader;
        this.i = new a(this, 8192);
        this.j = new a(this, 8192);
    }

    public void c() {
        if (this.j.a()) {
            this.j.c = 0;
            this.j.b = 0;
            while (!this.j.b()) {
                if (this.i.a()) {
                    this.i.b = 0;
                    try {
                        this.i.c = this.h.read(this.i.d, 0, this.i.a);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (!this.i.a() && this.i.c > 0) {
                    while (!this.i.a() && !this.j.b()) {
                        char[] cArr = this.i.d;
                        a aVar = this.i;
                        int i = aVar.b;
                        aVar.b = i + 1;
                        char c = cArr[i];
                        char[] cArr2;
                        a aVar2;
                        int i2;
                        switch (this.g) {
                            case 0:
                                switch (c) {
                                    case '\"':
                                        cArr2 = this.j.d;
                                        aVar2 = this.j;
                                        i2 = aVar2.c;
                                        aVar2.c = i2 + 1;
                                        cArr2[i2] = c;
                                        this.g = 2;
                                        break;
                                    case '\'':
                                        cArr2 = this.j.d;
                                        aVar2 = this.j;
                                        i2 = aVar2.c;
                                        aVar2.c = i2 + 1;
                                        cArr2[i2] = c;
                                        this.g = 1;
                                        break;
                                    case '/':
                                        this.g = 3;
                                        break;
                                    default:
                                        cArr2 = this.j.d;
                                        aVar2 = this.j;
                                        i2 = aVar2.c;
                                        aVar2.c = i2 + 1;
                                        cArr2[i2] = c;
                                        break;
                                }
                            case 1:
                                if (c == '\'') {
                                    this.g = 0;
                                }
                                cArr2 = this.j.d;
                                aVar2 = this.j;
                                i2 = aVar2.c;
                                aVar2.c = i2 + 1;
                                cArr2[i2] = c;
                                break;
                            case 2:
                                if (c == '\"') {
                                    this.g = 0;
                                }
                                cArr2 = this.j.d;
                                aVar2 = this.j;
                                i2 = aVar2.c;
                                aVar2.c = i2 + 1;
                                cArr2[i2] = c;
                                break;
                            case 3:
                                switch (c) {
                                    case '*':
                                        this.g = 4;
                                        break;
                                    case '/':
                                        cArr = this.j.d;
                                        aVar = this.j;
                                        i = aVar.c;
                                        aVar.c = i + 1;
                                        cArr[i] = '/';
                                        break;
                                    default:
                                        this.g = 0;
                                        cArr2 = this.j.d;
                                        aVar2 = this.j;
                                        i2 = aVar2.c;
                                        aVar2.c = i2 + 1;
                                        cArr2[i2] = '/';
                                        cArr2 = this.j.d;
                                        aVar2 = this.j;
                                        i2 = aVar2.c;
                                        aVar2.c = i2 + 1;
                                        cArr2[i2] = c;
                                        break;
                                }
                            case 4:
                                if (c != '*') {
                                    break;
                                }
                                this.g = 5;
                                break;
                            case 5:
                                switch (c) {
                                    case '*':
                                        break;
                                    case '/':
                                        this.g = 0;
                                        break;
                                    default:
                                        this.g = 4;
                                        break;
                                }
                            default:
                                break;
                        }
                    }
                }
                return;
            }
        }
    }

    public boolean a() {
        this.g = 0;
        return true;
    }

    public int a(char[] cArr, int i) {
        int i2 = 0;
        while (i2 < i) {
            c();
            if (this.j.a()) {
                break;
            }
            int min = Math.min(i - i2, this.j.c - this.j.b);
            if (cArr.length != 0) {
                for (int i3 = i2; i3 < min; i3++) {
                    char[] cArr2 = this.j.d;
                    a aVar = this.j;
                    int i4 = aVar.b;
                    aVar.b = i4 + 1;
                    cArr[i3] = cArr2[i4];
                }
            }
            i2 += min;
        }
        return i2;
    }

    public void b() {
        try {
            this.h.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
