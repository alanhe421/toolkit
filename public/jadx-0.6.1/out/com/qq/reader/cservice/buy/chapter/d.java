package com.qq.reader.cservice.buy.chapter;

import com.qq.reader.common.db.handle.v;
import com.qq.reader.module.bookchapter.online.OnlineChapter;
import java.io.File;

/* compiled from: OnlineChapterPayItem */
public class d {
    private OnlineChapter a;
    private boolean b;
    private File c = null;

    public d(OnlineChapter onlineChapter) {
        this.a = onlineChapter;
        String a = v.a("" + this.a.getBookId(), this.a.getChapterId());
        if (a != null) {
            this.c = new File(a);
        }
    }

    public OnlineChapter a() {
        return this.a;
    }

    public String b() {
        return this.a.getChapterName();
    }

    public float c() {
        return this.a.getPrice();
    }

    public float d() {
        return this.a.getSize();
    }

    public int e() {
        return this.a.getChapterId();
    }

    public boolean f() {
        return this.b;
    }

    public void a(boolean z) {
        this.b = z;
    }

    public boolean g() {
        return (this.a.getBooleanIsFree() || this.b) ? false : true;
    }

    public boolean h() {
        return this.a.getBooleanIsFree();
    }

    public boolean i() {
        if (this.c != null) {
            return this.c.exists();
        }
        return false;
    }

    public int hashCode() {
        long bookId = this.a.getBookId();
        return ((int) (bookId ^ ((long) (this.a.getChapterId() >>> 32)))) + ((((int) ((bookId >>> 32) ^ bookId)) + 31) * 31);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof d)) {
            return false;
        }
        long bookId = this.a.getBookId();
        int chapterId = this.a.getChapterId();
        d dVar = (d) obj;
        if (bookId != dVar.a.getBookId()) {
            return false;
        }
        if (chapterId != dVar.a.getChapterId()) {
            return false;
        }
        return true;
    }
}
