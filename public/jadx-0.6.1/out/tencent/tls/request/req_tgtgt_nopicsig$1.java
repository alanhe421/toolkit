package tencent.tls.request;

import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import java.util.List;
import tencent.tls.tlvs.tlv_t1;
import tencent.tls.tlvs.tlv_t100;
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
import tencent.tls.tlvs.tlv_t502;
import tencent.tls.tlvs.tlv_t509;
import tencent.tls.tlvs.tlv_t8;
import tencent.tls.tools.util;

class req_tgtgt_nopicsig$1 extends TLVPacker {
    final /* synthetic */ req_tgtgt_nopicsig this$0;
    final /* synthetic */ byte[] val$A1;
    final /* synthetic */ byte[] val$IMEI;
    final /* synthetic */ byte[] val$apk_id;
    final /* synthetic */ long val$appid;
    final /* synthetic */ long[] val$appid_array;
    final /* synthetic */ int val$bitmap;
    final /* synthetic */ int val$cap_type;
    final /* synthetic */ byte[] val$client_ip;
    final /* synthetic */ int val$client_version;
    final /* synthetic */ List val$domains;
    final /* synthetic */ int val$getsig;
    final /* synthetic */ int val$getsig1;
    final /* synthetic */ byte[] val$no_pic_sig;
    final /* synthetic */ int val$pic_size;
    final /* synthetic */ int val$pic_type;
    final /* synthetic */ int val$rc;
    final /* synthetic */ int val$ret_type;
    final /* synthetic */ long val$sappid;
    final /* synthetic */ async_context val$thisContext;
    final /* synthetic */ long val$uin;
    final /* synthetic */ long val$wxappid;

    req_tgtgt_nopicsig$1(req_tgtgt_nopicsig tencent_tls_request_req_tgtgt_nopicsig, int[] iArr, long j, int i, long j2, int i2, byte[] bArr, byte[] bArr2, int i3, int i4, long[] jArr, long j3, int i5, int i6, int i7, int i8, int i9, byte[] bArr3, async_context tencent_tls_request_async_context, byte[] bArr4, byte[] bArr5, List list, long j4) {
        this.this$0 = tencent_tls_request_req_tgtgt_nopicsig;
        this.val$appid = j;
        this.val$client_version = i;
        this.val$uin = j2;
        this.val$rc = i2;
        this.val$client_ip = bArr;
        this.val$A1 = bArr2;
        this.val$bitmap = i3;
        this.val$getsig = i4;
        this.val$appid_array = jArr;
        this.val$wxappid = j3;
        this.val$getsig1 = i5;
        this.val$pic_type = i6;
        this.val$cap_type = i7;
        this.val$pic_size = i8;
        this.val$ret_type = i9;
        this.val$IMEI = bArr3;
        this.val$thisContext = tencent_tls_request_async_context;
        this.val$apk_id = bArr4;
        this.val$no_pic_sig = bArr5;
        this.val$domains = list;
        this.val$sappid = j4;
        super(iArr);
    }

    protected byte[] loop(int i) {
        byte[] bArr = new byte[0];
        switch (i) {
            case 1:
                return new tlv_t1().get_tlv_1(this.val$uin, this.val$client_ip);
            case 8:
                return new tlv_t8().get_tlv_8(0, req_global._local_id, 0);
            case 24:
                return new tlv_t18().get_tlv_18(this.val$appid, this.val$client_version, this.val$uin, this.val$rc);
            case 256:
                return new tlv_t100().get_tlv_100(this.val$appid, this.val$wxappid, this.val$client_version, this.val$getsig1);
            case 262:
                tlv_t106 tencent_tls_tlvs_tlv_t106 = new tlv_t106();
                tencent_tls_tlvs_tlv_t106.set_data(this.val$A1, this.val$A1.length);
                return tencent_tls_tlvs_tlv_t106.get_buf();
            case 263:
                return new tlv_t107().get_tlv_107(this.val$pic_type, this.val$cap_type, this.val$pic_size, this.val$ret_type);
            case 274:
                return new tlv_t112().get_tlv_112(this.this$0._g._userid.getBytes());
            case 278:
                return new tlv_t116().get_tlv_116(this.val$bitmap, this.val$getsig, this.val$appid_array);
            case ErrorCode.ERROR_TBSINSTALLER_ISTBSCORELEGAL_01 /*321*/:
                return new tlv_t141().get_tlv_141(req_global._sim_operator_name, req_global._network_type, req_global._apn);
            case ErrorCode.ERROR_TBSINSTALLER_ISTBSCORELEGAL_02 /*322*/:
                return new tlv_t142().get_tlv_142(this.val$apk_id);
            case ErrorCode.ERROR_SDKENGINE_CANLOADTBS /*324*/:
                byte[] bArr2;
                bArr = new byte[0];
                if (this.val$IMEI == null || this.val$IMEI.length <= 0) {
                    bArr2 = bArr;
                } else {
                    bArr2 = new tlv_t109().get_tlv_109(this.val$IMEI);
                }
                byte[] bArr3 = new tlv_t124().get_tlv_124(util.get_os_type(), util.get_os_version(), req_global._network_type, req_global._sim_operator_name, new byte[0], req_global._apn);
                byte[] bArr4 = new tlv_t128().get_tlv_128(req_global._new_install, req_global._read_guid, req_global._guid_chg, req_global._dev_report, req_global._device, req_global._IMEI, null);
                byte[] bArr5 = new tlv_t16e().get_tlv_16e(req_global._device);
                return new tlv_t144().get_tlv_144(bArr2, bArr3, bArr4, bArr5, this.val$thisContext._tgtgt_key);
            case ErrorCode.THROWABLE_INITX5CORE /*325*/:
                return new tlv_t145().get_tlv_145(req_global._IMEI);
            case ErrorCode.TEST_THROWABLE_ISNOT_NULL /*327*/:
                return new tlv_t147().get_tlv_147(this.val$sappid, req_global._apk_v, req_global._apk_sig);
            case 340:
                return new tlv_t154().get_tlv_154(this.this$0._g._sso_seq);
            case 358:
                if ((this.val$bitmap & 128) != 0) {
                    return new tlv_t166().get_tlv_166(req_global._img_type);
                }
                return bArr;
            case 362:
                return new tlv_t16a().get_tlv_16a(this.val$no_pic_sig);
            case 363:
                if (this.val$domains == null || this.val$domains.size() <= 0) {
                    return bArr;
                }
                return new tlv_t16b().get_tlv_16b(this.val$domains);
            case 370:
                if (this.this$0._g._t172_data == null || this.this$0._g._t172_data.length <= 0) {
                    return bArr;
                }
                return new tlv_t172().get_tlv_172(this.this$0._g._t172_data);
            case 375:
                return new tlv_t177().get_tlv_177(util.BUILD_TIME, util.SDK_VERSION);
            case 1282:
                return new tlv_t502().get_tlv_502(req_global._acc_type);
            case 1289:
                return new tlv_t509().get_tlv_509(req_global._apk_sig, req_global._apk_id);
            default:
                return bArr;
        }
    }
}
