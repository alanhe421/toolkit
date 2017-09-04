package oicq.wlogin_sdk.request;

import android.os.Build.VERSION;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import oicq.wlogin_sdk.report.report_t;
import oicq.wlogin_sdk.tools.cryptor;
import oicq.wlogin_sdk.tools.util;

/* compiled from: request_report_error */
public class v extends k {
    public final int b = 1737040709;
    public final int c = 33;
    public final String d = "x'Z8mSi,V(Wu~.v:";

    public v(u uVar) {
        this.a = uVar;
    }

    public byte[] a(byte[] bArr, long j, long j2, long j3, int i) {
        Object obj = new byte[(bArr.length + 33)];
        util.int32_to_buf(obj, 0, 1737040709);
        util.int32_to_buf(obj, 4, 0);
        util.int32_to_buf(obj, 8, obj.length);
        util.int64_to_buf32(obj, 12, j);
        util.int64_to_buf32(obj, 16, j2);
        util.int64_to_buf32(obj, 20, j3 / 1000);
        util.int8_to_buf(obj, 24, i);
        System.arraycopy(bArr, 0, obj, 33, bArr.length);
        int length = bArr.length + 33;
        return obj;
    }

    public byte[] a(long j, byte[] bArr, byte[] bArr2, byte[] bArr3, long j2) {
        if (bArr2 == null || bArr2.length == 0) {
            Object encrypt = cryptor.encrypt(bArr, 0, bArr.length, "x'Z8mSi,V(Wu~.v:".getBytes());
            byte[] bArr4 = new byte[(encrypt.length + 10)];
            util.int32_to_buf(bArr4, 0, (int) j);
            util.int32_to_buf(bArr4, 4, (int) j2);
            util.int16_to_buf(bArr4, 8, 0);
            System.arraycopy(encrypt, 0, bArr4, 10, encrypt.length);
            int length = encrypt.length + 10;
            return bArr4;
        }
        encrypt = cryptor.encrypt(bArr, 0, bArr.length, bArr3);
        Object obj = new byte[((bArr2.length + 10) + encrypt.length)];
        util.int32_to_buf(obj, 0, (int) j);
        util.int32_to_buf(obj, 4, (int) j2);
        util.int16_to_buf(obj, 8, bArr2.length);
        System.arraycopy(bArr2, 0, obj, 10, bArr2.length);
        int length2 = bArr2.length + 10;
        System.arraycopy(encrypt, 0, obj, length2, encrypt.length);
        length = encrypt.length + length2;
        return obj;
    }

    public int b(long j, byte[] bArr, byte[] bArr2, byte[] bArr3, long j2) {
        byte[] bytes;
        long currentTimeMillis = System.currentTimeMillis();
        u.al.commit(VERSION.RELEASE, new String(u.G), "", util.buf_to_string(util.get_ksid(u.t)), new String(u.E), new String(u.Q), new String(u.I), new String(u.H), util.get_release_time(), util.SDK_VERSION);
        try {
            bytes = u.al.toJasonObj().toString().getBytes();
        } catch (Throwable th) {
            bytes = new byte[0];
        }
        if (bytes == null || bytes.length == 0) {
            return 0;
        }
        Object compress = util.compress(bytes);
        if (compress == null || compress.length == 0) {
            return 0;
        }
        byte[] bArr4 = new byte[(compress.length + 4)];
        util.int8_to_buf(bArr4, 0, 0);
        util.int8_to_buf(bArr4, 1, 1);
        util.int16_to_buf(bArr4, 2, compress.length);
        System.arraycopy(compress, 0, bArr4, 4, compress.length);
        byte[] a = a(bArr4, j, j2, currentTimeMillis, 0);
        report_t.delete_file(u.t);
        int b = b(a(a(j, a, bArr2, bArr3, j2)));
        util.LOGI("request_report_error(0) rsp: ret=" + b);
        if (b != 0) {
            report_t.write_tofile(u.al, u.t);
            return b;
        }
        u.al.clear_t2();
        return b;
    }

    public int c(long j, byte[] bArr, byte[] bArr2, byte[] bArr3, long j2) {
        if (!(this.a.d == null || u.e.booleanValue())) {
            u.e = Boolean.valueOf(true);
            long currentTimeMillis = System.currentTimeMillis();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            Calendar instance = Calendar.getInstance();
            int a = this.a.d.a();
            byte g = this.a.d.g();
            util.LOGI("bitmap:" + a + " network:" + g + " local network:" + util.get_network_type(u.t));
            for (int i = 0; i < 32; i++) {
                if (((1 << i) & a) != 0 && (g == (byte) 0 || (g == (byte) 1 && util.get_network_type(u.t) == 2))) {
                    byte[] bArr4;
                    long j3 = currentTimeMillis - ((long) (86400000 * i));
                    instance.setTimeInMillis(j3);
                    String format = simpleDateFormat.format(instance.getTime());
                    long logModifyTime = util.getLogModifyTime(u.t, format);
                    if (logModifyTime != 0) {
                        j3 = logModifyTime;
                    }
                    byte[] readLog = util.readLog(u.t, format);
                    if (readLog == null) {
                        bArr4 = new byte[0];
                    } else {
                        bArr4 = a(readLog, j, j2, j3, 1);
                    }
                    if (bArr4 != null && bArr4.length > 0) {
                        util.LOGI("request_report_error(1) rsp: ret=" + b(a(a(j, bArr4, bArr2, bArr3, j2))) + "(" + format + ")");
                    }
                }
            }
        }
        this.a.d = null;
        u.e = Boolean.valueOf(false);
        return 0;
    }

    public int a(long j, byte[] bArr, byte[] bArr2, byte[] bArr3, long j2, int i) {
        if (i == 0) {
            return b(j, bArr, bArr2, bArr3, j2);
        }
        if (i == 1) {
            return c(j, bArr, bArr2, bArr3, j2);
        }
        return 0;
    }
}
