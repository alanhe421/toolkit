package com.qq.reader.plugin.audiobook;

import com.qq.reader.common.download.task.f;
import com.qq.reader.common.download.task.state.TaskStateEnum;
import com.qq.reader.common.utils.ao;
import java.io.File;
import java.io.Serializable;

public class MusicDownloadTask extends f implements Serializable, Comparable<MusicDownloadTask> {
    private static final long serialVersionUID = -5444946602322193641L;
    long mBookId;
    long mChapterId;
    long mCurSize;
    int mProgress;

    public MusicDownloadTask(String str, long j, long j2, int i, String str2, int i2) {
        this(j + "_" + j2, str, j, j2, i, str2, i2);
    }

    public MusicDownloadTask(String str, String str2, long j, long j2, int i, String str3, int i2) {
        super(str, str2, ao.c(j));
        initialize();
        setId((long) str2.hashCode());
        setName(j + "_" + j2);
        setObjectURI(str2);
        setBookFormat(str3);
        this.mBookId = j;
        this.mChapterId = j2;
        this.mProgress = 0;
        setDrmflag(i2);
    }

    public long getBookId() {
        return this.mBookId;
    }

    public long getChapterId() {
        return this.mChapterId;
    }

    public long getCurSize() {
        return this.mCurSize;
    }

    public void setCurSize(long j) {
        this.mCurSize = j;
    }

    private void initialize() {
        setId(0);
        setCurrentSize(0);
        setSize(0);
        this.mCurSize = 0;
        setName("");
        setFilePath("");
        setState(TaskStateEnum.Prepared);
        setProgress(0);
        setStatusCode(f.SUCCESS);
    }

    public int getTaskType() {
        return 102;
    }

    public int compareTo(MusicDownloadTask musicDownloadTask) {
        if (this.mBookId == musicDownloadTask.mBookId && this.mChapterId == musicDownloadTask.mChapterId) {
            return 0;
        }
        if (this.mBookId > musicDownloadTask.mBookId) {
            return 1;
        }
        if (this.mBookId < musicDownloadTask.mBookId) {
            return -1;
        }
        if (this.mChapterId > musicDownloadTask.mChapterId) {
            return 1;
        }
        if (this.mChapterId < musicDownloadTask.mChapterId) {
            return -1;
        }
        return 0;
    }

    public String getFullName() {
        return getName() + "." + this.bookFormat;
    }

    public String getFilePath() {
        if (this.filePath == null || this.filePath.trim().length() <= 0) {
            this.filePath = getDownloadDirectory() + File.separator + this.mBookId + "_" + this.mChapterId + "." + this.bookFormat;
        }
        return this.filePath;
    }
}
