package com.qrcomic.entity;

import java.io.Serializable;

public class ComicSectionReaded implements Serializable {
    private static final long serialVersionUID = 1;
    public int mSectionIndex;
    public int offsetY;
    public String picId;
    public long readTs;
    public String sectionId;

    public ComicSectionReaded(String str, String str2, int i, long j) {
        this.readTs = 0;
        this.sectionId = str;
        this.picId = str2;
        this.offsetY = i;
        this.readTs = j;
    }

    public ComicSectionReaded(String str, String str2, int i, long j, int i2) {
        this(str, str2, i, j);
        this.mSectionIndex = i2;
    }

    public String toString() {
        return "{ sectionId = " + this.sectionId + " , picId = " + this.picId + " , offsetY = " + this.offsetY + ", readTs = " + this.readTs + " }";
    }
}
