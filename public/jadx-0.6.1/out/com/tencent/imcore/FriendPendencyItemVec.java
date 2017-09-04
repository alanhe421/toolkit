package com.tencent.imcore;

public class FriendPendencyItemVec {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public FriendPendencyItemVec() {
        this(internalJNI.new_FriendPendencyItemVec__SWIG_0(), true);
    }

    public FriendPendencyItemVec(long j) {
        this(internalJNI.new_FriendPendencyItemVec__SWIG_1(j), true);
    }

    protected FriendPendencyItemVec(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(FriendPendencyItemVec friendPendencyItemVec) {
        return friendPendencyItemVec == null ? 0 : friendPendencyItemVec.swigCPtr;
    }

    public long capacity() {
        return internalJNI.FriendPendencyItemVec_capacity(this.swigCPtr, this);
    }

    public void clear() {
        internalJNI.FriendPendencyItemVec_clear(this.swigCPtr, this);
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_FriendPendencyItemVec(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public boolean empty() {
        return internalJNI.FriendPendencyItemVec_empty(this.swigCPtr, this);
    }

    protected void finalize() {
        delete();
    }

    public FriendPendencyItem get(int i) {
        return new FriendPendencyItem(internalJNI.FriendPendencyItemVec_get(this.swigCPtr, this, i), false);
    }

    public void pushBack(FriendPendencyItem friendPendencyItem) {
        internalJNI.FriendPendencyItemVec_pushBack(this.swigCPtr, this, FriendPendencyItem.getCPtr(friendPendencyItem), friendPendencyItem);
    }

    public void reserve(long j) {
        internalJNI.FriendPendencyItemVec_reserve(this.swigCPtr, this, j);
    }

    public void set(int i, FriendPendencyItem friendPendencyItem) {
        internalJNI.FriendPendencyItemVec_set(this.swigCPtr, this, i, FriendPendencyItem.getCPtr(friendPendencyItem), friendPendencyItem);
    }

    public long size() {
        return internalJNI.FriendPendencyItemVec_size(this.swigCPtr, this);
    }
}
