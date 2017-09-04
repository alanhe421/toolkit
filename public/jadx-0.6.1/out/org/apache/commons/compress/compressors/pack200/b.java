package org.apache.commons.compress.compressors.pack200;

import java.io.FilterOutputStream;
import java.io.OutputStream;

/* compiled from: StreamBridge */
abstract class b extends FilterOutputStream {
    private final Object a;

    protected b(OutputStream outputStream) {
        super(outputStream);
        this.a = new Object();
    }

    protected b() {
        this(null);
    }
}
