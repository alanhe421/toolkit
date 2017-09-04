package FileCloud;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.c;
import com.qq.taf.jce.d;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class stPhotoUploadRsp extends JceStruct {
    static ArrayList<stPicInfo> cache_pic_info_list = new ArrayList();
    static Map<String, String> cache_stats = new HashMap();
    public int analyze_flag = 0;
    public String fileid = "";
    public ArrayList<stPicInfo> pic_info_list = null;
    public Map<String, String> stats = null;
    public String url = "";

    static {
        cache_pic_info_list.add(new stPicInfo());
        cache_stats.put("", "");
    }

    public stPhotoUploadRsp(String str, String str2, int i, ArrayList<stPicInfo> arrayList, Map<String, String> map) {
        this.url = str;
        this.fileid = str2;
        this.analyze_flag = i;
        this.pic_info_list = arrayList;
        this.stats = map;
    }

    public final void readFrom(c cVar) {
        this.url = cVar.a(1, true);
        this.fileid = cVar.a(2, true);
        this.analyze_flag = cVar.a(this.analyze_flag, 3, false);
        this.pic_info_list = (ArrayList) cVar.a(cache_pic_info_list, 4, false);
        this.stats = (Map) cVar.a(cache_stats, 5, false);
    }

    public final void writeTo(d dVar) {
        dVar.a(this.url, 1);
        dVar.a(this.fileid, 2);
        dVar.a(this.analyze_flag, 3);
        if (this.pic_info_list != null) {
            dVar.a(this.pic_info_list, 4);
        }
        if (this.stats != null) {
            dVar.a(this.stats, 5);
        }
    }
}
