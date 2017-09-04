package com.tencent.upload.task;

import FileCloud.FileDirInfo;
import android.text.TextUtils;
import com.etrump.jni.ETConverter;

public class Dentry {
    public static final int BUCKET = 2;
    public static final int DIR = 0;
    public static final int FILE = 1;
    public static final int ListBoth = 0;
    public static final int ListDirOnly = 1;
    public static final int ListFileOnly = 2;
    public static final int MORE = -1;
    public static final int VIDEO = 3;
    public String accessUrl;
    public String attribute;
    public long createTime;
    private Object externData;
    public long fileLength;
    public long fileSize;
    public long modifyTime;
    public String name;
    public String path;
    public String sha;
    public int type;

    public Dentry() {
        this.type = 0;
        this.sha = "";
        this.path = "";
        this.name = "";
        this.accessUrl = "";
        this.attribute = "";
        this.fileSize = 0;
        this.fileLength = 0;
        this.createTime = 0;
        this.modifyTime = 0;
        this.externData = null;
        this.type = 0;
    }

    public Dentry(int i) {
        this.type = 0;
        this.sha = "";
        this.path = "";
        this.name = "";
        this.accessUrl = "";
        this.attribute = "";
        this.fileSize = 0;
        this.fileLength = 0;
        this.createTime = 0;
        this.modifyTime = 0;
        this.externData = null;
        this.type = i;
    }

    public Dentry(FileDirInfo fileDirInfo) {
        this.type = 0;
        this.sha = "";
        this.path = "";
        this.name = "";
        this.accessUrl = "";
        this.attribute = "";
        this.fileSize = 0;
        this.fileLength = 0;
        this.createTime = 0;
        this.modifyTime = 0;
        this.externData = null;
        this.name = fileDirInfo.name;
        this.type = fileDirInfo.type;
        this.attribute = fileDirInfo.biz_attr;
        this.accessUrl = fileDirInfo.access_url;
        this.path = getRelativePath(fileDirInfo.path);
        this.createTime = fileDirInfo.ctime;
        this.modifyTime = fileDirInfo.mtime;
        this.fileSize = fileDirInfo.file_size;
        this.fileLength = fileDirInfo.file_length;
        if (this.type == 3) {
            VideoAttr videoAttr = new VideoAttr();
            videoAttr.title = fileDirInfo.video_list_info.video_file_info.title;
            videoAttr.desc = fileDirInfo.video_list_info.video_file_info.desc;
            videoAttr.isCheck = fileDirInfo.video_list_info.video_file_info.is_check;
            videoAttr.coverUrl = (String) fileDirInfo.video_list_info.video_file_info.reserve_attr.get("video_cover_url");
            videoAttr.timeLen = fileDirInfo.video_list_info.time_len;
            this.externData = new VideoInfo().setVideoAttr(videoAttr).setPlayUrlList(fileDirInfo.video_list_info.play_url).setVideoStatus(fileDirInfo.video_list_info.video_status).setTransStatus(fileDirInfo.video_list_info.trans_status);
        }
        this.sha = bin2Hex(fileDirInfo.sha);
    }

    private String bin2Hex(String str) {
        String str2 = "";
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            int i2 = charAt & 15;
            str2 = (str2 + Integer.toHexString((charAt & ETConverter.ET_CONVERTER_GLYPH_CACHE_SIZE_MASK) >> 4)) + Integer.toHexString(i2);
        }
        return str2;
    }

    private String getRelativePath(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int indexOf = str.indexOf("/", 0);
        if (indexOf != 0) {
            return "";
        }
        indexOf = str.indexOf("/", indexOf + 1);
        if (indexOf <= 0) {
            return "";
        }
        indexOf = str.indexOf("/", indexOf + 1);
        if (indexOf <= 0) {
            return "";
        }
        Object substring = str.substring(indexOf);
        return TextUtils.isEmpty(substring) ? "/" : substring;
    }

    public Dentry copy(Dentry dentry) {
        this.sha = dentry.sha;
        this.type = dentry.type;
        this.path = dentry.path;
        this.name = dentry.name;
        this.accessUrl = dentry.accessUrl;
        this.attribute = dentry.attribute;
        this.fileSize = dentry.fileSize;
        this.fileLength = dentry.fileLength;
        this.createTime = dentry.createTime;
        this.modifyTime = dentry.modifyTime;
        this.externData = dentry.externData;
        return this;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        Dentry dentry = (Dentry) obj;
        return this == dentry ? true : this.type == dentry.type && this.path.equals(dentry.path) && this.name.equals(dentry.name);
    }

    public VideoInfo getVideoInfo() {
        return (this.type == 3 && (this.externData instanceof VideoInfo)) ? (VideoInfo) this.externData : null;
    }

    public Dentry setAccessUrl(String str) {
        this.accessUrl = str;
        return this;
    }

    public Dentry setAttribute(String str) {
        this.attribute = str;
        return this;
    }

    public Dentry setCreateTime(long j) {
        this.createTime = j;
        return this;
    }

    public Dentry setFileLength(long j) {
        this.fileLength = j;
        return this;
    }

    public Dentry setModifyTime(long j) {
        this.modifyTime = j;
        return this;
    }

    public Dentry setName(String str) {
        this.name = str;
        return this;
    }

    public Dentry setPath(String str) {
        this.path = str;
        return this;
    }

    public Dentry setType(int i) {
        this.type = i;
        return this;
    }
}
