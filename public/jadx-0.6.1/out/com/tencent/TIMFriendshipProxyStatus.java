package com.tencent;

public enum TIMFriendshipProxyStatus {
    TIM_FRIENDSHIP_STATUS_NONE(0),
    TIM_FRIENDSHIP_STATUS_SYNCING(1),
    TIM_FRIENDSHIP_STATUS_SYNCED(2),
    TIM_FRIENDSHIP_STATUS_FAILED(3);
    
    private int status;

    private TIMFriendshipProxyStatus(int i) {
        this.status = 0;
        this.status = i;
    }

    public final int getStatus() {
        return this.status;
    }
}
