package com.tencent;

import com.tencent.imcore.AddFriendReq;
import com.tencent.imcore.FriendChangeUserInfo;
import java.io.UnsupportedEncodingException;

public class TIMSNSChangeInfo {
    private String identifier = "";
    private String nickName = "";
    private String remark = "";
    private String source = "";
    private String wording = "";

    TIMSNSChangeInfo(AddFriendReq addFriendReq) {
        if (addFriendReq != null) {
            setIdentifier(addFriendReq.getIdentifier());
            try {
                setSource(new String(addFriendReq.getSource(), "utf-8"));
                setWording(new String(addFriendReq.getWording(), "utf-8"));
                setNickName(new String(addFriendReq.getNickname(), "utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    TIMSNSChangeInfo(FriendChangeUserInfo friendChangeUserInfo) {
        if (friendChangeUserInfo != null) {
            setIdentifier(friendChangeUserInfo.getIdentifier());
            try {
                setSource(new String(friendChangeUserInfo.getAdd_source(), "utf-8"));
                setWording(new String(friendChangeUserInfo.getAdd_wording(), "utf-8"));
                setNickName(new String(friendChangeUserInfo.getNick(), "utf-8"));
                setRemark(new String(friendChangeUserInfo.getRemark(), "utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public String getNickName() {
        return this.nickName;
    }

    public String getRemark() {
        return this.remark;
    }

    public String getSource() {
        return this.source;
    }

    public String getWording() {
        return this.wording;
    }

    void setIdentifier(String str) {
        this.identifier = str;
    }

    void setNickName(String str) {
        this.nickName = str;
    }

    void setRemark(String str) {
        this.remark = str;
    }

    void setSource(String str) {
        this.source = str;
    }

    void setWording(String str) {
        this.wording = str;
    }
}
