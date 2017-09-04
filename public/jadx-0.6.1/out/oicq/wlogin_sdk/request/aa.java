package oicq.wlogin_sdk.request;

import android.os.Build.VERSION;
import java.net.Socket;
import oicq.wlogin_sdk.b.bh;
import oicq.wlogin_sdk.report.report_t;
import oicq.wlogin_sdk.request.oicq_request.EncryptionMethod;
import oicq.wlogin_sdk.tools.ErrMsg;
import oicq.wlogin_sdk.tools.cryptor;
import oicq.wlogin_sdk.tools.util;

/* compiled from: request_transport */
public class aa extends oicq_request {
    public int I;
    public int J;

    public aa(u uVar) {
        this.I = 0;
        this.J = 5;
        this.t = 2066;
        this.u = 1;
        this.v = "wtlogin.trans_emp";
        this.x = uVar;
        if (this.x.m != 0) {
            this.x.m = 1;
        }
    }

    public int c(boolean z) {
        return z ? 8080 : 8080;
    }

    public Socket d() {
        if (this.x.ai != null) {
            util.LOGD("_transport_sk", "_transport_sk" + this.x.ai.toString());
        } else {
            util.LOGD("_transport_sk", "_transport_sk null");
        }
        return this.x.ai;
    }

    public void a(Socket socket) {
        this.x.ai = socket;
    }

    public byte[] a(byte[] bArr, boolean z, byte[] bArr2, long j, long j2, TransReqContext transReqContext) {
        int i;
        Object a;
        if (bArr2 == null) {
            bArr2 = new byte[0];
        }
        if (true == z) {
            i = 1;
        } else {
            i = 0;
        }
        Object obj = new byte[0];
        if (this.x.r != null && this.x.r.length > 0) {
            a = new bh().a(this.x.r);
            obj = new byte[(a.length + 2)];
            util.int16_to_buf(obj, 0, 1);
            System.arraycopy(a, 0, obj, 2, a.length);
        }
        this.I = ((bArr2.length + 13) + 1) + obj.length;
        a = new byte[(bArr.length + this.I)];
        util.int8_to_buf(a, 0, i);
        util.int16_to_buf(a, 1, bArr.length);
        util.int64_to_buf32(a, 3, j);
        util.int64_to_buf32(a, 7, j2);
        util.int16_to_buf(a, 11, bArr2.length);
        System.arraycopy(bArr2, 0, a, 13, bArr2.length);
        i = bArr2.length + 13;
        util.int8_to_buf(a, i, obj.length);
        i++;
        System.arraycopy(obj, 0, a, i, obj.length);
        i += obj.length;
        System.arraycopy(bArr, 0, a, i, bArr.length);
        i += bArr.length;
        return a((byte[]) a, transReqContext.requestEm, transReqContext.wtSessionTicket, transReqContext.wtSessionTicketKey);
    }

    public byte[] a(byte[] bArr, byte[] bArr2, long j, long j2, int i) {
        int i2;
        if (bArr2 == null) {
            bArr2 = new byte[0];
            if (i == 0) {
                i2 = 0;
            } else {
                i2 = 3;
            }
        } else if (i == 0) {
            i2 = 1;
        } else {
            i2 = 2;
        }
        Object obj = new byte[0];
        if (this.x.r != null && this.x.r.length > 0) {
            Object a = new bh().a(this.x.r);
            obj = new byte[(a.length + 2)];
            util.int16_to_buf(obj, 0, 1);
            System.arraycopy(a, 0, obj, 2, a.length);
        }
        Object obj2 = obj;
        this.I = ((bArr2.length + 13) + 1) + obj2.length;
        obj = new byte[(bArr.length + this.I)];
        util.int8_to_buf(obj, 0, i2);
        util.int16_to_buf(obj, 1, bArr.length);
        util.int64_to_buf32(obj, 3, j);
        util.int64_to_buf32(obj, 7, j2);
        util.int16_to_buf(obj, 11, bArr2.length);
        System.arraycopy(bArr2, 0, obj, 13, bArr2.length);
        i2 = bArr2.length + 13;
        util.int8_to_buf(obj, i2, obj2.length);
        i2++;
        System.arraycopy(obj2, 0, obj, i2, obj2.length);
        i2 += obj2.length;
        System.arraycopy(bArr, 0, obj, i2, bArr.length);
        i2 += bArr.length;
        return a((byte[]) obj);
    }

    public int d(byte[] bArr, int i, int i2) {
        if (i2 < this.J) {
            return -1009;
        }
        int c = c(bArr, i);
        a((ErrMsg) null);
        util.LOGD(getClass().getName(), "type=" + c);
        return c;
    }

    public synchronized int a(long j, byte[] bArr, byte[] bArr2, byte[] bArr3, long j2, WUserSigInfo wUserSigInfo) {
        int i;
        int i2 = u.w;
        long currentTimeMillis = (System.currentTimeMillis() / 1000) + u.ac;
        u.al.commit(VERSION.RELEASE, new String(u.G), "", util.buf_to_string(util.get_ksid(u.t)), new String(u.E), new String(u.Q), new String(u.I), new String(u.H), util.get_release_time(), util.SDK_VERSION);
        try {
            byte[] bytes = u.al.toJasonObj().toString().getBytes();
        } catch (Throwable th) {
            bytes = new byte[0];
        }
        if (bytes != null) {
            if (bytes.length != 0) {
                Object compress = util.compress(bytes);
                if (compress == null || compress.length == 0) {
                    i = 0;
                } else {
                    Object obj = new byte[(compress.length + 8)];
                    util.int64_to_buf32(obj, 0, currentTimeMillis);
                    util.int8_to_buf(obj, 4, 0);
                    util.int8_to_buf(obj, 5, 1);
                    util.int16_to_buf(obj, 6, compress.length);
                    System.arraycopy(compress, 0, obj, 8, compress.length);
                    byte[] encrypt = cryptor.encrypt(obj, 0, obj.length, bArr3);
                    if (encrypt == null || encrypt.length == 0) {
                        i = 0;
                    } else {
                        report_t.delete_file(u.t);
                        long j3 = j;
                        int i3 = i2;
                        a(this.i, this.t, this.j, j3, this.m, this.n, i3, this.p, a(encrypt, bArr2, j2, 85, 0));
                        i = a(String.valueOf(j), true, wUserSigInfo);
                        if (i == 0) {
                            i = b();
                        }
                        if (i != 0) {
                            report_t.write_tofile(u.al, u.t);
                        } else {
                            u.al.clear_t2();
                        }
                    }
                }
            }
        }
        i = 0;
        return i;
    }

    public synchronized int a(long j, TransReqContext transReqContext, byte[] bArr, byte[] bArr2, long j2, long j3, WUserSigInfo wUserSigInfo) {
        int i;
        i = u.w;
        int i2 = 0;
        i = 0;
        while (true) {
            Object obj;
            Object obj2 = transReqContext._body;
            long currentTimeMillis = u.ac + (System.currentTimeMillis() / 1000);
            if (obj2 == null) {
                obj = new byte[0];
            } else {
                obj = obj2;
            }
            byte[] bArr3 = new byte[(obj.length + 4)];
            util.int64_to_buf32(bArr3, 0, currentTimeMillis);
            System.arraycopy(obj, 0, bArr3, 4, obj.length);
            if (bArr != null) {
                bArr3 = cryptor.encrypt(bArr3, 0, bArr3.length, bArr2);
            }
            if (bArr3 != null && bArr3.length > 0) {
                if (EncryptionMethod.EM_ST == transReqContext.requestEm && (transReqContext.wtSessionTicketKey == null || transReqContext.wtSessionTicketKey.length == 0 || transReqContext.wtSessionTicket == null || transReqContext.wtSessionTicket.length == 0)) {
                    transReqContext.requestEm = EncryptionMethod.EM_ECDH;
                    u.al.attr_api(2413503);
                    util.LOGI("using wt st encrypt body but no st key", "" + j);
                }
                a(j, a(bArr3, bArr != null, bArr, j2, j3, transReqContext), transReqContext.requestEm);
                i = a(String.valueOf(j), false, wUserSigInfo);
                if (i == 0) {
                    i = a(transReqContext);
                    if (i == 0 && bArr != null) {
                        bArr3 = transReqContext.get_body();
                        transReqContext.set_body(cryptor.decrypt(bArr3, 0, bArr3.length, bArr2));
                    }
                    if (i != 180) {
                        break;
                    }
                }
                break;
            }
            int i3 = i2 + 1;
            if (i2 >= 1) {
                break;
            }
            i2 = i3;
        }
        util.LOGI("request_transport rsp: ret=" + i);
        return i;
    }

    public int a(TransReqContext transReqContext) {
        int i = this.c;
        if (i <= this.f + 2) {
            return -1009;
        }
        this.g = (i - this.f) - 2;
        if (transReqContext.requestEm == EncryptionMethod.EM_ECDH) {
            if (this.x.m == 0) {
                i = a(this.h, this.f + 1, this.g, this.x.p);
                if (i < 0) {
                    util.LOGI("use ecdh decrypt_body failed");
                    i = a(this.h, this.f + 1, this.g, this.x.c);
                    if (i < 0) {
                        util.LOGI("use kc decrypt_body failed");
                    }
                }
            } else {
                i = a(this.h, this.f + 1, this.g, this.x.c);
                if (i < 0) {
                    util.LOGI("use kc decrypt_body failed");
                }
            }
        } else if (transReqContext.requestEm == EncryptionMethod.EM_ST) {
            i = a(this.h, this.f + 1, this.g, transReqContext.wtSessionTicketKey);
            if (i < 0) {
                util.LOGI("use session key decrypt_body failed", "");
            }
        } else {
            util.LOGI("unknown encryption method " + transReqContext.requestEm, "");
            i = util.E_ENCRYPTION_METHOD;
        }
        if (i >= 0) {
            return a(this.h, this.f + 1, this.g, transReqContext);
        }
        return i;
    }

    public int a(byte[] bArr, int i, int i2, TransReqContext transReqContext) {
        if (i2 < this.J) {
            return -1009;
        }
        int c = c(bArr, i);
        a((ErrMsg) null);
        util.LOGD(getClass().getName(), "type=" + c);
        switch (c) {
            case 0:
                Object obj = new byte[(i2 - this.J)];
                System.arraycopy(bArr, this.J + i, obj, 0, obj.length);
                transReqContext.set_body(obj);
                return c;
            case 180:
                int i3 = (this.J + 2) + i;
                bh bhVar = new bh();
                i3 = bhVar.c(bArr, i3, (this.c - i3) - 1);
                if (i3 > 0) {
                    this.x.m = 2;
                    this.x.r = bhVar.c();
                    util.LOGI("request_transport get rollback sig");
                    i3 = c;
                }
                return i3;
            default:
                return c;
        }
    }

    public synchronized int a(long j, TransReqContext transReqContext, byte[] bArr, byte[] bArr2, byte[] bArr3, long j2, long j3, WUserSigInfo wUserSigInfo) {
        int i;
        byte[] bArr4;
        int i2 = u.w;
        long length = (long) transReqContext._body.length;
        i = this.E;
        this.E = i + 1;
        Object a = a(length, (long) i, String.valueOf(j).getBytes(), j2, j2, bArr3, new String("wtlogin_conn_trans").getBytes(), new byte[8], 0, 0, u.A);
        Object obj = transReqContext._body;
        long currentTimeMillis = u.ac + (System.currentTimeMillis() / 1000);
        if (obj == null) {
            bArr4 = new byte[0];
        } else if (bArr == null) {
            bArr4 = new byte[((a.length + 4) + obj.length)];
            util.int64_to_buf32(bArr4, 0, currentTimeMillis);
            System.arraycopy(a, 0, bArr4, 4, a.length);
            System.arraycopy(obj, 0, bArr4, a.length + 4, obj.length);
        } else {
            Object obj2 = new byte[((a.length + 4) + obj.length)];
            util.int64_to_buf32(obj2, 0, currentTimeMillis);
            System.arraycopy(a, 0, obj2, 4, a.length);
            System.arraycopy(obj, 0, obj2, a.length + 4, obj.length);
            bArr4 = cryptor.encrypt(obj2, 0, obj2.length, bArr2);
        }
        if (bArr4 == null || bArr4.length <= 0) {
            i = 0;
        } else {
            currentTimeMillis = j;
            int i3 = i2;
            a(this.i, this.t, this.j, currentTimeMillis, this.m, this.n, i3, this.p, a(bArr4, bArr, j2, j3, 1));
            i = a(String.valueOf(j), true, wUserSigInfo);
            if (i == 0) {
                i = a(transReqContext);
                if (i == 0 && bArr != null) {
                    byte[] bArr5 = transReqContext.get_body();
                    transReqContext.set_body(cryptor.decrypt(bArr5, 0, bArr5.length, bArr2));
                }
            }
        }
        return i;
    }

    byte[] a(long j, long j2, byte[] bArr, long j3, long j4, byte[] bArr2, byte[] bArr3, byte[] bArr4, int i, long j5, byte[] bArr5) {
        Object obj;
        if (bArr2 == null) {
            obj = new byte[0];
        }
        Object obj2 = new byte[((((((((((((((bArr.length + 16) + 4) + 16) + 4) + obj.length) + 4) + bArr3.length) + 4) + bArr4.length) + 1) + 4) + 4) + bArr5.length) + 4)];
        util.int64_to_buf32(obj2, 0, ((long) obj2.length) + j);
        util.int64_to_buf32(obj2, 4, (long) ((obj2.length - 4) - 4));
        util.int64_to_buf32(obj2, 8, j2);
        util.int32_to_buf(obj2, 12, bArr.length + 4);
        System.arraycopy(bArr, 0, obj2, 16, bArr.length);
        int length = bArr.length + 16;
        util.int64_to_buf32(obj2, length, j3);
        length += 4;
        util.int64_to_buf32(obj2, length, j4);
        length += 16;
        util.int32_to_buf(obj2, length, obj.length + 4);
        length += 4;
        System.arraycopy(obj, 0, obj2, length, obj.length);
        length += obj.length;
        util.int32_to_buf(obj2, length, bArr3.length + 4);
        length += 4;
        System.arraycopy(bArr3, 0, obj2, length, bArr3.length);
        length += bArr3.length;
        util.int32_to_buf(obj2, length, bArr4.length + 4);
        length += 4;
        System.arraycopy(bArr4, 0, obj2, length, bArr4.length);
        length += bArr4.length;
        util.int8_to_buf(obj2, length, i);
        length++;
        util.int64_to_buf32(obj2, length, j5);
        length += 4;
        util.int32_to_buf(obj2, length, bArr5.length + 4);
        length += 4;
        System.arraycopy(bArr5, 0, obj2, length, bArr5.length);
        length += bArr5.length;
        util.int64_to_buf32(obj2, length, 4 + j);
        length += 4;
        return obj2;
    }
}
