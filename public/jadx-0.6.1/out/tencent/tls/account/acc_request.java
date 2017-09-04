package tencent.tls.account;

import java.nio.ByteBuffer;
import java.util.Arrays;
import tencent.tls.report.QLog;
import tencent.tls.tools.MD5;
import tencent.tls.tools.cryptor;
import tencent.tls.tools.util;

public class acc_request {
    public static final int CMD_GUEST = 118;
    public static final int CMD_REG_AC = 49;
    public static final int CMD_REG_CM = 54;
    public static final int CMD_REG_RA = 52;
    public static final int CMD_REG_VC = 53;
    public static final int CMD_RST_AC = 64;
    public static final int CMD_RST_CM = 67;
    public static final int CMD_RST_RA = 65;
    public static final int CMD_RST_VC = 66;
    public static final int CMD_SMS_AC = 33;
    public static final int CMD_SMS_CM = 38;
    public static final int CMD_SMS_RA = 36;
    public static final int CMD_SMS_VC = 37;
    public static final int CMD_STR_CM = 102;
    public static final int CMD_STR_QRY = 97;
    protected int _body_len = 0;
    protected int _cmd = 0;
    protected int _head_len = 11;
    protected int _os_type = 5;
    protected int _version = 1;

    public int get_cmd() {
        return this._cmd;
    }

    protected byte[] get_request(byte[] bArr) {
        ByteBuffer allocate = ByteBuffer.allocate((this._head_len + 2) + bArr.length);
        allocate.put((byte) 2);
        allocate.putShort((short) (((this._head_len + 1) + bArr.length) + 1));
        allocate.putShort((short) this._version);
        allocate.putShort((short) this._cmd);
        allocate.put((byte) 4);
        allocate.putInt(0);
        allocate.put(bArr);
        allocate.put((byte) 3);
        return allocate.array();
    }

    private static int check_header(byte[] bArr) {
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        try {
            wrap.get();
            if (wrap.getShort() != bArr.length) {
                return -1009;
            }
            wrap.getInt();
            return wrap.position() + wrap.get();
        } catch (Throwable e) {
            QLog.e(e);
            return -1009;
        }
    }

    public static int parse_checkvalid_rsp(int i, byte[] bArr, acc_status tencent_tls_account_acc_status) {
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        int check_header = check_header(bArr);
        if (check_header == -1009) {
            return -1009;
        }
        wrap.position(check_header);
        tencent_tls_account_acc_status.sec_ctrl_code = wrap.get();
        byte[] bArr2 = null;
        switch (i) {
            case 33:
            case 36:
            case 49:
            case 52:
            case 64:
            case 65:
                wrap.getShort();
                tencent_tls_account_acc_status.next_resend_time = wrap.getShort();
                tencent_tls_account_acc_status.total_time_over = wrap.getShort();
                break;
            case 37:
            case 53:
            case 66:
                acc_status.msalt = wrap.getLong();
                break;
            case 38:
            case 54:
            case CMD_RST_CM /*67*/:
                byte[] bArr3 = new byte[wrap.getShort()];
                wrap.get(bArr3);
                if (tencent_tls_account_acc_status.msgcode == null || tencent_tls_account_acc_status.msgcode.length == 0) {
                    bArr2 = acc_status.STATIC_KEY.getBytes();
                } else {
                    bArr2 = MD5.toMD5Byte(tencent_tls_account_acc_status.msgcode);
                }
                bArr2 = cryptor.decrypt(bArr3, 0, bArr3.length, bArr2);
                if (bArr2 != null) {
                    ByteBuffer wrap2 = ByteBuffer.wrap(bArr2);
                    bArr2 = new byte[wrap2.get()];
                    wrap2.get(bArr2);
                    tencent_tls_account_acc_status.nopicsig = new byte[wrap2.getShort()];
                    wrap2.get(tencent_tls_account_acc_status.nopicsig);
                    break;
                }
                return -1002;
                break;
            case 97:
                acc_status.msalt = wrap.getLong();
                short s = wrap.getShort();
                if (s > (short) 0) {
                    wrap.position(s + wrap.position());
                    break;
                }
                break;
            case 102:
                resolveTLV(i, wrap);
                break;
            case 118:
                acc_status.msalt = wrap.getLong();
                acc_status.regtime = wrap.getLong();
                bArr2 = new byte[wrap.get()];
                wrap.get(bArr2);
                tencent_tls_account_acc_status.userID = new String(bArr2);
                resolveTLV(i, wrap);
                tencent_tls_account_acc_status.promptinfo = new byte[wrap.getShort()];
                wrap.get(tencent_tls_account_acc_status.promptinfo);
                return 0;
        }
        tencent_tls_account_acc_status.token = new byte[wrap.get()];
        wrap.get(tencent_tls_account_acc_status.token);
        if (bArr2 != null && !Arrays.equals(tencent_tls_account_acc_status.token, bArr2)) {
            return -1002;
        }
        tencent_tls_account_acc_status.promptinfo = new byte[wrap.getShort()];
        wrap.get(tencent_tls_account_acc_status.promptinfo);
        return 0;
    }

    private static void resolveTLV(int i, ByteBuffer byteBuffer) {
        if (byteBuffer.get() != (byte) 0) {
        }
    }

    protected byte[] get_encrypt_token(byte[] bArr, byte[] bArr2) {
        Object obj = new byte[(bArr.length + 1)];
        util.int8_to_buf(obj, 0, bArr.length);
        System.arraycopy(bArr, 0, obj, 1, bArr.length);
        return cryptor.encrypt(obj, 0, obj.length, MD5.toMD5Byte(bArr2));
    }
}
