package com.tencent;

public enum TIMFutureFriendType {
    TIM_FUTURE_FRIEND_PENDENCY_IN_TYPE(1),
    TIM_FUTURE_FRIEND_PENDENCY_OUT_TYPE(2),
    TIM_FUTURE_FRIEND_RECOMMEND_TYPE(4),
    TIM_FUTURE_FRIEND_DECIDE_TYPE(8);
    
    private int value;

    private TIMFutureFriendType(int i) {
        this.value = i;
    }

    public final int getValue() {
        return this.value;
    }
}
