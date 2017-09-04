package FileCloud;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.c;
import com.qq.taf.jce.d;

public final class ObjectDeleteRsp extends JceStruct {
    static stResult cache_result;
    public stResult result = null;

    public ObjectDeleteRsp(stResult FileCloud_stResult) {
        this.result = FileCloud_stResult;
    }

    public final void readFrom(c cVar) {
        if (cache_result == null) {
            cache_result = new stResult();
        }
        this.result = (stResult) cVar.a(cache_result, 1, true);
    }

    public final void writeTo(d dVar) {
        dVar.a(this.result, 1);
    }
}
