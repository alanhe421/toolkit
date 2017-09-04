package com.qq.reader.readengine.kernel.a;

import com.qq.reader.common.utils.ao;
import com.qq.reader.readengine.fileparse.e;
import format.epub.b.c;
import format.epub.common.b.b;
import format.epub.common.b.f;
import format.epub.common.book.BookEPub;
import format.epub.common.text.model.i;
import format.epub.view.t;
import java.io.File;

/* compiled from: EPubInput */
public class a extends e {
    boolean a = false;
    format.epub.common.a.a b;

    public a(String str, long j) {
        this.f = BookEPub.createBookForFile(str, j);
    }

    public e u() {
        return this;
    }

    public i a() {
        return this.b.b;
    }

    public long b() {
        return (long) this.b.b.b(this.b.b.b() - 1);
    }

    public int c() {
        return this.b.b.b();
    }

    public boolean a(String str) {
        String substring;
        int lastIndexOf = str.lastIndexOf(47);
        if (lastIndexOf != -1) {
            substring = str.substring(lastIndexOf + 1);
        } else {
            substring = str;
        }
        File b = ao.b(com.qq.reader.common.c.a.aH + (str.hashCode() + ao.k(substring)) + "/newmodel2.t");
        if (b == null || !b.exists()) {
            return false;
        }
        return true;
    }

    public void b(String str) {
        String substring;
        int lastIndexOf = str.lastIndexOf(47);
        if (lastIndexOf != -1) {
            substring = str.substring(lastIndexOf + 1);
        } else {
            substring = str;
        }
        substring = com.qq.reader.common.c.a.aH + (str.hashCode() + ao.k(substring)) + "/";
        if (substring != null) {
            ao.c(new File(substring));
        }
    }

    public boolean s() {
        return false;
    }

    public void e() {
        if (this.b != null) {
            this.b.b();
            this.b.b.d();
            this.b = null;
        }
        b.p();
        f.q();
        ((c) c.a()).c();
        t.a();
        format.epub.view.style.f.b();
        format.epub.b.b.d();
        format.epub.view.f.a();
    }

    public boolean f() {
        return true;
    }

    public long v() {
        return this.f.getLength();
    }
}
