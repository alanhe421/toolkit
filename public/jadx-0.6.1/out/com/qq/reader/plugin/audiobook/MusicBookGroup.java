package com.qq.reader.plugin.audiobook;

import com.qq.reader.framework.mark.LocalMark;

public class MusicBookGroup extends LocalMark {
    private static final long serialVersionUID = 1;
    private int chapterCount;
    private long curDownloadChapterId = -1000;
    private long lastSeekTime;
    private String lastSongId;
    private int mDrmFlag = 2;
    private String mFileFormat = "mp3";
    private String mReader;

    public MusicBookGroup(long j, String str) {
        super(6, String.valueOf(j), 0, str, "");
        this.mBookId = j;
    }

    public int getChapterCount() {
        return this.chapterCount;
    }

    public void setChapterCount(int i) {
        this.chapterCount = i;
    }

    public String getmReader() {
        return this.mReader;
    }

    public void setmReader(String str) {
        this.mReader = str;
    }

    public String getmFileFormat() {
        return this.mFileFormat;
    }

    public void setmFileFormat(String str) {
        this.mFileFormat = str;
    }

    public int getmDrmFlag() {
        return this.mDrmFlag;
    }

    public void setmDrmFlag(int i) {
        this.mDrmFlag = i;
    }

    public long getLasttime() {
        return this.mOperateTime;
    }

    public void setLasttime(long j) {
        this.mOperateTime = j;
    }

    public String getLastSongId() {
        return this.lastSongId;
    }

    public void setLastSongId(String str) {
        this.lastSongId = str;
    }

    public long getLastSeekTime() {
        return this.lastSeekTime;
    }

    public void setLastSeekTime(long j) {
        this.lastSeekTime = j;
    }

    public long getCurDownloadChapterId() {
        return this.curDownloadChapterId;
    }

    public void setCurDownloadChapterId(long j) {
        this.curDownloadChapterId = j;
    }

    public long getReadTime() {
        return getLasttime();
    }
}
