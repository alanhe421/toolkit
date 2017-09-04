package FileCloud;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.c;
import com.qq.taf.jce.d;

public final class stPhotoUploadReq extends JceStruct {
    public int analyze_mode = 0;
    public String bind_info = "";
    public String bucket = "";
    public String directory = "";
    public long expired = 0;
    public String fileid = "";

    public stPhotoUploadReq(String str, long j, String str2, String str3, String str4, int i) {
        this.directory = str;
        this.expired = j;
        this.bind_info = str2;
        this.bucket = str3;
        this.fileid = str4;
        this.analyze_mode = i;
    }

    public final void readFrom(c cVar) {
        this.directory = cVar.a(1, false);
        this.expired = cVar.a(this.expired, 2, false);
        this.bind_info = cVar.a(3, false);
        this.bucket = cVar.a(4, false);
        this.fileid = cVar.a(5, false);
        this.analyze_mode = cVar.a(this.analyze_mode, 6, false);
    }

    public final void writeTo(d dVar) {
        if (this.directory != null) {
            dVar.a(this.directory, 1);
        }
        dVar.a(this.expired, 2);
        if (this.bind_info != null) {
            dVar.a(this.bind_info, 3);
        }
        if (this.bucket != null) {
            dVar.a(this.bucket, 4);
        }
        if (this.fileid != null) {
            dVar.a(this.fileid, 5);
        }
        dVar.a(this.analyze_mode, 6);
    }
}
