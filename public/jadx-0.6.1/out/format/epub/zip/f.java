package format.epub.zip;

import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Queue;

/* compiled from: ZipFile */
public final class f {
    private final a a;
    private final LinkedHashMap<String, c> b = new ZipFile$1(this);
    private boolean c;
    private boolean d = true;
    private final Queue<d> e = new LinkedList();

    /* compiled from: ZipFile */
    public interface a {
        InputStream a() throws IOException;
    }

    public f(a aVar) {
        this.a = aVar;
    }

    public Collection<c> a() {
        try {
            c();
        } catch (IOException e) {
            Log.e("ZipFile", "zip name :" + toString() + "error = " + e.toString());
        }
        Collection<c> arrayList = new ArrayList();
        arrayList.addAll(this.b.values());
        return arrayList;
    }

    private boolean a(d dVar, String str) throws IOException {
        c cVar = new c();
        cVar.a(dVar);
        if (cVar.a != 67324752) {
            return false;
        }
        if (cVar.l != null) {
            this.b.put(cVar.l, cVar);
            if (cVar.l.equalsIgnoreCase(str)) {
                return true;
            }
        }
        if ((cVar.c & 8) == 0) {
            dVar.b(cVar.h);
            return false;
        }
        a(dVar, cVar);
        return false;
    }

    private void c() throws IOException {
        if (!this.c) {
            this.c = true;
            d b = b();
            b.d(0);
            this.b.clear();
            while (true) {
                try {
                    a(b, null);
                } catch (Throwable th) {
                    a(b);
                }
            }
        }
    }

    private void a(d dVar, c cVar) throws IOException {
        a a = a.a(dVar, cVar);
        int i = 0;
        while (true) {
            int a2 = a.a(null, 0, 2048);
            if (a2 <= 0) {
                cVar.i = i;
                return;
            }
            i += a2;
        }
    }

    synchronized void a(d dVar) {
        this.e.add(dVar);
    }

    synchronized d b() throws IOException {
        d dVar;
        dVar = (d) this.e.poll();
        if (dVar == null) {
            dVar = new d(this.a);
        }
        return dVar;
    }

    private g a(c cVar) throws IOException {
        return new g(this, cVar);
    }

    public int a(String str) throws IOException {
        return c(str).i;
    }

    public InputStream b(String str) throws IOException {
        return a(c(str));
    }

    public c c(String str) throws IOException {
        c cVar;
        if (!this.b.isEmpty()) {
            cVar = (c) this.b.get(str);
            if (cVar == null) {
                if (this.c) {
                    throw new ZipException("Entry " + str + " is not found");
                }
            }
            return cVar;
        }
        d b = b();
        b.d(0);
        do {
            try {
            } catch (Exception e) {
                e.printStackTrace();
            } catch (Throwable th) {
                a(b);
            }
        } while (!a(b, str));
        cVar = (c) this.b.get(str);
        if (cVar != null) {
            a(b);
            return cVar;
        }
        a(b);
        throw new ZipException("Entry " + str + " is not found");
    }
}
