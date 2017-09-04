package tencent.tls.request;

import com.tencent.qalsdk.base.a;
import tencent.tls.report.QLog;
import tencent.tls.tlvs.tlv_t8;

public class req_imgcode_reask extends oicq_request {
    public req_imgcode_reask(req_global tencent_tls_request_req_global) {
        this._cmd = a.b;
        this._sub_cmd = 3;
        this._service_cmd = "wtlogin64.login";
        this._g = tencent_tls_request_req_global;
        this._g._encrypt_type = 0;
    }

    public byte[] get_request_body() {
        TLVPacker anonymousClass1 = new TLVPacker(new int[]{260, 8}) {
            protected byte[] loop(int i) {
                byte[] bArr = new byte[0];
                switch (i) {
                    case 8:
                        return new tlv_t8().get_tlv_8(0, req_global._local_id, 0);
                    case 260:
                        return req_global.get_async_data(req_imgcode_reask.this._g._seq)._t104.get_buf();
                    default:
                        return bArr;
                }
            }
        };
        return encrypt_body(anonymousClass1.doit(), this._sub_cmd, anonymousClass1.getTlvCnt());
    }

    public int make_request() {
        int i = req_global._app_client_version;
        int i2 = 0;
        while (true) {
            get_request(this._default_client_version, this._cmd, this._default_client_seq, this._g._uin, this._default_ext_retry, this._default_ext_type, i, this._default_ext_instance, get_request_body());
            int snd_rcv_req = snd_rcv_req();
            if (snd_rcv_req != 0) {
                return snd_rcv_req;
            }
            int i3 = get_response();
            QLog.i("retry num:" + i2 + " ret:" + i3, this._g._uin);
            if (i3 != 180) {
                return i3;
            }
            snd_rcv_req = i2 + 1;
            if (i2 >= 1) {
                return i3;
            }
            i2 = snd_rcv_req;
        }
    }
}
