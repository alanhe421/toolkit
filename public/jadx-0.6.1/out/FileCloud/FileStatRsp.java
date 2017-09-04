package FileCloud;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.c;
import com.qq.taf.jce.d;
import java.util.HashMap;
import java.util.Map;

public final class FileStatRsp extends JceStruct {
    static stResult cache_result;
    static Map<String, String> cache_stats;
    public stResult result = null;
    public Map<String, String> stats = null;

    public FileStatRsp(stResult FileCloud_stResult, Map<String, String> map) {
        this.result = FileCloud_stResult;
        this.stats = map;
    }

    public final void readFrom(c cVar) {
        if (cache_result == null) {
            cache_result = new stResult();
        }
        this.result = (stResult) cVar.a(cache_result, 1, true);
        if (cache_stats == null) {
            cache_stats = new HashMap();
            cache_stats.put("", "");
        }
        this.stats = (Map) cVar.a(cache_stats, 2, false);
    }

    public final void writeTo(d dVar) {
        dVar.a(this.result, 1);
        if (this.stats != null) {
            dVar.a(this.stats, 2);
        }
    }
}
