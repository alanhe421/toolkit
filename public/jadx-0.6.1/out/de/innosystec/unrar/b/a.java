package de.innosystec.unrar.b;

import java.io.IOException;

/* compiled from: IReadOnlyAccess */
public interface a {
    int a(byte[] bArr, int i) throws IOException;

    long a() throws IOException;

    void a(long j) throws IOException;

    void close() throws IOException;

    int read() throws IOException;

    int read(byte[] bArr, int i, int i2) throws IOException;
}
