package com.tencent.imcore;

public class PairVectorSession {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public PairVectorSession() {
        this(internalJNI.new_PairVectorSession__SWIG_0(), true);
    }

    public PairVectorSession(long j) {
        this(internalJNI.new_PairVectorSession__SWIG_1(j), true);
    }

    protected PairVectorSession(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(PairVectorSession pairVectorSession) {
        return pairVectorSession == null ? 0 : pairVectorSession.swigCPtr;
    }

    public long capacity() {
        return internalJNI.PairVectorSession_capacity(this.swigCPtr, this);
    }

    public void clear() {
        internalJNI.PairVectorSession_clear(this.swigCPtr, this);
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_PairVectorSession(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public boolean empty() {
        return internalJNI.PairVectorSession_empty(this.swigCPtr, this);
    }

    protected void finalize() {
        delete();
    }

    public PairSession get(int i) {
        return new PairSession(internalJNI.PairVectorSession_get(this.swigCPtr, this, i), false);
    }

    public void pushBack(PairSession pairSession) {
        internalJNI.PairVectorSession_pushBack(this.swigCPtr, this, PairSession.getCPtr(pairSession), pairSession);
    }

    public void reserve(long j) {
        internalJNI.PairVectorSession_reserve(this.swigCPtr, this, j);
    }

    public void set(int i, PairSession pairSession) {
        internalJNI.PairVectorSession_set(this.swigCPtr, this, i, PairSession.getCPtr(pairSession), pairSession);
    }

    public long size() {
        return internalJNI.PairVectorSession_size(this.swigCPtr, this);
    }
}
