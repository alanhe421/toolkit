package tencent.tls.oidb;

import java.nio.ByteBuffer;
import tencent.tls.oidb.OidbHead.OIDBHead;
import tencent.tls.oidb.cmd0x601.Oidb0X601.RspBody;
import tencent.tls.report.QLog;

public class Oidb0x601_response {
    private int dwBodyLen;
    private int dwHeadLen;
    private OIDBHead oidbHead;
    private int result = 0;
    private RspBody rspBody;

    public Oidb0x601_response(byte[] bArr) {
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

    public int getHeadResult() {
        if (this.oidbHead != null) {
            return this.oidbHead.uint32_result.get();
        }
        return -1;
    }
}
