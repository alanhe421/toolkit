package format.epub.common.core.a;

import android.util.Log;
import format.epub.common.b.b;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: ZLXMLProcessor */
public abstract class e {
    public static boolean a(f fVar, InputStream inputStream, int i) {
        IOException e;
        Throwable th;
        d dVar;
        try {
            dVar = new d(fVar, inputStream, i);
            try {
                fVar.m();
                dVar.b();
                fVar.n();
                if (dVar != null) {
                    dVar.a();
                }
                return true;
            } catch (IOException e2) {
                e = e2;
                try {
                    e.printStackTrace();
                    if (dVar != null) {
                        return false;
                    }
                    dVar.a();
                    return false;
                } catch (Throwable th2) {
                    th = th2;
                    if (dVar != null) {
                        dVar.a();
                    }
                    throw th;
                }
            }
        } catch (IOException e3) {
            e = e3;
            dVar = null;
            e.printStackTrace();
            if (dVar != null) {
                return false;
            }
            dVar.a();
            return false;
        } catch (Throwable th3) {
            th = th3;
            dVar = null;
            if (dVar != null) {
                dVar.a();
            }
            throw th;
        }
    }

    public static boolean a(f fVar, b bVar) {
        return a(fVar, bVar, 65536);
    }

    public static boolean a(f fVar, b bVar, int i) {
        InputStream i2;
        InputStream inputStream = null;
        try {
            i2 = bVar.i();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("XMLProcessor", "read error ::" + e.getMessage());
            i2 = inputStream;
        }
        if (i2 == null) {
            return false;
        }
        boolean a = a(fVar, i2, i);
        try {
            i2.close();
            return a;
        } catch (IOException e2) {
            return a;
        }
    }
}
