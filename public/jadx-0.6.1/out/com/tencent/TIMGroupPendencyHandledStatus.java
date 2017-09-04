package com.tencent;

public enum TIMGroupPendencyHandledStatus {
    NOT_HANDLED(0),
    HANDLED_BY_OTHER(1),
    HANDLED_BY_SELF(2);
    
    private int value;

    private TIMGroupPendencyHandledStatus(int i) {
        this.value = 0;
        this.value = i;
    }

    final int getValue() {
        return this.value;
    }
}
