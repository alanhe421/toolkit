package FileCloud;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.c;
import com.qq.taf.jce.d;

public final class FileStatReq extends JceStruct {
    static stAuth cache_auth;
    static int cache_type;
    public stAuth auth = null;
    public String bucket = "";
    public String fileid = "";
    public int type = 0;
    public String url = "";

    public FileStatReq(stAuth FileCloud_stAuth, String str, int i, String str2, String str3) {
        this.auth = FileCloud_stAuth;
        this.url = str;
        this.type = i;
        this.bucket = str2;
        this.fileid = str3;
    }

    public final void readFrom(c cVar) {
        if (cache_auth == null) {
            cache_auth = new stAuth();
        }
        this.auth = (stAuth) cVar.a(cache_auth, 1, true);
        this.url = cVar.a(2, true);
        this.type = cVar.a(this.type, 3, false);
        this.bucket = cVar.a(4, false);
        this.fileid = cVar.a(5, false);
    }

    public final void writeTo(d dVar) {
        dVar.a(this.auth, 1);
        dVar.a(this.url, 2);
        dVar.a(this.type, 3);
        if (this.bucket != null) {
            dVar.a(this.bucket, 4);
        }
        if (this.fileid != null) {
            dVar.a(this.fileid, 5);
        }
    }
}
