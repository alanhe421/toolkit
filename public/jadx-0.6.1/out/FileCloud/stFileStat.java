package FileCloud;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.c;
import com.qq.taf.jce.d;

public final class stFileStat extends JceStruct {
    static stFileSimple cache_file;
    public stFileSimple file = null;
    public long height = 0;
    public String md5 = "";
    public long type = 0;
    public long width = 0;

    public stFileStat(stFileSimple FileCloud_stFileSimple, String str, long j, long j2, long j3) {
        this.file = FileCloud_stFileSimple;
        this.md5 = str;
        this.type = j;
        this.width = j2;
        this.height = j3;
    }

    public final void readFrom(c cVar) {
        if (cache_file == null) {
            cache_file = new stFileSimple();
        }
        this.file = (stFileSimple) cVar.a(cache_file, 1, true);
        this.md5 = cVar.a(2, true);
        this.type = cVar.a(this.type, 3, true);
        this.width = cVar.a(this.width, 4, true);
        this.height = cVar.a(this.height, 5, true);
    }

    public final void writeTo(d dVar) {
        dVar.a(this.file, 1);
        dVar.a(this.md5, 2);
        dVar.a(this.type, 3);
        dVar.a(this.width, 4);
        dVar.a(this.height, 5);
    }
}
