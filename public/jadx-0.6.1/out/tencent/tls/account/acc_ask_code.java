package tencent.tls.account;

import android.os.Build.VERSION;
import java.nio.ByteBuffer;
import tencent.tls.tools.util;

public class acc_ask_code extends acc_request {
    public acc_ask_code(int i) {
        this._cmd = i;
    }

    public byte[] get_request(int i, String str, byte[] bArr, String str2, int i2, long j, byte[] bArr2) {
        if (str == null || bArr == null) {
            return null;
        }
        if (str2 == null) {
            str2 = "";
        }
        String str3 = VERSION.RELEASE;
        if (str3 == null) {
            str3 = "";
        }
        ByteBuffer allocate = ByteBuffer.allocate((((((((((((str.getBytes().length + 5) + 1) + (str3.length() + 1)) + (bArr.length + 1)) + (str2.length() + 1)) + 4) + 4) + 4) + 1) + bArr2.length) + 1) + (((acc_status.apk_sig.length + 2) + 4) + acc_status.apk_id.length));
        allocate.putInt(i);
        allocate.put((byte) str.getBytes().length);
        allocate.put(str.getBytes());
        allocate.put((byte) this._os_type);
        allocate.put((byte) str3.length());
        allocate.put(str3.getBytes());
        allocate.put((byte) bArr.length);
        allocate.put(bArr);
        allocate.put((byte) str2.length());
        allocate.put(str2.getBytes());
        allocate.putInt(i2);
        allocate.putInt(acc_status.lang);
        allocate.putInt((int) j);
        allocate.put((byte) bArr2.length);
        allocate.put(bArr2);
        allocate.put((byte) 1);
        allocate.put((byte) 1);
        allocate.put((byte) ((acc_status.apk_sig.length + 4) + acc_status.apk_id.length));
        allocate.put(acc_status.apk_sig);
        allocate.putInt(acc_status.apk_id.length);
        allocate.put(acc_status.apk_id);
        return super.get_request(allocate.array());
    }

    public byte[] get_request(int i, String str, byte[] bArr, String str2, int i2, long j, byte[] bArr2, TLSOpenAccountInfo tLSOpenAccountInfo) {
        if (str == null) {
            return null;
        }
        if (bArr == null) {
            return null;
        }
        if (tLSOpenAccountInfo.checkInvalid()) {
            return null;
        }
        if (str2 == null) {
            str2 = "";
        }
        byte[] bytes = tLSOpenAccountInfo.openappid.getBytes();
        byte[] bytes2 = tLSOpenAccountInfo.openid.getBytes();
        byte[] bytes3 = tLSOpenAccountInfo.access_token.getBytes();
        byte[] bArr3 = new byte[4];
        util.int32_to_buf(bArr3, 0, tLSOpenAccountInfo.openAccountType);
        String str3 = VERSION.RELEASE;
        if (str3 == null) {
            str3 = "";
        }
        ByteBuffer allocate = ByteBuffer.allocate((((((((((((str.length() + 5) + 1) + (str3.length() + 1)) + (bArr.length + 1)) + (str2.length() + 1)) + 4) + 4) + 4) + 1) + bArr2.length) + 1) + (((((((acc_status.apk_sig.length + 2) + 4) + acc_status.apk_id.length) + 6) + (bytes.length + 2)) + (bytes2.length + 2)) + (bytes3.length + 2)));
        allocate.putInt(i);
        allocate.put((byte) str.length());
        allocate.put(str.getBytes());
        allocate.put((byte) this._os_type);
        allocate.put((byte) str3.length());
        allocate.put(str3.getBytes());
        allocate.put((byte) bArr.length);
        allocate.put(bArr);
        allocate.put((byte) str2.length());
        allocate.put(str2.getBytes());
        allocate.putInt(i2);
        allocate.putInt(acc_status.lang);
        allocate.putInt((int) j);
        allocate.put((byte) bArr2.length);
        allocate.put(bArr2);
        allocate.put((byte) 5);
        allocate.put((byte) 1);
        allocate.put((byte) ((acc_status.apk_sig.length + 4) + acc_status.apk_id.length));
        allocate.put(acc_status.apk_sig);
        allocate.putInt(acc_status.apk_id.length);
        allocate.put(acc_status.apk_id);
        allocate.put((byte) 2);
        allocate.put((byte) 4);
        allocate.put(bArr3);
        allocate.put((byte) 3);
        allocate.put((byte) bytes.length);
        allocate.put(bytes);
        allocate.put((byte) 4);
        allocate.put((byte) bytes2.length);
        allocate.put(bytes2);
        allocate.put((byte) 5);
        allocate.put((byte) bytes3.length);
        allocate.put(bytes3);
        return super.get_request(allocate.array());
    }
}
