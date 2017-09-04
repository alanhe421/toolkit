package com.tencent;

public enum TIMFriendResponseType {
    Agree("Response_Action_Agree"),
    AgreeAndAdd("Response_Action_AgreeAndAdd"),
    Reject("Response_Action_Reject");
    
    private String action;

    private TIMFriendResponseType(String str) {
        this.action = "";
        this.action = str;
    }

    final String getAction() {
        return this.action;
    }
}
