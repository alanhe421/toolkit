package com.tencent.imcore;

public class IFriendshipActionCallbackV2 {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public IFriendshipActionCallbackV2() {
        this(internalJNI.new_IFriendshipActionCallbackV2(), true);
        internalJNI.IFriendshipActionCallbackV2_director_connect(this, this.swigCPtr, this.swigCMemOwn, true);
    }

    protected IFriendshipActionCallbackV2(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(IFriendshipActionCallbackV2 iFriendshipActionCallbackV2) {
        return iFriendshipActionCallbackV2 == null ? 0 : iFriendshipActionCallbackV2.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_IFriendshipActionCallbackV2(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void done(long j, FriendProfileVec friendProfileVec) {
        if (getClass() == IFriendshipActionCallbackV2.class) {
            internalJNI.IFriendshipActionCallbackV2_done(this.swigCPtr, this, j, FriendProfileVec.getCPtr(friendProfileVec), friendProfileVec);
            return;
        }
        internalJNI.IFriendshipActionCallbackV2_doneSwigExplicitIFriendshipActionCallbackV2(this.swigCPtr, this, j, FriendProfileVec.getCPtr(friendProfileVec), friendProfileVec);
    }

    public void fail(int i, String str) {
        if (getClass() == IFriendshipActionCallbackV2.class) {
            internalJNI.IFriendshipActionCallbackV2_fail(this.swigCPtr, this, i, str);
        } else {
            internalJNI.IFriendshipActionCallbackV2_failSwigExplicitIFriendshipActionCallbackV2(this.swigCPtr, this, i, str);
        }
    }

    protected void finalize() {
        delete();
    }

    public FriendProfileVec getVecFriends() {
        long IFriendshipActionCallbackV2_vecFriends_get = internalJNI.IFriendshipActionCallbackV2_vecFriends_get(this.swigCPtr, this);
        return IFriendshipActionCallbackV2_vecFriends_get == 0 ? null : new FriendProfileVec(IFriendshipActionCallbackV2_vecFriends_get, false);
    }

    public void setVecFriends(FriendProfileVec friendProfileVec) {
        internalJNI.IFriendshipActionCallbackV2_vecFriends_set(this.swigCPtr, this, FriendProfileVec.getCPtr(friendProfileVec), friendProfileVec);
    }

    protected void swigDirectorDisconnect() {
        this.swigCMemOwn = false;
        delete();
    }

    public void swigReleaseOwnership() {
        this.swigCMemOwn = false;
        internalJNI.IFriendshipActionCallbackV2_change_ownership(this, this.swigCPtr, false);
    }

    public void swigTakeOwnership() {
        this.swigCMemOwn = true;
        internalJNI.IFriendshipActionCallbackV2_change_ownership(this, this.swigCPtr, true);
    }
}
