package com.tencent.imcore;

public class GroupMsgSeqInfo {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public GroupMsgSeqInfo() {
        this(internalJNI.new_GroupMsgSeqInfo(), true);
    }

    protected GroupMsgSeqInfo(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(GroupMsgSeqInfo groupMsgSeqInfo) {
        return groupMsgSeqInfo == null ? 0 : groupMsgSeqInfo.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_GroupMsgSeqInfo(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public long getReadSeq() {
        return internalJNI.GroupMsgSeqInfo_readSeq_get(this.swigCPtr, this);
    }

    public long getSvrLatestSeq() {
        return internalJNI.GroupMsgSeqInfo_svrLatestSeq_get(this.swigCPtr, this);
    }

    public void setReadSeq(long j) {
        internalJNI.GroupMsgSeqInfo_readSeq_set(this.swigCPtr, this, j);
    }

    public void setSvrLatestSeq(long j) {
        internalJNI.GroupMsgSeqInfo_svrLatestSeq_set(this.swigCPtr, this, j);
    }
}
