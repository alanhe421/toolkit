package com.qq.reader.plugin.audiobook;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MusicAllTag implements Serializable, Cloneable {
    private long bookId;
    private String buyUrl;
    private List<MusicOnlineTag> onlineTags = new ArrayList();

    public MusicAllTag(long j) {
        this.bookId = j;
    }

    public List<MusicOnlineTag> getOnlineTags() {
        return this.onlineTags;
    }

    public void addOnlineChapterTag(MusicOnlineTag musicOnlineTag) {
        this.onlineTags.add(musicOnlineTag);
    }

    public long getBookId() {
        return this.bookId;
    }

    public void setBookId(long j) {
        this.bookId = j;
    }

    public MusicAllTag clone() {
        try {
            return (MusicAllTag) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public String getBuyUrl() {
        return this.buyUrl;
    }

    public void setBuyUrl(String str) {
        this.buyUrl = str;
    }
}
