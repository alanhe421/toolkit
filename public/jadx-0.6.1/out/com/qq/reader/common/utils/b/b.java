package com.qq.reader.common.utils.b;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;

/* compiled from: CSVReader */
public class b implements Closeable {
    private BufferedReader a;
    private boolean b;
    private a c;
    private int d;
    private boolean e;

    public b(Reader reader) {
        this(reader, ',', '\"', '\\');
    }

    public b(Reader reader, char c, char c2, char c3) {
        this(reader, c, c2, c3, 0, false);
    }

    public b(Reader reader, char c, char c2, char c3, int i, boolean z) {
        this(reader, c, c2, c3, i, z, true);
    }

    public b(Reader reader, char c, char c2, char c3, int i, boolean z, boolean z2) {
        this.b = true;
        this.a = new BufferedReader(reader);
        this.c = new a(c, c2, c3, z, z2);
        this.d = i;
    }

    public String[] a() throws IOException {
        Object obj = null;
        while (true) {
            String b = b();
            if (!this.b) {
                return obj;
            }
            Object a = this.c.a(b);
            if (a.length <= 0) {
                a = obj;
            } else if (obj != null) {
                Object obj2 = new String[(obj.length + a.length)];
                System.arraycopy(obj, 0, obj2, 0, obj.length);
                System.arraycopy(a, 0, obj2, obj.length, a.length);
                a = obj2;
            }
            if (!this.c.a()) {
                return a;
            }
            obj = a;
        }
    }

    private String b() throws IOException {
        if (!this.e) {
            for (int i = 0; i < this.d; i++) {
                this.a.readLine();
            }
            this.e = true;
        }
        String readLine = this.a.readLine();
        if (readLine == null) {
            this.b = false;
        }
        return this.b ? readLine : null;
    }

    public void close() throws IOException {
        this.a.close();
    }
}
