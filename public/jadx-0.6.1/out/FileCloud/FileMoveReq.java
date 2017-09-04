package FileCloud;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.c;
import com.qq.taf.jce.d;

public final class FileMoveReq extends JceStruct {
    static stAuth cache_auth;
    static int cache_type;
    public stAuth auth = null;
    public String bucket = "";
    public int delete_src = 0;
    public String dst_directory = "";
    public String dst_fileid = "";
    public String src_fileid = "";
    public String src_url = "";
    public int type = 0;

    public FileMoveReq(stAuth FileCloud_stAuth, String str, String str2, int i, int i2, String str3, String str4, String str5) {
        this.auth = FileCloud_stAuth;
        this.src_url = str;
        this.dst_directory = str2;
        this.delete_src = i;
        this.type = i2;
        this.bucket = str3;
        this.src_fileid = str4;
        this.dst_fileid = str5;
    }

    public final void readFrom(c cVar) {
        if (cache_auth == null) {
            cache_auth = new stAuth();
        }
        this.auth = (stAuth) cVar.a(cache_auth, 1, true);
        this.src_url = cVar.a(2, true);
        this.dst_directory = cVar.a(3, false);
        this.delete_src = cVar.a(this.delete_src, 4, false);
        this.type = cVar.a(this.type, 5, false);
        this.bucket = cVar.a(6, false);
        this.src_fileid = cVar.a(7, false);
        this.dst_fileid = cVar.a(8, false);
    }

    public final void writeTo(d dVar) {
        dVar.a(this.auth, 1);
        dVar.a(this.src_url, 2);
        if (this.dst_directory != null) {
            dVar.a(this.dst_directory, 3);
        }
        dVar.a(this.delete_src, 4);
        dVar.a(this.type, 5);
        if (this.bucket != null) {
            dVar.a(this.bucket, 6);
        }
        if (this.src_fileid != null) {
            dVar.a(this.src_fileid, 7);
        }
        if (this.dst_fileid != null) {
            dVar.a(this.dst_fileid, 8);
        }
    }
}
