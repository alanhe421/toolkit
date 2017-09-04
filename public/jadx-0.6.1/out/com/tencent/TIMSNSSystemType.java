package com.tencent;

public enum TIMSNSSystemType {
    INVALID(0),
    TIM_SNS_SYSTEM_ADD_FRIEND(1),
    TIM_SNS_SYSTEM_DEL_FRIEND(2),
    TIM_SNS_SYSTEM_ADD_FRIEND_REQ(3),
    TIM_SNS_SYSTEM_DEL_FRIEND_REQ(4),
    TIM_SNS_SYSTEM_ADD_BLACKLIST(5),
    TIM_SNS_SYSTEM_DEL_BLACKLIST(6),
    TIM_SNS_SYSTEM_PENDENCY_REPORT(7),
    TIM_SNS_SYSTEM_SNS_PROFILE_CHANGE(8),
    TIM_SNS_SYSTEM_ADD_RECOMMEND(9),
    TIM_SNS_SYSTEM_DEL_RECOMMEND(10),
    TIM_SNS_SYSTEM_ADD_DECIDE(11),
    TIM_SNS_SYSTEM_DEL_DECIDE(12),
    TIM_SNS_SYSTEM_RECOMMEND_REPORT(13),
    TIM_SNS_SYSTEM_DECIDE_REPORT(14);
    
    private int value;

    private TIMSNSSystemType(int i) {
        this.value = i;
    }

    static TIMSNSSystemType getType(int i) {
        switch (i) {
            case 1:
                return TIM_SNS_SYSTEM_ADD_FRIEND;
            case 2:
                return TIM_SNS_SYSTEM_DEL_FRIEND;
            case 3:
                return TIM_SNS_SYSTEM_ADD_FRIEND_REQ;
            case 4:
                return TIM_SNS_SYSTEM_DEL_FRIEND_REQ;
            case 5:
                return TIM_SNS_SYSTEM_ADD_BLACKLIST;
            case 6:
                return TIM_SNS_SYSTEM_DEL_BLACKLIST;
            case 7:
                return TIM_SNS_SYSTEM_PENDENCY_REPORT;
            case 8:
                return TIM_SNS_SYSTEM_SNS_PROFILE_CHANGE;
            case 9:
                return TIM_SNS_SYSTEM_ADD_RECOMMEND;
            case 10:
                return TIM_SNS_SYSTEM_DEL_RECOMMEND;
            case 11:
                return TIM_SNS_SYSTEM_ADD_DECIDE;
            case 12:
                return TIM_SNS_SYSTEM_DEL_DECIDE;
            case 13:
                return TIM_SNS_SYSTEM_RECOMMEND_REPORT;
            case 14:
                return TIM_SNS_SYSTEM_DECIDE_REPORT;
            default:
                return INVALID;
        }
    }

    public final int getValue() {
        return this.value;
    }
}
