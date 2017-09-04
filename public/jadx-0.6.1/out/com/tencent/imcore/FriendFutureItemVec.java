package com.tencent.imcore;

public class FriendFutureItemVec {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public FriendFutureItemVec() {
        this(internalJNI.new_FriendFutureItemVec__SWIG_0(), true);
    }

    public FriendFutureItemVec(long j) {
        this(internalJNI.new_FriendFutureItemVec__SWIG_1(j), true);
    }

    protected FriendFutureItemVec(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(FriendFutureItemVec friendFutureItemVec) {
        return friendFutureItemVec == null ? 0 : friendFutureItemVec.swigCPtr;
    }

    public long capacity() {
        return internalJNI.FriendFutureItemVec_capacity(this.swigCPtr, this);
    }

    public void clear() {
        internalJNI.FriendFutureItemVec_clear(this.swigCPtr, this);
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_FriendFutureItemVec(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public boolean empty() {
        return internalJNI.FriendFutureItemVec_empty(this.swigCPtr, this);
    }

    protected void finalize() {
        delete();
    }

    public FriendFutureItem get(int i) {
        return new FriendFutureItem(internalJNI.FriendFutureItemVec_get(this.swigCPtr, this, i), false);
    }

    public void pushBack(FriendFutureItem friendFutureItem) {
        internalJNI.FriendFutureItemVec_pushBack(this.swigCPtr, this, FriendFutureItem.getCPtr(friendFutureItem), friendFutureItem);
    }

    public void reserve(long j) {
        internalJNI.FriendFutureItemVec_reserve(this.swigCPtr, this, j);
    }

    public void set(int i, FriendFutureItem friendFutureItem) {
        internalJNI.FriendFutureItemVec_set(this.swigCPtr, this, i, FriendFutureItem.getCPtr(friendFutureItem), friendFutureItem);
    }

    public long size() {
        return internalJNI.FriendFutureItemVec_size(this.swigCPtr, this);
    }
}
