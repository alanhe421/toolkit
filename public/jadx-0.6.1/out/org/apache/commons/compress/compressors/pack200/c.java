package org.apache.commons.compress.compressors.pack200;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/* compiled from: TempFileCachingStreamBridge */
class c extends b {
    private final File a = File.createTempFile("commons-compress", "packtemp");

    c() throws IOException {
        this.a.deleteOnExit();
        this.out = new FileOutputStream(this.a);
    }
}
