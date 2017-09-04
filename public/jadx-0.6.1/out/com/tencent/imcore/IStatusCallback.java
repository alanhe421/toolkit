package com.tencent.imcore;

public class IStatusCallback {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public IStatusCallback() {
        this(internalJNI.new_IStatusCallback(), true);
        internalJNI.IStatusCallback_director_connect(this, this.swigCPtr, this.swigCMemOwn, true);
    }

    protected IStatusCallback(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(IStatusCallback iStatusCallback) {
        return iStatusCallback == null ? 0 : iStatusCallback.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_IStatusCallback(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void done(UserStatusVec userStatusVec) {
        if (getClass() == IStatusCallback.class) {
            internalJNI.IStatusCallback_done(this.swigCPtr, this, UserStatusVec.getCPtr(userStatusVec), userStatusVec);
        } else {
            internalJNI.IStatusCallback_doneSwigExplicitIStatusCallback(this.swigCPtr, this, UserStatusVec.getCPtr(userStatusVec), userStatusVec);
        }
    }

    public void fail(int i, String str) {
        if (getClass() == IStatusCallback.class) {
            internalJNI.IStatusCallback_fail(this.swigCPtr, this, i, str);
        } else {
            internalJNI.IStatusCallback_failSwigExplicitIStatusCallback(this.swigCPtr, this, i, str);
        }
    }

    protected void finalize() {
        delete();
    }

    public UserStatusVec getVecUserStatus() {
        long IStatusCallback_vecUserStatus_get = internalJNI.IStatusCallback_vecUserStatus_get(this.swigCPtr, this);
        return IStatusCallback_vecUserStatus_get == 0 ? null : new UserStatusVec(IStatusCallback_vecUserStatus_get, false);
    }

    public void setVecUserStatus(UserStatusVec userStatusVec) {
        internalJNI.IStatusCallback_vecUserStatus_set(this.swigCPtr, this, UserStatusVec.getCPtr(userStatusVec), userStatusVec);
    }

    protected void swigDirectorDisconnect() {
        this.swigCMemOwn = false;
        delete();
    }

    public void swigReleaseOwnership() {
        this.swigCMemOwn = false;
        internalJNI.IStatusCallback_change_ownership(this, this.swigCPtr, false);
    }

    public void swigTakeOwnership() {
        this.swigCMemOwn = true;
        internalJNI.IStatusCallback_change_ownership(this, this.swigCPtr, true);
    }
}
