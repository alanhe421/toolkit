package tencent.tls.request;

import com.tencent.qalsdk.base.a;
import tencent.tls.report.QLog;
import tencent.tls.tlvs.tlv_t;
import tencent.tls.tlvs.tlv_t100;
import tencent.tls.tlvs.tlv_t112;
import tencent.tls.tlvs.tlv_t502;
import tencent.tls.tlvs.tlv_t8;

public class req_exchange extends oicq_request {
    public req_exchange(req_global tencent_tls_request_req_global) {
        this._cmd = a.b;
        this._sub_cmd = 26;
        this._service_cmd = "wtlogin64.login";
        this._g = tencent_tls_request_req_global;
        this._g._encrypt_type = 0;
    }

    public byte[] get_request_body(long j, int i, String str, String str2, String str3) {
        final long j2 = j;
        final String str4 = str;
        final int i2 = i;
        final String str5 = str2;
        final String str6 = str3;
        TLVPacker anonymousClass1 = new TLVPacker(new int[]{256, 274, 1282, 1283, 1287, 8}) {
            protected byte[] loop(int i) {
                byte[] bArr = null;
                byte[] bArr2 = new byte[0];
                tlv_t tencent_tls_tlvs_tlv_t;
                switch (i) {
                    case 8:
                        return new tlv_t8().get_tlv_8(0, req_global._local_id, 0);
                    case 256:
                        return new tlv_t100().get_tlv_100(j2, 0, 0, 0);
                    case 274:
                        return new tlv_t112().get_tlv_112(str4 == null ? new byte[0] : str4.getBytes());
                    case 1282:
                        return new tlv_t502().get_tlv_502(i2);
                    case 1283:
                        tencent_tls_tlvs_tlv_t = new tlv_t(1283);
                        if (str5 != null) {
                            bArr = str5.getBytes();
                        }
                        return tencent_tls_tlvs_tlv_t.build_tlv(bArr);
                    case 1287:
                        tencent_tls_tlvs_tlv_t = new tlv_t(1287);
                        if (str6 != null) {
                            bArr = str6.getBytes();
                        }
                        return tencent_tls_tlvs_tlv_t.build_tlv(bArr);
                    default:
                        return bArr2;
                }
            }
        };
        return encrypt_body(anonymousClass1.doit(), this._sub_cmd, anonymousClass1.getTlvCnt());
    }

    public int make_request(long j, int i, String str, String str2, String str3) {
        int i2 = req_global._app_client_version;
        this._g._userid = str;
        int i3 = 0;
        while (true) {
            get_request(this._default_client_version, this._cmd, this._default_client_seq, 0, this._default_ext_retry, this._default_ext_type, i2, this._default_ext_instance, get_request_body(j, i, str, str2, str3));
            int snd_rcv_req = snd_rcv_req();
            if (snd_rcv_req != 0) {
                return snd_rcv_req;
            }
            int i4 = get_response();
            QLog.i("retry num:" + i3 + " ret:" + i4 + " uin: " + this._g._uin);
            if (i4 != 180) {
                return i4;
            }
            snd_rcv_req = i3 + 1;
            if (i3 >= 1) {
                return i4;
            }
            i3 = snd_rcv_req;
        }
    }
}
