package com.qq.reader.plugin.audiobook;

import com.qq.reader.framework.mark.Mark;
import com.qq.reader.plugin.audiobook.core.SongInfo;

public class MusicDownloadMark extends Mark {
    private static final long serialVersionUID = 6217777316990929898L;
    private long mChapterId;
    private String mChapterName;
    private String mCsize;
    private String mCtime;
    private MusicDownloadTask mDownloadTask;

    public MusicDownloadMark(String str, String str2, String str3, long j, long j2, String str4, String str5, long j3) {
        this.mChapterName = "";
        this.mType = 3;
        this.mId = str;
        this.mBookName = str2;
        this.mChapterName = str3;
        this.mBookId = j;
        this.mChapterId = j2;
        this.mCsize = str5;
        this.mCtime = str4;
        this.mStartPoint = j3;
    }

    protected String getChapterName() {
        return this.mChapterName;
    }

    protected String getCsize() {
        return this.mCsize;
    }

    protected String getCtime() {
        return this.mCtime;
    }

    protected long getChapterId() {
        return this.mChapterId;
    }

    public MusicDownloadTask getDownloadTask() {
        return this.mDownloadTask;
    }

    public void setDownloadTask(MusicDownloadTask musicDownloadTask) {
        this.mDownloadTask = musicDownloadTask;
    }

    public SongInfo changeToSong() {
        return new SongInfo(this.mId, this.mBookId);
    }
}
