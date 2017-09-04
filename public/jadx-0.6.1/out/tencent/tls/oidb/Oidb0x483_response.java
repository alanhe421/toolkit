package tencent.tls.oidb;

import java.nio.ByteBuffer;
import tencent.tls.oidb.OidbHead.OIDBHead;
import tencent.tls.oidb.cmd0x483.Oidb0X483.RspBody;
import tencent.tls.report.QLog;

public class Oidb0x483_response {
    private int dwBodyLen;
    private int dwHeadLen;
    private OIDBHead oidbHead;
    private int result = 0;
    private RspBody rspBody;

    public Oidb0x483_response(byte[] bArr) {
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

    public String getErrHint() {
        return this.rspBody.str_errhint.get();
    }

    public String getAccessToken() {
        return this.rspBody.st_token.str_access_token.get();
    }

    public long getExpireTime() {
        return (long) this.rspBody.st_token.uint32_expires_in.get();
    }

    public String getRefreshToken() {
        return this.rspBody.st_token.str_refresh_token.get();
    }

    public String getOpenid() {
        return this.rspBody.st_token.str_openid.get();
    }

    public String getScope() {
        return this.rspBody.st_token.str_scope.get();
    }

    public String getUnionid() {
        return this.rspBody.st_token.str_unionid.get();
    }
}
