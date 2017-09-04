package tencent.tls.request;

import java.net.Socket;
import tencent.tls.platform.TLSErrInfo;
import tencent.tls.platform.TLSUserInfo;
import tencent.tls.report.QLog;
import tencent.tls.tlvs.tlv_t172;
import tencent.tls.tools.cryptor;
import tencent.tls.tools.util;

public class req_transport extends oicq_request {
    public int _req_transport_eext_head_len;
    public int _rsp_transport_eext_head_len;

    public req_transport(req_global tencent_tls_request_req_global) {
        this._req_transport_eext_head_len = 0;
        this._rsp_transport_eext_head_len = 5;
        this._cmd = 2104;
        this._sub_cmd = 1;
        this._service_cmd = "wtlogin64.trans_emp";
        this._g = tencent_tls_request_req_global;
        this._g._encrypt_type = 0;
    }

    public int get_port(boolean z) {
        return z ? 8080 : 8080;
    }

    public Socket get_sk() {
        if (this._g._transport_sk != null) {
            QLog.d("_transport_sk" + this._g._transport_sk.toString());
        } else {
            QLog.d("_transport_sk null");
        }
        return this._g._transport_sk;
    }

    public void set_sk(Socket socket) {
        this._g._transport_sk = socket;
    }

    public byte[] get_request_body(byte[] bArr, byte[] bArr2, long j, long j2, int i) {
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
        if (this._g._t172_data != null && this._g._t172_data.length > 0) {
            Object obj2 = new tlv_t172().get_tlv_172(this._g._t172_data);
            obj = new byte[(obj2.length + 2)];
            util.int16_to_buf(obj, 0, 1);
            System.arraycopy(obj2, 0, obj, 2, obj2.length);
        }
        Object obj3 = obj;
        this._req_transport_eext_head_len = ((bArr2.length + 13) + 1) + obj3.length;
        obj = new byte[(bArr.length + this._req_transport_eext_head_len)];
        util.int8_to_buf(obj, 0, i2);
        util.int16_to_buf(obj, 1, bArr.length);
        util.int32_to_buf(obj, 3, 0);
        util.int64_to_buf32(obj, 7, j2);
        util.int16_to_buf(obj, 11, bArr2.length);
        System.arraycopy(bArr2, 0, obj, 13, bArr2.length);
        i2 = bArr2.length + 13;
        util.int8_to_buf(obj, i2, obj3.length);
        i2++;
        System.arraycopy(obj3, 0, obj, i2, obj3.length);
        i2 += obj3.length;
        System.arraycopy(bArr, 0, obj, i2, bArr.length);
        i2 += bArr.length;
        return encrypt_body(obj);
    }

    protected byte[] encrypt_body(byte[] bArr) {
        if (this._g._encrypt_type == 0) {
            return ecdh_encrypt_body(bArr, this._g._rand_key, this._g._pub_key, this._g._share_key);
        }
        return kc_encrypt_body(bArr, this._g._rand_key, 3);
    }

    public int get_response_body(byte[] bArr, int i, int i2) {
        if (i2 < this._rsp_transport_eext_head_len) {
            return -1009;
        }
        int i3 = get_response_ret_code(bArr, i);
        set_err_msg(null);
        QLog.d("type=" + i3);
        return i3;
    }

    public synchronized int make_request(long j, TransReqContext transReqContext, byte[] bArr, byte[] bArr2, long j2, long j3, TLSUserInfo tLSUserInfo) {
        int i;
        int i2 = req_global._app_client_version;
        int i3 = 0;
        i = 0;
        while (true) {
            byte[] bArr3;
            Object obj = transReqContext._body;
            long currentTimeMillis = req_global._l_init_time + (System.currentTimeMillis() / 1000);
            if (obj == null) {
                bArr3 = new byte[0];
            } else if (bArr == null) {
                bArr3 = new byte[(obj.length + 4)];
                util.int64_to_buf32(bArr3, 0, currentTimeMillis);
                System.arraycopy(obj, 0, bArr3, 4, obj.length);
            } else {
                Object obj2 = new byte[(obj.length + 4)];
                util.int64_to_buf32(obj2, 0, currentTimeMillis);
                System.arraycopy(obj, 0, obj2, 4, obj.length);
                bArr3 = cryptor.encrypt(obj2, 0, obj2.length, bArr2);
            }
            if (bArr3 != null && bArr3.length > 0) {
                currentTimeMillis = j;
                int i4 = i2;
                get_request(this._default_client_version, this._cmd, this._default_client_seq, currentTimeMillis, this._default_ext_retry, this._default_ext_type, i4, this._default_ext_instance, get_request_body(bArr3, bArr, j2, j3, 0));
                i = snd_rcv_req();
                if (i == 0) {
                    i = get_response(transReqContext);
                    if (i == 0 && bArr != null) {
                        byte[] bArr4 = transReqContext.get_body();
                        transReqContext.set_body(cryptor.decrypt(bArr4, 0, bArr4.length, bArr2));
                    }
                    if (i != 180) {
                        break;
                    }
                }
                break;
            }
            int i5 = i3 + 1;
            if (i3 >= 1) {
                break;
            }
            i3 = i5;
        }
        QLog.d("req_transport rsp: ret=" + i);
        return i;
    }

    public int get_response(TransReqContext transReqContext) {
        int i = this._pos;
        if (i <= this._rsp_head_len + 2) {
            return -1009;
        }
        this._rsp_body_len = (i - this._rsp_head_len) - 2;
        if (this._g._encrypt_type == 0) {
            i = decrypt_body(this._buf, this._rsp_head_len + 1, this._rsp_body_len, this._g._share_key);
            if (i < 0) {
                QLog.i("use ecdh decrypt_body failed");
                i = decrypt_body(this._buf, this._rsp_head_len + 1, this._rsp_body_len, this._g._rand_key);
                if (i < 0) {
                    QLog.i("use kc decrypt_body failed");
                }
            }
        } else {
            i = decrypt_body(this._buf, this._rsp_head_len + 1, this._rsp_body_len, this._g._rand_key);
            if (i < 0) {
                QLog.i("use kc decrypt_body failed");
            }
        }
        if (i >= 0) {
            return get_response_body(this._buf, this._rsp_head_len + 1, this._rsp_body_len, transReqContext);
        }
        return i;
    }

    public int get_response_body(byte[] bArr, int i, int i2, TransReqContext transReqContext) {
        if (i2 < this._rsp_transport_eext_head_len) {
            return -1009;
        }
        int i3 = get_response_ret_code(bArr, i);
        set_err_msg((TLSErrInfo) null);
        QLog.d("type=" + i3);
        switch (i3) {
            case 0:
                Object obj = new byte[(i2 - this._rsp_transport_eext_head_len)];
                System.arraycopy(bArr, this._rsp_transport_eext_head_len + i, obj, 0, obj.length);
                transReqContext.set_body(obj);
                return i3;
            case 180:
                int i4 = (this._rsp_transport_eext_head_len + 2) + i;
                tlv_t172 tencent_tls_tlvs_tlv_t172 = new tlv_t172();
                i4 = tencent_tls_tlvs_tlv_t172.get_tlv(bArr, i4, (this._pos - i4) - 1);
                if (i4 > 0) {
                    this._g._encrypt_type = 1;
                    this._g._t172_data = tencent_tls_tlvs_tlv_t172.get_data();
                    QLog.i("get rollback sig");
                    i4 = i3;
                }
                return i4;
            default:
                return i3;
        }
    }
}
