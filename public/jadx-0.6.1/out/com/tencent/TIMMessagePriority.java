package com.tencent;

public enum TIMMessagePriority {
    High(1),
    Normal(2),
    Low(3),
    Lowest(4);
    
    private int opt;

    private TIMMessagePriority(int i) {
        this.opt = i;
    }

    final int getValue() {
        return this.opt;
    }
}
