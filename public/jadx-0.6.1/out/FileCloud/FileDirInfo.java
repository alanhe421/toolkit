package FileCloud;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.c;
import com.qq.taf.jce.d;

public final class FileDirInfo extends JceStruct {
    static int cache_type;
    static VideoListInfo cache_video_list_info;
    public String access_url = "";
    public String biz_attr = "";
    public long ctime = 0;
    public long file_length = 0;
    public long file_size = 0;
    public long mtime = 0;
    public String name = "";
    public String path = "";
    public String sha = "";
    public int type = 0;
    public VideoListInfo video_list_info = null;

    public FileDirInfo(String str, String str2, long j, long j2, String str3, long j3, long j4, String str4, int i, String str5, VideoListInfo videoListInfo) {
        this.name = str;
        this.biz_attr = str2;
        this.file_size = j;
        this.file_length = j2;
        this.sha = str3;
        this.ctime = j3;
        this.mtime = j4;
        this.access_url = str4;
        this.type = i;
        this.path = str5;
        this.video_list_info = videoListInfo;
    }

    public final void readFrom(c cVar) {
        this.name = cVar.a(0, false);
        this.biz_attr = cVar.a(1, false);
        this.file_size = cVar.a(this.file_size, 2, false);
        this.file_length = cVar.a(this.file_length, 3, false);
        this.sha = cVar.a(4, false);
        this.ctime = cVar.a(this.ctime, 5, false);
        this.mtime = cVar.a(this.mtime, 6, false);
        this.access_url = cVar.a(7, false);
        this.type = cVar.a(this.type, 8, false);
        this.path = cVar.a(9, false);
        if (cache_video_list_info == null) {
            cache_video_list_info = new VideoListInfo();
        }
        this.video_list_info = (VideoListInfo) cVar.a(cache_video_list_info, 10, false);
    }

    public final void writeTo(d dVar) {
        if (this.name != null) {
            dVar.a(this.name, 0);
        }
        if (this.biz_attr != null) {
            dVar.a(this.biz_attr, 1);
        }
        dVar.a(this.file_size, 2);
        dVar.a(this.file_length, 3);
        if (this.sha != null) {
            dVar.a(this.sha, 4);
        }
        dVar.a(this.ctime, 5);
        dVar.a(this.mtime, 6);
        if (this.access_url != null) {
            dVar.a(this.access_url, 7);
        }
        dVar.a(this.type, 8);
        if (this.path != null) {
            dVar.a(this.path, 9);
        }
        if (this.video_list_info != null) {
            dVar.a(this.video_list_info, 10);
        }
    }
}
