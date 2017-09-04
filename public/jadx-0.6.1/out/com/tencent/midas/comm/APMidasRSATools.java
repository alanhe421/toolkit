package com.tencent.midas.comm;

import android.util.Base64;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;

public class APMidasRSATools {
    private final String a = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAy+ZF2XdZ6RwK/lAtyC7h\rLA4KaURXrD7gEGcx+t/l8KKLTLfR3j4vOHXHXjixipSXicyJcDH74rfO7ISnFkWQ\r+kVmB5kfhdrq5z6D/h/q7ko7MdU9SUlfZfAxwnS4VJY4xWoFWG9ZAoh5ZHJcloDU\rol0qYTUX/yWNiHkoUtnU+SP+ZJjODpqcYuVdLxlA0YelafeBc3SCeuEcPH9lIiRZ\rw0I91wQvPq7gM7/6qnMEdzm7nJdCIni83INl2bh3gW5UBwFBpNrI/fZkgDA4aVGV\rpplEP9bChkCpq2e1T9gw0ODuEVmgVaSvdwHLMYcGn+nYjWDYy16b6ImdkubF8q5l\rhwIDAQAB\r";
    private RSAPublicKey b;

    private byte a(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    private byte[] a(String str) {
        if (str == null || str.equals("")) {
            return null;
        }
        String toUpperCase = str.toUpperCase();
        int length = toUpperCase.length() / 2;
        char[] toCharArray = toUpperCase.toCharArray();
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) (a(toCharArray[i2 + 1]) | (a(toCharArray[i2]) << 4));
        }
        return bArr;
    }

    private byte[] a(RSAPublicKey rSAPublicKey, byte[] bArr) {
        if (rSAPublicKey == null) {
            throw new Exception("解密私钥为空, 请设置");
        }
        try {
            Cipher instance = Cipher.getInstance("RSA");
            instance.init(2, rSAPublicKey);
            return instance.doFinal(bArr);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("没有这样的解密算法");
        } catch (InvalidKeyException e2) {
            throw new Exception("密钥无效，请检查确认");
        } catch (IllegalBlockSizeException e3) {
            throw new Exception("密文长度无效或者过长");
        } catch (BadPaddingException e4) {
            throw new Exception("密文数据已损坏");
        } catch (Exception e5) {
            e5.printStackTrace();
            APLog.i("APMidasRSATools exception", e5.toString());
            throw new Exception("其他错误");
        }
    }

    private byte[] b(String str) {
        return a(str);
    }

    private void c(String str) {
        try {
            this.b = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(str, 0)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String deCodeKey(String str) {
        try {
            getClass();
            c("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAy+ZF2XdZ6RwK/lAtyC7h\rLA4KaURXrD7gEGcx+t/l8KKLTLfR3j4vOHXHXjixipSXicyJcDH74rfO7ISnFkWQ\r+kVmB5kfhdrq5z6D/h/q7ko7MdU9SUlfZfAxwnS4VJY4xWoFWG9ZAoh5ZHJcloDU\rol0qYTUX/yWNiHkoUtnU+SP+ZJjODpqcYuVdLxlA0YelafeBc3SCeuEcPH9lIiRZ\rw0I91wQvPq7gM7/6qnMEdzm7nJdCIni83INl2bh3gW5UBwFBpNrI/fZkgDA4aVGV\rpplEP9bChkCpq2e1T9gw0ODuEVmgVaSvdwHLMYcGn+nYjWDYy16b6ImdkubF8q5l\rhwIDAQAB\r");
            return new String(a(this.b, b(str)));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
