package com.tencent.imcore;

public class IFriendshipProxyListener {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public IFriendshipProxyListener() {
        this(internalJNI.new_IFriendshipProxyListener(), true);
        internalJNI.IFriendshipProxyListener_director_connect(this, this.swigCPtr, this.swigCMemOwn, true);
    }

    protected IFriendshipProxyListener(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(IFriendshipProxyListener iFriendshipProxyListener) {
        return iFriendshipProxyListener == null ? 0 : iFriendshipProxyListener.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_IFriendshipProxyListener(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public void onAddFriendNotify(FriendProfileVec friendProfileVec) {
        internalJNI.IFriendshipProxyListener_onAddFriendNotify(this.swigCPtr, this, FriendProfileVec.getCPtr(friendProfileVec), friendProfileVec);
    }

    public void onAddFriendReq(AddFriendReqVec addFriendReqVec) {
        internalJNI.IFriendshipProxyListener_onAddFriendReq(this.swigCPtr, this, AddFriendReqVec.getCPtr(addFriendReqVec), addFriendReqVec);
    }

    public void onDeleteFriendNotify(StrVec strVec) {
        internalJNI.IFriendshipProxyListener_onDeleteFriendNotify(this.swigCPtr, this, StrVec.getCPtr(strVec), strVec);
    }

    public void onFriendProfileUpdate(FriendProfileVec friendProfileVec) {
        internalJNI.IFriendshipProxyListener_onFriendProfileUpdate(this.swigCPtr, this, FriendProfileVec.getCPtr(friendProfileVec), friendProfileVec);
    }

    public void onProxyStatusChange(FriendProxyStatus friendProxyStatus) {
        internalJNI.IFriendshipProxyListener_onProxyStatusChange(this.swigCPtr, this, friendProxyStatus.swigValue());
    }

    protected void swigDirectorDisconnect() {
        this.swigCMemOwn = false;
        delete();
    }

    public void swigReleaseOwnership() {
        this.swigCMemOwn = false;
        internalJNI.IFriendshipProxyListener_change_ownership(this, this.swigCPtr, false);
    }

    public void swigTakeOwnership() {
        this.swigCMemOwn = true;
        internalJNI.IFriendshipProxyListener_change_ownership(this, this.swigCPtr, true);
    }
}
