package com.tencent.imcore;

public class MsgVec {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public MsgVec() {
        this(internalJNI.new_MsgVec__SWIG_0(), true);
    }

    public MsgVec(long j) {
        this(internalJNI.new_MsgVec__SWIG_1(j), true);
    }

    protected MsgVec(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(MsgVec msgVec) {
        return msgVec == null ? 0 : msgVec.swigCPtr;
    }

    public long capacity() {
        return internalJNI.MsgVec_capacity(this.swigCPtr, this);
    }

    public void clear() {
        internalJNI.MsgVec_clear(this.swigCPtr, this);
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_MsgVec(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public boolean empty() {
        return internalJNI.MsgVec_empty(this.swigCPtr, this);
    }

    protected void finalize() {
        delete();
    }

    public Msg get(int i) {
        return new Msg(internalJNI.MsgVec_get(this.swigCPtr, this, i), false);
    }

    public void pushBack(Msg msg) {
        internalJNI.MsgVec_pushBack(this.swigCPtr, this, Msg.getCPtr(msg), msg);
    }

    public void reserve(long j) {
        internalJNI.MsgVec_reserve(this.swigCPtr, this, j);
    }

    public void set(int i, Msg msg) {
        internalJNI.MsgVec_set(this.swigCPtr, this, i, Msg.getCPtr(msg), msg);
    }

    public long size() {
        return internalJNI.MsgVec_size(this.swigCPtr, this);
    }
}
