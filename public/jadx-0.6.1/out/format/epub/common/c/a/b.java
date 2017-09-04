package format.epub.common.c.a;

import com.dynamicload.Lib.DLConstants;
import com.iflytek.cloud.SpeechError;
import com.tencent.tinker.android.dx.instruction.Opcodes;

/* compiled from: CSSSelector */
public class b implements Comparable<b> {
    String a;
    String b;
    a c;

    /* compiled from: CSSSelector */
    public static class a {
        byte a;
        b b;

        public a(byte b, b bVar) {
            this.a = b;
            this.b = bVar;
        }

        public b a() {
            return this.b;
        }

        public byte b() {
            return this.a;
        }
    }

    public /* synthetic */ int compareTo(Object obj) {
        return a((b) obj);
    }

    public b(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    public b(String str) {
        int indexOf = str.indexOf(46);
        if (indexOf == -1) {
            this.a = str;
            return;
        }
        this.a = str.substring(0, indexOf);
        this.b = str.substring(indexOf + 1);
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    private static b a(b bVar, char[] cArr, int i, int i2, char c) {
        byte b = (byte) 0;
        char[] cArr2 = new char[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            cArr2[i3] = cArr[i3 + i];
        }
        b bVar2 = new b(new String(cArr2));
        if (bVar != null) {
            switch (c) {
                case '+':
                    b = (byte) 2;
                    break;
                case SpeechError.TIP_ERROR_IVP_TEXT_NOT_MATCH /*62*/:
                    b = (byte) 1;
                    break;
                case Opcodes.NOT_LONG /*126*/:
                    b = (byte) 3;
                    break;
            }
            bVar2.c = new a(b, bVar);
        }
        return bVar2;
    }

    public static b a(String str) {
        b bVar = null;
        char[] toCharArray = str.toCharArray();
        int length = str.length();
        char c = '?';
        int i = 0;
        int i2 = -1;
        while (i < length) {
            b bVar2;
            int i3;
            char c2;
            char c3;
            if (toCharArray[i] == '+' || toCharArray[i] == '>' || toCharArray[i] == '~') {
                if (i2 != -1) {
                    bVar = a(bVar, toCharArray, i2, i - i2, c);
                    i2 = -1;
                }
                bVar2 = bVar;
                i3 = i2;
                c2 = toCharArray[i];
            } else {
                if (Character.isWhitespace(toCharArray[i])) {
                    if (i2 != -1) {
                        bVar = a(bVar, toCharArray, i2, i - i2, c);
                        c2 = ' ';
                        bVar2 = bVar;
                        i3 = -1;
                    }
                } else if (i2 == -1) {
                    c2 = c;
                    bVar2 = bVar;
                    i3 = i;
                }
                c3 = c;
                bVar2 = bVar;
                i3 = i2;
                c2 = c3;
            }
            i++;
            c3 = c2;
            i2 = i3;
            bVar = bVar2;
            c = c3;
        }
        if (i2 != -1) {
            return a(bVar, toCharArray, i2, length - i2, c);
        }
        return bVar;
    }

    public a c() {
        return this.c;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        if (this.a.equals(bVar.a) && this.b.equals(bVar.b)) {
            return true;
        }
        return false;
    }

    public String toString() {
        return this.a + DLConstants.DEPENDENCY_PACKAGE_DIV + this.b;
    }

    public int hashCode() {
        if (this.a == null) {
            this.a = "";
        }
        if (this.b == null) {
            this.b = "";
        }
        int hashCode = this.a.hashCode();
        int hashCode2 = this.b.hashCode();
        return (((hashCode ^ (hashCode >>> 32)) + 31) * 31) + (hashCode2 ^ (hashCode2 >>> 32));
    }

    public int a(b bVar) {
        int compareTo = this.a.compareTo(bVar.a);
        if (compareTo != 0) {
            return compareTo;
        }
        compareTo = this.b.compareTo(bVar.b);
        if (compareTo != 0) {
            return compareTo;
        }
        if (bVar.c == null) {
            return -1;
        }
        if (this.c == null) {
            return 1;
        }
        compareTo = this.c.a - bVar.c.a;
        return compareTo == 0 ? this.c.b.a(bVar.c.b) : compareTo;
    }
}
