package org.apache.commons.compress.archivers.dump;

import com.tencent.tinker.android.dx.instruction.Opcodes;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.compress.archivers.dump.DumpArchiveConstants.SEGMENT_TYPE;

public class DumpArchiveEntry implements org.apache.commons.compress.archivers.a {
    private String a;
    private TYPE b = TYPE.UNKNOWN;
    private int c;
    private Set<PERMISSION> d = Collections.emptySet();
    private long e;
    private long f;
    private long g;
    private int h;
    private int i;
    private final c j = null;
    private final a k = new a();
    private String l;
    private int m;
    private int n;
    private int o;
    private long p;
    private int q;

    public enum PERMISSION {
        SETUID(2048),
        SETGUI(1024),
        STICKY(512),
        USER_READ(256),
        USER_WRITE(128),
        USER_EXEC(64),
        GROUP_READ(32),
        GROUP_WRITE(16),
        GROUP_EXEC(8),
        WORLD_READ(4),
        WORLD_WRITE(2),
        WORLD_EXEC(1);
        
        private int code;

        private PERMISSION(int i) {
            this.code = i;
        }

        public static Set<PERMISSION> find(int i) {
            Collection hashSet = new HashSet();
            for (PERMISSION permission : values()) {
                if ((permission.code & i) == permission.code) {
                    hashSet.add(permission);
                }
            }
            if (hashSet.isEmpty()) {
                return Collections.emptySet();
            }
            return EnumSet.copyOf(hashSet);
        }
    }

    public enum TYPE {
        WHITEOUT(14),
        SOCKET(12),
        LINK(10),
        FILE(8),
        BLKDEV(6),
        DIRECTORY(4),
        CHRDEV(2),
        FIFO(1),
        UNKNOWN(15);
        
        private int code;

        private TYPE(int i) {
            this.code = i;
        }

        public static TYPE find(int i) {
            TYPE type = UNKNOWN;
            TYPE[] values = values();
            int length = values.length;
            int i2 = 0;
            while (i2 < length) {
                TYPE type2 = values[i2];
                if (i != type2.code) {
                    type2 = type;
                }
                i2++;
                type = type2;
            }
            return type;
        }
    }

    static class a {
        private SEGMENT_TYPE a;
        private int b;
        private int c;
        private int d;
        private int e;
        private final byte[] f = new byte[512];

        a() {
        }

        public SEGMENT_TYPE a() {
            return this.a;
        }

        public int b() {
            return this.b;
        }

        public int c() {
            return this.d;
        }

        public int a(int i) {
            return this.f[i];
        }
    }

    public SEGMENT_TYPE a() {
        return this.k.a();
    }

    public int b() {
        return this.k.c();
    }

    public boolean a(int i) {
        return (this.k.a(i) & 1) == 0;
    }

    public int hashCode() {
        return this.n;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !obj.getClass().equals(getClass())) {
            return false;
        }
        DumpArchiveEntry dumpArchiveEntry = (DumpArchiveEntry) obj;
        if (this.k == null || dumpArchiveEntry.k == null) {
            return false;
        }
        if (this.n != dumpArchiveEntry.n) {
            return false;
        }
        if ((this.j != null || dumpArchiveEntry.j == null) && (this.j == null || this.j.equals(dumpArchiveEntry.j))) {
            return true;
        }
        return false;
    }

    public String toString() {
        return c();
    }

    static DumpArchiveEntry a(byte[] bArr) {
        DumpArchiveEntry dumpArchiveEntry = new DumpArchiveEntry();
        a aVar = dumpArchiveEntry.k;
        aVar.a = SEGMENT_TYPE.find(d.b(bArr, 0));
        aVar.b = d.b(bArr, 12);
        dumpArchiveEntry.n = aVar.c = d.b(bArr, 20);
        int c = d.c(bArr, 32);
        dumpArchiveEntry.a(TYPE.find((c >> 12) & 15));
        dumpArchiveEntry.b(c);
        dumpArchiveEntry.o = d.c(bArr, 34);
        dumpArchiveEntry.a(d.a(bArr, 40));
        dumpArchiveEntry.b(new Date((((long) d.b(bArr, 48)) * 1000) + ((long) (d.b(bArr, 52) / 1000))));
        dumpArchiveEntry.a(new Date((((long) d.b(bArr, 56)) * 1000) + ((long) (d.b(bArr, 60) / 1000))));
        dumpArchiveEntry.p = (((long) d.b(bArr, 64)) * 1000) + ((long) (d.b(bArr, 68) / 1000));
        dumpArchiveEntry.q = d.b(bArr, Opcodes.DOUBLE_TO_FLOAT);
        dumpArchiveEntry.c(d.b(bArr, Opcodes.ADD_INT));
        dumpArchiveEntry.d(d.b(bArr, Opcodes.REM_INT));
        aVar.d = d.b(bArr, 160);
        aVar.e = 0;
        c = 0;
        while (c < 512 && c < aVar.d) {
            if (bArr[c + Opcodes.SHR_LONG] == (byte) 0) {
                aVar.e = aVar.e + 1;
            }
            c++;
        }
        System.arraycopy(bArr, Opcodes.SHR_LONG, aVar.f, 0, 512);
        dumpArchiveEntry.m = aVar.b();
        return dumpArchiveEntry;
    }

    public String c() {
        return this.a;
    }

    String d() {
        return this.l;
    }

    public void a(TYPE type) {
        this.b = type;
    }

    public void b(int i) {
        this.c = i & 4095;
        this.d = PERMISSION.find(i);
    }

    public void a(long j) {
        this.e = j;
    }

    public void a(Date date) {
        this.g = date.getTime();
    }

    public void b(Date date) {
        this.f = date.getTime();
    }

    public void c(int i) {
        this.h = i;
    }

    public void d(int i) {
        this.i = i;
    }
}
