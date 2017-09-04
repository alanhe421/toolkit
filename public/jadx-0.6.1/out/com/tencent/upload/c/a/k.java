package com.tencent.upload.c.a;

import FileCloud.FileDirUpdateReq;
import FileCloud.VideoFileInfo;
import com.qq.taf.jce.JceStruct;
import com.tencent.upload.c.b;
import com.tencent.upload.task.VideoAttr;
import java.util.HashMap;
import java.util.Map;

public final class k extends b {
    private String a;
    private int b;
    private int c;
    private String d;
    private VideoAttr e;

    public k(String str, int i, int i2, String str2, VideoAttr videoAttr) {
        super("CMD_DIR_FILE_UPDATE");
        this.a = str;
        this.b = i;
        this.d = str2;
        this.e = videoAttr;
        this.c = i2;
    }

    protected final JceStruct h() {
        JceStruct fileDirUpdateReq = new FileDirUpdateReq();
        fileDirUpdateReq.path = this.a;
        fileDirUpdateReq.type = this.b;
        fileDirUpdateReq.auth = i();
        fileDirUpdateReq.biz_attr = this.d;
        fileDirUpdateReq.modify_flag = this.c;
        VideoFileInfo videoFileInfo = null;
        if (this.e != null) {
            Map hashMap = new HashMap();
            hashMap.put("video_cover_url", this.e.coverUrl);
            videoFileInfo = new VideoFileInfo(this.e.title, this.e.desc, this.e.isCheck, hashMap);
        }
        fileDirUpdateReq.video_file_info = videoFileInfo;
        return fileDirUpdateReq;
    }
}
