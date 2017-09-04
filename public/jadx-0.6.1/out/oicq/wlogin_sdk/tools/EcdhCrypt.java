package oicq.wlogin_sdk.tools;

import android.content.Context;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.KeyAgreement;
import oicq.wlogin_sdk.request.u;

public class EcdhCrypt {
    public static final String DEFAULT_PUB_KEY = "020b03cf3d99541f29ffec281bebbd4ea211292ac1f53d7128";
    public static final String DEFAULT_SHARE_KEY = "4da0f614fc9f29c2054c77048a6566d7";
    public static final String S_PUB_KEY = "04928D8850673088B343264E0C6BACB8496D697799F37211DEB25BB73906CB089FEA9639B4E0260498B51A992D50813DA8";
    public static final String X509_S_PUB_KEY = "3046301006072A8648CE3D020106052B8104001F03320004928D8850673088B343264E0C6BACB8496D697799F37211DEB25BB73906CB089FEA9639B4E0260498B51A992D50813DA8";
    public static byte[] _c_pri_key = new byte[0];
    public static byte[] _c_pub_key = new byte[0];
    private static byte[] _g_share_key = new byte[0];
    private static boolean initFlg = false;
    public static PrivateKey pkcs8PrivateKey;
    private static boolean userOpenSSLLib = true;
    public static PublicKey x509PublicKey;

    public native int GenECDHKeyEx(String str, String str2, String str3);

    public EcdhCrypt(Context context) {
        util.loadLibrary("wtecdh", context);
    }

    public int GenereateKey() {
        try {
            int GenECDHKeyEx;
            synchronized (EcdhCrypt.class) {
                GenECDHKeyEx = GenECDHKeyEx(S_PUB_KEY, "", "");
            }
            return GenECDHKeyEx;
        } catch (UnsatisfiedLinkError e) {
            util.LOGI("GenereateKey failed " + e.getMessage(), "");
            return -1;
        } catch (RuntimeException e2) {
            util.LOGW("OpenSSL generate key failed, turn another method " + e2.getMessage(), "");
            return -1;
        }
    }

    public byte[] get_c_pub_key() {
        return (byte[]) _c_pub_key.clone();
    }

    public void set_c_pub_key(byte[] bArr) {
        if (bArr != null) {
            _c_pub_key = (byte[]) bArr.clone();
        } else {
            _c_pub_key = new byte[0];
        }
    }

    public void set_c_pri_key(byte[] bArr) {
        if (bArr != null) {
            _c_pri_key = (byte[]) bArr.clone();
        } else {
            _c_pri_key = new byte[0];
        }
    }

    public byte[] get_g_share_key() {
        return (byte[]) _g_share_key.clone();
    }

    public void set_g_share_key(byte[] bArr) {
        if (bArr != null) {
            _g_share_key = (byte[]) bArr.clone();
        } else {
            _g_share_key = new byte[0];
        }
    }

    public byte[] calShareKeyMd5ByPeerPublicKey(byte[] bArr) {
        util.LOGI("userOpenSSLLib " + userOpenSSLLib + " peerRawPublicKey " + util.buf_to_string(bArr) + " at " + u.l(), "");
        if (true == userOpenSSLLib) {
            return calShareKeyByOpenSSL(util.buf_to_string(_c_pri_key), util.buf_to_string(_c_pub_key), util.buf_to_string(bArr));
        }
        return calShareKeyByBouncycastle(bArr);
    }

    private byte[] calShareKeyByOpenSSL(String str, String str2, String str3) {
        util.LOGI("calShareKeyByOpenSSL publickey " + str2, "");
        if (GenECDHKeyEx(str3, str2, str) == 0) {
            return _g_share_key;
        }
        u.al.attr_api(2461268);
        return null;
    }

    private PublicKey constructX509PublicKey(String str) {
        util.LOGI("constructX509PublicKey publickey " + str + " at " + u.l(), "");
        return KeyFactory.getInstance("EC", "BC").generatePublic(new X509EncodedKeySpec(util.string_to_buf(str)));
    }

    private byte[] calShareKeyByBouncycastle(byte[] bArr) {
        try {
            String str = "3046301006072A8648CE3D020106052B8104001F03320004";
            if (bArr.length < 30) {
                str = "302E301006072A8648CE3D020106052B8104001F031A00";
            }
            Key constructX509PublicKey = constructX509PublicKey(str + util.buf_to_string(bArr));
            util.LOGI("raw public key " + util.buf_to_string(_c_pub_key), "");
            util.LOGI("pkcs8PrivateKey " + pkcs8PrivateKey.toString(), "");
            KeyAgreement instance = KeyAgreement.getInstance("ECDH", "BC");
            instance.init(pkcs8PrivateKey);
            instance.doPhase(constructX509PublicKey, true);
            byte[] generateSecret = instance.generateSecret();
            byte[] toMD5Byte = MD5.toMD5Byte(generateSecret);
            util.LOGI("share key " + util.buf_to_string(generateSecret), "");
            util.LOGI("share key md5 " + util.buf_to_string(toMD5Byte), "");
            return toMD5Byte;
        } catch (ExceptionInInitializerError e) {
            util.LOGW("create key failed ExceptionInInitializerError, " + e.getMessage(), "");
            u.al.attr_api(2459818);
            return null;
        } catch (Exception e2) {
            util.LOGI("calShareKeyByBouncycastle failed " + pkcs8PrivateKey.toString() + " peer public key " + util.buf_to_string(bArr), "");
            util.printException(e2);
            u.al.attr_api(2459818);
            return null;
        }
    }

    private int initShareKeyByOpenSSL() {
        if (GenereateKey() != 0) {
            return -1;
        }
        if (_c_pub_key == null || _c_pub_key.length == 0 || _c_pri_key == null || _c_pri_key.length == 0 || _g_share_key == null || _g_share_key.length == 0) {
            util.LOGI("_c_pub_key " + util.buf_to_string(_c_pub_key), "");
            util.LOGI("_c_pri_key " + util.buf_to_string(_c_pri_key), "");
            util.LOGI("_g_share_key " + util.buf_to_string(_g_share_key), "");
            util.LOGI("initShareKeyByOpenSSL generate null key", "");
            return -2;
        }
        util.LOGI("initShareKeyByOpenSSL OK", "");
        return 0;
    }

    private int initShareKeyByBouncycastle() {
        try {
            KeyPairGenerator instance = KeyPairGenerator.getInstance("EC", "BC");
            instance.initialize(new ECGenParameterSpec("secp192k1"));
            KeyPair genKeyPair = instance.genKeyPair();
            PublicKey publicKey = genKeyPair.getPublic();
            Object encoded = publicKey.getEncoded();
            Object obj = genKeyPair.getPrivate();
            obj.getEncoded();
            Key constructX509PublicKey = constructX509PublicKey("3046301006072A8648CE3D020106052B8104001F03320004928D8850673088B343264E0C6BACB8496D697799F37211DEB25BB73906CB089FEA9639B4E0260498B51A992D50813DA8");
            KeyAgreement instance2 = KeyAgreement.getInstance("ECDH", "BC");
            instance2.init(obj);
            instance2.doPhase(constructX509PublicKey, true);
            _g_share_key = MD5.toMD5Byte(instance2.generateSecret());
            _c_pub_key = new byte[49];
            System.arraycopy(encoded, 23, _c_pub_key, 0, 49);
            x509PublicKey = publicKey;
            pkcs8PrivateKey = obj;
            return 0;
        } catch (ExceptionInInitializerError e) {
            util.LOGW("create key pair and shared key failed ExceptionInInitializerError, " + e.getMessage(), "");
            u.al.attr_api(2368735);
            return -1;
        } catch (Exception e2) {
            util.LOGI("initShareKeyByBouncycastle failed, ", "");
            util.printException(e2);
            u.al.attr_api(2368735);
            return -2;
        }
    }

    public int initShareKeyByDefault() {
        _c_pub_key = util.string_to_buf("020b03cf3d99541f29ffec281bebbd4ea211292ac1f53d7128");
        _g_share_key = util.string_to_buf("4da0f614fc9f29c2054c77048a6566d7");
        util.LOGI("initShareKeyByDefault OK", "");
        return 0;
    }

    public int initShareKey() {
        if (true == initFlg) {
            return 0;
        }
        initFlg = true;
        if (initShareKeyByOpenSSL() == 0) {
            userOpenSSLLib = true;
            return 0;
        } else if (initShareKeyByBouncycastle() != 0) {
            return initShareKeyByDefault();
        } else {
            userOpenSSLLib = false;
            return 0;
        }
    }
}
