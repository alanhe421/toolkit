package oicq.wlogin_sdk.request;

import android.content.Context;
import com.iflytek.speech.VoiceWakeuperAidl;
import com.tencent.connect.common.Constants;
import com.tencent.openqq.protocol.imsdk.im_common;
import com.tencent.smtt.sdk.TbsDownloadConfig;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.util.Random;
import oicq.wlogin_sdk.b.aa;
import oicq.wlogin_sdk.b.ab;
import oicq.wlogin_sdk.b.ad;
import oicq.wlogin_sdk.b.ae;
import oicq.wlogin_sdk.b.ah;
import oicq.wlogin_sdk.b.ai;
import oicq.wlogin_sdk.b.aj;
import oicq.wlogin_sdk.b.ak;
import oicq.wlogin_sdk.b.an;
import oicq.wlogin_sdk.b.ap;
import oicq.wlogin_sdk.b.aq;
import oicq.wlogin_sdk.b.at;
import oicq.wlogin_sdk.b.au;
import oicq.wlogin_sdk.b.ax;
import oicq.wlogin_sdk.b.ay;
import oicq.wlogin_sdk.b.az;
import oicq.wlogin_sdk.b.b;
import oicq.wlogin_sdk.b.bb;
import oicq.wlogin_sdk.b.bc;
import oicq.wlogin_sdk.b.bd;
import oicq.wlogin_sdk.b.be;
import oicq.wlogin_sdk.b.bg;
import oicq.wlogin_sdk.b.bh;
import oicq.wlogin_sdk.b.bi;
import oicq.wlogin_sdk.b.bj;
import oicq.wlogin_sdk.b.bl;
import oicq.wlogin_sdk.b.bm;
import oicq.wlogin_sdk.b.bq;
import oicq.wlogin_sdk.b.br;
import oicq.wlogin_sdk.b.bt;
import oicq.wlogin_sdk.b.bu;
import oicq.wlogin_sdk.b.bx;
import oicq.wlogin_sdk.b.cb;
import oicq.wlogin_sdk.b.ce;
import oicq.wlogin_sdk.b.cf;
import oicq.wlogin_sdk.b.ch;
import oicq.wlogin_sdk.b.ck;
import oicq.wlogin_sdk.b.cn;
import oicq.wlogin_sdk.b.co;
import oicq.wlogin_sdk.b.cp;
import oicq.wlogin_sdk.b.e;
import oicq.wlogin_sdk.b.f;
import oicq.wlogin_sdk.b.g;
import oicq.wlogin_sdk.b.h;
import oicq.wlogin_sdk.b.i;
import oicq.wlogin_sdk.b.k;
import oicq.wlogin_sdk.b.m;
import oicq.wlogin_sdk.b.n;
import oicq.wlogin_sdk.b.o;
import oicq.wlogin_sdk.b.p;
import oicq.wlogin_sdk.b.q;
import oicq.wlogin_sdk.b.s;
import oicq.wlogin_sdk.b.t;
import oicq.wlogin_sdk.b.v;
import oicq.wlogin_sdk.b.w;
import oicq.wlogin_sdk.b.x;
import oicq.wlogin_sdk.b.y;
import oicq.wlogin_sdk.b.z;
import oicq.wlogin_sdk.report.report_t3;
import oicq.wlogin_sdk.sharemem.WloginSigInfo;
import oicq.wlogin_sdk.tools.EcdhCrypt;
import oicq.wlogin_sdk.tools.ErrMsg;
import oicq.wlogin_sdk.tools.InternationMsg;
import oicq.wlogin_sdk.tools.InternationMsg.MSG_TYPE;
import oicq.wlogin_sdk.tools.MD5;
import oicq.wlogin_sdk.tools.cryptor;
import oicq.wlogin_sdk.tools.util;

public class oicq_request {
    static String[] C = new String[]{"111.30.137.20", "123.126.122.126", "123.151.176.23", "120.198.203.150", "14.17.41.156", "163.177.71.159", "101.227.130.77", "117.135.172.187", "140.207.69.123"};
    static String[] D = new String[]{"180.163.15.182", "183.192.200.28", "223.167.105.36", "183.61.56.18", "183.232.119.221", "163.177.86.123", "123.151.92.19", "125.39.52.120", "123.126.121.172", "117.135.169.107"};
    static int F = 0;
    static String G = "";
    static String H = "";
    protected byte[] A = new byte[0];
    protected byte[] B = new byte[0];
    int E = 0;
    public Context a;
    int b = 4096;
    int c = 0;
    int d = 27;
    int e = 0;
    public int f = 15;
    protected int g = 0;
    protected byte[] h = new byte[this.b];
    protected int i = 8001;
    protected int j = 0;
    protected int k = 3;
    protected int l = 0;
    protected int m = 0;
    protected int n = 2;
    protected int o = 0;
    protected int p = 0;
    InetSocketAddress q = null;
    int r = 0;
    byte[] s = new byte[6144];
    protected int t = 0;
    protected int u = 0;
    protected String v = "";
    byte w;
    public u x;
    protected EncryptionMethod y = EncryptionMethod.EM_ECDH;
    protected boolean z = false;

    public String a(boolean z) {
        if (z) {
            return D[((int) (Math.random() * 2.147483647E9d)) % D.length];
        }
        return C[((int) (Math.random() * 2.147483647E9d)) % C.length];
    }

    public static void a(int i, String str) {
        F = i;
        G = str;
    }

    private void a(int i, int i2, long j, int i3, int i4, int i5, int i6) {
        int i7;
        if (this.z) {
            i7 = Opcodes.FLOAT_TO_INT;
        } else {
            i7 = 7;
        }
        a(i, i2, j, i7, i3, i4, i5, i6);
    }

    private void b(int i, int i2, long j, int i3, int i4, int i5, int i6) {
        a(i, i2, j, 69, i3, i4, i5, i6);
    }

    private void a(int i, int i2, long j, int i3, int i4, int i5, int i6, int i7) {
        int i8 = this.j + 1;
        this.j = i8;
        this.c = 0;
        util.int8_to_buf(this.h, this.c, 2);
        this.c++;
        util.int16_to_buf(this.h, this.c, (this.d + 2) + i7);
        this.c += 2;
        util.int16_to_buf(this.h, this.c, i);
        this.c += 2;
        util.int16_to_buf(this.h, this.c, i2);
        this.c += 2;
        util.int16_to_buf(this.h, this.c, i8);
        this.c += 2;
        util.int32_to_buf(this.h, this.c, (int) j);
        this.c += 4;
        util.int8_to_buf(this.h, this.c, 3);
        this.c++;
        util.int8_to_buf(this.h, this.c, i3);
        this.c++;
        util.int8_to_buf(this.h, this.c, i4);
        this.c++;
        util.int32_to_buf(this.h, this.c, 2);
        this.c += 4;
        util.int32_to_buf(this.h, this.c, i5);
        this.c += 4;
        util.int32_to_buf(this.h, this.c, i6);
        this.c += 4;
    }

    public void a(int i, int i2, int i3, long j, int i4, int i5, int i6, int i7, int i8) {
        int i9 = this.j + 1;
        this.j = i9;
        this.c = 0;
        util.int8_to_buf(this.h, this.c, 2);
        this.c++;
        util.int16_to_buf(this.h, this.c, (this.d + 2) + i8);
        this.c += 2;
        util.int16_to_buf(this.h, this.c, i);
        this.c += 2;
        util.int16_to_buf(this.h, this.c, i2);
        this.c += 2;
        util.int16_to_buf(this.h, this.c, i9);
        this.c += 2;
        util.int32_to_buf(this.h, this.c, (int) j);
        this.c += 4;
        util.int8_to_buf(this.h, this.c, 3);
        this.c++;
        util.int8_to_buf(this.h, this.c, 7);
        this.c++;
        util.int8_to_buf(this.h, this.c, i4);
        this.c++;
        util.int32_to_buf(this.h, this.c, i5);
        this.c += 4;
        util.int32_to_buf(this.h, this.c, i6);
        this.c += 4;
        util.int32_to_buf(this.h, this.c, i7);
        this.c += 4;
    }

    public void a() {
        util.int8_to_buf(this.h, this.c, 3);
        this.c++;
    }

    public void a(byte[] bArr, int i) {
        if ((this.c + i) + 1 > this.b) {
            this.b = ((this.c + i) + 1) + 128;
            Object obj = new byte[this.b];
            System.arraycopy(this.h, 0, obj, 0, this.c);
            this.h = obj;
        }
        System.arraycopy(bArr, 0, this.h, this.c, i);
        this.c += i;
    }

    public void a(int i, int i2, int i3, long j, int i4, int i5, int i6, int i7, byte[] bArr, int i8) {
        a(i, i2, i3, j, i4, i5, i6, i7, i8);
        a(bArr, i8);
        a();
    }

    public void a(int i, int i2, int i3, long j, int i4, int i5, int i6, int i7, byte[] bArr) {
        a(i, i2, i3, j, i4, i5, i6, i7, bArr, bArr.length);
    }

    private void a(int i, int i2, long j, int i3, int i4, int i5, byte[] bArr) {
        a(i, i2, j, i3, i4, i5, bArr.length);
        a(bArr, bArr.length);
        a();
    }

    private void b(int i, int i2, long j, int i3, int i4, int i5, byte[] bArr) {
        b(i, i2, j, i3, i4, i5, bArr.length);
        a(bArr, bArr.length);
        a();
    }

    public void a(long j, byte[] bArr) {
        a(this.i, this.t, j, this.m, u.w, this.p, bArr);
    }

    public void b(long j, byte[] bArr) {
        b(this.i, this.t, j, this.m, u.w, this.p, bArr);
    }

    public void a(long j, byte[] bArr, EncryptionMethod encryptionMethod) {
        if (encryptionMethod == EncryptionMethod.EM_ST) {
            b(j, bArr);
        } else if (encryptionMethod == EncryptionMethod.EM_ECDH) {
            a(j, bArr);
        } else {
            util.LOGI("getRequestEncrptedPackage unknown encryption method " + encryptionMethod, "" + j);
        }
    }

    public int b() {
        int i = this.c;
        if (i <= this.f + 2) {
            return -1009;
        }
        int a;
        this.g = (i - this.f) - 2;
        i = util.buf_to_int16(this.h, 13);
        util.LOGI("enrypt method " + this.y + " rsp flag " + i, "");
        if (this.y == EncryptionMethod.EM_ECDH && i == 0) {
            if (this.x.m == 0) {
                a = a(this.h, this.f + 1, this.g, this.x.p);
                if (a < 0) {
                    util.LOGI("use ecdh decrypt_body failed", "");
                    a = a(this.h, this.f + 1, this.g, this.x.c);
                    if (a < 0) {
                        util.LOGI("use kc decrypt_body failed", "");
                    }
                }
            } else {
                a = a(this.h, this.f + 1, this.g, this.x.c);
                if (a < 0) {
                    util.LOGI("use kc decrypt_body failed", "");
                }
            }
        } else if (this.y == EncryptionMethod.EM_ST && 3 == i) {
            a = a(this.h, this.f + 1, this.g, this.B);
            if (a < 0) {
                util.LOGI("use session key decrypt_body failed", "");
            }
        } else if (this.y == EncryptionMethod.EM_ECDH && true == this.z && 4 == i) {
            Object decrypt = cryptor.decrypt(this.h, this.f + 1, this.g, this.x.p);
            if (decrypt == null) {
                util.LOGI("decrypted outer body failed", "" + this.x.f);
                return -1002;
            }
            int buf_to_int16 = util.buf_to_int16(decrypt, 0);
            if (buf_to_int16 > decrypt.length - 2) {
                util.LOGI("peer public key len wrong " + buf_to_int16, "" + this.x.f);
                return -1009;
            }
            Object obj = new byte[buf_to_int16];
            System.arraycopy(decrypt, 2, obj, 0, buf_to_int16);
            byte[] calShareKeyMd5ByPeerPublicKey = new EcdhCrypt(this.a).calShareKeyMd5ByPeerPublicKey(obj);
            if (calShareKeyMd5ByPeerPublicKey == null || calShareKeyMd5ByPeerPublicKey.length == 0) {
                return -1002;
            }
            obj = cryptor.decrypt(decrypt, buf_to_int16 + 2, (decrypt.length - 2) - buf_to_int16, calShareKeyMd5ByPeerPublicKey);
            if (obj == null) {
                u.al.attr_api(2461266);
                util.LOGI("use share key md5 decrypt failed", "" + this.x.f);
                return -1002;
            }
            if (this.h.length < (this.f + 2) + obj.length) {
                this.b = (this.f + 2) + obj.length;
                Object obj2 = new byte[this.b];
                System.arraycopy(this.h, 0, obj2, 0, this.f + 1);
                this.h = obj2;
            }
            System.arraycopy(obj, 0, this.h, this.f + 1, obj.length);
            this.c = (obj.length + (this.f + 2)) + this.c;
            a = 0;
        } else {
            util.LOGI("unknown encryption method " + this.y, "");
            a = util.E_ENCRYPTION_METHOD;
        }
        if (a >= 0) {
            return d(this.h, this.f + 1, this.g);
        }
        return a;
    }

    public void b(byte[] bArr, int i) {
        if (i > this.b) {
            this.b = i + 128;
            this.h = new byte[this.b];
        }
        this.c = i;
        System.arraycopy(bArr, 0, this.h, 0, i);
    }

    public byte[] c() {
        Object obj = new byte[this.c];
        System.arraycopy(this.h, 0, obj, 0, this.c);
        return obj;
    }

    public int a(byte[] bArr, int i, int i2, byte[] bArr2) {
        Object decrypt = cryptor.decrypt(bArr, i, i2, bArr2);
        if (decrypt == null) {
            return -1002;
        }
        this.g = decrypt.length;
        if ((decrypt.length + this.f) + 2 > this.b) {
            this.b = (decrypt.length + this.f) + 2;
            Object obj = new byte[this.b];
            System.arraycopy(this.h, 0, obj, 0, this.c);
            this.h = obj;
        }
        this.c = 0;
        System.arraycopy(decrypt, 0, this.h, i, decrypt.length);
        this.c = (decrypt.length + (this.f + 2)) + this.c;
        return 0;
    }

    byte[] a(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
        if (bArr == null || bArr2 == null || bArr3 == null || bArr4 == null) {
            return new byte[0];
        }
        Object encrypt = cryptor.encrypt(bArr, 0, bArr.length, bArr4);
        byte[] bArr5 = new byte[(((((bArr2.length + 2) + 2) + 2) + bArr3.length) + encrypt.length)];
        util.int8_to_buf(bArr5, 0, 1);
        util.int8_to_buf(bArr5, 1, 1);
        System.arraycopy(bArr2, 0, bArr5, 2, bArr2.length);
        int length = bArr2.length + 2;
        util.int16_to_buf(bArr5, length, VoiceWakeuperAidl.RES_SPECIFIED);
        length += 2;
        util.int16_to_buf(bArr5, length, bArr3.length);
        length += 2;
        System.arraycopy(bArr3, 0, bArr5, length, bArr3.length);
        length += bArr3.length;
        System.arraycopy(encrypt, 0, bArr5, length, encrypt.length);
        int length2 = encrypt.length + length;
        return bArr5;
    }

    byte[] a(byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr2 == null) {
            return new byte[0];
        }
        int i = this.x.m == 2 ? 3 : 2;
        Object encrypt = cryptor.encrypt(bArr, 0, bArr.length, bArr2);
        Object obj = new byte[((((bArr2.length + 2) + 2) + 2) + encrypt.length)];
        util.int8_to_buf(obj, 0, 1);
        util.int8_to_buf(obj, 1, i);
        System.arraycopy(bArr2, 0, obj, 2, bArr2.length);
        i = bArr2.length + 2;
        util.int16_to_buf(obj, i, VoiceWakeuperAidl.RES_SPECIFIED);
        i += 2;
        util.int16_to_buf(obj, i, 0);
        i += 2;
        System.arraycopy(encrypt, 0, obj, i, encrypt.length);
        i += encrypt.length;
        return obj;
    }

    byte[] a(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        Object encrypt = cryptor.encrypt(bArr, 0, bArr.length, bArr3);
        Object obj = new byte[((bArr2.length + 2) + encrypt.length)];
        util.int16_to_buf(obj, 0, bArr2.length);
        System.arraycopy(bArr2, 0, obj, 2, bArr2.length);
        int length = bArr2.length + 2;
        System.arraycopy(encrypt, 0, obj, length, encrypt.length);
        int length2 = encrypt.length + length;
        return obj;
    }

    byte[] a(byte[] bArr, int i, int i2) {
        Object obj = new byte[(bArr.length + 4)];
        util.int16_to_buf(obj, 0, i);
        util.int16_to_buf(obj, 2, i2);
        System.arraycopy(bArr, 0, obj, 4, bArr.length);
        return obj;
    }

    byte[] b(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[(bArr.length + 4)];
        util.int16_to_buf(bArr2, 0, i);
        util.int16_to_buf(bArr2, 2, i2);
        System.arraycopy(bArr, 0, bArr2, 4, bArr.length);
        if (this.x.m == 0) {
            return a(bArr2, this.x.c, this.x.n, this.x.p);
        }
        return a(bArr2, this.x.c);
    }

    protected byte[] a(byte[] bArr, EncryptionMethod encryptionMethod, byte[] bArr2, byte[] bArr3) {
        if (EncryptionMethod.EM_ST == encryptionMethod) {
            return b(bArr, bArr2, bArr3);
        }
        if (EncryptionMethod.EM_ECDH == encryptionMethod) {
            return a(bArr);
        }
        util.LOGI("encryptBody unknown encryption method " + encryptionMethod, "");
        return null;
    }

    protected byte[] a(byte[] bArr) {
        if (this.x.m == 0) {
            return a(bArr, this.x.c, this.x.n, this.x.p);
        }
        return a(bArr, this.x.c);
    }

    protected byte[] b(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        return a(bArr, bArr2, bArr3);
    }

    public Socket d() {
        return this.x.ah;
    }

    public void a(Socket socket) {
        this.x.ah = socket;
    }

    public String b(boolean z) {
        String[] strArr = new String[2];
        if (z) {
            strArr[0] = "wlogin.qq.com";
            strArr[1] = "wlogin1.qq.com";
        } else {
            strArr[0] = "wtlogin.qq.com";
            strArr[1] = "wtlogin1.qq.com";
        }
        return strArr[Math.abs(new Random().nextInt() % strArr.length)];
    }

    public int c(boolean z) {
        return z ? 443 : 443;
    }

    public String a(int i, boolean z) {
        String str = "";
        int i2 = i / 2;
        if (F != 0 && G != null && G.length() > 0) {
            str = G;
        } else if (i2 < 1) {
            if (z) {
                if (u.D == 1) {
                    str = new String(util.get_wap_server_host1(u.t));
                } else if (u.D == 2) {
                    str = new String(util.get_wap_server_host2(u.t));
                }
            } else if (u.D == 1) {
                str = new String(util.get_server_host1(u.t));
            } else if (u.D == 2) {
                str = new String(util.get_server_host2(u.t));
            }
            if (str.length() <= 0) {
                str = b(z);
            }
        } else if (i2 < 2) {
            str = b(z);
        } else {
            str = a(z);
        }
        H = str;
        return str;
    }

    public int a(String str, boolean z, WUserSigInfo wUserSigInfo) {
        int b;
        if (this.x.e()) {
            b = b(str, z, wUserSigInfo);
        } else {
            b = e();
        }
        if (b == -1000) {
            ErrMsg errMsg = new ErrMsg();
            errMsg.setMessage(InternationMsg.a(MSG_TYPE.MSG_4));
            a(errMsg);
        }
        return b;
    }

    public int b(String str, boolean z, WUserSigInfo wUserSigInfo) {
        Exception exception;
        util.LOGI(getClass().getName() + ":snd_rcv_req_msf ...", "" + this.x.f);
        int i = this.x.l;
        byte[] c = c();
        long currentTimeMillis = System.currentTimeMillis();
        int i2;
        byte[] bArr;
        report_t3 oicq_wlogin_sdk_report_report_t3;
        try {
            util.LOGI("WtloginMsfListener uin:" + str + " service_cmd:" + this.v + " timeout:" + i + " flag:" + z, "" + this.x.f);
            WtloginMsfListener wtloginMsfListener = new WtloginMsfListener(str, this.v, c, i, z, wUserSigInfo);
            Thread thread = new Thread(wtloginMsfListener);
            thread.start();
            thread.join((long) i);
            byte[] retData = wtloginMsfListener.getRetData();
            byte[] bArr2;
            if (retData == null) {
                try {
                    util.LOGI("recv data from server failed, ret=" + wtloginMsfListener.getRet(), "" + this.x.f);
                    bArr2 = retData;
                    i2 = -1000;
                    bArr = bArr2;
                } catch (Exception e) {
                    Exception exception2 = e;
                    bArr = retData;
                    exception = exception2;
                    util.printException(exception, "" + this.x.f);
                    i2 = -1000;
                    if (this.t != 2066) {
                        oicq_wlogin_sdk_report_report_t3 = new report_t3();
                        oicq_wlogin_sdk_report_report_t3._cmd = this.t;
                        oicq_wlogin_sdk_report_report_t3._sub = this.u;
                        oicq_wlogin_sdk_report_report_t3._rst2 = i2;
                        oicq_wlogin_sdk_report_report_t3._used = (int) (System.currentTimeMillis() - currentTimeMillis);
                        oicq_wlogin_sdk_report_report_t3._try = 0;
                        oicq_wlogin_sdk_report_report_t3._host = "";
                        oicq_wlogin_sdk_report_report_t3._ip = "";
                        oicq_wlogin_sdk_report_report_t3._port = 0;
                        oicq_wlogin_sdk_report_report_t3._conn = 0;
                        oicq_wlogin_sdk_report_report_t3._net = 0;
                        oicq_wlogin_sdk_report_report_t3._str = "";
                        if (i2 != 0) {
                            oicq_wlogin_sdk_report_report_t3._slen = c.length;
                            oicq_wlogin_sdk_report_report_t3._rlen = bArr.length;
                        } else {
                            oicq_wlogin_sdk_report_report_t3._slen = 0;
                            oicq_wlogin_sdk_report_report_t3._rlen = 0;
                        }
                        oicq_wlogin_sdk_report_report_t3._wap = 3;
                        u.al.add_t3(oicq_wlogin_sdk_report_report_t3);
                    }
                    util.LOGI(getClass().getName() + ":snd_rcv_req_msf ret=" + i2, "" + this.x.f);
                    return i2;
                }
                if (this.t != 2066) {
                    oicq_wlogin_sdk_report_report_t3 = new report_t3();
                    oicq_wlogin_sdk_report_report_t3._cmd = this.t;
                    oicq_wlogin_sdk_report_report_t3._sub = this.u;
                    oicq_wlogin_sdk_report_report_t3._rst2 = i2;
                    oicq_wlogin_sdk_report_report_t3._used = (int) (System.currentTimeMillis() - currentTimeMillis);
                    oicq_wlogin_sdk_report_report_t3._try = 0;
                    oicq_wlogin_sdk_report_report_t3._host = "";
                    oicq_wlogin_sdk_report_report_t3._ip = "";
                    oicq_wlogin_sdk_report_report_t3._port = 0;
                    oicq_wlogin_sdk_report_report_t3._conn = 0;
                    oicq_wlogin_sdk_report_report_t3._net = 0;
                    oicq_wlogin_sdk_report_report_t3._str = "";
                    if (i2 != 0) {
                        oicq_wlogin_sdk_report_report_t3._slen = c.length;
                        oicq_wlogin_sdk_report_report_t3._rlen = bArr.length;
                    } else {
                        oicq_wlogin_sdk_report_report_t3._slen = 0;
                        oicq_wlogin_sdk_report_report_t3._rlen = 0;
                    }
                    oicq_wlogin_sdk_report_report_t3._wap = 3;
                    u.al.add_t3(oicq_wlogin_sdk_report_report_t3);
                }
                util.LOGI(getClass().getName() + ":snd_rcv_req_msf ret=" + i2, "" + this.x.f);
                return i2;
            }
            b(retData, retData.length);
            bArr2 = retData;
            i2 = 0;
            bArr = bArr2;
            if (this.t != 2066) {
                oicq_wlogin_sdk_report_report_t3 = new report_t3();
                oicq_wlogin_sdk_report_report_t3._cmd = this.t;
                oicq_wlogin_sdk_report_report_t3._sub = this.u;
                oicq_wlogin_sdk_report_report_t3._rst2 = i2;
                oicq_wlogin_sdk_report_report_t3._used = (int) (System.currentTimeMillis() - currentTimeMillis);
                oicq_wlogin_sdk_report_report_t3._try = 0;
                oicq_wlogin_sdk_report_report_t3._host = "";
                oicq_wlogin_sdk_report_report_t3._ip = "";
                oicq_wlogin_sdk_report_report_t3._port = 0;
                oicq_wlogin_sdk_report_report_t3._conn = 0;
                oicq_wlogin_sdk_report_report_t3._net = 0;
                oicq_wlogin_sdk_report_report_t3._str = "";
                if (i2 != 0) {
                    oicq_wlogin_sdk_report_report_t3._slen = 0;
                    oicq_wlogin_sdk_report_report_t3._rlen = 0;
                } else {
                    oicq_wlogin_sdk_report_report_t3._slen = c.length;
                    oicq_wlogin_sdk_report_report_t3._rlen = bArr.length;
                }
                oicq_wlogin_sdk_report_report_t3._wap = 3;
                u.al.add_t3(oicq_wlogin_sdk_report_report_t3);
            }
            util.LOGI(getClass().getName() + ":snd_rcv_req_msf ret=" + i2, "" + this.x.f);
            return i2;
        } catch (Exception e2) {
            exception = e2;
            bArr = null;
            util.printException(exception, "" + this.x.f);
            i2 = -1000;
            if (this.t != 2066) {
                oicq_wlogin_sdk_report_report_t3 = new report_t3();
                oicq_wlogin_sdk_report_report_t3._cmd = this.t;
                oicq_wlogin_sdk_report_report_t3._sub = this.u;
                oicq_wlogin_sdk_report_report_t3._rst2 = i2;
                oicq_wlogin_sdk_report_report_t3._used = (int) (System.currentTimeMillis() - currentTimeMillis);
                oicq_wlogin_sdk_report_report_t3._try = 0;
                oicq_wlogin_sdk_report_report_t3._host = "";
                oicq_wlogin_sdk_report_report_t3._ip = "";
                oicq_wlogin_sdk_report_report_t3._port = 0;
                oicq_wlogin_sdk_report_report_t3._conn = 0;
                oicq_wlogin_sdk_report_report_t3._net = 0;
                oicq_wlogin_sdk_report_report_t3._str = "";
                if (i2 != 0) {
                    oicq_wlogin_sdk_report_report_t3._slen = 0;
                    oicq_wlogin_sdk_report_report_t3._rlen = 0;
                } else {
                    oicq_wlogin_sdk_report_report_t3._slen = c.length;
                    oicq_wlogin_sdk_report_report_t3._rlen = bArr.length;
                }
                oicq_wlogin_sdk_report_report_t3._wap = 3;
                u.al.add_t3(oicq_wlogin_sdk_report_report_t3);
            }
            util.LOGI(getClass().getName() + ":snd_rcv_req_msf ret=" + i2, "" + this.x.f);
            return i2;
        }
    }

    public int b(byte[] bArr) {
        return util.buf_to_int16(bArr, 1);
    }

    private void a(int i, long j, int i2, int i3, boolean z, boolean z2) {
        report_t3 oicq_wlogin_sdk_report_report_t3 = new report_t3();
        oicq_wlogin_sdk_report_report_t3._cmd = this.t;
        oicq_wlogin_sdk_report_report_t3._sub = this.u;
        oicq_wlogin_sdk_report_report_t3._rst2 = i;
        oicq_wlogin_sdk_report_report_t3._used = (int) (System.currentTimeMillis() - j);
        oicq_wlogin_sdk_report_report_t3._try = i3;
        oicq_wlogin_sdk_report_report_t3._host = H;
        if (oicq_wlogin_sdk_report_report_t3._host == null) {
            oicq_wlogin_sdk_report_report_t3._host = "";
        }
        if (this.q == null) {
            oicq_wlogin_sdk_report_report_t3._ip = "";
        } else {
            oicq_wlogin_sdk_report_report_t3._ip = this.q.getAddress().getHostAddress();
        }
        oicq_wlogin_sdk_report_report_t3._port = c(z);
        oicq_wlogin_sdk_report_report_t3._conn = i2;
        oicq_wlogin_sdk_report_report_t3._net = u.D;
        oicq_wlogin_sdk_report_report_t3._str = "";
        oicq_wlogin_sdk_report_report_t3._slen = 0;
        oicq_wlogin_sdk_report_report_t3._rlen = 0;
        if (!z) {
            oicq_wlogin_sdk_report_report_t3._wap = 0;
        } else if (z2) {
            oicq_wlogin_sdk_report_report_t3._wap = 2;
        } else {
            oicq_wlogin_sdk_report_report_t3._wap = 1;
        }
        u.al.add_t3(oicq_wlogin_sdk_report_report_t3);
    }

    public int e() {
        int i;
        Socket socket;
        util.LOGI(getClass().getName() + ":snd_rcv_req_tcp ...", "" + this.x.f);
        byte[] c = c();
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        long j = 0;
        boolean z = false;
        boolean z2 = false;
        int i5 = 0;
        String str = "";
        String str2 = "";
        Socket d = d();
        while (i4 < 6) {
            String a;
            int responseCode;
            InputStream inputStream;
            if (i4 != 0) {
                util.chg_retry_type(u.t);
            }
            z = util.is_wap_retry(u.t);
            this.q = null;
            if (!(i4 == 0 || this.t == 2066)) {
                a(i3, j, i5, i4, z, z2);
            }
            j = System.currentTimeMillis();
            String str3;
            String str4;
            if (z) {
                util.LOGI("try http connect " + i4 + " ...", "" + this.x.f);
                str3 = "";
                a = a(i4, z);
                try {
                    String str5;
                    URL url;
                    z2 = util.is_wap_proxy_retry(u.t);
                    if (z2) {
                        str4 = util.get_proxy_ip();
                        i = util.get_proxy_port();
                        if (str4 == null || str4.length() <= 0 || i == -1) {
                            z2 = false;
                            util.LOGI("proxy_ip=" + str4 + ",proxy_port=" + i + ",set is_wap_proxy_retry=" + false, "" + this.x.f);
                        }
                        str5 = str4;
                        i3 = i;
                    } else {
                        str5 = null;
                        i3 = -1;
                    }
                    if (z2) {
                        url = new URL("http://" + str5 + ":" + i3 + "/cgi-bin/wlogin_proxy_login");
                        str4 = str2;
                    } else {
                        this.q = a.a(a, 0, (long) this.x.l);
                        if (this.q != null) {
                            str3 = this.q.getAddress().getHostAddress();
                            if (str2.equals(str3)) {
                                throw new Exception("repeated failed http ip " + str2);
                            }
                            try {
                                this.q = null;
                                str2 = str3;
                            } catch (Exception e) {
                                i3 = -1000;
                                i4++;
                                str2 = str3;
                            }
                        } else {
                            str3 = str2;
                            str2 = a;
                        }
                        URL url2 = new URL("http://" + str2 + "/cgi-bin/wlogin_proxy_login");
                        str4 = str3;
                        url = url2;
                    }
                    try {
                        util.LOGI("try http proxy=" + z2 + " connect to " + url + " host " + a, "" + this.x.f);
                        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                        httpURLConnection.setRequestMethod(Constants.HTTP_POST);
                        if (z2) {
                            httpURLConnection.setRequestProperty("X-Online-Host", a);
                        }
                        httpURLConnection.setRequestProperty("Content-Type", "application/octet-stream");
                        httpURLConnection.setRequestProperty("Content-Disposition", "attachment; filename=micromsgresp.dat");
                        httpURLConnection.setRequestProperty("Content-Length", String.valueOf(c.length));
                        httpURLConnection.setConnectTimeout(this.x.l);
                        httpURLConnection.setReadTimeout(this.x.l);
                        httpURLConnection.setDoOutput(true);
                        httpURLConnection.setDoInput(true);
                        util.LOGI("http request connect ...", "" + this.x.f);
                        if (j.a(httpURLConnection, (long) this.x.l)) {
                            util.LOGI("http request write ...", "" + this.x.f);
                            OutputStream outputStream = httpURLConnection.getOutputStream();
                            outputStream.write(c, 0, c.length);
                            outputStream.flush();
                            responseCode = httpURLConnection.getResponseCode();
                            util.LOGI("http request response code=" + responseCode, "" + this.x.f);
                            if (200 != responseCode) {
                                i4++;
                                str2 = str4;
                                i3 = -1000;
                            } else {
                                a = str;
                                inputStream = httpURLConnection.getInputStream();
                                socket = d;
                                str = str4;
                            }
                        } else {
                            util.LOGI("http request connect failed", "" + this.x.f);
                            i4++;
                            str2 = str4;
                            i3 = -1000;
                        }
                    } catch (Exception e2) {
                        str3 = str4;
                        i3 = -1000;
                        i4++;
                        str2 = str3;
                    }
                } catch (Exception e3) {
                    str3 = str2;
                    i3 = -1000;
                    i4++;
                    str2 = str3;
                }
            } else {
                util.LOGI("try bin connect " + i4 + " ...", "" + this.x.f);
                if (d == null) {
                    str3 = "";
                    i5 = 1;
                    if (this.q == null) {
                        str3 = a(i4, z);
                        util.LOGI("DNS for " + str3 + " request ...", "" + this.x.f);
                        try {
                            this.r = c(z);
                            this.q = a.a(str3, this.r, (long) this.x.l);
                        } catch (Exception e4) {
                        }
                    }
                    if (this.q != null && this.q.getAddress() == null) {
                        if (this.q.getAddress() == null) {
                            util.LOGI("_server_ip get address failed", "" + this.x.f);
                        }
                        i3 = util.E_RESOLVE_ADDR;
                        i4++;
                        this.q = null;
                        a(null);
                        d = null;
                    } else if (this.q == null || str.equals(this.q.getAddress().getHostAddress())) {
                        if (this.q == null) {
                            util.LOGI("DNS for " + str3 + " request failed", "" + this.x.f);
                            i = util.E_RESOLVE_ADDR;
                        } else {
                            util.LOGI("repeated failed bin ip " + str, "" + this.x.f);
                            i = -1000;
                        }
                        i4++;
                        this.q = null;
                        d = null;
                        a(null);
                        i3 = i;
                    } else {
                        str4 = this.q.getAddress().getHostAddress();
                        util.LOGI("DNS for " + str3 + "(" + this.q.toString() + ") request OK", "" + this.x.f);
                    }
                } else {
                    str4 = str;
                }
                if (d == null) {
                    try {
                        util.LOGI("tcp connect to " + this.q + " request ...", "" + this.x.f);
                        socket = new Socket();
                        a(socket);
                        socket.connect(this.q, this.x.l);
                        socket.setSoTimeout(this.x.l);
                        socket.setReceiveBufferSize(this.s.length);
                        util.LOGI("tcp connect to " + this.q + " OK", "" + this.x.f);
                    } catch (Throwable th) {
                        i4++;
                        this.q = null;
                        a(null);
                        str = str4;
                        i3 = -1000;
                        d = null;
                    }
                } else {
                    socket = d;
                }
                util.LOGI("tcp request write ...", "" + this.x.f);
                OutputStream outputStream2 = socket.getOutputStream();
                outputStream2.write(c, 0, c.length);
                outputStream2.flush();
                str = str2;
                a = str4;
                inputStream = socket.getInputStream();
            }
            try {
                util.LOGI("recv data from server ...", "" + this.x.f);
                responseCode = 0;
                i3 = 0;
                while (i3 < this.f + 1) {
                    responseCode = inputStream.read(this.s, i3, (this.f + 1) - i3);
                    if (responseCode < 0) {
                        break;
                    }
                    i3 += responseCode;
                }
                if (responseCode < 0) {
                    i3 = -1000;
                    i4++;
                    if (!z) {
                        socket.close();
                        this.q = null;
                        socket = null;
                        a(null);
                    }
                    str2 = str;
                    d = socket;
                    str = a;
                } else {
                    i3 = b(this.s);
                    try {
                        if (i3 > this.f + 1) {
                            if (i3 < this.s.length) {
                                int i6 = this.f + 1;
                                i2 = i3 - i6;
                                while (i2 > 0) {
                                    responseCode = inputStream.read(this.s, i6, i2);
                                    if (responseCode == -1) {
                                        break;
                                    }
                                    i6 += responseCode;
                                    i2 -= responseCode;
                                }
                                if (responseCode != -1) {
                                    break;
                                }
                                i4++;
                                if (!z) {
                                    socket.close();
                                    this.q = null;
                                    socket = null;
                                    a(null);
                                }
                                d = socket;
                                i2 = i3;
                                i3 = -1000;
                                str2 = str;
                                str = a;
                            } else {
                                i4++;
                                if (!z) {
                                    socket.close();
                                    this.q = null;
                                    socket = null;
                                    a(null);
                                }
                                d = socket;
                                i2 = i3;
                                i3 = -1000;
                                str2 = str;
                                str = a;
                            }
                        } else {
                            i4++;
                            if (!z) {
                                socket.close();
                                this.q = null;
                                socket = null;
                                a(null);
                            }
                            d = socket;
                            i2 = i3;
                            i3 = -1000;
                            str2 = str;
                            str = a;
                        }
                    } catch (Throwable th2) {
                        i4++;
                        if (!z) {
                            try {
                                if (socket.isConnected()) {
                                    socket.close();
                                }
                            } catch (Exception e5) {
                            }
                            this.q = null;
                            socket = null;
                            a(null);
                        }
                        d = socket;
                        i2 = i3;
                        i3 = -1000;
                        str2 = str;
                        str = a;
                    }
                }
            } catch (Throwable th3) {
                i3 = i2;
                i4++;
                if (z) {
                    if (socket.isConnected()) {
                        socket.close();
                    }
                    this.q = null;
                    socket = null;
                    a(null);
                }
                d = socket;
                i2 = i3;
                i3 = -1000;
                str2 = str;
                str = a;
            }
        }
        i3 = i2;
        if (i4 >= 6) {
            i = -1000;
        } else {
            i = 0;
        }
        if (i == 0) {
            b(this.s, i3);
        }
        if (i == 0 && this.t != 2066) {
            report_t3 oicq_wlogin_sdk_report_report_t3 = new report_t3();
            oicq_wlogin_sdk_report_report_t3._cmd = this.t;
            oicq_wlogin_sdk_report_report_t3._sub = this.u;
            oicq_wlogin_sdk_report_report_t3._rst2 = i;
            oicq_wlogin_sdk_report_report_t3._used = (int) (System.currentTimeMillis() - j);
            oicq_wlogin_sdk_report_report_t3._try = i4;
            oicq_wlogin_sdk_report_report_t3._host = H;
            if (oicq_wlogin_sdk_report_report_t3._host == null) {
                oicq_wlogin_sdk_report_report_t3._host = "";
            }
            if (this.q == null) {
                oicq_wlogin_sdk_report_report_t3._ip = "";
            } else {
                oicq_wlogin_sdk_report_report_t3._ip = this.q.getAddress().getHostAddress();
            }
            oicq_wlogin_sdk_report_report_t3._port = this.r;
            oicq_wlogin_sdk_report_report_t3._conn = i5;
            oicq_wlogin_sdk_report_report_t3._net = u.D;
            oicq_wlogin_sdk_report_report_t3._str = "";
            oicq_wlogin_sdk_report_report_t3._slen = c.length;
            oicq_wlogin_sdk_report_report_t3._rlen = i3;
            if (!z) {
                oicq_wlogin_sdk_report_report_t3._wap = 0;
            } else if (z2) {
                oicq_wlogin_sdk_report_report_t3._wap = 2;
            } else {
                oicq_wlogin_sdk_report_report_t3._wap = 1;
            }
            u.al.add_t3(oicq_wlogin_sdk_report_report_t3);
        }
        util.LOGI(getClass().getName() + ":snd_rcv_req_tcp ret=" + i, "" + this.x.f);
        return i;
    }

    public int c(byte[] bArr, int i) {
        this.w = bArr[i];
        return bArr[i] & 255;
    }

    public void c(byte[] bArr, int i, int i2) {
        aq aqVar = new aq();
        int c = aqVar.c(bArr, i, i2);
        async_context b = u.b(this.x.h);
        if (c >= 0) {
            b._last_err_msg.setTitle(new String(aqVar.a()));
            b._last_err_msg.setMessage(new String(aqVar.g()));
            b._last_err_msg.setType(aqVar.h());
            b._last_err_msg.setOtherinfo(new String(aqVar.i()));
        }
    }

    public void a(ErrMsg errMsg) {
        async_context b = u.b(this.x.h);
        b._last_err_msg = new ErrMsg(0, "", "", "");
        if (errMsg != null) {
            try {
                b._last_err_msg = (ErrMsg) errMsg.clone();
            } catch (CloneNotSupportedException e) {
                b._last_err_msg = new ErrMsg(0, "", "", "");
            }
        }
    }

    public void a(at atVar) {
        try {
            ErrMsg errMsg = new ErrMsg();
            if (atVar != null) {
                errMsg.setType(atVar.a());
                errMsg.setTitle(new String(atVar.g()));
                errMsg.setMessage(new String(atVar.h()));
                errMsg.setOtherinfo(new String(atVar.i()));
                new e(u.t, errMsg).start();
            }
        } catch (Exception e) {
        }
    }

    public static byte[] b(byte[] bArr, byte[] bArr2) {
        Object obj = new byte[(bArr.length + bArr2.length)];
        System.arraycopy(bArr, 0, obj, 0, bArr.length);
        System.arraycopy(bArr2, 0, obj, bArr.length, bArr2.length);
        return obj;
    }

    public byte[] c(byte[] bArr) {
        Object decrypt;
        Object obj;
        String str = "%4;7t>;28<fc.5*6";
        if (u.B == null || u.B.length <= 0) {
            decrypt = cryptor.decrypt(bArr, 0, bArr.length, str.getBytes());
        } else {
            Object obj2 = new byte[16];
            if (u.B.length > obj2.length) {
                System.arraycopy(u.B, 0, obj2, 0, obj2.length);
            } else {
                System.arraycopy(u.B, 0, obj2, 0, u.B.length);
                for (int length = u.B.length; length < obj2.length; length++) {
                    obj2[length] = (byte) (length + 1);
                }
            }
            decrypt = cryptor.decrypt(bArr, 0, bArr.length, obj2);
            if (decrypt == null || decrypt.length <= 0) {
                decrypt = cryptor.decrypt(bArr, 0, bArr.length, str.getBytes());
            }
        }
        if (decrypt == null) {
            obj = (byte[]) bArr.clone();
        } else {
            obj = decrypt;
        }
        if (obj == null || obj.length < 16) {
            return (byte[]) null;
        }
        int length2 = obj.length - 16;
        byte[] bArr2 = new byte[length2];
        System.arraycopy(obj, 0, bArr2, 0, length2);
        Object obj3 = new byte[16];
        System.arraycopy(obj, length2, obj3, 0, 16);
        u.b(this.x.h)._tgtgt_key = obj3;
        return bArr2;
    }

    public int a(int i) {
        u.b(this.x.h)._last_flowid = i;
        return i;
    }

    public int f() {
        return u.b(this.x.h)._last_flowid;
    }

    public byte[] c(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        if (bArr == null || bArr2 == null || bArr3 == null) {
            return new byte[16];
        }
        byte[] bArr4 = new byte[((bArr.length + bArr2.length) + bArr3.length)];
        System.arraycopy(bArr, 0, bArr4, 0, bArr.length);
        int length = bArr.length + 0;
        System.arraycopy(bArr2, 0, bArr4, length, bArr2.length);
        length += bArr2.length;
        System.arraycopy(bArr3, 0, bArr4, length, bArr3.length);
        length += bArr3.length;
        return MD5.toMD5Byte(bArr4);
    }

    public byte[] a(bc bcVar) {
        i iVar = new i();
        o oVar = new o();
        bd bdVar = new bd();
        ap apVar = new ap();
        byte[] c = bcVar.c();
        int length = c.length;
        if (iVar.c(c, 2, length) < 0 || oVar.c(c, 2, length) < 0 || bdVar.c(c, 2, length) < 0) {
            return null;
        }
        Object b = iVar.b();
        Object b2 = oVar.b();
        Object b3 = bdVar.b();
        Object a = apVar.a(u.A);
        byte[] bArr = new byte[((((b.length + 3) + b2.length) + b3.length) + a.length)];
        bArr[0] = (byte) 64;
        util.int16_to_buf(bArr, 1, 4);
        System.arraycopy(b, 0, bArr, 3, b.length);
        int length2 = b.length + 3;
        System.arraycopy(b2, 0, bArr, length2, b2.length);
        length2 += b2.length;
        System.arraycopy(b3, 0, bArr, length2, b3.length);
        length2 += b3.length;
        System.arraycopy(a, 0, bArr, length2, a.length);
        length2 += a.length;
        return bArr;
    }

    void a(int i, byte[] bArr, int i2) {
        if (bArr != null && bArr.length > 0) {
            if (i == 1) {
                if (u.D == 1) {
                    util.set_server_host1(u.t, bArr);
                } else if (u.D == 2) {
                    util.set_server_host2(u.t, bArr);
                }
            } else if (i == 2) {
                if (u.D == 1) {
                    util.set_wap_server_host1(u.t, bArr);
                } else if (u.D == 2) {
                    util.set_wap_server_host2(u.t, bArr);
                }
            }
            util.LOGI("net type:" + u.D + " type:" + i + " host:" + new String(bArr) + " port:" + i2, "" + this.x.f);
        }
    }

    public int a(bi biVar) {
        Object c = biVar.c();
        if (c != null && c.length > 2) {
            int buf_to_int8 = util.buf_to_int8(c, 1);
            int i = 2;
            for (int i2 = 0; i2 < buf_to_int8 && c.length >= i + 1; i2++) {
                int buf_to_int82 = util.buf_to_int8(c, i);
                i++;
                if (c.length < i + 2) {
                    break;
                }
                int buf_to_int16 = util.buf_to_int16(c, i);
                i += 2;
                if (c.length < i + buf_to_int16) {
                    break;
                }
                byte[] bArr = new byte[buf_to_int16];
                System.arraycopy(c, i, bArr, 0, buf_to_int16);
                i += buf_to_int16;
                if (c.length < i + 2) {
                    break;
                }
                buf_to_int16 = util.buf_to_int16(c, i);
                i += 2;
                a(buf_to_int82, bArr, buf_to_int16);
            }
        }
        return 0;
    }

    public int a(ax axVar) {
        bi biVar = new bi();
        bh bhVar = new bh();
        byte[] c = axVar.c();
        int length = c.length;
        if (biVar.c(c, 2, length) > 0) {
            a(biVar);
        }
        if (bhVar.c(c, 2, length) > 0) {
            this.x.m = 1;
            this.x.r = bhVar.c();
            util.LOGI("get rollback sig", "");
        }
        return 0;
    }

    public int d(byte[] bArr, int i, int i2) {
        if (i2 < 5) {
            return -1009;
        }
        int i3;
        int a;
        long j = 4294967295L;
        g gVar = new g();
        h hVar = new h();
        s sVar = new s();
        v vVar = new v();
        p pVar = new p();
        q qVar = new q();
        m mVar = new m();
        t tVar = new t();
        f fVar = new f();
        w wVar = new w();
        e eVar = new e();
        n nVar = new n();
        x xVar = new x();
        y yVar = new y();
        aa aaVar = new aa();
        ab abVar = new ab();
        ah ahVar = new ah();
        k kVar = new k();
        i iVar = new i();
        o oVar = new o();
        ad adVar = new ad();
        z zVar = new z();
        ak akVar = new ak();
        ai aiVar = new ai();
        at atVar = new at();
        au auVar = new au();
        an anVar = new an();
        ck ckVar = new ck();
        ay ayVar = new ay();
        az azVar = new az();
        bb bbVar = new bb();
        bd bdVar = new bd();
        bc bcVar = new bc();
        ax axVar = new ax();
        bg bgVar = new bg();
        b bVar = new b(1298);
        be beVar = new be();
        bj bjVar = new bj();
        bl blVar = new bl();
        bm bmVar = new bm();
        bq bqVar = new bq();
        br brVar = new br();
        ae aeVar = new ae();
        bt btVar = new bt();
        bu buVar = new bu();
        bx bxVar = new bx();
        cn cnVar = new cn();
        co coVar = new co();
        byte[] bArr2 = null;
        byte[] bArr3 = null;
        byte[] bArr4 = null;
        byte[] bArr5 = null;
        byte[] bArr6 = null;
        byte[] bArr7 = null;
        byte[] bArr8 = null;
        byte[] bArr9 = null;
        aj ajVar = new aj();
        async_context b = u.b(this.x.h);
        long j2 = b._sappid;
        long j3 = b._appid;
        if (this.t == 2064) {
            switch (this.u) {
                case 2:
                    i3 = 2;
                    break;
                case 4:
                    i3 = 3;
                    break;
                case 7:
                    i3 = 6;
                    break;
                case 9:
                    i3 = 0;
                    break;
                case 10:
                case 11:
                    i3 = 1;
                    break;
                case 13:
                    i3 = 4;
                    break;
                case 15:
                    i3 = 5;
                    break;
                case 17:
                case 19:
                    i3 = 0;
                    break;
                case 18:
                    i3 = 7;
                    break;
                case 20:
                    i3 = 0;
                    break;
                case 22:
                    i3 = 0;
                    break;
                default:
                    return -1012;
            }
        }
        i3 = 0;
        int c = c(bArr, i + 2);
        int i4 = i + 5;
        this.x.d = null;
        switch (c) {
            case 0:
                if (i3 == 1) {
                    if (this.x.b == null) {
                        return util.E_NO_TGTKEY;
                    }
                    if (auVar.c(bArr, i4, (this.c - i4) - 1) >= 0) {
                        this.x.d = auVar;
                    }
                    if (axVar.c(bArr, i4, (this.c - i4) - 1) >= 0) {
                        a(axVar);
                    }
                    a = vVar.a(bArr, i4, (this.c - i4) - 1, this.x.b);
                } else if (i3 == 2) {
                    if (buVar.c(bArr, i4, this.c - i4) >= 0) {
                        b._msalt = buVar.a();
                    }
                    if (f() == 3) {
                        if (sVar.c(bArr, i4, (this.c - i4) - 1) >= 0) {
                            this.x.f = sVar.a();
                            this.x.a(this.x.g, Long.valueOf(this.x.f));
                        }
                        if (gVar.c(bArr, i4, (this.c - i4) - 1) >= 0) {
                            b._t104 = gVar;
                        }
                        a = 0;
                        break;
                    }
                    if (auVar.c(bArr, i4, (this.c - i4) - 1) >= 0) {
                        this.x.d = auVar;
                    }
                    if (axVar.c(bArr, i4, (this.c - i4) - 1) >= 0) {
                        a(axVar);
                    }
                    a = vVar.a(bArr, i4, (this.c - i4) - 1, b._tgtgt_key);
                } else if (i3 == 3 || i3 == 7) {
                    if (buVar.c(bArr, i4, this.c - i4) >= 0) {
                        b._msalt = buVar.a();
                    }
                    if (sVar.c(bArr, i4, this.c - i4) >= 0) {
                        this.x.f = sVar.a();
                        this.x.a(this.x.g, Long.valueOf(this.x.f));
                    }
                    a = gVar.c(bArr, i4, this.c - i4);
                    if (a >= 0) {
                        b._t104 = gVar;
                        a = 0;
                        break;
                    }
                } else if (22 == this.u) {
                    a = gVar.c(bArr, i4, this.c - i4);
                    if (a >= 0) {
                        b._t104 = gVar;
                        a = 0;
                        break;
                    }
                } else {
                    if (auVar.c(bArr, i4, (this.c - i4) - 1) >= 0) {
                        this.x.d = auVar;
                    }
                    if (axVar.c(bArr, i4, (this.c - i4) - 1) >= 0) {
                        a(axVar);
                    }
                    a = vVar.a(bArr, i4, (this.c - i4) - 1, b._tgtgt_key);
                    s.I = 0;
                }
                if (a >= 0) {
                    byte[] c2 = vVar.c();
                    int i5 = 2;
                    int length = c2.length;
                    if (atVar.c(c2, 2, length) > 0) {
                        a(atVar);
                    }
                    if (ahVar.c(c2, 2, length) > 0) {
                        this.x.a(ahVar.a(), ahVar.g());
                    }
                    if (sVar.c(c2, 2, length) >= 0) {
                        this.x.f = sVar.a();
                        this.x.a(this.x.g, Long.valueOf(this.x.f));
                    }
                    pVar.c(c2, 2, length);
                    qVar.c(c2, 2, length);
                    mVar.c(c2, 2, length);
                    tVar.c(c2, 2, length);
                    a = wVar.c(c2, 2, length);
                    if (a >= 0) {
                        byte[] a2;
                        byte[] a3;
                        if (fVar.c(c2, 2, length) >= 0) {
                            bArr7 = fVar.c();
                        }
                        if (kVar.c(c2, 2, length) >= 0) {
                            util.set_ksid(u.t, kVar.c());
                        }
                        if (eVar.c(c2, 2, length) >= 0) {
                            bArr2 = eVar.c();
                        }
                        if (nVar.c(c2, 2, length) >= 0) {
                            bArr3 = nVar.c();
                        }
                        if (xVar.c(c2, 2, length) >= 0) {
                            bArr4 = xVar.c();
                        }
                        if (aaVar.c(c2, 2, length) >= 0) {
                            bArr5 = aaVar.c();
                        }
                        if (abVar.c(c2, 2, length) >= 0) {
                            bArr6 = abVar.c();
                        }
                        if (adVar.c(c2, 2, length) >= 0) {
                            a2 = adVar.a();
                            bArr9 = adVar.g();
                            bArr8 = a2;
                        }
                        if (bxVar.c(c2, 2, length) >= 0) {
                            String str = this.x.g;
                            if (util.check_uin_account(str).booleanValue()) {
                                str = this.x.e(this.x.f);
                                if (str != null && str.length() > 0) {
                                    this.x.a(str, Long.valueOf(this.x.f), bxVar.a());
                                }
                            } else {
                                this.x.a(str, Long.valueOf(this.x.f), bxVar.a());
                            }
                            util.LOGI("put t186: name: " + str + " uin: " + this.x.f + " pwd=" + bxVar.a(), "");
                        }
                        util.LOGI("tgt len:" + util.buf_len(mVar.c()) + " tgt_key len:" + util.buf_len(pVar.c()) + " st len:" + util.buf_len(tVar.c()) + " st_key len:" + util.buf_len(qVar.c()) + " stwx_web len:" + util.buf_len(bArr7) + " lskey len:" + util.buf_len(bArr4) + " skey len:" + util.buf_len(bArr5) + " sig64 len:" + util.buf_len(bArr6) + " openid len:" + util.buf_len(bArr8) + " openkey len:" + util.buf_len(bArr9) + " pwdflag: " + bxVar.d() + " " + bxVar.a(), "" + this.x.f);
                        if (bcVar.c(c2, 2, length) >= 0) {
                            a2 = a(bcVar);
                            if (a2 == null || a2.length <= 0) {
                                this.x.j = new WFastLoginInfo();
                            } else {
                                this.x.j = new WFastLoginInfo(a2);
                            }
                        }
                        byte[][] bArr10 = (byte[][]) Array.newInstance(Byte.TYPE, new int[]{3, 0});
                        if (bbVar.c(c2, 2, length) >= 0) {
                            bArr10[0] = bbVar.a();
                            bArr10[1] = bbVar.g();
                            bArr10[2] = bbVar.h();
                        }
                        byte[][] bArr11 = (byte[][]) Array.newInstance(Byte.TYPE, new int[]{5, 0});
                        for (a = 0; a < 5; a++) {
                            bArr11[a] = new byte[0];
                        }
                        a = oVar.c(c2, 2, length);
                        if (iVar.c(c2, 2, length) >= 0 && a >= 0) {
                            bArr11[0] = (byte[]) b(iVar.c(), oVar.c()).clone();
                        }
                        if (bdVar.c(c2, 2, length) >= 0) {
                            bArr11[1] = bdVar.c();
                        }
                        if (coVar.c(c2, 2, length) >= 0) {
                            bArr11[4] = coVar.c();
                        }
                        if (b._sec_guid_flag) {
                            bArr11[2] = b._G;
                            bArr11[3] = b._dpwd;
                            bArr11[4] = b._t403.c();
                            b._sec_guid_flag = false;
                        }
                        byte[][] bArr12 = (byte[][]) Array.newInstance(Byte.TYPE, new int[]{15, 0});
                        for (a = 0; a < 15; a++) {
                            bArr12[a] = new byte[0];
                        }
                        if (ajVar.c(c2, 2, length) >= 0) {
                            bArr12[0] = ajVar.c();
                        }
                        if (aiVar.c(c2, 2, length) >= 0) {
                            bArr12[1] = aiVar.a();
                            bArr8 = aiVar.g();
                        }
                        if (anVar.c(c2, 2, length) >= 0) {
                            bArr12[2] = anVar.c();
                        }
                        if (ckVar.c(c2, 2, length) >= 0) {
                            bArr12[3] = ckVar.c();
                        }
                        if (ayVar.c(c2, 2, length) >= 0) {
                            bArr12[4] = ayVar.c();
                        }
                        if (bgVar.c(c2, 2, length) >= 0) {
                            bArr12[5] = bgVar.c();
                        }
                        if (bVar.c(c2, 2, length) >= 0) {
                            bArr12[6] = bVar.c();
                        }
                        if (beVar.c(c2, 2, length) >= 0) {
                            bArr12[7] = beVar.c();
                        }
                        cf cfVar = new cf();
                        if (cfVar.c(c2, 2, length) >= 0) {
                            bArr12[8] = cfVar.g();
                            a3 = cfVar.a();
                        } else {
                            a3 = bArr8;
                        }
                        ch chVar = new ch();
                        if (chVar.c(c2, 2, length) >= 0) {
                            bArr12[9] = chVar.a();
                            bArr12[10] = chVar.g();
                        }
                        b bVar2 = new b(im_common.MSG_PUSH);
                        if (bVar2.c(c2, 2, length) >= 0) {
                            bArr12[11] = bVar2.c();
                            util.LOGI("get DA2 in rsp", "");
                        } else {
                            util.LOGI("no DA2 in rsp", "");
                        }
                        bVar2 = new b(791);
                        if (bVar2.c(c2, 2, length) >= 0) {
                            WloginSigInfo._QRPUSHSig = bVar2.c();
                        } else {
                            WloginSigInfo._QRPUSHSig = new byte[0];
                        }
                        bVar2 = new b(307);
                        if (bVar2.c(c2, 2, length) >= 0) {
                            bArr12[13] = bVar2.c();
                        } else {
                            util.LOGW("get t133 failed", "" + this.x.f);
                        }
                        bVar2 = new b(308);
                        if (bVar2.c(c2, 2, length) >= 0) {
                            bArr12[14] = bVar2.c();
                        } else {
                            util.LOGW("get t134 failed", "" + this.x.f);
                        }
                        util.LOGI("encrypt_a1 len:" + util.buf_len(bArr11[0]) + " no_pic_sig len:" + util.buf_len(bArr11[1]) + " G len:" + util.buf_len(bArr11[2]) + " dpwd len:" + util.buf_len(bArr11[3]) + " randseed len:" + util.buf_len(bArr11[4]) + " vkey len:" + util.buf_len(bArr12[0]) + " openid len:" + util.buf_len(a3) + " access_token len:" + util.buf_len(bArr12[1]) + " d2 len:" + util.buf_len(bArr12[2]) + " d2_key len:" + util.buf_len(bArr12[3]) + " sid len:" + util.buf_len(bArr12[4]) + " aq_sig len:" + util.buf_len(bArr12[5]) + " pskey len:" + util.buf_len(bArr12[6]) + " super_key len:" + util.buf_len(bArr12[7]) + " paytoken len:" + util.buf_len(bArr12[8]) + " pf len:" + util.buf_len(bArr12[9]) + " pfkey len:" + util.buf_len(bArr12[10]) + " da2 len:" + util.buf_len(bArr12[11]) + " wt session ticket:" + util.buf_len(bArr12[13]) + " wt session ticket key:" + util.buf_len(bArr12[14]), "" + this.x.f);
                        if (zVar.c(c2, 2, length) >= 0) {
                            j = 4294967295L & ((long) zVar.a());
                        }
                        long[] jArr = new long[7];
                        a = 2;
                        long j4 = 2160000;
                        while (true) {
                            a = akVar.c(c2, a, length);
                            if (a < 0) {
                                util.LOGI("sappid:" + j2 + " appid:" + j3 + " app_pri:" + j + " login_bitmap:" + b._login_bitmap + " tk_valid:" + 0 + " a2_valid:" + j4 + " lskey_valid:" + jArr[0] + " skey_valid:" + jArr[1] + " vkey_valid:" + jArr[2] + " a8_valid:" + jArr[3] + " stweb_valid:" + jArr[4] + " d2_valid:" + jArr[5] + " sid_valid:" + jArr[6], "" + this.x.f);
                                this.x.ao = b._main_sigmap;
                                a = this.x.a(this.x.f, j2, bArr11, j3, j, u.f(), u.f() + 0, j4 + u.f(), wVar.a(), wVar.g(), wVar.h(), wVar.i(), bArr10, mVar.c(), pVar.c(), tVar.c(), qVar.c(), bArr7, bArr3, bArr2, bArr4, bArr5, bArr6, a3, bArr9, bArr12, jArr, b._login_bitmap);
                                if (a == 0) {
                                    while (true) {
                                        i5 = yVar.c(c2, i5, length);
                                        if (i5 < 0) {
                                            a = 0;
                                            break;
                                        }
                                        this.x.a(this.x.f, yVar.a(), u.f(), u.f() + 0, yVar.h(), yVar.g());
                                    }
                                } else {
                                    ErrMsg errMsg = new ErrMsg();
                                    errMsg.setMessage(InternationMsg.a(MSG_TYPE.MSG_2));
                                    a(errMsg);
                                    util.LOGI("put_siginfo fail,ret=" + a, "" + this.x.f);
                                    break;
                                }
                            }
                            long a4;
                            if (akVar.a() != 0) {
                                a4 = (long) akVar.a();
                            } else {
                                a4 = j4;
                            }
                            if (akVar.g() != 0) {
                                jArr[0] = (long) akVar.g();
                            } else {
                                jArr[0] = 1641600;
                            }
                            if (akVar.h() != 0) {
                                jArr[1] = (long) akVar.h();
                            } else {
                                jArr[1] = TbsDownloadConfig.DEFAULT_RETRY_INTERVAL_SEC;
                            }
                            if (akVar.i() != 0) {
                                jArr[2] = (long) akVar.i();
                            } else {
                                jArr[2] = 1728000;
                            }
                            if (akVar.j() != 0) {
                                jArr[3] = (long) akVar.j();
                            } else {
                                jArr[3] = 72000;
                            }
                            if (akVar.k() != 0) {
                                jArr[4] = (long) akVar.k();
                            } else {
                                jArr[4] = 6000;
                            }
                            if (akVar.l() != 0) {
                                jArr[5] = (long) akVar.l();
                            } else {
                                jArr[5] = 1728000;
                            }
                            if (akVar.m() != 0) {
                                jArr[6] = (long) akVar.m();
                                j4 = a4;
                            } else {
                                jArr[6] = 1728000;
                                j4 = a4;
                            }
                        }
                    }
                }
                break;
            case 1:
            case 15:
                util.LOGI("cmd " + Integer.toHexString(this.t) + " subcmd " + Integer.toHexString(this.u) + " result " + c + " will clean sig for uin " + this.x.f + " appid " + j2);
                this.x.d(this.x.f, j2);
                c(bArr, i4, (this.c - i4) - 1);
                a = c;
                break;
            case 2:
                a = gVar.c(bArr, i4, (this.c - i4) - 1);
                if (a >= 0) {
                    b._t104 = gVar;
                    cb cbVar = new cb();
                    if (cbVar.c(bArr, i4, (this.c - i4) - 1) >= 0) {
                        a(new ErrMsg(c, "", "", cbVar.a()));
                    } else {
                        a = hVar.c(bArr, i4, (this.c - i4) - 1);
                        if (a >= 0) {
                            b._t105 = hVar;
                            if (azVar.c(bArr, i4, (this.c - i4) - 1) >= 0) {
                                b._t165 = azVar;
                            } else {
                                b._t165 = new az();
                            }
                            a(null);
                        }
                    }
                    a = c;
                    break;
                }
                break;
            case 16:
                this.x.d(this.x.f, j2);
                a = ahVar.c(bArr, i4, (this.c - i4) - 1);
                if (a >= 0) {
                    this.x.a(ahVar.a(), ahVar.g());
                    c(bArr, i4, (this.c - i4) - 1);
                    a = c;
                    break;
                }
                break;
            case 41:
            case 116:
                b bVar3 = new b(ErrorCode.INFO_CAN_NOT_LOAD_TBS);
                a = bVar3.c(bArr, i4, (this.c - i4) - 1);
                if (a >= 0) {
                    WloginSigInfo._LHSig = bVar3.c();
                    c(bArr, i4, (this.c - i4) - 1);
                    a = c;
                    break;
                }
                break;
            case 160:
                if (sVar.c(bArr, i4, (this.c - i4) - 1) >= 0) {
                    this.x.f = sVar.a();
                    this.x.a(this.x.g, Long.valueOf(this.x.f));
                }
                a = gVar.c(bArr, i4, (this.c - i4) - 1);
                if (a >= 0) {
                    b._t104 = gVar;
                    a = bjVar.c(bArr, i4, (this.c - i4) - 1);
                    if (a >= 0) {
                        b._t174 = bjVar;
                        if (blVar.c(bArr, i4, (this.c - i4) - 1) >= 0) {
                            b._devlock_info.CountryCode = new String(blVar.a());
                            b._devlock_info.Mobile = new String(blVar.g());
                            b._devlock_info.MbItemSmsCodeStatus = blVar.h();
                            b._devlock_info.AvailableMsgCount = blVar.i();
                            b._devlock_info.TimeLimit = blVar.j();
                        }
                        ce ceVar = new ce();
                        if (ceVar.c(bArr, i4, (this.c - i4) - 1) >= 0) {
                            b._devlock_info.BakCountryCode = ceVar.g();
                            b._devlock_info.BakMobile = ceVar.h();
                            b._devlock_info.BakMobileState = ceVar.a();
                        }
                        if (bmVar.c(bArr, i4, (this.c - i4) - 1) >= 0) {
                            b._devlock_info.UnionVerifyUrl = new String(bmVar.a());
                        }
                        if (bqVar.c(bArr, i4, (this.c - i4) - 1) >= 0) {
                            b._devlock_info.MbGuideType = bqVar.a();
                            b._devlock_info.MbGuideMsg = new String(bqVar.g());
                            b._devlock_info.MbGuideInfoType = bqVar.h();
                            b._devlock_info.MbGuideInfo = new String(bqVar.i());
                        }
                        if (brVar.c(bArr, i4, (this.c - i4) - 1) >= 0) {
                            b._devlock_info.VerifyReason = new String(brVar.c());
                        }
                        if (cnVar.c(bArr, i4, (this.c - i4) - 1) >= 0) {
                            b._t402 = cnVar;
                        } else {
                            b._t402 = new cn();
                        }
                        if (coVar.c(bArr, i4, (this.c - i4) - 1) >= 0) {
                            b._t403 = coVar;
                        } else {
                            b._t403 = new co();
                        }
                        c(bArr, i4, (this.c - i4) - 1);
                        a = c;
                        break;
                    }
                }
                break;
            case Opcodes.ADD_INT_2ADDR /*176*/:
                c(bArr, i4, (this.c - i4) - 1);
                this.x.a(this.x.g);
                a = c;
                break;
            case 180:
                a = axVar.c(bArr, i4, (this.c - i4) - 1);
                if (a >= 0) {
                    a(axVar);
                    c(bArr, i4, (this.c - i4) - 1);
                    a = c;
                    break;
                }
                break;
            case 204:
                if (sVar.c(bArr, i4, (this.c - i4) - 1) >= 0) {
                    this.x.f = sVar.a();
                    this.x.a(this.x.g, Long.valueOf(this.x.f));
                }
                a = gVar.c(bArr, i4, (this.c - i4) - 1);
                if (a >= 0) {
                    b._t104 = gVar;
                    a = cnVar.c(bArr, i4, (this.c - i4) - 1);
                    if (a >= 0) {
                        b._t402 = cnVar;
                        a = coVar.c(bArr, i4, (this.c - i4) - 1);
                        if (a >= 0) {
                            b._t403 = coVar;
                            c(bArr, i4, (this.c - i4) - 1);
                            a = c;
                            break;
                        }
                    }
                }
                break;
            case 208:
                a = gVar.c(bArr, i4, (this.c - i4) - 1);
                if (a >= 0) {
                    b._t104 = gVar;
                    a = aeVar.c(bArr, i4, (this.c - i4) - 1);
                    if (a >= 0) {
                        b._t126 = aeVar;
                        a = btVar.c(bArr, i4, (this.c - i4) - 1);
                        if (a >= 0) {
                            b._smslogin_msgcnt = btVar.a();
                            b._smslogin_timelimit = btVar.g();
                            a = buVar.c(bArr, i4, (this.c - i4) - 1);
                            if (a >= 0) {
                                b._msalt = buVar.a();
                                a = 0;
                                break;
                            }
                        }
                    }
                }
                break;
            default:
                c(bArr, i4, (this.c - i4) - 1);
                a = c;
                break;
        }
        util.LOGI("type:" + c + " ret:" + (a > 0 ? "0x" + Integer.toHexString(a) : Integer.valueOf(a)), "" + this.x.f);
        if (a == 0) {
            a(null);
        } else {
            new cp().c(bArr, i4, (this.c - i4) - 1);
            if (cp.a) {
                ErrMsg errMsg2 = new ErrMsg();
                int b2 = new i(this.x, this.t, this.u, errMsg2).b(a);
                if (b2 != -1000) {
                    a(errMsg2);
                    a = b2;
                }
            }
        }
        if (a == 10 || a == Opcodes.OR_LONG || a == Opcodes.XOR_LONG || a == Opcodes.SHR_LONG || a == Opcodes.USHR_LONG || a == Opcodes.ADD_FLOAT || a == Opcodes.USHR_INT || (a >= 128 && a <= Opcodes.INT_TO_SHORT)) {
            a = -1000;
        }
        if (i3 == 2 || i3 == 6 || i3 == 7) {
            return a;
        }
        a(i3);
        return a;
    }

    public void a(WloginSigInfo wloginSigInfo) {
        if (true != wloginSigInfo.isWtSessionTicketExpired() && wloginSigInfo.wtSessionTicket != null && wloginSigInfo.wtSessionTicketKey != null) {
            this.A = (byte[]) wloginSigInfo.wtSessionTicket.clone();
            this.B = (byte[]) wloginSigInfo.wtSessionTicketKey.clone();
        }
    }

    public void g() {
        this.z = true;
    }
}
