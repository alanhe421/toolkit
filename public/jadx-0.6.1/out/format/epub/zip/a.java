package format.epub.zip;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

/* compiled from: Decompressor */
public abstract class a {
    private static Queue<Deflator> a = new LinkedList();

    public abstract int a() throws IOException;

    public abstract int a(byte[] bArr, int i, int i2) throws IOException;

    protected a() {
    }

    static void a(a aVar) {
        if (aVar instanceof Deflator) {
            synchronized (a) {
                a.add((Deflator) aVar);
            }
        }
    }

    public static a a(d dVar, c cVar) throws IOException {
        switch (cVar.d) {
            case 0:
                return new e(dVar, cVar);
            case 8:
                synchronized (a) {
                    if (a.isEmpty()) {
                        return new Deflator(dVar, cVar);
                    }
                    Deflator deflator = (Deflator) a.poll();
                    deflator.b(dVar, cVar);
                    return deflator;
                }
            default:
                throw new ZipException("Unsupported method of compression");
        }
    }

    public int b() throws IOException {
        return -1;
    }
}
