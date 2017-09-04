package FileCloud;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.c;
import com.qq.taf.jce.d;

public final class stVideoUploadReq extends JceStruct {
    public long cover_frame = 0;
    public String desc = "";
    public String title = "";

    public stVideoUploadReq(String str, String str2, long j) {
        this.title = str;
        this.desc = str2;
        this.cover_frame = j;
    }

    public final void readFrom(c cVar) {
        this.title = cVar.a(1, true);
        this.desc = cVar.a(2, false);
        this.cover_frame = cVar.a(this.cover_frame, 3, false);
    }

    public final void writeTo(d dVar) {
        dVar.a(this.title, 1);
        if (this.desc != null) {
            dVar.a(this.desc, 2);
        }
        dVar.a(this.cover_frame, 3);
    }
}
