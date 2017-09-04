package com.tencent.imcore;

public class IGroupMemberCallbackV2 {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public IGroupMemberCallbackV2() {
        this(internalJNI.new_IGroupMemberCallbackV2(), true);
        internalJNI.IGroupMemberCallbackV2_director_connect(this, this.swigCPtr, this.swigCMemOwn, true);
    }

    protected IGroupMemberCallbackV2(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(IGroupMemberCallbackV2 iGroupMemberCallbackV2) {
        return iGroupMemberCallbackV2 == null ? 0 : iGroupMemberCallbackV2.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_IGroupMemberCallbackV2(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void done(long j, GroupMemberInfoVec groupMemberInfoVec) {
        if (getClass() == IGroupMemberCallbackV2.class) {
            internalJNI.IGroupMemberCallbackV2_done(this.swigCPtr, this, j, GroupMemberInfoVec.getCPtr(groupMemberInfoVec), groupMemberInfoVec);
            return;
        }
        internalJNI.IGroupMemberCallbackV2_doneSwigExplicitIGroupMemberCallbackV2(this.swigCPtr, this, j, GroupMemberInfoVec.getCPtr(groupMemberInfoVec), groupMemberInfoVec);
    }

    public void fail(int i, String str) {
        if (getClass() == IGroupMemberCallbackV2.class) {
            internalJNI.IGroupMemberCallbackV2_fail(this.swigCPtr, this, i, str);
        } else {
            internalJNI.IGroupMemberCallbackV2_failSwigExplicitIGroupMemberCallbackV2(this.swigCPtr, this, i, str);
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
        internalJNI.IGroupMemberCallbackV2_change_ownership(this, this.swigCPtr, false);
    }

    public void swigTakeOwnership() {
        this.swigCMemOwn = true;
        internalJNI.IGroupMemberCallbackV2_change_ownership(this, this.swigCPtr, true);
    }
}
