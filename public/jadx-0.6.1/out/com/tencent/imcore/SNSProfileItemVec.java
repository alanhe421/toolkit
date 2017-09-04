package com.tencent.imcore;

public class SNSProfileItemVec {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public SNSProfileItemVec() {
        this(internalJNI.new_SNSProfileItemVec__SWIG_0(), true);
    }

    public SNSProfileItemVec(long j) {
        this(internalJNI.new_SNSProfileItemVec__SWIG_1(j), true);
    }

    protected SNSProfileItemVec(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(SNSProfileItemVec sNSProfileItemVec) {
        return sNSProfileItemVec == null ? 0 : sNSProfileItemVec.swigCPtr;
    }

    public long capacity() {
        return internalJNI.SNSProfileItemVec_capacity(this.swigCPtr, this);
    }

    public void clear() {
        internalJNI.SNSProfileItemVec_clear(this.swigCPtr, this);
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_SNSProfileItemVec(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public boolean empty() {
        return internalJNI.SNSProfileItemVec_empty(this.swigCPtr, this);
    }

    protected void finalize() {
        delete();
    }

    public SNSProfileItem get(int i) {
        return new SNSProfileItem(internalJNI.SNSProfileItemVec_get(this.swigCPtr, this, i), false);
    }

    public void pushBack(SNSProfileItem sNSProfileItem) {
        internalJNI.SNSProfileItemVec_pushBack(this.swigCPtr, this, SNSProfileItem.getCPtr(sNSProfileItem), sNSProfileItem);
    }

    public void reserve(long j) {
        internalJNI.SNSProfileItemVec_reserve(this.swigCPtr, this, j);
    }

    public void set(int i, SNSProfileItem sNSProfileItem) {
        internalJNI.SNSProfileItemVec_set(this.swigCPtr, this, i, SNSProfileItem.getCPtr(sNSProfileItem), sNSProfileItem);
    }

    public long size() {
        return internalJNI.SNSProfileItemVec_size(this.swigCPtr, this);
    }
}
