package okio;

import java.io.IOException;

/* compiled from: ForwardingSource */
public abstract class f implements p {
    private final p a;

    public f(p pVar) {
        if (pVar == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        this.a = pVar;
    }

    public long a(c cVar, long j) throws IOException {
        return this.a.a(cVar, j);
    }

    public q a() {
        return this.a.a();
    }

    public void close() throws IOException {
        this.a.close();
    }

    public String toString() {
        return getClass().getSimpleName() + "(" + this.a.toString() + ")";
    }
}
