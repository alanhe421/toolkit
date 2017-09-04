package org.apache.commons.compress.archivers.zip;

import java.math.BigInteger;
import org.apache.commons.compress.archivers.zip.UnsupportedZipFeatureException.a;

/* compiled from: ZipUtil */
public abstract class s {
    private static final byte[] a = ZipLong.getBytes(8448);

    public static long a(int i) {
        if (i < 0) {
            return 4294967296L + ((long) i);
        }
        return (long) i;
    }

    public static byte[] a(byte[] bArr) {
        int length = bArr.length - 1;
        for (int i = 0; i < bArr.length / 2; i++) {
            byte b = bArr[i];
            bArr[i] = bArr[length - i];
            bArr[length - i] = b;
        }
        return bArr;
    }

    static long a(BigInteger bigInteger) {
        if (bigInteger.bitLength() <= 63) {
            return bigInteger.longValue();
        }
        throw new NumberFormatException("The BigInteger cannot fit inside a 64 bit java long: [" + bigInteger + "]");
    }

    static BigInteger a(long j) {
        if (j < -2147483648L) {
            throw new IllegalArgumentException("Negative longs < -2^31 not permitted: [" + j + "]");
        }
        if (j < 0 && j >= -2147483648L) {
            j = a((int) j);
        }
        return BigInteger.valueOf(j);
    }

    public static int a(byte b) {
        return b >= (byte) 0 ? b : b + 256;
    }

    public static byte b(int i) {
        if (i > 255 || i < 0) {
            throw new IllegalArgumentException("Can only convert non-negative integers between [0,255] to byte: [" + i + "]");
        } else if (i < 128) {
            return (byte) i;
        } else {
            return (byte) (i - 256);
        }
    }

    static byte[] b(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        Object obj = new byte[bArr.length];
        System.arraycopy(bArr, 0, obj, 0, obj.length);
        return obj;
    }

    private static boolean b(n nVar) {
        return !nVar.g().b();
    }

    private static boolean c(n nVar) {
        return nVar.getMethod() == 0 || nVar.getMethod() == 8;
    }

    static void a(n nVar) throws UnsupportedZipFeatureException {
        if (!b(nVar)) {
            throw new UnsupportedZipFeatureException(a.a, nVar);
        } else if (!c(nVar)) {
            ZipMethod methodByCode = ZipMethod.getMethodByCode(nVar.getMethod());
            if (methodByCode == null) {
                throw new UnsupportedZipFeatureException(a.b, nVar);
            }
            throw new UnsupportedZipFeatureException(methodByCode, nVar);
        }
    }
}
