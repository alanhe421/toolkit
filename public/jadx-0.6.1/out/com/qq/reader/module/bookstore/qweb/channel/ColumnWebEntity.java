package com.qq.reader.module.bookstore.qweb.channel;

import android.text.TextUtils;
import java.io.Serializable;

public class ColumnWebEntity implements Serializable, Comparable<ColumnWebEntity> {
    private static final long serialVersionUID = 1;
    private String linkurl;
    private Object obj = new Object();
    private int selected;
    private String suffixUrl;
    private int tilteIndex;
    private String titleName;
    private int titleType;
    private int titleid;
    private boolean visible = true;
    private int webid;

    public void setLinkUrl(String str) {
        this.linkurl = str;
    }

    public void setSuffixUrl(String str) {
        this.suffixUrl = str;
    }

    public ColumnWebEntity(int i, int i2, String str, int i3, int i4, String str2) {
        this.titleid = i;
        this.webid = i2;
        this.titleName = str;
        this.titleType = i3;
        this.selected = i4;
        this.linkurl = str2;
    }

    public void setVisible(boolean z) {
        synchronized (this.obj) {
            this.visible = z;
        }
    }

    public boolean getViewVisible() {
        boolean z;
        synchronized (this.obj) {
            z = this.visible;
        }
        return z;
    }

    public void setSelected(int i) {
        this.selected = i;
    }

    public int getSelect() {
        return this.selected;
    }

    public String getLinkUrl() {
        return this.linkurl;
    }

    public String getSuffixUrl() {
        if (TextUtils.isEmpty(this.suffixUrl)) {
            return "";
        }
        return this.suffixUrl;
    }

    public int getTitleid() {
        return this.titleid;
    }

    public String getIdStr() {
        return String.valueOf(this.titleid);
    }

    public void setTitleid(int i) {
        this.titleid = i;
    }

    public String getTitleName() {
        return this.titleName;
    }

    public void setTitleName(String str) {
        this.titleName = str;
    }

    public int getTitleType() {
        return this.titleType;
    }

    public Integer getTilteIndex() {
        return Integer.valueOf(this.tilteIndex);
    }

    public void setTilteIndex(Integer num) {
        this.tilteIndex = num.intValue();
    }

    public int getWebid() {
        return this.webid;
    }

    public void setWebid(int i) {
        this.webid = i;
    }

    public int compareTo(ColumnWebEntity columnWebEntity) {
        return getTilteIndex().compareTo(columnWebEntity.getTilteIndex());
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ColumnWebEntity)) {
            return false;
        }
        if (this.titleid == ((ColumnWebEntity) obj).titleid && this.webid == ((ColumnWebEntity) obj).webid) {
            return true;
        }
        return false;
    }
}
