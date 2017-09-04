package com.tencent.imcore;

public class IGroupInfoListCallback {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public IGroupInfoListCallback() {
        this(internalJNI.new_IGroupInfoListCallback(), true);
        internalJNI.IGroupInfoListCallback_director_connect(this, this.swigCPtr, this.swigCMemOwn, true);
    }

    protected IGroupInfoListCallback(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(IGroupInfoListCallback iGroupInfoListCallback) {
        return iGroupInfoListCallback == null ? 0 : iGroupInfoListCallback.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_IGroupInfoListCallback(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void done(GroupDetailInfoVec groupDetailInfoVec) {
        if (getClass() == IGroupInfoListCallback.class) {
            internalJNI.IGroupInfoListCallback_done(this.swigCPtr, this, GroupDetailInfoVec.getCPtr(groupDetailInfoVec), groupDetailInfoVec);
        } else {
            internalJNI.IGroupInfoListCallback_doneSwigExplicitIGroupInfoListCallback(this.swigCPtr, this, GroupDetailInfoVec.getCPtr(groupDetailInfoVec), groupDetailInfoVec);
        }
    }

    public void fail(int i, String str) {
        if (getClass() == IGroupInfoListCallback.class) {
            internalJNI.IGroupInfoListCallback_fail(this.swigCPtr, this, i, str);
        } else {
            internalJNI.IGroupInfoListCallback_failSwigExplicitIGroupInfoListCallback(this.swigCPtr, this, i, str);
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
        internalJNI.IGroupInfoListCallback_change_ownership(this, this.swigCPtr, false);
    }

    public void swigTakeOwnership() {
        this.swigCMemOwn = true;
        internalJNI.IGroupInfoListCallback_change_ownership(this, this.swigCPtr, true);
    }
}
