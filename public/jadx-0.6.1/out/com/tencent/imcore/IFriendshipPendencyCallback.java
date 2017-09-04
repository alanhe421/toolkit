package com.tencent.imcore;

public class IFriendshipPendencyCallback {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public IFriendshipPendencyCallback() {
        this(internalJNI.new_IFriendshipPendencyCallback(), true);
        internalJNI.IFriendshipPendencyCallback_director_connect(this, this.swigCPtr, this.swigCMemOwn, true);
    }

    protected IFriendshipPendencyCallback(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(IFriendshipPendencyCallback iFriendshipPendencyCallback) {
        return iFriendshipPendencyCallback == null ? 0 : iFriendshipPendencyCallback.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_IFriendshipPendencyCallback(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void done(FriendPendencyMeta friendPendencyMeta, FriendPendencyItemVec friendPendencyItemVec) {
        if (getClass() == IFriendshipPendencyCallback.class) {
            internalJNI.IFriendshipPendencyCallback_done(this.swigCPtr, this, FriendPendencyMeta.getCPtr(friendPendencyMeta), friendPendencyMeta, FriendPendencyItemVec.getCPtr(friendPendencyItemVec), friendPendencyItemVec);
            return;
        }
        internalJNI.IFriendshipPendencyCallback_doneSwigExplicitIFriendshipPendencyCallback(this.swigCPtr, this, FriendPendencyMeta.getCPtr(friendPendencyMeta), friendPendencyMeta, FriendPendencyItemVec.getCPtr(friendPendencyItemVec), friendPendencyItemVec);
    }

    public void fail(int i, String str) {
        if (getClass() == IFriendshipPendencyCallback.class) {
            internalJNI.IFriendshipPendencyCallback_fail(this.swigCPtr, this, i, str);
        } else {
            internalJNI.IFriendshipPendencyCallback_failSwigExplicitIFriendshipPendencyCallback(this.swigCPtr, this, i, str);
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
        internalJNI.IFriendshipPendencyCallback_change_ownership(this, this.swigCPtr, false);
    }

    public void swigTakeOwnership() {
        this.swigCMemOwn = true;
        internalJNI.IFriendshipPendencyCallback_change_ownership(this, this.swigCPtr, true);
    }
}
