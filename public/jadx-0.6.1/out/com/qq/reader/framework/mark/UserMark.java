package com.qq.reader.framework.mark;

public class UserMark extends Mark {
    private static final long serialVersionUID = 5107290808052912810L;
    private int mChapterId;
    private long mChapterOffset;
    private long mDbId = 0;

    public UserMark(long j, String str, String str2, int i, long j2, long j3, int i2, long j4, String str3, String str4) {
        this.mBookId = j;
        this.mId = str;
        setBookName(str2);
        this.mChapterId = i;
        this.mChapterOffset = j2;
        this.mStartPoint = j3;
        this.mType = i2;
        this.mPercentStr = str3;
        this.mOperateTime = j4;
        this.mDescriptionStr = str4;
    }

    public int getChapterId() {
        return this.mChapterId;
    }

    public void setChapterId(int i) {
        this.mChapterId = i;
    }

    public long getChapterOffset() {
        return this.mChapterOffset;
    }

    public void setChapterOffset(long j) {
        this.mChapterOffset = j;
    }

    public long getDbId() {
        return this.mDbId;
    }

    public void setDbId(long j) {
        this.mDbId = j;
    }

    public boolean equals(Object obj) {
        return getId().equals(((Mark) obj).getId()) && this.mChapterId == ((UserMark) obj).getChapterId() && this.mChapterOffset == ((UserMark) obj).getChapterOffset() && this.mOperateTime == ((Mark) obj).getOperateTime();
    }

    public int hashCode() {
        return ((((((getId().hashCode() + 31) * 31) + (this.mChapterId ^ (this.mChapterId >>> 32))) * 31) + ((int) (this.mChapterOffset ^ (this.mChapterOffset >>> 32)))) * 31) + ((int) (this.mOperateTime ^ (this.mOperateTime >>> 32)));
    }
}
