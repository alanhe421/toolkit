package com.tencent.imcore;

public class IGroupTipsEventCallback {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public IGroupTipsEventCallback() {
        this(internalJNI.new_IGroupTipsEventCallback(), true);
        internalJNI.IGroupTipsEventCallback_director_connect(this, this.swigCPtr, this.swigCMemOwn, true);
    }

    protected IGroupTipsEventCallback(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(IGroupTipsEventCallback iGroupTipsEventCallback) {
        return iGroupTipsEventCallback == null ? 0 : iGroupTipsEventCallback.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_IGroupTipsEventCallback(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public void onGroupTipsEvent(GroupTipsElem groupTipsElem) {
        if (getClass() == IGroupTipsEventCallback.class) {
            internalJNI.IGroupTipsEventCallback_onGroupTipsEvent(this.swigCPtr, this, GroupTipsElem.getCPtr(groupTipsElem), groupTipsElem);
        } else {
            internalJNI.IGroupTipsEventCallback_onGroupTipsEventSwigExplicitIGroupTipsEventCallback(this.swigCPtr, this, GroupTipsElem.getCPtr(groupTipsElem), groupTipsElem);
        }
    }

    protected void swigDirectorDisconnect() {
        this.swigCMemOwn = false;
        delete();
    }

    public void swigReleaseOwnership() {
        this.swigCMemOwn = false;
        internalJNI.IGroupTipsEventCallback_change_ownership(this, this.swigCPtr, false);
    }

    public void swigTakeOwnership() {
        this.swigCMemOwn = true;
        internalJNI.IGroupTipsEventCallback_change_ownership(this, this.swigCPtr, true);
    }
}
