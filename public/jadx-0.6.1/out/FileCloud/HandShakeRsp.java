package FileCloud;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.c;
import com.qq.taf.jce.d;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class HandShakeRsp extends JceStruct {
    static Map<String, String> cache_config;
    static ArrayList<String> cache_download_svr;
    static stResult cache_result;
    static ArrayList<String> cache_upload_svr;
    public String clientip = "";
    public Map<String, String> config = null;
    public ArrayList<String> download_svr = null;
    public String last_update = "";
    public stResult result = null;
    public ArrayList<String> upload_svr = null;

    public HandShakeRsp(stResult FileCloud_stResult, String str, ArrayList<String> arrayList, ArrayList<String> arrayList2, Map<String, String> map, String str2) {
        this.result = FileCloud_stResult;
        this.last_update = str;
        this.upload_svr = arrayList;
        this.download_svr = arrayList2;
        this.config = map;
        this.clientip = str2;
    }

    public final void readFrom(c cVar) {
        if (cache_result == null) {
            cache_result = new stResult();
        }
        this.result = (stResult) cVar.a(cache_result, 1, true);
        this.last_update = cVar.a(2, false);
        if (cache_upload_svr == null) {
            cache_upload_svr = new ArrayList();
            cache_upload_svr.add("");
        }
        this.upload_svr = (ArrayList) cVar.a(cache_upload_svr, 3, false);
        if (cache_download_svr == null) {
            cache_download_svr = new ArrayList();
            cache_download_svr.add("");
        }
        this.download_svr = (ArrayList) cVar.a(cache_download_svr, 4, false);
        if (cache_config == null) {
            cache_config = new HashMap();
            cache_config.put("", "");
        }
        this.config = (Map) cVar.a(cache_config, 5, false);
        this.clientip = cVar.a(6, false);
    }

    public final void writeTo(d dVar) {
        dVar.a(this.result, 1);
        if (this.last_update != null) {
            dVar.a(this.last_update, 2);
        }
        if (this.upload_svr != null) {
            dVar.a(this.upload_svr, 3);
        }
        if (this.download_svr != null) {
            dVar.a(this.download_svr, 4);
        }
        if (this.config != null) {
            dVar.a(this.config, 5);
        }
        if (this.clientip != null) {
            dVar.a(this.clientip, 6);
        }
    }
}
