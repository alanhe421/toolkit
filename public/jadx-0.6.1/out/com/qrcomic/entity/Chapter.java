package com.qrcomic.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Chapter implements Serializable, Cloneable {
    public static final int SIZE = 10;
    private static final long serialVersionUID = 1;
    public String chapterName;
    public String chapterTitle;
    public transient boolean isNewLoad;
    public transient boolean isSelected;
    public List<String> sectionIdList;
    public transient List<f> sectionList;
    private transient long size;

    public Chapter() {
        this.size = 0;
        this.isSelected = false;
        this.isNewLoad = true;
        this.sectionList = new ArrayList(10);
    }

    public Chapter(int i) {
        this.size = 0;
        this.isSelected = false;
        this.isNewLoad = true;
        this.sectionList = new ArrayList(i);
    }

    public long getSizeFromSection() {
        if (!(this.sectionList == null || this.sectionList.size() == 0)) {
            for (f fVar : this.sectionList) {
                this.size += fVar.d;
            }
        }
        return this.size;
    }

    public long getSizeFromDownloadHistory() {
        if (!(this.sectionList == null || this.sectionList.size() == 0)) {
            this.size = 0;
            for (f fVar : this.sectionList) {
                if (fVar.r != null) {
                    this.size += fVar.r.g;
                }
            }
        }
        return this.size;
    }

    public boolean getIsSelected() {
        if (this.sectionList == null || this.sectionList.size() == 0) {
            this.isSelected = false;
            return false;
        }
        for (f fVar : this.sectionList) {
            if (!fVar.w) {
                this.isSelected = false;
                return false;
            }
        }
        this.isSelected = true;
        return true;
    }

    public String toString() {
        return "{ chapterName = " + this.chapterName + " , sectionList = " + this.sectionList + " }";
    }
}
