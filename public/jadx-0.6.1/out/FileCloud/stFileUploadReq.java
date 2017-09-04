package FileCloud;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.c;
import com.qq.taf.jce.d;

public final class stFileUploadReq extends JceStruct {
    static VideoFileInfo cache_video_file_info;
    public String biz_attr = "";
    public VideoFileInfo video_file_info = null;

    public stFileUploadReq(String str, VideoFileInfo videoFileInfo) {
        this.biz_attr = str;
        this.video_file_info = videoFileInfo;
    }

    public final void readFrom(c cVar) {
        this.biz_attr = cVar.a(1, true);
        if (cache_video_file_info == null) {
            cache_video_file_info = new VideoFileInfo();
        }
        this.video_file_info = (VideoFileInfo) cVar.a(cache_video_file_info, 2, false);
    }

    public final void writeTo(d dVar) {
        dVar.a(this.biz_attr, 1);
        if (this.video_file_info != null) {
            dVar.a(this.video_file_info, 2);
        }
    }
}
