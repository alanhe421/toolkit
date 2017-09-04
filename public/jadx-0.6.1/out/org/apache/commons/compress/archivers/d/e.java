package org.apache.commons.compress.archivers.d;

import com.tencent.tinker.android.dex.DexFormat;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import java.io.IOException;
import java.math.BigInteger;
import org.apache.commons.compress.archivers.zip.p;
import org.apache.commons.compress.archivers.zip.q;

/* compiled from: TarUtils */
public class e {
    static final p a = q.a(null);
    static final p b = new p() {
        public String a(byte[] bArr) {
            StringBuffer stringBuffer = new StringBuffer(r1);
            for (byte b : bArr) {
                if (b == (byte) 0) {
                    break;
                }
                stringBuffer.append((char) (b & 255));
            }
            return stringBuffer.toString();
        }
    };

    public static long a(byte[] bArr, int i, int i2) {
        long j = 0;
        int i3 = i + i2;
        if (i2 < 2) {
            throw new IllegalArgumentException("Length " + i2 + " must be at least 2");
        }
        if (bArr[i] != (byte) 0) {
            int i4 = i;
            while (i4 < i3 && bArr[i4] == (byte) 32) {
                i4++;
            }
            byte b = bArr[i3 - 1];
            if (b == (byte) 0 || b == (byte) 32) {
                int i5 = i3 - 1;
                byte b2 = bArr[i5 - 1];
                int i6 = i5;
                while (i4 < i6 - 1 && (r3 == (byte) 0 || r3 == (byte) 32)) {
                    i5 = i6 - 1;
                    b2 = bArr[i5 - 1];
                    i6 = i5;
                }
                while (i4 < i6) {
                    b2 = bArr[i4];
                    if (b2 < (byte) 48 || b2 > (byte) 55) {
                        throw new IllegalArgumentException(a(bArr, i, i2, i4, b2));
                    }
                    i4++;
                    j = ((long) (b2 - 48)) + (j << 3);
                }
            } else {
                throw new IllegalArgumentException(a(bArr, i, i2, i3 - 1, b));
            }
        }
        return j;
    }

    public static long b(byte[] bArr, int i, int i2) {
        if ((bArr[i] & 128) == 0) {
            return a(bArr, i, i2);
        }
        boolean z = bArr[i] == (byte) -1;
        if (i2 < 9) {
            return a(bArr, i, i2, z);
        }
        return b(bArr, i, i2, z);
    }

    private static long a(byte[] bArr, int i, int i2, boolean z) {
        if (i2 >= 9) {
            throw new IllegalArgumentException("At offset " + i + ", " + i2 + " byte binary number" + " exceeds maximum signed long" + " value");
        }
        long j = 0;
        int i3 = 1;
        while (i3 < i2) {
            long j2 = ((long) (bArr[i + i3] & 255)) + (j << 8);
            i3++;
            j = j2;
        }
        if (z) {
            j = (j - 1) ^ (((long) Math.pow(2.0d, (double) ((i2 - 1) * 8))) - 1);
        }
        return z ? -j : j;
    }

    private static long b(byte[] bArr, int i, int i2, boolean z) {
        Object obj = new byte[(i2 - 1)];
        System.arraycopy(bArr, i + 1, obj, 0, i2 - 1);
        BigInteger bigInteger = new BigInteger(obj);
        if (z) {
            bigInteger = bigInteger.add(BigInteger.valueOf(-1)).not();
        }
        if (bigInteger.bitLength() <= 63) {
            return z ? -bigInteger.longValue() : bigInteger.longValue();
        } else {
            throw new IllegalArgumentException("At offset " + i + ", " + i2 + " byte binary number" + " exceeds maximum signed long" + " value");
        }
    }

    public static boolean a(byte[] bArr, int i) {
        return bArr[i] == (byte) 1;
    }

    private static String a(byte[] bArr, int i, int i2, int i3, byte b) {
        return "Invalid byte " + b + " at offset " + (i3 - i) + " in '" + new String(bArr, i, i2).replaceAll(DexFormat.MAGIC_SUFFIX, "{NUL}") + "' len=" + i2;
    }

    public static String c(byte[] bArr, int i, int i2) {
        String a;
        try {
            a = a(bArr, i, i2, a);
        } catch (IOException e) {
            try {
                a = a(bArr, i, i2, b);
            } catch (Throwable e2) {
                throw new RuntimeException(e2);
            }
        }
        return a;
    }

    public static String a(byte[] bArr, int i, int i2, p pVar) throws IOException {
        while (i2 > 0 && bArr[(i + i2) - 1] == (byte) 0) {
            i2--;
        }
        if (i2 <= 0) {
            return "";
        }
        Object obj = new byte[i2];
        System.arraycopy(bArr, i, obj, 0, i2);
        return pVar.a(obj);
    }

    public static boolean a(byte[] bArr) {
        int i = 0;
        long j = 0;
        long j2 = 0;
        long j3 = 0;
        int i2 = 0;
        while (i < bArr.length) {
            int i3;
            byte b = bArr[i];
            if (Opcodes.REM_INT > i || i >= Opcodes.SUB_LONG) {
                byte b2 = b;
            } else {
                if ((byte) 48 <= b && b <= (byte) 55) {
                    i3 = i2 + 1;
                    if (i2 < 6) {
                        j3 = ((j3 * 8) + ((long) b)) - 48;
                        i2 = i3;
                        i3 = 32;
                    } else {
                        i2 = i3;
                    }
                }
                if (i2 > 0) {
                    i2 = 6;
                }
                i3 = 32;
            }
            j2 += (long) (i3 & 255);
            j += (long) i3;
            i++;
        }
        if (j3 == j2 || j3 == j || j3 > j2) {
            return true;
        }
        return false;
    }
}
