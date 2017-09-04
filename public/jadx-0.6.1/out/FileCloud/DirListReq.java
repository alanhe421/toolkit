package FileCloud;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.c;
import com.qq.taf.jce.d;

public final class DirListReq extends JceStruct {
    static stAuth cache_auth;
    static int cache_pattern;
    public stAuth auth = null;
    public String content = "";
    public long num = 0;
    public boolean order = false;
    public int pattern = 0;
    public String startpath = "";

    public DirListReq(stAuth FileCloud_stAuth, String str, long j, int i, boolean z, String str2) {
        this.auth = FileCloud_stAuth;
        this.startpath = str;
        this.num = j;
        this.pattern = i;
        this.order = z;
        this.content = str2;
    }

    public final void readFrom(c cVar) {
        if (cache_auth == null) {
            cache_auth = new stAuth();
        }
        this.auth = (stAuth) cVar.a(cache_auth, 1, true);
        this.startpath = cVar.a(2, true);
        this.num = cVar.a(this.num, 3, true);
        this.pattern = cVar.a(this.pattern, 4, false);
        this.order = cVar.a(this.order, 5, false);
        this.content = cVar.a(6, false);
    }

    public final void writeTo(d dVar) {
        dVar.a(this.auth, 1);
        dVar.a(this.startpath, 2);
        dVar.a(this.num, 3);
        dVar.a(this.pattern, 4);
        dVar.a(this.order, 5);
        if (this.content != null) {
            dVar.a(this.content, 6);
        }
    }
}
