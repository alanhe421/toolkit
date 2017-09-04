package format.epub.common.b;

import format.epub.zip.c;
import format.epub.zip.f.a;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/* compiled from: ZLZipEntryFile */
public final class f extends a {
    private static HashMap<b, format.epub.zip.f> d = new HashMap();

    static List<b> b(b bVar) {
        try {
            Collection<c> a = d(bVar).a();
            if (!a.isEmpty()) {
                ArrayList arrayList = new ArrayList(a.size());
                for (c cVar : a) {
                    arrayList.add(new f(bVar, cVar.l));
                }
                return arrayList;
            }
        } catch (IOException e) {
        }
        return Collections.emptyList();
    }

    private static format.epub.zip.f d(final b bVar) throws IOException {
        format.epub.zip.f fVar;
        synchronized (d) {
            fVar = bVar.o() ? (format.epub.zip.f) d.get(bVar) : null;
            if (fVar == null) {
                fVar = new format.epub.zip.f(new a() {
                    public InputStream a() throws IOException {
                        return bVar.i();
                    }
                });
                if (bVar.o()) {
                    d.put(bVar, fVar);
                }
            }
        }
        return fVar;
    }

    static void c(b bVar) {
        d.remove(bVar);
    }

    f(b bVar, String str) {
        super(bVar, str);
    }

    public long h() {
        try {
            return (long) d(this.a).a(this.b);
        } catch (IOException e) {
            return 0;
        }
    }

    public InputStream i() throws IOException {
        return d(this.a).b(this.b);
    }

    public static void q() {
        d.clear();
    }
}
