package okio;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/* compiled from: ForwardingTimeout */
public class g extends q {
    private q a;

    public g(q qVar) {
        if (qVar == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        this.a = qVar;
    }

    public final q a() {
        return this.a;
    }

    public final g a(q qVar) {
        if (qVar == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        this.a = qVar;
        return this;
    }

    public q a(long j, TimeUnit timeUnit) {
        return this.a.a(j, timeUnit);
    }

    public long l_() {
        return this.a.l_();
    }

    public boolean m_() {
        return this.a.m_();
    }

    public long d() {
        return this.a.d();
    }

    public q a(long j) {
        return this.a.a(j);
    }

    public q n_() {
        return this.a.n_();
    }

    public q o_() {
        return this.a.o_();
    }

    public void g() throws IOException {
        this.a.g();
    }
}
