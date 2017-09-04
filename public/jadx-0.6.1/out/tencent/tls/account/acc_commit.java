package tencent.tls.account;

import java.nio.ByteBuffer;
import tencent.tls.tools.MD5;
import tencent.tls.tools.cryptor;
import tencent.tls.tools.util;

public class acc_commit extends acc_request {
    public acc_commit(int i) {
        this._cmd = i;
    }

    public byte[] get_request(byte[] bArr, String str, byte[] bArr2) {
        ByteBuffer allocate;
        byte[] s2 = util.getS2(MD5.toMD5Byte(str), acc_status.msalt);
        if (this._cmd == 102) {
            allocate = ByteBuffer.allocate((((bArr.length + 1) + 1) + s2.length) + 1);
            allocate.put((byte) bArr.length);
            allocate.put(bArr);
            allocate.put((byte) s2.length);
            allocate.put(s2);
            allocate.put((byte) 0);
        } else {
            s2 = get_encrypt_token(bArr, s2, bArr2);
            allocate = ByteBuffer.allocate(((bArr.length + 1) + 2) + s2.length);
            allocate.put((byte) bArr.length);
            allocate.put(bArr);
            allocate.putShort((short) s2.length);
            allocate.put(s2);
        }
        return super.get_request(allocate.array());
    }

    public byte[] get_encrypt_token(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        byte[] bytes;
        ByteBuffer allocate = ByteBuffer.allocate((((bArr.length + 1) + 1) + bArr2.length) + 1);
        allocate.put((byte) bArr.length);
        allocate.put(bArr);
        allocate.put((byte) bArr2.length);
        allocate.put(bArr2);
        allocate.put((byte) 0);
        if (bArr3 == null || bArr3.length == 0) {
            bytes = acc_status.STATIC_KEY.getBytes();
        } else {
            bytes = MD5.toMD5Byte(bArr3);
        }
        return cryptor.encrypt(allocate.array(), 0, allocate.capacity(), bytes);
    }
}
