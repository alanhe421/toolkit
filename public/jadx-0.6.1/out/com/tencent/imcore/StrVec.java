package com.tencent.imcore;

public class StrVec {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public StrVec() {
        this(internalJNI.new_StrVec__SWIG_0(), true);
    }

    public StrVec(long j) {
        this(internalJNI.new_StrVec__SWIG_1(j), true);
    }

    protected StrVec(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(StrVec strVec) {
        return strVec == null ? 0 : strVec.swigCPtr;
    }

    public long capacity() {
        return internalJNI.StrVec_capacity(this.swigCPtr, this);
    }

    public void clear() {
        internalJNI.StrVec_clear(this.swigCPtr, this);
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_StrVec(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public boolean empty() {
        return internalJNI.StrVec_empty(this.swigCPtr, this);
    }

    protected void finalize() {
        delete();
    }

    public String get(int i) {
        return internalJNI.StrVec_get(this.swigCPtr, this, i);
    }

    public void pushBack(String str) {
        internalJNI.StrVec_pushBack(this.swigCPtr, this, str);
    }

    public void reserve(long j) {
        internalJNI.StrVec_reserve(this.swigCPtr, this, j);
    }

    public void set(int i, String str) {
        internalJNI.StrVec_set(this.swigCPtr, this, i, str);
    }

    public long size() {
        return internalJNI.StrVec_size(this.swigCPtr, this);
    }
}
