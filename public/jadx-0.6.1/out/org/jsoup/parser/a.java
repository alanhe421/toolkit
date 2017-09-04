package org.jsoup.parser;

import java.util.Arrays;
import java.util.Locale;
import org.jsoup.helper.c;

/* compiled from: CharacterReader */
public final class a {
    private final char[] a;
    private final int b;
    private int c = 0;
    private int d = 0;
    private final String[] e = new String[512];

    public a(String str) {
        c.a((Object) str);
        this.a = str.toCharArray();
        this.b = this.a.length;
    }

    public int a() {
        return this.c;
    }

    public boolean b() {
        return this.c >= this.b;
    }

    public char c() {
        return this.c >= this.b ? '￿' : this.a[this.c];
    }

    char d() {
        char c = this.c >= this.b ? '￿' : this.a[this.c];
        this.c++;
        return c;
    }

    void e() {
        this.c--;
    }

    public void f() {
        this.c++;
    }

    void g() {
        this.d = this.c;
    }

    void h() {
        this.c = this.d;
    }

    int a(char c) {
        for (int i = this.c; i < this.b; i++) {
            if (c == this.a[i]) {
                return i - this.c;
            }
        }
        return -1;
    }

    int a(CharSequence charSequence) {
        char charAt = charSequence.charAt(0);
        int i = this.c;
        while (i < this.b) {
            if (charAt != this.a[i]) {
                do {
                    i++;
                    if (i >= this.b) {
                        break;
                    }
                } while (charAt != this.a[i]);
            }
            int i2 = i + 1;
            int length = (charSequence.length() + i2) - 1;
            if (i < this.b && length <= this.b) {
                int i3 = 1;
                while (i2 < length && charSequence.charAt(i3) == this.a[i2]) {
                    i2++;
                    i3++;
                }
                if (i2 == length) {
                    return i - this.c;
                }
            }
            i++;
        }
        return -1;
    }

    public String b(char c) {
        int a = a(c);
        if (a == -1) {
            return k();
        }
        String a2 = a(this.c, a);
        this.c = a + this.c;
        return a2;
    }

    String a(String str) {
        int a = a((CharSequence) str);
        if (a == -1) {
            return k();
        }
        String a2 = a(this.c, a);
        this.c = a + this.c;
        return a2;
    }

    public java.lang.String a(char... r8) {
        /* JADX: method processing error */
/*
Error: java.lang.IndexOutOfBoundsException: bitIndex < 0: -1
	at java.util.BitSet.get(BitSet.java:623)
	at jadx.core.dex.visitors.CodeShrinker$ArgsInfo.usedArgAssign(CodeShrinker.java:138)
	at jadx.core.dex.visitors.CodeShrinker$ArgsInfo.access$300(CodeShrinker.java:43)
	at jadx.core.dex.visitors.CodeShrinker.canMoveBetweenBlocks(CodeShrinker.java:282)
	at jadx.core.dex.visitors.CodeShrinker.shrinkBlock(CodeShrinker.java:230)
	at jadx.core.dex.visitors.CodeShrinker.shrinkMethod(CodeShrinker.java:38)
	at jadx.core.dex.visitors.regions.LoopRegionVisitor.checkArrayForEach(LoopRegionVisitor.java:196)
	at jadx.core.dex.visitors.regions.LoopRegionVisitor.checkForIndexedLoop(LoopRegionVisitor.java:119)
	at jadx.core.dex.visitors.regions.LoopRegionVisitor.processLoopRegion(LoopRegionVisitor.java:65)
	at jadx.core.dex.visitors.regions.LoopRegionVisitor.enterRegion(LoopRegionVisitor.java:52)
	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:56)
	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:58)
	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:58)
	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:58)
	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:18)
	at jadx.core.dex.visitors.regions.LoopRegionVisitor.visit(LoopRegionVisitor.java:46)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
        /*
        r7 = this;
        r1 = r7.c;
        r2 = r7.b;
        r3 = r7.a;
    L_0x0006:
        r0 = r7.c;
        if (r0 >= r2) goto L_0x0016;
    L_0x000a:
        r4 = r8.length;
        r0 = 0;
    L_0x000c:
        if (r0 >= r4) goto L_0x0025;
    L_0x000e:
        r5 = r8[r0];
        r6 = r7.c;
        r6 = r3[r6];
        if (r6 != r5) goto L_0x0022;
    L_0x0016:
        r0 = r7.c;
        if (r0 <= r1) goto L_0x002c;
    L_0x001a:
        r0 = r7.c;
        r0 = r0 - r1;
        r0 = r7.a(r1, r0);
    L_0x0021:
        return r0;
    L_0x0022:
        r0 = r0 + 1;
        goto L_0x000c;
    L_0x0025:
        r0 = r7.c;
        r0 = r0 + 1;
        r7.c = r0;
        goto L_0x0006;
    L_0x002c:
        r0 = "";
        goto L_0x0021;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jsoup.parser.a.a(char[]):java.lang.String");
    }

    String b(char... cArr) {
        int i = this.c;
        int i2 = this.b;
        char[] cArr2 = this.a;
        while (this.c < i2 && Arrays.binarySearch(cArr, cArr2[this.c]) < 0) {
            this.c++;
        }
        return this.c > i ? a(i, this.c - i) : "";
    }

    String i() {
        int i = this.c;
        int i2 = this.b;
        char[] cArr = this.a;
        while (this.c < i2) {
            char c = cArr[this.c];
            if (c == '&' || c == '<' || c == '\u0000') {
                break;
            }
            this.c++;
        }
        return this.c > i ? a(i, this.c - i) : "";
    }

    String j() {
        int i = this.c;
        int i2 = this.b;
        char[] cArr = this.a;
        while (this.c < i2) {
            char c = cArr[this.c];
            if (c == '\t' || c == '\n' || c == '\r' || c == '\f' || c == ' ' || c == '/' || c == '>' || c == '\u0000') {
                break;
            }
            this.c++;
        }
        return this.c > i ? a(i, this.c - i) : "";
    }

    String k() {
        String a = a(this.c, this.b - this.c);
        this.c = this.b;
        return a;
    }

    String l() {
        int i = this.c;
        while (this.c < this.b) {
            char c = this.a[this.c];
            if ((c < 'A' || c > 'Z') && ((c < 'a' || c > 'z') && !Character.isLetter(c))) {
                break;
            }
            this.c++;
        }
        return a(i, this.c - i);
    }

    String m() {
        int i = this.c;
        while (this.c < this.b) {
            char c = this.a[this.c];
            if ((c < 'A' || c > 'Z') && ((c < 'a' || c > 'z') && !Character.isLetter(c))) {
                break;
            }
            this.c++;
        }
        while (!b()) {
            c = this.a[this.c];
            if (c < '0' || c > '9') {
                break;
            }
            this.c++;
        }
        return a(i, this.c - i);
    }

    String n() {
        int i = this.c;
        while (this.c < this.b) {
            char c = this.a[this.c];
            if ((c < '0' || c > '9') && ((c < 'A' || c > 'F') && (c < 'a' || c > 'f'))) {
                break;
            }
            this.c++;
        }
        return a(i, this.c - i);
    }

    String o() {
        int i = this.c;
        while (this.c < this.b) {
            char c = this.a[this.c];
            if (c < '0' || c > '9') {
                break;
            }
            this.c++;
        }
        return a(i, this.c - i);
    }

    boolean c(char c) {
        return !b() && this.a[this.c] == c;
    }

    boolean b(String str) {
        int length = str.length();
        if (length > this.b - this.c) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (str.charAt(i) != this.a[this.c + i]) {
                return false;
            }
        }
        return true;
    }

    boolean c(String str) {
        int length = str.length();
        if (length > this.b - this.c) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (Character.toUpperCase(str.charAt(i)) != Character.toUpperCase(this.a[this.c + i])) {
                return false;
            }
        }
        return true;
    }

    boolean c(char... cArr) {
        if (b()) {
            return false;
        }
        char c = this.a[this.c];
        for (char c2 : cArr) {
            if (c2 == c) {
                return true;
            }
        }
        return false;
    }

    boolean d(char[] cArr) {
        return !b() && Arrays.binarySearch(cArr, this.a[this.c]) >= 0;
    }

    boolean p() {
        if (b()) {
            return false;
        }
        char c = this.a[this.c];
        if ((c < 'A' || c > 'Z') && ((c < 'a' || c > 'z') && !Character.isLetter(c))) {
            return false;
        }
        return true;
    }

    boolean q() {
        if (b()) {
            return false;
        }
        char c = this.a[this.c];
        if (c < '0' || c > '9') {
            return false;
        }
        return true;
    }

    boolean d(String str) {
        if (!b(str)) {
            return false;
        }
        this.c += str.length();
        return true;
    }

    boolean e(String str) {
        if (!c(str)) {
            return false;
        }
        this.c += str.length();
        return true;
    }

    boolean f(String str) {
        return a(str.toLowerCase(Locale.ENGLISH)) > -1 || a(str.toUpperCase(Locale.ENGLISH)) > -1;
    }

    public String toString() {
        return new String(this.a, this.c, this.b - this.c);
    }

    private String a(int i, int i2) {
        int i3 = 0;
        char[] cArr = this.a;
        String[] strArr = this.e;
        if (i2 > 12) {
            return new String(cArr, i, i2);
        }
        int i4 = i;
        int i5 = 0;
        while (i3 < i2) {
            int i6 = i5 * 31;
            i3++;
            int i7 = i4 + 1;
            i5 = cArr[i4] + i6;
            i4 = i7;
        }
        i4 = i5 & (strArr.length - 1);
        String str = strArr[i4];
        if (str == null) {
            str = new String(cArr, i, i2);
            strArr[i4] = str;
            return str;
        } else if (a(i, i2, str)) {
            return str;
        } else {
            str = new String(cArr, i, i2);
            strArr[i4] = str;
            return str;
        }
    }

    boolean a(int i, int i2, String str) {
        if (i2 != str.length()) {
            return false;
        }
        char[] cArr = this.a;
        int i3 = 0;
        while (true) {
            int i4 = i2 - 1;
            if (i2 == 0) {
                return true;
            }
            int i5 = i + 1;
            int i6 = i3 + 1;
            if (cArr[i] != str.charAt(i3)) {
                return false;
            }
            i3 = i6;
            i = i5;
            i2 = i4;
        }
    }
}
