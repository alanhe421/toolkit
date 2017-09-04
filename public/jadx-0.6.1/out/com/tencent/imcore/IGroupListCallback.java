package com.tencent.imcore;

public class IGroupListCallback {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public IGroupListCallback() {
        this(internalJNI.new_IGroupListCallback(), true);
        internalJNI.IGroupListCallback_director_connect(this, this.swigCPtr, this.swigCMemOwn, true);
    }

    protected IGroupListCallback(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(IGroupListCallback iGroupListCallback) {
        return iGroupListCallback == null ? 0 : iGroupListCallback.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_IGroupListCallback(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void done(GroupBaseInfoVec groupBaseInfoVec) {
        if (getClass() == IGroupListCallback.class) {
            internalJNI.IGroupListCallback_done(this.swigCPtr, this, GroupBaseInfoVec.getCPtr(groupBaseInfoVec), groupBaseInfoVec);
        } else {
            internalJNI.IGroupListCallback_doneSwigExplicitIGroupListCallback(this.swigCPtr, this, GroupBaseInfoVec.getCPtr(groupBaseInfoVec), groupBaseInfoVec);
        }
    }

    public void fail(int i, String str) {
        if (getClass() == IGroupListCallback.class) {
            internalJNI.IGroupListCallback_fail(this.swigCPtr, this, i, str);
        } else {
            internalJNI.IGroupListCallback_failSwigExplicitIGroupListCallback(this.swigCPtr, this, i, str);
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
        internalJNI.IGroupListCallback_change_ownership(this, this.swigCPtr, false);
    }

    public void swigTakeOwnership() {
        this.swigCMemOwn = true;
        internalJNI.IGroupListCallback_change_ownership(this, this.swigCPtr, true);
    }
}
