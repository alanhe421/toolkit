package com.tencent.imcore;

public class FriendGroupVec {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public FriendGroupVec() {
        this(internalJNI.new_FriendGroupVec__SWIG_0(), true);
    }

    public FriendGroupVec(long j) {
        this(internalJNI.new_FriendGroupVec__SWIG_1(j), true);
    }

    protected FriendGroupVec(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(FriendGroupVec friendGroupVec) {
        return friendGroupVec == null ? 0 : friendGroupVec.swigCPtr;
    }

    public long capacity() {
        return internalJNI.FriendGroupVec_capacity(this.swigCPtr, this);
    }

    public void clear() {
        internalJNI.FriendGroupVec_clear(this.swigCPtr, this);
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_FriendGroupVec(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public boolean empty() {
        return internalJNI.FriendGroupVec_empty(this.swigCPtr, this);
    }

    protected void finalize() {
        delete();
    }

    public FriendGroup get(int i) {
        return new FriendGroup(internalJNI.FriendGroupVec_get(this.swigCPtr, this, i), false);
    }

    public void pushBack(FriendGroup friendGroup) {
        internalJNI.FriendGroupVec_pushBack(this.swigCPtr, this, FriendGroup.getCPtr(friendGroup), friendGroup);
    }

    public void reserve(long j) {
        internalJNI.FriendGroupVec_reserve(this.swigCPtr, this, j);
    }

    public void set(int i, FriendGroup friendGroup) {
        internalJNI.FriendGroupVec_set(this.swigCPtr, this, i, FriendGroup.getCPtr(friendGroup), friendGroup);
    }

    public long size() {
        return internalJNI.FriendGroupVec_size(this.swigCPtr, this);
    }
}
