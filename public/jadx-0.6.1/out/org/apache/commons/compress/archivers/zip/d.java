package org.apache.commons.compress.archivers.zip;

import java.io.IOException;

/* compiled from: FallbackZipEncoding */
class d implements p {
    private final String a;

    public d() {
        this.a = null;
    }

    public d(String str) {
        this.a = str;
    }

    public String a(byte[] bArr) throws IOException {
        if (this.a == null) {
            return new String(bArr);
        }
        return new String(bArr, this.a);
    }
}
