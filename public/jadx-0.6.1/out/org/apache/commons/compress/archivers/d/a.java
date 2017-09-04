package org.apache.commons.compress.archivers.d;

import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import org.apache.commons.compress.archivers.zip.p;

/* compiled from: TarArchiveEntry */
public class a implements org.apache.commons.compress.archivers.a {
    private String a;
    private int b;
    private int c;
    private int d;
    private long e;
    private long f;
    private boolean g;
    private byte h;
    private String i;
    private String j;
    private String k;
    private String l;
    private String m;
    private int n;
    private int o;
    private boolean p;
    private long q;
    private final File r;

    private a() {
        this.a = "";
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.i = "";
        this.j = "ustar\u0000";
        this.k = "00";
        this.m = "";
        this.n = 0;
        this.o = 0;
        String property = System.getProperty("user.name", "");
        if (property.length() > 31) {
            property = property.substring(0, 31);
        }
        this.l = property;
        this.r = null;
    }

    public a(byte[] bArr, p pVar) throws IOException {
        this();
        a(bArr, pVar);
    }

    public boolean a(a aVar) {
        return a().equals(aVar.a());
    }

    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return a((a) obj);
    }

    public int hashCode() {
        return a().hashCode();
    }

    public String a() {
        return this.a.toString();
    }

    public void a(String str) {
        this.a = a(str, false);
    }

    public void b(String str) {
        this.i = str;
    }

    public void a(int i) {
        this.c = i;
    }

    public void b(int i) {
        this.d = i;
    }

    public void c(String str) {
        this.l = str;
    }

    public void d(String str) {
        this.m = str;
    }

    public void a(long j) {
        this.f = j / 1000;
    }

    public long b() {
        return this.e;
    }

    public void b(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("Size is out of range: " + j);
        }
        this.e = j;
    }

    public void c(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Major device number is out of range: " + i);
        }
        this.n = i;
    }

    public void d(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Minor device number is out of range: " + i);
        }
        this.o = i;
    }

    public boolean c() {
        return this.p;
    }

    public boolean d() {
        return this.h == (byte) 83;
    }

    public boolean e() {
        return this.h == (byte) 76 && this.a.equals("././@LongLink");
    }

    public boolean f() {
        return this.h == (byte) 120 || this.h == (byte) 88;
    }

    public boolean g() {
        if (this.r != null) {
            return this.r.isDirectory();
        }
        if (this.h == (byte) 53 || a().endsWith("/")) {
            return true;
        }
        return false;
    }

    public void a(byte[] bArr, p pVar) throws IOException {
        a(bArr, pVar, false);
    }

    private void a(byte[] bArr, p pVar, boolean z) throws IOException {
        this.a = z ? e.c(bArr, 0, 100) : e.a(bArr, 0, 100, pVar);
        this.b = (int) e.b(bArr, 100, 8);
        this.c = (int) e.b(bArr, 108, 8);
        this.d = (int) e.b(bArr, 116, 8);
        this.e = e.b(bArr, Opcodes.NOT_INT, 12);
        this.f = e.b(bArr, Opcodes.FLOAT_TO_LONG, 12);
        this.g = e.a(bArr);
        this.h = bArr[Opcodes.SUB_LONG];
        this.i = z ? e.c(bArr, Opcodes.MUL_LONG, 100) : e.a(bArr, Opcodes.MUL_LONG, 100, pVar);
        this.j = e.c(bArr, 257, 6);
        this.k = e.c(bArr, 263, 2);
        this.l = z ? e.c(bArr, 265, 32) : e.a(bArr, 265, 32, pVar);
        this.m = z ? e.c(bArr, 297, 32) : e.a(bArr, 297, 32, pVar);
        this.n = (int) e.b(bArr, ErrorCode.ERROR_GETSTRINGARRAY_JARFILE, 8);
        this.o = (int) e.b(bArr, 337, 8);
        switch (a(bArr)) {
            case 2:
                this.p = e.a(bArr, 482);
                this.q = e.a(bArr, 483, 12);
                return;
            default:
                String c = z ? e.c(bArr, 345, Opcodes.ADD_LONG) : e.a(bArr, 345, Opcodes.ADD_LONG, pVar);
                if (g() && !this.a.endsWith("/")) {
                    this.a += "/";
                }
                if (c.length() > 0) {
                    this.a = c + "/" + this.a;
                    return;
                }
                return;
        }
    }

    private static String a(String str, boolean z) {
        String toLowerCase = System.getProperty("os.name").toLowerCase(Locale.ENGLISH);
        if (toLowerCase != null) {
            if (toLowerCase.startsWith("windows")) {
                if (str.length() > 2) {
                    char charAt = str.charAt(0);
                    if (str.charAt(1) == ':' && ((charAt >= 'a' && charAt <= 'z') || (charAt >= 'A' && charAt <= 'Z'))) {
                        str = str.substring(2);
                    }
                }
            } else if (toLowerCase.indexOf("netware") > -1) {
                int indexOf = str.indexOf(58);
                if (indexOf != -1) {
                    str = str.substring(indexOf + 1);
                }
            }
        }
        toLowerCase = str.replace(File.separatorChar, '/');
        while (!z && toLowerCase.startsWith("/")) {
            toLowerCase = toLowerCase.substring(1);
        }
        return toLowerCase;
    }

    private int a(byte[] bArr) {
        if (org.apache.commons.compress.a.a.a("ustar ", bArr, 257, 6)) {
            return 2;
        }
        if (org.apache.commons.compress.a.a.a("ustar\u0000", bArr, 257, 6)) {
            return 3;
        }
        return 0;
    }
}
