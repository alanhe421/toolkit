package com.tencent.imcore;

public class IGroupMemberResultCallback {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public IGroupMemberResultCallback() {
        this(internalJNI.new_IGroupMemberResultCallback(), true);
        internalJNI.IGroupMemberResultCallback_director_connect(this, this.swigCPtr, this.swigCMemOwn, true);
    }

    protected IGroupMemberResultCallback(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(IGroupMemberResultCallback iGroupMemberResultCallback) {
        return iGroupMemberResultCallback == null ? 0 : iGroupMemberResultCallback.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_IGroupMemberResultCallback(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void done(GroupMemberResultVec groupMemberResultVec) {
        if (getClass() == IGroupMemberResultCallback.class) {
            internalJNI.IGroupMemberResultCallback_done(this.swigCPtr, this, GroupMemberResultVec.getCPtr(groupMemberResultVec), groupMemberResultVec);
        } else {
            internalJNI.IGroupMemberResultCallback_doneSwigExplicitIGroupMemberResultCallback(this.swigCPtr, this, GroupMemberResultVec.getCPtr(groupMemberResultVec), groupMemberResultVec);
        }
    }

    public void fail(int i, String str) {
        if (getClass() == IGroupMemberResultCallback.class) {
            internalJNI.IGroupMemberResultCallback_fail(this.swigCPtr, this, i, str);
        } else {
            internalJNI.IGroupMemberResultCallback_failSwigExplicitIGroupMemberResultCallback(this.swigCPtr, this, i, str);
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
        internalJNI.IGroupMemberResultCallback_change_ownership(this, this.swigCPtr, false);
    }

    public void swigTakeOwnership() {
        this.swigCMemOwn = true;
        internalJNI.IGroupMemberResultCallback_change_ownership(this, this.swigCPtr, true);
    }
}
