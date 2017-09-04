package com.tencent;

public enum TIMFriendAllowType {
    TIM_FRIEND_INVALID("AllowType_Type_Invalid"),
    TIM_FRIEND_ALLOW_ANY("AllowType_Type_AllowAny"),
    TIM_FRIEND_DENY_ANY("AllowType_Type_DenyAny"),
    TIM_FRIEND_NEED_CONFIRM("AllowType_Type_NeedConfirm");
    
    private String type;

    private TIMFriendAllowType(String str) {
        this.type = "";
        this.type = str;
    }

    final String getType() {
        return this.type;
    }
}
