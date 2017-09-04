package okio;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;

/* compiled from: Timeout */
public class q {
    public static final q b = new q() {
        public q a(long j, TimeUnit timeUnit) {
            return this;
        }

        public q a(long j) {
            return this;
        }

        public void g() throws IOException {
        }
    };
    private boolean a;
    private long c;
    private long d;

    public q a(long j, TimeUnit timeUnit) {
        if (j < 0) {
            throw new IllegalArgumentException("timeout < 0: " + j);
        } else if (timeUnit == null) {
            throw new IllegalArgumentException("unit == null");
        } else {
            this.d = timeUnit.toNanos(j);
            return this;
        }
    }

    public long l_() {
        return this.d;
    }

    public boolean m_() {
        return this.a;
    }

    public long d() {
        if (this.a) {
            return this.c;
        }
        throw new IllegalStateException("No deadline");
    }

    public q a(long j) {
        this.a = true;
        this.c = j;
        return this;
    }

    public q n_() {
        this.d = 0;
        return this;
    }

    public q o_() {
        this.a = false;
        return this;
    }

    public void g() throws IOException {
        if (Thread.interrupted()) {
            throw new InterruptedIOException("thread interrupted");
        } else if (this.a && this.c - System.nanoTime() <= 0) {
            throw new InterruptedIOException("deadline reached");
        }
    }
}
