package tencent.tls.request;

import com.tencent.qalsdk.base.a;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import java.util.List;
import tencent.tls.platform.TLSUserInfo;
import tencent.tls.report.QLog;
import tencent.tls.tlvs.tlv_t1;
import tencent.tls.tlvs.tlv_t100;
import tencent.tls.tlvs.tlv_t104;
import tencent.tls.tlvs.tlv_t106;
import tencent.tls.tlvs.tlv_t107;
import tencent.tls.tlvs.tlv_t109;
import tencent.tls.tlvs.tlv_t112;
import tencent.tls.tlvs.tlv_t116;
import tencent.tls.tlvs.tlv_t124;
import tencent.tls.tlvs.tlv_t128;
import tencent.tls.tlvs.tlv_t141;
import tencent.tls.tlvs.tlv_t142;
import tencent.tls.tlvs.tlv_t144;
import tencent.tls.tlvs.tlv_t145;
import tencent.tls.tlvs.tlv_t147;
import tencent.tls.tlvs.tlv_t154;
import tencent.tls.tlvs.tlv_t166;
import tencent.tls.tlvs.tlv_t16a;
import tencent.tls.tlvs.tlv_t16b;
import tencent.tls.tlvs.tlv_t16e;
import tencent.tls.tlvs.tlv_t172;
import tencent.tls.tlvs.tlv_t177;
import tencent.tls.tlvs.tlv_t18;
import tencent.tls.tlvs.tlv_t185;
import tencent.tls.tlvs.tlv_t502;
import tencent.tls.tlvs.tlv_t509;
import tencent.tls.tlvs.tlv_t8;
import tencent.tls.tools.util;

public class req_TGTGT extends oicq_request {
    public static final int SIGSRC_NORMAL = 3;

    public req_TGTGT(req_global tencent_tls_request_req_global) {
        this._cmd = a.b;
        this._sub_cmd = 9;
        this._service_cmd = "wtlogin64.login";
        this._g = tencent_tls_request_req_global;
        this._g._encrypt_type = 0;
    }

    public byte[] get_request_body(long j, long j2, int i, long j3, int i2, byte[] bArr, byte[] bArr2, int i3, byte[] bArr3, byte[] bArr4, boolean z, byte[] bArr5, byte[] bArr6, int i4, int i5, long[] jArr, int i6, long j4, int i7, int i8, int i9, int i10, int i11, byte[] bArr7, byte[] bArr8, byte[] bArr9, List<String> list) {
        int[] iArr = new int[]{24, 1, 262, 278, 256, 263, 260, ErrorCode.ERROR_TBSINSTALLER_ISTBSCORELEGAL_02, 274, 1282, ErrorCode.ERROR_SDKENGINE_CANLOADTBS, ErrorCode.THROWABLE_INITX5CORE, ErrorCode.TEST_THROWABLE_ISNOT_NULL, 358, 340, ErrorCode.ERROR_TBSINSTALLER_ISTBSCORELEGAL_01, 8, 363, 370, 375, 362, 389, 1289};
        final async_context tencent_tls_request_async_context = req_global.get_async_data(this._g._seq);
        final long j5 = tencent_tls_request_async_context._msalt;
        final long j6 = j;
        final int i12 = i;
        final long j7 = j3;
        final int i13 = i2;
        final byte[] bArr10 = bArr;
        final byte[] bArr11 = bArr5;
        final long j8 = j2;
        final byte[] bArr12 = bArr2;
        final byte[] bArr13 = bArr3;
        final byte[] bArr14 = bArr4;
        final byte[] bArr15 = bArr6;
        final long j9 = j4;
        final int i14 = i6;
        final int i15 = i8;
        final int i16 = i9;
        final int i17 = i10;
        final int i18 = i11;
        final int i19 = i4;
        final int i20 = i5;
        final long[] jArr2 = jArr;
        final byte[] bArr16 = bArr7;
        final byte[] bArr17 = bArr9;
        final byte[] bArr18 = bArr8;
        final List<String> list2 = list;
        final boolean z2 = z;
        TLVPacker anonymousClass1 = new TLVPacker(iArr) {
            protected byte[] loop(int i) {
                byte[] bArr = new byte[0];
                switch (i) {
                    case 1:
                        return new tlv_t1().get_tlv_1(j7, bArr10);
                    case 8:
                        return new tlv_t8().get_tlv_8(0, req_global._local_id, 0);
                    case 24:
                        return new tlv_t18().get_tlv_18(j6, i12, j7, i13);
                    case 256:
                        return new tlv_t100().get_tlv_100(j6, j9, i12, i14);
                    case 260:
                        if (bArr18 == null || bArr18.length <= 0) {
                            return bArr;
                        }
                        return new tlv_t104().get_tlv_104(bArr18);
                    case 262:
                        if (bArr11 == null || bArr11.length == 0) {
                            return new tlv_t106().get_tlv_106(j6, j8, i12, bArr12, bArr10, 1, bArr13, j5, bArr14, req_global._read_guid, req_global._IMEI, 3, req_TGTGT.this._g._userid.getBytes(), req_global._acc_type);
                        }
                        tlv_t106 tencent_tls_tlvs_tlv_t106 = new tlv_t106();
                        tencent_tls_tlvs_tlv_t106.set_data(bArr11, bArr11.length);
                        return tencent_tls_tlvs_tlv_t106.get_buf();
                    case 263:
                        return new tlv_t107().get_tlv_107(i15, i16, i17, i18);
                    case 274:
                        if (req_TGTGT.this._g._userid != null) {
                            return new tlv_t112().get_tlv_112(req_TGTGT.this._g._userid.getBytes());
                        }
                        return bArr;
                    case 278:
                        return new tlv_t116().get_tlv_116(i19, i20, jArr2);
                    case ErrorCode.ERROR_TBSINSTALLER_ISTBSCORELEGAL_01 /*321*/:
                        return new tlv_t141().get_tlv_141(req_global._sim_operator_name, req_global._network_type, req_global._apn);
                    case ErrorCode.ERROR_TBSINSTALLER_ISTBSCORELEGAL_02 /*322*/:
                        return new tlv_t142().get_tlv_142(bArr17);
                    case ErrorCode.ERROR_SDKENGINE_CANLOADTBS /*324*/:
                        byte[] bArr2;
                        bArr = new byte[0];
                        if (bArr16 == null || bArr16.length <= 0) {
                            bArr2 = bArr;
                        } else {
                            bArr2 = new tlv_t109().get_tlv_109(bArr16);
                        }
                        byte[] bArr3 = new tlv_t124().get_tlv_124(util.get_os_type(), util.get_os_version(), req_global._network_type, req_global._sim_operator_name, new byte[0], req_global._apn);
                        byte[] bArr4 = new tlv_t128().get_tlv_128(req_global._new_install, req_global._read_guid, req_global._guid_chg, req_global._dev_report, req_global._device, req_global._IMEI, null);
                        byte[] bArr5 = new tlv_t16e().get_tlv_16e(req_global._device);
                        return new tlv_t144().get_tlv_144(bArr2, bArr3, bArr4, bArr5, tencent_tls_request_async_context._tgtgt_key);
                    case ErrorCode.THROWABLE_INITX5CORE /*325*/:
                        return new tlv_t145().get_tlv_145(req_global._IMEI);
                    case ErrorCode.TEST_THROWABLE_ISNOT_NULL /*327*/:
                        return new tlv_t147().get_tlv_147(j6, req_global._apk_v, req_global._apk_sig);
                    case 340:
                        return new tlv_t154().get_tlv_154(req_TGTGT.this._g._sso_seq);
                    case 358:
                        if ((i19 & 128) != 0) {
                            return new tlv_t166().get_tlv_166(req_global._img_type);
                        }
                        return bArr;
                    case 362:
                        if (bArr15 == null || bArr15.length <= 0) {
                            return bArr;
                        }
                        return new tlv_t16a().get_tlv_16a(bArr15);
                    case 363:
                        if (list2 == null || list2.size() <= 0) {
                            return bArr;
                        }
                        return new tlv_t16b().get_tlv_16b(list2);
                    case 370:
                        if (req_TGTGT.this._g._t172_data == null || req_TGTGT.this._g._t172_data.length <= 0) {
                            return bArr;
                        }
                        return new tlv_t172().get_tlv_172(req_TGTGT.this._g._t172_data);
                    case 375:
                        return new tlv_t177().get_tlv_177(util.BUILD_TIME, util.SDK_VERSION);
                    case 389:
                        if (z2) {
                            return new tlv_t185().get_tlv_185(1);
                        }
                        return bArr;
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

    public int make_request(long j, long j2, int i, long j3, int i2, byte[] bArr, byte[] bArr2, int i3, byte[] bArr3, boolean z, int i4, int i5, long[] jArr, int i6, long j4, int i7, int i8, int i9, int i10, int i11, TLSUserInfo tLSUserInfo) {
        int i12 = req_global._app_client_version;
        async_context tencent_tls_request_async_context = req_global.get_async_data(this._g._seq);
        tencent_tls_request_async_context._tgtgt_key = util.get_rand_16byte(req_global._IMEI_KEY);
        byte[] bArr4 = tencent_tls_request_async_context._tgtgt_key;
        tlv_t104 tencent_tls_tlvs_tlv_t104 = tencent_tls_request_async_context._t104;
        if (tencent_tls_tlvs_tlv_t104 == null) {
            tencent_tls_tlvs_tlv_t104 = new tlv_t104();
        }
        int i13 = 0;
        while (true) {
            byte[] bArr5 = get_request_body(j, j2, i12, j3, i2, bArr, bArr2, i3, bArr3, bArr4, z, null, null, i4, i5, jArr, i6, j4, i12, i8, i9, i10, i11, req_global._IMEI, tencent_tls_tlvs_tlv_t104.get_data(), req_global._apk_id, null);
            get_request(this._default_client_version, this._cmd, this._default_client_seq, j3, this._default_ext_retry, this._default_ext_type, i12, this._default_ext_instance, bArr5);
            int snd_rcv_req = snd_rcv_req();
            if (snd_rcv_req != 0) {
                return snd_rcv_req;
            }
            int i14 = get_response();
            QLog.i("retry num:" + i13 + " ret:" + i14, j3);
            if (i14 != 180) {
                return i14;
            }
            snd_rcv_req = i13 + 1;
            if (i13 >= 1) {
                return i14;
            }
            i13 = snd_rcv_req;
        }
    }

    public int make_request(long j, long j2, int i, long j3, int i2, byte[] bArr, byte[] bArr2, byte[] bArr3, int i3, int i4, long[] jArr, int i5, long j4, int i6, int i7, int i8, int i9, int i10, TLSUserInfo tLSUserInfo) {
        int i11 = req_global._app_client_version;
        tlv_t104 tencent_tls_tlvs_tlv_t104 = req_global.get_async_data(this._g._seq)._t104;
        if (tencent_tls_tlvs_tlv_t104 == null) {
            tencent_tls_tlvs_tlv_t104 = new tlv_t104();
        }
        byte[] decrypt_a1 = decrypt_a1(bArr2);
        if (decrypt_a1 == null) {
            return -1014;
        }
        int i12 = 0;
        while (true) {
            byte[] bArr4 = get_request_body(j, j2, i11, j3, i2, bArr, null, 0, null, null, false, decrypt_a1, bArr3, i3, i4, jArr, i5, j4, i11, i7, i8, i9, i10, req_global._IMEI, tencent_tls_tlvs_tlv_t104.get_data(), req_global._apk_id, null);
            get_request(this._default_client_version, this._cmd, this._default_client_seq, j3, this._default_ext_retry, this._default_ext_type, i11, this._default_ext_instance, bArr4);
            int snd_rcv_req = snd_rcv_req();
            if (snd_rcv_req != 0) {
                return snd_rcv_req;
            }
            int i13 = get_response();
            QLog.i("retry num:" + i12 + " ret:" + i13, j3);
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
