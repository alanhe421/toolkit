package FileCloud;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.c;
import com.qq.taf.jce.d;

public final class FileUploadRsp extends JceStruct {
    static byte[] cache_biz_rsp;
    static stResult cache_result;
    public byte[] biz_rsp = null;
    public int finish = 0;
    public long offset = 0;
    public stResult result = null;
    public String session = "";
    public long size = 0;

    public FileUploadRsp(stResult FileCloud_stResult, String str, long j, long j2, int i, byte[] bArr) {
        this.result = FileCloud_stResult;
        this.session = str;
        this.offset = j;
        this.size = j2;
        this.finish = i;
        this.biz_rsp = bArr;
    }

    public final void readFrom(c cVar) {
        if (cache_result == null) {
            cache_result = new stResult();
        }
        this.result = (stResult) cVar.a(cache_result, 1, true);
        this.session = cVar.a(2, false);
        this.offset = cVar.a(this.offset, 3, false);
        this.size = cVar.a(this.size, 4, false);
        this.finish = cVar.a(this.finish, 5, false);
        if (cache_biz_rsp == null) {
            byte[] bArr = new byte[1];
            cache_biz_rsp = bArr;
            bArr[0] = (byte) 0;
        }
        this.biz_rsp = cVar.a(cache_biz_rsp, 6, false);
    }

    public final void writeTo(d dVar) {
        dVar.a(this.result, 1);
        if (this.session != null) {
            dVar.a(this.session, 2);
        }
        dVar.a(this.offset, 3);
        dVar.a(this.size, 4);
        dVar.a(this.finish, 5);
        if (this.biz_rsp != null) {
            dVar.a(this.biz_rsp, 6);
        }
    }
}
