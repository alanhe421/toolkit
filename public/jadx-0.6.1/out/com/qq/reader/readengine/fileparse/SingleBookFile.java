package com.qq.reader.readengine.fileparse;

import com.qq.reader.common.db.handle.v;
import com.qq.reader.readengine.model.Chapter;
import java.io.File;
import java.io.Serializable;

public abstract class SingleBookFile implements Serializable {
    private static final long serialVersionUID = -8081231788798158957L;
    protected int mChapterId = 0;
    protected File mFile = null;
    protected long mFileDefaultLength = 0;
    protected long mFileLength = 0;
    protected String mFilePath = null;
    protected String mRootPath;

    public abstract Chapter getChapter();

    public abstract long getFileLength(long j);

    public abstract void setFileLength(long j);

    public SingleBookFile(String str, int i) {
        this.mRootPath = str;
        this.mChapterId = i;
    }

    public int getChapterId() {
        return this.mChapterId;
    }

    public String getBookPath() {
        return this.mFilePath;
    }

    public void checkFile() {
        if (this.mFile == null) {
            this.mFilePath = v.b(this.mRootPath, this.mChapterId);
            this.mFile = new File(this.mFilePath);
            this.mFileLength = this.mFile.length();
        }
    }

    public void reInitFileLength() {
        checkFile();
        this.mFileLength = this.mFile.length();
    }

    public boolean isExist() {
        checkFile();
        if (this.mFile.exists()) {
            return true;
        }
        return false;
    }

    public void close() {
        if (this.mFile == null) {
        }
    }
}
