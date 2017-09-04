package tencent.tls.account;

import java.nio.ByteBuffer;

public class acc_verify_code extends acc_request {
    public acc_verify_code(int i) {
        this._cmd = i;
    }

    public byte[] get_request(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = get_encrypt_token(bArr, bArr2);
        ByteBuffer allocate = ByteBuffer.allocate(((bArr.length + 1) + 2) + bArr3.length);
        allocate.put((byte) bArr.length);
        allocate.put(bArr);
        allocate.putShort((short) bArr3.length);
        allocate.put(bArr3);
        return super.get_request(allocate.array());
    }
}
