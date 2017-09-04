package com.tencent.imcore;

public class ElemVec {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public ElemVec() {
        this(internalJNI.new_ElemVec__SWIG_0(), true);
    }

    public ElemVec(long j) {
        this(internalJNI.new_ElemVec__SWIG_1(j), true);
    }

    protected ElemVec(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(ElemVec elemVec) {
        return elemVec == null ? 0 : elemVec.swigCPtr;
    }

    public long capacity() {
        return internalJNI.ElemVec_capacity(this.swigCPtr, this);
    }

    public void clear() {
        internalJNI.ElemVec_clear(this.swigCPtr, this);
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_ElemVec(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public boolean empty() {
        return internalJNI.ElemVec_empty(this.swigCPtr, this);
    }

    protected void finalize() {
        delete();
    }

    public Elem get(int i) {
        return new Elem(internalJNI.ElemVec_get(this.swigCPtr, this, i), false);
    }

    public void pushBack(Elem elem) {
        internalJNI.ElemVec_pushBack(this.swigCPtr, this, Elem.getCPtr(elem), elem);
    }

    public void reserve(long j) {
        internalJNI.ElemVec_reserve(this.swigCPtr, this, j);
    }

    public void set(int i, Elem elem) {
        internalJNI.ElemVec_set(this.swigCPtr, this, i, Elem.getCPtr(elem), elem);
    }

    public long size() {
        return internalJNI.ElemVec_size(this.swigCPtr, this);
    }
}
