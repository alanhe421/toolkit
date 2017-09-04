package com.qq.reader.liveshow.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class RankItem implements Serializable {
    @SerializedName("authorId")
    public long mAuthorId;
    @SerializedName("icon")
    public String mIcon;
    @SerializedName("nickname")
    public String mNickname;
    @SerializedName("uid")
    public String mUid;
}
