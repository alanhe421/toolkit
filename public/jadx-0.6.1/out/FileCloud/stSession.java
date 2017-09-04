package FileCloud;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.c;
import com.qq.taf.jce.d;

public final class stSession extends JceStruct {
    public long process_ip = 0;
    public short process_port = (short) 0;
    public String sid = "";

    public stSession(String str, long j, short s) {
        this.sid = str;
        this.process_ip = j;
        this.process_port = s;
    }

    public final void readFrom(c cVar) {
        this.sid = cVar.a(1, true);
        this.process_ip = cVar.a(this.process_ip, 2, false);
        this.process_port = cVar.a(this.process_port, 3, false);
    }

    public final void writeTo(d dVar) {
        dVar.a(this.sid, 1);
        dVar.a(this.process_ip, 2);
        dVar.a(this.process_port, 3);
    }
}
