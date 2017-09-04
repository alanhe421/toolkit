package com.tencent;

public class TIMProfileSystemElem extends TIMElem {
    private String fromUser;
    private String nickName;
    private TIMProfileSystemType subType;

    public TIMProfileSystemElem() {
        this.fromUser = "";
        this.nickName = "";
        this.type = TIMElemType.ProfileTips;
    }

    public String getFromUser() {
        return this.fromUser;
    }

    public String getNickName() {
        return this.nickName;
    }

    public TIMProfileSystemType getSubType() {
        return this.subType;
    }

    void setFromUser(String str) {
        this.fromUser = str;
    }

    void setNickName(String str) {
        this.nickName = str;
    }

    void setSubType(long j) {
        if (j == ((long) TIMProfileSystemType.TIM_PROFILE_SYSTEM_FRIEND_PROFILE_CHANGE.ordinal())) {
            this.subType = TIMProfileSystemType.TIM_PROFILE_SYSTEM_FRIEND_PROFILE_CHANGE;
        } else {
            this.subType = TIMProfileSystemType.INVALID;
        }
    }
}
