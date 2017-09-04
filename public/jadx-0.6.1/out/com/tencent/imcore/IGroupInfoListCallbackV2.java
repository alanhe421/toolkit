package com.tencent.imcore;

public class IGroupInfoListCallbackV2 {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public IGroupInfoListCallbackV2() {
        this(internalJNI.new_IGroupInfoListCallbackV2(), true);
        internalJNI.IGroupInfoListCallbackV2_director_connect(this, this.swigCPtr, this.swigCMemOwn, true);
    }

    protected IGroupInfoListCallbackV2(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(IGroupInfoListCallbackV2 iGroupInfoListCallbackV2) {
        return iGroupInfoListCallbackV2 == null ? 0 : iGroupInfoListCallbackV2.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_IGroupInfoListCallbackV2(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void done(long j, GroupDetailInfoVec groupDetailInfoVec) {
        if (getClass() == IGroupInfoListCallbackV2.class) {
            internalJNI.IGroupInfoListCallbackV2_done(this.swigCPtr, this, j, GroupDetailInfoVec.getCPtr(groupDetailInfoVec), groupDetailInfoVec);
            return;
        }
        internalJNI.IGroupInfoListCallbackV2_doneSwigExplicitIGroupInfoListCallbackV2(this.swigCPtr, this, j, GroupDetailInfoVec.getCPtr(groupDetailInfoVec), groupDetailInfoVec);
    }

    public void fail(int i, String str) {
        if (getClass() == IGroupInfoListCallbackV2.class) {
            internalJNI.IGroupInfoListCallbackV2_fail(this.swigCPtr, this, i, str);
        } else {
            internalJNI.IGroupInfoListCallbackV2_failSwigExplicitIGroupInfoListCallbackV2(this.swigCPtr, this, i, str);
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
        internalJNI.IGroupInfoListCallbackV2_change_ownership(this, this.swigCPtr, false);
    }

    public void swigTakeOwnership() {
        this.swigCMemOwn = true;
        internalJNI.IGroupInfoListCallbackV2_change_ownership(this, this.swigCPtr, true);
    }
}
