package tencent.tls.account;

import java.nio.ByteBuffer;

public class acc_reask_code extends acc_request {
    public acc_reask_code(int i) {
        this._cmd = i;
    }

    public byte[] get_request(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        ByteBuffer allocate = ByteBuffer.allocate(bArr.length + 1);
        allocate.put((byte) bArr.length);
        allocate.put(bArr);
        return super.get_request(allocate.array());
    }
}
