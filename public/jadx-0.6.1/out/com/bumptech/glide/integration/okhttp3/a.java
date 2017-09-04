package com.bumptech.glide.integration.okhttp3;

import com.bumptech.glide.Priority;
import com.bumptech.glide.g.b;
import com.bumptech.glide.load.a.c;
import com.bumptech.glide.load.b.d;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map.Entry;
import okhttp3.e;
import okhttp3.y;
import okhttp3.z;

/* compiled from: OkHttpStreamFetcher */
public class a implements c<InputStream> {
    private final okhttp3.e.a a;
    private final d b;
    private InputStream c;
    private z d;
    private volatile e e;

    public /* synthetic */ Object b(Priority priority) throws Exception {
        return a(priority);
    }

    public a(okhttp3.e.a aVar, d dVar) {
        this.a = aVar;
        this.b = dVar;
    }

    public InputStream a(Priority priority) throws Exception {
        okhttp3.w.a a = new okhttp3.w.a().a(this.b.b());
        for (Entry entry : this.b.c().entrySet()) {
            a.b((String) entry.getKey(), (String) entry.getValue());
        }
        this.e = this.a.a(a.b());
        y a2 = this.e.a();
        this.d = a2.g();
        if (a2.c()) {
            this.c = b.a(this.d.d(), this.d.b());
            return this.c;
        }
        throw new IOException("Request failed with code: " + a2.b());
    }

    public void a() {
        try {
            if (this.c != null) {
                this.c.close();
            }
        } catch (IOException e) {
        }
        if (this.d != null) {
            this.d.close();
        }
    }

    public String b() {
        return this.b.d();
    }

    public void c() {
        e eVar = this.e;
        if (eVar != null) {
            eVar.b();
        }
    }
}
