package com.tencent.imcore;

public class IGroupNotifyCallback {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public IGroupNotifyCallback() {
        this(internalJNI.new_IGroupNotifyCallback(), true);
        internalJNI.IGroupNotifyCallback_director_connect(this, this.swigCPtr, this.swigCMemOwn, true);
    }

    protected IGroupNotifyCallback(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(IGroupNotifyCallback iGroupNotifyCallback) {
        return iGroupNotifyCallback == null ? 0 : iGroupNotifyCallback.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_IGroupNotifyCallback(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void done() {
        if (getClass() == IGroupNotifyCallback.class) {
            internalJNI.IGroupNotifyCallback_done(this.swigCPtr, this);
        } else {
            internalJNI.IGroupNotifyCallback_doneSwigExplicitIGroupNotifyCallback(this.swigCPtr, this);
        }
    }

    public void fail(int i, String str) {
        if (getClass() == IGroupNotifyCallback.class) {
            internalJNI.IGroupNotifyCallback_fail(this.swigCPtr, this, i, str);
        } else {
            internalJNI.IGroupNotifyCallback_failSwigExplicitIGroupNotifyCallback(this.swigCPtr, this, i, str);
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
        internalJNI.IGroupNotifyCallback_change_ownership(this, this.swigCPtr, false);
    }

    public void swigTakeOwnership() {
        this.swigCMemOwn = true;
        internalJNI.IGroupNotifyCallback_change_ownership(this, this.swigCPtr, true);
    }
}
