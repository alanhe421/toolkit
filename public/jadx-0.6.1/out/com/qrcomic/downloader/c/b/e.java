package com.qrcomic.downloader.c.b;

import com.qrcomic.downloader.c.a.b;
import com.qrcomic.util.g;
import java.io.IOException;
import okhttp3.t;
import okhttp3.z;
import okio.c;
import okio.f;
import okio.j;
import okio.p;

/* compiled from: ProgressRespBody */
public class e extends z {
    private final z a;
    private final b b;
    private okio.e c;

    public e(z zVar, b bVar) {
        this.a = zVar;
        this.b = bVar;
    }

    public t a() {
        return this.a.a();
    }

    public long b() {
        return this.a.b();
    }

    public okio.e c() {
        if (this.c == null) {
            this.c = j.a(a(this.a.c()));
        }
        return this.c;
    }

    private p a(p pVar) {
        return new f(this, pVar) {
            long a = 0;
            final /* synthetic */ e b;

            public long a(c cVar, long j) throws IOException {
                long a = super.a(cVar, j);
                this.a = (a != -1 ? a : 0) + this.a;
                if (g.a()) {
                    g.b("DOWNLOAD", g.d, "download pic has downloaded data size = " + this.a);
                }
                this.b.b.a(this.a, this.b.a.b(), a == -1);
                return a;
            }
        };
    }
}
