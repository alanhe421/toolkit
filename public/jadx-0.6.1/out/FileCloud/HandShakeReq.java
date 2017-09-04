package FileCloud;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.c;
import com.qq.taf.jce.d;

public final class HandShakeReq extends JceStruct {
    static stAuth cache_auth;
    static stEnvironment cache_env;
    static int cache_flag;
    static int cache_type;
    public stAuth auth = null;
    public String clientip = "";
    public String download_domain = "";
    public String download_url = "";
    public stEnvironment env = null;
    public int flag = 0;
    public String last_update = "";
    public int type = 0;

    public HandShakeReq(stAuth FileCloud_stAuth, stEnvironment FileCloud_stEnvironment, int i, String str, String str2, String str3, int i2, String str4) {
        this.auth = FileCloud_stAuth;
        this.env = FileCloud_stEnvironment;
        this.flag = i;
        this.download_url = str;
        this.last_update = str2;
        this.clientip = str3;
        this.type = i2;
        this.download_domain = str4;
    }

    public final void readFrom(c cVar) {
        if (cache_auth == null) {
            cache_auth = new stAuth();
        }
        this.auth = (stAuth) cVar.a(cache_auth, 1, true);
        if (cache_env == null) {
            cache_env = new stEnvironment();
        }
        this.env = (stEnvironment) cVar.a(cache_env, 2, true);
        this.flag = cVar.a(this.flag, 3, true);
        this.download_url = cVar.a(4, false);
        this.last_update = cVar.a(5, false);
        this.clientip = cVar.a(6, false);
        this.type = cVar.a(this.type, 7, false);
        this.download_domain = cVar.a(8, false);
    }

    public final void writeTo(d dVar) {
        dVar.a(this.auth, 1);
        dVar.a(this.env, 2);
        dVar.a(this.flag, 3);
        if (this.download_url != null) {
            dVar.a(this.download_url, 4);
        }
        if (this.last_update != null) {
            dVar.a(this.last_update, 5);
        }
        if (this.clientip != null) {
            dVar.a(this.clientip, 6);
        }
        dVar.a(this.type, 7);
        if (this.download_domain != null) {
            dVar.a(this.download_domain, 8);
        }
    }
}
