package com.tencent.imcore;

import com.tencent.imcore.IBatchOprCallback.BatchOprDetailInfo.ErrInfo;

public class ErrInfoVec {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public ErrInfoVec() {
        this(internalJNI.new_ErrInfoVec__SWIG_0(), true);
    }

    public ErrInfoVec(long j) {
        this(internalJNI.new_ErrInfoVec__SWIG_1(j), true);
    }

    protected ErrInfoVec(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(ErrInfoVec errInfoVec) {
        return errInfoVec == null ? 0 : errInfoVec.swigCPtr;
    }

    public long capacity() {
        return internalJNI.ErrInfoVec_capacity(this.swigCPtr, this);
    }

    public void clear() {
        internalJNI.ErrInfoVec_clear(this.swigCPtr, this);
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_ErrInfoVec(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public boolean empty() {
        return internalJNI.ErrInfoVec_empty(this.swigCPtr, this);
    }

    protected void finalize() {
        delete();
    }

    public ErrInfo get(int i) {
        return new ErrInfo(internalJNI.ErrInfoVec_get(this.swigCPtr, this, i), false);
    }

    public void pushBack(ErrInfo errInfo) {
        internalJNI.ErrInfoVec_pushBack(this.swigCPtr, this, ErrInfo.getCPtr(errInfo), errInfo);
    }

    public void reserve(long j) {
        internalJNI.ErrInfoVec_reserve(this.swigCPtr, this, j);
    }

    public void set(int i, ErrInfo errInfo) {
        internalJNI.ErrInfoVec_set(this.swigCPtr, this, i, ErrInfo.getCPtr(errInfo), errInfo);
    }

    public long size() {
        return internalJNI.ErrInfoVec_size(this.swigCPtr, this);
    }
}
