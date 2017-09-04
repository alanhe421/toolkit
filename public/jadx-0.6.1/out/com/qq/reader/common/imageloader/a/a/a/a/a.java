package com.qq.reader.common.imageloader.a.a.a.a;

import com.pay.http.APPluginErrorCode;
import com.qq.reader.common.download.task.f;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/* compiled from: DiskLruCache */
final class a implements Closeable {
    static final Pattern a = Pattern.compile("[a-z0-9_-]{1,64}");
    private static final OutputStream s = new OutputStream() {
        public void write(int i) throws IOException {
        }
    };
    final ThreadPoolExecutor b = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue());
    private b c = null;
    private final File d;
    private final File e;
    private final File f;
    private final File g;
    private final int h;
    private long i;
    private int j;
    private final int k;
    private long l = 0;
    private int m = 0;
    private Writer n;
    private final LinkedHashMap<String, b> o = new LinkedHashMap(0, 0.75f, true);
    private int p;
    private long q = 0;
    private final Callable<Void> r = new Callable<Void>(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public /* synthetic */ Object call() throws Exception {
            return a();
        }

        public Void a() throws Exception {
            synchronized (this.a) {
                if (this.a.n == null) {
                } else {
                    this.a.i();
                    this.a.j();
                    if (this.a.g()) {
                        this.a.f();
                        this.a.p = 0;
                    }
                }
            }
            return null;
        }
    };

    /* compiled from: DiskLruCache */
    public final class a {
        final /* synthetic */ a a;
        private final b b;
        private final boolean[] c;
        private boolean d;
        private boolean e;

        /* compiled from: DiskLruCache */
        private class a extends FilterOutputStream {
            final /* synthetic */ a a;

            private a(a aVar, OutputStream outputStream) {
                this.a = aVar;
                super(outputStream);
            }

            public void write(int i) {
                try {
                    this.out.write(i);
                } catch (IOException e) {
                    this.a.d = true;
                }
            }

            public void write(byte[] bArr, int i, int i2) {
                try {
                    this.out.write(bArr, i, i2);
                } catch (IOException e) {
                    this.a.d = true;
                }
            }

            public void close() {
                try {
                    this.out.close();
                } catch (IOException e) {
                    this.a.d = true;
                }
            }

            public void flush() {
                try {
                    this.out.flush();
                } catch (IOException e) {
                    this.a.d = true;
                }
            }
        }

        private a(a aVar, b bVar) {
            this.a = aVar;
            this.b = bVar;
            this.c = bVar.d ? null : new boolean[aVar.k];
        }

        public OutputStream a(int i) throws IOException {
            OutputStream c;
            synchronized (this.a) {
                File b;
                OutputStream fileOutputStream;
                if (this.b.e != this) {
                    throw new IllegalStateException();
                }
                if (!this.b.d) {
                    this.c[i] = true;
                }
                b = this.b.b(i);
                try {
                    fileOutputStream = new FileOutputStream(b);
                } catch (FileNotFoundException e) {
                    this.a.d.mkdirs();
                    try {
                        fileOutputStream = new FileOutputStream(b);
                    } catch (FileNotFoundException e2) {
                        c = a.s;
                    }
                }
                c = new a(fileOutputStream);
            }
            return c;
        }

        public void a() throws IOException {
            if (this.d) {
                this.a.a(this, false);
                this.a.c(this.b.b);
            } else {
                this.a.a(this, true);
            }
            this.e = true;
        }

        public void b() throws IOException {
            this.a.a(this, false);
        }
    }

    /* compiled from: DiskLruCache */
    private final class b {
        final /* synthetic */ a a;
        private final String b;
        private final long[] c;
        private boolean d;
        private a e;
        private long f;

        private b(a aVar, String str) {
            this.a = aVar;
            this.b = str;
            this.c = new long[aVar.k];
        }

        public String a() throws IOException {
            StringBuilder stringBuilder = new StringBuilder();
            for (long append : this.c) {
                stringBuilder.append(' ').append(append);
            }
            return stringBuilder.toString();
        }

        private void a(String[] strArr) throws IOException {
            if (strArr.length != this.a.k) {
                throw b(strArr);
            }
            int i = 0;
            while (i < strArr.length) {
                try {
                    this.c[i] = Long.parseLong(strArr[i]);
                    i++;
                } catch (NumberFormatException e) {
                    throw b(strArr);
                }
            }
        }

        private IOException b(String[] strArr) throws IOException {
            throw new IOException("unexpected journal line: " + Arrays.toString(strArr));
        }

        public File a(int i) {
            return new File(this.a.d, this.b + "." + i);
        }

        public File b(int i) {
            return new File(this.a.d, this.b + "." + i + f.DOWNLOAD_FILE_TMP);
        }
    }

    /* compiled from: DiskLruCache */
    public final class c implements Closeable {
        final /* synthetic */ a a;
        private final String b;
        private final long c;
        private File[] d;
        private final InputStream[] e;
        private final long[] f;

        private c(a aVar, String str, long j, File[] fileArr, InputStream[] inputStreamArr, long[] jArr) {
            this.a = aVar;
            this.b = str;
            this.c = j;
            this.d = fileArr;
            this.e = inputStreamArr;
            this.f = jArr;
        }

        public File a(int i) {
            return this.d[i];
        }

        public void close() {
            for (Closeable a : this.e) {
                e.a(a);
            }
        }
    }

    private a(File file, int i, int i2, long j, int i3) {
        this.d = file;
        this.h = i;
        this.e = new File(file, "journal.rl");
        this.f = new File(file, "journal.tmp");
        this.g = new File(file, "journal.bkp");
        this.k = i2;
        this.i = j;
        this.j = i3;
    }

    public static a a(File file, int i, int i2, long j, int i3) throws IOException {
        if (j <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        } else if (i3 <= 0) {
            throw new IllegalArgumentException("maxFileCount <= 0");
        } else if (i2 <= 0) {
            throw new IllegalArgumentException("valueCount <= 0");
        } else {
            File file2 = new File(file, "journal.bkp");
            if (file2.exists()) {
                File file3 = new File(file, "journal.rl");
                if (file3.exists()) {
                    file2.delete();
                } else {
                    a(file2, file3, false);
                }
            }
            a aVar = new a(file, i, i2, j, i3);
            if (aVar.e.exists()) {
                try {
                    aVar.d();
                    aVar.e();
                    aVar.n = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(aVar.e, true), e.a));
                    return aVar;
                } catch (IOException e) {
                    System.out.println("DiskLruCache " + file + " is corrupt: " + e.getMessage() + ", removing");
                    aVar.b();
                }
            }
            file.mkdirs();
            aVar = new a(file, i, i2, j, i3);
            aVar.f();
            return aVar;
        }
    }

    private void d() throws IOException {
        Closeable dVar = new d(new FileInputStream(this.e), e.a);
        int i;
        try {
            String a = dVar.a();
            String a2 = dVar.a();
            String a3 = dVar.a();
            String a4 = dVar.a();
            String a5 = dVar.a();
            if ("libcore.io.DiskLruCache".equals(a) && "1".equals(a2) && Integer.toString(this.h).equals(a3) && Integer.toString(this.k).equals(a4) && "".equals(a5)) {
                i = 0;
                while (true) {
                    d(dVar.a());
                    i++;
                }
            } else {
                throw new IOException("unexpected journal header: [" + a + ", " + a2 + ", " + a4 + ", " + a5 + "]");
            }
        } catch (EOFException e) {
            this.p = i - this.o.size();
            e.a(dVar);
        } catch (Throwable th) {
            e.a(dVar);
        }
    }

    private void d(String str) throws IOException {
        int indexOf = str.indexOf(32);
        if (indexOf == -1) {
            throw new IOException("unexpected journal line: " + str);
        }
        String str2;
        int i = indexOf + 1;
        int indexOf2 = str.indexOf(32, i);
        if (indexOf2 == -1) {
            String substring = str.substring(i);
            if (indexOf == "REMOVE".length() && str.startsWith("REMOVE")) {
                this.o.remove(substring);
                return;
            }
            str2 = substring;
        } else {
            str2 = str.substring(i, indexOf2);
        }
        b bVar = (b) this.o.get(str2);
        if (bVar == null) {
            bVar = new b(str2);
            this.o.put(str2, bVar);
        }
        if (indexOf2 != -1 && indexOf == "CLEAN".length() && str.startsWith("CLEAN")) {
            String[] split = str.substring(indexOf2 + 1).split(" ");
            bVar.d = true;
            bVar.e = null;
            bVar.a(split);
        } else if (indexOf2 == -1 && indexOf == "DIRTY".length() && str.startsWith("DIRTY")) {
            bVar.e = new a(bVar);
        } else if (indexOf2 != -1 || indexOf != "READ".length() || !str.startsWith("READ")) {
            throw new IOException("unexpected journal line: " + str);
        }
    }

    private void e() throws IOException {
        a(this.f);
        Iterator it = this.o.values().iterator();
        while (it.hasNext()) {
            b bVar = (b) it.next();
            int i;
            if (bVar.e == null) {
                for (i = 0; i < this.k; i++) {
                    this.l += bVar.c[i];
                    this.m++;
                }
            } else {
                bVar.e = null;
                for (i = 0; i < this.k; i++) {
                    a(bVar.a(i));
                    a(bVar.b(i));
                }
                it.remove();
            }
        }
    }

    private synchronized void f() throws IOException {
        if (this.n != null) {
            this.n.close();
        }
        Writer bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.f), e.a));
        try {
            bufferedWriter.write("libcore.io.DiskLruCache");
            bufferedWriter.write("\n");
            bufferedWriter.write("1");
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.h));
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.k));
            bufferedWriter.write("\n");
            bufferedWriter.write("\n");
            for (b bVar : this.o.values()) {
                if (bVar.e != null) {
                    bufferedWriter.write("DIRTY " + bVar.b + '\n');
                } else {
                    bufferedWriter.write("CLEAN " + bVar.b + bVar.a() + '\n');
                }
            }
            bufferedWriter.close();
            if (this.e.exists()) {
                a(this.e, this.g, true);
            }
            a(this.f, this.e, false);
            this.g.delete();
            this.n = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.e, true), e.a));
        } catch (Throwable th) {
            bufferedWriter.close();
        }
    }

    private static void a(File file) throws IOException {
        if (file.exists() && !file.delete()) {
            throw new IOException();
        }
    }

    private static void a(File file, File file2, boolean z) throws IOException {
        if (z) {
            a(file2);
        }
        if (!file.renameTo(file2)) {
            throw new IOException();
        }
    }

    public synchronized c a(String str) throws IOException {
        int i;
        c cVar = null;
        synchronized (this) {
            h();
            e(str);
            b bVar = (b) this.o.get(str);
            if (bVar != null) {
                if (bVar.d) {
                    File[] fileArr = new File[this.k];
                    InputStream[] inputStreamArr = new InputStream[this.k];
                    int i2 = 0;
                    while (i2 < this.k) {
                        try {
                            File a = bVar.a(i2);
                            fileArr[i2] = a;
                            inputStreamArr[i2] = new FileInputStream(a);
                            i2++;
                        } catch (FileNotFoundException e) {
                            i = 0;
                            while (i < this.k && inputStreamArr[i] != null) {
                                e.a(inputStreamArr[i]);
                                i++;
                            }
                        }
                    }
                    this.p++;
                    this.n.append("READ " + str + '\n');
                    if (g()) {
                        this.b.submit(this.r);
                    }
                    cVar = new c(str, bVar.f, fileArr, inputStreamArr, bVar.c);
                }
            }
        }
        return cVar;
    }

    public a b(String str) throws IOException {
        return a(str, -1);
    }

    private synchronized a a(String str, long j) throws IOException {
        a aVar;
        h();
        e(str);
        b bVar = (b) this.o.get(str);
        if (j == -1 || (bVar != null && bVar.f == j)) {
            b bVar2;
            if (bVar == null) {
                bVar = new b(str);
                this.o.put(str, bVar);
                bVar2 = bVar;
            } else if (bVar.e != null) {
                aVar = null;
            } else {
                bVar2 = bVar;
            }
            aVar = new a(bVar2);
            bVar2.e = aVar;
            this.n.write("DIRTY " + str + '\n');
            this.n.flush();
        } else {
            aVar = null;
        }
        return aVar;
    }

    public File a() {
        return this.d;
    }

    private synchronized void a(a aVar, boolean z) throws IOException {
        int i = 0;
        synchronized (this) {
            b a = aVar.b;
            if (a.e != aVar) {
                throw new IllegalStateException();
            }
            if (z) {
                if (!a.d) {
                    int i2 = 0;
                    while (i2 < this.k) {
                        if (!aVar.c[i2]) {
                            aVar.b();
                            throw new IllegalStateException("Newly created entry didn't create value for index " + i2);
                        } else if (!a.b(i2).exists()) {
                            aVar.b();
                            break;
                        } else {
                            i2++;
                        }
                    }
                }
            }
            while (i < this.k) {
                File b = a.b(i);
                if (!z) {
                    a(b);
                } else if (b.exists()) {
                    File a2 = a.a(i);
                    b.renameTo(a2);
                    long j = a.c[i];
                    long length = a2.length();
                    a.c[i] = length;
                    this.l = (this.l - j) + length;
                    this.m++;
                }
                i++;
            }
            this.p++;
            a.e = null;
            if ((a.d | z) != 0) {
                a.d = true;
                this.n.write("CLEAN " + a.b + a.a() + '\n');
                if (z) {
                    long j2 = this.q;
                    this.q = 1 + j2;
                    a.f = j2;
                }
            } else {
                this.o.remove(a.b);
                this.n.write("REMOVE " + a.b + '\n');
            }
            this.n.flush();
            if (this.l > this.i || this.m > this.j || g()) {
                this.b.submit(this.r);
            }
        }
    }

    private boolean g() {
        return this.p >= APPluginErrorCode.ERROR_APP_SYSTEM && this.p >= this.o.size();
    }

    public synchronized boolean c(String str) throws IOException {
        boolean z;
        int i = 0;
        synchronized (this) {
            h();
            e(str);
            b bVar = (b) this.o.get(str);
            if (bVar == null || bVar.e != null) {
                z = false;
            } else {
                while (i < this.k) {
                    File a = bVar.a(i);
                    if (!a.exists() || a.delete()) {
                        this.l -= bVar.c[i];
                        this.m--;
                        bVar.c[i] = 0;
                        i++;
                    } else {
                        throw new IOException("failed to delete " + a);
                    }
                }
                this.p++;
                this.n.append("REMOVE " + str + '\n');
                this.o.remove(str);
                if (g()) {
                    this.b.submit(this.r);
                }
                if (this.c != null) {
                    this.c.a(str);
                }
                z = true;
            }
        }
        return z;
    }

    private void h() {
        if (this.n == null) {
            throw new IllegalStateException("cache is closed");
        }
    }

    public synchronized void close() throws IOException {
        if (this.n != null) {
            Iterator it = new ArrayList(this.o.values()).iterator();
            while (it.hasNext()) {
                b bVar = (b) it.next();
                if (bVar.e != null) {
                    bVar.e.b();
                }
            }
            i();
            j();
            this.n.close();
            this.n = null;
        }
    }

    private void i() throws IOException {
        while (this.l > this.i) {
            c((String) ((Entry) this.o.entrySet().iterator().next()).getKey());
        }
    }

    private void j() throws IOException {
        while (this.m > this.j) {
            c((String) ((Entry) this.o.entrySet().iterator().next()).getKey());
        }
    }

    public void b() throws IOException {
        close();
        e.a(this.d);
    }

    private void e(String str) {
        if (!a.matcher(str).matches()) {
            throw new IllegalArgumentException("keys must match regex [a-z0-9_-]{1,64}: \"" + str + "\"");
        }
    }
}
