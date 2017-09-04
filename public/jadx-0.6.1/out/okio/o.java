package okio;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;

/* compiled from: Sink */
public interface o extends Closeable, Flushable {
    q a();

    void a_(c cVar, long j) throws IOException;

    void close() throws IOException;

    void flush() throws IOException;
}
