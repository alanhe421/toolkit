package oicq.wlogin_sdk.tools;

public class cryptor {
    public static byte[] encrypt(byte[] bArr, int i, int i2, byte[] bArr2) {
        if (bArr == null || bArr2 == null) {
            return null;
        }
        Object obj = new byte[i2];
        System.arraycopy(bArr, i, obj, 0, i2);
        Object obj2 = new byte[bArr2.length];
        System.arraycopy(bArr2, 0, obj2, 0, bArr2.length);
        return new a().b(obj, obj2);
    }

    public static byte[] decrypt(byte[] bArr, int i, int i2, byte[] bArr2) {
        if (bArr == null || bArr2 == null) {
            return null;
        }
        byte[] bArr3 = new byte[i2];
        System.arraycopy(bArr, i, bArr3, 0, i2);
        byte[] bArr4 = new byte[bArr2.length];
        System.arraycopy(bArr2, 0, bArr4, 0, bArr2.length);
        return new a().a(bArr3, bArr4);
    }
}
