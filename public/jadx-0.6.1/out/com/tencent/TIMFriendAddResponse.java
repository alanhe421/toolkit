package com.tencent;

public class TIMFriendAddResponse {
    private String identifier = "";
    private String remark = "";
    private TIMFriendResponseType type;

    String getIdentifier() {
        return this.identifier;
    }

    String getRemark() {
        return this.remark;
    }

    TIMFriendResponseType getType() {
        return this.type;
    }

    public void setIdentifier(String str) {
        this.identifier = str;
    }

    public void setRemark(String str) {
        this.remark = str;
    }

    public void setType(TIMFriendResponseType tIMFriendResponseType) {
        this.type = tIMFriendResponseType;
    }
}
