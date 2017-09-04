package com.tencent.imcore;

public class IFriendshipGetFriendV2Callback {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public IFriendshipGetFriendV2Callback() {
        this(internalJNI.new_IFriendshipGetFriendV2Callback(), true);
        internalJNI.IFriendshipGetFriendV2Callback_director_connect(this, this.swigCPtr, this.swigCMemOwn, true);
    }

    protected IFriendshipGetFriendV2Callback(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(IFriendshipGetFriendV2Callback iFriendshipGetFriendV2Callback) {
        return iFriendshipGetFriendV2Callback == null ? 0 : iFriendshipGetFriendV2Callback.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_IFriendshipGetFriendV2Callback(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void done(FriendMetaInfo friendMetaInfo, FriendProfileVec friendProfileVec) {
        if (getClass() == IFriendshipGetFriendV2Callback.class) {
            internalJNI.IFriendshipGetFriendV2Callback_done(this.swigCPtr, this, FriendMetaInfo.getCPtr(friendMetaInfo), friendMetaInfo, FriendProfileVec.getCPtr(friendProfileVec), friendProfileVec);
            return;
        }
        internalJNI.IFriendshipGetFriendV2Callback_doneSwigExplicitIFriendshipGetFriendV2Callback(this.swigCPtr, this, FriendMetaInfo.getCPtr(friendMetaInfo), friendMetaInfo, FriendProfileVec.getCPtr(friendProfileVec), friendProfileVec);
    }

    public void fail(int i, String str) {
        if (getClass() == IFriendshipGetFriendV2Callback.class) {
            internalJNI.IFriendshipGetFriendV2Callback_fail(this.swigCPtr, this, i, str);
        } else {
            internalJNI.IFriendshipGetFriendV2Callback_failSwigExplicitIFriendshipGetFriendV2Callback(this.swigCPtr, this, i, str);
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
        internalJNI.IFriendshipGetFriendV2Callback_change_ownership(this, this.swigCPtr, false);
    }

    public void swigTakeOwnership() {
        this.swigCMemOwn = true;
        internalJNI.IFriendshipGetFriendV2Callback_change_ownership(this, this.swigCPtr, true);
    }
}
