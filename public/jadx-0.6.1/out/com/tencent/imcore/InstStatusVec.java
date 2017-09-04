package com.tencent.imcore;

public class InstStatusVec {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public InstStatusVec() {
        this(internalJNI.new_InstStatusVec__SWIG_0(), true);
    }

    public InstStatusVec(long j) {
        this(internalJNI.new_InstStatusVec__SWIG_1(j), true);
    }

    protected InstStatusVec(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(InstStatusVec instStatusVec) {
        return instStatusVec == null ? 0 : instStatusVec.swigCPtr;
    }

    public long capacity() {
        return internalJNI.InstStatusVec_capacity(this.swigCPtr, this);
    }

    public void clear() {
        internalJNI.InstStatusVec_clear(this.swigCPtr, this);
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_InstStatusVec(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public boolean empty() {
        return internalJNI.InstStatusVec_empty(this.swigCPtr, this);
    }

    protected void finalize() {
        delete();
    }

    public InstStatus get(int i) {
        return new InstStatus(internalJNI.InstStatusVec_get(this.swigCPtr, this, i), false);
    }

    public void pushBack(InstStatus instStatus) {
        internalJNI.InstStatusVec_pushBack(this.swigCPtr, this, InstStatus.getCPtr(instStatus), instStatus);
    }

    public void reserve(long j) {
        internalJNI.InstStatusVec_reserve(this.swigCPtr, this, j);
    }

    public void set(int i, InstStatus instStatus) {
        internalJNI.InstStatusVec_set(this.swigCPtr, this, i, InstStatus.getCPtr(instStatus), instStatus);
    }

    public long size() {
        return internalJNI.InstStatusVec_size(this.swigCPtr, this);
    }
}
