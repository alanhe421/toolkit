package com.tencent.upload.task;

import java.util.HashMap;
import java.util.Map;

public class VideoInfo {
    public static final int CHECKFAIL = 4;
    public static final int CHECKNOTPASS = 3;
    public static final int CHECKPASS = 2;
    public static final int DEFAULT = 0;
    public static final int F0 = 0;
    public static final int F10 = 10;
    public static final int F20 = 20;
    public static final int F30 = 30;
    public static final int INVALID = 0;
    public static final int MaskAll = 65535;
    public static final int MaskBizAttr = 1;
    public static final int MaskDesc = 4;
    public static final int MaskTitle = 2;
    public static final int MaskVideoCover = 8;
    public static final int TRANSCODEDONE = 2;
    public static final int TRANSCODEFAIL = 3;
    public static final int TRANSCODING = 1;
    public static final int UPLOADING = 1;
    public Map<Integer, String> playUrlList;
    public Map<Integer, Integer> transStatus;
    public VideoAttr videoAttr;
    public int videoStatus = 0;

    VideoInfo setPlayUrlList(Map<Integer, String> map) {
        this.playUrlList = new HashMap(map);
        return this;
    }

    VideoInfo setTransStatus(Map<Integer, Integer> map) {
        this.transStatus = new HashMap(map);
        return this;
    }

    VideoInfo setVideoAttr(VideoAttr videoAttr) {
        this.videoAttr = videoAttr;
        return this;
    }

    VideoInfo setVideoStatus(int i) {
        this.videoStatus = i;
        return this;
    }
}
