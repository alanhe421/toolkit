package com.tencent.imcore;

public class IFriendGroupCallback {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public IFriendGroupCallback() {
        this(internalJNI.new_IFriendGroupCallback(), true);
        internalJNI.IFriendGroupCallback_director_connect(this, this.swigCPtr, this.swigCMemOwn, true);
    }

    protected IFriendGroupCallback(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(IFriendGroupCallback iFriendGroupCallback) {
        return iFriendGroupCallback == null ? 0 : iFriendGroupCallback.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_IFriendGroupCallback(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void done(FriendGroupVec friendGroupVec) {
        if (getClass() == IFriendGroupCallback.class) {
            internalJNI.IFriendGroupCallback_done(this.swigCPtr, this, FriendGroupVec.getCPtr(friendGroupVec), friendGroupVec);
        } else {
            internalJNI.IFriendGroupCallback_doneSwigExplicitIFriendGroupCallback(this.swigCPtr, this, FriendGroupVec.getCPtr(friendGroupVec), friendGroupVec);
        }
    }

    public void fail(int i, String str) {
        if (getClass() == IFriendGroupCallback.class) {
            internalJNI.IFriendGroupCallback_fail(this.swigCPtr, this, i, str);
        } else {
            internalJNI.IFriendGroupCallback_failSwigExplicitIFriendGroupCallback(this.swigCPtr, this, i, str);
        }
    }

    protected void finalize() {
        delete();
    }

    protected void swigDirectorDisconnect() {
        this.swigCMemOwn = false;
        delete();
    }

    public void swigReleaseOwnership() {
        this.swigCMemOwn = false;
        internalJNI.IFriendGroupCallback_change_ownership(this, this.swigCPtr, false);
    }

    public void swigTakeOwnership() {
        this.swigCMemOwn = true;
        internalJNI.IFriendGroupCallback_change_ownership(this, this.swigCPtr, true);
    }
}
