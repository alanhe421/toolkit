package FileCloud;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.c;
import com.qq.taf.jce.d;

public final class FileDirUpdateReq extends JceStruct {
    static stAuth cache_auth;
    static int cache_type;
    static VideoFileInfo cache_video_file_info;
    public stAuth auth = null;
    public String biz_attr = "";
    public int modify_flag = 0;
    public String path = "";
    public int type = 1;
    public VideoFileInfo video_file_info = null;

    public FileDirUpdateReq(stAuth FileCloud_stAuth, String str, String str2, int i, int i2, VideoFileInfo videoFileInfo) {
        this.auth = FileCloud_stAuth;
        this.path = str;
        this.biz_attr = str2;
        this.type = i;
        this.modify_flag = i2;
        this.video_file_info = videoFileInfo;
    }

    public final void readFrom(c cVar) {
        if (cache_auth == null) {
            cache_auth = new stAuth();
        }
        this.auth = (stAuth) cVar.a(cache_auth, 1, true);
        this.path = cVar.a(2, true);
        this.biz_attr = cVar.a(3, false);
        this.type = cVar.a(this.type, 4, false);
        this.modify_flag = cVar.a(this.modify_flag, 5, false);
        if (cache_video_file_info == null) {
            cache_video_file_info = new VideoFileInfo();
        }
        this.video_file_info = (VideoFileInfo) cVar.a(cache_video_file_info, 6, false);
    }

    public final void writeTo(d dVar) {
        dVar.a(this.auth, 1);
        dVar.a(this.path, 2);
        if (this.biz_attr != null) {
            dVar.a(this.biz_attr, 3);
        }
        dVar.a(this.type, 4);
        dVar.a(this.modify_flag, 5);
        if (this.video_file_info != null) {
            dVar.a(this.video_file_info, 6);
        }
    }
}
