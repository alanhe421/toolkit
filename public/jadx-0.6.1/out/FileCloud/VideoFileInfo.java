package FileCloud;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.c;
import com.qq.taf.jce.d;
import java.util.HashMap;
import java.util.Map;

public final class VideoFileInfo extends JceStruct {
    static Map<String, String> cache_reserve_attr = new HashMap();
    public String desc = "";
    public boolean is_check = false;
    public Map<String, String> reserve_attr = null;
    public String title = "";

    static {
        cache_reserve_attr.put("", "");
    }

    public VideoFileInfo(String str, String str2, boolean z, Map<String, String> map) {
        this.title = str;
        this.desc = str2;
        this.is_check = z;
        this.reserve_attr = map;
    }

    public final void readFrom(c cVar) {
        this.title = cVar.a(0, false);
        this.desc = cVar.a(1, false);
        this.is_check = cVar.a(this.is_check, 2, false);
        this.reserve_attr = (Map) cVar.a(cache_reserve_attr, 3, false);
    }

    public final void writeTo(d dVar) {
        if (this.title != null) {
            dVar.a(this.title, 0);
        }
        if (this.desc != null) {
            dVar.a(this.desc, 1);
        }
        dVar.a(this.is_check, 2);
        if (this.reserve_attr != null) {
            dVar.a(this.reserve_attr, 3);
        }
    }
}
