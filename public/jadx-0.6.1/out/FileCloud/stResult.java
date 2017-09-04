package FileCloud;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.c;
import com.qq.taf.jce.d;

public final class stResult extends JceStruct {
    public String msg = "";
    public int ret = 0;

    public stResult(int i, String str) {
        this.ret = i;
        this.msg = str;
    }

    public final void readFrom(c cVar) {
        this.ret = cVar.a(this.ret, 1, true);
        this.msg = cVar.a(2, false);
    }

    public final void writeTo(d dVar) {
        dVar.a(this.ret, 1);
        if (this.msg != null) {
            dVar.a(this.msg, 2);
        }
    }
}
