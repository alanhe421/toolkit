package com.tencent.imcore;

public class IGroupUpdateCallback {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public IGroupUpdateCallback() {
        this(internalJNI.new_IGroupUpdateCallback(), true);
        internalJNI.IGroupUpdateCallback_director_connect(this, this.swigCPtr, this.swigCMemOwn, true);
    }

    protected IGroupUpdateCallback(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(IGroupUpdateCallback iGroupUpdateCallback) {
        return iGroupUpdateCallback == null ? 0 : iGroupUpdateCallback.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_IGroupUpdateCallback(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public void onMembersUpdate(GroupTipsElem groupTipsElem) {
        if (getClass() == IGroupUpdateCallback.class) {
            internalJNI.IGroupUpdateCallback_onMembersUpdate(this.swigCPtr, this, GroupTipsElem.getCPtr(groupTipsElem), groupTipsElem);
        } else {
            internalJNI.IGroupUpdateCallback_onMembersUpdateSwigExplicitIGroupUpdateCallback(this.swigCPtr, this, GroupTipsElem.getCPtr(groupTipsElem), groupTipsElem);
        }
    }

    protected void swigDirectorDisconnect() {
        this.swigCMemOwn = false;
        delete();
    }

    public void swigReleaseOwnership() {
        this.swigCMemOwn = false;
        internalJNI.IGroupUpdateCallback_change_ownership(this, this.swigCPtr, false);
    }

    public void swigTakeOwnership() {
        this.swigCMemOwn = true;
        internalJNI.IGroupUpdateCallback_change_ownership(this, this.swigCPtr, true);
    }
}
