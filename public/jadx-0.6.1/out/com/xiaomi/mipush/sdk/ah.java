package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.b.c;
import com.xiaomi.xmpush.thrift.ab;
import com.xiaomi.xmpush.thrift.ac;
import com.xiaomi.xmpush.thrift.af;
import com.xiaomi.xmpush.thrift.aj;
import com.xiaomi.xmpush.thrift.ak;
import com.xiaomi.xmpush.thrift.am;
import com.xiaomi.xmpush.thrift.ao;
import com.xiaomi.xmpush.thrift.aq;
import com.xiaomi.xmpush.thrift.ar;
import com.xiaomi.xmpush.thrift.v;
import com.xiaomi.xmpush.thrift.x;
import com.xiaomi.xmpush.thrift.y;
import java.nio.ByteBuffer;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.thrift.a;

public class ah {
    private static final byte[] a = new byte[]{(byte) 100, (byte) 23, (byte) 84, (byte) 114, (byte) 72, (byte) 0, (byte) 4, (byte) 97, (byte) 73, (byte) 97, (byte) 2, (byte) 52, (byte) 84, (byte) 102, (byte) 18, (byte) 32};

    protected static <T extends a<T, ?>> ac a(Context context, T t, com.xiaomi.xmpush.thrift.a aVar) {
        return a(context, t, aVar, !aVar.equals(com.xiaomi.xmpush.thrift.a.Registration), context.getPackageName(), q.a(context).c());
    }

    protected static <T extends a<T, ?>> ac a(Context context, T t, com.xiaomi.xmpush.thrift.a aVar, boolean z, String str, String str2) {
        byte[] a = ar.a(t);
        if (a == null) {
            c.a("invoke convertThriftObjectToBytes method, return null.");
            return null;
        }
        ac acVar = new ac();
        if (z) {
            String f = q.a(context).f();
            if (TextUtils.isEmpty(f)) {
                c.a("regSecret is empty, return null");
                return null;
            }
            try {
                a = b(com.xiaomi.channel.commonutils.g.a.a(f), a);
            } catch (Exception e) {
                c.d("encryption error. ");
            }
        }
        v vVar = new v();
        vVar.a = 5;
        vVar.b = "fakeid";
        acVar.a(vVar);
        acVar.a(ByteBuffer.wrap(a));
        acVar.a(aVar);
        acVar.c(true);
        acVar.b(str);
        acVar.a(z);
        acVar.a(str2);
        return acVar;
    }

    private static Cipher a(byte[] bArr, int i) {
        Key secretKeySpec = new SecretKeySpec(bArr, "AES");
        AlgorithmParameterSpec ivParameterSpec = new IvParameterSpec(a);
        Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
        instance.init(i, secretKeySpec, ivParameterSpec);
        return instance;
    }

    public static a a(Context context, ac acVar) {
        if (acVar.c()) {
            try {
                byte[] a = a(com.xiaomi.channel.commonutils.g.a.a(q.a(context).f()), acVar.f());
            } catch (Throwable e) {
                throw new d("the aes decrypt failed.", e);
            }
        }
        a = acVar.f();
        a a2 = a(acVar.a(), acVar.c);
        if (a2 != null) {
            ar.a(a2, a);
        }
        return a2;
    }

    private static a a(com.xiaomi.xmpush.thrift.a aVar, boolean z) {
        switch (ai.a[aVar.ordinal()]) {
            case 1:
                return new com.xiaomi.xmpush.thrift.ah();
            case 2:
                return new ao();
            case 3:
                return new am();
            case 4:
                return new aq();
            case 5:
                return new ak();
            case 6:
                return new x();
            case 7:
                return new ab();
            case 8:
                return new aj();
            case 9:
                if (z) {
                    return new af();
                }
                a yVar = new y();
                yVar.a(true);
                return yVar;
            case 10:
                return new ab();
            default:
                return null;
        }
    }

    public static byte[] a(byte[] bArr, byte[] bArr2) {
        return a(bArr, 2).doFinal(bArr2);
    }

    public static byte[] b(byte[] bArr, byte[] bArr2) {
        return a(bArr, 1).doFinal(bArr2);
    }
}
