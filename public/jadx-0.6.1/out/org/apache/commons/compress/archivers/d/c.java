package org.apache.commons.compress.archivers.d;

import java.io.IOException;

/* compiled from: TarArchiveSparseEntry */
public class c {
    private final boolean a;

    public c(byte[] bArr) throws IOException {
        this.a = e.a(bArr, 504);
    }

    public boolean a() {
        return this.a;
    }
}
