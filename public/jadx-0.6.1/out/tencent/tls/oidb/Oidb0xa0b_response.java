package tencent.tls.oidb;

import java.nio.ByteBuffer;
import tencent.tls.oidb.OidbHead.OIDBHead;
import tencent.tls.oidb.cmd0xa0b.Oidb0Xa0b.RspBody;
import tencent.tls.report.QLog;

public class Oidb0xa0b_response {
    private int dwBodyLen;
    private int dwHeadLen;
    private OIDBHead oidbHead;
    private int result = 0;
    private RspBody rspBody;

    public Oidb0xa0b_response(byte[] bArr) {
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        try {
            wrap.get();
            this.dwHeadLen = wrap.getInt();
            this.dwBodyLen = wrap.getInt();
            byte[] bArr2 = new byte[this.dwHeadLen];
            wrap.get(bArr2);
            this.oidbHead = new OIDBHead();
            this.oidbHead.mergeFrom(bArr2);
            bArr2 = new byte[this.dwBodyLen];
            wrap.get(bArr2);
            this.rspBody = new RspBody();
            this.rspBody.mergeFrom(bArr2);
            wrap.get();
        } catch (Throwable e) {
            QLog.e(e);
            this.result = -1009;
        }
    }

    public int getResult() {
        return this.result;
    }

    public long getTinyID() {
        return this.rspBody.uint64_tinyid.get();
    }

    public byte[] getA2() {
        return this.rspBody.bytes_a2.get().toByteArray();
    }

    public byte[] getD2() {
        return this.rspBody.bytes_d2.get().toByteArray();
    }

    public byte[] getD2Key() {
        return this.rspBody.bytes_d2key.get().toByteArray();
    }

    public String getErrMsg() {
        return this.oidbHead.str_error_msg.get();
    }

    public int getAdminFlag() {
        return this.rspBody.uint32_adminflag.get();
    }

    public String getIdentifier() {
        return this.rspBody.str_identifier.get();
    }

    public String getUserSig() {
        return this.rspBody.str_tlssig.get();
    }

    public int getHeadResult() {
        if (this.oidbHead != null) {
            return this.oidbHead.uint32_result.get();
        }
        return -1;
    }
}
