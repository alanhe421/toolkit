package com.tencent.imcore;

public class FriendPendencyMeta {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public FriendPendencyMeta() {
        this(internalJNI.new_FriendPendencyMeta(), true);
    }

    protected FriendPendencyMeta(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(FriendPendencyMeta friendPendencyMeta) {
        return friendPendencyMeta == null ? 0 : friendPendencyMeta.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_FriendPendencyMeta(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public long getDdwNumPerPage() {
        return internalJNI.FriendPendencyMeta_ddwNumPerPage_get(this.swigCPtr, this);
    }

    public long getDdwSeq() {
        return internalJNI.FriendPendencyMeta_ddwSeq_get(this.swigCPtr, this);
    }

    public long getDdwTimestamp() {
        return internalJNI.FriendPendencyMeta_ddwTimestamp_get(this.swigCPtr, this);
    }

    public long getDdwUnReadCnt() {
        return internalJNI.FriendPendencyMeta_ddwUnReadCnt_get(this.swigCPtr, this);
    }

    public void setDdwNumPerPage(long j) {
        internalJNI.FriendPendencyMeta_ddwNumPerPage_set(this.swigCPtr, this, j);
    }

    public void setDdwSeq(long j) {
        internalJNI.FriendPendencyMeta_ddwSeq_set(this.swigCPtr, this, j);
    }

    public void setDdwTimestamp(long j) {
        internalJNI.FriendPendencyMeta_ddwTimestamp_set(this.swigCPtr, this, j);
    }

    public void setDdwUnReadCnt(long j) {
        internalJNI.FriendPendencyMeta_ddwUnReadCnt_set(this.swigCPtr, this, j);
    }
}
