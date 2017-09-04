package FileCloud;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.c;
import com.qq.taf.jce.d;

public final class stVideoUploadRsp extends JceStruct {
    public String cover_fileid = "";
    public String cover_url = "";
    public String fileid = "";
    public String url = "";

    public stVideoUploadRsp(String str, String str2, String str3, String str4) {
        this.url = str;
        this.fileid = str2;
        this.cover_url = str3;
        this.cover_fileid = str4;
    }

    public final void readFrom(c cVar) {
        this.url = cVar.a(1, true);
        this.fileid = cVar.a(2, true);
        this.cover_url = cVar.a(3, false);
        this.cover_fileid = cVar.a(4, false);
    }

    public final void writeTo(d dVar) {
        dVar.a(this.url, 1);
        dVar.a(this.fileid, 2);
        if (this.cover_url != null) {
            dVar.a(this.cover_url, 3);
        }
        if (this.cover_fileid != null) {
            dVar.a(this.cover_fileid, 4);
        }
    }
}
