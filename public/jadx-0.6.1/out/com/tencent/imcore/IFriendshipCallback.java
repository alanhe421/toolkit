package com.tencent.imcore;

public class IFriendshipCallback {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public IFriendshipCallback() {
        this(internalJNI.new_IFriendshipCallback(), true);
        internalJNI.IFriendshipCallback_director_connect(this, this.swigCPtr, this.swigCMemOwn, true);
    }

    protected IFriendshipCallback(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(IFriendshipCallback iFriendshipCallback) {
        return iFriendshipCallback == null ? 0 : iFriendshipCallback.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_IFriendshipCallback(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void done() {
        if (getClass() == IFriendshipCallback.class) {
            internalJNI.IFriendshipCallback_done(this.swigCPtr, this);
        } else {
            internalJNI.IFriendshipCallback_doneSwigExplicitIFriendshipCallback(this.swigCPtr, this);
        }
    }

    public void fail(int i, String str) {
        if (getClass() == IFriendshipCallback.class) {
            internalJNI.IFriendshipCallback_fail(this.swigCPtr, this, i, str);
        } else {
            internalJNI.IFriendshipCallback_failSwigExplicitIFriendshipCallback(this.swigCPtr, this, i, str);
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
        internalJNI.IFriendshipCallback_change_ownership(this, this.swigCPtr, false);
    }

    public void swigTakeOwnership() {
        this.swigCMemOwn = true;
        internalJNI.IFriendshipCallback_change_ownership(this, this.swigCPtr, true);
    }
}
