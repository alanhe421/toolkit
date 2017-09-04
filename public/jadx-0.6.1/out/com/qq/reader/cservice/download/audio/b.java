package com.qq.reader.cservice.download.audio;

import com.qq.reader.common.c.a;
import com.qq.reader.common.db.handle.v;
import com.qq.reader.cservice.onlineread.OnlineTag;

/* compiled from: AudioBookDownloadTask */
public class b extends com.qq.reader.cservice.download.a.b {
    public long a;
    public int b;
    public String c;
    public OnlineTag d;
    public int e;

    public b(OnlineTag onlineTag, long j, int i, String str, String str2, String str3, String str4, int i2) {
        super(0, str2, str3, str4);
        this.e = i2;
        this.a = j;
        this.b = i;
        this.c = str;
        this.d = onlineTag.z();
    }

    public b(long j, int i, String str, String str2, String str3, String str4, int i2, int i3) {
        super(0, str2, str3, str4, i2, -3230);
        this.e = i3;
        this.a = j;
        this.b = i;
        this.c = str;
        this.d = v.b().a(String.valueOf(j));
    }

    protected void a() {
        super.a();
    }

    public String b() {
        return this.c;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof b)) {
            return false;
        }
        if (this.a == ((b) obj).a && this.b == ((b) obj).b) {
            return true;
        }
        return false;
    }

    protected String c() {
        return a.bm;
    }

    protected void d() {
        a.a().g(this);
    }

    protected void a(com.qq.reader.cservice.download.a.b bVar, boolean z) {
        a.a().e(bVar, z);
    }

    protected void e() {
        a.a().f(this);
    }

    protected void f() {
    }
}
