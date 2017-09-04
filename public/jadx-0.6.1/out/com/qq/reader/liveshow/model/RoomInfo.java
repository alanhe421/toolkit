package com.qq.reader.liveshow.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class RoomInfo implements Serializable {
    @SerializedName("authorLevel")
    public int authorLevel;
    @SerializedName("admireCount")
    public int mAdmireCount;
    @SerializedName("authorId")
    public String mAuthorId;
    @SerializedName("cover")
    public String mCover;
    @SerializedName("hlsLink")
    public String mHlsLink;
    @SerializedName("hostAvatar")
    public String mHostAvatar;
    @SerializedName("hostUid")
    public String mHostUid;
    @SerializedName("hostUserName")
    public String mHostUserName;
    @SerializedName("roomId")
    public int mRoomId;
    @SerializedName("rtmpLink")
    public String mRtmpLink;
    @SerializedName("state")
    public int mState;
    @SerializedName("timeSpan")
    public int mTimeSpan;
    @SerializedName("title")
    public String mTitle;
    @SerializedName("totalWatchCount")
    public int mTotalWatchCount;
    @SerializedName("vid")
    public String mVid;
    @SerializedName("showVipLevel")
    public int showVipLevel;
}
