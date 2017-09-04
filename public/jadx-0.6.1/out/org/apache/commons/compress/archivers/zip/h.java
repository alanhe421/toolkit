package org.apache.commons.compress.archivers.zip;

import com.tencent.tinker.android.dx.instruction.Opcodes;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: Simple8BitZipEncoding */
class h implements p {
    private final char[] a;
    private final List<a> b;

    /* compiled from: Simple8BitZipEncoding */
    private static final class a implements Comparable<a> {
        public final char a;
        public final byte b;

        public /* synthetic */ int compareTo(Object obj) {
            return a((a) obj);
        }

        a(byte b, char c) {
            this.b = b;
            this.a = c;
        }

        public int a(a aVar) {
            return this.a - aVar.a;
        }

        public String toString() {
            return "0x" + Integer.toHexString(65535 & this.a) + "->0x" + Integer.toHexString(this.b & 255);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            if (this.a == aVar.a && this.b == aVar.b) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return this.a;
        }
    }

    public h(char[] cArr) {
        this.a = (char[]) cArr.clone();
        List arrayList = new ArrayList(this.a.length);
        int i = Opcodes.NEG_FLOAT;
        for (char aVar : this.a) {
            i = (byte) (i + 1);
            arrayList.add(new a(i, aVar));
        }
        Collections.sort(arrayList);
        this.b = Collections.unmodifiableList(arrayList);
    }

    public char a(byte b) {
        if (b >= (byte) 0) {
            return (char) b;
        }
        return this.a[b + 128];
    }

    public String a(byte[] bArr) throws IOException {
        char[] cArr = new char[bArr.length];
        for (int i = 0; i < bArr.length; i++) {
            cArr[i] = a(bArr[i]);
        }
        return new String(cArr);
    }
}
