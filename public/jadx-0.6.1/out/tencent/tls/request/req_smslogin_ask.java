package tencent.tls.request;

import com.tencent.qalsdk.base.a;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import tencent.tls.platform.TLSUserInfo;
import tencent.tls.report.QLog;
import tencent.tls.tlvs.tlv_t100;
import tencent.tls.tlvs.tlv_t109;
import tencent.tls.tlvs.tlv_t112;
import tencent.tls.tlvs.tlv_t116;
import tencent.tls.tlvs.tlv_t142;
import tencent.tls.tlvs.tlv_t145;
import tencent.tls.tlvs.tlv_t154;
import tencent.tls.tlvs.tlv_t502;
import tencent.tls.tlvs.tlv_t509;
import tencent.tls.tlvs.tlv_t8;

public class req_smslogin_ask extends oicq_request {
    public req_smslogin_ask(req_global tencent_tls_request_req_global) {
        this._cmd = a.b;
        this._sub_cmd = 17;
        this._service_cmd = "wtlogin64.login";
        this._g = tencent_tls_request_req_global;
        this._g._encrypt_type = 0;
    }

    public byte[] get_request_body(long j, long j2, int i, int i2, byte[] bArr, int i3, int i4, long[] jArr) {
        final long j3 = j;
        final long j4 = j2;
        final int i5 = i2;
        final int i6 = i;
        final byte[] bArr2 = bArr;
        final int i7 = i3;
        final int i8 = i4;
        final long[] jArr2 = jArr;
        TLVPacker anonymousClass1 = new TLVPacker(new int[]{256, 265, 8, ErrorCode.ERROR_TBSINSTALLER_ISTBSCORELEGAL_02, ErrorCode.THROWABLE_INITX5CORE, 340, 274, 1282, 278, 1289}) {
            protected byte[] loop(int i) {
                byte[] bArr = new byte[0];
                switch (i) {
                    case 8:
                        return new tlv_t8().get_tlv_8(0, req_global._local_id, 0);
                    case 256:
                        return new tlv_t100().get_tlv_100(j3, j4, i5, i6);
                    case 265:
                        if (req_global._IMEI == null || req_global._IMEI.length <= 0) {
                            return bArr;
                        }
                        return new tlv_t109().get_tlv_109(req_global._IMEI);
                    case 274:
                        return new tlv_t112().get_tlv_112(bArr2);
                    case 278:
                        return new tlv_t116().get_tlv_116(i7, i8, jArr2);
                    case ErrorCode.ERROR_TBSINSTALLER_ISTBSCORELEGAL_02 /*322*/:
                        return new tlv_t142().get_tlv_142(req_global._apk_id);
                    case ErrorCode.THROWABLE_INITX5CORE /*325*/:
                        return new tlv_t145().get_tlv_145(req_global._IMEI);
                    case 340:
                        return new tlv_t154().get_tlv_154(req_smslogin_ask.this._g._sso_seq);
                    case 1282:
                        return new tlv_t502().get_tlv_502(req_global._acc_type);
                    case 1289:
                        return new tlv_t509().get_tlv_509(req_global._apk_sig, req_global._apk_id);
                    default:
                        return bArr;
                }
            }
        };
        return encrypt_body(anonymousClass1.doit(), this._sub_cmd, anonymousClass1.getTlvCnt());
    }

    public int make_request(long j, long j2, int i, String str, int i2, int i3, TLSUserInfo tLSUserInfo) {
        int i4 = req_global._app_client_version;
        int i5 = 0;
        while (true) {
            int i6 = i4;
            get_request(this._default_client_version, this._cmd, this._default_client_seq, this._g._uin, this._default_ext_retry, this._default_ext_type, i6, this._default_ext_instance, get_request_body(j, j2, i, i4, str.getBytes(), i2, i3, null));
            int snd_rcv_req = snd_rcv_req();
            if (snd_rcv_req != 0) {
                return snd_rcv_req;
            }
            int i7 = get_response();
            QLog.i("retry num:" + i5 + " ret:" + i7, this._g._uin);
            if (i7 != 180) {
                return i7;
            }
            snd_rcv_req = i5 + 1;
            if (i5 >= 1) {
                return i7;
            }
            i5 = snd_rcv_req;
        }
    }
}
