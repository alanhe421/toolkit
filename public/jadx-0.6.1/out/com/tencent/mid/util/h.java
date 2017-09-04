package com.tencent.mid.util;

import android.util.Base64;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import java.io.ByteArrayOutputStream;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class h {
    private static RSAPublicKey a = null;

    public static void a(String str) {
        try {
            a = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(str, 0)));
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException e2) {
            throw new Exception("公钥非法");
        } catch (NullPointerException e3) {
            throw new Exception("公钥数据为空");
        }
    }

    public static byte[] a(byte[] bArr) {
        if (a == null) {
            throw new Exception("加密公钥为空, 请设置");
        }
        byte[] doFinal;
        Cipher instance = Cipher.getInstance("RSA/NONE/PKCS1PADDING");
        instance.init(1, a);
        int length = bArr.length;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 0;
        int i2 = 0;
        while (length - i2 > 0) {
            doFinal = length - i2 > Opcodes.INVOKE_SUPER_RANGE ? instance.doFinal(bArr, i2, Opcodes.INVOKE_SUPER_RANGE) : instance.doFinal(bArr, i2, length - i2);
            byteArrayOutputStream.write(doFinal, 0, doFinal.length);
            i2 = i + 1;
            int i3 = i2;
            i2 *= Opcodes.INVOKE_SUPER_RANGE;
            i = i3;
        }
        doFinal = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();
        return doFinal;
    }

    public static byte[][] a(byte[] bArr, int i) {
        int length = bArr.length / i;
        int length2 = bArr.length % i;
        int i2 = length2 != 0 ? 1 : 0;
        byte[][] bArr2 = new byte[(length + i2)][];
        for (int i3 = 0; i3 < length + i2; i3++) {
            Object obj = new byte[i];
            if (i3 != (length + i2) - 1 || length2 == 0) {
                System.arraycopy(bArr, i3 * i, obj, 0, i);
            } else {
                System.arraycopy(bArr, i3 * i, obj, 0, length2);
            }
            bArr2[i3] = obj;
        }
        return bArr2;
    }

    public static String b(byte[] bArr) {
        if (a == null) {
            throw new Exception("解密公钥为空, 请设置");
        }
        try {
            Cipher instance = Cipher.getInstance("RSA/NONE/PKCS1PADDING");
            instance.init(2, a);
            byte[][] a = a(bArr, a.getModulus().bitLength() / 8);
            int length = a.length;
            String str = "";
            int i = 0;
            while (i < length) {
                String str2 = str + new String(instance.doFinal(a[i]), "UTF-8");
                i++;
                str = str2;
            }
            return str;
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此解密算法");
        } catch (NoSuchPaddingException e2) {
            e2.printStackTrace();
            return null;
        } catch (InvalidKeyException e3) {
            throw new Exception("解密私钥非法,请检查");
        } catch (IllegalBlockSizeException e4) {
            throw new Exception("密文长度非法");
        } catch (BadPaddingException e5) {
            throw new Exception("密文数据已损坏");
        }
    }
}
