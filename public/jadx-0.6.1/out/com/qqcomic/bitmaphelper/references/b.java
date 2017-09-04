package com.qqcomic.bitmaphelper.references;

import com.qrcomic.util.g;
import java.io.Closeable;
import java.io.IOException;

/* compiled from: Closeables */
public class b {
    public static void a(Closeable closeable, boolean z) throws IOException {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                if (!z) {
                    throw e;
                } else if (g.a()) {
                    g.a("Closeables", g.d, "IOException thrown while closing Closeable.");
                }
            }
        }
    }
}
