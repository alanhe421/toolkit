package com.tencent;

public class TIMCustomElem extends TIMElem {
    byte[] data;
    private String desc;
    byte[] ext;
    byte[] sound;

    public TIMCustomElem() {
        this.type = TIMElemType.Custom;
    }

    public byte[] getData() {
        return this.data == null ? "".getBytes() : this.data;
    }

    public String getDesc() {
        return this.desc == null ? "" : this.desc;
    }

    public byte[] getExt() {
        return this.ext == null ? "".getBytes() : this.ext;
    }

    public byte[] getSound() {
        return this.sound == null ? "".getBytes() : this.sound;
    }

    public void setData(byte[] bArr) {
        this.data = bArr;
    }

    public void setDesc(String str) {
        this.desc = str;
    }

    public void setExt(byte[] bArr) {
        this.ext = bArr;
    }

    public void setSound(byte[] bArr) {
        this.sound = bArr;
    }
}
