package tencent.tls.oidb;

import java.nio.ByteBuffer;
import tencent.tls.account.TLSOpenAccountInfo.OpenAccountStatus;
import tencent.tls.oidb.OidbHead.OIDBHead;
import tencent.tls.oidb.cmd0x602.Oidb0X602.Open2UserInfo;
import tencent.tls.oidb.cmd0x602.Oidb0X602.RspBody;
import tencent.tls.report.QLog;

public class Oidb0x602_response {
    private int dwBodyLen;
    private int dwHeadLen;
    private OIDBHead oidbHead;
    private int result = 0;
    private RspBody rspBody;

    public Oidb0x602_response(byte[] bArr) {
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

    public OpenAccountStatus getOpenAccountStatus() {
        if (this.rspBody.rpt_open2userinfos.size() > 0) {
            switch (((Open2UserInfo) this.rspBody.rpt_open2userinfos.get(0)).uint32_result_flg.get()) {
                case 1:
                    return OpenAccountStatus.USED_BINDED;
                case 2:
                    return OpenAccountStatus.USED_UNBINDED;
                case 3:
                    return OpenAccountStatus.UNUSED;
            }
        }
        return OpenAccountStatus.UNKNOWN;
    }
}
