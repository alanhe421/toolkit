package org.apache.commons.compress.archivers.dump;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.dump.DumpArchiveConstants.SEGMENT_TYPE;

/* compiled from: DumpArchiveInputStream */
public class b extends org.apache.commons.compress.archivers.b {
    protected e a;
    private c b;
    private DumpArchiveEntry c;
    private boolean d;
    private boolean e;
    private long f;
    private long g;
    private int h;
    private final byte[] i = new byte[1024];
    private byte[] j;
    private int k;
    private final Map<Integer, a> l = new HashMap();
    private final Map<Integer, DumpArchiveEntry> m = new HashMap();
    private Queue<DumpArchiveEntry> n;

    public b(InputStream inputStream) throws ArchiveException {
        this.a = new e(inputStream);
        this.e = false;
        try {
            byte[] a = this.a.a();
            if (d.b(a)) {
                this.b = new c(a);
                this.a.a(this.b.c(), this.b.d());
                this.j = new byte[4096];
                b();
                c();
                this.l.put(Integer.valueOf(2), new a(2, 2, 4, "."));
                this.n = new PriorityQueue(10, new Comparator<DumpArchiveEntry>(this) {
                    final /* synthetic */ b a;

                    {
                        this.a = r1;
                    }

                    public /* synthetic */ int compare(Object obj, Object obj2) {
                        return a((DumpArchiveEntry) obj, (DumpArchiveEntry) obj2);
                    }

                    public int a(DumpArchiveEntry dumpArchiveEntry, DumpArchiveEntry dumpArchiveEntry2) {
                        if (dumpArchiveEntry.d() == null || dumpArchiveEntry2.d() == null) {
                            return Integer.MAX_VALUE;
                        }
                        return dumpArchiveEntry.d().compareTo(dumpArchiveEntry2.d());
                    }
                });
                return;
            }
            throw new UnrecognizedFormatException();
        } catch (Exception e) {
            throw new ArchiveException(e.getMessage(), e);
        }
    }

    public long a() {
        return this.a.b();
    }

    private void b() throws IOException {
        byte[] a = this.a.a();
        if (d.b(a)) {
            this.c = DumpArchiveEntry.a(a);
            if (SEGMENT_TYPE.CLRI != this.c.a()) {
                throw new InvalidFormatException();
            } else if (this.a.skip((long) (this.c.b() * 1024)) == -1) {
                throw new EOFException();
            } else {
                this.h = this.c.b();
                return;
            }
        }
        throw new InvalidFormatException();
    }

    private void c() throws IOException {
        byte[] a = this.a.a();
        if (d.b(a)) {
            this.c = DumpArchiveEntry.a(a);
            if (SEGMENT_TYPE.BITS != this.c.a()) {
                throw new InvalidFormatException();
            } else if (this.a.skip((long) (this.c.b() * 1024)) == -1) {
                throw new EOFException();
            } else {
                this.h = this.c.b();
                return;
            }
        }
        throw new InvalidFormatException();
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (this.e || this.d || this.g >= this.f) {
            return -1;
        }
        int i3;
        int i4;
        int i5;
        if (((long) i2) + this.g > this.f) {
            i3 = (int) (this.f - this.g);
            i4 = 0;
            i5 = i;
        } else {
            i4 = 0;
            i3 = i2;
            i5 = i;
        }
        while (i3 > 0) {
            int length = i3 > this.i.length - this.k ? this.i.length - this.k : i3;
            if (this.k + length <= this.i.length) {
                System.arraycopy(this.i, this.k, bArr, i5, length);
                i4 += length;
                this.k += length;
                i3 -= length;
                length += i5;
            } else {
                length = i5;
            }
            if (i3 > 0) {
                if (this.h >= 512) {
                    byte[] a = this.a.a();
                    if (d.b(a)) {
                        this.c = DumpArchiveEntry.a(a);
                        this.h = 0;
                    } else {
                        throw new InvalidFormatException();
                    }
                }
                DumpArchiveEntry dumpArchiveEntry = this.c;
                int i6 = this.h;
                this.h = i6 + 1;
                if (dumpArchiveEntry.a(i6)) {
                    Arrays.fill(this.i, (byte) 0);
                } else if (this.a.read(this.i, 0, this.i.length) != this.i.length) {
                    throw new EOFException();
                }
                this.k = 0;
            }
            i5 = length;
        }
        this.g += (long) i4;
        return i4;
    }

    public void close() throws IOException {
        if (!this.d) {
            this.d = true;
            this.a.close();
        }
    }
}
