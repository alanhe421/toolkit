package tencent.tls.request;

import com.tencent.qalsdk.base.a;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import java.util.List;
import tencent.tls.platform.TLSUserInfo;
import tencent.tls.report.QLog;

public class req_tgtgt_nopicsig extends oicq_request {
    public req_tgtgt_nopicsig(req_global tencent_tls_request_req_global) {
        this._cmd = a.b;
        this._sub_cmd = 15;
        this._service_cmd = "wtlogin64.login";
        this._g = tencent_tls_request_req_global;
        this._g._encrypt_type = 0;
    }

    public byte[] get_request_body(long j, int i, long j2, int i2, byte[] bArr, byte[] bArr2, byte[] bArr3, int i3, int i4, long[] jArr, int i5, long j3, int i6, int i7, int i8, int i9, int i10, long j4, byte[] bArr4, byte[] bArr5, List<String> list) {
        TLVPacker 1 = new 1(this, new int[]{24, 1, 262, 278, 256, 263, ErrorCode.ERROR_SDKENGINE_CANLOADTBS, ErrorCode.ERROR_TBSINSTALLER_ISTBSCORELEGAL_02, 274, 1282, ErrorCode.THROWABLE_INITX5CORE, 358, 362, 363, 340, ErrorCode.ERROR_TBSINSTALLER_ISTBSCORELEGAL_01, 8, ErrorCode.TEST_THROWABLE_ISNOT_NULL, 370, 375, 1289}, j, i, j2, i2, bArr, bArr2, i3, i4, jArr, j3, i5, i7, i8, i9, i10, bArr4, req_global.get_async_data(this._g._seq), bArr5, bArr3, list, j4);
        return encrypt_body(1.doit(), this._sub_cmd, 1.getTlvCnt());
    }

    public int make_request(long j, int i, long j2, int i2, byte[] bArr, byte[] bArr2, byte[] bArr3, int i3, int i4, long[] jArr, int i5, long j3, int i6, int i7, int i8, int i9, int i10, long j4, TLSUserInfo tLSUserInfo) {
        int i11 = req_global._app_client_version;
        byte[] decrypt_a1 = decrypt_a1(bArr2);
        if (decrypt_a1 == null) {
            return -1014;
        }
        int i12 = 0;
        while (true) {
            get_request(this._default_client_version, this._cmd, this._default_client_seq, j2, this._default_ext_retry, this._default_ext_type, i11, this._default_ext_instance, get_request_body(j, i11, j2, i2, bArr, decrypt_a1, bArr3, i3, i4, jArr, i5, j3, i11, i7, i8, i9, i10, j4, req_global._IMEI, req_global._apk_id, null));
            int snd_rcv_req = snd_rcv_req();
            if (snd_rcv_req != 0) {
                return snd_rcv_req;
            }
            int i13 = get_response();
            QLog.i("retry num:" + i12 + " ret:" + i13, j2);
            if (i13 != 180) {
                return i13;
            }
            snd_rcv_req = i12 + 1;
            if (i12 >= 1) {
                return i13;
            }
            i12 = snd_rcv_req;
        }
    }
}
