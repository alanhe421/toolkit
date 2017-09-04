package oicq.wlogin_sdk.code2d;

import java.util.ArrayList;
import oicq.wlogin_sdk.b.b;
import oicq.wlogin_sdk.request.u;
import oicq.wlogin_sdk.tools.util;

public class fetch_code extends b {

    public static class QRCodeCustom {
        public int Dpi = 72;
        public int EcLevel = 2;
        public int Hint = 2;
        public int Margin = 4;
        public int Micro = 0;
        public int Size = 3;
        public int Version = 0;
    }

    public fetch_code() {
        this._cmd = 49;
    }

    public byte[] get_request(long j, long j2, long j3, byte[] bArr, QRCodeCustom qRCodeCustom, long j4, long j5, byte[] bArr2) {
        int i;
        int length = (bArr.length + 17) + 2;
        int[] iArr = new int[]{17, 22, 27, 29, 31, 51};
        int length2 = iArr.length;
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        while (i2 < length2) {
            b bVar = new b();
            bVar.b(iArr[i2]);
            byte[] bArr3 = null;
            i = 0;
            switch (iArr[i2]) {
                case 17:
                    if (bArr2 != null && bArr2.length > 0) {
                        i = bArr2.length;
                        util.LOGI("qrpushsig of " + j2, j + "");
                        bArr3 = bArr2;
                        break;
                    }
                case 22:
                    bArr3 = getAppInfo(j2, j3);
                    i = bArr3.length;
                    break;
                case 27:
                    i = 30;
                    bArr3 = new byte[30];
                    util.int32_to_buf(bArr3, 0, qRCodeCustom.Micro);
                    util.int32_to_buf(bArr3, 4, qRCodeCustom.Version);
                    util.int32_to_buf(bArr3, 8, qRCodeCustom.Size);
                    util.int32_to_buf(bArr3, 12, qRCodeCustom.Margin);
                    util.int32_to_buf(bArr3, 16, qRCodeCustom.Dpi);
                    util.int32_to_buf(bArr3, 20, qRCodeCustom.EcLevel);
                    util.int32_to_buf(bArr3, 24, qRCodeCustom.Hint);
                    util.int16_to_buf(bArr3, 28, 0);
                    break;
                case 29:
                    i = 14;
                    bArr3 = new byte[14];
                    util.int8_to_buf(bArr3, 0, 1);
                    util.int64_to_buf32(bArr3, 1, j4);
                    util.int64_to_buf32(bArr3, 5, j5);
                    util.int8_to_buf(bArr3, 9, 0);
                    util.int64_to_buf32(bArr3, 10, 0);
                    break;
                case 31:
                    i = (((((((((u.K.length + 3) + 2) + u.J.length) + 2) + 2) + u.C.length) + 2) + 0) + 2) + u.F.length;
                    bArr3 = new byte[i];
                    util.int8_to_buf(bArr3, 0, u.Z);
                    int fill_staff = fill_staff(bArr3, u.J, fill_staff(bArr3, u.K, 1));
                    util.int16_to_buf(bArr3, fill_staff, u.D);
                    fill_staff(bArr3, u.F, fill_staff(bArr3, new byte[0], fill_staff(bArr3, u.C, fill_staff + 2)));
                    break;
                case 51:
                    byte[] bArr4 = (byte[]) u.A.clone();
                    bArr3 = bArr4;
                    i = bArr4.length;
                    break;
            }
            if (bArr3 != null) {
                bVar.c(bArr3, i);
                bVar.e();
                Object b = bVar.b();
                i = b.length + length;
                arrayList.add(b);
            } else {
                i = length;
            }
            i2++;
            length = i;
        }
        Object obj = new byte[length];
        util.int64_to_buf32(obj, 2, j2);
        util.int64_to_buf(obj, 6, j);
        util.int8_to_buf(obj, 14, 8);
        i = fill_staff(obj, bArr, 15);
        int size = arrayList.size();
        util.int16_to_buf(obj, i, size);
        int i3 = i + 2;
        for (length = 0; length < size; length++) {
            bArr4 = (byte[]) arrayList.get(length);
            System.arraycopy(bArr4, 0, obj, i3, bArr4.length);
            i3 += bArr4.length;
        }
        return get_request(j, true, obj);
    }

    public int get_response(byte[] bArr) {
        Object obj = get_response(bArr, 0);
        if (obj == null || obj.length < 11) {
            return -1009;
        }
        _status.h = (long) util.buf_to_int32(obj, 2);
        _status.b = util.buf_to_int8(obj, 6);
        if (_status.b != 0) {
            int buf_to_int16 = util.buf_to_int16(obj, 7);
            _status.f = new byte[buf_to_int16];
            System.arraycopy(obj, 9, _status.f, 0, buf_to_int16);
            buf_to_int16 += 9;
            return _status.b;
        }
        buf_to_int16 = util.buf_to_int16(obj, 7);
        c.i = new byte[buf_to_int16];
        System.arraycopy(obj, 9, c.i, 0, buf_to_int16);
        buf_to_int16 += 9;
        int buf_to_int162 = util.buf_to_int16(obj, buf_to_int16);
        buf_to_int16 += 2;
        for (int i = 0; i < buf_to_int162; i++) {
            int buf_to_int163 = util.buf_to_int16(obj, buf_to_int16);
            buf_to_int16 += 2;
            int buf_to_int164 = util.buf_to_int16(obj, buf_to_int16);
            buf_to_int16 += 2;
            switch (buf_to_int163) {
                case 23:
                    _status.j = new byte[buf_to_int164];
                    System.arraycopy(obj, buf_to_int16, _status.j, 0, buf_to_int164);
                    buf_to_int16 += buf_to_int164;
                    break;
                case 28:
                    _status.k = (long) util.buf_to_int32(obj, buf_to_int16);
                    buf_to_int16 += 4;
                    _status.l = util.buf_to_int16(obj, buf_to_int16);
                    buf_to_int16 += 2;
                    break;
                default:
                    break;
            }
        }
        return _status.b;
    }
}
