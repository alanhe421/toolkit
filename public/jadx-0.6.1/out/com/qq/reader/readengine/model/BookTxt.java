package com.qq.reader.readengine.model;

import com.qq.reader.cservice.onlineread.OnlineTag;
import java.io.Serializable;

public class BookTxt extends IBook implements Serializable {
    private static final long serialVersionUID = -2212058193370545905L;

    public BookTxt(String str, String str2, String str3, int i, String str4, long j) {
        this.mBookName = str;
        this.mBookPath = str2;
        this.mAuthor = str3;
        this.mEncoding = i;
        this.mSoSoUrl = str4;
        setBookNetId(j);
    }

    public BookTxt(OnlineTag onlineTag, int i) {
        this.mBookName = onlineTag.b();
        this.mBookPath = onlineTag.c();
        this.mAuthor = onlineTag.o();
        this.mEncoding = i;
        this.mSoSoUrl = onlineTag.v();
        setBookNetId(Long.valueOf(onlineTag.k()).longValue());
    }

    public boolean isAutoParserChapter() {
        return false;
    }
}
