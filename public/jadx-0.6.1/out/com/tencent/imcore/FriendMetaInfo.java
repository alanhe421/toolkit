package com.tencent.imcore;

public class FriendMetaInfo {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public FriendMetaInfo() {
        this(internalJNI.new_FriendMetaInfo(), true);
    }

    protected FriendMetaInfo(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(FriendMetaInfo friendMetaInfo) {
        return friendMetaInfo == null ? 0 : friendMetaInfo.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_FriendMetaInfo(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public long getDdwInfoSeq() {
        return internalJNI.FriendMetaInfo_ddwInfoSeq_get(this.swigCPtr, this);
    }

    public long getDdwNextSeq() {
        return internalJNI.FriendMetaInfo_ddwNextSeq_get(this.swigCPtr, this);
    }

    public long getDdwTimestamp() {
        return internalJNI.FriendMetaInfo_ddwTimestamp_get(this.swigCPtr, this);
    }

    public boolean getRecover() {
        return internalJNI.FriendMetaInfo_recover_get(this.swigCPtr, this);
    }

    public void setDdwInfoSeq(long j) {
        internalJNI.FriendMetaInfo_ddwInfoSeq_set(this.swigCPtr, this, j);
    }

    public void setDdwNextSeq(long j) {
        internalJNI.FriendMetaInfo_ddwNextSeq_set(this.swigCPtr, this, j);
    }

    public void setDdwTimestamp(long j) {
        internalJNI.FriendMetaInfo_ddwTimestamp_set(this.swigCPtr, this, j);
    }

    public void setRecover(boolean z) {
        internalJNI.FriendMetaInfo_recover_set(this.swigCPtr, this, z);
    }
}
