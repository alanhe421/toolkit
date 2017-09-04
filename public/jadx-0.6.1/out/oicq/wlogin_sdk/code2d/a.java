package oicq.wlogin_sdk.code2d;

import android.content.Context;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import oicq.wlogin_sdk.b.b;
import oicq.wlogin_sdk.request.WloginAllSigInfo;
import oicq.wlogin_sdk.request.d;
import oicq.wlogin_sdk.request.oicq_request;
import oicq.wlogin_sdk.request.u;
import oicq.wlogin_sdk.tools.cryptor;
import oicq.wlogin_sdk.tools.util;

/* compiled from: close_code */
public class a extends b {
    protected static byte[] a = null;

    public a() {
        this._cmd = 20;
    }

    public byte[] a(long j, long j2, long j3, byte[] bArr, byte[] bArr2, byte[] bArr3, int i, List<byte[]> list, byte[] bArr4, byte[] bArr5, long j4, long j5) {
        int i2;
        int length;
        if (list == null) {
            list = new ArrayList();
        }
        int[] iArr = new int[]{22, 24, 25, 29, 104};
        int[] iArr2 = new int[0];
        Object obj = null;
        if (!c.s || bArr4.length <= 16) {
            iArr = iArr2;
        } else {
            obj = new byte[(bArr4.length - 16)];
            Object obj2 = new byte[16];
            System.arraycopy(bArr4, 0, obj, 0, obj.length);
            System.arraycopy(bArr4, obj.length, obj2, 0, obj2.length);
            a = obj2;
        }
        b bVar = new b();
        bVar.b(104);
        bVar.c(u.A, u.A.length);
        bVar.e();
        list.add(bVar.b());
        for (i2 = 0; i2 < iArr.length; i2++) {
            byte[] bArr6 = null;
            b bVar2 = new b();
            bVar2.b(iArr[i2]);
            switch (iArr[i2]) {
                case 22:
                    bArr6 = getAppInfo(j2, j3);
                    break;
                case 24:
                    Object obj3 = obj;
                    break;
                case 25:
                    bArr6 = bArr5;
                    break;
                case 29:
                    bArr6 = new byte[10];
                    util.int8_to_buf(bArr6, 0, 0);
                    util.int64_to_buf32(bArr6, 1, j4);
                    util.int64_to_buf32(bArr6, 5, j5);
                    util.int8_to_buf(bArr6, 9, 0);
                    break;
            }
            if (bArr6 != null) {
                bVar2.c(bArr6, bArr6.length);
                bVar2.e();
                list.add(bVar2.b());
            }
        }
        int size = list.size();
        int length2 = ((((bArr.length + 16) + 2) + bArr2.length) + 1) + 2;
        i2 = 0;
        while (i2 < size) {
            if (list.get(i2) != null) {
                length = ((byte[]) list.get(i2)).length + length2;
            } else {
                length = length2;
            }
            i2++;
            length2 = length;
        }
        Object obj4 = new byte[length2];
        util.int64_to_buf32(obj4, 2, j2);
        util.int64_to_buf(obj4, 6, j);
        util.int16_to_buf(obj4, 14, bArr.length);
        System.arraycopy(bArr, 0, obj4, 16, bArr.length);
        length = bArr.length + 16;
        util.int16_to_buf(obj4, length, bArr2.length);
        length += 2;
        System.arraycopy(bArr2, 0, obj4, length, bArr2.length);
        length += bArr2.length;
        util.int8_to_buf(obj4, length, 3);
        length++;
        util.int16_to_buf(obj4, length, size);
        length2 = length + 2;
        i2 = 0;
        while (i2 < size) {
            byte[] bArr7 = (byte[]) list.get(i2);
            if (bArr7 != null) {
                System.arraycopy(bArr7, 0, obj4, length2, bArr7.length);
                length = bArr7.length + length2;
            } else {
                length = length2;
            }
            i2++;
            length2 = length;
        }
        return get_request(j, true, obj4);
    }

    public int a(byte[] bArr, long j, Context context) {
        Object obj = get_response(bArr, 0);
        if (obj == null || obj.length < 11) {
            return -1009;
        }
        _status.a = util.buf_to_int64(obj, 2);
        _status.b = util.buf_to_int8(obj, 10) & 255;
        if (_status.b != 0) {
            int buf_to_int16 = util.buf_to_int16(obj, 11);
            _status.f = new byte[buf_to_int16];
            System.arraycopy(obj, 13, _status.f, 0, buf_to_int16);
            int i = buf_to_int16 + 13;
            return _status.b;
        }
        _status.c = ((long) util.buf_to_int32(obj, 11)) & 4294967295L;
        buf_to_int16 = util.buf_to_int16(obj, 15);
        _status.d = new byte[buf_to_int16];
        System.arraycopy(obj, 17, _status.d, 0, buf_to_int16);
        buf_to_int16 += 17;
        if (((buf_to_int16 + 2) + 2) + 1 >= obj.length) {
            return _status.b;
        }
        int buf_to_int162 = util.buf_to_int16(obj, buf_to_int16);
        buf_to_int16 += 2;
        if (buf_to_int162 == 0) {
            return _status.b;
        }
        Object decrypt = cryptor.decrypt(obj, buf_to_int16, buf_to_int162, a);
        if (decrypt == null || decrypt.length == 0) {
            return -1014;
        }
        int buf_to_int163 = util.buf_to_int16(decrypt, 0);
        byte[] bArr2 = null;
        byte[] bArr3 = null;
        Object obj2 = null;
        i = 2;
        int i2 = 0;
        while (i2 < buf_to_int163) {
            Object obj3;
            byte[] bArr4;
            int buf_to_int164 = util.buf_to_int16(decrypt, i);
            i += 2;
            int buf_to_int165 = util.buf_to_int16(decrypt, i);
            int i3 = i + 2;
            switch (buf_to_int164) {
                case 24:
                    obj = new byte[buf_to_int165];
                    System.arraycopy(decrypt, i3, obj, 0, buf_to_int165);
                    obj3 = obj2;
                    bArr4 = bArr3;
                    Object obj4 = obj;
                    obj = obj3;
                    break;
                case 25:
                    obj = new byte[buf_to_int165];
                    System.arraycopy(decrypt, i3, obj, 0, buf_to_int165);
                    bArr4 = bArr3;
                    bArr3 = bArr2;
                    break;
                case 30:
                    obj = new byte[buf_to_int165];
                    System.arraycopy(decrypt, i3, obj, 0, buf_to_int165);
                    bArr3 = bArr2;
                    obj3 = obj;
                    obj = obj2;
                    obj2 = obj3;
                    break;
                default:
                    obj = obj2;
                    bArr4 = bArr3;
                    bArr3 = bArr2;
                    break;
            }
            i2++;
            obj3 = obj;
            i = i3 + buf_to_int165;
            bArr2 = bArr3;
            bArr3 = bArr4;
            obj2 = obj3;
        }
        if (c.s) {
            if (obj2 == null || bArr2 == null || bArr3 == null) {
                return -1009;
            }
            TreeMap a = d.a(context, "tk_file");
            if (a == null) {
                return -1004;
            }
            WloginAllSigInfo wloginAllSigInfo = (WloginAllSigInfo) a.get(Long.valueOf(_status.a));
            if (wloginAllSigInfo == null) {
                return -1004;
            }
            byte[][] bArr5 = (byte[][]) Array.newInstance(Byte.TYPE, new int[]{5, 0});
            for (int i4 = 0; i4 < 5; i4++) {
                bArr5[i4] = new byte[0];
            }
            bArr5[0] = oicq_request.b(bArr2, bArr3);
            bArr5[1] = obj2;
            wloginAllSigInfo.put_siginfo(j, bArr5, u.f());
            a.put(Long.valueOf(_status.a), wloginAllSigInfo.get_clone());
            u.aj.a(a, "tk_file");
        }
        return _status.b;
    }
}
