package com.tencent;

public enum TIMFriendRelationType {
    TIM_FRIEND_RELATION_TYPE_NONE("CheckResult_Type_NoRelation"),
    TIM_FRIEND_RELATION_TYPE_SELF("CheckResult_Type_AWithB"),
    TIM_FRIEND_RELATION_TYPE_OTHER("CheckResult_Type_BWithA"),
    TIM_FRIEND_RELATION_TYPE_BOTH("CheckResult_Type_BothWay");
    
    private String value;

    private TIMFriendRelationType(String str) {
        this.value = str;
    }

    public final String getValue() {
        return this.value;
    }
}
