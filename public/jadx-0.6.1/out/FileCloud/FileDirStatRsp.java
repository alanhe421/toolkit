package FileCloud;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.c;
import com.qq.taf.jce.d;

public final class FileDirStatRsp extends JceStruct {
    static FileDirInfo cache_info;
    static stResult cache_result;
    public FileDirInfo info = null;
    public stResult result = null;

    public FileDirStatRsp(stResult FileCloud_stResult, FileDirInfo fileDirInfo) {
        this.result = FileCloud_stResult;
        this.info = fileDirInfo;
    }

    public final void readFrom(c cVar) {
        if (cache_result == null) {
            cache_result = new stResult();
        }
        this.result = (stResult) cVar.a(cache_result, 0, true);
        if (cache_info == null) {
            cache_info = new FileDirInfo();
        }
        this.info = (FileDirInfo) cVar.a(cache_info, 1, false);
    }

    public final void writeTo(d dVar) {
        dVar.a(this.result, 0);
        if (this.info != null) {
            dVar.a(this.info, 1);
        }
    }
}
