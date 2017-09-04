package com.qq.reader.readengine.model;

public class BookTing extends IBook {
    public BookTing(long j, String str) {
        setBookNetId(j);
        setBookPath(str);
        setReadType(1);
    }

    public boolean isAutoParserChapter() {
        return false;
    }
}
