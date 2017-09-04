package com.tencent.imcore;

public class IGroupGetPendencyCallback {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public IGroupGetPendencyCallback() {
        this(internalJNI.new_IGroupGetPendencyCallback(), true);
        internalJNI.IGroupGetPendencyCallback_director_connect(this, this.swigCPtr, this.swigCMemOwn, true);
    }

    protected IGroupGetPendencyCallback(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(IGroupGetPendencyCallback iGroupGetPendencyCallback) {
        return iGroupGetPendencyCallback == null ? 0 : iGroupGetPendencyCallback.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_IGroupGetPendencyCallback(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void done(GroupPendencyMeta groupPendencyMeta, GroupPendencyItemVec groupPendencyItemVec) {
        if (getClass() == IGroupGetPendencyCallback.class) {
            internalJNI.IGroupGetPendencyCallback_done(this.swigCPtr, this, GroupPendencyMeta.getCPtr(groupPendencyMeta), groupPendencyMeta, GroupPendencyItemVec.getCPtr(groupPendencyItemVec), groupPendencyItemVec);
            return;
        }
        internalJNI.IGroupGetPendencyCallback_doneSwigExplicitIGroupGetPendencyCallback(this.swigCPtr, this, GroupPendencyMeta.getCPtr(groupPendencyMeta), groupPendencyMeta, GroupPendencyItemVec.getCPtr(groupPendencyItemVec), groupPendencyItemVec);
    }

    public void fail(int i, String str) {
        if (getClass() == IGroupGetPendencyCallback.class) {
            internalJNI.IGroupGetPendencyCallback_fail(this.swigCPtr, this, i, str);
        } else {
            internalJNI.IGroupGetPendencyCallback_failSwigExplicitIGroupGetPendencyCallback(this.swigCPtr, this, i, str);
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
        internalJNI.IGroupGetPendencyCallback_change_ownership(this, this.swigCPtr, false);
    }

    public void swigTakeOwnership() {
        this.swigCMemOwn = true;
        internalJNI.IGroupGetPendencyCallback_change_ownership(this, this.swigCPtr, true);
    }
}
