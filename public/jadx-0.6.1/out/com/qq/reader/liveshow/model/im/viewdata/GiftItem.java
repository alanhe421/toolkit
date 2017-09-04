package com.qq.reader.liveshow.model.im.viewdata;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class GiftItem implements Serializable {
    @SerializedName("h5url")
    public String h5Url;
    @SerializedName("id")
    public int mId;
    @SerializedName("image")
    public String mImgUrl;
    @SerializedName("name")
    public String mName;
    @SerializedName("price")
    public int mPrice;
    @SerializedName("type")
    public int mType = -1;
    @SerializedName("showTime")
    public long showTime;
    @SerializedName("unit")
    public String unit;
}
