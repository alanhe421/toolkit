package FileCloud;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.c;
import com.qq.taf.jce.d;

public final class stVideoInfo extends JceStruct {
    static int cache_status;
    public String cover_url = "";
    public String desc = "";
    public String md5 = "";
    public int play_time = 0;
    public String play_url = "";
    public int status = 0;
    public String title = "";
    public int upload_time = 0;
    public String vid = "";

    public stVideoInfo(String str, int i, int i2, int i3, String str2, String str3, String str4, String str5, String str6) {
        this.vid = str;
        this.status = i;
        this.play_time = i2;
        this.upload_time = i3;
        this.title = str2;
        this.desc = str3;
        this.cover_url = str4;
        this.play_url = str5;
        this.md5 = str6;
    }

    public final void readFrom(c cVar) {
        this.vid = cVar.a(1, false);
        this.status = cVar.a(this.status, 2, false);
        this.play_time = cVar.a(this.play_time, 3, true);
        this.upload_time = cVar.a(this.upload_time, 4, true);
        this.title = cVar.a(5, false);
        this.desc = cVar.a(6, false);
        this.cover_url = cVar.a(7, false);
        this.play_url = cVar.a(8, false);
        this.md5 = cVar.a(9, false);
    }

    public final void writeTo(d dVar) {
        if (this.vid != null) {
            dVar.a(this.vid, 1);
        }
        dVar.a(this.status, 2);
        dVar.a(this.play_time, 3);
        dVar.a(this.upload_time, 4);
        if (this.title != null) {
            dVar.a(this.title, 5);
        }
        if (this.desc != null) {
            dVar.a(this.desc, 6);
        }
        if (this.cover_url != null) {
            dVar.a(this.cover_url, 7);
        }
        if (this.play_url != null) {
            dVar.a(this.play_url, 8);
        }
        if (this.md5 != null) {
            dVar.a(this.md5, 9);
        }
    }
}
