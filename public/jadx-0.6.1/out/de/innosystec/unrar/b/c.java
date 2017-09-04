package de.innosystec.unrar.b;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/* compiled from: ReadOnlyAccessFile */
public class c extends RandomAccessFile implements a {
    static final /* synthetic */ boolean a = (!c.class.desiredAssertionStatus());

    public c(File file) throws FileNotFoundException {
        super(file, "r");
    }

    public int a(byte[] bArr, int i) throws IOException {
        if (a || i > 0) {
            readFully(bArr, 0, i);
            return i;
        }
        throw new AssertionError(i);
    }

    public long a() throws IOException {
        return getFilePointer();
    }

    public void a(long j) throws IOException {
        seek(j);
    }
}
