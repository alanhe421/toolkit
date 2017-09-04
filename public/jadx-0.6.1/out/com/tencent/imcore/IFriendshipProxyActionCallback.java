package com.tencent.imcore;

public class IFriendshipProxyActionCallback {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    protected IFriendshipProxyActionCallback(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(IFriendshipProxyActionCallback iFriendshipProxyActionCallback) {
        return iFriendshipProxyActionCallback == null ? 0 : iFriendshipProxyActionCallback.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_IFriendshipProxyActionCallback(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void done(FriendProfileVec friendProfileVec, long j, long j2) {
        internalJNI.IFriendshipProxyActionCallback_done(this.swigCPtr, this, FriendProfileVec.getCPtr(friendProfileVec), friendProfileVec, j, j2);
    }

    public void fail(int i, String str) {
        internalJNI.IFriendshipProxyActionCallback_fail(this.swigCPtr, this, i, str);
    }

    protected void finalize() {
        delete();
    }

    public boolean getAllFlag() {
        return internalJNI.IFriendshipProxyActionCallback_getAllFlag(this.swigCPtr, this);
    }

    public FriendProfileVec getVecFriends() {
        long IFriendshipProxyActionCallback_vecFriends_get = internalJNI.IFriendshipProxyActionCallback_vecFriends_get(this.swigCPtr, this);
        return IFriendshipProxyActionCallback_vecFriends_get == 0 ? null : new FriendProfileVec(IFriendshipProxyActionCallback_vecFriends_get, false);
    }

    public void setAllFlag(boolean z) {
        internalJNI.IFriendshipProxyActionCallback_setAllFlag(this.swigCPtr, this, z);
    }

    public void setVecFriends(FriendProfileVec friendProfileVec) {
        internalJNI.IFriendshipProxyActionCallback_vecFriends_set(this.swigCPtr, this, FriendProfileVec.getCPtr(friendProfileVec), friendProfileVec);
    }
}
