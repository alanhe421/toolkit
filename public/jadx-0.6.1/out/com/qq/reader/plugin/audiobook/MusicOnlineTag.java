package com.qq.reader.plugin.audiobook;

import com.qq.reader.appconfig.e;
import java.io.Serializable;

public class MusicOnlineTag implements Serializable, Cloneable {
    private static final long serialVersionUID = 4755905662373694114L;
    private int chapterCount;
    private String mAuthor = "";
    private long mBid;
    private String mBname = "";
    private String mBtime = "";
    private String mBuyUrl = "";
    private long mCid = -1;
    private String mCname = "";
    private String mCsize = "";
    private String mCtime = "";
    private String mDownloadUrl = "";
    private int mDrmFlag = 2;
    private String mFileFormat = "mp3";
    private String mPrice = "";
    private String mReader = "";

    public MusicOnlineTag(long j) {
        this.mBid = j;
    }

    public MusicOnlineTag setDrmFlag(int i) {
        this.mDrmFlag = i;
        return this;
    }

    public MusicOnlineTag setFileFormat(String str) {
        this.mFileFormat = str;
        return this;
    }

    public MusicOnlineTag setBuyUrl(String str) {
        this.mBuyUrl = e.aj + str.substring(str.indexOf("?") + 1);
        return this;
    }

    public MusicOnlineTag setDownloadUrl(String str) {
        this.mDownloadUrl = str;
        return this;
    }

    public MusicOnlineTag setBname(String str) {
        this.mBname = str;
        return this;
    }

    public MusicOnlineTag setAuthor(String str) {
        this.mAuthor = str;
        return this;
    }

    public MusicOnlineTag setReader(String str) {
        this.mReader = str;
        return this;
    }

    public MusicOnlineTag setBtime(String str) {
        this.mBtime = str;
        return this;
    }

    public MusicOnlineTag setPrice(String str) {
        this.mPrice = str;
        return this;
    }

    public MusicOnlineTag setCsize(String str) {
        this.mCsize = str;
        return this;
    }

    public MusicOnlineTag setCtime(String str) {
        this.mCtime = str;
        return this;
    }

    public MusicOnlineTag setCid(long j) {
        this.mCid = j;
        return this;
    }

    public MusicOnlineTag setCname(String str) {
        this.mCname = str;
        return this;
    }

    public int getmDrmFlag() {
        return this.mDrmFlag;
    }

    public String getFileFormat() {
        return this.mFileFormat;
    }

    public long getBid() {
        return this.mBid;
    }

    public String getBname() {
        return this.mBname;
    }

    public String getAuthor() {
        return this.mAuthor;
    }

    public String getReader() {
        return this.mReader;
    }

    public String getBtime() {
        return this.mBtime;
    }

    public String getPrice() {
        return this.mPrice;
    }

    public String getCsize() {
        return this.mCsize;
    }

    public String getCtime() {
        return this.mCtime;
    }

    public long getCid() {
        return this.mCid;
    }

    public String getCname() {
        return this.mCname;
    }

    public String getDownloadUrl() {
        return this.mDownloadUrl;
    }

    public String getBuyUrl() {
        return this.mBuyUrl;
    }

    public MusicOnlineTag clone() {
        try {
            return (MusicOnlineTag) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public int getChapterCount() {
        return this.chapterCount;
    }

    public MusicOnlineTag setChapterCount(int i) {
        this.chapterCount = i;
        return this;
    }
}
