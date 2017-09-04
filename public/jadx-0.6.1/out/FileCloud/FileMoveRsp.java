package FileCloud;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.c;
import com.qq.taf.jce.d;

public final class FileMoveRsp extends JceStruct {
    static stResult cache_result;
    public String fileid = "";
    public stResult result = null;
    public String url = "";

    public FileMoveRsp(stResult FileCloud_stResult, String str, String str2) {
        this.result = FileCloud_stResult;
        this.url = str;
        this.fileid = str2;
    }

    public final void readFrom(c cVar) {
        if (cache_result == null) {
            cache_result = new stResult();
        }
        this.result = (stResult) cVar.a(cache_result, 1, true);
        this.url = cVar.a(2, false);
        this.fileid = cVar.a(3, false);
    }

    public final void writeTo(d dVar) {
        dVar.a(this.result, 1);
        if (this.url != null) {
            dVar.a(this.url, 2);
        }
        if (this.fileid != null) {
            dVar.a(this.fileid, 3);
        }
    }
}
