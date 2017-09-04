package com.qq.reader.module.bookchapter.online;

import com.qq.reader.common.db.handle.v;
import com.qq.reader.readengine.model.Chapter;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class OnlineChapter extends Chapter {
    private static final long serialVersionUID = 1545312631334402826L;
    private String mAuthorWords;
    private long mBookId;
    private String mBookName;
    private int mChapterId;
    private String mChapterMD5 = "";
    private String mChapterName;
    private int mCommentCount;
    private long mFeeWords;
    private List<OnlineChapterComment> mHotCommentList = new ArrayList();
    private int mIsFree;
    private float mPrice;
    private int mReadCount;
    private int mRedPacketAmount;
    protected float mSize;
    private long mUUID;
    private long mWords;
    private int mWprice;

    public float getPrice() {
        return this.mPrice;
    }

    public void setPrice(float f) {
        this.mPrice = f;
    }

    public long getBookId() {
        return this.mBookId;
    }

    public void setBookId(long j) {
        this.mBookId = j;
    }

    public String getBookName() {
        return this.mBookName;
    }

    public void setBookName(String str) {
        this.mBookName = str;
    }

    public int getChapterId() {
        return this.mChapterId;
    }

    public void setChapterId(int i) {
        this.mChapterId = i;
    }

    public String getChapterName() {
        return this.mChapterName;
    }

    public void setChapterName(String str) {
        this.mChapterName = str;
    }

    public long getWords() {
        return this.mWords;
    }

    public void setWords(long j) {
        this.mWords = j;
    }

    public long getFeeWords() {
        return this.mFeeWords;
    }

    public void setFeeWords(long j) {
        this.mFeeWords = j;
    }

    public boolean getBooleanIsFree() {
        return this.mIsFree == 1;
    }

    public int getIntIsFree() {
        return this.mIsFree;
    }

    public void setIsFree(int i) {
        this.mIsFree = i;
    }

    public String getFilePath() {
        return v.a("" + getBookId(), getChapterId());
    }

    public String getFileDir() {
        String str = "" + getBookId();
        if (str == null || str.length() <= 0 || getChapterId() <= 0) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(v.c(str));
        stringBuilder.append(File.separator);
        return stringBuilder.toString();
    }

    public String getFileName() {
        return getChapterId() + ".qct";
    }

    public String getChapterMD5() {
        if (this.mChapterMD5 == null) {
            return "";
        }
        return this.mChapterMD5;
    }

    public void setChapterMD5(String str) {
        if (str != null) {
            this.mChapterMD5 = str;
        }
    }

    public int hashCode() {
        return ((((int) (this.mBookId ^ (this.mBookId >>> 32))) + 31) * 31) + (this.mChapterId ^ (this.mChapterId >>> 32));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof OnlineChapter)) {
            return false;
        }
        OnlineChapter onlineChapter = (OnlineChapter) obj;
        if (this.mBookId != onlineChapter.mBookId) {
            return false;
        }
        if (this.mChapterId != onlineChapter.mChapterId) {
            return false;
        }
        return true;
    }

    public int getWprice() {
        return this.mWprice;
    }

    public void setWprice(int i) {
        this.mWprice = i;
    }

    public String getAuthorWords() {
        return this.mAuthorWords;
    }

    public void setAuthorWords(String str) {
        this.mAuthorWords = str;
    }

    public int getCommentCount() {
        return this.mCommentCount;
    }

    public void setCommentCount(int i) {
        this.mCommentCount = i;
    }

    public long getUUID() {
        return this.mUUID;
    }

    public void setUUID(long j) {
        this.mUUID = j;
    }

    public float getSize() {
        return this.mSize;
    }

    public void setSize(float f) {
        this.mSize = f;
    }

    public String getChapterTagName() {
        return "章";
    }

    public List<OnlineChapterComment> getHotCommentList() {
        return this.mHotCommentList;
    }

    public void clearHotCommentList() {
        this.mHotCommentList.clear();
    }

    public void addHotComment(OnlineChapterComment onlineChapterComment) {
        this.mHotCommentList.add(onlineChapterComment);
    }

    public void addHotComment(List<OnlineChapterComment> list) {
        this.mHotCommentList.addAll(list);
    }

    public int getReadCount() {
        return this.mReadCount;
    }

    public void setReadCount(int i) {
        this.mReadCount = i;
    }

    public int getRedPacketAmount() {
        return this.mRedPacketAmount;
    }

    public void setRedPacketAmount(int i) {
        this.mRedPacketAmount = i;
    }
}
