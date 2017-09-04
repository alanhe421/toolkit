package org.apache.commons.compress.archivers.zip;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CodingErrorAction;

/* compiled from: NioZipEncoding */
class g implements p {
    private final Charset a;

    public g(Charset charset) {
        this.a = charset;
    }

    public String a(byte[] bArr) throws IOException {
        return this.a.newDecoder().onMalformedInput(CodingErrorAction.REPORT).onUnmappableCharacter(CodingErrorAction.REPORT).decode(ByteBuffer.wrap(bArr)).toString();
    }
}
