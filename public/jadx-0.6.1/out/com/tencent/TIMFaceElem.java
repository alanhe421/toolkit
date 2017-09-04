package com.tencent;

public class TIMFaceElem extends TIMElem {
    private byte[] data;
    private int index;

    public TIMFaceElem() {
        this.type = TIMElemType.Face;
    }

    public byte[] getData() {
        return this.data;
    }

    public int getIndex() {
        return this.index;
    }

    public void setData(byte[] bArr) {
        this.data = bArr;
    }

    public void setIndex(int i) {
        this.index = i;
    }
}
