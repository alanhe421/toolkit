package FileCloud;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.c;
import com.qq.taf.jce.d;

public final class DirCreateRsp extends JceStruct {
    static stResult cache_result;
    public String access_url = "";
    public long ctime = 0;
    public boolean is_over_write = false;
    public String path = "";
    public stResult result = null;

    public DirCreateRsp(stResult FileCloud_stResult, boolean z, long j, String str, String str2) {
        this.result = FileCloud_stResult;
        this.is_over_write = z;
        this.ctime = j;
        this.access_url = str;
        this.path = str2;
    }

    public final void readFrom(c cVar) {
        if (cache_result == null) {
            cache_result = new stResult();
        }
        this.result = (stResult) cVar.a(cache_result, 1, true);
        this.is_over_write = cVar.a(this.is_over_write, 2, false);
        this.ctime = cVar.a(this.ctime, 3, false);
        this.access_url = cVar.a(4, false);
        this.path = cVar.a(5, false);
    }

    public final void writeTo(d dVar) {
        dVar.a(this.result, 1);
        dVar.a(this.is_over_write, 2);
        dVar.a(this.ctime, 3);
        if (this.access_url != null) {
            dVar.a(this.access_url, 4);
        }
        if (this.path != null) {
            dVar.a(this.path, 5);
        }
    }
}
