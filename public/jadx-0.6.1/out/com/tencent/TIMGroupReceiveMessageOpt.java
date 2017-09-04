package com.tencent;

public enum TIMGroupReceiveMessageOpt {
    ReceiveAndNotify(0),
    NotReceive(1),
    ReceiveNotNotify(2);
    
    private long opt;

    private TIMGroupReceiveMessageOpt(long j) {
        this.opt = j;
    }

    final long getValue() {
        return this.opt;
    }
}
