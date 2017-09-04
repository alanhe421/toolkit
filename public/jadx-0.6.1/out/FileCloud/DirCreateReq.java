package FileCloud;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.c;
import com.qq.taf.jce.d;

public final class DirCreateReq extends JceStruct {
    static stAuth cache_auth;
    public stAuth auth = null;
    public String biz_attr = "";
    public String path = "";
    public boolean to_over_write = false;

    public DirCreateReq(stAuth FileCloud_stAuth, String str, String str2, boolean z) {
        this.auth = FileCloud_stAuth;
        this.path = str;
        this.biz_attr = str2;
        this.to_over_write = z;
    }

    public final void readFrom(c cVar) {
        if (cache_auth == null) {
            cache_auth = new stAuth();
        }
        this.auth = (stAuth) cVar.a(cache_auth, 1, true);
        this.path = cVar.a(2, true);
        this.biz_attr = cVar.a(3, false);
        this.to_over_write = cVar.a(this.to_over_write, 4, false);
    }

    public final void writeTo(d dVar) {
        dVar.a(this.auth, 1);
        dVar.a(this.path, 2);
        if (this.biz_attr != null) {
            dVar.a(this.biz_attr, 3);
        }
        dVar.a(this.to_over_write, 4);
    }
}
