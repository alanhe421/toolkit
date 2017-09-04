package com.tencent.imcore;

public class IFriendshipActionCallback {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public IFriendshipActionCallback() {
        this(internalJNI.new_IFriendshipActionCallback(), true);
        internalJNI.IFriendshipActionCallback_director_connect(this, this.swigCPtr, this.swigCMemOwn, true);
    }

    protected IFriendshipActionCallback(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(IFriendshipActionCallback iFriendshipActionCallback) {
        return iFriendshipActionCallback == null ? 0 : iFriendshipActionCallback.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_IFriendshipActionCallback(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void done(FriendProfileVec friendProfileVec) {
        if (getClass() == IFriendshipActionCallback.class) {
            internalJNI.IFriendshipActionCallback_done(this.swigCPtr, this, FriendProfileVec.getCPtr(friendProfileVec), friendProfileVec);
        } else {
            internalJNI.IFriendshipActionCallback_doneSwigExplicitIFriendshipActionCallback(this.swigCPtr, this, FriendProfileVec.getCPtr(friendProfileVec), friendProfileVec);
        }
    }

    public void fail(int i, String str) {
        if (getClass() == IFriendshipActionCallback.class) {
            internalJNI.IFriendshipActionCallback_fail(this.swigCPtr, this, i, str);
        } else {
            internalJNI.IFriendshipActionCallback_failSwigExplicitIFriendshipActionCallback(this.swigCPtr, this, i, str);
        }
    }

    protected void finalize() {
        delete();
    }

    public FriendProfileVec getVecFriends() {
        long IFriendshipActionCallback_vecFriends_get = internalJNI.IFriendshipActionCallback_vecFriends_get(this.swigCPtr, this);
        return IFriendshipActionCallback_vecFriends_get == 0 ? null : new FriendProfileVec(IFriendshipActionCallback_vecFriends_get, false);
    }

    public void setVecFriends(FriendProfileVec friendProfileVec) {
        internalJNI.IFriendshipActionCallback_vecFriends_set(this.swigCPtr, this, FriendProfileVec.getCPtr(friendProfileVec), friendProfileVec);
    }

    protected void swigDirectorDisconnect() {
        this.swigCMemOwn = false;
        delete();
    }

    public void swigReleaseOwnership() {
        this.swigCMemOwn = false;
        internalJNI.IFriendshipActionCallback_change_ownership(this, this.swigCPtr, false);
    }

    public void swigTakeOwnership() {
        this.swigCMemOwn = true;
        internalJNI.IFriendshipActionCallback_change_ownership(this, this.swigCPtr, true);
    }
}
