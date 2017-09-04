package com.qq.reader.readengine.fileparse;

import com.qq.reader.module.bookchapter.online.OnlineChapter;
import com.qq.reader.readengine.model.Chapter;

public class OnlineBookFile extends SingleBookFile {
    private static final long serialVersionUID = 1;
    private OnlineChapter mChapter = null;

    public OnlineBookFile(String str, OnlineChapter onlineChapter, int i) {
        super(str, i);
        setChapter(onlineChapter);
    }

    public void setChapter(OnlineChapter onlineChapter) {
        this.mChapter = onlineChapter;
    }

    public void setFileLength(long j) {
        this.mFileLength = j;
    }

    public long getFileLength(long j) {
        if (j > 0) {
            this.mFileDefaultLength = j;
            return this.mFileDefaultLength;
        }
        checkFile();
        return this.mFileLength;
    }

    public Chapter getChapter() {
        return this.mChapter;
    }
}
