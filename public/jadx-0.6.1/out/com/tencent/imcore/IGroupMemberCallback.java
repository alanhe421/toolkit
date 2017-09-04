package com.tencent.imcore;

public class IGroupMemberCallback {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public IGroupMemberCallback() {
        this(internalJNI.new_IGroupMemberCallback(), true);
        internalJNI.IGroupMemberCallback_director_connect(this, this.swigCPtr, this.swigCMemOwn, true);
    }

    protected IGroupMemberCallback(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(IGroupMemberCallback iGroupMemberCallback) {
        return iGroupMemberCallback == null ? 0 : iGroupMemberCallback.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_IGroupMemberCallback(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void done(GroupMemberInfoVec groupMemberInfoVec) {
        if (getClass() == IGroupMemberCallback.class) {
            internalJNI.IGroupMemberCallback_done(this.swigCPtr, this, GroupMemberInfoVec.getCPtr(groupMemberInfoVec), groupMemberInfoVec);
        } else {
            internalJNI.IGroupMemberCallback_doneSwigExplicitIGroupMemberCallback(this.swigCPtr, this, GroupMemberInfoVec.getCPtr(groupMemberInfoVec), groupMemberInfoVec);
        }
    }

    public void fail(int i, String str) {
        if (getClass() == IGroupMemberCallback.class) {
            internalJNI.IGroupMemberCallback_fail(this.swigCPtr, this, i, str);
        } else {
            internalJNI.IGroupMemberCallback_failSwigExplicitIGroupMemberCallback(this.swigCPtr, this, i, str);
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
        internalJNI.IGroupMemberCallback_change_ownership(this, this.swigCPtr, false);
    }

    public void swigTakeOwnership() {
        this.swigCMemOwn = true;
        internalJNI.IGroupMemberCallback_change_ownership(this, this.swigCPtr, true);
    }
}
