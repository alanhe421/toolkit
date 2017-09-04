package com.tencent;

public class TIMFriendMetaInfo {
    private long infoSeq;
    private long nextSeq;
    private boolean recover;
    private long timestamp;

    public long getInfoSeq() {
        return this.infoSeq;
    }

    public long getNextSeq() {
        return this.nextSeq;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public boolean isRecover() {
        return this.recover;
    }

    public void setInfoSeq(long j) {
        this.infoSeq = j;
    }

    public void setNextSeq(long j) {
        this.nextSeq = j;
    }

    public void setRecover(boolean z) {
        this.recover = z;
    }

    public void setTimestamp(long j) {
        this.timestamp = j;
    }
}
