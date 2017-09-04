package com.qq.reader.liveshow.model;

import com.google.gson.annotations.SerializedName;
import com.qq.reader.liveshow.model.im.viewdata.GiftItem;
import java.io.Serializable;
import java.util.List;

public class RoomDetail implements Serializable {
    @SerializedName("gift")
    public List<GiftItem> gift;
    @SerializedName("code")
    public int mCode;
    @SerializedName("rank")
    public List<RankItem> rank;
    @SerializedName("roomInfo")
    public RoomInfo roomInfo;

    public String toString() {
        return "RoomDetail{roomInfo=" + this.roomInfo + ", mCode=" + this.mCode + ", rank=" + this.rank + ", gift=" + this.gift + '}';
    }
}
