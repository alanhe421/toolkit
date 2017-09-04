package com.qq.reader.plugin.audiobook.model;

import com.qq.reader.module.bookchapter.online.OnlineChapter;

public class MusicChapter extends OnlineChapter {
    public static final int PAY_TYPE_BOOK = 2;
    public static final int PAY_TYPE_CHAPTER = 3;
    public static final int PAY_TYPE_FREE = 1;
    private long mAcid;
    private long mDuration;

    public long getDuration() {
        return this.mDuration;
    }

    public void setDuration(long j) {
        this.mDuration = j;
    }

    public long getAcid() {
        return this.mAcid;
    }

    public void setAcid(long j) {
        this.mAcid = j;
    }

    public String getChapterTagName() {
        return "集";
    }
}
