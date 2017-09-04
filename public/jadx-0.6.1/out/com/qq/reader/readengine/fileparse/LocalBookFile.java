package com.qq.reader.readengine.fileparse;

import com.qq.reader.framework.mark.Mark;
import com.qq.reader.readengine.model.Chapter;

public class LocalBookFile extends SingleBookFile {
    private Mark mMark = null;

    public LocalBookFile(String str, Mark mark) {
        super(str, 1);
        this.mMark = mark;
    }

    public boolean isExist() {
        return false;
    }

    public void setFileLength(long j) {
    }

    public long getFileLength(long j) {
        return 0;
    }

    public Chapter getChapter() {
        return null;
    }
}
