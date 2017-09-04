package com.tencent;

public class TIMAddFriendRequest {
    private String addSource = "";
    private String addWording = "";
    private String friendGroup = "";
    private String identifier = "";
    private String remark = "";

    public String getAddSource() {
        return this.addSource;
    }

    public String getAddWording() {
        return this.addWording;
    }

    public String getFriendGroup() {
        return this.friendGroup;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setAddWording(String str) {
        this.addWording = str;
    }

    public void setAddrSource(String str) {
        this.addSource = str;
    }

    public void setFriendGroup(String str) {
        this.friendGroup = str;
    }

    public void setIdentifier(String str) {
        this.identifier = str;
    }

    public void setRemark(String str) {
        this.remark = str;
    }
}
