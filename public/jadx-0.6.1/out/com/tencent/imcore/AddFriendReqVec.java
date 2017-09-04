package com.tencent.imcore;

public class AddFriendReqVec {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public AddFriendReqVec() {
        this(internalJNI.new_AddFriendReqVec__SWIG_0(), true);
    }

    public AddFriendReqVec(long j) {
        this(internalJNI.new_AddFriendReqVec__SWIG_1(j), true);
    }

    protected AddFriendReqVec(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(AddFriendReqVec addFriendReqVec) {
        return addFriendReqVec == null ? 0 : addFriendReqVec.swigCPtr;
    }

    public long capacity() {
        return internalJNI.AddFriendReqVec_capacity(this.swigCPtr, this);
    }

    public void clear() {
        internalJNI.AddFriendReqVec_clear(this.swigCPtr, this);
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_AddFriendReqVec(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public boolean empty() {
        return internalJNI.AddFriendReqVec_empty(this.swigCPtr, this);
    }

    protected void finalize() {
        delete();
    }

    public AddFriendReq get(int i) {
        return new AddFriendReq(internalJNI.AddFriendReqVec_get(this.swigCPtr, this, i), false);
    }

    public void pushBack(AddFriendReq addFriendReq) {
        internalJNI.AddFriendReqVec_pushBack(this.swigCPtr, this, AddFriendReq.getCPtr(addFriendReq), addFriendReq);
    }

    public void reserve(long j) {
        internalJNI.AddFriendReqVec_reserve(this.swigCPtr, this, j);
    }

    public void set(int i, AddFriendReq addFriendReq) {
        internalJNI.AddFriendReqVec_set(this.swigCPtr, this, i, AddFriendReq.getCPtr(addFriendReq), addFriendReq);
    }

    public long size() {
        return internalJNI.AddFriendReqVec_size(this.swigCPtr, this);
    }
}
