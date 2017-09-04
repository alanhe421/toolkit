package com.tencent.imcore;

public class IFriendshipGetFutureCallback {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public IFriendshipGetFutureCallback() {
        this(internalJNI.new_IFriendshipGetFutureCallback(), true);
        internalJNI.IFriendshipGetFutureCallback_director_connect(this, this.swigCPtr, this.swigCMemOwn, true);
    }

    protected IFriendshipGetFutureCallback(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(IFriendshipGetFutureCallback iFriendshipGetFutureCallback) {
        return iFriendshipGetFutureCallback == null ? 0 : iFriendshipGetFutureCallback.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_IFriendshipGetFutureCallback(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void done(FutureFriendMeta futureFriendMeta, FriendFutureItemVec friendFutureItemVec) {
        if (getClass() == IFriendshipGetFutureCallback.class) {
            internalJNI.IFriendshipGetFutureCallback_done(this.swigCPtr, this, FutureFriendMeta.getCPtr(futureFriendMeta), futureFriendMeta, FriendFutureItemVec.getCPtr(friendFutureItemVec), friendFutureItemVec);
            return;
        }
        internalJNI.IFriendshipGetFutureCallback_doneSwigExplicitIFriendshipGetFutureCallback(this.swigCPtr, this, FutureFriendMeta.getCPtr(futureFriendMeta), futureFriendMeta, FriendFutureItemVec.getCPtr(friendFutureItemVec), friendFutureItemVec);
    }

    public void fail(int i, String str) {
        if (getClass() == IFriendshipGetFutureCallback.class) {
            internalJNI.IFriendshipGetFutureCallback_fail(this.swigCPtr, this, i, str);
        } else {
            internalJNI.IFriendshipGetFutureCallback_failSwigExplicitIFriendshipGetFutureCallback(this.swigCPtr, this, i, str);
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
        internalJNI.IFriendshipGetFutureCallback_change_ownership(this, this.swigCPtr, false);
    }

    public void swigTakeOwnership() {
        this.swigCMemOwn = true;
        internalJNI.IFriendshipGetFutureCallback_change_ownership(this, this.swigCPtr, true);
    }
}
