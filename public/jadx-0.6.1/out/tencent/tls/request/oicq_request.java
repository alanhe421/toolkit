package tencent.tls.request;

import com.iflytek.speech.VoiceWakeuperAidl;
import com.tencent.qalsdk.base.a;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import tencent.tls.platform.SigType;
import tencent.tls.platform.TLSErrInfo;
import tencent.tls.report.QLog;
import tencent.tls.tlvs.tlv_t;
import tencent.tls.tlvs.tlv_t104;
import tencent.tls.tlvs.tlv_t105;
import tencent.tls.tlvs.tlv_t106;
import tencent.tls.tlvs.tlv_t113;
import tencent.tls.tlvs.tlv_t11f;
import tencent.tls.tlvs.tlv_t126;
import tencent.tls.tlvs.tlv_t130;
import tencent.tls.tlvs.tlv_t138;
import tencent.tls.tlvs.tlv_t146;
import tencent.tls.tlvs.tlv_t149;
import tencent.tls.tlvs.tlv_t150;
import tencent.tls.tlvs.tlv_t16a;
import tencent.tls.tlvs.tlv_t172;
import tencent.tls.tlvs.tlv_t183;
import tencent.tls.tlvs.tlv_t505;
import tencent.tls.tools.I18nMsg;
import tencent.tls.tools.I18nMsg.MSG_TYPE;
import tencent.tls.tools.cryptor;
import tencent.tls.tools.util;

public class oicq_request {
    static final int ECDH_KEY = 0;
    static final int KC_KEY = 1;
    static String _save_host = "";
    static String[] _static_web_wlogin_ip = new String[]{"183.62.104.188"};
    static String[] _static_wlogin_ip = new String[]{"183.62.104.188"};
    static int _test = 0;
    static String _test_host = "";
    protected byte[] _buf = new byte[this._max];
    protected int _cmd = 0;
    protected int _default_client_seq = 0;
    protected int _default_client_version = 8002;
    protected int _default_ext_instance = 0;
    protected int _default_ext_no = 0;
    protected int _default_ext_retry = 0;
    protected int _default_ext_type = 2;
    protected int _default_ext_version = 3;
    protected int _default_ext_version1 = 0;
    public req_global _g;
    int _max = 4096;
    int _pos = 0;
    byte[] _recv_ret_buf = new byte[10240];
    int _rep_body_len = 0;
    int _req_head_len = 31;
    byte _ret;
    protected int _rsp_body_len = 0;
    public int _rsp_head_len = 19;
    InetSocketAddress _server_ip = null;
    int _server_port = 0;
    protected String _service_cmd = "";
    protected int _sub_cmd = 0;

    public String get_static_ip(boolean z) {
        if (z) {
            return _static_web_wlogin_ip[((int) (Math.random() * 2.147483647E9d)) % _static_web_wlogin_ip.length];
        }
        return _static_wlogin_ip[((int) (Math.random() * 2.147483647E9d)) % _static_wlogin_ip.length];
    }

    public static void set_test(int i, String str) {
        _test = i;
        _test_host = str;
    }

    public void fill_head(int i, int i2, int i3, long j, int i4, int i5, int i6, int i7, int i8) {
        int i9 = this._default_client_seq + 1;
        this._default_client_seq = i9;
        this._pos = 0;
        util.int8_to_buf(this._buf, this._pos, 2);
        this._pos++;
        util.int16_to_buf(this._buf, this._pos, (this._req_head_len + 2) + i8);
        this._pos += 2;
        util.int16_to_buf(this._buf, this._pos, i);
        this._pos += 2;
        util.int16_to_buf(this._buf, this._pos, i2);
        this._pos += 2;
        util.int16_to_buf(this._buf, this._pos, i9);
        this._pos += 2;
        util.int64_to_buf(this._buf, this._pos, j);
        this._pos += 8;
        util.int8_to_buf(this._buf, this._pos, 3);
        this._pos++;
        util.int8_to_buf(this._buf, this._pos, 7);
        this._pos++;
        util.int8_to_buf(this._buf, this._pos, i4);
        this._pos++;
        util.int32_to_buf(this._buf, this._pos, i5);
        this._pos += 4;
        util.int32_to_buf(this._buf, this._pos, i6);
        this._pos += 4;
        util.int32_to_buf(this._buf, this._pos, i7);
        this._pos += 4;
    }

    public void fill_end() {
        util.int8_to_buf(this._buf, this._pos, 3);
        this._pos++;
    }

    public void fill_body(byte[] bArr, int i) {
        if ((this._pos + i) + 1 > this._max) {
            this._max = ((this._pos + i) + 1) + 128;
            Object obj = new byte[this._max];
            System.arraycopy(this._buf, 0, obj, 0, this._pos);
            this._buf = obj;
        }
        System.arraycopy(bArr, 0, this._buf, this._pos, i);
        this._pos += i;
    }

    public void fill(int i, int i2, int i3, long j, int i4, int i5, int i6, int i7, byte[] bArr, int i8) {
        fill_head(i, i2, i3, j, i4, i5, i6, i7, i8);
        fill_body(bArr, i8);
        fill_end();
    }

    public void get_request(int i, int i2, int i3, long j, int i4, int i5, int i6, int i7, byte[] bArr) {
        fill(i, i2, i3, j, i4, i5, i6, i7, bArr, bArr.length);
    }

    public int get_response() {
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
            return get_response_body(this._buf, this._rsp_head_len + 1, this._rsp_body_len);
        }
        return i;
    }

    public void set_buf(byte[] bArr, int i) {
        if (i > this._max) {
            this._max = i + 128;
            this._buf = new byte[this._max];
        }
        this._pos = i;
        System.arraycopy(bArr, 0, this._buf, 0, i);
    }

    public byte[] get_buf() {
        Object obj = new byte[this._pos];
        System.arraycopy(this._buf, 0, obj, 0, this._pos);
        return obj;
    }

    public int decrypt_body(byte[] bArr, int i, int i2, byte[] bArr2) {
        Object decrypt = cryptor.decrypt(bArr, i, i2, bArr2);
        if (decrypt == null) {
            return -1002;
        }
        this._rsp_body_len = decrypt.length;
        if ((decrypt.length + this._rsp_head_len) + 2 > this._max) {
            this._max = (decrypt.length + this._rsp_head_len) + 2;
            Object obj = new byte[this._max];
            System.arraycopy(this._buf, 0, obj, 0, this._pos);
            this._buf = obj;
        }
        this._pos = 0;
        System.arraycopy(decrypt, 0, this._buf, i, decrypt.length);
        this._pos = (decrypt.length + (this._rsp_head_len + 2)) + this._pos;
        return 0;
    }

    byte[] ecdh_encrypt_body(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
        if (bArr == null || bArr2 == null || bArr3 == null || bArr4 == null) {
            return new byte[0];
        }
        Object encrypt = cryptor.encrypt(bArr, 0, bArr.length, bArr4);
        byte[] bArr5 = new byte[(((((bArr2.length + 2) + 2) + 2) + bArr3.length) + encrypt.length)];
        util.int8_to_buf(bArr5, 0, 1);
        util.int8_to_buf(bArr5, 1, 1);
        System.arraycopy(bArr2, 0, bArr5, 2, bArr2.length);
        int length = bArr2.length + 2;
        util.int16_to_buf(bArr5, length, VoiceWakeuperAidl.RES_SPECIFIED);
        length += 2;
        util.int16_to_buf(bArr5, length, bArr3.length);
        length += 2;
        System.arraycopy(bArr3, 0, bArr5, length, bArr3.length);
        length += bArr3.length;
        System.arraycopy(encrypt, 0, bArr5, length, encrypt.length);
        int length2 = encrypt.length + length;
        QLog.d("ecdh req body " + bArr5.length);
        return bArr5;
    }

    byte[] kc_encrypt_body(byte[] bArr, byte[] bArr2, int i) {
        if (bArr == null || bArr2 == null) {
            return new byte[0];
        }
        Object encrypt = cryptor.encrypt(bArr, 0, bArr.length, bArr2);
        byte[] bArr3 = new byte[((((bArr2.length + 2) + 2) + 2) + encrypt.length)];
        util.int8_to_buf(bArr3, 0, 1);
        util.int8_to_buf(bArr3, 1, i);
        System.arraycopy(bArr2, 0, bArr3, 2, bArr2.length);
        int length = bArr2.length + 2;
        util.int16_to_buf(bArr3, length, VoiceWakeuperAidl.RES_SPECIFIED);
        length += 2;
        util.int16_to_buf(bArr3, length, 0);
        length += 2;
        System.arraycopy(encrypt, 0, bArr3, length, encrypt.length);
        int length2 = encrypt.length + length;
        QLog.d("kc req body " + bArr3.length);
        return bArr3;
    }

    byte[] kc_encrypt_body(byte[] bArr, byte[] bArr2) {
        return kc_encrypt_body(bArr, bArr2, 2);
    }

    byte[] encrypt_body(byte[] bArr, int i, int i2) {
        QLog.i("subcmd 0x" + Integer.toHexString(i));
        Object obj = new byte[(bArr.length + 4)];
        util.int16_to_buf(obj, 0, i);
        util.int16_to_buf(obj, 2, i2);
        System.arraycopy(bArr, 0, obj, 4, bArr.length);
        if (this._g._encrypt_type == 0) {
            return ecdh_encrypt_body(obj, this._g._rand_key, this._g._pub_key, this._g._share_key);
        }
        return kc_encrypt_body(obj, this._g._rand_key);
    }

    protected byte[] encrypt_body(byte[] bArr) {
        if (this._g._encrypt_type == 0) {
            return ecdh_encrypt_body(bArr, this._g._rand_key, this._g._pub_key, this._g._share_key);
        }
        return kc_encrypt_body(bArr, this._g._rand_key);
    }

    public Socket get_sk() {
        if (this._g._sk != null) {
            QLog.d("_sk" + this._g._sk.toString());
        } else {
            QLog.d("_sknull");
        }
        return this._g._sk;
    }

    public void set_sk(Socket socket) {
        this._g._sk = socket;
    }

    public String get_host(boolean z) {
        String[] strArr = new String[2];
        if (z) {
            strArr[0] = "ida1.qq.com";
            strArr[1] = "ida1.qq.com";
        } else {
            strArr[0] = "ida.qq.com";
            strArr[1] = "ida.qq.com";
        }
        return strArr[Math.abs(new Random().nextInt() % strArr.length)];
    }

    public int get_port(boolean z) {
        return z ? 443 : 443;
    }

    public String resolve_server_addr(int i, boolean z) {
        String str = "";
        int i2 = i / 2;
        if (_test != 0 && _test_host != null && _test_host.length() > 0) {
            str = _test_host;
        } else if (i2 < 1) {
            if (z) {
                if (req_global._network_type == 1) {
                    str = util.get_server_host(req_global._context, "wap-host1");
                } else if (req_global._network_type == 2) {
                    str = util.get_server_host(req_global._context, "wap-host2");
                }
            } else if (req_global._network_type == 1) {
                str = util.get_server_host(req_global._context, "host1");
            } else if (req_global._network_type == 2) {
                str = util.get_server_host(req_global._context, "host2");
            }
            if (str == null || str.length() <= 0) {
                str = get_host(z);
            }
        } else if (i2 < 2) {
            str = get_host(z);
        } else {
            str = get_static_ip(z);
        }
        _save_host = str;
        QLog.d("resolve_server_addr OK host:" + str + " tryno:" + i2);
        return str;
    }

    public int snd_rcv_req() {
        int snd_rcv_req_sso;
        if (this._g._use_sso_channel) {
            snd_rcv_req_sso = snd_rcv_req_sso();
        } else {
            snd_rcv_req_sso = snd_rcv_req_tcp();
        }
        if (snd_rcv_req_sso == -1000) {
            TLSErrInfo tLSErrInfo = new TLSErrInfo();
            tLSErrInfo.ErrCode = snd_rcv_req_sso;
            tLSErrInfo.Msg = I18nMsg.getMsg(MSG_TYPE.MSG_4);
            set_err_msg(tLSErrInfo);
        }
        return snd_rcv_req_sso;
    }

    public int snd_rcv_req_sso() {
        QLog.i(getClass().getName() + ":snd_rcv_req_sso ...", this._g._uin);
        int i = this._g._time_out;
        byte[] bArr = get_buf();
        int i2 = util.connRetryTimes;
        int i3 = 0;
        while (i2 > 0) {
            try {
                QLog.i("SSORunner service_cmd:" + this._service_cmd + " timeout:" + i, this._g._uin);
                SSORunner sSORunner = new SSORunner(this._service_cmd, bArr, i);
                sSORunner.run();
                byte[] resData = sSORunner.getResData();
                if (resData != null) {
                    set_buf(resData, resData.length);
                    i3 = 0;
                    break;
                }
                QLog.i("recv data from server failed, ret=" + sSORunner.getRet(), this._g._uin);
                if (a.d == sSORunner.getRet()) {
                    i3 = -1023;
                } else if (a.e == sSORunner.getRet()) {
                    i3 = -1000;
                } else {
                    i3 = -1000;
                }
                i2--;
            } catch (Throwable e) {
                QLog.e(e);
                i3 = -1000;
            }
        }
        QLog.i(getClass().getName() + ":snd_rcv_req_sso ret=" + i3, this._g._uin);
        return i3;
    }

    public int get_rsp_length(byte[] bArr) {
        return util.buf_to_int16(bArr, 1);
    }

    public int snd_rcv_req_tcp() {
        Throwable e;
        int i;
        QLog.i(getClass().getName() + ":snd_rcv_req_tcp ...", this._g._uin);
        byte[] bArr = get_buf();
        Socket socket = get_sk();
        int i2 = 0;
        int i3 = 0;
        while (i2 < 6) {
            Socket socket2;
            if (i2 != 0) {
                util.chg_retry_type(req_global._context);
            }
            util.is_wap_retry(req_global._context);
            this._server_ip = null;
            QLog.i("try bin connect " + i2 + " ...", this._g._uin);
            if (socket == null) {
                String str = "";
                if (this._server_ip == null) {
                    str = resolve_server_addr(i2, false);
                    QLog.i("DNS for " + str + " request ...", this._g._uin);
                    try {
                        this._server_port = get_port(false);
                        this._server_ip = DNS_resolver.get_DNS_resolver(str, this._server_port, (long) this._g._time_out);
                    } catch (Exception e2) {
                    }
                }
                if (this._server_ip == null) {
                    QLog.i("DNS for " + str + " request failed", this._g._uin);
                    i2++;
                    this._server_ip = null;
                    set_sk(null);
                    socket = null;
                } else {
                    QLog.i("DNS for " + str + "(" + this._server_ip.toString() + ") request OK", this._g._uin);
                }
            }
            if (socket == null) {
                try {
                    QLog.i("tcp connect to " + this._server_ip + " request ...", this._g._uin);
                    socket2 = new Socket();
                    set_sk(socket2);
                    socket2.connect(this._server_ip, this._g._time_out);
                    socket2.setSoTimeout(this._g._time_out);
                    socket2.setReceiveBufferSize(this._recv_ret_buf.length);
                    QLog.i("tcp connect to " + this._server_ip + " OK", this._g._uin);
                } catch (Throwable e3) {
                    QLog.e(e3);
                    i2++;
                    this._server_ip = null;
                    set_sk(null);
                    socket = null;
                }
            } else {
                socket2 = socket;
            }
            QLog.i("tcp request write ...", this._g._uin);
            OutputStream outputStream = socket2.getOutputStream();
            outputStream.write(bArr, 0, bArr.length);
            outputStream.flush();
            InputStream inputStream = socket2.getInputStream();
            socket = socket2;
            try {
                QLog.i("recv data from server ...", this._g._uin);
                i = 0;
                int i4 = 0;
                while (i4 < this._rsp_head_len + 1) {
                    i = inputStream.read(this._recv_ret_buf, i4, (this._rsp_head_len + 1) - i4);
                    if (i < 0) {
                        break;
                    }
                    i4 += i;
                }
                if (i >= 0) {
                    i3 = get_rsp_length(this._recv_ret_buf);
                    if (i3 > this._rsp_head_len + 1) {
                        if (i3 < this._recv_ret_buf.length) {
                            int i5 = this._rsp_head_len + 1;
                            i4 = i3 - i5;
                            while (i4 > 0) {
                                i = inputStream.read(this._recv_ret_buf, i5, i4);
                                if (i == -1) {
                                    break;
                                }
                                i5 += i;
                                i4 -= i;
                            }
                            if (i != -1) {
                                break;
                            }
                            i2++;
                            socket.close();
                            this._server_ip = null;
                            set_sk(null);
                            socket = null;
                        } else {
                            i2++;
                            socket.close();
                            this._server_ip = null;
                            set_sk(null);
                            socket = null;
                        }
                    } else {
                        i2++;
                        socket.close();
                        this._server_ip = null;
                        set_sk(null);
                        socket = null;
                    }
                } else {
                    i2++;
                    socket.close();
                    this._server_ip = null;
                    try {
                        set_sk(null);
                        socket = null;
                    } catch (Exception e4) {
                        e3 = e4;
                        socket = null;
                        QLog.e(e3);
                        i2++;
                        try {
                            if (socket.isConnected()) {
                                socket.close();
                            }
                        } catch (Exception e5) {
                        }
                        this._server_ip = null;
                        set_sk(null);
                        socket = null;
                    }
                }
            } catch (Exception e6) {
                e3 = e6;
                QLog.e(e3);
                i2++;
                if (socket.isConnected()) {
                    socket.close();
                }
                this._server_ip = null;
                set_sk(null);
                socket = null;
            }
        }
        if (i2 >= 6) {
            i = -1000;
        } else {
            i = 0;
        }
        if (i == 0) {
            set_buf(this._recv_ret_buf, i3);
        }
        QLog.i(getClass().getName() + ":snd_rcv_req_tcp ret=" + i, this._g._uin);
        return i;
    }

    public int get_response_ret_code(byte[] bArr, int i) {
        this._ret = bArr[i];
        return bArr[i] & 255;
    }

    public String toString() {
        String str = "";
        for (int i = 0; i < this._pos; i++) {
            str = (str + Integer.toHexString((this._buf[i] >> 4) & 15)) + Integer.toHexString(this._buf[i] & 15);
        }
        return str;
    }

    public void get_err_msg(byte[] bArr, int i, int i2) {
        tlv_t146 tencent_tls_tlvs_tlv_t146 = new tlv_t146();
        int i3 = tencent_tls_tlvs_tlv_t146.get_tlv(bArr, i, i2);
        async_context tencent_tls_request_async_context = req_global.get_async_data(this._g._seq);
        if (i3 >= 0) {
            tencent_tls_request_async_context._last_err_msg = new TLSErrInfo(tencent_tls_tlvs_tlv_t146.errType, tencent_tls_tlvs_tlv_t146.errTitle, tencent_tls_tlvs_tlv_t146.errMsg, tencent_tls_tlvs_tlv_t146.extraErrMsg);
            QLog.i("t146 errtype: " + tencent_tls_tlvs_tlv_t146.errType);
        }
    }

    public void set_err_msg(TLSErrInfo tLSErrInfo) {
        async_context tencent_tls_request_async_context = req_global.get_async_data(this._g._seq);
        if (tLSErrInfo != null) {
            tencent_tls_request_async_context._last_err_msg = tLSErrInfo;
        } else {
            tencent_tls_request_async_context._last_err_msg = new TLSErrInfo();
        }
    }

    public void show_alert_dialog(tlv_t149 tencent_tls_tlvs_tlv_t149) {
        if (tencent_tls_tlvs_tlv_t149 != null) {
            try {
                new alert_thread(req_global._context, new TLSErrInfo(tencent_tls_tlvs_tlv_t149.get_type(), tencent_tls_tlvs_tlv_t149.get_title(), tencent_tls_tlvs_tlv_t149.get_content(), tencent_tls_tlvs_tlv_t149.get_otherinfo())).start();
            } catch (Exception e) {
            }
        }
    }

    public static byte[] encrypt_a1(byte[] bArr, byte[] bArr2) {
        Object obj = new byte[(bArr.length + bArr2.length)];
        System.arraycopy(bArr, 0, obj, 0, bArr.length);
        System.arraycopy(bArr2, 0, obj, bArr.length, bArr2.length);
        return obj;
    }

    public byte[] decrypt_a1(byte[] bArr) {
        Object decrypt;
        Object obj;
        String str = "%4;7t>;28<fc.5*6";
        if (req_global._IMEI_KEY == null || req_global._IMEI_KEY.length <= 0) {
            decrypt = cryptor.decrypt(bArr, 0, bArr.length, str.getBytes());
        } else {
            Object obj2 = new byte[16];
            if (req_global._IMEI_KEY.length > obj2.length) {
                System.arraycopy(req_global._IMEI_KEY, 0, obj2, 0, obj2.length);
            } else {
                System.arraycopy(req_global._IMEI_KEY, 0, obj2, 0, req_global._IMEI_KEY.length);
                for (int length = req_global._IMEI_KEY.length; length < obj2.length; length++) {
                    obj2[length] = (byte) (length + 1);
                }
            }
            decrypt = cryptor.decrypt(bArr, 0, bArr.length, obj2);
            if (decrypt == null || decrypt.length <= 0) {
                decrypt = cryptor.decrypt(bArr, 0, bArr.length, str.getBytes());
            }
        }
        if (decrypt == null) {
            obj = (byte[]) bArr.clone();
        } else {
            obj = decrypt;
        }
        if (obj == null || obj.length < 16) {
            return null;
        }
        int length2 = obj.length - 16;
        byte[] bArr2 = new byte[length2];
        System.arraycopy(obj, 0, bArr2, 0, length2);
        Object obj3 = new byte[16];
        System.arraycopy(obj, length2, obj3, 0, 16);
        req_global.get_async_data(this._g._seq)._tgtgt_key = obj3;
        return bArr2;
    }

    void set_server_host(int i, byte[] bArr, int i2) {
        if (bArr != null && bArr.length > 0) {
            if (i == 1) {
                if (req_global._network_type == 1) {
                    util.set_server_host(req_global._context, bArr, "host1");
                } else if (req_global._network_type == 2) {
                    util.set_server_host(req_global._context, bArr, "host2");
                }
            } else if (i == 2) {
                if (req_global._network_type == 1) {
                    util.set_server_host(req_global._context, bArr, "wap-host1");
                } else if (req_global._network_type == 2) {
                    util.set_server_host(req_global._context, bArr, "wap-host2");
                }
            }
            QLog.i("net type:" + req_global._network_type + " type:" + i + " host:" + new String(bArr) + " port:" + i2, this._g._uin);
        }
    }

    public int parse_t173(tlv_t tencent_tls_tlvs_tlv_t) {
        Object obj = tencent_tls_tlvs_tlv_t.get_data();
        if (obj != null && obj.length > 2) {
            int buf_to_int8 = util.buf_to_int8(obj, 1);
            int i = 2;
            for (int i2 = 0; i2 < buf_to_int8 && obj.length >= i + 1; i2++) {
                int buf_to_int82 = util.buf_to_int8(obj, i);
                i++;
                if (obj.length < i + 2) {
                    break;
                }
                int buf_to_int16 = util.buf_to_int16(obj, i);
                i += 2;
                if (obj.length < i + buf_to_int16) {
                    break;
                }
                Object obj2 = new byte[buf_to_int16];
                System.arraycopy(obj, i, obj2, 0, buf_to_int16);
                i += buf_to_int16;
                if (obj.length < i + 2) {
                    break;
                }
                buf_to_int16 = util.buf_to_int16(obj, i);
                i += 2;
                set_server_host(buf_to_int82, obj2, buf_to_int16);
            }
        }
        return 0;
    }

    public int parse_t161(tlv_t tencent_tls_tlvs_tlv_t) {
        tlv_t tencent_tls_tlvs_tlv_t2 = new tlv_t(371);
        tlv_t172 tencent_tls_tlvs_tlv_t172 = new tlv_t172();
        byte[] bArr = tencent_tls_tlvs_tlv_t.get_data();
        int length = bArr.length;
        if (tencent_tls_tlvs_tlv_t2.get_tlv(bArr, 2, length) > 0) {
            parse_t173(tencent_tls_tlvs_tlv_t2);
        }
        if (tencent_tls_tlvs_tlv_t172.get_tlv(bArr, 2, length) > 0) {
            this._g._encrypt_type = 1;
            this._g._t172_data = tencent_tls_tlvs_tlv_t172.get_data();
            QLog.i("get rollback sig");
        }
        return 0;
    }

    public int get_response_body(byte[] bArr, int i, int i2) {
        int i3;
        long j = 4294967295L;
        tlv_t104 tencent_tls_tlvs_tlv_t104 = new tlv_t104();
        tlv_t105 tencent_tls_tlvs_tlv_t105 = new tlv_t105();
        tlv_t113 tencent_tls_tlvs_tlv_t113 = new tlv_t113();
        tlv_t tencent_tls_tlvs_tlv_t = new tlv_t(281);
        tlv_t tencent_tls_tlvs_tlv_t2 = new tlv_t(269);
        tencent_tls_tlvs_tlv_t2 = new tlv_t(266);
        tlv_t130 tencent_tls_tlvs_tlv_t130 = new tlv_t130();
        tlv_t106 tencent_tls_tlvs_tlv_t106 = new tlv_t106();
        tencent_tls_tlvs_tlv_t2 = new tlv_t(268);
        tlv_t11f tencent_tls_tlvs_tlv_t11f = new tlv_t11f();
        tlv_t138 tencent_tls_tlvs_tlv_t138 = new tlv_t138();
        tlv_t149 tencent_tls_tlvs_tlv_t149 = new tlv_t149();
        tlv_t150 tencent_tls_tlvs_tlv_t150 = new tlv_t150();
        tencent_tls_tlvs_tlv_t2 = new tlv_t(ErrorCode.ERROR_TBSINSTALLER_ISTBSCORELEGAL_03);
        tencent_tls_tlvs_tlv_t2 = new tlv_t(773);
        tlv_t16a tencent_tls_tlvs_tlv_t16a = new tlv_t16a();
        tencent_tls_tlvs_tlv_t2 = new tlv_t(353);
        tencent_tls_tlvs_tlv_t2 = new tlv_t(372);
        tlv_t126 tencent_tls_tlvs_tlv_t126 = new tlv_t126();
        tlv_t505 tencent_tls_tlvs_tlv_t505 = new tlv_t505();
        tlv_t183 tencent_tls_tlvs_tlv_t183 = new tlv_t183();
        async_context tencent_tls_request_async_context = req_global.get_async_data(this._g._seq);
        long j2 = tencent_tls_request_async_context._src_appid;
        long j3 = tencent_tls_request_async_context._appid;
        switch (this._sub_cmd) {
            case 2:
            case 3:
                i3 = 0;
                break;
            case 7:
                i3 = 6;
                break;
            case 9:
                i3 = 0;
                break;
            case 15:
                i3 = 5;
                break;
            case 17:
            case 19:
                i3 = 0;
                break;
            case 18:
                i3 = 7;
                break;
            case 25:
                i3 = 8;
                break;
            case 26:
                i3 = 9;
                break;
            default:
                return -1012;
        }
        if (i2 < 5) {
            return -1009;
        }
        int i4 = get_response_ret_code(bArr, i + 2);
        QLog.i("get_response_body type=" + i4);
        int i5 = i + 5;
        this._g._t150 = null;
        tlv_t tencent_tls_tlvs_tlv_t3;
        switch (i4) {
            case 0:
                if (i3 != 7 && i3 != 8) {
                    int length;
                    long j4;
                    ArrayList arrayList;
                    if (i3 != 9) {
                        if (i3 == 0) {
                            if (tencent_tls_tlvs_tlv_t113.get_tlv(bArr, i5, this._pos - i5) < 0) {
                                i4 = -1003;
                                break;
                            }
                            this._g._uin = tencent_tls_tlvs_tlv_t113.get_uin();
                            this._g.put_account(this._g._userid, this._g._uin);
                        }
                        if (tencent_tls_tlvs_tlv_t150.get_tlv(bArr, i5, (this._pos - i5) - 1) >= 0) {
                            this._g._t150 = tencent_tls_tlvs_tlv_t150;
                        }
                        if (tencent_tls_tlvs_tlv_t2.get_tlv(bArr, i5, (this._pos - i5) - 1) >= 0) {
                            parse_t161(tencent_tls_tlvs_tlv_t2);
                            QLog.d("parse t161 called");
                        }
                        i4 = tencent_tls_tlvs_tlv_t.get_tlv(bArr, i5, (this._pos - i5) - 1, tencent_tls_request_async_context._tgtgt_key);
                        if (i4 >= 0) {
                            byte[] bArr2 = tencent_tls_tlvs_tlv_t.get_data();
                            length = bArr2.length;
                            if (tencent_tls_tlvs_tlv_t149.get_tlv(bArr2, 2, length) > 0) {
                                show_alert_dialog(tencent_tls_tlvs_tlv_t149);
                            }
                            if (tencent_tls_tlvs_tlv_t130.get_tlv(bArr2, 2, length) > 0) {
                                this._g.set_time_ip(tencent_tls_tlvs_tlv_t130.get_time(), tencent_tls_tlvs_tlv_t130.get_ipaddr());
                            }
                            tencent_tls_tlvs_tlv_t2.get_tlv(bArr2, 2, length);
                            tencent_tls_tlvs_tlv_t2.get_tlv(bArr2, 2, length);
                            tencent_tls_tlvs_tlv_t2.get_tlv(bArr2, 2, length);
                            tencent_tls_tlvs_tlv_t2.get_tlv(bArr2, 2, length);
                            tencent_tls_tlvs_tlv_t2 = new tlv_t(1283);
                            tencent_tls_tlvs_tlv_t2.get_tlv(bArr2, 2, length);
                            if (tencent_tls_tlvs_tlv_t11f.get_tlv(bArr2, 2, length) >= 0) {
                                j = ((long) tencent_tls_tlvs_tlv_t11f.get_tk_pri()) & 4294967295L;
                            }
                            QLog.i("sdkAppid:" + tencent_tls_request_async_context._appid + " tk_valid:" + 2160000 + " a2_valid:" + 2160000, this._g._uin);
                            byte[] bArr3 = new byte[0];
                            byte[] bArr4 = new byte[0];
                            int i6 = tencent_tls_tlvs_tlv_t2.get_tlv(bArr2, 2, length);
                            if (tencent_tls_tlvs_tlv_t106.get_tlv(bArr2, 2, length) >= 0 && i6 >= 0) {
                                bArr3 = encrypt_a1(tencent_tls_tlvs_tlv_t106.get_data(), tencent_tls_tlvs_tlv_t2.get_data());
                            }
                            if (tencent_tls_tlvs_tlv_t16a.get_tlv(bArr2, 2, length) >= 0) {
                                bArr4 = tencent_tls_tlvs_tlv_t16a.get_data();
                            }
                            j4 = req_global.get_cur_time();
                            arrayList = new ArrayList();
                            arrayList.add(new Ticket(64, tencent_tls_tlvs_tlv_t2.get_data(), tencent_tls_tlvs_tlv_t2.get_data(), j4, 2160000 + j4));
                            arrayList.add(new Ticket(262144, tencent_tls_tlvs_tlv_t2.get_data(), tencent_tls_tlvs_tlv_t2.get_data(), j4, j4 + 1728000));
                            arrayList.add(new Ticket(SigType.TLS, tencent_tls_tlvs_tlv_t2.get_data(), null, j4, j4 + 2160000));
                            i4 = this._g.put_siginfo(this._g._uin, j2, bArr3, bArr4, j3, j, j4, arrayList, tencent_tls_request_async_context._login_bitmap);
                            if (i4 == 0) {
                                i4 = 0;
                                break;
                            }
                            TLSErrInfo tLSErrInfo = new TLSErrInfo();
                            tLSErrInfo.ErrCode = i4;
                            tLSErrInfo.Msg = I18nMsg.getMsg(MSG_TYPE.MSG_2);
                            set_err_msg(tLSErrInfo);
                            QLog.i("put_siginfo fail,ret=", this._g._uin);
                            break;
                        }
                        QLog.d("119 can not decrypt");
                        break;
                    }
                    if (tencent_tls_tlvs_tlv_t113.get_tlv(bArr, i5, this._pos - i5) >= 0) {
                        this._g._uin = tencent_tls_tlvs_tlv_t113.get_uin();
                        tlv_t tencent_tls_tlvs_tlv_t4 = new tlv_t(1286);
                        if (tencent_tls_tlvs_tlv_t4.get_tlv(bArr, i5, this._pos - i5) >= 0) {
                            this._g._admin = util.buf_to_int32(tencent_tls_tlvs_tlv_t4.get_data(), 0);
                        }
                        tencent_tls_tlvs_tlv_t3 = new tlv_t(274);
                        length = tencent_tls_tlvs_tlv_t3.get_tlv(bArr, i5, this._pos - i5);
                        String str = this._g._userid;
                        if (length >= 0) {
                            str = new String(tencent_tls_tlvs_tlv_t3.get_data());
                        }
                        this._g.put_open_account(this._g._userid, str, this._g._uin);
                        this._g._userid = str;
                        tencent_tls_tlvs_tlv_t2.get_tlv(bArr, i5, this._pos - i5);
                        tencent_tls_tlvs_tlv_t2.get_tlv(bArr, i5, this._pos - i5);
                        tencent_tls_tlvs_tlv_t2.get_tlv(bArr, i5, this._pos - i5);
                        j4 = req_global.get_cur_time();
                        arrayList = new ArrayList();
                        arrayList.add(new Ticket(64, tencent_tls_tlvs_tlv_t2.get_data(), tencent_tls_tlvs_tlv_t2.get_data(), j4, 2160000 + j4));
                        arrayList.add(new Ticket(262144, tencent_tls_tlvs_tlv_t2.get_data(), tencent_tls_tlvs_tlv_t2.get_data(), j4, j4 + 1728000));
                        this._g.put_siginfo(this._g._uin, j2, new byte[0], new byte[0], j3, 4294967295L, j4, arrayList, tencent_tls_request_async_context._login_bitmap);
                        i4 = 0;
                        break;
                    }
                    i4 = -1003;
                    break;
                }
                if (i3 == 8) {
                    i4 = tencent_tls_tlvs_tlv_t183.get_tlv(bArr, i5, this._pos - i5);
                    if (i4 >= 0) {
                        tencent_tls_request_async_context._msalt = tencent_tls_tlvs_tlv_t183.getMsalt();
                        this._g.put_account(this._g._userid, this._g._uin);
                    }
                }
                i4 = tencent_tls_tlvs_tlv_t104.get_tlv(bArr, i5, this._pos - i5);
                if (i4 >= 0) {
                    tencent_tls_request_async_context._t104 = tencent_tls_tlvs_tlv_t104;
                    i4 = 0;
                    break;
                }
                break;
            case 1:
            case 15:
                this._g.clear_sig(this._g._uin, j2);
                get_err_msg(bArr, i5, (this._pos - i5) - 1);
                break;
            case 2:
                i3 = tencent_tls_tlvs_tlv_t104.get_tlv(bArr, i5, (this._pos - i5) - 1);
                if (i3 >= 0) {
                    tencent_tls_request_async_context._t104 = tencent_tls_tlvs_tlv_t104;
                    i3 = tencent_tls_tlvs_tlv_t105.get_tlv(bArr, i5, (this._pos - i5) - 1);
                    if (i3 >= 0) {
                        tencent_tls_request_async_context._t105 = tencent_tls_tlvs_tlv_t105;
                        tencent_tls_tlvs_tlv_t3 = new tlv_t(1284);
                        if (tencent_tls_tlvs_tlv_t3.get_tlv(bArr, i5, (this._pos - i5) - 1) >= 0) {
                            QLog.i("got t504");
                        }
                        set_err_msg(new TLSErrInfo(i4, null, new String(tencent_tls_tlvs_tlv_t3.get_data())));
                        break;
                    }
                    i4 = i3;
                    break;
                }
                i4 = i3;
                break;
            case 16:
                i3 = tencent_tls_tlvs_tlv_t130.get_tlv(bArr, i5, (this._pos - i5) - 1);
                if (i3 >= 0) {
                    this._g.set_time_ip(tencent_tls_tlvs_tlv_t130.get_time(), tencent_tls_tlvs_tlv_t130.get_ipaddr());
                    get_err_msg(bArr, i5, (this._pos - i5) - 1);
                    break;
                }
                i4 = i3;
                break;
            case 160:
                i3 = tencent_tls_tlvs_tlv_t104.get_tlv(bArr, i5, (this._pos - i5) - 1);
                if (i3 >= 0) {
                    tencent_tls_request_async_context._t104 = tencent_tls_tlvs_tlv_t104;
                    i3 = tencent_tls_tlvs_tlv_t2.get_tlv(bArr, i5, (this._pos - i5) - 1);
                    if (i3 >= 0) {
                        tencent_tls_request_async_context._t174 = tencent_tls_tlvs_tlv_t2;
                        get_err_msg(bArr, i5, (this._pos - i5) - 1);
                        break;
                    }
                    i4 = i3;
                    break;
                }
                i4 = i3;
                break;
            case Opcodes.ADD_INT_2ADDR /*176*/:
                get_err_msg(bArr, i5, (this._pos - i5) - 1);
                this._g.remove_account(this._g._userid);
                this._g.clear_sig(this._g._uin, 0);
                break;
            case 180:
                i3 = tencent_tls_tlvs_tlv_t2.get_tlv(bArr, i5, (this._pos - i5) - 1);
                if (i3 >= 0) {
                    parse_t161(tencent_tls_tlvs_tlv_t2);
                    QLog.d("0xb4 parse t161 called");
                    get_err_msg(bArr, i5, (this._pos - i5) - 1);
                    break;
                }
                i4 = i3;
                break;
            case 208:
                i4 = tencent_tls_tlvs_tlv_t104.get_tlv(bArr, i5, (this._pos - i5) - 1);
                if (i4 >= 0) {
                    tencent_tls_request_async_context._t104 = tencent_tls_tlvs_tlv_t104;
                    i4 = tencent_tls_tlvs_tlv_t126.get_tlv(bArr, i5, (this._pos - i5) - 1);
                    if (i4 >= 0) {
                        tencent_tls_request_async_context._t126 = tencent_tls_tlvs_tlv_t126;
                        i4 = tencent_tls_tlvs_tlv_t505.get_tlv(bArr, i5, (this._pos - i5) - 1);
                        if (i4 >= 0) {
                            tencent_tls_request_async_context._smslogin_reask = tencent_tls_tlvs_tlv_t505.getReask();
                            tencent_tls_request_async_context._smslogin_expire = tencent_tls_tlvs_tlv_t505.getExpire();
                            QLog.i("reask: " + tencent_tls_request_async_context._smslogin_reask + ", expire: " + tencent_tls_request_async_context._smslogin_expire);
                            i4 = tencent_tls_tlvs_tlv_t183.get_tlv(bArr, i5, (this._pos - i5) - 1);
                            if (i4 >= 0) {
                                tencent_tls_request_async_context._msalt = tencent_tls_tlvs_tlv_t183.getMsalt();
                                i4 = 0;
                                break;
                            }
                        }
                    }
                }
                break;
            case 255:
                get_err_msg(bArr, i5, (this._pos - i5) - 1);
                i4 = tencent_tls_request_async_context._last_err_msg.ErrCode;
                break;
            default:
                get_err_msg(bArr, i5, (this._pos - i5) - 1);
                break;
        }
        QLog.i("ret:" + (i4 > 0 ? "0x" + Integer.toHexString(i4) : Integer.valueOf(i4)), this._g._uin);
        tencent_tls_request_async_context._last_err_msg.ErrCode = i4;
        if (i4 == 0) {
            set_err_msg(null);
            tencent_tls_request_async_context._last_err_msg.ErrCode = 0;
        }
        if (i4 == 10 || i4 == Opcodes.XOR_LONG || i4 == Opcodes.SHR_LONG || i4 == Opcodes.USHR_LONG || i4 == Opcodes.ADD_FLOAT || i4 == Opcodes.USHR_INT || (i4 >= 128 && i4 <= Opcodes.INT_TO_SHORT)) {
            return -1000;
        }
        return i4;
    }
}
