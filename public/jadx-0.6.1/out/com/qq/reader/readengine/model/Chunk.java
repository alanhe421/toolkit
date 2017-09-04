package com.qq.reader.readengine.model;

import java.io.Serializable;

public class Chunk implements Serializable {
    private static final long serialVersionUID = -842799073773332921L;
    private long mStartPointZip;
    private int mZlibSize;

    public Chunk(int i, long j) {
        this.mZlibSize = i;
        this.mStartPointZip = j;
    }

    public int getZlibSize() {
        return this.mZlibSize;
    }

    public long getStartPointZip() {
        return this.mStartPointZip;
    }
}
