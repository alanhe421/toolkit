package FileCloud;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.c;
import com.qq.taf.jce.d;

public final class stPicInfo extends JceStruct {
    public long height = 0;
    public long tag = 0;
    public long width = 0;

    public stPicInfo(long j, long j2, long j3) {
        this.tag = j;
        this.width = j2;
        this.height = j3;
    }

    public final void readFrom(c cVar) {
        this.tag = cVar.a(this.tag, 1, true);
        this.width = cVar.a(this.width, 2, true);
        this.height = cVar.a(this.height, 3, true);
    }

    public final void writeTo(d dVar) {
        dVar.a(this.tag, 1);
        dVar.a(this.width, 2);
        dVar.a(this.height, 3);
    }
}
