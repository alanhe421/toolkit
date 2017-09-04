package de.innosystec.unrar.rarfile;

import java.util.Arrays;

/* compiled from: NewSubHeaderType */
public class l {
    public static final l a = new l(new byte[]{(byte) 67, (byte) 77, (byte) 84});
    public static final l b = new l(new byte[]{(byte) 65, (byte) 67, (byte) 76});
    public static final l c = new l(new byte[]{(byte) 83, (byte) 84, (byte) 77});
    public static final l d = new l(new byte[]{(byte) 85, (byte) 79, (byte) 87});
    public static final l e = new l(new byte[]{(byte) 65, (byte) 86});
    public static final l f = new l(new byte[]{(byte) 82, (byte) 82});
    public static final l g = new l(new byte[]{(byte) 69, (byte) 65, (byte) 50});
    public static final l h = new l(new byte[]{(byte) 69, (byte) 65, (byte) 66, (byte) 69});
    private byte[] i;

    private l(byte[] bArr) {
        this.i = bArr;
    }

    public boolean a(byte[] bArr) {
        return Arrays.equals(this.i, bArr);
    }

    public String toString() {
        return new String(this.i);
    }
}
