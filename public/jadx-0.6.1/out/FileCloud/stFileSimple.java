package FileCloud;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.c;
import com.qq.taf.jce.d;

public final class stFileSimple extends JceStruct {
    public String bind_info = "";
    public long create_time = 0;
    public long size = 0;
    public String url = "";

    public stFileSimple(String str, long j, long j2, String str2) {
        this.url = str;
        this.create_time = j;
        this.size = j2;
        this.bind_info = str2;
    }

    public final void readFrom(c cVar) {
        this.url = cVar.a(1, true);
        this.create_time = cVar.a(this.create_time, 2, true);
        this.size = cVar.a(this.size, 3, true);
        this.bind_info = cVar.a(4, false);
    }

    public final void writeTo(d dVar) {
        dVar.a(this.url, 1);
        dVar.a(this.create_time, 2);
        dVar.a(this.size, 3);
        if (this.bind_info != null) {
            dVar.a(this.bind_info, 4);
        }
    }
}
