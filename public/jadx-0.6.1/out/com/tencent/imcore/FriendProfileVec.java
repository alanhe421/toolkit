package com.tencent.imcore;

public class FriendProfileVec {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public FriendProfileVec() {
        this(internalJNI.new_FriendProfileVec__SWIG_0(), true);
    }

    public FriendProfileVec(long j) {
        this(internalJNI.new_FriendProfileVec__SWIG_1(j), true);
    }

    protected FriendProfileVec(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(FriendProfileVec friendProfileVec) {
        return friendProfileVec == null ? 0 : friendProfileVec.swigCPtr;
    }

    public long capacity() {
        return internalJNI.FriendProfileVec_capacity(this.swigCPtr, this);
    }

    public void clear() {
        internalJNI.FriendProfileVec_clear(this.swigCPtr, this);
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_FriendProfileVec(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public boolean empty() {
        return internalJNI.FriendProfileVec_empty(this.swigCPtr, this);
    }

    protected void finalize() {
        delete();
    }

    public FriendProfile get(int i) {
        return new FriendProfile(internalJNI.FriendProfileVec_get(this.swigCPtr, this, i), false);
    }

    public void pushBack(FriendProfile friendProfile) {
        internalJNI.FriendProfileVec_pushBack(this.swigCPtr, this, FriendProfile.getCPtr(friendProfile), friendProfile);
    }

    public void reserve(long j) {
        internalJNI.FriendProfileVec_reserve(this.swigCPtr, this, j);
    }

    public void set(int i, FriendProfile friendProfile) {
        internalJNI.FriendProfileVec_set(this.swigCPtr, this, i, FriendProfile.getCPtr(friendProfile), friendProfile);
    }

    public long size() {
        return internalJNI.FriendProfileVec_size(this.swigCPtr, this);
    }
}
