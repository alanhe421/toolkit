package com.qq.reader.module.readpage;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import com.qq.reader.readengine.kernel.PageIndex;
import format.epub.view.ZLRectNoteArrayList;
import format.epub.view.ZLTextElementAreaArrayList;

/* compiled from: PageCache */
public class i {
    private int a = 2;
    private Bitmap[] b = null;
    private PageIndex[] c = null;
    private int d;
    private int e;
    private final j f;
    private ZLTextElementAreaArrayList[] g = null;
    private ZLTextElementAreaArrayList h = null;
    private ZLRectNoteArrayList[] i = null;
    private ZLRectNoteArrayList j = null;
    private n[] k = null;
    private n l = null;
    private d[] m = null;
    private d n;

    public j a() {
        return this.f;
    }

    public i(j jVar, int i) {
        this.f = jVar;
        this.f.a(this);
        this.a = i;
        this.b = new Bitmap[this.a];
        this.c = new PageIndex[this.a];
        this.g = new ZLTextElementAreaArrayList[this.a];
        this.i = new ZLRectNoteArrayList[this.a];
        this.h = new ZLTextElementAreaArrayList();
        this.j = new ZLRectNoteArrayList();
        this.k = new n[this.a];
        this.m = new d[this.a];
        g();
    }

    public ZLTextElementAreaArrayList b() {
        return this.h;
    }

    public ZLRectNoteArrayList c() {
        return this.j;
    }

    public n d() {
        return this.l;
    }

    public d e() {
        return this.n;
    }

    public void a(int i, int i2) {
        if (!(this.d == i && this.e == i2)) {
            this.d = i;
            this.e = i2;
            for (int i3 = 0; i3 < this.a; i3++) {
                if (this.b[i3] != null) {
                    this.b[i3].recycle();
                }
                this.b[i3] = null;
            }
            System.gc();
        }
        g();
    }

    public void f() {
        for (int i = 0; i < this.a; i++) {
            if (this.b[i] != null) {
                this.b[i].recycle();
            }
            this.b[i] = null;
        }
        System.gc();
        g();
    }

    public Bitmap a(PageIndex pageIndex) {
        return a(pageIndex, 0);
    }

    public d b(PageIndex pageIndex) {
        for (int i = 0; i < this.a; i++) {
            if (this.c[i] == pageIndex) {
                return this.m[i];
            }
        }
        return null;
    }

    public Bitmap a(PageIndex pageIndex, int i) {
        for (int i2 = 0; i2 < this.a; i2++) {
            if (pageIndex == this.c[i2]) {
                this.h = this.g[i2];
                this.j = this.i[i2];
                this.l = this.k[i2];
                this.n = this.m[i2];
                return this.b[i2];
            }
        }
        int g = g(pageIndex);
        this.c[g] = pageIndex;
        if (this.b[g] == null) {
            this.b[g] = Bitmap.createBitmap(this.d, this.e, Config.ARGB_8888);
            System.gc();
        }
        if (this.g[g] == null) {
            this.g[g] = new ZLTextElementAreaArrayList();
        } else {
            this.g[g].clear();
        }
        if (this.i[g] == null) {
            this.i[g] = new ZLRectNoteArrayList();
        } else {
            this.i[g].clear();
        }
        if (this.k[g] == null) {
            this.k[g] = new n();
        } else {
            this.k[g].a();
        }
        if (this.m[g] == null) {
            this.m[g] = new d();
        }
        this.f.a(this.b[g], pageIndex, this.g[g], this.i[g], this.k[g], this.m[g], i);
        this.h = this.g[g];
        this.j = this.i[g];
        this.l = this.k[g];
        this.n = this.m[g];
        return this.b[g];
    }

    public Bitmap a(Canvas canvas, PageIndex pageIndex) {
        if (this.g[0] == null) {
            this.g[0] = new ZLTextElementAreaArrayList();
        } else {
            this.g[0].clear();
        }
        if (this.i[0] == null) {
            this.i[0] = new ZLRectNoteArrayList();
        } else {
            this.i[0].clear();
        }
        this.f.a(canvas, pageIndex, this.g[0], this.i[0]);
        this.h = this.g[0];
        this.j = this.i[0];
        return this.b[0];
    }

    public Bitmap c(PageIndex pageIndex) {
        int g = g(pageIndex);
        this.c[g] = pageIndex;
        if (this.b[g] == null) {
            this.b[g] = Bitmap.createBitmap(this.d, this.e, Config.RGB_565);
        }
        this.f.a(this.b[g], pageIndex);
        return this.b[g];
    }

    public boolean d(PageIndex pageIndex) {
        for (int i = 0; i < this.a; i++) {
            if (pageIndex == this.c[i]) {
                return true;
            }
        }
        return false;
    }

    private int g(PageIndex pageIndex) {
        int i = 0;
        for (int i2 = 0; i2 < this.a; i2++) {
            if (this.c[i2] == null) {
                return i2;
            }
        }
        while (i < this.a) {
            if (this.c[i] != PageIndex.current) {
                return i;
            }
            i++;
        }
        throw new RuntimeException("That's impossible");
    }

    public void g() {
        for (int i = 0; i < this.a; i++) {
            this.c[i] = null;
        }
    }

    public void a(boolean z) {
        for (int i = 0; i < this.a; i++) {
            if (this.c[i] != null) {
                this.c[i] = z ? this.c[i].getPrevious() : this.c[i].getNext();
            }
        }
    }

    public boolean e(PageIndex pageIndex) {
        for (int i = 0; i < this.a; i++) {
            if (this.c[i] == pageIndex) {
                return true;
            }
        }
        return false;
    }

    public void f(PageIndex pageIndex) {
        for (int i = 0; i < this.a; i++) {
            if (this.c[i] == pageIndex) {
                this.c[i] = null;
                return;
            }
        }
    }

    public void b(boolean z) {
        for (int i = 0; i < this.a; i++) {
            if (this.c[i] != null) {
                if (z) {
                    if (this.c[i] == PageIndex.previous_left) {
                        this.c[i] = null;
                    } else if (this.c[i] == PageIndex.previous_right) {
                        this.c[i] = null;
                    } else if (this.c[i] == PageIndex.current_left) {
                        this.c[i] = PageIndex.previous_left;
                    } else if (this.c[i] == PageIndex.current_right) {
                        this.c[i] = PageIndex.previous_right;
                    } else if (this.c[i] == PageIndex.next_left) {
                        this.c[i] = PageIndex.current_left;
                    } else if (this.c[i] == PageIndex.next_right) {
                        this.c[i] = PageIndex.current_right;
                    }
                } else if (this.c[i] == PageIndex.previous_left) {
                    this.c[i] = PageIndex.current_left;
                } else if (this.c[i] == PageIndex.previous_right) {
                    this.c[i] = PageIndex.current_right;
                } else if (this.c[i] == PageIndex.current_left) {
                    this.c[i] = PageIndex.next_left;
                } else if (this.c[i] == PageIndex.current_right) {
                    this.c[i] = PageIndex.next_right;
                } else if (this.c[i] == PageIndex.next_left) {
                    this.c[i] = null;
                } else if (this.c[i] == PageIndex.next_right) {
                    this.c[i] = null;
                }
            }
        }
    }
}
