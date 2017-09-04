package com.tencent;

public enum TIMNetworkStatus {
    TIM_NETWORK_STATUS_CONNECTED(1),
    TIM_NETWORK_STATUS_DISCONNECTED(2);
    
    private long value;

    private TIMNetworkStatus(long j) {
        this.value = j;
    }

    final long getValue() {
        return this.value;
    }
}
