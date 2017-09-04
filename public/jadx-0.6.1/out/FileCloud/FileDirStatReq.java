package FileCloud;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.c;
import com.qq.taf.jce.d;

public final class FileDirStatReq extends JceStruct {
    static stAuth cache_auth;
    static int cache_type;
    public stAuth auth = null;
    public String path = "";
    public int type = 1;

    public FileDirStatReq(stAuth FileCloud_stAuth, String str, int i) {
        this.auth = FileCloud_stAuth;
        this.path = str;
        this.type = i;
    }

    public final void readFrom(c cVar) {
        if (cache_auth == null) {
            cache_auth = new stAuth();
        }
        this.auth = (stAuth) cVar.a(cache_auth, 1, true);
        this.path = cVar.a(2, true);
        this.type = cVar.a(this.type, 3, false);
    }

    public final void writeTo(d dVar) {
        dVar.a(this.auth, 1);
        dVar.a(this.path, 2);
        dVar.a(this.type, 3);
    }
}
