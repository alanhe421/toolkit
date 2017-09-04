package com.tencent;

public enum TIMGroupPendencyOperationType {
    REFUSE(0),
    ACCEPT(1);
    
    private int value;

    private TIMGroupPendencyOperationType(int i) {
        this.value = 1;
        this.value = i;
    }

    final int getValue() {
        return this.value;
    }
}
