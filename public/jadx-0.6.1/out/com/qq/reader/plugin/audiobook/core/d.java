package com.qq.reader.plugin.audiobook.core;

import android.content.Context;
import com.qq.reader.common.db.handle.v;
import java.io.File;

/* compiled from: AudioPlayer */
public class d {
    private a a = null;
    private final k b;

    public d(k kVar) {
        this.b = kVar;
    }

    public boolean a(Context context, SongInfo songInfo, int i) {
        try {
            if (this.a != null) {
                this.a.h();
            }
            String a = v.a(String.valueOf(songInfo.e()), songInfo.a.getChapterId());
            if (a == null) {
                this.a = new i(context, songInfo, songInfo.c(), this.b);
            } else if (new File(a).exists()) {
                this.a = new g(context, songInfo, a, this.b);
            } else {
                this.a = new i(context, songInfo, songInfo.c(), this.b);
            }
            return this.a.b();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void a() {
        if (this.a != null) {
            this.a.h();
            this.a = null;
        }
    }

    public void b() {
        if (this.a != null) {
            this.a.c();
        }
    }

    public void c() {
        if (this.a != null) {
            this.a.h();
        }
    }

    public void d() {
        if (this.a != null) {
            this.a.e();
        }
    }

    public void e() {
        if (this.a != null) {
            this.a.d();
        }
    }

    public void f() {
        if (this.a != null) {
            this.a.g();
        }
    }

    public void g() {
        if (this.a != null) {
            this.a.f();
        }
    }

    public int h() {
        if (this.a != null) {
            return this.a.a();
        }
        return 3;
    }

    public boolean i() {
        if (this.a != null) {
            return this.a.m();
        }
        return false;
    }

    public long j() {
        if (this.a != null) {
            return this.a.i();
        }
        return 0;
    }

    public long k() {
        if (this.a != null) {
            return this.a.j();
        }
        return 0;
    }

    public int l() {
        if (this.a != null) {
            return this.a.n();
        }
        return 0;
    }

    public long a(int i) {
        if (this.a != null) {
            return this.a.b(i);
        }
        return 0;
    }

    public long m() {
        if (this.a != null) {
            return this.a.k();
        }
        return 0;
    }

    public long n() {
        if (this.a != null) {
            return this.a.l();
        }
        return 0;
    }

    public void a(float f) {
        if (this.a != null) {
            this.a.a(f);
        }
    }
}
