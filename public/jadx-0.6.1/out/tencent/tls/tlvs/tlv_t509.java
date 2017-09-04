package tencent.tls.tlvs;

import java.nio.ByteBuffer;

public class tlv_t509 extends tlv_t {
    public tlv_t509() {
        this._cmd = 1289;
    }

    public byte[] get_tlv_509(byte[] bArr, byte[] bArr2) {
        ByteBuffer allocate = ByteBuffer.allocate((bArr.length + 2) + bArr2.length);
        allocate.put(bArr);
        allocate.putShort((short) bArr2.length);
        allocate.put(bArr2);
        set_data(allocate.array(), allocate.limit());
        return get_buf();
    }
}
