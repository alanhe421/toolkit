package com.qq.reader.view.metro;

import java.io.Serializable;

public class MetroItem implements Serializable {
    public static final int METRO_PLUS = -100;
    private static final long serialVersionUID = 8509991005723638947L;
    private int mCount;
    private int mId;
    private String mName;

    public MetroItem(int i, String str) {
        this.mId = i;
        this.mName = str;
        this.mCount = -1;
    }

    public MetroItem(int i, String str, int i2) {
        this.mId = i;
        this.mName = str;
        this.mCount = i2;
    }

    public int getId() {
        return this.mId;
    }

    public String getName() {
        return this.mName;
    }

    public String getDisplayName() {
        return this.mName;
    }

    public int getCount() {
        return this.mCount;
    }
}
