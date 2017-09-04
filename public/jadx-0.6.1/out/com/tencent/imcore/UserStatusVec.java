package com.tencent.imcore;

public class UserStatusVec {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public UserStatusVec() {
        this(internalJNI.new_UserStatusVec__SWIG_0(), true);
    }

    public UserStatusVec(long j) {
        this(internalJNI.new_UserStatusVec__SWIG_1(j), true);
    }

    protected UserStatusVec(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(UserStatusVec userStatusVec) {
        return userStatusVec == null ? 0 : userStatusVec.swigCPtr;
    }

    public long capacity() {
        return internalJNI.UserStatusVec_capacity(this.swigCPtr, this);
    }

    public void clear() {
        internalJNI.UserStatusVec_clear(this.swigCPtr, this);
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_UserStatusVec(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public boolean empty() {
        return internalJNI.UserStatusVec_empty(this.swigCPtr, this);
    }

    protected void finalize() {
        delete();
    }

    public UserStatus get(int i) {
        return new UserStatus(internalJNI.UserStatusVec_get(this.swigCPtr, this, i), false);
    }

    public void pushBack(UserStatus userStatus) {
        internalJNI.UserStatusVec_pushBack(this.swigCPtr, this, UserStatus.getCPtr(userStatus), userStatus);
    }

    public void reserve(long j) {
        internalJNI.UserStatusVec_reserve(this.swigCPtr, this, j);
    }

    public void set(int i, UserStatus userStatus) {
        internalJNI.UserStatusVec_set(this.swigCPtr, this, i, UserStatus.getCPtr(userStatus), userStatus);
    }

    public long size() {
        return internalJNI.UserStatusVec_size(this.swigCPtr, this);
    }
}
