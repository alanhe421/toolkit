package oicq.wlogin_sdk.request;

import com.tencent.connect.common.Constants;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import javax.crypto.Cipher;
import oicq.wlogin_sdk.b.cp;
import oicq.wlogin_sdk.b.cr;
import oicq.wlogin_sdk.tools.ErrMsg;
import oicq.wlogin_sdk.tools.MD5;
import oicq.wlogin_sdk.tools.cryptor;
import oicq.wlogin_sdk.tools.util;

/* compiled from: fetch_notice */
public class i {
    private u a;
    private int b;
    private int c;
    private ErrMsg d;

    public i(u uVar, int i, int i2, ErrMsg errMsg) {
        this.a = uVar;
        this.b = i;
        this.c = i2;
        this.d = errMsg;
    }

    byte[] a(int i) {
        ByteBuffer allocate = ByteBuffer.allocate(((((util.SDK_VERSION.length() + 24) + 2) + 2) + 1) + 4);
        async_context b = u.b(this.a.h);
        allocate.put(u.ad);
        allocate.putInt((int) b._sappid);
        allocate.putInt((int) b._sub_appid);
        allocate.putShort((short) 0);
        allocate.putLong(this.a.f);
        allocate.putShort((short) util.SDK_VERSION.length());
        allocate.put(util.SDK_VERSION.getBytes());
        allocate.putShort((short) this.b);
        allocate.putShort((short) this.c);
        allocate.put((byte) (i == -1000 ? 1 : 0));
        allocate.putInt(i);
        byte[] a = new cr().a(0, u.u, 0);
        ByteBuffer allocate2 = ByteBuffer.allocate(((((allocate.capacity() + 4) + 4) + (cp.j.length + 4)) + 8) + a.length);
        allocate2.putShort((short) 2);
        allocate2.putShort((short) 3);
        allocate2.putShort((short) 512);
        allocate2.putShort((short) allocate.capacity());
        allocate2.put(allocate.array());
        allocate2.putShort((short) 513);
        allocate2.putShort((short) 4);
        allocate2.putInt(2);
        allocate2.putShort((short) 2);
        allocate2.putShort((short) cp.j.length);
        allocate2.put(cp.j);
        allocate2.put(a);
        a = cryptor.encrypt(allocate2.array(), 0, allocate2.position(), this.a.c);
        if (a == null) {
            a = new byte[0];
        }
        ByteBuffer allocate3 = ByteBuffer.allocate(((this.a.c.length + 2) + 2) + a.length);
        allocate3.putShort((short) this.a.c.length);
        allocate3.put(this.a.c);
        allocate3.putShort((short) a.length);
        allocate3.put(a);
        return allocate3.array();
    }

    int a(byte[] bArr) {
        byte[] bArr2;
        Exception e;
        byte[] bArr3 = null;
        util.LOGI("notice len " + bArr.length, this.a.f + "");
        byte[] string_to_buf = util.string_to_buf("30820122300d06092a864886f70d01010105000382010f003082010a0282010100bb65a9189e42aabf8c4c97b8bb7e35f6239df71152c0108d5c9b98d86ed7b14fa4b6e2ca5749eda03b5746e97b10c1772eab364fcedbfc4b3bb4d839ed97f657daa54622b54dfb29fe66f37f3e26e779675fec2337d0f8cbdf550b04f936be0927bbbecc851b6d966a3ba51c9747a8c588979ec248d5c8a66d1dd4fed0bcd3eb78725fd04b25cdceeac17d0068f07b3a2a360105cc1f4a0fd361d8d3ff0a9e5598b4196304635482be7ceda63a80479aa396a341fb206c81d7c476f860ac6d90734d523d1015eb73f390104c2bb85d0c05db4d11feae941ff5c92be9a1c123283dc2e0dc1368420d6f71cc50e343534e7c0ff16345680859e14c35f1f021cdfb0203010001");
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        try {
            bArr2 = new byte[wrap.getShort()];
            wrap.get(bArr2);
            Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            instance.init(2, (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(string_to_buf)));
            string_to_buf = instance.doFinal(bArr2);
            if (string_to_buf == null || string_to_buf.length == 0) {
                return -1000;
            }
            ByteBuffer wrap2 = ByteBuffer.wrap(string_to_buf);
            bArr2 = new byte[wrap2.getShort()];
            try {
                wrap2.get(bArr2);
                bArr3 = new byte[wrap2.getShort()];
                wrap2.get(bArr3);
                wrap2.getInt();
            } catch (Exception e2) {
                e = e2;
                util.printException(e);
                if (bArr2 != null) {
                }
                return -1000;
            }
            if (bArr2 != null || bArr3 == null) {
                return -1000;
            }
            string_to_buf = new byte[(wrap.limit() - wrap.position())];
            wrap.get(string_to_buf);
            string_to_buf = cryptor.decrypt(string_to_buf, 0, string_to_buf.length, bArr2);
            if (string_to_buf == null || string_to_buf.length == 0) {
                return -1000;
            }
            if (!Arrays.equals(bArr3, MD5.toMD5Byte(string_to_buf))) {
                return -1000;
            }
            ByteBuffer wrap3 = ByteBuffer.wrap(string_to_buf);
            wrap3.getShort();
            short s = wrap3.getShort();
            if (s == (short) 0) {
                return -1000;
            }
            for (short s2 = (short) 0; s2 < s; s2++) {
                short s3 = wrap3.getShort();
                byte[] bArr4 = new byte[wrap3.getShort()];
                wrap3.get(bArr4);
                switch (s3) {
                    case (short) 1281:
                        ByteBuffer wrap4 = ByteBuffer.wrap(bArr4);
                        int i = wrap4.getInt();
                        util.LOGI("what " + i, "");
                        if (i == 0) {
                            bArr4 = new byte[wrap4.getShort()];
                            util.LOGI("msg len " + bArr4.length, "");
                            if (bArr4.length != 0) {
                                wrap4.get(bArr4);
                                this.d.setMessage(new String(bArr4));
                                this.d.setType(257);
                                break;
                            }
                            return -1000;
                        }
                        return -1000;
                    case (short) 1282:
                        this.d.setTitle(new String(bArr4));
                        break;
                    default:
                        break;
                }
            }
            return this.d.getType();
        } catch (Exception e3) {
            e = e3;
            bArr2 = null;
            util.printException(e);
            if (bArr2 != null) {
            }
            return -1000;
        }
    }

    int b(int i) {
        Exception exception;
        Throwable th;
        HttpURLConnection httpURLConnection = null;
        try {
            byte[] a = a(i);
            HttpURLConnection httpURLConnection2 = (HttpURLConnection) new URL("http://" + (new String[]{"ts7", "ts8", "ts9"}[((int) Math.round(Math.random() * 1000.0d)) % 3] + ".qq.com:8080") + "/msg").openConnection();
            try {
                httpURLConnection2.setRequestMethod(Constants.HTTP_POST);
                httpURLConnection2.setRequestProperty("Content-Length", a.length + "");
                httpURLConnection2.setRequestProperty("Content-Type", "application/octet-stream");
                httpURLConnection2.setConnectTimeout(cp.i);
                httpURLConnection2.setReadTimeout(cp.i);
                httpURLConnection2.setDoOutput(true);
                httpURLConnection2.setDoInput(true);
                if (j.a(httpURLConnection2, (long) this.a.l)) {
                    OutputStream outputStream = httpURLConnection2.getOutputStream();
                    outputStream.write(a);
                    outputStream.close();
                    int responseCode = httpURLConnection2.getResponseCode();
                    util.LOGI("notice request response code=" + responseCode, "" + this.a.f);
                    if (200 != responseCode) {
                        if (httpURLConnection2 != null) {
                            httpURLConnection2.disconnect();
                        }
                        return -1000;
                    }
                    Object obj;
                    util.LOGI("recv notice ...", "" + this.a.f);
                    InputStream inputStream = httpURLConnection2.getInputStream();
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = inputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr, 0, read);
                    }
                    if (a(byteArrayOutputStream.toByteArray()) != -1000) {
                        obj = 1;
                    } else {
                        obj = null;
                    }
                    byteArrayOutputStream.close();
                    if (obj == null) {
                        if (httpURLConnection2 != null) {
                            httpURLConnection2.disconnect();
                        }
                        return -1000;
                    }
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    return 257;
                }
                util.LOGI("notice request connect failed", "" + this.a.f);
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                }
                return -1000;
            } catch (Exception e) {
                Exception exception2 = e;
                httpURLConnection = httpURLConnection2;
                exception = exception2;
                try {
                    util.printException(exception);
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    return -1000;
                } catch (Throwable th2) {
                    th = th2;
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                httpURLConnection = httpURLConnection2;
                th = th3;
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                throw th;
            }
        } catch (Exception e2) {
            exception = e2;
            util.printException(exception);
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            return -1000;
        }
    }
}
