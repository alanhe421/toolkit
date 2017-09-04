package com.tencent.imcore;

public class FutureFriendMeta {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public FutureFriendMeta() {
        this(internalJNI.new_FutureFriendMeta(), true);
    }

    protected FutureFriendMeta(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(FutureFriendMeta futureFriendMeta) {
        return futureFriendMeta == null ? 0 : futureFriendMeta.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_FutureFriendMeta(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public long getDdwCurrentDecideTimestamp() {
        return internalJNI.FutureFriendMeta_ddwCurrentDecideTimestamp_get(this.swigCPtr, this);
    }

    public long getDdwCurrentPendencyTimestamp() {
        return internalJNI.FutureFriendMeta_ddwCurrentPendencyTimestamp_get(this.swigCPtr, this);
    }

    public long getDdwCurrentRecommendTimestamp() {
        return internalJNI.FutureFriendMeta_ddwCurrentRecommendTimestamp_get(this.swigCPtr, this);
    }

    public long getDdwDecideSequence() {
        return internalJNI.FutureFriendMeta_ddwDecideSequence_get(this.swigCPtr, this);
    }

    public long getDdwDecideUnreadCnt() {
        return internalJNI.FutureFriendMeta_ddwDecideUnreadCnt_get(this.swigCPtr, this);
    }

    public long getDdwPendencySequence() {
        return internalJNI.FutureFriendMeta_ddwPendencySequence_get(this.swigCPtr, this);
    }

    public long getDdwPendencyUnReadCnt() {
        return internalJNI.FutureFriendMeta_ddwPendencyUnReadCnt_get(this.swigCPtr, this);
    }

    public long getDdwRecommendSequence() {
        return internalJNI.FutureFriendMeta_ddwRecommendSequence_get(this.swigCPtr, this);
    }

    public long getDdwRecommendUnReadCnt() {
        return internalJNI.FutureFriendMeta_ddwRecommendUnReadCnt_get(this.swigCPtr, this);
    }

    public long getDdwReqNum() {
        return internalJNI.FutureFriendMeta_ddwReqNum_get(this.swigCPtr, this);
    }

    public long getDdwTimestamp() {
        return internalJNI.FutureFriendMeta_ddwTimestamp_get(this.swigCPtr, this);
    }

    public int getIDirection() {
        return internalJNI.FutureFriendMeta_iDirection_get(this.swigCPtr, this);
    }

    public void setDdwCurrentDecideTimestamp(long j) {
        internalJNI.FutureFriendMeta_ddwCurrentDecideTimestamp_set(this.swigCPtr, this, j);
    }

    public void setDdwCurrentPendencyTimestamp(long j) {
        internalJNI.FutureFriendMeta_ddwCurrentPendencyTimestamp_set(this.swigCPtr, this, j);
    }

    public void setDdwCurrentRecommendTimestamp(long j) {
        internalJNI.FutureFriendMeta_ddwCurrentRecommendTimestamp_set(this.swigCPtr, this, j);
    }

    public void setDdwDecideSequence(long j) {
        internalJNI.FutureFriendMeta_ddwDecideSequence_set(this.swigCPtr, this, j);
    }

    public void setDdwDecideUnreadCnt(long j) {
        internalJNI.FutureFriendMeta_ddwDecideUnreadCnt_set(this.swigCPtr, this, j);
    }

    public void setDdwPendencySequence(long j) {
        internalJNI.FutureFriendMeta_ddwPendencySequence_set(this.swigCPtr, this, j);
    }

    public void setDdwPendencyUnReadCnt(long j) {
        internalJNI.FutureFriendMeta_ddwPendencyUnReadCnt_set(this.swigCPtr, this, j);
    }

    public void setDdwRecommendSequence(long j) {
        internalJNI.FutureFriendMeta_ddwRecommendSequence_set(this.swigCPtr, this, j);
    }

    public void setDdwRecommendUnReadCnt(long j) {
        internalJNI.FutureFriendMeta_ddwRecommendUnReadCnt_set(this.swigCPtr, this, j);
    }

    public void setDdwReqNum(long j) {
        internalJNI.FutureFriendMeta_ddwReqNum_set(this.swigCPtr, this, j);
    }

    public void setDdwTimestamp(long j) {
        internalJNI.FutureFriendMeta_ddwTimestamp_set(this.swigCPtr, this, j);
    }

    public void setIDirection(int i) {
        internalJNI.FutureFriendMeta_iDirection_set(this.swigCPtr, this, i);
    }
}
