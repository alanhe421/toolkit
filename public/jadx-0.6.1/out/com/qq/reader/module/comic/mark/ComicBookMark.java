package com.qq.reader.module.comic.mark;

import android.text.TextUtils;
import com.qq.reader.common.imageloader.a.a.a;
import com.qq.reader.common.utils.ao;
import com.qq.reader.framework.mark.LocalMark;

public class ComicBookMark extends LocalMark {
    protected long mCid;
    protected long mPicId;
    protected int mPicIndex;
    protected int mPicOffset;
    protected long mSectionId;
    protected int mSectionIndex;

    public ComicBookMark(long j) {
        this(j, "");
    }

    public ComicBookMark(long j, String str) {
        super(9, String.valueOf(j), 0, str, false);
        this.mBookId = j;
        this.mCid = j;
    }

    public void copyValue(ComicBookMark comicBookMark) {
        if (comicBookMark != null) {
            setSectionId(comicBookMark.getSectionId());
            setSectionIndex(comicBookMark.getSectionIndex());
            setPicId(comicBookMark.getPicId());
            setPicIndex(comicBookMark.getPicIndex());
            setPicOffset(comicBookMark.getPicOffset());
        }
    }

    public long getCid() {
        return this.mCid;
    }

    public void setCid(long j) {
        this.mCid = j;
    }

    public long getSectionId() {
        return this.mSectionId;
    }

    public void setSectionId(long j) {
        this.mSectionId = j;
    }

    public long getPicId() {
        return this.mPicId;
    }

    public void setPicId(long j) {
        this.mPicId = j;
    }

    public int getSectionIndex() {
        return this.mSectionIndex;
    }

    public void setSectionIndex(int i) {
        this.mSectionIndex = i;
    }

    public int getPicIndex() {
        return this.mPicIndex;
    }

    public void setPicIndex(int i) {
        this.mPicIndex = i;
    }

    public int getPicOffset() {
        return this.mPicOffset;
    }

    public void setPicOffset(int i) {
        this.mPicOffset = i;
    }

    public String getImageURI() {
        if (this.mCoverUrl == null || this.mCoverUrl.length() == 0) {
            this.mCoverUrl = ao.h(getBookId());
            if (TextUtils.isEmpty(this.mCoverUrl)) {
                this.mCoverUrl = a.a(1, getBookShortName());
            }
        }
        return this.mCoverUrl;
    }

    public String getImagePath() {
        if (this.mCoverUrl == null || this.mCoverUrl.length() == 0) {
            this.mCoverUrl = ao.h(getBookId());
        }
        return this.mCoverUrl;
    }
}
