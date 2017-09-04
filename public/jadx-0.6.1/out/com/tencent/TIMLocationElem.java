package com.tencent;

public class TIMLocationElem extends TIMElem {
    private String desc;
    private double latitude;
    private double longitude;

    public TIMLocationElem() {
        this.type = TIMElemType.Location;
    }

    public String getDesc() {
        return this.desc;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public void setDesc(String str) {
        this.desc = str;
    }

    public void setLatitude(double d) {
        this.latitude = d;
    }

    public void setLongitude(double d) {
        this.longitude = d;
    }
}
