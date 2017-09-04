package tencent.tls.request;

import com.tencent.qalsdk.base.a;
import tencent.tls.platform.TLSUserInfo;
import tencent.tls.report.QLog;
import tencent.tls.tlvs.tlv_t;
import tencent.tls.tlvs.tlv_t100;
import tencent.tls.tlvs.tlv_t107;
import tencent.tls.tlvs.tlv_t112;
import tencent.tls.tlvs.tlv_t154;
import tencent.tls.tlvs.tlv_t502;
import tencent.tls.tlvs.tlv_t509;
import tencent.tls.tlvs.tlv_t8;

public class req_getsalt extends oicq_request {
    public req_getsalt(req_global tencent_tls_request_req_global) {
        this._cmd = a.b;
        this._sub_cmd = 25;
        this._service_cmd = "wtlogin64.login";
        this._g = tencent_tls_request_req_global;
        this._g._encrypt_type = 0;
    }

    public byte[] get_request_body(long j, long j2, int i, int i2, String str, int i3, int i4, int i5, int i6) {
        final String str2 = str;
        final long j3 = j;
        final long j4 = j2;
        final int i7 = i;
        final int i8 = i2;
        final int i9 = i3;
        final int i10 = i4;
        final int i11 = i5;
        final int i12 = i6;
        TLVPacker anonymousClass1 = new TLVPacker(new int[]{256, 274, 263, 265, 340, 8, 1282, 1289}) {
            protected byte[] loop(int i) {
                byte[] bArr = new byte[0];
                if (str2 != null) {
                    bArr = str2.getBytes();
                }
                byte[] bArr2 = new byte[0];
                switch (i) {
                    case 8:
                        return new tlv_t8().get_tlv_8(0, req_global._local_id, 0);
                    case 256:
                        return new tlv_t100().get_tlv_100(j3, j4, i7, i8);
                    case 263:
                        return new tlv_t107().get_tlv_107(i9, i10, i11, i12);
                    case 265:
                        tlv_t tencent_tls_tlvs_tlv_t = new tlv_t(265);
                        tencent_tls_tlvs_tlv_t.set_data(req_global._IMEI, req_global._IMEI.length);
                        return tencent_tls_tlvs_tlv_t.get_buf();
                    case 274:
                        return new tlv_t112().get_tlv_112(bArr);
                    case 340:
                        return new tlv_t154().get_tlv_154(req_getsalt.this._g._sso_seq);
                    case 1282:
                        return new tlv_t502().get_tlv_502(req_global._acc_type);
                    case 1289:
                        return new tlv_t509().get_tlv_509(req_global._apk_sig, req_global._apk_id);
                    default:
                        return bArr2;
                }
            }
        };
        return encrypt_body(anonymousClass1.doit(), this._sub_cmd, anonymousClass1.getTlvCnt());
    }

    public int make_request(long j, long j2, int i, String str, int i2, int i3, int i4, int i5, TLSUserInfo tLSUserInfo) {
        int i6 = req_global._app_client_version;
        this._g._userid = str;
        int i7 = 0;
        while (true) {
            int i8 = i6;
            get_request(this._default_client_version, this._cmd, this._default_client_seq, 0, this._default_ext_retry, this._default_ext_type, i8, this._default_ext_instance, get_request_body(j, j2, i6, i, str, i2, i3, i4, i5));
            int snd_rcv_req = snd_rcv_req();
            if (snd_rcv_req != 0) {
                return snd_rcv_req;
            }
            int i9 = get_response();
            QLog.i("retry num:" + i7 + " ret:" + i9 + " uin: " + this._g._uin);
            if (i9 != 180) {
                return i9;
            }
            snd_rcv_req = i7 + 1;
            if (i7 >= 1) {
                return i9;
            }
            i7 = snd_rcv_req;
        }
    }
}
