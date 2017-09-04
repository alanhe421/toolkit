package FileCloud;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.c;
import com.qq.taf.jce.d;
import java.util.ArrayList;

public final class DirListRsp extends JceStruct {
    static ArrayList<FileDirInfo> cache_infos;
    static stResult cache_result;
    public String content = "";
    public long dir_count = 0;
    public long file_count = 0;
    public boolean hasmore = false;
    public ArrayList<FileDirInfo> infos = null;
    public stResult result = null;

    public DirListRsp(stResult FileCloud_stResult, long j, long j2, ArrayList<FileDirInfo> arrayList, String str, boolean z) {
        this.result = FileCloud_stResult;
        this.dir_count = j;
        this.file_count = j2;
        this.infos = arrayList;
        this.content = str;
        this.hasmore = z;
    }

    public final void readFrom(c cVar) {
        if (cache_result == null) {
            cache_result = new stResult();
        }
        this.result = (stResult) cVar.a(cache_result, 1, true);
        this.dir_count = cVar.a(this.dir_count, 2, false);
        this.file_count = cVar.a(this.file_count, 3, false);
        if (cache_infos == null) {
            cache_infos = new ArrayList();
            cache_infos.add(new FileDirInfo());
        }
        this.infos = (ArrayList) cVar.a(cache_infos, 4, false);
        this.content = cVar.a(5, false);
        this.hasmore = cVar.a(this.hasmore, 6, false);
    }

    public final void writeTo(d dVar) {
        dVar.a(this.result, 1);
        dVar.a(this.dir_count, 2);
        dVar.a(this.file_count, 3);
        if (this.infos != null) {
            dVar.a(this.infos, 4);
        }
        if (this.content != null) {
            dVar.a(this.content, 5);
        }
        dVar.a(this.hasmore, 6);
    }
}
