package FileCloud;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.c;
import com.qq.taf.jce.d;

public final class stFileUploadRsp extends JceStruct {
    public String url = "";

    public stFileUploadRsp(String str) {
        this.url = str;
    }

    public final void readFrom(c cVar) {
        this.url = cVar.a(1, false);
    }

    public final void writeTo(d dVar) {
        if (this.url != null) {
            dVar.a(this.url, 1);
        }
    }
}
