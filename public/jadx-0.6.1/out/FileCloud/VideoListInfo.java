package FileCloud;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.c;
import com.qq.taf.jce.d;
import java.util.HashMap;
import java.util.Map;

public final class VideoListInfo extends JceStruct {
    static Map<Integer, String> cache_play_url;
    static Map<Integer, Integer> cache_trans_status;
    static VideoFileInfo cache_video_file_info;
    static int cache_video_status;
    public Map<Integer, String> play_url = null;
    public long time_len = 0;
    public Map<Integer, Integer> trans_status = null;
    public VideoFileInfo video_file_info = null;
    public int video_status = 0;

    public VideoListInfo(Map<Integer, Integer> map, int i, long j, Map<Integer, String> map2, VideoFileInfo videoFileInfo) {
        this.trans_status = map;
        this.video_status = i;
        this.time_len = j;
        this.play_url = map2;
        this.video_file_info = videoFileInfo;
    }

    public final void readFrom(c cVar) {
        if (cache_trans_status == null) {
            cache_trans_status = new HashMap();
            cache_trans_status.put(Integer.valueOf(0), Integer.valueOf(0));
        }
        this.trans_status = (Map) cVar.a(cache_trans_status, 0, false);
        this.video_status = cVar.a(this.video_status, 1, false);
        this.time_len = cVar.a(this.time_len, 2, false);
        if (cache_play_url == null) {
            cache_play_url = new HashMap();
            cache_play_url.put(Integer.valueOf(0), "");
        }
        this.play_url = (Map) cVar.a(cache_play_url, 3, false);
        if (cache_video_file_info == null) {
            cache_video_file_info = new VideoFileInfo();
        }
        this.video_file_info = (VideoFileInfo) cVar.a(cache_video_file_info, 4, false);
    }

    public final void writeTo(d dVar) {
        if (this.trans_status != null) {
            dVar.a(this.trans_status, 0);
        }
        dVar.a(this.video_status, 1);
        dVar.a(this.time_len, 2);
        if (this.play_url != null) {
            dVar.a(this.play_url, 3);
        }
        if (this.video_file_info != null) {
            dVar.a(this.video_file_info, 4);
        }
    }
}
