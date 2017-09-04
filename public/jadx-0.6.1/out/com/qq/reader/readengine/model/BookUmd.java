package com.qq.reader.readengine.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BookUmd extends IBook implements Serializable {
    private static final long serialVersionUID = -948833793262252520L;
    private int mChapterNumber = 0;
    private int[] mChapterOffSets;
    private List<String> mChapterTitles;
    private ArrayList<Chunk> mChunks = new ArrayList();
    private long mContentStartPoint = -1;
    private String mDay = "";
    private String mGender = "";
    private String mMonth = "";
    private String mPublisher = "";
    private String mYear = "";

    public ArrayList<Chunk> getChunks() {
        return this.mChunks;
    }

    public void addChunks(Chunk chunk) {
        this.mChunks.add(chunk);
    }

    public BookUmd(String str, String str2, String str3) {
        this.mBookName = str;
        this.mBookPath = str2;
        this.mAuthor = str3;
        this.mEncoding = 100;
    }

    public String getYear() {
        return this.mYear;
    }

    public void setYear(String str) {
        this.mYear = str;
    }

    public String getMonth() {
        return this.mMonth;
    }

    public void setMonth(String str) {
        this.mMonth = str;
    }

    public String getDay() {
        return this.mDay;
    }

    public void setDay(String str) {
        this.mDay = str;
    }

    public String getGender() {
        return this.mGender;
    }

    public void setGender(String str) {
        this.mGender = str;
    }

    public String getPublisher() {
        return this.mPublisher;
    }

    public void setPublisher(String str) {
        this.mPublisher = str;
    }

    public int getChapterNumber() {
        return this.mChapterNumber;
    }

    public void setChapterNumber(int i) {
        this.mChapterNumber = i;
    }

    public int[] getChapterOffSets() {
        return this.mChapterOffSets;
    }

    public void setChapterOffSets(int[] iArr) {
        this.mChapterOffSets = iArr;
    }

    public List<String> getChapterTitles() {
        return this.mChapterTitles;
    }

    public void setChapterTitles(List<String> list) {
        this.mChapterTitles = list;
    }

    public long getContentStartPoint() {
        return this.mContentStartPoint;
    }

    public void setContentStartPoint(long j) {
        this.mContentStartPoint = j;
    }

    public boolean isAutoParserChapter() {
        return true;
    }
}
