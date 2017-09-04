package com.qq.reader.module.comic.entity;

import com.qq.reader.common.login.c;
import java.io.File;

/* compiled from: ComicMultiDownloadPayItem */
public class m {
    private o a;

    public m(o oVar) {
        this.a = oVar;
    }

    public o a() {
        return this.a;
    }

    public String b() {
        return this.a.e();
    }

    public int c() {
        return this.a.f();
    }

    public long d() {
        return this.a.g();
    }

    public String e() {
        return this.a.d();
    }

    public String f() {
        return this.a.d();
    }

    public boolean g() {
        return this.a.a();
    }

    public void a(boolean z) {
        this.a.a(z);
    }

    public boolean h() {
        return (i() || g()) ? false : true;
    }

    public boolean i() {
        return this.a.i();
    }

    public boolean j() {
        if (c.c() == null) {
            return false;
        }
        String a = com.qrcomic.downloader.a.c.a(c.c().c(), this.a.c(), this.a.d());
        if (a == null) {
            return false;
        }
        File file = new File(a);
        if (file.exists() && file.isDirectory() && file.list().length == this.a.k()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        long parseLong = Long.parseLong(this.a.c());
        return ((int) (parseLong ^ (Long.parseLong(this.a.d()) >>> 32))) + ((((int) ((parseLong >>> 32) ^ parseLong)) + 31) * 31);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof m)) {
            return false;
        }
        String c = this.a.c();
        String d = this.a.d();
        m mVar = (m) obj;
        if (c.equals(mVar.a().c()) && d.equals(mVar.a().d())) {
            return true;
        }
        return false;
    }
}
